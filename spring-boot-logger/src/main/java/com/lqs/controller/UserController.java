package com.lqs.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 日志配置第三种lombok的注解，logger对象就是log
@Slf4j
@RestController
@RequestMapping("users")
// 日志方法二， 继承一个基本的log配置
//public class UserController extends BaseLogger{
//    日志第一种，每一个Controller都实例化一个
//    private Logger log = LoggerFactory.getLogger(UserController.class);

public class UserController extends BaseLogger{

    @GetMapping
    public String test(){
        log.debug("haha");
        log.info("haa");
        log.warn("hah");
        log.error("hahah");
        return "hah122";
    }

    @PostMapping
    public String test1(){
        return "haha2";
    }
}
