package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: RegisterRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/20 13:46
 * @projectname: nnkj
 **/
public class RegisterCheckEmailPhoneRes extends ResponseBean {

    /**
     * code : 300 //300:成功,400:业务异常,500:系统异常
     * message : “注册已成功，用户ID为: {0}，管理员用户名为：{1}“
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * json : null
     * list : null
     * data : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private Object json;
    private Object list;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
