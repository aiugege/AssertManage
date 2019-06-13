package com.zjhl.pad.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* File: PhoneUtils
* Author: leeky 
* Version: V1.0
* Create: 2018/9/14 14:34 
* description: 判断电话号码
*/
public class PhoneUtils {

    /**
     *
     * @param phoneNum
     * @return 有效返回true,否则返回false
     */
    public static boolean isPhoneNum(String phoneNum) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$");
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }

}
