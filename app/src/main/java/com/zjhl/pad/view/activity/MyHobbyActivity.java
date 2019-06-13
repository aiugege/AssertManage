package com.zjhl.pad.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.ModelPreferenceFactoring;
import com.zjhl.pad.app.utils.ModelPreferenceForfaiting;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.AssertCommitReq;
import com.zjhl.pad.moudle.entity.req.BohuiResonReq;
import com.zjhl.pad.moudle.entity.req.CansalResonReq;
import com.zjhl.pad.moudle.entity.req.HobbyDeleteReq;
import com.zjhl.pad.moudle.entity.req.HobbyReq;
import com.zjhl.pad.moudle.entity.req.LookBohuiReasonReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.req.MyHobbyReq;
import com.zjhl.pad.moudle.entity.res.AssertCommitRes;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.BohuiResonRes;
import com.zjhl.pad.moudle.entity.res.CanselRes;
import com.zjhl.pad.moudle.entity.res.HobbyBaoliRes;
import com.zjhl.pad.moudle.entity.res.HobbyDeleteRes;
import com.zjhl.pad.moudle.entity.res.LookBohuiReasonRs;
import com.zjhl.pad.moudle.entity.res.MyHobbyRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.ClassifyMorAdapter;
import com.zjhl.pad.view.adapter.DataOrderMainAdapter;
import com.zjhl.pad.view.adapter.HobbyBaoliAdapter;
import com.zjhl.pad.view.adapter.MyHobbyOfferAdapter;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.RejectDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: MyHobbyActivity.java 我的偏好
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/8 14:37
 * Changes (from 2018/5/8)
 */
public class MyHobbyActivity extends BaseActivity {

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

    @BindView(R.id.tv_no_assert)
    TextView tvNoAssert;
    DataOrderMainAdapter mainAdapter;
    ClassifyMorAdapter moreAdapter;
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
    private String country_id;
    private String ascDesc = "desc";
    private String orderBy;
    private String userType;
    //福费廷
    private MyHobbyOfferAdapter quoteListAdapter;
    //保理
    private HobbyBaoliAdapter boliofferAdapter;
    private android.app.AlertDialog myDialog = null;
    private Button bt_payButton;
    boolean isChecked = false;
    private String scope_start;
    private String scope_end;
    SureOrCancelDialog sureOrCancelDialog;
    private String assestId;
    private String et_tiexianlv;
    private android.app.AlertDialog myDialogBohui = null;
    private String advice_reason;

    private android.app.AlertDialog myDialogBohuiReason = null;
    String advise = "";
    //报价id
    private String mpriceId;

