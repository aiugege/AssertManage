package com.zjhl.pad.share;

import android.content.Context;
import android.content.SharedPreferences;

import com.zjhl.pad.constants.Constants;

/*
* File: SharedPreferanceUtils.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/4/3 10:17 
* Changes (from 2018/4/3) 
*/
public class SharedPreferanceUtils {

    private static volatile SharedPreferences sharedPreferences = null;
    private static volatile SharedPreferences.Editor editor = null;


    public SharedPreferanceUtils(Context constants){
        sharedPreferences = constants.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    /**
     * 保存身份证状态是否通过
     *
     * @param
     */
    public static void setidCardState(String idCardState) {
        editor.putString(Constants.SHARED_IDCARD, idCardState);
        editor.commit();
    }

    public static String getidCardState() {
        return sharedPreferences.getString(Constants.SHARED_IDCARD,"");
    }
}
