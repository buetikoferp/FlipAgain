package com.team.flipagain.client.domain;

/**
 * Created by delay on 28.03.2016.
 */
public class TBL_FieldOfStudy {
    /**
     * Name der Datenbanktabelle
     */
    public static final String TABLE_NAME = "fieldofstudy";
    /**
     * PrimaryKey
     */
    private static final String studyID = "studyID";

    /**
     * Attribute
     */
    public static final String name = "name";
    public static final String value = "2";
    /**
     * SQL Anweisung zur Schemadefintion
     */
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            studyID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
            name + " TEXT NOT NULL UNIQUE" +
            ";";

    /**
     * Standart-Sortierreihenfolge für die Tabelle
     */
    public static final String DEFAULT_SORT_ORDER =
            name;

    /**
     * SQL Anweisung zum loeschen der Tabelle
     */

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " +
                    TABLE_NAME;

    /**
     * SQL STATEMENT INSERT NEW FIELDOFSTUDY
     */
    public static final String STMT_FIELDOFSTUDY_INSERT =
            "INSERT INTO " + TABLE_NAME +
                    "(name) " +
                    "VALUES (" + value+");";

    /**
     * SQL STATEMENT DELETE BUNDLE BY NAME
     */
    public static final String STMT_BUNDLE_DELETE_BY_NAME =
            "DELETE " + TABLE_NAME +
                    " WHERE name = ?";






}

