package com.zjhl.pad.entity;

import java.io.Serializable;

/*
* File: LoginMsgCode.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/4/10 13:59 
* Changes (from 2018/4/10) 
*/
public class LoginMsgCode implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"expireDate":1523533173716}
     * json : null
     * list : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private DataBean data;
    private Object json;
    private Object list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public static class DataBean {
        /**
         * expireDate : 1523533173716
         */

        private long expireDate;

        public long getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(long expireDate) {
            this.expireDate = expireDate;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "expireDate=" + expireDate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginMsgCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", data=" + data +
                ", json=" + json +
                ", list=" + list +
                '}';
    }
}
