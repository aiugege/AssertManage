package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: ReviewOfferSubmitOnSaleListRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 * //当资产为福费廷时，点击确认返回要约函内容，保理无要约函
 **/
public class ReviewOfferSubmitLetterOnSaleListRes extends ResponseBean {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"id":7,"fAssetsId":3,"bOrgName":"机构名称_002","sOrgName":"机构名称_001","assetsNo":"1001","signDate":"2018-05-04 ","openFullName":"中国银行","debtType":1,"lcNo":"10","maturity":"2018-04-28 ","amount":8000,"currency":"CNY","deadLine":10,"payDate":"2018-05-04 ","payEndDate":"2018-05-04 ","grace":10,"fee":0.3,"bankCharges":0.3,"preCharging":0.3,"deliveryDate":"2018-05-04 ","payBillDay":10,"payAccount":"test","pollicitaBackDate":"2018-05-04 ","pollicitaBackEndDate":"2018-05-04 ","signPerson":"FFT15238723748542386","remark":"12","createTime":"2018-05-04 15:43:35","updateTime":"2018-05-04 15:43:35","yn":true}
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
         * id : 7
         * fAssetsId : 3
         * bOrgName : 机构名称_002
         * sOrgName : 机构名称_001
         * assetsNo : 1001
         * signDate : 2018-05-04
         * openFullName : 中国银行
         * debtType : 1
         * lcNo : 10
         * maturity : 2018-04-28
         * amount : 8000
         * currency : CNY
         * deadLine : 10
         * payDate : 2018-05-04
         * payEndDate : 2018-05-04
         * grace : 10
         * fee : 0.3
         * bankCharges : 0.3
         * preCharging : 0.3
         * deliveryDate : 2018-05-04
         * payBillDay : 10
         * payAccount : test
         * pollicitaBackDate : 2018-05-04
         * pollicitaBackEndDate : 2018-05-04
         * signPerson : FFT15238723748542386
         * remark : 12
         * createTime : 2018-05-04 15:43:35
         * updateTime : 2018-05-04 15:43:35
         * yn : true
         */

        private int id;
        private String fAssetsId;
        private String bOrgName;
        private String sOrgName;
        private String assetsNo;
        private String signDate;
        private String openFullName;
        private String debtType;
        private String lcNo;
        private String maturity;
        private String amount;
        private String currency;
        private String deadLine;
        private String payDate;
        private String payEndDate;
        private String grace;
        private String fee;
        private String bankCharges;
        private String preCharging;
        private String deliveryDate;
        private String payBillDay;
        private String payAccount;
        private String pollicitaBackDate;
        private String pollicitaBackEndDate;
        private String signPerson;
        private String remark;
        private String createTime;
        private String updateTime;
        private boolean yn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getfAssetsId() {
            return fAssetsId;
        }

        public void setfAssetsId(String fAssetsId) {
            this.fAssetsId = fAssetsId;
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

        public String getAssetsNo() {
            return assetsNo;
        }

        public void setAssetsNo(String assetsNo) {
            this.assetsNo = assetsNo;
        }

        public String getSignDate() {
            return signDate;
        }

        public void setSignDate(String signDate) {
            this.signDate = signDate;
        }

        public String getOpenFullName() {
            return openFullName;
        }

        public void setOpenFullName(String openFullName) {
            this.openFullName = openFullName;
        }

        public String getDebtType() {
            return debtType;
        }

        public void setDebtType(String debtType) {
            this.debtType = debtType;
        }

        public String getLcNo() {
            return lcNo;
        }

        public void setLcNo(String lcNo) {
            this.lcNo = lcNo;
        }

        public String getMaturity() {
            return maturity;
        }

        public void setMaturity(String maturity) {
            this.maturity = maturity;
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

        public String getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(String deadLine) {
            this.deadLine = deadLine;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getPayEndDate() {
            return payEndDate;
        }

        public void setPayEndDate(String payEndDate) {
            this.payEndDate = payEndDate;
        }

        public String getGrace() {
            return grace;
        }

        public void setGrace(String grace) {
            this.grace = grace;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getBankCharges() {
            return bankCharges;
        }

        public void setBankCharges(String bankCharges) {
            this.bankCharges = bankCharges;
        }

        public String getPreCharging() {
            return preCharging;
        }

        public void setPreCharging(String preCharging) {
            this.preCharging = preCharging;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getPayBillDay() {
            return payBillDay;
        }

        public void setPayBillDay(String payBillDay) {
            this.payBillDay = payBillDay;
        }

        public String getPayAccount() {
            return payAccount;
        }

        public void setPayAccount(String payAccount) {
            this.payAccount = payAccount;
        }

        public String getPollicitaBackDate() {
            return pollicitaBackDate;
        }

        public void setPollicitaBackDate(String pollicitaBackDate) {
            this.pollicitaBackDate = pollicitaBackDate;
        }

        public String getPollicitaBackEndDate() {
            return pollicitaBackEndDate;
        }

        public void setPollicitaBackEndDate(String pollicitaBackEndDate) {
            this.pollicitaBackEndDate = pollicitaBackEndDate;
        }

        public String getSignPerson() {
            return signPerson;
        }

        public void setSignPerson(String signPerson) {
            this.signPerson = signPerson;
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

        public boolean isYn() {
            return yn;
        }

        public void setYn(boolean yn) {
            this.yn = yn;
        }
    }
}
