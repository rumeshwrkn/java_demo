package com.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.controllers.Helper;
import com.controllers.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class UserModel {
    private Connection connection = Db.getConn();
    private PreparedStatement stmt;
    private ResultSet rs;


    public boolean RegUser(String fName, String lName, String mobile, String email, String pass, String status){
       boolean result = false;
       String sql = "INSERT INTO users (u_fname, u_lname, u_mobile, u_email, u_pass, u_status) VALUES ( ?, ?, ? , ?, ?, ? )";
       try {
          // PreparedStatement stmt;
           stmt = connection.prepareStatement(sql);
           stmt.setString(1, fName);
           stmt.setString(2, lName);
           stmt.setString(3, mobile);
           stmt.setString(4, email);
           stmt.setString(5, pass);
           stmt.setString(6, status);
           result = stmt.executeUpdate() == 1;
       }catch (SQLException se){
           System.out.println(se.getMessage());
       }
       return result;
   }

   public boolean isUniqueUser(String email) {
       boolean result = false;
       String sql = "SELECT COUNT(u_email) FROM users WHERE u_email = ?";
       try {
           stmt = connection.prepareStatement(sql);
           stmt.setString(1, email);
           ResultSet rs = stmt.executeQuery();
           rs.next();
           result = rs.getInt( 1) == 0;
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
       return result;
   }

    public boolean login(String email, String password){
        boolean result = false;
        String sql = "Select * FROM users WHERE u_email = ? LIMIT 1";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                  BCrypt.Result checkPwd = BCrypt.verifyer().verify(password.toCharArray(),rs.getString("u_pass"));
                  result = checkPwd.verified;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

//   public boolean login(String email, String password){
//        boolean result = false;
//        String sql = "Select * FROM users WHERE u_email = ? AND u_pass = ?";
//        try {
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1,email);
//            stmt.setString(2,password);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()){
//                result = true;
//            }
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return result;
//   }

    public String userStatus(String id){
//        Map<String, String > status = new HashMap<String, String>();
//        status.put("1", "Active");
//        status.put("2", "Inactive");

        BiMap<String, String> status = HashBiMap.create();
        status.put("1","Active");
        status.put("0","Inactive");
        return status.get(id);
    }

   public ObservableList<User> getUsers(){
       ObservableList<User> List = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                String id = rs.getString("u_id");
                String firstName = rs.getString("u_fname");
                String lastName = rs.getString("u_lname");
                String mobile = rs.getString("u_mobile");
                String email = rs.getString("u_email");
                String status = rs.getString("u_status");
                status = Helper.selectedUserStatus(status, false);
                List.add(new User(id, firstName,lastName,mobile,email,status));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return List;
   }

   public boolean update(String field, String val, String id) throws SQLException {
        String sql = "UPDATE users SET " + field + " =  ? WHERE u_id = ? LIMIT 1";

        stmt = connection.prepareStatement(sql);
        stmt.setString(1,val);
        stmt.setString(2,id);
        return stmt.executeUpdate() == 1;

   }
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM users WHERE u_id = ? LIMIT 10";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        return stmt.executeUpdate() == 1;

    }
}
