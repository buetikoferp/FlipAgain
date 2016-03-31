package com.team.flipagain.client.messaging;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.team.flipagain.client.application.ApplicationInterface;
import com.team.flipagain.server.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeoutException;


/**
 * Created by Anthony Delay on 29.03.2016.
 */

public class ClientSender implements ApplicationInterface{

    private final static String QUEUE_NAME = "flipChannel";



    public void send(User u) throws IOException, TimeoutException {
        ConnectionFactory clientFactory = new ConnectionFactory();
        clientFactory.setHost("localhost");
        Connection clientConn = clientFactory.newConnection();
        Channel clientChannel = clientConn.createChannel();

        clientChannel.queueDeclare(QUEUE_NAME, false, false, false, null);

       // clientChannel.basicPublish("", QUEUE_NAME, null, u.getBytes());

        System.out.println(" [x] Sent '" + u + "'");
        clientChannel.close();
        clientConn.close();
    }

    @Override
    public boolean getAuthorization(User u) {
        try {
            send(u);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }
}