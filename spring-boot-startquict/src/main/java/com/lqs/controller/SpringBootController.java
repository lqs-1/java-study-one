package com.lqs.controller;

import com.lqs.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "springboot")

/*
* 读取配置文件的方法：
*   第一种，通过value一个一个读
*   第二种，通过Environment类型获取全部
*   第三种，封装实体
* */


public class SpringBootController {
//    第一种，通过value一个一个读,可以直接打印
//    @Value("${country}")
//    private String country;
//    @Value("${country2.name}")
//    private String country2;
//    @Value("${list2[0].name}")
//    private String list1;

//    第二种，通过Environment类型获取全部
//    @Autowired
//    private Environment env;
//    System.out.println("ok...."+env.getProperty("country"));
//    System.out.println("ok...."+env.getProperty("country2.name"));
//    System.out.println("ok...."+env.getProperty("list2[0].name"));


//    第三种，封装实体
    @Autowired
    private DataSource dataSource;


    @GetMapping(value = "get")
    public String test(){
        System.out.println(dataSource);
        return "ok......";
    }
}
