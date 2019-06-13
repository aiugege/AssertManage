package com.zjhl.pad.app.utils;

import android.text.InputFilter;
import android.text.Spanned;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.view.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc: CodeEditInputFilter
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/5/22 17:12
 * @projectname: nnkj
 **/
public class CodeEditInputFilter implements InputFilter {
    /**
     * 最大数字
     */
    public static final int MAX_LENGTH = 11;
    /**
     * 最大数字2
     */
    public static final int MAX_LENGTH2 = 8;
    Pattern p;
    Pattern p1;

    public CodeEditInputFilter() {
        p = Pattern.compile("[A-Z]");   //除数字外的其他的大写字母
        p1 = Pattern.compile("[a-z]");   //除数字外的其他的小写字母
//        p1 = Pattern.compile("[A-Z]");   //除数字外的其他的小写字母
    }

    /**
     * source    新输入的字符串
     * start    新输入的字符串起始下标，一般为0
     * end    新输入的字符串终点下标，一般为source长度-1
     * dest    输入之前文本框内容
     * dstart    原内容起始坐标，一般为0
     * dend    原内容终点坐标，一般为dest长度-1
     */

    @Override
    public CharSequence filter(CharSequence src, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String oldtext = dest.toString();
        System.out.println(oldtext);
        //验证删除等按键
        if ("".equals(src.toString())) {
            return null;
        }
        String ss = "";

        //验证前6位是否是字母情况
        Matcher m = p.matcher(src);
        Matcher m1 = p1.matcher(src);
        boolean mres = m.matches();
//        MyLogger.pLog().e("mmm=" + mmm);
        if (!mres && "".equals(oldtext) || !mres && oldtext.length() < 6) {
            if(m1.matches()) {
                return dest.subSequence(dstart, dend).toString().toUpperCase() + src.toString().toUpperCase();
            }else{
                if(src.toString().length()==MAX_LENGTH){
                    return dest.subSequence(dstart, dend) + src.toString().toUpperCase();
                }else if(src.toString().length()==MAX_LENGTH2){
                    return dest.subSequence(dstart, dend) + src.toString().toUpperCase();
                }else {
                    return dest.subSequence(dstart, dend);
                }
            }
        }
        if (mres && oldtext.length() < 6) {
            oldtext = oldtext.toUpperCase() + src.toString().toUpperCase();
            return null;
        } else if (oldtext.length() >= 6) {
            ss = (oldtext + src.toString().toUpperCase());
            int dold = ss.length();
            if (dold == MAX_LENGTH) {
//                ToastUtils.showShort(MyApplication.mMyApplication.getResources().getString(R.string.toast_amount_not)+MAX_VALUE);
//                MyLogger.pLog().e("=1=" + dest.subSequence(dstart, dend));
                return dest.subSequence(dstart, dend) + src.toString().toUpperCase();
            } else if (dold == MAX_LENGTH2) {
//                MyLogger.pLog().e("=2=" + dest.subSequence(dstart, dend));
                return dest.subSequence(dstart, dend) + src.toString().toUpperCase();

            } else if (dold > MAX_LENGTH) {
                return dest.subSequence(dstart, dend);
            }
        } else {
            return null;
        }

        return dest.subSequence(dstart, dend) + src.toString().toUpperCase();
    }
}
