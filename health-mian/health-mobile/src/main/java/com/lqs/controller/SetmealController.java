package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.lqs.api.SetmealService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.pojo.Order;
import com.lqs.pojo.Setmeal;
import com.lqs.utils.SMSUtils;
import com.lqs.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping(value = "setmeal")
public class SetmealController {

    @Reference(version = "1.0")
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;


    @GetMapping(value = "getSetmeal.do")
    public Result getAllSetmeal(){

        try {
            List<Setmeal> setmealList = setmealService.findAllSetmeal();
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }


    @GetMapping(value = "findById.do")
    public Result findSetmealById(@RequestParam("id") Integer id){
        try{
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }


    // 发送验证码
    @GetMapping(value = "getSmsCode.do")
    public Result getSmsCode(String telephone) {
         // 先判断是否在60秒内redis中有过验证码
        String isHas;
        try{
            isHas = jedisPool.getResource().get(telephone);
        }catch (Exception e){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        if (isHas != null){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FREQUENTLY);
        }
        // 发送验证码
        String smsCode = ValidateCodeUtils.generateValidateCode4String(4);
        try {
//            setmealService.getSmsCode(telephone, smsCode);
            Result result = SMSUtils.sendShortMessage(telephone, smsCode);
//            // 发送成功，存入数据库，使用字符串类型
            String redisSms = jedisPool.getResource().setex(telephone, 300, smsCode);
            return result;
        }catch (Exception e){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }



}
