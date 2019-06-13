package com.zjhl.pad.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.utils.EditInputFilterOneHunderd;
import com.zjhl.pad.app.utils.EditInputMoneyFilter;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ScreenUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.ForfaitingRportReq;
import com.zjhl.pad.moudle.entity.req.IssueForfaitingReq;
import com.zjhl.pad.moudle.entity.req.IussingForfaitingEntryReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.CheckLetterRes;
import com.zjhl.pad.moudle.entity.res.ForfaitingPreferenceDetail;
import com.zjhl.pad.moudle.entity.res.HandleFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.FactoringIssuingActivity;
import com.zjhl.pad.view.activity.ForfaitingIssuingActivity;
import com.zjhl.pad.view.base.BaseFragment;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: FactoringSellFragment
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.fragment
 * @author: pluto
 * @create: 2018/4/25 13:38
 * @projectname: nnkj
 **/
public class ForfaitingBuyFragment extends BaseFragment {


    @BindView(R.id.release_forfaitingbuy_tv_type)
    TextView releaseForfaitingbuyTvType;
    @BindView(R.id.release_forfaitingbuy_et_type)
    TextView releaseForfaitingbuyEtType;
    @BindView(R.id.release_forfaitingbuy_rl_type)
    RelativeLayout releaseForfaitingbuyRlType;
    @BindView(R.id.release_forfaitingbuy_next_tv_title)
    TextView releaseForfaitingbuyNextTvTitle;
    @BindView(R.id.release_forfaitingbuy_next_et_title)
    EditText releaseForfaitingbuyNextEtTitle;
    @BindView(R.id.release_forfaitingbuy_next_rl_title)
    LinearLayout releaseForfaitingbuyNextRlTitle;
    @BindView(R.id.release_forfaitingbuy_next_tv_currency)
    TextView releaseForfaitingbuyNextTvCurrency;
    @BindView(R.id.release_forfaitingbuy_next_et_currency)
    TextView releaseForfaitingbuyNextEtCurrency;
    @BindView(R.id.release_forfaitingbuy_next_rl_currency)
    RelativeLayout releaseForfaitingbuyNextRlCurrency;
    @BindView(R.id.release_forfaitingbuy_next_tv_amount)
    TextView releaseForfaitingbuyNextTvAmount;
    @BindView(R.id.release_forfaitingbuy_next_et_amount)
    EditText releaseForfaitingbuyNextEtAmount;
    @BindView(R.id.release_forfaitingbuy_next_rl_amount)
    LinearLayout releaseForfaitingbuyNextRlAmount;
    @BindView(R.id.release_forfaitingbuy_tv_discountrate)
    TextView releaseForfaitingbuyTvDiscountrate;
    @BindView(R.id.release_forfaitingbuy_et_discountrate)
    EditText releaseForfaitingbuyEtDiscountrate;
    @BindView(R.id.release_forfaitingbuy_rl_discountrate)
    LinearLayout releaseForfaitingbuyRlDiscountrate;
    @BindView(R.id.release_forfaitingbuy_tv_creditmaturitydate)
    TextView releaseForfaitingbuyTvCreditmaturitydate;
    @BindView(R.id.release_forfaitingbuy_et_creditmaturitydate)
    TextView releaseForfaitingbuyEtCreditmaturitydate;
    @BindView(R.id.release_forfaitingbuy_rl_creditmaturitydate)
    RelativeLayout releaseForfaitingbuyRlCreditmaturitydate;
    @BindView(R.id.release_forfaitingbuy_tv_credittype)
    TextView releaseForfaitingbuyTvCredittype;
    @BindView(R.id.release_forfaitingbuy_et_credittype)
    TextView releaseForfaitingbuyEtCredittype;
    @BindView(R.id.release_forfaitingbuy_rl_credittype)
    RelativeLayout releaseForfaitingbuyRlCredittype;
    @BindView(R.id.release_forfaitingbuy_tv_dealcount)
    TextView releaseForfaitingbuyTvDealcount;
    @BindView(R.id.release_forfaitingbuy_rl_dealcount)
    RelativeLayout releaseForfaitingbuyRlDealcount;
    @BindView(R.id.release_forfaitingbuy_tv_validityinformation)
    TextView releaseForfaitingbuyTvValidityinformation;
    @BindView(R.id.release_forfaitingbuy_et_validityinformation)
    TextView releaseForfaitingbuyEtValidityinformation;
    @BindView(R.id.release_forfaitingbuy_rl_validityinformation)
    RelativeLayout releaseForfaitingbuyRlValidityinformation;
    @BindView(R.id.release_forfaitingbuy_tv_area)
    TextView releaseForfaitingbuyTvArea;
    @BindView(R.id.release_forfaitingbuy_et_area)
    TextView releaseForfaitingbuyEtArea;
    @BindView(R.id.release_forfaitingbuy_rl_area)
    RelativeLayout releaseForfaitingbuyRlArea;
    @BindView(R.id.release_forfaitingbuy_tv_state)
    TextView releaseForfaitingbuyTvState;
    @BindView(R.id.release_forfaitingbuy_et_state)
    TextView releaseForfaitingbuyEtState;
    @BindView(R.id.release_forfaitingbuy_rl_state)
    RelativeLayout releaseForfaitingbuyRlState;
    @BindView(R.id.release_forfaitingbuy_button_next)
    Button releaseForfaitingbuyButtonNext;
    Unbinder unbinder;

