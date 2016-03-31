package com.team.flipagain.client.domain;

import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class Bundle {

    private int bundleId;
    private String name;

    Bundle(int bundleId, String name){
        this.bundleId = bundleId;
        this.name = name;
    }


 // GETTER



    public int getBundleId() {
        return bundleId;
    }



    public String getName() {
        return name;
    }

 // SETTER




    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }





    public void setName(String name) {
        this.name = name;
    }
}
