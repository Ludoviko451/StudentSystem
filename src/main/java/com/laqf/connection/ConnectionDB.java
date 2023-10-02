package com.laqf.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private String db = "sena_db";
    private String url = "jdbc:mysql://localhost:3306/" + db;
    private String name = "root";
    private String pass = "";

    public ConnectionDB() {
        // Constructor vacío
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return connection;
    }



    public static void main(String[] args) {
        ConnectionDB connectionDB = new ConnectionDB();
        Connection c = connectionDB.getConnection();

        if (c != null) {
            System.out.println("Successful connection");
        } else {
            System.out.println("Connection error");}
    }
}

























