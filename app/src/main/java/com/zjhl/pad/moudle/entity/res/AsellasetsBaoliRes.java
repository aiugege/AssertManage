package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: AsellasetsBaoliRes.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/23 16:52 
* Changes (from 2018/5/23) 
*/
public class AsellasetsBaoliRes implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 1
     * totalPage : 1
     * pageSize : 3
     * page : 1
     * data : [{"id":1,"companyOrgId":1,"factoringNo":"1001","seller":"1","factoringType":1,"currency":"CNY","amount":8800,"transferRate":1.2,"maturity":"2018-04-26 ","indateMessage":null,"factoringName":"发放发的","isCove":1,"insurer":"23","checkState":202,"checkReason":"运营经办审核通过，进入待运营复核审核","areaName":"亚洲","countryName":"中国","gradeCode":null,"myAssets":0}]
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
         * id : 1
         * companyOrgId : 1
         * factoringNo : 1001
         * seller : 1
         * factoringType : 1
         * currency : CNY
         * amount : 8800
         * transferRate : 1.2
         * maturity : 2018-04-26
         * indateMessage : null
         * factoringName : 发放发的
         * isCove : 1
         * insurer : 23
         * checkState : 202
         * checkReason : 运营经办审核通过，进入待运营复核审核
         * areaName : 亚洲
         * countryName : 中国
         * gradeCode : null
         * myAssets : 0
         */

        private int id;
        private int companyOrgId;
        private String factoringNo;
        private String seller;
        private int factoringType;
        private String currency;
        private String amount;
        private String transferRate;
        private String maturity;
        private String indateMessage;
        private String factoringName;
        private String isCove;
        private String insurer;
        private String checkStateBefore;
        private String checkState;
        private String checkReason;
        private String areaName;
        private String countryName;
        private String gradeCode;
        private String myAssets;
        private String trsNo;
        private String yn;
        private String countryNameEn;
        private String areaNameEn;
        private String enCountryName;
        private String enAreaName;

        public String getEnCountryName() {
            return enCountryName;
        }

        public void setEnCountryName(String enCountryName) {
            this.enCountryName = enCountryName;
        }

        public String getEnAreaName() {
            return enAreaName;
        }

        public void setEnAreaName(String enAreaName) {
            this.enAreaName = enAreaName;
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

        public String getCheckStateBefore() {
            return checkStateBefore;
        }

        public void setCheckStateBefore(String checkStateBefore) {
            this.checkStateBefore = checkStateBefore;
        }

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

        public String getFactoringNo() {
            return factoringNo;
        }

        public void setFactoringNo(String factoringNo) {
            this.factoringNo = factoringNo;
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

        public String getMyAssets() {
            return myAssets;
        }

        public void setMyAssets(String myAssets) {
            this.myAssets = myAssets;
        }

        public String getTrsNo() {
            return trsNo;
        }

        public void setTrsNo(String trsNo) {
            this.trsNo = trsNo;
        }
    }

    @Override
    public String toString() {
        return "AsellasetsBaoliRes{" +
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
