package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjhl.pad.app.utils.EditInputFilterOneHunderd;
import com.zjhl.pad.app.utils.EditInputFilterRate;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: MarketForfaitingDetailActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/5/15 20:14
 * @projectname: nnkj
 **/
public class MarketFactoringOfferActivity extends BaseActivity {


    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_id_number)
    TextView tvIdNumber;
    @BindView(R.id.market_forfaiting_offer_tv_number)
    TextView marketForfaitingOfferTvNumber;
    @BindView(R.id.market_forfaiting_offer_tv2_bank)
    TextView marketForfaitingOfferTv2Bank;
    @BindView(R.id.market_forfaiting_offer_ll_bank)
    LinearLayout marketForfaitingOfferLlBank;
    @BindView(R.id.market_forfaiting_offer_tv_amount)
    TextView marketForfaitingOfferTvAmount;
    @BindView(R.id.market_forfaiting_offer_tv2_amount)
    TextView marketForfaitingOfferTv2Amount;
    @BindView(R.id.market_forfaiting_offer_ll_amount)
    LinearLayout marketForfaitingOfferLlAmount;
    @BindView(R.id.market_forfaiting_offer_tv_date)
    TextView marketForfaitingOfferTvDate;
    @BindView(R.id.market_forfaiting_offer_tv2_date)
    TextView marketForfaitingOfferTv2Date;
    @BindView(R.id.market_forfaiting_offer_ll_date)
    LinearLayout marketForfaitingOfferLlDate;
    @BindView(R.id.market_forfaiting_offer_tv_messagedate)
    TextView marketForfaitingOfferTvMessagedate;
    @BindView(R.id.market_forfaiting_offer_tv2_messagedate)
    TextView marketForfaitingOfferTv2Messagedate;
    @BindView(R.id.market_forfaiting_offer_ll_messagedate)
    LinearLayout marketForfaitingOfferLlMessagedate;
    @BindView(R.id.market_forfaiting_offer_ll)
    LinearLayout marketForfaitingOfferLl;
    @BindView(R.id.market_forfaiting_offer_tv_rate)
    TextView marketForfaitingOfferTvRate;
    @BindView(R.id.market_forfaiting_offer_ev_rate)
    EditText marketForfaitingOfferEvRate;
    @BindView(R.id.market_forfaiting_offer_tv2_rate)
    TextView marketForfaitingOfferTv2Rate;
    @BindView(R.id.market_forfaiting_offer_ll_rate)
    LinearLayout marketForfaitingOfferLlRate;
    @BindView(R.id.market_forfaiting_offer_ll2)
    LinearLayout marketForfaitingOfferLl2;
    @BindView(R.id.market_forfaiting_offer_submit)
    ImageView marketForfaitingOfferSubmit;
    //    private String id = "";//详情id
    MarketFactoringDetailRes marketFactoringDetailRes;
    MarketForfaitingOfferReq marketForfaitingOfferReq;
    SureOrCancelDialog sureOrCancelDialog;
    String transferRate = "";
    double doubleTransferRate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forfaiting_offer);
        ButterKnife.bind(this);
//        id = getIntent().getStringExtra("id");
        EditInputFilterRate[] filters = {new EditInputFilterRate()};
        marketForfaitingOfferEvRate.setFilters(filters);
        initDiscountRateListener(marketForfaitingOfferEvRate);
        marketFactoringDetailRes = (MarketFactoringDetailRes) getIntent().getSerializableExtra("marketFactoringDetailRes");
        marketForfaitingOfferTvDate.setText(R.string.filtrate_factoring_enddate);
        initView();
//        MyLogger.pLog().i("id:" + id);
        MyLogger.pLog().i("marketFactoringDetailRes:" + marketFactoringDetailRes.toString());
        setData();
