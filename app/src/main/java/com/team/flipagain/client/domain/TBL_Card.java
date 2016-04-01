package com.team.flipagain.client.domain;

/**
 * Created by delay on 28.03.2016.
 */
public class TBL_Card {
    /**
     * Name der Datenbanktabelle
     */
    private static final String TABLE_NAME = "card";
    /**
     * PrimaryKey
     */
    private static final String cardID = "cardID";

    /**
     * Attribute
     */
    private static final String question = "question";
    private static final String answer = "answer";
    private static final String rating = "rating";
    /**
     * Foreign Key
     */
    private static final String bundleID = "bundleID";
    /**
     * SQL Anweisung zur Schemadefintion
     */
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            cardID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
            question + " TEXT NOT NULL," +
            answer + " TEXT NOT NULL," +
            rating + " INTEGER," +
            bundleID + " INTEGER REFERENCES bundle" +
            ")";

    /**
     * Standart-Sortierreihenfolge für die Tabelle
     */
    public static final String DEFAULT_SORT_ORDER =
            cardID;

    /**
     * SQL Anweisung zum loeschen der Tabelle
     */

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " +
                    TABLE_NAME;

    /**
     *
     * @param question
     * @param answer
     * @param rating
     * @param bundleID
     * @return
     */
    public static String STMT_CardInsert( String question, String  answer , String rating , String bundleID){
        return "INSERT INTO " + TABLE_NAME +
                "(" +getQuestion() + ", "+ getAnswer() + ", " + getRating()+ ", " + getBundleID() + "  ) " + "VALUES ('"+question+ "', '"+ answer+ "', '"+rating+ "' , '" + bundleID + "')";
    }

    /**
     * SQL STATEMENT DELETE
     */
    public static final String STMT_STUDY_DELETE_BY_NAME =
            "DELETE " + TABLE_NAME +
                    " WHERE cardID = ?";


    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getCardID() {
        return cardID;
    }

    public static String getQuestion() {
        return question;
    }

    public static String getAnswer() {
        return answer;
    }

    public static String getRating() {
        return rating;
    }

    public static String getBundleID() {
        return bundleID;
    }
}
