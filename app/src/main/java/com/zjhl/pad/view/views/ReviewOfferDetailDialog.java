package com.zjhl.pad.view.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitOnSaleListRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.adapter.HandleOnSaleListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.view.base.BaseFragment.PAGE_SIZE;

/**
 * @desc: ReviewOfferDetailDialog 复核查看报价详情弹窗
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class ReviewOfferDetailDialog extends BaseDialog {
    @BindView(R.id.dialog_offer_detail_iv_close)
    ImageView dialogOfferDetailIvClose;
    @BindView(R.id.dialog_offer_detail_iv_ss)
    ImageView dialogOfferDetailIvSs;
    @BindView(R.id.dialog_offer_detail_title)
    LinearLayout dialogOfferDetailTitle;
    @BindView(R.id.market_forfaiting_detail_rv)
    RecyclerView marketForfaitingDetailRv;
    @BindView(R.id.market_forfaiting_detail_sl)
    SwipeRefreshLayout marketForfaitingDetailSl;
    @BindView(R.id.dialog_offer_detail_ll)
    LinearLayout dialogOfferDetailLl;
    @BindView(R.id.dialog_offer_detail_tv_cancel)
    TextView dialogOfferDetailTvCancel;
    @BindView(R.id.dialog_offer_detail_line)
    View dialogOfferDetailLine;
    @BindView(R.id.dialog_offer_detail_tv_sure)
    TextView dialogOfferDetailTvSure;
    @BindView(R.id.dialog_offer_detail_ll_conent)
    LinearLayout dialogOfferDetailLlConent;
    private TextView mTvSure;//确定
    private ImageView mClose;//关闭
    private TextView mCcncel;//取消 驳回
    private TextView reason;//驳回原因
    private TextView title2;//title2
    private View mLine;//竖线
    private LinearLayout mDialogDetailLl;//报价列表布局
    private LinearLayout mDialogDetailLlBottom;//报价列表底部布局
    private String assestId = "";//资产id
    private String tradingType = "";//1福费廷2保理
    HandleOnSaleListAdapter handleOnSaleListAdapter;
    private int mNextRequestPage = 1;

//    RecyclerView marketForfaitingDetailRv;
//    SwipeRefreshLayout marketForfaitingDetailSl;

    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
    private String isSelect = "";//报价id
    private String companyname = "";//报价公司名称
    int isSelectSell = 0;//1有复核驳回报价了 0 没有
    public ReviewOfferSubmitOnSaleListRes reviewOfferSubmitOnSaleListRes;//提交成功返回数据 用于展示邀约函

    public ReviewOfferDetailDialog(Context context) {
        super(context);
    }

    public ReviewOfferDetailDialog(Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }

    public ReviewOfferDetailDialog(Context context, OnBaseDialogListener onBaseDialogListener, String assestId, String tradingType) {
        super(context, onBaseDialogListener);
        this.assestId = assestId;
        this.tradingType = tradingType;
        this.reviewOfferSubmitOnSaleListRes = reviewOfferSubmitOnSaleListRes;

        //初始化报价列表结束
        refresh();
    }


    /**
     * @param context              上下文
     * @param onBaseDialogListener 回调监听
     * @param cancelName           自定义取消名字
     * @param sureName             自定义确定名字
     * @param title                自定义title
     */
    public ReviewOfferDetailDialog(Context context, OnBaseDialogListener onBaseDialogListener,
                                   String title, String cancelName, String sureName) {
        super(context, onBaseDialogListener);
        setCancelName(cancelName);
        setSureName(sureName);
        setTitleName(title);
    }
    //设置对话框的样式

    @Override
    protected int getDialogStyleId() {
        return R.style.BaseDialog;
    }
    //继承于BaseDialog的方法，设置布局用的，这样对话框张啥样久随心所欲啦

    @Override
    protected View getView() {

        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_offer_detail, null);   //得到各种
//        ButterKnife.bind(context,view);
        mTvSure = (TextView) view.findViewById(R.id.dialog_offer_detail_tv_sure);
        mCcncel = (TextView) view.findViewById(R.id.dialog_offer_detail_tv_cancel);
        reason = (TextView) view.findViewById(R.id.dialog_offer_detail_title_bohui);
        title2 = (TextView) view.findViewById(R.id.dialog_offer_detail_title_tiexianlv);
        mDialogDetailLl = (LinearLayout) view.findViewById(R.id.dialog_offer_detail_ll);
        mDialogDetailLlBottom = (LinearLayout) view.findViewById(R.id.dialog_offer_detail_ll_bottom);
        mClose = (ImageView) view.findViewById(R.id.dialog_offer_detail_iv_close);
        mLine = (View) view.findViewById(R.id.dialog_offer_detail_line);
        marketForfaitingDetailRv = (RecyclerView) view.findViewById(R.id.market_forfaiting_detail_rv);
        marketForfaitingDetailSl = (SwipeRefreshLayout) view.findViewById(R.id.market_forfaiting_detail_sl);
        if("2".equals(tradingType)){
            title2.setText(MyApplication.mMyApplication.getResources().getString(R.string.market_factoring_detail_transferrate));
        }
