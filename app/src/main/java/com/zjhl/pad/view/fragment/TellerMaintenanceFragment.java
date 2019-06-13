package com.zjhl.pad.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.LockReq;
import com.zjhl.pad.moudle.entity.req.LoginReq;
import com.zjhl.pad.moudle.entity.req.NomalReq;
import com.zjhl.pad.moudle.entity.req.TellerManagementReq;
import com.zjhl.pad.moudle.entity.res.LockRes;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.LoginActivity;
import com.zjhl.pad.view.activity.ResetPasswordActivity;
import com.zjhl.pad.view.activity.TellerDetailsManagementActivity;
import com.zjhl.pad.view.adapter.PermissionSettingAdapter;
import com.zjhl.pad.view.adapter.TellerManagerListAdapter;
import com.zjhl.pad.view.base.BaseFragment;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: TellerMaintenanceFragment.java 櫃員设置
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/27 11:51
 * Changes (from 2018/4/27)
 */
public class TellerMaintenanceFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;
    private PermissionSettingAdapter permissionSettingAdapter;
    private String mid;
    private String mrealName;
    private int mNextRequestPage = 1;
    private String lock_id;
    private String zhengchang_id;
    private String lockState;
    SureOrCancelDialog sureOrCancelDialog;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_teller_management, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        initRefreshLayout();
        initAdapter();
        initHomeIndexData();
        initItemListener();
        return rootView;
    }

    private void initAdapter() {
        List<TellerManagementRes.DataBean> data = new ArrayList<>();
        permissionSettingAdapter = new PermissionSettingAdapter(data);
        permissionSettingAdapter.openLoadAnimation();
        rvList.setAdapter(permissionSettingAdapter);
    }

    private void initHomeIndexData() {
        TellerManagementReq TellerManagmentReq = new TellerManagementReq();
        TellerManagmentReq.setPage(mNextRequestPage);
        TellerManagmentReq.setPageSize(PAGE_SIZE);
        ActionPresenter.getInstance().TellerManagementRet(TellerManagmentReq).enqueue(new Callback<TellerManagementRes>() {
            @Override
            public void onResponse(Call<TellerManagementRes> call, Response<TellerManagementRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            setData(true, response.body().getData());
                            permissionSettingAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
                        }
                    } else {
                        permissionSettingAdapter.setEnableLoadMore(true);
                        swipeLayout.setRefreshing(false);
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            permissionSettingAdapter.setNewData(data);
        } else {
            if (size > 0) {
                permissionSettingAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            permissionSettingAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            permissionSettingAdapter.loadMoreComplete();
        }
    }

    private void initRefreshLayout() {
        //启用刷新
        swipeLayout.setRefreshing(false);
        swipeLayout.setColorSchemeResources(R.color.blue);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }


    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        permissionSettingAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initHomeIndexData();
    }

    private void initItemListener() {
        permissionSettingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                //锁定状态
                lockState = permissionSettingAdapter.getItem(position).getLockState();
            }
        });

        permissionSettingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                TellerManagementRes.DataBean item = permissionSettingAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.bt_lock:
                        zhengchang_id = permissionSettingAdapter.getItem(position).getId();
                        //锁定或者解锁
                        lockState = permissionSettingAdapter.getItem(position).getLockState();
                        initSureOrCancelDialogView("", getString(R.string.mine_will_sale_sure));
//                        lockState();
                        break;
                    case R.id.wite_off:
                        lock_id = permissionSettingAdapter.getItem(position).getId();
                        nomal();
                        break;
                }
            }

            @OnClick({R.id.rv_list, R.id.swipeLayout})
            public void onViewClicked(View view) {
                switch (view.getId()) {
                    case R.id.rv_list:
                        break;
                    case R.id.swipeLayout:
                        break;
                }
            }
        });
    }

    private void nomal() {
        NomalReq nomalReq = new NomalReq();
        nomalReq.setId(lock_id);
        ActionPresenter.getInstance().nomalRet(nomalReq).enqueue(new Callback<LockRes>() {
            @Override
            public void onResponse(Call<LockRes> call, Response<LockRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        initHomeIndexData();
//                        ToastUtils.showShort(response.body().getMessage());
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
            public void onFailure(Call<LockRes> call, Throwable t) {

            }
        });
    }


    private void lockState() {
        //锁定/解锁
        LockReq lockReq = new LockReq();
        if ("1".equals(lockState)) {
            lockReq.setCode("2");
        } else if ("2".equals(lockState)) {
            lockReq.setCode("1");
        }
        lockReq.setId(zhengchang_id);

        ActionPresenter.getInstance().lockRet(lockReq).enqueue(new Callback<LockRes>() {

            @Override
            public void onResponse(Call<LockRes> call, Response<LockRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        initHomeIndexData();
                        ToastUtils.showShort(getString(R.string.toast_teller_lockorunlock));
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
            public void onFailure(Call<LockRes> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = "";
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                lockState();
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect,String companyName) {
                MyLogger.pLog().i("negative");
            }
        }, "" + dialogContent, getString(R.string.issue_factoring_sell_no), getString(R.string.issue_factoring_sell_yes));
        sureOrCancelDialog.show();
    }

}
