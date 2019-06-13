package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: MyOfferFufei.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/18 11:11 
* Changes (from 2018/5/18) 
*/
public class MyOfferFufei implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 1
     * totalPage : 1
     * pageSize : 10
     * page : 1
     * data : [{"id":34,"title":"浙江生屋科技股权信息002","assetsNo":"FFT15263549596981574","amount":89563,"currency":"CNY","maturity":"2018-07-10","openFullName":"中国银行总行","debtType":1,"discountRate":4.8,"assetsDiscountRate":5,"discountRateOld":0,"recheckState":"104","priceState":"105","dayNum":0,"updateTranLetter":0,"rejectOpinion":"拒绝","countryName":"中国","indateMessage":"2018-06-10 00:00:00","ownOrgId":0}]
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
         * id : 34
         * title : 浙江生屋科技股权信息002
         * assetsNo : FFT15263549596981574
         * amount : 89563
         * currency : CNY
         * maturity : 2018-07-10
         * openFullName : 中国银行总行
         * debtType : 1
         * discountRate : 4.8
         * assetsDiscountRate : 5
         * discountRateOld : 0
         * recheckState : 104
         * priceState : 105
         * dayNum : 0
         * updateTranLetter : 0
         * rejectOpinion : 拒绝
         * countryName : 中国
         * indateMessage : 2018-06-10 00:00:00
         * ownOrgId : 0
         */

        private int id;
        private String title;
        private String assetsNo;
        private String amount;
        private String currency;
        private String maturity;
        private String openFullName;
        private String debtType;
        private String discountRate;
        private String oldDiscountRate;
        private String assetsDiscountRate;
        private String discountRateOld;
        private String recheckState;
        private String priceState;
        private String dayNum;
        private int updateTranLetter;
        private String rejectOpinion;
        private String countryName;
        private String indateMessage;
        private int ownOrgId;
        private String priceId;
        private String isnBlockChain;
        private String republishFlag;
        private String yn;
        private String countryNameEn;
        private String areaNameEn;
        private String enCountryName;
        private String enAreaName;
        private String priceReason;

        public String getPriceReason() {
            return priceReason;
        }

        public void setPriceReason(String priceReason) {
            this.priceReason = priceReason;
        }

        public String getEnCountryName() {
            return enCountryName;
        }

        public void setEnCountryName(String enCountryName) {
            this.enCountryName = enCountryName;
        }

        public String getEnAreaName() {
            return enAreaName;
        }

        public void setEnAreaName(String enAreaName) {
            this.enAreaName = enAreaName;
        }

        public String getCountryNameEn() {
            return countryNameEn;
        }

        public void setCountryNameEn(String countryNameEn) {
            this.countryNameEn = countryNameEn;
        }

        public String getAreaNameEn() {
            return areaNameEn;
        }

        public void setAreaNameEn(String areaNameEn) {
            this.areaNameEn = areaNameEn;
        }
        public String getYn() {
            return yn;
        }

        public void setYn(String yn) {
            this.yn = yn;
        }

        public String getRepublishFlag() {
            return republishFlag;
        }

        public void setRepublishFlag(String republishFlag) {
            this.republishFlag = republishFlag;
        }

        public String getIsnBlockChain() {
            return isnBlockChain;
        }

        public void setIsnBlockChain(String isnBlockChain) {
            this.isnBlockChain = isnBlockChain;
        }

        public String getOldDiscountRate() {
            return oldDiscountRate;
        }

        public void setOldDiscountRate(String oldDiscountRate) {
            this.oldDiscountRate = oldDiscountRate;
        }

        public String getPriceId() {
            return priceId;
        }

        public void setPriceId(String priceId) {
            this.priceId = priceId;
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

        public String getAssetsNo() {
            return assetsNo;
        }

        public void setAssetsNo(String assetsNo) {
            this.assetsNo = assetsNo;
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

        public String getMaturity() {
            return maturity;
        }

        public void setMaturity(String maturity) {
            this.maturity = maturity;
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

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getAssetsDiscountRate() {
            return assetsDiscountRate;
        }

        public void setAssetsDiscountRate(String assetsDiscountRate) {
            this.assetsDiscountRate = assetsDiscountRate;
        }

        public String getDiscountRateOld() {
            return discountRateOld;
        }

        public void setDiscountRateOld(String discountRateOld) {
            this.discountRateOld = discountRateOld;
        }

        public String getRecheckState() {
            return recheckState;
        }

        public void setRecheckState(String recheckState) {
            this.recheckState = recheckState;
        }

        public String getPriceState() {
            return priceState;
        }

        public void setPriceState(String priceState) {
            this.priceState = priceState;
        }

        public String getDayNum() {
            return dayNum;
        }

        public void setDayNum(String dayNum) {
            this.dayNum = dayNum;
        }

        public int getUpdateTranLetter() {
            return updateTranLetter;
        }

        public void setUpdateTranLetter(int updateTranLetter) {
            this.updateTranLetter = updateTranLetter;
        }

        public String getRejectOpinion() {
            return rejectOpinion;
        }

        public void setRejectOpinion(String rejectOpinion) {
            this.rejectOpinion = rejectOpinion;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getIndateMessage() {
            return indateMessage;
        }

        public void setIndateMessage(String indateMessage) {
            this.indateMessage = indateMessage;
        }

        public int getOwnOrgId() {
            return ownOrgId;
        }

        public void setOwnOrgId(int ownOrgId) {
            this.ownOrgId = ownOrgId;
        }
    }

    @Override
    public String toString() {
        return "MyOfferFufei{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", json=" + json +
                ", list=" + list +
                ", data=" + data +
                '}';
    }
}
