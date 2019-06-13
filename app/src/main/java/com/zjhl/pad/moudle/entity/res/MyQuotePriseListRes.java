package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: MyQuotePriseListRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/17 15:59
 * @projectname: nnkj
 **/
public class MyQuotePriseListRes extends ResponseBean implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : [{"id":7,"fPriceId":379,"discountRate":1.8,"priceDate":"2018-08-07 05:47 06","priceState":3,"rejectOpinion":"拒绝原因啊","remark":"不通过"}]
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
         * id : 7
         * fPriceId : 379
         * discountRate : 1.8
         * priceDate : 2018-08-07 05:47 06
         * priceState : 3
         * rejectOpinion : 拒绝原因啊
         * remark : 不通过
         */

        private String id;
        private String fPriceId;
        private String discountRate;
        private String priceDate;
        private String priceState;
        private String rejectOpinion;
        private String remark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getfPriceId() {
            return fPriceId;
        }

        public void setfPriceId(String fPriceId) {
            this.fPriceId = fPriceId;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getPriceDate() {
            return priceDate;
        }

        public void setPriceDate(String priceDate) {
            this.priceDate = priceDate;
        }

        public String getPriceState() {
            return priceState;
        }

        public void setPriceState(String priceState) {
            this.priceState = priceState;
        }

        public String getRejectOpinion() {
            return rejectOpinion;
        }

        public void setRejectOpinion(String rejectOpinion) {
            this.rejectOpinion = rejectOpinion;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
