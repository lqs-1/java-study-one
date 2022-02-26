package com.lqs.controller;


import com.lqs.domain.User;
import com.lqs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/find")
    public List<User> findUser(){
        List<User> userList = userService.find();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

}
