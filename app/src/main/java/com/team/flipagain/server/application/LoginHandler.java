package com.team.flipagain.server.application;


import com.team.flipagain.server.domain.DBManager;
import com.team.flipagain.server.domain.DomainInterface;
import com.team.flipagain.server.domain.User;

import java.sql.SQLException;

/**
 * Created by Raffaele on 22.03.2016.
 */
public class LoginHandler {
    private static DomainInterface di = new DBManager();


    public static boolean getAuthorization(User u) {

        try {
            di.getUser(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }
}
