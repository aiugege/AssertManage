package com.zjhl.pad.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.EditInputFilterOneHunderd;
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.Model;
import com.zjhl.pad.app.utils.ModelFactoring;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.PxSpDpUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.AssertCommitReq;
import com.zjhl.pad.moudle.entity.req.BaojiatxReq;
import com.zjhl.pad.moudle.entity.req.BaoliAssertReq;
import com.zjhl.pad.moudle.entity.req.BohuiResonReq;
import com.zjhl.pad.moudle.entity.req.CansalResonReq;
import com.zjhl.pad.moudle.entity.req.ForfaitingRportReq;
import com.zjhl.pad.moudle.entity.req.IussingFactoringEntryReq;
import com.zjhl.pad.moudle.entity.req.LookBohuiReasonReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.req.MyassetsReq;
import com.zjhl.pad.moudle.entity.req.ReviewCheckTransferReq;
import com.zjhl.pad.moudle.entity.res.AssertCommitRes;
import com.zjhl.pad.moudle.entity.res.BaojiatxRs;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.BohuiResonRes;
import com.zjhl.pad.moudle.entity.res.CanselRes;
import com.zjhl.pad.moudle.entity.res.CheckLetterRes;
import com.zjhl.pad.moudle.entity.res.CheckTransferLetterRes;
import com.zjhl.pad.moudle.entity.res.DownCheckLetterRes;
import com.zjhl.pad.moudle.entity.res.LookBohuiReasonRs;
import com.zjhl.pad.moudle.entity.res.MyOfferBaoliRs;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.ClassifyMorAdapter;
import com.zjhl.pad.view.adapter.DataOrderMainAdapter;
import com.zjhl.pad.view.adapter.MyOfferAdapter;
import com.zjhl.pad.view.adapter.MyOfferBaoliAdapter;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.CommonPopupWindow;
import com.zjhl.pad.view.views.DownloadDialog;
import com.zjhl.pad.view.views.RejectDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: MyQuoteActivity.java 我的报价
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/8 14:37
 * Changes (from 2018/5/8)
 */
public class MyQuoteActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_fufeiting)
    RelativeLayout tvFufeiting;
    @BindView(R.id.bt_public_data)
    TextView btPublicData;
    @BindView(R.id.bt_select_shuaixuan)
    TextView btSelectShuaixuan;

    @BindView(R.id.data_order)
    RelativeLayout dataOrder;
    @BindView(R.id.bt_select)
    TextView btSelect;

    @BindView(R.id.rl_shuaixuan)
    RelativeLayout rlShuaixuan;

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    DataOrderMainAdapter mainAdapter;
    ClassifyMorAdapter moreAdapter;
    @BindView(R.id.tv_no_assert)
    TextView tvNoAssert;
    private ListView lsvMore;
    private ListView order_more_list;
    private List<Map<String, Object>> mainList;
    private List<Map<String, Object>> mainFactoringList;
    private String[] textList = {MyApplication.mMyApplication.getResources().getString(R.string.lable_forfaiting), MyApplication.mMyApplication.getResources().getString(R.string.lable_facotring)};
    private String assetsType;
    private String bizhong_type;
    private String tiexianlvStartType;
    private String tiexianlvEndType;
    private int mNextRequestPage = 1;
    private int mNextRequestPage1 = 1;
    private String mine_fufeiting;
    private String country_id;
    private String money_type;
    private String ascDesc = "desc";
    private String orderBy;
    private String offerType;
    private String userType;
    //福费廷
    private MyOfferAdapter quoteListAdapter;
    private MyOfferBaoliAdapter boliofferAdapter;
    private AlertDialog myDialog = null;
    private Button bt_payButton;
    boolean isChecked = false;
    private String scope_start;
    private String scope_end;
    SureOrCancelDialog sureOrCancelDialog;
    private String assestId;
    private String et_tiexianlv;
    private AlertDialog myDialogBohui = null;
    private String advice_reason;
    private String checkState;

    private AlertDialog myDialogBohuiReason = null;
    String advise = "";
    //报价id
    private String mpriceId;

    //增加前缀防止重复
    private String shareKey = Constants.SPKEY.SP_MY_QUOTE_KEY;
    private RejectDialog rejectDialog;

    String discountrate = "";
    double doubleTransferRate;
    Model model = new Model();
    ModelFactoring modelFactoring = new ModelFactoring();
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mine_quote);
        mContext = this;
        ButterKnife.bind(this);
        tvContent.setText(R.string.myquote_title);
        myDialogBohui = new AlertDialog.Builder(MyQuoteActivity.this, R.style.Dialog).create();
        assetsType = "1";
        resetFiltrate();
        initQuoteAdapter(assetsType);
        myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();


        myDialogBohuiReason = new AlertDialog.Builder(MyQuoteActivity.this, R.style.Dialog).create();
        rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        initModle();
        initModleFactoring();
        initData();

        if ("1".equals(assetsType)) {
            mNextRequestPage = 1;
            getMyassertData();
        } else if ("2".equals(assetsType)) {
            mNextRequestPage = 1;
            getBaoLiAssebao();
        }
        initRefreshLayout();