    @BindView(R.id.release_forfaitingbuy_et_dealcount)
    TextView releaseForfaitingbuyEtDealcount;

    @BindView(R.id.tv_select_type)
    TextView tvSelectType;
    IussingForfaitingEntryReq iussingForfaitingEntryReq;
    private int forfaitingtype = 0;//信用证1国内2国际

    private String[] array1_arear = {};
    private String[] array2_arear = {};
    private String[] array3_arear = {};

    Map<String, String> arearmap1;
    Map<String, String> arearmap2;
    private String[] array1_country = {};
    private String[] array2_country = {};
    private String[] array3_country = {};
    Map<String, String> countrymap1;
    Map<String, String> countrymap2;
    private String id_area = "";
    private String countryid = "";

    //当编辑时传过来的资产id有值  保存和提交用更新接口
    private String assestId;
    //当编辑时传过来的值  1 信用证 2 保理
    private String assestType;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_release_forfaitingbuy, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        releaseForfaitingbuyButtonNext.setText(getString(R.string.issue_forfaiting_sell_submit));
//        forfaitingtype = 1;
        initDataCoutryCity();

        EditInputMoneyFilter[] codefilters1 = {new EditInputMoneyFilter()};
        releaseForfaitingbuyNextEtAmount.setFilters(codefilters1);
        EditInputFilterOneHunderd[] filters = {new EditInputFilterOneHunderd()};
        releaseForfaitingbuyEtDiscountrate.setFilters(filters);
        initDiscountRateListener(releaseForfaitingbuyEtDiscountrate);
        iussingForfaitingEntryReq = new IussingForfaitingEntryReq();
        if (!StringUtils.isEmpty(assestId) && "1".equals(assestType)) {
            forfaitingPrefenceDetailRet(assestId);
        }
        releaseForfaitingbuyTvArea.setText(R.string.issue_forfaiting_buy_openbank_area);
        releaseForfaitingbuyTvState.setText(R.string.issue_forfaiting_buy_openbank_country);
        return rootView;
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.release_forfaitingbuy_rl_type, R.id.release_forfaitingbuy_ll_type, R.id.release_forfaitingbuy_next_rl_currency, R.id.release_forfaitingbuy_rl_creditmaturitydate, R.id.release_forfaitingbuy_rl_credittype, R.id.release_forfaitingbuy_rl_dealcount, R.id.release_forfaitingbuy_rl_validityinformation, R.id.release_forfaitingbuy_rl_area, R.id.release_forfaitingbuy_rl_state, R.id.release_forfaitingbuy_button_next, R.id.rl_duihuan_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.release_forfaitingbuy_ll_type:
            case R.id.release_forfaitingbuy_rl_type:
                onForfaitingTypePicker();
                break;
            case R.id.release_forfaitingbuy_next_rl_currency:
                if (forfaitingtype != 1) {
                    onConstellationPicker();
                }
                break;
            case R.id.release_forfaitingbuy_rl_creditmaturitydate:
                onYearMonthDayPicker(releaseForfaitingbuyEtCreditmaturitydate);
                break;
            case R.id.release_forfaitingbuy_rl_credittype:
                break;
            case R.id.release_forfaitingbuy_rl_dealcount:
//                onInformationTypePicker(releaseForfaitingbuyEtDealcount);
                break;
            case R.id.release_forfaitingbuy_rl_validityinformation:
                onYearMonthDayPicker(releaseForfaitingbuyEtValidityinformation);
                break;
            case R.id.release_forfaitingbuy_rl_area:
                if (forfaitingtype != 1) {
                    onAreaPicker();
                }
                break;
            case R.id.release_forfaitingbuy_rl_state:
                if (forfaitingtype != 1) {
                    onCountriesPicker(releaseForfaitingbuyEtState);
                }
                break;
            case R.id.release_forfaitingbuy_button_next:
                if (valideData()) {
                    if (!StringUtils.isEmpty(assestId) && "1".equals(assestType)) {
                        iussingForfaitingEntryReq.setId(assestId);
                        //更新资产接口
                        forfaitingPrefenceUpdateRet(setData());
                    } else {
                        initDetailData(setData());
                    }

                }
                break;
            case R.id.rl_duihuan_type:
                //兑换方式
                onDuiHuanTypePicker(tvSelectType);
                break;
        }
    }

    private void onDuiHuanTypePicker(final TextView tvSelectType) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        "NEGOTIATION", "ACCEPTANCE", "DEF PAYMENT", "PAYMENT", "MIXED PAYMENT"
                } : new String[]{
                        "NEGOTIATION", "ACCEPTANCE", "DEF PAYMENT", "PAYMENT", "MIXED PAYMENT"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(getResources().getColor(R.color.white));
//        picker.setGravity(Gravity.CENTER);
        picker.setTopHeight(50);
        picker.setTopPadding(20);
        //不显示文字 必须设置为空
        picker.setCancelText("");
        picker.setSubmitText("");
        picker.setsubmitTextIcon(R.drawable.submit_icon);
        picker.setCancelTextIcon(R.drawable.cancel_icon);
        picker.setTopLineColor(getResources().getColor(R.color.white));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "信用证类型" : "L/C Type");
        picker.setTitleTextColor(getResources().getColor(R.color.dark));
