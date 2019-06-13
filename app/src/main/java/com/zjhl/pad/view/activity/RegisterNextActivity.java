package com.zjhl.pad.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.RandomUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.CheckCompaneyNameReq;
import com.zjhl.pad.moudle.entity.req.RegisterCheckEmailPhoneReq;
import com.zjhl.pad.moudle.entity.req.RegisterReq;
import com.zjhl.pad.moudle.entity.res.CheckCompaneyNameRes;
import com.zjhl.pad.moudle.entity.res.RegisterCheckEmailPhoneRes;
import com.zjhl.pad.moudle.entity.res.RegisterRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: RegisterNextActivity.java 注册第二页
 * Author: lzl
 * Version: V1.0
 * Create: 2018/4/17 16:51
 * Changes (from 2018/4/17)
 */
public class RegisterNextActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.body_type)
    ImageView bodyType;
    @BindView(R.id.hend)
    LinearLayout hend;
    @BindView(R.id.iv_persion)
    TextView ivPersion;
    //    @BindView(R.id.et_putphone_emial)
//    EditText etPutphoneEmial;
    @BindView(R.id.iv_password_lock)
    TextView ivPasswordLock;
    @BindView(R.id.et_put_relative)
    EditText etPutRelative;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_put_number)
    EditText etPutNumber;
    @BindView(R.id.et_put_enterprise_email)
    EditText etPutPhoneEmail;
    @BindView(R.id.tv_smg)
    TextView tvSmg;
    @BindView(R.id.put_phone_number)
    EditText putPhoneNumber;
    @BindView(R.id.check_msg)
    Button checkMsg;
    @BindView(R.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R.id.et_put_phone_checknumber)
    EditText etPutPhoneChecknumber;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.bt_text)
    TextView btText;
    @BindView(R.id.bt_text1)
    TextView btText1;
    @BindView(R.id.button_find_password_next)
    TextView buttonFindPasswordNext;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.bt_argree)
    ImageView btArgree;

    @BindView(R.id.et_putphone_zhizhao_name)
    EditText etPutphoneZhizhaoName;

    @BindView(R.id.et_put_adress)
    EditText etPutAdress;
    @BindView(R.id.regist_iv_logo)
    ImageView registIvLogo;
    private String phone_number;
    private String enterprise_Email;
    private String put_invite_code;
    private String put_checkCode;
    private String relative_name;
    private String enterprise_name;
    boolean isChecked = false;
    private AlertDialog myDialog = null;
    private MyCountDownTimer mCDT;
    private boolean isStartTimer = false;
    private Button bt_payButton;
    private String jigou1;
    private String jigou2;
    private String country_id;
    private String id_area;
    private String et_put_adress;
    //协议书否同意标识
    private String on_or_yes_accept;
    private Handler handler = new Handler();
    //图片随机码
    private String imgcode;
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regist_next);
        ButterKnife.bind(this);
        myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();
        if ("cn".equals(lanage)) {
            btText.setVisibility(View.VISIBLE);
            btText1.setVisibility(View.GONE);
            registIvLogo.setImageResource(R.drawable.register_cn_newlogo1);
        } else if ("en".equals(lanage)) {
            btText1.setVisibility(View.VISIBLE);
            btText.setVisibility(View.GONE);
            registIvLogo.setImageResource(R.drawable.register_en_newlogo1);
        }
        initData();
        initView();
        etPutphoneZhizhaoName.setFocusable(true);
        etPutphoneZhizhaoName.setFocusableInTouchMode(true);
        etPutphoneZhizhaoName.requestFocus();
    }

    private void initView() {
        /**
         * 检测企业名称
         */
        EditText et_componey = (EditText) findViewById(R.id.et_putphone_zhizhao_name);
        et_componey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (delayRunCheckName != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRunCheckName);
                }
                //延迟2000ms，如果不再输入字符，则执行该线程的run方法
                handler.postDelayed(delayRunCheckName, 5300);
            }
        });
    }


    private void initData() {
        getImageCode();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            //以一个机构
            jigou1 = extras.getString("jigou1");
            jigou2 = extras.getString("jigou2");
            country_id = extras.getString("country");
            id_area = extras.getString("id_area");
        }
    }


    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //手机号
            phone_number = etPutNumber.getText().toString().trim();
            //企业邮箱
            enterprise_Email = etPutPhoneEmail.getText().toString().trim();
            RegisterCheckEmailPhoneReq registerCheckEmailPhoneReq = new RegisterCheckEmailPhoneReq();
            registerCheckEmailPhoneReq.setEmail(enterprise_Email);
            registerCheckEmailPhoneReq.setPhone(phone_number);
            ActionPresenter.getInstance().registerCheckEmailPhoneRet(registerCheckEmailPhoneReq).enqueue(new Callback<RegisterCheckEmailPhoneRes>() {
                @Override
                public void onResponse(Call<RegisterCheckEmailPhoneRes> call, Response<RegisterCheckEmailPhoneRes> response) {
                    if (response != null && response.body() != null) {
                        if (300 == response.body().getCode()) {
                            checkMsg.setEnabled(true);

                        } else if (400 == response.body().getCode()) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else if (500 == response.body().getCode()) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }


                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("onFailure：", "" + t.getMessage());
                }
            });
        }
    };

    private Runnable delayRunCheckName = new Runnable() {
        @Override
        public void run() {
            String componeyName = etPutphoneZhizhaoName.getText().toString().trim();
            //企业名称
            CheckCompaneyNameReq checkCompaneyName = new CheckCompaneyNameReq();
            checkCompaneyName.setCompanyName(componeyName);
            ActionPresenter.getInstance().checkcompaneyRet(checkCompaneyName).enqueue(new Callback<CheckCompaneyNameRes>() {
                @Override
                public void onResponse(Call<CheckCompaneyNameRes> call, Response<CheckCompaneyNameRes> response) {
                    if (response != null && response.body() != null) {
                        if (300 == response.body().getCode()) {
//                            checkMsg.setEnabled(true);
//                            ToastUtils.showShort(response.body().getMessage());
                        } else if (400 == response.body().getCode()) {
                            ToastUtils.showShort(response.body().getMessage());

                        } else if (500 == response.body().getCode()) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }


                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("onFailure：", "" + t.getMessage());
                }
            });
        }
    };


    /**
     * 验证邮箱
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @OnTextChanged(value = R.id.et_put_enterprise_email, callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
    void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @OnTextChanged(value = R.id.et_put_enterprise_email, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @OnTextChanged(value = R.id.et_put_enterprise_email, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s) {
        if (delayRun != null) {
            //每次editText有变化的时候，则移除上次发出的延迟线程
            handler.removeCallbacks(delayRun);
        }
        //延迟2000ms，如果不再输入字符，则执行该线程的run方法
        handler.postDelayed(delayRun, 5300);
    }


    @OnClick({R.id.bt_text, R.id.bt_text1, R.id.button_find_password_next, R.id.check_msg, R.id.bt_argree, R.id.iv_excite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_text:
            case R.id.bt_text1:
                ToastUtils.showShort(getString(R.string.register_select_agree));
                break;

            case R.id.bt_argree:
                if (isChecked) {
                    btArgree.setBackgroundResource(R.drawable.mine_no_select);
                    isChecked = false;

                } else {
                    btArgree.setBackgroundResource(R.drawable.yes_select);
                    isChecked = true;

                    //协议dilog
                    payTypeDialog();
                }
                break;
            case R.id.check_msg:
                //图片验证码
                getImageCode();
                break;
            case R.id.iv_excite:
                finish();
                break;
            /**
             * 点击注册
             */
            case R.id.button_find_password_next:
                //营业执照名称
                enterprise_name = etPutphoneZhizhaoName.getText().toString().trim();
                //联系人姓名
                relative_name = etPutRelative.getText().toString().trim();
                //注册地地址
                et_put_adress = etPutAdress.getText().toString().trim();
                //手机号
                phone_number = etPutNumber.getText().toString().trim();
                //企业邮箱
                enterprise_Email = etPutPhoneEmail.getText().toString().trim();
                //验证码
                put_checkCode = putPhoneNumber.getText().toString().trim();

                //邀请码
                put_invite_code = etPutPhoneChecknumber.getText().toString().trim();

                if (TextUtils.isEmpty(enterprise_name)) {
                    ToastUtils.showShort(getString(R.string.register_no_witer));
                    break;
                }

                if (TextUtils.isEmpty(et_put_adress)) {
                    ToastUtils.showShort(getString(R.string.register_no));
                    break;
                }
                if (TextUtils.isEmpty(relative_name)) {
                    ToastUtils.showShort(getString(R.string.register_name_no));
                    break;
                }
                if (TextUtils.isEmpty(phone_number)) {
                    ToastUtils.showShort(getString(R.string.register_phone_no));
                    break;
                }
                if (TextUtils.isEmpty(enterprise_Email)) {
                    ToastUtils.showShort(getString(R.string.register_email_no));
                    break;
                }
                if (TextUtils.isEmpty(put_checkCode)) {
                    ToastUtils.showShort(getString(R.string.login_smg_code));
                    break;
                }


                if (!TextUtils.isEmpty(enterprise_name) && !TextUtils.isEmpty(et_put_adress) && !TextUtils.isEmpty(relative_name) && !TextUtils.isEmpty(enterprise_Email) && !TextUtils.isEmpty(put_checkCode)) {

                    if (!isChecked) {
                        on_or_yes_accept = "0";
                        ToastUtils.showShort(getString(R.string.register_select_agree));
                        return;
                    } else {
                        on_or_yes_accept = "1";
                        //请求接口
                        getRegisterData();
                    }


                }
                break;
        }
    }

    Window window;
    ImageView iv_close;

    private void payTypeDialog() {
        if (myDialog != null) {
            myDialog.setCancelable(false);
            myDialog.show();
            window = myDialog.getWindow();
        } else {
            myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();
            window = myDialog.getWindow();
            myDialog.setCancelable(false);
        }


        window.setContentView(R.layout.regist_dialogs);
        //计时器
        mCDT = new MyCountDownTimer(30 * 1000, 1000);
        mCDT.start();


        WebView iv_viewView = (WebView) window.findViewById(R.id.webView);
        iv_close = (ImageView) window.findViewById(R.id.iv_close);
        bt_payButton = (Button) window.findViewById(R.id.bt_payButton);
        bt_payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                btArgree.setBackgroundResource(R.drawable.mine_no_select);
                isChecked = true;
                myDialog.dismiss();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                btArgree.setBackgroundResource(R.drawable.no_select);
                isChecked = false;

                mCDT.cancel();
                isStartTimer = false;
            }
        });

        String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();


        //名称
        if ("cn".equals(lanage)) {
            iv_viewView.loadUrl("file:///android_asset/chinese.html");
        } else if ("en".equals(lanage)) {
            iv_viewView.loadUrl("file:///android_asset/english.html");
        }

        iv_viewView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候是控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知
        });
    }

    private void getImageCode() {
        if (TextUtils.isEmpty(MyApplication.imgcode)) {
            imgcode = TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_DATE1) + RandomUtils.getRandomNumbers(4);
            ActionPresenter.getInstance().registerImgRet(MyApplication.imgcode).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
//                        MyLogger.pLog().d("=====" + response.body().toString());
                        MyLogger.pLog().d("=====" + response.body());
                        //下载网络图片
//                        InputStream inputStream = response.body().byteStream();
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        FileUtils.writeFile(new File("/storage/emulated/0/11.jpg"),inputStream);
                        checkMsg.setBackgroundDrawable(new BitmapDrawable(bitmap));

                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("onFailure：", "" + t.getMessage());
                }
            });
        } else {
            imgcode = MyApplication.imgcode;
            ActionPresenter.getInstance().registerImgRet(MyApplication.imgcode).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response != null && response.body() != null) {
//                        MyLogger.pLog().d("=====" + response.body().toString());
                        MyLogger.pLog().d("=====" + response.body());
                        //下载网络图片
//                        InputStream inputStream = response.body().byteStream();
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        FileUtils.writeFile(new File("/storage/emulated/0/11.jpg"),inputStream);
                        checkMsg.setBackgroundDrawable(new BitmapDrawable(bitmap));

                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("onFailure：", "" + t.getMessage());
                }
            });
        }

    }

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            bt_payButton.setText(l / 1000 + "s");
            bt_payButton.setEnabled(false);
