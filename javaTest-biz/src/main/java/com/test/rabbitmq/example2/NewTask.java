package com.test.rabbitmq.example2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * Created by zyinyan on 2015/7/15.
 */
public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static  void main(String[] args) throws IOException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.248.128");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true ,false , false , null);
        for(int i=0; i<10; ++i) {
            String msg = getMessage(i);
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
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

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Fifth message.....";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
