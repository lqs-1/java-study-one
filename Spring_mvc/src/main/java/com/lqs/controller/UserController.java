package com.lqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller


// 返回页面,页面跳转的demo类
@RequestMapping( value = "/user", method = RequestMethod.GET)
public class UserController {

    @RequestMapping(value = "/index")
    public String save(){
        System.out.println("Controller save running....");
        return "success";
    }

    @RequestMapping(value = "/index2")
    public ModelAndView save1(){
        /*
        * Model:模型，作用封装数据
        * View:视图，作用展示数据
        * ModelAndView 对象可以不new，写在形参，自动注入，mvc框架注入
        * ModelAndView 对象是可以拆开的，返回字符串类型的View，只在方法中使用Model实例对象设置域参数
        * */
        ModelAndView modelAndView = new ModelAndView();
        // 设置模型数据，往域中设置数据
        modelAndView.addObject("username", "itcast");
        // 设置视图(获取模板)，资源文件
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/index3")
    public ModelAndView save3(ModelAndView modelAndView){
        // 设置模型数据，往域中设置数据
        modelAndView.addObject("username", "itheima");
        // 设置视图(获取模板)，资源文件
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/index4")
    public String save4(Model model){
        // 设置模型数据，往域中设置数据
        model.addAttribute("username", "lqs");
        return "index";
    }



    /*
    * 原始方式设置域参数
    * request对象
    * */
    @RequestMapping(value = "/index5")
    public String save5(HttpServletRequest request){
        // 设置模型数据，往域中设置数据
        request.setAttribute("username", "liqisong利器是哪个");
        return "index";
    }

}
