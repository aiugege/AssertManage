package com.zjhl.pad.moudle.entity.req;

/* 
* File: CheckBindPhoneNumberQeq.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/13 11:58 
* Changes (from 2018/5/13) 
*/
public class CheckBindPhoneNumberReq {
    private String mobile;
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
