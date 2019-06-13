package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;

/**
 * @desc: MarketForfaitingDetailRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/15 20:39
 * @projectname: nnkj
 **/
public class MarketForfaitingDetailOldRes extends ResponseBean {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"tradeSum":1,"successSum":1,"failSum":0,"successRate":"100.00%","successAmount":18000,"companyOrg":{"id":2065,"companyTypeId1":"bankUser","companyTypeId2":"2","areaId":"1","countryId":"1","provinceId":null,"companyName":"中国邮政储蓄银行金台路支行","companyDomicile":"北京市丰台区丰台科技园18号楼","contactName":"王芳","content":null,"checkState":111,"lockState":1,"gradeCode":"commonUser","swiftCode":"BKC****J88A","licenseNo":" 914****1MA4L16JQ9B","remark":null,"inviteCode":null,"createTime":"2018-05-07 09:54:31","updateTime":"2018-05-15 17:57:02","yn":1,"orgDetail":"{\"licenseNO\":\" 91430111MA4L16JQ9B\",\"mobile\":\"18926598560\",\"swiftCode\":\"BKCHCNBJ88A\",\"licenseImgUrl\":\"CCC.DOC\",\"orgId\":\"2065\",\"orgRegisterAddr\":\"北京市丰台区丰台科技园18号楼\"}","contactTel":"176****3560"},"assets":{"id":265,"assetsNo":"FFT15259406023252764","companyName":null,"factoringType":null,"factoringName":null,"title":"天下霸唱盗墓古董A001号坑","amount":"99999999999.99","currency":"CNY","discountRate":5,"debtType":1,"lcNo":"1820QL243451","creditType":"SKHDS","openSwift":"BEfUJ12458B","openFullName":"中国银行北京小营支行","tenderSwift":"ICBKCNBJ566","tenderFullName":"中国工商银行崇文门支行","reimbursingBankSwift":"PCBCCNBJ529B","reimbursingBankName":"中国工商银行股份有限公司","issuingDate":"2018-06-10 00:00:00","acceptanceDate":"2018-07-10 00:00:00","maturity":"2018-08-10 00:00:00","indateMessage":"2018-05-20 00:00:00","countryName":"中国","areaName":"亚洲","recheckState":104,"republishFlag":0,"recheckReason":null,"companyOrgId":2065,"ownOrgId":null,"pollicitaUrl":null,"myAssets":null}}
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

    public static class DataBean implements Serializable{
        /**
         * tradeSum : 1
         * successSum : 1
         * failSum : 0
         * successRate : 100.00%
         * successAmount : 18000
         * companyOrg : {"id":2065,"companyTypeId1":"bankUser","companyTypeId2":"2","areaId":"1","countryId":"1","provinceId":null,"companyName":"中国邮政储蓄银行金台路支行","companyDomicile":"北京市丰台区丰台科技园18号楼","contactName":"王芳","content":null,"checkState":111,"lockState":1,"gradeCode":"commonUser","swiftCode":"BKC****J88A","licenseNo":" 914****1MA4L16JQ9B","remark":null,"inviteCode":null,"createTime":"2018-05-07 09:54:31","updateTime":"2018-05-15 17:57:02","yn":1,"orgDetail":"{\"licenseNO\":\" 91430111MA4L16JQ9B\",\"mobile\":\"18926598560\",\"swiftCode\":\"BKCHCNBJ88A\",\"licenseImgUrl\":\"CCC.DOC\",\"orgId\":\"2065\",\"orgRegisterAddr\":\"北京市丰台区丰台科技园18号楼\"}","contactTel":"176****3560"}
         * assets : {"id":265,"assetsNo":"FFT15259406023252764","companyName":null,"factoringType":null,"factoringName":null,"title":"天下霸唱盗墓古董A001号坑","amount":"99999999999.99","currency":"CNY","discountRate":5,"debtType":1,"lcNo":"1820QL243451","creditType":"SKHDS","openSwift":"BEfUJ12458B","openFullName":"中国银行北京小营支行","tenderSwift":"ICBKCNBJ566","tenderFullName":"中国工商银行崇文门支行","reimbursingBankSwift":"PCBCCNBJ529B","reimbursingBankName":"中国工商银行股份有限公司","issuingDate":"2018-06-10 00:00:00","acceptanceDate":"2018-07-10 00:00:00","maturity":"2018-08-10 00:00:00","indateMessage":"2018-05-20 00:00:00","countryName":"中国","areaName":"亚洲","recheckState":104,"republishFlag":0,"recheckReason":null,"companyOrgId":2065,"ownOrgId":null,"pollicitaUrl":null,"myAssets":null}
         */

        private String tradeSum;
        private String successSum;
        private String failSum;
        private String successRate;
        private String successAmount;
        private CompanyOrgBean companyOrg;
        private AssetsBean assets;

        public String getTradeSum() {
            return tradeSum;
        }

        public void setTradeSum(String tradeSum) {
            this.tradeSum = tradeSum;
        }

        public String getSuccessSum() {
            return successSum;
        }

        public void setSuccessSum(String successSum) {
            this.successSum = successSum;
        }

        public String getFailSum() {
            return failSum;
        }

        public void setFailSum(String failSum) {
            this.failSum = failSum;
        }

        public String getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(String successRate) {
            this.successRate = successRate;
        }

        public String getSuccessAmount() {
            return successAmount;
        }

        public void setSuccessAmount(String successAmount) {
            this.successAmount = successAmount;
        }

        public CompanyOrgBean getCompanyOrg() {
            return companyOrg;
        }

        public void setCompanyOrg(CompanyOrgBean companyOrg) {
            this.companyOrg = companyOrg;
        }

        public AssetsBean getAssets() {
            return assets;
        }

        public void setAssets(AssetsBean assets) {
            this.assets = assets;
        }

        public static class CompanyOrgBean  implements Serializable{
            /**
             * id : 2065
             * companyTypeId1 : bankUser
             * companyTypeId2 : 2
             * areaId : 1
             * countryId : 1
             * provinceId : null
             * companyName : 中国邮政储蓄银行金台路支行
             * companyDomicile : 北京市丰台区丰台科技园18号楼
             * contactName : 王芳
             * content : null
             * checkState : 111
             * lockState : 1
             * gradeCode : commonUser
             * swiftCode : BKC****J88A
             * licenseNo :  914****1MA4L16JQ9B
             * remark : null
             * inviteCode : null
             * createTime : 2018-05-07 09:54:31
             * updateTime : 2018-05-15 17:57:02
             * yn : 1
             * orgDetail : {"licenseNO":" 91430111MA4L16JQ9B","mobile":"18926598560","swiftCode":"BKCHCNBJ88A","licenseImgUrl":"CCC.DOC","orgId":"2065","orgRegisterAddr":"北京市丰台区丰台科技园18号楼"}
             * contactTel : 176****3560
             */

            private int id;
            private String companyTypeId1;
            private String companyTypeId2;
            private String areaId;
            private String countryId;
            private Object provinceId;
            private String companyName;
            private String companyDomicile;
            private String contactName;
            private Object content;
            private int checkState;
            private int lockState;
            private String gradeCode;
            private String swiftCode;
            private String licenseNo;
            private Object remark;
            private Object inviteCode;
            private String createTime;
            private String updateTime;
            private int yn;
            private String orgDetail;
            private String contactTel;

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

            public Object getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(Object provinceId) {
                this.provinceId = provinceId;
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

            public String getContactTel() {
                return contactTel;
            }

            public void setContactTel(String contactTel) {
                this.contactTel = contactTel;
            }
        }

        public static class AssetsBean  implements Serializable{
            /**
             * id : 265
             * assetsNo : FFT15259406023252764
             * companyName : null
             * factoringType : null
             * factoringName : null
             * title : 天下霸唱盗墓古董A001号坑
             * amount : 99999999999.99
             * currency : CNY
             * discountRate : 5
             * debtType : 1
             * lcNo : 1820QL243451
             * creditType : SKHDS
             * openSwift : BEfUJ12458B
             * openFullName : 中国银行北京小营支行
             * tenderSwift : ICBKCNBJ566
             * tenderFullName : 中国工商银行崇文门支行
             * reimbursingBankSwift : PCBCCNBJ529B
             * reimbursingBankName : 中国工商银行股份有限公司
             * issuingDate : 2018-06-10 00:00:00
             * acceptanceDate : 2018-07-10 00:00:00
             * maturity : 2018-08-10 00:00:00
             * indateMessage : 2018-05-20 00:00:00
             * countryName : 中国
             * areaName : 亚洲
             * recheckState : 104
             * republishFlag : 0
             * recheckReason : null
             * companyOrgId : 2065
             * ownOrgId : null
             * pollicitaUrl : null
             * myAssets : null
             */

            private int id;
            private String assetsNo;
            private String companyName;
            private String factoringType;
            private String factoringName;
            private String title;
            private String amount;
            private String currency;
            private String discountRate;
            private String debtType;
            private String lcNo;
            private String creditType;
            private String openSwift;
            private String openFullName;
            private String tenderSwift;
            private String tenderFullName;
            private String reimbursingBankSwift;
            private String reimbursingBankName;
            private String issuingDate;
            private String acceptanceDate;
            private String maturity;
            private String indateMessage;
            private String countryName;
            private String areaName;
            private String recheckState;
            private String republishFlag;
            private String recheckReason;
            private String companyOrgId;
            private String ownOrgId;
            private String pollicitaUrl;
            private String myAssets;
            private String assetAgreement;
            private String enAreaName;
            private String enCountryName;

            public String getEnAreaName() {
                return enAreaName;
            }

            public void setEnAreaName(String enAreaName) {
                this.enAreaName = enAreaName;
            }

            public String getEnCountryName() {
                return enCountryName;
            }

            public void setEnCountryName(String enCountryName) {
                this.enCountryName = enCountryName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAssetsNo() {
                return assetsNo;
            }

            public void setAssetsNo(String assetsNo) {
                this.assetsNo = assetsNo;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getFactoringType() {
                return factoringType;
            }

            public void setFactoringType(String factoringType) {
                this.factoringType = factoringType;
            }

            public String getFactoringName() {
                return factoringName;
            }

            public void setFactoringName(String factoringName) {
                this.factoringName = factoringName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public String getDiscountRate() {
                return discountRate;
            }

            public void setDiscountRate(String discountRate) {
                this.discountRate = discountRate;
            }

            public String getDebtType() {
                return debtType;
            }

            public void setDebtType(String debtType) {
                this.debtType = debtType;
            }

            public String getLcNo() {
                return lcNo;
            }

            public void setLcNo(String lcNo) {
                this.lcNo = lcNo;
            }

            public String getCreditType() {
                return creditType;
            }

            public void setCreditType(String creditType) {
                this.creditType = creditType;
            }

            public String getOpenSwift() {
                return openSwift;
            }

            public void setOpenSwift(String openSwift) {
                this.openSwift = openSwift;
            }

            public String getOpenFullName() {
                return openFullName;
            }

            public void setOpenFullName(String openFullName) {
                this.openFullName = openFullName;
            }

            public String getTenderSwift() {
                return tenderSwift;
            }

            public void setTenderSwift(String tenderSwift) {
                this.tenderSwift = tenderSwift;
            }

            public String getTenderFullName() {
                return tenderFullName;
            }

            public void setTenderFullName(String tenderFullName) {
                this.tenderFullName = tenderFullName;
            }

            public String getReimbursingBankSwift() {
                return reimbursingBankSwift;
            }

            public void setReimbursingBankSwift(String reimbursingBankSwift) {
                this.reimbursingBankSwift = reimbursingBankSwift;
            }

            public String getReimbursingBankName() {
                return reimbursingBankName;
            }

            public void setReimbursingBankName(String reimbursingBankName) {
                this.reimbursingBankName = reimbursingBankName;
            }

            public String getIssuingDate() {
                return issuingDate;
            }

            public void setIssuingDate(String issuingDate) {
                this.issuingDate = issuingDate;
            }

            public String getAcceptanceDate() {
                return acceptanceDate;
            }

            public void setAcceptanceDate(String acceptanceDate) {
                this.acceptanceDate = acceptanceDate;
            }

            public String getMaturity() {
                return maturity;
            }

            public void setMaturity(String maturity) {
                this.maturity = maturity;
            }

            public String getIndateMessage() {
                return indateMessage;
            }

            public void setIndateMessage(String indateMessage) {
                this.indateMessage = indateMessage;
            }

            public String getCountryName() {
                return countryName;
            }

            public void setCountryName(String countryName) {
                this.countryName = countryName;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getRecheckState() {
                return recheckState;
            }

            public void setRecheckState(String recheckState) {
                this.recheckState = recheckState;
            }

            public String getRepublishFlag() {
                return republishFlag;
            }

            public void setRepublishFlag(String republishFlag) {
                this.republishFlag = republishFlag;
            }

            public String getRecheckReason() {
                return recheckReason;
            }

            public void setRecheckReason(String recheckReason) {
                this.recheckReason = recheckReason;
            }

            public String getCompanyOrgId() {
                return companyOrgId;
            }

            public void setCompanyOrgId(String companyOrgId) {
                this.companyOrgId = companyOrgId;
            }

            public String getOwnOrgId() {
                return ownOrgId;
            }

            public void setOwnOrgId(String ownOrgId) {
                this.ownOrgId = ownOrgId;
            }

            public String getPollicitaUrl() {
                return pollicitaUrl;
            }

            public void setPollicitaUrl(String pollicitaUrl) {
                this.pollicitaUrl = pollicitaUrl;
            }

            public String getMyAssets() {
                return myAssets;
            }

            public void setMyAssets(String myAssets) {
                this.myAssets = myAssets;
            }

            public String getAssetAgreement() {
                return assetAgreement;
            }

            public void setAssetAgreement(String assetAgreement) {
                this.assetAgreement = assetAgreement;
            }


        }
    }
}
