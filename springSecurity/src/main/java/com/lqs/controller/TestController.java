package com.lqs.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "haha")
public class TestController {

    // security的注解可以控制到方法级别
    @RequestMapping(value = "hh")
    // 注解控制。这个方法只有add角色可以访问
    @PreAuthorize("hasRole('add')")
    public String tst1(){
        return "haha";
    }


}
