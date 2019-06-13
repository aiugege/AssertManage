package com.zjhl.pad.app.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.lzy.okhttputils.OkHttpUtils;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.LocalManageUtil;
import com.zjhl.pad.app.utils.RandomUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.activity.LoginActivity;
import com.zjhl.pad.view.activity.MainActivity;

import cn.jpush.android.api.JPushInterface;
import pl.aprilapps.easyphotopicker.EasyImage;

/*
* File: MyApplication.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/4/3 9:50 
* Changes (from 2018/4/3) 
*/
public class MyApplication extends Application{
    public static volatile ActionPresenter mActionPresenter;
    public static volatile MyApplication mMyApplication;
    public static volatile String imgcode;

    @Override
    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(base);//5.0版本以下对于方法数限制65536个，超过报找不到类运行、错误
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(LocalManageUtil.setLocal(base));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());
        mMyApplication = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);     		// 初始化 JPush
        OkHttpUtils.init(this);
        //初始化log开始
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        //初始化log结束
        mActionPresenter = ActionPresenter.getInstance();
        //初始化用户状态
        UpdateUserInfo(false,"","");
        imgcode = TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_DATE1) + RandomUtils.getRandomNumbers(4);
//        MyApplication.mMyApplication.UpdateUserInfo(true, response.body().getData().getToken(), response.body().getData().getCurrentId() + "");
//        String token = (String) SharedPreferanceUtils.get(mMyApplication, Constants.USERINFO.USERINFO_TOKEN, "");
//        if (!TextUtils.isEmpty(token)){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("token_apply","2"));
//        }
        //初始化图片存放目录
        EasyImage.configuration(this)
                .setImagesFolderName("zjhlimages")
                .saveInRootPicturesDirectory();
        SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn");
        LocalManageUtil.saveSelectLanguage(mMyApplication, 1);
//        setLanguage(this);
        //经办
//        UpdateUserInfo(true,"0dbcdceb0ebe4af3a62fba3f86fae651","2306");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2050");
//        UpdateUserInfo(true,"d22ef2bb568c4b36b13e417ce7ba0865","2313");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2107");
//        UpdateUserInfo(true,"432ee8a1b6e9400c9d1012d14d2dff5a","1020");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2102");

//        UpdateUserInfo(true,"c9680aa2a7c44cfd92d2cf52a8474d6f","2288");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2191");

//        UpdateUserInfo(true,"3ed0f09a58684509ba39794b51f16a5d","1006");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2050");
//
//        UpdateUserInfo(true,"705183d48ce740698da45afa0a85539e","2516");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2191");
//        UpdateUserInfo(true,"65e1cfb387234ec3bb61a180d648b012","2577");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2224");
//        UpdateUserInfo(true,"bdc1f3393492480fb872f626d6a37c0d","2573");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2222");
        //复核
//        UpdateUserInfo(true,"d677e988c10640818667a63994eb43d3","1020");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2050");
//
//        UpdateUserInfo(true,"4a9c3ad45fb141438ab7cf43b1daa4bc","2305");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2102");

//        UpdateUserInfo(true,"927e17da334d4aaa8fc6c37bf52fcb2a","2306");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2102");
//
//        UpdateUserInfo(true,"17122d2a97154644941d821bbe1a690e","2314");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2102");

//        UpdateUserInfo(true,"35d6194d107d428fa5eebc6d8148978d","2487");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2102");

//        UpdateUserInfo(true,"e036ee36ca6d4db0a6ff9e9109153fb2","2107");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"2314");
//        UpdateUserInfo(true,"fb4cbce2b73d4049a95a1d09a437ccda","1312");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"129");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_TYPE,"3");
//
//        UpdateUserInfo(true,"108d43f0ced846b089f89647669af5ae","1290");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"127");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_TYPE,"1");

//        UpdateUserInfo(true,"d34d493cc8544352a32f41d23f5d31ad","1242");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,"11");
//        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_TYPE,"3");

//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
    }

        //未登录状态  status 默认false  token 默认"" currentid默认""
    public void UpdateUserInfo(boolean status,String token,String currentid){
        //初始化用户状态
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_STATUS,status);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_TOKEN,token);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_CURRENTID,currentid);
    }
        //未登录状态  status 默认false  token 默认"" currentid默认"" companyName默认"" realName默认"" companyId默认"" email默认"" userType默认""
    public void UpdateUserInfo(boolean status,String token,String currentid,String companyName,String realName,String companyId,String email,String userType){
        //初始化用户状态
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_STATUS,status);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_TOKEN,token);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_CURRENTID,currentid);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYNAME, companyName);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_USERNAME,realName);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, companyId);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_EMAIL, email);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_TYPE, userType);
    }

    //未登录状态  status 默认false  token 默认"" curr    entid默认""
    public void UpdateUserInfo(boolean status, String token, String currentid, LoginRes loginRes){
        UpdateUserInfo( status, token, currentid);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_OBJECT,loginRes);
        SharedPreferanceUtils.put(mMyApplication, Constants.USERINFO.USERINFO_COMPANYID,loginRes.getData().getCompanyId()+"");
    }

    /**
     *
     * 设置语言
     *
     * @param context
     *
     */
//    public static void setLanguage(Context context) {
//        String language = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
//        Resources res = context.getResources();
//        Configuration config = res.getConfiguration();
//        if("cn".equals(language)) {
//            config.locale = Locale.CHINESE;//选择语言类别,这个是中文
//        }else if("en".equals(language)) {
//            config.locale = Locale.ENGLISH;//选择语言类别,这个是英文
//        }
//        res.updateConfiguration(config, res.getDisplayMetrics());
//    }
}