//
        initItemListener();

    }


    private void getBaoLiAssebao() {
//        if (TextUtils.isEmpty(assetsType)){
//            assetsType = "1";
//        }
        //保理
        BaoliAssertReq baoliRes = new BaoliAssertReq();
        baoliRes.setAssetsType(assetsType);
        baoliRes.setPage(mNextRequestPage1);
        baoliRes.setPageSize(PAGE_SIZE);
        baoliRes.setCheckState(offerType);
        baoliRes.setMinimum(scope_start);
        baoliRes.setMaximum(scope_end);
        baoliRes.setCurrency(bizhong_type);
        baoliRes.setMinTransferRate(tiexianlvStartType);
        baoliRes.setMaxTransferRate(tiexianlvEndType);
        baoliRes.setCountryId(country_id);
        baoliRes.setAscDesc(ascDesc);
        baoliRes.setOrderBy(orderBy);
        ActionPresenter.getInstance().myasserRet(baoliRes).enqueue(new Callback<MyOfferBaoliRs>() {
            @Override
            public void onResponse(Call<MyOfferBaoliRs> call, Response<MyOfferBaoliRs> response) {

//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage1 == 1) {
                                setDataBaoli(true, response.body().getData());
                            } else if (mNextRequestPage1 > 1 && mNextRequestPage1 <= response.body().getTotalPage()) {
                                setDataBaoli(false, response.body().getData());
                            } else {
                                boliofferAdapter.loadMoreEnd(false);
                            }

                            boliofferAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
                        } else {
                            boliofferAdapter.setEnableLoadMore(true);
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


    private void setDataBaoli(boolean isRefresh, List dataBaoli) {
        //增加页码  设置数据
        mNextRequestPage1++;
        final int size = dataBaoli == null ? 0 : dataBaoli.size();
        if (size > 0) {
            tvNoAssert.setVisibility(View.GONE);
            swipeLayout.setVisibility(View.VISIBLE);
        } else {
            swipeLayout.setVisibility(View.GONE);
            tvNoAssert.setVisibility(View.VISIBLE);
        }
        if (isRefresh) {
            boliofferAdapter.setNewData(dataBaoli);

        } else {
            if (size > 0) {
                boliofferAdapter.addData(dataBaoli);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            boliofferAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            boliofferAdapter.loadMoreComplete();
        }
    }


    private void initItemListener() {
//保理列表
        boliofferAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                assestId = boliofferAdapter.getItem(position).getId() + "";
                mpriceId = boliofferAdapter.getItem(position).getPriceId();
                discountrate = boliofferAdapter.getItem(position).getTransferRate();
                advise = boliofferAdapter.getItem(position).getRejectOpinion();
                checkState = boliofferAdapter.getItem(position).getCheckState();
                if (!StringUtils.isEmpty(discountrate)) {
                    doubleTransferRate = Double.valueOf(discountrate);
                }
                if (view != null) {
                    switch (view.getId()) {
                        case R.id.bt_lock:
                            //查看详情
                            startActivity(new Intent(MyQuoteActivity.this, OfferDetailActivity.class));
                            break;
                        case R.id.bt_offer_look:
                            //查看邀约函
//                            LetterOffer();
                            break;
                        case R.id.tv_fuhe_cansal:
                            initSureOrCancelDialogView("1", getString(R.string.toast_forfaiting_sell_cancelhint1));
//                            //取消
//                            reviewFactoringCancelBuyRet();
                            break;
                        case R.id.tv_fuhe_sure:
                            if ("108".equals(checkState)) {
                                initSureOrCancelDialogView("2", getString(R.string.toast_forfaiting_sell_cancelcontent));
                            } else {
//                            //确认
                                reviewFactoringSureRet();
                            }
                            break;
                        case R.id.tv_bohuiyuanyin:
                            //提交驳回原因
                            showBohuiResonDiog();
                            break;
                        case R.id.tv_fuhe_bohui:
                            //驳回
                            showReviewRejectDialog("2");

                            break;
                        case R.id.tv_fuhe_commit:
                            initSureOrCancelDialogView("3", getString(R.string.myquote_sure_quote));
//                            //提交
//                            reviewOfferForfaitingCommitRet();
                            break;

                        case R.id.tv_yuanyin:
                            //查看驳回原因
//                            getLookBoHuiReason();
                            showBohuiReason();
                            break;
                        case R.id.tv_offer_edit:
                            //编辑
                            initPopWindow();
                            break;
                        case R.id.tv_offer_edit1:
                            //查看邀约函

                            break;
                        case R.id.tv_offer_report:
                            //再次发布

                            break;
                        case R.id.tv_check_more:
                            //查看更多
                            showCheckMoreSide(view, assestId, assetsType, "2");
                            break;
                    }
                }
            }
        });
        /**
         * 保理
         */
        boliofferAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                assestId = boliofferAdapter.getItem(position).getId() + "";
                mpriceId = boliofferAdapter.getItem(position).getPriceId();
                checkState = boliofferAdapter.getItem(position).getCheckState();
                //查看详情
//                DisPatcher.startMarketFactoringDetailActivity(MyQuoteActivity.this, assestId + "", "0");
                DisPatcher.startMyQuoteFactoringDetailActivity(MyQuoteActivity.this, assestId + "", mpriceId);

            }
        });

        /**
         * 福费廷
         */
        quoteListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                assestId = quoteListAdapter.getItem(position).getId() + "";
                mpriceId = quoteListAdapter.getItem(position).getPriceId() + "";
                //查看详情
//                DisPatcher.startMarketForfaitingDetailActivity(MyQuoteActivity.this, assestId + "", "0");
                DisPatcher.startMyQuoteForfaitingDetailActivity(MyQuoteActivity.this, assestId + "", mpriceId);

            }
        });

        quoteListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                assestId = quoteListAdapter.getItem(position).getId() + "";
                mpriceId = quoteListAdapter.getItem(position).getPriceId();

                advise = quoteListAdapter.getItem(position).getRejectOpinion();
                checkState = quoteListAdapter.getItem(position).getRecheckState();
