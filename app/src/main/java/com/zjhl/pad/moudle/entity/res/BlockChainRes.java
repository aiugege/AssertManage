package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: BlockChainRes 区块链
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/5/15 20:39
 * @projectname: nnkj
 **/
public class BlockChainRes extends ResponseBean implements Serializable {


    /**
     * code : 300
     * message : SUCCESS
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * data : [{"bussTypeState":1,"assetsNo":"Z18052900013","ownOrgName":null,"openSwift":"1","tenderSwift":"1","amount":1,"currency":"CNY","lcNo":"1","chainDate":"2018-05-29","operateType":0,"blockChainNode":"5d799409ecc4fc964ebc3be6e33002a5184b784166bd2261e8dab76d2c9b0c9c"},{"bussTypeState":3,"assetsNo":"Z18052900009","ownOrgName":"深圳国投商业保理有限公司","openSwift":"q","tenderSwift":"qqqq","amount":1000,"currency":"CNY","lcNo":"rrr","chainDate":"2018-05-29","operateType":1,"blockChainNode":"0331463285b4380653a7036e50b43a8ef1d35e372f47f62fd2dea815434e320a"},{"bussTypeState":1,"assetsNo":"Z18052900010","ownOrgName":null,"openSwift":"1111111111","tenderSwift":"123123123","amount":10000,"currency":"EUR","lcNo":"123123123","chainDate":"2018-05-29","operateType":0,"blockChainNode":"edfd5b35ce62609ef9f6973c63cc1765d23f5319d680e0d73f12016c1e640eea"}]
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
         * bussTypeState : 1
         * assetsNo : Z18052900013
         * ownOrgName : null
         * openSwift : 1
         * tenderSwift : 1
         * amount : 1
         * currency : CNY
         * lcNo : 1
         * chainDate : 2018-05-29
         * operateType : 0
         * blockChainNode : 5d799409ecc4fc964ebc3be6e33002a5184b784166bd2261e8dab76d2c9b0c9c
         */

        private String bussTypeState;
        private String assetsNo;
        private String ownOrgName;
        private String openSwift;
        private String tenderSwift;
        private String amount;
        private String currency;
        private String lcNo;
        private String chainDate;
        private String operateType;
        private String blockChainNode;
        private String isnBlockChain;

        public String getIsnBlockChain() {
            return isnBlockChain;
        }

        public void setIsnBlockChain(String isnBlockChain) {
            this.isnBlockChain = isnBlockChain;
        }

        public String getBussTypeState() {
            return bussTypeState;
        }

        public void setBussTypeState(String bussTypeState) {
            this.bussTypeState = bussTypeState;
        }

        public String getAssetsNo() {
            return assetsNo;
        }

        public void setAssetsNo(String assetsNo) {
            this.assetsNo = assetsNo;
        }

        public String getOwnOrgName() {
            return ownOrgName;
        }

        public void setOwnOrgName(String ownOrgName) {
            this.ownOrgName = ownOrgName;
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

        public String getChainDate() {
            return chainDate;
        }

        public void setChainDate(String chainDate) {
            this.chainDate = chainDate;
        }

        public String getOperateType() {
            return operateType;
        }

        public void setOperateType(String operateType) {
            this.operateType = operateType;
        }

        public String getBlockChainNode() {
            return blockChainNode;
        }

        public void setBlockChainNode(String blockChainNode) {
            this.blockChainNode = blockChainNode;
        }
    }
}
