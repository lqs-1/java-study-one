package com.lqs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.entity.Result;
import com.lqs.pojo.CheckItem;
import com.lqs.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @PostMapping(value = "/add.do")
    public Result addCheckItem(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }


}
