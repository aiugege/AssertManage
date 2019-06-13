package com.zjhl.pad.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.FactoringModel;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MarketFactoringSellReq;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.FiltrateActivity;
import com.zjhl.pad.view.activity.MarketFactoringOfferActivity;
import com.zjhl.pad.view.activity.MarketForfaitingFiltrateActivity;
import com.zjhl.pad.view.adapter.ClassifyMorAdapter;
import com.zjhl.pad.view.adapter.DataOrderMainAdapter;
import com.zjhl.pad.view.adapter.MarketFactoringAdapter;
import com.zjhl.pad.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
public class MarketFactoringFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.market_forfaiting_rv)
    RecyclerView marketForfaitingRv;
    @BindView(R.id.market_forfaiting_sl)
    SwipeRefreshLayout marketForfaitingSl;
    @BindView(R.id.market_forfaiting_bt_public_data)
    TextView marketForfaitingBtPublicData;
    @BindView(R.id.market_forfaiting_data_order)
    RelativeLayout marketForfaitingDataOrder;
    @BindView(R.id.market_forfaiting_bt_select_shuaixuan)
    TextView marketForfaitingBtSelectShuaixuan;
    @BindView(R.id.market_forfaiting_rl_shuaixuan)
    RelativeLayout marketForfaitingRlShuaixuan;

    @BindView(R.id.tv_no_assert)
    TextView tvNoAssert;
    Unbinder unbinder1;

    private int mNextRequestPage = 1;

    private MarketFactoringAdapter marketFactoringAdapter;
    //筛选菜单开始
    private String assetsType = "2";
    private String mine_fufeiting;
    private String country_id;
    private String money_type;
    private String ascDesc = "desc";
    private String orderBy;
    private String offerType;
    private String userType;

    private ListView order_more_list;
    DataOrderMainAdapter mainAdapter;
    ClassifyMorAdapter moreAdapter;
    private List<Map<String, Object>> mainList;
    private String shareKey = Constants.SPKEY.SP_MARKET_FACTORING_KEY;

    private static final int RESULT_CANCELED = 0;
    private static final int RESULT_CANCELEDFILTER = 1;
    private String bizhong_type;
    private String openfullname;
    private String scope_start;
    private String scope_end;
    private String id_area;
    private String factoringtype;
    private String factoring_type;
    private String factoring_type2;
    //筛选菜单结束

    FactoringModel factoringModel = new FactoringModel();
    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_market_forfaiting, null);
        unbinder = ButterKnife.bind(this, view);
        marketForfaitingRv.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
        initRefreshLayout();
        initAdapter();
        initHomeIndexData();
        initItemListener();
        //筛选开始
        initModle();
        resetFilterData();
        //筛选结束
//        refresh();

//        View footerView = getFooterView(0, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                marketForfaitingAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
//                ToastUtils.showShort("点击了保理更多资产");
//                DisPatcher.startMarketFactoringActivity(getActivity());
//            }
//        });
//        marketFactoringAdapter.addFooterView(footerView, 0);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initAdapter() {
        List<MarketFactoringSellRes.DataBean> data = new ArrayList<>();
        marketFactoringAdapter = new MarketFactoringAdapter(data);
        marketFactoringAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        marketFactoringAdapter.openLoadAnimation();
        marketForfaitingRv.setAdapter(marketFactoringAdapter);
    }

    public void initItemListener() {
        marketFactoringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemClick: ");
//                Toast.makeText(getActivity(), "onItemClick" + position, Toast.LENGTH_SHORT).show();
                MyLogger.pLog().d("onItemClick: "/*+((MarketForfaitingSellRes.DataBean)adapter.getItem(position)).getTitle()*/);
                boolean userStatus = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
                if ((userStatus)) {
                    DisPatcher.startMarketFactoringDetailActivity(getActivity(), ((MarketFactoringSellRes.DataBean) adapter.getItem(position)).getId() + "", ((MarketFactoringSellRes.DataBean) adapter.getItem(position)).getMyAssets() + "");
                } else {
                    ToastUtils.showShort(getString(R.string.toast_market_detail));
                }
            }
        });
        marketFactoringAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
