package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.RandomUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.moudle.entity.req.ForgetPassSendCodeReq;
import com.zjhl.pad.moudle.entity.req.RegisterCodeReq;
import com.zjhl.pad.moudle.entity.req.RegisterDictionaryReq;
import com.zjhl.pad.moudle.entity.req.RegisterImgCodeReq;
import com.zjhl.pad.moudle.entity.res.ForgetPassNextRes;
import com.zjhl.pad.moudle.entity.res.ForgetPassSendCodeRes;
import com.zjhl.pad.moudle.entity.res.RegisterCodeRes;
import com.zjhl.pad.moudle.entity.res.RegisterDictionaryRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.app.application.MyApplication.mMyApplication;

/**
 * @desc: LoginActivityTest
 * @version: v1.0
 * @packagename: com.zjhl.pad.activity
 * @author: pluto
 * @create: 2018/4/18 13:46
 * @projectname: nnkj
 **/
public class LoginActivityTest extends BaseActivity {
    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.textView_register)
    TextView textViewRegister;
    @BindView(R.id.iv_persion)
    ImageView ivPersion;
    @BindView(R.id.et_putphone_emial)
    EditText etPutphoneEmial;
    @BindView(R.id.iv_password_lock)
    ImageView ivPasswordLock;
    @BindView(R.id.put_et_password)
    EditText putEtPassword;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.et_getet)
    EditText etGetet;
    @BindView(R.id.buttion_getMsg)
    Button buttionGetMsg;
    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.zhongjin_logo)
    ImageView zhongjinLogo;
    private String imgcode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        MyLogger.pLog().i("注册");
        registerAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
