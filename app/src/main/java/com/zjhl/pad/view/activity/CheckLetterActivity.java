package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.req.ReviewOfferSubmitLetterOnSaleListReq;
import com.zjhl.pad.moudle.entity.res.CheckLetterRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitOnSaleListRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: ReviewOfferDetailDialog 查看要约函弹窗
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class CheckLetterActivity extends BaseActivity {
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
    TextView dialogOfferLetterdetailTv2Lc;
    @BindView(R.id.dialog_offer_letterdetail_ll_lc)
    LinearLayout dialogOfferLetterdetailLlLc;
    @BindView(R.id.dialog_offer_letterdetail_tv_lcproposer)
    TextView dialogOfferLetterdetailTvLcproposer;
    @BindView(R.id.dialog_offer_letterdetail_tv2_lcproposer)
    TextView dialogOfferLetterdetailTv2Lcproposer;
    @BindView(R.id.dialog_offer_letterdetail_ll_lcproposer)
    LinearLayout dialogOfferLetterdetailLlLcproposer;
    @BindView(R.id.dialog_offer_letterdetail_tv_basicTransaction)
    TextView dialogOfferLetterdetailTvBasicTransaction;
    @BindView(R.id.dialog_offer_letterdetail_tv2_basicTransaction)
    TextView dialogOfferLetterdetailTv2BasicTransaction;
    @BindView(R.id.dialog_offer_letterdetail_ll_basicTransaction)
    LinearLayout dialogOfferLetterdetailLlBasicTransaction;
    @BindView(R.id.dialog_offer_letterdetail_tv_interestRate)
    TextView dialogOfferLetterdetailTvInterestRate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_interestRate)
    TextView dialogOfferLetterdetailTv2InterestRate;
    @BindView(R.id.dialog_offer_letterdetail_ll_interestRate)
    LinearLayout dialogOfferLetterdetailLlInterestRate;
    @BindView(R.id.dialog_offer_letterdetail_tv_fee)
    TextView dialogOfferLetterdetailTvFee;
    @BindView(R.id.dialog_offer_letterdetail_tv2_fee)
    TextView dialogOfferLetterdetailTv2Fee;
    @BindView(R.id.dialog_offer_letterdetail_ll_fee)
    LinearLayout dialogOfferLetterdetailLlFee;
    @BindView(R.id.dialog_offer_letterdetail_tv_invoiceAmount)
    TextView dialogOfferLetterdetailTvInvoiceAmount;
    @BindView(R.id.dialog_offer_letterdetail_tv2_invoiceAmount)
    TextView dialogOfferLetterdetailTv2InvoiceAmount;
    @BindView(R.id.dialog_offer_letterdetail_ll_invoiceAmount)
    LinearLayout dialogOfferLetterdetailLlInvoiceAmount;
    @BindView(R.id.dialog_offer_letterdetail_tv_grace)
    TextView dialogOfferLetterdetailTvGrace;
    @BindView(R.id.dialog_offer_letterdetail_tv2_grace)
    TextView dialogOfferLetterdetailTv2Grace;
    @BindView(R.id.dialog_offer_letterdetail_ll_grace)
    LinearLayout dialogOfferLetterdetailLlGrace;
    @BindView(R.id.dialog_offer_letterdetail_tv_discountPeriodDay)
    TextView dialogOfferLetterdetailTvDiscountPeriodDay;
    @BindView(R.id.dialog_offer_letterdetail_tv2_discountPeriodDay)
    TextView dialogOfferLetterdetailTv2DiscountPeriodDay;
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
    TextView dialogOfferLetterdetailTv2DiscountPeriodWork;
    @BindView(R.id.dialog_offer_letterdetail_ll_discountPeriodWork)
    LinearLayout dialogOfferLetterdetailLlDiscountPeriodWork;
    @BindView(R.id.dialog_offer_letterdetail_tv_bankCost)
    TextView dialogOfferLetterdetailTvBankCost;
    @BindView(R.id.dialog_offer_letterdetail_tv2_bankCost)
    TextView dialogOfferLetterdetailTv2BankCost;
    @BindView(R.id.dialog_offer_letterdetail_ll_bankCost)
    LinearLayout dialogOfferLetterdetailLlBankCost;
    @BindView(R.id.dialog_offer_letterdetail_tv_preCharging)
    TextView dialogOfferLetterdetailTvPreCharging;
    @BindView(R.id.dialog_offer_letterdetail_tv2_preCharging)
    TextView dialogOfferLetterdetailTv2PreCharging;
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
    TextView dialogOfferLetterdetailTv2PayBillDay;
    @BindView(R.id.dialog_offer_letterdetail_ll_payBillDay)
    LinearLayout dialogOfferLetterdetailLlPayBillDay;
    @BindView(R.id.dialog_offer_letterdetail_tv_payAccount)
    TextView dialogOfferLetterdetailTvPayAccount;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payAccount)
    TextView dialogOfferLetterdetailTv2PayAccount;
    @BindView(R.id.dialog_offer_letterdetail_ll_payAccount)
    LinearLayout dialogOfferLetterdetailLlPayAccount;
    @BindView(R.id.dialog_offer_letterdetail_tv_payAccountBank)
    TextView dialogOfferLetterdetailTvPayAccountBank;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payAccountBank)
    TextView dialogOfferLetterdetailTv2PayAccountBank;
    @BindView(R.id.dialog_offer_letterdetail_ll_payAccountBank)
    LinearLayout dialogOfferLetterdetailLlPayAccountBank;
    @BindView(R.id.dialog_offer_letterdetail_tv_payAccountApproach)
    TextView dialogOfferLetterdetailTvPayAccountApproach;
    @BindView(R.id.dialog_offer_letterdetail_tv2_payAccountApproach)
    TextView dialogOfferLetterdetailTv2PayAccountApproach;
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
    TextView dialogOfferLetterdetailTv2SpecialAgreement;
    @BindView(R.id.dialog_offer_letterdetail_ll_specialAgreement)
    LinearLayout dialogOfferLetterdetailLlSpecialAgreement;
    @BindView(R.id.dialog_offer_letterdetail_tv_signPerson)
    TextView dialogOfferLetterdetailTvSignPerson;
    @BindView(R.id.dialog_offer_letterdetail_tv2_signPerson)
    TextView dialogOfferLetterdetailTv2SignPerson;
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
    public ReviewOfferSubmitOnSaleListRes reviewOfferSubmitOnSaleListRes;//提交成功返回数据 用于展示邀约函
    String responsecode = "";
    @BindView(R.id.dialog_offer_letterdetail_tv_currency)
    TextView dialogOfferLetterdetailTvCurrency;
    @BindView(R.id.dialog_offer_letterdetail_tv2_currency)
    TextView dialogOfferLetterdetailTv2Currency;
    @BindView(R.id.dialog_offer_letterdetail_ll_currency)
    LinearLayout dialogOfferLetterdetailLlCurrency;
    @BindView(R.id.dialog_offer_letterdetail_tv_discountrate)
    TextView dialogOfferLetterdetailTvDiscountrate;
    @BindView(R.id.dialog_offer_letterdetail_tv2_discountrate)
    TextView dialogOfferLetterdetailTv2Discountrate;
    @BindView(R.id.dialog_offer_letterdetail_ll_discountrate)
    LinearLayout dialogOfferLetterdetailLlDiscountrate;
    @BindView(R.id.dialog_offer_letterdetail_tv_amount)
    TextView dialogOfferLetterdetailTvAmount;
    @BindView(R.id.dialog_offer_letterdetail_tv2_amount)
    TextView dialogOfferLetterdetailTv2Amount;
    @BindView(R.id.dialog_offer_letterdetail_ll_amount)
    LinearLayout dialogOfferLetterdetailLlAmount;
    @BindView(R.id.dialog_offer_letterdetail_tv_deadLine)
    TextView dialogOfferLetterdetailTvDeadLine;
    @BindView(R.id.dialog_offer_letterdetail_tv2_deadLine)
    TextView dialogOfferLetterdetailTv2DeadLine;
    @BindView(R.id.dialog_offer_letterdetail_ll_deadLine)
    LinearLayout dialogOfferLetterdetailLlDeadLine;
    private String assetsId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_letter_detail);
        ButterKnife.bind(this);
        assetsId = getIntent().getStringExtra("assetsId");
        if (!StringUtils.isEmpty(assetsId)) {
            checkLetterRet();
        } else {
            finish();
        }
    }

    //设置数据
    public void setDate(CheckLetterRes.DataBean date) {
        dialogOfferLetterdetailEtNumber.setText(date.getFlowNo());
        dialogOfferLetterdetailEtLcnumber.setText(date.getAssetsNo());
        dialogOfferLetterdetailTvDate.setText(date.getSignDate());
        dialogOfferLetterdetailTv2Iussingbankname.setText(date.getOpenFullName());
        dialogOfferLetterdetailTv2Acceptingbankname.setText(date.getTenderFullName());
        if ("1".equals(date.getDebtType())) {
            dialogOfferLetterdetailTv2Credittype.setText(getString(R.string.market_forfaiting_type1));
        } else if ("2".equals(date.getDebtType())) {
            dialogOfferLetterdetailTv2Credittype.setText(getString(R.string.market_forfaiting_type2));
        }
        dialogOfferLetterdetailTv2Creditnumber.setText(date.getLcNo());
        dialogOfferLetterdetailTv2Lcproposer.setText(date.getCreditApp());
        dialogOfferLetterdetailTv2Lc.setText(date.getCreditBeneficiary());
        dialogOfferLetterdetailTv2BasicTransaction.setText(date.getBasicTransaction());
        dialogOfferLetterdetailTv2DeadLine.setText(date.getDeadLine());//信用证期限deadLine
        dialogOfferLetterdetailTv2InvoiceAmount.setText(date.getInvoiceAmount());
        dialogOfferLetterdetailTv2Amount.setText(date.getAmount());//承兑金额amount
        dialogOfferLetterdetailTv2Currency.setText(date.getCurrency());
        dialogOfferLetterdetailTv2Acceptdate.setText(date.getAcceptEndDate());
        dialogOfferLetterdetailTv2Discountrate.setText(date.getDiscountRate());
        dialogOfferLetterdetailTv2InterestRate.setText(date.getInterestRate());
        dialogOfferLetterdetailTv2Grace.setText(date.getGrace());
        dialogOfferLetterdetailTv2Fee.setText(date.getFee());
        dialogOfferLetterdetailTv2DiscountPeriodDay.setText(date.getDiscountPeriodDay());
        dialogOfferLetterdetailTv2DiscountPeriodStart.setText(date.getDiscountPeriodStart());
        dialogOfferLetterdetailTv2DiscountAcceptDate.setText(date.getDiscountAcceptDate());
        dialogOfferLetterdetailTv2DiscountPeriodWork.setText(date.getDiscountPeriodWork());
        dialogOfferLetterdetailTv2BankCost.setText(date.getBankCost());
        dialogOfferLetterdetailTv2PreCharging.setText(date.getPreCharging());
        dialogOfferLetterdetailTv2DeliveryDate.setText(date.getDeliveryDate());
        dialogOfferLetterdetailTv2PayBillDay.setText(date.getPayBillDay());
        dialogOfferLetterdetailTv2PayAccount.setText(date.getPayAccount());
        dialogOfferLetterdetailTv2PayAccountBank.setText(date.getPayAccountBank());
        dialogOfferLetterdetailTv2PayAccountApproach.setText(date.getPayAccountApproach());
        dialogOfferLetterdetailTv2PollicitaBackDate.setText(date.getPollicitaBackDate());
        dialogOfferLetterdetailTv2PollicitaBackEndDate.setText(date.getPollicitaBackEndDate());
        dialogOfferLetterdetailTv2SpecialAgreement.setText(date.getSpecialAgreement());
        dialogOfferLetterdetailTv2SignPerson.setText(date.getSignPerson());
//        dialogOfferLetterdetailTv2.setText(date.getLcNo());//saveUrl
//        dialogOfferLetterdetailTv2.setText(date.getLcNo());//remark
//        dialogOfferLetterdetailTv2.setText(date.getLcNo());//createTime
        dialogOfferLetterdetailEtFor.setText(date.getSorgName());
        dialogOfferLetterdetailEtTo.setText(date.getBorgName());
//        dialogOfferLetterdetailTv2.setText(date.getLcNo());
        closeWaitDialog();
    }


    /**
     * //查看要约函  复用MessageListReq id字段
     *
     * @POST(Constants.NETPATH.CHECKLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> checkLetterRet(@Body RequestBody requestBody);
     * public Call<ReviewOfferSubmitLetterOnSaleListRes> checkLetterRet(MessageListReq data) {
     * Call<ReviewOfferSubmitLetterOnSaleListRes> checkLetterRet = mApi.checkLetterRet(createRequestBody(data));
     * return checkLetterRet;
     * }
     */


    private void checkLetterRet() {
        showWaitDialog();
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setId(assetsId);
        ActionPresenter.getInstance().checkLetterRet(messageListReq).enqueue(new Callback<CheckLetterRes>() {
            @Override
            public void onResponse(Call<CheckLetterRes> call, Response<CheckLetterRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            setDate(response.body().getData());
                        }
                    } else if (response.body().getCode() == 400) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else if (response.body().getCode() == 500) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else {
                        ToastUtils.showShort(response.body().getMessage());
                    }
                }
//                closeWaitDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
                closeWaitDialog();
            }
        });
    }


    @OnClick({R.id.dialog_offer_letterdetail_iv_close, R.id.dialog_offer_letterdetail_title, R.id.dialog_offer_letterdetail_et_title, R.id.dialog_offer_letterdetail_tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_offer_letterdetail_tv_sure:
            case R.id.dialog_offer_letterdetail_iv_close:
                finish();
                break;
            case R.id.dialog_offer_letterdetail_title:
                break;
            case R.id.dialog_offer_letterdetail_et_title:
                break;
        }
    }
}
