package com.lqs;

// 官方publisher方式

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class PublisherTest {

    @Test
    public void testConsumer() throws IOException, TimeoutException {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置链接参数
        connectionFactory.setHost("localhost");
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
        channel.queueDeclare(queueName, false, false, false, null);

        // 发送消息
        String message = "hello rabbitmq";
        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

        System.out.println("发送消息成功：【" + message + "】");

        // 5.关闭通道和连接
        channel.close();
        connection.close();
    }


}
