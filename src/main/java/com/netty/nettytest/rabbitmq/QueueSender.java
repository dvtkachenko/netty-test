package com.netty.nettytest.rabbitmq;

import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class QueueSender {

    private final static String QUEUE_NAME = "test-pipe";

    public static void main(String[] argv) throws Exception {

        QueueController queue = new QueueController(QUEUE_NAME);
        queue.initChannel();

        String message = "Hello World!";
        queue.publishMessage(message);
        System.out.println(" [x] Sent '" + message + "'");
        queue.publishMessage(message + " 1");
        System.out.println(" [x] Sent '" + message + "'");
        queue.publishMessage(message + " 2");
        System.out.println(" [x] Sent '" + message + "'");
        queue.close();
    }
}
