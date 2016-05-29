package com.team.flipagain.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by delay on 27.03.2016.
 */
public class Database extends SQLiteOpenHelper {
    private String TAG = "DBMANAGER";
    /**
     * NAME OF DATABASE
     */
    private static final String DATABASE_NAME = "flipAgain.db";
    private static final int DATABASE_VERSION = 1;

    private static Database sINSTANCE;
    private static Object sLOCK = "";


    public static Database getInstance(Context context){
        if( sINSTANCE == null ) {
            synchronized(sLOCK) {
                if( sINSTANCE == null ) {
                    sINSTANCE = new Database(context.getApplicationContext());
                }
            }
        }
        return sINSTANCE;
    }

    private Database(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_NAME = "test";
        String moduleID = "testID";
        String name = "name";


        db.execSQL(TBL_FOStudy.SQL_CREATE);
        Log.d(TAG, " Created FOS");
        db.execSQL(TBL_User.SQL_CREATE);
        Log.d(TAG, " Created USER");
        db.execSQL(TBL_Module.SQL_CREATE);
        Log.d(TAG, " Created Module");
        db.execSQL(TBL_Bundle.SQL_CREATE);
        Log.d(TAG, " Created Bundle");
        db.execSQL(TBL_Card.SQL_CREATE);
        Log.d(TAG, " Created Card");


        //insert default values
        db.execSQL(TBL_FOStudy.STMT_FieldOfStudyInsert("'Informatik'"));
        db.execSQL(TBL_FOStudy.STMT_FieldOfStudyInsert("'Wing'"));
        db.execSQL(TBL_FOStudy.STMT_FieldOfStudyInsert("'Maschinenbau'"));
        db.execSQL(TBL_FOStudy.STMT_FieldOfStudyInsert("'Raumplanung'"));
        db.execSQL(TBL_FOStudy.STMT_FieldOfStudyInsert("'Elektrotechnik'"));

        //insert default values
        db.execSQL((TBL_Module.STMT_ModuleInsert("'Informationsicherheit'" , "'1'")));
        db.execSQL((TBL_Module.STMT_ModuleInsert("'prog1'" , "'1'")));
        db.execSQL((TBL_Module.STMT_ModuleInsert("'Wirtschaft'" , "'2'")));
        db.execSQL((TBL_Module.STMT_ModuleInsert("'Wirtschaftsprojekt'" , "'2'")));
        db.execSQL((TBL_Module.STMT_ModuleInsert("'How Engine works'" , "'3'")));
        db.execSQL((TBL_Module.STMT_ModuleInsert("'Gegend beobachten'" , "'4'")));
        db.execSQL((TBL_Module.STMT_ModuleInsert("'Stecker einstecken'" , "'5'")));

        //insert default bundles
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'CIA Fragen'","'1'" , "'1'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Modul infsi Fragen'","'1'" , "'1'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Generics Fragen'","'1'" , "'2'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Modul prog1 Fragen'","'1'" , "'2'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Modul programieren1 Fragen'","'1'" , "'2'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Modul Wirtschaft Fragen'","'1'" , "'3'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Modul Wirtschaftprojekt Fragen'","'1'" , "'4'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'how engine works fragen'", "'1'", "'5'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'how engine works fragen1'", "'1'", "'5'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Wie wird beobachtet'", "'1'", "'6'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'ohne Kurzschluss'", "'1'", "'7'"));

        //insert default bundles
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?1", " 1Das funktioniert so CIA Fragen", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?2", " 2Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?3", " 3Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?4", " 4Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?5", " 5Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?6", " 6Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?7", " 7Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?8", " 8Das funktioniert so", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?9", " 9Das funktioniert so (Letzte Karte)", "0", "1"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?1", " Das funktioniert so Modul infsi Fragen", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?2", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?3", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?4", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?5", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?6", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?7", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?8", " Das funktioniert so", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?9", " Das funktioniert so  (Letzte Karte)", "0", "2"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?1", " Das funktioniert so Generics Fragen", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?2", " Das funktioniert so", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?3", " Das funktioniert so", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?4", " Das funktioniert so", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?5", " Das funktioniert so", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?6", " Das funktioniert so", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?7", " Das funktioniert so  (Letzte Karte)", "0", "3"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?1", " Das funktioniert so Modul prog1 Fragen", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?2", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?3", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?4", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?5", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?6", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?7", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?8", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?9", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?10", " Das funktioniert so", "0", "4"));
        db.execSQL(TBL_Card.STMT_CardInsert("Wie funktioniert das?11", " Das funktioniert so  (Letzte Karte)", "0", "4"));




    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(TBL_User.SQL_DROP);
        onCreate(db);
    }
}
