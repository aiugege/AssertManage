package com.zjhl.pad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.android.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zjhl.pad.constants.Constants;
import com.zjhl.pad.entity.LoginMsgCode;
import com.zjhl.pad.okhttp.JsonGenericsSerializator;

import okhttp3.MediaType;

/*
* FileName: 忘记密码
* Author: LZL
* Version: V1.0
* Create: 2018/4/9 19:23 
* Changes (from 2018/4/9) 
*/
public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_back;
    private Button buttion_getMsg;
    private Button button_forget_password;
    private EditText et_putphone_emial;
    private EditText getMsgCode;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
        addListner();
    }

    private void addListner() {
        iv_back.setOnClickListener(this);
        button_forget_password.setOnClickListener(this);
    }

    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //调用验证手机号，密码接口
//            getCheckUserPasswordDate();
        }
    };
    private void initView() {
        //返回上一级
        iv_back = (ImageView)findViewById(R.id.iv_back);
        //获取短信验证码
        buttion_getMsg = (Button) findViewById(R.id.buttion_getMsg);
        //忘记密码提交
        button_forget_password = (Button)findViewById(R.id.button_forget_password);
        //输入邮箱或手机号
        et_putphone_emial = (EditText)findViewById(R.id.et_putphone_emial);
        getMsgCode = (EditText)findViewById(R.id.getMsgCode);

        et_putphone_emial.addTextChangedListener(new TextWatcher() {
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
                handler.postDelayed(delayRun,3000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_excite:
                //返回到我的吗？
                finish();
                break;
            case R.id.buttion_getMsg:
                //获取短信验证码
                getNextMsgCode();
                break;
            case R.id.iv_back:
                ForgetPasswordActivity.this.finish();
                break;
            case R.id.button_forget_password:
                //点击下一步
                String mputphone_emial = et_putphone_emial.getText().toString().trim();
                //短信验证码
                String mMsgCode = getMsgCode.getText().toString().trim();

                if (TextUtils.isEmpty(mputphone_emial)){
                    showShortToast1("请输入邮箱或手机号");
                }

                if (TextUtils.isEmpty(mMsgCode)){
                    showShortToast1("请输入验证码");
                }

                //如果手机号密码都不为空请求下一步接口
                if (!TextUtils.isEmpty(mputphone_emial) && !TextUtils.isEmpty(mMsgCode)){
//                        getNextSave();
                        startActivity(new Intent(ForgetPasswordActivity.this,FindPasswordSureActivity.class));
                }
                break;
        }
    }

//    private void getNextSave() {
//        OkHttpUtils
//                .postString()
//                .url(Constants.API_ROOT + "/user/validBackPwdCode")
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .content(String.valueOf(jsonObject))
//                .build().connTimeOut(10000).readTimeOut(10000)
//                .execute(new GenericsCallback<LoginMsgCode>(new JsonGenericsSerializator())
//    }

    private void getNextMsgCode() {
        String mputphone_emial = et_putphone_emial.getText().toString().trim();
        JSONObject header=new JSONObject();
        header.put("channel",1);
        header.put("language","cn");
//        header.put("currentId",);
//        header.put("token",);

        JSONObject body=new JSONObject();
        body.put("userName",mputphone_emial);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("header",header);
        jsonObject.put("body",body);

//        OkHttpUtils
//                .postString()
//                .url(Constants.API_ROOT + "/user/backPwd")
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .content(String.valueOf(jsonObject))
//                .build().connTimeOut(10000).readTimeOut(10000)
//                .execute(new GenericsCallback<LoginMsgCode>(new JsonGenericsSerializator())
    }
}
