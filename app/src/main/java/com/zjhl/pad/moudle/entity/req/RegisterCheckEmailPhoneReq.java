package com.zjhl.pad.moudle.entity.req;

/**
 * @desc: RegisterReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.moudle.entity.req
 * @author: pluto
 * @create: 2018/4/20 13:49
 * @projectname: nnkj
 **/
public class RegisterCheckEmailPhoneReq {
    /**
     * email : 112346@qq.com
     * phone : 18647894561
     */

    private String email;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
