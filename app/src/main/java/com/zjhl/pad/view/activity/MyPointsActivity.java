package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.TellerManagementReq;
import com.zjhl.pad.moudle.entity.res.IntegralRes;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.MyPointsAdapter;
import com.zjhl.pad.view.adapter.TellerManagerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* File: MyPointsActivity.java 我的积分
* Author: DELL 
* Version: V1.0
* Create: 2018/5/5 14:51 
* Changes (from 2018/5/5) 
*/
public class MyPointsActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;



    @BindView(R.id.bt_custmer)
    LinearLayout btCustmer;


    //总积分
    @BindView(R.id.tv_points)
    TextView tvPoints;


    TextView tvJiaoyiSucessfulJifen;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    private int mNextRequestPage = 1;
    private MyPointsAdapter tellerManagerListAdapter;
    public static final int PAGE_SIZE1 = 100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_points);
        ButterKnife.bind(this);
        rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        initRefreshLayout();
        initAdapter();
        initView();
        initPonitData();
    }

    private void initAdapter() {
        List<IntegralRes.DataBean.IntegralDetailListBean> data = new ArrayList<>();
        tellerManagerListAdapter = new MyPointsAdapter(data);
        tellerManagerListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initPonitData();
            }
        });
        tellerManagerListAdapter.openLoadAnimation();
        rvList.setAdapter(tellerManagerListAdapter);
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
        tellerManagerListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initPonitData();
    }

    private void initPonitData() {
        TellerManagementReq tellerManagementReq = new TellerManagementReq();
        tellerManagementReq.setPage(mNextRequestPage);
        tellerManagementReq.setPageSize(PAGE_SIZE1);


        ActionPresenter.getInstance().MyIntegral(tellerManagementReq).enqueue(new Callback<IntegralRes>() {
            @Override
            public void onResponse(Call<IntegralRes> call, Response<IntegralRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getData() != null) {
                        if (mNextRequestPage == 1) {
                            int userIntegral = response.body().getData().getUserIntegral();
                            tvPoints.setText(String.valueOf(userIntegral));
                            setData(true,response.body().getData().getIntegralDetailList());
                        } else if (mNextRequestPage > 1 && mNextRequestPage<=response.body().getTotalPage()) {
                            setData(false, response.body().getData().getIntegralDetailList());
                        }else {
                            tellerManagerListAdapter.loadMoreEnd(false);
                        }

                        tellerManagerListAdapter.setEnableLoadMore(true);
                        swipeLayout.setRefreshing(false);
                    } else {
                        tellerManagerListAdapter.setEnableLoadMore(true);
                        swipeLayout.setRefreshing(false);
                        MyLogger.pLog().e(response.body().getMessage());
                    }

                } else {
//                    ToastUtils.showShort(getString(R.string.mine_jifen_time_out));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void setData(boolean isRefresh, List<IntegralRes.DataBean.IntegralDetailListBean> data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            tellerManagerListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                tellerManagerListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE1) {
            //第一页如果不够一页就不显示没有更多数据布局
            tellerManagerListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            tellerManagerListAdapter.loadMoreComplete();
        }
    }


    private void initView() {
        tvContent.setText(R.string.mine_integral);
    }

    @OnClick(R.id.iv_excite)
    public void onViewClicked() {
        finish();
    }
}
