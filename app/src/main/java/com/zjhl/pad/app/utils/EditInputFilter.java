package com.zjhl.pad.app.utils;

import android.text.InputFilter;
import android.text.Spanned;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.view.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc: EditInputFilter
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/5/22 17:12
 * @projectname: nnkj
 **/
public class EditInputFilter implements InputFilter {
    /**
     * 最大数字
     */
    public static final int MAX_VALUE = 10;
    /**
     * 小数点后的数字的位数
     */
    public static final int PONTINT_LENGTH = 4;
    Pattern p;
    public EditInputFilter(){
        p = Pattern.compile("[0-9]*");   //除数字外的其他的
    }

    /**
     *  source    新输入的字符串
     *  start    新输入的字符串起始下标，一般为0
     *  end    新输入的字符串终点下标，一般为source长度-1
     *  dest    输入之前文本框内容
     *  dstart    原内容起始坐标，一般为0
     *  dend    原内容终点坐标，一般为dest长度-1
     */

    @Override
    public CharSequence filter(CharSequence src, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String oldtext =  dest.toString();
        System.out.println(oldtext);
        //验证删除等按键
        if ("".equals(src.toString())) {
            return null;
        }
        //验证非数字或者小数点的情况
        Matcher m = p.matcher(src);
        if(oldtext.contains(".")){
            //已经存在小数点的情况下，只能输入数字
            if(!m.matches()){
                return null;
            }
        }else{
            //未输入小数点的情况下，可以输入小数点和数字
            if(!m.matches() && !src.equals(".") ){
                return null;
            }
        }
        //验证输入金额的大小
        if(!src.toString().equals("")&&!src.toString().equals(".")){
            double dold = Double.parseDouble(oldtext+src.toString());
            if(dold > MAX_VALUE){
                ToastUtils.showShort(MyApplication.mMyApplication.getResources().getString(R.string.toast_amount_not)+MAX_VALUE);
                return dest.subSequence(dstart, dend);
            }else if(dold == MAX_VALUE){
                if(src.toString().equals(".")){
                    ToastUtils.showShort(MyApplication.mMyApplication.getResources().getString(R.string.toast_amount_not)+MAX_VALUE);
                    return dest.subSequence(dstart, dend);
                }
            }
        }
        //验证小数位精度是否正确
        if(oldtext.contains(".")){
            int index = oldtext.indexOf(".");
            int len = dend - index;
            //小数位只能4位
            if(len > PONTINT_LENGTH){
                CharSequence newText = dest.subSequence(dstart, dend);
                return newText;
            }
        }
        return dest.subSequence(dstart, dend) +src.toString();
    }
}
