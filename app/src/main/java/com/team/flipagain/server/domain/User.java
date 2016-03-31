package com.team.flipagain.server.domain;

/**
 * Created by Raffaele on 31.03.2016.
 */
public class User {

    private String username;
    private String password;


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