    //增加前缀防止重复
    private String shareKey = Constants.SPKEY.SP_MY_QUOTE_KEY;
    private RejectDialog rejectDialog;
    private int assert_id;
    private String xinyongzhengType;
    private String arres_id;
    private String factoringType;
    ModelPreferenceFactoring modelPreferenceFactoring = new ModelPreferenceFactoring();
    ModelPreferenceForfaiting modelPreferenceForfaiting = new ModelPreferenceForfaiting();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mine_quote);
        ButterKnife.bind(this);
        tvContent.setText(R.string.myhobby_title);
        myDialogBohui = new android.app.AlertDialog.Builder(MyHobbyActivity.this, R.style.Dialog).create();
        assetsType = "1";
        shareKey = Constants.SPKEY.SP_MY_HOBBY_KEY + assetsType;
        initQuoteAdapter(assetsType);
        myDialog = new android.app.AlertDialog.Builder(this, R.style.Dialog).create();
        btPublicData.setText(getString(R.string.filtrate_enddate));

        myDialogBohuiReason = new android.app.AlertDialog.Builder(MyHobbyActivity.this, R.style.Dialog).create();
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

        //保理
        HobbyReq baoliHobbyRes = new HobbyReq();
        baoliHobbyRes.setFactoringType(factoringType);
        baoliHobbyRes.setAssetsType(assetsType);
        baoliHobbyRes.setCurrency(bizhong_type);
        //机构id
        baoliHobbyRes.setPage(mNextRequestPage);
        baoliHobbyRes.setPageSize(PAGE_SIZE);
        //利率开始
        baoliHobbyRes.setRateBegin(tiexianlvStartType);
        //利率结束
        baoliHobbyRes.setRateEnd(tiexianlvEndType);
        baoliHobbyRes.setAmountBegin(scope_start);
        baoliHobbyRes.setAmountEnd(scope_end);
        ////国家id （多个用逗号分割）
        baoliHobbyRes.setCountryIds(country_id);
        baoliHobbyRes.setAreaId(arres_id);
        //信用证类型
        baoliHobbyRes.setOrderBy(orderBy);
        baoliHobbyRes.setAscDesc(ascDesc);
        ActionPresenter.getInstance().myHobbyRet(baoliHobbyRes).enqueue(new Callback<HobbyBaoliRes>() {
            @Override
            public void onResponse(Call<HobbyBaoliRes> call, Response<HobbyBaoliRes> response) {

//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage == 1) {
                                setDataBaoli(true, response.body().getData());
                            } else if (mNextRequestPage > 1 && mNextRequestPage<=response.body().getTotalPage()) {
                                setDataBaoli(false, response.body().getData());
                            }else {
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
        mNextRequestPage++;
        final int size = dataBaoli == null ? 0 : dataBaoli.size();
        if(size>0){
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
//                mpriceId = boliofferAdapter.getItem(position).getPriceId();
                if (view != null) {
                    switch (view.getId()) {

                        case R.id.tv_delete:
                            //删除
                            hobbyDeleteDialogView("2");

                            break;

                        case R.id.tv_edit:
                            //编辑
                            DisPatcher.startFactoringIssuingActivity(MyHobbyActivity.this, assestId, "2");
//                            startActivity(new Intent(MyHobbyActivity.this,FactoringIssuingActivity.class).putExtra("hobby_id",assestId));
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


            }
        });

        /**
         * 福费廷
         */
        quoteListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                assestId = quoteListAdapter.getItem(position).getId() + "";
//                mpriceId = quoteListAdapter.getItem(position).getPriceId() + "";

            }
        });

        quoteListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                assestId = quoteListAdapter.getItem(position).getId() + "";
