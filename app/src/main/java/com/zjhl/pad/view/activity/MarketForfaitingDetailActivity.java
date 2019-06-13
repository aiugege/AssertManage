package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.BigDecimalUtil;
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.MarketForfaitingDetailListAdapter;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.ExpandLayout;
import com.zjhl.pad.view.views.RejectDialog;
import com.zjhl.pad.view.views.RingProgressBar;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
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
public class MarketForfaitingDetailActivity extends BaseActivity {


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
    @BindView(R.id.market_forfaiting_detail_tv_seller)
    TextView marketForfaitingDetailTvSeller;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history)
    TextView marketForfaitingDetailTvSellerLeftHistory;
    @BindView(R.id.ringProgressBar4)
    RingProgressBar ringProgressBar4;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_chart)
    TextView marketForfaitingDetailTvSellerLeftHistoryChart;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_chartwan)
    TextView marketForfaitingDetailTvSellerLeftHistoryChartwan;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_chartpercent)
    TextView marketForfaitingDetailTvSellerLeftHistoryChartpercent;
    @BindView(R.id.market_forfaiting_detail_rl_seller_left_precent)
    LinearLayout marketForfaitingDetailRlSellerLeftPrecent;
    @BindView(R.id.market_forfaiting_detail_ll_seller_left_history_chart)
    RelativeLayout marketForfaitingDetailLlSellerLeftHistoryChart;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_count)
    TextView marketForfaitingDetailTvSellerLeftHistoryCount;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_totalcount)
    TextView marketForfaitingDetailTvSellerLeftHistoryTotalcount;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_percent)
    LinearLayout marketForfaitingDetailTvSellerLeftPercent;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_amount)
    TextView marketForfaitingDetailTvSellerLeftHistoryAmount;
    @BindView(R.id.market_forfaiting_detail_tv_seller_left_history_amountwan)
    TextView marketForfaitingDetailTvSellerLeftHistoryAmountwan;
    @BindView(R.id.market_forfaiting_detail_tv_seller_history_lable)
    TextView marketForfaitingDetailTvSellerHistoryLable;
    @BindView(R.id.market_forfaiting_detail_ll_seller_left_history_amount)
    LinearLayout marketForfaitingDetailLlSellerLeftHistoryAmount;
    @BindView(R.id.market_forfaiting_detail_ll_seller_left)
    RelativeLayout marketForfaitingDetailLlSellerLeft;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_history)
    TextView marketForfaitingDetailTvSellerRightHistory;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_company)
    TextView marketForfaitingDetailTvSellerRightCompany;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_zhizhaolable)
    TextView marketForfaitingDetailTvSellerRightZhizhaolable;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_zhizhaocode)
    TextView marketForfaitingDetailTvSellerRightZhizhaocode;
    @BindView(R.id.market_forfaiting_detail_rl_seller_right_zhizhao)
    RelativeLayout marketForfaitingDetailRlSellerRightZhizhao;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_swiftlable)
    TextView marketForfaitingDetailTvSellerRightSwiftlable;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_swiftcode)
    TextView marketForfaitingDetailTvSellerRightSwiftcode;
    @BindView(R.id.market_forfaiting_detail_rl_seller_right_swift)
    RelativeLayout marketForfaitingDetailRlSellerRightSwift;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_arealable)
    TextView marketForfaitingDetailTvSellerRightArealable;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_areacode)
    TextView marketForfaitingDetailTvSellerRightAreacode;
    @BindView(R.id.market_forfaiting_detail_rl_seller_right_area)
    RelativeLayout marketForfaitingDetailRlSellerRightArea;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_namelable)
    TextView marketForfaitingDetailTvSellerRightNamelable;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_namemiddle)
    TextView marketForfaitingDetailTvSellerRightNamemiddle;
    @BindView(R.id.market_forfaiting_detail_tv_seller_right_namecode)
    TextView marketForfaitingDetailTvSellerRightNamecode;
    @BindView(R.id.market_forfaiting_detail_rl_seller_right_name)
    RelativeLayout marketForfaitingDetailRlSellerRightName;
    @BindView(R.id.market_forfaiting_detail_ll_seller_right)
    LinearLayout marketForfaitingDetailLlSellerRight;
    @BindView(R.id.market_forfaiting_detail_ll_seller)
    LinearLayout marketForfaitingDetailLlSeller;
    @BindView(R.id.market_forfaiting_detail_tv_quote)
    TextView marketForfaitingDetailTvQuote;
    @BindView(R.id.market_forfaiting_detail_rv)
    RecyclerView marketForfaitingDetailRv;
    @BindView(R.id.market_forfaiting_detail_sl)
    SwipeRefreshLayout marketForfaitingDetailSl;
    @BindView(R.id.market_forfaiting_detail_tv_submit)
    TextView marketForfaitingDetailTvSubmit;
    @BindView(R.id.expandLayout)
    ExpandLayout expandLayout;
    @BindView(R.id.market_forfaiting_detail_iv_line)
    ImageView marketForfaitingDetailIvLine;
    @BindView(R.id.market_forfaiting_detail_tv_creditnumber)
    TextView marketForfaitingDetailTvCreditnumber;
    @BindView(R.id.market_forfaiting_detail_tv2_creditnumber)
    TextView marketForfaitingDetailTv2Creditnumber;
    @BindView(R.id.market_forfaiting_detail_ll_creditnumber)
    LinearLayout marketForfaitingDetailLlCreditnumber;
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
    @BindView(R.id.market_forfaiting_detail_rg)
    RadioGroup marketForfaitingDetailRg;
    @BindView(R.id.progressBarHorizontal)
    ProgressBar progressBarHorizontal;
    @BindView(R.id.market_forfaiting_detail_ll)
    LinearLayout marketForfaitingDetailLl;
    @BindView(R.id.market_forfaiting_detail_ll_forfaitingdealfile)
    LinearLayout marketForfaitingDetailLlForfaitingdealfile;
    //    private String id = "";//详情资产id
    private String myAssets = "";//是否我发布的 1是 0不是
    MarketForfaitingDetailRes marketForfaitingDetailRes;
    //资产报价列表
    MarketForfaitingOfferListRes marketForfaitingOfferListRes;
    //成交进度计算
    private int mProgress = 0;
    MarketForfaitingDetailListAdapter marketForfaitingDetailListAdapter;
    private int mNextRequestPage = 1;
    //当前资产是否是自己发布的  1是 显示撮合 0不是显示报价  默认0
    private int isme = -1;
    private String isSelect = "";

    //文件附件列表
    private View fileListView;
    LoginRes loginRes;
    LoginRes.DataBean dataBean1;
    String letterUrl;//协议

    //报价操作开始
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");

    private String assetsType = "1";

    private String priceId;
    private String assetsId;//详情资产id
    private MarketForfaitingOfferReq marketForfaitingOfferReq;
    int isSelectSell = 0;//1有经办选择的报价了 0 没有
    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;

    //报价操作结束
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forfaiting_detail);
        ButterKnife.bind(this);
        //获取参数
        assetsId = getIntent().getStringExtra("id");
        myAssets = getIntent().getStringExtra("myAssets");
        MyLogger.pLog().i("id:" + assetsId);
        /*if ("1".equals(myAssets)) {
            marketForfaitingDetailTvSubmit.setText("确认撮合");
            marketForfaitingDetailTvSubmit.setVisibility(View.VISIBLE);
        }else{
            marketForfaitingDetailTvSubmit.setVisibility(View.GONE);
        }*/
        //获取参数结束
        //初始化报价列表
        marketForfaitingDetailRv.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
 /*        initRefreshLayout();
       initAdapter();
//        initDetailOfferListData(id);
        initItemListener();*/
        //初始化报价列表结束
        //默认收缩列表
