package com.models;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Db {
    public static Connection DbConn;
    public static Connection getConn() {
        try {
            DbConn =  DriverManager.getConnection("jdbc:mysql://localhost/fx_app", "root", "");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return DbConn;
    }
}
