package com.zjhl.pad.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vincent.filepicker.ToastUtil;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.ModelFactoring;
import com.zjhl.pad.app.utils.ModelSellAssets;
import com.zjhl.pad.app.utils.ModelSellFactoring;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.AssertCommitReq;
import com.zjhl.pad.moudle.entity.req.AssertFufeitingDeleteRq;
import com.zjhl.pad.moudle.entity.req.AssertsBaoliReq;
import com.zjhl.pad.moudle.entity.req.BaojiatxReq;
import com.zjhl.pad.moudle.entity.req.BohuiResonReq;
import com.zjhl.pad.moudle.entity.req.CansalResonReq;
import com.zjhl.pad.moudle.entity.req.LookBohuiReasonReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.MyassetsReq;
import com.zjhl.pad.moudle.entity.req.ReviewCheckTransferReq;
import com.zjhl.pad.moudle.entity.res.AsellasetsBaoliRes;
import com.zjhl.pad.moudle.entity.res.AsellasetsFuFeiTingRes;
import com.zjhl.pad.moudle.entity.res.AssertCommitRes;
import com.zjhl.pad.moudle.entity.res.BaojiatxRs;
import com.zjhl.pad.moudle.entity.res.BohuiResonRes;
import com.zjhl.pad.moudle.entity.res.CanselRes;
import com.zjhl.pad.moudle.entity.res.CheckTransferLetterRes;
import com.zjhl.pad.moudle.entity.res.LookBohuiReasonRs;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.SellAssetsSelectActivity;
import com.zjhl.pad.view.adapter.ClassifyMorAdapter;
import com.zjhl.pad.view.adapter.DataOrderMainAdapter;
import com.zjhl.pad.view.adapter.SellAssetsBaoliAdapter;
import com.zjhl.pad.view.adapter.SellassertFufeitingAdapter;
import com.zjhl.pad.view.base.BaseFragment;
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
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: SellAssetsFragment.java 待售资产
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/23 9:32
 * Changes (from 2018/5/23)
 */
public class SellAssetsFragment extends BaseFragment {
    @BindView(R.id.bt_select)
    TextView btSelect;
    @BindView(R.id.bt_public_data)
    TextView btPublicData;
    @BindView(R.id.data_order)
    RelativeLayout dataOrder;
    @BindView(R.id.bt_select_shuaixuan)
    TextView btSelectShuaixuan;

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @BindView(R.id.tv_no_assert)
    TextView tvNoAssert;
    Unbinder unbinder;
    private ListView lsvMore;
    private String[] textList = {MyApplication.mMyApplication.getResources().getString(R.string.lable_forfaiting), MyApplication.mMyApplication.getResources().getString(R.string.lable_facotring)};
    DataOrderMainAdapter mainAdapter;
    private ClassifyMorAdapter moreAdapter;
    private String ascDesc = "desc";
    private List<Map<String, Object>> mainList;
    private List<Map<String, Object>> mainFactoringList;
    private String assetsType;
    private String orderBy;
    private ListView order_more_list;
    private SellassertFufeitingAdapter sellassertFufeitingAdapter;
    private SellAssetsBaoliAdapter boliofferAdapter;
    private android.app.AlertDialog myDialog = null;
    private android.app.AlertDialog myDialogBohui = null;
    private android.app.AlertDialog myDialogBohuiReason = null;
    private String et_tiexianlv;
    private String priceId;
    private int mNextRequestPage = 1;
    private int mNextRequestPage1 = 1;
    private String assets_id;
    private String advice_reason;
    private String advise;
    private String mbo_huiReason;
    private String userType;
    private RejectDialog rejectDialog;

    private String bizhong_type;
    private String tiexianlvStartType;
    private String tiexianlvEndType;
    private String mine_fufeiting;
    private String country_id;
    private String openfullname;
    private String offerType;
    private String scope_start;
    private String scope_end;
    private String recheckState;//资产状态

    private static final int RESULT_CANCELED = 0;
    private static final int RESULT_CANCELEDFILTER = 1;
    //增加前缀防止重复
    private String shareKey = Constants.SPKEY.SP_MINE_SELL_ASSETS_LIST_KEY;
    SureOrCancelDialog sureOrCancelDialog;
    ModelSellAssets model = new ModelSellAssets();

