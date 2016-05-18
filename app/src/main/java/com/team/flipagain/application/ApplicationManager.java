package com.team.flipagain.application;

import android.content.Context;

import com.team.flipagain.domain.DBManager;
import com.team.flipagain.domain.DomainInterface;

/**
 * Created by Philipp on 05.04.2016.
 */
public class ApplicationManager {

    public void createNewBundle(String nameOfBundle,String nameofModule, Context context){
        DomainInterface dbManager = new DBManager(context);
        dbManager.insertBundle(nameOfBundle, nameofModule);
    }


    public void addNewCard(String bundle, String question, String solution, Context context) {
        DomainInterface domainInterface = new DBManager(context);
        domainInterface.insertCard(bundle, question, solution);
    }


}
