package com.zjhl.pad.moudle.entity.res;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.util.List;

/**
 * @desc: MarketForfaitingSellRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 **/
public class MarketForfaitingSellRes extends ResponseBean {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 4
     * totalPage : 1
     * pageSize : 10
     * page : 1
     * data : [{"id":29,"debtType":1,"companyOrgId":null,"ownOrgId":null,"assetsNo":"FFT15245516221114901","trsTimes":0,"title":"资产标题","openSwift":null,"tenderSwift":null,"openFullName":"asfdas","tenderFullName":null,"lcNo":null,"issuingDate":null,"acceptanceDate":null,"maturity":"2018-04-26 00:00:00","amount":11,"reservePrice":null,"currency":"CNY","startInterestDate":null,"creditType":null,"discountRate":5,"inputNum":null,"indateMessage":null,"recheckState":null,"areaId":null,"countryId":null,"exchangeRate":null,"dollar":null,"republishFlag":null,"recheckReason":null,"revokeOrgId":null,"remark":null,"createTime":null,"updateTime":"2018-04-28 15:10:35","yn":null,"residueDay":"0","recheckStateStr":null,"myAssets":0,"sellingState":null,"companyName":null,"sellCompanyName":null,"finishDiscountRate":null,"factoringType":null,"factoringName":null,"countryName":"中国","areaName":"亚洲"},{"id":31,"debtType":1,"companyOrgId":null,"ownOrgId":null,"assetsNo":"FFT15248112423466063","trsTimes":0,"title":"资产标题","openSwift":null,"tenderSwift":null,"openFullName":"asfdas","tenderFullName":null,"lcNo":null,"issuingDate":null,"acceptanceDate":null,"maturity":"2018-05-10 00:00:00","amount":1265425,"reservePrice":null,"currency":"CNY","startInterestDate":null,"creditType":null,"discountRate":5,"inputNum":null,"indateMessage":null,"recheckState":null,"areaId":null,"countryId":null,"exchangeRate":null,"dollar":null,"republishFlag":null,"recheckReason":null,"revokeOrgId":null,"remark":null,"createTime":null,"updateTime":"2018-04-28 15:10:31","yn":null,"residueDay":"12","recheckStateStr":null,"myAssets":0,"sellingState":null,"companyName":null,"sellCompanyName":null,"finishDiscountRate":null,"factoringType":null,"factoringName":null,"countryName":"中国","areaName":"亚洲"},{"id":32,"debtType":1,"companyOrgId":null,"ownOrgId":null,"assetsNo":"FFT15248191711977564","trsTimes":0,"title":"资产标题","openSwift":null,"tenderSwift":null,"openFullName":"asfdas","tenderFullName":null,"lcNo":null,"issuingDate":null,"acceptanceDate":null,"maturity":"2018-05-10 00:00:00","amount":1265425,"reservePrice":null,"currency":"CNY","startInterestDate":null,"creditType":null,"discountRate":5,"inputNum":null,"indateMessage":null,"recheckState":null,"areaId":null,"countryId":null,"exchangeRate":null,"dollar":null,"republishFlag":null,"recheckReason":null,"revokeOrgId":null,"remark":null,"createTime":null,"updateTime":"2018-04-28 15:10:30","yn":null,"residueDay":"12","recheckStateStr":null,"myAssets":0,"sellingState":null,"companyName":null,"sellCompanyName":null,"finishDiscountRate":null,"factoringType":null,"factoringName":null,"countryName":"中国","areaName":"亚洲"},{"id":33,"debtType":1,"companyOrgId":null,"ownOrgId":null,"assetsNo":"FFT15238723748302386","trsTimes":0,"title":"测试福费廷资产001","openSwift":null,"tenderSwift":null,"openFullName":"中国银行","tenderFullName":null,"lcNo":null,"issuingDate":null,"acceptanceDate":null,"maturity":"2018-05-10 00:00:00","amount":999999,"reservePrice":999999,"currency":"CNY","startInterestDate":null,"creditType":null,"discountRate":1.1,"inputNum":null,"indateMessage":null,"recheckState":null,"areaId":null,"countryId":null,"exchangeRate":null,"dollar":null,"republishFlag":null,"recheckReason":null,"revokeOrgId":null,"remark":null,"createTime":null,"updateTime":"2018-04-28 15:10:29","yn":null,"residueDay":"12","recheckStateStr":null,"myAssets":0,"sellingState":null,"companyName":null,"sellCompanyName":null,"finishDiscountRate":null,"factoringType":null,"factoringName":null,"countryName":"中国","areaName":"亚洲"}]
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
         * id : 29
         * debtType : 1
         * companyOrgId : null
         * ownOrgId : null
         * assetsNo : FFT15245516221114901
         * trsTimes : 0
         * title : 资产标题
         * openSwift : null
         * tenderSwift : null
         * openFullName : asfdas
         * tenderFullName : null
         * lcNo : null
         * issuingDate : null
         * acceptanceDate : null
         * maturity : 2018-04-26 00:00:00
         * amount : 11
         * reservePrice : null
         * currency : CNY
         * startInterestDate : null
         * creditType : null
         * discountRate : 5
         * inputNum : null
         * indateMessage : null
         * recheckState : null
         * areaId : null
         * countryId : null
         * exchangeRate : null
         * dollar : null
         * republishFlag : null
         * recheckReason : null
         * revokeOrgId : null
         * remark : null
         * createTime : null
         * updateTime : 2018-04-28 15:10:35
         * yn : null
         * residueDay : 0
         * recheckStateStr : null
         * myAssets : 0
         * sellingState : null
         * companyName : null
         * sellCompanyName : null
         * finishDiscountRate : null
         * factoringType : null
         * factoringName : null
         * countryName : 中国
         * areaName : 亚洲
         */

