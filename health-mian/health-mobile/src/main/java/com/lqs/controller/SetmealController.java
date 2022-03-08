package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.SetmealService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.pojo.Setmeal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "setmeal")
public class SetmealController {

    @Reference(version = "1.0")
    private SetmealService setmealService;


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



}
