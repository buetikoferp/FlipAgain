package com.team.flipagain.server.messaging;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.team.flipagain.server.application.LoginHandler;
import com.team.flipagain.server.domain.User;

/**
 * Created by Raffaele & Philipp on 30.03.2016.
 */
public class ServerMessager {

    private final static String QUEUE_NAME = "flipChannel";


    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException{
       final FileWriter fos = new FileWriter("C:\\flipagain\\log\\serverlog.txt");
        ConnectionFactory serverFactory = new ConnectionFactory();
        serverFactory.setHost("localhost");

        Connection serverConn = serverFactory.newConnection();
        Channel serverChannel = serverConn.createChannel();

        serverChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages.");

        Consumer consumer = new DefaultConsumer(serverChannel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{

                LoginHandler.getAuthorization(null);
                fos.write((" [x] Received '"+"Hallo" +"'"));



            }
        };

        serverChannel.basicConsume(QUEUE_NAME, consumer);

    }

}

