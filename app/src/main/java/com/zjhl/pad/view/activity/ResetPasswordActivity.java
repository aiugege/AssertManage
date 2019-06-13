package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.DataVerifyUtils;
import com.zjhl.pad.app.utils.EdittextUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.RegisterDictionaryReq;
import com.zjhl.pad.moudle.entity.req.ResetPasswordReq;
import com.zjhl.pad.moudle.entity.res.RegisterDictionaryRes;
import com.zjhl.pad.moudle.entity.res.ResetPasswordRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.app.application.MyApplication.mMyApplication;

/*
* File:重置密码
* Author: lzl
* Version: V1.0
* Create: 2018/4/12 8:45 
* Changes (from 2018/4/12) 
*/
public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_excite;
    private EditText et_original_putpassword;
    //原始密码
    private  String moriginal_putpassword;
    private EditText et_new_password;
    private String mnew_password;
    private EditText et_reset_sure;
    private String mreset_sure;
    private TextView button_find_password_save;
    private ImageButton imageButton_display_pwd;
    private ImageButton imageButton_new_display_pwd;
    private ImageButton imageButton_sure_new_display_pwd;
    /**
     * 是否明文显示密码的标记，默认false不明文显示
     */
    private boolean isShowPwd = false;
    /**
     * 新密码标示
     */
    private boolean isShowNewPwd = false;
    /**
     * 再次输入确认密码
     */
    private boolean isShowSurePwd = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_password);

        initView();
        addListner();
    }

    private void addListner() {
        iv_excite.setOnClickListener(this);
        imageButton_display_pwd.setOnClickListener(this);
        imageButton_new_display_pwd.setOnClickListener(this);
        imageButton_sure_new_display_pwd.setOnClickListener(this);
        button_find_password_save.setOnClickListener(this);
    }

    private void initView() {
        iv_excite = (ImageView)findViewById(R.id.iv_excite);
        et_original_putpassword = (EditText) findViewById(R.id.et_original_putpassword);
        et_new_password = (EditText)findViewById(R.id.et_new_password);
        et_reset_sure = (EditText)findViewById(R.id.et_sure_new_password);
        button_find_password_save = (TextView)findViewById(R.id.button_find_password_save);
        imageButton_display_pwd = (ImageButton) findViewById(R.id.imageButton_display_pwd);
        imageButton_new_display_pwd = (ImageButton)findViewById(R.id.imageButton_new_display_pwd);
        imageButton_sure_new_display_pwd = (ImageButton)findViewById(R.id.imageButton_sure_new_display_pwd);
        et_original_putpassword.setTypeface(Typeface.DEFAULT);
        et_new_password.setTypeface(Typeface.DEFAULT);
        et_reset_sure.setTypeface(Typeface.DEFAULT);

    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.iv_excite:
                 //返回上个页面
                    finish();
                 break;

                 //原始密码
             case R.id.imageButton_display_pwd:
                 //小眼睛切换,点击切换显示密码状态的image
                 isShowPwd = EdittextUtils.setVisiblePassword(isShowPwd, et_original_putpassword);
                 imageButton_display_pwd.setImageResource(isShowPwd ? R.drawable.ac_open_eye : R.drawable.ac_eye);

                 break;

                 //新密码
             case R.id.imageButton_new_display_pwd:
                 isShowNewPwd = EdittextUtils.setVisiblePassword(isShowNewPwd, et_new_password);
                 imageButton_new_display_pwd.setImageResource(isShowNewPwd ? R.drawable.ac_open_eye : R.drawable.ac_eye);
                 break;

                 //请再次确认新密码
             case R.id.imageButton_sure_new_display_pwd:
                 isShowSurePwd = EdittextUtils.setVisiblePassword(isShowSurePwd,et_reset_sure);
                 imageButton_sure_new_display_pwd.setImageResource(isShowSurePwd ? R.drawable.ac_open_eye : R.drawable.ac_eye);
                 break;
             case R.id.button_find_password_save:
                 //原始密码
                  moriginal_putpassword = et_original_putpassword.getText().toString().trim();
                 //新密码
                  mnew_password = et_new_password.getText().toString().trim();
                  //确认密码
                 mreset_sure = et_reset_sure.getText().toString().trim();

                 if (TextUtils.isEmpty(moriginal_putpassword)){
//                     if (!DataVerifyUtils.verifyPassword(moriginal_putpassword)){
                         ToastUtils.showShort(getString(R.string.toast_resetpassword_oldpass));
                         break;
//                     }
//                 }else {
//                     ToastUtils.showShort("旧密码不正确");
//                     break;
                 }
                 if (!TextUtils.isEmpty(mnew_password)){
                     if (!DataVerifyUtils.verifyPassword(mnew_password)){
                         ToastUtils.showShort(getString(R.string.toast_resetpassword_new));
                         break;
                     }
                 }
// else {
//                     ToastUtils.showShort("请输入新密码");
//                     break;
//                 }
//
//                 if (!TextUtils.isEmpty(mnew_password)){
//                     if (!DataVerifyUtils.verifyPassword(mnew_password)){
//                         ToastUtils.showShort("密码长度8——16位(内含数字、大小写及特殊字符)");
//                         break;
//                     }
//                 }else {
//                     ToastUtils.showShort("请输入新密码");
//                     break;
//                 }
//
                 if (!TextUtils.isEmpty(mreset_sure)){
                     if (!DataVerifyUtils.verifyPassword(mreset_sure)){
                         ToastUtils.showShort(getString(R.string.toast_resetpassword_new));
                         break;
                     }
                 }
                 if (!(mreset_sure).equals(mnew_password)){
                         ToastUtils.showShort(getString(R.string.toast_resetpassword_second));
                         break;
                 }
// else {
//                     ToastUtils.showShort("请输入新密码");
//                     break;
//                 }
//
//                 if (!TextUtils.isEmpty(moriginal_putpassword) && !TextUtils.isEmpty(mnew_password) && !TextUtils.isEmpty(mreset_sure)){
                     //保存新密码
                     saveNewPassword();
//                 }
                 break;

         }
    }


    private void saveNewPassword() {

//        String currentId = (String) SharedPreferanceUtils.get(mMyApplication, Constants.USERINFO.USERINFO_CURRENTID, "");

        ResetPasswordReq registerDictionaryReq = new ResetPasswordReq();
        //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
        final String email = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_EMAIL, "");
        if (!TextUtils.isEmpty(email)){
            registerDictionaryReq.setUserName(email);
        }


        registerDictionaryReq.setNewPwd(mnew_password);
        registerDictionaryReq.setConfirmPwd(mreset_sure);
        registerDictionaryReq.setOldPwd(moriginal_putpassword);

        ActionPresenter.getInstance().LoginAfterResetPassword(registerDictionaryReq).enqueue(new Callback<ResetPasswordRes>() {
            @Override
            public void onResponse(Call<ResetPasswordRes> call, Response<ResetPasswordRes> response) {
                if (response != null && response.body() != null) {
                    int code = response.body().getCode();
                    if (300==code){
                        ToastUtils.showShort(getString(R.string.toast_resetpassword_success));
//                        startActivity(new Intent(ResetPasswordActivity.this,MainActivity.class).putExtra("select_tag","1"));
//                        startActivity(new Intent(ResetPasswordActivity.this,MainActivity2.class).putExtra("select_tag","1"));
                        ResetPasswordActivity.this.finish();
                    } else if(400==code){
                        ToastUtils.showShort(response.body().getMessage());
                    }else  if (500==code){
                        ToastUtils.showShort(response.body().getMessage());
                    }else {
                        ToastUtils.showShort(response.body().getMessage());
                    }

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