//        if("3".equals(userType)){
            reason.setVisibility(View.GONE);
//        }
        if (baseDialogListener != null) {
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            mCcncel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelectSell == 0) {
//                        baseDialogListener.negative(isSelect);
                        baseDialogListener.negative(isSelect,companyname);
                        dismiss();
                    } else if (isSelectSell == 1) {
//                        ToastUtils.showShort(context.getString(R.string.toast_reviewoffer_reject));
                        ToastUtils.showShort(context.getString(R.string.toast_issue_frofaiting_editsuccess));
                    } else if (isSelectSell == 2) {
                        ToastUtils.showShort(context.getString(R.string.toast_reviewoffer_noprice));
                    }
                }
            });
            mTvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    baseDialogListener.positive(reviewOfferSubmitOnSaleListRes);
//                    confirmOfferData(isSelect);
                    if (!StringUtils.isEmpty(isSelect)) {
                        baseDialogListener.positive(reviewOfferSubmitOnSaleListRes, isSelect);
                        dismiss();
                    } else {
//                        ToastUtils.showShort(context.getString(R.string.toast_reviewoffer_pircecommit));
                        ToastUtils.showShort(context.getString(R.string.toast_issue_frofaiting_editsuccess));
                    }
                }
            });
        }


        marketForfaitingDetailRv.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        initRefreshLayout();
        initAdapter();
