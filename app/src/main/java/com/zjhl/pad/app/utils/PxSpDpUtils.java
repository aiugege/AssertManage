package com.zjhl.pad.app.utils;

import android.content.Context;

/**
 * @desc: PxSpDpUtils
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/7/23 17:49
 * @projectname: nnkj
 **/
public class PxSpDpUtils {
    private static PxSpDpUtils pxSpDpUtils;

    private PxSpDpUtils(){

    }
    public static synchronized PxSpDpUtils getInstance() {
        if (pxSpDpUtils == null) {
            pxSpDpUtils = new PxSpDpUtils();
        }
        return pxSpDpUtils;
    }

    /**
     * dp转换成px
     */
    public int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成dp
     */
    private int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换成px
     */
    private int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转换成sp
     */
    private int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
