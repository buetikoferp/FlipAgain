package com.team.flipagain.server.messaging;

import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp on 01.04.2016.
 */
public class ServerMessager {

    public void send(Serializable obj){
        try {
            ServerProducer sp = new ServerProducer("ServerProducer");
            sp.sendMessage(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public Serializable receive(){
        try {
            ServerConsumer sc = new ServerConsumer("ServerConsumer");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
