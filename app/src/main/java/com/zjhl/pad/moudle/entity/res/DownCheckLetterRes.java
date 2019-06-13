package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: CheckLetterRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/6/6 18:13
 * @projectname: nnkj
 **/
public class DownCheckLetterRes extends ResponseBean {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : http://139.199.116.36:8088/group1/M00/00/01/rBUABlr5JLSAcuX6AAU7FottTCk785.pdf
     * json : null
     * list : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private String data;
    private String json;
    private String list;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
