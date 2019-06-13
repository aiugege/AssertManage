package com.zjhl.pad.moudle.entity.req;

/* 
* File: BaojiatxRe.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/22 17:53 
* Changes (from 2018/5/22) 
*/
public class BaojiatxReq {

    private String discountRate;
    private String priceId;
    private String id;

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
