package com.zjhl.pad.moudle.entity.res;

import com.zjhl.pad.moudle.entity.base.ResponseBean;

import java.util.List;

/**
 * @desc: RegisterRes
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.res
 * @author: pluto
 * @create: 2018/4/20 13:46
 * @projectname: nnkj
 **/
public class RegisterDictionaryRes extends ResponseBean {

    /**一级数据{"code":300,"message":"SUCCESS","totalCount":0,"totalPage":0,"pageSize":0,"page":0,"data":[{"id":53,"code":"bankUser","name":"银行用户","value":"0","groupId":"institutionType","sort":1,"yn":true},{"id":54,"code":"financialInstitution","name":"非银行金融机构","value":"1","groupId":"institutionType","sort":2,"yn":true},{"id":55,"code":"company","name":"公司企业","value":"2","groupId":"institutionType","sort":3,"yn":true}],"json":null,"list":null}
     * company二级数据{"code":300,"message":"SUCCESS","totalCount":0,"totalPage":0,"pageSize":0,"page":0,"data":[{"id":93,"code":"centralEnterprises","name":"中央企业","value":"","groupId":"company","sort":1,"yn":true},{"id":94,"code":"nationalHolding","name":"国有控股","value":"","groupId":"company","sort":2,"yn":true},{"id":95,"code":"stateOwnedEnterprises","name":"地方国企","value":"","groupId":"company","sort":3,"yn":true},{"id":96,"code":"privateEnterprises","name":"民营企业","value":"","groupId":"company","sort":4,"yn":true},{"id":97,"code":"foreignEnterprise","name":"外资企业","value":"","groupId":"company","sort":5,"yn":true},{"id":98,"code":"microfinanceEnterprise","name":"小贷公司","value":"","groupId":"company","sort":6,"yn":true},{"id":99,"code":"guaranteeCorporation","name":"担保公司","value":"","groupId":"company","sort":7,"yn":true},{"id":100,"code":"importFactor","name":"保理公司","value":"","groupId":"company","sort":8,"yn":true},{"id":101,"code":"\r\n\r\nfinanceLeaseCompany\r\n","name":"融资租赁公司","value":"","groupId":"company","sort":9,"yn":true}],"json":null,"list":null}
     * code : 300
     * message : SUCCESS/…..
     * totalCount : 0
     * totalPage : 0
     * pageSize : 0
     * page : 0
     * json : null
     * list : null
     * data : [{"id":75,"code":"policyLenderBank ","name":"政策银行","value":"","groupId":"bankUser","sort":1,"yn":true}]
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

    @Override
    public String toString() {
        return "RegisterDictionaryRes{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", json=" + json +
                ", list=" + list +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 75
         * code : policyLenderBank
         * name : 政策银行
         * value :
         * groupId : bankUser
         * sort : 1
         * yn : true
         */

        private String id;
        private String code;
        private String name;
        private String value;
        private String groupId;
        private String areaId;
        private int sort;
        private boolean yn;

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getId() {
            return id;

        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public boolean isYn() {
            return yn;
        }

        public void setYn(boolean yn) {
            this.yn = yn;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", groupId='" + groupId + '\'' +
                    ", sort=" + sort +
                    ", yn=" + yn +
                    '}';
        }

    }

}