//        picker.setTitleTextSize(18);
//        picker.setCancelTextColor(0xFF33B5E5);
//        picker.setCancelTextSize(13);
//        picker.setSubmitTextColor(0xFF33B5E5);
//        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(getResources().getColor(R.color.dark));
        picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
//        picker.setLineColor(getResources().getColor(R.color.dark));
        config.setColor(getResources().getColor(R.color.dark));//线颜色
//        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        config.setThick(0.5f);
        config.setWheelSize(100);
        picker.setLineConfig(config);
//        picker.setWeightWidth(0);

        // 获取屏幕的高宽
//        final int screenHeight = ScreenUtils.getScreenHeight(getActivity());
        final int screenWidth = ScreenUtils.getScreenWidth(getActivity());
        picker.setItemWidth(screenWidth);
//        picker.setWidth(100);
        picker.setBackgroundColor(getResources().getColor(R.color.white));
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                ToastUtils.showShort("index=" + index + ", item=" + item);
                if (tvSelectType instanceof TextView) {
                    ((TextView) tvSelectType).setText(item);
                }
//                releaseForfaitingsellNextEtType.setText(item);
//                forfaitingtype = index + 1;
//                releaseForfaitingsellNextEtCurrency.setText(item);
            }
        });
        picker.show();
    }

    public IussingForfaitingEntryReq setData() {
        /*
        "title":"求购一个特别便宜的4",   //标题   200  非空
		"currency":"CNY",            //币种    5   非空
		"deadLine":"2018-04-24 10:54:52",  //资产期限  非空
		"amount":123,              //金额   (13,2)     非空
		"creditType":"123123",       //信用证类型  50  非空
		"indateMessage":"2018-04-23 10:54:52", //信息有效期  非空
		"areaId":"1",     //地区        非空
		"countryId":"1",  //国家        非空
		"discountRate":5   //预期贴现率   (13,2)    非空
		"debtType":2     //债权类型：1 国内证、2 国际证', 非空

         */
//        iussingForfaitingEntryReq.setTitle("");

        iussingForfaitingEntryReq.setCurrency("" + releaseForfaitingbuyNextEtCurrency.getText().toString());
        iussingForfaitingEntryReq.setDeadLine("" + releaseForfaitingbuyEtValidityinformation.getText().toString());
        iussingForfaitingEntryReq.setAmount("" + releaseForfaitingbuyNextEtAmount.getText().toString());
//        iussingForfaitingEntryReq.setCreditType("" + releaseForfaitingbuyEtDealcount.getText().toString());
        iussingForfaitingEntryReq.setIndateMessage("" + releaseForfaitingbuyEtCreditmaturitydate.getText().toString());
        iussingForfaitingEntryReq.setAreaId("" + id_area);
        iussingForfaitingEntryReq.setCountryId("" + countryid);
        iussingForfaitingEntryReq.setDiscountRate("" + releaseForfaitingbuyEtDiscountrate.getText().toString());
        iussingForfaitingEntryReq.setCreditType("" + tvSelectType.getText().toString().trim());
        iussingForfaitingEntryReq.setDebtType("" + forfaitingtype);
        return iussingForfaitingEntryReq;
    }

    //校验请求数据
    private boolean valideData() {
        boolean res = true;
        if (forfaitingtype == 0) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_xinyongzhengleixing));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingbuyNextEtCurrency.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_currency));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingbuyNextEtAmount.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_amount));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingbuyEtDiscountrate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_discountrate));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingbuyEtValidityinformation.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_assetsdate));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingbuyEtCreditmaturitydate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_enddate));
            res = false;
        }
        return res;
    }

    //偏好录入 福费廷接口
    public void initDetailData(IussingForfaitingEntryReq iussingForfaitingEntryReq) {
        MyLogger.pLog().i("偏好录入福费廷接口");
        iussingForfaitingEntryReq.setId("");
        ActionPresenter.getInstance().iussingForfaitingRet(iussingForfaitingEntryReq).enqueue(new Callback<MarketFactoringDetailRes>() {
            @Override
            public void onResponse(Call<MarketFactoringDetailRes> call, Response<MarketFactoringDetailRes> response) {
                MyLogger.pLog().d("=====" + response.body().toString());
                MyLogger.pLog().d("=====" + response.body().getCode());

                if (response.body().getCode() == 300) {
//                    if (response.body().getData() != null) {
                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                    getActivity().finish();
//                        setData(response.body());
//                    }
                } else if (response.body().getCode() == 415) {
                    ToastUtils.showShort(response.body().getMessage());
                    MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                    MyLogger.pLog().e(response.body().getMessage());
//                    finish();
                } else {
                    ToastUtils.showShort(response.body().getMessage());
                    MyLogger.pLog().e(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }


    public void onConstellationPicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        "CNY", "USD", "EUR"
                } : new String[]{
                        "CNY", "USD", "EUR"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(getResources().getColor(R.color.white));
//        picker.setGravity(Gravity.CENTER);
        picker.setTopHeight(50);
        picker.setTopPadding(20);
        //不显示文字 必须设置为空
        picker.setCancelText("");
        picker.setSubmitText("");
        picker.setsubmitTextIcon(R.drawable.submit_icon);
        picker.setCancelTextIcon(R.drawable.cancel_icon);
        picker.setTopLineColor(getResources().getColor(R.color.white));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "币种" : "Currency");
        picker.setTitleTextColor(getResources().getColor(R.color.dark));
//        picker.setTitleTextSize(18);
//        picker.setCancelTextColor(0xFF33B5E5);
//        picker.setCancelTextSize(13);
//        picker.setSubmitTextColor(0xFF33B5E5);
//        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(getResources().getColor(R.color.dark));
        picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
//        picker.setLineColor(getResources().getColor(R.color.dark));
        config.setColor(getResources().getColor(R.color.dark));//线颜色
//        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        config.setThick(0.5f);
        config.setWheelSize(100);
        picker.setLineConfig(config);
//        picker.setWeightWidth(0);

        // 获取屏幕的高宽
//        final int screenHeight = ScreenUtils.getScreenHeight(getActivity());
        final int screenWidth = ScreenUtils.getScreenWidth(getActivity());
        picker.setItemWidth(screenWidth);
//        picker.setWidth(100);
        picker.setBackgroundColor(getResources().getColor(R.color.white));
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                ToastUtils.showShort("index=" + index + ", item=" + item);
//                releaseForfaitingsellNextEtType.setText(item);
                releaseForfaitingbuyNextEtCurrency.setText(item);
            }
        });
        picker.show();
    }

    public void onForfaitingTypePicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_type1), MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_type2)
                } : new String[]{
                        "Domestic L/C", "International L/C"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(getResources().getColor(R.color.white));
//        picker.setGravity(Gravity.CENTER);
        picker.setTopHeight(50);
        picker.setTopPadding(20);
        //不显示文字 必须设置为空
        picker.setCancelText("");
        picker.setSubmitText("");
        picker.setsubmitTextIcon(R.drawable.submit_icon);
        picker.setCancelTextIcon(R.drawable.cancel_icon);
        picker.setTopLineColor(getResources().getColor(R.color.white));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "信用证类型" : "Forfaiting Type");
        picker.setTitleTextColor(getResources().getColor(R.color.dark));
