package com.team.flipagain.client.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class MRY_FieldOfStudy {
    private String TAG = "FieldOfStudy";
    //ATTRIBUTE
    private int studyID;
    private String name;
    private ArrayList<MRY_Module> moduleList;

    // DBMANAGER
    private DBManager db;

    //CONSTRUCTOR
    public MRY_FieldOfStudy(Context context){
        db = DBManager.getInstance(context);
        open();
        Log.d(getName(), "Field of Study memory created");
    }

    /**
     * Erzeugung ohne Context nicht moeglich Singleton!
     */
    @SuppressWarnings("nope unused^^")
    private MRY_FieldOfStudy(){}

    /**
     * Diese Methode erstellt neue Studiengaenge.
     * @param name
     * @return autogenerierte studyID
     */
    public long insertFieldOfStudy(String name){
        final ContentValues data = new ContentValues();
            data.put(TBL_FieldOfStudy.name, name);

        final SQLiteDatabase dbCon = db.getWritableDatabase();

        try{
            final long id = dbCon.insertOrThrow(TBL_FieldOfStudy.TABLE_NAME,null, data);
            Log.i(TAG, "Field of Study mit id=" + id + " erzeugt.");
            return id;
        }finally {
            dbCon.close();
        }
    }

    private void open() {
        db.getReadableDatabase();
        Log.d(getName(),"Datenbank FlipAgain geoeffnet");

    }

    private void close(){
        db.close();
        Log.d(TAG, "Datenbank FlipAgain geschlossen");
    }


    //GETTER + SETTER

    public int getStudyID() {
        return studyID;
    }

    public void setStudyID(int studyID) {
        this.studyID = studyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MRY_Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<MRY_Module> moduleList) {
        this.moduleList = moduleList;
    }
}
