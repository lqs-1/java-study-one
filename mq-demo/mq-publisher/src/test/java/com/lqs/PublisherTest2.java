package com.lqs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


}
