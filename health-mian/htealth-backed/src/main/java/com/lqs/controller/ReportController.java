package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.ReportService;
import com.lqs.api.SetmealService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "report")
public class ReportController {
    @Reference(version = "1.0")
    private ReportService reportService;

    @Reference(version = "1.0")
    private SetmealService setmealService;

    @GetMapping(value = "getMemberReport.do")
    public Result getMemberReport(){
        // 查询一年内会员的数量
        try{
            Result result = reportService.getMemberReport();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    @GetMapping(value = "getSetmealReport.do")
    public Result getSetmealReport(){
        // 套餐信息统计
        // 这个map用来存放所有的数据
        Map<String, Object> data = new HashMap<>();

        // 图例和具体饼图数据分开
        // 用来存储图例
        List<String> titleName = new ArrayList<>();
        // 用来存储饼图数据
        List<Map<String, Object>> resultData;
        try{
            resultData = setmealService.findSetMealCount();
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
        // 根据饼图数据抽出套餐名字
        for (Map<String, Object> resultDatum : resultData) {
            String name = (String) resultDatum.get("name");
            titleName.add(name);
        }
        data.put("setmealName", titleName);
        data.put("setmealCount", resultData);

        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, data);
    }


    @GetMapping(value = "getBusinessReportData.do")
    public Result getBusinessReportData(){
        // 获取的数据很多，运营数据
        try{
            Map<String, Object> resultMap = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }

    }


}
