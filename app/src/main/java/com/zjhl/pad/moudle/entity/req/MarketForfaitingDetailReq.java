package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: MarketForfaitingDetailReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class MarketForfaitingDetailReq {


    private String assertId;
    private String factoringId;
    private String yn = "0";

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getFactoringId() {
        return factoringId;
    }

    public void setFactoringId(String factoringId) {
        this.factoringId = factoringId;
    }

    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }
}
