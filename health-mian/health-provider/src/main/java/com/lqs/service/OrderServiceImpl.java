package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.api.OrderService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.mapper.MemberMapper;
import com.lqs.mapper.OrderMapper;
import com.lqs.mapper.SetmealMapper;
import com.lqs.pojo.Member;
import com.lqs.pojo.Order;
import com.lqs.pojo.OrderSetting;
import com.lqs.pojo.Setmeal;
import com.lqs.utils.DateUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service(interfaceClass = OrderService.class, version = "1.0")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void excelInfoUpload(List<OrderSetting> orderSettingList) throws IOException {
        // 如果表中有这个日期，那么就是修改，如果没有，就是添加
        // 或者直接全部删除，然后在添加
        if(orderSettingList.size()>0 && orderSettingList != null){
            // 先删除所有
            orderMapper.deleteOrderSetting();
            for (OrderSetting orderSetting : orderSettingList) {
                // 统计这个对象中的日期在数据库中的个数，如果为0，就添加，如果非零就修改
//                Integer count = orderMapper.findDateCount(orderSetting.getOrderDate());
//                if(count > 0){
//                    orderMapper.editOrderSetting(orderSetting);
//                }else {
//                    orderMapper.addOrderSetting(orderSetting);
//                }

               // 删除之后在添加
                orderMapper.addOrderSetting(orderSetting);
            }
        }
    }

    @Override
    public List<Map> getOrderSettingBy(String date) {
        String begin = date + "-1";
        // 没有31也能插队
        String end = date + "-30";
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("begin", begin);
        queryMap.put("end", end);
        // 调用mapper根据日期查询预定设置的数据
        List<OrderSetting> orderSettingByMonthList = orderMapper.getOrderSettingByMonth(queryMap);
        // 封装最终得到的数据
        List<Map> resultList = new ArrayList<>();
        if (orderSettingByMonthList.size() > 0 && orderSettingByMonthList != null){
            for (OrderSetting orderSetting : orderSettingByMonthList) {
                Map<String, Object> m = new HashMap<>();
                m.put("date", orderSetting.getOrderDate().getDate());
                m.put("number", orderSetting.getNumber());
                m.put("reservations", orderSetting.getReservations());
                resultList.add(m);
            }
        }
        return resultList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        // 设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatdate = simpleDateFormat.format(orderSetting.getOrderDate());
        // 修改数据
        Map<String, Object> editMap = new HashMap<>();
        editMap.put("orderDate", formatdate);
        editMap.put("number", orderSetting.getNumber());

        // 查询是否是没有预定设置的日期，如果是，那么创建
        Integer dateCount = orderMapper.findDateCount(formatdate);
        if (dateCount > 0){
            orderMapper.editOrderSetting(editMap);
        }else {
            orderMapper.addOrderSetting(orderSetting);
        }
    }

    @Override
    public Result addOrder(Map map) throws Exception {

        /**
         1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
         2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
         3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
         4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
         5、预约成功，更新当日的已预约人数
         */

        //获取日期
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderMapper.findByOrderDate(date);//根据日期查询预约设置信息
        if(orderSetting == null){
            //所选日期没有提前进行预约设置，不能完成预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        if(orderSetting.getReservations() >= orderSetting.getNumber()){
            //所选日期已经约满，无法预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }

        //判断是否在重复预约
        String telephone = (String) map.get("telephone");
        Member member = memberMapper.findByTelephone(telephone);
        if(member != null){
            Integer memberId = member.getId();//会员id
            Integer setmealId = Integer.parseInt((String)map.get("setmealId"));//套餐id
            Order order = new Order(memberId,date,setmealId);
            //  同一个人同一天预约的列表
            List<Order> orderList = orderMapper.findByCondition(order);
            if(orderList != null && orderList.size() > 0){
                //用户在重复预约，不能完成预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }

        if(member == null){
            //当前用户不是会员，需要自动完成注册
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
                member.setRegTime(new Date());
            memberMapper.add(member);
        }

        //保存预约信息
        Order order = new Order(member.getId(),
                date,
                (String)map.get("orderType"),
                Order.ORDERSTATUS_NO,
                Integer.parseInt((String) map.get("setmealId")));
        orderMapper.add(order);

        //更新已预约人数
//        orderSetting.setReservations(orderSetting.getReservations() + 1);
//        orderMapper.editReservationsByOrderDate(orderSetting);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(date);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("orderDate", dateString);
        map1.put("reservations", orderSetting.getReservations() + 1);
        orderMapper.editReservationsByOrderDate(map1);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        // 查出这个预定
        Order order = orderMapper.findById(id);
        // 根据setmealId查出套餐
        Setmeal setmeal = setmealMapper.findById(order.getSetmealId());
        // 根据memberId查出会员信息
        Member member = memberMapper.findById(order.getMemberId());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("member", member.getPhoneNumber());
        resultMap.put("setmeal", setmeal.getName());
        resultMap.put("orderDate", order.getOrderDate());
        resultMap.put("orderType", order.getOrderType());

        return resultMap;
    }

}
