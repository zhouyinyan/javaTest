package com.test.rabbitmq.example5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by zyinyan on 2015/7/15.
 */
public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.248.128");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        for(int i=0; i<10; ++i) {
            String msg = getMessage(i);
            String routingKey = getRoutingKey(i);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
            System.out.println(" [x] Sent '" + msg + "' -- routingKey ["+ routingKey +"]");
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

    private static String getRoutingKey(int i){
        String device = (i % 2 == 0) ? "cron" : "kenel" ;
        String level = (i % 3 == 0) ? "error" : ((i % 3 == 1) ? "waring" : "info" );
        return device+"."+level;
    }
}
