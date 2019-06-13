package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: MarketForfaitingSellRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/28 14:17
 * @projectname: nnkj
 **/
public class MarketFactoringSellReq {

    /**
     * page : 1
     * pageSize : 5
     * direction : 1
     * factoringType : 2
     * currency : USD
     * amountStart : 1000
     * amountEnd : 1600
     * limitDate : 12
     * trsTimes : 3
     * countryIds : 1,2,3
     * countryId : 1
     * areaId : 1
     * seller : 1
     * factoringName : zh
     * ranking : 1
     * AscDesc : asc
     * orderBy : amount
     */
/*
 "page":  1,    //当前页    必输
		"pageSize":5,   //每页条数  必输
	"factoringType":"2",  //保理类型  1 单保理、明保理 2 单保理、暗保理  3、            双保理、明保理  4、双保理、暗保理' ,
		"currency":"USD",   //币种
		"amountStart":1000,   //金额 开始
		"amountEnd":1600,    //金额 结束
		"limitDate":12,       //资产期限    月份为单位
		"trsTimes":"3",       //交易次数
		"countryIds":"1,2,3",    //城市 多选
		"countryId":"1",      //城市 单选
		"areaId":"1",        //地区
		"seller":"1",         //资产卖出方
		"factoringName":"zh",    //  融资商名称
		"ranking":"1"           //融资商评级
       "AscDesc":"asc",    //排序方式， 升序降序
		"orderBy":"amount"   //排序字段

 */
    private int page;
    private int pageSize;
    private int direction;
    private String factoringType;
    private String currency;
    private String amountStart;
    private String amountEnd;
    private String limitDate;
    private String trsTimes;
    private String countryIds;
    private String countryId;
    private String areaId;
    private String seller;
    private String factoringName;
    private String ranking;
    private String AscDesc;
    private String orderBy;

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

    public String getFactoringType() {
        return factoringType;
    }

    public void setFactoringType(String factoringType) {
        this.factoringType = factoringType;
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

    public String getTrsTimes() {
        return trsTimes;
    }

    public void setTrsTimes(String trsTimes) {
        this.trsTimes = trsTimes;
    }

    public String getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(String countryIds) {
        this.countryIds = countryIds;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getFactoringName() {
        return factoringName;
    }

    public void setFactoringName(String factoringName) {
        this.factoringName = factoringName;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getAscDesc() {
        return AscDesc;
    }

    public void setAscDesc(String AscDesc) {
        this.AscDesc = AscDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