//        DisPatcher.sendNeedLoginBroadcast(LoginActivityTest.this);
    }

    @OnClick({R.id.buttion_getMsg, R.id.button_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttion_getMsg:
                break;
            case R.id.button_login:
//                SharedPreferanceUtils.put(this,Constants.SPKEY.SP_ROOT_KEY,"111");
//                MyApplication.mActionPresenter.login();
//                Log.d("000：","000000");
//                LoginCheckUserPasswordReq loginData = new LoginCheckUserPasswordReq();
//                loginData.setUserName("694726990@qq.com");
//                loginData.setUserPassword("123321");
//                new ActionPresenter().login(loginData, new Observer<LoginCheckUserPasswordRes>(getAppActivity()) {
//                    @Override
//                    public void onNext(LoginCheckUserPasswordRes data) {
//                        // TODO 做登录的成功的操作
////                        Toast.makeText(getAppActivity(), "" + data.userInfo.nickName, Toast.LENGTH_SHORT).show();
//                    }
//                });

//                ActionPresenter.getInstance().login(loginData, new DefaultObserver<ResponseBean>() {
//                @Override
//                public void onNext(ResponseBean value) {
////                    startActivity(new Intent(LoginActivityTest.this,LoginActivity.class));
////                    Log.d("LoginCheckUserPasswordRes：",((LoginCheckUserPasswordRes)value.data));
//                    Log.d("LoginCheckUserPasswordRes：",""+value.toString());
//                }
//
//                    @Override
//                public void onError(Throwable e) {
//                    Log.d("onError：","=====");
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d("onComplete：","=====");
//                }
//            });

//                MyLogger.pLog().i("验证用户是否存在接口");
//                LoginCheckUserPasswordReq loginCheckUserPasswordReq = new LoginCheckUserPasswordReq();
//                loginCheckUserPasswordReq.setUserName("694726990@qq.com");
//                loginCheckUserPasswordReq.setUserPassword("123321");
//                ActionPresenter.getInstance().loginCheckUserPasswordRet(loginCheckUserPasswordReq).enqueue(new Callback<LoginCheckUserPasswordRes>() {
//                    @Override
//                    public void onResponse(Call<LoginCheckUserPasswordRes> call, Response<LoginCheckUserPasswordRes> response) {
//                        MyLogger.pLog().d("LoginCheckUserPasswordRes："+response.body().getData().getRealName()+";");
//                        MyLogger.pLog().d("LoginCheckUserPasswordRes："+response.body().toString());
//                        MyLogger.pLog().d("LoginCheckUserPasswordRes："+response.body().getCode());
////                        DisPatcher.sendNeedLoginBroadcast(LoginActivityTest.this);
//                        //token超时 重置用户信息
////                        MyApplication.mMyApplication.UpdateUserInfo(true,response.body().gettoken,getcurrentid);
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：",""+t.getMessage());
//                    }
//                });
//
//                MyLogger.pLog().i("发送验证码在接口");
//                LoginCheckUserPasswordReq loginCheckUserPasswordReq1 = new LoginCheckUserPasswordReq();
//                loginCheckUserPasswordReq1.setUserName("694726990@qq.com");
//                loginCheckUserPasswordReq1.setUserPassword("123321");
//                ActionPresenter.getInstance().loginMsgCodeRet(loginCheckUserPasswordReq1).enqueue(new Callback<LoginMsgCodeRes>() {
//                    @Override
//                    public void onResponse(Call<LoginMsgCodeRes> call, Response<LoginMsgCodeRes> response) {
//                        MyLogger.pLog().d("LoginMsgCodeRes："+response.body().getData().getExpireDate()+";");
//                        MyLogger.pLog().d("LoginMsgCodeRes："+response.body().toString());
//                        MyLogger.pLog().d("LoginMsgCodeRes："+response.body().getCode());
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：",""+t.getMessage());
//                    }
//                });


//                MyLogger.pLog().i("登录接口");
//                LoginReq loginReq = new LoginReq();
//                loginReq.setUserName("694726990@qq.com");
//                loginReq.setUserPassword("123321");
//                loginReq.setCode("1234");
//                ActionPresenter.getInstance().loginRet(loginReq).enqueue(new Callback<LoginRes>() {
//                    @Override
//                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
//                        MyLogger.pLog().d("====="+response.body().toString());
//                        MyLogger.pLog().d("====="+response.body().getCode());
//                        if(response.body().getCode()==300) {
//                            //保存用户信息
//                            MyApplication.mMyApplication.UpdateUserInfo(true, response.body().getData().getToken(), response.body().getData().getCurrentId() + "");
//                        }else{
//                            MyLogger.pLog().e(response.body().getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：",""+t.getMessage());
//                    }
//                });

//                ToastUtils.showShort("111111111111");

                /*
                "companyTypeId1":"1",      //机构类型（一级）   非空     5
		"companyTypeId2":"2",      //机构类型（二级）'   非空    5
      "countryId":"1",             //国家              非空     5
		"companyName":"中金汇理",     //公司名称       非空     100
		"contactName":"张三",          //联系人姓名      非空    50
		"phone":"18700000006",       //电话             非空    20
		"email":"946952535@qq.com",//邮箱              非空    50
		"inviteCode":"1"  ，       //邀请码              非空      10
     “"accept":1”，          //是否同意条款     1 同意 0 不同意
      "identifyingCode":"3219",  //验证码       非空
      "expireDate":60,        //过期时间        非空
      "imgCode":"Q7D2"     //图片验证码      非空
      "imgCodeId":"201804181234"  //图片验证唯一标识  非空

                 */

//                MyLogger.pLog().i("用户注册接口");
//                RegisterReq registerReq = new RegisterReq();
//                registerReq.setCompanyTypeId1("55");
//                registerReq.setCompanyTypeId2("93");
//                registerReq.setCompanyName("中金汇理");
//                registerReq.setContactName("zhang");
//                registerReq.setPhone("1");
//                registerReq.setEmail("765884601@qq.com");
//                registerReq.setInviteCode("1");
//                registerReq.setAccept("1");
//                registerReq.setIdentifyingCode("3219");
//                registerReq.setExpireDate(60);
//                registerReq.setImgCode("1");
//                registerReq.setImgCodeId("1");
//                ActionPresenter.getInstance().registerRet(registerReq).enqueue(new Callback<RegisterRes>() {
//                    @Override
//                    public void onResponse(Call<RegisterRes> call, Response<RegisterRes> response) {
//                        MyLogger.pLog().d("=====" + response.body().toString());
//                        MyLogger.pLog().d("=====" + response.body().getCode());
//                        if (response.body().getCode() == 300) {
//                        } else {
//                            MyLogger.pLog().e(response.body().getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：", "" + t.getMessage());
//                    }
//                });

//            MyLogger.pLog().i("获取注册验证码接口");
//            RegisterCodeReq registerCodeReq = new RegisterCodeReq();
//                registerCodeReq.setType("1");
//                registerCodeReq.setCode("765884601@qq.com");
//            ActionPresenter.getInstance().registerCodeRet(registerCodeReq).enqueue(new Callback<RegisterCodeRes>() {
//                @Override
//                public void onResponse(Call<RegisterCodeRes> call, Response<RegisterCodeRes> response) {
//                    MyLogger.pLog().d("=====" + response.body().toString());
//                    MyLogger.pLog().d("=====" + response.body().getCode());
//                    if (response.body().getCode() == 300) {
//                    } else {
//                        MyLogger.pLog().e(response.body().getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call call, Throwable t) {
//                    Log.d("onFailure：", "" + t.getMessage());
//                }
//            });

//            MyLogger.pLog().i("异步校验手机号、邮箱唯一");
//            RegisterCheckEmailPhoneReq registerCheckEmailPhoneReq = new RegisterCheckEmailPhoneReq();
//                registerCheckEmailPhoneReq.setEmail("765884601@qq.com");
//                registerCheckEmailPhoneReq.setPhone("15811432927");
//            ActionPresenter.getInstance().registerCheckEmailPhoneRet(registerCheckEmailPhoneReq).enqueue(new Callback<RegisterCheckEmailPhoneRes>() {
//                @Override
//                public void onResponse(Call<RegisterCheckEmailPhoneRes> call, Response<RegisterCheckEmailPhoneRes> response) {
//                    MyLogger.pLog().d("=====" + response.body().toString());
//                    MyLogger.pLog().d("=====" + response.body().getCode());
//                    if (response.body().getCode() == 300) {
//                    } else {
//                        MyLogger.pLog().e(response.body().getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call call, Throwable t) {
//                    Log.d("onFailure：", "" + t.getMessage());
//                }
//            });

                MyLogger.pLog().i("机构注册时生成图片验证码接口");
//                RegisterImgCodeReq registerImgCodeReq = new RegisterImgCodeReq();
//                registerImgCodeReq.setImgCodeId(TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_DATE1) + RandomUtils.getRandomNumbers(4));
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
                                zhongjinLogo.setImageBitmap(bitmap);
                                writeResponseBodyToDisk(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("onFailure：", "" + t.getMessage());
                        }
                    });
                } else {
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
                                zhongjinLogo.setImageBitmap(bitmap);
                                writeResponseBodyToDisk(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.d("onFailure：", "" + t.getMessage());
                        }
                    });
                }


