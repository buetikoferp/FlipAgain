package com.team.flipagain.client.domain;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by delay on 30.03.2016.
 */
public interface DomainInterface {

    //DBMANAGER

    public ArrayList<String> getNamesofSelectedTable(String tableName, String rowName, String rowID);
}
