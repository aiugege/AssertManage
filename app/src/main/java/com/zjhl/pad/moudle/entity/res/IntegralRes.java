package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: IntegralRes.java 积分
* Author: DELL 
* Version: V1.0
* Create: 2018/5/5 18:17 
* Changes (from 2018/5/5) 
*/
public class IntegralRes implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"userIntegral":200,"integralDetailList":[{"id":46,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:51","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":47,"fUserIntegralId":26,"integralState":2,"integralDate":"2018-05-07 11:43:53","integralDesc":"福费廷资产交易失败","integralValue":200,"remark":"不知道213456","createTime":null,"updateTime":null,"yn":null},{"id":48,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:54","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":49,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:55","integralDesc":"机构补充资料","integralValue":300,"remark":"","createTime":null,"updateTime":null,"yn":null},{"id":50,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:57","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":51,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:58","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":52,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:44:02","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null}]}
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
         * userIntegral : 200
         * integralDetailList : [{"id":46,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:51","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":47,"fUserIntegralId":26,"integralState":2,"integralDate":"2018-05-07 11:43:53","integralDesc":"福费廷资产交易失败","integralValue":200,"remark":"不知道213456","createTime":null,"updateTime":null,"yn":null},{"id":48,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:54","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":49,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:55","integralDesc":"机构补充资料","integralValue":300,"remark":"","createTime":null,"updateTime":null,"yn":null},{"id":50,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:57","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":51,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:43:58","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null},{"id":52,"fUserIntegralId":26,"integralState":1,"integralDate":"2018-05-07 11:44:02","integralDesc":"机构注册","integralValue":200,"remark":null,"createTime":null,"updateTime":null,"yn":null}]
         */

        private int userIntegral;
        private List<IntegralDetailListBean> integralDetailList;

        public int getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(int userIntegral) {
            this.userIntegral = userIntegral;
        }

        public List<IntegralDetailListBean> getIntegralDetailList() {
            return integralDetailList;
        }

        public void setIntegralDetailList(List<IntegralDetailListBean> integralDetailList) {
            this.integralDetailList = integralDetailList;
        }

        public static class IntegralDetailListBean {
            /**
             * id : 46
             * fUserIntegralId : 26
             * integralState : 1
             * integralDate : 2018-05-07 11:43:51
             * integralDesc : 机构注册
             * integralValue : 200
             * remark : null
             * createTime : null
             * updateTime : null
             * yn : null
             */

            private int id;
            private int fUserIntegralId;
            private int integralState;
            private String integralDate;
            private int integralValue;
            private Object remark;
            private Object createTime;
            private Object updateTime;
            private Object yn;
            private String integralDescEn;
            private String integralDesc;

            public String getIntegralDescEn() {
                return integralDescEn;
            }

            public void setIntegralDescEn(String integralDescEn) {
                this.integralDescEn = integralDescEn;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFUserIntegralId() {
                return fUserIntegralId;
            }

            public void setFUserIntegralId(int fUserIntegralId) {
                this.fUserIntegralId = fUserIntegralId;
            }

            public int getIntegralState() {
                return integralState;
            }

            public void setIntegralState(int integralState) {
                this.integralState = integralState;
            }

            public String getIntegralDate() {
                return integralDate;
            }

            public void setIntegralDate(String integralDate) {
                this.integralDate = integralDate;
            }

            public String getIntegralDesc() {
                return integralDesc;
            }

            public void setIntegralDesc(String integralDesc) {
                this.integralDesc = integralDesc;
            }

            public int getIntegralValue() {
                return integralValue;
            }

            public void setIntegralValue(int integralValue) {
                this.integralValue = integralValue;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getYn() {
                return yn;
            }

            public void setYn(Object yn) {
                this.yn = yn;
            }
        }
    }

    @Override
    public String toString() {
        return "IntegralRes{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", data=" + data +
                ", json=" + json +
                ", list=" + list +
                '}';
    }
}
