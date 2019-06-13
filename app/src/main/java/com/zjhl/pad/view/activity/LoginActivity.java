package com.zjhl.pad.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.EmailUtils;
import com.zjhl.pad.app.utils.ExampleUtil;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.NumberUtils;
import com.zjhl.pad.app.utils.PermissionDialog;
import com.zjhl.pad.app.utils.PermissionsManager;
import com.zjhl.pad.app.utils.PhoneUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TagAliasOperatorHelper;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.IussingFactoringEntryReq;
import com.zjhl.pad.moudle.entity.req.LoginCheckUserPasswordReq;
import com.zjhl.pad.moudle.entity.req.LoginReq;
import com.zjhl.pad.moudle.entity.res.CheckControlRes;
import com.zjhl.pad.moudle.entity.res.LoginCheckUserPasswordRes;
import com.zjhl.pad.moudle.entity.res.LoginMsgCodeRes;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.app.application.MyApplication.mMyApplication;
import static com.zjhl.pad.app.utils.TagAliasOperatorHelper.ACTION_SET;
import static com.zjhl.pad.app.utils.TagAliasOperatorHelper.DELAY_SEND_ACTION;
import static com.zjhl.pad.app.utils.TagAliasOperatorHelper.DELAY_SET_MOBILE_NUMBER_ACTION;


