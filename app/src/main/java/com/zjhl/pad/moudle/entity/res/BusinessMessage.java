package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: BusinessMessage.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/6/14 17:42 
* Changes (from 2018/6/14) 
*/
public class BusinessMessage implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 2
     * totalPage : 1
     * pageSize : 10
     * page : 1
     * list : [{"id":3,"number":1,"title":"新闻资讯1","source":"个热缩管","state":101,"checker":1,"recheckReason":"","publishDate":"2018-04-12 19:24:01","remark":null,"createTime":"2018-04-12 17:58:50","updateTime":"2018-04-13 13:40:34","createrId":1,"updaterId":1,"languageType":"cn","yn":true,"type":1,"content":"分哇v个挖让v果然"}]
     * json : null
     * data : null
     */

    private int code;
    private String message;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private int page;
    private Object json;
    private Object data;
    private List<ListBean> list;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * number : 1
         * title : 新闻资讯1
         * source : 个热缩管
         * state : 101
         * checker : 1
         * recheckReason :
         * publishDate : 2018-04-12 19:24:01
         * remark : null
         * createTime : 2018-04-12 17:58:50
         * updateTime : 2018-04-13 13:40:34
         * createrId : 1
         * updaterId : 1
         * languageType : cn
         * yn : true
         * type : 1
         * content : 分哇v个挖让v果然
         */

        private int id;
        private int number;
        private String title;
        private String source;
        private int state;
        private int checker;
        private String recheckReason;
        private String publishDate;
        private Object remark;
        private String createTime;
        private String updateTime;
        private int createrId;
        private int updaterId;
        private String languageType;
        private boolean yn;
        private int type;
        private String content;
        private String linkName;
        private String assetsId;
        private String assetsType;
        private String messageNotice;
        private String readState;
        private String cType;

        public String getcType() {
            return cType;
        }

        public void setcType(String cType) {
            this.cType = cType;
        }

        public String getReadState() {
            return readState;
        }

        public void setReadState(String readState) {
            this.readState = readState;
        }

        public String getMessageNotice() {
            return messageNotice;
        }

        public void setMessageNotice(String messageNotice) {
            this.messageNotice = messageNotice;
        }

        public String getAssetsType() {
            return assetsType;
        }

        public void setAssetsType(String assetsType) {
            this.assetsType = assetsType;
        }

        public String getAssetsId() {
            return assetsId;
        }

        public void setAssetsId(String assetsId) {
            this.assetsId = assetsId;
        }

        public String getLinkName() {
            return linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getChecker() {
            return checker;
        }

        public void setChecker(int checker) {
            this.checker = checker;
        }

        public String getRecheckReason() {
            return recheckReason;
        }

        public void setRecheckReason(String recheckReason) {
            this.recheckReason = recheckReason;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getCreaterId() {
            return createrId;
        }

        public void setCreaterId(int createrId) {
            this.createrId = createrId;
        }

        public int getUpdaterId() {
            return updaterId;
        }

        public void setUpdaterId(int updaterId) {
            this.updaterId = updaterId;
        }

        public String getLanguageType() {
            return languageType;
        }

        public void setLanguageType(String languageType) {
            this.languageType = languageType;
        }

        public boolean isYn() {
            return yn;
        }

        public void setYn(boolean yn) {
            this.yn = yn;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @Override
    public String toString() {
        return "BusinessMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", json=" + json +
                ", data=" + data +
                ", list=" + list +
                '}';
    }
}
