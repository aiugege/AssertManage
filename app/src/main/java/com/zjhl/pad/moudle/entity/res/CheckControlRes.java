package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: CheckControlRes  检查是否有福费廷录入权限
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/6/13 15:59
 * @projectname: nnkj
 **/
public class CheckControlRes extends ResponseBean {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"factoring":1,"forfeiting":0}
     * json : null
     * list : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private DataBean data;
    private Object json;
    private Object list;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * factoring : 1
         * forfeiting : 0
         */

        private String factoring;
        private String forfeiting;

        public String getFactoring() {
            return factoring;
        }

        public void setFactoring(String factoring) {
            this.factoring = factoring;
        }

        public String getForfeiting() {
            return forfeiting;
        }

        public void setForfeiting(String forfeiting) {
            this.forfeiting = forfeiting;
        }
    }
}
