package com.models;

import com.controllers.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentModel {

    private Connection conn = Db.getConn();
    private PreparedStatement stmt;
    private ResultSet rs;


    public ObservableList getStu(){

        ObservableList<Student> ol = FXCollections.observableArrayList();

        String sql = "SELECT * FROM students";
        try {
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            while (rs.next()){
                ol.add(new Student(rs.getString("id"), rs.getString("name") ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return ol;

    }


    public boolean delStudent(String id){
        Boolean result = false;
        String sql = "DELETE FROM students WHERE id = ? LIMIT 1";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            result =  stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }



}
