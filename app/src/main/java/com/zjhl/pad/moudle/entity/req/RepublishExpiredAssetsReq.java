package com.zjhl.pad.moudle.entity.req;

/*
 * File: RepublishExpiredAssetsReq
 * Author: Leeky
 * Version: V1.0
 * Create: 2018/12/13 17:36
 * description: 重新发布已失效资产请求体
 */ public class RepublishExpiredAssetsReq {


    /**
     * id : 5
     * discountRate : 10
     * transferRate : 10
     * indateMessage : 2019-12-10
     */

    private String id;
    private String discountRate;
    private String transferRate;
    private String indateMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(String transferRate) {
        this.transferRate = transferRate;
    }

    public String getIndateMessage() {
        return indateMessage;
    }

    public void setIndateMessage(String indateMessage) {
        this.indateMessage = indateMessage;
    }
}
