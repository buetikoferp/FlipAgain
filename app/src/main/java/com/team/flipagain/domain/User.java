package com.team.flipagain.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Raffaele on 22.03.2016.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    //ATTRIBUT
    private int userId;
    private String username;
    private String password;
    private ArrayList<Bundle> personalBundleList;
    private boolean isAuthorized = false;
    private boolean isNewUser = false;

    public User( int userId, String username, String password){
        this.username = username;
        this.password = password;
        this.userId = userId;
    }



    //GETTER + SETTER
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<com.team.flipagain.domain.Bundle> getPersonalBundleList() {
        return personalBundleList;
    }

    public void setPersonalBundleList(ArrayList<Bundle> personalBundleList) {
        this.personalBundleList = personalBundleList;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(boolean isNewUser) {
        this.isNewUser = isNewUser;
    }
}
