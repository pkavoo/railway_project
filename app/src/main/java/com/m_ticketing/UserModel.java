package com.m_ticketing;

import com.orm.SugarRecord;

/**
 * Created by CYMMOH on 8/12/2016.
 */
public class UserModel extends SugarRecord {
    String username;
    String name;
    String idNo;
    String email;
    String password;
    String confirmpassword;

    public UserModel() {

    }

    public UserModel(String username, String name, String idNo, String email, String password, String confirmpassword) {
        this.username = username;
        this.name = name;
        this.idNo = idNo;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
