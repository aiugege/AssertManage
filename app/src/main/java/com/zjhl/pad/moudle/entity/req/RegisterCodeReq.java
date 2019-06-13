package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: RegisterReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class RegisterCodeReq {

    /**
     * type : 1   //手机或邮箱  1邮箱 2 手机  必输
     目前默认都是邮箱，后期再根据业务修改
     * code : itheng@126.com
     */

    private String type;
    private String code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
