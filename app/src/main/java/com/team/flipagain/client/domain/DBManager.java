package com.team.flipagain.client.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class DBManager {
    private String TAG = "DBMANAGER";
    private ArrayList<Object> fieldOfStudyList = new ArrayList<>();
    private ArrayList<Object> moduleList = new ArrayList<>();

    // DBMANAGER
    private Database db;

    //CONSTRUCTOR
    public DBManager(Context context){
        db = Database.getInstance(context);
        open();

    }

    /**
     * Erzeugung ohne Context nicht moeglich Singleton!
     */
    @SuppressWarnings("nope unused")
    private DBManager(){}



    /**
     * Diese Methode erstellt neue Studiengaenge.
     * @param name
     * @return autogenerierte rowStudyID
     */
    public long insertFieldOfStudy(String name){
        final ContentValues data = new ContentValues();
            data.put(TBL_FOStudy.getRowNameOfStudy(), name);

        final SQLiteDatabase dbCon = db.getWritableDatabase();

        try{
            final long id = dbCon.insertOrThrow(TBL_FOStudy.getTableName(),null, data);
            Log.i(TAG, "Field of Study mit id=" + id + " erzeugt.");
            return id;
        }finally {
            dbCon.close();
        }
    }

    /**
     *
     * @param tableName
     * @param rowName
     * @param rowID
     * @param WHEREname
     * @return
     */
    public ArrayList<String> getNamesofSelectedTable(String tableName, String rowName, String rowID , String WHEREname){
        final SQLiteDatabase dbCon = db.getReadableDatabase();

        switch (tableName){
            case "fieldofstudy":

                Cursor a = dbCon.rawQuery("SELECT " + rowName + "," + rowID + " FROM " + tableName, null);
                try{
                    while(a.moveToNext()){
                        int studyID = a.getInt(a.getColumnIndex(rowID));
                        String name = a.getString(a.getColumnIndex(rowName));
                        fieldOfStudyList.add(new FieldOfStudy(studyID,name));
                    }
                }finally {
                    dbCon.close();
                }

                return getNamesOfClass(fieldOfStudyList, "FieldOfStudy");

            case "module":

                Cursor b = dbCon.rawQuery("SELECT " + rowName + "," + rowID + " FROM " + tableName + " WHERE " + WHEREname, null);
                try{
                    while(b.moveToNext()){
                        int moduleID = b.getInt(b.getColumnIndex(rowID));
                        String name = b.getString(b.getColumnIndex(rowName));
                        moduleList.add(new Module(moduleID,name));
                    }
                }finally {
                    dbCon.close();
                }

                return getNamesOfClass(moduleList, "Module");

            default:
                return null;
        }


    }


    private ArrayList<String> getNamesOfClass(ArrayList<Object> object, String instance){
        ArrayList<String> names = new ArrayList<>();
        if(object != null){
            switch(instance){
                case "FieldOfStudy":
                      for(Object objects : object ){
                        FieldOfStudy study = (FieldOfStudy)objects;
                         names.add(study.getName());
                      }
                        break;
                case "Module":
                    for(Object objects : object ){
                        Module module = (Module)objects;
                        names.add(module.getModuleName());
                    }
                        break;
                default:
                    Log.d(TAG, instance);

            }
        }else{
            return null;
        }




        return names;
    }

    private void open() {
        db.getReadableDatabase();
        Log.d(TAG,"Datenbank FlipAgain geoeffnet");

    }

    private void close(){
        db.close();
        Log.d(TAG, "Datenbank FlipAgain geschlossen");
    }





}
