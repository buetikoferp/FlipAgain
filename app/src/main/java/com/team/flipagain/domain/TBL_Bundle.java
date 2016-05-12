package com.team.flipagain.domain;

/**
 * Created by delay on 27.03.2016.
 */
public final class TBL_Bundle {
    /**
     * Name der Datenbanktabelle
     */
    private static final String TABLE_NAME = "bundle";
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
            name + " TEXT NOT NULL," +
            userID + " INTEGER REFERENCES user," +
            moduleID + " INTEGER REFERENCES module" +

            ")";

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
     *
     * @param bundleName
     * @param user_ID
     * @param module_ID
     * @return SQL String
     */
    public static String STMT_BundleInsert(String bundleName , String user_ID ,String module_ID){
        return "INSERT INTO " + TABLE_NAME +
                "( " +name+ "," +userID+ "," +moduleID+ " ) " + "VALUES (" + bundleName + ", " +user_ID+", " + module_ID + ")";
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

    public static String getBundleID() {
        return bundleID;
    }

    public static String getName() {
        return name;
    }

    public static String getUserID() {
        return userID;
    }

    public static String getModuleID() {
        return moduleID;
    }
}
