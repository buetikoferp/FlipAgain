package com.team.flipagain.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class DBManager implements DomainInterface{
    private String TAG = "DBMANAGER";

    private ArrayList<FieldOfStudy> ListOfFieldOfStudy = new ArrayList<>();
    private HashSet<Module> SetOfModule = new HashSet<>();
    private ArrayList<Bundle> ListOfBundle = new ArrayList<>();
    private ArrayList<Card> ListOfCard = new ArrayList<>();
    // DBMANAGER
    private Database db;

    //CONSTRUCTOR
    public DBManager(Context context){
        db = Database.getInstance(context);
        open();

    }

    //Konstruktor für Einhaltung des Singleton
    private DBManager(){}

    private void open() {
        db.getReadableDatabase();
        Log.d(TAG, "Datenbank FlipAgain geoeffnet");

    }

    private void close(){
        db.close();
        Log.d(TAG, "Datenbank FlipAgain geschlossen");
    }

    @Override
    public void saveBundle(Bundle bundle) {

    }


   /*
   LOKALE DATENBANK
    */

    public ArrayList<FieldOfStudy> getListOfStudy(){
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        Cursor a = dbCon.rawQuery("SELECT " + TBL_FOStudy.getRowNameOfStudy() + "," + TBL_FOStudy.getRowStudyID() + " FROM " + TBL_FOStudy.getTableName(), null);
        try {
            while (a.moveToNext()) {
                int studyID = a.getInt(a.getColumnIndex(TBL_FOStudy.getRowStudyID()));
                String name = a.getString(a.getColumnIndex(TBL_FOStudy.getRowNameOfStudy()));
                ListOfFieldOfStudy.add(new FieldOfStudy(studyID, name));
            }
        } finally {
            dbCon.close();
            close();
            a.close();
        }

        return ListOfFieldOfStudy;
    }

    public HashSet<Module> getListOfModule(String WhereStatement){
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        Cursor b = dbCon.rawQuery("SELECT " + TBL_Module.getRowName() + "," + TBL_Module.getRowModuleID() + " FROM " + TBL_Module.getTableName() + ", fieldofstudy" + " WHERE fieldofstudy.nameofstudy  =" + "'" + WhereStatement + "'  AND fieldofstudy.rowStudyID =" + TBL_Module.getTableName() + ".rowStudyID", null);
        try {
            while (b.moveToNext()) {
                int moduleID = b.getInt(b.getColumnIndex(TBL_Module.getRowModuleID()));
                String name = b.getString(b.getColumnIndex(TBL_Module.getRowName()));
                SetOfModule.add(new Module(moduleID, name));
            }
        } finally {
            dbCon.close();
            b.close();
            close();
        }

        return SetOfModule;
    }

    public ArrayList<Bundle> getListOfBundle(String WhereStatement){
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        Cursor c = dbCon.rawQuery("SELECT " + TBL_Bundle.getName() + "," + TBL_Bundle.getBundleID() + " FROM " + TBL_Bundle.getTableName() + ", module" + " WHERE module.rowName  =" + "'" + WhereStatement + "'  AND module.rowModuleID =" + TBL_Bundle.getTableName() + ".moduleID", null);
        try {
            while (c.moveToNext()) {
                int bundleID = c.getInt(c.getColumnIndex( TBL_Bundle.getBundleID()));
                String name = c.getString(c.getColumnIndex(TBL_Bundle.getName()));
                Log.d(TAG, " created name " + name + "   created id " + bundleID);
                ListOfBundle.add(new Bundle(bundleID, name, 0));                                                     // HIER MUSS NOCH USERID GEADDED WERDEN!
            }
        } finally {
            dbCon.close();
            c.close();
            close();
        }

        return ListOfBundle;
    }

    public ArrayList<Bundle> getListOfBundleFromUser(String WhereStatement){
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        String user = "1";
        Cursor c = dbCon.rawQuery("SELECT " + TBL_Bundle.getName() + "," + TBL_Bundle.getBundleID() + " FROM " + TBL_Bundle.getTableName() + ", module" + " WHERE module.rowName  =" + "'" + WhereStatement + "'  AND module.rowModuleID =" + TBL_Bundle.getTableName() + ".moduleID" + " AND bundle.userID =" + user, null);
        try {
            while (c.moveToNext()) {
                int bundleID = c.getInt(c.getColumnIndex(TBL_Bundle.getBundleID()));
                String name = c.getString(c.getColumnIndex(TBL_Bundle.getName()));
                Log.d(TAG, " created name " + name + "   created id " + bundleID);
                ListOfBundle.add(new Bundle(bundleID, name, 0));                                                     // HIER MUSS NOCH USERID GEADDED WERDEN!
            }
        } finally {
            dbCon.close();
            c.close();
            close();
        }

        return ListOfBundle;
    }



    public ArrayList<Card> getListofCard(String WhereStatement){
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        Cursor d = dbCon.rawQuery("SELECT " + TBL_Card.getAnswer() + "," + TBL_Card.getQuestion() + ", "+ TBL_Card.getCardID()+ ", " + TBL_Card.getRating()+ " FROM " + TBL_Card.getTableName() + ", bundle" + " WHERE bundle.name  =" + "'" + WhereStatement + "'  AND bundle.bundleID =" + TBL_Card.getTableName() + ".bundleID", null);
        try {
            while (d.moveToNext()) {
                int cardID = d.getInt(d.getColumnIndex(TBL_Card.getCardID()));
                String question = d.getString(d.getColumnIndex(TBL_Card.getQuestion()));
                String answer = d.getString(d.getColumnIndex(TBL_Card.getAnswer()));
                int rating = d.getInt(d.getColumnIndex(TBL_Card.getRating()));

                ListOfCard.add(new Card(cardID,0 , question ,answer ,  1));
            }
        } finally {
            dbCon.close();
            d.close();
        }

        return ListOfCard;
    }

    @Override
    public User getUser() {
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        Cursor d = dbCon.rawQuery("Select " + TBL_User.getName() + ", " + TBL_User.getPassword() + ", " + TBL_User.getUserID() + " FROM " + TBL_User.getTableName(), null);
        User user = null;
        try{
            while (d.moveToNext()){
               user = new User(
                       d.getInt(d.getColumnIndex(TBL_User.getUserID())) ,
                       d.getString(d.getColumnIndex(TBL_User.getName())),
                       d.getString(d.getColumnIndex(TBL_User.getPassword())));
            }
        }finally {
            db.close();
            dbCon.close();
        }

        return user;
    }

    @Override
    public void registerUser(User user) {
        final SQLiteDatabase dbCon = db.getWritableDatabase();
        Cursor cursor = dbCon.rawQuery("SELECT " + TBL_User.getUserID() + ", " + TBL_User.getName() + ", " + TBL_User.getPassword() + " FROM " + TBL_User.getTableName(), null);
        try {
            cursor.moveToFirst();
            final ContentValues data = new ContentValues();
            data.put(TBL_User.getEmail(), user.getUsername());
            data.put(TBL_User.getUserID(), user.getUserId());
            data.put(TBL_User.getPassword(),user.getPassword());
            dbCon.insertOrThrow(TBL_User.getTableName(),null,data);
        }finally {
            dbCon.close();
            cursor.close();
        }
    }



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

    @Override
    public void insertCard(String nameOfBundle, String question, String solution) {
        final SQLiteDatabase dbCon = db.getWritableDatabase();
        Cursor cursor = dbCon.rawQuery("SELECT " + TBL_Bundle.getName() + "," + TBL_Bundle.getBundleID() + " FROM " + TBL_Bundle.getTableName() + " WHERE " + TBL_Bundle.getName() + " = " + "'" + nameOfBundle + "'", null);
        int bundleID;
        try {
            cursor.moveToFirst();
            bundleID = cursor.getInt(cursor.getColumnIndex(TBL_Bundle.getBundleID()));


            final ContentValues data = new ContentValues();
            data.put(TBL_Card.getQuestion(), question);
            data.put(TBL_Card.getAnswer(), solution);
            data.put(TBL_Card.getBundleID(), bundleID);

            /**
             * HIER MUSS NOCH EIN CARD Obj erstellt werden und dem Server geschickt werden.
             */

            final long id = dbCon.insertOrThrow(TBL_Card.getTableName(),null, data);
            Log.i(TAG, "Field of Study mit id=" + id + " erzeugt.");
        }finally {
            dbCon.close();
            cursor.close();
        }
    }





}
