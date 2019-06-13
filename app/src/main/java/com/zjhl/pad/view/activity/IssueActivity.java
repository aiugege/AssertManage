package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.IussingFactoringEntryReq;
import com.zjhl.pad.moudle.entity.res.CheckControlRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: IssueActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/4/26 16:06
 * @projectname: nnkj
 **/
public class IssueActivity extends BaseActivity {
    @BindView(R.id.issue_forfaiting_ll)
    LinearLayout issueForfaitingLl;
    @BindView(R.id.issue_factoring_ll)
    LinearLayout issueFactoringLl;

    @BindView(R.id.issue_rl)
    RelativeLayout issueRl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        ButterKnife.bind(this);
        checkControlRet();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.issue_forfaiting_ll, R.id.issue_factoring_ll})
    public void onViewClicked(View view) {
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        String forfaiting = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_FORFAITING, "0").toString();
        String factoring = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_FACTORING, "0").toString();
        if ("2".equals(userType)) {
            if ("1".equals(forfaiting) || "1".equals(factoring)) {
                switch (view.getId()) {
                    case R.id.issue_forfaiting_ll:
                        DisPatcher.startForfaitingIssuingActivity(this, "", "");
                        break;
                    case R.id.issue_factoring_ll:
                        DisPatcher.startFactoringIssuingActivity(this, "", "");
                        break;
                }
            } else {
                ToastUtils.showShort(getString(R.string.toast_issue_noaccess));
            }
        } else {
            ToastUtils.showShort(getString(R.string.toast_issue_role));
        }
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
        showWaitDialog();
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
                closeWaitDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
                closeWaitDialog();
            }
        });

    }

    @OnClick(R.id.issue_rl)
    public void onViewClicked() {
        finish();
    }
}
