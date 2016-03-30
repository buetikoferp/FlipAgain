package com.team.flipagain.server.messaging;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Created by Raffaele & Philipp on 30.03.2016.
 */
public class ServerMessager {

    private final static String QUEUE_NAME = "flipChannel";

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException{

        ConnectionFactory serverFactory = new ConnectionFactory();
        serverFactory.setHost("localhost");

        Connection serverConn = serverFactory.newConnection();
        Channel serverChannel = serverConn.createChannel();

        serverChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages.");

        Consumer consumer = new DefaultConsumer(serverChannel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
                String message = new String (body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

            }
        };

        serverChannel.basicConsume(QUEUE_NAME, consumer);

    }

}

