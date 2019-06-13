package com.zjhl.pad.moudle.entity.req;

/* 
* File: SellAssetsFuFeitingReq.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/24 11:19 
* Changes (from 2018/5/24) 
*/
public class SellAssetsFuFeitingReq {


    /**
     * assetsType : 1
     * page : 1
     * pageSize : 3
     * recheckState : 9
     * currency : CNY
     * minimum : 1
     * maximum : 2
     * minDiscountRate : 1
     * maxDiscountRate : 2
     * debyType : 1
     * countryId : 1,2
     * openFullName : 中国银行
     * ascDesc : asc
     * orderBy : amount
     */

//    private String assetsType;
    private int page;
    private int pageSize;
    private String recheckState;
    private String currency;
    private String minimum;
    private String maximum;
    private String minDiscountRate;
    private String maxDiscountRate;
    private String debyType;
    private String countryId;
    private String openFullName;
    private String ascDesc;
    private String orderBy;
    private String deadline;

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public String getRecheckState() {
        return recheckState;
    }

    public void setRecheckState(String recheckState) {
        this.recheckState = recheckState;
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

    public String getMinDiscountRate() {
        return minDiscountRate;
    }

    public void setMinDiscountRate(String minDiscountRate) {
        this.minDiscountRate = minDiscountRate;
    }

    public String getMaxDiscountRate() {
        return maxDiscountRate;
    }

    public void setMaxDiscountRate(String maxDiscountRate) {
        this.maxDiscountRate = maxDiscountRate;
    }

    public String getDebyType() {
        return debyType;
    }

    public void setDebyType(String debyType) {
        this.debyType = debyType;
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

    @Override
    public String toString() {
        return "SellAssetsFuFeitingReq{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", recheckState='" + recheckState + '\'' +
                ", currency='" + currency + '\'' +
                ", minimum='" + minimum + '\'' +
                ", maximum='" + maximum + '\'' +
                ", minDiscountRate='" + minDiscountRate + '\'' +
                ", maxDiscountRate='" + maxDiscountRate + '\'' +
                ", debyType='" + debyType + '\'' +
                ", countryId='" + countryId + '\'' +
                ", openFullName='" + openFullName + '\'' +
                ", ascDesc='" + ascDesc + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