//        expandLayout.initExpand(false);
        //初始化数据
        initDetailData(assetsId);
        blockChainCompanyRet(assetsId);
//        loginRes = (LoginRes) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_OBJECT, new LoginRes());
//        if(StringUtils.isNullObject(loginRes)) {
//            dataBean1 = loginRes.getData();
//        }
    }

    private void initAdapter() {
        List<MarketForfaitingOfferListRes.DataBean> data = new ArrayList<>();
        marketForfaitingDetailListAdapter = new MarketForfaitingDetailListAdapter(data, myAssets);
        marketForfaitingDetailListAdapter.openLoadAnimation();
        marketForfaitingDetailRv.setAdapter(marketForfaitingDetailListAdapter);
    }

    public void initItemListener() {
        marketForfaitingDetailListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                RadioButton radioButton = (RadioButton)view.findViewById(R.id.item_forfaiting_detail_offer_list_rb);
//                radioButton.setChecked(true);
//                marketForfaitingDetailListAdapter.notifyDataSetChanged();
                //报价已经有107经办确认 就不能选择了 isSelectSell = 1
                if (isSelectSell == 0) {
                    marketForfaitingDetailListAdapter.onItemClick(adapter, view, position);
                }
                //获取当前选中的item的id 用于提交报价撮合
                isSelect = ((MarketForfaitingOfferListRes.DataBean) adapter.getItem(position)).getId() + "";
                MyLogger.pLog().d("onItemClick: " + ((MarketForfaitingOfferListRes.DataBean) adapter.getItem(position)).getId());
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        marketForfaitingDetailListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        marketForfaitingDetailListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildClick: ");
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        marketForfaitingDetailListAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildLongClick: ");
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemChildLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initRefreshLayout() {
        //启用刷新
        marketForfaitingDetailSl.setRefreshing(false);
        //禁用下拉刷新
//        marketForfaitingSl.setEnabled(false);
        marketForfaitingDetailSl.setColorSchemeResources(R.color.blue);
        marketForfaitingDetailSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        marketForfaitingDetailListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initDetailOfferListData(assetsId);

    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            marketForfaitingDetailListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                marketForfaitingDetailListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            marketForfaitingDetailListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            marketForfaitingDetailListAdapter.loadMoreComplete();
        }
    }


    //  是否显示底部按钮 确认撮合
    public void isShowButton1(List<MarketForfaitingOfferListRes.DataBean> list) {
        String companyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
        int isshowbutton = 1;//0 不显示 1显示
//        if (StringUtils.isNullObject(dataBean1)) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNullObject(list.get(i))) {
                    //'是否成交 1 待成交 2 成交 0 未成交(默认)'
//                    if ("107".equals(list.get(i).getPriceState())) {
                    if (!StringUtils.isEmpty(companyid) && companyid.equals(list.get(i).getsOrgId()) && "1".equals(list.get(i).getIsStruck())) {
                        isshowbutton = 0;
                        isSelectSell = 1;
                    }
                }
            }
            if ("2".equals(userType)) {
                if (isshowbutton == 1 && isme == 1) {
                    marketForfaitingDetailTvSubmit.setVisibility(View.VISIBLE);
                } /*else {
                    marketForfaitingDetailTvSubmit.setVisibility(View.GONE);
                }*/
            }
        }