//                discountrate = quoteListAdapter.getItem(position).getDiscountRate();
                discountrate = quoteListAdapter.getItem(position).getAssetsDiscountRate();
                if (!StringUtils.isEmpty(discountrate)) {
                    doubleTransferRate = Double.valueOf(discountrate);
                }
                if (view != null) {
                    switch (view.getId()) {
                        case R.id.tv_fuhe_bohui1:
                            //驳回
                            showReviewRejectDialog("2");
                            break;
                        case R.id.tv_fuhe_commit1:
                            initSureOrCancelDialogView("1", getString(R.string.myquote_sure_quote));
//                            //提交
//                            reviewOfferForfaitingCommitRet();
                            break;
                        case R.id.tv_fuhe_cansal:

                            initSureOrCancelDialogView("2", getString(R.string.toast_forfaiting_sell_cancelhint1));
//                            //删除 取消
//                            reviewForfaitingCancelBuyRet();
                            break;
                        case R.id.tv_offer_edit2:
                            //编辑
                            initPopWindow();
                            break;
                        case R.id.tv_yuanyin:
                            //驳回原因
                            showBohuiReason();
                            break;
                        case R.id.tv_fuhe_sure:
                            if ("108".equals(checkState)) {
                                initSureOrCancelDialogView("3", getString(R.string.toast_forfaiting_sell_cancelcontent));
                            } else {
//                            //确认
                                getMackSure();
                            }
                            break;
                        case R.id.bt_offer_look_book:
                            //查看邀约函
                            downCheckLetterRet(assestId);
//                            DisPatcher.startCheckLetterActivity(MyQuoteActivity.this, assestId);
                            break;
                        case R.id.tv_offer_edit1:
                            //查看要约函
                            downCheckLetterRet(assestId);
//                            DisPatcher.startCheckLetterActivity(MyQuoteActivity.this, assestId);
                            break;
                        case R.id.tv_offer_report:
                            //再次发布
//                            DisPatcher.startCheckLetterActivity(MyQuoteActivity.this, assestId);
                            initSureOrCancelDialogView("4", getString(R.string.myquote_reportorno));
                            break;
                        case R.id.tv_check_more:
                            //查看更多(复核-撮合成功)
                            showCheckMoreCenter(view, assestId, assetsType, "0");
                            break;
                        case R.id.tv_check_more_1:
                            //查看更多(复核-撮合成功)
                            showCheckMoreCenter(view, assestId, assetsType, "1");
                            break;
                        case R.id.tv_check_more_report:
                            //查看更多(复核-交易完成)
                            showCheckMoreCenter(view, assestId, assetsType, "0");
                            break;
                        case R.id.tv_check_more_right:
                            //查看更多右(经办 撮合成功，交易完成)
                            showCheckMoreSide(view, assestId, assetsType, "0");
                            break;
                        case R.id.tv_check_more_right_1:
                            //查看更多右(经办 已取消)
                            showCheckMoreSide(view, assestId, assetsType, "1");
                            break;

                    }


                }


            }


        });


    }

    //福费廷 买家确认
    private void getMackSure() {
        MessageListReq macksureReq = new MessageListReq();
        macksureReq.setId(assestId);

        ActionPresenter.getInstance().reviewForfaitingSureRet(macksureReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && !TextUtils.isEmpty(response.code() + "")) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(response.body().getMessage());
                        refresh();
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

    //保理 买家确认
    private void reviewFactoringSureRet() {
        MessageListReq macksureReq = new MessageListReq();
        macksureReq.setId(assestId);

        ActionPresenter.getInstance().reviewFactoringSureRet(macksureReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && !TextUtils.isEmpty(response.code() + "")) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(response.body().getMessage());

                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        refresh();
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

    private void getAssertCommit() {
        //提交
        AssertCommitReq assertCommitReq = new AssertCommitReq();
        assertCommitReq.setId(assestId);

        ActionPresenter.getInstance().asserRet(assertCommitReq).enqueue(new Callback<AssertCommitRes>() {
            @Override
            public void onResponse(Call<AssertCommitRes> call, Response<AssertCommitRes> response) {

                if (response != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        refresh();
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

    //我的报价 福费廷复核提交接口
  /*  public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferForfaitingCommitRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferForfaitingCommitRet = mApi.reviewOfferForfaitingCommitRet(createRequestBody(data));
        return reviewOfferForfaitingCommitRet;
    }
    */
    private void reviewOfferForfaitingCommitRet() {
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(mpriceId);

        ActionPresenter.getInstance().reviewOfferForfaitingCommitRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        refresh();
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

    private void getAssertDelete() {
        //取消
        CansalResonReq quxiaoResonReq = new CansalResonReq();
        quxiaoResonReq.setId(assestId);
        quxiaoResonReq.setOperateButton("3");
        ActionPresenter.getInstance().cansalRet(quxiaoResonReq).enqueue(new Callback<CanselRes>() {
            @Override
            public void onResponse(Call<CanselRes> call, Response<CanselRes> response) {

                if (response != null) {
                    if (response.body().getCode() == 300) {
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
//                        ToastUtils.showShort(response.body().getMessage());
                        if ("1".equals(assetsType)) {
                            mNextRequestPage = 1;
                            getMyassertData();
                        } else if ("2".equals(assetsType)) {
                            mNextRequestPage = 1;
                            getBaoLiAssebao();
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

    /**
     * //复核  福费廷 买家复核取消接口 返回复用BlockChainRes  请求复用MessageListReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGCANCELBUY) Call<BlockChainRes> reviewForfaitingCancelBuyRet(@Body RequestBody requestBody);
     * <p>
     * public Call<BlockChainRes> reviewForfaitingCancelBuyRet(MessageListReq data) {
     * Call<BlockChainRes> reviewForfaitingCancelBuyRet = mApi.reviewForfaitingCancelBuyRet(createRequestBody(data));
     * return reviewForfaitingCancelBuyRet;
     * }
     */
    private void reviewForfaitingCancelBuyRet() {
        //取消
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setId(assestId);
        ActionPresenter.getInstance().reviewForfaitingCancelBuyRet(messageListReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {

                if (response != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_soldinassets_cancel_success));
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        if ("1".equals(assetsType)) {
                            mNextRequestPage = 1;
                            getMyassertData();
                        } else if ("2".equals(assetsType)) {
                            mNextRequestPage = 1;
                            getBaoLiAssebao();
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

    /**
     * /**
     * //复核 保理 买家复核取消接口 返回复用BlockChainRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGCANCELBUY) Call<BlockChainRes> reviewFactoringCancelBuyRet(@Body RequestBody requestBody);
     */
    private void reviewFactoringCancelBuyRet() {
        //取消
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setId(assestId);
        ActionPresenter.getInstance().reviewFactoringCancelBuyRet(messageListReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {

                if (response != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_soldinassets_cancel_success));
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
//                        if ("1".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getMyassertData();
//                        } else if ("2".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getBaoLiAssebao();
//                        }
                        refresh();
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
     * /**
     * //我的报价 复核岗审核通过、驳回、取消
     *
     * @POST(Constants.NETPATH.OFFERBOHUIPATH) Call<BohuiResonRes> offerBohuiRet(@Body RequestBody requestBody);
     * public Call<BohuiResonRes> offerBohuiRet(AssertCommitReq data) {
     * Call<BohuiResonRes> offerBohuiRet = mApi.offerBohuiRet(createRequestBody(data));
     * return offerBohuiRet;
     * }
     */


    private void offerBohuiRet(final String advice) {
        AssertCommitReq assertCommitReq = new AssertCommitReq();
        assertCommitReq.setId(mpriceId);
        assertCommitReq.setRefuseAdvice("" + advice);
        ActionPresenter.getInstance().offerBohuiRet(assertCommitReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
//                        if ("2".equals(advice)) {
//                            ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));

//                        }
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
//                        if ("1".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getMyassertData();
//                        } else if ("2".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getBaoLiAssebao();
//                        }
                        refresh();
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
     * /**
     * //我的报价 复核岗审核通过、驳回、取消
     *
     * @POST(Constants.NETPATH.OFFERBOHUIPATH) Call<BohuiResonRes> factoringOfferBohuiRet(@Body RequestBody requestBody);
     * public Call<BohuiResonRes> factoringOfferBohuiRet(AssertCommitReq data) {
     * Call<BohuiResonRes> factoringOfferBohuiRet = mApi.factoringOfferBohuiRet(createRequestBody(data));
     * return factoringOfferBohuiRet;
     * }1, "提交", 2, "驳回" 3,"取消";
     */


    private void factoringOfferBohuiRet(final String type) {
        AssertCommitReq assertCommitReq = new AssertCommitReq();
        assertCommitReq.setId(mpriceId);
        assertCommitReq.setOperateButton(type);
        assertCommitReq.setRefuseAdvice("");
        ActionPresenter.getInstance().factoringOfferBohuiRet(assertCommitReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
//                        if ("2".equals(type)) {
//                            ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));
//                        }
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
//                        if ("1".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getMyassertData();
//                        } else if ("2".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getBaoLiAssebao();
//                        }
                        refresh();
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

    Window window_bohui;
    ImageView iv_close_bohui;

    private void showBohuiResonDiog() {
        if (myDialogBohui != null) {
            myDialogBohui.setCancelable(false);
            myDialogBohui.show();
            window_bohui = myDialogBohui.getWindow();
        } else {
            myDialogBohui = new AlertDialog.Builder(MyQuoteActivity.this, R.style.Dialog).create();
            window_bohui = myDialogBohui.getWindow();
            myDialogBohui.setCancelable(false);
        }


        window.setContentView(R.layout.assert_reason_dilog);
        //驳回内容
        EditText tv_adviceM = (EditText) window.findViewById(R.id.tv_advice);
        advice_reason = tv_adviceM.getText().toString().trim();
        Button bt_reason_commit = (Button) window.findViewById(R.id.bt_reason_commit);
        iv_close_bohui = (ImageView) window.findViewById(R.id.iv_close);
        if (StringUtils.isEmpty(advice_reason)) {
            ToastUtils.showShort(getString(R.string.hint_dialog_reject_content));
        } else {
            bt_reason_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getbohuiReson();
                }
            });
        }
        iv_close_bohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialogBohui.dismiss();
            }
        });
    }

    private void getbohuiReson() {
        BohuiResonReq bohuiResonReq = new BohuiResonReq();
        bohuiResonReq.setId(assestId);
        bohuiResonReq.setOperateButton("2");
        bohuiResonReq.setAdvise(advice_reason);
        ActionPresenter.getInstance().bohuiRet(bohuiResonReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
//                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));

                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        getMyassertData();
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


    private void initPopWindow() {

        if (myDialog != null) {
            myDialog.setCancelable(false);
            myDialog.show();
            window = myDialog.getWindow();
        } else {
            myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();
            window = myDialog.getWindow();
            myDialog.setCancelable(false);
        }


        window.setContentView(R.layout.offer_edit);
        myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
        TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
        TextView tv_commit = (TextView) window.findViewById(R.id.tv_commit);
        if ("2".equals(assetsType)) {
            tv_title.setText(R.string.filtrate_factoring_transferrate);
        }
        final EditText ed_tiexianlv1 = (EditText) window.findViewById(R.id.ed_tiexianlv1);
        EditInputFilterOneHunderd[] filters = {new EditInputFilterOneHunderd()};
        ed_tiexianlv1.setFilters(filters);
        if ("2".equals(assetsType)) {
            ed_tiexianlv1.setHint(R.string.filtrate_factoring_transferrate);
        }

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //贴现率
                et_tiexianlv = ed_tiexianlv1.getText().toString().trim();
                if ("1".equals(assetsType)) {
                    String rateStr = et_tiexianlv;
                    double rateDouble;
                    if (!StringUtils.isEmpty(rateStr)) {
//                    initDetailData(rateStr);
                        rateDouble = Double.valueOf(rateStr);
                        if (rateDouble <= doubleTransferRate) {
                            commitTiexianlv();
                        } else {
                            ToastUtils.showShort(getString(R.string.toast_myquote_nodiscountrate));
                        }
                    }
                } else {
                    String rateStr = et_tiexianlv;
                    double rateDouble;
                    if (!StringUtils.isEmpty(rateStr)) {
//                    initDetailData(rateStr);
                        rateDouble = Double.valueOf(rateStr);
                        if (rateDouble <= doubleTransferRate) {
                            commitTiexianlv();
                        } else {
                            ToastUtils.showShort(getString(R.string.toast_myquote_nodiscountrate));
                        }
                    }
                }
//                commitTiexianlv();
                myDialog.dismiss();
            }
        });


    }

    private void commitTiexianlv() {
        //贴现率
        BaojiatxReq baojiaReq = new BaojiatxReq();
        baojiaReq.setDiscountRate(et_tiexianlv);
        baojiaReq.setId(mpriceId);

        ActionPresenter.getInstance().myasserRet(baojiaReq).enqueue(new Callback<BaojiatxRs>() {
            @Override
            public void onResponse(Call<BaojiatxRs> call, Response<BaojiatxRs> response) {

                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
                    if (response.body().getCode() == 300) {
//                        if ("1".equals(assetsType)) {
//                            ToastUtils.showShort(getString(R.string.toast_myquote_editprice));
//                        } else {
//                            ToastUtils.showShort(getString(R.string.toast_myquote_editprice1));
//                        }

                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        refresh();
//                        if("1".equals(assetsType)){
//                            reviewOfferForfaitingCommitRet();
//                        }else if("2".equals(assetsType)){
//                            reviewOfferForfaitingCommitRet();
//                        }
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


    ImageView iv_close;
    Window window;

    private void LetterOffer() {
        if (myDialog != null) {
            myDialog.setCancelable(false);
            myDialog.show();
            window = myDialog.getWindow();
        } else {
            myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();
            window = myDialog.getWindow();
            myDialog.setCancelable(false);
        }


        window.setContentView(R.layout.regist_dialogs);
        iv_close = (ImageView) window.findViewById(R.id.iv_close);
        bt_payButton = (Button) window.findViewById(R.id.bt_payButton);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                isChecked = true;
            }
        });
    }

    private void initQuoteAdapter(String assetsType) {
        List<MyOfferBaoliRs.DataBean> dataBaoli = new ArrayList<>();
        boliofferAdapter = new MyOfferBaoliAdapter(dataBaoli);
        boliofferAdapter.openLoadAnimation();
//        rvList.setAdapter(boliofferAdapter);

        List<MyOfferFufei.DataBean> data = new ArrayList<>();
        quoteListAdapter = new MyOfferAdapter(data);
        quoteListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadFuFeiting();
            }
        });
        boliofferAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreBaoli();
            }
        });
        quoteListAdapter.openLoadAnimation();
        setChangeAdapter(assetsType);
    }

    private void setChangeAdapter(String assetsType) {
        if ("1".equals(assetsType)) {
//            mineForfaitingOnSaleListAdapter.setHasStableIds(true);
            rvList.setAdapter(quoteListAdapter);
        } else if ("2".equals(assetsType)) {
//            mineFactoringOnSaleListAdapter.setHasStableIds(true);
            rvList.setAdapter(boliofferAdapter);
        }
        initItemListener();
    }

    private void loadFuFeiting() {
        getMyassertData();
    }

    private void loadMoreBaoli() {
        getBaoLiAssebao();
    }


    //    private void initQuoteBoaliAdapter() {
//        List<MyOfferBaoliRs.DataBean> data1 = new ArrayList<>();
//        boliofferAdapter = new MyOfferBaoliAdapter(data1);
//        boliofferAdapter.openLoadAnimation();
//        rvList.setAdapter(boliofferAdapter);
//    }
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
//        boliofferAdapter.setEnableLoadMore(false);
        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
                mNextRequestPage = 1;
                quoteListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                getMyassertData();
            } else if ("2".equals(assetsType)) {
                boliofferAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mNextRequestPage1 = 1;
                getBaoLiAssebao();
            }
        }

    }


    private void setData(boolean isRefresh, List dataFufeiting) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = dataFufeiting == null ? 0 : dataFufeiting.size();
        if (size > 0) {
            tvNoAssert.setVisibility(View.GONE);
            swipeLayout.setVisibility(View.VISIBLE);
        } else {
            swipeLayout.setVisibility(View.GONE);
            tvNoAssert.setVisibility(View.VISIBLE);
        }
        if (isRefresh) {
            quoteListAdapter.setNewData(dataFufeiting);

        } else {
            if (size > 0) {
                quoteListAdapter.addData(dataFufeiting);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            quoteListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            quoteListAdapter.loadMoreComplete();
        }
    }


    private void initData() {
        myDialog = new AlertDialog.Builder(this, R.style.Dialog).create();

    }

    //
    private void getMyassertData() {
        MyassetsReq myassetsRes = new MyassetsReq();
        myassetsRes.setAssetsType(assetsType);
        myassetsRes.setCurrency(bizhong_type);
        myassetsRes.setRecheckState(offerType);
        myassetsRes.setMinimum(scope_start);
        myassetsRes.setMaximum(scope_end);
        myassetsRes.setMinDiscountRate(tiexianlvStartType);
        myassetsRes.setMaxDiscountRate(tiexianlvEndType);
        myassetsRes.setPage(mNextRequestPage);
        myassetsRes.setPageSize(PAGE_SIZE);
        myassetsRes.setDebtType(mine_fufeiting);
        myassetsRes.setCountryId(country_id);
        myassetsRes.setOpenFullName(money_type);
        myassetsRes.setAscDesc(ascDesc);
        myassetsRes.setOrderBy(orderBy);

        ActionPresenter.getInstance().myasserRet(myassetsRes).enqueue(new Callback<MyOfferFufei>() {
            @Override
            public void onResponse(Call<MyOfferFufei> call, Response<MyOfferFufei> response) {
//                MyLogger.pLog().d("LoginRes："+response.body().toString());
//                MyLogger.pLog().d("LoginRes："+response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage == 1) {
                                setData(true, response.body().getData());
                            } else if (mNextRequestPage > 1 && mNextRequestPage <= response.body().getTotalPage()) {
                                setData(false, response.body().getData());
                            } else {
                                quoteListAdapter.loadMoreEnd(false);
                            }

                            quoteListAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
                        } else {
                            quoteListAdapter.setEnableLoadMore(true);
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

    private void initModleFactoring() {
        mainFactoringList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < modelFactoring.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", modelFactoring.LISTVIEWTXT[i]);
            mainFactoringList.add(map);
        }
    }

    private void initModle() {
        mainList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < model.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", model.LISTVIEWTXT[i]);
            mainList.add(map);
        }
    }

    @OnClick({R.id.tv_fufeiting, R.id.bt_public_data, R.id.bt_select_shuaixuan, R.id.iv_excite, R.id.data_order, R.id.rl_shuaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fufeiting:
                //福费廷，
                orderBy = "";
                showFufeiting();
                break;
            case R.id.bt_public_data:
            case R.id.data_order:
                //发布时间排序
                if ("1".equals(assetsType)) {
                    showDataOrder();
                } else {
                    showDataFactoringOrder();
                }
                break;
            case R.id.iv_excite:
                finish();
                break;
            case R.id.bt_select_shuaixuan:
            case R.id.rl_shuaixuan:
                //筛选
//                Intent intent = new Intent(MyQuoteActivity.this, FiltrateActivity.class);
//                intent.putExtra("shareKey", shareKey);
//                startActivityForResult(intent, 1);
                DisPatcher.startFiltrateActivity(MyQuoteActivity.this, shareKey, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode) {
            //资产状态
            offerType = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "offerType", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"currency_type",currency);
            //币种
            bizhong_type = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "currency_type", "");


            //贴现率范围
            tiexianlvStartType = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "tiexianlv_start", "");
            tiexianlvEndType = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "tiexianlv_end", "");

            //承兑金额范围
            scope_start = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "scope_start", "");
            scope_end = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "scope_end", "");

            //开证行名称
            money_type = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "open_book_name", "");

            //福费廷类型
            mine_fufeiting = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "mine_fufeiting", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"country_id",guojia_seconde_id);
            country_id = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "country_id", "");
            assetsType = (String) SharedPreferanceUtils.get(MyQuoteActivity.this, shareKey + "assetsType", "1");
            refresh();
        }
    }

    private void showDataOrder() {
//        orderBy = "publish_time";
        View popupView_order = getLayoutInflater().inflate(R.layout.mine_list_data_order, null);

        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(MyQuoteActivity.this, mainList);
//        mainAdapter.setSelectItem(0);
        order_main_list.setAdapter(mainAdapter);

        if ("publish_time".equals(orderBy)) {
            mainAdapter.setSelectItem(0);
        } else if ("maturity".equals(orderBy)) {
            mainAdapter.setSelectItem(1);
        } else if ("amount".equals(orderBy)) {
            mainAdapter.setSelectItem(2);
        } else if ("discount_rate".equals(orderBy)) {
            mainAdapter.setSelectItem(3);
        }
        order_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    //资产发布时间
                    orderBy = "publish_time";
                } else if (position == 1) {
                    //资产到期日
                    orderBy = "maturity";
                } else if (position == 2) {
                    //兑现金额
                    orderBy = "amount";
                } else if (position == 3) {
                    //贴现率
                    orderBy = "discount_rate";
                }
                initAdapter(model.MORELISTTXT[position]);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                btPublicData.setText(model.LISTVIEWTXT[position]);

            }
        });
        order_main_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 一定要设置这个属性，否则ListView不会刷新
        initAdapter(model.MORELISTTXT[0]);