//        picker.setTitleTextSize(18);
//        picker.setCancelTextColor(0xFF33B5E5);
//        picker.setCancelTextSize(13);
//        picker.setSubmitTextColor(0xFF33B5E5);
//        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(getResources().getColor(R.color.dark));
        picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
//        picker.setLineColor(getResources().getColor(R.color.dark));
        config.setColor(getResources().getColor(R.color.dark));//线颜色
//        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        config.setThick(0.5f);
        config.setWheelSize(100);
        picker.setLineConfig(config);
//        picker.setWeightWidth(0);

        // 获取屏幕的高宽
//        final int screenHeight = ScreenUtils.getScreenHeight(getActivity());
        final int screenWidth = ScreenUtils.getScreenWidth(getActivity());
        picker.setItemWidth(screenWidth);
//        picker.setWidth(100);
        picker.setBackgroundColor(getResources().getColor(R.color.white));
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                ToastUtils.showShort("index=" + index + ", item=" + item);
                releaseForfaitingbuyEtType.setText(item);
                forfaitingtype = index + 1;
//                releaseForfaitingsellNextEtCurrency.setText(item);
                if (index == 0) {
                    if (array2_arear != null && array2_arear.length > 0) {
                        releaseForfaitingbuyEtArea.setText(array2_arear[0]);
                        id_area = array1_arear[0];
                    }
                    releaseForfaitingbuyNextEtCurrency.setText("CNY");
                }
                if (index == 1) {
                    releaseForfaitingbuyEtState.setText("");
                    releaseForfaitingbuyEtArea.setText("");
                    countryid = "";
                }
                getSecondeCountryList();
            }
        });
        picker.show();
    }

    public void onAreaPicker() {
        if (array2_arear != null && array2_arear.length > 0) {
            boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
//        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
//                isChinese ? new String[]{
//                        "亚洲", "欧洲", "美洲", "非洲", "大洋洲", "南极洲"
//                } : new String[]{
//                        "Asia", "Europe", "America", "Africa", "Oceania", "Antarctica"
//                });
            SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                    isChinese ? array2_arear : array2_arear);
            picker.setCanLoop(false);//不禁用循环
            picker.setTopBackgroundColor(getResources().getColor(R.color.white));
//        picker.setGravity(Gravity.CENTER);
            picker.setTopHeight(50);
            picker.setTopPadding(20);
            //不显示文字 必须设置为空
            picker.setCancelText("");
            picker.setSubmitText("");
            picker.setsubmitTextIcon(R.drawable.submit_icon);
            picker.setCancelTextIcon(R.drawable.cancel_icon);
            picker.setTopLineColor(getResources().getColor(R.color.white));
            picker.setTopLineHeight(1);
            picker.setTitleText(isChinese ? "地区" : "Area");
            picker.setTitleTextColor(getResources().getColor(R.color.dark));
//        picker.setTitleTextSize(18);
//        picker.setCancelTextColor(0xFF33B5E5);
//        picker.setCancelTextSize(13);
//        picker.setSubmitTextColor(0xFF33B5E5);
//        picker.setSubmitTextSize(13);
            picker.setSelectedTextColor(getResources().getColor(R.color.dark));
            picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
            picker.setWheelModeEnable(false);
            LineConfig config = new LineConfig();
//        picker.setLineColor(getResources().getColor(R.color.dark));
            config.setColor(getResources().getColor(R.color.dark));//线颜色
//        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
            config.setThick(0.5f);
            config.setWheelSize(100);
            picker.setLineConfig(config);
//        picker.setWeightWidth(0);

            // 获取屏幕的高宽
//        final int screenHeight = ScreenUtils.getScreenHeight(getActivity());
            final int screenWidth = ScreenUtils.getScreenWidth(getActivity());
            picker.setItemWidth(screenWidth);
//        picker.setWidth(100);
            picker.setBackgroundColor(getResources().getColor(R.color.white));
            //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
            picker.setSelectedIndex(0);
            picker.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
//                ToastUtils.showShort("index=" + index + ", item=" + item);
                    releaseForfaitingbuyEtArea.setText(item);
                    id_area = array1_arear[index];
                    getSecondeCountryList();
                }
            });
            picker.show();
        } else {
            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_nodata));
        }
    }

    //年月日
    public void onYearMonthDayPicker(final View view) {
        final String[] DateString = new String[1];
        final DatePicker picker = new DatePicker(getActivity());
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


    public void onInformationTypePicker(final View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        "SKHDS", "SKHDS1"
                } : new String[]{
                        "SKHDS", "SKHDS1"
                });
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(getResources().getColor(R.color.white));
//        picker.setGravity(Gravity.CENTER);
        picker.setTopHeight(50);
        picker.setTopPadding(20);
        //不显示文字 必须设置为空
        picker.setCancelText("");
        picker.setSubmitText("");
        picker.setsubmitTextIcon(R.drawable.submit_icon);
        picker.setCancelTextIcon(R.drawable.cancel_icon);
        picker.setTopLineColor(getResources().getColor(R.color.white));
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "信用证类型" : "L/C Type");
        picker.setTitleTextColor(getResources().getColor(R.color.dark));
