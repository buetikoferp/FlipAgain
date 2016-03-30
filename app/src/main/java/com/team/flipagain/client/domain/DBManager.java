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
    private FieldOfStudy fieldOfStudy;


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
            data.put(TBL_FOStudy.rowNameOfStudy, name);

        final SQLiteDatabase dbCon = db.getWritableDatabase();

        try{
            final long id = dbCon.insertOrThrow(TBL_FOStudy.TABLE_NAME,null, data);
            Log.i(TAG, "Field of Study mit id=" + id + " erzeugt.");
            return id;
        }finally {
            dbCon.close();
        }
    }

    public ArrayList<FieldOfStudy> getSomeShitFromDatabase(String tableName, String rowName, String rowID ){
        final SQLiteDatabase dbCon = db.getReadableDatabase();

        Cursor c = dbCon.rawQuery("SELECT " + rowName +"," + rowID + " FROM " + tableName ,null );
        try{
            while(c.moveToNext()){
                int studyID = c.getInt(c.getColumnIndex(rowID));
                String name = c.getString(c.getColumnIndex(rowName));
                new FieldOfStudy(studyID,name);
            }
        }finally {
            dbCon.close();
        }

        return fieldOfStudy.getFieldOfStudyList();
    }

    private void open() {
        db.getReadableDatabase();
        Log.d(TAG,"Datenbank FlipAgain geoeffnet");

    }

    private void close(){
        db.close();
        Log.d(TAG, "Datenbank FlipAgain geschlossen");
    }


    //GETTER + SETTER


}
