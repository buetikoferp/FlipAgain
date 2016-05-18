package com.team.flipagain.messaging;

import com.team.flipagain.domain.Bundle;
import com.team.flipagain.domain.FieldOfStudy;
import com.team.flipagain.domain.Module;
import com.team.flipagain.domain.User;

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
    private Thread consumerThread;

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


    public void receive(Object recievedObject){
        this.recivedObject = recievedObject;
    }

    public Object getRecivedObject(){
        return recivedObject;
    }

    @Override
    public boolean validateUser(User user) throws IOException, TimeoutException {
        clientConsumer = new ClientConsumer("flipiagain");
        consumerThread = new Thread(clientConsumer);
        send(user);
        consumerThread.start();

        return clientConsumer.getUser().isAuthorized();
    }

    @Override
    public ArrayList<FieldOfStudy> getFieldOfStudyByName() {


        return null;
    }

    @Override
    public ArrayList<Module> getModuleByName(FieldOfStudy fieldOfStudy) throws TimeoutException, IOException {
        clientConsumer = new ClientConsumer("flipagain");
        consumerThread = new Thread(clientConsumer);
        send(fieldOfStudy);
        consumerThread.start();
        return clientConsumer.getModuleList();
    }

    @Override
    public Bundle downloadBundle(String bundleName) throws IOException, TimeoutException {
        clientConsumer = new ClientConsumer("flipagain");
        consumerThread = new Thread(clientConsumer);
        send(bundleName);
        consumerThread.start();
        return clientConsumer.getBundle();
    }

    @Override
    public ArrayList<Bundle> synchronize(User user) throws IOException, TimeoutException {
        clientConsumer = new ClientConsumer("flipiagain");
        consumerThread = new Thread(clientConsumer);
        send(user);
        consumerThread.start();
        ArrayList<Bundle> synchronizedList = user.getPersonalBundleList();
        return synchronizedList;
    }
}