/*
 * FileName: 登录
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/3 13:17
 * Changes (from 2018/4/3)
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.textView_register)
    TextView textViewRegister;
    @BindView(R.id.iv_persion)
    ImageView ivPersion;

    @BindView(R.id.iv_password_lock)
    ImageView ivPasswordLock;

    @BindView(R.id.iv_msg)
    ImageView ivMsg;

    @BindView(R.id.buttion_getMsg)
    Button buttionGetMsg;
    @BindView(R.id.button_login)
    TextView buttonLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.et_putphone_emial)
    EditText etPutphoneEmial;
    @BindView(R.id.put_et_password)
    EditText putEtPassword;
    @BindView(R.id.et_getet)
    EditText etGetet;
    @BindView(R.id.zhongjin_logo)
    ImageView zhongjinLogo;

    private Handler handler = new Handler();
    private String phone_emial;
    private String password;
    private String et_mgetCode;
    //短信到计时器
    private MyCountDownTimer mCDT;
    //验证码有效期
    private long expireDate;
    private boolean isStartTimer = false;
    private PermissionDialog permissionDialog;
    private String updatepwdFlag;
    private String currentId;
    boolean isAliasAction = false;
    public static int sequence = 1;
    int action = -1;
    String alias = null;
    Set<String> tags = null;
    private SparseArray<Object> setActionCache = new SparseArray<Object>();
    private Context context;
    private String companyId;
    private String userType;
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
    private Boolean isPhoneOrEmailLegal = false;
    private Boolean isCheckUsernamePass = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getPermission();
        init(LoginActivity.this);
        EditText viewById = (EditText) findViewById(R.id.et_putphone_emial);
        initListener(putEtPassword);
        putEtPassword.setTypeface(Typeface.DEFAULT);
        /*viewById.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String etpassword = putEtPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(etpassword)) {
                    if (delayRun != null) {
                        //每次editText有变化的时候，则移除上次发出的延迟线程
                        handler.removeCallbacks(delayRun);
                    }
                    //延迟2000ms，如果不再输入字符，则执行该线程的run方法
                    handler.postDelayed(delayRun, 5000);
                }

            }
        });*/

        if ("cn".equals(lanage)) {
            zhongjinLogo.setImageResource(R.drawable.register_cn_newlogo1);
        } else if ("en".equals(lanage)) {
            zhongjinLogo.setImageResource(R.drawable.register_en_newlogo1);
        }
        checkUserName();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //未登录状态  status 默认false  token 默认"" currentid默认"" companyName默认"" realName默认"" companyId默认"" email默认"" userType默认""
        mMyApplication.UpdateUserInfo(false, "", "", "", "", "", "", "");
        //退出登录 刷新消息数量
        DisPatcher.sendSystemMessageCount(this);
        //退出登录 刷新消息
        DisPatcher.sendSystemMessage(this);
        MyLogger.pLog().d("退出登录停止接受消息");
        JPushInterface.stopPush(MyApplication.mMyApplication);
        //删除标签
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = TagAliasOperatorHelper.ACTION_DELETE;
        tagAliasBean.isAliasAction = true;
        TagAliasOperatorHelper.getInstance().handleAction(MyApplication.mMyApplication, 1, tagAliasBean);
    }

    public void init(Context context) {
        if (context != null) {
            this.context = context.getApplicationContext();
        }
    }

    public void initListener(final EditText view) {
        view.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String viewtext = view.getText().toString();
                if (hasFocus) {

                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    if (!isCheckUsernamePass) {
                        //还未验证用户名密码
                        checkUserNamePassRet();
                    }

                }
            }
        });
    }

    private Handler delaySendHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DELAY_SEND_ACTION:
                    if (msg.obj != null && msg.obj instanceof TagAliasOperatorHelper.TagAliasBean) {
                        sequence++;
                        TagAliasOperatorHelper.TagAliasBean tagAliasBean = (TagAliasOperatorHelper.TagAliasBean) msg.obj;
                        setActionCache.put(sequence, tagAliasBean);
                        if (context != null) {
                            handleAction(context, sequence, tagAliasBean);
                        } else {
                        }
                    } else {
                    }
                    break;
                case DELAY_SET_MOBILE_NUMBER_ACTION:
                    if (msg.obj != null && msg.obj instanceof String) {
                        sequence++;
                        String mobileNumber = (String) msg.obj;
                        setActionCache.put(sequence, mobileNumber);
//                        if(context !=null) {
//                            handleAction(context, sequence, mobileNumber);
//                        }else {
//                            Logger.e(TAG, "#unexcepted - context was null");
//                        }
                    } else {
                    }
                    break;
            }
        }
    };

    private void getPermission() {
        boolean isAgree = checkPermission_v4(new PermissionsManager.PermissionListener() {

            @Override
            public void getRequestPermissionCallBack(String[] permissions, boolean isAgree) {
                if (!isAgree) {
                    final String[] p = permissions;
                    if (permissionDialog == null)
                        permissionDialog = new PermissionDialog(LoginActivity.this, new PermissionDialog.PermissionCheckListener() {

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
//        if (isAgree) {
//            init();
//            initThirdList();
//            getData();
//        }
    }
/*


    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //调用验证手机号，密码接口
            phone_emial = etPutphoneEmial.getText().toString().trim();
            //输入密码
            password = putEtPassword.getText().toString().trim();

            LoginCheckUserPasswordReq loginCheckUserPasswordReq = new LoginCheckUserPasswordReq();
            loginCheckUserPasswordReq.setUserName(phone_emial);
            loginCheckUserPasswordReq.setUserPassword(password);
            ActionPresenter.getInstance().loginCheckUserPasswordRet(loginCheckUserPasswordReq).enqueue(new Callback<LoginCheckUserPasswordRes>() {
                @Override
                public void onResponse(Call<LoginCheckUserPasswordRes> call, Response<LoginCheckUserPasswordRes> response) {

                    if (response != null && response.body() != null) {
                        String message = response.body().getMessage();
                        int code = response.body().getCode();
                        //300成功
                        if (300 == code) {
                            buttionGetMsg.setEnabled(true);
                            ToastUtils.showShort(getString(R.string.toast_login_check_success));
                        } else if (400 == code) {
                            ToastUtils.showShort(message);
                        } else if (500 == code) {
                            ToastUtils.showShort(message);
                        } else {
                            ToastUtils.showShort(message);
                        }

                    }
                    //token超时 重置用户信息
//                        MyApplication.mMyApplication.UpdateUserInfo(true,response.body().gettoken,getcurrentid);
                }

                @Override
                public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：",""+t.getMessassge());
                }
            });

        }
    };

    @OnTextChanged(value = R.id.put_et_password, callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
    void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @OnTextChanged(value = R.id.put_et_password, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @OnTextChanged(value = R.id.put_et_password, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s) {
        if (delayRun != null) {
            //每次editText有变化的时候，则移除上次发出的延迟线程
            handler.removeCallbacks(delayRun);
        }
        //延迟2000ms，如果不再输入字符，则执行该线程的run方法
        handler.postDelayed(delayRun, 5000);
    }
*/

//    etPutphoneEmial.


    @OnClick({R.id.iv_excite, R.id.buttion_getMsg, R.id.button_login, R.id.textView_register, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                LoginActivity.this.finish();
                break;
            case R.id.buttion_getMsg:
                //获取验证码
                //手机号或邮箱
                phone_emial = etPutphoneEmial.getText().toString().trim();
                //输入密码
                password = putEtPassword.getText().toString().trim();
                if (isLegalUserName() && !TextUtils.isEmpty(password)) {
                    checkUserNamePassRetAndGetSmsCode();
                    break;
                }
                //短信验证码
                if (isCheckUsernamePass) {
                    getSmsCode();
                    break;
                }
            case R.id.button_login:
                //登录
                //手机号或邮箱
                phone_emial = etPutphoneEmial.getText().toString().trim();
                //输入密码
                password = putEtPassword.getText().toString().trim();
                //验证码
                et_mgetCode = etGetet.getText().toString().toString();
//                if (TextUtils.isEmpty(phone_emial) || TextUtils.isEmpty(phone_emial)) {
//                    ToastUtils.showShort(getString(R.string.login_emial_phone));
//                    break;
//                }
                if (!isPhoneOrEmailLegal) {
                    break;
                }

                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort(getString(R.string.login_password));
                    break;
                }

                if (TextUtils.isEmpty(et_mgetCode) || et_mgetCode.length() != 4) {
                    ToastUtils.showShort(getString(R.string.login_smg_code));
                    break;
                }
                if (!TextUtils.isEmpty(phone_emial) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(et_mgetCode)) {
                    //登录接口
                    getLoginDate();
                }

                break;
            case R.id.textView_register:
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                DisPatcher.startRegisterNewActivity(this);
                break;

            case R.id.tv_forget_password:
                //跳转到忘记密码
//                startActivity(new Intent(LoginActivity.this,MyQuoteActivity));
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
        }
    }


    private void getLoginDate() {

        LoginReq loginReq = new LoginReq();
        loginReq.setUserName(phone_emial);
        loginReq.setUserPassword(password);
        loginReq.setCode(et_mgetCode);
        ActionPresenter.getInstance().loginRet(loginReq).enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                MyLogger.pLog().d("LoginRes：" + response.body().toString());
                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        LoginRes.DataBean currentData = response.body().getData();
                        if (currentData != null) {
                            String email = currentData.getEmail();
                            userType = currentData.getUserType();
                            currentId = currentData.getCurrentId();
                            //机构id
                            companyId = currentData.getCompanyId();
                            //机构状态
                            int checkState = currentData.getStatus();
                            //公司名字
                            String companyName = currentData.getCompanyName();
                            //用户姓名
                            String realName = currentData.getRealName();

                            SharedPreferanceUtils.put(LoginActivity.this, Constants.USERINFO.USERINFO_COMPANYNAME, companyName);
                            SharedPreferanceUtils.put(LoginActivity.this, Constants.USERINFO.USERINFO_USERNAME, realName);
                            SharedPreferanceUtils.put(LoginActivity.this, Constants.USERINFO.USERINFO_CURRENTID, currentId);
                            SharedPreferanceUtils.put(LoginActivity.this, Constants.USERINFO.USERINFO_COMPANYID, companyId);
                            SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_EMAIL, email);
                            SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, userType);

                            //登录 刷新消息
                            DisPatcher.sendSystemMessageCount(LoginActivity.this);
                            if (String.valueOf(checkState).equals("101") || String.valueOf(checkState).equals("102") ||
                                    String.valueOf(checkState).equals("104") || String.valueOf(checkState).equals("105") ||
                                    String.valueOf(checkState).equals("107") || String.valueOf(checkState).equals("108") || String.valueOf(checkState).equals("110")) {
//                               ToastUtils.showShort("资料补充已完成，平台会在1-3工作日内对您的资料进行审核，审核通过后会已邮件形式通知到您，请您耐心等待");
                            }
                            //是否是第一次登录 0是 1-否
                            String firstLogin = "1";
                            firstLogin = currentData.getFirstLogin();
                            if (!TextUtils.isEmpty(userType)) {
                                if ("1".equals(userType) && !TextUtils.isEmpty(firstLogin) && firstLogin.equals("0")) {
                                    ToastUtils.showShort(getString(R.string.login_message));
                                }
                            }
                            //三个月是否登录过
                            String threeMonthUpdate = currentData.getThreeMonthUpdate();
                            if ("0".equals(threeMonthUpdate)) {
                                ToastUtils.showShort(getString(R.string.login_relog));
                            }

                            String inPutAlias = getInPutAlias();
                            if (TextUtils.isEmpty(inPutAlias)) {
                                return;
                            } else {
                                inPutAlias = "ALIAS_" + inPutAlias;
                            }
                            isAliasAction = true;
                            action = ACTION_SET;

                            TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
                            tagAliasBean.action = action;
                            sequence++;
                            if (isAliasAction) {
                                tagAliasBean.alias = inPutAlias;
                            } else {
                                tagAliasBean.tags = tags;
                            }
                            tagAliasBean.isAliasAction = isAliasAction;
                            TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);
                            MyLogger.pLog().d("判断接受消息是否停止");
                            if (JPushInterface.isPushStopped(MyApplication.mMyApplication)) {
                                MyLogger.pLog().d("登录成功重新启用接受消息");
                                JPushInterface.resumePush(MyApplication.mMyApplication);
                            }

                            //是否强制用户修改密码 0强制 1不强制
                            updatepwdFlag = currentData.getUpdatepwdFlag();

                        }

                        //保存用户信息
                        MyApplication.mMyApplication.UpdateUserInfo(true, response.body().getData().getToken(), response.body().getData().getCurrentId() + "", response.body());
//                        SharedPreferanceUtils.put(LoginActivity.this,"userType",userType);
                        if (!TextUtils.isEmpty(updatepwdFlag) && updatepwdFlag.equals("0")) {
                            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                            LoginActivity.this.finish();
                        } else {
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("select_main", "1"));
//                            startActivity(new Intent(LoginActivity.this, MainActivity2.class).putExtra("select_main", "1"));
                            MainActivity2.reStart(LoginActivity.this);
                            LoginActivity.this.finish();
                        }
                        //1；管理员；2：操作经办员；3：操作复核员） 除管理员外其他都可当普通用户处理
//                        if ("0".equals(updatepwdFlag) && "1".equals(userType)) {
//                            ToastUtils.showShort(getString(R.string.login_message));
//                        }
                        checkControlRet();

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

    /**
     * 处理设置tag
     */
    public void handleAction(Context context, int sequence, TagAliasOperatorHelper.TagAliasBean tagAliasBean) {
        init(context);
        if (tagAliasBean == null) {
            return;
        }
        put(sequence, tagAliasBean);
        if (tagAliasBean.isAliasAction) {
            switch (tagAliasBean.action) {

                case ACTION_SET:
                    JPushInterface.setAlias(context, sequence, tagAliasBean.alias);
                    break;
                default:
                    return;
            }
        } else {
            switch (tagAliasBean.action) {

                case ACTION_SET:
                    JPushInterface.setTags(context, sequence, tagAliasBean.tags);
                    break;

                default:
                    return;
            }
        }
    }


    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        init(context);
        //根据sequence从之前操作缓存中获取缓存记录
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = (TagAliasOperatorHelper.TagAliasBean) setActionCache.get(sequence);
        if (tagAliasBean == null) {
//            ExampleUtil.showToast("获取缓存记录失败", context);
            return;
        }
        if (jPushMessage.getErrorCode() == 0) {
            setActionCache.remove(sequence);
            String logs = getActionStr(tagAliasBean.action) + " alias success";
            ExampleUtil.showToast(logs, context);
        } else {
            String logs = "Failed to " + getActionStr(tagAliasBean.action) + " alias, errorCode:" + jPushMessage.getErrorCode();
            if (!RetryActionIfNeeded(jPushMessage.getErrorCode(), tagAliasBean)) {
                ExampleUtil.showToast(logs, context);
            }
        }
    }

    private boolean RetryActionIfNeeded(int errorCode, TagAliasOperatorHelper.TagAliasBean tagAliasBean) {
        if (!ExampleUtil.isConnected(context)) {
            return false;
        }
        //返回的错误码为6002 超时,6014 服务器繁忙,都建议延迟重试
        if (errorCode == 6002 || errorCode == 6014) {
            if (tagAliasBean != null) {
                Message message = new Message();
                message.what = DELAY_SEND_ACTION;
                message.obj = tagAliasBean;
                delaySendHandler.sendMessageDelayed(message, 1000 * 60);
                String logs = getRetryStr(tagAliasBean.isAliasAction, tagAliasBean.action, errorCode);
                ExampleUtil.showToast(logs, context);
                return true;
            }
        }
        return false;
    }

    private String getRetryStr(boolean isAliasAction, int actionType, int errorCode) {
        String str = "Failed to %s %s due to %s. Try again after 60s.";
        str = String.format(Locale.ENGLISH, str, getActionStr(actionType), (isAliasAction ? "alias" : " tags"), (errorCode == 6002 ? "timeout" : "server too busy"));
        return str;
    }

    private String getActionStr(int actionType) {
        switch (actionType) {

            case ACTION_SET:
                return "set";
        }
        return "unkonw operation";
    }

    public void put(int sequence, Object tagAliasBean) {
        setActionCache.put(sequence, tagAliasBean);
    }

    private String getInPutAlias() {

        if (TextUtils.isEmpty(companyId)) {
            Toast.makeText(getApplicationContext(), R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return null;
        }
        if (!ExampleUtil.isValidTagAndAlias(companyId)) {
            Toast.makeText(getApplicationContext(), R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
            return null;
        }
        return companyId;
    }

    private void getSmsCode() {
        LoginCheckUserPasswordReq loginCheckUserPasswordReq1 = new LoginCheckUserPasswordReq();
        loginCheckUserPasswordReq1.setUserName(phone_emial);
        loginCheckUserPasswordReq1.setUserPassword(password);
        ActionPresenter.getInstance().loginMsgCodeRet(loginCheckUserPasswordReq1).enqueue(new Callback<LoginMsgCodeRes>() {
            @Override
            public void onResponse(Call<LoginMsgCodeRes> call, Response<LoginMsgCodeRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
                        //验证码有效期
//                        expireDate = response.body().getData().getExpireDate();
                        //计时器
                        mCDT = new MyCountDownTimer(180 * 1000, 1000);
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
            buttionGetMsg.setText(l / 1000 + "s");
            buttionGetMsg.setEnabled(false);
        }

        @Override
        public void onFinish() {
            if (mCDT != null) {
                mCDT.cancel();
            } else {
                mCDT = null;
            }
            isStartTimer = false;
            buttionGetMsg.setEnabled(true);
            buttionGetMsg.setText(getString(R.string.mine_information_get));
        }
    }

    /**
     * /**
     * //检查用户名密码接口
     */
    private void checkUserNamePassRet() {
        //调用验证手机号，密码接口
        phone_emial = etPutphoneEmial.getText().toString().trim();
        //输入密码
        password = putEtPassword.getText().toString().trim();

        LoginCheckUserPasswordReq loginCheckUserPasswordReq = new LoginCheckUserPasswordReq();
        loginCheckUserPasswordReq.setUserName(phone_emial);
        loginCheckUserPasswordReq.setUserPassword(password);
        ActionPresenter.getInstance().loginCheckUserPasswordRet(loginCheckUserPasswordReq).enqueue(new Callback<LoginCheckUserPasswordRes>() {
            @Override
            public void onResponse(Call<LoginCheckUserPasswordRes> call, Response<LoginCheckUserPasswordRes> response) {

                if (response != null && response.body() != null) {
                    String message = response.body().getMessage();
                    int code = response.body().getCode();
                    //300成功
                    if (300 == code) {
                        isCheckUsernamePass = true;
                        ToastUtils.showShort(getString(R.string.toast_login_check_success));
                    } else if (400 == code) {
                        ToastUtils.showShort(message);
                    } else if (500 == code) {
                        ToastUtils.showShort(message);
                    } else {
                        ToastUtils.showShort(message);
                    }

                }
                //token超时 重置用户信息
//                        MyApplication.mMyApplication.UpdateUserInfo(true,response.body().gettoken,getcurrentid);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：",""+t.getMessassge());
            }
        });
    }

    /**
     * /**
     * //检查用户名密码接口 并且 获取验证码
     */
    private void checkUserNamePassRetAndGetSmsCode() {
        //调用验证手机号，密码接口
        phone_emial = etPutphoneEmial.getText().toString().trim();
        //输入密码
        password = putEtPassword.getText().toString().trim();

        LoginCheckUserPasswordReq loginCheckUserPasswordReq = new LoginCheckUserPasswordReq();
        loginCheckUserPasswordReq.setUserName(phone_emial);
        loginCheckUserPasswordReq.setUserPassword(password);
        ActionPresenter.getInstance().loginCheckUserPasswordRet(loginCheckUserPasswordReq).enqueue(new Callback<LoginCheckUserPasswordRes>() {
            @Override
            public void onResponse(Call<LoginCheckUserPasswordRes> call, Response<LoginCheckUserPasswordRes> response) {

                if (response != null && response.body() != null) {
                    String message = response.body().getMessage();
                    int code = response.body().getCode();
                    //300成功
                    if (300 == code) {
                        isCheckUsernamePass = true;
                        ToastUtils.showShort(getString(R.string.toast_login_check_success));
                        getSmsCode();
                    } else if (400 == code) {
                        ToastUtils.showShort(message);
                    } else if (500 == code) {
                        ToastUtils.showShort(message);
                    } else {
                        ToastUtils.showShort(message);
                    }

                }
                //token超时 重置用户信息
//                        MyApplication.mMyApplication.UpdateUserInfo(true,response.body().gettoken,getcurrentid);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：",""+t.getMessassge());
            }
        });
    }

    /**
     * /**
     * //检查权限接口
     *
     * @POST(Constants.NETPATH.CHECKCONTROR) Call<DownCheckLetterRes> checkControlRet(@Body RequestBody requestBody);
     * public Call<CheckControlRes> checkControlRet(IussingFactoringEntryReq data) {
     * Call<CheckControlRes> checkControlRet = mApi.checkControlRet(createRequestBody(data));
     * return checkControlRet;
     * }
     */
    private void checkControlRet() {
//        showWaitDialog();
        IussingFactoringEntryReq iussingFactoringEntryReq = new IussingFactoringEntryReq();
        ActionPresenter.getInstance().checkControlRet(iussingFactoringEntryReq).enqueue(new Callback<CheckControlRes>() {
            @Override
            public void onResponse(Call<CheckControlRes> call, Response<CheckControlRes> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (StringUtils.isNullObject(response.body().getData())) {
                        SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_FORFAITING, response.body().getData().getForfeiting());
                        SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_FACTORING, response.body().getData().getFactoring());
                        MyLogger.pLog().d("getForfeiting" + response.body().getData().getForfeiting());
                        MyLogger.pLog().d("getFactoring" + response.body().getData().getFactoring());
                    }
                }
//                closeWaitDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
                closeWaitDialog();
            }
        });

    }

    private void checkUserName() {
        etPutphoneEmial.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String userName = etPutphoneEmial.getText().toString().trim();
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

    private boolean isLegalUserName() {
        String userName = etPutphoneEmial.getText().toString().trim();
        if (!TextUtils.isEmpty(userName)) {
            if (userName.length() > 11) {
                if (EmailUtils.isEmail(userName)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (NumberUtils.isNumber(userName)) {
                    if (PhoneUtils.isPhoneNum(userName)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (EmailUtils.isEmail(userName)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }
}
