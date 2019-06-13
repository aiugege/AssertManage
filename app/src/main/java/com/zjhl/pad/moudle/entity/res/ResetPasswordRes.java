package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;

/*
* File: ResetPassword.java 登录后的重置密码
* Author: DELL 
* Version: V1.0
* Create: 2018/5/2 20:05 
* Changes (from 2018/5/2) 
*/
public class ResetPasswordRes implements Serializable {


    /**
     * code : 500
     * message : 2年内设置密码重复
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : null
     * json : null
     * list : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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

    @Override
    public String toString() {
        return "ResetPassword{" +
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
