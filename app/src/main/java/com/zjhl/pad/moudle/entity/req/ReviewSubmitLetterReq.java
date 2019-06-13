package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: ReviewSubmitLetterReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/17 11:09
 * @projectname: nnkj
 **/
public class ReviewSubmitLetterReq implements Serializable {


    /**
     "id": 26, 资产id
	 "ST0210":"sdalsdj/asdfs/sdsd.url"
     * 让渡函URL
     */


    private String id;
    private String ST0210;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getST0210() {
        return ST0210;
    }

    public void setST0210(String ST0210) {
        this.ST0210 = ST0210;
    }
}
