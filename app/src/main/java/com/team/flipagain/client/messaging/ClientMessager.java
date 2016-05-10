package com.team.flipagain.client.messaging;

import com.team.flipagain.client.domain.Bundle;
import com.team.flipagain.client.domain.FieldOfStudy;
import com.team.flipagain.client.domain.Module;
import com.team.flipagain.client.domain.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp on 03.04.2016.
 */
public class ClientMessager implements ServerRequest{
    private Object recivedObject;
    private ClientProducer clientProducer;
    private ClientConsumer clientConsumer;

    public void send(Serializable obj){
        try {
            clientProducer = new ClientProducer("flipagain");
            clientProducer.sendMessage(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public void receivingLoop() throws IOException, TimeoutException {
        clientConsumer = new ClientConsumer("flipagain");
        Thread consumerThread = new Thread(clientConsumer);
        consumerThread.run();
    }

    public void receive(Object recievedObject){
        this.recivedObject = recievedObject;
    }

    public Object getRecivedObject(){
        return recivedObject;
    }

    @Override
    public User addUser(User user) throws IOException, TimeoutException {
        send(user);
        receivingLoop();
        return clientConsumer.getUser();
    }

    @Override
    public User validateUser(User user) throws IOException, TimeoutException {
        send(user);
        receivingLoop();
        return clientConsumer.getUser();
    }

    @Override
    public ArrayList<FieldOfStudy> getFieldOfStudyByName() {
        return null;
    }

    @Override
    public ArrayList<Module> getModuleByName() {
        return null;
    }

    @Override
    public Bundle downloadBundle(String bundleName) throws IOException, TimeoutException {
        send(bundleName);
        receivingLoop();
        return clientConsumer.getBundle();
    }
}
