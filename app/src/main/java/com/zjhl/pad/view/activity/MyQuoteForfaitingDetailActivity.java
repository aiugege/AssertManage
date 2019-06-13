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
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.req.ForfaitingRportReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.MyQuotePriseListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.ExpandLayout;
import com.zjhl.pad.view.views.HandleOfferDetailDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class MyQuoteForfaitingDetailActivity extends BaseActivity {


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
    @BindView(R.id.market_forfaiting_detail_tv_bank)
    TextView marketForfaitingDetailTvBank;
    @BindView(R.id.market_forfaiting_detail_tv_bank_yellow_iv)
    TextView marketForfaitingDetailTvBankYellowIv;
    @BindView(R.id.market_forfaiting_detail_tv_bank_yellow1_ll)
    LinearLayout marketForfaitingDetailTvBankYellow1Ll;
    @BindView(R.id.market_forfaiting_detail_tv_amount)
    TextView marketForfaitingDetailTvAmount;
    @BindView(R.id.market_forfaiting_detail_tv_amount_text)
    TextView marketForfaitingDetailTvAmountText;
    @BindView(R.id.market_forfaiting_detail_ll_jine)
    LinearLayout marketForfaitingDetailLlJine;
    @BindView(R.id.market_forfaiting_detail_tv_type_text)
    TextView marketForfaitingDetailTvTypeText;
    @BindView(R.id.market_forfaiting_detail_ll_type)
    LinearLayout marketForfaitingDetailLlType;
    @BindView(R.id.market_forfaiting_detail_rl_lable1)
    RelativeLayout marketForfaitingDetailRlLable1;
    @BindView(R.id.market_forfaiting_detail_tv_area)
    TextView marketForfaitingDetailTvArea;
    @BindView(R.id.market_forfaiting_detail_tv_line)
    TextView marketForfaitingDetailTvLine;
    @BindView(R.id.market_forfaiting_detail_tv_country)
    TextView marketForfaitingDetailTvCountry;
    @BindView(R.id.market_forfaiting_detail_ll_lable2_area)
    LinearLayout marketForfaitingDetailLlLable2Area;
    @BindView(R.id.market_forfaiting_detail_tv_date_lable)
    TextView marketForfaitingDetailTvDateLable;
    @BindView(R.id.market_forfaiting_detail_tv_date)
    TextView marketForfaitingDetailTvDate;
    @BindView(R.id.market_forfaiting_detail_ll_lable2_date)
    LinearLayout marketForfaitingDetailLlLable2Date;
    @BindView(R.id.market_forfaiting_detail_tv_currency_lable)
    TextView marketForfaitingDetailTvCurrencyLable;
    @BindView(R.id.market_forfaiting_detail_tv_currency)
    TextView marketForfaitingDetailTvCurrency;
    @BindView(R.id.market_forfaiting_detail_ll_currency)
    LinearLayout marketForfaitingDetailLlCurrency;
    @BindView(R.id.market_forfaiting_detail_tv_rate_lable)
    TextView marketForfaitingDetailTvRateLable;
    @BindView(R.id.market_forfaiting_detail_tv_rate)
    TextView marketForfaitingDetailTvRate;
    @BindView(R.id.market_forfaiting_detail_ll_rate)
    LinearLayout marketForfaitingDetailLlRate;
    @BindView(R.id.market_forfaiting_detail_tv_duedate_lable)
    TextView marketForfaitingDetailTvDuedateLable;
    @BindView(R.id.market_forfaiting_detail_tv_duedate)
    TextView marketForfaitingDetailTvDuedate;
    @BindView(R.id.market_forfaiting_detail_ll_duedate)
    LinearLayout marketForfaitingDetailLlDuedate;
    @BindView(R.id.market_forfaiting_detail_ll_lable2_data)
    LinearLayout marketForfaitingDetailLlLable2Data;
    @BindView(R.id.market_forfaiting_detail_tv_unfold_lable)
    TextView marketForfaitingDetailTvUnfoldLable;
    @BindView(R.id.market_forfaiting_detail_iv_unfold)
    ImageView marketForfaitingDetailIvUnfold;
    @BindView(R.id.market_forfaiting_detail_ll_unfold)
    LinearLayout marketForfaitingDetailLlUnfold;
    @BindView(R.id.market_forfaiting_detail_ll_lable2)
    RelativeLayout marketForfaitingDetailLlLable2;

    @BindView(R.id.expandLayout)
    ExpandLayout expandLayout;
    @BindView(R.id.market_forfaiting_detail_iv_line)
    ImageView marketForfaitingDetailIvLine;
    @BindView(R.id.market_forfaiting_detail_tv_creditnumber)
    TextView marketForfaitingDetailTvCreditnumber;
    @BindView(R.id.market_forfaiting_detail_tv2_creditnumber)
    TextView marketForfaitingDetailTv2Creditnumber;
    @BindView(R.id.market_forfaiting_detail_tv_credittype)
    TextView marketForfaitingDetailTvCredittype;
    @BindView(R.id.market_forfaiting_detail_tv2_credittype)
    TextView marketForfaitingDetailTv2Credittype;
    @BindView(R.id.market_forfaiting_detail_tv2_credittype0)
    TextView marketForfaitingDetailTv2Credittype0;
    @BindView(R.id.market_forfaiting_detail_ll_credittype)
    LinearLayout marketForfaitingDetailLlCredittype;
    @BindView(R.id.market_forfaiting_detail_tv_creditdate)
    TextView marketForfaitingDetailTvCreditdate;
    @BindView(R.id.market_forfaiting_detail_tv2_creditdate)
    TextView marketForfaitingDetailTv2Creditdate;
    @BindView(R.id.market_forfaiting_detail_ll_creditdate)
    LinearLayout marketForfaitingDetailLlCreditdate;
    @BindView(R.id.market_forfaiting_detail_tv_iussingbankswift)
    TextView marketForfaitingDetailTvIussingbankswift;
    @BindView(R.id.market_forfaiting_detail_tv2_iussingbankswift)
    TextView marketForfaitingDetailTv2Iussingbankswift;
    @BindView(R.id.market_forfaiting_detail_ll_iussingbankswift)
    LinearLayout marketForfaitingDetailLlIussingbankswift;
    @BindView(R.id.market_forfaiting_detail_tv_iussingbankname)
    TextView marketForfaitingDetailTvIussingbankname;
    @BindView(R.id.market_forfaiting_detail_tv2_iussingbankname)
    TextView marketForfaitingDetailTv2Iussingbankname;
    @BindView(R.id.market_forfaiting_detail_ll_iussingbankname)
    LinearLayout marketForfaitingDetailLlIussingbankname;
    @BindView(R.id.market_forfaiting_detail_tv_acceptingbankswift)
    TextView marketForfaitingDetailTvAcceptingbankswift;
    @BindView(R.id.market_forfaiting_detail_tv2_acceptingbankswift)
    TextView marketForfaitingDetailTv2Acceptingbankswift;
    @BindView(R.id.market_forfaiting_detail_ll_acceptingbankswift)
    LinearLayout marketForfaitingDetailLlAcceptingbankswift;
    @BindView(R.id.market_forfaiting_detail_tv_acceptingbankname)
    TextView marketForfaitingDetailTvAcceptingbankname;
    @BindView(R.id.market_forfaiting_detail_tv2_acceptingbankname)
    TextView marketForfaitingDetailTv2Acceptingbankname;
    @BindView(R.id.market_forfaiting_detail_ll_acceptingbankname)
    LinearLayout marketForfaitingDetailLlAcceptingbankname;
    @BindView(R.id.market_forfaiting_detail_tv_acceptingdate)
    TextView marketForfaitingDetailTvAcceptingdate;
    @BindView(R.id.market_forfaiting_detail_tv2_acceptingdate)
    TextView marketForfaitingDetailTv2Acceptingdate;
    @BindView(R.id.market_forfaiting_detail_ll_acceptingdate)
    LinearLayout marketForfaitingDetailLlAcceptingdate;
    @BindView(R.id.market_forfaiting_detail_tv_paybankswift)
    TextView marketForfaitingDetailTvPaybankswift;
    @BindView(R.id.market_forfaiting_detail_tv2_paybankswift)
    TextView marketForfaitingDetailTv2Paybankswift;
    @BindView(R.id.market_forfaiting_detail_ll_paybankswift)
    LinearLayout marketForfaitingDetailLlPaybankswift;
    @BindView(R.id.market_forfaiting_detail_tv_paybankname)
    TextView marketForfaitingDetailTvPaybankname;
    @BindView(R.id.market_forfaiting_detail_tv2_paybankname)
    TextView marketForfaitingDetailTv2Paybankname;
    @BindView(R.id.market_forfaiting_detail_ll_paybankname)
    LinearLayout marketForfaitingDetailLlPaybankname;
    @BindView(R.id.market_forfaiting_detail_iv_line1)
    ImageView marketForfaitingDetailIvLine1;
    @BindView(R.id.market_forfaiting_detail_tv_blockchain)
    TextView marketForfaitingDetailTvBlockchain;
    @BindView(R.id.market_forfaiting_detail_tv2_blockchain)
    TextView marketForfaitingDetailTv2Blockchain;
    @BindView(R.id.market_forfaiting_detail_ll_blockchain)
    LinearLayout marketForfaitingDetailLlBlockchain;
    @BindView(R.id.market_forfaiting_detail_tv_forfaitingdeal)
    TextView marketForfaitingDetailTvForfaitingdeal;
    @BindView(R.id.market_forfaiting_detail_tv2_forfaitingdeal)
    TextView marketForfaitingDetailTv2Forfaitingdeal;
    @BindView(R.id.market_forfaiting_detail_iv_forfaitingdeal)
    ImageView marketForfaitingDetailIvForfaitingdeal;
    @BindView(R.id.market_forfaiting_detail_ll_forfaitingdeal)
    LinearLayout marketForfaitingDetailLlForfaitingdeal;
    @BindView(R.id.myquote_forfaiting_detail_ll_title)
    LinearLayout myquoteForfaitingDetailLlTitle;
    @BindView(R.id.myquote_forfaiting_detail_tv_name)
    TextView myquoteForfaitingDetailTvName;
    @BindView(R.id.myquote_forfaiting_detail_tv_precent)
    TextView myquoteForfaitingDetailTvPrecent;
    @BindView(R.id.myquote_forfaiting_detail_ll_price)
    LinearLayout myquoteForfaitingDetailLlPrice;
    @BindView(R.id.market_forfaiting_detail_ll_forfaitingdealfile)
    LinearLayout marketForfaitingDetailLlForfaitingdealfile;
    @BindView(R.id.market_forfaiting_detail_ll0)
    LinearLayout marketForfaitingDetailLl0;
    @BindView(R.id.item_forfaiting_detail_rl)
    RelativeLayout itemForfaitingDetailRl;
    @BindView(R.id.myquote_forfaiting_detail_ll_title2)
    LinearLayout myquoteForfaitingDetailLlTitle2;
    @BindView(R.id.myquote_forfaiting_detail_ll_pricelist)
    LinearLayout myquoteForfaitingDetailLlPricelist;
    @BindView(R.id.myquote_forfaiting_detail_ll_price2)
    LinearLayout myquoteForfaitingDetailLlPrice2;
    private String myAssets = "";//是否我发布的 1是 0不是
    MarketForfaitingDetailRes marketForfaitingDetailRes;
    //资产报价列表
    MarketForfaitingOfferListRes marketForfaitingOfferListRes;

    LoginRes.DataBean dataBean1;
    String letterUrl;//协议

    //报价操作开始
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");

    private String assetsType = "1";

    private String assetsId;//详情资产id
    private String priceId;//报价id

    //文件附件列表
    private View fileListView;

    //报价操作结束
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquote_forfaiting_detail);
        ButterKnife.bind(this);
        //获取参数
        assetsId = getIntent().getStringExtra("id");
        myAssets = getIntent().getStringExtra("myAssets");
        priceId = getIntent().getStringExtra("priceId");
