package com.team.flipagain.client.domain;

/**
 * Created by delay on 27.03.2016.
 */
public final class TBL_Bundle {
    /**
     * Name der Datenbanktabelle
     */
    public static final String TABLE_NAME = "bundle";
    /**
     * PrimaryKey
     */
    private static final String bundleID = "bundleID";

    /**
     * Attribute
     */
    private static final String name = "name";
    /**
     * ForeignKeys
     */
    private static final String userID = "userID";
    private static final String moduleID = "moduleID";
    /**
     * SQL Anweisung zur Schemadefintion
     */
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                    bundleID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    name + " TEXT NOT NULL UNIQUE," +
                    userID + " INTEGER REFERENCES user," +
                    moduleID + " INTEGER REFERENCES module" +

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
     * SQL STATEMENT INSERT NEW BUNDLE
     */
    public static final String STMT_BUNDLE_INSERT =
            "INSERT INTO " + TABLE_NAME +
                    " (name) " +
                    "VALUES (?)";

    /**
     * SQL STATEMENT DELETE BUNDLE BY NAME
     */
    public static final String STMT_BUNDLE_DELETE_BY_NAME =
            "DELETE " + TABLE_NAME +
                    " WHERE name = ?";






}