//        initDetailOfferListData(id);
        initItemListener();
        //初始化数据
        return view;
    }

    /**
     * 设置取消名字
     *
     * @param string
     * @return
     */
    public BaseDialog setCancelName(String string) {
        mCcncel.setText(string);
        return this;
    }

    /**
     * 设置取消 显示状态
     *
     * @param isShow
     * @return
     */
    public BaseDialog setCancelVisible(boolean isShow) {
        if (isShow) {
            mCcncel.setVisibility(View.VISIBLE);
        } else {
            mCcncel.setVisibility(View.GONE);
            mLine.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置确定名字
     *
     * @param string
     * @return
     */
    public BaseDialog setSureName(String string) {
        mTvSure.setText(string);
        return this;
    }

    /**
     * 设置标题名字
     *
     * @param string
     * @return
     */
    public BaseDialog setTitleName(String string) {
//        mTvTitle.setText(string);
        return this;
    }

    //市场行情福费廷、详情报价列表接口
    public void initDetailOfferListData(String id) {
        MyLogger.pLog().i("市场行情福费廷、详情报价列表接口");
//        showWaitDialog();
        MarketForfaitingOfferListReq marketForfaitingOfferListReq = new MarketForfaitingOfferListReq();
        marketForfaitingOfferListReq.setAssertId(id);
        //资产类型1-福费廷 2-保理
        marketForfaitingOfferListReq.setTradingType(tradingType);
        if ("2".equals(userType)) {
            ActionPresenter.getInstance().handleOfferListOnSaleListRet(marketForfaitingOfferListReq).enqueue(new Callback<MarketForfaitingOfferListRes>() {
                @Override
                public void onResponse(Call<MarketForfaitingOfferListRes> call, Response<MarketForfaitingOfferListRes> response) {
                    if (response != null && response.body() != null) {
//                        MyLogger.pLog().d("=====" + response.body().toString());
//                        MyLogger.pLog().d("=====" + response.body().getCode());
//                closeWaitDialog();
                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                                //是否含有当前报价机构
//                        isShowButton(list);
                                setData(true, list);
//                        setDataList(response.body().getData());
                                handleOnSaleListAdapter.setEnableLoadMore(true);
                                marketForfaitingDetailSl.setRefreshing(false);
                            }
                        } else if (response.body().getCode() == 415) {
                            MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                            MyLogger.pLog().e(response.body().getMessage());
//                    finish();
                        } else {
                            handleOnSaleListAdapter.setEnableLoadMore(true);
                            marketForfaitingDetailSl.setRefreshing(false);
                            MyLogger.pLog().e(response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("onFailure：", "" + t.getMessage());
                }
            });
        } else if ("3".equals(userType)) {
            ActionPresenter.getInstance().reviewOfferListOnSaleListRet(marketForfaitingOfferListReq).enqueue(new Callback<MarketForfaitingOfferListRes>() {
                @Override
                public void onResponse(Call<MarketForfaitingOfferListRes> call, Response<MarketForfaitingOfferListRes> response) {
                    if (response != null && response.body() != null) {
//                        MyLogger.pLog().d("=====" + response.body().toString());
//                        MyLogger.pLog().d("=====" + response.body().getCode());
//                closeWaitDialog();
                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                                if (list != null && list.size() > 0) {
                                    setData(true, list);
                                    isShowButton(list);

                                } else {
                                    isSelectSell = 2;
                                }
//                        setDataList(response.body().getData());
                                handleOnSaleListAdapter.setEnableLoadMore(true);
                                marketForfaitingDetailSl.setRefreshing(false);
                            }
                        } else if (response.body().getCode() == 415) {
                            MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                            MyLogger.pLog().e(response.body().getMessage());
//                    finish();
                        } else {
                            handleOnSaleListAdapter.setEnableLoadMore(true);
                            marketForfaitingDetailSl.setRefreshing(false);
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
    }

    private void initAdapter() {
        List<MarketForfaitingOfferListRes.DataBean> data = new ArrayList<>();
        handleOnSaleListAdapter = new HandleOnSaleListAdapter(data, "1");
        handleOnSaleListAdapter.openLoadAnimation();
        marketForfaitingDetailRv.setAdapter(handleOnSaleListAdapter);
    }

    public void initItemListener() {
        handleOnSaleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                RadioButton radioButton = (RadioButton)view.findViewById(R.id.item_forfaiting_detail_offer_list_rb);
//                radioButton.setChecked(true);
//                handleOnSaleListAdapter.notifyDataSetChanged();
                handleOnSaleListAdapter.onItemClick(adapter, view, position);
                //获取当前选中的item的id 用于提交报价撮合
//                isSelect = ((MarketForfaitingOfferListRes.DataBean)adapter.getItem(position)).getId()+"";
                MyLogger.pLog().d("onItemClick: " + ((MarketForfaitingOfferListRes.DataBean) adapter.getItem(position)).getId());
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        handleOnSaleListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        handleOnSaleListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildClick: ");
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        handleOnSaleListAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildLongClick: ");
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemChildLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initRefreshLayout() {
        //启用刷新
        marketForfaitingDetailSl.setRefreshing(false);
        //禁用下拉刷新
//        marketForfaitingSl.setEnabled(false);
        marketForfaitingDetailSl.setColorSchemeResources(R.color.blue);
        marketForfaitingDetailSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        handleOnSaleListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initDetailOfferListData(assestId);

    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            handleOnSaleListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                handleOnSaleListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            handleOnSaleListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            handleOnSaleListAdapter.loadMoreComplete();
        }
    }

    //市场 福费廷 卖家 复核 确认撮合接口
//    public void confirmOfferData(String id) {
//        MyLogger.pLog().i("市场 福费廷 卖家 复核 确认撮合接口");
//        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
//        marketForfaitingOfferReq.setId(id);
//        ActionPresenter.getInstance().reviewOfferSubmitOnSaleListRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitOnSaleListRes>() {
//            @Override
//            public void onResponse(Call<ReviewOfferSubmitOnSaleListRes> call, Response<ReviewOfferSubmitOnSaleListRes> response) {
//                MyLogger.pLog().d("=====" + response.body().toString());
//                MyLogger.pLog().d("=====" + response.body().getCode());
//                if (response.body().getCode() == 300) {
//                    //确认撮合成功！
//                    reviewOfferSubmitOnSaleListRes = response.body();
//                    //回调数据到fragment
//                    baseDialogListener.positive(reviewOfferSubmitOnSaleListRes,isSelect);
////                    ToastUtils.showShort("提交成功");
//                } else {
//                    MyLogger.pLog().e(response.body().getMessage());
//                    ToastUtils.showShort(response.body().getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Log.d("onFailure：", "" + t.getMessage());
//            }
//        });
//    }
    //  是否显示选择驳回过
    public void isShowButton(List<MarketForfaitingOfferListRes.DataBean> list) {
        String companyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNullObject(list.get(i))) {
                    if("107".equals(list.get(i).getPriceState())){
                        isSelect = list.get(i).getId() + "";
                        companyname = list.get(i).getbOrgName() + "";
                        mDialogDetailLlBottom.setVisibility(View.VISIBLE);
                    }
                    if ("108".equals(list.get(i).getPriceState())) {
                        isSelectSell = 1;
                    }
                }
            }
        }
    }
}
