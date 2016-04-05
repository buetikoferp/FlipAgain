package com.team.flipagain.server.domain;

import com.team.flipagain.client.domain.Card;

import java.util.ArrayList;



/**
 * Created by Raffaele on 23.03.2016.
 */
public class Bundle {

    private int bundleId;
    private String name;
    private int userId;
    private ArrayList<Card> cardList;

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
