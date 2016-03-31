package com.team.flipagain.client.domain;

import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class Bundle {

    private int bundleId;
    private String name;

    private User user;
    private Module module;
    private ArrayList<Card> CardList;

 // GETTER

    public ArrayList<Card> getCardList() {
        return CardList;
    }

    public Module getModule() {
        return module;
    }

    public int getBundleId() {
        return bundleId;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

 // SETTER

    public void setModule(Module module) {
        this.module = module;
    }


    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setCardList(ArrayList<Card> cardList) {
        CardList = cardList;
    }



    public void setName(String name) {
        this.name = name;
    }
}
