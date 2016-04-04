package com.team.flipagain.server.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Philipp on 31.03.2016.
 */
public class DBManager implements DomainInterface{
    private Connection conn;



    @Override
    public User getUser(User user) throws SQLException {
        //TODO SQL STATEMENT FÜR USER;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_user");
        System.out.println(rs);


        return null;
    }


    public static void main(String[] argv) throws SQLException {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/flipagain", "postgres","Raffaele123");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_user");


            while (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("Username: "+username+" Password: "+password);
            }

            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

    }



}
