package com.zjhl.pad.moudle.entity.req;

/*
* File: HobbyReq.java 保理 我的偏好 详情
* Author: DELL 
* Version: V1.0
* Create: 2018/6/4 17:06 
* Changes (from 2018/6/4) 
*/
public class HobbyReq {

    private String assetsType;
    private String currency;
    private int page;
    private int pageSize;
    private String rateBegin;
    private String rateEnd;
    private String amountBegin;
    private String amountEnd;
    private String areaId;
    private String countryIds;
    private String factoringType;
    private String orderBy;
    private String ascDesc;

    public String getFactoringType() {
        return factoringType;
    }

    public void setFactoringType(String factoringType) {
        this.factoringType = factoringType;
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

    public String getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(String countryIds) {
        this.countryIds = countryIds;
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
