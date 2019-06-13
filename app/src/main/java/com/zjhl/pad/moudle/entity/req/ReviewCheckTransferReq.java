package com.zjhl.pad.moudle.entity.req;

/* 
* File: ReviewCheckTransferReq
* Author: leeky 
* Version: V1.0
* Create: 2018/9/6 13:47 
* description: 查看让渡函请求体
*/
public class ReviewCheckTransferReq {
    /**
     * bussId : 3
     * assetsType : 1
     */

    private String bussId;
    private String assetsType;
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }
}
