package com.zjhl.pad.presenter.okhttp;

import com.google.gson.TypeAdapter;
import com.zjhl.pad.app.utils.MyLogger;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @desc: DecodeResponseBodyConverter
 * @version: v1.0
 * @packagename: com.zjhl.pad.presenter.okhttp
 * @author: pluto
 * @create: 2018/7/20 10:32
 * @projectname: nnkj
 **/
public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            //解密字符串
            String temp = new String(value.bytes(), "utf-8");
//        String response = value.string();
//            MyLogger.pLog().e("temp=" + temp);
            return adapter.fromJson(temp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}