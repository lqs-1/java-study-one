package com.lqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLoggerApplication {

    public static void main(String[] args) {
//        关闭热部署
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SpringBootLoggerApplication.class, args);
    }

}
