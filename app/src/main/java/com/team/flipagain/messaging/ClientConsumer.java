package com.team.flipagain.messaging;

/**
 * Created by Philipp on 01.04.2016.
 */

import android.util.Log;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.team.flipagain.domain.Bundle;
import com.team.flipagain.domain.FieldOfStudy;
import com.team.flipagain.domain.Module;
import com.team.flipagain.domain.User;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;


/**
 * The endpoint that consumes messages off of the queue. Happens to be runnable.
 * @author syntx
 *
 */
public class ClientConsumer extends EndPoint implements Runnable, Consumer{

    private User user;
    private Module module;
    private Bundle bundle;
    private FieldOfStudy fos;
    private ArrayList<Bundle> bundleList;

    public ClientConsumer(String endPointName) throws IOException, TimeoutException{
        super(endPointName);
        user = null;
        module = null;
    }

    public void run() {

        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) throws IOException {
        Log.d("ClientConsumer", "Abfrage ClientConsumer:  " + SerializationUtils.deserialize(body).toString());
        Object obj = SerializationUtils.deserialize(body);
        if((obj) instanceof Module){
            module = (SerializationUtils.deserialize(body));
            Log.d("ClientConsumer", "Abfrage Module:  " + module.getModuleName());
        }else if((obj) instanceof User){
            user = (SerializationUtils.deserialize(body));
        }else if((obj) instanceof Bundle){
            bundle =(SerializationUtils.deserialize(body));
        }

        obj = null;
        try {
            super.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public User getUser(){
        return user;
    }

    public Bundle getBundle() {return bundle;}

    public Module getModule() {
        return module;
    }

    public ArrayList<Module> getModuleList(){return fos.getModuleList();}

    public ArrayList<Bundle> getBundleList(){return bundleList;}





    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}