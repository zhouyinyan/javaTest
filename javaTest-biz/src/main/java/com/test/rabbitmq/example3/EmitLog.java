package com.test.rabbitmq.example3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * Created by zyinyan on 2015/7/15.
 */
public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.248.128");
        Connection connection = connectionFactory.newConnection();

        Channel channel =  connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        for(int i=0; i<10; ++i) {
            String msg = getMessage(i);
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
                    System.out.println(" [x] Sent '" + msg + "'");
        }

        channel.close();
        connection.close();

    }

    private static String getMessage(int i){
        StringBuilder words = new StringBuilder("message"+i+" ");
        for(int j=0;j<i;++j){
            words.append(".");
        }
        return words.toString();
    }

}
