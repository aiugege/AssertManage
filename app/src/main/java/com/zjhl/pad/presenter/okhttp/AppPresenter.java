package com.zjhl.pad.presenter.okhttp;

/**
 * @desc: AppPresenter
 * @version: v1.0
 * @packagename: com.zjhl.pad.okhttp
 * @author: pluto
 * @create: 2018/4/18 13:05
 * @projectname: nnkj
 **/

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.moudle.entity.base.RequestBean;
import com.zjhl.pad.moudle.entity.base.ResponseBean;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 请求网络业务的基类，AppPresenterr 的封装
 */

public class AppPresenter {
    protected ServerApi mApi = RetrofitUtils.getInstance().create(ServerApi.class);
    private static final Gson gson = new Gson();

    /**
     * 1. 转换
     * 统一处理一些动作
     */
    public static <T> void convert(Observable<ResponseBean<T>> observable, Observer<T> observer) {
//        observable.map(new Function<ResponseBean<T>, T>() {
//                    @Override
//                    public T apply(ResponseBean<T> httpResult) throws Exception {
//                        // 打印响应的对象
////                        LogUtils.object(httpResult);
//                        Log.d("httpResult:",httpResult.message);
//                        // TODO 实际开发的时候统一处理一些东西
//                        if (httpResult == null || httpResult.data == null) {
//                            throw new RuntimeException("请求数据异常");
//                        } else if ("400".equals(httpResult.code)) {
//                            throw new RuntimeException(httpResult.message);
//                        } else if ("500".equals(httpResult.code)) {
//                            throw new RuntimeException(httpResult.message);
//                        }
//                            return httpResult.data;
//
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
    }

    /**
     * 2. 执行的方法
     */
    public static <T> void execute(Observable<ResponseBean> observable, Observer<ResponseBean> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 3.请求数据是Json，Json转成RequestBody
     */
    public static RequestBody createRequestBody(Object obj) {
        String language = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
//        if(!StringUtils.isEmpty(language)){
//
//        }
        return createRequestBody(obj, language);
    }

    /**
     * 4.请求数据是Json，Json转成RequestBody  language 语言
     */
    public static RequestBody createRequestBody(Object obj, String language) {
        RequestBean bean = new RequestBean<>(obj);
        if (!StringUtils.isEmpty(language)) {
            bean.header.setLanguage(language);
        }

        if ((boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false)) {
            bean.header.setToken(SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TOKEN, "").toString());
            bean.header.setCurrentId(SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_CURRENTID, "").toString());
        }
//        RequestBean bean = new RequestBean<>();
        String json = gson.toJson(bean);
        // 打印请求的Json
//        MyLogger.pLog().d(json);
        RequestBody body = getRequestBody(json, "application/json; charset=utf-8");
        return body;
    }


    /**
     * 5.请求数据是Json，Json转成RequestBody ；文件  文件转换
     */
    public static MultipartBody.Part createMultipartBody(File file) {
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part multipartBody =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return multipartBody;
    }

    /**
     * 6.请求数据是文件  文件转换
     */
    public static Map<String, RequestBody> createDesBody() {
        // 添加描述
        //组装partMap对象
        Map<String, RequestBody> partMap = new HashMap<>();
        partMap.put("channel", getRequestBody("3", "multipart/form-data"));
        //测试时注释  正式发布解除注释
        if ((boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false)) {
            partMap.put("token", getRequestBody(SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TOKEN, "").toString(), "multipart/form-data"));
            partMap.put("userName", getRequestBody(SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_CURRENTID, "").toString(), "multipart/form-data"));
            partMap.put("language", getRequestBody(SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString(), "multipart/form-data"));
        }
        return partMap;
    }


    /**
     * 7.请求数据是Json，Json转成RequestBody ；多文件  文件转换
     */
    public static List<MultipartBody.Part> createMultipartBody(List<File> file) {
        if(file!=null&&file.size()>0) {
            List<MultipartBody.Part> multipartBodys = new ArrayList<>(file.size());
            // 创建 RequestBody，用于封装 请求RequestBody
            for (int i = 0; i < file.size(); i++) {
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file.get(i));

// MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part multipartBody =
                        MultipartBody.Part.createFormData("file", file.get(i).getName(), requestFile);
                multipartBodys.add(multipartBody);
            }
            return multipartBodys;
        }
        return null;
    }

    @NonNull
    private static RequestBody getRequestBody(String descriptionString, String s) {
        return RequestBody.create(
                MediaType.parse(s), descriptionString);
    }

}

