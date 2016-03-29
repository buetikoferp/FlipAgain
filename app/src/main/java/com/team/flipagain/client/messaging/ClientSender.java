package com.team.flipagain.client.messaging;

import android.util.Log;

import com.team.flipagain.client.application.ApplicationInterface;

/**
 * Created by Anthony Delay on 29.03.2016.
 */
public class ClientSender implements ApplicationInterface {
    private String TAG = "SENDER";
    @Override
    public boolean getAuthorization(String username, String password) {
        Log.d(TAG, username + " " + password);
        return true;
    }
}
