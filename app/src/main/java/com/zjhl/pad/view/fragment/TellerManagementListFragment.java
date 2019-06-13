package com.zjhl.pad.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.TellerManagementReq;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.TellerDetailsManagementActivity;
import com.zjhl.pad.view.adapter.TellerManagerListAdapter;
import com.zjhl.pad.view.base.BaseFragment;

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
 * File: TellerManagementListFragment.java 柜员列表
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/27 10:49
 * Changes (from 2018/4/27)
 */
public class TellerManagementListFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    Unbinder unbinder;
    private int mNextRequestPage = 1;

    private TellerManagerListAdapter tellerManagerListAdapter;
    private String mid;
    private String mrealName;
    private String mphone;
    private String memail;
    private String muserPassword;
    private String lockState;
    private String certificate;
    private String persion_type;
    private int mNextRequestPage1 = 1;


    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_teller_list, null);
        unbinder = ButterKnife.bind(this, rootView);
        rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        registerAction(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE);
        initRefreshLayout();
        initAdapter();
        initHomeIndexData();
        initItemListener();
//        View footerView = getFooterView(0, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                marketForfaitingAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
//                ToastUtils.showShort("点击了保理更多资产");
//                DisPatcher.startMarketFactoringActivity(getActivity());
//            }
//        });
//        tellerManagerListAdapter.addFooterView(footerView, 0);
        return rootView;
    }

    private void initItemListener() {


        tellerManagerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        tellerManagerListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TellerManagementRes.DataBean item = tellerManagerListAdapter.getItem(position);
                if (item != null) {
                    mid = tellerManagerListAdapter.getItem(position).getId();
                    mrealName = tellerManagerListAdapter.getItem(position).getRealName();
                    mphone = tellerManagerListAdapter.getItem(position).getPhone();
                    memail = tellerManagerListAdapter.getItem(position).getEmail();
                    muserPassword = tellerManagerListAdapter.getItem(position).getUserPassword();
                    certificate = tellerManagerListAdapter.getItem(position).getCertificate();
//                    String usertype = item.getUserType() + "";
                    persion_type = tellerManagerListAdapter.getItem(position).getUserType();

                }
                Intent intent = new Intent(getActivity(), TellerDetailsManagementActivity.class);
                intent.putExtra("tv_remove", mid);
                intent.putExtra("rname", mrealName);
                intent.putExtra("mphone", mphone);
                intent.putExtra("mmemail", memail);
                intent.putExtra("muserPassword", muserPassword);
                intent.putExtra("mcertificate", certificate);
                intent.putExtra("persion_type", persion_type);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            refresh();
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
//    private View getFooterView(int type, View.OnClickListener listener) {
//        @SuppressLint("RestrictedApi") View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_market_foot_view, (ViewGroup) rvList.getParent(), false);
//        if (type == 1) {
////            ImageView imageView = (ImageView) view.findViewById(R.id.item_market_more_button);
////            imageView.setImageResource(R.mipmap.rm_icon);
////            ToastUtils.showShort("点击了更多资产111");
//        }
//        view.setOnClickListener(listener);
//        return view;
//    }

    private void refresh() {
        //下拉刷新
        mNextRequestPage1 = 1;
        tellerManagerListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initHomeIndexData();
    }

//        if (response.body().getData() != null) {
//
//        setData(true, response.body().getData());
//        tellerManagerListAdapter.setEnableLoadMore(true);
//        swipeLayout.setRefreshing(false);
//    }
//} else {
//        tellerManagerListAdapter.setEnableLoadMore(true);
//        swipeLayout.setRefreshing(false);
//        MyLogger.pLog().e(response.body().getMessage());
//        }

    private void initHomeIndexData() {
        TellerManagementReq TellerManagmentReq = new TellerManagementReq();
        TellerManagmentReq.setPage(mNextRequestPage1);
        TellerManagmentReq.setPageSize(PAGE_SIZE);
        ActionPresenter.getInstance().TellerManagementRet(TellerManagmentReq).enqueue(new Callback<TellerManagementRes>() {
            @Override
            public void onResponse(Call<TellerManagementRes> call, Response<TellerManagementRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

//                    if (response.body().getData() != null) {
//                        if (mNextRequestPage1 == 1) {
//                            setData(true, response.body().getData());
//                        } else if (mNextRequestPage1 > 1 && mNextRequestPage1 <=response.body().getTotalPage()) {
//                            setData(false, response.body().getData());
//                        }else {
//                            tellerManagerListAdapter.loadMoreEnd(false);
//                        }
//
//                        tellerManagerListAdapter.setEnableLoadMore(true);
//                        swipeLayout.setRefreshing(false);
//                    } else {
//                        tellerManagerListAdapter.setEnableLoadMore(true);
//                        swipeLayout.setRefreshing(false);
//                        MyLogger.pLog().e(response.body().getMessage());
//                    }
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage1 == 1) {
                                setData(true, response.body().getData());
                            } else if (mNextRequestPage1 > 1 && mNextRequestPage1 <= response.body().getTotalPage()) {
                                setData(false, response.body().getData());
                            } else {
                                tellerManagerListAdapter.loadMoreEnd(false);
                            }

                            tellerManagerListAdapter.setEnableLoadMore(true);

                            if (swipeLayout != null)
                                swipeLayout.setRefreshing(false);
                        } else {
                            tellerManagerListAdapter.setEnableLoadMore(true);
                            if (swipeLayout != null)
                                swipeLayout.setRefreshing(false);
                            MyLogger.pLog().e(response.body().getMessage());
                        }

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

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage1++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            tellerManagerListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                tellerManagerListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            tellerManagerListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            tellerManagerListAdapter.loadMoreComplete();
        }
    }

    private void initAdapter() {
        List<TellerManagementRes.DataBean> data = new ArrayList<>();
        tellerManagerListAdapter = new TellerManagerListAdapter(data);
        tellerManagerListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {

            @Override
            public void onLoadMoreRequested() {
                initHomeIndexData();

            }
        });
        tellerManagerListAdapter.openLoadAnimation();
        rvList.setAdapter(tellerManagerListAdapter);


    }

    @Override
    protected void initData() {
        super.initData();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE);
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        if (intent.getAction().equals(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE)) {
            refresh();
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
}
