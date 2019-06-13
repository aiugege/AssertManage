package com.zjhl.pad.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjhl.pad.app.utils.EditInputFilterOneHunderd;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.RepublishExpiredAssetsReq;
import com.zjhl.pad.moudle.entity.req.ReviewCheckTransferReq;
import com.zjhl.pad.moudle.entity.res.CheckTransferLetterRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DatePicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: SellAssetsRepublishActivity
 * Author: Leeky
 * Version: V1.0
 * Create: 2018/12/5 14:21
 * description: 待售资产已失效重新发布
 */
public class SellAssetsRepublishActivity extends BaseActivity {

    @BindView(R.id.dialog_republish_iv_close)
    ImageView dialogRepublishIvClose;
    @BindView(R.id.dialog_republish_iv_ss)
    ImageView dialogRepublishIvSs;
    @BindView(R.id.sell_assets_tv_number)
    TextView sellAssetsTvNumber;
    @BindView(R.id.sell_assets_next_tv_title)
    TextView sellAssetsNextTvTitle;
    @BindView(R.id.sell_assets_next_et_title)
    TextView sellAssetsNextEtTitle;
    @BindView(R.id.sell_assets_next_rl_title)
    LinearLayout sellAssetsNextRlTitle;
    @BindView(R.id.sell_assets_next_tv_acceptdate)
    TextView sellAssetsNextTvAcceptdate;
    @BindView(R.id.sell_assets_next_et_acceptdate)
    TextView sellAssetsNextEtAcceptdate;
    @BindView(R.id.sell_assets_tv_discountrate)
    TextView sellAssetsTvDiscountrate;
    @BindView(R.id.sell_assets_et_discountrate)
    EditText sellAssetsEtDiscountrate;
    @BindView(R.id.sell_assets_ll_discountrate)
    LinearLayout sellAssetsLlDiscountrate;
    @BindView(R.id.sell_assets_tv_warning)
    TextView sellAssetsTvWarning;
    @BindView(R.id.dialog_republish_tv_sure)
    TextView dialogRepublishTvSure;
    @BindView(R.id.dialog_republish_ll_conent)
    LinearLayout dialogRepublishLlConent;

