package com.zjhl.pad.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.android.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zjhl.pad.constants.Constants;
import com.zjhl.pad.entity.CheckUserPassword;
import com.zjhl.pad.entity.Login;
import com.zjhl.pad.entity.LoginMsgCode;
import com.zjhl.pad.okhttp.JsonGenericsSerializator;
import com.zjhl.pad.utils.DataVerifyUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/*
* FileName: 登录
* Author: DELL
* Version: V1.0
* Create: 2018/4/3 13:17
* Changes (from 2018/4/3)
*/
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    //返回×
    private ImageView iv_excite;
    private TextView textView_register;
    private EditText et_putphone_emial;
    //手机号或邮箱
    private String phone_emial;
    private EditText put_et_password;
    //密码
    private String password;
    private EditText et_getCode;
    //验证码
    private String et_mgetCode;

    private TextView tv_forget_password;
    private Button buttion_getMsg;
    //短信到计时器
    private MyCountDownTimer mCDT;

    private boolean isStartTimer = false;
    //登录按钮
    private Button button_login;
    //验证码有效期
    private long expireDate;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        addListner();
    }

    private void addListner() {
        iv_excite.setOnClickListener(this);
        buttion_getMsg.setOnClickListener(this);
        button_login.setOnClickListener(this);
        textView_register.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
    }

    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //调用验证手机号，密码接口
            getCheckUserPasswordDate();
        }
    };

    private void initView() {
        iv_excite = (ImageView) findViewById(R.id.iv_excite);
        textView_register = (TextView) findViewById(R.id.textView_register);
        et_putphone_emial = (EditText)findViewById(R.id.et_putphone_emial);
        put_et_password = (EditText) findViewById(R.id.put_et_password);
        et_getCode = (EditText) findViewById(R.id.et_getet);
        tv_forget_password = (TextView)findViewById(R.id.tv_forget_password);
        buttion_getMsg = (Button) findViewById(R.id.buttion_getMsg);
        button_login = (Button)findViewById(R.id.button_login);

        /**
         * 监听密码输入完成后调用验证手机号,密码是否正确
         */
        put_et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (delayRun!=null){
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }
                //延迟2000ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRun,5500);
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_excite:
                //点击×返回
                finish();
                break;

            case R.id.buttion_getMsg:
                //手机号或邮箱
                phone_emial = et_putphone_emial.getText().toString().trim();
                //输入密码
                password  = put_et_password.getText().toString().trim();
                //短信验证码
                getSmsCode();


                break;

            case R.id.textView_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            //登录按钮
            case R.id.button_login:
                //手机号或邮箱
                phone_emial = et_putphone_emial.getText().toString().trim();
                //输入密码
                password  = put_et_password.getText().toString().trim();
                //验证码
                et_mgetCode = et_getCode.getText().toString().toString();
                if (TextUtils.isEmpty(phone_emial) || TextUtils.isEmpty(phone_emial)){
                    showShortToast1("请输入邮箱或手机号");
                    break;
                }

                if (TextUtils.isEmpty(password)){
                    showShortToast1("请输入密码");
                    break;
                }

                if (TextUtils.isEmpty(et_mgetCode)){
                    showShortToast1("请输入短信验证码");
                    break;
                }
                if (!TextUtils.isEmpty(phone_emial)&&!TextUtils.isEmpty(password) && !TextUtils.isEmpty(et_mgetCode)){
                    //登录接口
                    getLoginDate();
                }

                break;
            case R.id.tv_forget_password:


                //跳转到忘记密码
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
                break;
        }
    }

    /**
     * 请求的登录接口
     */
    private void getLoginDate() {
        showWaitDialog();
        JSONObject header=new JSONObject();
        header.put("channel",1);
        header.put("language","cn");

        JSONObject body=new JSONObject();
        body.put("userName",phone_emial);
        body.put("userPassword",password);
        body.put("code",et_mgetCode);
        body.put("expireDate",expireDate);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("header",header);
        jsonObject.put("body",body);
        OkHttpUtils
                .postString()
                .url(Constants.API_ROOT + "/user/login")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(String.valueOf(jsonObject))
                .build().connTimeOut(10000).readTimeOut(10000)
                .execute(new GenericsCallback<Login>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Login response, int id) {
                        closeWaitDialog();
                        if (response!=null){
                            String message = response.getMessage();
                            int code = response.getCode();
                            //300成功
                            if (300==code){
                                buttion_getMsg.setEnabled(true);
                                showShortToast1(message);
//                                response.getData().get
                            }else if (400==code){
                                showShortToast1(message);
                            }else if (500==code){
                                showShortToast1(message);
                            }

                        }
                    }
                });
    }


    private void getSmsCode() {
        showWaitDialog();
        JSONObject header=new JSONObject();
        header.put("channel",1);
        header.put("language","cn");

        JSONObject body=new JSONObject();
        body.put("userName",phone_emial);
        body.put("userPassword",password);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("header",header);
        jsonObject.put("body",body);

        //因为这是穿的json格式的，必须加 mediaType(MediaType.parse("application/json; charset=utf-8")
        OkHttpUtils
                .postString()
                .url(Constants.API_ROOT + "/user/sendCode")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(String.valueOf(jsonObject))
                .build().connTimeOut(10000).readTimeOut(10000)
                .execute(new GenericsCallback<LoginMsgCode>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(LoginMsgCode response, int id) {
                        closeWaitDialog();
                        if (response!=null){
                            String message = response.getMessage();
                            int code = response.getCode();
                            if (300 == code){
                                showShortToast1(message);
                                //验证码有效期
                                expireDate = response.getData().getExpireDate();
                                //计时器
                                mCDT = new MyCountDownTimer(60 * 1000, 500);
                                mCDT.start();
                            }else if (400 == code){
                                showShortToast1(message);
                            }else if (500 == code){
                                showShortToast1(message);
                            }
                        }
                    }
                });

    }

    /**
     * 验证登录用户名和密码
     */
    private void getCheckUserPasswordDate() {
        showWaitDialog();
        //手机号或邮箱
        phone_emial = et_putphone_emial.getText().toString().trim();
        //输入密码
        password  = put_et_password.getText().toString().trim();
        JSONObject header = new JSONObject();
        header.put("channel",1);
        header.put("language","cn");

        JSONObject body = new JSONObject();
        body.put("userName",phone_emial);
        body.put("userPassword",password);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("header",header);
        jsonObject.put("body",body);

        OkHttpUtils.postString()
                .url(Constants.API_ROOT + "/user/findByNameAndPwd")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(String.valueOf(jsonObject))
                .build().connTimeOut(10000).readTimeOut(10000).execute(new GenericsCallback<CheckUserPassword>(new JsonGenericsSerializator()){
            @Override
            public void onError(Call call, Exception e, int id) {
                e.getMessage();
            }

            @Override
            public void onResponse(CheckUserPassword response, int id) {
                if (response!=null){
                    closeWaitDialog();
                    String message = response.getMessage();
                    String code = response.getCode();
                    //300成功
                    if ("300".equals(code)){
                        buttion_getMsg.setEnabled(true);
                        showShortToast1(message);
                    }else if ("400".equals(code)){
                        showShortToast1(message);
                    }else if ("500".equals(code)){
                        showShortToast1(message);
                    }
                }

            }
        });
    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            buttion_getMsg.setText(l / 1000 + "s");
        }

        @Override
        public void onFinish() {
            mCDT.cancel();
            mCDT = null;
            isStartTimer = false;
            buttion_getMsg.setText("获取");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCDT != null) {
            mCDT.cancel();
            mCDT=null;
        }

    }
}