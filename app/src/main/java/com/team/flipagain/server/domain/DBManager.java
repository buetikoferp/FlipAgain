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

    public DBManager(){
        startConnection();
    }

    @Override
    public User getUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_user");
            System.out.println(rs);
            while (rs.next()) {

                if (username.equals(rs.getString("username") )&& password.equals(rs.getString("password"))) {
                    user.setIsAuthorized(true);
                }else{
                    System.out.println("Login fail: username or password is wrong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return user;
    }

    /**
     * Startet die verbindungen zur Datenbank
     */
    private void startConnection(){
        try{
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/flipagain", "postgres","Raffaele123");
        }catch(SQLException e){
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (conn != null) {
            System.out.println("Connection success");
        } else {
            System.out.println("Failed to make connection!");
        }
    }







}
