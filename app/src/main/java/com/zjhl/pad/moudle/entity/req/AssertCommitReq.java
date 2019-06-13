package com.zjhl.pad.moudle.entity.req;

/* 
* File: AssertCommitReq.java
* Author: DELL 
* Version: V1.0
* Create: 2018/5/24 15:47 
* Changes (from 2018/5/24) 
*/
public class AssertCommitReq {

    private String id;

    private String operateButton;
    private String refuseAdvice;
    private String advise;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperateButton() {
        return operateButton;
    }

    public void setOperateButton(String operateButton) {
        this.operateButton = operateButton;
    }

    public String getRefuseAdvice() {
        return refuseAdvice;
    }

    public void setRefuseAdvice(String refuseAdvice) {
        this.refuseAdvice = refuseAdvice;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
}
