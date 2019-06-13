package com.zjhl.pad.view.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zjhl.pad.view.base.BaseFragment;


/*
* File: SettingFragment.java 
* Author: 刘子龙
* Version: V100R001C01 
* Create: 2018/4/2 19:27
* Changes (from 2018/4/2)
*/
public class SettingFragment extends BaseFragment {

    private TextView textView;
    @Override
    protected View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("自定义页面");
    }
}
