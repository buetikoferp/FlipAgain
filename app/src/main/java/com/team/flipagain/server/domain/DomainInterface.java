package com.team.flipagain.server.domain;

import java.sql.SQLException;

/**
 * Created by Philipp on 31.03.2016.
 */
public interface DomainInterface {
    /**
     *
     * @param user
     * @return
     */
    public User getUser(User user) throws SQLException;



}
