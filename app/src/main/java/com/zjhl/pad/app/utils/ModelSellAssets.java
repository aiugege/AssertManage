package com.zjhl.pad.app.utils;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.view.R;

/*
* File: Model.java
* Author: DELL 
* Version: V1.0
* Create: 2018/5/9 11:35 
* Changes (from 2018/5/9) 
*/
public class ModelSellAssets {

    // 第一个listview的文本数据数组
    public  String[] LISTVIEWTXT = new String[] { MyApplication.mMyApplication.getResources().getString(R.string.filtrate_inputdate), MyApplication.mMyApplication.getResources().getString(R.string.filtrate_enddate),
            MyApplication.mMyApplication.getResources().getString(R.string.filtrate_amount), MyApplication.mMyApplication.getResources().getString(R.string.filtrate_discountrate) };

    // 第二个listview的文本数据
    public  String[][] MORELISTTXT = {
            {MyApplication.mMyApplication.getResources().getString(R.string.filtrate_asc), MyApplication.mMyApplication.getResources().getString(R.string.filtrate_desc)},
            {MyApplication.mMyApplication.getResources().getString(R.string.filtrate_asc), MyApplication.mMyApplication.getResources().getString(R.string.filtrate_desc)},
            {MyApplication.mMyApplication.getResources().getString(R.string.filtrate_asc), MyApplication.mMyApplication.getResources().getString(R.string.filtrate_desc)},
            {MyApplication.mMyApplication.getResources().getString(R.string.filtrate_asc), MyApplication.mMyApplication.getResources().getString(R.string.filtrate_desc)},
    };
}
