package com.team.flipagain.client.domain;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by delay on 30.03.2016.
 */
public interface DomainInterface {



    /**
     * Returniert eine ArrayListe mit Obj der mitgegebenen DB Tabelle
     * @param tableName
     * @param WHEREname
     * @return
     */
    public ArrayList<Object> getClassListofSelectedTable(String tableName, String WHEREname);

    /**
     * Sobald Server muss hier Bundle obj erstellt und gesendet werden.
     * @param bundleName
     * @param moduleName
     */
    public void insertBundle(String bundleName , String moduleName);

    /**
     * Sobald Server muss hier Card obj erstellt und gesendet werden.
     * @param nameOfBundle
     * @param question
     * @param solution
     */
    public void insertCard(String nameOfBundle, String question, String solution);


}
