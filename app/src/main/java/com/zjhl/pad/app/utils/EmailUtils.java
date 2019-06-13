package com.zjhl.pad.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* File: EmailUtils
* Author: leeky 
* Version: V1.0
* Create: 2018/9/14 14:37 
* description: 判断邮箱
*/
public class EmailUtils {

    /**
     *
     * @param email
     * @return 有效返回true,否则返回false
     */
    public static boolean isEmail(String email) {

        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