//        MyLogger.pLog().i("id:" + assetsId);
        //获取参数结束
        //初始化报价列表
        //初始化报价列表结束
        //默认收缩列表
//        expandLayout.initExpand(false);
        //初始化数据
        initDetailData(assetsId);
        blockChainCompanyRet(assetsId);
    }

    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.market_forfaiting_detail_tv2_blockchain, R.id.market_forfaiting_detail_tv2_forfaitingdeal, R.id.market_forfaiting_detail_iv_forfaitingdeal, R.id.iv_Rtv, R.id.tv_id_number, R.id.market_forfaiting_detail_ll_unfold, R.id.market_forfaiting_detail_iv_unfold, R.id.market_forfaiting_detail_tv_unfold_lable})
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
            case R.id.market_forfaiting_detail_tv2_forfaitingdeal:
            case R.id.market_forfaiting_detail_iv_forfaitingdeal:
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
            case R.id.tv_id_number:
                break;
            case R.id.market_forfaiting_detail_tv2_blockchain:
                DisPatcher.startBlockChainDetailActivity(this, assetsId);
                break;

            case R.id.market_forfaiting_detail_ll_unfold:
            case R.id.market_forfaiting_detail_tv_unfold_lable:
            case R.id.market_forfaiting_detail_iv_unfold:
                expandLayout.toggleExpand();
                if (expandLayout.isExpand()) {
                    marketForfaitingDetailTvUnfoldLable.setText(R.string.market_forfaiting_detail_takeup);
                } else {
                    marketForfaitingDetailTvUnfoldLable.setText(R.string.market_forfaiting_detail_takedown);
                }
                break;
        }
    }

    ////市场行情信用证、详情接口
    public void initDetailData(String id) {
        MyLogger.pLog().i("市场行情信用证、详情接口");
        MarketForfaitingDetailReq marketForfaitingDetailReq = new MarketForfaitingDetailReq();
        marketForfaitingDetailReq.setAssertId(id);
        marketForfaitingDetailReq.setYn("0");
        ActionPresenter.getInstance().marketForfaitingDetailRet(marketForfaitingDetailReq).enqueue(new Callback<MarketForfaitingDetailRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingDetailRes> call, Response<MarketForfaitingDetailRes> response) {
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
    //区块链查询接口

    /**
     * //区块链机构查询接口
     *
     * @POST(Constants.NETPATH.BLOCKCHAINCOMPANY) Call<BlockChainRes> blockChainCompanyRet(@Body RequestBody requestBody);
     * <p>
     * public Call<BlockChainRes> blockChainCompanyRet(BlockChainReq data) {
     * Call<BlockChainRes> blockChainCompanyRet = mApi.blockChainCompanyRet(createRequestBody(data));
     * return blockChainCompanyRet;
     * }
     */
    public void blockChainCompanyRet(String id) {
        MyLogger.pLog().i("区块链查询接口");
        BlockChainReq blockChainReq = new BlockChainReq();
        blockChainReq.setBussId(id);
        ActionPresenter.getInstance().blockChainBussinessRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null && response.body().getData().size() > 0) {
                            if ("1".equals(response.body().getData().get(0).getBussTypeState())) {

                                marketForfaitingDetailTv2Blockchain.setText(R.string.market_forfaiting_detail_stepone);

                            } else if ("2".equals(response.body().getData().get(0).getBussTypeState())) {

                                marketForfaitingDetailTv2Blockchain.setText(R.string.market_forfaiting_detail_steptwo);

                            } else if ("3".equals(response.body().getData().get(0).getBussTypeState())) {

                                marketForfaitingDetailTv2Blockchain.setText(R.string.market_forfaiting_detail_stepthree);

                            }
                        } else {
                            marketForfaitingDetailTv2Blockchain.setText(R.string.market_forfaiting_detail_none);
                            marketForfaitingDetailTv2Blockchain.setEnabled(false);
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


    public void setData(MarketForfaitingDetailRes marketForfaitingDetailRes1) {
        marketForfaitingDetailRes = marketForfaitingDetailRes1;

        MarketForfaitingDetailRes.DataBean dataBean = marketForfaitingDetailRes.getData();

        String yn = "0";
        yn = dataBean.getCompanyOrg().getYn();
        if (StringUtils.isNullObject(dataBean)) {
            MarketForfaitingDetailRes.DataBean.AssetsBean assetsBean = dataBean.getAssets();
            if (StringUtils.isNullObject(assetsBean)) {

                if (StringUtils.isNullObject(assetsBean.getAssetAgreement()) && StringUtils.isNullObject(assetsBean.getAssetAgreement().getST0202()) && !StringUtils.isEmpty(assetsBean.getAssetAgreement().getST0202().getAttachment_url()) && assetsBean.getAssetAgreement().getST0202().getAttachment_url().length() > 10) {
                    letterUrl = assetsBean.getAssetAgreement().getST0202().getAttachment_url();
                    setFileDataList(marketForfaitingDetailLlForfaitingdealfile, letterUrl);
//                    expandLayout.setViewHeight(marketForfaitingDetailLlForfaitingdealfile,expandLayout.getLayoutParams().height);
                } else {
                    marketForfaitingDetailTv2Forfaitingdeal.setTextColor(getResources().getColor(R.color.gray));
                    marketForfaitingDetailIvForfaitingdeal.setVisibility(View.GONE);
                    marketForfaitingDetailLlForfaitingdeal.setVisibility(View.GONE);
                    marketForfaitingDetailLlForfaitingdealfile.setVisibility(View.GONE);
//                    expandLayout = findViewById(R.id.expandLayout);
//                    expandLayout.initExpand(true);
//                    Drawable originBitmapDrawable = ContextCompat.getDrawable(this,
//                            R.drawable.download_icon);
//                    marketForfaitingDetailIvForfaitingdeal.setImageDrawable(SkxDrawableHelper.tintDrawable(originBitmapDrawable, getResources().getColor(R.color.gray)));
                }
                //设置标题
                tvContent.setText(getString(R.string.blockchain_number) + assetsBean.getAssetsNo());
                marketForfaitingDetailTvBank.setText(assetsBean.getTitle());
                String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
                if ("cn".equals(lanage)) {

                    marketForfaitingDetailTvArea.setText(assetsBean.getAreaName());
                    marketForfaitingDetailTvCountry.setText(assetsBean.getCountryName());
                } else if ("en".equals(lanage)) {
                    marketForfaitingDetailTvArea.setText(assetsBean.getEnAreaName());
                    marketForfaitingDetailTvCountry.setText(assetsBean.getEnCountryName());
                }
                marketForfaitingDetailTvDate.setText(assetsBean.getIndateMessage());
                marketForfaitingDetailTvCurrency.setText(assetsBean.getCurrency());
                marketForfaitingDetailTvRate.setText(assetsBean.getDiscountRate() + "%");
                marketForfaitingDetailTvDuedate.setText(assetsBean.getMaturity());
                marketForfaitingDetailTvAmount.setText(assetsBean.getAmount());
                //类型  1 国内信用证 2 国际信用证
                if ("1".equals(assetsBean.getDebtType())) {
                    marketForfaitingDetailTvTypeText.setText(R.string.market_forfaiting_type1);
                } else if ("2".equals(assetsBean.getDebtType())) {
                    marketForfaitingDetailTvTypeText.setText(R.string.market_forfaiting_type2);
                } else {
                    marketForfaitingDetailLlType.setVisibility(View.GONE);
                }
                myAssets = assetsBean.getMyAssets();

                //展开内容
                marketForfaitingDetailTv2Creditnumber.setText(assetsBean.getLcNo());
                if ("1".equals(assetsBean.getDebtType() + "")) {
                    marketForfaitingDetailTv2Credittype0.setText(getString(R.string.market_forfaiting_type1));
                } else if ("2".equals(assetsBean.getDebtType() + "")) {
                    marketForfaitingDetailTv2Credittype0.setText(getString(R.string.market_forfaiting_type2));
                }

                marketForfaitingDetailTv2Credittype.setText(assetsBean.getCreditType());
                marketForfaitingDetailTv2Creditdate.setText(assetsBean.getIssuingDate());
                marketForfaitingDetailTv2Iussingbankswift.setText(assetsBean.getOpenSwift());
                marketForfaitingDetailTv2Iussingbankname.setText(assetsBean.getOpenFullName());
                marketForfaitingDetailTv2Acceptingbankswift.setText(assetsBean.getTenderSwift());
                marketForfaitingDetailTv2Acceptingbankname.setText(assetsBean.getTenderFullName());
                marketForfaitingDetailTv2Acceptingdate.setText(assetsBean.getAcceptanceDate());//承兑日期
                marketForfaitingDetailTv2Paybankswift.setText(assetsBean.getReimbursingBankSwift());
                marketForfaitingDetailTv2Paybankname.setText(assetsBean.getReimbursingBankName());
                //交易完成130请求报价列表
                if ("130".equals(assetsBean.getRecheckState())) {
                    initDetailOfferListData2(assetsId);
                }
                //报价中104 或者 108 120  125请求报价列表
                if ("104".equals(assetsBean.getRecheckState())||"108".equals(assetsBean.getRecheckState())||"120".equals(assetsBean.getRecheckState())||"125".equals(assetsBean.getRecheckState())) {
                    if(!"0".equals(yn)) {
                        myQuotePriseListRet(priceId);
                    }
                }

            }

        }

        //默认收缩列表
        expandLayout.initExpand(false);
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
//        showWaitDialog();
        ActionPresenter.getInstance().downloadFileWithDynamicUrlSync(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                MyLogger.pLog().d("LoginRes："  + response.body().toString());
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

    @Override
    protected void onResume() {
        super.onResume();
//        refresh();
    }


    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    MyLogger.pLog().d("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
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
        marketForfaitingOfferListReq.setTradingType("1");
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
                        DisPatcher.startPicturePreviewActivity(MyQuoteForfaitingDetailActivity.this, url);
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
