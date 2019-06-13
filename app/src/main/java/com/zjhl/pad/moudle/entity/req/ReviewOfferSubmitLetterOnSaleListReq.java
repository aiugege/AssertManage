package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: ReviewOfferSubmitLetterOnSaleListReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/5/17 11:09
 * @projectname: nnkj
 **/
public class ReviewOfferSubmitLetterOnSaleListReq implements Serializable {


    /**
     * id : 23
     * creditApp : 张大大
     * creditBeneficiary : 张小小
     * basicTransaction : 私募基金
     * interestRate : 预收
     * fee : 25.23
     * invoiceAmount : 25.23
     * grace : 8
     * discountPeriodDay : 8
     * discountPeriodStart : 2018-12-15
     * discountAcceptDate : 2019-10-02
     * discountPeriodWork : 10
     * bankCost : 1000.12
     * preCharging : 10.26
     * deliveryDate : 2018-08-12
     * payBillDay : 10
     * payAccount : 622202199301234554
     * payAccountBank : 厦门国际银行朝阳门支行
     * payAccountApproach : 划扣
     * pollicitaBackDate : 2018-05-14
     * pollicitaBackEndDate : 2018-02-15
     * specialAgreement : 哈哈哈
     * signPerson : 韩梅梅
     */

    private String id;
    private String creditApp;
    private String creditBeneficiary;
    private String basicTransaction;
    private String interestRate;
    private String fee;
    private String invoiceAmount;
    private String acceptEndDate;
    private String grace;
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

    public String getAcceptEndDate() {
        return acceptEndDate;
    }

    public void setAcceptEndDate(String acceptEndDate) {
        this.acceptEndDate = acceptEndDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getGrace() {
        return grace;
    }

    public void setGrace(String grace) {
        this.grace = grace;
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
}
