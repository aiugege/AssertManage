package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: MarketForfaitingOfferListRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/17 15:59
 * @projectname: nnkj
 **/
public class MarketForfaitingOfferListRes extends ResponseBean implements Serializable {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 1
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : [{"id":2,"bussNo":"CSCJ1524206180462132","bussId":1,"tradingType":1,"discountRate":3.8,"amount":100,"currency":"CNY","priceState":107,"isStruck":1,"struckDate":"2018-04-23 ","remark":null,"bOrgName":"机构名称_001","sOrgName":"机构名称_001","borgId":1,"sorgId":1}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 2
         * bussNo : CSCJ1524206180462132
         * bussId : 1
         * tradingType : 1
         * discountRate : 3.8
         * amount : 100
         * currency : CNY
         * priceState : 107
         * isStruck : 1
         * struckDate : 2018-04-23
         * remark : null
         * bOrgName : 机构名称_001
         * sOrgName : 机构名称_001
         * borgId : 1
         * sorgId : 1
         */

        private int id;
        private String bussNo;
        private String bussId;
        private String tradingType;
        private String discountRate;
        private String amount;
        private String currency;
        private String priceState;
        private String isStruck;
        private String struckDate;
        private String remark;
        private String bOrgName;
        private String sOrgName;
        private String bOrgId;
        private String sOrgId;
        private String rejectOpinion;
        private String createTime;
        private String updateTime;
        private boolean isSelected = false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBussNo() {
            return bussNo;
        }

        public void setBussNo(String bussNo) {
            this.bussNo = bussNo;
        }

        public String getBussId() {
            return bussId;
        }

        public void setBussId(String bussId) {
            this.bussId = bussId;
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

        public String getTradingType() {
            return tradingType;
        }

        public void setTradingType(String tradingType) {
            this.tradingType = tradingType;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPriceState() {
            return priceState;
        }

        public void setPriceState(String priceState) {
            this.priceState = priceState;
        }

        public String getIsStruck() {
            return isStruck;
        }

        public void setIsStruck(String isStruck) {
            this.isStruck = isStruck;
        }

        public String getStruckDate() {
            return struckDate;
        }

        public void setStruckDate(String struckDate) {
            this.struckDate = struckDate;
        }

        public Object getRemark() {
            return remark;
        }

        public String getbOrgName() {
            return bOrgName;
        }

        public void setbOrgName(String bOrgName) {
            this.bOrgName = bOrgName;
        }

        public String getsOrgName() {
            return sOrgName;
        }

        public void setsOrgName(String sOrgName) {
            this.sOrgName = sOrgName;
        }

        public String getbOrgId() {
            return bOrgId;
        }

        public void setbOrgId(String bOrgId) {
            this.bOrgId = bOrgId;
        }

        public String getsOrgId() {
            return sOrgId;
        }

        public void setsOrgId(String sOrgId) {
            this.sOrgId = sOrgId;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRejectOpinion() {
            return rejectOpinion;
        }

        public void setRejectOpinion(String rejectOpinion) {
            this.rejectOpinion = rejectOpinion;
        }
    }
}
