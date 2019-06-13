package com.zjhl.pad.app.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zjhl.pad.app.application.MyApplication;

/**
 * @desc: ViewUtils
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/6/6 10:50
 * @projectname: nnkj
 **/
public class ViewUtils {
    private static boolean isShow = true;//默认显示

    public ViewUtils() {
    }

    /**
     * 判断内容显示
     *
     * @param message
     */
    public static boolean ViewTextNull(View view, CharSequence message) {
        boolean result = false;
        if (isShow && view != null) {
            if (view instanceof TextView){
//                mToast = Toast.makeText(MyApplication.mMyApplication, message, Toast.LENGTH_SHORT);
                if (!StringUtils.isEmpty(((TextView)view).getText().toString())) {
                    result = true;
                }else{
                    ToastUtils.showShort(message);
                }
            } else if (view instanceof EditText){
                if (!StringUtils.isEmpty(((EditText)view).getText().toString())) {
                        result = true;
                }else{
                    ToastUtils.showShort(message);
                }
            }
        }
        return result;
    }

}
