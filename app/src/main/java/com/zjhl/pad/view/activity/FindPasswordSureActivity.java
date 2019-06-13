package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zjhl.pad.app.utils.DataVerifyUtils;
import com.zjhl.pad.app.utils.EdittextUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.ForgetPassSendCodeReq;
import com.zjhl.pad.moudle.entity.req.ResetPasswordReq;
import com.zjhl.pad.moudle.entity.res.ForgetPassSendCodeRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.ClearEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File:找回密码
 * Author: lzl
 * Version: V1.0
 * Create: 2018/4/11 11:52
 * Changes (from 2018/4/11)
 */
public class FindPasswordSureActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_excite;
    private ClearEditText et_putpassword;
    private EditText et_sure_new_password;
    private Button button_find_password;
    //确认密码
    private String sure_password;

    //新密码
    private String new_password;

    private String met_sure_new_password;
    private String forgetusername;
    private ImageButton imageButton_display_pwd;
    /**
     * 是否明文显示密码的标记，默认false不明文显示
     */
    private boolean isShowPwd = false;
    /**
     * 新密码标示
     */
    private boolean isShowNewPwd = false;
    private ImageButton imageButton_new_display_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword_sure_password);

        initView();
        addListner();
        initData();
    }

    private void initData() {
        if (getIntent().getExtras() != null) {
            forgetusername = getIntent().getExtras().getString("forget_username");
        }
    }

    private void addListner() {
        iv_excite.setOnClickListener(this);
        button_find_password.setOnClickListener(this);
        et_sure_new_password.setOnClickListener(this);
        imageButton_display_pwd.setOnClickListener(this);
        imageButton_new_display_pwd.setOnClickListener(this);
    }

    private void initView() {
        iv_excite = (ImageView) findViewById(R.id.iv_excite);

        //输入新密码
        et_putpassword = (ClearEditText) findViewById(R.id.et_putpassword);

        //输入确认密码
        et_sure_new_password = (EditText) findViewById(R.id.et_sure_new_password);

        button_find_password = (Button) findViewById(R.id.button_find_password);
        imageButton_new_display_pwd = (ImageButton)findViewById(R.id.imageButton_new_display_pwd);
        imageButton_display_pwd = (ImageButton) findViewById(R.id.imageButton_display_pwd);
        iv_excite = (ImageView) findViewById(R.id.iv_excite);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.imageButton_display_pwd:
                //小眼睛切换,点击切换显示密码状态的image
                isShowPwd = EdittextUtils.setVisiblePassword(isShowPwd, et_putpassword);
                imageButton_display_pwd.setImageResource(isShowPwd ? R.drawable.ac_open_eye : R.drawable.ac_eye);

                break;
            case R.id.imageButton_new_display_pwd:
                //再次填写新密码
                isShowNewPwd = EdittextUtils.setVisiblePassword(isShowNewPwd, et_sure_new_password);
                imageButton_new_display_pwd.setImageResource(isShowNewPwd ? R.drawable.ac_open_eye : R.drawable.ac_eye);
                break;
            case R.id.button_find_password:
                new_password = et_putpassword.getText().toString().trim();
                sure_password = button_find_password.getText().toString().trim();
                met_sure_new_password = et_sure_new_password.getText().toString().trim();
//                if (!TextUtils.isEmpty(new_password)) {
//                    if (!DataVerifyUtils.verifyPassword(new_password)) {
//                        ToastUtils.showShort("密码长度8——16位(内含数字、大小写及特殊字符)");
//                        break;
//                    }
//                } else {
//                    ToastUtils.showShort("请输入新密码");
//                    break;
//                }

//                if (!TextUtils.isEmpty(sure_password)) {
//                    if (!DataVerifyUtils.verifyPassword(sure_password)) {
//                        ToastUtils.showShort("密码长度8——16位(内含数字、大小写及特殊字符)");
//                        break;
//                    }
//                } else {
//                    ToastUtils.showShort("请输入确认密码");
//                    break;
//                }
                if (TextUtils.isEmpty(new_password)){
                    ToastUtils.showShort(getString(R.string.password_no_black));
                    break;
                }
                if (TextUtils.isEmpty(sure_password)){
                    ToastUtils.showShort(getString(R.string.password_sure_password_no));
                    break;
                }


                if (!TextUtils.isEmpty(new_password) && !TextUtils.isEmpty(sure_password)) {
                    //请求接口
                    FindPassword();

                }

        }
    }

    private void FindPassword() {

        ResetPasswordReq loginCheckUserPasswordReq1 = new ResetPasswordReq();
        loginCheckUserPasswordReq1.setUserName(forgetusername);
        loginCheckUserPasswordReq1.setNewPwd(new_password);
        loginCheckUserPasswordReq1.setConfirmPwd(met_sure_new_password);
        ActionPresenter.getInstance().SavegudingRet(loginCheckUserPasswordReq1).enqueue(new Callback<ForgetPassSendCodeRes>() {
            @Override
            public void onResponse(Call<ForgetPassSendCodeRes> call, Response<ForgetPassSendCodeRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
                        FindPasswordSureActivity.this.finish();
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));
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
}