    ModelSellFactoring modelFactoring = new ModelSellFactoring();

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_sellassert, null);
        unbinder = ButterKnife.bind(this, view);
        registerAction(Constants.BROADCAST_ACTION.SEND_REFERSH_SELL_ASSERT);
        rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        myDialog = new android.app.AlertDialog.Builder(getActivity(), R.style.Dialog).create();
        myDialogBohui = new android.app.AlertDialog.Builder(getActivity(), R.style.Dialog).create();
        myDialogBohuiReason = new android.app.AlertDialog.Builder(getActivity(), R.style.Dialog).create();
        assetsType = "1";
        shareKey = Constants.SPKEY.SP_MINE_SELL_ASSETS_LIST_KEY + assetsType;
        resetFiltrate();
        SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
        initAdapter(assetsType);
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
                getMyassertData();
            } else if ("2".equals(assetsType)) {
                getBaoLiAssebao();
            }
            btPublicData.setText(getString(R.string.filtrate_inputdate));
            initModle();
            initModleFactoring();
            initRefreshLayout();
            initItemListener();

        }
        return view;
    }


    private void initItemListener() {
        sellassertFufeitingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //资产id
                assets_id = sellassertFufeitingAdapter.getItem(position).getId();
                //查看详情
//                DisPatcher.startMarketForfaitingDetailActivity(getActivity(), assets_id + "", sellassertFufeitingAdapter.getItem(position).getMyAssets() + "");
                DisPatcher.startSoldForfaitingDetailActivity(getActivity(), assets_id + "", "0");
            }
        });

        /**
         * 福费廷里面的按钮
         */
        sellassertFufeitingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                assets_id = sellassertFufeitingAdapter.getItem(position).getId();
                recheckState = sellassertFufeitingAdapter.getItem(position).getRecheckState();
                String assetsNo = sellassertFufeitingAdapter.getItem(position).getAssetsNo();
                String title = sellassertFufeitingAdapter.getItem(position).getTitle();
                String discountRate = sellassertFufeitingAdapter.getItem(position).getDiscountRate();

                switch (view.getId()) {
                    case R.id.tv_fuhe_commit1:
                        initSureOrCancelDialogView("1", getString(R.string.issue_forfaiting_sell_yesorno));
//                        //提交-
//                        if ("2".equals(userType)) {
//                            handleForfaitingCommitRet();
//                        } else if ("3".equals(userType)) {
//                            getAssertCommit();
//                        }

                        break;
                    case R.id.tv_fuhe_commit2:
                        initSureOrCancelDialogView("2", getString(R.string.issue_forfaiting_sell_yesorno));
//                        //经办提交
//                        if ("2".equals(userType)) {
//                            handleForfaitingCommitRet();
//                        } else if ("3".equals(userType)) {
//                            getAssertCommit();
//                        }

                        break;
                    case R.id.tv_edit:
                        //编辑
//                        initPopWindow();
//                        startActivity(getActivity(), ForfaitingIssuingActivity.class);
//                        Intent intent = new Intent(getActivity(), ForfaitingIssuingActivity.class).putExtra("assestId", assets_id);
//                        startActivity(intent);
                        //经办编辑
                        if ("2".equals(userType)) {
                            DisPatcher.startForfaitingIssuingActivity(getActivity(), assets_id, "1");
                        }else if ("3".equals(userType)) {
                            //复核弹编辑框
                            checkExpiredAssets(assets_id, "1", assetsNo, title, discountRate);
                        }
                        refresh();
                        break;
                    case R.id.tv_delete:
                        initSureOrCancelDialogView("3", getString(R.string.mine_hobby_delete));
//                        //删除
//                        getAssertDelete();
                        break;
                    case R.id.tv_baoli_bohui:
                        //驳回
                        if ("210".equals(recheckState) || "211".equals(recheckState)) {
                            //1, "提交", 2, "驳回" 3,"取消";
                            getbohui("2", "");
                        } else {
                            showReviewRejectDialog("2","");
                        }
                        break;
                    case R.id.tv_yuanyin:
                        //查看驳回原因
                        getLookBoHuiReason();

                        break;
                    case R.id.tv_baoli_publish:
                        initSureOrCancelDialogView("4", getString(R.string.mine_hobby_publish));
//                        //发布
//                        reviewForfaitingIussRet();
                        break;
                    case R.id.tv_baoli_cancel:
                        initSureOrCancelDialogView("5", getString(R.string.toast_forfaiting_sell_cancelhint));
//                        //取消
//                        getAssertCancel("3");

                        break;


                }
            }
        });

        boliofferAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //资产id
                assets_id = boliofferAdapter.getItem(position).getId() + "";
                //查看详情
