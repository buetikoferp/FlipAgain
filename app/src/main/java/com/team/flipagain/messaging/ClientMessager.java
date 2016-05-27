package com.team.flipagain.messaging;

import android.util.Log;

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



    public ClientMessager getClientMessenger(){

        return this;
    }
    public void startThread(){
        try {
            clientConsumer = new ClientConsumer("flipagain");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        consumerThread = new Thread(clientConsumer);
        consumerThread.start();
    }
    public ClientMessager() {
        startThread();
    }


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
    public User validateUser(User user) throws IOException, TimeoutException {

        send(user);

        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return clientConsumer.getUser();
    }

    @Override
    public ArrayList<FieldOfStudy> getFieldOfStudyByName() {


        return null;
    }

    @Override
    public ArrayList<Module> getModuleByName(FieldOfStudy fieldOfStudy) throws TimeoutException, IOException {

        send(fieldOfStudy);
        consumerThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return clientConsumer.getModuleList();
    }

    @Override
    public Bundle downloadBundle(String bundleName) throws IOException, TimeoutException {

        send(bundleName);
        consumerThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return clientConsumer.getBundle();
    }

    @Override
    public ArrayList<Bundle> synchronize(User user) throws IOException, TimeoutException {

        send(user);
        consumerThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Bundle> synchronizedList = user.getPersonalBundleList();
        return synchronizedList;
    }

    @Override
    public ArrayList<Bundle> getBundleList(Module module) throws IOException, TimeoutException {
        send(module);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        Module modul = clientConsumer.getModule();
        if(modul == null ){
            Log.d("ListHandler", "Module ist null" );
        }else{
            Log.d("ListHandler", "Module ist nicht null"+ modul.getModuleName());
        }
        ArrayList<Bundle> bundleList = modul.getBundlesOfModuleList();
        if(bundleList == null ){
            Log.d("ListHandler", "ListofBundle ist null"  );
        }else{
            Log.d("ListHandler", "ListofBundle ist nicht null" + bundleList.get(0).getName());
        }
        return bundleList;
    }

    @Override
    public void insertNewBundle(Bundle bundle) throws IOException, TimeoutException {

        send(bundle);
        consumerThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
