package com.team.flipagain.client.application;

import android.widget.ArrayAdapter;

/**
 * Created by delay on 02.04.2016.
 */
public interface ListHandlerInterface {

    //Methoden der Klasse Listhandler
    public ArrayAdapter getFieldOfStudyAdapter();
    public ArrayAdapter getModuleAdapter(String fieldOfStudyName);
    public ArrayAdapter getBundleAdapter(String moduleName);
    public void createNewBundle(String name, String modulName);
}
