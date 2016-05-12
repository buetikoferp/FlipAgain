package com.team.flipagain.domain;

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
    private static final String rowModuleID = "rowModuleID";

    /**
     * Attribute
     */
    private static final String rowName = "rowName";
    /**
     * Foreign keys
     */
    private static final String studyID = "rowStudyID";
    /**
     * SQL Anweisung zur Schemadefintion
     */
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            rowModuleID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
            rowName + " TEXT NOT NULL," +
            studyID + " INTEGER REFERENCES fieldofstudy" +
            ")";

    /**
     * Standart-Sortierreihenfolge für die Tabelle
     */
    public static final String DEFAULT_SORT_ORDER =
            rowName;

    /**
     * SQL Anweisung zum loeschen der Tabelle
     */

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " +
                    TABLE_NAME;

    /**
     *
     * @param name
     * @param fieldOfstudyID
     * @return String
     */
    public static String STMT_ModuleInsert(String name, String fieldOfstudyID){
        return "INSERT INTO " + TABLE_NAME +
                "( " + rowName + " , " + studyID + " ) " + "VALUES (" + name + " , " + fieldOfstudyID + ")";
    }
    /**
     * SQL STATEMENT DELETE
     */
    public static final String STMT_STUDY_DELETE_BY_NAME =
            "DELETE " + TABLE_NAME +
                    " WHERE rowName = ?";


    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getRowModuleID() {
        return rowModuleID;
    }

    public static String getRowName() {
        return rowName;
    }

    public static String getStudyID() {
        return studyID;
    }
}


