package com.team.flipagain.application;

import android.content.Context;
import android.os.AsyncTask;

import com.team.flipagain.domain.DBManager;
import com.team.flipagain.domain.DomainInterface;
import com.team.flipagain.domain.User;

/**
 * Created by Philipp on 05.04.2016.
 */
public class ApplicationManager {
    private User user;
    private Context context;
    private String module;
    private String bundle;


    public void registerUser(User user, Context context){
        DomainInterface domain = new DBManager(context);
        domain.registerUser(user);
    }

    public User getUser(Context context){
        DomainInterface dbManager = new DBManager(context);
        return dbManager.getUser();
    }

    public void createNewBundle(String nameOfBundle,String nameofModule, Context context){
        DomainInterface dbManager = new DBManager(context);
        dbManager.insertBundle(nameOfBundle, nameofModule);
    }


    public void addNewCard(String bundle, String question, String solution, Context context) {
        DomainInterface domainInterface = new DBManager(context);
        domainInterface.insertCard(bundle, question, solution);
    }



}
