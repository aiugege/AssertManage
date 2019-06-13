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
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.MarketFactoringDetailListAdapter;
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
public class MarketFactoringDetailActivity extends BaseActivity {


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
    @BindView(R.id.market_factoring_detail_tv_seller)
    TextView marketFactoringDetailTvSeller;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history)
    TextView marketFactoringDetailTvSellerLeftHistory;
    @BindView(R.id.ringProgressBar4)
    RingProgressBar ringProgressBar4;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_chart)
    TextView marketFactoringDetailTvSellerLeftHistoryChart;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_chartwan)
    TextView marketFactoringDetailTvSellerLeftHistoryChartwan;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_chartpercent)
    TextView marketFactoringDetailTvSellerLeftHistoryChartpercent;
    @BindView(R.id.market_factoring_detail_rl_seller_left_precent)
    LinearLayout marketFactoringDetailRlSellerLeftPrecent;
    @BindView(R.id.market_factoring_detail_ll_seller_left_history_chart)
    RelativeLayout marketFactoringDetailLlSellerLeftHistoryChart;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_count)
    TextView marketFactoringDetailTvSellerLeftHistoryCount;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_totalcount)
    TextView marketFactoringDetailTvSellerLeftHistoryTotalcount;
    @BindView(R.id.market_factoring_detail_tv_seller_left_percent)
    LinearLayout marketFactoringDetailTvSellerLeftPercent;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_amount)
    TextView marketFactoringDetailTvSellerLeftHistoryAmount;
    @BindView(R.id.market_factoring_detail_tv_seller_left_history_amountwan)
    TextView marketFactoringDetailTvSellerLeftHistoryAmountwan;
    @BindView(R.id.market_factoring_detail_tv_seller_history_lable)
    TextView marketFactoringDetailTvSellerHistoryLable;
    @BindView(R.id.market_factoring_detail_ll_seller_left_history_amount)
    LinearLayout marketFactoringDetailLlSellerLeftHistoryAmount;
    @BindView(R.id.market_factoring_detail_ll_seller_left)
    RelativeLayout marketFactoringDetailLlSellerLeft;
    @BindView(R.id.market_factoring_detail_tv_seller_right_history)
    TextView marketFactoringDetailTvSellerRightHistory;
    @BindView(R.id.market_factoring_detail_tv_seller_right_company)
    TextView marketFactoringDetailTvSellerRightCompany;
    @BindView(R.id.market_factoring_detail_tv_seller_right_zhizhaolable)
    TextView marketFactoringDetailTvSellerRightZhizhaolable;
    @BindView(R.id.market_factoring_detail_tv_seller_right_zhizhaocode)
    TextView marketFactoringDetailTvSellerRightZhizhaocode;
    @BindView(R.id.market_factoring_detail_rl_seller_right_zhizhao)
    RelativeLayout marketFactoringDetailRlSellerRightZhizhao;
    @BindView(R.id.market_factoring_detail_tv_seller_right_swiftlable)
    TextView marketFactoringDetailTvSellerRightSwiftlable;
    @BindView(R.id.market_factoring_detail_tv_seller_right_swiftcode)
    TextView marketFactoringDetailTvSellerRightSwiftcode;
    @BindView(R.id.market_factoring_detail_rl_seller_right_swift)
    RelativeLayout marketFactoringDetailRlSellerRightSwift;
    @BindView(R.id.market_factoring_detail_tv_seller_right_arealable)
    TextView marketFactoringDetailTvSellerRightArealable;
    @BindView(R.id.market_factoring_detail_tv_seller_right_areacode)
    TextView marketFactoringDetailTvSellerRightAreacode;
    @BindView(R.id.market_factoring_detail_rl_seller_right_area)
    RelativeLayout marketFactoringDetailRlSellerRightArea;
    @BindView(R.id.market_factoring_detail_tv_seller_right_namelable)
    TextView marketFactoringDetailTvSellerRightNamelable;
    @BindView(R.id.market_factoring_detail_tv_seller_right_namemiddle)
    TextView marketFactoringDetailTvSellerRightNamemiddle;
    @BindView(R.id.market_factoring_detail_tv_seller_right_namecode)
    TextView marketFactoringDetailTvSellerRightNamecode;
    @BindView(R.id.market_factoring_detail_rl_seller_right_name)
    RelativeLayout marketFactoringDetailRlSellerRightName;
    @BindView(R.id.market_factoring_detail_ll_seller_right)
    LinearLayout marketFactoringDetailLlSellerRight;
    @BindView(R.id.market_factoring_detail_ll_seller)
    LinearLayout marketFactoringDetailLlSeller;
    @BindView(R.id.market_factoring_detail_tv_quote)
    TextView marketFactoringDetailTvQuote;
    @BindView(R.id.market_factoring_detail_rg)
    RadioGroup marketFactoringDetailRg;
    @BindView(R.id.market_factoring_detail_rv)
    RecyclerView marketFactoringDetailRv;
    @BindView(R.id.market_factoring_detail_sl)
    SwipeRefreshLayout marketFactoringDetailSl;
    @BindView(R.id.market_factoring_detail_tv_submit)
    TextView marketFactoringDetailTvSubmit;
    @BindView(R.id.progressBarHorizontal)
    ProgressBar progressBarHorizontal;
    @BindView(R.id.market_factoring_detail_ll)
    LinearLayout marketFactoringDetailLl;
    @BindView(R.id.market_factoring_detail_ll_factoringdealfile)
    LinearLayout marketFactoringDetailLlFactoringdealfile;
    //    private String id = "";//详情id
    private String myAssets = "";//是否我发布的 1是 0不是
    MarketFactoringDetailRes marketFactoringDetailRes;
    //资产报价列表
    MarketForfaitingOfferListRes marketForfaitingOfferListRes;
    //成交进度计算
    private int mProgress = 0;
    MarketFactoringDetailListAdapter marketFactoringDetailListAdapter;
    private int mNextRequestPage = 1;
    //当前资产是否是自己发布的  1是 显示撮合 0不是显示报价  默认0
    private int isme = -1;
    private String isSelect = "";

    //文件附件列表
    private View fileListView;
    LoginRes loginRes;
    LoginRes.DataBean dataBean1;
    //报价操作开始
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");

    private String assetsType = "2";

    private String priceId;
    private String assetsId;//详情资产id
    private MarketForfaitingOfferReq marketForfaitingOfferReq;

    int isSelectSell = 0;//1有经办选择的报价了 0 没有
    private String letterUrl;//财务报表

    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;
    //报价操作结束
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoring_detail);
        ButterKnife.bind(this);
        //获取参数
        assetsId = getIntent().getStringExtra("id");
        myAssets = getIntent().getStringExtra("myAssets");
        MyLogger.pLog().i("assetsId:" + assetsId);
