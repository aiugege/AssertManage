package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;

/**
 * @desc: LoginCheckUserPasswordRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.entity.res
 * @author: pluto
 * @create: 2018/4/18 13:54
 * @projectname: nnkj
 **/
public class LoginCheckUserPasswordRes extends ResponseBean {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"id":1,"realName":"gai99","userName":"sun","userPassword":"88163384D83D3CA310BC09CD0B632455","email":"694726990@qq.com","phone":"17600123560","userType":"1","channel":1,"languageType":1,"lockState":1,"remark":"1","createTime":"2018-03-27 18:31:35","updateTime":"2018-04-16 13:39:17","yn":1,"phoneBind":0,"fcompanyOrgId":1}
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
         * id : 1
         * realName : gai99
         * userName : sun
         * userPassword : 88163384D83D3CA310BC09CD0B632455
         * email : 694726990@qq.com
         * phone : 17600123560
         * userType : 1
         * channel : 1
         * languageType : 1
         * lockState : 1
         * remark : 1
         * createTime : 2018-03-27 18:31:35
         * updateTime : 2018-04-16 13:39:17
         * yn : 1
         * phoneBind : 0
         * fcompanyOrgId : 1
         */

        private int id;
        private String realName;
        private String userName;
        private String userPassword;
        private String email;
        private String phone;
        private String userType;
        private int channel;
        private int languageType;
        private int lockState;
        private String remark;
        private String createTime;
        private String updateTime;
        private int yn;
        private int phoneBind;
        private int fcompanyOrgId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getLockState() {
            return lockState;
        }

        public void setLockState(int lockState) {
            this.lockState = lockState;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public int getPhoneBind() {
            return phoneBind;
        }

        public void setPhoneBind(int phoneBind) {
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
                    ", remark='" + remark + '\'' +
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
        return "LoginCheckUserPasswordRes{" +
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
