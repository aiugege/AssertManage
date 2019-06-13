package com.zjhl.pad.view.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.ModeBanckReq;
import com.zjhl.pad.moudle.entity.res.ModeBanckRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: TellerDetailsManagementActivity.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/5 11:49
 * Changes (from 2018/5/5)
 */
public class TellerDetailsManagementActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    //手机号——不可编辑
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_put_phone_number)
    EditText etPutPhoneNumber;
    @BindView(R.id.bt_phone_modifier)
    TextView btPhoneModifier;

    @BindView(R.id.et_put_email)
    EditText etPutEmial;

    @BindView(R.id.bt_emial_modifier)
    TextView btEmialModifier;
    @BindView(R.id.et_myhoby)
    EditText etMyhoby;
    @BindView(R.id.bt_password_modifier)
    TextView btPasswordModifier;
    @BindView(R.id.bt_sure_commit)
    TextView btSureCommit;
    @BindView(R.id.bt_spinner)
    Spinner btSpinner;
    private String language_item;

    @BindView(R.id.tv_qukuailian)
    TextView tvQukuailian;

    private String usertype;
    private String mremove_id;
    private String mphone_number;
    private String memail;
    private String mpassword;
    private String rname;
    private String mphone;
    private String mmemail;
    private String muserPassword;
    private String lanage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teller_details_management);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvContent.setText(R.string.mine_guiyuan_fenpei);
        Bundle extras = getIntent().getExtras();
        etMyhoby.setTypeface(Typeface.DEFAULT);

        lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

//        if ("cn".equals(lanage)) {
//            bt_spinner_english_zheng.setVisibility(View.VISIBLE);
//            bt_spinner_english.SETv
//        }
//        intent.putExtra("tv_remove",mid);
//        intent.putExtra("rname",mrealName);
//        intent.putExtra("mphone",mphone);
//        intent.putExtra("mmemail",memail);
//        intent.putExtra("muserPassword",muserPassword);
        if (extras != null) {
            //删除某用户的id
            mremove_id = extras.getString("tv_remove");
            rname = extras.getString("rname");
            tvName.setText(rname);
            mphone = extras.getString("mphone");
            etPutPhoneNumber.setText(mphone);
            mmemail = extras.getString("mmemail");
            etPutEmial.setText(mmemail);
            muserPassword = extras.getString("muserPassword");
            etMyhoby.setText(muserPassword);
            String mcertificate = extras.getString("mcertificate");
            tvQukuailian.setText(mcertificate);
            String persion_type = extras.getString("persion_type");
            btSpinner.setVisibility(View.VISIBLE);
            usertype = persion_type;
            if ("2".equals(usertype)) {
                btSpinner.setSelection(0);
            }else if ("3".equals(usertype)) {
                btSpinner.setSelection(1);
            }

        }

        btSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ("cn".equals(lanage)) {
                    String[] languages = getResources().getStringArray(R.array.songs);
                    if (languages != null) {
                        language_item = languages[i];
                        if ("经办".equals(language_item)) {
                            usertype = "2";
                        } else if ("复核".equals(language_item)) {
                            usertype = "3";
                        }
                    }

                } else if ("en".equals(lanage)) {
                    String[] languages_english = getResources().getStringArray(R.array.songs_english_zheng);
                    if (languages_english != null) {
                        language_item = languages_english[i];
                        if ("Transactor".equals(language_item)) {
                            usertype = "2";
                        } else if ("Reviewer".equals(language_item)) {
                            usertype = "3";
                        }
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @OnClick({R.id.iv_excite, R.id.bt_phone_modifier, R.id.bt_emial_modifier, R.id.bt_password_modifier, R.id.bt_sure_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                TellerDetailsManagementActivity.this.finish();
                break;
            case R.id.bt_phone_modifier:
                //修改手机号
//                etPutPhoneNumber.setVisibility(View.VISIBLE);
//                tvPhoneNumber.setVisibility(View.GONE);
                etPutPhoneNumber.setEnabled(true);
                mphone = "";
                break;
            case R.id.bt_emial_modifier:
                //修改邮箱
//                tvEmail.setVisibility(View.GONE);
//                etPutEmial.setVisibility(View.VISIBLE);
                etPutEmial.setEnabled(true);
                mmemail = "";
                break;

            case R.id.bt_password_modifier:
                //修改密码
//                etMyhoby.setVisibility(View.VISIBLE);
//                tvMyhoby.setVisibility(View.GONE);
                etMyhoby.setEnabled(true);
                muserPassword = "";
                break;
            case R.id.bt_sure_commit:
                //姓名
                rname = tvName.getText().toString().trim();
                mphone_number = etPutPhoneNumber.getText().toString().trim();
                memail = etPutEmial.getText().toString().trim();
                mpassword = etMyhoby.getText().toString().trim();
                commitData();
                break;
        }
    }

    private void commitData() {
        ModeBanckReq modebanckReq = new ModeBanckReq();
        if (!TextUtils.isEmpty(mphone)) {
            modebanckReq.setPhone(mphone);
        }
        if (!TextUtils.isEmpty(mphone_number)) {
            modebanckReq.setPhone(mphone_number);
        }

        if (!TextUtils.isEmpty(memail)) {
            modebanckReq.setEmail(memail);
        }
        if (!TextUtils.isEmpty(mmemail)) {
            modebanckReq.setEmail(mmemail);
        }

        modebanckReq.setId(mremove_id);
        if (!TextUtils.isEmpty(muserPassword)) {
            modebanckReq.setUserPassword(mpassword);
        }
        if (!TextUtils.isEmpty(mpassword)) {
            modebanckReq.setUserPassword(mpassword);
        }
        modebanckReq.setUserType(usertype);
        ActionPresenter.getInstance().modeBanckRet(modebanckReq).enqueue(new Callback<ModeBanckRes>() {
            @Override
            public void onResponse(Call<ModeBanckRes> call, Response<ModeBanckRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        ToastUtils.showShort(getString(R.string.toast_teller_lockorunlock));
                        TellerDetailsManagementActivity.this.finish();
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
