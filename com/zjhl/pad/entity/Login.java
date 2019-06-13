package com.zjhl.pad.entity;

import java.io.Serializable;

/*
* File: Login.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/4/11 9:50 
* Changes (from 2018/4/11) 
*/
public class Login implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"org":{"id":1,"companyUserId":1,"companyTypeId1":"3","companyTypeId2":"2","areaId":"1","countryId":"1","companyName":"中国银行","companyDomicile":"1","contactName":"中国银行","content":null,"checkState":101,"lockState":2,"gradeCode":"1","remark":null,"inviteCode":null,"createTime":"2018-04-07 16:25:24","updateTime":"2018-04-11 09:18:31","yn":1,"orgDetail":"{\"licenseImgUrl\":{\"paramUrl\":\"AAA.DOC\",\"paramCode\":\"B23-01-58\"},\"licenseNO\":\"营业执照号\",\"orgId\":\"1\",\"orgRegisterAddr\":\"注册地址\"}"},"currentId":1,"updatepwdFlag":"0","list":{"密码重置":"","资料补充":"/companyOrg/mackupCompany","柜员管理":"/user/findCompanyUserList","我的资产":"/user ","企业信息":"/user/orgInfo"},"token":"1ceed47e28e942e7a052efaa74d036a9","status":101}
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
         * org : {"id":1,"companyUserId":1,"companyTypeId1":"3","companyTypeId2":"2","areaId":"1","countryId":"1","companyName":"中国银行","companyDomicile":"1","contactName":"中国银行","content":null,"checkState":101,"lockState":2,"gradeCode":"1","remark":null,"inviteCode":null,"createTime":"2018-04-07 16:25:24","updateTime":"2018-04-11 09:18:31","yn":1,"orgDetail":"{\"licenseImgUrl\":{\"paramUrl\":\"AAA.DOC\",\"paramCode\":\"B23-01-58\"},\"licenseNO\":\"营业执照号\",\"orgId\":\"1\",\"orgRegisterAddr\":\"注册地址\"}"}
         * currentId : 1
         * updatepwdFlag : 0
         * list : {"密码重置":"","资料补充":"/companyOrg/mackupCompany","柜员管理":"/user/findCompanyUserList","我的资产":"/user ","企业信息":"/user/orgInfo"}
         * token : 1ceed47e28e942e7a052efaa74d036a9
         * status : 101
         */

        private OrgBean org;
        private int currentId;
        private String updatepwdFlag;
        private ListBean list;
        private String token;
        private int status;

        public OrgBean getOrg() {
            return org;
        }

        public void setOrg(OrgBean org) {
            this.org = org;
        }

        public int getCurrentId() {
            return currentId;
        }

        public void setCurrentId(int currentId) {
            this.currentId = currentId;
        }

        public String getUpdatepwdFlag() {
            return updatepwdFlag;
        }

        public void setUpdatepwdFlag(String updatepwdFlag) {
            this.updatepwdFlag = updatepwdFlag;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
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

        public static class OrgBean {
            /**
             * id : 1
             * companyUserId : 1
             * companyTypeId1 : 3
             * companyTypeId2 : 2
             * areaId : 1
             * countryId : 1
             * companyName : 中国银行
             * companyDomicile : 1
             * contactName : 中国银行
             * content : null
             * checkState : 101
             * lockState : 2
             * gradeCode : 1
             * remark : null
             * inviteCode : null
             * createTime : 2018-04-07 16:25:24
             * updateTime : 2018-04-11 09:18:31
             * yn : 1
             * orgDetail : {"licenseImgUrl":{"paramUrl":"AAA.DOC","paramCode":"B23-01-58"},"licenseNO":"营业执照号","orgId":"1","orgRegisterAddr":"注册地址"}
             */

            private int id;
            private int companyUserId;
            private String companyTypeId1;
            private String companyTypeId2;
            private String areaId;
            private String countryId;
            private String companyName;
            private String companyDomicile;
            private String contactName;
            private Object content;
            private int checkState;
            private int lockState;
            private String gradeCode;
            private Object remark;
            private Object inviteCode;
            private String createTime;
            private String updateTime;
            private int yn;
            private String orgDetail;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCompanyUserId() {
                return companyUserId;
            }

            public void setCompanyUserId(int companyUserId) {
                this.companyUserId = companyUserId;
            }

            public String getCompanyTypeId1() {
                return companyTypeId1;
            }

            public void setCompanyTypeId1(String companyTypeId1) {
                this.companyTypeId1 = companyTypeId1;
            }

            public String getCompanyTypeId2() {
                return companyTypeId2;
            }

            public void setCompanyTypeId2(String companyTypeId2) {
                this.companyTypeId2 = companyTypeId2;
            }

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getCountryId() {
                return countryId;
            }

            public void setCountryId(String countryId) {
                this.countryId = countryId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCompanyDomicile() {
                return companyDomicile;
            }

            public void setCompanyDomicile(String companyDomicile) {
                this.companyDomicile = companyDomicile;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public int getCheckState() {
                return checkState;
            }

            public void setCheckState(int checkState) {
                this.checkState = checkState;
            }

            public int getLockState() {
                return lockState;
            }

            public void setLockState(int lockState) {
                this.lockState = lockState;
            }

            public String getGradeCode() {
                return gradeCode;
            }

            public void setGradeCode(String gradeCode) {
                this.gradeCode = gradeCode;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getInviteCode() {
                return inviteCode;
            }

            public void setInviteCode(Object inviteCode) {
                this.inviteCode = inviteCode;
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

            public String getOrgDetail() {
                return orgDetail;
            }

            public void setOrgDetail(String orgDetail) {
                this.orgDetail = orgDetail;
            }
        }

        public static class ListBean {
            /**
             * 密码重置 :
             * 资料补充 : /companyOrg/mackupCompany
             * 柜员管理 : /user/findCompanyUserList
             * 我的资产 : /user
             * 企业信息 : /user/orgInfo
             */

            private String 密码重置;
            private String 资料补充;
            private String 柜员管理;
            private String 我的资产;
            private String 企业信息;

            public String get密码重置() {
                return 密码重置;
            }

            public void set密码重置(String 密码重置) {
                this.密码重置 = 密码重置;
            }

            public String get资料补充() {
                return 资料补充;
            }

            public void set资料补充(String 资料补充) {
                this.资料补充 = 资料补充;
            }

            public String get柜员管理() {
                return 柜员管理;
            }

            public void set柜员管理(String 柜员管理) {
                this.柜员管理 = 柜员管理;
            }

            public String get我的资产() {
                return 我的资产;
            }

            public void set我的资产(String 我的资产) {
                this.我的资产 = 我的资产;
            }

            public String get企业信息() {
                return 企业信息;
            }

            public void set企业信息(String 企业信息) {
                this.企业信息 = 企业信息;
            }
        }
    }

}
