package com.zjhl.pad.presenter.okhttp;

import com.zjhl.pad.app.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * @desc: RetrofitUtils
 * @version: v1.0
 * @packagename: com.zjhl.pad.okhttp
 * @author: pluto
 * @create: 2018/4/18 11:51
 * @projectname: nnkj
 **/
public class RetrofitUtils {
        public static final String BASE_URL = ""+ Constants.API_ROOT;
        /**
         * 超时时间
         */
        public static final int TIMEOUT = 60;
        private static volatile RetrofitUtils mInstance;
        private Retrofit mRetrofit;

        public static RetrofitUtils getInstance() {
            if (mInstance == null) {
                synchronized (RetrofitUtils.class) {
                    if (mInstance == null) {
                        mInstance = new RetrofitUtils();
                    }
                }
            }
            return mInstance;
        }

        private RetrofitUtils() {
            initRetrofit();
        }

        /**
         * 初始化Retrofit
         */
        private void initRetrofit() {
            //声明日志类
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            //设定日志级别
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(httpLoggingInterceptor);
            // 设置超时
            builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();
            mRetrofit = new Retrofit.Builder()
                    // 设置请求的域名
                    .baseUrl(BASE_URL)
                    // 设置解析转换工厂，用自己定义的
//                    .addConverterFactory(ResponseConvert.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(DecodeConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }

        /**
         * 创建API
         */
        public <T> T create(Class<T> clazz) {
            return mRetrofit.create(clazz);
        }
}
