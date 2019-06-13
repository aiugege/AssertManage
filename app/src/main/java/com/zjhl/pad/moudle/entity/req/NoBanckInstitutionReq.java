package com.zjhl.pad.moudle.entity.req;

/*
 * File: NoBanckInstitutionReq.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/13 14:48
 * Changes (from 2018/5/13)
 */
public class NoBanckInstitutionReq {


    /**
     * orgType : financialInstitution
     * jsonOrg : {"permitName":"金融机构许可证","permitNo":"金融机构许可证编号","legalPerson":"法定代表人","licenseNO":"营业执照号","officeAddr":"办公地址","licenseImgUrl":{"paramUrl":"bbb.DOC"},"dataUploadUrl":{"paramUrl":"xiangguanziliao.DOC"},"orgId":"2064","mobile":"13958695690"}
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


    public static class JsonOrgBean {
        /**
         * permitName : 金融机构许可证
         * permitNo : 金融机构许可证编号
         * legalPerson : 法定代表人
         * licenseNO : 营业执照号
         * officeAddr : 办公地址
         * licenseImgUrl : {"paramUrl":"bbb.DOC"}
         * dataUploadUrl : {"paramUrl":"xiangguanziliao.DOC"}
         * orgId : 2064
         * mobile : 13958695690
         */
        /*
        "permitName": "金融机构许可证",
			"permitNo": "金融机构许可证编号",
			"legalPerson": "法定代表人",
			"licenseNO": "营业执照号",
			"officeAddr": "办公地址",
            "swiftCode": "BKCHCNBJ23A",
              "orgRegisterAddr":"注册地址",
			"licenseImgUrl": "bbb.DOC",
			"dataUploadUrl": "xiangguanziliao.DOC",
			"orgId": "2064",
			"mobile": "13958695690"

         */

        private String permitName;
        private String permitNo;
        private String legalPerson;
        private String licenseNO;
        private String officeAddr;
//        private LicenseImgUrlBean licenseImgUrl;
//        private DataUploadUrlBean dataUploadUrl;
        private String licenseImgUrl;
        private String dataUploadUrl;
        private String orgId;
        private String mobile;
        private String orgRegisterAddr;
        private String companyName;
        private String swiftCode;
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

        public String getLicenseImgUrl() {
            return licenseImgUrl;
        }

        public void setLicenseImgUrl(String licenseImgUrl) {
            this.licenseImgUrl = licenseImgUrl;
        }

        public String getDataUploadUrl() {
            return dataUploadUrl;
        }

        public void setDataUploadUrl(String dataUploadUrl) {
            this.dataUploadUrl = dataUploadUrl;
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
//
//        public DataUploadUrlBean getDataUploadUrl() {
//            if (dataUploadUrl == null) {
//                dataUploadUrl = new DataUploadUrlBean();
//            }
//            return dataUploadUrl;
//        }
//
//        public void setDataUploadUrl(DataUploadUrlBean dataUploadUrl) {
//            this.dataUploadUrl = dataUploadUrl;
//        }

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


        public static class LicenseImgUrlBean {
            /**
             * paramUrl : bbb.DOC
             */

            private String paramUrl;

            public String getParamUrl() {
                return paramUrl;
            }

            public void setParamUrl(String paramUrl) {
                this.paramUrl = paramUrl;
            }
        }

        public static class DataUploadUrlBean {
            /**
             * paramUrl : xiangguanziliao.DOC
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
                    "permitName='" + permitName + '\'' +
                    ", permitNo='" + permitNo + '\'' +
                    ", legalPerson='" + legalPerson + '\'' +
                    ", licenseNO='" + licenseNO + '\'' +
                    ", officeAddr='" + officeAddr + '\'' +
                    ", licenseImgUrl=" + licenseImgUrl +
                    ", dataUploadUrl=" + dataUploadUrl +
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

        @Override
        public String toString() {
            return "JsonProductBean{" +
                    "forfeiting=" + forfeiting +
                    ", factoring=" + factoring +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NoBanckInstitutionReq{" +
                "orgType='" + orgType + '\'' +
                ", jsonOrg=" + jsonOrg +
                ", jsonProduct=" + jsonProduct +
                '}';
    }
}
