package com.controllers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.models.UserTypesModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {
    public static String selectedUserStatus(String val, Boolean isInverse) {
        BiMap<String, String> status = HashBiMap.create();
        UserTypesModel userTypesModel = new UserTypesModel();
        ResultSet rs = userTypesModel.getTypes();
        try {
            while (rs.next()){
                status.put(rs.getString("ut_id"), rs.getString("ut_name"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        if (isInverse) {
            return status.inverse().get(val);
        }else{
            return  status.get(val);
        }
    }

    public static BiMap<String, String> userType() {
        BiMap<String, String> userType = HashBiMap.create();
        UserTypesModel userTypesModel = new UserTypesModel();
        ResultSet rs = userTypesModel.getTypes();
        try {
            while (rs.next()){
                userType.put(rs.getString("ut_id"), rs.getString("ut_name"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return userType;
    }

    public static Object userStatus(String val, boolean isInverse){
        BiMap<String, String> status = HashBiMap.create();
        status.put("1", "Active");
        status.put("0", "In-active");

        if (val.isEmpty()){
            return status;
        }else{
            if (isInverse){
                return status.inverse().get(val);
            }else{
                return status.get(val);
            }
        }



    }



}
