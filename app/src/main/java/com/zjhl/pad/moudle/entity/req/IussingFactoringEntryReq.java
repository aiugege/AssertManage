package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: MarketForfaitingDetailReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class IussingFactoringEntryReq {


    /**
     * factoringType : 1
     * currency : CNY
     * amount : 123
     * transferRate : 3.8
     * maturity : 2018-05-20 18:32:17
     * indateMessage : 2018-05-20 18:32:17
     * isCove : 1
     * areaId : 1
     * countryId : 1
     */

    private String id;
    private String factoringType;
    private String currency;
    private String amount;
    private String transferRate;
    private String maturity;
    private String indateMessage;
    private String isCove;
    private String areaId;
    private String countryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoringType() {
        return factoringType;
    }

    public void setFactoringType(String factoringType) {
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

    public String getIsCove() {
        return isCove;
    }

    public void setIsCove(String isCove) {
        this.isCove = isCove;
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
}
