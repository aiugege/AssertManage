package com.zjhl.pad.app.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * DialogUtils
 * Dialog弹窗工具类
 * Created by chen on 2017/8/24.
 *
 * @desc: DialogUtils
 * @version: v1.0
 * @packagename: com.zjhl.pad.app.utils
 * @author: pluto
 * @create: 2018/5/7 20:07
 * @projectname: nnkj
 **/


public class DialogUtils {

    /**
     * @param context
     * @param titleResId
     * @param messageResId
     * @param cancelable
     * @param yesOnClick
     * @param noOnClick
     */
    public static void showDialog(Context context, int titleResId, int messageResId, boolean cancelable,
                                  DialogInterface.OnClickListener yesOnClick,
                                  DialogInterface.OnClickListener noOnClick) {
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle(titleResId)
                .setMessage(messageResId).setCancelable(cancelable)
                .setPositiveButton("yes", yesOnClick).setNegativeButton("no", noOnClick).create();
        dialog.show();
        ;

    }

    /**
     * 自定义View
     *
     * @param context
     * @param titleResId
     * @param v
     * @param cancelable
     */
    public static void showDialog(Context context, int titleResId, View v, boolean cancelable) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(titleResId).setView(v).setCancelable(cancelable)
                .setPositiveButton("yes", null).create();
        dialog.show();
    }

    /**
     * 单选对话框
     *
     * @param context
     * @param titleResId
     * @param arrayId
     * @param choiceOnClickListener
     */
    public static void showMultiItemsDialog(Context context, int titleResId, int arrayId,
                                            DialogInterface.OnClickListener choiceOnClickListener) {
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle(titleResId)
                .setSingleChoiceItems(arrayId, -1, choiceOnClickListener).setCancelable(true).create();
        dialog.show();
    }
}