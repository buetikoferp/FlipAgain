package com.team.flipagain.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.team.flipagain.messaging.ClientMessager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeoutException;

/**
 * Created by Raffaele on 23.03.2016.
 */
public class DBManager  implements DomainInterface{
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
        db.getWritableDatabase();
        Log.d(TAG, "Datenbank FlipAgain geoeffnet");

    }

    private void close(){
        db.close();
        Log.d(TAG, "Datenbank FlipAgain geschlossen");
    }

    /*--------------------------------------------------------Server-------------------------------------------*/
    @Override
    public void saveBundle(Bundle bundle, String module) {
            insertBundle(bundle.getName(),module);

        if(bundle.getCardList() != null){
            Log.d(TAG, "GetCardList ist nicht gleich null! SizeofCardList: " + bundle.getCardList().size() + " Frage der Karte = " + bundle.getCardList().get(0).getQuestion() );

                for (Card card : bundle.getCardList()) {
                    Log.d(TAG, "Frage der Karte: "+card.getQuestion());
                    insertCard(bundle.getName(), card.getQuestion(), card.getAnswer(), true);
                }
            }


    }

    @Override
    public ArrayList<Bundle> getServerListofBundle(String moduleName) {

        Module module = new Module(0 ,moduleName);
        try {
            ClientMessager clientMessager = new ClientMessager("listofbundle");
            ListOfBundle = clientMessager.getBundleList(module);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return ListOfBundle;
    }

    @Override
    public void resetUser() {
        final SQLiteDatabase dbCon = db.getWritableDatabase();
        dbCon.execSQL(TBL_User.STMT_STUDY_DELETE);
    }

    @Override
    public Bundle getBundle() {
        return serverBundle;
    }

    @Override
    public void setBundle(Bundle bundle) {
        serverBundle = bundle;
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

            b.close();
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
                ListOfBundle.add(new Bundle(bundleID, name, getUser().getUserId(), 0));
            }
        } finally {

            c.close();

        }

        return ListOfBundle;
    }

    public ArrayList<Bundle> getListOfBundleFromUser(String WhereStatement){
        final SQLiteDatabase dbCon = db.getReadableDatabase();
        int userID = getUser().getUserId();
        Cursor c = dbCon.rawQuery("SELECT " + TBL_Bundle.getName() + "," + TBL_Bundle.getBundleID() + " FROM " + TBL_Bundle.getTableName() + ", module" + " WHERE module.rowName  =" + "'" + WhereStatement + "'  AND module.rowModuleID =" + TBL_Bundle.getTableName() + ".moduleID" + " AND bundle.userID ='" + userID+"'", null);
        try {
            while (c.moveToNext()) {
                int bundleID = c.getInt(c.getColumnIndex(TBL_Bundle.getBundleID()));
                String name = c.getString(c.getColumnIndex(TBL_Bundle.getName()));
                Log.d(TAG, " created name " + name + "   created id " + bundleID);
                ListOfBundle.add(new Bundle(bundleID, name, 0, 0));                                                     // HIER MUSS NOCH USERID GEADDED WERDEN!
            }
        } finally {

            c.close();

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
            d.close();


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
            dbCon.insertOrThrow(TBL_User.getTableName(), null, data);
        }finally {


            cursor.close();
        }
    }

    private Bundle serverBundle;
    private UploadBundle uploadBundle = null;

    public void insertBundle(String bundleName , String moduleName){

        final SQLiteDatabase dbCon =  db.getWritableDatabase();
        Cursor cursor = dbCon.rawQuery("SELECT " + TBL_Module.getRowName() + "," + TBL_Module.getRowModuleID() + " FROM " + TBL_Module.getTableName() + " WHERE " + TBL_Module.getRowName() + " = " + "'" + moduleName + "'", null);
        int moduleID;
        int userID = getUser().getUserId();
        try {
            cursor.moveToFirst();
            moduleID = cursor.getInt(cursor.getColumnIndex(TBL_Module.getRowModuleID()));


            final ContentValues data = new ContentValues();
            data.put(TBL_Bundle.getName(), bundleName);
            data.put(TBL_Bundle.getUserID(), userID);
            data.put(TBL_Bundle.getModuleID(), moduleID);



            final long id = dbCon.insertOrThrow(TBL_Bundle.getTableName(),null, data);
            serverBundle = new Bundle((int)id + 100 , bundleName,userID, moduleID);

        }finally {

            cursor.close();
        }
    }

    @Override
    public void insertCard(String nameOfBundle, String question, String solution, boolean isDownload) {
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

            final long id = dbCon.insertOrThrow(TBL_Card.getTableName(), null, data);

            serverBundle.getCardList().add(new Card((int) id + 100, getUser().getUserId(), question, solution, serverBundle.getBundleId()));
            if(isDownload == false){
                sendNewBundle();
            }


        }finally {

            cursor.close();
        }
    }

    public void sendNewBundle(){
        uploadBundle = new UploadBundle();
        uploadBundle.execute((Void) null);
    }

    public class UploadBundle extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ClientMessager clientMessager = new ClientMessager("insertNewBundle");

            try {
                clientMessager.insertNewBundle(serverBundle);
                Log.d(TAG, serverBundle.getName() + " wurde geschickt!!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return null;
        }
    }






}