//                Toast.makeText(getActivity(), "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        marketFactoringAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildClick: ");
//                Toast.makeText(getActivity(), "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        marketFactoringAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildLongClick: ");
//                Toast.makeText(getActivity(), "onItemChildLongClick" + position, Toast.LENGTH_SHORT).show();
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
        @SuppressLint("RestrictedApi") View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_market_foot_view, (ViewGroup) marketForfaitingRv.getParent(), false);
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
    public void initHomeIndexData() {
        MyLogger.pLog().i("市场行情 保理-在售资产接口");
        MarketFactoringSellReq marketFactoringSellReq = new MarketFactoringSellReq();
        marketFactoringSellReq.setPage(mNextRequestPage);
        marketFactoringSellReq.setPageSize(PAGE_SIZE);
//        marketFactoringSellReq.setDirection(1);
        if ("2".equals(assetsType)) {
            marketFactoringSellReq.setCurrency(bizhong_type);
            marketFactoringSellReq.setFactoringType(factoringtype);
            marketFactoringSellReq.setAmountStart(scope_start);
            marketFactoringSellReq.setAmountEnd(scope_end);
            marketFactoringSellReq.setCountryId(country_id);
            marketFactoringSellReq.setAreaId(id_area);
            marketFactoringSellReq.setAscDesc(ascDesc);
            marketFactoringSellReq.setOrderBy(orderBy);
        }
//        marketFactoringSellReq.setAscDesc(ascDesc);
//        marketFactoringSellReq.setOrderBy(orderBy);
        ActionPresenter.getInstance().marketFactoringSellRet(marketFactoringSellReq).enqueue(new Callback<MarketFactoringSellRes>() {
            @Override
            public void onResponse(Call<MarketFactoringSellRes> call, Response<MarketFactoringSellRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage == 1) {
                                setData(true, response.body().getData());
                            } else if (mNextRequestPage > 1 && mNextRequestPage<=response.body().getTotalPage()) {
                                setData(false, response.body().getData());
                            }else {
                                marketFactoringAdapter.loadMoreEnd(false);
                            }
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
        initHomeIndexData();
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
        initHomeIndexData();
        //加载更多  请求带当前第几页
//        new Request(mNextRequestPage, new RequestCallBack() {
//            @Override
//            public void success(List<Status> data) {
//                setData(false, data);
//            }
//
//            @Override
//            public void fail(Exception e) {
//                marketFactoringAdapter.loadMoreFail();
//                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
//            }
//        }).start();
    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if(size>0){
            tvNoAssert.setVisibility(View.GONE);
            marketForfaitingSl.setVisibility(View.VISIBLE);
        } else {
            marketForfaitingSl.setVisibility(View.GONE);
            tvNoAssert.setVisibility(View.VISIBLE);
        }
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

    //筛选菜单开始
    private void initModle() {
        mainList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < factoringModel.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", factoringModel.LISTVIEWTXT[i]);
            mainList.add(map);
        }
    }

    private void initAdapter(String[] array) {
        moreAdapter = new ClassifyMorAdapter(getActivity(), array);
        order_more_list.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    private void showDataOrder() {

        View popupView_order = getActivity().getLayoutInflater().inflate(R.layout.mine_list_data_order, null);
//        orderBy = "publish_time";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(getActivity(), mainList);
//        mainAdapter.setSelectItem(0);
        order_main_list.setAdapter(mainAdapter);
        if("publish_time".equals(orderBy)){
            mainAdapter.setSelectItem(0);
        }else if("amount".equals(orderBy)){
            mainAdapter.setSelectItem(1);
        }else if("maturity".equals(orderBy)){
            mainAdapter.setSelectItem(2);
        }else if("transfer_rate".equals(orderBy)){
            mainAdapter.setSelectItem(3);
        }
        order_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    //资产发布时间
                    orderBy = "publish_time";
//                    marketForfaitingBtPublicData.setText(R.string.filtrate_inputdate);
                } else if (position == 1) {
                    //金额
                    orderBy = "amount";
//                    marketForfaitingBtPublicData.setText(R.string.filtrate_money);
                } else if (position == 2) {
                    //保理到期日
                    orderBy = "maturity";
                } else if (position == 3) {
                    //转让利率
                    orderBy = "transfer_rate";
                }
                initAdapter(factoringModel.MORELISTTXT[position]);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                marketForfaitingBtPublicData.setText(factoringModel.LISTVIEWTXT[position]);

            }
        });
        order_main_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 一定要设置这个属性，否则ListView不会刷新
        initAdapter(factoringModel.MORELISTTXT[0]);
