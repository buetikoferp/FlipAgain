package com.team.flipagain.client.messaging;

import com.team.flipagain.client.application.ApplicationInterface;

/**
 * Created by Anthony Delay on 29.03.2016.
 */
public class ClientSender implements ApplicationInterface {
    @Override
    public boolean getAuthorization(String username, String password) {
        return false;
    }
}
