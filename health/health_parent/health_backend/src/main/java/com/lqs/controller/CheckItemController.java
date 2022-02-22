package com.lqs.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import com.lqs.pojo.CheckItem;
import com.lqs.service.CheckItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 检查项管理
@RestController
@RequestMapping(value = "/checkitem")
public class CheckItemController {

    @Reference  // 到zookeeper服务注册中心查找服务
    private CheckItemService checkItemService;

    // 新增检查项
    @PostMapping(value = "/add")
    public Result addCheckItem(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //  失败返回数据
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        //  成功返回数据
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }





}