//        if ("1".equals(myAssets)) {
//            marketFactoringDetailTvSubmit.setText("确认撮合");
//        } else {
//            marketFactoringDetailTvSubmit.setVisibility(View.GONE);
//        }
        //获取参数结束
        //初始化报价列表
        marketFactoringDetailRv.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
/*         initRefreshLayout();
       initAdapter();
//        initDetailOfferListData(id);
        initItemListener();*/
        //初始化报价列表结束
        //默认收缩列表
        expandLayout.initExpand(false);
        //初始化数据
        initDetailData(assetsId);
        loginRes = (LoginRes) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_OBJECT, null);
        if (StringUtils.isNullObject(loginRes)) {
            dataBean1 = loginRes.getData();
        }
    }

    private void initAdapter() {
        List<MarketForfaitingOfferListRes.DataBean> data = new ArrayList<>();
        marketFactoringDetailListAdapter = new MarketFactoringDetailListAdapter(data, myAssets);
        marketFactoringDetailListAdapter.openLoadAnimation();
        marketFactoringDetailRv.setAdapter(marketFactoringDetailListAdapter);
    }

    public void initItemListener() {
        marketFactoringDetailListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                RadioButton radioButton = (RadioButton)view.findViewById(R.id.item_Factoring_detail_offer_list_rb);
//                radioButton.setChecked(true);
//                marketFactoringDetailListAdapter.notifyDataSetChanged();
                //报价已经有107经办确认 就不能选择了 isSelectSell = 1
                if (isSelectSell == 0) {
                    marketFactoringDetailListAdapter.onItemClick(adapter, view, position);
                }
                //获取当前选中的item的id 用于提交报价撮合
                isSelect = ((MarketForfaitingOfferListRes.DataBean) adapter.getItem(position)).getId() + "";
                MyLogger.pLog().d("onItemClick: " + ((MarketForfaitingOfferListRes.DataBean) adapter.getItem(position)).getId());
//                Toast.makeText(MarketForfaitingDetailActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        marketFactoringDetailListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
//                Toast.makeText(MarketFactoringDetailActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        marketFactoringDetailListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildClick: ");
//                Toast.makeText(MarketFactoringDetailActivity.this, "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        marketFactoringDetailListAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildLongClick: ");
//                Toast.makeText(MarketFactoringDetailActivity.this, "onItemChildLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initRefreshLayout() {
        //启用刷新
        marketFactoringDetailSl.setRefreshing(false);
        //禁用下拉刷新
//        marketFactoringSl.setEnabled(false);
        marketFactoringDetailSl.setColorSchemeResources(R.color.blue);
        marketFactoringDetailSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        marketFactoringDetailListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initDetailOfferListData(assetsId);

    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            marketFactoringDetailListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                marketFactoringDetailListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            marketFactoringDetailListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            marketFactoringDetailListAdapter.loadMoreComplete();
        }
    }


    //  是否显示底部按钮 确认撮合
    public void isShowButton1(List<MarketForfaitingOfferListRes.DataBean> list) {
        String companyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        int isshowbutton = 1;//0 不显示 1显示
        //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
//        if (StringUtils.isNullObject(dataBean1)) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNullObject(list.get(i))) {
//                    if (!companyid.equals(list.get(i).getbOrgId())) {
//                    if ("107".equals(list.get(i).getPriceState())) {
                    if (!StringUtils.isEmpty(companyid) && companyid.equals(list.get(i).getsOrgId()) && "1".equals(list.get(i).getIsStruck())) {
                        isshowbutton = 0;
                        isSelectSell = 1;
                    }
//                    }
                }
            }
            if ("2".equals(userType)) {
                if (isshowbutton == 1 && isme == 1) {
                    marketFactoringDetailTvSubmit.setVisibility(View.VISIBLE);
                }/* else {
                    marketFactoringDetailTvSubmit.setVisibility(View.GONE);
                }*/
            }
        }
