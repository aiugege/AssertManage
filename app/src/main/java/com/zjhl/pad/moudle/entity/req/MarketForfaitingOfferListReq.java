package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: MarketForfaitingOfferListRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/17 15:59
 * @projectname: nnkj
 **/
public class MarketForfaitingOfferListReq implements Serializable {


    /**
     * assertId : 1
     * tradingType : 1
     */

    private String assertId;
    private String tradingType;

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }
}