//                mpriceId = quoteListAdapter.getItem(position).getPriceId();
                if (view != null) {
                    switch (view.getId()) {
                        case R.id.tv_delete:
                            //删除
                            hobbyDeleteDialogView("1");
                            break;

                        case R.id.tv_edit:
                            //编辑
                            DisPatcher.startFactoringIssuingActivity(MyHobbyActivity.this, assestId, "1");
//                            startActivity(new Intent(MyHobbyActivity.this,FactoringIssuingActivity.class).putExtra("hobby_id",assestId));
                            break;

                    }


                }


            }


        });


    }

    private void hobbyDeleteDialogView(final String fubaodelete) {
        String dialogContent = getString(R.string.mine_will_sale_sure);

        sureOrCancelDialog = new SureOrCancelDialog(MyHobbyActivity.this, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                if ("1".equals(fubaodelete)){
                    hobbyDelete();
                }else if ("2".equals(fubaodelete)){
                    hobbyBaoliDelete();
                }

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

    private void hobbyBaoliDelete() {
        //保理删除
        HobbyDeleteReq hobbyReq = new HobbyDeleteReq();
        hobbyReq.setId(assestId);

        ActionPresenter.getInstance().hobbyBaoliDeleteRet(hobbyReq).enqueue(new Callback<HobbyDeleteRes>() {
            @Override
            public void onResponse(Call<HobbyDeleteRes> call, Response<HobbyDeleteRes> response) {

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

    private void hobbyDelete() {
        //福费廷删除
        HobbyDeleteReq hobbyReq = new HobbyDeleteReq();
        hobbyReq.setId(assestId);

        ActionPresenter.getInstance().hobbyFufeitingDeleteRet(hobbyReq).enqueue(new Callback<HobbyDeleteRes>() {
            @Override
            public void onResponse(Call<HobbyDeleteRes> call, Response<HobbyDeleteRes> response) {

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

    //福费廷 买家确认
    private void getMackSure() {
        MessageListReq macksureReq = new MessageListReq();
        macksureReq.setId(assestId);

        ActionPresenter.getInstance().reviewForfaitingSureRet(macksureReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && !TextUtils.isEmpty(response.code() + "")) {
                    if (response.body().getCode() == 300) {
                        ToastUtils.showShort(response.body().getMessage());
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
                        ToastUtils.showShort(response.body().getMessage());
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
                        ToastUtils.showShort(response.body().getMessage());
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
                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));
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
                        ToastUtils.showShort(response.body().getMessage());
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


    private void getbohui() {
        BohuiResonReq bohuiResonReq = new BohuiResonReq();
        bohuiResonReq.setId(assestId);
        bohuiResonReq.setOperateButton("2");
        bohuiResonReq.setAdvise("");
        ActionPresenter.getInstance().bohuiRet(bohuiResonReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
                        ToastUtils.showShort(response.body().getMessage());
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


    private void offerBohuiRet() {
        AssertCommitReq assertCommitReq = new AssertCommitReq();
        assertCommitReq.setId(mpriceId);
        assertCommitReq.setRefuseAdvice("");
        ActionPresenter.getInstance().offerBohuiRet(assertCommitReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
//                        ToastUtils.showShort(response.body().getMessage());
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


    private void factoringOfferBohuiRet(String type) {
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
                        ToastUtils.showShort(response.body().getMessage());
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
            myDialogBohui = new android.app.AlertDialog.Builder(MyHobbyActivity.this, R.style.Dialog).create();
            window_bohui = myDialogBohui.getWindow();
            myDialogBohui.setCancelable(false);
        }


        window.setContentView(R.layout.assert_reason_dilog);
        //驳回内容
        EditText tv_adviceM = (EditText) window.findViewById(R.id.tv_advice);
        advice_reason = tv_adviceM.getText().toString().trim();
        Button bt_reason_commit = (Button) window.findViewById(R.id.bt_reason_commit);
        iv_close_bohui = (ImageView) window.findViewById(R.id.iv_close);
        bt_reason_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getbohuiReson();

            }
        });
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
//                        ToastUtils.showShort(response.body().getMessage());
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

    private void lockBook() {

    }



    ImageView iv_close;
    Window window;

    private void LetterOffer() {
        if (myDialog != null) {
            myDialog.setCancelable(false);
            myDialog.show();
            window = myDialog.getWindow();
        } else {
            myDialog = new android.app.AlertDialog.Builder(this, R.style.Dialog).create();
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
        List<HobbyBaoliRes.DataBean> dataBaoli = new ArrayList<>();
        boliofferAdapter = new HobbyBaoliAdapter(dataBaoli);
        boliofferAdapter.openLoadAnimation();
//        rvList.setAdapter(boliofferAdapter);

        List<MyHobbyRes.DataBean> data = new ArrayList<>();
        quoteListAdapter = new MyHobbyOfferAdapter(data);
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

        boliofferAdapter.openLoadAnimation();
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
        mNextRequestPage = 1;
        boliofferAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        quoteListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
//        boliofferAdapter.setEnableLoadMore(false);
        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
                getMyassertData();
            } else if ("2".equals(assetsType)) {
                getBaoLiAssebao();
            }
        }

    }


    private void setData(boolean isRefresh, List dataFufeiting) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = dataFufeiting == null ? 0 : dataFufeiting.size();
        if(size>0){
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
        myDialog = new android.app.AlertDialog.Builder(this, R.style.Dialog).create();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //资产id
            assert_id = extras.getInt("assert_id");
        }

    }

    //福费廷
    private void getMyassertData() {

        MyHobbyReq myHobbyReq = new MyHobbyReq();
        myHobbyReq.setCompanyOrgId(assert_id + "");
        myHobbyReq.setAssetsType(assetsType);
        myHobbyReq.setCurrency(bizhong_type);
        //机构id
        myHobbyReq.setPage(mNextRequestPage);
        myHobbyReq.setPageSize(PAGE_SIZE);
        //利率开始
        myHobbyReq.setRateBegin(tiexianlvStartType);
        //利率结束
        myHobbyReq.setRateEnd(tiexianlvEndType);
        myHobbyReq.setAmountBegin(scope_start);
        myHobbyReq.setAmountEnd(scope_end);
        ////国家id （多个用逗号分割）
        myHobbyReq.setCountryIds(country_id);
        myHobbyReq.setAreaId(arres_id);
        //信用证类型
        myHobbyReq.setDebtType(xinyongzhengType);
        myHobbyReq.setOrderBy(orderBy);
        myHobbyReq.setAscDesc(ascDesc);
        ActionPresenter.getInstance().myHobbyRet(myHobbyReq).enqueue(new Callback<MyHobbyRes>() {
            @Override
            public void onResponse(Call<MyHobbyRes> call, Response<MyHobbyRes> response) {
//                MyLogger.pLog().d("LoginRes："+response.body().toString());
//                MyLogger.pLog().d("LoginRes："+response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage == 1) {
                                setData(true, response.body().getData());
                            } else if (mNextRequestPage > 1 && mNextRequestPage<=response.body().getTotalPage()) {
                                setData(false, response.body().getData());
                            }else {
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
        for (int i = 0; i < modelPreferenceFactoring.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", modelPreferenceFactoring.LISTVIEWTXT[i]);
            mainFactoringList.add(map);
        }
    }

    private void initModle() {
        mainList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < modelPreferenceForfaiting.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", modelPreferenceForfaiting.LISTVIEWTXT[i]);
            mainList.add(map);
        }
    }

    @OnClick({R.id.tv_fufeiting, R.id.bt_public_data, R.id.bt_select_shuaixuan, R.id.iv_excite, R.id.data_order, R.id.rl_shuaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fufeiting:
                //福费廷，保理选择
                orderBy = "";
                showFufeiting();
                break;
            case R.id.bt_public_data:
            case R.id.data_order:
                //发布时间排序
                if ("1".equals(assetsType)) {
//                    orderBy = "";
                    showDataOrder();
                } else {
//                    orderBy = "";
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
//                DisPatcher.startFiltrateActivity(MyHobbyActivity.this, shareKey, 1);
                Intent intent = new Intent(MyHobbyActivity.this, HobbySellectActivity.class);
                intent.putExtra("shareKey", shareKey);
                intent.putExtra("assetsType", assetsType);
                startActivityForResult(intent,9);
//                startActivityForResult(new Intent(MyHobbyActivity.this, HobbySellectActivity.class).putExtra("hobby_assetsType", assetsType), 9);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (9 == requestCode) {
            //保理类型
            //单双保理
            String danshaungbaoli = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "baoli_danshuang", "");

            //明暗保理
            String minganbaoli = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "baoli_ming_an", "");
            if (getResources().getString(R.string.market_forfaiting_filtrate_single).equals(danshaungbaoli) && getResources().getString(R.string.market_forfaiting_filtrate_disclosed).equals(minganbaoli)) {
                factoringType = "1";
            } else if (getResources().getString(R.string.market_forfaiting_filtrate_single).equals(danshaungbaoli) && getResources().getString(R.string.market_forfaiting_filtrate_undisclosed).equals(minganbaoli)) {
                factoringType = "2";
            } else if (getResources().getString(R.string.market_forfaiting_filtrate_double).equals(danshaungbaoli) && getResources().getString(R.string.market_forfaiting_filtrate_disclosed).equals(minganbaoli)) {
                factoringType = "3";
            } else if (getResources().getString(R.string.market_forfaiting_filtrate_double).equals(danshaungbaoli) && getResources().getString(R.string.market_forfaiting_filtrate_undisclosed).equals(minganbaoli)) {
                factoringType = "4";
            }else{
                factoringType = "";
                MyLogger.pLog().d(factoringType);
            }

            //信用证类型
            xinyongzhengType = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "xinyong_card", "");

            //贴现率范围
            tiexianlvStartType = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "hobby_tiexianlv_start", "");
            tiexianlvEndType = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "hobby_tiexianlv_end", "");

            //承兑金额范围
            scope_start = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "hobby_scope_start", "");
            scope_end = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "hobby_scope_end", "");

            //币种
            bizhong_type = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "hobby_select_type", "");


            //地区id
            arres_id = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "hobby_country_id", "");

            //国家
            country_id = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "guojia_seconde_id", "");
//            assetsType = (String) SharedPreferanceUtils.get(MyHobbyActivity.this, shareKey + "assetsType", "1");
            refresh();
        }
    }

    //保理
    private void showDataFactoringOrder() {
//         getLayoutInflater().inflate(R.layout.mine_list_data_order,null);
        View popupView_order = LayoutInflater.from(this).inflate(R.layout.mine_list_data_order, null);
//        View root = getLayoutInflater().inflate.inflate(R.layout.mine_list_data_order, null);
//        orderBy = "maturity";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(MyHobbyActivity.this, mainFactoringList);
        mainAdapter.setSelectItem(0);
        order_main_list.setAdapter(mainAdapter);
        if("maturity".equals(orderBy)){
            mainAdapter.setSelectItem(0);
        }else if("amount".equals(orderBy)){
            mainAdapter.setSelectItem(1);
        }else if("transfer_rate".equals(orderBy)){
            mainAdapter.setSelectItem(2);
        }
        order_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    //保理到期日
                    orderBy = "maturity";
                } else if (position == 1) {
                    //金额
                    orderBy = "amount";
                } else if (position == 2) {
                    //转让利率
                    orderBy = "transfer_rate";
                }
                initAdapter(modelPreferenceFactoring.MORELISTTXT[position]);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                btPublicData.setText(modelPreferenceFactoring.LISTVIEWTXT[position]);

            }
        });
        order_main_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 一定要设置这个属性，否则ListView不会刷新
        initAdapter(modelPreferenceFactoring.MORELISTTXT[0]);
