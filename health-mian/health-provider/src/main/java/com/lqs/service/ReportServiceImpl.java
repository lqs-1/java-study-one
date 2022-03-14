package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqs.api.ReportService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service(interfaceClass = ReportService.class, version = "1.0")
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;


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


    public static void main(String[] args) {
        // 获取从此刻开始的前12个月
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -12);
//        System.out.println(new SimpleDateFormat("yyyy-MM").format(time));
        for(int i=1; i<13; i++){
            Date time = calendar.getTime();
            calendar.add(Calendar.MONTH,1);
            System.out.println(new SimpleDateFormat("yyyy-MM").format(time));



        }
    }

}
