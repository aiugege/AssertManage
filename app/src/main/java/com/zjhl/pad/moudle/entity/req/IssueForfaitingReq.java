package com.zjhl.pad.moudle.entity.req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @desc: IssueForfaitingReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/3 17:26
 * @projectname: nnkj
 **/
public class IssueForfaitingReq implements Serializable {

    /**
     * {
     * "title": "资产标题",
     * "ST-02-12":"sjd/sd.dl",/ 信用证承兑电文件路径，多个路径使用英文";"分割 /
     * "ST-02-01":"sjd/sd.dl",/ 信用证照片路径，多个路径使用英文";"分割 /
     * "debtType": 1,/ 债权类型（福费廷类型）：1 国内福费廷、2 国际福费廷  / 必输
     * "openSwift":"slkjflkas",/ 开证行SWIFT码 / 必输
     * "openFullName":"asfdas",/ 开证行全名 /
     * "tenderSwift":"adfasdf",/ 承兑行SWIFT码 /
     * "tenderFullName":"afdsad",/ 承兑行全名 /
     * "reimbursingBankSwift":"",/ 偿付行SWIFT码 /
     * "reimbursingBankName":"",/ 偿付行全名 /
     * "amount":1265425,/ 承兑金额 /
     * "currency":"CNY",/ 承兑币种 /
     * <p>
     * "lcNo":"asdas16546162",/ 信用证编号 / 必输
     * "issuingDate":"2018-02-10",/ 信用证签发日期 /
     * "maturity":"2018-05-10",/ 资产到期日 /
     * "acceptanceDate":"2018-02-10",/ 承兑日期 /
     * "creditType":"SKHDS",/ 信用证类型 /
     * "discountRate":5.0,/ 贴现率 /
     * "indateMessage":"2018-05-10",/ 信息有效期 /
     * "ST-02-02":"lkdjlad/sdfsj/sdfsj/ss.sql",/ 福费廷协议 /
     * "areaId":"1",/ 地区ID /
     * "countryId":"1",/ 国家ID /
     * "commitType":"1"/ 提交类型：1保存，2 提交/
     * }
     * title : 资产标题
     * ST-02-12 : sjd/sd.dl
     * ST-02-01 : sjd/sd.dl
     * debtType : 1
     * openSwift : slkjflkas
     * openFullName : asfdas
     * tenderSwift : adfasdf
     * tenderFullName : afdsad
     * reimbursingBankSwift :
     * reimbursingBankName :
     * amount : 1265425
     * currency : CNY
     * lcNo : asdas16546162
     * issuingDate : 2018-02-10
     * maturity : 2018-05-10
     * acceptanceDate : 2018-02-10
     * creditType : SKHDS
     * discountRate : 5
     * indateMessage : 2018-05-10
     * ST-02-02 : lkdjlad/sdfsj/sdfsj/ss.sql
     * areaId : 1
     * countryId : 1
     * commitType : 1
     */

    private String id;
    private String title;
    @SerializedName("ST0212")
    private String ST0212;
    @SerializedName("ST0201")
    private String ST0201;
    private int debtType;
    private String openSwift;
    private String openFullName;
    private String tenderSwift;
    private String tenderFullName;
    private String reimbursingBankSwift;
    private String reimbursingBankName;
    private String amount;
    private String currency;
    private String lcNo;
    private String issuingDate;
    private String maturity;
    private String acceptanceDate;
    private String creditType;
    private String discountRate;
    private String indateMessage;
    @SerializedName("ST0202")
    private String ST0202;
    private String areaId;
    private String countryId;
    private String commitType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getST0212() {
        return ST0212;
    }

    public void setST0212(String ST0212) {
        this.ST0212 = ST0212;
    }

    public String getST0201() {
        return ST0201;
    }

    public void setST0201(String ST0201) {
        this.ST0201 = ST0201;
    }

    public int getDebtType() {
        return debtType;
    }

    public void setDebtType(int debtType) {
        this.debtType = debtType;
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

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
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

    public String getIndateMessage() {
        return indateMessage;
    }

    public void setIndateMessage(String indateMessage) {
        this.indateMessage = indateMessage;
    }

    public String getST0202() {
        return ST0202;
    }

    public void setST0202(String ST0202) {
        this.ST0202 = ST0202;
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

    public String getCommitType() {
        return commitType;
    }

    public void setCommitType(String commitType) {
        this.commitType = commitType;
    }

    @Override
    public String toString() {
        return "IssueForfaitingReq{" +
                "title='" + title + '\'' +
                ", ST0212='" + ST0212 + '\'' +
                ", ST0201='" + ST0201 + '\'' +
                ", debtType=" + debtType +
                ", openSwift='" + openSwift + '\'' +
                ", openFullName='" + openFullName + '\'' +
                ", tenderSwift='" + tenderSwift + '\'' +
                ", tenderFullName='" + tenderFullName + '\'' +
                ", reimbursingBankSwift='" + reimbursingBankSwift + '\'' +
                ", reimbursingBankName='" + reimbursingBankName + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", lcNo='" + lcNo + '\'' +
                ", issuingDate='" + issuingDate + '\'' +
                ", maturity='" + maturity + '\'' +
                ", acceptanceDate='" + acceptanceDate + '\'' +
                ", creditType='" + creditType + '\'' +
                ", discountRate='" + discountRate + '\'' +
                ", indateMessage='" + indateMessage + '\'' +
                ", ST0202='" + ST0202 + '\'' +
                ", areaId='" + areaId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", commitType='" + commitType + '\'' +
                '}';
    }
}
