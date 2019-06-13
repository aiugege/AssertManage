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
import com.zjhl.pad.moudle.entity.req.ForfaitingRportReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.MyQuotePriseListRes;
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
public class MyQuoteFactoringDetailActivity extends BaseActivity {


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
    @BindView(R.id.myquote_forfaiting_detail_ll_title)
    LinearLayout myquoteForfaitingDetailLlTitle;
    @BindView(R.id.myquote_forfaiting_detail_tv_name)
    TextView myquoteForfaitingDetailTvName;
    @BindView(R.id.myquote_forfaiting_detail_tv_precent)
    TextView myquoteForfaitingDetailTvPrecent;
    @BindView(R.id.myquote_forfaiting_detail_ll_price)
    LinearLayout myquoteForfaitingDetailLlPrice;
    MarketFactoringDetailRes marketFactoringDetailRes;
    @BindView(R.id.myquote_forfaiting_detail_ll_title2)
    LinearLayout myquoteForfaitingDetailLlTitle2;
    @BindView(R.id.myquote_forfaiting_detail_ll_pricelist)
    LinearLayout myquoteForfaitingDetailLlPricelist;
    @BindView(R.id.myquote_forfaiting_detail_ll_price2)
    LinearLayout myquoteForfaitingDetailLlPrice2;

    LoginRes loginRes;
    LoginRes.DataBean dataBean1;
    //报价操作开始
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
    @BindView(R.id.market_factoring_detail_tv2_seller)
    TextView marketFactoringDetailTv2Seller;
    @BindView(R.id.market_factoring_detail_ll_factoringdealfile)
    LinearLayout marketFactoringDetailLlFactoringdealfile;

    private String assetsType = "2";

    private String assetsId;//详情资产id
    private String priceId;//报价id
    private MarketForfaitingOfferReq marketForfaitingOfferReq;

    private String letterUrl;//财务报表

    //文件附件列表
    private View fileListView;

    //报价操作结束
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquote_factoring_detail);
        ButterKnife.bind(this);
        //获取参数
        assetsId = getIntent().getStringExtra("id");
        priceId = getIntent().getStringExtra("priceId");
