package com.lqs.controller;

import com.lqs.domain.User;
import com.lqs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "save", method = {RequestMethod.POST}, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String save(User user) throws IOException {
        userService.save(user);
        return "成功";
    }

    @RequestMapping(value = "find")
    public ModelAndView find() throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        List<User> userList = userService.findAll();

        modelAndView.setViewName("find");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }




}
