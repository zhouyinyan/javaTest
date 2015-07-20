package com.test.rabbitmq.example4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by zyinyan on 2015/7/15.
 */
public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, InterruptedException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.248.128");
        Connection connection = connectionFactory.newConnection();

        Channel channel =  connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName,EXCHANGE_NAME,"error");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicQos(1);

        channel.basicConsume(queueName, false, consumer);

        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());

            System.out.println(" [x] Received '" + msg + "'");

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }


    }
}
