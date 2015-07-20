package com.test.rabbitmq.example1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.omg.CORBA.FloatSeqHelper;

import java.io.IOException;

/**
 * Created by zyinyan on 2015/7/14.
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, InterruptedException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.248.128");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery =  queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println(" [x] Received '" + msg + "'");
        }

    }

}
