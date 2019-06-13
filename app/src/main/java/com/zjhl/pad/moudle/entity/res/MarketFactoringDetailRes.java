package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;

/**
 * @desc: MarketForfaitingDetailRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/15 20:39
 * @projectname: nnkj
 **/
public class MarketFactoringDetailRes extends ResponseBean  implements Serializable{


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : {"tradeSum":1,"successSum":1,"failSum":0,"successRate":"100.00%","successAmount":6,"companyOrg":{"id":2249,"companyTypeId1":"bankUser","companyTypeId2":"policyLenderBank ","areaId":"1","countryId":"中国","provinceId":null,"companyName":"USBqqq","companyDomicile":"RMBtoo","contactName":"your","content":null,"checkState":111,"lockState":1,"gradeCode":null,"swiftCode":"www****wwww","licenseNo":"wwww****www","remark":null,"inviteCode":"","createTime":"2018-06-12","updateTime":"2018-06-12","yn":1,"orgDetail":"{\"licenseNO\":\"wwwwwwwwwww\",\"mobile\":\"13600000005\",\"licenseImgUrl\":\"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsfeR-ALin0AAQwYNISxA0637.jpg\",\"swiftCode\":\"wwwwwwwwwww\",\"orgId\":\"2249\",\"orgRegisterAddr\":\"RMBtoo\"}","contactTel":"136****0005"},"factoringResponse":{"id":465,"companyOrgId":2249,"factoringNo":"B18061200014","seller":"保理","factoringType":2,"currency":"CNY","amount":"63251455.00","transferRate":"6.0000","maturity":"2018-12-01","indateMessage":"2018-07-01","factoringName":"会员","isCove":2,"insurer":"","checkState":104,"checkReason":null,"areaName":"欧洲","countryName":"法罗群岛（丹）","gradeCode":null,"myAssets":"0","originators":"","obligors":"******","assetAgreement":{"ST0207":{"param_code":"ST0207","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHyAaf0SAANxGXiFFO0144.jpg"},"ST0208":{"param_code":"ST0208","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHaAWrzbAANxGXiFFO0269.jpg"},"ST0204":{"param_code":"ST0204","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg"}},"enAreaName":"Europe","enCountryName":"Faeroe Islands","isnPrice":1}}
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

    public static class DataBean   implements Serializable{
        /**
         * tradeSum : 1
         * successSum : 1
         * failSum : 0
         * successRate : 100.00%
         * successAmount : 6
         * companyOrg : {"id":2249,"companyTypeId1":"bankUser","companyTypeId2":"policyLenderBank ","areaId":"1","countryId":"中国","provinceId":null,"companyName":"USBqqq","companyDomicile":"RMBtoo","contactName":"your","content":null,"checkState":111,"lockState":1,"gradeCode":null,"swiftCode":"www****wwww","licenseNo":"wwww****www","remark":null,"inviteCode":"","createTime":"2018-06-12","updateTime":"2018-06-12","yn":1,"orgDetail":"{\"licenseNO\":\"wwwwwwwwwww\",\"mobile\":\"13600000005\",\"licenseImgUrl\":\"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsfeR-ALin0AAQwYNISxA0637.jpg\",\"swiftCode\":\"wwwwwwwwwww\",\"orgId\":\"2249\",\"orgRegisterAddr\":\"RMBtoo\"}","contactTel":"136****0005"}
         * factoringResponse : {"id":465,"companyOrgId":2249,"factoringNo":"B18061200014","seller":"保理","factoringType":2,"currency":"CNY","amount":"63251455.00","transferRate":"6.0000","maturity":"2018-12-01","indateMessage":"2018-07-01","factoringName":"会员","isCove":2,"insurer":"","checkState":104,"checkReason":null,"areaName":"欧洲","countryName":"法罗群岛（丹）","gradeCode":null,"myAssets":"0","originators":"","obligors":"******","assetAgreement":{"ST0207":{"param_code":"ST0207","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHyAaf0SAANxGXiFFO0144.jpg"},"ST0208":{"param_code":"ST0208","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHaAWrzbAANxGXiFFO0269.jpg"},"ST0204":{"param_code":"ST0204","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg"}},"enAreaName":"Europe","enCountryName":"Faeroe Islands","isnPrice":1}
         */

