package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: MarketForfaitingDetailRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/15 20:39
 * @projectname: nnkj
 **/
public class MessageListRes extends ResponseBean  implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 17
     * totalPage : 2
     * pageSize : 10
     * page : 1
     * data : [{"id":66,"title":"\u201c美丽中国\u201d怎么建？习近平明确了时间表和路线图","publishDate":"2018-05-25","content":null},{"id":78,"title":"欧盟：美国对进口汽车加征关税违反WTO规定","publishDate":"2018-05-25","content":null},{"id":77,"title":"国务院：赋予自贸区更大改革自主权","publishDate":"2018-05-25","content":null},{"id":57,"title":"测试资讯4","publishDate":"2018-05-09","content":null},{"id":56,"title":"测试资讯3","publishDate":"2018-05-09","content":null},{"id":69,"title":"测试资讯17","publishDate":"2018-05-09","content":null},{"id":70,"title":"测试资讯15","publishDate":"2018-05-09","content":null},{"id":46,"title":"ceshi","publishDate":"2018-05-04","content":null},{"id":72,"title":"feeee","publishDate":"2018-05-04","content":null},{"id":76,"title":"崇尚英雄、缅怀先烈，习近平这十句话必须铭记","publishDate":"2018-05-02","content":null}]
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
         * id : 66
         * title : “美丽中国”怎么建？习近平明确了时间表和路线图
         * publishDate : 2018-05-25
         * content : null
         */

        private int id;
        private String title;
        private String publishDate;
        private String content;
        private String cType;
        private String linkName;

        public String getLinkName() {
            return linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public String getcType() {
            return cType;
        }

        public void setcType(String cType) {
            this.cType = cType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
