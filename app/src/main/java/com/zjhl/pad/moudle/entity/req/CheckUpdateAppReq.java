package com.zjhl.pad.moudle.entity.req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @desc: CheckUpdateAppReq 检查app更新
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/8 20:16
 * @projectname: nnkj
 **/
public class CheckUpdateAppReq implements Serializable {

    //"type":1 //1-IOS 2-Android
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