    private Context context;
    //当重新发布失效资产时时传过来的资产id
    private String assestId = "";
    //当重新发布失效资产时传过来的值  1 福费廷 2 保理
    private String assestType = "";
    //1.只能修改信息有效期 2.信息有效期和贴现率都不能修改 3.表示两个字段都能修改
    private String showCode = "";
    //资产编号
    private String assetsNo = "";
    //标题
    private String title = "";
    //贴现率
    private String discountRate = "";
    //信息有效期
    private String acceptDate = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sell_assets_republish);
        ButterKnife.bind(this);
        context = this;
        assestId = getIntent().getStringExtra("assestId");
        assestType = getIntent().getStringExtra("assestType");
        showCode = getIntent().getStringExtra("showCode");
        assetsNo = getIntent().getStringExtra("assetsNo");
        title = getIntent().getStringExtra("title");
        discountRate = getIntent().getStringExtra("discountRate");
        if ("1".equals(assestType)) {
            sellAssetsTvDiscountrate.setText(getResources().getString(R.string.issue_forfaiting_sell_discountrate));
            sellAssetsTvWarning.setText(getResources().getString(R.string.toast_forfaiting_sell_republish_warning));
        }else if ("2".equals(assestType)) {
            //保理隐藏标题
            sellAssetsNextRlTitle.setVisibility(View.GONE);
            sellAssetsTvDiscountrate.setText(getResources().getString(R.string.issue_factoring_sell_transferrate));
            sellAssetsTvWarning.setText(getResources().getString(R.string.toast_factoring_sell_republish_warning));
        }
        sellAssetsTvNumber.setText(getResources().getString(R.string.dialog_offer_letter_assertnumber) + assetsNo);
        sellAssetsNextEtTitle.setText(title);

        if ("1".equals(showCode)) {
            //贴现率不可编辑
            sellAssetsEtDiscountrate.setFocusable(false);
            sellAssetsEtDiscountrate.setFocusableInTouchMode(false);
            sellAssetsEtDiscountrate.setText(discountRate);
        }else if ("3".equals(showCode)) {
            //贴现率可编辑
            EditInputFilterOneHunderd[] filters = {new EditInputFilterOneHunderd()};
            sellAssetsEtDiscountrate.setFilters(filters);
            initDiscountRateListener(sellAssetsEtDiscountrate);
            sellAssetsEtDiscountrate.setText(discountRate);
        }
    }

    @OnClick({R.id.dialog_republish_iv_close, R.id.sell_assets_next_et_acceptdate, R.id.dialog_republish_tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_republish_iv_close:
                finish();
                break;
            case R.id.sell_assets_next_et_acceptdate:
                onYearMonthDayPicker(sellAssetsNextEtAcceptdate);
                break;
            case R.id.dialog_republish_tv_sure:
                acceptDate = sellAssetsNextEtAcceptdate.getText().toString().trim();
                discountRate = sellAssetsEtDiscountrate.getText().toString().trim();
                if ("1".equals(assestType)) {
                    //福费廷
                    if (TextUtils.isEmpty(acceptDate)) {
                        ToastUtils.showShort(getResources().getString(R.string.hint_issue_forfaiting_sell_indate));
                        break;
                    }
                    if (TextUtils.isEmpty(discountRate)) {
                        ToastUtils.showShort(getResources().getString(R.string.hint_issue_forfaiting_buy_discountrate));
                        break;
                    }
                    updateExpiredAssetsForfaiting(assestId, discountRate, acceptDate);
                }else if ("2".equals(assestType)) {
                    //保理
                    if (TextUtils.isEmpty(acceptDate)) {
                        ToastUtils.showShort(getResources().getString(R.string.hint_issue_forfaiting_sell_indate));
                        break;
                    }
                    if (TextUtils.isEmpty(discountRate)) {
                        ToastUtils.showShort(getResources().getString(R.string.hint_forfaiting_offer_transferrate));
                        break;
                    }
                    updateExpiredAssetsFactoring(assestId, discountRate, acceptDate);
                }
                break;
        }
    }

    /**
     * 日期选择
     * @param view
     */
    public void onYearMonthDayPicker(final View view) {
        final String[] DateString = new String[1];
        final DatePicker picker = new DatePicker(SellAssetsRepublishActivity.this);
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
        picker.setTitleText(R.string.issue_forfaiting_picker_date);
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

    /**
     * 重新发布已失效资产福费廷信息接口
     * @param assets_id
     * @param discountRate
     * @param indateMessage
     */
    private void updateExpiredAssetsForfaiting(final String assets_id, final String discountRate, final String indateMessage) {
        RepublishExpiredAssetsReq republishExpiredAssetsReq = new RepublishExpiredAssetsReq();
        republishExpiredAssetsReq.setId(assets_id);
        republishExpiredAssetsReq.setDiscountRate(discountRate);
        republishExpiredAssetsReq.setIndateMessage(indateMessage);

        ActionPresenter.getInstance().updateExpiredAssetsForfaitingRet(republishExpiredAssetsReq).enqueue(new Callback<CheckTransferLetterRes>() {
            @Override
            public void onResponse(Call<CheckTransferLetterRes> call, Response<CheckTransferLetterRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {
                            DisPatcher.sendRefreshSellAssertBroadcast(SellAssetsRepublishActivity.this);
                            SellAssetsRepublishActivity.this.finish();
                        } else if (response.body().getCode() == 400) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else if (response.body().getCode() == 500) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckTransferLetterRes> call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    /**
     * 重新发布已失效资产保理信息接口
     * @param assets_id
     * @param transferRate
     * @param indateMessage
     */
    private void updateExpiredAssetsFactoring(final String assets_id, final String transferRate, final String indateMessage) {
        RepublishExpiredAssetsReq republishExpiredAssetsReq = new RepublishExpiredAssetsReq();
        republishExpiredAssetsReq.setId(assets_id);
        republishExpiredAssetsReq.setTransferRate(transferRate);
        republishExpiredAssetsReq.setIndateMessage(indateMessage);

        ActionPresenter.getInstance().updateExpiredAssetsFactoringRet(republishExpiredAssetsReq).enqueue(new Callback<CheckTransferLetterRes>() {
            @Override
            public void onResponse(Call<CheckTransferLetterRes> call, Response<CheckTransferLetterRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {
                            DisPatcher.sendRefreshSellAssertBroadcast(SellAssetsRepublishActivity.this);
                            SellAssetsRepublishActivity.this.finish();
                        } else if (response.body().getCode() == 400) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else if (response.body().getCode() == 500) {
                            ToastUtils.showShort(response.body().getMessage());
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckTransferLetterRes> call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }
}
