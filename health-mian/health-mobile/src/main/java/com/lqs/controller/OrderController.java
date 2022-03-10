package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.OrderService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Reference(version = "1.0")
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;



    // 保存预定信息
    @PostMapping(value = "order.do")
    public Result order(@RequestBody Map map){
        // 校验验证码是否正确
        String telephone = (String) map.get("telephone");
        String smsCode = (String) map.get("validateCode");
        try{
            String realSmsCode = jedisPool.getResource().get(telephone);
            if(smsCode == null  && smsCode != realSmsCode){
//                System.out.println("pl");
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //设置订单信息的预定方式
        map.put("orderType", Order.ORDERTYPE_WEIXIN);
        try{
            Result result = orderService.addOrder(map);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    @GetMapping(value = "findById.do")
    public Result findById(@RequestParam("id") Integer id){
//        System.out.println(id);
        try {
            Map<String, Object> orderInfo = orderService.findById(id);
//            System.out.println(order.getSetmealId());
//            System.out.println(order.getMemberId());
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderInfo);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }


}
