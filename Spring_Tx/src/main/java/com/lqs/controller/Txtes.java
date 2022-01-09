package com.lqs.controller;

import com.lqs.service.UserService;
import com.lqs.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Txtes {

    @Autowired
    UserServiceImpl userService;


    public static void main(String[] args) {

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean(UserService.class);
        userService.update("lqs", "lss", 200);
    }
}
