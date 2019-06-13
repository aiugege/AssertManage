package com.zjhl.pad.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.ReviewOfferSubmitLetterOnSaleListReq;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitOnSaleListRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.listener.OnBaseActivityListener;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DatePicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: ReviewOfferDetailDialog 发送要约函弹窗
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class ReviewOfferLetterActivity extends BaseActivity {
    @BindView(R.id.dialog_offer_letterdetail_iv_close)
    ImageView dialogOfferLetterdetailIvClose;
    @BindView(R.id.dialog_offer_letterdetail_iv_ss)
    ImageView dialogOfferLetterdetailIvSs;
    @BindView(R.id.dialog_offer_letterdetail_tv_number)
    TextView dialogOfferLetterdetailTvNumber;
    @BindView(R.id.dialog_offer_letterdetail_et_number)
    TextView dialogOfferLetterdetailEtNumber;
    @BindView(R.id.dialog_offer_letterdetail_title)
    LinearLayout dialogOfferLetterdetailTitle;
    @BindView(R.id.dialog_offer_letterdetail_et_title)
    TextView dialogOfferLetterdetailEtTitle;
    @BindView(R.id.dialog_offer_letterdetail_tv_for)
    TextView dialogOfferLetterdetailTvFor;
    @BindView(R.id.dialog_offer_letterdetail_et_for)
    TextView dialogOfferLetterdetailEtFor;
    @BindView(R.id.dialog_offer_letterdetail_for)
    LinearLayout dialogOfferLetterdetailFor;
    @BindView(R.id.dialog_offer_letterdetail_tv_to)
    TextView dialogOfferLetterdetailTvTo;
    @BindView(R.id.dialog_offer_letterdetail_et_to)
    TextView dialogOfferLetterdetailEtTo;
    @BindView(R.id.dialog_offer_letterdetail_to)
    LinearLayout dialogOfferLetterdetailTo;
    @BindView(R.id.dialog_offer_letterdetail_tv_date)
    TextView dialogOfferLetterdetailTvDate;
    @BindView(R.id.dialog_offer_letterdetail_tv_detail)
    TextView dialogOfferLetterdetailTvDetail;
    @BindView(R.id.dialog_offer_letterdetail_tv_lcnumber)
    TextView dialogOfferLetterdetailTvLcnumber;
    @BindView(R.id.dialog_offer_letterdetail_et_lcnumber)
    TextView dialogOfferLetterdetailEtLcnumber;
    @BindView(R.id.dialog_offer_letterdetail_lcnumber)
    LinearLayout dialogOfferLetterdetailLcnumber;
    @BindView(R.id.dialog_offer_letterdetail_tv_detail1)
    TextView dialogOfferLetterdetailTvDetail1;
    @BindView(R.id.dialog_offer_letterdetail_tv_iussingbankname)
    TextView dialogOfferLetterdetailTvIussingbankname;
    @BindView(R.id.dialog_offer_letterdetail_tv2_iussingbankname)
    TextView dialogOfferLetterdetailTv2Iussingbankname;
    @BindView(R.id.dialog_offer_letterdetail_ll_iussingbankname)
    LinearLayout dialogOfferLetterdetailLlIussingbankname;
    @BindView(R.id.dialog_offer_letterdetail_tv_acceptingbankname)
    TextView dialogOfferLetterdetailTvAcceptingbankname;
    @BindView(R.id.dialog_offer_letterdetail_tv2_acceptingbankname)
    TextView dialogOfferLetterdetailTv2Acceptingbankname;
    @BindView(R.id.dialog_offer_letterdetail_ll_acceptingbankname)
    LinearLayout dialogOfferLetterdetailLlAcceptingbankname;
    @BindView(R.id.dialog_offer_letterdetail_tv_credittype)
    TextView dialogOfferLetterdetailTvCredittype;
    @BindView(R.id.dialog_offer_letterdetail_tv2_credittype)
    TextView dialogOfferLetterdetailTv2Credittype;
    @BindView(R.id.dialog_offer_letterdetail_ll_credittype)
    LinearLayout dialogOfferLetterdetailLlCredittype;
    @BindView(R.id.dialog_offer_letterdetail_tv_creditnumber)
    TextView dialogOfferLetterdetailTvCreditnumber;
    @BindView(R.id.dialog_offer_letterdetail_tv2_creditnumber)
    TextView dialogOfferLetterdetailTv2Creditnumber;
    @BindView(R.id.dialog_offer_letterdetail_ll_creditnumber)
    LinearLayout dialogOfferLetterdetailLlCreditnumber;
    @BindView(R.id.dialog_offer_letterdetail_tv_lc)
    TextView dialogOfferLetterdetailTvLc;
    @BindView(R.id.dialog_offer_letterdetail_tv2_lc)
    EditText dialogOfferLetterdetailTv2Lc;
    @BindView(R.id.dialog_offer_letterdetail_ll_lc)
    LinearLayout dialogOfferLetterdetailLlLc;
    @BindView(R.id.dialog_offer_letterdetail_tv_lcproposer)
    TextView dialogOfferLetterdetailTvLcproposer;
    @BindView(R.id.dialog_offer_letterdetail_tv2_lcproposer)
    EditText dialogOfferLetterdetailTv2Lcproposer;
    @BindView(R.id.dialog_offer_letterdetail_ll_lcproposer)
    LinearLayout dialogOfferLetterdetailLlLcproposer;
    @BindView(R.id.dialog_offer_letterdetail_tv_basicTransaction)
    TextView dialogOfferLetterdetailTvBasicTransaction;
    @BindView(R.id.dialog_offer_letterdetail_tv2_basicTransaction)
    EditText dialogOfferLetterdetailTv2BasicTransaction;
    @BindView(R.id.dialog_offer_letterdetail_ll_basicTransaction)
    LinearLayout dialogOfferLetterdetailLlBasicTransaction;
    @BindView(R.id.dialog_offer_letterdetail_tv_interestRate)
    TextView dialogOfferLetterdetailTvInterestRate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_interestRate)
    EditText dialogOfferLetterdetailTv2InterestRate;
    @BindView(R.id.dialog_offer_letterdetail_ll_interestRate)
    LinearLayout dialogOfferLetterdetailLlInterestRate;
    @BindView(R.id.dialog_offer_letterdetail_tv_fee)
    TextView dialogOfferLetterdetailTvFee;
    @BindView(R.id.dialog_offer_letterdetail_tv2_fee)
    EditText dialogOfferLetterdetailTv2Fee;
    @BindView(R.id.dialog_offer_letterdetail_ll_fee)
    LinearLayout dialogOfferLetterdetailLlFee;
    @BindView(R.id.dialog_offer_letterdetail_tv_invoiceAmount)
    TextView dialogOfferLetterdetailTvInvoiceAmount;
    @BindView(R.id.dialog_offer_letterdetail_tv2_invoiceAmount)
    EditText dialogOfferLetterdetailTv2InvoiceAmount;
    @BindView(R.id.dialog_offer_letterdetail_ll_invoiceAmount)
    LinearLayout dialogOfferLetterdetailLlInvoiceAmount;
    @BindView(R.id.dialog_offer_letterdetail_tv_grace)
    TextView dialogOfferLetterdetailTvGrace;
    @BindView(R.id.dialog_offer_letterdetail_tv2_grace)
    EditText dialogOfferLetterdetailTv2Grace;
    @BindView(R.id.dialog_offer_letterdetail_ll_grace)
    LinearLayout dialogOfferLetterdetailLlGrace;
    @BindView(R.id.dialog_offer_letterdetail_tv_discountPeriodDay)
    TextView dialogOfferLetterdetailTvDiscountPeriodDay;
    @BindView(R.id.dialog_offer_letterdetail_tv2_discountPeriodDay)
    EditText dialogOfferLetterdetailTv2DiscountPeriodDay;
    @BindView(R.id.dialog_offer_letterdetail_ll_discountPeriodDay)
    LinearLayout dialogOfferLetterdetailLlDiscountPeriodDay;
    @BindView(R.id.dialog_offer_letterdetail_tv_discountPeriodStart)
    TextView dialogOfferLetterdetailTvDiscountPeriodStart;
    @BindView(R.id.dialog_offer_letterdetail_tv2_discountPeriodStart)
    TextView dialogOfferLetterdetailTv2DiscountPeriodStart;
    @BindView(R.id.dialog_offer_letterdetail_ll_discountPeriodStart)
    LinearLayout dialogOfferLetterdetailLlDiscountPeriodStart;
    @BindView(R.id.dialog_offer_letterdetail_tv_discountAcceptDate)
    TextView dialogOfferLetterdetailTvDiscountAcceptDate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_discountAcceptDate)
    TextView dialogOfferLetterdetailTv2DiscountAcceptDate;
    @BindView(R.id.dialog_offer_letterdetail_ll_discountAcceptDate)
    LinearLayout dialogOfferLetterdetailLlDiscountAcceptDate;
    @BindView(R.id.dialog_offer_letterdetail_tv_discountPeriodWork)
    TextView dialogOfferLetterdetailTvDiscountPeriodWork;
    @BindView(R.id.dialog_offer_letterdetail_tv2_discountPeriodWork)
    EditText dialogOfferLetterdetailTv2DiscountPeriodWork;
    @BindView(R.id.dialog_offer_letterdetail_ll_discountPeriodWork)
    LinearLayout dialogOfferLetterdetailLlDiscountPeriodWork;
    @BindView(R.id.dialog_offer_letterdetail_tv_bankCost)
    TextView dialogOfferLetterdetailTvBankCost;
    @BindView(R.id.dialog_offer_letterdetail_tv2_bankCost)
    EditText dialogOfferLetterdetailTv2BankCost;
    @BindView(R.id.dialog_offer_letterdetail_ll_bankCost)
    LinearLayout dialogOfferLetterdetailLlBankCost;
    @BindView(R.id.dialog_offer_letterdetail_tv_preCharging)
    TextView dialogOfferLetterdetailTvPreCharging;
    @BindView(R.id.dialog_offer_letterdetail_tv2_preCharging)
    EditText dialogOfferLetterdetailTv2PreCharging;
    @BindView(R.id.dialog_offer_letterdetail_ll_preCharging)
    LinearLayout dialogOfferLetterdetailLlPreCharging;
    @BindView(R.id.dialog_offer_letterdetail_tv_deliveryDate)
    TextView dialogOfferLetterdetailTvDeliveryDate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_deliveryDate)
    TextView dialogOfferLetterdetailTv2DeliveryDate;
    @BindView(R.id.dialog_offer_letterdetail_ll_deliveryDate)
    LinearLayout dialogOfferLetterdetailLlDeliveryDate;
    @BindView(R.id.dialog_offer_letterdetail_tv_payBillDay)
    TextView dialogOfferLetterdetailTvPayBillDay;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payBillDay)
    EditText dialogOfferLetterdetailTv2PayBillDay;
    @BindView(R.id.dialog_offer_letterdetail_ll_payBillDay)
    LinearLayout dialogOfferLetterdetailLlPayBillDay;
    @BindView(R.id.dialog_offer_letterdetail_tv_payAccount)
    TextView dialogOfferLetterdetailTvPayAccount;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payAccount)
    EditText dialogOfferLetterdetailTv2PayAccount;
    @BindView(R.id.dialog_offer_letterdetail_ll_payAccount)
    LinearLayout dialogOfferLetterdetailLlPayAccount;
    @BindView(R.id.dialog_offer_letterdetail_tv_payAccountBank)
    TextView dialogOfferLetterdetailTvPayAccountBank;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payAccountBank)
    EditText dialogOfferLetterdetailTv2PayAccountBank;
    @BindView(R.id.dialog_offer_letterdetail_ll_payAccountBank)
    LinearLayout dialogOfferLetterdetailLlPayAccountBank;
    @BindView(R.id.dialog_offer_letterdetail_tv_payAccountApproach)
    TextView dialogOfferLetterdetailTvPayAccountApproach;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payAccountApproach)
    EditText dialogOfferLetterdetailTv2PayAccountApproach;
    @BindView(R.id.dialog_offer_letterdetail_ll_payAccountApproach)
    LinearLayout dialogOfferLetterdetailLlPayAccountApproach;
    @BindView(R.id.dialog_offer_letterdetail_tv_pollicitaBackDate)
    TextView dialogOfferLetterdetailTvPollicitaBackDate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_pollicitaBackDate)
    TextView dialogOfferLetterdetailTv2PollicitaBackDate;
    @BindView(R.id.dialog_offer_letterdetail_ll_pollicitaBackDate)
    LinearLayout dialogOfferLetterdetailLlPollicitaBackDate;
    @BindView(R.id.dialog_offer_letterdetail_tv_pollicitaBackEndDate)
    TextView dialogOfferLetterdetailTvPollicitaBackEndDate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_pollicitaBackEndDate)
    TextView dialogOfferLetterdetailTv2PollicitaBackEndDate;
    @BindView(R.id.dialog_offer_letterdetail_ll_pollicitaBackEndDate)
    LinearLayout dialogOfferLetterdetailLlPollicitaBackEndDate;
    @BindView(R.id.dialog_offer_letterdetail_tv_specialAgreement)
    TextView dialogOfferLetterdetailTvSpecialAgreement;
    @BindView(R.id.dialog_offer_letterdetail_tv2_specialAgreement)
    EditText dialogOfferLetterdetailTv2SpecialAgreement;
    @BindView(R.id.dialog_offer_letterdetail_ll_specialAgreement)
    LinearLayout dialogOfferLetterdetailLlSpecialAgreement;
    @BindView(R.id.dialog_offer_letterdetail_tv_signPerson)
    TextView dialogOfferLetterdetailTvSignPerson;
    @BindView(R.id.dialog_offer_letterdetail_tv2_signPerson)
    EditText dialogOfferLetterdetailTv2SignPerson;
    @BindView(R.id.dialog_offer_letterdetail_ll_signPerson)
    LinearLayout dialogOfferLetterdetailLlSignPerson;
    @BindView(R.id.dialog_offer_letterdetail_ll)
    LinearLayout dialogOfferLetterdetailLl;
    @BindView(R.id.dialog_offer_letterdetail_tv_acceptdate)
    TextView dialogOfferLetterdetailTvAcceptdate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_acceptdate)
    TextView dialogOfferLetterdetailTv2Acceptdate;
    @BindView(R.id.dialog_offer_letterdetail_ll_acceptdate)
    LinearLayout dialogOfferLetterdetailLlAcceptdate;
    //    @BindView(R.id.dialog_offer_letterdetail_tv_sure)
