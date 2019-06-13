package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: RegisterReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class RegisterDictionaryReq {

    /**
     * /查询一级机构类型时写死为institutionType
     查询二级时 传入对应一级的code值

     */

    private String groupId = "institutionType";

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
