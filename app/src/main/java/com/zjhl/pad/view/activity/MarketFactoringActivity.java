package com.zjhl.pad.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MarketFactoringSellReq;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.MarketFactoringAdapter;
import com.zjhl.pad.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: FactoringSellFragment
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.fragment
 * @author: pluto
 * @create: 2018/4/25 13:38
 * @projectname: nnkj 保理
 **/
public class MarketFactoringActivity extends BaseActivity {


    Unbinder unbinder;
    @BindView(R.id.market_forfaiting_rv)
    RecyclerView marketForfaitingRv;
    @BindView(R.id.market_forfaiting_sl)
    SwipeRefreshLayout marketForfaitingSl;

    private int mNextRequestPage = 1;

    private MarketFactoringAdapter marketFactoringAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        setContentView(R.layout.activity_market_forfaiting);
        ButterKnife.bind(this);
        marketForfaitingRv.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        initAdapter();
        initRefreshLayout();
        marketForfaitingSl.setRefreshing(true);
        initItemListener();
        refresh();


//        View footerView = getFooterView(0, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                marketForfaitingAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
//                ToastUtils.showShort("点击了更多资产");
//            }
//        });
//        marketFactoringAdapter.addFooterView(footerView, 0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initAdapter() {
        List<MarketFactoringSellRes.DataBean> data = new ArrayList<>();
        marketFactoringAdapter = new MarketFactoringAdapter(data);
        marketFactoringAdapter.openLoadAnimation();
        marketFactoringAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                MyLogger.pLog().i("加载更多");
                loadMore();
            }
        });
        marketForfaitingRv.setAdapter(marketFactoringAdapter);
    }

    public void initItemListener() {
        marketFactoringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemClick: ");
                ToastUtils.showShort("onItemClick" + position);
            }
        });
        marketFactoringAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
                ToastUtils.showShort("onItemLongClick" + position);
                return true;
            }
        });
        marketFactoringAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildClick: ");
                ToastUtils.showShort("onItemChildClick" + position);
            }
        });
        marketFactoringAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildLongClick: ");
                ToastUtils.showShort("onItemChildLongClick" + position);
                return true;
            }
        });
    }

    private void initRefreshLayout() {
        //启用刷新
        marketForfaitingSl.setRefreshing(false);
        //禁用下拉刷新
//        marketForfaitingSl.setEnabled(false);
        marketForfaitingSl.setColorSchemeResources(R.color.blue);
        marketForfaitingSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        @SuppressLint("RestrictedApi") View view = LayoutInflater.from(MarketFactoringActivity.this).inflate(R.layout.item_market_foot_view, (ViewGroup) marketForfaitingRv.getParent(), false);
        if (type == 1) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.item_market_more_button);
//            imageView.setImageResource(R.mipmap.rm_icon);
//            ToastUtils.showShort("点击了更多资产111");
        }
        view.setOnClickListener(listener);
        return view;
    }


    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketFactoringAdapter.removeFooterView(v);
            }
        };
    }

    ////市场行情 保理-在售资产接口
    public void initHomeIndexData(final boolean isRefresh) {
        MyLogger.pLog().i("市场行情 保理-在售资产接口");
        MarketFactoringSellReq marketFactoringSellReq = new MarketFactoringSellReq();
        marketFactoringSellReq.setPage(mNextRequestPage);
        marketFactoringSellReq.setPageSize(PAGE_SIZE);
        marketFactoringSellReq.setDirection(1);
        ActionPresenter.getInstance().marketFactoringSellRet(marketFactoringSellReq).enqueue(new Callback<MarketFactoringSellRes>() {
            @Override
            public void onResponse(Call<MarketFactoringSellRes> call, Response<MarketFactoringSellRes> response) {
                if (response != null && response.body() != null) {
                    if (response != null && response.body() != null) {
                        MyLogger.pLog().d("=====" + response.body().toString());
                        MyLogger.pLog().d("=====" + response.body().getCode());

                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                setData(isRefresh, response.body().getData());
                                marketFactoringAdapter.setEnableLoadMore(true);
                                marketForfaitingSl.setRefreshing(false);
                            }
                        } else {
                            marketFactoringAdapter.setEnableLoadMore(true);
                            marketForfaitingSl.setRefreshing(false);
                            MyLogger.pLog().e(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        marketFactoringAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initHomeIndexData(true);
        //请求成功
//        if(true) {
//            setData(true, data);
//            marketFactoringAdapter.setEnableLoadMore(true);
//            marketForfaitingSl.setRefreshing(false);
//        }else {
//            //请求失败
//            marketFactoringAdapter.setEnableLoadMore(true);
//            marketForfaitingSl.setRefreshing(false);
//        }

    }

    private void loadMore() {
        //加载更多  请求带当前第几页
        initHomeIndexData(false);
    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            marketFactoringAdapter.setNewData(data);
        } else {
            if (size > 0) {
                marketFactoringAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            marketFactoringAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            marketFactoringAdapter.loadMoreComplete();
        }
    }


}
