package com.team.flipagain.server.messaging;

import com.rabbitmq.client.Envelope;
import com.team.flipagain.server.application.LoginHandler;
import com.team.flipagain.server.domain.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp on 01.04.2016.
 */
public final class ServerMessager {
    private Object messageObject;

    /**
     * Übergibt das gesendete Objekt.
     * @param messageObject
     */
        public void recievedObject(Object messageObject){
        this.messageObject = messageObject;




        User u = (User) messageObject;
            System.out.println(u.getUsername()+" | "+u.getPassword());

        LoginHandler.getAuthorization(u);
    }
}