//                DisPatcher.startMarketFactoringDetailActivity(getActivity(), assets_id + "", boliofferAdapter.getItem(position).getMyAssets() + "");
                DisPatcher.startSoldFactoringDetailActivity(getActivity(), assets_id + "");
            }
        });
        /**
         * 保理里面的按钮
         */
        boliofferAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                assets_id = boliofferAdapter.getItem(position).getId() + "";
                recheckState = boliofferAdapter.getItem(position).getCheckState() + "";
                String assetsNo = sellassertFufeitingAdapter.getItem(position).getAssetsNo();
                String title = sellassertFufeitingAdapter.getItem(position).getTitle();
                String discountRate = sellassertFufeitingAdapter.getItem(position).getDiscountRate();
                switch (view.getId()) {
                    case R.id.tv_fuhe_commit1:
                        initSureOrCancelDialogView("1", getString(R.string.issue_forfaiting_sell_yesorno));
//                        if ("2".equals(userType)) {
//                            //经办提交
//                            handleFactoringCommitRet();
//                        } else if ("3".equals(userType)) {
//                            factoringOfferBohuiRet("1", "");
//                        }

                        break;
                    case R.id.tv_fuhe_commit:
                        initSureOrCancelDialogView("2", getString(R.string.issue_forfaiting_sell_yesorno));
//                        //复核提交-
//                        if ("2".equals(userType)) {
//                            handleFactoringCommitRet();
//                        } else if ("3".equals(userType)) {
////                            getAssertCommit();
//                            //1, "提交", 2, "驳回" 3,"取消";
//                            factoringOfferBohuiRet("1", "");
//                        }

                        break;
                    case R.id.tv_offer_edit:
                        //编辑
//                        initPopWindow();
//                        startActivity(getActivity(), ForfaitingIssuingActivity.class);
//                        Intent intent = new Intent(getActivity(), ForfaitingIssuingActivity.class).putExtra("assestId", assets_id);
//                        startActivity(intent);
//                        DisPatcher.startForfaitingIssuingActivity(getActivity(), assets_id, "2");
                        break;
                    case R.id.tv_delete:
                        //删除
                        initSureOrCancelDialogView("3", getString(R.string.mine_hobby_delete));
//                        factoringDeleteRet();
                        break;
                    case R.id.tv_fuhe_bohui:
                        //复核驳回-
                        if ("210".equals(recheckState) || "211".equals(recheckState)) {
                            //1, "提交", 2, "驳回" 3,"取消";
                            factoringOfferBohuiRet("2", "");
                        } else {
                            showReviewRejectDialog("2","");
                        }
                        break;
                    case R.id.bt_bohuireson:
                        //驳回原因
//                        getbohui();
                        getLookBoHuiReason();
                        break;
                    case R.id.tv_bohui_reason:
                        //驳回原因
//                        getbohui();
                        getLookBoHuiReason();
                        break;
                    case R.id.tv_cancel:
                        initSureOrCancelDialogView("4", getString(R.string.toast_forfaiting_sell_cancelhint));
//                        //取消-复核
//                        factoringOfferBohuiRet("3", "");
                        break;
                    case R.id.tv_fuhe_edit:
                        //经办编辑
                        if ("2".equals(userType)) {
                            DisPatcher.startForfaitingIssuingActivity(getActivity(), assets_id, "2");
                        }else if ("3".equals(userType)) {
                            //复核弹编辑框
                            checkExpiredAssets(assets_id, "2", assetsNo, title, discountRate);
                        }
                        refresh();
                        break;
                    case R.id.tv_fuhe_fabu:
                        //保理复合 发布按钮
                        initSureOrCancelDialogView("5", getString(R.string.mine_hobby_publish));
//                        reviewFactoringCommitCommitRet();
                        break;
                }
            }
        });
    }

    //福费廷
    private void getAssertDelete() {
        AssertFufeitingDeleteRq fufeitingDeleteReq = new AssertFufeitingDeleteRq();
        fufeitingDeleteReq.setId(assets_id);
        ActionPresenter.getInstance().deleteRet(fufeitingDeleteReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
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

    /**
     * // 保理待售资产 删除的接口
     *
     * @POST(Constants.NETPATH.FACTORINGLOOKBOHUIDELETEPATH) Call<BohuiResonRes> factoringDeleteRet(@Body RequestBody requestBody);
     * <p>
     * public Call<BohuiResonRes> factoringDeleteRet(AssertFufeitingDeleteRq data) {
     * Call<BohuiResonRes> factoringDeleteRet = mApi.factoringDeleteRet(createRequestBody(data));
     * return factoringDeleteRet;
     * }
     */

    private void factoringDeleteRet() {
        AssertFufeitingDeleteRq fufeitingDeleteReq = new AssertFufeitingDeleteRq();
        fufeitingDeleteReq.setId(assets_id);
        ActionPresenter.getInstance().factoringDeleteRet(fufeitingDeleteReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
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

    private void getbohui(String buttonType, String reson) {
        BohuiResonReq bohuiResonReq = new BohuiResonReq();
        bohuiResonReq.setId(assets_id);
        bohuiResonReq.setOperateButton(buttonType);
        bohuiResonReq.setAdvise(reson);
        ActionPresenter.getInstance().bohuiRet(bohuiResonReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
//                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));

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

    private void getLookBoHuiReason() {
        LookBohuiReasonReq lookquxiaoResonReq = new LookBohuiReasonReq();
        lookquxiaoResonReq.setAuditId(assets_id);
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

    /**
     * //复核  福费廷发布接口
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGIUSS) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingIussRet(@Body RequestBody requestBody);
     */
    private void reviewForfaitingIussRet() {
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(assets_id);
        ActionPresenter.getInstance().reviewForfaitingIussRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_sellassets_success));

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

    private void showBohuiReason() {

        Window window_bohui_reson;
        ImageView iv_close_bohui_reason;
        if (myDialogBohuiReason != null) {
            myDialogBohuiReason.setCancelable(false);
            myDialogBohuiReason.show();
            window_bohui_reson = myDialogBohuiReason.getWindow();
        } else {
            myDialogBohuiReason = new android.app.AlertDialog.Builder(getActivity(), R.style.Dialog).create();
            window_bohui_reson = myDialogBohuiReason.getWindow();
            myDialogBohuiReason.setCancelable(false);
        }

        myDialogBohuiReason.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window_bohui_reson.setContentView(R.layout.assert_reason_no);
        iv_close_bohui_reason = (ImageView) window_bohui_reson.findViewById(R.id.iv_close);
        TextView mbohuiReason = (TextView) window_bohui_reson.findViewById(R.id.tv_bohui_reason);
        if (!StringUtils.isEmpty(advise)) {
            //驳回原因advise
            mbohuiReason.setText(advise);
        } else {
            mbohuiReason.setText(R.string.mine_will_sale);
        }
        mbohuiReason.setMovementMethod(ScrollingMovementMethod.getInstance());
        iv_close_bohui_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialogBohuiReason.dismiss();
            }
        });


    }

    //复核 输入驳回原因 驳回
    //复核驳回  advisetype 操作类型 1, "提交", 2, "驳回" 3,"取消";
    private void showReviewRejectDialog(final String advisetype,String companyname) {
        rejectDialog = new RejectDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {
                MyLogger.pLog().e(isSelect);
                if ("1".equals(assetsType)) {
                    getbohui(advisetype, isSelect);
                } else if ("2".equals(assetsType)) {
                    factoringOfferBohuiRet(advisetype, isSelect);
                }
            }

            @Override
            public void negative(String isSelect,String companyName) {
                //驳回操作
//                reviewBoHuiReason();
            }
        }, "",companyname);
        rejectDialog.show();
    }


    private void getAssertCancel(String type) {
        //1, "提交", 2, "驳回" 3,"取消";
        CansalResonReq quxiaoResonReq = new CansalResonReq();
        quxiaoResonReq.setId(assets_id);
        quxiaoResonReq.setOperateButton(type);
        ActionPresenter.getInstance().cansalRet(quxiaoResonReq).enqueue(new Callback<CanselRes>() {
            @Override
            public void onResponse(Call<CanselRes> call, Response<CanselRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_soldinassets_cancel_success));
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

    /**
     * /**
     * //资产录入 待售列表保理 复核岗审核通过、驳回、取消 复核岗审核通过、驳回、取消
     *
     * @POST(Constants.NETPATH.OFFERBOHUIPATH) Call<BohuiResonRes> factoringOfferBohuiRet(@Body RequestBody requestBody);
     * public Call<BohuiResonRes> factoringOfferBohuiRet(AssertCommitReq data) {
     * Call<BohuiResonRes> factoringOfferBohuiRet = mApi.factoringOfferBohuiRet(createRequestBody(data));
     * return factoringOfferBohuiRet;
     * }1, "提交", 2, "驳回" 3,"取消";
     */


    private void factoringOfferBohuiRet(final String type, String advisetype) {
        AssertCommitReq assertCommitReq = new AssertCommitReq();
        assertCommitReq.setId(assets_id);
        assertCommitReq.setOperateButton(type);
        assertCommitReq.setAdvise(advisetype);
        ActionPresenter.getInstance().factoringOfferBohuiRet(assertCommitReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        myDialogBohui.dismiss();
//                        if ("1".equals(type)) {
//                            ToastUtils.showShort(getString(R.string.toast_sellassets_success));
//                        } else if ("2".equals(type)) {
//                            ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));
//                        }else if ("3".equals(type)) {
//                            ToastUtils.showShort(getString(R.string.toast_soldinassets_cancel_success));
//                        }
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
        assertCommitReq.setId(assets_id);
        assertCommitReq.setOperateButton("1");
        ActionPresenter.getInstance().asserRet(assertCommitReq).enqueue(new Callback<AssertCommitRes>() {
            @Override
            public void onResponse(Call<AssertCommitRes> call, Response<AssertCommitRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_onsalelist_forfaiting_adapter_commit));
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

    //待售资产 保理复核发布接口
//    @POST(Constants.NETPATH.REVIEWASSERTBAOLIPATH)
//    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringCommitCommitRet(@Body RequestBody requestBody);
    private void reviewFactoringCommitCommitRet() {
        //提交
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(assets_id);
        ActionPresenter.getInstance().reviewFactoringCommitCommitRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(response.body().getMessage());
//                        if ("1".equals(assetsType)) {
//                            mNextRequestPage = 1;
//                            getMyassertData();
//                        } else if ("2".equals(assetsType)) {
//                            mNextRequestPage1 = 1;
//                            getBaoLiAssebao();
//                        }

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

    /**
     * //待售资产 福费廷提交按钮列表接口
     *
     * @POST(Constants.NETPATH.ASSERTCOMMITPATH) Call<ReviewOfferSubmitLetterOnSaleListRes> handleForfaitingCommitRet(@Body RequestBody requestBody);
     */
    private void handleForfaitingCommitRet() {
        //提交
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(assets_id);
        ActionPresenter.getInstance().handleForfaitingCommitRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_onsalelist_forfaiting_adapter_commit));

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

    /**
     * //待售资产 保理经办提交按钮列表接口
     *
     * @POST(Constants.NETPATH.ASSERTCOMMITPATH) Call<ReviewOfferSubmitLetterOnSaleListRes> handleForfaitingCommitRet(@Body RequestBody requestBody);
     */
    private void handleFactoringCommitRet() {
        //提交
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(assets_id);
        ActionPresenter.getInstance().handleFactoringCommitRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(getString(R.string.toast_onsalelist_forfaiting_adapter_commit));
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


    private void initAdapter(String assetsType) {
        List<AsellasetsBaoliRes.DataBean> dataBaoli = new ArrayList<>();
        boliofferAdapter = new SellAssetsBaoliAdapter(dataBaoli);
        boliofferAdapter.openLoadAnimation();
        rvList.setAdapter(boliofferAdapter);

        List<AsellasetsFuFeiTingRes.DataBean> data = new ArrayList<>();
        sellassertFufeitingAdapter = new SellassertFufeitingAdapter(data);
        sellassertFufeitingAdapter.openLoadAnimation();
        rvList.setAdapter(sellassertFufeitingAdapter);
        sellassertFufeitingAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMyassertData();
            }
        });
        boliofferAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreBaoli();
            }
        });
//        sellassertFufeitingAdapter.openLoadAnimation();
//        rvList.setAdapter(quoteListAdapter);
        if ("1".equals(assetsType)) {
            rvList.setAdapter(sellassertFufeitingAdapter);
        } else if ("2".equals(assetsType)) {
            rvList.setAdapter(boliofferAdapter);
        }

    }

    private void loadMoreBaoli() {
        getBaoLiAssebao();
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
        sellassertFufeitingAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        boliofferAdapter.setEnableLoadMore(false);
        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
                mNextRequestPage = 1;
                //福费廷
                getMyassertData();
            } else if ("2".equals(assetsType)) {
                mNextRequestPage1 = 1;
                getBaoLiAssebao();
            }
        }
    }

    private void getMyassertData() {
        MyassetsReq myassetsRes = new MyassetsReq();
        myassetsRes.setPage(mNextRequestPage);
        myassetsRes.setPageSize(PAGE_SIZE);
        if ("1".equals(assetsType)) {
            myassetsRes.setCurrency(bizhong_type);
            myassetsRes.setRecheckState(offerType);
            myassetsRes.setMinimum(scope_start);
            myassetsRes.setMaximum(scope_end);
            myassetsRes.setMinDiscountRate(tiexianlvStartType);
            myassetsRes.setMaxDiscountRate(tiexianlvEndType);
            myassetsRes.setDebtType(mine_fufeiting);
            myassetsRes.setCountryId(country_id);
            myassetsRes.setOpenFullName(openfullname);
            myassetsRes.setAscDesc(ascDesc);
            myassetsRes.setOrderBy(orderBy);
        }

        ActionPresenter.getInstance().asserfufeitingRet(myassetsRes).enqueue(new Callback<AsellasetsFuFeiTingRes>() {
            @Override
            public void onResponse(Call<AsellasetsFuFeiTingRes> call, Response<AsellasetsFuFeiTingRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage == 1) {
                                setData(true, response.body().getData());
                            } else if (mNextRequestPage > 1 && mNextRequestPage <= response.body().getTotalPage()) {
                                setData(false, response.body().getData());
                            } else {
                                //第一页如果不够一页就不显示没有更多数据布局
                                sellassertFufeitingAdapter.loadMoreEnd(false);
                            }
                            sellassertFufeitingAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
                        } else {
                            sellassertFufeitingAdapter.setEnableLoadMore(true);
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

    private void setData(boolean isRefresh, List dataFufeiting) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = dataFufeiting == null ? 0 : dataFufeiting.size();
        if (size > 0) {
            tvNoAssert.setVisibility(View.GONE);
        } else {
            tvNoAssert.setVisibility(View.VISIBLE);
        }
        if (isRefresh) {
            sellassertFufeitingAdapter.setNewData(dataFufeiting);
        } else {
            if (size > 0) {
                sellassertFufeitingAdapter.addData(dataFufeiting);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            sellassertFufeitingAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            sellassertFufeitingAdapter.loadMoreComplete();
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

    private void initModleFactoring() {
        mainFactoringList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < modelFactoring.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", modelFactoring.LISTVIEWTXT[i]);
            mainFactoringList.add(map);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        if (Constants.BROADCAST_ACTION.SEND_REFERSH_SELL_ASSERT.equals(intent.getAction())) {
            refresh();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unregisterAction(Constants.BROADCAST_ACTION.SEND_REFERSH_SELL_ASSERT);
    }

    @OnClick({R.id.tv_fufeiting, R.id.bt_public_data, R.id.bt_select_shuaixuan, R.id.data_order, R.id.rl_shuaixuan})
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
                    showDataOrder();
                } else {
                    showDataFactoringOrder();
                }
                break;

            case R.id.bt_select_shuaixuan:
            case R.id.rl_shuaixuan:
                //筛选
//                startActivity(new Intent(getActivity(), SellAssetsSelectActivity.class));
//                DisPatcher.startFiltrateActivity(getActivity(), shareKey, 1);
                Intent intent = new Intent(getActivity(), SellAssetsSelectActivity.class);
                intent.putExtra("shareKey", shareKey);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                break;
        }
    }

    private void showDataOrder() {
//         getLayoutInflater().inflate(R.layout.mine_list_data_order,null);
        View popupView_order = LayoutInflater.from(mContext).inflate(R.layout.mine_list_data_order, null);
//        View root = getLayoutInflater().inflate.inflate(R.layout.mine_list_data_order, null);
//        orderBy = "create_time";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(getActivity(), mainList);
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
                    //资产录入时间
                    orderBy = "create_time";
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
//                    btPublicData.setText(R.string.mine_will_shengxu);
                    ascDesc = "asc";
                } else if (position == 1) {
//                    btPublicData.setText(R.string.mine_will_sale_jianxu);
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

    //保理
    private void showDataFactoringOrder() {
//         getLayoutInflater().inflate(R.layout.mine_list_data_order,null);
        View popupView_order = LayoutInflater.from(mContext).inflate(R.layout.mine_list_data_order, null);
//        View root = getLayoutInflater().inflate.inflate(R.layout.mine_list_data_order, null);
//        orderBy = "create_time";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(getActivity(), mainFactoringList);
//        mainAdapter.setSelectItem(0);
        order_main_list.setAdapter(mainAdapter);

        if ("create_time".equals(orderBy)) {
            mainAdapter.setSelectItem(0);
        } else if ("amount".equals(orderBy)) {
            mainAdapter.setSelectItem(1);
        }else if ("maturity".equals(orderBy)) {
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
                    orderBy = "create_time";
                } else if (position == 1) {
                    //金额
                    orderBy = "amount";
                }else if (position == 2) {
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
//                    btPublicData.setText(getString(R.string.filtrate_asc));
                    ascDesc = "asc";
                } else if (position == 1) {
//                    btPublicData.setText(getString(R.string.filtrate_desc));
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
        moreAdapter = new ClassifyMorAdapter(getActivity(), array);
        order_more_list.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    private void showFufeiting() {

        final View popupView = getActivity().getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item_filter_list, textList));
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

                shareKey = Constants.SPKEY.SP_MINE_SELL_ASSETS_LIST_KEY + assetsType;
                btPublicData.setText(getString(R.string.filtrate_inputdate));
                SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
                window_fufeiting.dismiss();
//                initQuoteAdapter(assetsType);
                setChangeAdapter(assetsType);
                refresh();
            }
        });
//        SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
    }

    private void getBaoLiAssebao() {

        //保理
        AssertsBaoliReq baoliRes = new AssertsBaoliReq();
        baoliRes.setPage(mNextRequestPage1);
        baoliRes.setPageSize(PAGE_SIZE);
        if ("2".equals(assetsType)) {
            baoliRes.setCurrency(bizhong_type);
            baoliRes.setCheckState(offerType);
            baoliRes.setMinimum(scope_start);
            baoliRes.setMaximum(scope_end);
            baoliRes.setMinTransferRate(tiexianlvStartType);
            baoliRes.setMaxTransferRate(tiexianlvEndType);
//        mineForfaitingOnSaleListReq1.setDebyType(mine_fufeiting);
            baoliRes.setCountryId(country_id);
            baoliRes.setOpenFullName(openfullname);
            baoliRes.setAscDesc(ascDesc);
            baoliRes.setOrderBy(orderBy);
        }
        //资产状态
        ActionPresenter.getInstance().asserfufeitingRet(baoliRes).enqueue(new Callback<AsellasetsBaoliRes>() {
            @Override
            public void onResponse(Call<AsellasetsBaoliRes> call, Response<AsellasetsBaoliRes> response) {

                MyLogger.pLog().d("LoginRes：" + response.body().toString());
                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage1 == 1) {
                                setDataBaoli(true, response.body().getData());
                            } else if (mNextRequestPage1 > 1 && mNextRequestPage1 <= response.body().getTotalPage()) {
                                setDataBaoli(false, response.body().getData());
                            } else {
                                //第一页如果不够一页就不显示没有更多数据布局
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
        } else {
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

    private void setChangeAdapter(String assetsType) {
        if ("1".equals(assetsType)) {
            rvList.setAdapter(sellassertFufeitingAdapter);
        } else if ("2".equals(assetsType)) {
            rvList.setAdapter(boliofferAdapter);
        }
        initItemListener();
    }

    private void loadFuFeiting() {
        showFufeiting();
    }

    protected void initFilterData() {
        myDialog = new AlertDialog.Builder(getActivity(), R.style.Dialog).create();
        //资产状态
        offerType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "offerType", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"currency_type",currency);
        //币种
        bizhong_type = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "currency_type", "");

        //贴现率范围
        tiexianlvStartType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "tiexianlv_start", "");
        tiexianlvEndType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "tiexianlv_end", "");

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_FIRST_USER) {
            if (resultCode == RESULT_CANCELEDFILTER) {
//                ToastUtils.showShort("筛选");
                initFilterData();
                refresh();
            }
        }
    }

    public void resetFiltrate() {
        //承兑金额范围
        SharedPreferanceUtils.put(getActivity(), shareKey + "scope_start", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "scope_end", "");
        //贴现率范围
        SharedPreferanceUtils.put(getActivity(), shareKey + "tiexianlv_start", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "tiexianlv_end", "");
        //开证行名称
        SharedPreferanceUtils.put(getActivity(), shareKey + "open_book_name", "");
//                SharedPreferanceUtils.put(getActivity(), shareKey + "aleadyCance", offerType);
        //福费廷类型
        SharedPreferanceUtils.put(getActivity(), shareKey + "mine_fufeiting", "");
        //资产状态
        SharedPreferanceUtils.put(getActivity(), shareKey + "offerType", "");
        //币种
        SharedPreferanceUtils.put(getActivity(), shareKey + "currency_type", "");
        //保理 福费廷
//                SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
        //地区
        SharedPreferanceUtils.put(getActivity(), shareKey + "country_id", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "area_name", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "area_enname", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "country_name", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "country_enname", "");
    }


    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = getString(R.string.mine_will_sale_sure);
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                if ("1".equals(assetsType)) {
                    if ("1".equals(SureOrCancelDialogtype)) {
                        //提交-
                        if ("2".equals(userType)) {
                            handleForfaitingCommitRet();
                        } else if ("3".equals(userType)) {
                            getAssertCommit();
                        }
                    } else if ("2".equals(SureOrCancelDialogtype)) {
                        //经办提交
                        if ("2".equals(userType)) {
                            handleForfaitingCommitRet();
                        } else if ("3".equals(userType)) {
                            getAssertCommit();
                        }
                    } else if ("3".equals(SureOrCancelDialogtype)) {
                        //删除
                        getAssertDelete();
                    } else if ("4".equals(SureOrCancelDialogtype)) {
                        //发布
                        reviewForfaitingIussRet();
                    } else if ("5".equals(SureOrCancelDialogtype)) {
                        //取消
                        getAssertCancel("3");
                    }
                } else if ("2".equals(assetsType)) {
                    if ("1".equals(SureOrCancelDialogtype)) {
                        if ("2".equals(userType)) {
                            //经办提交
                            handleFactoringCommitRet();
                        } else if ("3".equals(userType)) {
                            factoringOfferBohuiRet("1", "");
                        }
                    } else if ("2".equals(SureOrCancelDialogtype)) {
                        //复核提交-
                        if ("2".equals(userType)) {
                            handleFactoringCommitRet();
                        } else if ("3".equals(userType)) {
//                            getAssertCommit();
                            //1, "提交", 2, "驳回" 3,"取消";
                            factoringOfferBohuiRet("1", "");
                        }
                    } else if ("3".equals(SureOrCancelDialogtype)) {
                        //删除
                        factoringDeleteRet();
                    } else if ("4".equals(SureOrCancelDialogtype)) {
                        //取消-复核
                        factoringOfferBohuiRet("3", "");
                    }else if ("5".equals(SureOrCancelDialogtype)) {
                        //发布-复核
                        reviewFactoringCommitCommitRet();
                    }
                }
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect,String companyName) {
                MyLogger.pLog().i("negative");
            }
        }, "" + dialogContent, getString(R.string.onsalelist_forfaiting_adapter_cancel), getString(R.string.onsalelist_forfaiting_adapter_sure));
        sureOrCancelDialog.show();
    }

    /**
     * 复核人员查询已失效资产接口
     *
     * @POST(Constants.NETPATH.CHECKEXPIREDASSETS) Call<CheckTransferLetterRes> checkExpiredAssetsRet(@Body RequestBody requestBody);
     *
     * public Call<CheckTransferLetterRes> checkExpiredAssetsRet(ReviewCheckTransferReq data) {
     *         Call<CheckTransferLetterRes> checkExpiredAssetsRet = mApi.checkExpiredAssetsRet(createRequestBody(data));
     *         return checkExpiredAssetsRet;
     *     }
     * }
     */
    private void checkExpiredAssets(final String assets_id, final String assetsType, final String assetsNo, final String title, final String discountRate) {
        ReviewCheckTransferReq reviewCheckTransferReq = new ReviewCheckTransferReq();
        reviewCheckTransferReq.setId(assets_id);
        reviewCheckTransferReq.setType(assetsType);
        ActionPresenter.getInstance().checkExpiredAssetsRet(reviewCheckTransferReq).enqueue(new Callback<CheckTransferLetterRes>() {
            @Override
            public void onResponse(Call<CheckTransferLetterRes> call, Response<CheckTransferLetterRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {
                            //1.只能修改信息有效期 2.信息有效期和贴现率都不能修改 3.表示两个字段都能修改
                            String code = response.body().getData();
                            if ("1".equals(code)) {
                                DisPatcher.startSellAssetsRepublishActivity(getActivity(), assets_id, assetsType, "1", assetsNo, title, discountRate);
                            } else if ("3".equals(code)){
                                DisPatcher.startSellAssetsRepublishActivity(getActivity(), assets_id, assetsType, "3", assetsNo, title, discountRate);
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
            }

            @Override
            public void onFailure(Call<CheckTransferLetterRes> call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }
}