        private String tradeSum;
        private String successSum;
        private String failSum;
        private String successRate;
        private String successAmount;
        private CompanyOrgBean companyOrg;
        private FactoringResponseBean factoringResponse;

        public String getTradeSum() {
            return tradeSum;
        }

        public void setTradeSum(String tradeSum) {
            this.tradeSum = tradeSum;
        }

        public String getSuccessSum() {
            return successSum;
        }

        public void setSuccessSum(String successSum) {
            this.successSum = successSum;
        }

        public String getFailSum() {
            return failSum;
        }

        public void setFailSum(String failSum) {
            this.failSum = failSum;
        }

        public String getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(String successRate) {
            this.successRate = successRate;
        }

        public String getSuccessAmount() {
            return successAmount;
        }

        public void setSuccessAmount(String successAmount) {
            this.successAmount = successAmount;
        }

        public CompanyOrgBean getCompanyOrg() {
            return companyOrg;
        }

        public void setCompanyOrg(CompanyOrgBean companyOrg) {
            this.companyOrg = companyOrg;
        }

        public FactoringResponseBean getFactoringResponse() {
            return factoringResponse;
        }

        public void setFactoringResponse(FactoringResponseBean factoringResponse) {
            this.factoringResponse = factoringResponse;
        }

        public static class CompanyOrgBean   implements Serializable{
            /**
             * id : 2249
             * companyTypeId1 : bankUser
             * companyTypeId2 : policyLenderBank
             * areaId : 1
             * countryId : 中国
             * provinceId : null
             * companyName : USBqqq
             * companyDomicile : RMBtoo
             * contactName : your
             * content : null
             * checkState : 111
             * lockState : 1
             * gradeCode : null
             * swiftCode : www****wwww
             * licenseNo : wwww****www
             * remark : null
             * inviteCode :
             * createTime : 2018-06-12
             * updateTime : 2018-06-12
             * yn : 1
             * orgDetail : {"licenseNO":"wwwwwwwwwww","mobile":"13600000005","licenseImgUrl":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsfeR-ALin0AAQwYNISxA0637.jpg","swiftCode":"wwwwwwwwwww","orgId":"2249","orgRegisterAddr":"RMBtoo"}
             * contactTel : 136****0005
             */

            private int id;
            private String companyTypeId1;
            private String companyTypeId2;
            private String areaId;
            private String countryId;
            private String provinceId;
            private String companyName;
            private String companyDomicile;
            private String contactName;
            private String content;
            private String checkState;
            private String lockState;
            private String gradeCode;
            private String swiftCode;
            private String licenseNo;
            private String remark;
            private String inviteCode;
            private String createTime;
            private String updateTime;
            private String yn;
            private String orgDetail;
            private String contactTel;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCompanyDomicile() {
                return companyDomicile;
            }

