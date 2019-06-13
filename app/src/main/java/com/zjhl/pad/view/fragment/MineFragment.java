package com.zjhl.pad.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TagAliasOperatorHelper;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.LoginReq;
import com.zjhl.pad.moudle.entity.req.UserManualReq;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.MineStateRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.BanckInstitutionalActivity;
import com.zjhl.pad.view.activity.EnterpriseActivity;
import com.zjhl.pad.view.activity.LoginActivity;
import com.zjhl.pad.view.activity.MainActivity2;
import com.zjhl.pad.view.activity.MyHobbyActivity;
import com.zjhl.pad.view.activity.MyPointsActivity;
import com.zjhl.pad.view.activity.MyQuoteActivity;
import com.zjhl.pad.view.activity.NoBanckInstitutionalActivity;
import com.zjhl.pad.view.activity.OperationManualActivity;
import com.zjhl.pad.view.activity.PropertyActivity;
import com.zjhl.pad.view.activity.ResetPasswordActivity;
import com.zjhl.pad.view.activity.TellerManagementActivity;
import com.zjhl.pad.view.base.BaseFragment;

import java.io.File;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.app.application.MyApplication.mMyApplication;
import static com.zjhl.pad.app.utils.TagAliasOperatorHelper.ACTION_SET;


/*
 * File: MineFragment.java 我的
 * Author: 刘子龙
 * Version: V100R001C01
 * Create: 2018/4/2 19:27
 * Changes (from 2018/4/2)
 */
public class MineFragment extends BaseFragment {


