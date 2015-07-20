package com.test.springamqp.quick;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by zyinyan on 2015/7/15.
 */
public class Quick {

    public static void main(String[] args) {

        ApplicationContext context =
                new GenericXmlApplicationContext("classpath:spring/rabbit-context.xml");

        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);

        amqpTemplate.convertAndSend("myqueue", "foo");

        String foo = (String) amqpTemplate.receiveAndConvert("myqueue");

        System.out.println("---"+foo);

    }


}
