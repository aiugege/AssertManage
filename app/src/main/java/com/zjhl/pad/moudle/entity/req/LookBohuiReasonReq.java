package com.zjhl.pad.moudle.entity.req;

/*
* File: BohuiReasonReq.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/25 11:44 
* Changes (from 2018/5/25) 
*/
public class LookBohuiReasonReq {

    private String auditId;
    private String auditType;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }
}