//        order_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textList));
        final PopupWindow window_quote = new PopupWindow(popupView_order, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_quote.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_quote.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_quote.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_quote.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_quote.showAsDropDown(btSelect, 0, 20);
        if("asc".equals(ascDesc)){
            moreAdapter.setSelectItem(0);
        }else if("desc".equals(ascDesc)){
            moreAdapter.setSelectItem(1);
        }
        order_more_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                btPublicData.setText(position);
                if (position == 0) {
//                    btPublicData.setText(getResources().getString(R.string.filtrate_asc));
                    ascDesc = "asc";
                } else if (position == 1) {
//                    btPublicData.setText(getResources().getString(R.string.filtrate_desc));
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

    private void showDataOrder() {
        View popupView_order = getLayoutInflater().inflate(R.layout.mine_list_data_order, null);
//        orderBy = "dead_line";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(MyHobbyActivity.this, mainList);
        mainAdapter.setSelectItem(0);
        order_main_list.setAdapter(mainAdapter);
        if("dead_line".equals(orderBy)){
            mainAdapter.setSelectItem(0);
        }else if("amount".equals(orderBy)){
            mainAdapter.setSelectItem(1);
        }else if("discount_rate".equals(orderBy)){
            mainAdapter.setSelectItem(2);
        }
        order_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    //资产到期日
                    orderBy = "dead_line";
                } else if (position == 1) {
                    //兑现金额
                    orderBy = "amount";
                } else if (position == 2) {
                    //贴现率
                    orderBy = "discount_rate";
                }
                initAdapter(modelPreferenceForfaiting.MORELISTTXT[position]);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                btPublicData.setText(modelPreferenceForfaiting.LISTVIEWTXT[position]);

            }
        });
        order_main_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 一定要设置这个属性，否则ListView不会刷新
        initAdapter(modelPreferenceForfaiting.MORELISTTXT[0]);
