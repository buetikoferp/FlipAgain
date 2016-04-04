package com.team.flipagain.client.messaging;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp on 03.04.2016.
 */
public class ClientMessager extends EndPoint {

    public ClientMessager(String endpointName) throws IOException, TimeoutException {
        super(endpointName);
    }


}
