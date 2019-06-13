package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.util.List;

/**
 * @desc: HomeBannerRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 11:16
 * @projectname: nnkj
 **/
public class HomeBannerRes extends ResponseBean {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : [{"id":6,"terminal":1,"url":"http://139.199.116.36/group1/M00/00/00/rBUABlrLGI-AR7i_AAcJRAvm9oA152.jpg","createrId":1,"createTime":"2018-04-09 15:39:00","yn":1,"state":1,"sort":1},{"id":3,"terminal":1,"url":"http://139.199.116.36\\group1\\M00/00/00/rBUABlrJ6jGAIDEjAACrbCSEX6U989.png","createrId":1,"createTime":"2018-04-08 18:08:51","yn":1,"state":1,"sort":3},{"id":4,"terminal":1,"url":"http://139.199.116.36\\group1\\M00/00/00/rBUABlrKy-aAOHY2AAICn1prGY4291.jpg","createrId":1,"createTime":"2018-04-09 10:11:54","yn":1,"state":1,"sort":4},{"id":5,"terminal":1,"url":"https://www.baidu.com/img/bd_logo1.png","createrId":1,"createTime":"2018-04-09 10:19:43","yn":1,"state":1,"sort":5}]
     * json : null
     * list : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private Object json;
    private Object list;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * terminal : 1
         * url : http://139.199.116.36/group1/M00/00/00/rBUABlrLGI-AR7i_AAcJRAvm9oA152.jpg
         * createrId : 1
         * createTime : 2018-04-09 15:39:00
         * yn : 1
         * state : 1
         * sort : 1
         */

        private int id;
        private int terminal;
        private String url;
        private int createrId;
        private String createTime;
        private int yn;
        private int state;
        private int sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTerminal() {
            return terminal;
        }

        public void setTerminal(int terminal) {
            this.terminal = terminal;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getCreaterId() {
            return createrId;
        }

        public void setCreaterId(int createrId) {
            this.createrId = createrId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getYn() {
            return yn;
        }

        public void setYn(int yn) {
            this.yn = yn;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