//        order_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textList));
        final PopupWindow window_quote = new PopupWindow(popupView_order, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_quote.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_quote.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_quote.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_quote.update();

        if ("asc".equals(ascDesc)) {
            moreAdapter.setSelectItem(0);
        } else if ("desc".equals(ascDesc)) {
            moreAdapter.setSelectItem(1);
        }
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_quote.showAsDropDown(btSelect, 0, 20);
        order_more_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                btPublicData.setText(position);
                if (position == 0) {
//                    btPublicData.setText(getString(R.string.filtrate_asc));
                    ascDesc = "asc";
                } else if (position == 1) {
//                    btPublicData.setText(getString(R.string.filtrate_desc));
                    ascDesc = "desc";
                }
                moreAdapter.setSelectItem(position);
                moreAdapter.notifyDataSetChanged();
                window_quote.dismiss();

                setChangeAdapter(assetsType);
                refresh();

            }
        });
    }

    //保理
    private void showDataFactoringOrder() {
//         getLayoutInflater().inflate(R.layout.mine_list_data_order,null);
        View popupView_order = LayoutInflater.from(this).inflate(R.layout.mine_list_data_order, null);
//        View root = getLayoutInflater().inflate.inflate(R.layout.mine_list_data_order, null);

        //资产录入时间
//        orderBy = "publish_time";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(MyQuoteActivity.this, mainFactoringList);
//        mainAdapter.setSelectItem(0);
        order_main_list.setAdapter(mainAdapter);

        if ("publish_time".equals(orderBy)) {
            mainAdapter.setSelectItem(0);
        } else if ("amount".equals(orderBy)) {
            mainAdapter.setSelectItem(1);
        } else if ("maturity".equals(orderBy)) {
            mainAdapter.setSelectItem(2);
        } else if ("transfer_rate".equals(orderBy)) {
            mainAdapter.setSelectItem(3);
        }
        order_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    //资产录入时间
                    orderBy = "publish_time";
                } else if (position == 1) {
                    //金额
                    orderBy = "amount";
                } else if (position == 2) {
                    //保理到期日
                    orderBy = "maturity";
                } else if (position == 3) {
                    //转让利率
                    orderBy = "transfer_rate";
                }
                initAdapter(modelFactoring.MORELISTTXT[position]);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                btPublicData.setText(modelFactoring.LISTVIEWTXT[position]);

            }
        });
        order_main_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 一定要设置这个属性，否则ListView不会刷新
        initAdapter(modelFactoring.MORELISTTXT[0]);
