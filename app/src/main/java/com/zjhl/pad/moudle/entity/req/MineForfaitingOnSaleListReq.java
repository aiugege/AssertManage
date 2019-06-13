package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: MineForfaitingOnSaleListReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/23 13:33
 * @projectname: nnkj
 **/
public class MineForfaitingOnSaleListReq implements Serializable {

    /**
     * page : 1
     * pageSize : 3
     * title : 黄金
     * recheckState : 9
     * currency : CNY
     * minimum : 1
     * maximum : 2
     * minDiscountRate : 1
     * maxDiscountRate : 2
     * deadline : 1
     * trsNo : 1
     * countryId : 1,2
     * saleState : 1
     * openFullName : 中国银行
     * ascDesc : ASC
     * orderBy : amount
     */

    private int page;
    private int pageSize;
    private String title;
    private String recheckState;
    private String checkState;
    private String currency;
    private String minimum;
    private String maximum;
    private String minDiscountRate;
    private String maxDiscountRate;
    private String deadline;
    private String trsNo;
    private String countryId;
    private String saleState;
    private String openFullName;
    private String ascDesc;
    private String orderBy;
    private String debtType;

    public String getDebtType() {
        return debtType;
    }

    public void setDebtType(String debtType) {
        this.debtType = debtType;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTrsNo() {
        return trsNo;
    }

    public void setTrsNo(String trsNo) {
        this.trsNo = trsNo;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
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
