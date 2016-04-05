package com.team.flipagain.client.application;

import com.team.flipagain.client.messaging.ClientMessager;

import java.io.Serializable;

/**
 * Created by Philipp on 05.04.2016.
 */
public class ApplicationManager {

    public void send(Object obj){
        Serializable s = (Serializable)obj;
        ClientMessager cm = new ClientMessager();
        cm.send(s);
    }
}
