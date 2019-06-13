package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;

/*
* File: MineStateRes.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/7 20:36 
* Changes (from 2018/5/7) 
*/
public class MineStateRes implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"userId":1,"userType":"2","lockState":"1","orgId":1,"orgState":104}
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
         * userId : 1
         * userType : 2
         * lockState : 1
         * orgId : 1
         * orgState : 104
         */

        private int userId;
        private String userType;
        private String lockState;
        private int orgId;
        private int orgState;
        private String orgType;


        public String getOrgType() {
            return orgType;
        }

        public void setOrgType(String orgType) {
            this.orgType = orgType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getLockState() {
            return lockState;
        }

        public void setLockState(String lockState) {
            this.lockState = lockState;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getOrgState() {
            return orgState;
        }

        public void setOrgState(int orgState) {
            this.orgState = orgState;
        }
    }

    @Override
    public String toString() {
        return "MineStateRes{" +
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