//        }
    }

    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.iv_Rtv, R.id.tv_id_number, R.id.market_factoring_detail_tv_submit, R.id.market_factoring_detail_tv2_factoringdeal, R.id.market_factoring_detail_iv_factoringdeal})
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
            case R.id.market_factoring_detail_tv_submit:
                //未增加前置判断是否跳转  或者是否是报价人等判断
                if (isme == 0) {
                    //报价接口
                    DisPatcher.startMarketFactoringOfferActivity(this, marketFactoringDetailRes);
                } else {
//                    //撮合接口
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

    //市场行情保理、详情报价列表接口
    public void initDetailOfferListData(String id) {
        MyLogger.pLog().i("市场行情保理、详情报价列表接口");
        showWaitDialog();
        MarketForfaitingOfferListReq marketForfaitingOfferListReq = new MarketForfaitingOfferListReq();
        marketForfaitingOfferListReq.setAssertId(id);
        //资产类型1-福费廷 2-保理
        marketForfaitingOfferListReq.setTradingType("2");
//        ActionPresenter.getInstance().marketForfaitingOfferListRet(marketForfaitingOfferListReq).enqueue(new Callback<MarketForfaitingOfferListRes>() {
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
                            marketFactoringDetailListAdapter.setEnableLoadMore(true);
                            marketFactoringDetailSl.setRefreshing(false);
//                            if (list == null || list.size() == 0) {
//                                marketFactoringDetailTvQuote.setVisibility(View.GONE);
//                                marketFactoringDetailSl.setVisibility(View.GONE);
//                                marketFactoringDetailLl.setVisibility(View.GONE);
//                            }
                        }
//                        marketFactoringDetailTvQuote.setVisibility(View.GONE);
//                        marketFactoringDetailSl.setVisibility(View.GONE);
//                        marketFactoringDetailLl.setVisibility(View.GONE);
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        MyLogger.pLog().e(response.body().getMessage());
                        finish();
                    } else {
                        marketFactoringDetailListAdapter.setEnableLoadMore(true);
                        marketFactoringDetailSl.setRefreshing(false);
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

    //市场 保理 卖家 确认撮合接口
    public void confirmOfferData(String id) {
        MyLogger.pLog().i("市场 保理 卖家 确认撮合接口");
        showWaitDialog();
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId(id);
        ActionPresenter.getInstance().marketForfaitingOfferConfirmRet(marketForfaitingOfferReq).enqueue(new Callback<MarketForfaitingDetailRes>() {
            @Override
            public void onResponse(Call<MarketForfaitingDetailRes> call, Response<MarketForfaitingDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    closeWaitDialog();
                    if (response.body().getCode() == 300) {
                        //提交成功！
                        ToastUtils.showShort(getString(R.string.toast_market_forfaiting_detail_success));
                        marketFactoringDetailTvSubmit.setVisibility(View.GONE);
                        refresh();
//                        finish();
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
                myAssets = factoringResponseBean.getMyAssets();
                //我发布的显示撮合按钮  1-是，0-否  是否报价按钮显示在拿到列表后再判断是不是当前报价人
                if ("1".equals(factoringResponseBean.getMyAssets())) {
//                    marketFactoringDetailTvSubmit.setVisibility(View.GONE);
                    //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
                    if ("2".equals(userType) && "104".equals(factoringResponseBean.getCheckState())) {
//                        marketFactoringDetailTvSubmit.setVisibility(View.VISIBLE);
                        marketFactoringDetailTvSubmit.setText(getString(R.string.market_forfaiting_detail_make_sure));
                        isme = 1;
                    }
                } else {
                    if ("2".equals(userType) && "104".equals(factoringResponseBean.getCheckState())&&"0".equals(factoringResponseBean.getIsnPrice())) {
                        marketFactoringDetailTvSubmit.setText(getString(R.string.market_forfaiting_detail_submit_price));
                        //更应从报价列表取是否显示按钮状态  如果当前用户报过价了 应该不显示报价按钮了
                        marketFactoringDetailTvSubmit.setVisibility(View.VISIBLE);
                        isme = 0;
                    }
                }

                //展开内容
                marketFactoringDetailTv2Creditnumber.setText(factoringResponseBean.getInsurer());//信用保险承保人
                marketFactoringDetailTv2Credittype.setText(factoringResponseBean.getOriginators());//原始债权人
                marketFactoringDetailTv2Creditdate.setText(factoringResponseBean.getObligors());//原始债务人
//                marketFactoringDetailTv2Iussingbankswift.setText(assetsBean.getOpenSwift());
//                marketFactoringDetailTv2Iussingbankname.setText(assetsBean.getOpenFullName());
//                marketFactoringDetailTv2Acceptingbankswift.setText(assetsBean.getTenderSwift());
//                marketFactoringDetailTv2Acceptingbankname.setText(assetsBean.getTenderFullName());
//                marketFactoringDetailTv2Acceptingdate.setText(assetsBean.getAcceptanceDate());//承兑日期
//                marketFactoringDetailTv2Paybankswift.setText(assetsBean.getReimbursingBankSwift());
//                marketFactoringDetailTv2Paybankname.setText(assetsBean.getReimbursingBankName());
                //拿到资产id请求报价列表
//                initDetailOfferListData(assetsBean.getId()+"");
                if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement())) {
                    if (StringUtils.isNullObject(factoringResponseBean.getAssetAgreement().getST0208())) {
                        if (!StringUtils.isEmpty(factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url()) && factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url().length() > 10) {
                            letterUrl = factoringResponseBean.getAssetAgreement().getST0208().getAttachment_url();
                            setFileDataList(marketFactoringDetailLlFactoringdealfile, letterUrl);
                        } else {
                            marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                            marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                            marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
//                        Drawable originBitmapDrawable = ContextCompat.getDrawable(this,
//                                R.drawable.download_icon);
//                        marketFactoringDetailIvFactoringdeal.setImageDrawable(SkxDrawableHelper.tintDrawable(originBitmapDrawable, getResources().getColor(R.color.gray)));
                        }
                    } else {
                        marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                        marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                        marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
//                        Drawable originBitmapDrawable = ContextCompat.getDrawable(this,
//                                R.drawable.download_icon);
//                        marketFactoringDetailIvFactoringdeal.setImageDrawable(SkxDrawableHelper.tintDrawable(originBitmapDrawable, getResources().getColor(R.color.gray)));
                    }
                } else {
                    marketFactoringDetailTv2Factoringdeal.setHintTextColor(getResources().getColor(R.color.gray));
                    marketFactoringDetailIvFactoringdeal.setVisibility(View.GONE);
                    marketFactoringDetailLlFactoringdeal.setVisibility(View.GONE);
//                        Drawable originBitmapDrawable = ContextCompat.getDrawable(this,
//                                R.drawable.download_icon);
//                        marketFactoringDetailIvFactoringdeal.setImageDrawable(SkxDrawableHelper.tintDrawable(originBitmapDrawable, getResources().getColor(R.color.gray)));
                }

            }
            MarketFactoringDetailRes.DataBean.CompanyOrgBean companyOrgBean = dataBean.getCompanyOrg();
            if (StringUtils.isNullObject(companyOrgBean)) {
                marketFactoringDetailTvSellerRightCompany.setText(companyOrgBean.getCompanyName());
                marketFactoringDetailTvSellerRightZhizhaocode.setText(companyOrgBean.getLicenseNo());
                marketFactoringDetailTvSellerRightSwiftcode.setText(companyOrgBean.getSwiftCode());
                marketFactoringDetailTvSellerRightAreacode.setText(companyOrgBean.getCompanyDomicile());
                marketFactoringDetailTvSellerRightNamelable.setText(companyOrgBean.getContactName());
                marketFactoringDetailTvSellerRightNamecode.setText(companyOrgBean.getContactTel());
            }
            //卖方信息
            if(!StringUtils.isEmpty(StringUtils.nullStrToEmpty(dataBean.getSuccessSum()))) {
                marketFactoringDetailTvSellerLeftHistoryCount.setText(StringUtils.nullStrToEmpty(dataBean.getSuccessSum()) + "");
            }
            marketFactoringDetailTvSellerLeftHistoryTotalcount.setText(dataBean.getTradeSum() + "");
//            marketFactoringDetailTvDate.setText(dataBean.getTradeSum());
            marketFactoringDetailTvSellerLeftHistoryChart.setText(dataBean.getSuccessRate());
            marketFactoringDetailTvSellerLeftHistoryAmount.setText(dataBean.getSuccessAmount());

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
                        DisPatcher.startPicturePreviewActivity(MarketFactoringDetailActivity.this, url);
                    }
                }
            });
            ((LinearLayout) view).addView(fileListView);
        }
    }

    //    public void setDataList(MarketFactoringOfferListRes marketFactoringOfferListRes1) {
//    public void setDataList(List<MarketForfaitingOfferListRes.DataBean> dataList) {
//        marketFactoringDetailRg.removeAllViews();
////        marketFactoringOfferListRes = marketFactoringOfferListRes1;
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
//            marketFactoringDetailRg.addView(offerListView);
//        }
//    }

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

    @Override
    protected void onResume() {
        super.onResume();
//        refresh();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==111) {
            if (requestCode == 666) {
                marketFactoringDetailTvSubmit.setVisibility(View.GONE);
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
