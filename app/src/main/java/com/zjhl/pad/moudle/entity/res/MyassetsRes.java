package com.zjhl.pad.moudle.entity.res;

import java.util.List;

/*
* File: MyassetsRes.java
* Author: DELL 
* Version: V1.0
* Create: 2018/5/17 10:38 
* Changes (from 2018/5/17) 
*/
public class MyassetsRes {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : [{"id":2,"discountRate":0.41,"discountRateOld":0.42,"amount":12000,"currency":"USD","seller":"sqc1","factoringType":1,"factoringNo":"BL152446405675023495","maturity":"2018-04-11 ","indateMessage ":"2018-04-11","factoringName":"sqc1","countryName":"中国","rejectOpinion":"拒绝","checkState":"102","updateTranLetter":"1"}]
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
         * discountRate : 0.41
         * discountRateOld : 0.42
         * amount : 12000
         * currency : USD
         * seller : sqc1
         * factoringType : 1
         * factoringNo : BL152446405675023495
         * maturity : 2018-04-11
         * indateMessage  : 2018-04-11
         * factoringName : sqc1
         * countryName : 中国
         * rejectOpinion : 拒绝
         * checkState : 102
         * updateTranLetter : 1
         */

        private int id;
        private String discountRate;
        private String discountRateOld;
        private String amount;
        private String currency;
        private String seller;
        private int factoringType;
        private String factoringNo;
        private String maturity;
        private String indateMessage;
        private String factoringName;
        private String countryName;
        private String rejectOpinion;
        private String checkState;
        private String updateTranLetter;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getRejectOpinion() {
            return rejectOpinion;
        }

        public void setRejectOpinion(String rejectOpinion) {
            this.rejectOpinion = rejectOpinion;
        }

        public String getCheckState() {
            return checkState;
        }

        public void setCheckState(String checkState) {
            this.checkState = checkState;
        }

        public String getUpdateTranLetter() {
            return updateTranLetter;
        }

        public void setUpdateTranLetter(String updateTranLetter) {
            this.updateTranLetter = updateTranLetter;
        }
    }
}
