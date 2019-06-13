package com.zjhl.pad.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.EmailUtils;
import com.zjhl.pad.app.utils.PasswordUtils;
import com.zjhl.pad.app.utils.PhoneUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.TellerManagermentAddReq;
import com.zjhl.pad.moudle.entity.res.ResetPasswordRes;
import com.zjhl.pad.moudle.entity.res.TellerManagermentAddRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.ResetPasswordActivity;
import com.zjhl.pad.view.activity.TellerManagementActivity;
import com.zjhl.pad.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: TellerManagementAddFragment.java 柜员添加
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/27 10:56
 * Changes (from 2018/4/27)
 */
public class TellerManagementAddFragment extends BaseFragment {

    Unbinder unbinder1;
    private Spinner bt_spinner;
    @BindView(R.id.et_putphone_phone)
    EditText etPutphonePhone;
    @BindView(R.id.et_put_relative)
    EditText etPutRelative;
    @BindView(R.id.et_put_email)
    EditText etPutEmail;
    @BindView(R.id.et_put_enterprise_password)
    EditText etPutEnterprisePassword;
    @BindView(R.id.bt_sure_commit)
    TextView btSureCommit;
    Unbinder unbinder;
    private String language_item;
    private int usertype;
    //柜员姓名
    private String pohne_name;
    //手机号
    private String phone_number;
    //邮箱
    private String put_emial;
    //密码
    private String put_password;
    ArrayAdapter<String> adapter;//数组 配置器 下拉菜单赋值用
    String[] arrModel;//转换 数组
    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_tellermanagement_add, null);
        bt_spinner = (Spinner) view.findViewById(R.id.bt_spinner);
        unbinder = ButterKnife.bind(this, view);
        addListner();

        String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
        if ("cn".equals(lanage)) {

        } else if ("en".equals(lanage)) {
            arrModel = new String[]{"Transactor", "Reviewer"};
            //将可选内容与ArrayAdapter连接起来
            adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_select, arrModel);
            bt_spinner.setAdapter(adapter);
        }

//        getDataFromServe();
        return view;
    }


    private void getDataFromServe() {
        TellerManagermentAddReq registerDictionaryReq = new TellerManagermentAddReq();
        //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
//        final String email = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_EMAIL, "");
//        if (!TextUtils.isEmpty(email)){
//            registerDictionaryReq.setUserName("861293572@qq.com");
//        }

        registerDictionaryReq.setRealName(pohne_name);
        registerDictionaryReq.setPhone(phone_number);
        registerDictionaryReq.setEmail(put_emial);
        registerDictionaryReq.setUserPassword(put_password);
        registerDictionaryReq.setUserType(usertype);
//        registerDictionaryReq.

        ActionPresenter.getInstance().AddTellerManager(registerDictionaryReq).enqueue(new Callback<TellerManagermentAddRes>() {
            @Override
            public void onResponse(Call<TellerManagermentAddRes> call, Response<TellerManagermentAddRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        DisPatcher.sendAddmember(getActivity());
                        //柜员姓名
                        etPutphonePhone.setText("");
                        //手机号
                        etPutRelative.setText("");
                        //邮箱
                        etPutEmail.setText("");
                        //密码
                        etPutEnterprisePassword.setText("");
//                        getActivity().finish();
//                        startActivity(new Intent(getContext(), TellerManagementActivity.class).putExtra("teller_position",1));
//                        TellerMa
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

            }
        });
    }


    private void addListner() {

        bt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

                if ("cn".equals(lanage)) {
                    String[] languages = getResources().getStringArray(R.array.songs);
                    if (languages != null) {
                        language_item = languages[i];
                        if ("经办".equals(language_item)) {
                            usertype = 2;
                        } else if ("复核".equals(language_item)) {
                            usertype = 3;
                        }
                    }

                } else if ("en".equals(lanage)) {
                    String[] languages_english = getResources().getStringArray(R.array.songs_english_zheng);
                    if (languages_english != null) {
                        language_item = languages_english[i];
                        if ("Transactor".equals(language_item)) {
                            usertype = 2;
                        } else if ("Reviewer".equals(language_item)) {
                            usertype = 3;
                        }
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.bt_sure_commit)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sure_commit:
                //柜员姓名
                pohne_name = etPutphonePhone.getText().toString().trim();
                //手机号
                phone_number = etPutRelative.getText().toString().trim();
                //邮箱
                put_emial = etPutEmail.getText().toString().trim();
                //密码
                put_password = etPutEnterprisePassword.getText().toString().trim();

                if (TextUtils.isEmpty(pohne_name)) {
                    ToastUtils.showShort(getString(R.string.mine_guiyuan_add_name));
                    break;
                }

                if (TextUtils.isEmpty(phone_number) || !PhoneUtils.isPhoneNum(phone_number)) {
                    ToastUtils.showShort(getString(R.string.toast_mine_guiyuan_add_phonenumber));
                    break;
                }

                if (TextUtils.isEmpty(put_emial) || !EmailUtils.isEmail(put_emial)) {
                    ToastUtils.showShort(getString(R.string.mine_guiyuan_add_email));
                    break;
                }

                if (TextUtils.isEmpty(put_password)) {
                    ToastUtils.showShort(getString(R.string.mine_guiyuan_add_password));
                    break;
                }

                if (!PasswordUtils.isPassword(put_password)) {
                    ToastUtils.showShort(getString(R.string.toast_password_not_format));
                    break;
                }

                if (!TextUtils.isEmpty(pohne_name) && !TextUtils.isEmpty(phone_number) && !TextUtils.isEmpty(put_emial) && !TextUtils.isEmpty(put_password)) {
                    getDataFromServe();
                }
                break;
        }
    }
}
