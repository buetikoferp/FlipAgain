package com.team.flipagain.client.messaging;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp on 03.04.2016.
 */
public class ClientMessager{
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

    public Object receive(){
        Object obj = null;
        try {
            ClientConsumer cc = new ClientConsumer("ClientConsumer");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return obj;
    }


}