//        picker.setTitleTextSize(18);
//        picker.setCancelTextColor(0xFF33B5E5);
//        picker.setCancelTextSize(13);
//        picker.setSubmitTextColor(0xFF33B5E5);
//        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(getResources().getColor(R.color.dark));
        picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
//        picker.setLineColor(getResources().getColor(R.color.dark));
        config.setColor(getResources().getColor(R.color.dark));//线颜色
//        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        config.setThick(0.5f);
        config.setWheelSize(100);
        picker.setLineConfig(config);
//        picker.setWeightWidth(0);

        // 获取屏幕的高宽
//        final int screenHeight = ScreenUtils.getScreenHeight(getActivity());
        final int screenWidth = ScreenUtils.getScreenWidth(getActivity());
        picker.setItemWidth(screenWidth);
//        picker.setWidth(100);
        picker.setBackgroundColor(getResources().getColor(R.color.white));
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                ToastUtils.showShort("index=" + index + ", item=" + item);
                if (view instanceof TextView) {
                    ((TextView) view).setText(item);
                }
//                releaseForfaitingsellNextEtType.setText(item);
//                forfaitingtype = index + 1;
//                releaseForfaitingsellNextEtCurrency.setText(item);
            }
        });
        picker.show();
    }


    public void onCountriesPicker(final View view) {
        if (array2_country != null && array2_country.length > 0) {
            boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
//        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
//                isChinese ? new String[]{
//                        "中国", "日本"
//                } : new String[]{
//                        "China", "Japan"
//                });
            SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                    isChinese ? array2_country : array2_country);
            picker.setCanLoop(false);//不禁用循环
            picker.setTopBackgroundColor(getResources().getColor(R.color.white));
//        picker.setGravity(Gravity.CENTER);
            picker.setTopHeight(50);
            picker.setTopPadding(20);
            //不显示文字 必须设置为空
            picker.setCancelText("");
            picker.setSubmitText("");
            picker.setsubmitTextIcon(R.drawable.submit_icon);
            picker.setCancelTextIcon(R.drawable.cancel_icon);
            picker.setTopLineColor(getResources().getColor(R.color.white));
            picker.setTopLineHeight(1);
            picker.setTitleText(isChinese ? "国家" : "Countries");
            picker.setTitleTextColor(getResources().getColor(R.color.dark));
//        picker.setTitleTextSize(18);
//        picker.setCancelTextColor(0xFF33B5E5);
//        picker.setCancelTextSize(13);
//        picker.setSubmitTextColor(0xFF33B5E5);
//        picker.setSubmitTextSize(13);
            picker.setSelectedTextColor(getResources().getColor(R.color.dark));
            picker.setUnSelectedTextColor(getResources().getColor(R.color.common_text_gray));
            picker.setWheelModeEnable(false);
            LineConfig config = new LineConfig();
//        picker.setLineColor(getResources().getColor(R.color.dark));
            config.setColor(getResources().getColor(R.color.dark));//线颜色
//        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
            config.setThick(0.5f);
            config.setWheelSize(100);
            picker.setLineConfig(config);
//        picker.setWeightWidth(0);

            // 获取屏幕的高宽
//        final int screenHeight = ScreenUtils.getScreenHeight(getActivity());
            final int screenWidth = ScreenUtils.getScreenWidth(getActivity());
            picker.setItemWidth(screenWidth);
//        picker.setWidth(100);
            picker.setBackgroundColor(getResources().getColor(R.color.white));
            //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
            picker.setSelectedIndex(0);
            picker.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
//                ToastUtils.showShort("index=" + index + ", item=" + item);
                    if (view instanceof TextView) {
                        ((TextView) view).setText(item);
                    }
                    countryid = array1_country[index];
                }
            });
            picker.show();
        } else {
            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_nodata));
        }
    }

    private void initDataCoutryCity() {
        showWaitDialog();
        RegisterCounrtyArear registerCounrtyArear = new RegisterCounrtyArear();
        registerCounrtyArear.setName("");
        registerCounrtyArear.setId("");

        ActionPresenter.getInstance().registerDictionaryArearRet(registerCounrtyArear).enqueue(new Callback<RegisterCountryArearRes>() {
            @Override
            public void onResponse(Call<RegisterCountryArearRes> call, Response<RegisterCountryArearRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        closeWaitDialog();
//                        data_arear = response.body().getData();
                        if (response.body().getData() != null) {
                            arearmap1 = new HashMap<String, String>();
                            arearmap2 = new HashMap<String, String>();
                            array1_arear = new String[response.body().getData().size()];
                            array2_arear = new String[response.body().getData().size()];
                            array3_arear = new String[response.body().getData().size()];
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                array1_arear[i] = response.body().getData().get(i).getId();
                                array2_arear[i] = response.body().getData().get(i).getName();
                                array3_arear[i] = response.body().getData().get(i).getEnName();
                                arearmap1.put(array1_arear[i], array2_arear[i]);
                                arearmap2.put(array1_arear[i], array3_arear[i]);
                            }
                        }
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }

    private void getSecondeCountryList() {
        //选择国家
        RegisterSelectCountry registerselectReq = new RegisterSelectCountry();
        registerselectReq.setAreaId(id_area);
        if (forfaitingtype == 2) {
            registerselectReq.setInternational("1");
        }
        ActionPresenter.getInstance().registerDictionaryRet(registerselectReq).enqueue(new Callback<RegisterCountryArearRes>() {
            @Override
            public void onResponse(Call<RegisterCountryArearRes> call, Response<RegisterCountryArearRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        data = response.body().getData();
//                        data_country = response.body().getData();
                        if (response.body().getData() != null) {
                            countrymap1 = new HashMap<String, String>();
                            countrymap2 = new HashMap<String, String>();
                            array1_country = new String[response.body().getData().size()];
                            array2_country = new String[response.body().getData().size()];
                            array3_country = new String[response.body().getData().size()];
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                array1_country[i] = response.body().getData().get(i).getId();
                                array2_country[i] = response.body().getData().get(i).getName();
                                array3_country[i] = response.body().getData().get(i).getEnName();
                                countrymap1.put(array1_country[i], array2_country[i]);
                                countrymap2.put(array1_country[i], array3_country[i]);
                            }
                        }
                        if (forfaitingtype == 1) {
                            releaseForfaitingbuyEtState.setText(array2_country[0]);
                            countryid = array1_country[0];
                        }
//编辑数据反显国家
                        if (array2_country != null && array2_country.length > 0 && !StringUtils.isEmpty(countryid) && countrymap1 != null && countrymap1.size() > 0) {
//                            int countrid = Integer.valueOf(countryid);
                            releaseForfaitingbuyEtState.setText(countrymap1.get(countryid));
                        }
                        //编辑数据反显国家
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    //编辑开始

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        assestId = ((FactoringIssuingActivity) context).getAssestId();
        assestType = ((FactoringIssuingActivity) context).getAssestType();
        MyLogger.pLog().i("assestId=" + assestId);
        MyLogger.pLog().i("assestType=" + assestType);
    }

    /**
     * //福费廷偏好查询详情接口 复用ForfaitingRportReq id
     *
     * @POST(Constants.NETPATH.FORFAITINGPREFERENCEDETAIL) Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet(@Body RequestBody requestBody);
     * <p>
     * public Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet(ForfaitingRportReq data) {
     * Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet = mApi.forfaitingPrefenceDetailRet(createRequestBody(data));
     * return forfaitingPrefenceDetailRet;
     * }
     */
    public void forfaitingPrefenceDetailRet(String assestId) {
        ForfaitingRportReq forfaitingRportReq = new ForfaitingRportReq();
        forfaitingRportReq.setId(assestId);
        MyLogger.pLog().i("福费廷偏好查询详情接口");
        ActionPresenter.getInstance().forfaitingPrefenceDetailRet(forfaitingRportReq).enqueue(new Callback<ForfaitingPreferenceDetail>() {
            @Override
            public void onResponse(Call<ForfaitingPreferenceDetail> call, Response<ForfaitingPreferenceDetail> response) {
                MyLogger.pLog().d("=====" + response.body().toString());
                MyLogger.pLog().d("=====" + response.body().getCode());

                if (response.body().getCode() == 300) {
                    setDetailDate(response.body());
                } else if (response.body().getCode() == 415) {
                    ToastUtils.showShort(response.body().getMessage());
                    MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                    MyLogger.pLog().e(response.body().getMessage());
//                    finish();
                } else {
                    ToastUtils.showShort(response.body().getMessage());
                    MyLogger.pLog().e(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    //设置请求数据
    private void setDetailDate(ForfaitingPreferenceDetail forfaitingPreferenceDetail) {
        if (StringUtils.isNullObject(forfaitingPreferenceDetail) && StringUtils.isNullObject(forfaitingPreferenceDetail.getData())) {
            ForfaitingPreferenceDetail.DataBean dataBean = forfaitingPreferenceDetail.getData();
            /*
       "id": 1,       //id
"areaId": "1",   //地区id
"areaName": "1",   //地区中文名 （前端根据语言选择展示对应语种的字段）
"areaNameEn": "1",   //地区英文名
"countryId": "1",  //国家id
    "countryName": "美国",  //国家中文名
"countryNameEn": "USA" //国家英文名
    "amount": 7000,    //金额
    "currency": "CNY",     //币种
    "deadLine": "2018-05-20 ",      //资产期限
    "discountRate": 0.3,    //预计贴现率
    "creditType": "123123",   //信用证类型
    "indateMessage": "2018-05-20 ",  //信息有效期
"debtType":2     //债权类型：1 国内证、2 国际证',
         */
            if ("1".equals(dataBean.getDebtType())) {
                releaseForfaitingbuyEtType.setText(getString(R.string.market_forfaiting_type1));
                forfaitingtype = 1;
            } else if ("2".equals(dataBean.getDebtType())) {
                releaseForfaitingbuyEtType.setText(getString(R.string.market_forfaiting_type2));
                forfaitingtype = 2;
            }
            releaseForfaitingbuyNextEtCurrency.setText(StringUtils.nullStrToEmpty(dataBean.getCurrency()));
            releaseForfaitingbuyNextEtAmount.setText(StringUtils.nullStrToEmpty(dataBean.getAmount()));
            releaseForfaitingbuyEtDiscountrate.setText(StringUtils.nullStrToEmpty(dataBean.getDiscountRate()));
            releaseForfaitingbuyEtValidityinformation.setText(StringUtils.nullStrToEmpty(dataBean.getDeadLine()));
            tvSelectType.setText(StringUtils.nullStrToEmpty(dataBean.getCreditType()));
            releaseForfaitingbuyEtCreditmaturitydate.setText(StringUtils.nullStrToEmpty(dataBean.getIndateMessage()));
            countryid = dataBean.getCountryId();
            id_area = dataBean.getAreaId();
            if (array2_arear != null && array2_arear.length > 0 && !StringUtils.isEmpty(id_area)) {
//                int idarea = Integer.valueOf(id_area);
                releaseForfaitingbuyEtArea.setText(StringUtils.nullStrToEmpty(arearmap1.get(id_area)));
                getSecondeCountryList();
            }
//
//            releaseForfaitingbuyNextEtCurrency.setText(StringUtils.nullStrToEmpty(dataBean.getCurrency()));
//            releaseForfaitingbuyNextEtCurrency.setText(StringUtils.nullStrToEmpty(dataBean.getCurrency()));
        }
    }

    /**
     * /**
     * //福费廷偏好更新接口
     *
     * @POST(Constants.NETPATH.FORFAITINGPREFERENCEUPDATE) Call<CheckLetterRes> forfaitingPrefenceUpdateRet(@Body RequestBody requestBody);
     * public Call<CheckLetterRes> forfaitingPrefenceUpdateRet(IussingForfaitingEntryReq data) {
     * Call<CheckLetterRes> forfaitingPrefenceUpdateRet = mApi.forfaitingPrefenceUpdateRet(createRequestBody(data));
     * return forfaitingPrefenceUpdateRet;
     * }
     */
    public void forfaitingPrefenceUpdateRet(IussingForfaitingEntryReq iussingForfaitingEntryReq) {
        MyLogger.pLog().i("福费廷偏好更新接口");
        ActionPresenter.getInstance().forfaitingPrefenceUpdateRet(iussingForfaitingEntryReq).enqueue(new Callback<CheckLetterRes>() {
            @Override
            public void onResponse(Call<CheckLetterRes> call, Response<CheckLetterRes> response) {
                MyLogger.pLog().d("=====" + response.body().toString());
                MyLogger.pLog().d("=====" + response.body().getCode());

                if (response.body().getCode() == 300) {
                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                    getActivity().finish();
                } else if (response.body().getCode() == 415) {
                    ToastUtils.showShort(response.body().getMessage());
                    MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                    MyLogger.pLog().e(response.body().getMessage());
//                    finish();
                } else {
                    ToastUtils.showShort(response.body().getMessage());
                    MyLogger.pLog().e(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

}
