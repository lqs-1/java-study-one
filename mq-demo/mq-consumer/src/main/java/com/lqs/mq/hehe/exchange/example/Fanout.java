package com.lqs.mq.hehe.exchange.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Fanout {

    @RabbitListener(queues = "fanout.queue1")
    public void queue1(String msg){
        System.out.println(msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void queue2(String msg){
        System.out.println(msg);
    }



}
