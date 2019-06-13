package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: TellerManagementRes.java 柜员列表
* Author: DELL 
* Version: V1.0
* Create: 2018/5/4 13:33 
* Changes (from 2018/5/4) 
*/
public class TellerManagementRes implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 1
     * totalPage : 1
     * pageSize : 2
     * page : 1
     * data : [{"id":2080,"realName":"陈桂峰","userName":null,"userPassword":"A65FA9809FA72F6FE694C9909CB3D4B6","email":"itheng@126.com","phone":"18641399433","userType":"1","channel":1,"languageType":1,"lockState":1,"remark":null,"createTime":"2018-05-03 13:39:31","updateTime":"2018-05-04 11:48:59","yn":1,"phoneBind":null,"fcompanyOrgId":2042}]
     * json : null
     * list : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;

    private Object json;
    private Object list;
    private List<DataBean> data;


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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2080
         * realName : 陈桂峰
         * userName : null
         * userPassword : A65FA9809FA72F6FE694C9909CB3D4B6
         * email : itheng@126.com
         * phone : 18641399433
         * userType : 1
         * channel : 1
         * languageType : 1
         * lockState : 1
         * remark : null
         * createTime : 2018-05-03 13:39:31
         * updateTime : 2018-05-04 11:48:59
         * yn : 1
         * phoneBind : null
         * fcompanyOrgId : 2042
         */

        private String id;
        private String realName;
        private String userName;
        private String userPassword;
        private String email;
        private String phone;
        private String userType;
        private int channel;
        private int languageType;
        private String lockState;
        private Object remark;
        private String createTime;
        private String updateTime;
        private int yn;
        private Object phoneBind;
        private int fcompanyOrgId;
        private String certificate;

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public int getLanguageType() {
            return languageType;
        }

        public void setLanguageType(int languageType) {
            this.languageType = languageType;
        }

        public String getLockState() {
            return lockState;
        }

        public void setLockState(String lockState) {
            this.lockState = lockState;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getYn() {
            return yn;
        }

        public void setYn(int yn) {
            this.yn = yn;
        }

        public Object getPhoneBind() {
            return phoneBind;
        }

        public void setPhoneBind(Object phoneBind) {
            this.phoneBind = phoneBind;
        }

        public int getFcompanyOrgId() {
            return fcompanyOrgId;
        }

        public void setFcompanyOrgId(int fcompanyOrgId) {
            this.fcompanyOrgId = fcompanyOrgId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", realName='" + realName + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userPassword='" + userPassword + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", userType='" + userType + '\'' +
                    ", channel=" + channel +
                    ", languageType=" + languageType +
                    ", lockState=" + lockState +
                    ", remark=" + remark +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", yn=" + yn +
                    ", phoneBind=" + phoneBind +
                    ", fcompanyOrgId=" + fcompanyOrgId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TellerManagementRes{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", json=" + json +
                ", list=" + list +
                ", data=" + data +
                '}';
    }
}
