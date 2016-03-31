package com.team.flipagain.client.application;

import com.team.flipagain.server.domain.User;

/**
 * Created by Raffaele on 23.03.2016.
 */
public interface ApplicationInterface {
    /**
     * Check if user and password are correct.
     * @param User
     * @return
     */
   public boolean getAuthorization(User u);
}
