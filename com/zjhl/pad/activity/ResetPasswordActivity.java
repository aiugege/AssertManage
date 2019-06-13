package com.zjhl.pad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.atguigu.android.R;
import com.zjhl.pad.utils.DataVerifyUtils;

/*
* File:重置密码
* Author: DELL 
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
    private Button button_find_password_save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_password);

        initView();
        addListner();
    }

    private void addListner() {
        iv_excite.setOnClickListener(this);
    }

    private void initView() {
        iv_excite = (ImageView)findViewById(R.id.iv_excite);
        et_original_putpassword = (EditText) findViewById(R.id.et_original_putpassword);
        et_new_password = (EditText)findViewById(R.id.et_new_password);
        et_reset_sure = (EditText)findViewById(R.id.et_reset_sure);
        button_find_password_save = (Button)findViewById(R.id.button_find_password_save);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.iv_excite:
                 //返回上个页面
                    finish();
                 break;
             case R.id.button_find_password_save:
                 //原始密码
                  moriginal_putpassword = et_original_putpassword.getText().toString().trim();
                 //新密码
                  mnew_password = et_new_password.getText().toString().trim();
                  //确认密码
                 mreset_sure = et_reset_sure.getText().toString().trim();

                 if (!TextUtils.isEmpty(moriginal_putpassword)){
                     if (!DataVerifyUtils.verifyPassword(moriginal_putpassword)){
                         showShortToast1("密码长度8——16位(内含数字、大小写及特殊字符)");
                         break;
                     }
                 }else {
                     showShortToast1("请输入原始密码");
                     break;
                 }


                 if (!TextUtils.isEmpty(mnew_password)){
                     if (!DataVerifyUtils.verifyPassword(mnew_password)){
                         showShortToast1("密码长度8——16位(内含数字、大小写及特殊字符)");
                         break;
                     }
                 }else {
                     showShortToast1("请输入新密码");
                     break;
                 }

                 if (!TextUtils.isEmpty(mnew_password)){
                     if (!DataVerifyUtils.verifyPassword(mnew_password)){
                         showShortToast1("密码长度8——16位(内含数字、大小写及特殊字符)");
                         break;
                     }
                 }else {
                     showShortToast1("请输入新密码");
                     break;
                 }

                 if (!TextUtils.isEmpty(mreset_sure)){
                     if (!DataVerifyUtils.verifyPassword(mreset_sure)){
                         showShortToast1("密码长度8——16位(内含数字、大小写及特殊字符)");
                         break;
                     }
                 }else {
                     showShortToast1("请输入新密码");
                     break;
                 }

                 if (!TextUtils.isEmpty(moriginal_putpassword) && !TextUtils.isEmpty(mnew_password) && !TextUtils.isEmpty(mreset_sure)){
                     //保存新密码
//                     saveNewPassword();
                 }
                 break;

         }
    }

//    private void saveNewPassword() {
//
//    }
}
