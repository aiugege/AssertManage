package com.zjhl.pad.moudle.entity.req;

/* 
* File: BanckInstitutionalReq.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/11 15:23 
* Changes (from 2018/5/11) 
*/
public class BanckInstitutionalReq {


    /**
     * orgType : bankUser
     * jsonOrg : {"licenseNO":"532501100006302","swiftCode":"BKCHCNBJ23A","orgRegisterAddr":"北京市朝阳区霄云路188号","licenseImgUrl":"bbb.DOC","orgId":"2065","mobile":"13958695690"}
     * jsonProduct : {"forfeiting":{"assetsType":1,"id":"1"},"factoring":{"assetsType":2,"id":""}}
     */

    private String orgType;
    private JsonOrgBean jsonOrg;
    private JsonProductBean jsonProduct;

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public JsonOrgBean getJsonOrg() {
        return jsonOrg;
    }

    public void setJsonOrg(JsonOrgBean jsonOrg) {
        this.jsonOrg = jsonOrg;
    }

    public JsonProductBean getJsonProduct() {
        return jsonProduct;
    }

    public void setJsonProduct(JsonProductBean jsonProduct) {
        this.jsonProduct = jsonProduct;
    }

    public static class JsonOrgBean {
        /**
         "jsonOrg": {
         "licenseNO": "营业执照号",
         "orgCode": "组织机构代码",
         "officeAddr": "办公地址",
         "swiftCode": "BKCHCNBJ23A",
         "orgRegisterAddr":"注册地址",
         "licenseImgUrl":  "营业执照电子版.DOC",
         "orgCodeImg": "组织机构代码电子版.DOC",
         "yearFinancialTable": "近十二个月财务报表.DOC",
         "orgId": "2055",
         "mobile": "13958695690"
         }

         */

        private String licenseNO;
        private String orgCode;
        private String officeAddr;
        private String swiftCode;
        private String orgRegisterAddr;
        private String licenseImgUrl;
        private String orgCodeImg;
        private String yearFinancialTable;
        private String orgId;
        private String mobile;
        private String uscc;
        private String companyName;
        private String areaId;
        private String countryId;

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

        public String getUscc() {
            return uscc;
        }

        public void setUscc(String uscc) {
            this.uscc = uscc;
        }

        public String getLicenseNO() {
            return licenseNO;
        }

        public void setLicenseNO(String licenseNO) {
            this.licenseNO = licenseNO;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public void setSwiftCode(String swiftCode) {
            this.swiftCode = swiftCode;
        }

        public String getOrgRegisterAddr() {
            return orgRegisterAddr;
        }

        public void setOrgRegisterAddr(String orgRegisterAddr) {
            this.orgRegisterAddr = orgRegisterAddr;
        }

        public String getLicenseImgUrl() {
            return licenseImgUrl;
        }

        public void setLicenseImgUrl(String licenseImgUrl) {
            this.licenseImgUrl = licenseImgUrl;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getOfficeAddr() {
            return officeAddr;
        }

        public void setOfficeAddr(String officeAddr) {
            this.officeAddr = officeAddr;
        }

        public String getOrgCodeImg() {
            return orgCodeImg;
        }

        public void setOrgCodeImg(String orgCodeImg) {
            this.orgCodeImg = orgCodeImg;
        }

        public String getYearFinancialTable() {
            return yearFinancialTable;
        }

        public void setYearFinancialTable(String yearFinancialTable) {
            this.yearFinancialTable = yearFinancialTable;
        }
    }

    public static class JsonProductBean {
        /**
         * forfeiting : {"assetsType":1,"id":"1"}
         * factoring : {"assetsType":2,"id":""}
         */

        private ForfeitingBean forfeiting;
        private FactoringBean factoring;

        public ForfeitingBean getForfeiting() {
            return forfeiting;
        }

        public void setForfeiting(ForfeitingBean forfeiting) {
            this.forfeiting = forfeiting;
        }

        public FactoringBean getFactoring() {
            return factoring;
        }

        public void setFactoring(FactoringBean factoring) {
            this.factoring = factoring;
        }

        public static class ForfeitingBean {
            /**
             * assetsType : 1
             * id : 1
             */

            private String assetsType;
            private String id;

            public String getAssetsType() {
                return assetsType;
            }

            public void setAssetsType(String assetsType) {
                this.assetsType = assetsType;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class FactoringBean {
            /**
             * assetsType : 2
             * id :
             */

            private String assetsType;
            private String id;

            public String getAssetsType() {
                return assetsType;
            }

            public void setAssetsType(String assetsType) {
                this.assetsType = assetsType;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
