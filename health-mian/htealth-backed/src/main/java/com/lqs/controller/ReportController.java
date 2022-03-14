package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.ReportService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "report")
public class ReportController {
    @Reference(version = "1.0")
    private ReportService reportService;

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




}
