package com.lqs.mq.hehe;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


// amqp方式的consumer

@Component
public class Mqee {


    @RabbitListener(queues = "queue1")
    public void test(String msg){
        System.out.println(msg);

    }
}
