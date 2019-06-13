package com.zjhl.pad.moudle.entity.req;

/* 
* File: TellerManagementReq.java 柜员添加
* Author: DELL 
* Version: V1.0
* Create: 2018/5/4 10:42 
* Changes (from 2018/5/4) 
*/
public class TellerManagementReq {

    private int page;
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
