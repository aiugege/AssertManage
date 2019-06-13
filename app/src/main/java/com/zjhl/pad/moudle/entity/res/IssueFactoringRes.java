package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.util.List;

/**
 * @desc: MarketForfaitingSellRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 **/
public class IssueFactoringRes extends ResponseBean {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 1
     * totalPage : 1
     * pageSize : 10
     * page : 1
     * data : [{"id":4,"companyOrgId":null,"factoringNo":"1001","areaId":null,"countryId":null,"seller":"1","factoringType":2,"currency":"CNY","amount":8900,"transferRate":null,"maturity":null,"factoringName":"zh2","originators":null,"obligors":null,"isCove":null,"insurer":null,"checkState":null,"checkReason":null,"revokeOrgId":null,"exchangeRate":null,"dollar":null,"remark":null,"createTime":null,"updateTime":"2018-04-26 10:26:53","yn":null,"residueDay":"0","companyName":null,"sellingState":null,"areaName":"亚洲","countryName":"中国","gradeCode":"diamondUser","factoringTypeString":null,"myAssets":"0"}]
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
         * id : 4
         * companyOrgId : null
         * factoringNo : 1001
         * areaId : null
         * countryId : null
         * seller : 1
         * factoringType : 2
         * currency : CNY
         * amount : 8900
         * transferRate : null
         * maturity : null
         * factoringName : zh2
         * originators : null
         * obligors : null
         * isCove : null
         * insurer : null
         * checkState : null
         * checkReason : null
         * revokeOrgId : null
         * exchangeRate : null
         * dollar : null
         * remark : null
         * createTime : null
         * updateTime : 2018-04-26 10:26:53
         * yn : null
         * residueDay : 0
         * companyName : null
         * sellingState : null
         * areaName : 亚洲
         * countryName : 中国
         * gradeCode : diamondUser
         * factoringTypeString : null
         * myAssets : 0
         */

        private int id;
        private String companyOrgId;
        private String factoringNo;
        private String areaId;
        private String countryId;
        private String seller;
        private int factoringType;
        private String currency;
        private int amount;
        private String transferRate;
        private String maturity;
        private String factoringName;
        private String originators;
        private String obligors;
        private String isCove;
        private String insurer;
        private String checkState;
        private String checkReason;
        private String revokeOrgId;
        private String exchangeRate;
        private String dollar;
        private String remark;
        private String createTime;
        private String updateTime;
        private String yn;
        private String residueDay;
        private String companyName;
        private String sellingState;
        private String areaName;
        private String countryName;
        private String gradeCode;
        private String factoringTypeString;
        private String myAssets;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompanyOrgId() {
            return companyOrgId;
        }

        public void setCompanyOrgId(String companyOrgId) {
            this.companyOrgId = companyOrgId;
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

        public int getFactoringType() {
            return factoringType;
        }

        public void setFactoringType(int factoringType) {
            this.factoringType = factoringType;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
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

        public String getResidueDay() {
            return residueDay;
        }

        public void setResidueDay(String residueDay) {
            this.residueDay = residueDay;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getSellingState() {
            return sellingState;
        }

        public void setSellingState(String sellingState) {
            this.sellingState = sellingState;
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

        public String getGradeCode() {
            return gradeCode;
        }

        public void setGradeCode(String gradeCode) {
            this.gradeCode = gradeCode;
        }

        public String getFactoringTypeString() {
            return factoringTypeString;
        }

        public void setFactoringTypeString(String factoringTypeString) {
            this.factoringTypeString = factoringTypeString;
        }

        public String getMyAssets() {
            return myAssets;
        }

        public void setMyAssets(String myAssets) {
            this.myAssets = myAssets;
        }
    }
}