        private int id;
        private String debtType;
        private String companyOrgId;
        private String ownOrgId;
        private String assetsNo;
        private String trsTimes;
        private String title;
        private String openSwift;
        private String tenderSwift;
        private String openFullName;
        private String tenderFullName;
        private String lcNo;
        private String issuingDate;
        private String acceptanceDate;
        private String maturity;
        private String amount;
        private String reservePrice;
        private String currency;
        private String startInterestDate;
        private String creditType;
        private String discountRate;
        private String inputNum;
        private String indateMessage;
        private String recheckState;
        private String areaId;
        private String countryId;
        private String exchangeRate;
        private String dollar;
        private String republishFlag;
        private String recheckReason;
        private String revokeOrgId;
        private String remark;
        private String createTime;
        private String updateTime;
        private String yn;
        private String residueDay;
        private String recheckStateStr;
        private String myAssets;
        private String sellingState;
        private String companyName;
        private String sellCompanyName;
        private String finishDiscountRate;
        private String factoringType;
        private String factoringName;
        private String countryName;
        private String areaName;
        private String isnBlockChain;
        private String countryNameEn;
        private String areaNameEn;

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

        public String getIsnBlockChain() {
            return isnBlockChain;
        }

        public void setIsnBlockChain(String isnBlockChain) {
            this.isnBlockChain = isnBlockChain;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDebtType() {
            return debtType;
        }

        public void setDebtType(String debtType) {
            this.debtType = debtType;
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

        public String getAssetsNo() {
            return assetsNo;
        }

        public void setAssetsNo(String assetsNo) {
            this.assetsNo = assetsNo;
        }

        public String getTrsTimes() {
            return trsTimes;
        }

        public void setTrsTimes(String trsTimes) {
            this.trsTimes = trsTimes;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOpenSwift() {
            return openSwift;
        }

        public void setOpenSwift(String openSwift) {
            this.openSwift = openSwift;
        }

        public String getTenderSwift() {
            return tenderSwift;
        }

        public void setTenderSwift(String tenderSwift) {
            this.tenderSwift = tenderSwift;
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

        public String getLcNo() {
            return lcNo;
        }

        public void setLcNo(String lcNo) {
            this.lcNo = lcNo;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getReservePrice() {
            return reservePrice;
        }

        public void setReservePrice(String reservePrice) {
            this.reservePrice = reservePrice;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getStartInterestDate() {
            return startInterestDate;
        }

        public void setStartInterestDate(String startInterestDate) {
            this.startInterestDate = startInterestDate;
        }

        public String getCreditType() {
            return creditType;
        }

        public void setCreditType(String creditType) {
            this.creditType = creditType;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getInputNum() {
            return inputNum;
        }

        public void setInputNum(String inputNum) {
            this.inputNum = inputNum;
        }

        public String getIndateMessage() {
            return indateMessage;
        }

        public void setIndateMessage(String indateMessage) {
            this.indateMessage = indateMessage;
        }

        public String getRecheckState() {
            return recheckState;
        }

        public void setRecheckState(String recheckState) {
            this.recheckState = recheckState;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getDollar() {
            return dollar;
        }

        public void setDollar(String dollar) {
            this.dollar = dollar;
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

        public String getRevokeOrgId() {
            return revokeOrgId;
        }

        public void setRevokeOrgId(String revokeOrgId) {
            this.revokeOrgId = revokeOrgId;
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

        public String getResidueDay() {
            return residueDay;
        }

        public void setResidueDay(String residueDay) {
            this.residueDay = residueDay;
        }

        public String getRecheckStateStr() {
            return recheckStateStr;
        }

        public void setRecheckStateStr(String recheckStateStr) {
            this.recheckStateStr = recheckStateStr;
        }

        public String getMyAssets() {
            return myAssets;
        }

        public void setMyAssets(String myAssets) {
            this.myAssets = myAssets;
        }

        public String getSellingState() {
            return sellingState;
        }

        public void setSellingState(String sellingState) {
            this.sellingState = sellingState;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getSellCompanyName() {
            return sellCompanyName;
        }

        public void setSellCompanyName(String sellCompanyName) {
            this.sellCompanyName = sellCompanyName;
        }

        public String getFinishDiscountRate() {
            return finishDiscountRate;
        }

        public void setFinishDiscountRate(String finishDiscountRate) {
            this.finishDiscountRate = finishDiscountRate;
        }

        public String getFactoringType() {
            return factoringType;
        }

        public void setFactoringType(String factoringType) {
            this.factoringType = factoringType;
        }

        public String getFactoringName() {
            return factoringName;
        }

        public void setFactoringName(String factoringName) {
            this.factoringName = factoringName;
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
    }
}
