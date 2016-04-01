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
                    a.close();
                }

                return getNamesOfClass(fieldOfStudyList, "FieldOfStudy");

            case "module":

                Cursor b = dbCon.rawQuery("SELECT " + rowName + "," + rowID + " FROM " + tableName + ", fieldofstudy"+ " WHERE fieldofstudy.nameofstudy  =" + "'" + WHEREname +"'  AND fieldofstudy.rowStudyID =" +tableName +".rowStudyID" , null);
                try{
                    while(b.moveToNext()){
                        int moduleID = b.getInt(b.getColumnIndex(rowID));
                        String name = b.getString(b.getColumnIndex(rowName));
                        moduleList.add(new Module(moduleID,name));
                    }
                }finally {
                    dbCon.close();
                    b.close();
                }

                return getNamesOfClass(moduleList, "Module");


            case "bundle":
                Log.d(TAG , " Kommt in Case bundle, tablename = " +tableName + " Wherename " + WHEREname);
                Cursor c = dbCon.rawQuery("SELECT " + rowName + "," + rowID + " FROM " + tableName + ", module"+ " WHERE module.rowName  =" + "'" + WHEREname +"'  AND module.rowModuleID =" +tableName +".moduleID" , null);
                try{
                    while(c.moveToNext()){
                        int bundleID = c.getInt(c.getColumnIndex(rowID));
                        String name = c.getString(c.getColumnIndex(rowName));
                        Log.d(TAG, " created name "+name+ "   created id " + bundleID);
                        bundleList.add(new Bundle(bundleID,name));
                    }
                }finally {
                    dbCon.close();
                    c.close();
                }

                return getNamesOfClass(bundleList, "Bundle");

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

                case "Bundle":
                    for(Object objects : object ){
                        Bundle bundle = (Bundle)objects;
                        names.add(bundle.getName());
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
                        bundleList.add(new Bundle(bundleID, name));
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
