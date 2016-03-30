package com.team.flipagain.client.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.team.flipagain.client.application.ApplicationInterface;

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

    public void send(String username, String password) throws IOException, TimeoutException {
        ConnectionFactory clientFactory = new ConnectionFactory();
        clientFactory.setHost("localhost");
        Connection clientConn = clientFactory.newConnection();
        Channel clientChannel = clientConn.createChannel();

        clientChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = username+password;
        clientChannel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");
        clientChannel.close();
        clientConn.close();
    }

    @Override
    public boolean getAuthorization(String username, String password) {
        try {
            send(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }
}