//        }
    }

    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.market_forfaiting_detail_tv2_blockchain, R.id.market_forfaiting_detail_tv2_forfaitingdeal, R.id.market_forfaiting_detail_iv_forfaitingdeal, R.id.iv_Rtv, R.id.tv_id_number, R.id.market_forfaiting_detail_sl, R.id.market_forfaiting_detail_tv_submit, R.id.market_forfaiting_detail_ll_unfold, R.id.market_forfaiting_detail_iv_unfold, R.id.market_forfaiting_detail_tv_unfold_lable})
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
            case R.id.market_forfaiting_detail_sl:
                break;
            case R.id.market_forfaiting_detail_tv_submit:
                //未增加前置判断是否跳转  或者是否是报价人等判断
                if (isme == 0) {
                    //报价接口
                    DisPatcher.startMarketForfaitingOfferActivity(this, marketForfaitingDetailRes);
                } else {
                    //撮合接口
                    if (!StringUtils.isEmpty(isSelect)) {
                        initSureOrCancelDialogView("",getString(R.string.issue_forfaiting_sell_yesorno));
//                        confirmOfferData(isSelect);
                    } else {
                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_choice));
                    }

                    //报价查看详情
//                    if ("2".equals(userType)) {
//                        showHandleOfferDetailDialog(assetsId, 0);
//
//                    } else if ("3".equals(userType)) {
//                        showReviewOfferDetailDialog(assetsId, assetsType);
//                    }
                }
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
//        ActionPresenter.getInstance().blockChainCompanyRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
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

    //市场行情信用证、详情报价列表接口
    public void initDetailOfferListData(String id) {
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

//                        MarketForfaitingOfferListRes.DataBean b = new MarketForfaitingOfferListRes.DataBean();
//                        list.add(b);
//                        list.add(new MarketForfaitingOfferListRes.DataBean());
//                        list.add(new MarketForfaitingOfferListRes.DataBean());

                            //是否含有当前报价机构
                            setData(true, list);
                            isShowButton1(list);
//                        setDataList(response.body().getData());
                            marketForfaitingDetailListAdapter.setEnableLoadMore(true);
                            marketForfaitingDetailSl.setRefreshing(false);
//                            if (list == null || list.size() == 0) {
//                                marketForfaitingDetailTvQuote.setVisibility(View.GONE);
//                                marketForfaitingDetailSl.setVisibility(View.GONE);
//                                marketForfaitingDetailLl.setVisibility(View.GONE);
//                            }
                        }