//            MyLogger.pLog().i("获取机构接口");
//            RegisterDictionaryReq registerDictionaryReq = new RegisterDictionaryReq();
//            //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
//            registerDictionaryReq.setGroupId("institutionType");
//           registerDictionaryReq.setGroupId("company");
////            ActionPresenter.getInstance().registerDictionaryRet(registerDictionaryReq).enqueue(new Callback<RegisterDictionaryRes>() {
//                @Override
//                public void onResponse(Call<RegisterDictionaryRes> call, Response<RegisterDictionaryRes> response) {
//                    MyLogger.pLog().d("=====" + response.body().toString());
//                    MyLogger.pLog().d("=====" + response.body().getCode());
//                    if (response.body().getCode() == 300) {
//                    } else {
//                        MyLogger.pLog().e(response.body().getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call call, Throwable t) {
//                    Log.d("onFailure：", "" + t.getMessage());
//                }
//            });

                MyLogger.pLog().i("找回密码 发送验证码接口");
                ForgetPassSendCodeReq forgetPassSendCodeReq = new ForgetPassSendCodeReq();
                forgetPassSendCodeReq.setUserName("694726990@qq.com");
//                forgetPassSendCodeReq.setUserName("765884601@qq.com");
                ActionPresenter.getInstance().forgetPassSendCodeRet(forgetPassSendCodeReq).enqueue(new Callback<ForgetPassSendCodeRes>() {
                    @Override
                    public void onResponse(Call<ForgetPassSendCodeRes> call, Response<ForgetPassSendCodeRes> response) {
                        if (response != null && response.body() != null) {
                            MyLogger.pLog().d("=====" + response.body().toString());
                            MyLogger.pLog().d("=====" + response.body().getCode());
                            if (response.body().getCode() == 300) {
                            } else {
                                MyLogger.pLog().e(response.body().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.d("onFailure：", "" + t.getMessage());
                    }
                });

//                MyLogger.pLog().i("找回密码 下一步接口");
//                ForgetPassSendCodeReq forgetPassNextReq = new ForgetPassSendCodeReq();
//                forgetPassNextReq.setUserName("694726990@qq.com");
//                forgetPassNextReq.setCode("123");
//                ActionPresenter.getInstance().forgetPassNextRet(forgetPassNextReq).enqueue(new Callback<ForgetPassNextRes>() {
//                    @Override
//                    public void onResponse(Call<ForgetPassNextRes> call, Response<ForgetPassNextRes> response) {
//                        if (response != null && response.body() != null) {
//                            MyLogger.pLog().d("=====" + response.body().toString());
//                            MyLogger.pLog().d("=====" + response.body().getCode());
//                            if (response.body().getCode() == 300) {
//                            } else {
//                                MyLogger.pLog().e(response.body().getMessage());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.d("onFailure：", "" + t.getMessage());
//                    }
//                });

                break;
        }
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
//        MyLogger.pLog().i("接受广播1"+intent.getAction());
//        MyLogger.pLog().i("接受广播2"+Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
        if (Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION.equals(intent.getAction())) {
            //
//            MyLogger.pLog().i("接受广播成功");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    MyLogger.pLog().d("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
