package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: BlockChainReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/28 11:59
 * @projectname: nnkj
 **/
public class BlockChainReq implements Serializable{

    /**
     * {
     * "bussNo": "", //业务编号
     * "bussId":"", //资产表ID
     * "bussTypeState":"" //交易标识 1 发布 2 撮合成功 3 交易完成
     * }
     */

    private String bussNo;
    private String bussId;
    private String[] bussIds;
    private String bussTypeState;

    public String getBussNo() {
        return bussNo;
    }

    public void setBussNo(String bussNo) {
        this.bussNo = bussNo;
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public String getBussTypeState() {
        return bussTypeState;
    }

    public void setBussTypeState(String bussTypeState) {
        this.bussTypeState = bussTypeState;
    }

    public String[] getBussIds() {
        return bussIds;
    }

    public void setBussIds(String[] bussIds) {
        this.bussIds = bussIds;
    }
}
