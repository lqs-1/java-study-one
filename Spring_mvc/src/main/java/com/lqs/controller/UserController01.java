package com.lqs.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqs.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller


// 回写数据的demo类
@RequestMapping(value = "goods")
public class UserController01 {

    // 回写字符串,原始方式
    @RequestMapping(value = "index")
    public void save(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello first");
    }

    // 回写字符串,mvc，推荐
    @RequestMapping(value = "index2")
    @ResponseBody
    public String save2() {
        /*
        * 直接回写，要和寻找模板区分开，用ResponseBody注解来区分
        * 不进行页面跳转，直接回写
        * */
        return "hello itheima";
    }

    // 回写json数据,利用json封装工具
    @RequestMapping(value = "index3")
    @ResponseBody
    public String save3() throws JsonProcessingException {
        User user = new User();
        user.setUsername("李奇凇");
        user.setAge(20);
        // 使用json的转换工具将对象转换成json格式字符串再返回
        ObjectMapper om = new ObjectMapper();
        String json_data = om.writeValueAsString(user);
        return json_data;
    }


    // 回写json数据,springMvc框架的适配器，适配器自己配置
    @RequestMapping(value = "index4")
    @ResponseBody
    public User save4() throws JsonProcessingException {
        User user = new User();
        user.setUsername("李奇凇");
        user.setAge(20);
        // 使用json的转换工具将对象转换成json格式字符串再返回
        return user;
    }

}
