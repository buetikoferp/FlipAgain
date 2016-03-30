package com.team.flipagain.server.messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Philipp on 29.03.2016.
 */


public class ServerReceiver {

    public static void main(String[] args) throws IOException{
        int port = 4848;

        ServerSocket ssocket = new ServerSocket(port);

        try{
            while(true){
                Socket client = ssocket.accept();
                try{
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println(new Date().toString());
                }
                finally{
                    client.close();
                }
            }
        } finally{
            ssocket.close();
        }
    }

}