package com.zjhl.pad.moudle.entity.req;

/* 
* File: ResetPasswordReq.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/2 20:25 
* Changes (from 2018/5/2) 
*/
public class ResetPasswordReq {

    private String userName;
    private String oldPwd;
    private String newPwd;
    private String confirmPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
