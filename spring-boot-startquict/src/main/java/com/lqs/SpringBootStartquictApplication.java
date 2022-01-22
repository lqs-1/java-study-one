package com.lqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
* springboot的启动文件，就如同django和flask一样，可以用命令来启动
* @SpringBootApplication: 这个注解表明这个类是一个springboot的应用上下文文件
*
* SpringApplication.run(SpringBootStartquictApplication.class, args); 就是调用spring的容器，里面同样存放的是bean这些东西
*
* */
@SpringBootApplication
public class SpringBootStartquictApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStartquictApplication.class, args);
    }
}
