package com.team.flipagain.client.domain;

import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class MRY_Bundle {

    private int bundleId;
    private String name;

    private MRY_User user;
    private MRY_Module module;
    private ArrayList<MRY_Card> CardList;

 // GETTER

    public ArrayList<MRY_Card> getCardList() {
        return CardList;
    }

    public MRY_Module getModule() {
        return module;
    }

    public int getBundleId() {
        return bundleId;
    }

    public MRY_User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

 // SETTER

    public void setModule(MRY_Module module) {
        this.module = module;
    }


    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }


    public void setUser(MRY_User user) {
        this.user = user;
    }


    public void setCardList(ArrayList<MRY_Card> cardList) {
        CardList = cardList;
    }



    public void setName(String name) {
        this.name = name;
    }
}
