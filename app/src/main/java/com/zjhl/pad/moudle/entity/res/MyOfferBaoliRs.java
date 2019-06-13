package com.zjhl.pad.moudle.entity.res;

import java.util.List;

/*
* File: MyOfferBaoliRs.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/21 11:52 
* Changes (from 2018/5/21) 
*/
public class MyOfferBaoliRs {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : [{"id":2,"seller":"北京宏图远大贸易","factoringType":1,"amount":12000,"factoringNo":"BL152446405675023495","maturity":"2018-04-11 ","currency":"USD","factoringName":"sqc1","checkState":102,"transferRate":1.21,"discountRate":0.41,"discountRateOld":0.42,"updateTranLetter":1,"rejectOpinion":"拒绝","indateMessage ":"2018-04-11","countryName":"中国","ownOrgId":0,"priceState":"105","priceId":38}]
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
         * id : 2
         * seller : 北京宏图远大贸易
         * factoringType : 1
         * amount : 12000
         * factoringNo : BL152446405675023495
         * maturity : 2018-04-11
         * currency : USD
         * factoringName : sqc1
         * checkState : 102
         * transferRate : 1.21
         * discountRate : 0.41
         * discountRateOld : 0.42
         * updateTranLetter : 1
         * rejectOpinion : 拒绝
         * indateMessage  : 2018-04-11
         * countryName : 中国
         * ownOrgId : 0
         * priceState : 105
         * priceId : 38
         */

        private int id;
        private String seller;
        private String factoringType;
        private String amount;
        private String factoringNo;
        private String maturity;
        private String currency;
        private String factoringName;
        private String checkState;
        private String transferRate;
        private String discountRate;
        private String discountRateOld;
        private String oldDiscountRate;
        private String updateTranLetter;
        private String rejectOpinion;
        private String indateMessage;
        private String countryName;
        private String ownOrgId;
        private String priceState;
        private String priceId;
        private String yn;
        private String countryNameEn;
        private String areaNameEn;
        private String enAreaName;
        private String enCountryName;
        private String priceReason;

        public String getPriceReason() {
            return priceReason;
        }

        public void setPriceReason(String priceReason) {
            this.priceReason = priceReason;
        }

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

        public String getCountryNameEn() {
            return countryNameEn;
        }

        public void setCountryNameEn(String countryNameEn) {
            this.countryNameEn = countryNameEn;
        }

        public String getAreaNameEn() {
            return areaNameEn;
        }

        public void setAreaNameEn(String areaNameEn) {
            this.areaNameEn = areaNameEn;
        }
        public String getYn() {
            return yn;
        }

        public void setYn(String yn) {
            this.yn = yn;
        }

        public String getOldDiscountRate() {
            return oldDiscountRate;
        }

        public void setOldDiscountRate(String oldDiscountRate) {
            this.oldDiscountRate = oldDiscountRate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFactoringNo() {
            return factoringNo;
        }

        public void setFactoringNo(String factoringNo) {
            this.factoringNo = factoringNo;
        }

        public String getMaturity() {
            return maturity;
        }

        public void setMaturity(String maturity) {
            this.maturity = maturity;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getFactoringName() {
            return factoringName;
        }

        public void setFactoringName(String factoringName) {
            this.factoringName = factoringName;
        }

        public String getCheckState() {
            return checkState;
        }

        public void setCheckState(String checkState) {
            this.checkState = checkState;
        }

        public String getTransferRate() {
            return transferRate;
        }

        public void setTransferRate(String transferRate) {
            this.transferRate = transferRate;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getDiscountRateOld() {
            return discountRateOld;
        }

        public void setDiscountRateOld(String discountRateOld) {
            this.discountRateOld = discountRateOld;
        }

        public String getUpdateTranLetter() {
            return updateTranLetter;
        }

        public void setUpdateTranLetter(String updateTranLetter) {
            this.updateTranLetter = updateTranLetter;
        }

        public String getRejectOpinion() {
            return rejectOpinion;
        }

        public void setRejectOpinion(String rejectOpinion) {
            this.rejectOpinion = rejectOpinion;
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

        public String getOwnOrgId() {
            return ownOrgId;
        }

        public void setOwnOrgId(String ownOrgId) {
            this.ownOrgId = ownOrgId;
        }

        public String getPriceState() {
            return priceState;
        }

        public void setPriceState(String priceState) {
            this.priceState = priceState;
        }

        public String getPriceId() {
            return priceId;
        }

        public void setPriceId(String priceId) {
            this.priceId = priceId;
        }
    }

    @Override
    public String toString() {
        return "MyOfferBaoliRs{" +
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
