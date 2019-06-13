package com.zjhl.pad.moudle.entity.req;

/*
 * File: EnterpriseReq.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/13 17:36
 * Changes (from 2018/5/13)
 */
public class EnterpriseReq {

    /**
     * orgType : company
     * jsonOrg : {"licenseNO":"营业执照号","orgCode":"组织机构代码","registerPlace":"注册地","officeAddr":"办公地址","licenseImgUrl":{"paramUrl":"营业执照电子版.DOC"},"orgCodeImg":{"paramUrl":"组织机构代码电子版.DOC"},"yearFinancialTable":{"paramUrl":"近十二个月财务报表.DOC"},"orgId":"2055","mobile":"13958695690"}
     * jsonProduct : {"forfeiting":{"assetsType":1,"id":""},"factoring":{"assetsType":2,"id":""}}
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
        if (jsonOrg == null) {
            jsonOrg = new JsonOrgBean();
        }
        return jsonOrg;
    }

    public void setJsonOrg(JsonOrgBean jsonOrg) {
        this.jsonOrg = jsonOrg;
    }

    public JsonProductBean getJsonProduct() {
        if (jsonProduct == null) {
            jsonProduct = new JsonProductBean();
        }
        return jsonProduct;
    }

    public void setJsonProduct(JsonProductBean jsonProduct) {
        this.jsonProduct = jsonProduct;
    }

    @Override
    public String toString() {
        return "EnterpriseReq{" +
                "orgType='" + orgType + '\'' +
                ", jsonOrg=" + jsonOrg +
                ", jsonProduct=" + jsonProduct +
                '}';
    }

    public static class JsonOrgBean {
        /**
         * licenseNO : 营业执照号
         * orgCode : 组织机构代码
         * registerPlace : 注册地
         * officeAddr : 办公地址
         * licenseImgUrl : {"paramUrl":"营业执照电子版.DOC"}
         * orgCodeImg : {"paramUrl":"组织机构代码电子版.DOC"}
         * yearFinancialTable : {"paramUrl":"近十二个月财务报表.DOC"}
         * orgId : 2055
         * mobile : 13958695690
         */
        /*
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

         */

        private String licenseNO;
        private String orgCode;
//        private String registerPlace;
        private String officeAddr;
        private String licenseImgUrl;
        private String orgCodeImg;
        private String yearFinancialTable;
        private String swiftCode;
//        private LicenseImgUrlBean licenseImgUrl;
//        private OrgCodeImgBean orgCodeImg;
//        private YearFinancialTableBean yearFinancialTable;
        private int orgId;
        private String mobile;
        private String orgRegisterAddr;
        private String companyName;
        private String uscc;
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

        public void setLicenseImgUrl(String licenseImgUrl) {
            this.licenseImgUrl = licenseImgUrl;
        }

        public void setOrgCodeImg(String orgCodeImg) {
            this.orgCodeImg = orgCodeImg;
        }

        public void setYearFinancialTable(String yearFinancialTable) {
            this.yearFinancialTable = yearFinancialTable;
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

        public String getLicenseNO() {
            return licenseNO;
        }

        public void setLicenseNO(String licenseNO) {
            this.licenseNO = licenseNO;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

//        public String getRegisterPlace() {
//            return registerPlace;
//        }
//
//        public void setRegisterPlace(String registerPlace) {
//            this.registerPlace = registerPlace;
//        }

        public String getOfficeAddr() {
            return officeAddr;
        }

        public void setOfficeAddr(String officeAddr) {
            this.officeAddr = officeAddr;
        }

//        public LicenseImgUrlBean getLicenseImgUrl() {
//            if (licenseImgUrl == null) {
//                licenseImgUrl = new LicenseImgUrlBean();
//            }
//            return licenseImgUrl;
//        }
//
//        public void setLicenseImgUrl(LicenseImgUrlBean licenseImgUrl) {
//            this.licenseImgUrl = licenseImgUrl;
//        }

//        public OrgCodeImgBean getOrgCodeImg() {
//            if (orgCodeImg == null) {
//                orgCodeImg = new OrgCodeImgBean();
//            }
//            return orgCodeImg;
//        }
//
//        public void setOrgCodeImg(OrgCodeImgBean orgCodeImg) {
//            this.orgCodeImg = orgCodeImg;
//        }
//
//        public YearFinancialTableBean getYearFinancialTable() {
//            if (yearFinancialTable == null) {
//                yearFinancialTable = new YearFinancialTableBean();
//            }
//            return yearFinancialTable;
//        }
//
//        public void setYearFinancialTable(YearFinancialTableBean yearFinancialTable) {
//            this.yearFinancialTable = yearFinancialTable;
//        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public static class LicenseImgUrlBean {
            /**
             * paramUrl : 营业执照电子版.DOC
             */

            private String paramUrl;

            public String getParamUrl() {
                return paramUrl;
            }

            public void setParamUrl(String paramUrl) {
                this.paramUrl = paramUrl;
            }
        }

        public static class OrgCodeImgBean {
            /**
             * paramUrl : 组织机构代码电子版.DOC
             */

            private String paramUrl;

            public String getParamUrl() {
                return paramUrl;
            }

            public void setParamUrl(String paramUrl) {
                this.paramUrl = paramUrl;
            }
        }

        public static class YearFinancialTableBean {
            /**
             * paramUrl : 近十二个月财务报表.DOC
             */

            private String paramUrl;

            public String getParamUrl() {
                return paramUrl;
            }

            public void setParamUrl(String paramUrl) {
                this.paramUrl = paramUrl;
            }
        }

        @Override
        public String toString() {
            return "JsonOrgBean{" +
                    "licenseNO='" + licenseNO + '\'' +
                    ", orgCode='" + orgCode + '\'' +
//                    ", registerPlace='" + registerPlace + '\'' +
                    ", officeAddr='" + officeAddr + '\'' +
                    ", licenseImgUrl=" + licenseImgUrl +
                    ", orgCodeImg=" + orgCodeImg +
                    ", yearFinancialTable=" + yearFinancialTable +
                    ", orgId='" + orgId + '\'' +
                    ", mobile='" + mobile + '\'' +
                    '}';
        }
    }

    public static class JsonProductBean {
        /**
         * forfeiting : {"assetsType":1,"id":""}
         * factoring : {"assetsType":2,"id":""}
         */

        private ForfeitingBean forfeiting;
        private FactoringBean factoring;

        public ForfeitingBean getForfeiting() {
            if (forfeiting == null) {
                forfeiting = new ForfeitingBean();
            }
            return forfeiting;
        }

        public void setForfeiting(ForfeitingBean forfeiting) {
            this.forfeiting = forfeiting;
        }

        public FactoringBean getFactoring() {
            if (factoring == null) {
                factoring = new FactoringBean();
            }
            return factoring;
        }

        public void setFactoring(FactoringBean factoring) {
            this.factoring = factoring;
        }

        public static class ForfeitingBean {
            /**
             * assetsType : 1
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
