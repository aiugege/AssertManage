package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: MarketForfaitingDetailReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class IussingForfaitingEntryReq {


    /**
     * title : 求购一个特别便宜的4
     * currency : CNY
     * deadLine : 2018-04-24 10:54:52
     * amount : 123
     * creditType : 123123
     * indateMessage : 2018-04-23 10:54:52
     * areaId : 1
     * countryId : 1
     * discountRate : 5
     * debtType : 2
     */

    private String id;
    private String title;
    private String currency;
    private String deadLine;
    private String amount;
    private String creditType;
    private String indateMessage;
    private String areaId;
    private String countryId;
    private String discountRate;
    private String debtType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getDebtType() {
        return debtType;
    }

    public void setDebtType(String debtType) {
        this.debtType = debtType;
    }
}
