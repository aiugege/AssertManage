package com.zjhl.pad.moudle.entity.req;

import java.io.Serializable;

/**
 * @desc: LoginCheckUserPasswordReq
 * @version: v1.0
 * @packagename: com.zjhl.pad.entity.req
 * @author: pluto
 * @create: 2018/4/18 13:49
 * @projectname: nnkj
 *  检查用户名密码/发送验证码接口请求对象
 **/
public class LoginCheckUserPasswordReq /*extends RequestBean*/implements Serializable {
    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
