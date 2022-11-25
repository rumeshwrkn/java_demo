package com.controllers;

import javafx.scene.control.CheckBox;

public class User {

    public String id, fname, lname, mobile, email , status;
    public CheckBox selectUser;

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public CheckBox getSelectUser() {
        return selectUser;
    }

    public void setSelectUser(CheckBox selectUser) {
        this.selectUser = selectUser;
    }

    public User(String id, String fname, String lname, String mobile, String email, String status) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
        this.selectUser = new CheckBox();
    }

}
