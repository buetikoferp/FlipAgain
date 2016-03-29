package com.team.flipagain.client.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delay on 27.03.2016.
 */
public class DBManager extends SQLiteOpenHelper {
    /**
     * NAME OF DATABASE
     */
    private static final String DATABASE_NAME = "flipAgain.db";
    private static final int DATABASE_VERSION = 1;

    private static DBManager sINSTANCE;
    private static Object sLOCK = "";


    public static DBManager getInstance(Context context){
        if( sINSTANCE == null ) {
            synchronized(sLOCK) {
                if( sINSTANCE == null ) {
                    sINSTANCE = new DBManager(context.getApplicationContext());
                }
            }
        }
        return sINSTANCE;
    }

    private DBManager(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TBL_FieldOfStudy.SQL_CREATE);
        db.execSQL(TBL_User.SQL_CREATE);
        db.execSQL(TBL_Module.SQL_CREATE);
        db.execSQL(TBL_Bundle.SQL_CREATE);
        db.execSQL(TBL_Card.SQL_CREATE);

        //insert default values
        db.execSQL(TBL_FieldOfStudy.STMT_FieldOfStudyInsert("Informatik"));
        db.execSQL(TBL_FieldOfStudy.STMT_FieldOfStudyInsert("Wing"));
        db.execSQL(TBL_FieldOfStudy.STMT_FieldOfStudyInsert("Maschinenbau"));
        db.execSQL(TBL_FieldOfStudy.STMT_FieldOfStudyInsert("Raumplanung"));
        db.execSQL(TBL_FieldOfStudy.STMT_FieldOfStudyInsert("Elektrotechnik"));
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TBL_FieldOfStudy.SQL_DROP);
        db.execSQL(TBL_User.SQL_DROP);
        db.execSQL(TBL_Module.SQL_DROP);
        db.execSQL(TBL_Bundle.SQL_DROP);
        db.execSQL(TBL_Card.SQL_DROP);
        onCreate(db);
    }
}