//        MyLogger.pLog().i("assetsId:" + assetsId);
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
        String yn = "0";
        yn = dataBean.getCompanyOrg().getYn();
        if (StringUtils.isNullObject(dataBean)) {
            MarketFactoringDetailRes.DataBean.FactoringResponseBean factoringResponseBean = dataBean.getFactoringResponse();
            if (StringUtils.isNullObject(factoringResponseBean)) {
                //设置标题
                tvContent.setText(getString(R.string.blockchain_number)+factoringResponseBean.getFactoringNo());
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
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0208())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url().length() > 10) {
                            letterUrl = factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlFactoringdealfile,letterUrl);
                        } else {
                            marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                            marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
                        }
                    } else {
                        marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                        marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
                    }
                } else {
                    marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                    marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
                }

            }
            //交易完成130请求报价列表
            if ("130".equals(factoringResponseBean.getCheckState())) {
                initDetailOfferListData2(assetsId);
            }
            //报价中104 或者 108 120  125请求报价列表
            if ("104".equals(factoringResponseBean.getCheckState())||"108".equals(factoringResponseBean.getCheckState())||"120".equals(factoringResponseBean.getCheckState())||"125".equals(factoringResponseBean.getCheckState())) {
                if(!"0".equals(yn)) {
                    myQuotePriseListRet(priceId);
                }
            }
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

    //市场行情福费廷、详情报价列表接口
    public void initDetailOfferListData(String id) {
        MyLogger.pLog().i("市场行情福费廷、详情报价列表接口");
//        showWaitDialog();
        MarketForfaitingOfferListReq marketForfaitingOfferListReq = new MarketForfaitingOfferListReq();
        marketForfaitingOfferListReq.setAssertId(id);
        //资产类型1-福费廷 2-保理
        marketForfaitingOfferListReq.setTradingType(assetsType);
        if ("2".equals(userType)) {
            ActionPresenter.getInstance().handleOfferListOnSaleListRet(marketForfaitingOfferListReq).enqueue(new Callback<MarketForfaitingOfferListRes>() {
                @Override
                public void onResponse(Call<MarketForfaitingOfferListRes> call, Response<MarketForfaitingOfferListRes> response) {
                    if (response != null && response.body() != null) {
//                        MyLogger.pLog().d("=====" + response.body().toString());
//                        MyLogger.pLog().d("=====" + response.body().getCode());
                        closeWaitDialog();
                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                                if (list != null && list.size() > 0) {
//                                    if("1".equals(list.get(0).getIsStruck())) {
                                    myquoteForfaitingDetailLlTitle.setVisibility(View.VISIBLE);
                                    myquoteForfaitingDetailLlPrice.setVisibility(View.VISIBLE);
                                    myquoteForfaitingDetailTvName.setText(list.get(0).getbOrgName());
                                    myquoteForfaitingDetailTvPrecent.setText(list.get(0).getDiscountRate() + "%");
//                                    }
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
//                closeWaitDialog();
                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                                if (list != null && list.size() > 0) {
//                                    if("1".equals(list.get(0).getIsStruck())) {
                                    myquoteForfaitingDetailLlTitle.setVisibility(View.VISIBLE);
                                    myquoteForfaitingDetailLlPrice.setVisibility(View.VISIBLE);
                                    myquoteForfaitingDetailTvName.setText(list.get(0).getbOrgName());
                                    myquoteForfaitingDetailTvPrecent.setText(list.get(0).getDiscountRate() + "%");
//                                    }
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

    //市场行情信用证、详情报价列表接口
    public void initDetailOfferListData2(String id) {
        MyLogger.pLog().i("市场行情信用证、详情报价列表接口");
        showWaitDialog();
        MarketForfaitingOfferListReq marketForfaitingOfferListReq = new MarketForfaitingOfferListReq();
        marketForfaitingOfferListReq.setAssertId(id);
        //资产类型1-信用证 2-保理
        marketForfaitingOfferListReq.setTradingType("2");
        ActionPresenter.getInstance().marketForfaitingOfferList1Ret(marketForfaitingOfferListReq).enqueue(new Callback<MarketForfaitingOfferListRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingOfferListRes> call, Response<MarketForfaitingOfferListRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    closeWaitDialog();
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            List<MarketForfaitingOfferListRes.DataBean> list = response.body().getData();
                            if (list != null && list.size() > 0) {
//                                    if("1".equals(list.get(0).getIsStruck())) {
                                myquoteForfaitingDetailLlTitle.setVisibility(View.VISIBLE);
                                myquoteForfaitingDetailLlPrice.setVisibility(View.VISIBLE);
                                myquoteForfaitingDetailTvName.setText(list.get(0).getbOrgName());
                                myquoteForfaitingDetailTvPrecent.setText(list.get(0).getDiscountRate() + "%");
//                                    }
                            }
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
                        DisPatcher.startPicturePreviewActivity(MyQuoteFactoringDetailActivity.this, url);
                    }
                }
            });
            ((LinearLayout) view).addView(fileListView);
        }
    }

    //报价列表
    public void setPriceDataList(View view, List<MyQuotePriseListRes.DataBean> list) {
        ((LinearLayout) view).removeAllViews();
        for (MyQuotePriseListRes.DataBean data : list) {
            fileListView = LayoutInflater.from(this).inflate(R.layout.item_myquote_detail_price_list, null);
            TextView rate = (TextView) fileListView.findViewById(R.id.item_myquote_detail_price_list_rate);
            TextView date = (TextView) fileListView.findViewById(R.id.item_myquote_detail_price_list_date);
            TextView result = (TextView) fileListView.findViewById(R.id.item_myquote_detail_price_list_result);
            TextView reason3 = (TextView) fileListView.findViewById(R.id.item_myquote_detail_price_list_reson3);
            rate.setText(data.getDiscountRate()+"%");
            //复核结果 1 待复核 2 通过 3 未通过
            if ("1".equals(data.getPriceState())) {
                result.setTextColor(getResources().getColor(R.color.red));
                result.setText(R.string.item_myquote_detail_price_list_daifuhe);
            } else if ("2".equals(data.getPriceState())) {
                result.setText(R.string.item_myquote_detail_price_list_tongguo);
                result.setTextColor(getResources().getColor(R.color.register_button_bg));
            } else if ("3".equals(data.getPriceState())) {
                result.setTextColor(getResources().getColor(R.color.red));
                result.setText(R.string.item_myquote_detail_price_list_weitongguo);
            }
            date.setText(data.getPriceDate());
            reason3.setText(data.getRejectOpinion());
            ((LinearLayout) view).addView(fileListView);
        }
    }

    /**
     * //根据报价查询报价流水信息（前五条）接口  复用ForfaitingRportReq id
     *
     * @POST(Constants.NETPATH.MYQUOTEPRISELIST) Call<MyQuotePriseListRes> myQuotePriseListRet(@Body RequestBody requestBody);
     */
    //根据报价查询报价流水信息（前五条）接口  复用ForfaitingRportReq id
    public void myQuotePriseListRet(String id) {
        MyLogger.pLog().i("根据报价查询报价流水信息（前五条）接口");
        showWaitDialog();
        ForfaitingRportReq forfaitingRportReq = new ForfaitingRportReq();
        forfaitingRportReq.setId(id);
        ActionPresenter.getInstance().myQuotePriseListRet(forfaitingRportReq).enqueue(new Callback<MyQuotePriseListRes>() {
            @Override
            public void onResponse(Call<MyQuotePriseListRes> call, Response<MyQuotePriseListRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    closeWaitDialog();
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            List<MyQuotePriseListRes.DataBean> list = response.body().getData();
                            if (list != null && list.size() > 0) {
                                myquoteForfaitingDetailLlTitle2.setVisibility(View.VISIBLE);
                                myquoteForfaitingDetailLlPrice2.setVisibility(View.VISIBLE);
                                setPriceDataList(myquoteForfaitingDetailLlPricelist, list);
                            }
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
}
