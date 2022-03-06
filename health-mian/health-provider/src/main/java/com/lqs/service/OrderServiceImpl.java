package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.api.OrderService;
import com.lqs.mapper.OrderMapper;
import com.lqs.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderService.class, version = "1.0")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

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
}
