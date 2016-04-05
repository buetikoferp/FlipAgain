package com.team.flipagain.server.messaging;

/**
 * Created by Philipp on 01.04.2016.
 */

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * The endpoint that consumes messages off of the queue. Happens to be runnable.
  */
public class ServerConsumer extends com.team.flipagain.server.messaging.EndPoint implements Runnable, Consumer{
    private ServerMessager serMes = new ServerMessager();
    public ServerConsumer(String endPointName) throws IOException, TimeoutException{
        super(endPointName);
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
        System.out.println("Consumer "+consumerTag +" registered");
    }

    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
        Object object = (SerializationUtils.deserialize(body));
        serMes.recievedObject(object);
        System.out.println("Objekt erhalten");


    }



    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}