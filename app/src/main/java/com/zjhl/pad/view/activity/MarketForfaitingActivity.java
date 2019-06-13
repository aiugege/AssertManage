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
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingSellReq;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingSellRes;
import com.zjhl.pad.moudle.ventity.PirceBean;
import com.zjhl.pad.moudle.ventity.SynshesizeBean;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BasePopActivity;
import com.zjhl.pad.view.adapter.MarketForfaitingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: FactoringSellFragment
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.fragment
 * @author: pluto
 * @create: 2018/4/25 13:38
 * @projectname: nnkj
 **/
public class MarketForfaitingActivity extends BasePopActivity {

    @BindView(R.id.market_forfaiting_rv)
    RecyclerView marketForfaitingRv;
    @BindView(R.id.market_forfaiting_sl)
    SwipeRefreshLayout marketForfaitingSl;
    @BindView(R.id.cb_synthesize)
    //综合排序
            CheckBox cbSynthesize;
    @BindView(R.id.ll_synthesize_tab)
    LinearLayout llSynthesizeTab;
    //金额
    @BindView(R.id.cb_money)
    CheckBox cbMoney;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    //底价
    @BindView(R.id.cb_price)
    CheckBox cbPrice;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    //筛选
    @BindView(R.id.cb_shaixuan)
    CheckBox cbShaixuan;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    @BindView(R.id.ll_stay_visit_selsect)
    LinearLayout llStayVisitSelsect;
    private int mNextRequestPage = 1;

    private MarketForfaitingAdapter marketForfaitingAdapter;

    //筛选条件开始

    /**
     * 展示综合排序的数据源
     */
    List<SynshesizeBean> mPopBeens = new ArrayList<>();
    /**
     * 展示金额的数据
     */
    List<String> mTypes = new ArrayList<>();
    /**
     * 展示底价的数据
     */
    List<PirceBean> mTimes = new ArrayList<>();
    /**
     * 展示的金额str集合
     */
    List<String> mTimeStr = new ArrayList<>();


    //筛选条件结束

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView() {
        setContentView(R.layout.activity_market_forfaiting);
        ButterKnife.bind(this);
        tvContent.setText("资产列表");
        initPopDate();
        initPopView();
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
//        marketForfaitingAdapter.addFooterView(footerView, 0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initAdapter() {
        List<MarketForfaitingSellRes.DataBean> data = new ArrayList<>();
        marketForfaitingAdapter = new MarketForfaitingAdapter(data);
//        marketForfaitingAdapter.setAutoLoadMoreSize(4);
        marketForfaitingAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                MyLogger.pLog().i("加载更多");
                loadMore();
            }
        });
        marketForfaitingAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        marketForfaitingRv.setAdapter(marketForfaitingAdapter);
    }

    public void initItemListener() {
        marketForfaitingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemClick: ");
                ToastUtils.showShort("onItemClick" + position);
            }
        });
        marketForfaitingAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
                ToastUtils.showShort("onItemLongClick" + position);
                return true;
            }
        });
        marketForfaitingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildClick: ");
                ToastUtils.showShort("onItemChildClick" + position);
            }
        });
        marketForfaitingAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
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
        marketForfaitingAdapter.setEnableLoadMore(false);
        marketForfaitingSl.setColorSchemeResources(R.color.blue);
        marketForfaitingSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        @SuppressLint("RestrictedApi") View view = LayoutInflater.from(this).inflate(R.layout.item_market_foot_view, (ViewGroup) marketForfaitingRv.getParent(), false);
        if (type == 1) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.item_market_more_button);
