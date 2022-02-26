package com.lqs.controller;


import com.lqs.domain.User;
import com.lqs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/find")
    public List<User> findall(){
        List<User> userList = userService.find();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }



}
