package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.ExpandLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
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
public class SoldFactoringDetailActivity extends BaseActivity {


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
    @BindView(R.id.market_factoring_detail_tv_bank)
    TextView marketFactoringDetailTvBank;
    @BindView(R.id.market_factoring_detail_tv_bank_yellow_iv)
    TextView marketFactoringDetailTvBankYellowIv;
    @BindView(R.id.market_factoring_detail_tv_bank_yellow1_ll)
    LinearLayout marketFactoringDetailTvBankYellow1Ll;
    @BindView(R.id.market_factoring_detail_tv_amount)
    TextView marketFactoringDetailTvAmount;
    @BindView(R.id.market_factoring_detail_tv_amount_text)
    TextView marketFactoringDetailTvAmountText;
    @BindView(R.id.market_factoring_detail_ll_jine)
    LinearLayout marketFactoringDetailLlJine;
    @BindView(R.id.market_factoring_detail_tv_type_text1)
    TextView marketFactoringDetailTvTypeText1;
    @BindView(R.id.market_factoring_detail_tv_type_text2)
    TextView marketFactoringDetailTvTypeText2;
    @BindView(R.id.market_factoring_detail_ll_type)
    LinearLayout marketFactoringDetailLlType;
    @BindView(R.id.market_factoring_detail_rl_lable1)
    RelativeLayout marketFactoringDetailRlLable1;
    @BindView(R.id.market_factoring_detail_tv_area)
    TextView marketFactoringDetailTvArea;
    @BindView(R.id.market_factoring_detail_tv_line)
    TextView marketFactoringDetailTvLine;
    @BindView(R.id.market_factoring_detail_tv_country)
    TextView marketFactoringDetailTvCountry;
    @BindView(R.id.market_factoring_detail_ll_lable2_area)
    LinearLayout marketFactoringDetailLlLable2Area;
    @BindView(R.id.market_factoring_detail_tv_date_lable)
    TextView marketFactoringDetailTvDateLable;
    @BindView(R.id.market_factoring_detail_tv_date)
    TextView marketFactoringDetailTvDate;
    @BindView(R.id.market_factoring_detail_ll_lable2_date)
    LinearLayout marketFactoringDetailLlLable2Date;
    @BindView(R.id.market_factoring_detail_tv_currency_lable)
    TextView marketFactoringDetailTvCurrencyLable;
    @BindView(R.id.market_factoring_detail_tv_currency)
    TextView marketFactoringDetailTvCurrency;
    @BindView(R.id.market_factoring_detail_ll_currency)
    LinearLayout marketFactoringDetailLlCurrency;
    @BindView(R.id.market_factoring_detail_tv_rate_lable)
    TextView marketFactoringDetailTvRateLable;
    @BindView(R.id.market_factoring_detail_tv_rate)
    TextView marketFactoringDetailTvRate;
    @BindView(R.id.market_factoring_detail_ll_rate)
    LinearLayout marketFactoringDetailLlRate;
    @BindView(R.id.market_factoring_detail_tv_duedate_lable)
    TextView marketFactoringDetailTvDuedateLable;
    @BindView(R.id.market_factoring_detail_tv_duedate)
    TextView marketFactoringDetailTvDuedate;
    @BindView(R.id.market_factoring_detail_ll_duedate)
    LinearLayout marketFactoringDetailLlDuedate;
    @BindView(R.id.market_factoring_detail_ll_lable2_data)
    LinearLayout marketFactoringDetailLlLable2Data;
    @BindView(R.id.market_factoring_detail_iv_line)
    ImageView marketFactoringDetailIvLine;
    @BindView(R.id.market_factoring_detail_tv_creditnumber)
    TextView marketFactoringDetailTvCreditnumber;
    @BindView(R.id.market_factoring_detail_tv2_creditnumber)
    TextView marketFactoringDetailTv2Creditnumber;
    @BindView(R.id.market_factoring_detail_ll_creditnumber)
    LinearLayout marketFactoringDetailLlCreditnumber;
    @BindView(R.id.market_factoring_detail_tv_credittype)
    TextView marketFactoringDetailTvCredittype;
    @BindView(R.id.market_factoring_detail_tv2_credittype)
    TextView marketFactoringDetailTv2Credittype;
    @BindView(R.id.market_factoring_detail_ll_credittype)
    LinearLayout marketFactoringDetailLlCredittype;
    @BindView(R.id.market_factoring_detail_tv_creditdate)
    TextView marketFactoringDetailTvCreditdate;
    @BindView(R.id.market_factoring_detail_tv2_creditdate)
    TextView marketFactoringDetailTv2Creditdate;
    @BindView(R.id.market_factoring_detail_ll_creditdate)
    LinearLayout marketFactoringDetailLlCreditdate;
    @BindView(R.id.market_factoring_detail_tv_factoringdeal)
    TextView marketFactoringDetailTvFactoringdeal;
    @BindView(R.id.market_factoring_detail_tv2_factoringdeal)
    TextView marketFactoringDetailTv2Factoringdeal;
    @BindView(R.id.market_factoring_detail_iv_factoringdeal)
    ImageView marketFactoringDetailIvFactoringdeal;
    @BindView(R.id.market_factoring_detail_ll_factoringdeal)
    LinearLayout marketFactoringDetailLlFactoringdeal;
    @BindView(R.id.market_factoring_detail_tv_unfold_lable)
    TextView marketFactoringDetailTvUnfoldLable;
    @BindView(R.id.market_factoring_detail_iv_unfold)
    ImageView marketFactoringDetailIvUnfold;
    @BindView(R.id.market_factoring_detail_ll_unfold)
    LinearLayout marketFactoringDetailLlUnfold;
    @BindView(R.id.market_factoring_detail_tv_iussingbankswift)
    TextView marketFactoringDetailTvIussingbankswift;
    @BindView(R.id.market_factoring_detail_tv2_iussingbankswift)
    TextView marketFactoringDetailTv2Iussingbankswift;
    @BindView(R.id.market_factoring_detail_ll_iussingbankswift)
    LinearLayout marketFactoringDetailLlIussingbankswift;
    @BindView(R.id.market_factoring_detail_tv_iussingbankname)
    TextView marketFactoringDetailTvIussingbankname;
    @BindView(R.id.market_factoring_detail_tv2_iussingbankname)
    TextView marketFactoringDetailTv2Iussingbankname;
    @BindView(R.id.market_factoring_detail_ll_iussingbankname)
    LinearLayout marketFactoringDetailLlIussingbankname;
    @BindView(R.id.market_factoring_detail_tv_acceptingbankswift)
    TextView marketFactoringDetailTvAcceptingbankswift;
    @BindView(R.id.market_factoring_detail_tv2_acceptingbankswift)
    TextView marketFactoringDetailTv2Acceptingbankswift;
    @BindView(R.id.market_factoring_detail_ll_acceptingbankswift)
    LinearLayout marketFactoringDetailLlAcceptingbankswift;
    @BindView(R.id.market_factoring_detail_tv_acceptingbankname)
    TextView marketFactoringDetailTvAcceptingbankname;
    @BindView(R.id.market_factoring_detail_tv2_acceptingbankname)
    TextView marketFactoringDetailTv2Acceptingbankname;
    @BindView(R.id.market_factoring_detail_ll_acceptingbankname)
    LinearLayout marketFactoringDetailLlAcceptingbankname;
    @BindView(R.id.market_factoring_detail_tv_acceptingdate)
    TextView marketFactoringDetailTvAcceptingdate;
    @BindView(R.id.market_factoring_detail_tv2_acceptingdate)
    TextView marketFactoringDetailTv2Acceptingdate;
    @BindView(R.id.market_factoring_detail_ll_acceptingdate)
    LinearLayout marketFactoringDetailLlAcceptingdate;
    @BindView(R.id.market_factoring_detail_tv_paybankswift)
    TextView marketFactoringDetailTvPaybankswift;
    @BindView(R.id.market_factoring_detail_tv2_paybankswift)
    TextView marketFactoringDetailTv2Paybankswift;
    @BindView(R.id.market_factoring_detail_ll_paybankswift)
    LinearLayout marketFactoringDetailLlPaybankswift;
    @BindView(R.id.market_factoring_detail_tv_paybankname)
    TextView marketFactoringDetailTvPaybankname;
    @BindView(R.id.market_factoring_detail_tv2_paybankname)
    TextView marketFactoringDetailTv2Paybankname;
    @BindView(R.id.market_factoring_detail_ll_paybankname)
    LinearLayout marketFactoringDetailLlPaybankname;
    @BindView(R.id.market_factoring_detail_iv_line1)
    ImageView marketFactoringDetailIvLine1;
    @BindView(R.id.market_factoring_detail_tv_blockchain)
    TextView marketFactoringDetailTvBlockchain;
    @BindView(R.id.market_factoring_detail_tv2_blockchain)
    TextView marketFactoringDetailTv2Blockchain;
    @BindView(R.id.market_factoring_detail_ll_blockchain)
    LinearLayout marketFactoringDetailLlBlockchain;
    @BindView(R.id.expandLayout)
    ExpandLayout expandLayout;
    @BindView(R.id.market_factoring_detail_ll_lable2)
    RelativeLayout marketFactoringDetailLlLable2;
    MarketFactoringDetailRes marketFactoringDetailRes;

