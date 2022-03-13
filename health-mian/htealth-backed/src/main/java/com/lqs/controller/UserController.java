package com.lqs.controller;

/*
* 用户操作
* */


import com.lqs.constant.MessageConstant;
import com.lqs.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    /*登录成功后，要向页面会写数据，页面不用传递参数过来，这个操作和django很相似，只要用户登录成功，框架就会将用户信息保存到session中*/
    /*
    * django：在Django的auth框架认证之后，就会将信息保存到session中，通过请求对象获取，user = request.user
    * flask：用户登录成功之后，将用户信息保存到session中，如果是前后端分离的情况，那么每次加载，就会重新发送请求
    * springSecurity：认证完成后，会将用户信息，保存到框架提供的上下文对象SecurityContextHolder.getContext().getAuthentication().getPrincipal()，底层基于session
    *
    * */
    @GetMapping("getUserName.do")
    public Result getUserName(){
        // 获取错来的是一个User对象。是框架提供的User对象
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user != null){
            // 获取当前用户的用户名
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        }

        return new Result(false, MessageConstant.GET_USERNAME_FAIL);
    }



}
