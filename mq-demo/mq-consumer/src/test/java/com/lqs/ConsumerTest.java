package com.lqs;


import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 官方consumer方式


public class ConsumerTest {
    @Test
    public void testConsumer() throws IOException, TimeoutException {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置链接参数
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("lqs");
        connectionFactory.setPassword("lqs");
        // 创建链接对象
        Connection connection = connectionFactory.newConnection();
        // 2.创建通道Channel
        Channel channel = connection.createChannel();

        // 3.创建队列，在消费端，也要创建队列，是因为，有可能consumer的速度比publisher的速度快，看似两边都在创建队列，实际只是创建了一个，这种方式只是为了保险
        String queueName = "queue1";
        channel.queueDeclare(queueName,false,false,false,null);
        // 订阅消息，传入队列名字
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 5.处理消息
                String message = new String(body);
                System.out.println("接收到消息：【" + message + "】");
            }
        });
        System.out.println("等待接收消息");
    }


}
