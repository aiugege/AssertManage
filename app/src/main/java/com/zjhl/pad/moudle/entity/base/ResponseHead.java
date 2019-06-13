package com.zjhl.pad.moudle.entity.base;

/**
 * @desc: ResponseHead
 * @version: v1.0
 * @packagename: com.zjhl.pad.entity.res
 * @author: pluto
 * @create: 2018/4/18 12:00
 * @projectname: nnkj
 **/
public class ResponseHead {
    public String code;
    public String message;
    public int totalCount;
    public int totalPage;
    public int pageSize;
    public int page;
    public Object json;
    public Object list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
}
