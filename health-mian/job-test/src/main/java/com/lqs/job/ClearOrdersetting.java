package com.lqs.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.OrderService;

public class ClearOrdersetting {

    @Reference(version = "1.0")
    private OrderService orderService;

    public void clearOrderSetting(){
        System.out.println("清除过期的ordersetting");
        try{
            orderService.deleteOrderSetting();
            System.out.println("清除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("清除失败");
        }
    }



}
