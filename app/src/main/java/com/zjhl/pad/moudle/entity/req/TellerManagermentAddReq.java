package com.zjhl.pad.moudle.entity.req;

/* 
* File: TellerManagermentAddReq.java 添加柜员
* Author: DELL 
* Version: V1.0
* Create: 2018/5/3 14:27 
* Changes (from 2018/5/3) 
*/
public class TellerManagermentAddReq {

    private String realName;
    private String phone;
    private String email;
    private String userPassword;
    private int userType;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
