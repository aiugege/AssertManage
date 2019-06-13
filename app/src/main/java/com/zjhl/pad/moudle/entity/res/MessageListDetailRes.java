package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;

/**
 * @desc: MarketForfaitingDetailRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/15 20:39
 * @projectname: nnkj
 **/
public class MessageListDetailRes extends ResponseBean  implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"id":66,"number":1525248169543,"title":"\u201c美丽中国\u201d怎么建？习近平明确了时间表和路线图","source":"银行机构","state":101,"checker":1,"recheckReason":"","publishDate":"2018-05-25","remark":null,"createTime":"2018-05-02","updateTime":"2018-05-25","createrId":14,"updaterId":1,"languageType":"cn","yn":true,"type":1,"content":"<p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;\"><strong>习近平在全国生态环境保护大会上强调<\/strong><\/p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;\"><strong>坚决打好污染防治攻坚战<\/strong><\/p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;\"><strong>推动生态文明建设迈上新台阶<\/strong><\/p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;\">李克强韩正讲话汪洋王沪宁赵乐际出席<\/p><p style=\"margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; line-height: 2em;\">全国生态环境保护大会18日至19日在北京召开。中共中央总书记、国家主席、中央军委主席习近平出席会议并发表重要讲话。他强调，要自觉把经济社会发展同生态文明建设统筹起来，充分发挥党的领导和我国社会主义制度能够集中力量办大事的政治优势，充分利用改革开放40年来积累的坚实物质基础，加大力度推进生态文明建设、解决生态环境问题，坚决打好污染防治攻坚战，推动我国生态文明建设迈上新台阶。<\/p>"}
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
         * id : 66
         * number : 1525248169543
         * title : “美丽中国”怎么建？习近平明确了时间表和路线图
         * source : 银行机构
         * state : 101
         * checker : 1
         * recheckReason :
         * publishDate : 2018-05-25
         * remark : null
         * createTime : 2018-05-02
         * updateTime : 2018-05-25
         * createrId : 14
         * updaterId : 1
         * languageType : cn
         * yn : true
         * type : 1
         * content : <p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;"><strong>习近平在全国生态环境保护大会上强调</strong></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;"><strong>坚决打好污染防治攻坚战</strong></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;"><strong>推动生态文明建设迈上新台阶</strong></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em;">李克强韩正讲话汪洋王沪宁赵乐际出席</p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; font-family: &quot;Microsoft Yahei&quot;, Helvetica, sans-serif; white-space: normal; background-color: rgb(255, 255, 255); text-indent: 2em; line-height: 2em;">全国生态环境保护大会18日至19日在北京召开。中共中央总书记、国家主席、中央军委主席习近平出席会议并发表重要讲话。他强调，要自觉把经济社会发展同生态文明建设统筹起来，充分发挥党的领导和我国社会主义制度能够集中力量办大事的政治优势，充分利用改革开放40年来积累的坚实物质基础，加大力度推进生态文明建设、解决生态环境问题，坚决打好污染防治攻坚战，推动我国生态文明建设迈上新台阶。</p>
         */

        private int id;
        private String number;
        private String title;
        private String source;
        private String state;
        private String checker;
        private String recheckReason;
        private String publishDate;
        private String remark;
        private String createTime;
        private String updateTime;
        private String createrId;
        private String updaterId;
        private String languageType;
        private boolean yn;
        private String type;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getChecker() {
            return checker;
        }

        public void setChecker(String checker) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public String getCreaterId() {
            return createrId;
        }

        public void setCreaterId(String createrId) {
            this.createrId = createrId;
        }

        public String getUpdaterId() {
            return updaterId;
        }

        public void setUpdaterId(String updaterId) {
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
