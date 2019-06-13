package com.zjhl.pad.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.Model;
import com.zjhl.pad.app.utils.ModelFactoring;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.AssertCommitReq;
import com.zjhl.pad.moudle.entity.req.BaojiatxReq;
import com.zjhl.pad.moudle.entity.req.CansalResonReq;
import com.zjhl.pad.moudle.entity.req.IussingFactoringEntryReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.req.MineForfaitingOnSaleListReq;
import com.zjhl.pad.moudle.entity.req.ReviewCheckTransferReq;
import com.zjhl.pad.moudle.entity.req.ReviewSubmitLetterReq;
import com.zjhl.pad.moudle.entity.res.BaojiatxRs;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.BohuiResonRes;
import com.zjhl.pad.moudle.entity.res.CanselRes;
import com.zjhl.pad.moudle.entity.res.CheckLetterRes;
import com.zjhl.pad.moudle.entity.res.CheckTransferLetterRes;
import com.zjhl.pad.moudle.entity.res.DownCheckLetterRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.MyOfferBaoliRs;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitOnSaleListRes;
import com.zjhl.pad.moudle.entity.res.UploadFileRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.FiltrateActivity;
import com.zjhl.pad.view.activity.PdfActivity;
import com.zjhl.pad.view.activity.ReviewOfferLetterActivity;
import com.zjhl.pad.view.activity.SubmitLetterActivity;
import com.zjhl.pad.view.adapter.ClassifyMorAdapter;
import com.zjhl.pad.view.adapter.DataOrderMainAdapter;
import com.zjhl.pad.view.adapter.MineFactoringOnSaleListAdapter;
import com.zjhl.pad.view.adapter.MineForfaitingOnSaleListAdapter;
import com.zjhl.pad.view.base.BaseFragment;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.DownloadDialog;
import com.zjhl.pad.view.views.EnterpriseRejectDialog;
import com.zjhl.pad.view.views.HandleOfferDetailDialog;
import com.zjhl.pad.view.views.RejectDialog;
import com.zjhl.pad.view.views.ReviewOfferDetailDialog;
import com.zjhl.pad.view.views.ReviewOfferLetterDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.shaohui.bottomdialog.BottomDialog;
import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: SoldInAssetsFragment.java 在售资产
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/23 9:35
 * Changes (from 2018/5/23)
 */
public class SoldInAssetsFragment extends BaseFragment {
    DataOrderMainAdapter mainAdapter;
    ClassifyMorAdapter moreAdapter;
    @BindView(R.id.bt_select)
    TextView btSelect;
    @BindView(R.id.tv_fufeiting)
    RelativeLayout tvFufeiting;
    @BindView(R.id.bt_public_data)
    TextView btPublicData;
    @BindView(R.id.data_order)
    RelativeLayout dataOrder;
    @BindView(R.id.bt_select_shuaixuan)
    TextView btSelectShuaixuan;
    @BindView(R.id.rl_shuaixuan)
    RelativeLayout rlShuaixuan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_no_assert)
    TextView tvNoAssert;
    Unbinder unbinder;
    private ListView lsvMore;
    private ListView order_more_list;
    private List<Map<String, Object>> mainList;
    private List<Map<String, Object>> mainFactoringList;
    private String[] textList = {MyApplication.mMyApplication.getResources().getString(R.string.lable_forfaiting), MyApplication.mMyApplication.getResources().getString(R.string.lable_facotring)};
    private int mNextRequestPage = 1;
    private int mNextRequestPage1 = 1;
    private MineForfaitingOnSaleListAdapter mineForfaitingOnSaleListAdapter;
    private MineFactoringOnSaleListAdapter mineFactoringOnSaleListAdapter;
    private AlertDialog myDialog = null;
    private Button bt_payButton;
    boolean isChecked = false;
    private String assetsType;
    private String bizhong_type;
    private String tiexianlvStartType;
    private String tiexianlvEndType;
    private String mine_fufeiting;
    private String country_id;
    private String area_id;
    private String openfullname;
    private String ascDesc = "desc";
    private String orderBy;
    private String offerType;
    private String scope_start;
    private String scope_end;
    SureOrCancelDialog sureOrCancelDialog;
    private String priceId;
    String assetsState = "";
    private String et_tiexianlv;
    //增加前缀防止重复
    private String shareKey = Constants.SPKEY.SP_MINE_ON_SALE_LIST_KEY;
    MineForfaitingOnSaleListReq mineForfaitingOnSaleListReq;
    MineForfaitingOnSaleListReq mineForfaitingOnSaleListReq1;
    MarketForfaitingOfferReq marketForfaitingOfferReq;
    MessageListReq messageListReq;
    HandleOfferDetailDialog offerDetailDialog;
    ReviewOfferDetailDialog reviewOfferDetailDialog;
    RejectDialog rejectDialog;
    ReviewOfferLetterDialog reviewOfferLetterDialog;
    String operateButton = "";
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
    public ReviewOfferSubmitOnSaleListRes reviewOfferSubmitOnSaleListRes;//提交成功返回数据 用于展示邀约函
    private int actionCode = 1;

    ReviewSubmitLetterReq reviewSubmitLetterReq = new ReviewSubmitLetterReq();
    private String assets_id;
    protected static final int SEND_SMS_REQUEST = 0;
    private static final int RESULT_CANCELED = 0;
    private static final int RESULT_CANCELEDFILTER = 1;
    Model model = new Model();
    EnterpriseRejectDialog enterpriseRejectDialog;
    ModelFactoring modelFactoring = new ModelFactoring();

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_sellassert, null);

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        assetsType = "1";
        shareKey = Constants.SPKEY.SP_MINE_ON_SALE_LIST_KEY + assetsType;
        SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
        btPublicData.setText(getString(R.string.market_forfaiting_title_filtrate_send));
        resetFiltrate();
        initQuoteAdapter(assetsType);
        myDialog = new AlertDialog.Builder(getActivity(), R.style.Dialog).create();
        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
