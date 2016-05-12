package com.team.flipagain.domain;

/**
 * Created by delay on 28.03.2016.
 */
public class TBL_FOStudy {
    /**
     * Name der Datenbanktabelle
     */
    private static final String TABLE_NAME = "fieldofstudy";
    /**
     * PrimaryKey
     */
    private static final String rowStudyID = "rowStudyID";

    /**
     * Attribute
     */
    private static final String rowNameOfStudy = "nameofstudy";
    /**
     * SQL Anweisung zur Schemadefintion
     *
     * create table songs(
     id integer  primary key autoincrement,
     title varchar(20) not null,
     artist int references artist x
     */
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            rowStudyID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            rowNameOfStudy + " TEXT NOT NULL UNIQUE )";

    /**
     * Standart-Sortierreihenfolge für die Tabelle x
     */
    public static final String DEFAULT_SORT_ORDER =
            rowNameOfStudy;

    /**
     * SQL Anweisung zum loeschen der Tabelle
     */

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " +
                    TABLE_NAME;

    /**
     * SQL STATEMENT INSERT NEW FIELDOFSTUDY
     */
    public static String STMT_FieldOfStudyInsert(String name){
        return "INSERT INTO " + TABLE_NAME +
                "( " + rowNameOfStudy + " ) " + "VALUES (" + name + ")";
    }

    /**
     * SQL STATEMENT DELETE BUNDLE BY NAME
     */
    public static final String STMT_BUNDLE_DELETE_BY_NAME =
            "DELETE " + TABLE_NAME +
                    " WHERE name = ?";


    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getRowStudyID() {
        return rowStudyID;
    }

    public static String getRowNameOfStudy() {
        return rowNameOfStudy;
    }
}

