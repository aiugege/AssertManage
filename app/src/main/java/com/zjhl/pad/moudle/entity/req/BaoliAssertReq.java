package com.zjhl.pad.moudle.entity.req;

/*
* File: BaoliAssertReq.java 
* Author: DELL   保理
* Version: V1.0
* Create: 2018/5/21 11:12 
* Changes (from 2018/5/21) 
*/
public class BaoliAssertReq {


    /**
     * assetsType : 2
     * page : 1
     * pageSize : 3
     * checkState : 9
     * currency : CNY
     * minimum : 1
     * maximum : 2
     * minTransferRate : 1
     * maxTransferRate : 2
     * countryId : 1,2
     * openFullName : 中国银行
     * ascDesc : asc
     * orderBy : amount
     */

    private String assetsType;
    private int page;
    private int pageSize;
    private String checkState;
    private String currency;
    private String minimum;
    private String maximum;
    private String minTransferRate;
    private String maxTransferRate;
    private String countryId;
    private String openFullName;
    private String ascDesc;
    private String orderBy;

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
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

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinTransferRate() {
        return minTransferRate;
    }

    public void setMinTransferRate(String minTransferRate) {
        this.minTransferRate = minTransferRate;
    }

    public String getMaxTransferRate() {
        return maxTransferRate;
    }

    public void setMaxTransferRate(String maxTransferRate) {
        this.maxTransferRate = maxTransferRate;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getOpenFullName() {
        return openFullName;
    }

    public void setOpenFullName(String openFullName) {
        this.openFullName = openFullName;
    }

    public String getAscDesc() {
        return ascDesc;
    }

    public void setAscDesc(String ascDesc) {
        this.ascDesc = ascDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
