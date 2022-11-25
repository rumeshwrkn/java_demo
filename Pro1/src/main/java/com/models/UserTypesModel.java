package com.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserTypesModel {

    private Connection conn = Db.getConn();
    private PreparedStatement statement;
    private ResultSet rs;

    public ResultSet getTypes(){
        String sql = "SELECT * FROM user_types";
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return rs;
    }
}
