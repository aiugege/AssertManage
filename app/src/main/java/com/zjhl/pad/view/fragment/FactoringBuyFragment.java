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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.EditInputFilterOneHunderd;
import com.zjhl.pad.app.utils.EditInputMoneyFilter;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ScreenUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.ForfaitingRportReq;
import com.zjhl.pad.moudle.entity.req.IussingFactoringEntryReq;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.CheckLetterRes;
import com.zjhl.pad.moudle.entity.res.FactoringPreferenceDetail;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.FactoringIssuingActivity;
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
public class FactoringBuyFragment extends BaseFragment {

    @BindView(R.id.release_factoringbuy_tv_type)
    TextView releaseFactoringbuyTvType;
    @BindView(R.id.release_factoringbuy_et_type)
    TextView releaseFactoringbuyEtType;
    @BindView(R.id.release_factoringbuy_rl_type)
    RelativeLayout releaseFactoringbuyRlType;
    @BindView(R.id.release_factoringbuy_tv_currency)
    TextView releaseFactoringbuyTvCurrency;
    @BindView(R.id.release_factoringbuy_et_currency)
    TextView releaseFactoringbuyEtCurrency;
    @BindView(R.id.release_factoringbuy_rl_currency)
    RelativeLayout releaseFactoringbuyRlCurrency;
    @BindView(R.id.release_factoringbuy_tv_amount)
    TextView releaseFactoringbuyTvAmount;
    @BindView(R.id.release_factoringbuy_et_amount)
    EditText releaseFactoringbuyEtAmount;
    @BindView(R.id.release_factoringbuy_rl_amount)
    LinearLayout releaseFactoringbuyRlAmount;
    @BindView(R.id.release_factoringbuy_tv_transfer_tates)
    TextView releaseFactoringbuyTvTransferTates;
    @BindView(R.id.release_factoringbuy_et_transfer_tates)
    EditText releaseFactoringbuyEtTransferTates;
    @BindView(R.id.release_factoringbuy_rl_transfer_tates)
    RelativeLayout releaseFactoringbuyRlTransferTates;
    @BindView(R.id.release_factoringbuy_tv_maturity_date)
    TextView releaseFactoringbuyTvMaturityDate;
    @BindView(R.id.release_factoringbuy_et_maturity_date)
    TextView releaseFactoringbuyEtMaturityDate;
    @BindView(R.id.release_factoringbuy_rl_maturity_date)
    RelativeLayout releaseFactoringbuyRlMaturityDate;
    @BindView(R.id.release_factoringbuy_tv_validityinformation)
    TextView releaseFactoringbuyTvValidityinformation;
    @BindView(R.id.release_factoringbuy_et_validityinformation)
    TextView releaseFactoringbuyEtValidityinformation;
    @BindView(R.id.release_factoringbuy_rl_validityinformation)
    RelativeLayout releaseFactoringbuyRlValidityinformation;
    @BindView(R.id.release_factoringbuy_tv_insure)
    TextView releaseFactoringbuyTvInsure;
    @BindView(R.id.release_factoringbuy_yes)
    RadioButton releaseFactoringbuyYes;
    @BindView(R.id.release_factoringbuy_no)
    RadioButton releaseFactoringbuyNo;
    @BindView(R.id.release_factoringbuy_rg)
    RadioGroup releaseFactoringbuyRg;
    @BindView(R.id.release_factoringbuy_rl_insure)
    RelativeLayout releaseFactoringbuyRlInsure;
    @BindView(R.id.release_factoringbuy_tv_area)
    TextView releaseFactoringbuyTvArea;
    @BindView(R.id.release_factoringbuy_et_area)
    TextView releaseFactoringbuyEtArea;
    @BindView(R.id.release_factoringbuy_rl_area)
    RelativeLayout releaseFactoringbuyRlArea;
    @BindView(R.id.release_factoringbuy_tv_state)
    TextView releaseFactoringbuyTvState;
    @BindView(R.id.release_factoringbuy_et_state)
    TextView releaseFactoringbuyEtState;
    @BindView(R.id.release_factoringbuy_rl_state)
    RelativeLayout releaseFactoringbuyRlState;
    @BindView(R.id.release_factoringbuy_button_next)
    Button releaseFactoringbuyButtonNext;
    Unbinder unbinder;
    IussingFactoringEntryReq iussingFactoringEntryReq;
    @BindView(R.id.release_factoringbuy_et_maturity_before)
    TextView releaseFactoringbuyEtMaturityBefore;
    private int factoringtype = 0;//保理类型1 单保理、明保理 2 单保理、暗保  理  3双保理、明保理  4、双保理、暗保理' ,
    private int ischeck = 0;//否 0 是1 默认0

