package com.team.flipagain.client.messaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Created by Anthony Delay on 29.03.2016.
 */

public class ClientSender {

    public static void main(String[] args) throws IOException{
        String serverAdress = "152.96.56.49";
        int port = 4848;

        Socket sclient = new Socket(serverAdress, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(sclient.getInputStream()));
        String serverAnswer = input.readLine();
    }

}