//    TextView dialogOfferLetterdetailTvSure;
    @BindView(R.id.dialog_offer_letterdetail_ll_conent)
    LinearLayout dialogOfferLetterdetailLlConent;

    ReviewOfferSubmitLetterOnSaleListReq reviewOfferSubmitLetterOnSaleListReq;
    @BindView(R.id.dialog_offer_letterdetail_tv_sure)
    TextView dialogOfferLetterdetailTvSure;
    private String isSelect = "";//报价id
    public ReviewOfferSubmitOnSaleListRes reviewOfferSubmitOnSaleListRes;//提交成功返回数据 用于展示邀约函
    String responsecode = "";

    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_offer_letter_detail);
        ButterKnife.bind(this);
        dialogOfferLetterdetailTv2Lcproposer.setFocusable(true);
        dialogOfferLetterdetailTv2Lcproposer.setFocusableInTouchMode(true);
        dialogOfferLetterdetailTv2Lcproposer.requestFocus();
        isSelect = getIntent().getStringExtra("isSelect");
        reviewOfferSubmitOnSaleListRes = (ReviewOfferSubmitOnSaleListRes) getIntent().getSerializableExtra("reviewOfferSubmitLetterOnSaleListReq");
        setData(reviewOfferSubmitOnSaleListRes);
    }

    public void setData(ReviewOfferSubmitOnSaleListRes reviewOfferSubmitOnSaleListRes) {
        if (reviewOfferSubmitOnSaleListRes != null && reviewOfferSubmitOnSaleListRes.getData() != null) {
            dialogOfferLetterdetailEtNumber.setText(reviewOfferSubmitOnSaleListRes.getData().getPollicitaNo());
            dialogOfferLetterdetailEtFor.setText(reviewOfferSubmitOnSaleListRes.getData().getbOrgName());
            dialogOfferLetterdetailEtTo.setText(reviewOfferSubmitOnSaleListRes.getData().getsOrgName());
            dialogOfferLetterdetailEtLcnumber.setText(reviewOfferSubmitOnSaleListRes.getData().getBussNo());
            dialogOfferLetterdetailTv2Iussingbankname.setText(reviewOfferSubmitOnSaleListRes.getData().getOpenFullName());
            dialogOfferLetterdetailTv2Acceptingbankname.setText(reviewOfferSubmitOnSaleListRes.getData().getTenderFullName());
            dialogOfferLetterdetailTv2Creditnumber.setText(reviewOfferSubmitOnSaleListRes.getData().getLcNo());
            dialogOfferLetterdetailTvDate.setText(reviewOfferSubmitOnSaleListRes.getData().getSignDate());
            if ("1".equals(reviewOfferSubmitOnSaleListRes.getData().getDebtType())) {
                dialogOfferLetterdetailTv2Credittype.setText(getResources().getString(R.string.market_forfaiting_type1));/** 债权类型（信用证类型）：1 国内信用证、2 国际信用证  必输*/
            } else if ("2".equals(reviewOfferSubmitOnSaleListRes.getData().getDebtType())) {
                dialogOfferLetterdetailTv2Credittype.setText(getResources().getString(R.string.market_forfaiting_type2));/** 债权类型（信用证类型）：1 国内信用证、2 国际信用证  必输*/
            }
        }
    }


    //我的资产 在售资产资产卖家要约函发送，并确认提交接口
    public void confirmOfferData(ReviewOfferSubmitLetterOnSaleListReq reviewOfferSubmitLetterOnSaleListReq) {
        showWaitDialog();
        MyLogger.pLog().i("我的资产 在售资产资产卖家要约函发送，并确认提交接口");
        ActionPresenter.getInstance().reviewOfferSubmitLetterOnSaleListRet(reviewOfferSubmitLetterOnSaleListReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {
                if (response != null && response.body() != null) {
//                    MyLogger.pLog().d("=====" + response.body().toString());
//                    MyLogger.pLog().d("=====" + response.body().getCode());
                    Bundle bundle = new Bundle();

                    closeWaitDialog();
                    if (response.body().getCode() == 300) {
                        //确认撮合成功！
                        //回调数据到fragment
//                    ToastUtils.showShort("提交成功");
                        bundle.putString("result", "300");

                        setResult(RESULT_CANCELED, getIntent().putExtras(bundle));
                        finish();
                    } else {
                        bundle.putString("result", "" + response.body().getMessage());
                        MyLogger.pLog().e(response.body().getMessage());
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_review_offerletter_faild));
                    }
                } else {
                    MyLogger.pLog().e(response.body().getMessage());
//                        ToastUtils.showShort(response.body().getMessage());
                    ToastUtils.showShort(getString(R.string.toast_review_offerletter_faild));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
                closeWaitDialog();
                finish();
            }
        });
    }


    //设置数据
    public ReviewOfferSubmitLetterOnSaleListReq setDate() {
        reviewOfferSubmitLetterOnSaleListReq = new ReviewOfferSubmitLetterOnSaleListReq();
        reviewOfferSubmitLetterOnSaleListReq.setId(isSelect);
        reviewOfferSubmitLetterOnSaleListReq.setCreditApp(dialogOfferLetterdetailTv2Lcproposer.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setCreditBeneficiary(dialogOfferLetterdetailTv2Lc.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setBasicTransaction(dialogOfferLetterdetailTv2BasicTransaction.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setInterestRate(dialogOfferLetterdetailTv2InterestRate.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setFee(dialogOfferLetterdetailTv2Fee.getText().toString());
        //承兑到期日
        reviewOfferSubmitLetterOnSaleListReq.setAcceptEndDate(dialogOfferLetterdetailTv2Acceptdate.getText().toString());

        reviewOfferSubmitLetterOnSaleListReq.setInvoiceAmount(dialogOfferLetterdetailTv2InvoiceAmount.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setGrace(dialogOfferLetterdetailTv2Grace.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setDiscountPeriodDay(dialogOfferLetterdetailTv2DiscountPeriodDay.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setDiscountPeriodStart(dialogOfferLetterdetailTv2DiscountPeriodStart.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setDiscountAcceptDate(dialogOfferLetterdetailTv2DiscountAcceptDate.getText().toString());

        reviewOfferSubmitLetterOnSaleListReq.setDiscountPeriodWork(dialogOfferLetterdetailTv2DiscountPeriodWork.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setBankCost(dialogOfferLetterdetailTv2BankCost.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setPreCharging(dialogOfferLetterdetailTv2PreCharging.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setDeliveryDate(dialogOfferLetterdetailTv2DeliveryDate.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setPayBillDay(dialogOfferLetterdetailTv2PayBillDay.getText().toString());

        reviewOfferSubmitLetterOnSaleListReq.setPayAccount(dialogOfferLetterdetailTv2PayAccount.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setPayAccountBank(dialogOfferLetterdetailTv2PayAccountBank.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setPayAccountApproach(dialogOfferLetterdetailTv2PayAccountApproach.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setPollicitaBackDate(dialogOfferLetterdetailTv2PollicitaBackDate.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setPollicitaBackEndDate(dialogOfferLetterdetailTv2PollicitaBackEndDate.getText().toString());

        reviewOfferSubmitLetterOnSaleListReq.setSpecialAgreement(dialogOfferLetterdetailTv2SpecialAgreement.getText().toString());
        reviewOfferSubmitLetterOnSaleListReq.setSignPerson(dialogOfferLetterdetailTv2SignPerson.getText().toString());

        return reviewOfferSubmitLetterOnSaleListReq;
    }

    //校验数据
    public boolean valideData() {
        boolean res = true;
        if (StringUtils.isEmpty(dialogOfferLetterdetailTv2Lcproposer.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_shenqingren));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2Lc.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_shouyiren));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2BasicTransaction.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_maoyipinming));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2InvoiceAmount.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_fapiao));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2InterestRate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_jiexifangshi));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2DiscountPeriodDay.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_tiexianqi));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2DiscountPeriodWork.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_tianshu));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2PayAccount.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_zhanghao));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2PayAccountBank.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_kaihuhang));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2PayAccountApproach.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_fukuanlujing));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2SignPerson.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_qianzi));
            res = false;
        } else if (StringUtils.isEmpty(dialogOfferLetterdetailTv2DiscountPeriodStart.getText().toString()) || StringUtils.isEmpty(dialogOfferLetterdetailTv2Acceptdate.getText().toString())
                || StringUtils.isEmpty(dialogOfferLetterdetailTv2DiscountAcceptDate.getText().toString()) || StringUtils.isEmpty(dialogOfferLetterdetailTv2DeliveryDate.getText().toString())
                || StringUtils.isEmpty(dialogOfferLetterdetailTv2PollicitaBackDate.getText().toString()) || StringUtils.isEmpty(dialogOfferLetterdetailTv2PollicitaBackEndDate.getText().toString())) {
            //承兑到期日、买入行付款日、开证行承兑付款到期日、 单据交付、邀约日期、时效日期等为空
            ToastUtils.showShort(getString(R.string.toast_offer_letterdetail_riqi));
            res = false;
        }