//                getForfaitingData();
            } else if ("2".equals(assetsType)) {
//                getFactoringData();
//            }
            }

            btPublicData.setText(getString(R.string.filtrate_senddate));
            rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
            initModle();
            initModleFactoring();
//            initFilterData();
            initRefreshLayout();
            refresh();
//            initItemListener();
        }
        return rootView;
    }


    private void setFactoringData(boolean isRefresh, List dataBaoli) {
        //增加页码  设置数据
        mNextRequestPage1++;
        final int size = dataBaoli == null ? 0 : dataBaoli.size();
        if (size > 0) {
            tvNoAssert.setVisibility(View.GONE);
        } else {
            tvNoAssert.setVisibility(View.VISIBLE);
        }
        if (isRefresh) {
            mineFactoringOnSaleListAdapter.setNewData(dataBaoli);

        } else {
            if (size > 0) {
                mineFactoringOnSaleListAdapter.addData(dataBaoli);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mineFactoringOnSaleListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            mineFactoringOnSaleListAdapter.loadMoreComplete();
        }
    }


    private void initItemListener() {


        /**
         * 保理
         */
        mineFactoringOnSaleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DisPatcher.startMarketFactoringDetailActivity(getActivity(), ((MyOfferBaoliRs.DataBean) adapter.getItem(position)).getId() + "", /*((MyOfferBaoliRs.DataBean) adapter.getItem(position)).getMyAssets() + */"1");
                DisPatcher.startSoldFactoringDetailActivity(getActivity(), ((MyOfferBaoliRs.DataBean) adapter.getItem(position)).getId() + "");
                if (mineFactoringOnSaleListAdapter != null && mineFactoringOnSaleListAdapter.getItemCount() > 0) {
                    assets_id = mineFactoringOnSaleListAdapter.getItem(position).getId() + "";
                }

            }
        });
        mineFactoringOnSaleListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mineFactoringOnSaleListAdapter != null && mineFactoringOnSaleListAdapter.getItemCount() > 0) {
                    priceId = mineFactoringOnSaleListAdapter.getItem(position).getPriceId();
                    assets_id = mineFactoringOnSaleListAdapter.getItem(position).getId() + "";
                    assetsState = mineFactoringOnSaleListAdapter.getItem(position).getCheckState() + "";
                }
                if (view != null) {
                    switch (view.getId()) {
                        case R.id.ll_fuhe_offer_detail:
                            //报价查看详情
                            initDetailOfferListData(assets_id, "2");
//                            if ("2".equals(userType)) {
//                                showHandleOfferDetailDialog(assets_id, position);
//
//                            } else if ("3".equals(userType)) {
//                                showReviewOfferDetailDialog(assets_id, assetsType);
//                            }
                            break;
                        case R.id.ll_fuhe_check_letter:
                            //查看邀约函
//                            LetterOffer();
                            break;
                        case R.id.tv_fuhe_cancel:
                            //取消
                            initSureOrCancelDialogView("1", getString(R.string.toast_forfaiting_sell_cancelhint));
//                            if ("104".equals(assetsState)) {
//                                operateButton = "3";
//                                factoringOfferBohuiRet(operateButton, "");
//                            } else {
//                                reviewFactoringCancelSellRet(assets_id);
//                            }
                            break;
                        case R.id.tv_offer_reason:
                            //驳回原因
                            String english = "";
                            if ("en".equals(lanage)) {
                                english = "The bid of ";
                            }
                            String[] reason = {};
                            if (!StringUtils.isEmpty(mineFactoringOnSaleListAdapter.getItem(position).getPriceReason())) {
                                reason = mineFactoringOnSaleListAdapter.getItem(position).getPriceReason().split("@@");
                            }
                            if (reason.length == 2) {
                                showReviewRejectDialog(getString(R.string.sellassert_forfaiting_adapter_rejectreson), english + "" + reason[0], getString(R.string.sold_assets_reject_reason1), getString(R.string.sold_assets_reject_reason2) + reason[1]);
                            }
                            break;
                        case R.id.tv_fuhe_commit1:
                            //提交
                            //提交让渡函
//                            reviewSubmitLetterReq.setId(assets_id + "");
//                            actionCode = 1;
//                            bottomDialog();
                            Intent intent = new Intent(getActivity(), SubmitLetterActivity.class);
                            intent.putExtra("assetsType", assetsType);
                            intent.putExtra("assets_id", assets_id);
                            startActivityForResult(intent, 666);
                            break;
                        case R.id.tv_check_more_right:
                            //查看更多右
                            showCheckMoreSide(view, assets_id, assetsType, "2");

                            break;
                    }
                }
            }
        });
        /**
         * 福费廷
         */
        mineForfaitingOnSaleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                priceId = mineForfaitingOnSaleListAdapter.getItem(position).getPriceId();
