package com.zjhl.pad.application;

import android.app.Application;
import android.content.Context;

/*
* File: MyApplication.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/4/3 9:50 
* Changes (from 2018/4/3) 
*/
public class MyApplication extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(base);//5.0版本以下对于方法数限制65536个，超过报找不到类运行、错误
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
