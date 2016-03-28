package com.team.flipagain.client.domain;

/**
 * Created by delay on 28.03.2016.
 */
public class TBL_Module {
    /**
     * Name der Datenbanktabelle
     */
    public static final String TABLE_NAME = "module";
    /**
     * PrimaryKey
     */
    private static final String moduleID = "moduleID";

    /**
     * Attribute
     */
    private static final String name = "name";
    /**
     * Foreign keys
     */
    private static final String studyID = "studyID";
    /**
     * SQL Anweisung zur Schemadefintion
     */
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            moduleID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
            name + " TEXT NOT NULL UNIQUE," +
            studyID + " INTEGER REFERENCES user" +
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
     * SQL STATEMENT INSERT
     */
    public static final String STMT_MODULE_INSERT =
            "INSERT INTO " + TABLE_NAME +
                    "(name) " +
                    "VALUES (?)";

    /**
     * SQL STATEMENT DELETE
     */
    public static final String STMT_STUDY_DELETE_BY_NAME =
            "DELETE " + TABLE_NAME +
                    " WHERE name = ?";






}


