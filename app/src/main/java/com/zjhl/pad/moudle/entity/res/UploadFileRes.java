package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

/**
 * @desc: UploadFileRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/8 15:49
 * @projectname: nnkj
 **/
public class UploadFileRes extends ResponseBean {


    /**
     * code : 200
     * message : 成功
     * url : http://139.199.116.36:8088/group1/M00/00/09/rBUABlsON96AZ7-cAAQ8Q5Iwufg885.png
     * creditLetterData : {"send_bank_name":"上海浦东发展银行","amount":"8874000","place_of_expiry":"CANADA","form_of_documentary_credit":"Credit","recv_bank":" BOFWCAT2XXX","drawee_bank":" SPDBCNSH342","currency_code":"USD","date_of_issue":"2017-12-29","date_of_expiry":"2008-02-21","documentary_credit_number":"LC94011700043","drawee_bank_name":"上海浦东发展银行","send_bank":" SPDBCNSH342","recv_bank_name":"蒙特利尔银行"}
     * userName : null
     */

    private int code;
    private String message;
    private String url;
    private CreditLetterDataBean creditLetterData;
    private String userName;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CreditLetterDataBean getCreditLetterData() {
        return creditLetterData;
    }

    public void setCreditLetterData(CreditLetterDataBean creditLetterData) {
        this.creditLetterData = creditLetterData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static class CreditLetterDataBean {
        /**
         * send_bank_name : 上海浦东发展银行
         * amount : 8874000
         * place_of_expiry : CANADA
         * form_of_documentary_credit : Credit
         * recv_bank :  BOFWCAT2XXX
         * drawee_bank :  SPDBCNSH342
         * currency_code : USD
         * date_of_issue : 2017-12-29
         * date_of_expiry : 2008-02-21
         * documentary_credit_number : LC94011700043
         * drawee_bank_name : 上海浦东发展银行
         * send_bank :  SPDBCNSH342
         * recv_bank_name : 蒙特利尔银行
         */

        private String send_bank_name;
        private String amount;
        private String place_of_expiry;
        private String form_of_documentary_credit;
        private String recv_bank;
        private String drawee_bank;
        private String currency_code;
        private String date_of_issue;
        private String date_of_expiry;
        private String documentary_credit_number;
        private String drawee_bank_name;
        private String send_bank;
        private String recv_bank_name;

        public String getSend_bank_name() {
            return send_bank_name;
        }

        public void setSend_bank_name(String send_bank_name) {
            this.send_bank_name = send_bank_name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPlace_of_expiry() {
            return place_of_expiry;
        }

        public void setPlace_of_expiry(String place_of_expiry) {
            this.place_of_expiry = place_of_expiry;
        }

        public String getForm_of_documentary_credit() {
            return form_of_documentary_credit;
        }

        public void setForm_of_documentary_credit(String form_of_documentary_credit) {
            this.form_of_documentary_credit = form_of_documentary_credit;
        }

        public String getRecv_bank() {
            return recv_bank;
        }

        public void setRecv_bank(String recv_bank) {
            this.recv_bank = recv_bank;
        }

        public String getDrawee_bank() {
            return drawee_bank;
        }

        public void setDrawee_bank(String drawee_bank) {
            this.drawee_bank = drawee_bank;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }

        public String getDate_of_issue() {
            return date_of_issue;
        }

        public void setDate_of_issue(String date_of_issue) {
            this.date_of_issue = date_of_issue;
        }

        public String getDate_of_expiry() {
            return date_of_expiry;
        }

        public void setDate_of_expiry(String date_of_expiry) {
            this.date_of_expiry = date_of_expiry;
        }

        public String getDocumentary_credit_number() {
            return documentary_credit_number;
        }

        public void setDocumentary_credit_number(String documentary_credit_number) {
            this.documentary_credit_number = documentary_credit_number;
        }

        public String getDrawee_bank_name() {
            return drawee_bank_name;
        }

        public void setDrawee_bank_name(String drawee_bank_name) {
            this.drawee_bank_name = drawee_bank_name;
        }

        public String getSend_bank() {
            return send_bank;
        }

        public void setSend_bank(String send_bank) {
            this.send_bank = send_bank;
        }

        public String getRecv_bank_name() {
            return recv_bank_name;
        }

        public void setRecv_bank_name(String recv_bank_name) {
            this.recv_bank_name = recv_bank_name;
        }
    }
}
