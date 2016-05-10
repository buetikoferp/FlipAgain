package com.team.flipagain.client.domain;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by delay on 30.03.2016.
 */
public interface DomainInterface {



    /**
     * Returniert eine ArrayListe mit Obj der mitgegebenen DB Tabelle -----------Wird noch gelöscht
     * @param tableName
     * @param WHEREname
     * @return
     */
    public ArrayList<Object> getClassListofSelectedTable(String tableName, String WHEREname);



    //LOKAL
    public void insertBundle(String bundleName , String moduleName);
    public void insertCard(String nameOfBundle, String question, String solution);
    public ArrayList<FieldOfStudy> getListOfStudy();
    public HashSet<Module> getListOfModlue(String WhereStatement);
    public ArrayList<Bundle> getListOfBundle(String WhereStatement);
    public ArrayList<Card> getListofCard(String WhereStatement);

    //SERVER
    public ArrayList<Bundle> getServerListofBundle(String nameOfModule);

    /**
     * Speichert heruntergeladenes Bundle in lokale Datenbank
     * @param bundle
     */
    public void saveBundle(Bundle bundle);


}