//        reviewOfferSubmitLetterOnSaleListReq = new ReviewOfferSubmitLetterOnSaleListReq();
//        reviewOfferSubmitLetterOnSaleListReq.setId(isSelect);
//        reviewOfferSubmitLetterOnSaleListReq.setCreditApp(dialogOfferLetterdetailTv2Lcproposer.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setCreditBeneficiary(dialogOfferLetterdetailTv2Lc.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setBasicTransaction(dialogOfferLetterdetailTv2BasicTransaction.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setInterestRate(dialogOfferLetterdetailTv2InterestRate.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setFee(dialogOfferLetterdetailTv2Fee.getText().toString());
//        //承兑到期日
//        reviewOfferSubmitLetterOnSaleListReq.setAcceptEndDate(dialogOfferLetterdetailTv2Acceptdate.getText().toString());
//
//        reviewOfferSubmitLetterOnSaleListReq.setInvoiceAmount(dialogOfferLetterdetailTv2InvoiceAmount.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setGrace(dialogOfferLetterdetailTv2Grace.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setDiscountPeriodDay(dialogOfferLetterdetailTv2DiscountPeriodDay.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setDiscountPeriodStart(dialogOfferLetterdetailTv2DiscountPeriodStart.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setDiscountAcceptDate(dialogOfferLetterdetailTv2DiscountAcceptDate.getText().toString());
//
//        reviewOfferSubmitLetterOnSaleListReq.setDiscountPeriodWork(dialogOfferLetterdetailTv2DiscountPeriodWork.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setBankCost(dialogOfferLetterdetailTv2BankCost.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setPreCharging(dialogOfferLetterdetailTv2PreCharging.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setDeliveryDate(dialogOfferLetterdetailTv2DeliveryDate.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setPayBillDay(dialogOfferLetterdetailTv2PayBillDay.getText().toString());
//
//        reviewOfferSubmitLetterOnSaleListReq.setPayAccount(dialogOfferLetterdetailTv2PayAccount.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setPayAccountBank(dialogOfferLetterdetailTv2PayAccount.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setPayAccountApproach(dialogOfferLetterdetailTv2PayAccountApproach.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setPollicitaBackDate(dialogOfferLetterdetailTv2PollicitaBackDate.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setPollicitaBackEndDate(dialogOfferLetterdetailTv2PollicitaBackEndDate.getText().toString());
//
//        reviewOfferSubmitLetterOnSaleListReq.setSpecialAgreement(dialogOfferLetterdetailTv2SpecialAgreement.getText().toString());
//        reviewOfferSubmitLetterOnSaleListReq.setSignPerson(dialogOfferLetterdetailTv2SignPerson.getText().toString());

        return res;
    }

    @OnClick({R.id.dialog_offer_letterdetail_tv_date, R.id.dialog_offer_letterdetail_tv_sure, R.id.dialog_offer_letterdetail_iv_close, R.id.dialog_offer_letterdetail_tv2_discountPeriodStart, R.id.dialog_offer_letterdetail_tv_discountPeriodDay, R.id.dialog_offer_letterdetail_tv2_discountPeriodDay, R.id.dialog_offer_letterdetail_ll_discountPeriodDay, R.id.dialog_offer_letterdetail_tv_acceptdate, R.id.dialog_offer_letterdetail_tv2_acceptdate, R.id.dialog_offer_letterdetail_ll_acceptdate, R.id.dialog_offer_letterdetail_tv_discountAcceptDate, R.id.dialog_offer_letterdetail_tv2_discountAcceptDate, R.id.dialog_offer_letterdetail_ll_discountAcceptDate, R.id.dialog_offer_letterdetail_tv_deliveryDate, R.id.dialog_offer_letterdetail_tv2_deliveryDate, R.id.dialog_offer_letterdetail_ll_deliveryDate, R.id.dialog_offer_letterdetail_tv_payBillDay, R.id.dialog_offer_letterdetail_tv2_payBillDay, R.id.dialog_offer_letterdetail_ll_payBillDay, R.id.dialog_offer_letterdetail_tv_pollicitaBackDate, R.id.dialog_offer_letterdetail_tv2_pollicitaBackDate, R.id.dialog_offer_letterdetail_ll_pollicitaBackDate, R.id.dialog_offer_letterdetail_tv_pollicitaBackEndDate, R.id.dialog_offer_letterdetail_tv2_pollicitaBackEndDate, R.id.dialog_offer_letterdetail_ll_pollicitaBackEndDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_offer_letterdetail_tv2_discountPeriodStart:
                onYearMonthDayPicker(dialogOfferLetterdetailTv2DiscountPeriodStart);
                break;
            case R.id.dialog_offer_letterdetail_iv_close:
//                initSureOrCancelDialogView("2",getString(R.string.toast_forfaiting_sell_cancelhint));
                finish();
                break;
            case R.id.dialog_offer_letterdetail_tv_date:
                break;
            case R.id.dialog_offer_letterdetail_tv_sure:
                initSureOrCancelDialogView("1", getString(R.string.toast_forfaiting_sell_send));
              /*  if(valideData()) {
                    confirmOfferData(setDate());
                }*/
//                MyLogger.pLog().e("点击事件");
//                Bundle bundle = new Bundle();
//                bundle.putString("result", "300");
//                setResult(RESULT_CANCELED, getIntent().putExtras(bundle));
//                finish();
                break;
            case R.id.dialog_offer_letterdetail_tv_discountPeriodDay:
                break;
            case R.id.dialog_offer_letterdetail_tv2_discountPeriodDay:
                break;
            case R.id.dialog_offer_letterdetail_ll_discountPeriodDay:
                break;
            case R.id.dialog_offer_letterdetail_tv_acceptdate:
                break;
            case R.id.dialog_offer_letterdetail_tv2_acceptdate:
                onYearMonthDayPicker(dialogOfferLetterdetailTv2Acceptdate);
                break;
            case R.id.dialog_offer_letterdetail_ll_acceptdate:
                break;
            case R.id.dialog_offer_letterdetail_tv_discountAcceptDate:
                break;
            case R.id.dialog_offer_letterdetail_tv2_discountAcceptDate:

                onYearMonthDayPicker(dialogOfferLetterdetailTv2DiscountAcceptDate);
                break;
            case R.id.dialog_offer_letterdetail_ll_discountAcceptDate:
                break;
            case R.id.dialog_offer_letterdetail_tv_deliveryDate:
                break;
            case R.id.dialog_offer_letterdetail_tv2_deliveryDate:

                onYearMonthDayPicker(dialogOfferLetterdetailTv2DeliveryDate);
                break;
            case R.id.dialog_offer_letterdetail_ll_deliveryDate:
                break;
            case R.id.dialog_offer_letterdetail_tv_payBillDay:
                break;
            case R.id.dialog_offer_letterdetail_tv2_payBillDay:
                break;
            case R.id.dialog_offer_letterdetail_ll_payBillDay:
                break;
            case R.id.dialog_offer_letterdetail_tv_pollicitaBackDate:
                break;
            case R.id.dialog_offer_letterdetail_tv2_pollicitaBackDate:

                onYearMonthDayPicker(dialogOfferLetterdetailTv2PollicitaBackDate);
                break;
            case R.id.dialog_offer_letterdetail_ll_pollicitaBackDate:
                break;
            case R.id.dialog_offer_letterdetail_tv_pollicitaBackEndDate:
                break;
            case R.id.dialog_offer_letterdetail_tv2_pollicitaBackEndDate:

                onYearMonthDayPicker(dialogOfferLetterdetailTv2PollicitaBackEndDate);
                break;
            case R.id.dialog_offer_letterdetail_ll_pollicitaBackEndDate:
                break;
        }
    }


    //年月日
    public void onYearMonthDayPicker(final View view) {
        final String[] DateString = new String[1];
        final DatePicker picker = new DatePicker(ReviewOfferLetterActivity.this);
        picker.setCanLoop(true);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1970, 1, 1);
        picker.setRangeEnd(2111, 1, 11);
        picker.setCancelText("");
        picker.setSubmitText("");
        picker.setsubmitTextIcon(R.drawable.submit_icon);
        picker.setCancelTextIcon(R.drawable.cancel_icon);
        LineConfig config = new LineConfig();
        config.setThick(0.2f);
        picker.setLineConfig(config);
        picker.setSelectedTextColor(getResources().getColor(R.color.dark));
        picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
        picker.setTitleText(getString(R.string.issue_forfaiting_picker_date));
        picker.setTitleTextSize(18);
        picker.setTopLineColor(getResources().getColor(R.color.white));
        picker.setSelectedItem(Integer.valueOf(TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_YEAR)), Integer.valueOf(TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_MONTH)), Integer.valueOf(TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_DAY)));
        picker.setWeightEnable(true);
        picker.setLineColor(Color.BLACK);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
//                ToastUtils.showShort(year + "-" + month + "-" + day);
//                releaseForfaitingsellNextEtCreditissuringdate.setText(year + "-" + month + "-" + day);
//                DateString[0] = year + "-" + month + "-" + day;
                if (view instanceof TextView) {
                    ((TextView) view).setText(year + "-" + month + "-" + day);
                }

            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
//                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
//                release_forfaitingsell_next_et_creditissuringdate
//                releaseForfaitingsellNextEtCreditissuringdate.setText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
//                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
//                releaseForfaitingsellNextEtCreditissuringdate.setText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
//                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
//                releaseForfaitingsellNextEtCreditissuringdate.setText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
//        picker.set
        picker.show();
    }

    //二次确认框
    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = getString(R.string.issue_forfaiting_sell_yesorno);
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(this, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                if ("1".equals(SureOrCancelDialogtype)) {
                    if (valideData()) {
                        confirmOfferData(setDate());
                    }
                } else if ("2".equals(SureOrCancelDialogtype)) {
                    finish();
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

}
