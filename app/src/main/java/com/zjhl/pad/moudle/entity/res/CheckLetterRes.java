package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: CheckLetterRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/6/6 18:13
 * @projectname: nnkj
 **/
public class CheckLetterRes extends ResponseBean {

    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"id":193,"flowNo":"20180530155337","assetsNo":"Z18053000027","signDate":"2018-05-30","openFullName":"上海浦东发展银行","tenderFullName":"上海浦东发展银行","debtType":1,"lcNo":"Lc94011700043","creditApp":"张大大","creditBeneficiary":"张小小","basicTransaction":"私募基金","deadLine":"2018-11-01","invoiceAmount":25.23,"amount":8874000,"currency":"USD","acceptEndDate":"2018-12-15","discountRate":1.23,"interestRate":"预收","fee":25.23,"discountPeriodDay":8,"discountPeriodStart":"2018-12-15","discountAcceptDate":"2019-10-02","discountPeriodWork":10,"bankCost":1000.12,"preCharging":10.26,"deliveryDate":"2018-08-12","payBillDay":10,"payAccount":"622202199301234554","payAccountBank":"622202199301234554","payAccountApproach":"划扣","pollicitaBackDate":"2018-05-14","pollicitaBackEndDate":"2018-02-15","specialAgreement":"无","signPerson":"韩梅梅","saveUrl":"http://139.199.116.36:8088/group1/M00/00/09/rBUABlsOWIaAc4L-AAU64KfCSQo735.pdf","remark":null,"createTime":"2018-05-30","updateTime":"2018-05-30","yn":1,"fassetsId":789,"borgName":"深圳国投商业保理有限公司","sorgName":"牛牛金科1"}
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
         * id : 193
         * flowNo : 20180530155337
         * assetsNo : Z18053000027
         * signDate : 2018-05-30
         * openFullName : 上海浦东发展银行
         * tenderFullName : 上海浦东发展银行
         * debtType : 1
         * lcNo : Lc94011700043
         * creditApp : 张大大
         * creditBeneficiary : 张小小
         * basicTransaction : 私募基金
         * deadLine : 2018-11-01
         * invoiceAmount : 25.23
         * amount : 8874000
         * currency : USD
         * acceptEndDate : 2018-12-15
         * discountRate : 1.23
         * interestRate : 预收
         * fee : 25.23
         * discountPeriodDay : 8
         * discountPeriodStart : 2018-12-15
         * discountAcceptDate : 2019-10-02
         * discountPeriodWork : 10
         * bankCost : 1000.12
         * preCharging : 10.26
         * deliveryDate : 2018-08-12
         * payBillDay : 10
         * payAccount : 622202199301234554
         * payAccountBank : 622202199301234554
         * payAccountApproach : 划扣
         * pollicitaBackDate : 2018-05-14
         * pollicitaBackEndDate : 2018-02-15
         * specialAgreement : 无
         * signPerson : 韩梅梅
         * saveUrl : http://139.199.116.36:8088/group1/M00/00/09/rBUABlsOWIaAc4L-AAU64KfCSQo735.pdf
         * remark : null
         * createTime : 2018-05-30
         * updateTime : 2018-05-30
         * yn : 1
         * fassetsId : 789
         * borgName : 深圳国投商业保理有限公司
         * sorgName : 牛牛金科1
         */

        private int id;
        private String flowNo;
        private String assetsNo;
        private String signDate;
        private String openFullName;
        private String tenderFullName;
        private String debtType;
        private String lcNo;
        private String creditApp;
        private String creditBeneficiary;
        private String basicTransaction;
        private String deadLine;
        private String invoiceAmount;
        private String amount;
        private String currency;
        private String acceptEndDate;
        private String discountRate;
        private String interestRate;
        private String fee;
        private String discountPeriodDay;
        private String discountPeriodStart;
        private String discountAcceptDate;
        private String discountPeriodWork;
        private String bankCost;
        private String preCharging;
        private String deliveryDate;
        private String payBillDay;
        private String payAccount;
        private String payAccountBank;
        private String payAccountApproach;
        private String pollicitaBackDate;
        private String pollicitaBackEndDate;
        private String specialAgreement;
        private String signPerson;
        private String saveUrl;
        private String remark;
        private String createTime;
        private String updateTime;
        private String yn;
        private String fassetsId;
        private String borgName;
        private String sorgName;
        private String grace;

        public String getGrace() {
            return grace;
        }

        public void setGrace(String grace) {
            this.grace = grace;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFlowNo() {
            return flowNo;
        }

        public void setFlowNo(String flowNo) {
            this.flowNo = flowNo;
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

        public String getTenderFullName() {
            return tenderFullName;
        }

        public void setTenderFullName(String tenderFullName) {
            this.tenderFullName = tenderFullName;
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

        public String getCreditApp() {
            return creditApp;
        }

        public void setCreditApp(String creditApp) {
            this.creditApp = creditApp;
        }

        public String getCreditBeneficiary() {
            return creditBeneficiary;
        }

        public void setCreditBeneficiary(String creditBeneficiary) {
            this.creditBeneficiary = creditBeneficiary;
        }

        public String getBasicTransaction() {
            return basicTransaction;
        }

        public void setBasicTransaction(String basicTransaction) {
            this.basicTransaction = basicTransaction;
        }

        public String getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(String deadLine) {
            this.deadLine = deadLine;
        }

        public String getInvoiceAmount() {
            return invoiceAmount;
        }

        public void setInvoiceAmount(String invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
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

        public String getAcceptEndDate() {
            return acceptEndDate;
        }

        public void setAcceptEndDate(String acceptEndDate) {
            this.acceptEndDate = acceptEndDate;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getInterestRate() {
            return interestRate;
        }

        public void setInterestRate(String interestRate) {
            this.interestRate = interestRate;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getDiscountPeriodDay() {
            return discountPeriodDay;
        }

        public void setDiscountPeriodDay(String discountPeriodDay) {
            this.discountPeriodDay = discountPeriodDay;
        }

        public String getDiscountPeriodStart() {
            return discountPeriodStart;
        }

        public void setDiscountPeriodStart(String discountPeriodStart) {
            this.discountPeriodStart = discountPeriodStart;
        }

        public String getDiscountAcceptDate() {
            return discountAcceptDate;
        }

        public void setDiscountAcceptDate(String discountAcceptDate) {
            this.discountAcceptDate = discountAcceptDate;
        }

        public String getDiscountPeriodWork() {
            return discountPeriodWork;
        }

        public void setDiscountPeriodWork(String discountPeriodWork) {
            this.discountPeriodWork = discountPeriodWork;
        }

        public String getBankCost() {
            return bankCost;
        }

        public void setBankCost(String bankCost) {
            this.bankCost = bankCost;
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

        public String getPayAccountBank() {
            return payAccountBank;
        }

        public void setPayAccountBank(String payAccountBank) {
            this.payAccountBank = payAccountBank;
        }

        public String getPayAccountApproach() {
            return payAccountApproach;
        }

        public void setPayAccountApproach(String payAccountApproach) {
            this.payAccountApproach = payAccountApproach;
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

        public String getSpecialAgreement() {
            return specialAgreement;
        }

        public void setSpecialAgreement(String specialAgreement) {
            this.specialAgreement = specialAgreement;
        }

        public String getSignPerson() {
            return signPerson;
        }

        public void setSignPerson(String signPerson) {
            this.signPerson = signPerson;
        }

        public String getSaveUrl() {
            return saveUrl;
        }

        public void setSaveUrl(String saveUrl) {
            this.saveUrl = saveUrl;
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

        public String getYn() {
            return yn;
        }

        public void setYn(String yn) {
            this.yn = yn;
        }

        public String getFassetsId() {
            return fassetsId;
        }

        public void setFassetsId(String fassetsId) {
            this.fassetsId = fassetsId;
        }

        public String getBorgName() {
            return borgName;
        }

        public void setBorgName(String borgName) {
            this.borgName = borgName;
        }

        public String getSorgName() {
            return sorgName;
        }

        public void setSorgName(String sorgName) {
            this.sorgName = sorgName;
        }
    }
}
