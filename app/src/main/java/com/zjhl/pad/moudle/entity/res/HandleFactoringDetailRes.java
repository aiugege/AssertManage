package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: MarketForfaitingSellRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 **/
public class HandleFactoringDetailRes extends ResponseBean {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"acceptanceDate":"2018-05-15","tenderSwift":"rrrr","discountRate":"1.11","reimbursingBankSwift":"yyyyyy","issuingDate":"2018-05-15","openFullName":"eeeee","title":"qqqq","indateMessage":"2018-05-15","creditType":"SKHDS","countryId":"55","tenderFullName":"ttttttt","currency":"CNY","id":275,"recheckState":101,"ST0212":"http://139.199.116.36:8088/group1/M00/00/02/rBUABlr6jBOAOsxbABNV11tEYp4853.jpg","amount":"33333.0","maturity":"2018-07-01","openSwift":"wwwwww","lcNo":"aaaaa","areaId":"1","recheckReason":null,"ST0201":"http://139.199.116.36:8088/group1/M00/00/02/rBUABlr6jDGAdKhkACmILzogSqE981.jpg","debtType":1,"reimbursingBankName":"uuuuuu","ST0202":"上传"}
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
         * acceptanceDate : 2018-05-15
         * tenderSwift : rrrr
         * discountRate : 1.11
         * reimbursingBankSwift : yyyyyy
         * issuingDate : 2018-05-15
         * openFullName : eeeee
         * title : qqqq
         * indateMessage : 2018-05-15
         * creditType : SKHDS
         * countryId : 55
         * tenderFullName : ttttttt
         * currency : CNY
         * id : 275
         * recheckState : 101
         * ST0212 : http://139.199.116.36:8088/group1/M00/00/02/rBUABlr6jBOAOsxbABNV11tEYp4853.jpg
         * amount : 33333.0
         * maturity : 2018-07-01
         * openSwift : wwwwww
         * lcNo : aaaaa
         * areaId : 1
         * recheckReason : null
         * ST0201 : http://139.199.116.36:8088/group1/M00/00/02/rBUABlr6jDGAdKhkACmILzogSqE981.jpg
         * debtType : 1
         * reimbursingBankName : uuuuuu
         * ST0202 : 上传
         */

        private String acceptanceDate;
        private String tenderSwift;
        private String discountRate;
        private String reimbursingBankSwift;
        private String issuingDate;
        private String openFullName;
        private String title;
        private String indateMessage;
        private String creditType;
        private String countryId;
        private String tenderFullName;
        private String currency;
        private int id;
        private String recheckState;
        private String ST0212;
        private String amount;
        private String maturity;
        private String openSwift;
        private String lcNo;
        private String areaId;
        private String recheckReason;
        private String ST0201;
        private String debtType;
        private String reimbursingBankName;
        private String ST0202;

        public String getAcceptanceDate() {
            return acceptanceDate;
        }

        public void setAcceptanceDate(String acceptanceDate) {
            this.acceptanceDate = acceptanceDate;
        }

        public String getTenderSwift() {
            return tenderSwift;
        }

        public void setTenderSwift(String tenderSwift) {
            this.tenderSwift = tenderSwift;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getReimbursingBankSwift() {
            return reimbursingBankSwift;
        }

        public void setReimbursingBankSwift(String reimbursingBankSwift) {
            this.reimbursingBankSwift = reimbursingBankSwift;
        }

        public String getIssuingDate() {
            return issuingDate;
        }

        public void setIssuingDate(String issuingDate) {
            this.issuingDate = issuingDate;
        }

        public String getOpenFullName() {
            return openFullName;
        }

        public void setOpenFullName(String openFullName) {
            this.openFullName = openFullName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIndateMessage() {
            return indateMessage;
        }

        public void setIndateMessage(String indateMessage) {
            this.indateMessage = indateMessage;
        }

        public String getCreditType() {
            return creditType;
        }

        public void setCreditType(String creditType) {
            this.creditType = creditType;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getTenderFullName() {
            return tenderFullName;
        }

        public void setTenderFullName(String tenderFullName) {
            this.tenderFullName = tenderFullName;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRecheckState() {
            return recheckState;
        }

        public void setRecheckState(String recheckState) {
            this.recheckState = recheckState;
        }

        public String getST0212() {
            return ST0212;
        }

        public void setST0212(String ST0212) {
            this.ST0212 = ST0212;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMaturity() {
            return maturity;
        }

        public void setMaturity(String maturity) {
            this.maturity = maturity;
        }

        public String getOpenSwift() {
            return openSwift;
        }

        public void setOpenSwift(String openSwift) {
            this.openSwift = openSwift;
        }

        public String getLcNo() {
            return lcNo;
        }

        public void setLcNo(String lcNo) {
            this.lcNo = lcNo;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getRecheckReason() {
            return recheckReason;
        }

        public void setRecheckReason(String recheckReason) {
            this.recheckReason = recheckReason;
        }

        public String getST0201() {
            return ST0201;
        }

        public void setST0201(String ST0201) {
            this.ST0201 = ST0201;
        }

        public String getDebtType() {
            return debtType;
        }

        public void setDebtType(String debtType) {
            this.debtType = debtType;
        }

        public String getReimbursingBankName() {
            return reimbursingBankName;
        }

        public void setReimbursingBankName(String reimbursingBankName) {
            this.reimbursingBankName = reimbursingBankName;
        }

        public String getST0202() {
            return ST0202;
        }

        public void setST0202(String ST0202) {
            this.ST0202 = ST0202;
        }
    }
}