    private static final String TAG = MineFragment.class.getSimpleName();//"MineFragment"
    @BindView(R.id.iv_nologin_user)
    ImageView ivNologinUser;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.et_getet)
    TextView etGetet;
    @BindView(R.id.buttion_getMsg)
    TextView buttionGetMsg;
    @BindView(R.id.bt_enterprise_msg)
    RelativeLayout btEnterpriseMsg;
    @BindView(R.id.iv_asset)
    ImageView ivAsset;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.bt_mine_assets)
    RelativeLayout btMineAssets;
    @BindView(R.id.tv_shopping)
    TextView tvShopping;
    @BindView(R.id.bt_bug)
    RelativeLayout btBug;
    @BindView(R.id.bt_custmer)
    LinearLayout btCustmer;
    @BindView(R.id.iv_tmanager)
    ImageView ivTmanager;
    @BindView(R.id.et_manager)
    TextView etManager;
    @BindView(R.id.bt_teller_management)
    RelativeLayout btTellerManagement;
    @BindView(R.id.iv_manager)
    ImageView ivManager;
    @BindView(R.id.et_msg)
    TextView etMsg;

    @BindView(R.id.button_login)
    TextView buttonLogin;
    Unbinder unbinder;
    @BindView(R.id.bt_my_hoby)
    RelativeLayout btMyHoby;
    @BindView(R.id.rl_reset_password)
    RelativeLayout rlResetPassword;
    @BindView(R.id.my_jifen)
    RelativeLayout myJifen;
    //用户id
    @BindView(R.id.tv_id)
    TextView tvId;

    private String userType;
    //机构id
    private int orgId;
    private String orgType;
    private String orgState;
    private  String apkUrl;
    private String lanage;
    private String compane_name;
    private String username;
    private TextView company_name;
    private TextView tv_username;
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        registerAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
         company_name = (TextView) view.findViewById(R.id.tv_companey);
         tv_username = (TextView) view.findViewById(R.id.tv_username);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        String currentid1 = (String) SharedPreferanceUtils.get(getActivity(), Constants.USERINFO.USERINFO_CURRENTID, "");
        if (!TextUtils.isEmpty(currentid1)){
            initDataFromServer();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
//        initDataFromServer();
        if ("cn".equals(lanage)) {
            ivNologinUser.setBackgroundResource(R.drawable.register_cn_newlogo1);
        } else if ("en".equals(lanage)) {
            ivNologinUser.setBackgroundResource(R.drawable.register_en_newlogo1);
        }
        return rootView;
    }

    private void initDataFromServer() {
        ActionPresenter.getInstance().mineRet().enqueue(new Callback<MineStateRes>() {
            @Override
            public void onResponse(Call<MineStateRes> call, Response<MineStateRes> response) {

                compane_name = (String) SharedPreferanceUtils.get(getActivity(),Constants.USERINFO.USERINFO_COMPANYNAME,"");
                username = (String) SharedPreferanceUtils.get(getActivity(),Constants.USERINFO.USERINFO_USERNAME,"");
                if (!TextUtils.isEmpty(compane_name)){

                    company_name.setText(compane_name);
                }
                if (!TextUtils.isEmpty(username)){

                    tv_username.setText(username);
                }

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        setCompanyData(response);


                    } else if (response.body().getCode() == 400) {
                        ToastUtils.showShort(response.body().getMessage());
                    }else if (response.body().getCode() == 410) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        startActivity(new Intent(getActivity(),LoginActivity.class));
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

    private void setCompanyData(Response<MineStateRes> response) {
        MineStateRes.DataBean data = response.body().getData();
        if (data != null) {
            //企业信息状态
             orgState = data.getOrgState()+"";
            //企业类型
            orgType = data.getOrgType();
            //公司id
            orgId = data.getOrgId();
            //用户类型 1 管理员 2 经办 3复核
            userType = data.getUserType();
            if ("1".equals(userType)) {
                btEnterpriseMsg.setVisibility(View.VISIBLE);
                btTellerManagement.setVisibility(View.VISIBLE);
                if (orgState.equals("101") || orgState.equals("102") || orgState.equals("104")
                        || orgState.equals("105") || orgState.equals("107") || orgState.equals("108") || orgState.equals("110")) {
                    //审核中
//                                buttionGetMsg.setBackgroundResource(R.drawable.will_finish);
//                                buttionGetMsg.setTextColor(getResources().getColor(R.color.red));
//                                btEnterpriseMsg.setVisibility(View.VISIBLE);
                    buttionGetMsg.setText(getString(R.string.mine_enterpriseinformation_checking));
                    buttionGetMsg.setVisibility(View.VISIBLE);
                } else if ("103".equals(orgState) || "106".equals(orgState) || "109".equals(orgState) || "112".equals(orgState)) {
                    //审核不通过
                    buttionGetMsg.setBackgroundResource(R.drawable.will_finish);
                    buttionGetMsg.setTextColor(getResources().getColor(R.color.red));
//                                btEnterpriseMsg.setVisibility(View.VISIBLE);
                    buttionGetMsg.setText(getString(R.string.mine_enerpriseinformation_no_crose));
                    buttionGetMsg.setVisibility(View.VISIBLE);
                } else if ("111".equals(orgState)) {
                    //审核成功
//                                buttionGetMsg.setBackgroundResource(R.drawable.mine_checking);
//                                buttionGetMsg.setTextColor(getResources().getColor(R.color.gray));
//                                buttionGetMsg.setText(R.string.mine_check_secess);
//                                buttionGetMsg.setEnabled(false);
                    buttionGetMsg.setVisibility(View.GONE);
//                                btEnterpriseMsg.setVisibility(View.VISIBLE);
                } else if (orgState.equals("100")) {
                    //待完善
                    buttionGetMsg.setBackgroundResource(R.drawable.will_finish);
                    buttionGetMsg.setTextColor(getResources().getColor(R.color.red));
                    buttionGetMsg.setText(R.string.mine_will_conpleate);
                    buttionGetMsg.setVisibility(View.VISIBLE);
//                                btEnterpriseMsg.setVisibility(View.VISIBLE);
//                                btEnterpriseMsg.setEnabled(true);
                }
            }else {
                btEnterpriseMsg.setVisibility(View.GONE);
                btTellerManagement.setVisibility(View.GONE);
            }
            //是否锁定
            String lockState = data.getLockState();
            //企业信息各种状态
//                            buttionGetMsg.setText(data.getOrgState()));
            tvId.setText(String.valueOf(orgId));


        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
    }

    @OnClick({R.id.bt_enterprise_msg, R.id.bt_mine_assets, R.id.bt_bug, R.id.bt_custmer, R.id.bt_teller_management, R.id.button_login, R.id.bt_my_hoby, R.id.rl_reset_password, R.id.my_jifen, R.id.tv_shopping,R.id.rl_question,R.id.rl_operation_manual,R.id.tv_opration_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_enterprise_msg:

                if ("bankUser".equals(orgType)) {
//                    //银行机构
                    Intent intent = new Intent(getActivity(), BanckInstitutionalActivity.class);
//                    intent.putExtra("company_name", companyName);
//                    intent.putExtra("company_domicile", companyDomicile);
//                    intent.putExtra("license_no", licenseNo);
//                    intent.putExtra("swift_code", swiftCode);
//                    intent.putExtra("contact_tel", contactTel);
//                    intent.putExtra("memail", email);
//                    intent.putExtra("mid", mid);
//                    intent.putExtra("swiftCode", swiftCode);
//                    intent.putExtra("orgDetailBean", orgDetailBean);
                    intent.putExtra("org_Id", orgId);

                    startActivityForResult(intent, 0);
                    break;
                } else if ("financialInstitution".equals(orgType)) {
                    //非银行机构
                    Intent intent = new Intent(getActivity(), NoBanckInstitutionalActivity.class);
//                    intent.putExtra("company_name", companyName);
//                    intent.putExtra("company_domicile", companyDomicile);
//                    intent.putExtra("license_no", licenseNo);
//                    intent.putExtra("contact_tel", contactTel);
//                    intent.putExtra("mid", mid);
//                    intent.putExtra("swiftCode", swiftCode);
//                    intent.putExtra("memail",email);
                    intent.putExtra("org_Id", orgId);
                    startActivityForResult(intent,1);
//                    intent.putExtra("orgDetailBean", orgDetailBean);
                } else if ("company".equals(orgType)) {
//                    //企业
                    Intent intent = new Intent(getActivity(), EnterpriseActivity.class);
//                    intent.putExtra("company_name", companyName);
//                    intent.putExtra("company_domicile", companyDomicile);
//                    intent.putExtra("license_no", licenseNo);
//                    intent.putExtra("swift_code", swiftCode);
//                    intent.putExtra("contact_tel", contactTel);
//                    intent.putExtra("memail", email);
//                    intent.putExtra("mid", mid);
//                    intent.putExtra("swiftCode", swiftCode);
////                    intent.putExtra("orgDetailBean", orgDetailBean);
                    intent.putExtra("org_Id", orgId);
                    startActivityForResult(intent,2);
               }
                break;
            case R.id.bt_mine_assets:
                //我的资产
                startActivity(new Intent(getActivity(), PropertyActivity.class));
                break;
            case R.id.bt_bug:
                //我的报价
//                startActivity(new Intent(getActivity(),));
                break;
            case R.id.bt_custmer:
                break;
            case R.id.bt_teller_management:
                if (!TextUtils.isEmpty(userType)) {
                    if ("1".equals(userType)) { // 1 管理员 2 经办 3复核
                        if ("111".equals(String.valueOf(orgState))){
                            startActivity(new Intent(getActivity(), TellerManagementActivity.class));
                        } else {
                            ToastUtils.showShort(getString(R.string.mine_origin_complete));
                        }

                    }
                }

                break;
            case R.id.bt_my_hoby:
                //我的爱好
                startActivity(new Intent(getActivity(), MyHobbyActivity.class).putExtra("assert_id", orgId));
                break;
            case R.id.button_login:
                logoutRet();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //未登录状态  status 默认false  token 默认"" currentid默认"" companyName默认"" realName默认"" companyId默认"" email默认"" userType默认""
                mMyApplication.UpdateUserInfo(false, "", "", "", "", "", "", "");
                //退出登录 刷新消息数量
                DisPatcher.sendSystemMessageCount(getActivity());
                //退出登录 刷新消息
                DisPatcher.sendSystemMessage(getActivity());
                MyLogger.pLog().d("退出登录停止接受消息");
                JPushInterface.stopPush(MyApplication.mMyApplication);
                //删除标签
                TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
                tagAliasBean.action = TagAliasOperatorHelper.ACTION_DELETE;
                tagAliasBean.isAliasAction = true;
                TagAliasOperatorHelper.getInstance().handleAction(MyApplication.mMyApplication, 1, tagAliasBean);
                break;

            case R.id.rl_reset_password:
                //密码重置
                startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
                break;
            case R.id.my_jifen:
                //我的积分
                startActivity(new Intent(getActivity(), MyPointsActivity.class));
                break;
            case R.id.tv_shopping:
                //我的报价
                startActivity(new Intent(getActivity(), MyQuoteActivity.class));
                break;
            case R.id.rl_question:
                //常见问题
                startActivity(new Intent(getActivity(), OperationManualActivity.class));
                break;
            case R.id.rl_operation_manual:
               //操作手册

                break;
            case R.id.tv_opration_download:
                //下载
                downloadFileWithDynamicUrlSync();

                break;
        }
    }

    private void downFile() {

    }

//    OkHttpUtils.get().
    private void downloadFileWithDynamicUrlSync() {
        if ("cn".equals(lanage)) {
            userManualRet("30001","http://user/manual/资产易数字资产交易平台操作手册.pdf");
        } else if ("en".equals(lanage)) {
            userManualRet("30002","http://user/manual/Easydigitalassetstradingplatformoperationmanual.pdf");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode || 0 == requestCode || 2 == requestCode) {
//            initDataFromServer();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }
    private void logoutRet() {

        LoginReq loginReq = new LoginReq();
        ActionPresenter.getInstance().logoutRet(loginReq).enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                MyLogger.pLog().d("logoutRet：" + response.body().toString());
                MyLogger.pLog().d("logoutRet：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        MyLogger.pLog().d("登出");
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
     * 下载操作手册
     * @param code
     * @param url
     */
    private void userManualRet(String code, final String url) {
        showWaitDialog();
        UserManualReq userManualReq = new UserManualReq();
        userManualReq.setCode(code);
        ActionPresenter.getInstance().userManualRet(userManualReq).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                MyLogger.pLog().d("logoutRet：" + response.body().toString());
//                MyLogger.pLog().d("logoutRet：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    //下载操作手册成功
                    String writtenToDisk = FileUtils.writeResponseBodyToDisk(MyApplication.mMyApplication, response.body(), url);
                    if (!StringUtils.isEmpty(writtenToDisk)) {
                        ToastUtils.showLong(getString(R.string.toastmarket_forfaiting_detail_downfile) + writtenToDisk);
                    }
                }
            closeWaitDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
                closeWaitDialog();
            }
        });

    }

}