//                DisPatcher.startMarketForfaitingDetailActivity(getActivity(), ((MyOfferFufei.DataBean) adapter.getItem(position)).getId() + "", /*((MyOfferFufei.DataBean) adapter.getItem(position)).getMyAssets() + */"1");
                DisPatcher.startSoldForfaitingDetailActivity(getActivity(), ((MyOfferFufei.DataBean) adapter.getItem(position)).getId() + "", "1");
                if (mineForfaitingOnSaleListAdapter != null && mineForfaitingOnSaleListAdapter.getItemCount() > 0) {
                    priceId = mineForfaitingOnSaleListAdapter.getItem(position).getPriceId();
                    assets_id = mineForfaitingOnSaleListAdapter.getItem(position).getId() + "";
                }
            }
        });

        mineForfaitingOnSaleListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mineForfaitingOnSaleListAdapter != null && mineForfaitingOnSaleListAdapter.getItemCount() > 0) {
                    priceId = mineForfaitingOnSaleListAdapter.getItem(position).getPriceId();
                    assets_id = mineForfaitingOnSaleListAdapter.getItem(position).getId() + "";
                    assetsState = mineForfaitingOnSaleListAdapter.getItem(position).getRecheckState() + "";

                }
                if (view != null) {
                    switch (view.getId()) {
                        case R.id.ll_fuhe_offer_detail:
                            initDetailOfferListData(assets_id, "1");
//                            if ("2".equals(userType)) {
//                                showHandleOfferDetailDialog(assets_id, position);
//
//                            } else if ("3".equals(userType)) {
//                                showReviewOfferDetailDialog(assets_id, assetsType);
//                            }
                            break;
                        case R.id.ll_fuhe_check_letter:
                            //查看要约函
//                            checkLetterRet();
                            downCheckLetterRet(assets_id);
//                            DisPatcher.startCheckLetterActivity(getActivity(), assets_id);
                            break;
                        case R.id.tv_fuhe_cancel:
                            //取消
                            initSureOrCancelDialogView("2", getString(R.string.toast_forfaiting_sell_cancelhint));
//                            if ("104".equals(assetsState)) {
//                                operateButton = "3";
//                                reviewForfaitingCancelSellRet0(operateButton, "");
//                            } else {
//                                reviewForfaitingCancelSellRet(assets_id);
//                            }
                            break;
                        case R.id.tv_offer_reason:
                            //驳回原因
                            String english = "";
                            if ("en".equals(lanage)) {
                                english = "The bid of ";
                            }
//                            showReviewRejectDialog(""+ mineForfaitingOnSaleListAdapter.getItem(position).getPriceReason(), "", "" );
                            String[] reason = {};
                            if (!StringUtils.isEmpty(mineForfaitingOnSaleListAdapter.getItem(position).getPriceReason())) {
                                reason = mineForfaitingOnSaleListAdapter.getItem(position).getPriceReason().split("@@");
                            }
                            if (reason.length == 2) {
                                showReviewRejectDialog(getString(R.string.sellassert_forfaiting_adapter_rejectreson), english + "" + reason[0], getString(R.string.sold_assets_reject_reason1), getString(R.string.sold_assets_reject_reason2) + reason[1]);
                            }
//                            测试对话框代码showReviewRejectDialog(getString(R.string.sellassert_forfaiting_adapter_rejectreson), "" , getString(R.string.sold_assets_reject_reason1), getString(R.string.sold_assets_reject_reason2) + "附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒附件阿拉山口的积分懒");
                            break;
                        case R.id.tv_fuhe_commit1:
                            //提交让渡函
//                            reviewSubmitLetterReq.setId(((MyOfferFufei.DataBean) adapter.getItem(position)).getId() + "");
//                            actionCode = 1;
//                            bottomDialog();
                            Intent intent = new Intent(getActivity(), SubmitLetterActivity.class);
                            intent.putExtra("assetsType", assetsType);
                            intent.putExtra("assets_id", assets_id);
                            startActivityForResult(intent, 666);
                            break;
                        case R.id.tv_check_more_right:
                            //查看更多右(经办 撮合成功，交易完成)
                            showCheckMoreSide(view, assets_id, assetsType, "0");
                            break;
                        case R.id.tv_check_more_right_1:
                            //查看更多右(经办 已取消)
                            showCheckMoreSide(view, assets_id, assetsType, "1");
                            break;
                        case R.id.tv_check_more_center:
                            //查看更多中(复核 撮合成功 2个)
                            showCheckMoreCenter(view, assets_id, assetsType, "0");
                            break;
                        case R.id.tv_check_more_center_1:
                            //查看更多中(复核 撮合成功 1个)
                            showCheckMoreCenter(view, assets_id, assetsType, "1");
                            break;

                    }


                }


            }


        });


    }

    private void showHandleOfferDetailDialog(String assets_id, int position) {
        //报价详情
        offerDetailDialog = new HandleOfferDetailDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {
                //经办提交成功  刷新列表
                refresh();
            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {
            }

            @Override
            public void negative(String isSelect, String companyName) {

            }
        }, assets_id + "", assetsType);
        offerDetailDialog.setCancelVisible(false);
        offerDetailDialog.show();
    }

    //复核驳回
    private void showReviewRejectDialog(String companyname) {
        rejectDialog = new RejectDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {
                MyLogger.pLog().e(isSelect);
                reviewFactoringRejectRet(priceId, isSelect);
            }

            @Override
            public void negative(String isSelect, String companyName) {
                //驳回操作
//                reviewBoHuiReason();
            }
        }, "", companyname);
        rejectDialog.show();
    }

    //复核报价列表
    private void showReviewOfferDetailDialog(String assets_id, String tradingType) {
        reviewOfferDetailDialog = new ReviewOfferDetailDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, final String isSelect) {
//                reviewOfferSubmitOnSaleListRes = (ReviewOfferSubmitOnSaleListRes) responseBean;
                //报价id 显示邀约函弹窗
//                showLetter(isSelect);
                //请求邀约函数据 并显示弹窗
                new SureOrCancelDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {
                    @Override
                    public void positive() {
                        confirmOfferData(isSelect);
                    }

                    @Override
                    public void positive(ResponseBean responseBean, String isSelect) {

                    }

                    @Override
                    public void negative(String isSelect, String companyName) {

                    }
                }, getString(R.string.issue_forfaiting_sell_yesorno),
                        getString(R.string.onsalelist_forfaiting_adapter_cancel),
                        getString(R.string.onsalelist_forfaiting_adapter_sure)).show();