    LoginRes loginRes;
    LoginRes.DataBean dataBean1;
    //报价操作开始
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
    @BindView(R.id.market_factoring_detail_tv2_seller)
    TextView marketFactoringDetailTv2Seller;
    @BindView(R.id.market_factoring_detail_tv_insurancecompact)
    TextView marketFactoringDetailTvInsurancecompact;
    @BindView(R.id.market_factoring_detail_tv2_insurancecompact)
    TextView marketFactoringDetailTv2Insurancecompact;
    @BindView(R.id.market_factoring_detail_iv_insurancecompact)
    ImageView marketFactoringDetailIvInsurancecompact;
    @BindView(R.id.market_factoring_detail_ll_insurancecompact)
    LinearLayout marketFactoringDetailLlInsurancecompact;
    @BindView(R.id.market_factoring_detail_tv_compact)
    TextView marketFactoringDetailTvCompact;
    @BindView(R.id.market_factoring_detail_tv2_compact)
    TextView marketFactoringDetailTv2Compact;
    @BindView(R.id.market_factoring_detail_iv_compact)
    ImageView marketFactoringDetailIvCompact;
    @BindView(R.id.market_factoring_detail_ll_compact)
    LinearLayout marketFactoringDetailLlCompact;
    @BindView(R.id.market_factoring_detail_tv_transportdocument)
    TextView marketFactoringDetailTvTransportdocument;
    @BindView(R.id.market_factoring_detail_tv2_transportdocument)
    TextView marketFactoringDetailTv2Transportdocument;
    @BindView(R.id.market_factoring_detail_iv_transportdocument)
    ImageView marketFactoringDetailIvTransportdocument;
    @BindView(R.id.market_factoring_detail_ll_transportdocument)
    LinearLayout marketFactoringDetailLlTransportdocument;
    @BindView(R.id.market_factoring_detail_tv_notice)
    TextView marketFactoringDetailTvNotice;
    @BindView(R.id.market_factoring_detail_tv2_notice)
    TextView marketFactoringDetailTv2Notice;
    @BindView(R.id.market_factoring_detail_iv_notice)
    ImageView marketFactoringDetailIvNotice;
    @BindView(R.id.market_factoring_detail_ll_notice)
    LinearLayout marketFactoringDetailLlNotice;
    @BindView(R.id.market_factoring_detail_tv_tradingcontract)
    TextView marketFactoringDetailTvTradingcontract;
    @BindView(R.id.market_factoring_detail_tv2_tradingcontract)
    TextView marketFactoringDetailTv2Tradingcontract;
    @BindView(R.id.market_factoring_detail_iv_tradingcontract)
    ImageView marketFactoringDetailIvTradingcontract;
    @BindView(R.id.market_factoring_detail_ll_tradingcontract)
    LinearLayout marketFactoringDetailLlTradingcontract;
    @BindView(R.id.market_factoring_detail_ll_factoringdealfile)
    LinearLayout marketFactoringDetailLlFactoringdealfile;
    @BindView(R.id.market_factoring_detail_ll_insurancecompactfile)
    LinearLayout marketFactoringDetailLlInsurancecompactfile;
    @BindView(R.id.market_factoring_detail_ll_compactfile)
    LinearLayout marketFactoringDetailLlCompactfile;
    @BindView(R.id.market_factoring_detail_ll_transportdocumentfile)
    LinearLayout marketFactoringDetailLlTransportdocumentfile;
    @BindView(R.id.market_factoring_detail_ll_noticefile)
    LinearLayout marketFactoringDetailLlNoticefile;
    @BindView(R.id.market_factoring_detail_ll_tradingcontractfile)
    LinearLayout marketFactoringDetailLlTradingcontractfile;
    @BindView(R.id.myquote_forfaiting_detail_ll_price)
    LinearLayout myquoteForfaitingDetailLlPrice;

