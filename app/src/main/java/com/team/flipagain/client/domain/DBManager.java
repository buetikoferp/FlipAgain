package com.team.flipagain.client.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class DBManager implements DomainInterface{
    private String TAG = "DBMANAGER";
    private ArrayList<Object> fieldOfStudyList = new ArrayList<>();
    private ArrayList<Object> moduleList = new ArrayList<>();
    private ArrayList<Object> bundleList = new ArrayList<>();
    private ArrayList<Object> cardList = new ArrayList<>();

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



    public void insertBundle(String bundleName , String moduleName){
        final SQLiteDatabase dbCon = db.getWritableDatabase();
        Cursor cursor = dbCon.rawQuery("SELECT " + TBL_Module.getRowName() + "," + TBL_Module.getRowModuleID() + " FROM " + TBL_Module.getTableName() + " WHERE " + TBL_Module.getRowName() + " = " + "'" + moduleName + "'", null);
        int moduleID;
        try {
            cursor.moveToFirst();
            moduleID = cursor.getInt(cursor.getColumnIndex(TBL_Module.getRowModuleID()));


            final ContentValues data = new ContentValues();
            data.put(TBL_Bundle.getName(), bundleName);
            //data.put(TBL_Bundle.getUserID(), USERID!!!);                                                  Hier muss dann noch die UserID hiinzugefügt werden
            data.put(TBL_Bundle.getModuleID(), moduleID);

            final long id = dbCon.insertOrThrow(TBL_Bundle.getTableName(),null, data);
            Log.i(TAG, "Field of Study mit id=" + id + " erzeugt.");
        }finally {
            dbCon.close();
            cursor.close();
        }
    }






    private void open() {
        db.getReadableDatabase();
        Log.d(TAG,"Datenbank FlipAgain geoeffnet");

    }

    private void close(){
        db.close();
        Log.d(TAG, "Datenbank FlipAgain geschlossen");
    }

    /**
     * Noch unfertige Methode soll später eine Liste von Objekten einer Tabelle zurückgeben.
     * @param tableName
     * @param WHEREname
     * @return
     */
    public ArrayList<Object> getClassListofSelectedTable(String tableName, String WHEREname) {
        final SQLiteDatabase dbCon = db.getReadableDatabase();

        switch (tableName) {
            case "fieldofstudy":

                Cursor a = dbCon.rawQuery("SELECT " + TBL_FOStudy.getRowNameOfStudy() + "," + TBL_FOStudy.getRowStudyID() + " FROM " + tableName, null);
                try {
                    while (a.moveToNext()) {
                        int studyID = a.getInt(a.getColumnIndex(TBL_FOStudy.getRowStudyID()));
                        String name = a.getString(a.getColumnIndex(TBL_FOStudy.getRowNameOfStudy()));
                        fieldOfStudyList.add(new FieldOfStudy(studyID, name));
                    }
                } finally {
                    dbCon.close();
                    a.close();
                }

                return fieldOfStudyList;

            case "module":

                Cursor b = dbCon.rawQuery("SELECT " + TBL_Module.getRowName() + "," + TBL_Module.getRowModuleID() + " FROM " + tableName + ", fieldofstudy" + " WHERE fieldofstudy.nameofstudy  =" + "'" + WHEREname + "'  AND fieldofstudy.rowStudyID =" + tableName + ".rowStudyID", null);
                try {
                    while (b.moveToNext()) {
                        int moduleID = b.getInt(b.getColumnIndex(TBL_Module.getRowModuleID()));
                        String name = b.getString(b.getColumnIndex(TBL_Module.getRowName()));
                        moduleList.add(new Module(moduleID, name));
                    }
                } finally {
                    dbCon.close();
                    b.close();
                }

                return moduleList;


            case "bundle":
                Log.d(TAG, " Kommt in Case bundle, tablename = " + tableName + " Wherename " + WHEREname);
                Cursor c = dbCon.rawQuery("SELECT " + TBL_Bundle.getName() + "," + TBL_Bundle.getBundleID() + " FROM " + tableName + ", module" + " WHERE module.rowName  =" + "'" + WHEREname + "'  AND module.rowModuleID =" + tableName + ".moduleID", null);
                try {
                    while (c.moveToNext()) {
                        int bundleID = c.getInt(c.getColumnIndex( TBL_Bundle.getBundleID()));
                        String name = c.getString(c.getColumnIndex(TBL_Bundle.getName()));
                        Log.d(TAG, " created name " + name + "   created id " + bundleID);
                        bundleList.add(new Bundle(bundleID, name, 0));                                                     // HIER MUSS NOCH USERID GEADDED WERDEN!
                    }
                } finally {
                    dbCon.close();
                    c.close();
                }

                return bundleList;

            case "card":
                Cursor d = dbCon.rawQuery("SELECT " + TBL_Card.getAnswer() + "," + TBL_Card.getQuestion() + ", "+ TBL_Card.getCardID()+ ", " + TBL_Card.getRating()+ " FROM " + tableName + ", bundle" + " WHERE bundle.name  =" + "'" + WHEREname + "'  AND bundle.bundleID =" + tableName + ".bundleID", null);
                try {
                    while (d.moveToNext()) {
                        int cardID = d.getInt(d.getColumnIndex(TBL_Card.getCardID()));
                        String question = d.getString(d.getColumnIndex(TBL_Card.getQuestion()));
                        String answer = d.getString(d.getColumnIndex(TBL_Card.getAnswer()));
                        int rating = d.getInt(d.getColumnIndex(TBL_Card.getRating()));

                        cardList.add(new Card(cardID, question ,answer , rating));
                    }
                } finally {
                    dbCon.close();
                    d.close();
                }

                return cardList;

            default:
                return null;
        }
    }



}