            public void setCompanyDomicile(String companyDomicile) {
                this.companyDomicile = companyDomicile;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCheckState() {
                return checkState;
            }

            public void setCheckState(String checkState) {
                this.checkState = checkState;
            }

            public String getLockState() {
                return lockState;
            }

            public void setLockState(String lockState) {
                this.lockState = lockState;
            }

            public String getGradeCode() {
                return gradeCode;
            }

            public void setGradeCode(String gradeCode) {
                this.gradeCode = gradeCode;
            }

            public String getSwiftCode() {
                return swiftCode;
            }

            public void setSwiftCode(String swiftCode) {
                this.swiftCode = swiftCode;
            }

            public String getLicenseNo() {
                return licenseNo;
            }

            public void setLicenseNo(String licenseNo) {
                this.licenseNo = licenseNo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getInviteCode() {
                return inviteCode;
            }

            public void setInviteCode(String inviteCode) {
                this.inviteCode = inviteCode;
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

            public String getOrgDetail() {
                return orgDetail;
            }

            public void setOrgDetail(String orgDetail) {
                this.orgDetail = orgDetail;
            }

            public String getContactTel() {
                return contactTel;
            }

            public void setContactTel(String contactTel) {
                this.contactTel = contactTel;
            }
        }

        public static class FactoringResponseBean   implements Serializable{
            /**
             * id : 465
             * companyOrgId : 2249
             * factoringNo : B18061200014
             * seller : 保理
             * factoringType : 2
             * currency : CNY
             * amount : 63251455.00
             * transferRate : 6.0000
             * maturity : 2018-12-01
             * indateMessage : 2018-07-01
             * factoringName : 会员
             * isCove : 2
             * insurer :
             * checkState : 104
             * checkReason : null
             * areaName : 欧洲
             * countryName : 法罗群岛（丹）
             * gradeCode : null
             * myAssets : 0
             * originators :
             * obligors : ******
             * assetAgreement : {"ST0207":{"param_code":"ST0207","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHyAaf0SAANxGXiFFO0144.jpg"},"ST0208":{"param_code":"ST0208","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHaAWrzbAANxGXiFFO0269.jpg"},"ST0204":{"param_code":"ST0204","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg"}}
             * enAreaName : Europe
             * enCountryName : Faeroe Islands
             * isnPrice : 1
             */

            private int id;
            private int companyOrgId;
            private String factoringNo;
            private String seller;
            private String factoringType;
            private String currency;
            private String amount;
            private String transferRate;
            private String maturity;
            private String indateMessage;
            private String factoringName;
            private int isCove;
            private String insurer;
            private String checkState;
            private String checkReason;
            private String areaName;
            private String countryName;
            private String gradeCode;
            private String myAssets;
            private String isnPrice;
            private String originators;
            private String obligors;
            private AssetAgreementBean assetAgreement;
            private String enAreaName;
            private String enCountryName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCompanyOrgId() {
                return companyOrgId;
            }

            public void setCompanyOrgId(int companyOrgId) {
                this.companyOrgId = companyOrgId;
            }

            public String getFactoringNo() {
                return factoringNo;
            }

            public void setFactoringNo(String factoringNo) {
                this.factoringNo = factoringNo;
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

            public String getFactoringName() {
                return factoringName;
            }

            public void setFactoringName(String factoringName) {
                this.factoringName = factoringName;
            }

            public int getIsCove() {
                return isCove;
            }

            public void setIsCove(int isCove) {
                this.isCove = isCove;
            }

            public String getInsurer() {
                return insurer;
            }

            public void setInsurer(String insurer) {
                this.insurer = insurer;
            }

            public String getCheckState() {
                return checkState;
            }

            public void setCheckState(String checkState) {
                this.checkState = checkState;
            }

            public String getCheckReason() {
                return checkReason;
            }

            public void setCheckReason(String checkReason) {
                this.checkReason = checkReason;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getCountryName() {
                return countryName;
            }

            public void setCountryName(String countryName) {
                this.countryName = countryName;
            }

            public String getGradeCode() {
                return gradeCode;
            }

            public void setGradeCode(String gradeCode) {
                this.gradeCode = gradeCode;
            }

            public String getMyAssets() {
                return myAssets;
            }

            public void setMyAssets(String myAssets) {
                this.myAssets = myAssets;
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

            public AssetAgreementBean getAssetAgreement() {
                return assetAgreement;
            }

            public void setAssetAgreement(AssetAgreementBean assetAgreement) {
                this.assetAgreement = assetAgreement;
            }

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

            public String getIsnPrice() {
                return isnPrice;
            }

            public void setIsnPrice(String isnPrice) {
                this.isnPrice = isnPrice;
            }

            public static class AssetAgreementBean   implements Serializable{
                /**
                 * ST0207 : {"param_code":"ST0207","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHyAaf0SAANxGXiFFO0144.jpg"}
                 * ST0208 : {"param_code":"ST0208","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHaAWrzbAANxGXiFFO0269.jpg"}
                 * ST0204 : {"param_code":"ST0204","attachment_url":"http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg"}
                 */

                private ST0207Bean ST0207;
                private ST0208Bean ST0208;
                private ST0204Bean ST0204;
                private ST0205Bean ST0205;
                private ST0206Bean ST0206;
                private ST0209Bean ST0209;

                public ST0205Bean getST0205() {
                    return ST0205;
                }

                public void setST0205(ST0205Bean ST0205) {
                    this.ST0205 = ST0205;
                }

                public ST0206Bean getST0206() {
                    return ST0206;
                }

                public void setST0206(ST0206Bean ST0206) {
                    this.ST0206 = ST0206;
                }

                public ST0209Bean getST0209() {
                    return ST0209;
                }

                public void setST0209(ST0209Bean ST0209) {
                    this.ST0209 = ST0209;
                }

                public ST0207Bean getST0207() {
                    return ST0207;
                }

                public void setST0207(ST0207Bean ST0207) {
                    this.ST0207 = ST0207;
                }

                public ST0208Bean getST0208() {
                    return ST0208;
                }

                public void setST0208(ST0208Bean ST0208) {
                    this.ST0208 = ST0208;
                }

                public ST0204Bean getST0204() {
                    return ST0204;
                }

                public void setST0204(ST0204Bean ST0204) {
                    this.ST0204 = ST0204;
                }

                public static class ST0207Bean   implements Serializable{
                    /**
                     * param_code : ST0207
                     * attachment_url : http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHyAaf0SAANxGXiFFO0144.jpg
                     */

                    private String param_code;
                    private String attachment_url;

                    public String getParam_code() {
                        return param_code;
                    }

                    public void setParam_code(String param_code) {
                        this.param_code = param_code;
                    }

                    public String getAttachment_url() {
                        return attachment_url;
                    }

                    public void setAttachment_url(String attachment_url) {
                        this.attachment_url = attachment_url;
                    }
                }

                public static class ST0208Bean   implements Serializable{
                    /**
                     * param_code : ST0208
                     * attachment_url : http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHaAWrzbAANxGXiFFO0269.jpg
                     */

                    private String param_code;
                    private String attachment_url;

                    public String getParam_code() {
                        return param_code;
                    }

                    public void setParam_code(String param_code) {
                        this.param_code = param_code;
                    }

                    public String getAttachment_url() {
                        return attachment_url;
                    }

                    public void setAttachment_url(String attachment_url) {
                        this.attachment_url = attachment_url;
                    }
                }

                public static class ST0204Bean   implements Serializable{
                    /**
                     * param_code : ST0204
                     * attachment_url : http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg
                     */

                    private String param_code;
                    private String attachment_url;

                    public String getParam_code() {
                        return param_code;
                    }

                    public void setParam_code(String param_code) {
                        this.param_code = param_code;
                    }

                    public String getAttachment_url() {
                        return attachment_url;
                    }

                    public void setAttachment_url(String attachment_url) {
                        this.attachment_url = attachment_url;
                    }
                }
                public static class ST0205Bean   implements Serializable{
                    /**
                     * param_code : ST0204
                     * attachment_url : http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg
                     */

                    private String param_code;
                    private String attachment_url;

                    public String getParam_code() {
                        return param_code;
                    }

                    public void setParam_code(String param_code) {
                        this.param_code = param_code;
                    }

                    public String getAttachment_url() {
                        return attachment_url;
                    }

                    public void setAttachment_url(String attachment_url) {
                        this.attachment_url = attachment_url;
                    }
                }
                public static class ST0206Bean   implements Serializable{
                    /**
                     * param_code : ST0204
                     * attachment_url : http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg
                     */

                    private String param_code;
                    private String attachment_url;

                    public String getParam_code() {
                        return param_code;
                    }

                    public void setParam_code(String param_code) {
                        this.param_code = param_code;
                    }

                    public String getAttachment_url() {
                        return attachment_url;
                    }

                    public void setAttachment_url(String attachment_url) {
                        this.attachment_url = attachment_url;
                    }
                }
                public static class ST0209Bean   implements Serializable{
                    /**
                     * param_code : ST0204
                     * attachment_url : http://192.144.138.58:80/group1/M00/00/17/rBUQBVsffHmAXa_sAANxGXiFFO0510.jpg
                     */

                    private String param_code;
                    private String attachment_url;

                    public String getParam_code() {
                        return param_code;
                    }

                    public void setParam_code(String param_code) {
                        this.param_code = param_code;
                    }

                    public String getAttachment_url() {
                        return attachment_url;
                    }

                    public void setAttachment_url(String attachment_url) {
                        this.attachment_url = attachment_url;
                    }
                }
            }
        }
    }
}
