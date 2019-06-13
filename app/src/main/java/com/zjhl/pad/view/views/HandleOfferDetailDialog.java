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
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.adapter.HandleOnSaleListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.view.base.BaseFragment.PAGE_SIZE;

/**
 * @desc: HandleOfferDetailDialog
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class HandleOfferDetailDialog extends BaseDialog {
    private TextView mTvSure;//确定
    private ImageView mClose;//关闭
    private TextView mCcncel;//取消 驳回
    private View mLine;//竖线
    private View mReason;//驳回原因
    private LinearLayout mDialogDetailLl;//报价列表布局
    private LinearLayout mDialogDetailLlBottom;//报价列表底部布局
    private String assestId = "";//资产id
    private String tradingType = "";//资产类型1-福费廷 2-保理
    HandleOnSaleListAdapter handleOnSaleListAdapter;

    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;

    private int mNextRequestPage = 1;

    RecyclerView marketForfaitingDetailRv;
    SwipeRefreshLayout marketForfaitingDetailSl;

    private String isSelect = "";
    int isSelectSell = 0;//1有经办选择的报价了 0 没有

    public HandleOfferDetailDialog(Context context) {
        super(context);
    }

    public HandleOfferDetailDialog(Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }

    public HandleOfferDetailDialog(Context context, OnBaseDialogListener onBaseDialogListener, String assestId, String tradingType) {
        super(context, onBaseDialogListener);
        this.assestId = assestId;
        this.tradingType = tradingType;

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
    public HandleOfferDetailDialog(Context context, OnBaseDialogListener onBaseDialogListener,
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
    protected View getView() {  // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_offer_detail, null);   //得到各种
        mTvSure = (TextView) view.findViewById(R.id.dialog_offer_detail_tv_sure);
        mCcncel = (TextView) view.findViewById(R.id.dialog_offer_detail_tv_cancel);
        mReason = (TextView) view.findViewById(R.id.dialog_offer_detail_title_bohui);
        mDialogDetailLl = (LinearLayout) view.findViewById(R.id.dialog_offer_detail_ll);
        mDialogDetailLlBottom = (LinearLayout) view.findViewById(R.id.dialog_offer_detail_ll_bottom);
        mDialogDetailLlBottom.setVisibility(View.VISIBLE);
        mClose = (ImageView) view.findViewById(R.id.dialog_offer_detail_iv_close);
        mLine = (View) view.findViewById(R.id.dialog_offer_detail_line);
        marketForfaitingDetailRv = (RecyclerView) view.findViewById(R.id.market_forfaiting_detail_rv);
        marketForfaitingDetailSl = (SwipeRefreshLayout) view.findViewById(R.id.market_forfaiting_detail_sl);
//        if(){
//
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
                    baseDialogListener.negative("","");
                    dismiss();
                }
            });
            mTvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelectSell == 0) {
                        if (!StringUtils.isEmpty(isSelect)) {
                            initSureOrCancelDialogView("",context.getString(R.string.issue_forfaiting_sell_yesorno));
//                            confirmOfferData(isSelect);
//                            dismiss();
                        } else {
                            ToastUtils.showShort(context.getString(R.string.toast_handleoffer_choiceprice));
                        }
                    } else {
                        ToastUtils.showShort(context.getString(R.string.toast_handleoffer_piricecommit));
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
    public void initDetailOfferListData(String id, String tradingType) {
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
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

//                        MarketForfaitingOfferListRes.DataBean b = new MarketForfaitingOfferListRes.DataBean();
//                        list.add(b);
//                        list.add(new MarketForfaitingOfferListRes.DataBean());
//                        list.add(new MarketForfaitingOfferListRes.DataBean());
                                //是否含有当前报价机构
                                isShowButton(list);
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

//                        MarketForfaitingOfferListRes.DataBean b = new MarketForfaitingOfferListRes.DataBean();
//                        list.add(b);
//                        list.add(new MarketForfaitingOfferListRes.DataBean());
//                        list.add(new MarketForfaitingOfferListRes.DataBean());
                                //是否报过价了
                                isShowButton(list);
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
//                if(isSelectSell==0) {
                handleOnSaleListAdapter.onItemClick(adapter, view, position);
//                }
                //获取当前选中的item的id 用于提交报价撮合
                isSelect = ((MarketForfaitingOfferListRes.DataBean) adapter.getItem(position)).getId() + "";
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
        initDetailOfferListData(assestId, tradingType);

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

    //市场 福费廷 卖家 确认撮合接口
    public void confirmOfferData(String id) {
        MyLogger.pLog().i("市场 福费廷 卖家 确认撮合接口");
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(id);
        ActionPresenter.getInstance().marketForfaitingOfferConfirmRet(marketForfaitingOfferReq).enqueue(new Callback<MarketForfaitingDetailRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingDetailRes> call, Response<MarketForfaitingDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
                        //经办提交成功  刷新列表
                        baseDialogListener.positive();
                        //确认撮合成功！
//                        ToastUtils.showShort(context.getResources().getString(R.string.toast_market_forfaiting_detail_success));
                        ToastUtils.showShort(context.getString(R.string.toast_issue_frofaiting_editsuccess));
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
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

    //  是否显示选择的报价
    public void isShowButton(List<MarketForfaitingOfferListRes.DataBean> list) {
        String companyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        //如果当前用户报过价了 应该不可以报价了
//        if (StringUtils.isNullObject(dataBean1)) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNullObject(list.get(i))) {
                    if ("107".equals(list.get(i).getPriceState())) {
                        isSelectSell = 1;
                    }
//                    }
                }
            }
        }
    }

    //二次确认框
    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = context.getString(R.string.issue_forfaiting_sell_yesorno);
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(context, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                confirmOfferData(isSelect);
                dismiss();
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect, String companyName) {
                MyLogger.pLog().i("negative");
            }
        }, "" + dialogContent, context.getString(R.string.onsalelist_forfaiting_adapter_cancel), context.getString(R.string.onsalelist_forfaiting_adapter_sure));
        sureOrCancelDialog.show();
    }
}
