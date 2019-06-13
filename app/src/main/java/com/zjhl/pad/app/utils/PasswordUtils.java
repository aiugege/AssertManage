package com.zjhl.pad.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* File: PasswordUtils
* Author: leeky 
* Version: V1.0
* Create: 2018/9/18 12:17 
* description: 密码验证
*/
public class PasswordUtils {

    /**
     *^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])^.{8,16}$
     * @param password
     * @return 有效返回true,否则返回false
     */
    public static boolean isPassword(String password) {

        Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])^.{8,16}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
