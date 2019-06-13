package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: ForfaitingRportReq   再次发布
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/3 17:26
 * @projectname: nnkj
 **/
public class ForfaitingRportReq implements Serializable {


    /**
     * id : 26
     * indateMessage : 2018-07-14
     * discountRate : 7
     */

    private String id;
    private String indateMessage;
    private String discountRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndateMessage() {
        return indateMessage;
    }

    public void setIndateMessage(String indateMessage) {
        this.indateMessage = indateMessage;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }
}
