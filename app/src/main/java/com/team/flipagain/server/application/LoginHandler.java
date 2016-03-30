package com.team.flipagain.server.application;

import com.team.flipagain.client.application.ApplicationInterface;

/**
 * Created by Raffaele on 22.03.2016.
 */
public class LoginHandler implements ApplicationInterface {


    @Override
    public boolean getAuthorization(String username, String password) {
        return true;
    }
}