//        order_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textList));
        final PopupWindow window_quote = new PopupWindow(popupView_order, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_quote.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_quote.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_quote.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_quote.update();

        if ("asc".equals(ascDesc)) {
            moreAdapter.setSelectItem(0);
        } else if ("desc".equals(ascDesc)) {
            moreAdapter.setSelectItem(1);
        }
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_quote.showAsDropDown(btSelect, 0, 20);
        order_more_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                btPublicData.setText(position);
                if (position == 0) {
//                    btPublicData.setText("升序");
                    ascDesc = "asc";
                } else if (position == 1) {
//                    btPublicData.setText("降序");
                    ascDesc = "desc";
                }
//                SharedPreferanceUtils.put(MyQuoteActivity.this,"order_data",ascDesc);
                moreAdapter.setSelectItem(position);

                moreAdapter.notifyDataSetChanged();
                window_quote.dismiss();
                refresh();
            }
        });
    }

    private void initAdapter(String[] array) {
        moreAdapter = new ClassifyMorAdapter(this, array);
        order_more_list.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    private void showFufeiting() {
        final View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.item_filter_list, textList));
        final PopupWindow window_fufeiting = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_fufeiting.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_fufeiting.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_fufeiting.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_fufeiting.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_fufeiting.showAsDropDown(btSelect, 0, 20);
        lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                btSelect.setText(textList[position]);

                if (0 == position) {
                    //福费廷
                    assetsType = "1";
//                    getMyassertData();
                } else if (1 == position) {
                    //保理
                    assetsType = "2";
//                    getBaoLiAssebao();

                }
                btPublicData.setText(getString(R.string.filtrate_senddate));
                SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "assetsType", assetsType);

                window_fufeiting.dismiss();
