package com.lqs.mq.hehe;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;


// amqp方式的consumer
// 加入spring容器管理
@Component
public class Mqee {



    /*
    * 默认方式是预取所有的消息到消费者，一个一个的拿，你一个我一个
    * 这种方式有一个弊端，只会在消息拿完之后在进行处理，在同服务多消费者的情况下，就算设置了时间等待，也是一个一个的拿平均分，也就是预取值是所有
    * 解决这个问题就是把预取值设置为比较小的数字，比如设置成1，这样就是拿一个处理一个，然后再去拿，这个时候，就会根据等待时间来合理分配消息
    * prefetch=1
    * 这个demo是单个队列对应多个或者一个处理者的方式，没用到exchange
    *
    * */

    // 性能好多处理一点
    @RabbitListener(queues = "queue1")
    public void test(String msg) throws InterruptedException {
        System.out.println(msg+ ",时间:"+LocalTime.now());
        Thread.sleep(20);

    }
    // 性能差，少处理一点
    @RabbitListener(queues = "queue1")
    public void test2(String msg) throws InterruptedException {
        System.err.println(msg+",时间:"+LocalTime.now());
        Thread.sleep(200);

    }

    // 转换器测试
    @RabbitListener(queues = "queue3")
    public void test3(Map<String, Object> msg){
        System.out.println(msg);
    }
}