    private String assetsType = "2";

    private String assetsId;//详情资产id
    private MarketForfaitingOfferReq marketForfaitingOfferReq;

    private String letterUrl;//财务报表
    private String letterUrl2;//保理合同
    private String letterUrl3;//货物保险单
    private String letterUrl4;//运输单据
    private String letterUrl5;//应收账款转让通知书
    private String letterUrl6;//贸易合同
    //文件附件列表
    private View fileListView;

    //报价操作结束
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_factoring_detail);
        ButterKnife.bind(this);
        //获取参数
        assetsId = getIntent().getStringExtra("id");
        MyLogger.pLog().i("assetsId:" + assetsId);
        //默认收缩列表
        expandLayout.initExpand(false);
        //初始化数据
        initDetailData(assetsId);
        loginRes = (LoginRes) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_OBJECT, null);
        if (StringUtils.isNullObject(loginRes)) {
            dataBean1 = loginRes.getData();
        }
    }


    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.iv_Rtv, R.id.tv_id_number, R.id.market_factoring_detail_tv2_factoringdeal, R.id.market_factoring_detail_iv_factoringdeal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.tv_content:
                break;
            case R.id.iv_Ricon:
                break;
            case R.id.iv_Rtv:
                break;
            case R.id.tv_id_number:
                break;
            case R.id.market_factoring_detail_iv_factoringdeal:
            case R.id.market_factoring_detail_tv2_factoringdeal:
                if (!StringUtils.isEmpty(letterUrl)) {
//                    downloadFileWithDynamicUrlSync(letterUrl);
                    showWaitDialog();
                    String[] urls = letterUrl.split(";");
                    for (int i = 0; i < urls.length; i++) {
                        downloadFileWithDynamicUrlSync(urls[i]);
                    }
                    closeWaitDialog();
                }
                break;
        }
    }

    ////市场行情保理、详情接口
    public void initDetailData(String id) {
        MyLogger.pLog().i("市场行情福费廷、详情接口");
        MarketForfaitingDetailReq marketForfaitingDetailReq = new MarketForfaitingDetailReq();
        marketForfaitingDetailReq.setFactoringId(id);
        marketForfaitingDetailReq.setYn("0");
        ActionPresenter.getInstance().marketFactoringDetailRet(marketForfaitingDetailReq).enqueue(new Callback<MarketFactoringDetailRes>() {
            @Override
            public void onResponse(Call<MarketFactoringDetailRes> call, Response<MarketFactoringDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            setData(response.body());
                        }
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        MyLogger.pLog().e(response.body().getMessage());
                        finish();
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


    public void setData(MarketFactoringDetailRes marketForfaitingDetailRes1) {
        marketFactoringDetailRes = marketForfaitingDetailRes1;

        MarketFactoringDetailRes.DataBean dataBean = marketFactoringDetailRes.getData();

        if (StringUtils.isNullObject(dataBean)) {
            MarketFactoringDetailRes.DataBean.FactoringResponseBean factoringResponseBean = dataBean.getFactoringResponse();
            if (StringUtils.isNullObject(factoringResponseBean)) {
                //设置标题
                tvContent.setText(getString(R.string.blockchain_number) + factoringResponseBean.getFactoringNo());
                marketFactoringDetailTvBank.setText(factoringResponseBean.getFactoringName());

                String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
                if ("cn".equals(lanage)) {
                    marketFactoringDetailTvArea.setText(factoringResponseBean.getAreaName());
                    marketFactoringDetailTvCountry.setText(factoringResponseBean.getCountryName());
                } else if ("en".equals(lanage)) {
                    marketFactoringDetailTvArea.setText(factoringResponseBean.getEnAreaName());
                    marketFactoringDetailTvCountry.setText(factoringResponseBean.getEnCountryName());
                }
                /** 是否投保 1 投保 2 不投保 */
                if ("1".equals(factoringResponseBean.getIsCove())) {
                    marketFactoringDetailTvBankYellow1Ll.setVisibility(View.VISIBLE);
                } else if ("2".equals(factoringResponseBean.getIsCove())) {
                    marketFactoringDetailTvBankYellow1Ll.setVisibility(View.GONE);
                }
                marketFactoringDetailTvDate.setText(factoringResponseBean.getIndateMessage());
                marketFactoringDetailTvCurrency.setText(factoringResponseBean.getCurrency());
                marketFactoringDetailTvRate.setText(factoringResponseBean.getTransferRate() + "%");
                marketFactoringDetailTvDuedate.setText(factoringResponseBean.getMaturity());
                marketFactoringDetailTvAmount.setText(factoringResponseBean.getAmount());
                /** 保理类型 1 单保理、明保理 2 单保理、暗保理 3、双保理、明保理 4、双保理、暗保理 */
                if ("1".equals(factoringResponseBean.getFactoringType())) {
                    marketFactoringDetailTvTypeText1.setText(getString(R.string.market_forfaiting_filtrate_single));
                    marketFactoringDetailTvTypeText2.setText(getString(R.string.market_forfaiting_filtrate_disclosed));
                } else if ("2".equals(factoringResponseBean.getFactoringType())) {
                    marketFactoringDetailTvTypeText1.setText(getString(R.string.market_forfaiting_filtrate_single));
                    marketFactoringDetailTvTypeText2.setText(getString(R.string.market_forfaiting_filtrate_undisclosed));
                } else if ("3".equals(factoringResponseBean.getFactoringType())) {
                    marketFactoringDetailTvTypeText1.setText(getString(R.string.market_forfaiting_filtrate_double));
                    marketFactoringDetailTvTypeText2.setText(getString(R.string.market_forfaiting_filtrate_disclosed));
                } else if ("4".equals(factoringResponseBean.getFactoringType())) {
                    marketFactoringDetailTvTypeText1.setText(getString(R.string.market_forfaiting_filtrate_double));
                    marketFactoringDetailTvTypeText2.setText(getString(R.string.market_forfaiting_filtrate_undisclosed));
                } else {
                    //如果都不是 就隐藏
                    marketFactoringDetailLlType.setVisibility(View.GONE);
                }

                //展开内容
                marketFactoringDetailTv2Creditnumber.setText(factoringResponseBean.getInsurer());//信用保险承保人
                marketFactoringDetailTv2Credittype.setText(factoringResponseBean.getOriginators());//原始债权人
                marketFactoringDetailTv2Creditdate.setText(factoringResponseBean.getObligors());//原始债务人
                marketFactoringDetailTv2Seller.setText(factoringResponseBean.getSeller());//资产卖出方
                //拿到资产id请求报价列表
//                initDetailOfferListData(assetsBean.getId()+"");
                if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement())) {
                    int i = 0,j = 0;
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0208())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url().length() > 10) {
                            letterUrl = factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlFactoringdealfile, letterUrl);
                        } else {
                            marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                            marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
                            i++;
                        }
                    } else {
                        marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                        marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
                        j++;
                    }
                    //保理合同
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0204())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0204().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0204().getAttachment_url().length() > 10) {
                            letterUrl2 = factoringResponseBean.getAssetAgreement().getST0204().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlInsurancecompactfile, letterUrl2);
                        } else {
                            marketFactoringDetailTv2Insurancecompact.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvInsurancecompact.setVisibility(View.GONE);
                            marketFactoringDetailLlInsurancecompact.setVisibility(View.GONE);
                            i++;
                        }
                    } else {
                        marketFactoringDetailTv2Insurancecompact.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvInsurancecompact.setVisibility(View.GONE);
                        marketFactoringDetailLlInsurancecompact.setVisibility(View.GONE);
                        j++;
                    }
                    //货物保险单
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0209())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0209().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0209().getAttachment_url().length() > 10) {
                            letterUrl3 = factoringResponseBean.getAssetAgreement().getST0209().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlCompactfile, letterUrl3);
                        } else {

                            marketFactoringDetailTv2Compact.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvCompact.setVisibility(View.GONE);
                            marketFactoringDetailLlCompact.setVisibility(View.GONE);
                            i++;
                        }
                    } else {

                        marketFactoringDetailTv2Compact.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvCompact.setVisibility(View.GONE);
                        marketFactoringDetailLlCompact.setVisibility(View.GONE);
                        j++;
                    }
                    //运输单据
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0206())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0206().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0206().getAttachment_url().length() > 10) {
                            letterUrl4 = factoringResponseBean.getAssetAgreement().getST0206().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlTransportdocumentfile, letterUrl4);
                        } else {
                            marketFactoringDetailTv2Transportdocument.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvTransportdocument.setVisibility(View.GONE);
                            marketFactoringDetailLlTransportdocument.setVisibility(View.GONE);
                            i++;
                        }
                    } else {
                        marketFactoringDetailTv2Transportdocument.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvTransportdocument.setVisibility(View.GONE);
                        marketFactoringDetailLlTransportdocument.setVisibility(View.GONE);
                        j++;
                    }
                    //应收账款转让通知书
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0207())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0207().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0207().getAttachment_url().length() > 10) {
                            letterUrl5 = factoringResponseBean.getAssetAgreement().getST0207().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlNoticefile, letterUrl5);
                        } else {
                            marketFactoringDetailTv2Notice.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvNotice.setVisibility(View.GONE);
                            marketFactoringDetailLlNotice.setVisibility(View.GONE);
                            i++;
                        }
                    } else {
                        marketFactoringDetailTv2Notice.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvNotice.setVisibility(View.GONE);
                        marketFactoringDetailLlNotice.setVisibility(View.GONE);
                        j++;
                    }
                    //贸易合同
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0205())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0205().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0205().getAttachment_url().length() > 10) {
                            letterUrl6 = factoringResponseBean.getAssetAgreement().getST0205().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlTradingcontractfile, letterUrl6);
                        } else {
                            marketFactoringDetailTv2Tradingcontract.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvTradingcontract.setVisibility(View.GONE);
                            marketFactoringDetailLlTradingcontract.setVisibility(View.GONE);
                            i++;
                        }
                    } else {
                        marketFactoringDetailTv2Tradingcontract.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvTradingcontract.setVisibility(View.GONE);
                        marketFactoringDetailLlTradingcontract.setVisibility(View.GONE);
                        j++;
                    }
                    if(i==6||j==6){
                        myquoteForfaitingDetailLlPrice.setVisibility(View.GONE);
                    }
                } else {
                    marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                    marketFactoringDetailTv2Insurancecompact.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvInsurancecompact.setVisibility(View.GONE);
                    marketFactoringDetailTv2Compact.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvCompact.setVisibility(View.GONE);
                    marketFactoringDetailTv2Transportdocument.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvTransportdocument.setVisibility(View.GONE);
                    marketFactoringDetailTv2Notice.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvNotice.setVisibility(View.GONE);
                    marketFactoringDetailTv2Tradingcontract.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvTradingcontract.setVisibility(View.GONE);
                    myquoteForfaitingDetailLlPrice.setVisibility(View.GONE);
                }

            }
            MarketFactoringDetailRes.DataBean.CompanyOrgBean companyOrgBean = dataBean.getCompanyOrg();

        }
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
                    String writtenToDisk = FileUtils.writeResponseBodyToDisk(MyApplication.mMyApplication, response.body(), url);
                    if (!StringUtils.isEmpty(writtenToDisk)) {
                        ToastUtils.showLong(getString(R.string.toastmarket_forfaiting_detail_downfile) + writtenToDisk);
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    @OnClick({R.id.market_factoring_detail_tv_insurancecompact, R.id.market_factoring_detail_tv2_insurancecompact, R.id.market_factoring_detail_iv_insurancecompact, R.id.market_factoring_detail_ll_insurancecompact, R.id.market_factoring_detail_tv_compact, R.id.market_factoring_detail_tv2_compact, R.id.market_factoring_detail_iv_compact, R.id.market_factoring_detail_ll_compact, R.id.market_factoring_detail_tv_transportdocument, R.id.market_factoring_detail_tv2_transportdocument, R.id.market_factoring_detail_iv_transportdocument, R.id.market_factoring_detail_ll_transportdocument, R.id.market_factoring_detail_tv_notice, R.id.market_factoring_detail_tv2_notice, R.id.market_factoring_detail_iv_notice, R.id.market_factoring_detail_ll_notice, R.id.market_factoring_detail_tv_tradingcontract, R.id.market_factoring_detail_tv2_tradingcontract, R.id.market_factoring_detail_iv_tradingcontract, R.id.market_factoring_detail_ll_tradingcontract})
    public void onViewClickedDownload(View view) {
        switch (view.getId()) {
            case R.id.market_factoring_detail_tv_insurancecompact:
                break;
            case R.id.market_factoring_detail_tv2_insurancecompact:
            case R.id.market_factoring_detail_iv_insurancecompact:
                if (!StringUtils.isEmpty(letterUrl2)) {
//                    downloadFileWithDynamicUrlSync(letterUrl);
                    showWaitDialog();
                    String[] urls = letterUrl2.split(";");
                    for (int i = 0; i < urls.length; i++) {
                        downloadFileWithDynamicUrlSync(urls[i]);
                    }
                    closeWaitDialog();
                }
                break;
            case R.id.market_factoring_detail_ll_insurancecompact:
                break;
            case R.id.market_factoring_detail_tv_compact:
                break;
            case R.id.market_factoring_detail_tv2_compact:
            case R.id.market_factoring_detail_iv_compact:
                if (!StringUtils.isEmpty(letterUrl3)) {
//                    downloadFileWithDynamicUrlSync(letterUrl);
                    showWaitDialog();
                    String[] urls = letterUrl3.split(";");
                    for (int i = 0; i < urls.length; i++) {
                        downloadFileWithDynamicUrlSync(urls[i]);
                    }
                    closeWaitDialog();
                }
                break;
            case R.id.market_factoring_detail_ll_compact:
                break;
            case R.id.market_factoring_detail_tv_transportdocument:
                break;
            case R.id.market_factoring_detail_tv2_transportdocument:
            case R.id.market_factoring_detail_iv_transportdocument:
                if (!StringUtils.isEmpty(letterUrl4)) {
//                    downloadFileWithDynamicUrlSync(letterUrl);
                    showWaitDialog();
                    String[] urls = letterUrl4.split(";");
                    for (int i = 0; i < urls.length; i++) {
                        downloadFileWithDynamicUrlSync(urls[i]);
                    }
                    closeWaitDialog();
                }
                break;
            case R.id.market_factoring_detail_ll_transportdocument:
                break;
            case R.id.market_factoring_detail_tv_notice:
                break;
            case R.id.market_factoring_detail_tv2_notice:
            case R.id.market_factoring_detail_iv_notice:
                if (!StringUtils.isEmpty(letterUrl5)) {
//                    downloadFileWithDynamicUrlSync(letterUrl);
                    showWaitDialog();
                    String[] urls = letterUrl5.split(";");
                    for (int i = 0; i < urls.length; i++) {
                        downloadFileWithDynamicUrlSync(urls[i]);
                    }
                    closeWaitDialog();
                }
                break;
            case R.id.market_factoring_detail_ll_notice:
                break;
            case R.id.market_factoring_detail_tv_tradingcontract:
                break;
            case R.id.market_factoring_detail_tv2_tradingcontract:
            case R.id.market_factoring_detail_iv_tradingcontract:
                if (!StringUtils.isEmpty(letterUrl6)) {
//                    downloadFileWithDynamicUrlSync(letterUrl);
                    showWaitDialog();
                    String[] urls = letterUrl6.split(";");
                    for (int i = 0; i < urls.length; i++) {
                        downloadFileWithDynamicUrlSync(urls[i]);
                    }
                    closeWaitDialog();
                }
                break;
            case R.id.market_factoring_detail_ll_tradingcontract:
                break;
        }
    }

    public void setFileDataList(View view, String urls) {
        ((LinearLayout) view).removeAllViews();
        List<String> urlslist = StringUtils.splitStr(urls, ";");
        for (final String url : urlslist) {
            fileListView = LayoutInflater.from(this).inflate(R.layout.item_forfaiting_detail_file_list, null);
            RelativeLayout relativeLayout = (RelativeLayout) fileListView.findViewById(R.id.item_forfaiting_detail_rl);
            ImageView imageView = (ImageView) fileListView.findViewById(R.id.item_forfaiting_detail_iv);
            if ("jpg".equals(StringUtils.isType(url))) {
                imageView.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(url))) {
                imageView.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(url))) {
                imageView.setBackgroundResource(R.drawable.zip_shrink);
            }
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("jpg".equals(StringUtils.isType(url))) {
                        DisPatcher.startPicturePreviewActivity(SoldFactoringDetailActivity.this, url);
                    }
                }
            });
            ((LinearLayout) view).addView(fileListView);
        }
    }
}
