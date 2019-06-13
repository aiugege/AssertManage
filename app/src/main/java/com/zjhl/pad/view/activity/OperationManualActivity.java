package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

/*
* File: OperationManualActivity.java 常见问题
* Author: DELL 
* Version: V1.0
* Create: 2018/7/11 14:18 
* Changes (from 2018/7/11) 
*/
public class OperationManualActivity extends BaseActivity {

    private  WebView oper_webview;
    private TextView tv_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_manual);
         oper_webview = findViewById(R.id.operation_webview);
          tv_content = (TextView) findViewById(R.id.tv_content);

        tv_content.setText(getString(R.string.mine_faq_question));
        String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

        WebSettings webSettings = oper_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        //开启缓存功能

        webSettings.setAppCacheEnabled(true);

//设置缓存模式

//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        if ("cn".equals(lanage)) {
            oper_webview.loadUrl(Constants.API_ROOT2+"help/?lang=cn");
            closeWaitDialog();
        } else if ("en".equals(lanage)) {
            oper_webview.loadUrl(Constants.API_ROOT2+"help/?lang=en");
            closeWaitDialog();
        }

//        oper_webview.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if(newProgress==100){
////                    showWaitDialog();//加载完网页进度条消失
//                }
//                else{
////                    closeWaitDialog();
////                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
////                    pg1.setProgress(newProgress);//设置进度值
//                }
//
//            }
//        });

        findViewById(R.id.iv_excite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationManualActivity.this.finish();
            }
        });

    }
    }


