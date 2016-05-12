package com.team.flipagain.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class Bundle implements Serializable{

    private int bundleId;
    private String name;
    private int userId;
    private ArrayList<Card> cardList;

    /**
     *
     * @param bundleId
     * @param name
     * @param userId
     */
    public Bundle(int bundleId, String name , int userId){
        this.bundleId = bundleId;
        this.name = name;
        this.userId = userId;
    }

    public int getBundleId() {
        return bundleId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }
}
