package com.test.rabbitmq.example1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 *
 * Created by zyinyan on 2015/7/14.
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        //连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.248.128");
        //连接
        Connection connection = connectionFactory.newConnection();
        //channel
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "hello 3";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        System.out.println(" [x] Sent '" + msg + "'");

        channel.close();

        connection.close();

    }

}
