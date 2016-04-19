package com.team.flipagain.client.messaging;

import com.team.flipagain.client.domain.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp on 03.04.2016.
 */
public class ClientMessager{
    private Object recivedObject;

    public void send(Serializable obj){
        ClientProducer cp = null;
        try {
            cp = new ClientProducer("flipagain");
            cp.sendMessage(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public void receive(Object recievedObject){
        this.recivedObject = recievedObject;
    }

    public Object getRecivedObject(){
        return recivedObject;
    }

}
