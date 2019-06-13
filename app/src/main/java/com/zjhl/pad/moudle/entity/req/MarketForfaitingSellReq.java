package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: MarketForfaitingSellRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 **/
public class MarketForfaitingSellReq {
    /**
     * page : 1
     * pageSize : 5
     * direction : 1
     * currency : CNY
     * amountStart : 50000
     * amountEnd : 60000
     * limitDate : 1
     * countryIds : 1,2,3
     * openFullName : abc
     * keyword : abc
     * trsTimes : 3
     * areaId : 1
     * countryId : 1
     * ascDesc : asc
     * orderBy :  amount
     */
/*
"page": 1 ,    //当前页        必输
		"pageSize": 5,   //页数       必输
	     "currency"： "CNY"， //币种
     "amountStart"; 50000,    //金额开始”
      "amountEnd"; 60000,   //金额结束”
       "limitDate"; 1       //资产期限 （1~12）  月为单位
       "countryIds": "1,2,3",  //多个国家id用英文逗号隔开
      "openFullName" ; "abc" //开证行
"keyword":"abc",//模糊搜索关键字”
"trsTimes":3,     //交易次数
"areaId":"1",      //区域id
"countryId":"1",   //国家id
"ascDesc":"asc",    // 排序方式   asc 升序   desc 降序
"orderBy"："amount" //排序字段
"maturity", "资产到期日"
"amount", "金额"
"reserve_price","底价"

 */
    private int page;
    private int pageSize;
    private int direction;
    private String currency;
    private String amountStart;
    private String amountEnd;
    private String limitDate;
    private String countryIds;
    private String openFullName;
    private String keyword;
    private String trsTimes;
    private String areaId;
    private String countryId;
    private String ascDesc;
    private String orderBy;
    private String debtType;
    private String maturity;
    private String amount;
    private String reserve_price;

    public String getDebtType() {
        return debtType;
    }

    public void setDebtType(String debtType) {
        this.debtType = debtType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmountStart() {
        return amountStart;
    }

    public void setAmountStart(String amountStart) {
        this.amountStart = amountStart;
    }

    public String getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(String amountEnd) {
        this.amountEnd = amountEnd;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(String countryIds) {
        this.countryIds = countryIds;
    }

    public String getOpenFullName() {
        return openFullName;
    }

    public void setOpenFullName(String openFullName) {
        this.openFullName = openFullName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTrsTimes() {
        return trsTimes;
    }

    public void setTrsTimes(String trsTimes) {
        this.trsTimes = trsTimes;
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

    public String getAscDesc() {
        return ascDesc;
    }

    public void setAscDesc(String ascDesc) {
        this.ascDesc = ascDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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

    public String getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(String reserve_price) {
        this.reserve_price = reserve_price;
    }
}