//        order_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textList));
        final PopupWindow window_quote = new PopupWindow(popupView_order, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_quote.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_quote.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_quote.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_quote.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_quote.showAsDropDown(btSelect, 0, 20);
        if("asc".equals(ascDesc)){
            moreAdapter.setSelectItem(0);
        }else if("desc".equals(ascDesc)){
            moreAdapter.setSelectItem(1);
        }
        order_more_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                btPublicData.setText(position);
                if (position == 0) {
//                    btPublicData.setText(getResources().getString(R.string.filtrate_asc));
                    ascDesc = "asc";
                } else if (position == 1) {
//                    btPublicData.setText(getResources().getString(R.string.filtrate_desc));
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

    private void initAdapter(String[] array) {
        moreAdapter = new ClassifyMorAdapter(this, array);
        order_more_list.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    private void showFufeiting() {
        final View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down1, textList));
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

                    btPublicData.setText(getString(R.string.filtrate_enddate));
//                    getMyassertData();
                } else if (1 == position) {
                    //保理
                    assetsType = "2";
//                    getBaoLiAssebao();

                    btPublicData.setText(getString(R.string.filtrate_factoring_enddate));
                }
//                btPublicData.setText(getString(R.string.market_forfaiting_title_filtrate_send));
                SharedPreferanceUtils.put(MyHobbyActivity.this, shareKey + "hobby_assetsType", assetsType);

                window_fufeiting.dismiss();
//                initQuoteAdapter(assetsType);
                setChangeAdapter(assetsType);
                refresh();

            }
        });
