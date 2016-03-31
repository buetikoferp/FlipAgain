package com.team.flipagain.client.domain;

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
       //db.execSQL(TBL_User.SQL_CREATE);
        Log.d(TAG, " Created USER");
        db.execSQL(TBL_Module.SQL_CREATE);
        Log.d(TAG, " Created Module");
        db.execSQL(TBL_Bundle.SQL_CREATE);
        Log.d(TAG, " Created Bundle");
       // db.execSQL(TBL_Card.SQL_CREATE);
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
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'how engine works fragen'","'1'" , "'5'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'how engine works fragen1'","'1'" , "'5'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'Wie wird beobachtet'","'1'" , "'6'"));
        db.execSQL(TBL_Bundle.STMT_BundleInsert("'ohne Kurzschluss'","'1'" , "'7'"));
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TBL_FOStudy.SQL_DROP);
        db.execSQL(TBL_User.SQL_DROP);
        db.execSQL(TBL_Module.SQL_DROP);
        db.execSQL(TBL_Bundle.SQL_DROP);
        db.execSQL(TBL_Card.SQL_DROP);
        onCreate(db);
    }
}
