package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: RegisterReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class RegisterReq {

    /**
     * companyTypeId1 : 1
     * companyTypeId2 : 2
     * countryId : 1
     * companyName : 中金汇理
     * contactName : 张三
     * phone : 18700000006
     * email : 946952535@qq.com
     * inviteCode : 1
     * accept : 1
     * identifyingCode : 3219
     * expireDate : 60
     * imgCode : Q7D2
     * imgCodeId : 201804181234
     */

    private String companyTypeId1;
    private String companyTypeId2;
    private String countryId;
    private String companyName;
    private String contactName;
    private String phone;
    private String email;
    private String inviteCode;
    private String accept;
    private String identifyingCode;
    private int expireDate;
    private String imgCode;
    private String imgCodeId;
    private String areaId;
    private String companyDomicile;

    public String getCompanyDomicile() {
        return companyDomicile;
    }

    public void setCompanyDomicile(String companyDomicile) {
        this.companyDomicile = companyDomicile;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCompanyTypeId1() {
        return companyTypeId1;
    }

    public void setCompanyTypeId1(String companyTypeId1) {
        this.companyTypeId1 = companyTypeId1;
    }

    public String getCompanyTypeId2() {
        return companyTypeId2;
    }

    public void setCompanyTypeId2(String companyTypeId2) {
        this.companyTypeId2 = companyTypeId2;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getIdentifyingCode() {
        return identifyingCode;
    }

    public void setIdentifyingCode(String identifyingCode) {
        this.identifyingCode = identifyingCode;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public String getImgCodeId() {
        return imgCodeId;
    }

    public void setImgCodeId(String imgCodeId) {
        this.imgCodeId = imgCodeId;
    }
}