//        order_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textList));
        final PopupWindow window_quote = new PopupWindow(popupView_order, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_quote.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_quote.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_quote.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_quote.update();
        if("asc".equals(ascDesc)){
            moreAdapter.setSelectItem(0);
        }else if("desc".equals(ascDesc)){
            moreAdapter.setSelectItem(1);
        }

//        final String typestr = marketForfaitingBtPublicData.getText().toString()+" ";
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_quote.showAsDropDown(marketForfaitingBtPublicData, 0, 20);
        order_more_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                btPublicData.setText(position);
                String typestr2 = "";
                if (position == 0) {
                    typestr2 = getString(R.string.filtrate_asc);
                    ascDesc = "asc";
                } else if (position == 1) {
                    typestr2 = getString(R.string.filtrate_desc);
                    ascDesc = "desc";
                }
//                SharedPreferanceUtils.put(MyQuoteActivity.this,"order_data",ascDesc);
//                marketForfaitingBtPublicData.setText(typestr2);
                moreAdapter.setSelectItem(position);

                moreAdapter.notifyDataSetChanged();
                window_quote.dismiss();
//                initHomeIndexData();
                refresh();

            }
        });
    }
    //筛选菜单结束

    @Override
    public void onResume() {
        super.onResume();
//        refresh();
    }

    @Override
    public void onStart() {
        super.onStart();
//        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.market_forfaiting_bt_public_data, R.id.market_forfaiting_data_order, R.id.market_forfaiting_bt_select_shuaixuan, R.id.market_forfaiting_rl_shuaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.market_forfaiting_bt_public_data:
            case R.id.market_forfaiting_data_order:
                showDataOrder();
                break;
            case R.id.market_forfaiting_bt_select_shuaixuan:
            case R.id.market_forfaiting_rl_shuaixuan:
                //1福费廷  2 保理
//                DisPatcher.startMarketForfaitingFiltrateActivity(getActivity(), "2");
                Intent intent = new Intent(getActivity(), MarketForfaitingFiltrateActivity.class);
//                intent.putExtra("assetsType", assetsType);
                intent.putExtra("shareKey", shareKey);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
//                DisPatcher.startFiltrateActivity(getActivity(), shareKey, 1);
                break;
        }
    }

    protected void initFilterData() {
//        myDialog = new AlertDialog.Builder(getActivity(), R.style.Dialog).create();
        //资产状态
        offerType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "offerType", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"currency_type",currency);
        //币种
        bizhong_type = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "currency_type", "");

        //贴现率范围
//        tiexianlvStartType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "tiexianlv_start", "");
//        tiexianlvEndType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "tiexianlv_end", "");

        //承兑金额范围
        scope_start = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "scope_start", "");
        scope_end = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "scope_end", "");

        //开证行名称
        openfullname = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "open_book_name", "");

        //福费廷类型
        mine_fufeiting = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "mine_fufeiting", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"country_id",guojia_seconde_id);
        country_id = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "country_id", "");
        assetsType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "assetsType", "1");
        id_area = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "id_area", "");
        factoring_type = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "factoring_type", "");
        factoring_type2 = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "factoring_type2", "");
        //保理类型  1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理'
        if ("1".equals(factoring_type) && "1".equals(factoring_type2)) {
            factoringtype = "1";
        } else if ("1".equals(factoring_type) && "2".equals(factoring_type2)) {
            factoringtype = "2";
        } else if ("2".equals(factoring_type) && "1".equals(factoring_type2)) {
            factoringtype = "3";
        } else if ("2".equals(factoring_type) && "2".equals(factoring_type2)) {
            factoringtype = "4";
        } else {
            factoringtype = "";
            MyLogger.pLog().d("===========" + factoringtype);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELEDFILTER) {
            initFilterData();
            refresh();
        }
    }

    protected void resetFilterData() {
        //承兑金额范围
        SharedPreferanceUtils.put(getActivity(), shareKey + "scope_start", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "scope_end", "");
        //福费廷类型
        SharedPreferanceUtils.put(getActivity(), shareKey + "mine_fufeiting", "");
        //币种
        SharedPreferanceUtils.put(getActivity(), shareKey + "currency_type", "");
        //保理 福费廷
//                SharedPreferanceUtils.put(FiltrateActivity.this, shareKey + "assetsType", assetsType);
        //地区
        SharedPreferanceUtils.put(getActivity(), shareKey + "country_id", "");
        //开证行名称
        SharedPreferanceUtils.put(getActivity(), shareKey + "open_book_name", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "factoring_type", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "factoring_type2", "");
        factoringtype = "";
    }
}
