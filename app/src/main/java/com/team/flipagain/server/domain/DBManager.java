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

        String username = user.getUsername();
        String password = user.getPassword();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_user");
        System.out.println(rs);
        while (rs.next()) {

            if (username.equals(rs.getString("username") )&& password.equals(rs.getString("password"))) {
                user.setIsAuthorized(true);
            }
        }
       return user;
    }

    public Connection getConnection()throws SQLException{


        return conn;
    }


    public static void main(String[] argv)  {

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


            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

    }



}
