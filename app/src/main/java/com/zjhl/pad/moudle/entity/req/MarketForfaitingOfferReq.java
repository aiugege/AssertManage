package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: MarketForfaitingOfferReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/17 11:09
 * @projectname: nnkj
 **/
public class MarketForfaitingOfferReq
{

    /**id :1
     * bussId : 1
     * tradingType : 1
     * discountRate : 3.8
     */

    private String id;
    private String bussId;
    private String tradingType;
    private String discountRate;
    private String refuseAdvice;

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefuseAdvice() {
        return refuseAdvice;
    }

    public void setRefuseAdvice(String refuseAdvice) {
        this.refuseAdvice = refuseAdvice;
    }
}
