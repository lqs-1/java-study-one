package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.api.ReportService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.mapper.MemberMapper;
import com.lqs.mapper.OrderMapper;
import com.lqs.mapper.SetmealMapper;
import com.lqs.pojo.Setmeal;
import com.lqs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service(interfaceClass = ReportService.class, version = "1.0")
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SetmealMapper setmealMapper;


    @Override
    public Result getMemberReport() {
        // 定义一个要返回的data数据（字典）
        Map<String, List> dataMap = new HashMap<>();

        // 定义一个装日期的List
        List<String> months = new ArrayList();

        // 获取从此刻开始的前12个月
        Calendar calendar = Calendar.getInstance();
        // 从此刻开始往前推12个月，比如去年2月到今年1月，当月未完不算在内
        calendar.add(Calendar.MONTH, -12);
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy.MM");
        for(int i = 1; i < 13; i++){
            calendar.add(Calendar.MONTH,1);
            Date originData = calendar.getTime();
            String data = dataFormat.format(originData);
            months.add(data);
        }
        // 将年月数据放进map
        dataMap.put("months", months);


        // 定义一个装会员数量的List
        List<Integer> memberCount = new ArrayList<>();

        // 根据月份查询会员数量
        for (String month : months) {
            String monthData = month + ".28";
            Integer count = memberMapper.findByMonth(monthData);
            memberCount.add(count);
        }

        // 将截至每月的总人数数据放进map
        dataMap.put("memberCount", memberCount);

        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, dataMap);
    }



    @Override
    // 查询运营数据
    public Map<String, Object> getBusinessReportData() throws Exception {
        // 接数据
        Map<String, Object> resultMap = new HashMap<>();

        // 获取当前日期
        String todayDate = DateUtils.parseDate2String(new Date());
        // 获取当前周的第一天日期
        String thisWeekFirstDay = DateUtils.getThisWeekFirstDay();
        // 获取当前月的第一天的日期
        String thisMonthFirstDay = DateUtils.getThisMonthFirstDay();


        // 查询今天的新增会员数
        Integer todayNewMember = memberMapper.findTodayNewMemberCount(todayDate);
        // 查询总会员数
        Integer totalMember = memberMapper.findTotalMemberCount();
        // 查询本周新增会员数量
        Integer thisWeekNewMember = memberMapper.findThisWeekNewMemberCount(thisWeekFirstDay);
        // 查询本月新增会员数量
        Integer thisMonthNewMember = memberMapper.findThisMonthNewMemberCount(thisMonthFirstDay);
        // 查询今天的预约数量
        Integer todayOrderNumber = orderMapper.findTodayOrderCount(todayDate);
        // 查询今天到诊数量
        Integer todayVisitsNumber = orderMapper.findTodayVisitsCount(todayDate);
        // 查询本周预约数量
        Integer thisWeekOrderNumber = orderMapper.findThisWeekOrderCount(thisWeekFirstDay);
        // 查询本周到诊数量
        Integer thisWeekVisitsNumber = orderMapper.findThisWeekVisitsCount(thisWeekFirstDay);
        // 查询本月的预约数量
        Integer thisMonthOrderNumber = orderMapper.findThisMonthOrderCount(thisMonthFirstDay);
        // 查询本月到诊数量
        Integer thisMonthVisitsNumber = orderMapper.findThisMonthVisitsCount(thisMonthFirstDay);
        // 查询最火的前两个套餐,本月的
        List<Map<String, Object>> hotSetmeal = new ArrayList<>();
//        {name:'阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐',setmeal_count:200,proportion:0.222},
//        {name:'阳光爸妈升级肿瘤12项筛查体检套餐',setmeal_count:200,proportion:0.222}
//        select setmeal_id,count(setmeal_id) as count from t_order group by setmeal_id order by count desc limit 2;
        // 查询出做获得两个项目
        List<Map<String, Object>> hotSetmealOrderCount = orderMapper.findHotSetmealOrderCount();
        for (Map<String, Object> stringObjectMap : hotSetmealOrderCount) {
            Map<String, Object> result= new HashMap<>();
            // 获取套餐id
            Integer setmeal_id = (Integer) stringObjectMap.get("setmeal_id");
            // 根据套餐id查询套餐详情
            Setmeal setmeal = setmealMapper.findById(setmeal_id);
            // 获取套餐名字
            String name = setmeal.getName();
            // 获取套餐预约这个数量
            Object setmeal_count = stringObjectMap.get("setmeal_count");
            result.put("name", name);
            result.put("setmeal_count", setmeal_count);
            hotSetmeal.add(result);
        }

        // 放数据
        resultMap.put("reportDate", todayDate);
        resultMap.put("todayNewMember", todayNewMember);
        resultMap.put("totalMember", totalMember);
        resultMap.put("thisWeekNewMember", thisWeekNewMember);
        resultMap.put("thisMonthNewMember", thisMonthNewMember);
        resultMap.put("todayOrderNumber", todayOrderNumber);
        resultMap.put("todayVisitsNumber", todayVisitsNumber);
        resultMap.put("thisWeekOrderNumber", thisWeekOrderNumber);
        resultMap.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        resultMap.put("thisMonthOrderNumber", thisMonthOrderNumber);
        resultMap.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        resultMap.put("hotSetmeal", hotSetmeal);
        return resultMap;
    }



    public static void main(String[] args) {
        // 获取从此刻开始的前12个月
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -12);
//        System.out.println(new SimpleDateFormat("yyyy-MM").format(time));
//        for(int i=1; i<13; i++){
//            Date time = calendar.getTime();
//            calendar.add(Calendar.MONTH,1);
//            System.out.println(new SimpleDateFormat("yyyy-MM").format(time));

//        }



        // 获取当前这一周的第一天（星期一）和当前这一个月第一天的日期
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar ca = Calendar.getInstance();
        // 设置一周中的第一天是周几，中国是周一
//        ca.setFirstDayOfWeek(Calendar.MONDAY);
        // 获取今天是当前周的地几天
//        int firstDayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
        // 推算出周一的日期，当前这一周的第一天减去今天（1-x）
//        ca.add(Calendar.DATE, ca.getFirstDayOfWeek() - firstDayOfWeek);
//        System.out.println("这周周一：" + simpleDateFormat.format(ca.getTime()));
        // 根据当前月推算
//        ca.add(Calendar.MONTH, 0);
        // 第一天的日期
//        ca.set(Calendar.DAY_OF_MONTH,1);
//        System.out.println("这月第一天日期：" + simpleDateFormat.format(ca.getTime()));

    }
}
