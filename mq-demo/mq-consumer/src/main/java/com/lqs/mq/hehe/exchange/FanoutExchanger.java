package com.lqs.mq.hehe.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 这个demo使用到exchange
* fanout类型的exchange就表示，将这个交换机收到的消息，广播到所有和他绑定的消息队列中
* */


// 这种方式是自己配置topic和direct都可以这样，但是 我用注解一步到位

@Configuration // 将exchange绑定信息传入spring配置
public class FanoutExchanger {
    // 创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout");
    }
    // 创建队列1
    @Bean
    public Queue queue1(){
        return new Queue("fanout.queue1");
    }
    // 创建队列2
    @Bean
    public Queue queue2(){
        return new Queue("fanout.queue2");
    }
    // 将队列1绑定到交换机
    @Bean
    public Binding bindingQueue1(Queue queue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    // 将队列2绑定到交换机
    @Bean
    public Binding bindingQueue2(Queue queue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

//    @Bean
//    public Queue queue3(){
//        return new Queue("queue3");
//    }


}