//            iv_close.setEnabled(false);
        }

        @Override
        public void onFinish() {
            if (mCDT != null) {
                mCDT.cancel();
                isStartTimer = false;
                bt_payButton.setEnabled(true);
//                iv_close.setEnabled(true);
                bt_payButton.setText(R.string.register_agree);
//                buttonFindPasswordNext.setEnabled(true);
//                if (myDialog!=null){
//                    myDialog.dismiss();
//                }


            }

        }
    }

    private void getRegisterData() {

        RegisterReq registerReq = new RegisterReq();
        registerReq.setCompanyTypeId1(jigou1);
        registerReq.setCompanyTypeId2(jigou2);
        registerReq.setAreaId(id_area);
        registerReq.setCountryId(country_id);
        registerReq.setCompanyName(enterprise_name);
        registerReq.setCompanyDomicile(et_put_adress);
        registerReq.setContactName(relative_name);
        registerReq.setPhone(phone_number);
        registerReq.setEmail(enterprise_Email);
        registerReq.setInviteCode(put_invite_code);
        registerReq.setAccept(on_or_yes_accept);
//        registerReq.setIdentifyingCode(put_invite_code);
        registerReq.setImgCode(put_checkCode);
        registerReq.setImgCodeId(imgcode);

        ActionPresenter.getInstance().registerRet(registerReq).enqueue(new Callback<RegisterRes>() {
            @Override
            public void onResponse(Call<RegisterRes> call, Response<RegisterRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        startActivity(new Intent(RegisterNextActivity.this, LoginActivity.class));
                        ToastUtils.showShort(response.body().getMessage());
                        RegisterNextActivity.this.finish();
                    } else if (response.body().getCode() == 400) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else if (response.body().getCode() == 500) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else {
                        ToastUtils.showShort(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
//                    }
//                });
            }

        });

    }

}
