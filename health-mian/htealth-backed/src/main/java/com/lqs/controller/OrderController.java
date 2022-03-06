package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.OrderService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.pojo.OrderSetting;
import com.lqs.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Reference(version = "1.0")
    private OrderService orderService;

    @PostMapping(value = "upload.do")
    public Result orderInfoUpload(@RequestParam("excelFile")MultipartFile excelFile) throws IOException {
        // 通过excel报表工具类，自定义的方法，读取excel文件
        try{

            List<String[]> readExcel = POIUtils.readExcel(excelFile);
//        System.out.println(readExcel);
            // 用来存放遍历后的结果,封装
            List<OrderSetting> orderSettingList = new ArrayList<>();
            for (String[] data : readExcel) {
                int number = Integer.parseInt(data[1]);
                OrderSetting orderSetting = new OrderSetting(new Date(data[0]), number);
//            System.out.println(orderSetting);
                orderSettingList.add(orderSetting);
            }
            // 进行存储或者修改
            orderService.excelInfoUpload(orderSettingList);

            return  new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }


    @PostMapping(value = "getOrderSettingByMonth.do")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> resultList = orderService.getOrderSettingBy(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, resultList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }



    @PostMapping(value = "editNumberByDate.do")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderService.editNumberByDate(orderSetting);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }


}