//        initDetailData(marketFactoringDetailRes);
    }

    public void initDiscountRateListener(final EditText view) {
        view.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String viewtext = view.getText().toString();
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    view.setText(StringUtils.doubleFormate(viewtext));
                    // 此处为失去焦点时的处理内容
                }
            }
        });
    }
    ////市场行情福费廷、提交报价接口
    public void initDetailData(String rate) {
        MyLogger.pLog().i("市场行情福费廷、提交报价接口");
//        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
//        marketForfaitingOfferReq.setBussId("");
//        marketForfaitingOfferReq.setTradingType("");
        marketForfaitingOfferReq.setDiscountRate(rate + "");
        ActionPresenter.getInstance().marketForfaitingOfferRet(marketForfaitingOfferReq).enqueue(new Callback<MarketForfaitingDetailRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingDetailRes> call, Response<MarketForfaitingDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        setResult(111);
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_offer));
                        finish();
                        if (response.body().getData() != null) {
//                        setData(response.body());

                        }
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_offer_faild));
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    public void initView() {
        sureOrCancelDialog = new SureOrCancelDialog(MarketFactoringOfferActivity.this, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                String rateStr = marketForfaitingOfferEvRate.getText().toString();
                initDetailData(StringUtils.doubleFormate(rateStr));
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect,String companyName) {
                MyLogger.pLog().i("negative");
            }
        },  getString(R.string.forfaiting_offer_yesorno), getString(R.string.onsalelist_forfaiting_adapter_cancel), getString(R.string.onsalelist_forfaiting_adapter_sure));
    }

    public void setData() {
//        marketFactoringDetailRes = marketForfaitingDetailRes1;
        marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        MarketFactoringDetailRes.DataBean dataBean = marketFactoringDetailRes.getData();

        if (StringUtils.isNullObject(dataBean)) {
            MarketFactoringDetailRes.DataBean.FactoringResponseBean assetsBean = dataBean.getFactoringResponse();
            MarketFactoringDetailRes.DataBean.CompanyOrgBean companyOrgBean = dataBean.getCompanyOrg();
            if (StringUtils.isNullObject(companyOrgBean)) {
                marketForfaitingOfferTv2Bank.setText(companyOrgBean.getCompanyName());
            }
            if (StringUtils.isNullObject(assetsBean)) {
                //设置标题
                tvContent.setText(R.string.forfaiting_offer_price);
                marketForfaitingOfferTvNumber.setText(assetsBean.getFactoringNo());
                marketForfaitingOfferTv2Amount.setText(assetsBean.getAmount());
                marketForfaitingOfferTv2Date.setText(assetsBean.getMaturity());
                marketForfaitingOfferTv2Messagedate.setText(assetsBean.getIndateMessage());
                transferRate = assetsBean.getTransferRate();
                if(!StringUtils.isEmpty(transferRate)){
                    doubleTransferRate = Double.valueOf(transferRate);
                }
                //设置资产id
                marketForfaitingOfferReq.setBussId(assetsBean.getId() + "");
                //设置资产类型1 福费廷 2保理
                marketForfaitingOfferReq.setTradingType("2");
            }

        }
    }

    @OnClick({R.id.iv_excite, R.id.market_forfaiting_offer_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.market_forfaiting_offer_ev_rate:
//                marketForfaitingOfferEvRate.clearFocus();
                marketForfaitingOfferEvRate.setFocusable(true);
                marketForfaitingOfferEvRate.setFocusableInTouchMode(true);
                break;
            case R.id.market_forfaiting_offer_submit:
//                marketForfaitingOfferEvRate.clearFocus();
//                marketForfaitingOfferEvRate.setFocusable(false);
//                marketForfaitingOfferEvRate.setFocusableInTouchMode(false);
                String rateStr = marketForfaitingOfferEvRate.getText().toString();
                double rateDouble;
                if (!StringUtils.isEmpty(rateStr)) {
                    rateDouble = Double.valueOf(rateStr);
                    if(rateDouble<=doubleTransferRate){
                        sureOrCancelDialog.setCancelable(true).show();
                    }else{
                        ToastUtils.showShort(getString(R.string.toast_myquote_notransferrate));
                    }
//                    initDetailData(rateStr);
                } else {
                    ToastUtils.showShort(getString(R.string.toast_myquote_notransferrate));
                }
                break;
        }
    }
}
