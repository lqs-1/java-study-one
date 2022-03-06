package com.lqs.controller;


import com.lqs.domain.User;
import com.lqs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login")
//    @ResponseBody
    public ModelAndView dologin(User user, ModelAndView modelAndView){

        User authUser = userService.findByName(user);
        System.out.println(authUser);
        if (authUser == null){
            modelAndView.setViewName("fail");
            return modelAndView;
        }
            modelAndView.setViewName("success");
            modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "logout")
    public String dologout(HttpSession session){
        return "index";
    }
}
