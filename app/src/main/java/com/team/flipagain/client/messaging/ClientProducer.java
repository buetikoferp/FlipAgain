package com.team.flipagain.client.messaging;

/**
 * Created by Philipp on 01.04.2016.
 */

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class ClientProducer extends EndPoint{


    public ClientProducer(String endPointName) throws IOException, TimeoutException{
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
