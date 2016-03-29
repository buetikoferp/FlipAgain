package com.team.flipagain.client.application;

/**
 * Created by Raffaele on 23.03.2016.
 */
public interface ApplicationInterface {
    /**
     * Check if user and password are correct.
     * @param username
     * @param password
     * @return
     */
   public boolean login(String username, String password);
}
