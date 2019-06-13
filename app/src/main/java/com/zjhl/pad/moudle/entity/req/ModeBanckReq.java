package com.zjhl.pad.moudle.entity.req;

/*
* File: ModeBanckReq.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/10 20:21 
* Changes (from 2018/5/10) 
*/
public class ModeBanckReq {

    private String phone;
    private String email;
    private String id;
    private String userPassword;
    private String userType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
