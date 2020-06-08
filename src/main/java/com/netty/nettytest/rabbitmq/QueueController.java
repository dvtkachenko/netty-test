package com.netty.nettytest.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class QueueController {

    private Connection connection;
    private Channel channel;
    private String queueName;

    public QueueController(String queueName) {
        this.queueName = queueName;
    }

    public void initChannel() throws Exception {
//        try (Connection connection = factory.newConnection();

        if (connection == null) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            System.out.println("Queue " + queueName + " is initialized");
        }
    }

    public void publishMessage(String message) throws Exception {
         channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
    }

    public void listenMessage(DeliverCallback deliverCallback) throws Exception {
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

    public void close() throws Exception {
        channel.close();
        connection.close();
        channel = null;
        connection = null;
    }
}
