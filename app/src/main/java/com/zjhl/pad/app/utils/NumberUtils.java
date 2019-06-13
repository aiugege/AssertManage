package com.zjhl.pad.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* File: NumberUtils
* Author: leeky 
* Version: V1.0
* Create: 2018/9/14 15:14 
* description: 判断数字
*/
public class NumberUtils {

    /**
     *
     * @param number
     * @return 有效返回true,否则返回false
     */
    public static boolean isNumber(String number) {

        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(number);
        return m.matches();
    }
}
