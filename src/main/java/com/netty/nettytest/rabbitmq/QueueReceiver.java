package com.netty.nettytest.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class QueueReceiver {

    private final static String QUEUE_NAME = "test-pipe";
    static long messageCounter = 0;

    public static void main(String[] argv) throws Exception {

//        System.out.println("command line args -> ");
//        for(String arg: argv) {
//            System.out.println(arg);
//        }

        String queueChannelName = argv[0] == null ? QUEUE_NAME : argv[0];
        QueueController queue = new QueueController(queueChannelName);
        queue.initChannel();
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            messageCounter++;
            String message = new String(delivery.getBody(), "UTF-8");
            if(messageCounter%1000 == 0 || messageCounter < 100) {
                System.out.println("Read from queue " + messageCounter + " messages");
            }
//            System.out.println(" [x] Received '" + message + "'");
        };

        queue.listenMessage(deliverCallback);
//        queue.close();
    }
}
