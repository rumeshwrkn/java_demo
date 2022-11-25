package com.controllers;

public class ValidationRules {
    public static boolean isEmpty(String val){
        return  (val == null || val.isEmpty() || val.isBlank());
    }
    public static boolean minLen(String val, Integer len){
        isEmpty(val);
        return val.length() >= len;
    }
    public static boolean isMobile(String val){
        isEmpty(val);
        return val.matches("\\d{10}");
    }
    public static boolean isText(String val){
        isEmpty(val);
        return val.matches("[a-zA-Z ]+");
    }
    public  static boolean isEmail(String val){
        return val.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}");
    }




}
