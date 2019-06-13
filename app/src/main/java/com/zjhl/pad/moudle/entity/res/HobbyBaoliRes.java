package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: HobbyRes.java 我的偏好  保理
* Author: DELL 
* Version: V1.0
* Create: 2018/6/4 17:31 
* Changes (from 2018/6/4) 
*/
public class HobbyBaoliRes implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 2
     * totalPage : 1
     * pageSize : 5
     * page : 1
     * data : [{"id":6,"areaId":"1","areaName":"1","areaNameEn":"1","countryId":"1","countryName":"美国","countryNameEn":"USA","buyingNo":"BFFT1524552069943773","factoringType":1,"amount":123,"currency":"CNY","maturity":"2018-04-24 ","transfer_rate":5,"indateMessage":"1"}]
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
         * buyingNo : BFFT1524552069943773
         * factoringType : 1
         * amount : 123
         * currency : CNY
         * maturity : 2018-04-24
         * transfer_rate : 5
         * indateMessage : 1
         */

        private int id;
        private String areaId;
        private String areaName;
        private String areaNameEn;
        private String countryId;
        private String countryName;
        private String countryNameEn;
        private String buyingNo;
        private int factoringType;
        private String amount;
        private String currency;
        private String maturity;
        private String transfer_rate;
        private String transferRate;
        private String indateMessage;

        public String getTransferRate() {
            return transferRate;
        }

        public void setTransferRate(String transferRate) {
            this.transferRate = transferRate;
        }

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

        public int getFactoringType() {
            return factoringType;
        }

        public void setFactoringType(int factoringType) {
            this.factoringType = factoringType;
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

        public String getMaturity() {
            return maturity;
        }

        public void setMaturity(String maturity) {
            this.maturity = maturity;
        }

        public String getTransfer_rate() {
            return transfer_rate;
        }

        public void setTransfer_rate(String transfer_rate) {
            this.transfer_rate = transfer_rate;
        }

        public String getIndateMessage() {
            return indateMessage;
        }

        public void setIndateMessage(String indateMessage) {
            this.indateMessage = indateMessage;
        }
    }

    @Override
    public String toString() {
        return "HobbyRes{" +
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
