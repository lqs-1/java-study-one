package com.lqs.mq.hehe.exchange.example;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
* 用注解配置
* 这种方式，等号后面全是注解，exchange后面要跟上type，不跟就默认是direct模式的交换机
* 三种模式：fanout,topic,direct
* fanout:这种模式是广播的，没有key，
* direct:这种模式是根据key来选择队列的，
* topic:这种模式是根据通配符加上key来实现选择队列的，通配符，*表示一个单词，#表示任意个单词，必须用.（点）区分，例如：队列和exchange的key是#.news,那么exchange就会将收到的消息给和自己绑定的所有的news结尾的队列
*
* 队列和exchange的绑定是放在consumer这边的，各种规则都定义在这边
* */



@Component
public class Topic {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue1"),
            exchange = @Exchange(value = "topic", type = "topic"),
            key = {"#.news", "abc.#"}
    ))
    public void queue1(String msg){
        System.out.println(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue2"),
            exchange = @Exchange(value = "topic", type = "topic"),
            key = {"china.#", "abc.#"}
    ))
    public void queue2(String msg){
        System.out.println(msg);
    }

}