//                        marketForfaitingDetailTvQuote.setVisibility(View.GONE);
//                        marketForfaitingDetailSl.setVisibility(View.GONE);
//                        marketForfaitingDetailLl.setVisibility(View.GONE);
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        MyLogger.pLog().e(response.body().getMessage());
                        finish();
                    } else {
                        marketForfaitingDetailListAdapter.setEnableLoadMore(true);
                        marketForfaitingDetailSl.setRefreshing(false);
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


    //市场 福费廷 卖家 确认撮合接口
    public void confirmOfferData(String id) {
        MyLogger.pLog().i("市场 福费廷 卖家 确认撮合接口");
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(id);
        ActionPresenter.getInstance().marketForfaitingOfferConfirmRet(marketForfaitingOfferReq).enqueue(new Callback<MarketForfaitingDetailRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingDetailRes> call, Response<MarketForfaitingDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
                        //确认撮合成功！
                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));
                        marketForfaitingDetailTvSubmit.setVisibility(View.GONE);
                        refresh();
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
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

    public void setData(MarketForfaitingDetailRes marketForfaitingDetailRes1) {
        marketForfaitingDetailRes = marketForfaitingDetailRes1;

        MarketForfaitingDetailRes.DataBean dataBean = marketForfaitingDetailRes.getData();

        if (StringUtils.isNullObject(dataBean)) {
            MarketForfaitingDetailRes.DataBean.AssetsBean assetsBean = dataBean.getAssets();
            if (StringUtils.isNullObject(assetsBean)) {
                if (StringUtils.isNullObject(assetsBean.getAssetAgreement()) && StringUtils.isNullObject(assetsBean.getAssetAgreement().getST0202()) && !StringUtils.isEmpty(assetsBean.getAssetAgreement().getST0202().getAttachment_url()) && assetsBean.getAssetAgreement().getST0202().getAttachment_url().length() > 10) {
                    letterUrl = assetsBean.getAssetAgreement().getST0202().getAttachment_url();
                    setFileDataList(marketForfaitingDetailLlForfaitingdealfile,letterUrl);
                } else {
                    marketForfaitingDetailTv2Forfaitingdeal.setTextColor(getResources().getColor(R.color.gray));
                    marketForfaitingDetailIvForfaitingdeal.setVisibility(View.GONE);
                    marketForfaitingDetailLlForfaitingdealfile.setVisibility(View.GONE);
                    marketForfaitingDetailLlForfaitingdeal.setVisibility(View.GONE);
//                    Drawable originBitmapDrawable = ContextCompat.getDrawable(this,
//                            R.drawable.download_icon);
//                    marketForfaitingDetailIvForfaitingdeal.setImageDrawable(SkxDrawableHelper.tintDrawable(originBitmapDrawable, getResources().getColor(R.color.gray)));
                }
                //设置标题
                tvContent.setText(getString(R.string.blockchain_number)+assetsBean.getAssetsNo());
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
                //我发布的显示撮合按钮  1-是，0-否  是否报价按钮显示在拿到列表后再判断是不是当前报价人
                if ("1".equals(assetsBean.getMyAssets())) {
//                    marketForfaitingDetailTvSubmit.setVisibility(View.GONE);
                    //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
                    if ("2".equals(userType) && "104".equals(assetsBean.getRecheckState())) {
//                        marketForfaitingDetailTvSubmit.setVisibility(View.VISIBLE);
                        marketForfaitingDetailTvSubmit.setText(R.string.market_forfaiting_detail_make_sure);
                        isme = 1;
                    }
                } else {
                    ///** 是否报价 */ 1-已报价,0-未报价
                    if ("2".equals(userType) && "104".equals(assetsBean.getRecheckState())&&"0".equals(assetsBean.getIsnPrice())) {
                        marketForfaitingDetailTvSubmit.setText(R.string.market_forfaiting_detail_submit_price);
                        //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
                        marketForfaitingDetailTvSubmit.setVisibility(View.VISIBLE);
                        isme = 0;
                    }
                }
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
                //拿到资产id请求报价列表
//                initDetailOfferListData(assetsBean.getId()+"");

            }
            MarketForfaitingDetailRes.DataBean.CompanyOrgBean companyOrgBean = dataBean.getCompanyOrg();
            if (StringUtils.isNullObject(companyOrgBean)) {
                marketForfaitingDetailTvSellerRightCompany.setText(companyOrgBean.getCompanyName());
                marketForfaitingDetailTvSellerRightZhizhaocode.setText(companyOrgBean.getLicenseNo());
                marketForfaitingDetailTvSellerRightSwiftcode.setText(companyOrgBean.getSwiftCode());
                marketForfaitingDetailTvSellerRightAreacode.setText(companyOrgBean.getCompanyDomicile());
                marketForfaitingDetailTvSellerRightNamelable.setText(companyOrgBean.getContactName());
                marketForfaitingDetailTvSellerRightNamecode.setText(companyOrgBean.getContactTel());
            }
            //卖方信息
            if(!StringUtils.isEmpty(StringUtils.nullStrToEmpty(dataBean.getSuccessSum()))){
                marketForfaitingDetailTvSellerLeftHistoryCount.setText(StringUtils.nullStrToEmpty(dataBean.getSuccessSum()) + "");
            }
            marketForfaitingDetailTvSellerLeftHistoryTotalcount.setText(dataBean.getTradeSum() + "");
//            marketForfaitingDetailTvDate.setText(dataBean.getTradeSum());
            marketForfaitingDetailTvSellerLeftHistoryChart.setText(dataBean.getSuccessRate());
            marketForfaitingDetailTvSellerLeftHistoryAmount.setText(dataBean.getSuccessAmount());

            //设置百分比
            if (!StringUtils.isEmpty(dataBean.getSuccessRate())) {
                String rate = dataBean.getSuccessRate();
                NumberFormat nf = NumberFormat.getPercentInstance();
                Number number = 0;
                try {
                    number = nf.parse(rate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //模拟进度
                final int finalNumber = BigDecimalUtil.getInt(number.doubleValue() * 100);
                MyLogger.pLog().e("finalNumber=" + finalNumber);
//                ringProgressBar4.setProgress(finalNumber);
                progressBarHorizontal.setProgress(finalNumber);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (mProgress < finalNumber) {
//                            mProgress += 10;
//                            ringProgressBar4.setProgress(mProgress);
//                            try {
//                                Thread.sleep(200);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }).start();
            }
        }
        initAdapter();
        initRefreshLayout();
        initItemListener();
        refresh();
        expandLayout.initExpand(false);
    }

    //    //    public void setDataList(MarketForfaitingOfferListRes marketForfaitingOfferListRes1) {
//    public void setDataList(List<MarketForfaitingOfferListRes.DataBean> dataList) {
//        marketForfaitingDetailRg.removeAllViews();
////        marketForfaitingOfferListRes = marketForfaitingOfferListRes1;
//        for (MarketForfaitingOfferListRes.DataBean dataList1 : dataList) {
//            offerListView = LayoutInflater.from(this).inflate(R.layout.item_forfaiting_detail_offer_list, null);
//            RadioButton radioButton = (RadioButton) offerListView.findViewById(R.id.item_forfaiting_detail_offer_list_rb);
//            TextView textViewcompanyname = (TextView) offerListView.findViewById(R.id.item_forfaiting_detail_offer_list_companyname);
//            TextView textdate = (TextView) offerListView.findViewById(R.id.item_forfaiting_detail_offer_list_date);
//            TextView textrate = (TextView) offerListView.findViewById(R.id.item_forfaiting_detail_offer_list_rate);
//            textViewcompanyname.setText(dataList1.getbOrgName());
////            marketForfaitingDetailRg.addView(radioButton);
//            textdate.setText(dataList1.getStruckDate());
//            textrate.setText(dataList1.getDiscountRate());
//            marketForfaitingDetailRg.addView(offerListView);
//        }
//    }

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
                        DisPatcher.startPicturePreviewActivity(MarketForfaitingDetailActivity.this, url);
                    }
                }
            });
            ((LinearLayout) view).addView(fileListView);
        }
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

                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_reject));
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

    @Override
    protected void onResume() {
        super.onResume();
//        refresh();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==111) {
            if (requestCode == 666) {
                marketForfaitingDetailTvSubmit.setVisibility(View.GONE);
            }
        }
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
                confirmOfferData(isSelect);
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
