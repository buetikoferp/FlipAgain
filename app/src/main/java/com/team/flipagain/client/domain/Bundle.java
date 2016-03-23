package com.team.flipagain.client.domain;

import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class Bundle {
    private int bundleId;

    private User user;

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    private ArrayList<Card> cardList;

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) {
        this.modul = modul;
    }

    /**
     * return the bundleId
     * @return int
     */
    public int getBundleId() {
        return bundleId;
    }

    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Modul modul;
}