    Map<String, String> arearmap1;
    Map<String, String> arearmap2;
    private String[] array1_arear = {};
    private String[] array2_arear = {};
    private String[] array3_arear = {};
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
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_release_factoringbuy, null);

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        releaseFactoringbuyRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                MyLogger.pLog().i("i=" + i);
            }
        });
        releaseFactoringbuyButtonNext.setText(R.string.issue_factoring_buy_submit);
        iussingFactoringEntryReq = new IussingFactoringEntryReq();
        initDataCoutryCity();

        EditInputMoneyFilter[] codefilters1 = {new EditInputMoneyFilter()};
        releaseFactoringbuyEtAmount.setFilters(codefilters1);
        EditInputFilterOneHunderd[] filters = {new EditInputFilterOneHunderd()};
        releaseFactoringbuyEtTransferTates.setFilters(filters);
        initDiscountRateListener(releaseFactoringbuyEtTransferTates);
        if (!StringUtils.isEmpty(assestId) && "2".equals(assestType)) {
            factoringPrefenceDetailRet(assestId);
        }
        if ("cn".equals(lanage)) {
            releaseFactoringbuyEtMaturityBefore.setVisibility(View.VISIBLE);
        } else if ("en".equals(lanage)) {
            releaseFactoringbuyEtMaturityBefore.setVisibility(View.VISIBLE);
//            releaseFactoringbuyEtMaturityBefore.setVisibility(View.INVISIBLE);
        }
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

    private void selectRadioButton() {
        RadioButton rb = (RadioButton) getActivity().findViewById(releaseFactoringbuyRg.getCheckedRadioButtonId());
        if (getResources().getString(R.string.issue_factoring_sell_yes).equals(rb.getText().toString())) {
            ischeck = 1;
        } else {
            ischeck = 0;
        }
    }

    @OnClick({R.id.release_factoringbuy_rl_type, R.id.release_factoringbuy_rl_currency, R.id.release_factoringbuy_rl_transfer_tates, R.id.release_factoringbuy_rl_maturity_date, R.id.release_factoringbuy_rl_validityinformation, R.id.release_factoringbuy_yes, R.id.release_factoringbuy_no, R.id.release_factoringbuy_rg, R.id.release_factoringbuy_rl_area, R.id.release_factoringbuy_rl_state, R.id.release_factoringbuy_button_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.release_factoringbuy_rl_type:
                onFactoringTypePicker();
                break;
            case R.id.release_factoringbuy_rl_currency:
                onConstellationPicker(releaseFactoringbuyEtCurrency);
                break;
            case R.id.release_factoringbuy_rl_transfer_tates:
                break;
            case R.id.release_factoringbuy_rl_maturity_date:
                onYearMonthDayPicker(releaseFactoringbuyEtMaturityDate);
                break;
            case R.id.release_factoringbuy_rl_validityinformation:
                onYearMonthDayPicker(releaseFactoringbuyEtValidityinformation);
                break;
            case R.id.release_factoringbuy_yes:
                ischeck = 1;
                break;
            case R.id.release_factoringbuy_no:
                ischeck = 0;
                break;
            case R.id.release_factoringbuy_rg:
                break;
            case R.id.release_factoringbuy_rl_area:
                onAreaPicker();
                break;
            case R.id.release_factoringbuy_rl_state:
                onCountriesPicker(releaseFactoringbuyEtState);
                break;
            case R.id.release_factoringbuy_button_next:
                if (valideDetailData()) {

                    if (!StringUtils.isEmpty(assestId) && "2".equals(assestType)) {
                        iussingFactoringEntryReq.setId(assestId);
                        //更新资产接口
                        factoringPrefenceUpdateRet(setData());
                    } else {
                        initDetailData(setData());
                    }


                }
                break;
        }
    }

    public IussingFactoringEntryReq setData() {
        /*
        {
		"factoringType":"1",  //保理类型
1 单保理、明保理 2 单保理、暗保  理  3双保理、明保理  4、双保理、暗保理' ,
		"currency":"CNY",     //币种    5   非空
		"amount":123,       //金额   (13,2)     非空
		"transferRate":3.8,   //转让利率  (13,2)    非空
		"maturity":"2018-05-20 18:32:17",   到期日   非空
		"indateMessage":"2018-05-20 18:32:17",  //信息有效期 非空
		"isCove":1,    //是否已投保 1 是 0 否
		"areaId":"1",   //地区id
		"countryId":"1"   //国家id

	}

         */
        iussingFactoringEntryReq.setFactoringType("" + factoringtype);
        iussingFactoringEntryReq.setCurrency("" + releaseFactoringbuyEtCurrency.getText().toString());
        iussingFactoringEntryReq.setAmount("" + releaseFactoringbuyEtAmount.getText().toString());
        iussingFactoringEntryReq.setTransferRate("" + releaseFactoringbuyEtTransferTates.getText().toString());
        iussingFactoringEntryReq.setMaturity("" + releaseFactoringbuyEtMaturityDate.getText().toString());
        iussingFactoringEntryReq.setIndateMessage("" + releaseFactoringbuyEtValidityinformation.getText().toString());
        iussingFactoringEntryReq.setIsCove("" + ischeck);
        iussingFactoringEntryReq.setAreaId("" + id_area);
        iussingFactoringEntryReq.setCountryId("" + countryid);
//        iussingFactoringEntryReq.setDebtType("");
        return iussingFactoringEntryReq;
    }

    //检查请求数据
    private boolean valideDetailData() {
        boolean res = true;
        if (factoringtype == 0) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_type));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringbuyEtCurrency.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_bizhong));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringbuyEtAmount.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_jine));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringbuyEtTransferTates.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_zhuanranglilv));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringbuyEtMaturityDate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_daoqiri));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringbuyEtValidityinformation.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_youxiaoqi));
            res = false;
        } else if (ischeck < 0) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_iscove));
            res = false;
        }
        return res;
    }

    //偏好录入 保理接口
    public void initDetailData(IussingFactoringEntryReq iussingFactoringEntryReq) {
        MyLogger.pLog().i("偏好录入保理接口");
        ActionPresenter.getInstance().iussingFactoringRet(iussingFactoringEntryReq).enqueue(new Callback<MarketFactoringDetailRes>() {
            @Override
            public void onResponse(Call<MarketFactoringDetailRes> call, Response<MarketFactoringDetailRes> response) {
                if (response != null && response.body() != null) {
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
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    public void onConstellationPicker(final View view) {
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
                if (view instanceof TextView) {
                    ((TextView) view).setText(item);
                }
//                releaseForfaitingsellNextEtCurrency.setText(item);
            }
        });
        picker.show();
    }


    public void onFactoringTypePicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type1), MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type2), MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type3), MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type4)
                } : new String[]{
                        MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type1), MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type2), MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type3), MyApplication.mMyApplication.getResources().getString(R.string.market_facotring_type4)
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
        picker.setTitleText(isChinese ? "保理类型" : "Factoring Type");
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
                releaseFactoringbuyEtType.setText(item);
                factoringtype = index + 1;
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
//                releaseFactoringsubmitEtArea.setText(item);
                    releaseFactoringbuyEtArea.setText(item);
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
//                forfaitingtype = index + 1;
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
//编辑数据反显国家
                        if (array2_country != null && array2_country.length > 0 && !StringUtils.isEmpty(countryid) && countrymap1 != null && countrymap1.size() > 0) {
//                            int countrid = Integer.valueOf(countryid);
                            releaseFactoringbuyEtState.setText(countrymap1.get(countryid));
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
     * /**
     * //保理偏好查询详情接口 复用ForfaitingRportReq id
     *
     * @POST(Constants.NETPATH.FACTORINGPREFERENCEDETAIL) Call<FactoringPreferenceDetail> factoringPrefenceDetailRet(@Body RequestBody requestBody);
     * public Call<FactoringPreferenceDetail> factoringPrefenceDetailRet(ForfaitingRportReq data) {
     * Call<FactoringPreferenceDetail> factoringPrefenceDetailRet = mApi.factoringPrefenceDetailRet(createRequestBody(data));
     * return factoringPrefenceDetailRet;
     * }
     */
    public void factoringPrefenceDetailRet(String assestId) {
        ForfaitingRportReq forfaitingRportReq = new ForfaitingRportReq();
        forfaitingRportReq.setId(assestId);
        MyLogger.pLog().i("福费廷偏好查询详情接口");
        ActionPresenter.getInstance().factoringPrefenceDetailRet(forfaitingRportReq).enqueue(new Callback<FactoringPreferenceDetail>() {
            @Override
            public void onResponse(Call<FactoringPreferenceDetail> call, Response<FactoringPreferenceDetail> response) {
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
    private void setDetailDate(FactoringPreferenceDetail factoringPreferenceDetail) {
        if (StringUtils.isNullObject(factoringPreferenceDetail) && StringUtils.isNullObject(factoringPreferenceDetail.getData())) {
            FactoringPreferenceDetail.DataBean dataBean = factoringPreferenceDetail.getData();
            /*
 "id": 1,       //id
"areaId": "1",   //地区id
"areaName": "1",   //地区中文名 （前端根据语言选择展示对应语种的字段）
"areaNameEn": "1",   //地区英文名
"countryId": "1",  //国家id
    "countryName": "美国",  //国家中文名
"countryNameEn": "USA" //国家英文名
    "factoringType": 1,    //保理类型 1 单保理、明保理 2 单保理、暗保  理  3双保理、明保理  4、双保理、暗保理'
    "amount": 123,      //金额
    "currency": "CNY",    //币种
    "isCove": 1,          //是否投保
    "transferRate": 3.8,     //转让利率
    "maturity": "2018-05-20 ",  //资产期限
    "indateMessage": "2018-05-20 ",   //信息有效期

         */
            if ("1".equals(dataBean.getFactoringType())) {
                factoringtype = 1;
                releaseFactoringbuyEtType.setText(getString(R.string.market_facotring_type1));
            } else if ("2".equals(dataBean.getFactoringType())) {
                factoringtype = 2;
                releaseFactoringbuyEtType.setText(getString(R.string.market_facotring_type2));
            } else if ("3".equals(dataBean.getFactoringType())) {
                factoringtype = 3;
                releaseFactoringbuyEtType.setText(getString(R.string.market_facotring_type3));
            } else if ("4".equals(dataBean.getFactoringType())) {
                releaseFactoringbuyEtType.setText(getString(R.string.market_facotring_type4));
                factoringtype = 4;
            }
            releaseFactoringbuyEtCurrency.setText(StringUtils.nullStrToEmpty(dataBean.getCurrency()));
            releaseFactoringbuyEtAmount.setText(StringUtils.nullStrToEmpty(dataBean.getAmount()));
            releaseFactoringbuyEtTransferTates.setText(StringUtils.nullStrToEmpty(dataBean.getTransferRate()));
            releaseFactoringbuyEtMaturityDate.setText(StringUtils.nullStrToEmpty(dataBean.getMaturity()));
            releaseFactoringbuyEtValidityinformation.setText(StringUtils.nullStrToEmpty(dataBean.getIndateMessage()));
            countryid = dataBean.getCountryId();
            id_area = dataBean.getAreaId();
            if (array2_arear != null && array2_arear.length > 0 && !StringUtils.isEmpty(id_area)) {
                releaseFactoringbuyEtArea.setText(StringUtils.nullStrToEmpty(arearmap1.get(id_area)));
                getSecondeCountryList();
            }
            if ("1".equals(dataBean.getIsCove())) {
                ischeck = 1;
                releaseFactoringbuyYes.setChecked(true);
//                releaseFactoringsellNextEtInsure.setText("是");
            } else if ("2".equals(dataBean.getIsCove())) {
//                releaseFactoringsellNextEtInsure.setText("否");
                releaseFactoringbuyNo.setChecked(true);
                ischeck = 2;
            }
//
//            releaseForfaitingbuyNextEtCurrency.setText(StringUtils.nullStrToEmpty(dataBean.getCurrency()));
//            releaseForfaitingbuyNextEtCurrency.setText(StringUtils.nullStrToEmpty(dataBean.getCurrency()));
        }
    }

    /**
     * /**
     * //保理偏好更新接口
     *
     * @POST(Constants.NETPATH.FACTORINGPREFERENCEUPDATE) Call<CheckLetterRes> factoringPrefenceUpdateRet(@Body RequestBody requestBody);
     * public Call<CheckLetterRes> factoringPrefenceUpdateRet(IussingFactoringEntryReq data) {
     * Call<CheckLetterRes> factoringPrefenceUpdateRet = mApi.factoringPrefenceUpdateRet(createRequestBody(data));
     * return factoringPrefenceUpdateRet;
     * }
     */
    public void factoringPrefenceUpdateRet(IussingFactoringEntryReq iussingFactoringEntryReq) {
        MyLogger.pLog().i("保理偏好更新接口");
        ActionPresenter.getInstance().factoringPrefenceUpdateRet(iussingFactoringEntryReq).enqueue(new Callback<CheckLetterRes>() {
            @Override
            public void onResponse(Call<CheckLetterRes> call, Response<CheckLetterRes> response) {
//                MyLogger.pLog().d("=====" + response.body().toString());
//                MyLogger.pLog().d("=====" + response.body().getCode());

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
