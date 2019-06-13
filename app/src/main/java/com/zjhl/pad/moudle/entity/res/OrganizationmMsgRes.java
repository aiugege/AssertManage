package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: OrganizationmMsgRes.java  企业信息
* Author: DELL 
* Version: V1.0
* Create: 2018/5/8 10:20 
* Changes (from 2018/5/8) 
*/
public class OrganizationmMsgRes implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"id":2065,"companyTypeId1":"银行用户","companyTypeId2":null,"companyName":"中国邮政储蓄银行金台路支行","companyDomicile":"北京市朝阳区霄云路188号","contactName":"王芳","checkState":101,"swiftCode":"BKCHCNBJ23A","licenseNo":"532501100006302","licenseUrl":"bbb.DOC","orgDetail":"{\"licenseNO\":\"532501100006302\",\"mobile\":\"13958695690\",\"swiftCode\":\"BKCHCNBJ23A\",\"licenseImgUrl\":\"bbb.DOC\",\"orgId\":\"2065\",\"orgRegisterAddr\":\"北京市朝阳区霄云路188号\"}","contactTel":"17600123560","userType":"1","email":"ycwftb@126.com","productList":[{"id":2066,"productCode":"1","productDetail":null,"remark":"中国邮政储蓄银行金台路支行添加","createTime":"2018-05-08 14:32:27","updateTime":"2018-05-08 14:32:27","yn":1,"fcompanyOrgId":2065},{"id":2067,"productCode":"2","productDetail":null,"remark":"中国邮政储蓄银行金台路支行添加","createTime":"2018-05-08 14:32:27","updateTime":"2018-05-08 14:32:27","yn":1,"fcompanyOrgId":2065}]}
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
         * id : 2065
         * companyTypeId1 : 银行用户
         * companyTypeId2 : null
         * companyName : 中国邮政储蓄银行金台路支行
         * companyDomicile : 北京市朝阳区霄云路188号
         * contactName : 王芳
         * checkState : 101
         * swiftCode : BKCHCNBJ23A
         * licenseNo : 532501100006302
         * licenseUrl : bbb.DOC
         * orgDetail : {"licenseNO":"532501100006302","mobile":"13958695690","swiftCode":"BKCHCNBJ23A","licenseImgUrl":"bbb.DOC","orgId":"2065","orgRegisterAddr":"北京市朝阳区霄云路188号"}
         * contactTel : 17600123560
         * userType : 1
         * email : ycwftb@126.com
         * productList : [{"id":2066,"productCode":"1","productDetail":null,"remark":"中国邮政储蓄银行金台路支行添加","createTime":"2018-05-08 14:32:27","updateTime":"2018-05-08 14:32:27","yn":1,"fcompanyOrgId":2065},{"id":2067,"productCode":"2","productDetail":null,"remark":"中国邮政储蓄银行金台路支行添加","createTime":"2018-05-08 14:32:27","updateTime":"2018-05-08 14:32:27","yn":1,"fcompanyOrgId":2065}]
         */

        private int id;
        private String companyTypeId1;
        private String companyTypeId2;
        private String companyTypeId1En;
        private String companyTypeId2En;
        private String companyName;
        private String companyDomicile;
        private String contactName;
        private int checkState;
        private String swiftCode;
        private String licenseNo;
        private String licenseUrl;
        private String orgDetail;
        private String contactTel;
        private String userType;
        private String email;
        private String orgRegisterAddr;
        private String uscc;
        private String officeAddr;
        private String yearFinancialTable;
        private String permitName;
        private String permitNo;
        private String legalPerson;
        private String phoneBind;
        private String auditAdvise;
        private String companyTypeIdEn1;
        private String companyTypeIdEn2;
        private String areaId;
        private String countryId;
        private String areaName;
        private String countryName;
        private String areaNameEn;

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        private String countryNameEn;

        public String getAreaNameEn() {
            return areaNameEn;
        }

        public void setAreaNameEn(String areaNameEn) {
            this.areaNameEn = areaNameEn;
        }

        public String getCountryNameEn() {
            return countryNameEn;
        }

        public void setCountryNameEn(String countryNameEn) {
            this.countryNameEn = countryNameEn;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getCompanyTypeIdEn1() {
            return companyTypeIdEn1;
        }

        public void setCompanyTypeIdEn1(String companyTypeIdEn1) {
            this.companyTypeIdEn1 = companyTypeIdEn1;
        }

        public String getCompanyTypeIdEn2() {
            return companyTypeIdEn2;
        }

        public void setCompanyTypeIdEn2(String companyTypeIdEn2) {
            this.companyTypeIdEn2 = companyTypeIdEn2;
        }

        public String getAuditAdvise() {
            return auditAdvise;
        }

        public void setAuditAdvise(String auditAdvise) {
            this.auditAdvise = auditAdvise;
        }

        public String getPhoneBind() {
            return phoneBind;
        }

        public void setPhoneBind(String phoneBind) {
            this.phoneBind = phoneBind;
        }

        public String getPermitName() {
            return permitName;
        }

        public void setPermitName(String permitName) {
            this.permitName = permitName;
        }

        public String getPermitNo() {
            return permitNo;
        }

        public void setPermitNo(String permitNo) {
            this.permitNo = permitNo;
        }

        public String getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        public String getYearFinancialTable() {
            return yearFinancialTable;
        }

        public void setYearFinancialTable(String yearFinancialTable) {
            this.yearFinancialTable = yearFinancialTable;
        }

        private List<ProductListBean> productList;

//        private OrgDetailBean orgDetail;

        public String getOfficeAddr() {
            return officeAddr;
        }

        public void setOfficeAddr(String officeAddr) {
            this.officeAddr = officeAddr;
        }

        public String getUscc() {
            return uscc;
        }

        public void setUscc(String uscc) {
            this.uscc = uscc;
        }

        public String getCompanyTypeId2En() {
            return companyTypeId2En;
        }

        public void setCompanyTypeId2En(String companyTypeId2En) {
            this.companyTypeId2En = companyTypeId2En;
        }

        public String getCompanyTypeId1En() {
            return companyTypeId1En;
        }

        public void setCompanyTypeId1En(String companyTypeId1En) {
            this.companyTypeId1En = companyTypeId1En;
        }

        public String getOrgRegisterAddr() {
            return orgRegisterAddr;
        }

        public void setOrgRegisterAddr(String orgRegisterAddr) {
            this.orgRegisterAddr = orgRegisterAddr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getCheckState() {
            return checkState;
        }

        public void setCheckState(int checkState) {
            this.checkState = checkState;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public void setSwiftCode(String swiftCode) {
            this.swiftCode = swiftCode;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getOrgDetail() {
            return orgDetail;
        }

        public void setOrgDetail(String orgDetail) {
            this.orgDetail = orgDetail;
        }

//        public OrgDetailBean getOrgDetail() {
//            return orgDetail;
//        }
//
//        public void setOrgDetail(OrgDetailBean orgDetail) {
//            this.orgDetail = orgDetail;
//        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean {
            /**
             * id : 2066
             * productCode : 1
             * productDetail : null
             * remark : 中国邮政储蓄银行金台路支行添加
             * createTime : 2018-05-08 14:32:27
             * updateTime : 2018-05-08 14:32:27
             * yn : 1
             * fcompanyOrgId : 2065
             */

            private int id;
            private String productCode;
            private Object productDetail;
            private String remark;
            private String createTime;
            private String updateTime;
            private int yn;
            private int fcompanyOrgId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public Object getProductDetail() {
                return productDetail;
            }

            public void setProductDetail(Object productDetail) {
                this.productDetail = productDetail;
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

            public int getFcompanyOrgId() {
                return fcompanyOrgId;
            }

            public void setFcompanyOrgId(int fcompanyOrgId) {
                this.fcompanyOrgId = fcompanyOrgId;
            }
        }
        public static class OrgDetailBean implements Serializable{
            /*
            "licenseNO":"532501100006302",
        "mobile":"13958695690",
        "swiftCode":"BKCHCNBJ23A",
        "licenseImgUrl":"bbb.DOC",
        "orgId":"2065",
        "orgRegisterAddr":"北京市朝阳区霄云路188号"

             */
            public String licenseNO;
            public String mobile;
            public String swiftCode;
            public String licenseImgUrl;
            public String orgRegisterAddr;
            private String permitName;
            private String permitNo;
            private String legalPerson;

            public String getPermitName() {
                return permitName;
            }

            public void setPermitName(String permitName) {
                this.permitName = permitName;
            }

            public String getPermitNo() {
                return permitNo;
            }

            public void setPermitNo(String permitNo) {
                this.permitNo = permitNo;
            }

            public String getLegalPerson() {
                return legalPerson;
            }

            public void setLegalPerson(String legalPerson) {
                this.legalPerson = legalPerson;
            }

            public String getLicenseNO() {
                return licenseNO;
            }

            public void setLicenseNO(String licenseNO) {
                this.licenseNO = licenseNO;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getSwiftCode() {
                return swiftCode;
            }

            public void setSwiftCode(String swiftCode) {
                this.swiftCode = swiftCode;
            }

            public String getLicenseImgUrl() {
                return licenseImgUrl;
            }

            public void setLicenseImgUrl(String licenseImgUrl) {
                this.licenseImgUrl = licenseImgUrl;
            }

            public String getOrgRegisterAddr() {
                return orgRegisterAddr;
            }

            public void setOrgRegisterAddr(String orgRegisterAddr) {
                this.orgRegisterAddr = orgRegisterAddr;
            }

            @Override
            public String toString() {
                return "OrgDetailBean{" +
                        "licenseNO='" + licenseNO + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", swiftCode='" + swiftCode + '\'' +
                        ", licenseImgUrl='" + licenseImgUrl + '\'' +
                        ", orgRegisterAddr='" + orgRegisterAddr + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "OrganizationmMsgRes{" +
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