//                MyLogger.pLog().e(isSelect);
            }

//            @Override
//            public void negative(String isSelect) {
            //驳回操作
//                reviewBoHuiReason();
//                priceId = isSelect;
//                showReviewRejectDialog();
//            }

            @Override
            public void negative(String isSelect, String companyName) {
                //驳回操作
                priceId = isSelect;
                showReviewRejectDialog(companyName);
            }
        }, assets_id + "", tradingType);
        reviewOfferDetailDialog.show();
    }

    //显示邀约函弹窗
    private void showLetter(String isSelect) {
        reviewOfferDetailDialog.dismiss();
        reviewOfferLetterDialog = new ReviewOfferLetterDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {

            @Override
            public void positive() {

            }

            @Override
            public void positive(ResponseBean responseBean, String isSelect) {
//                MyLogger.pLog().e("1111");
                if ("300".equals(isSelect)) {
//                    ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));

                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                    refresh();
                } else {
                    ToastUtils.showShort("" + isSelect);
                }
            }

            @Override
            public void negative(String isSelect, String companyName) {

            }
        }, reviewOfferSubmitOnSaleListRes, isSelect);
        reviewOfferLetterDialog.setCancelVisible(false);
        reviewOfferLetterDialog.show();
    }


    private void commitTiexianlv() {
        //贴现率
        BaojiatxReq baojiaReq = new BaojiatxReq();
        baojiaReq.setDiscountRate(et_tiexianlv);
        baojiaReq.setPriceId(priceId);

        ActionPresenter.getInstance().myasserRet(baojiaReq).enqueue(new Callback<BaojiatxRs>() {
            @Override
            public void onResponse(Call<BaojiatxRs> call, Response<BaojiatxRs> response) {

//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        ToastUtils.showShort(response.body().getMessage());
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


    private void initQuoteAdapter(String assetsType) {
        List<MyOfferBaoliRs.DataBean> dataBaoli = new ArrayList<>();
        mineFactoringOnSaleListAdapter = new MineFactoringOnSaleListAdapter(dataBaoli);
        mineFactoringOnSaleListAdapter.openLoadAnimation();
//        rvList.setAdapter(mineFactoringOnSaleListAdapter);

        List<MyOfferFufei.DataBean> data = new ArrayList<>();
        mineForfaitingOnSaleListAdapter = new MineForfaitingOnSaleListAdapter(data);
        mineForfaitingOnSaleListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadForfaiting();
            }
        });
        mineFactoringOnSaleListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadFactoring();
            }
        });
        mineForfaitingOnSaleListAdapter.openLoadAnimation();
        rvList.setAdapter(mineForfaitingOnSaleListAdapter);
        setChangeAdapter(assetsType);
    }

    private void setChangeAdapter(String assetsType) {
        if ("1".equals(assetsType)) {
//            mineForfaitingOnSaleListAdapter.setHasStableIds(true);
            rvList.setAdapter(mineForfaitingOnSaleListAdapter);
        } else if ("2".equals(assetsType)) {
//            mineFactoringOnSaleListAdapter.setHasStableIds(true);
            rvList.setAdapter(mineFactoringOnSaleListAdapter);
        }
        initItemListener();
    }

    private void loadForfaiting() {
        getForfaitingData();
    }

    private void loadFactoring() {
        getFactoringData();
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
        if (!TextUtils.isEmpty(assetsType)) {
            mineForfaitingOnSaleListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
            if ("1".equals(assetsType)) {
                mNextRequestPage = 1;
                getForfaitingData();
            } else if ("2".equals(assetsType)) {
                mNextRequestPage1 = 1;
                mineFactoringOnSaleListAdapter.setEnableLoadMore(false);
                getFactoringData();
            }
        }

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
            mineForfaitingOnSaleListAdapter.setNewData(dataFufeiting);
        } else {
            if (size > 0) {
                mineForfaitingOnSaleListAdapter.addData(dataFufeiting);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mineForfaitingOnSaleListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            mineForfaitingOnSaleListAdapter.loadMoreComplete();
        }
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
        openfullname = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "openfullname", "");

        //福费廷类型
        mine_fufeiting = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "mine_fufeiting", "");
