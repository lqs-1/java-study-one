package com.lqs.mq.hehe.exchange.example;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Direct {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue1"),
            exchange = @Exchange("direct"),
            key = {"yellow", "red"}
    ))
    public void queue1(String msg){
        System.out.println(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue2"),
            exchange = @Exchange("direct"),
            key = {"blue", "red"}
    ))
    public void queue2(String msg){
        System.out.println(msg);
    }


}
