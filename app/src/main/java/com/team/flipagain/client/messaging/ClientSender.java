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
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.SerializationUtils;


/**
 * Created by Anthony Delay on 29.03.2016.
 */

public class ClientSender implements ApplicationInterface{

    private final static String QUEUE_NAME = "flipChannel";



    public void send(Serializable object) throws IOException, TimeoutException {
        ConnectionFactory clientFactory = new ConnectionFactory();
        clientFactory.setHost("localhost");
        Connection clientConn = clientFactory.newConnection();
        Channel clientChannel = clientConn.createChannel();

        clientChannel.queueDeclare(QUEUE_NAME, false, false, false, null);

       clientChannel.basicPublish("", QUEUE_NAME, null, SerializationUtils.serialize(object));

        System.out.println(" [x] Sent '" +object + "'");
        clientChannel.close();
        clientConn.close();
    }


    public boolean getAuthorization(ArrayList<User> u) {
        try {
            send(u);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getAuthorization(User u) {
        return false;
    }
}