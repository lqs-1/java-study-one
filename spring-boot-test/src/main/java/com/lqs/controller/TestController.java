package com.lqs.controller;


import com.lqs.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

@RestController
@RequestMapping("tests")
public class TestController {

    @GetMapping
    public String testController(){
        System.out.println("run is success.....");
        return "success";
    }

    @GetMapping("1")
    public User testController1(){
        System.out.println("run is success.....");
        User user = new User();
        user.setId(1);
        user.setName("lqs");
        return user;
    }


}
