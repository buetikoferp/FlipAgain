package com.team.flipagain.domain;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by delay on 30.03.2016.
 */
public interface DomainInterface {




    //LOKAL
    public void registerUser(User user);
    public User getUser();
    public void insertBundle(String bundleName , String moduleName);
    public void insertCard(String nameOfBundle, String question, String solution);
    public ArrayList<FieldOfStudy> getListOfStudy();
    public HashSet<Module> getListOfModule(String WhereStatement);
    public ArrayList<Bundle> getListOfBundle(String WhereStatement);
    public ArrayList<Card> getListofCard(String WhereStatement);
    public ArrayList<Bundle> getListOfBundleFromUser(String WhereStatement);



    //SERVER
     /**
     * Speichert heruntergeladenes Bundle in lokale Datenbank
     * @param bundle
     */
     public void saveBundle(Bundle bundle, String module);
    public ArrayList<Bundle> getServerListofBundle(String moduleName);

    void resetUser();
}
