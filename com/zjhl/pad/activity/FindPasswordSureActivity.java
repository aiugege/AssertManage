package com.zjhl.pad.activity;

import android.content.Intent;
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
* File:找回密码
* Author: DELL 
* Version: V1.0
* Create: 2018/4/11 11:52 
* Changes (from 2018/4/11) 
*/
public class FindPasswordSureActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_back;
    private EditText et_putpassword;
    private EditText et_sure;
    private Button button_find_password;
    //确认密码
    private String sure_password;

    //新密码
    private String new_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_findpassword_sure_password);

        initView();
        addListner();
    }

    private void addListner() {
        iv_back.setOnClickListener(this);
        button_find_password.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView)findViewById(R.id.iv_back);

        //输入新密码
        et_putpassword = (EditText)findViewById(R.id.et_putpassword);

        //输入确认密码
        et_sure = (EditText)findViewById(R.id.et_sure);

        button_find_password = (Button)findViewById(R.id.button_find_password);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                    finish();
                break;

            case R.id.button_find_password:
                 new_password = et_putpassword.getText().toString().trim();
                sure_password = button_find_password.getText().toString().trim();

                if (!TextUtils.isEmpty(new_password)){
                    if (!DataVerifyUtils.verifyPassword(new_password)){
                        showShortToast1("密码长度8——16位(内含数字、大小写及特殊字符)");
                        break;
                    }
                }else {
                    showShortToast1("请输入新密码");
                    break;
                }

                if (!TextUtils.isEmpty(sure_password)){
                    if (!DataVerifyUtils.verifyPassword(sure_password)){
                        showShortToast1("密码长度8——16位(内含数字、大小写及特殊字符)");
                        break;
                    }
                }else {
                    showShortToast1("请输入确认密码");
                    break;
                }

            if (!new_password.equals(sure_password)){
                    showShortToast1("您输入的两次密码不一致");
                    break;
                }

            if (!TextUtils.isEmpty(new_password) && !TextUtils.isEmpty(sure_password)){
                //请求接口
//                FindPassword();
                startActivity(new Intent(FindPasswordSureActivity.this,LoginActivity.class));
                FindPasswordSureActivity.this.finish();
            }

        }
    }

//    private void FindPassword() {
//
//
//    }
}
