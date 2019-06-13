package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/*
* File: MyHobbyRes.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/6/3 15:23 
* Changes (from 2018/6/3) 
*/
public class MyHobbyRes implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 2
     * totalPage : 1
     * pageSize : 5
     * page : 1
     * data : [{"id":6,"areaId":"1","areaName":"1","areaNameEn":"1","countryId":"1","countryName":"美国","countryNameEn":"USA","buyingNo":"12","title":"求购一个特别便宜的4","receiptBank":null,"amount":123,"currency":"CNY","deadLine":"2018-04-24 ","discountRate":5,"creditType":"123123","indateMessage":"2018-04-23 ","debtType":2}]
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
         * id : 6
         * areaId : 1
         * areaName : 1
         * areaNameEn : 1
         * countryId : 1
         * countryName : 美国
         * countryNameEn : USA
         * buyingNo : 12
         * title : 求购一个特别便宜的4
         * receiptBank : null
         * amount : 123
         * currency : CNY
         * deadLine : 2018-04-24
         * discountRate : 5
         * creditType : 123123
         * indateMessage : 2018-04-23
         * debtType : 2
         */

        private int id;
        private String areaId;
        private String areaName;
        private String areaNameEn;
        private String countryId;
        private String countryName;
        private String countryNameEn;
        private String buyingNo;
        private String title;
        private String receiptBank;
        private String amount;
        private String currency;
        private String deadLine;
        private String discountRate;
        private String creditType;
        private String indateMessage;
        private String debtType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAreaNameEn() {
            return areaNameEn;
        }

        public void setAreaNameEn(String areaNameEn) {
            this.areaNameEn = areaNameEn;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCountryNameEn() {
            return countryNameEn;
        }

        public void setCountryNameEn(String countryNameEn) {
            this.countryNameEn = countryNameEn;
        }

        public String getBuyingNo() {
            return buyingNo;
        }

        public void setBuyingNo(String buyingNo) {
            this.buyingNo = buyingNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getReceiptBank() {
            return receiptBank;
        }

        public void setReceiptBank(String receiptBank) {
            this.receiptBank = receiptBank;
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

        public String getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(String deadLine) {
            this.deadLine = deadLine;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getCreditType() {
            return creditType;
        }

        public void setCreditType(String creditType) {
            this.creditType = creditType;
        }

        public String getIndateMessage() {
            return indateMessage;
        }

        public void setIndateMessage(String indateMessage) {
            this.indateMessage = indateMessage;
        }

        public String getDebtType() {
            return debtType;
        }

        public void setDebtType(String debtType) {
            this.debtType = debtType;
        }
    }
}
