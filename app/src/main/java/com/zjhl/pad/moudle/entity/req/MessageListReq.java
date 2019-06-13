package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: MarketForfaitingOfferReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/17 11:09
 * @projectname: nnkj
 **/
public class MessageListReq implements Serializable
{


    /**
     * type : 1
     * page : 1
     * pageSize : 10
     */

    private String id;
    private String type;
    private String ctype;
    private String page;
    private String pageSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
}
