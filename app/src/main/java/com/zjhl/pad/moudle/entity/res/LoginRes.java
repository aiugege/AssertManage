package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;

/*
* File: LoginRes.java
* Author: DELL 
* Version: V1.0
* Create: 2018/4/11 9:50 
* Changes (from 2018/4/11) 
*/
public class LoginRes implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"token":"94ac5d0c6f4e489891b9f0d708993a97","status":113,"companyId":1,"companyName":"gai99","mobile":"18700000113","email":"694726990@qq.com","currentId":1,"list":null,"updatepwdFlag":"0","userType":"1"}
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

    @Override
    public String toString() {
        return "LoginRes{" +
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

    public static class DataBean {
        /**
         * token : 94ac5d0c6f4e489891b9f0d708993a97
         * status : 113
         * companyId : 1
         * companyName : gai99
         * mobile : 18700000113
         * email : 694726990@qq.com
         * currentId : 1
         * list : null
         * updatepwdFlag : 0
         * userType : 1
         */

        private String token;
        private int status;
        private String companyId;
        private String companyName;
        private String mobile;
        private String email;
        private String currentId;
        private Object list;
        private String updatepwdFlag;
        private String userType;
        private String threeMonthUpdate;
        private String realName;
        private String firstLogin;

        public String getFirstLogin() {
            return firstLogin;
        }

        public void setFirstLogin(String firstLogin) {
            this.firstLogin = firstLogin;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getThreeMonthUpdate() {
            return threeMonthUpdate;
        }

        public void setThreeMonthUpdate(String threeMonthUpdate) {
            this.threeMonthUpdate = threeMonthUpdate;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCurrentId() {
            return currentId;
        }

        public void setCurrentId(String currentId) {
            this.currentId = currentId;
        }

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }

        public String getUpdatepwdFlag() {
            return updatepwdFlag;
        }

        public void setUpdatepwdFlag(String updatepwdFlag) {
            this.updatepwdFlag = updatepwdFlag;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }

}
