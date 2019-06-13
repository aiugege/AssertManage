package com.zjhl.pad.moudle.entity.req;

/*
* File: MyHobbyReq.java 我的偏好————福费廷
* Author: DELL 
* Version: V1.0
* Create: 2018/6/3 15:19 
* Changes (from 2018/6/3) 
*/
public class MyHobbyReq {

    private String companyOrgId;
    private String assetsType;
    private String currency;
    private int page;
    private int pageSize;
    private String rateBegin;
    private String rateEnd;
    private String deadLine;
    private String countryIds;
    private String debtType;
    private String orderBy;
    private String ascDesc;
    private String areaId;
    private String amountBegin;
    private String amountEnd;

    public String getAmountBegin() {
        return amountBegin;
    }

    public void setAmountBegin(String amountBegin) {
        this.amountBegin = amountBegin;
    }

    public String getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(String amountEnd) {
        this.amountEnd = amountEnd;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCompanyOrgId() {
        return companyOrgId;
    }

    public void setCompanyOrgId(String companyOrgId) {
        this.companyOrgId = companyOrgId;
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getRateBegin() {
        return rateBegin;
    }

    public void setRateBegin(String rateBegin) {
        this.rateBegin = rateBegin;
    }

    public String getRateEnd() {
        return rateEnd;
    }

    public void setRateEnd(String rateEnd) {
        this.rateEnd = rateEnd;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(String countryIds) {
        this.countryIds = countryIds;
    }

    public String getDebtType() {
        return debtType;
    }

    public void setDebtType(String debtType) {
        this.debtType = debtType;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getAscDesc() {
        return ascDesc;
    }

    public void setAscDesc(String ascDesc) {
        this.ascDesc = ascDesc;
    }
}