//            imageView.setImageResource(R.drawable.rm_icon);
//            ToastUtils.showShort("点击了更多资产111");
        }
        view.setOnClickListener(listener);
        return view;
    }


    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketForfaitingAdapter.removeFooterView(v);
            }
        };
    }

    ////市场行情福费廷、在售列表接口
    public void initHomeIndexData(final boolean isRefresh) {

        MyLogger.pLog().i("市场行情福费廷、在售列表接口");
        MarketForfaitingSellReq marketForfaitingSellReq = new MarketForfaitingSellReq();
        marketForfaitingSellReq.setPage(mNextRequestPage);
        marketForfaitingSellReq.setPageSize(PAGE_SIZE);
        marketForfaitingSellReq.setDirection(1);
        ActionPresenter.getInstance().marketForfaitingSellRet(marketForfaitingSellReq).enqueue(new Callback<MarketForfaitingSellRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingSellRes> call, Response<MarketForfaitingSellRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            setData(isRefresh, response.body().getData(), response.body().getTotalPage());
//                        marketForfaitingAdapter.setEnableLoadMore(true);
                            marketForfaitingSl.setRefreshing(false);
                        }
                    } else {
//                    marketForfaitingAdapter.setEnableLoadMore(true);
                        marketForfaitingSl.setRefreshing(false);
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

    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        marketForfaitingAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initHomeIndexData(true);
        //请求成功

    }

    private void loadMore() {
        //加载更多  请求带当前第几页
        initHomeIndexData(false);
    }

    private void setData(boolean isRefresh, List data, int totalPage) {
        if (mNextRequestPage <= totalPage) {
            //增加页码  设置数据
            mNextRequestPage++;
            final int size = data == null ? 0 : data.size();
            if (isRefresh) {
                marketForfaitingAdapter.setNewData(data);
            } else {
                if (size > 0) {
                    marketForfaitingAdapter.addData(data);
                }
            }
            if (size < PAGE_SIZE) {
//        if (mNextRequestPage < totalPage) {
                //第一页如果不够一页就不显示没有更多数据布局
                marketForfaitingAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
            } else {
                marketForfaitingAdapter.loadMoreComplete();
            }
        } else {
            marketForfaitingAdapter.loadMoreEnd();
        }
    }

    //初始化筛选条件开始

    /**
     * 初始化数据
     */
    private void initPopDate() {
        // 初始化综合排序
        SynshesizeBean synshesizeBean1 = new SynshesizeBean("中和排序");
        SynshesizeBean synshesizeBean2 = new SynshesizeBean("最新发布");
        SynshesizeBean synshesizeBean3 = new SynshesizeBean("资产到期日升序");
        SynshesizeBean synshesizeBean4 = new SynshesizeBean("资产到期日降序");
        mPopBeens.add(synshesizeBean1);
        mPopBeens.add(synshesizeBean2);
        mPopBeens.add(synshesizeBean3);
        mPopBeens.add(synshesizeBean4);
        // 初始化金额
        mTypes.add("1000");
        mTypes.add("2000");
        mTypes.add("3000");
        // 初始化底价
        PirceBean pirceBean1 = new PirceBean("1", "11");
        PirceBean pirceBean2 = new PirceBean("2", "22");
        PirceBean pirceBean3 = new PirceBean("3", "33");
        PirceBean pirceBean4 = new PirceBean("4", "44");
        mTimes.add(pirceBean1);
        mTimes.add(pirceBean2);
        mTimes.add(pirceBean3);
        mTimes.add(pirceBean4);
        // 获取时间中可用于筛选的数据
        for (PirceBean bean : mTimes) {
            mTimeStr.add(bean.getTimeStr());
        }
    }


    /**
     * 初始化控件
     */
    private void initPopView() {
        // 点击选择综合排序整体
        llSynthesizeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbSynthesize.isChecked())
                    cbSynthesize.setChecked(false);
                else
                    cbSynthesize.setChecked(true);
            }
        });
        // 点击选择金额整体
        llMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbMoney.isChecked())
                    cbMoney.setChecked(false);
                else
                    cbMoney.setChecked(true);
            }
        });
        // 点击选择底价整体
        llPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbPrice.isChecked())
                    cbPrice.setChecked(false);
                else
                    cbPrice.setChecked(true);
            }
        });

        // 选择城市cb
        cbSynthesize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggleT(isChecked, llSynthesizeTab, mPopBeens, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        cbSynthesize.setText(mPopBeens.get(position).getFilterStr());
                        MyLogger.pLog().i(mPopBeens.get(position).getFilterStr());
                    }
                }, cbSynthesize, cbMoney, cbPrice);
            }
        });

        // 选择类型cb
        cbMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, llMoney, mTypes, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        cbMoney.setText(mTypes.get(position));
                        MyLogger.pLog().i(mTypes.get(position));
                    }
                }, cbMoney, cbSynthesize, cbPrice);
            }
        });
        // 选择时间cb
        cbPrice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, llPrice, mTimeStr, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        cbPrice.setText(mTimeStr.get(position));
                        MyLogger.pLog().i(mTimeStr.get(position));
                    }
                }, cbPrice, cbSynthesize, cbMoney);
            }
        });

    }

    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.iv_Rtv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.tv_content:
                break;
            case R.id.iv_Ricon:
                break;
            case R.id.iv_Rtv:
                break;
        }
    }
    //初始化筛选条件结束


}