//                initQuoteAdapter(assetsType);
                setChangeAdapter(assetsType);
                refresh();

            }
        });
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "assetsType", assetsType);
    }

    private void getLookBoHuiReason() {
        LookBohuiReasonReq lookquxiaoResonReq = new LookBohuiReasonReq();
        lookquxiaoResonReq.setAuditId(assestId);
        if ("1".equals(assetsType)) {
            lookquxiaoResonReq.setAuditType("2");
        } else if ("2".equals(assetsType)) {
            lookquxiaoResonReq.setAuditType("3");

        }

        ActionPresenter.getInstance().lookcansalRet(lookquxiaoResonReq).enqueue(new Callback<LookBohuiReasonRs>() {
            @Override
            public void onResponse(Call<LookBohuiReasonRs> call, Response<LookBohuiReasonRs> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(response.body().getMessage());
                        //意见
                        advise = response.body().getData().getAdvise();
                        showBohuiReason();
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


    private void showBohuiReason() {

        Window window_bohui_reson;
        ImageView iv_close_bohui_reason;
        if (myDialogBohuiReason != null) {
            myDialogBohuiReason.setCancelable(false);
            myDialogBohuiReason.show();
            window_bohui_reson = myDialogBohuiReason.getWindow();
        } else {
            myDialogBohuiReason = new AlertDialog.Builder(MyQuoteActivity.this, R.style.Dialog).create();
            window_bohui_reson = myDialogBohuiReason.getWindow();
            myDialogBohuiReason.setCancelable(false);
        }

        myDialogBohuiReason.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window_bohui_reson.setContentView(R.layout.assert_reason_no);
        iv_close_bohui_reason = (ImageView) window_bohui_reson.findViewById(R.id.iv_close);
        TextView mbohuiReason = (TextView) window_bohui_reson.findViewById(R.id.tv_bohui_reason);
        mbohuiReason.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (!StringUtils.isEmpty(advise)) {
            //驳回原因advise
            mbohuiReason.setText(advise);
        } else {
            mbohuiReason.setText(getString(R.string.sellassert_forfaiting_adapter_fuheweitongguo));
        }
        iv_close_bohui_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialogBohuiReason.dismiss();
            }
        });


    }

    //复核 输入驳回原因 驳回
    //复核驳回  advisetype 操作类型 1, "提交", 2, "驳回" 3,"取消";
    private void showReviewRejectDialog(final String advisetype) {
        rejectDialog = new RejectDialog(MyQuoteActivity.this, new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {
                MyLogger.pLog().e(isSelect);
                if ("1".equals(assetsType)) {
//                    getbohui(advisetype, isSelect);
                    offerBohuiRet(isSelect);
                } else if ("2".equals(assetsType)) {
//                    factoringOfferBohuiRet(advisetype, isSelect);
                    offerBohuiRet("" + isSelect);
                }
            }

            @Override
            public void negative(String isSelect, String companyName) {
                //驳回操作
//                reviewBoHuiReason();
            }
        }, "");
        rejectDialog.show();
    }

    /**
     * //福费廷资产再次发布接口
     *
     * @POST(Constants.NETPATH.FORFAITINGREPORT) Call<CheckLetterRes> forfaitingReportRet(@Body RequestBody requestBody);
     * <p>
     * public Call<CheckLetterRes> forfaitingReportRet(ForfaitingRportReq data) {
     * Call<CheckLetterRes> forfaitingReportRet = mApi.forfaitingReportRet(createRequestBody(data));
     * return forfaitingReportRet;
     * }
     */

    private void forfaitingReportRet(String assestId) {
        ForfaitingRportReq forfaitingRportReq = new ForfaitingRportReq();
        forfaitingRportReq.setId(assestId);


        ActionPresenter.getInstance().forfaitingReportRet(forfaitingRportReq).enqueue(new Callback<CheckLetterRes>() {
            @Override
            public void onResponse(Call<CheckLetterRes> call, Response<CheckLetterRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {

                        //我的资产
                        startActivity(new Intent(MyQuoteActivity.this, PropertyActivity.class));
                        finish();
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
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SharedPreferanceUtils.clear(MyQuoteActivity.this);
    }

    public void resetFiltrate() {
        //资产状态
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "offerType", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"currency_type",currency);
        //币种
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "currency_type", "");
        //贴现率范围
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "tiexianlv_start", "");
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "tiexianlv_end", "");
        //承兑金额范围
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "scope_start", "");
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "scope_end", "");
        //开证行名称
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "open_book_name", "");
        //福费廷类型
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "mine_fufeiting", "");
//     s.put(FiltrateActivity.this,"country_id",guojia_seconde_id);
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "country_id", "");
        SharedPreferanceUtils.put(MyQuoteActivity.this, shareKey + "assetsType", "1");
    }

    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = getString(R.string.mine_hobby_commit);
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(MyQuoteActivity.this, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                if ("1".equals(assetsType)) {
                    if ("1".equals(SureOrCancelDialogtype)) {
                        //提交
                        reviewOfferForfaitingCommitRet();
                    } else if ("2".equals(SureOrCancelDialogtype)) {
                        //删除 取消
                        reviewForfaitingCancelBuyRet();
                    } else if ("3".equals(SureOrCancelDialogtype)) {
                        //确认
                        getMackSure();
                    } else if ("4".equals(SureOrCancelDialogtype)) {
                        //再次发布
                        forfaitingReportRet(assestId);
                    }
                } else if ("2".equals(assetsType)) {
                    if ("1".equals(SureOrCancelDialogtype)) {
                        //取消
                        reviewFactoringCancelBuyRet();
                    } else if ("2".equals(SureOrCancelDialogtype)) {
                        //确认
                        reviewFactoringSureRet();
                    } else if ("3".equals(SureOrCancelDialogtype)) {
                        //提交
                        reviewOfferForfaitingCommitRet();
                    }
                }
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect, String companyName) {
                MyLogger.pLog().i("negative");
            }
        }, "" + dialogContent, getString(R.string.onsalelist_forfaiting_adapter_cancel), getString(R.string.onsalelist_forfaiting_adapter_sure));
        sureOrCancelDialog.show();
    }


    /**
     * /**
     * //下载邀约函接口
     *
     * @POST(Constants.NETPATH.DOWNLOADCHECKLETTER) Call<CheckLetterRes> downCheckLetterRet(@Body RequestBody requestBody);
     * public Call<DownCheckLetterRes> downCheckLetterRet(IussingFactoringEntryReq data) {
     * Call<DownCheckLetterRes> downCheckLetterRet = mApi.downCheckLetterRet(createRequestBody(data));
     * return downCheckLetterRet;
     * }
     */
    private void downCheckLetterRet(String assets_id) {
        IussingFactoringEntryReq iussingFactoringEntryReq = new IussingFactoringEntryReq();
        iussingFactoringEntryReq.setId(assets_id);
        ActionPresenter.getInstance().downCheckLetterRet(iussingFactoringEntryReq).enqueue(new Callback<DownCheckLetterRes>() {
            @Override
            public void onResponse(Call<DownCheckLetterRes> call, Response<DownCheckLetterRes> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        String filename = "";
                        if (!StringUtils.isEmpty(response.body().getData())) {
                            filename = response.body().getData().substring(response.body().getData().lastIndexOf("/") + 1);
                        }
                        String path0 = getExternalFilesDir(null) + File.separator + filename;
                        if (!FileUtils.isFileExist(path0)) {
                            downloadFileWithDynamicUrlSync(response.body().getData());
                        } else {
                            Intent intent = new Intent(MyQuoteActivity.this, PdfActivity.class);
                            intent.putExtra("path", path0 + "");
                            startActivity(intent);
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

    /**
     * /**
     * 下载文件
     *
     * @return
     * @GET Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
     * public Call<ResponseBody> downloadFileWithDynamicUrlSync(String Url) {
     * Call<ResponseBody> downloadFileWithDynamicUrlSync = mApi.downloadFileWithDynamicUrlSync(Url);
     * return downloadFileWithDynamicUrlSync;
     * }
     */
    private void downloadFileWithDynamicUrlSync(final String url) {
        ActionPresenter.getInstance().downloadFileWithDynamicUrlSync(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    String path = FileUtils.writeResponseBodyToDisk(MyApplication.mMyApplication, response.body(), url);
                    if (!StringUtils.isEmpty(path)) {
                        Intent intent = new Intent(MyQuoteActivity.this, PdfActivity.class);
                        intent.putExtra("path", path + "");
                        startActivity(intent);
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
     * 查询让渡函接口
     *
     * @POST(Constants.NETPATH.CHECKTRANSFERLETTER) Call<CheckTransferLetterRes> checkTransferLetterRet(@Body RequestBody requestBody);
     * <p>
     * public Call<CheckTransferLetterRes> checkTransferLetterRet(ReviewCheckTransferReq data) {
     * Call<CheckTransferLetterRes> checkTransferLetterRet = mApi.checkTransferLetterRet(createRequestBody(data));
     * return checkTransferLetterRet;
     * }
     */
    private void checkTransferLetter(String assets_id, String assetsType) {
        ReviewCheckTransferReq reviewCheckTransferReq = new ReviewCheckTransferReq();
        reviewCheckTransferReq.setBussId(assets_id);
        reviewCheckTransferReq.setAssetsType(assetsType);
        ActionPresenter.getInstance().checkTransferLetterRet(reviewCheckTransferReq).enqueue(new Callback<CheckTransferLetterRes>() {
            @Override
            public void onResponse(Call<CheckTransferLetterRes> call, Response<CheckTransferLetterRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {

                            showDownloadDialog(response.body().getData());

                        } else if (response.body().getCode() == 400) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else if (response.body().getCode() == 500) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckTransferLetterRes> call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    /**
     * 展示下载dialog
     *
     * @param data
     */
    private void showDownloadDialog(final String data) {
        final DownloadDialog downloadDialog = new DownloadDialog(MyQuoteActivity.this, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {

            }

            @Override
            public void negative(String isSelect, String companyName) {
                if (!TextUtils.isEmpty(data)) {
                    List<String> urlList = new ArrayList<>();
                    if (!data.contains(";")) {
                        urlList.add(data);
                    } else {
                        urlList = StringUtils.splitStr(data, ";");
                    }
                    for (int i = 0; i < urlList.size(); i++) {
                        downloadFileWithDynamicUrlSyncForTransferLetter(urlList.get(i));
                    }
                }
            }
        }, getResources().getString(R.string.onsalelist_forfaiting_adapter_check_transfer_letter), getResources().getString(R.string.dialog_download), data);
        downloadDialog.show();
    }

    /**
     * /**
     * 下载文件
     *
     * @return
     * @GET Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
     * public Call<ResponseBody> downloadFileWithDynamicUrlSync(String Url) {
     * Call<ResponseBody> downloadFileWithDynamicUrlSync = mApi.downloadFileWithDynamicUrlSync(Url);
     * return downloadFileWithDynamicUrlSync;
     * }
     */
    private void downloadFileWithDynamicUrlSyncForTransferLetter(final String url) {
//        showWaitDialog();
        ActionPresenter.getInstance().downloadFileWithDynamicUrlSync(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                closeWaitDialog();
                if (response != null && response.body() != null) {
                    String writtenToDisk = FileUtils.writeResponseBodyToDisk(MyApplication.mMyApplication, response.body(), url);
                    if (!StringUtils.isEmpty(writtenToDisk)) {
                        ToastUtils.showLong(getString(R.string.toastmarket_forfaiting_detail_downfile) + writtenToDisk);
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
//                closeWaitDialog();
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    /**
     * 更多dialog(中间)
     *
     * @param anchorView
     * @param assestId
     * @param assetsType
     * @param showFlag   0 全显示，1 只显示查看邀约函，2 只显示查看让渡函
     */
    private void showCheckMoreCenter(View anchorView, final String assestId, final String assetsType, String showFlag) {
        final View mMoreView = getLayoutInflater().inflate(R.layout.dialog_check_letter, null);
        final PopupWindow mMorePopupWindow = new PopupWindow(mMoreView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView mTvCheckLetter = (TextView) mMoreView.findViewById(R.id.tv_check_letter);
        TextView mTvCheckTransferLetter = (TextView) mMoreView.findViewById(R.id.tv_check_transfer_letter);
        if ("0".equals(showFlag)) {
            mTvCheckLetter.setVisibility(View.VISIBLE);
            mTvCheckTransferLetter.setVisibility(View.VISIBLE);
        } else if ("1".equals(showFlag)) {
            mTvCheckLetter.setVisibility(View.VISIBLE);
            mTvCheckTransferLetter.setVisibility(View.GONE);
        } else if ("2".equals(showFlag)) {
            mTvCheckLetter.setVisibility(View.GONE);
            mTvCheckTransferLetter.setVisibility(View.VISIBLE);
        }
        mMorePopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mMorePopupWindow.setFocusable(true);
        mMorePopupWindow.setOutsideTouchable(true);
        mMorePopupWindow.update();
        View popupView = mMorePopupWindow.getContentView();
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        anchorView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOffset = anchorView.getMeasuredWidth() / 2 - popupView.getMeasuredWidth() / 2;
        mMorePopupWindow.showAsDropDown(anchorView, xOffset, 10);
        mTvCheckLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downCheckLetterRet(assestId);
            }
        });
        mTvCheckTransferLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTransferLetter(assestId, assetsType);
            }
        });
    }

    /**
     * 更多dialog(右边)
     *
     * @param anchorView
     * @param assestId
     * @param assetsType
     * @param showFlag   0 全显示，1 只显示查看邀约函，2 只显示查看让渡函
     */
    private void showCheckMoreSide(View anchorView, final String assestId, final String assetsType, String showFlag) {
        final View mMoreView = getLayoutInflater().inflate(R.layout.dialog_check_letter2, null);
        final PopupWindow mMorePopupWindow = new PopupWindow(mMoreView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView mTvCheckLetter = (TextView) mMoreView.findViewById(R.id.tv_check_letter);
        TextView mTvCheckTransferLetter = (TextView) mMoreView.findViewById(R.id.tv_check_transfer_letter);
        if ("0".equals(showFlag)) {
            mTvCheckLetter.setVisibility(View.VISIBLE);
            mTvCheckTransferLetter.setVisibility(View.VISIBLE);
        } else if ("1".equals(showFlag)) {
            mTvCheckLetter.setVisibility(View.VISIBLE);
            mTvCheckTransferLetter.setVisibility(View.GONE);
        } else if ("2".equals(showFlag)) {
            mTvCheckLetter.setVisibility(View.GONE);
            mTvCheckTransferLetter.setVisibility(View.VISIBLE);
        }
        mMorePopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mMorePopupWindow.setFocusable(true);
        mMorePopupWindow.setOutsideTouchable(true);
        mMorePopupWindow.update();
        View popupView = mMorePopupWindow.getContentView();
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        anchorView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int xOffset = anchorView.getMeasuredWidth() / 2 - popupView.getMeasuredWidth();
        mMorePopupWindow.showAsDropDown(anchorView, xOffset, 10);
        mTvCheckLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downCheckLetterRet(assestId);
            }
        });
        mTvCheckTransferLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTransferLetter(assestId, assetsType);
            }
        });
    }

}
