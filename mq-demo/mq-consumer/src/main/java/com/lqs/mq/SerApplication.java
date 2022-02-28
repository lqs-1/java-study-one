package com.lqs.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@Component
public class SerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SerApplication.class,args);

    }



}
