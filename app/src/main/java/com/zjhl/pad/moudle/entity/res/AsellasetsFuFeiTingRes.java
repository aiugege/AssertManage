package com.zjhl.pad.moudle.entity.res;

import java.io.Serializable;
import java.util.List;

/*
* File: AsellasetsFuFeiTingRes.java 福费廷
* Author: DELL 
* Version: V1.0
* Create: 2018/5/23 16:48 
* Changes (from 2018/5/23) 
*/
public class AsellasetsFuFeiTingRes implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 1
     * totalPage : 1
     * pageSize : 3
     * page : 1
     * data : [{"id":1,"assetsNo":"FFT15238723748302386","title":"石油","amount":9700,"currency":"CNY","discountRate":1.1,"debtType":1,"lcNo":"10","creditType":"信用证类型","openSwift":"BKCHCNBJ110","openFullName":"中国银行","tenderSwift":"BKCHCNBJ110","tenderFullName":"北京银行","reimbursingBankSwift":null,"reimbursingBankName":null,"issuingDate":"2018-03-29 ","acceptanceDate":"2018-04-12 ","maturity":"2018-05-10 ","indateMessage":"2018-04-06 ","countryName":"中国","areaName":"亚洲","recheckState":101,"republishFlag":0,"recheckReason":"","companyOrgId":2012,"ownOrgId":1,"pollicitaUrl":"http://139.199.116.36:8088/group1/M00/00/01/rBUABlr5JLSAcuX6AAU7FottTCk785.pdf","myAssets":0}]
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
         * id : 1
         * assetsNo : FFT15238723748302386
         * title : 石油
         * amount : 9700
         * currency : CNY
         * discountRate : 1.1
         * debtType : 1
         * lcNo : 10
         * creditType : 信用证类型
         * openSwift : BKCHCNBJ110
         * openFullName : 中国银行
         * tenderSwift : BKCHCNBJ110
         * tenderFullName : 北京银行
         * reimbursingBankSwift : null
         * reimbursingBankName : null
         * issuingDate : 2018-03-29
         * acceptanceDate : 2018-04-12
         * maturity : 2018-05-10
         * indateMessage : 2018-04-06
         * countryName : 中国
         * areaName : 亚洲
         * recheckState : 101
         * republishFlag : 0
         * recheckReason :
         * companyOrgId : 2012
         * ownOrgId : 1
         * pollicitaUrl : http://139.199.116.36:8088/group1/M00/00/01/rBUABlr5JLSAcuX6AAU7FottTCk785.pdf
         * myAssets : 0
         */

        private String id;
        private String assetsNo;
        private String title;
        private String amount;
        private String currency;
        private String discountRate;
        private String debtType;
        private String lcNo;
        private String creditType;
        private String openSwift;
        private String openFullName;
        private String tenderSwift;
        private String tenderFullName;
        private String reimbursingBankSwift;
        private String reimbursingBankName;
        private String issuingDate;
        private String acceptanceDate;
        private String maturity;
        private String indateMessage;
        private String countryName;
        private String areaName;
        private String recheckStateBefore;
        private String recheckState;
        private String republishFlag;
        private String recheckReason;
        private String companyOrgId;
        private String ownOrgId;
        private String pollicitaUrl;
        private String myAssets;
        private String trsTimes;
        private String yn;
        private String countryNameEn;
        private String areaNameEn;
        private String enAreaName;
        private String enCountryName;

        public String getEnAreaName() {
            return enAreaName;
        }

        public void setEnAreaName(String enAreaName) {
            this.enAreaName = enAreaName;
        }

        public String getEnCountryName() {
            return enCountryName;
        }

        public void setEnCountryName(String enCountryName) {
            this.enCountryName = enCountryName;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAssetsNo() {
            return assetsNo;
        }

        public void setAssetsNo(String assetsNo) {
            this.assetsNo = assetsNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getDebtType() {
            return debtType;
        }

        public void setDebtType(String debtType) {
            this.debtType = debtType;
        }

        public String getRecheckStateBefore() {
            return recheckStateBefore;
        }

        public void setRecheckStateBefore(String recheckStateBefore) {
            this.recheckStateBefore = recheckStateBefore;
        }

        public String getLcNo() {
            return lcNo;
        }

        public void setLcNo(String lcNo) {
            this.lcNo = lcNo;
        }

        public String getCreditType() {
            return creditType;
        }

        public void setCreditType(String creditType) {
            this.creditType = creditType;
        }

        public String getOpenSwift() {
            return openSwift;
        }

        public void setOpenSwift(String openSwift) {
            this.openSwift = openSwift;
        }

        public String getOpenFullName() {
            return openFullName;
        }

        public void setOpenFullName(String openFullName) {
            this.openFullName = openFullName;
        }

        public String getTenderSwift() {
            return tenderSwift;
        }

        public void setTenderSwift(String tenderSwift) {
            this.tenderSwift = tenderSwift;
        }

        public String getTenderFullName() {
            return tenderFullName;
        }

        public void setTenderFullName(String tenderFullName) {
            this.tenderFullName = tenderFullName;
        }

        public String getReimbursingBankSwift() {
            return reimbursingBankSwift;
        }

        public void setReimbursingBankSwift(String reimbursingBankSwift) {
            this.reimbursingBankSwift = reimbursingBankSwift;
        }

        public String getReimbursingBankName() {
            return reimbursingBankName;
        }

        public void setReimbursingBankName(String reimbursingBankName) {
            this.reimbursingBankName = reimbursingBankName;
        }

        public String getIssuingDate() {
            return issuingDate;
        }

        public void setIssuingDate(String issuingDate) {
            this.issuingDate = issuingDate;
        }

        public String getAcceptanceDate() {
            return acceptanceDate;
        }

        public void setAcceptanceDate(String acceptanceDate) {
            this.acceptanceDate = acceptanceDate;
        }

        public String getMaturity() {
            return maturity;
        }

        public void setMaturity(String maturity) {
            this.maturity = maturity;
        }

        public String getIndateMessage() {
            return indateMessage;
        }

        public void setIndateMessage(String indateMessage) {
            this.indateMessage = indateMessage;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getRecheckState() {
            return recheckState;
        }

        public void setRecheckState(String recheckState) {
            this.recheckState = recheckState;
        }

        public String getRepublishFlag() {
            return republishFlag;
        }

        public void setRepublishFlag(String republishFlag) {
            this.republishFlag = republishFlag;
        }

        public String getRecheckReason() {
            return recheckReason;
        }

        public void setRecheckReason(String recheckReason) {
            this.recheckReason = recheckReason;
        }

        public String getCompanyOrgId() {
            return companyOrgId;
        }

        public void setCompanyOrgId(String companyOrgId) {
            this.companyOrgId = companyOrgId;
        }

        public String getOwnOrgId() {
            return ownOrgId;
        }

        public void setOwnOrgId(String ownOrgId) {
            this.ownOrgId = ownOrgId;
        }

        public String getPollicitaUrl() {
            return pollicitaUrl;
        }

        public void setPollicitaUrl(String pollicitaUrl) {
            this.pollicitaUrl = pollicitaUrl;
        }

        public String getMyAssets() {
            return myAssets;
        }

        public void setMyAssets(String myAssets) {
            this.myAssets = myAssets;
        }

        public String getTrsTimes() {
            return trsTimes;
        }

        public void setTrsTimes(String trsTimes) {
            this.trsTimes = trsTimes;
        }
    }

    @Override
    public String toString() {
        return "AsellasetsFuFeiTingRes{" +
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
