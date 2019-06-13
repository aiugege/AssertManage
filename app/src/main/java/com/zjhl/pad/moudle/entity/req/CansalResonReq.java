package com.zjhl.pad.moudle.entity.req;

/* 
* File: CansalResonReq.java 取消
* Author: DELL 
* Version: V1.0
* Create: 2018/5/25 10:48 
* Changes (from 2018/5/25) 
*/
public class CansalResonReq {

    private String id;
    private String operateButton;
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

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
}
