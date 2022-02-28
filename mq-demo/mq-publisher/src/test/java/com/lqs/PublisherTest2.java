package com.lqs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

// amqp方式的publisher

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherTest2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "queue1";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }


    @Test
    public void testSendMessage2WorkQueue() {
        String queueName = "queue1";
        String message = "hello, spring amqp!";
        //  同时发送50个消息
        for (int i = 1; i <= 50; i++){
            rabbitTemplate.convertAndSend(queueName, message+i);
        }
    }
    @Test
    public void testFanout(){
        String exchangeName = "fanout";
        String msg = "hahahahaha";

        rabbitTemplate.convertAndSend(exchangeName,"",msg);
    }

    @Test
    public void testDirect(){
        String exchangeName = "direct";
        String msg = "yellow";

        rabbitTemplate.convertAndSend(exchangeName,"red",msg);
    }

    @Test
    public void testTopic(){
        String exchangeName = "topic";
        String msg = "hello";

        rabbitTemplate.convertAndSend(exchangeName,"abc.news",msg);
    }


    @Test
    public void testConverter(){
        String queueName = "queue3";
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "李奇凇");
        msg.put("age",20);

        rabbitTemplate.convertAndSend(queueName,msg);
    }







}
