package com.zjhl.pad.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.zjhl.pad.app.utils.EmailUtils;
import com.zjhl.pad.app.utils.NumberUtils;
import com.zjhl.pad.app.utils.PermissionDialog;
import com.zjhl.pad.app.utils.PermissionsManager;
import com.zjhl.pad.app.utils.PhoneUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.ForgetPassSendCodeReq;
import com.zjhl.pad.moudle.entity.req.LoginCheckUserPasswordReq;
import com.zjhl.pad.moudle.entity.res.ForgetPassNextRes;
import com.zjhl.pad.moudle.entity.res.ForgetPassSendCodeRes;
import com.zjhl.pad.moudle.entity.res.LoginMsgCodeRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * FileName: 忘记密码
 * Author: LZL
 * Version: V1.0
 * Create: 2018/4/9 19:23
 * Changes (from 2018/4/9)
 */
public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    //    @BindView(R.id.iv_back)
    private ImageView iv_back;
    private Button buttion_getMsg;
    private TextView button_forget_password;
    private EditText et_putphone_emial;
    private EditText getMsgCode;

    private Handler handler = new Handler();
    private PermissionDialog permissionDialog;
    //短信到计时器
    private MyCountDownTimer mCDT;
    private boolean isStartTimer = false;
    private String mMsgCode;
    private String mputphone_emial;
    private Boolean isPhoneOrEmailLegal = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
        getPermission();
        addListner();
        checkUserName();
    }

    private void getPermission() {
        boolean isAgree = checkPermission_v4(new PermissionsManager.PermissionListener() {

            @Override
            public void getRequestPermissionCallBack(String[] permissions, boolean isAgree) {
                if (!isAgree) {
                    final String[] p = permissions;
                    if (permissionDialog == null)
                        permissionDialog = new PermissionDialog(ForgetPasswordActivity.this, new PermissionDialog.PermissionCheckListener() {

                            @Override
                            public void userChoosed(boolean isChooseOk) {
                                if (isChooseOk) {
                                    checkShouldShowPermission(p);
                                    getPermission();
                                } else {
                                    finish();
                                }
                            }
                        });
                    permissionDialog.show();
                }
            }
        }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION);
    }


    private void addListner() {
        iv_back.setOnClickListener(this);
        button_forget_password.setOnClickListener(this);
        buttion_getMsg.setOnClickListener(this);
    }

    private void initView() {
//        ButterKnife.bind(this);
        //返回上一级
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //获取短信验证码
        buttion_getMsg = (Button) findViewById(R.id.buttion_getMsg);
        //忘记密码提交
        button_forget_password = (TextView) findViewById(R.id.button_forget_password);
        //输入邮箱或手机号
        et_putphone_emial = (EditText) findViewById(R.id.et_putphone_emial);
        getMsgCode = (EditText) findViewById(R.id.getMsgCode);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                mputphone_emial = et_putphone_emial.getText().toString().trim();
                //短信验证码
                mMsgCode = getMsgCode.getText().toString().trim();

                if (!isPhoneOrEmailLegal) {
                    break;
                }

                if (TextUtils.isEmpty(mMsgCode)||mMsgCode.length()!=4) {
                    ToastUtils.showShort(getString(R.string.login_smg_code));
                    break;
                }

                //如果手机号密码都不为空请求下一步接口
                if (!TextUtils.isEmpty(mputphone_emial) && !TextUtils.isEmpty(mMsgCode)) {
                    getNextSave();

                }
                break;
        }
    }

    private void getNextSave() {
        ForgetPassSendCodeReq loginCheckUserPasswordReq1 = new ForgetPassSendCodeReq();
        loginCheckUserPasswordReq1.setUserName(mputphone_emial);
//        loginCheckUserPasswordReq1.setUserName(mputphone_emial);
        loginCheckUserPasswordReq1.setCode(mMsgCode);
        ActionPresenter.getInstance().forgetPassNextRet(loginCheckUserPasswordReq1).enqueue(new Callback<ForgetPassNextRes>() {
            @Override
            public void onResponse(Call<ForgetPassNextRes> call, Response<ForgetPassNextRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
                        startActivity(new Intent(ForgetPasswordActivity.this, FindPasswordSureActivity.class).putExtra("forget_username", mputphone_emial));
                        ForgetPasswordActivity.this.finish();
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
            }
        });
    }

    private void getNextMsgCode() {
        String mputphone_emial = et_putphone_emial.getText().toString().trim();
        ForgetPassSendCodeReq loginCheckUserPasswordReq1 = new ForgetPassSendCodeReq();
        loginCheckUserPasswordReq1.setUserName(mputphone_emial);
        ActionPresenter.getInstance().forgetPassSendCodeRet(loginCheckUserPasswordReq1).enqueue(new Callback<ForgetPassSendCodeRes>() {
            @Override
            public void onResponse(Call<ForgetPassSendCodeRes> call, Response<ForgetPassSendCodeRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
                        //计时器
                        mCDT = new MyCountDownTimer(180 * 1000, 500);
                        mCDT.start();
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
            }
        });
    }

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            buttion_getMsg.setText(l / 1000 + "s");
            buttion_getMsg.setEnabled(false);
        }

        @Override
        public void onFinish() {
            if (mCDT != null) {
                mCDT.cancel();
            } else {
                mCDT = null;
            }
            isStartTimer = false;
            buttion_getMsg.setText("获取");
            buttion_getMsg.setEnabled(true);
        }
    }

    private void checkUserName() {
        et_putphone_emial.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String userName = et_putphone_emial.getText().toString().trim();
                int len = userName.length();
                if (b) {

                } else {
                    if (!TextUtils.isEmpty(userName)) {
                        if (userName.length() > 11) {
                            if (EmailUtils.isEmail(userName)) {
                                isPhoneOrEmailLegal = true;
                            } else {
                                isPhoneOrEmailLegal =false;
                                ToastUtils.showShort(getString(R.string.mine_guiyuan_add_email));
                            }
                        } else {
                            if (NumberUtils.isNumber(userName)) {
                                if (PhoneUtils.isPhoneNum(userName)) {
                                    isPhoneOrEmailLegal = true;
                                } else {
                                    isPhoneOrEmailLegal =false;
                                    ToastUtils.showShort(getString(R.string.input_legal_phone_number));
                                }
                            } else {
                                if (EmailUtils.isEmail(userName)) {
                                    isPhoneOrEmailLegal = true;
                                } else {
                                    isPhoneOrEmailLegal =false;
                                    ToastUtils.showShort(getString(R.string.mine_guiyuan_add_email));
                                }
                            }

                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.login_emial_phone));
                    }

                }


            }
        });
    }
}