//        SharedPreferanceUtils.put(FiltrateActivity.this,"country_id",guojia_seconde_id);
        country_id = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "country_id", "");
        area_id = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "area_id", "");
        assetsType = (String) SharedPreferanceUtils.get(getActivity(), shareKey + "assetsType", "1");
    }

    //保理
    private void getFactoringData() {
        mineForfaitingOnSaleListReq1 = new MineForfaitingOnSaleListReq();
//        mineForfaitingOnSaleListReq1.setSaleState("1");
        mineForfaitingOnSaleListReq1.setPage(mNextRequestPage1);
        mineForfaitingOnSaleListReq1.setPageSize(PAGE_SIZE);
        if ("2".equals(assetsType)) {
            mineForfaitingOnSaleListReq1.setCurrency(bizhong_type);
            mineForfaitingOnSaleListReq1.setCheckState(offerType);
            mineForfaitingOnSaleListReq1.setMinimum(scope_start);
            mineForfaitingOnSaleListReq1.setMaximum(scope_end);
            mineForfaitingOnSaleListReq1.setMinDiscountRate(tiexianlvStartType);
            mineForfaitingOnSaleListReq1.setMaxDiscountRate(tiexianlvEndType);
//        mineForfaitingOnSaleListReq1.setDebyType(mine_fufeiting);
            if (!StringUtils.isEmpty(country_id) && !StringUtils.isEmpty(area_id)) {
                mineForfaitingOnSaleListReq1.setCountryId(country_id + "," + area_id);
            }
            mineForfaitingOnSaleListReq1.setOpenFullName(openfullname);
            mineForfaitingOnSaleListReq1.setAscDesc(ascDesc);
            mineForfaitingOnSaleListReq1.setOrderBy(orderBy);
        }
        ActionPresenter.getInstance().mineFactoringOnSaleListRet(mineForfaitingOnSaleListReq1).enqueue(new Callback<MyOfferBaoliRs>() {
            //        ActionPresenter.getInstance().myasserRet(baoliRes).enqueue(new Callback<MyOfferBaoliRs>() {
            @Override
            public void onResponse(Call<MyOfferBaoliRs> call, Response<MyOfferBaoliRs> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage1 == 1) {
                                setFactoringData(true, response.body().getData());
                            } else if (mNextRequestPage1 > 1 && mNextRequestPage1 <= response.body().getTotalPage()) {
                                setFactoringData(false, response.body().getData());
                            } else {
                                mineFactoringOnSaleListAdapter.loadMoreEnd(false);
                            }

                            mineFactoringOnSaleListAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
                        } else {
                            mineFactoringOnSaleListAdapter.setEnableLoadMore(true);
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

    //福费廷
    private void getForfaitingData() {
        mineForfaitingOnSaleListReq = new MineForfaitingOnSaleListReq();
//        mineForfaitingOnSaleListReq.setSaleState("1");
        mineForfaitingOnSaleListReq.setPage(mNextRequestPage);
        mineForfaitingOnSaleListReq.setPageSize(PAGE_SIZE);
        if ("1".equals(assetsType)) {
            mineForfaitingOnSaleListReq.setCurrency(bizhong_type);
            mineForfaitingOnSaleListReq.setRecheckState(offerType);
            mineForfaitingOnSaleListReq.setMinimum(scope_start);
            mineForfaitingOnSaleListReq.setMaximum(scope_end);
            mineForfaitingOnSaleListReq.setMinDiscountRate(tiexianlvStartType);
            mineForfaitingOnSaleListReq.setMaxDiscountRate(tiexianlvEndType);
            mineForfaitingOnSaleListReq.setDebtType(mine_fufeiting);
            if (!StringUtils.isEmpty(country_id) && !StringUtils.isEmpty(area_id)) {
                mineForfaitingOnSaleListReq.setCountryId(country_id + "," + area_id);
            }
            mineForfaitingOnSaleListReq.setOpenFullName(openfullname);
            mineForfaitingOnSaleListReq.setAscDesc(ascDesc);
            mineForfaitingOnSaleListReq.setOrderBy(orderBy);
        }
        ActionPresenter.getInstance().mineForfaitingOnSaleListRet(mineForfaitingOnSaleListReq).enqueue(new Callback<MyOfferFufei>() {
            //        ActionPresenter.getInstance().myasserRet(myassetsRes).enqueue(new Callback<MyOfferFufei>() {
            @Override
            public void onResponse(Call<MyOfferFufei> call, Response<MyOfferFufei> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            if (mNextRequestPage == 1) {
                                setData(true, response.body().getData());
                            } else if (mNextRequestPage > 1 && mNextRequestPage <= response.body().getTotalPage()) {
                                setData(false, response.body().getData());
                            } else {
                                mineForfaitingOnSaleListAdapter.loadMoreEnd(false);
                            }

                            mineForfaitingOnSaleListAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
                        } else {
                            mineForfaitingOnSaleListAdapter.setEnableLoadMore(true);
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

    @OnClick({R.id.tv_fufeiting, R.id.bt_public_data, R.id.bt_select_shuaixuan, R.id.data_order, R.id.rl_shuaixuan, R.id.bt_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fufeiting:
            case R.id.bt_select:
                //福费廷，保理选择
                orderBy = "";
                showForfaiting();
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
//                startActivity(new Intent(getActivity(), MineOnSaleListFiltrateActivity.class));
//                DisPatcher.startFiltrateActivity(getActivity(), shareKey, 1);
                Intent intent = new Intent(getActivity(), FiltrateActivity.class);
                intent.putExtra("shareKey", shareKey);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                break;
        }
    }

    //保理
    private void showDataFactoringOrder() {
//         getLayoutInflater().inflate(R.layout.mine_list_data_order,null);
        View popupView_order = LayoutInflater.from(mContext).inflate(R.layout.mine_list_data_order, null);
//        View root = getLayoutInflater().inflate.inflate(R.layout.mine_list_data_order, null);
//        orderBy = "publish_time";
        ListView order_main_list = (ListView) popupView_order.findViewById(R.id.classify_mainlist);
        order_more_list = (ListView) popupView_order.findViewById(R.id.classify_morelist);
        mainAdapter = new DataOrderMainAdapter(getActivity(), mainFactoringList);
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
                    //发布时间
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
//                "maturity", "资产到期日"
//                "amount", "金额"
//                "transfer_rate","转让利率"
//                "publish_time" 发布时间

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
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_quote.showAsDropDown(btSelect, 0, 20);

        if ("asc".equals(ascDesc)) {
            moreAdapter.setSelectItem(0);
        } else if ("desc".equals(ascDesc)) {
            moreAdapter.setSelectItem(1);
        }
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

    private void showDataOrder() {

        View popupView_order = getActivity().getLayoutInflater().inflate(R.layout.mine_list_data_order, null);
//        orderBy = "publish_time";
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

    private void initAdapter(String[] array) {
        moreAdapter = new ClassifyMorAdapter(getActivity(), array);
        order_more_list.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    private void showForfaiting() {
        final View popupView = getActivity().getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_filter_list, textList);
        lsvMore.setAdapter(arrayAdapter);
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
//                    getForfaitingData();
                } else if (1 == position) {
                    //保理
                    assetsType = "2";
//                    getFactoringData();
                }
                shareKey = Constants.SPKEY.SP_MINE_ON_SALE_LIST_KEY + assetsType;
                btPublicData.setText(getString(R.string.filtrate_senddate));
                SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
                window_fufeiting.dismiss();
//                initQuoteAdapter(assetsType);
                setChangeAdapter(assetsType);
                refresh();
            }
        });
//        SharedPreferanceUtils.put(getActivity(), shareKey + "assetsType", assetsType);
    }


    /**
     * /**
     * //复核  福费廷 卖家复核驳回接口 复用MarketForfaitingOfferReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGCANCEL) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet(@Body RequestBody requestBody);
     * public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet(MarketForfaitingOfferReq data) {
     * Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet = mApi.reviewFactoringRejectRet(createRequestBody(data));
     * return reviewFactoringRejectRet;
     * }
     */
    private void reviewFactoringRejectRet(String id, String refuse) {
        marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(id);
        marketForfaitingOfferReq.setRefuseAdvice(refuse);
        ActionPresenter.getInstance().reviewFactoringRejectRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {

                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
//                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));
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

    ////复核  福费廷 卖家复核取消接口 资产状态108以前的取消  108以后用下面的取消
    private void reviewForfaitingCancelSellRet0(String operateButton, String advisetype) {
        //取消
        CansalResonReq quxiaoResonReq = new CansalResonReq();
        quxiaoResonReq.setId(assets_id);
        quxiaoResonReq.setOperateButton(operateButton);
        quxiaoResonReq.setAdvise(advisetype);
        ActionPresenter.getInstance().cansalRet(quxiaoResonReq).enqueue(new Callback<CanselRes>() {
            @Override
            public void onResponse(Call<CanselRes> call, Response<CanselRes> response) {

                if (response != null) {
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
     * /**
     * //复核  福费廷 卖家复核取消接口 返回复用BlockChainRes 请求复用BlockChainReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGCANCELSELL) Call<BlockChainRes> reviewForfaitingCancelSellRet(@Body RequestBody requestBody);
     * public Call<BlockChainRes> reviewForfaitingCancelSellRet(BlockChainReq data) {
     * Call<BlockChainRes> reviewForfaitingCancelSellRet = mApi.reviewForfaitingCancelSellRet(createRequestBody(data));
     * return reviewForfaitingCancelSellRet;
     * }
     */
    private void reviewForfaitingCancelSellRet(String id) {
        messageListReq = new MessageListReq();
        messageListReq.setId(id);
        ActionPresenter.getInstance().reviewForfaitingCancelSellRet(messageListReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
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
     * //复核  保理 卖家复核取消接口 返回复用BlockChainRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGCANCELSELL) Call<BlockChainRes> reviewFactoringCancelSellRet(@Body RequestBody requestBody);
     * public Call<BlockChainRes> reviewFactoringCancelSellRet(MessageListReq data) {
     * Call<BlockChainRes> reviewFactoringCancelSellRet = mApi.reviewFactoringCancelSellRet(createRequestBody(data));
     * return reviewFactoringCancelSellRet;
     * }
     */
    private void reviewFactoringCancelSellRet(String id) {
        messageListReq = new MessageListReq();
        messageListReq.setId(id);
        ActionPresenter.getInstance().reviewFactoringCancelSellRet(messageListReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
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
//提交让渡函

    /**
     * //资产卖家福费廷确认接口 提交让渡函
     *
     * @POST(Constants.NETPATH.REVIEWSUBMITLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     */
    private void reviewSubmitLetter() {
        ActionPresenter.getInstance().reviewSubmitLetterOnSaleListRet(reviewSubmitLetterReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {
                if (response != null && response.body() != null) {
//                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {

//                            ToastUtils.showShort(getString(R.string.toast_soldinassets_commit_success));
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
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    /**
     * /**
     * //资产卖家保理确认接口 提交让渡函  复用ReviewOfferSubmitLetterOnSaleListRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGSUBMITLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     * public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(ReviewSubmitLetterReq data) {
     * Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet = mApi.reviewFactoringSubmitLetterOnSaleListRet(createRequestBody(data));
     * return reviewFactoringSubmitLetterOnSaleListRet;
     * }
     */
    private void reviewFactoringSubmitLetterOnSaleListRet() {
        ActionPresenter.getInstance().reviewFactoringSubmitLetterOnSaleListRet(reviewSubmitLetterReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {
                if (response != null && response.body() != null) {
//                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {

//                            ToastUtils.showShort(getString(R.string.toast_soldinassets_commit_success));
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
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    /*
        弹窗选择文件、照相开始
         */
    public void bottomDialog() {
        final BottomDialog bottomDialog = BottomDialog.create(getFragmentManager());
        bottomDialog.setViewListener(new BottomDialog.ViewListener() {      // 可以进行一些必要对View的操作
            @Override
            public void bindView(View v) {
                // you can do bind view operation
                dialogAction(v, bottomDialog);
            }
        })
                .setLayoutRes(R.layout.activity_file_choice)
                .setDimAmount(0.3f)            // Dialog window 背景色深度 范围：0 到 1，默认是0.2f
                .setCancelOutside(true)     // 点击外部区域是否关闭，默认true
                .setTag("BottomDialog")     // 设置DialogFragment的tag
                .show();
    }

    private void dialogAction(View v, final BottomDialog bottomDialog) {
        TextView upload = (TextView) v.findViewById(R.id.activity_file_choice_upload);
        TextView photo = (TextView) v.findViewById(R.id.activity_file_choice_photo);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                EasyImage.openChooserWithDocuments(SoldInAssetsFragment.this,"让渡函", 0);
                EasyImage.openDocuments(SoldInAssetsFragment.this, 0);
//                EasyImage.openGallery(SoldInAssetsFragment.this, 0);
                bottomDialog.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openCamera(SoldInAssetsFragment.this, 0);
                bottomDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        MyLogger.pLog().i("requestCode0:" + requestCode);
//        MyLogger.pLog().i("88" + resultCode + "88");
        if (requestCode == 666 && resultCode == 666) {
            refresh();
        }
        if (requestCode == Activity.RESULT_FIRST_USER) {

            if (resultCode == RESULT_CANCELED && data != null) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("result");
                if ("300".equals(result)) {
//                    ToastUtils.showShort(getString(R.string.toast_soldinassets_send_success));

                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                    refresh();
                } else {
                    ToastUtils.showShort(getString(R.string.toast_review_offerletter_faild));

                }
            } else if (resultCode == RESULT_CANCELEDFILTER) {
//                ToastUtils.showShort("筛选");
                initFilterData();
                refresh();
            }
        } else {
            EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    //Some error handling 获取图片失败
                }

                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    //让渡函
                    if (actionCode == 1) {
                        MyLogger.pLog().i("1:" + imageFile.getPath());
                        uploadFile(imageFile, actionCode);
                    }
                }

                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    // Cancel handling, you might wanna remove taken photo if it was canceled
                    if (source == EasyImage.ImageSource.CAMERA) {
                        File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getActivity());
                        if (photoFile != null) photoFile.delete();
                    }
                }

            });
        }
//        actionCode = 0;
    }

    /*
    弹窗选择文件、照相结束
     */
    private void uploadFile(File file, final int type) {
//        isLoading(true);
        showWaitDialog();
        MyLogger.pLog().i("上传文件接口");
//        UploadFileReq uploadFileReq = new UploadFileReq();
//        uploadFileReq.setExtensionName(file.getName());
//        uploadFileReq.setFileSize(file.getTotalSpace()+"");
//        ActionPresenter.getInstance().uploadFileRet(null, file).enqueue(new Callback<UploadFileRes>() {
        ActionPresenter.getInstance().uploadFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
//                MyLogger.pLog().d("===上传文件成功！==");
//                isLoading(false);
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
//                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        MyLogger.pLog().i(response.body().getUrl());
                        ToastUtils.showShort(response.body().getMessage());
                        //让渡函 1福费廷 2保理
                        if (type == 1 && "1".equals(assetsType)) {
                            reviewSubmitLetterReq.setST0210(response.body().getUrl());
                            reviewSubmitLetter();
                        } else if (type == 1 && "2".equals(assetsType)) {
                            reviewSubmitLetterReq.setST0210(response.body().getUrl());
                            reviewFactoringSubmitLetterOnSaleListRet();
                        }
                        refresh();
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                } else {
                    MyLogger.pLog().e(response.message());
                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_failed));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                closeWaitDialog();
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    //提交让渡函结束
//市场 福费廷 保理 卖家 复核 确认撮合接口  保理没有邀约函
    public void confirmOfferData(final String id) {
        MyLogger.pLog().i("市场 福费廷 卖家 复核 确认撮合接口");
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(id);
        ActionPresenter.getInstance().reviewOfferSubmitOnSaleListRet(marketForfaitingOfferReq).enqueue(new Callback<ReviewOfferSubmitOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitOnSaleListRes> call, Response<ReviewOfferSubmitOnSaleListRes> response) {
//                MyLogger.pLog().d("=====" + response.body().toString());
//                MyLogger.pLog().d("=====" + response.body().getCode());
                if (response.body().getCode() == 300) {
                    if ("1".equals(assetsType)) {
                        //确认撮合成功！
                        reviewOfferSubmitOnSaleListRes = response.body();
//                        showLetter(id);
//                        DisPatcher.startReviewOfferLetterActivity(getActivity(), id, reviewOfferSubmitOnSaleListRes, 9);
                        Intent intent = new Intent(getActivity(), ReviewOfferLetterActivity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra("isSelect", id);
                        bundle.putSerializable("reviewOfferSubmitLetterOnSaleListReq", reviewOfferSubmitOnSaleListRes);
                        intent.putExtras(bundle);
//                        intent.putExtra("reviewOfferSubmitLetterOnSaleListReq", reviewOfferSubmitOnSaleListRes);
                        startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                    } else {
                        //回调数据到fragment
//                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));

                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        refresh();
                    }
                } else {
                    MyLogger.pLog().e(response.body().getMessage());
                    ToastUtils.showShort(response.body().getMessage());
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


    private void factoringOfferBohuiRet(String type, String advisetype) {
        AssertCommitReq assertCommitReq = new AssertCommitReq();
        assertCommitReq.setId(assets_id);
        assertCommitReq.setOperateButton(type);
        assertCommitReq.setAdvise(advisetype);
        ActionPresenter.getInstance().factoringOfferBohuiRet(assertCommitReq).enqueue(new Callback<BohuiResonRes>() {
            @Override
            public void onResponse(Call<BohuiResonRes> call, Response<BohuiResonRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        myDialogBohui.dismiss();
//                        ToastUtils.showShort(response.body().getMessage());

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void resetFiltrate() {
        //承兑金额范围
        SharedPreferanceUtils.put(getActivity(), shareKey + "scope_start", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "scope_end", "");
        //贴现率范围
        SharedPreferanceUtils.put(getActivity(), shareKey + "tiexianlv_start", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "tiexianlv_end", "");
        //开证行名称
        SharedPreferanceUtils.put(getActivity(), shareKey + "openfullname", "");
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
        SharedPreferanceUtils.put(getActivity(), shareKey + "country_enname", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "country_name", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "area_id", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "area_name", "");
        SharedPreferanceUtils.put(getActivity(), shareKey + "area_enname", "");
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
                    if ("2".equals(SureOrCancelDialogtype)) {
                        if ("104".equals(assetsState)) {
                            operateButton = "3";
                            reviewForfaitingCancelSellRet0(operateButton, "");
                        } else {
                            reviewForfaitingCancelSellRet(assets_id);
                        }
                    }
                } else if ("2".equals(assetsType)) {
                    if ("1".equals(SureOrCancelDialogtype)) {
                        if ("104".equals(assetsState)) {
                            operateButton = "3";
                            factoringOfferBohuiRet(operateButton, "");
                        } else {
                            reviewFactoringCancelSellRet(assets_id);
                        }
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
                        String path0 = getActivity().getExternalFilesDir(null) + File.separator + filename;
                        if (!FileUtils.isFileExist(path0)) {
                            downloadFileWithDynamicUrlSync(response.body().getData());
                        } else {
                            Intent intent = new Intent(getActivity(), PdfActivity.class);
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
//                        ToastUtils.showShort("完成");
                        Intent intent = new Intent(getActivity(), PdfActivity.class);
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

    //驳回原因
    private void showReviewRejectDialog(String title, String reason, String reason1, String reason2) {
        enterpriseRejectDialog = new EnterpriseRejectDialog(getActivity(), null, title, reason, reason1, reason2);
        enterpriseRejectDialog.show();
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
                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                                if (list.size() > 0) {
                                    showHandleOfferDetailDialog(assets_id, 0);
                                } else {
                                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_nodata));
                                }
                            }
                        } else if (response.body().getCode() == 415) {
                            MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                            MyLogger.pLog().e(response.body().getMessage());
                        } else {
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
                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                                if (list.size() > 0) {
                                    showReviewOfferDetailDialog(assets_id, assetsType);
                                } else {
                                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_nodata));
                                }
                            }
                        } else if (response.body().getCode() == 415) {
                            MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                            MyLogger.pLog().e(response.body().getMessage());
                        } else {
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
        final DownloadDialog downloadDialog = new DownloadDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {
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
