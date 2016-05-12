package com.team.flipagain.application;

import android.widget.ArrayAdapter;

/**
 * Created by delay on 02.04.2016.
 */
public interface ListHandlerInterface {

    //Methoden der Klasse Listhandler

    /**
     * Gibt Adapter mit gefüllten FieldofStudy Strings
     * @return
     */
    public ArrayAdapter getFieldOfStudyAdapter();

    /**
     * Gibt Adapter mit gefüllten Module Strings zurück
     * @param fieldOfStudyName
     * @return
     */
    public ArrayAdapter getModuleAdapter(String fieldOfStudyName);

    /**
     * Gibt Adapter mit den Bundle Strings zurück
     * @param moduleName
     * @return
     */
    public ArrayAdapter getBundleAdapter(String moduleName);

    /**
     * Ruft DBManager auf.
     * diese Methode sollte später eine eigenen Klasse bekommen
     * @param name
     * @param modulName
     */
    public void createNewBundle(String name, String modulName);
}
