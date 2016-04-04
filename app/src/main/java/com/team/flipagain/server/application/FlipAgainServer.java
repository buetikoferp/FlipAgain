package com.team.flipagain.server.application;

import com.team.flipagain.server.domain.DBManager;
import com.team.flipagain.server.domain.DomainInterface;
import com.team.flipagain.server.messaging.ServerConsumer;
import com.team.flipagain.server.messaging.ServerMessager;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Raffaele on 04.04.2016.
 */
public class FlipAgainServer {



    public static void main(String args[]){



        ServerConsumer consumer = null;
        try {
            consumer = new ServerConsumer("flipagain");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        DomainInterface di = new DBManager();



    }



}
