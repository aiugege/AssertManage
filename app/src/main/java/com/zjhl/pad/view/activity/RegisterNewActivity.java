package com.zjhl.pad.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.EmailUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.PhoneUtils;
import com.zjhl.pad.app.utils.RandomUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.CheckCompaneyNameReq;
import com.zjhl.pad.moudle.entity.req.RegisterCheckEmailPhoneReq;
import com.zjhl.pad.moudle.entity.req.RegisterDictionaryReq;
import com.zjhl.pad.moudle.entity.req.RegisterReq;
import com.zjhl.pad.moudle.entity.res.CheckCompaneyNameRes;
import com.zjhl.pad.moudle.entity.res.RegisterCheckEmailPhoneRes;
import com.zjhl.pad.moudle.entity.res.RegisterDictionaryRes;
import com.zjhl.pad.moudle.entity.res.RegisterRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: RegisterNextActivity.java 新注册页
 * Author: lzl
 * Version: V1.0
 * Create: 2018/4/17 16:51
 * Changes (from 2018/4/17)
 */
public class RegisterNewActivity extends BaseActivity {


    @BindView(R.id.regist_new_iv_excite)
    ImageView registNewIvExcite;
    @BindView(R.id.regist_iv_logo)
    ImageView registIvLogo;
    @BindView(R.id.regist_new_iv_1)
    ImageView registNewIv1;
    @BindView(R.id.regist_new_rl_select)
    RelativeLayout registNewRlSelect;
    @BindView(R.id.regist_new_iv_2)
    ImageView registNewIv2;
    @BindView(R.id.regist_new_rl_select1)
    RelativeLayout registNewRlSelect1;
    @BindView(R.id.regist_new_ll_type)
    LinearLayout registNewLlType;
    @BindView(R.id.regist_new_company_iv_name)
    TextView registNewCompanyIvName;
    @BindView(R.id.regist_new_company_et_name)
    EditText registNewCompanyEtName;
    @BindView(R.id.regist_new_ll_company)
    LinearLayout registNewLlCompany;
    @BindView(R.id.regist_new_tv_person)
    TextView registNewTvPerson;
    @BindView(R.id.regist_new_et_person)
    EditText registNewEtPerson;
    @BindView(R.id.regist_new_ll_person)
    LinearLayout registNewLlPerson;
    @BindView(R.id.regist_new_tv_phone)
    TextView registNewTvPhone;
    @BindView(R.id.regist_new_et_phone)
    EditText registNewEtPhone;
    @BindView(R.id.regist_new_ll_phone)
    LinearLayout registNewLlPhone;
    @BindView(R.id.regist_new_tv_email)
    TextView registNewTvEmail;
    @BindView(R.id.regist_new_et_email)
    EditText registNewEtEmail;
    @BindView(R.id.regist_new_ll_email)
    LinearLayout registNewLlEmail;
    @BindView(R.id.regist_new_tv_code)
    TextView registNewTvCode;
    @BindView(R.id.regist_new_et_code)
    EditText registNewEtCode;
    @BindView(R.id.regist_new_iv_code)
    ImageView registNewIvCode;
    @BindView(R.id.regist_new_ll_code)
    RelativeLayout registNewLlCode;
    @BindView(R.id.regist_new_tv_letter)
    TextView registNewTvLetter;
    @BindView(R.id.regist_new_et_letter)
    EditText registNewEtLetter;
    @BindView(R.id.regist_new_ll_letter)
    LinearLayout registNewLlLetter;
    @BindView(R.id.regist_new_iv_xieyi)
    ImageView registNewIvXieyi;
    @BindView(R.id.regist_new_tv_xieyi)
    TextView registNewTvXieyi;
    @BindView(R.id.regist_new_tv2_xieyi)
    TextView registNewTv2Xieyi;
    @BindView(R.id.regist_new_ll_xieyi)
    LinearLayout registNewLlXieyi;
    @BindView(R.id.regist_new_tv3_xieyi)
    TextView registNewTv3Xieyi;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.regist_new_tv_button)
    TextView registNewTvButton;
    @BindView(R.id.regist_new_tv_select)
    TextView registNewTvSelect;
    @BindView(R.id.regist_new_tv_select1)
    TextView registNewTvSelect1;

    private String phone;
    private String email;
    private String put_invite_code;
    private String code;
    private String personName;
    private String companyName;
    boolean isChecked = false;
    private AlertDialog myDialog = null;
    private MyCountDownTimer mCDT;
    private boolean isStartTimer = false;
    private Button bt_payButton;
    //协议书否同意标识
    private String on_or_yes_accept;
    private Handler handler = new Handler();
    //图片随机码
    private String imgcode;


    private List<RegisterDictionaryRes.DataBean> comapnyFist;
    private List<RegisterDictionaryRes.DataBean> data;
    private List<String> dataListType;
    private List<String> dataListType2;
    private String[] array;
    private String[] array1;
    private ListView lsvMore;
    String id1 = "";
    String id2 = "";
    String code1 = "";
    String code2 = "";
    private PopupWindow companyWindow;
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_new);
        ButterKnife.bind(this);
        myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();
        if ("cn".equals(lanage)) {
            registNewTv2Xieyi.setVisibility(View.VISIBLE);
            registNewTv3Xieyi.setVisibility(View.GONE);
            registIvLogo.setImageResource(R.drawable.register_cn_newlogo1);
        } else if ("en".equals(lanage)) {
            registNewTv2Xieyi.setVisibility(View.GONE);
            registNewTv3Xieyi.setVisibility(View.VISIBLE);
            registIvLogo.setImageResource(R.drawable.register_en_newlogo1);
        }
        initData();
        initCompayData();
        checkPhone();
        checkEmail();

    }

    private void initData() {
        getImageCode();
        dataListType = new ArrayList<String>();
        dataListType2 = new ArrayList<String>();
    }

    /**
     * 失去焦点，检测手机号
     */
    private void checkPhone() {
        registNewEtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String phone = registNewEtPhone.getText().toString().trim();
                if (b) {

                } else {
                    if (!TextUtils.isEmpty(phone)) {
                        if (PhoneUtils.isPhoneNum(phone)) {
                            correctionRepetition(phone, "");
                        } else {
                            ToastUtils.showShort(getString(R.string.input_legal_phone_number));
                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.register_phone_no));
                    }
                }
            }
        });
    }

    /**
     * 失去焦点，检测邮箱
     */
    private void checkEmail() {
        registNewEtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String email = registNewEtEmail.getText().toString().trim();
                if (b) {

                } else {
                    if (!TextUtils.isEmpty(email)) {
                        if (EmailUtils.isEmail(email)) {
                            correctionRepetition("", email);
                        } else {
                            ToastUtils.showShort(getString(R.string.mine_guiyuan_add_email));
                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.register_email_no));
                    }
                }
            }
        });
    }

    /**
     * 校重（手机号、邮箱）接口
     */
    private void correctionRepetition(String phone, String email) {

        RegisterCheckEmailPhoneReq registerCheckEmailPhoneReq = new RegisterCheckEmailPhoneReq();
        registerCheckEmailPhoneReq.setEmail(email);
        registerCheckEmailPhoneReq.setPhone(phone);
        ActionPresenter.getInstance().registerCheckEmailPhoneRet(registerCheckEmailPhoneReq).enqueue(new Callback<RegisterCheckEmailPhoneRes>() {
            @Override
            public void onResponse(Call<RegisterCheckEmailPhoneRes> call, Response<RegisterCheckEmailPhoneRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {

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

    private Runnable delayRunCheckName = new Runnable() {
        @Override
        public void run() {
            String componeyName = registNewCompanyEtName.getText().toString().trim();
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
                registNewIvXieyi.setBackgroundResource(R.drawable.no_select);
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
                        registNewIvCode.setBackgroundDrawable(new BitmapDrawable(bitmap));

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
                        registNewIvCode.setBackgroundDrawable(new BitmapDrawable(bitmap));

                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("onFailure：", "" + t.getMessage());
                }
            });
        }

    }

    @OnClick({R.id.regist_new_iv_excite, R.id.regist_iv_logo,  R.id.regist_new_tv_login, R.id.regist_new_tv_select, R.id.regist_new_iv_1, R.id.regist_new_rl_select, R.id.regist_new_tv_select1, R.id.regist_new_iv_2, R.id.regist_new_rl_select1, R.id.regist_new_ll_type, R.id.regist_new_company_iv_name, R.id.regist_new_company_et_name, R.id.regist_new_ll_company, R.id.regist_new_tv_person, R.id.regist_new_et_person, R.id.regist_new_ll_person, R.id.regist_new_tv_phone, R.id.regist_new_et_phone, R.id.regist_new_ll_phone, R.id.regist_new_tv_email, R.id.regist_new_et_email, R.id.regist_new_ll_email, R.id.regist_new_tv_code, R.id.regist_new_et_code, R.id.regist_new_iv_code, R.id.regist_new_ll_code, R.id.regist_new_tv_letter, R.id.regist_new_et_letter, R.id.regist_new_ll_letter, R.id.regist_new_iv_xieyi, R.id.regist_new_tv_xieyi, R.id.regist_new_tv2_xieyi, R.id.regist_new_ll_xieyi, R.id.regist_new_tv3_xieyi, R.id.rl_msg, R.id.regist_new_tv_button})
    public void onViewClickedNew(View view) {
        switch (view.getId()) {
            case R.id.regist_new_iv_excite:
                finish();
                break;
            case R.id.regist_iv_logo:
                break;
            case R.id.regist_new_iv_1:
            case R.id.regist_new_tv_select:
                initPopWindow();
                break;
            case R.id.regist_new_tv_login:
                startActivity(new Intent(RegisterNewActivity.this, LoginActivity.class));
                break;
            case R.id.regist_new_rl_select:
                break;
            case R.id.regist_new_iv_2:
            case R.id.regist_new_tv_select1:
                if(!StringUtils.isEmpty(code1)) {
                    initPopWindow2();
                }
                break;
            case R.id.regist_new_rl_select1:
                break;
            case R.id.regist_new_ll_type:
                break;
            case R.id.regist_new_company_iv_name:
                break;
            case R.id.regist_new_company_et_name:
                break;
            case R.id.regist_new_ll_company:
                break;
            case R.id.regist_new_tv_person:
                break;
            case R.id.regist_new_et_person:
                break;
            case R.id.regist_new_ll_person:
                break;
            case R.id.regist_new_tv_phone:
                break;
            case R.id.regist_new_et_phone:
                break;
            case R.id.regist_new_ll_phone:
                break;
            case R.id.regist_new_tv_email:
                break;
            case R.id.regist_new_et_email:
                break;
            case R.id.regist_new_ll_email:
                break;
            case R.id.regist_new_tv_code:
                break;
            case R.id.regist_new_et_code:
                break;
            case R.id.regist_new_iv_code:
                //图片验证码
                getImageCode();
                break;
            case R.id.regist_new_ll_code:
                break;
            case R.id.regist_new_tv_letter:
                break;
            case R.id.regist_new_et_letter:
                break;
            case R.id.regist_new_ll_letter:
                break;
            case R.id.regist_new_iv_xieyi:
                if (isChecked) {
                    registNewIvXieyi.setBackgroundResource(R.drawable.no_select);
                    isChecked = false;
                } else {
                    registNewIvXieyi.setBackgroundResource(R.drawable.yes_select);
                    isChecked = true;
                    //协议dilog
                    payTypeDialog();
                }
                break;
            case R.id.regist_new_tv_xieyi:
                ToastUtils.showShort(getString(R.string.register_select_agree));
                break;
            case R.id.regist_new_tv2_xieyi:
                ToastUtils.showShort(getString(R.string.register_select_agree));
                break;
            case R.id.regist_new_ll_xieyi:
                break;
            case R.id.regist_new_tv3_xieyi:
                break;
            case R.id.rl_msg:
                break;
            case R.id.regist_new_tv_button:
                //企业名称
                companyName = registNewCompanyEtName.getText().toString().trim();
                //联系人姓名
                personName = registNewEtPerson.getText().toString().trim();
                //手机号
                phone = registNewEtPhone.getText().toString().trim();
                //企业邮箱
                email = registNewEtEmail.getText().toString().trim();
                //验证码
                code = registNewEtCode.getText().toString().trim();

                //邀请码
                put_invite_code = registNewEtLetter.getText().toString().trim();

                if (TextUtils.isEmpty(companyName)) {
                    ToastUtils.showShort(getString(R.string.register_no_witer));
                    break;
                }

                if (TextUtils.isEmpty(personName)) {
                    ToastUtils.showShort(getString(R.string.register_name_no));
                    break;
                }

                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showShort(getString(R.string.register_phone_no));
                    break;
                }

                if (!PhoneUtils.isPhoneNum(phone)) {
                    ToastUtils.showShort(getString(R.string.input_legal_phone_number));
                    break;
                }

                if (TextUtils.isEmpty(email)) {
                    ToastUtils.showShort(getString(R.string.register_email_no));
                    break;
                }

                if (!EmailUtils.isEmail(email)) {
                    ToastUtils.showShort(getString(R.string.mine_guiyuan_add_email));
                    break;
                }

                if (TextUtils.isEmpty(code)) {
//                    ToastUtils.showShort(getString(R.string.login_smg_code));
                    ToastUtils.showShort(getString(R.string.login_smg_code));
                    break;
                }


                if (!TextUtils.isEmpty(companyName) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(personName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(code)) {

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

    @OnClick({R.id.regist_new_tv_select, R.id.regist_new_tv_select1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist_new_tv_select:
                break;
            case R.id.regist_new_tv_select1:
                break;
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
        registerReq.setCompanyTypeId1(code1);
        registerReq.setCompanyTypeId2(code2);
        registerReq.setCompanyName(companyName);
        registerReq.setContactName(personName);
        registerReq.setPhone(phone);
        registerReq.setEmail(email);
        registerReq.setInviteCode(put_invite_code);
        registerReq.setAccept(on_or_yes_accept);
//        registerReq.setIdentifyingCode(put_invite_code);
        registerReq.setImgCode(code);
        registerReq.setImgCodeId(imgcode);

        ActionPresenter.getInstance().registerRet(registerReq).enqueue(new Callback<RegisterRes>() {
            @Override
            public void onResponse(Call<RegisterRes> call, Response<RegisterRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        startActivity(new Intent(RegisterNewActivity.this, LoginActivity.class));
                        ToastUtils.showShort(response.body().getMessage());
                        RegisterNewActivity.this.finish();
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

    private void initCompayData() {
        RegisterDictionaryReq registerDictionaryReq = new RegisterDictionaryReq();
        //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
        registerDictionaryReq.setGroupId("institutionType");

        ActionPresenter.getInstance().registerDictionaryRet(registerDictionaryReq).enqueue(new Callback<RegisterDictionaryRes>() {
            @Override
            public void onResponse(Call<RegisterDictionaryRes> call, Response<RegisterDictionaryRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        comapnyFist = response.body().getData();
                        dataListType.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < comapnyFist.size(); i++) {
                            dataListType.add(comapnyFist.get(i).getName());
                        }
                        int size = dataListType.size();
                        array1 = (String[]) dataListType.toArray(new String[size]);
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void initPopWindow() {
        try {
            View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

            lsvMore = (ListView) popupView.findViewById(R.id.list);
            if (dataListType != null) {
                lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, dataListType));
            }

            companyWindow = new PopupWindow(popupView, 260, 300);
            companyWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffffff")));
            companyWindow.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            companyWindow.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            companyWindow.update();
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            companyWindow.showAsDropDown(registNewTvSelect, 0, 20);
            lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    registNewTvSelect.setText(array1[position]);
//                byys.setText(data.get(position).getName());
                    if (comapnyFist.size() > 0) {
                        code1 = comapnyFist.get(position).getCode();
                        //机构第一个id
                        id1 = comapnyFist.get(position).getId();
                    }
                    getSecondeList();
                    companyWindow.dismiss();
                }
            });
        } catch (Exception e) {

        }


    }

    private void initPopWindow2() {
        try {
            View popupView = getLayoutInflater().inflate(R.layout.layout_list2, null);
            lsvMore = (ListView) popupView.findViewById(R.id.list_country);
            if (dataListType2 != null) {
                lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, dataListType2));
            }
            companyWindow = new PopupWindow(popupView, 260, 400);
            companyWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffffff")));
            companyWindow.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            companyWindow.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            companyWindow.update();
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            companyWindow.showAsDropDown(registNewTvSelect1, 0, 20);

            lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    registNewTvSelect1.setText(array[position]);
                    //机构第二个ID
                    id2 = data.get(position).getId();
                    code2 = data.get(position).getCode();
                    companyWindow.dismiss();
                }
            });
        } catch (Exception e) {

        }

    }

    private void getSecondeList() {
        showWaitDialog();
        RegisterDictionaryReq registerDictionaryReq = new RegisterDictionaryReq();
        //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
        registerDictionaryReq.setGroupId(code1);
        ActionPresenter.getInstance().registerDictionaryRet(registerDictionaryReq).enqueue(new Callback<RegisterDictionaryRes>() {
            @Override
            public void onResponse(Call<RegisterDictionaryRes> call, Response<RegisterDictionaryRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        closeWaitDialog();
                        data = response.body().getData();
                        dataListType2.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < data.size(); i++) {
                            dataListType2.add(data.get(i).getName());
                        }
                        int size = dataListType2.size();
                        array = (String[]) dataListType2.toArray(new String[size]);
                        if (data.size() > 0) {
                            registNewTvSelect1.setText(data.get(0).getName());
                            code2 = data.get(0).getCode();
                            id2 = registNewTvSelect1.getText().toString().trim();
                        }

                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}
