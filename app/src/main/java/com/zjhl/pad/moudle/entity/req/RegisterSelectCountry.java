package com.zjhl.pad.moudle.entity.req;

/*
 * File: RegisterSelectCountry.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/2 11:33
 * Changes (from 2018/5/2)
 */
public class RegisterSelectCountry {

    /*
    "areaId":1          //地区id     非空
	"name":"南极",            //可支持名称的模糊查询  非必填
		"id":13	                //可支持id查询         非必填
"international":1 //国际信用证 1为国际，国际信用证时查询的国家不包含中国 非必填  （支持老版本）

     */
    private String areaId;
    private String international;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international;
    }
}
