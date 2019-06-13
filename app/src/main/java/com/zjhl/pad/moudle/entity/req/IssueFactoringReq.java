package com.zjhl.pad.moudle.entity.req;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @desc: IssueFactoringReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/8 20:16
 * @projectname: nnkj
 **/
public class IssueFactoringReq implements Serializable {

    /**
     * id : 9
     * companyOrgId : 2012
     * userType : 2
     * ST-02-04 : 111
     * ST-02-05 : 222
     * ST-02-06 : 333
     * ST-02-07 : 444
     * ST-02-08 : 555
     * ST-02-09 : 666
     * factoringName : sqc1
     * countryId : 4561
     * areaId : 1231
     * commitType : 2
     * seller : sqc1
     * factoringType : 1
     * currency : cny1
     * amount : 10000
     * transferRate : 1.21
     * maturity : 2018-1-1 00:00:00
     * indateMessage : 2018-1-1 00:00:00
     * originators : sqc
     * obligors : sqc
     * isCove : 1
     * insurer : sqc
     */

    private String id;
    private String companyOrgId;
    private String userType;
    @SerializedName("ST0204")
    private String ST0204;
    @SerializedName("ST0205")
    private String ST0205;
    @SerializedName("ST0206")
    private String ST0206;
    @SerializedName("ST0207")
    private String ST0207;
    @SerializedName("ST0208")
    private String ST0208;
    @SerializedName("ST0209")
    private String ST0209;
    private String factoringName;
    private String countryId;
    private String areaId;
    private String commitType;
    private String seller;
    private String factoringType;
    private String currency;
    private String amount;
    private String transferRate;
    private String maturity;
    private String indateMessage;
    private String originators;
    private String obligors;
    private String isCove;
    private String insurer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyOrgId() {
        return companyOrgId;
    }

    public void setCompanyOrgId(String companyOrgId) {
        this.companyOrgId = companyOrgId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getST0204() {
        return ST0204;
    }

    public void setST0204(String ST0204) {
        this.ST0204 = ST0204;
    }

    public String getST0205() {
        return ST0205;
    }

    public void setST0205(String ST0205) {
        this.ST0205 = ST0205;
    }

    public String getST0206() {
        return ST0206;
    }

    public void setST0206(String ST0206) {
        this.ST0206 = ST0206;
    }

    public String getST0207() {
        return ST0207;
    }

    public void setST0207(String ST0207) {
        this.ST0207 = ST0207;
    }

    public String getST0208() {
        return ST0208;
    }

    public void setST0208(String ST0208) {
        this.ST0208 = ST0208;
    }

    public String getST0209() {
        return ST0209;
    }

    public void setST0209(String ST0209) {
        this.ST0209 = ST0209;
    }

    public String getFactoringName() {
        return factoringName;
    }

    public void setFactoringName(String factoringName) {
        this.factoringName = factoringName;
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

    public String getCommitType() {
        return commitType;
    }

    public void setCommitType(String commitType) {
        this.commitType = commitType;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(String transferRate) {
        this.transferRate = transferRate;
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

    public String getOriginators() {
        return originators;
    }

    public void setOriginators(String originators) {
        this.originators = originators;
    }

    public String getObligors() {
        return obligors;
    }

    public void setObligors(String obligors) {
        this.obligors = obligors;
    }

    public String getIsCove() {
        return isCove;
    }

    public void setIsCove(String isCove) {
        this.isCove = isCove;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    @Override
    public String toString() {
        return "IssueFactoringReq{" +
                "id=" + id +
                ", companyOrgId=" + companyOrgId +
                ", userType=" + userType +
                ", ST0204='" + ST0204 + '\'' +
                ", ST0205='" + ST0205 + '\'' +
                ", ST0206='" + ST0206 + '\'' +
                ", ST0207='" + ST0207 + '\'' +
                ", ST0208='" + ST0208 + '\'' +
                ", ST0209='" + ST0209 + '\'' +
                ", factoringName='" + factoringName + '\'' +
                ", countryId=" + countryId +
                ", areaId=" + areaId +
                ", commitType=" + commitType +
                ", seller='" + seller + '\'' +
                ", factoringType=" + factoringType +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", transferRate=" + transferRate +
                ", maturity='" + maturity + '\'' +
                ", indateMessage='" + indateMessage + '\'' +
                ", originators='" + originators + '\'' +
                ", obligors='" + obligors + '\'' +
                ", isCove='" + isCove + '\'' +
                ", insurer='" + insurer + '\'' +
                '}';
    }
}
