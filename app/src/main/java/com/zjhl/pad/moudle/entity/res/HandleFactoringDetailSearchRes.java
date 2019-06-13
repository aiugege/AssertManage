package com.zjhl.pad.moudle.entity.res;

import com.google.gson.annotations.SerializedName;
import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: MarketForfaitingSellRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 **/
public class HandleFactoringDetailSearchRes extends ResponseBean {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"id":9,"companyOrgId":2012,"ownOrgId":null,"factoringNo":"BL152413128645685305","areaId":"1231","countryId":"4561","seller":"sqc1","factoringType":1,"currency":"cny1","amount":10000,"transferRate":0,"maturity":"2018-01-01 ","indateMessage ":"2018-01-01 ","factoringName":"sqc1","originators":"sqc","obligors":"sqc","isCove":1,"insurer":"sqc","trsTimes":null,"republishFlag":null,"checkState":101,"checkReason":null,"revokeOrgId":null,"exchangeRate":null,"dollar":null,"remark":null,"createTime":null,"updateTime":null,"yn":null,"auditAdvise":"3"}
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
         * id : 9
         * companyOrgId : 2012
         * ownOrgId : null
         * factoringNo : BL152413128645685305
         * areaId : 1231
         * countryId : 4561
         * seller : sqc1
         * factoringType : 1
         * currency : cny1
         * amount : 10000
         * transferRate : 0
         * maturity : 2018-01-01
         * indateMessage  : 2018-01-01
         * factoringName : sqc1
         * originators : sqc
         * obligors : sqc
         * isCove : 1
         * insurer : sqc
         * trsTimes : null
         * republishFlag : null
         * checkState : 101
         * checkReason : null
         * revokeOrgId : null
         * exchangeRate : null
         * dollar : null
         * remark : null
         * createTime : null
         * updateTime : null
         * yn : null
         * auditAdvise : 3
         */

        private int id;
        private int companyOrgId;
        private String ownOrgId;
        private String factoringNo;
        private String areaId;
        private String countryId;
        private String seller;
        private String factoringType;
        private String currency;
        private String amount;
        private String transferRate;
        private String maturity;
        private String indateMessage;
        private String factoringName;
        private String originators;
        private String obligors;
        private String isCove;
        private String insurer;
        private String trsTimes;
        private String republishFlag;
        private String checkState;
        private String checkReason;
        private String revokeOrgId;
        private String exchangeRate;
        private String dollar;
        private String remark;
        private String createTime;
        private String updateTime;
        private String yn;
        private String auditAdvise;



        @SerializedName("ST0204")
        private String ST0204;
        @SerializedName("ST0205")
        private String ST0205;
        @SerializedName("ST0206")
        private String ST0206;
        @SerializedName("ST0207")
        private String ST0207;
        @SerializedName("ST0208")
        private String ST0208;
        @SerializedName("ST0209")
        private String ST0209;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyOrgId() {
            return companyOrgId;
        }

        public void setCompanyOrgId(int companyOrgId) {
            this.companyOrgId = companyOrgId;
        }

        public String getOwnOrgId() {
            return ownOrgId;
        }

        public void setOwnOrgId(String ownOrgId) {
            this.ownOrgId = ownOrgId;
        }

        public String getFactoringNo() {
            return factoringNo;
        }

        public void setFactoringNo(String factoringNo) {
            this.factoringNo = factoringNo;
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

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getFactoringType() {
            return factoringType;
        }

        public void setFactoringType(String factoringType) {
            this.factoringType = factoringType;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTransferRate() {
            return transferRate;
        }

        public void setTransferRate(String transferRate) {
            this.transferRate = transferRate;
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

        public String getFactoringName() {
            return factoringName;
        }

        public void setFactoringName(String factoringName) {
            this.factoringName = factoringName;
        }

        public String getOriginators() {
            return originators;
        }

        public void setOriginators(String originators) {
            this.originators = originators;
        }

        public String getObligors() {
            return obligors;
        }

        public void setObligors(String obligors) {
            this.obligors = obligors;
        }

        public String getIsCove() {
            return isCove;
        }

        public void setIsCove(String isCove) {
            this.isCove = isCove;
        }

        public String getInsurer() {
            return insurer;
        }

        public void setInsurer(String insurer) {
            this.insurer = insurer;
        }

        public String getTrsTimes() {
            return trsTimes;
        }

        public void setTrsTimes(String trsTimes) {
            this.trsTimes = trsTimes;
        }

        public String getRepublishFlag() {
            return republishFlag;
        }

        public void setRepublishFlag(String republishFlag) {
            this.republishFlag = republishFlag;
        }

        public String getCheckState() {
            return checkState;
        }

        public void setCheckState(String checkState) {
            this.checkState = checkState;
        }

        public String getCheckReason() {
            return checkReason;
        }

        public void setCheckReason(String checkReason) {
            this.checkReason = checkReason;
        }

        public String getRevokeOrgId() {
            return revokeOrgId;
        }

        public void setRevokeOrgId(String revokeOrgId) {
            this.revokeOrgId = revokeOrgId;
        }

        public String getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getDollar() {
            return dollar;
        }

        public void setDollar(String dollar) {
            this.dollar = dollar;
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

        public String getYn() {
            return yn;
        }

        public void setYn(String yn) {
            this.yn = yn;
        }

        public String getAuditAdvise() {
            return auditAdvise;
        }

        public void setAuditAdvise(String auditAdvise) {
            this.auditAdvise = auditAdvise;
        }

        public String getST0204() {
            return ST0204;
        }

        public void setST0204(String ST0204) {
            this.ST0204 = ST0204;
        }

        public String getST0205() {
            return ST0205;
        }

        public void setST0205(String ST0205) {
            this.ST0205 = ST0205;
        }

        public String getST0206() {
            return ST0206;
        }

        public void setST0206(String ST0206) {
            this.ST0206 = ST0206;
        }

        public String getST0207() {
            return ST0207;
        }

        public void setST0207(String ST0207) {
            this.ST0207 = ST0207;
        }

        public String getST0208() {
            return ST0208;
        }

        public void setST0208(String ST0208) {
            this.ST0208 = ST0208;
        }

        public String getST0209() {
            return ST0209;
        }

        public void setST0209(String ST0209) {
            this.ST0209 = ST0209;
        }
    }
}
