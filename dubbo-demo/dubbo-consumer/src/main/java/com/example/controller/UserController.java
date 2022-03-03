package com.example.controller;

import com.example.domain.User;
import com.example.serviceimpl.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Reference
    private UserService userService;
    @RequestMapping(value = "/find")
    public String findUser(){
        System.out.println(userService.findAll());
        return userService.findAll().toString();
    }

}
