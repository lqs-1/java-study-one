package com.lqs.controller;


import com.lqs.domain.User;
import com.lqs.domain.Vo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller

// 获取请求参数
@RequestMapping(value = "get")
public class GetBaseParams {

    // 获取前端传递的普通键值对参数
    @RequestMapping(value = "index")
    @ResponseBody
    public String save(String username, int age){
        return username+age;
    }

    // 获取pojo对象,参数名的个数和实体类的参数个数相同，参数名也是相同的
    @RequestMapping(value = "index2")
    @ResponseBody
    public User save2(User user) {
        return user;
    }


    // 获取数组类型的参数，用于请求参数中有多个同名的参数，springMvc会自动的将键相同的封装到一个数组中
    @RequestMapping(value = "index3")
    @ResponseBody
    public List save3(String[] strs) {
        return Arrays.asList(strs);
    }

    // 获取集合类型的参数，借助于view对象来封装，需要一个pojo对象，这个对象用来封装集合
    // 获取集合类型的参数，也就是获取多个对象，在获取之后，将这些对象以键值对的方式封装到集合对象中
    @RequestMapping(value = "index4")
    @ResponseBody
    public List<User> save4(Vo vo) {
        System.out.println(vo);
        System.out.println(vo.getListUser());
        return vo.getListUser();
    }


    // 获取集合类型的参数二，如果前端发送得是ajax请求的话，并且在前端发送的application是json类型的数据，name就可以直接用形参来接，使用@RequestBody注解，参数名字必须一致，
    // 就是从请求体中获取相对应的参数，组织一个集合，并且封装到集合中
    @RequestMapping(value = "index5")
    @ResponseBody
    public List<User> save5(@RequestBody List<User> userList) {
        System.out.println(userList);
        return userList;
    }
    // RequestParam,来获取参数
    @RequestMapping(value = "index6")
    @ResponseBody
    // value表示被传递过来的参数的名字，用于和后面的类型变量映射
    // required表示是否必须传递，默认是true,defaultValue表示如果不传递，name就给定默认值
    public String save6(@RequestParam(value = "name", required = false, defaultValue = "lqs") String username){
        return username;
    }

    // 使用restful风格
    // 在地址后面用大括号书写占位符，就不用在请求地址中用？这种传统方式了,django和flask相同
    @RequestMapping(value = "index7/{name}")
    @ResponseBody
    // 使用PathVariable注解和占位符绑定，必须和占位符的名称一致
    public String save7(@PathVariable("name") String username){
        return username;
    }

    // 使用转换器
    // 在传递日期的时候，每个人的书写格式可能由不同，所以使用转换器，转成一样的
    @RequestMapping(value = "index8/")
    @ResponseBody
    public Date save8(Date date){
        return date;
    }

    // 获取请求头里面的参数RequestHeader注解
    @RequestMapping("index9")
    @ResponseBody
    public String save9(@RequestHeader(value = "User-Agent", required = false) String user_agent){
        return user_agent;
    }

    // 获取cookie使用CookieValue注解
    @RequestMapping("index10")
    @ResponseBody
    public String save10(@CookieValue(value = "JSESSIONID", required = true) String jsessionid){
        return jsessionid;
    }


}
