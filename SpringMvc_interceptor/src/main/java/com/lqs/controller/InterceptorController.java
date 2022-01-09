package com.lqs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "interceptor")
public class InterceptorController {

    @RequestMapping(value = "index")
    public ModelAndView index(ModelAndView modelAndView){
        System.out.println("index");
        modelAndView.addObject("info", "lqs");
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
