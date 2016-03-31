package com.team.flipagain.client.domain;

import java.util.ArrayList;

/**
 * Created by Raffaele on 22.03.2016.
 */
public class User {
    //ATRIBUT
    private int userId;
    private String username;
    private String password;
    private String email;
    private ArrayList<Bundle> bundleList;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Bundle> getBundleList() {
        return bundleList;
    }

    public void setBundleList(ArrayList<Bundle> bundleList) {
        this.bundleList = bundleList;
    }
}
