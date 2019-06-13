package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: ForgetPassSendCodeReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/23 18:04
 * @projectname: nnkj
 **/
public class ForgetPassSendCodeReq implements Serializable {

    /**
     * userName : 694726990@qq.com
     * code : 4587
     */

    private String userName;
    private String code;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
