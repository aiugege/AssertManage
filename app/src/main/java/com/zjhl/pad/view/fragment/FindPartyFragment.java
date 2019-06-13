package com.zjhl.pad.view.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zjhl.pad.view.base.BaseFragment;


/*
* File: FindPartyFragment.java 发现
* Author: 刘子龙
* Version: V100R001C01
* Create: 2018/4/2 19:27
* Changes (from 2018/4/2)
*/
public class FindPartyFragment extends BaseFragment {


    private static final String TAG = FindPartyFragment.class.getSimpleName();//"CommonFrameFragment"
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG,"第三方Fragment页面被初始化了...");
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "第三方Fragment数据被初始化了...");
        textView.setText("第三方页面");
    }
}