//        SharedPreferanceUtils.put(MyHobbyActivity.this,  "assetsType", assetsType);
    }

    private void getLookBoHuiReason() {
        LookBohuiReasonReq lookquxiaoResonReq = new LookBohuiReasonReq();
        lookquxiaoResonReq.setAuditId(assestId);
        if ("1".equals(assetsType)) {
            lookquxiaoResonReq.setAuditType("2");
        } else if ("2".equals(assetsType)) {
            lookquxiaoResonReq.setAuditType("3");
        }
//        lookquxiaoResonReq.setAuditType(assetsType);
        ActionPresenter.getInstance().lookcansalRet(lookquxiaoResonReq).enqueue(new Callback<LookBohuiReasonRs>() {
            @Override
            public void onResponse(Call<LookBohuiReasonRs> call, Response<LookBohuiReasonRs> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        ToastUtils.showShort(response.body().getMessage());
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
            myDialogBohuiReason = new android.app.AlertDialog.Builder(MyHobbyActivity.this, R.style.Dialog).create();
            window_bohui_reson = myDialogBohuiReason.getWindow();
            myDialogBohuiReason.setCancelable(false);
        }

        window_bohui_reson.setContentView(R.layout.assert_reason_no);
        iv_close_bohui_reason = (ImageView) window_bohui_reson.findViewById(R.id.iv_close);
        TextView mbohuiReason = (TextView) window_bohui_reson.findViewById(R.id.tv_bohui_reason);
        //驳回原因advise
        mbohuiReason.setText(advise);
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
        rejectDialog = new RejectDialog(MyHobbyActivity.this, new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {
                MyLogger.pLog().e(isSelect);
                if ("1".equals(assetsType)) {
//                    getbohui(advisetype, isSelect);
                    offerBohuiRet();
                } else if ("2".equals(assetsType)) {
//                    factoringOfferBohuiRet(advisetype, isSelect);
                }
                shareKey = Constants.SPKEY.SP_MY_HOBBY_KEY + assetsType;
            }

            @Override
            public void negative(String isSelect,String companyName) {
                //驳回操作
//                reviewBoHuiReason();
            }
        }, "");
        rejectDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "baoli_danshuang");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "baoli_ming_an");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "xinyong_card");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "hobby_tiexianlv_start");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "hobby_tiexianlv_end");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "hobby_scope_start");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "hobby_scope_end");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "hobby_select_type");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "hobby_country_id");
        SharedPreferanceUtils.remove(MyHobbyActivity.this, shareKey + "guojia_seconde_id");
    }
}
