package com.zjhl.pad.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;
import com.vincent.filepicker.filter.entity.NormalFile;
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
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.IssueFactoringReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.HandleFactoringDetailSearchRes;
import com.zjhl.pad.moudle.entity.res.IssueFactoringRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.moudle.entity.res.UploadFileRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.ForfaitingIssuingActivity;
import com.zjhl.pad.view.base.BaseFragment;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import me.shaohui.bottomdialog.BottomDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_TAKEN_AUTO_SELECTED;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_TAKEN_DONE;

/**
 * @desc: FactoringSellFragment
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.fragment
 * @author: pluto
 * @create: 2018/4/25 13:38
 * @projectname: nnkj
 **/
public class FactoringSellFragment extends BaseFragment {
    @BindView(R.id.release_factoringsell_next_tv_sell)
    TextView releaseFactoringsellNextTvSell;
    @BindView(R.id.release_factoringsell_next_et_sell)
    EditText releaseFactoringsellNextEtSell;
    @BindView(R.id.release_factoringsell_next_rl_sell)
    LinearLayout releaseFactoringsellNextRlSell;
    @BindView(R.id.release_factoringsell_next_tv_area)
    TextView releaseFactoringsellNextTvArea;
    @BindView(R.id.release_factoringsell_next_et_area)
    TextView releaseFactoringsellNextEtArea;
    @BindView(R.id.release_factoringsell_next_rl_area)
    RelativeLayout releaseFactoringsellNextRlArea;
    @BindView(R.id.release_factoringsell_next_tv_state)
    TextView releaseFactoringsellNextTvState;
    @BindView(R.id.release_factoringsell_next_et_state)
    TextView releaseFactoringsellNextEtState;
    @BindView(R.id.release_factoringsell_next_rl_state)
    RelativeLayout releaseFactoringsellNextRlState;
    @BindView(R.id.release_factoringsell_next_tv_type)
    TextView releaseFactoringsellNextTvType;
    @BindView(R.id.release_factoringsell_next_et_type)
    TextView releaseFactoringsellNextEtType;
    @BindView(R.id.release_factoringsell_next_rl_type)
    RelativeLayout releaseFactoringsellNextRlType;
    @BindView(R.id.release_factoringsell_next_tv_currency)
    TextView releaseFactoringsellNextTvCurrency;
    @BindView(R.id.release_factoringsell_next_et_currency)
    TextView releaseFactoringsellNextEtCurrency;
    @BindView(R.id.release_factoringsell_next_rl_currency)
    RelativeLayout releaseFactoringsellNextRlCurrency;
    @BindView(R.id.release_factoringsell_next_tv_transfer_tates)
    TextView releaseFactoringsellNextTvTransferTates;
    @BindView(R.id.release_factoringsell_next_et_transfer_tates)
    EditText releaseFactoringsellNextEtTransferTates;
    @BindView(R.id.release_factoringsell_next_rl_transfer_tates)
    LinearLayout releaseFactoringsellNextRlTransferTates;
    @BindView(R.id.release_factoringsell_next_tv_maturity_date)
    TextView releaseFactoringsellNextTvMaturityDate;
    @BindView(R.id.release_factoringsell_next_et_maturity_date)
    TextView releaseFactoringsellNextEtMaturityDate;
    @BindView(R.id.release_factoringsell_next_rl_maturity_date)
    RelativeLayout releaseFactoringsellNextRlMaturityDate;
    @BindView(R.id.release_factoringsell_next_tv_name)
    TextView releaseFactoringsellNextTvName;
    @BindView(R.id.release_factoringsell_next_et_name)
    EditText releaseFactoringsellNextEtName;
    @BindView(R.id.release_factoringsell_next_rl_name)
    LinearLayout releaseFactoringsellNextRlName;
    @BindView(R.id.release_factoringsell_next_tv_original_creditor)
    TextView releaseFactoringsellNextTvOriginalCreditor;
    @BindView(R.id.release_factoringsell_next_et_original_creditor)
    EditText releaseFactoringsellNextEtOriginalCreditor;
    @BindView(R.id.release_factoringsell_next_rl_original_creditor)
    LinearLayout releaseFactoringsellNextRlOriginalCreditor;
    @BindView(R.id.release_factoringsell_next_tv_original_debtor)
    TextView releaseFactoringsellNextTvOriginalDebtor;
    @BindView(R.id.release_factoringsell_next_et_original_debtor)
    EditText releaseFactoringsellNextEtOriginalDebtor;
    @BindView(R.id.release_factoringsell_next_rl_original_debtor)
    LinearLayout releaseFactoringsellNextRlOriginalDebtor;
    @BindView(R.id.release_factoringsell_next_tv_insure)
    TextView releaseFactoringsellNextTvInsure;
    @BindView(R.id.release_factoringsell_next_et_insure)
    TextView releaseFactoringsellNextEtInsure;
    @BindView(R.id.release_factoringsell_next_rl_insure)
    RelativeLayout releaseFactoringsellNextRlInsure;
    @BindView(R.id.release_factoringsell_next_tv_assurer)
    TextView releaseFactoringsellNextTvAssurer;
    @BindView(R.id.release_factoringsell_next_et_assurer)
    EditText releaseFactoringsellNextEtAssurer;
    @BindView(R.id.release_factoringsell_next_rl_assurer)
    LinearLayout releaseFactoringsellNextRlAssurer;
    @BindView(R.id.release_factoringsell_next_button_next)
    Button releaseFactoringsellNextButtonNext;
    @BindView(R.id.release_factoringsell_next_rl)
    LinearLayout releaseFactoringsellNextRl;
    @BindView(R.id.release_factoringsell_submit_tv_compact)
    TextView releaseFactoringsellSubmitTvCompact;
    @BindView(R.id.release_factoringsell_submit_tv_compact_upload)
    TextView releaseFactoringsellSubmitTvCompactUpload;
    @BindView(R.id.release_factoringsell_submit_rl_compact)
    RelativeLayout releaseFactoringsellSubmitRlCompact;
    @BindView(R.id.release_factoringsell_submit_tv_insurancecompact)
    TextView releaseFactoringsellSubmitTvInsurancecompact;
    @BindView(R.id.release_factoringsell_submit_tv_insurancecompact_upload)
    TextView releaseFactoringsellSubmitTvInsurancecompactUpload;
    @BindView(R.id.release_factoringsell_submit_rl_insurancecompact)
    RelativeLayout releaseFactoringsellSubmitRlInsurancecompact;
    @BindView(R.id.release_factoringsell_submit_tv_transportdocument)
    TextView releaseFactoringsellSubmitTvTransportdocument;
    @BindView(R.id.release_factoringsell_submit_tv_transportdocument_upload)
    TextView releaseFactoringsellSubmitTvTransportdocumentUpload;
    @BindView(R.id.release_factoringsell_submit_rl_transportdocument)
    RelativeLayout releaseFactoringsellSubmitRlTransportdocument;
    @BindView(R.id.release_factoringsell_submit_tv_notice)
    TextView releaseFactoringsellSubmitTvNotice;
    @BindView(R.id.release_factoringsell_submit_rl_notice)
    RelativeLayout releaseFactoringsellSubmitRlNotice;
    @BindView(R.id.release_factoringsell_submit_tv_financialstatement)
    TextView releaseFactoringsellSubmitTvFinancialstatement;
    @BindView(R.id.release_factoringsell_submit_tv_financialstatement_upload)
    TextView releaseFactoringsellSubmitTvFinancialstatementUpload;
    @BindView(R.id.release_factoringsell_submit_rl_financialstatement)
    RelativeLayout releaseFactoringsellSubmitRlFinancialstatement;
    @BindView(R.id.release_factoringsell_submit_tv_tradingcontract)
    TextView releaseFactoringsellSubmitTvTradingcontract;
    @BindView(R.id.release_factoringsell_submit_tv_tradingcontract_upload)
    TextView releaseFactoringsellSubmitTvTradingcontractUpload;
    @BindView(R.id.release_factoringsell_submit_rl_tradingcontract)
    RelativeLayout releaseFactoringsellSubmitRlTradingcontract;
    @BindView(R.id.release_factoringsell_button_submit)
    Button releaseFactoringsellButtonSubmit;
    @BindView(R.id.release_factoringsell_submit_rl)
    LinearLayout releaseFactoringsellSubmitRl;
    @BindView(R.id.release_factoringsell_submit_rl2)
    LinearLayout releaseFactoringsellSubmitRl2;
    Unbinder unbinder;
    @BindView(R.id.release_factoringsell_next_ll_title1)
    LinearLayout releaseFactoringsellNextLlTitle1;
    @BindView(R.id.release_factoringsell_next_ll_title2)
    LinearLayout releaseFactoringsellNextLlTitle2;
    @BindView(R.id.release_factoringsell_next_tv_amount)
    TextView releaseFactoringsellNextTvAmount;
    @BindView(R.id.release_factoringsell_next_et_amount)
    EditText releaseFactoringsellNextEtAmount;
    @BindView(R.id.release_factoringsell_next_rl_amount)
    LinearLayout releaseFactoringsellNextRlAmount;
    @BindView(R.id.release_factoringsell_next_tv_validity_credit)
    TextView releaseFactoringsellNextTvValidityCredit;
    @BindView(R.id.release_factoringsell_next_et_validity_credit)
    TextView releaseFactoringsellNextEtValidityCredit;
    @BindView(R.id.release_factoringsell_next_rl_validity_credit)
    RelativeLayout releaseFactoringsellNextRlValidityCredit;

    //代表那个按钮触发  用于判断
    int actionCode = 0;
    IssueFactoringReq issueFactoringReq;
    @BindView(R.id.release_factoringsell_submit_ll_financialstatement)
    LinearLayout releaseFactoringsellSubmitLlFinancialstatement;
    @BindView(R.id.release_factoringsell_submit_ll_insurancecompact)
    LinearLayout releaseFactoringsellSubmitLlInsurancecompact;
    @BindView(R.id.release_factoringsell_submit_ll_notice)
    LinearLayout releaseFactoringsellSubmitLlNotice;
    @BindView(R.id.release_factoringsell_submit_ll_notice_upload)
    TextView releaseFactoringsellSubmitLlNoticeUpload;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv1)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv1;
    @BindView(R.id.release_factoringsell_next_ll_rl1_financialstatement1)
    RelativeLayout releaseFactoringsellNextLlRl1Financialstatement1;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementivdelete1)
    ImageView releaseFactoringsellNextLlRlFinancialstatementivdelete1;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatement1)
    RelativeLayout releaseFactoringsellNextLlRlFinancialstatement1;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv11)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv11;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv2)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv2;
    @BindView(R.id.release_factoringsell_next_ll_rl2_financialstatement2)
    RelativeLayout releaseFactoringsellNextLlRl2Financialstatement2;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementivdelete2)
    ImageView releaseFactoringsellNextLlRlFinancialstatementivdelete2;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatement2)
    RelativeLayout releaseFactoringsellNextLlRlFinancialstatement2;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv21)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv21;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv3)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv3;
    @BindView(R.id.release_factoringsell_next_ll_rl3_financialstatement3)
    RelativeLayout releaseFactoringsellNextLlRl3Financialstatement3;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementivdelete3)
    ImageView releaseFactoringsellNextLlRlFinancialstatementivdelete3;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatement3)
    RelativeLayout releaseFactoringsellNextLlRlFinancialstatement3;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv31)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv31;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv4)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv4;
    @BindView(R.id.release_factoringsell_next_ll_rl4_financialstatement4)
    RelativeLayout releaseFactoringsellNextLlRl4Financialstatement4;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementivdelete4)
    ImageView releaseFactoringsellNextLlRlFinancialstatementivdelete4;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatement4)
    RelativeLayout releaseFactoringsellNextLlRlFinancialstatement4;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv41)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv41;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv5)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv5;
    @BindView(R.id.release_factoringsell_next_ll_rl5_financialstatement5)
    RelativeLayout releaseFactoringsellNextLlRl5Financialstatement5;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementivdelete5)
    ImageView releaseFactoringsellNextLlRlFinancialstatementivdelete5;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatement5)
    RelativeLayout releaseFactoringsellNextLlRlFinancialstatement5;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv51)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv51;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv6)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv6;
    @BindView(R.id.release_factoringsell_next_ll_rl6_financialstatement6)
    RelativeLayout releaseFactoringsellNextLlRl6Financialstatement6;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementivdelete6)
    ImageView releaseFactoringsellNextLlRlFinancialstatementivdelete6;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatement6)
    RelativeLayout releaseFactoringsellNextLlRlFinancialstatement6;
    @BindView(R.id.release_factoringsell_next_ll_rl_financialstatementiv61)
    ImageView releaseFactoringsellNextLlRlFinancialstatementiv61;
    @BindView(R.id.release_factoringsell_next_ll_financialstatement)
    LinearLayout releaseFactoringsellNextLlFinancialstatement;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv1)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv1;
    @BindView(R.id.release_factoringsell_next_ll_rl1_insurancecompact1)
    RelativeLayout releaseFactoringsellNextLlRl1Insurancecompact1;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete1)
    ImageView releaseFactoringsellNextLlRlInsurancecompactivdelete1;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompact1)
    RelativeLayout releaseFactoringsellNextLlRlInsurancecompact1;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv11)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv11;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv2)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv2;
    @BindView(R.id.release_factoringsell_next_ll_rl2_insurancecompact2)
    RelativeLayout releaseFactoringsellNextLlRl2Insurancecompact2;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete2)
    ImageView releaseFactoringsellNextLlRlInsurancecompactivdelete2;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompact2)
    RelativeLayout releaseFactoringsellNextLlRlInsurancecompact2;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv21)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv21;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv3)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv3;
    @BindView(R.id.release_factoringsell_next_ll_rl3_insurancecompact3)
    RelativeLayout releaseFactoringsellNextLlRl3Insurancecompact3;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete3)
    ImageView releaseFactoringsellNextLlRlInsurancecompactivdelete3;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompact3)
    RelativeLayout releaseFactoringsellNextLlRlInsurancecompact3;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv31)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv31;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv4)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv4;
    @BindView(R.id.release_factoringsell_next_ll_rl4_insurancecompact4)
    RelativeLayout releaseFactoringsellNextLlRl4Insurancecompact4;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete4)
    ImageView releaseFactoringsellNextLlRlInsurancecompactivdelete4;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompact4)
    RelativeLayout releaseFactoringsellNextLlRlInsurancecompact4;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv41)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv41;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv5)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv5;
    @BindView(R.id.release_factoringsell_next_ll_rl5_insurancecompact5)
    RelativeLayout releaseFactoringsellNextLlRl5Insurancecompact5;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete5)
    ImageView releaseFactoringsellNextLlRlInsurancecompactivdelete5;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompact5)
    RelativeLayout releaseFactoringsellNextLlRlInsurancecompact5;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv51)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv51;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv6)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv6;
    @BindView(R.id.release_factoringsell_next_ll_rl6_insurancecompact6)
    RelativeLayout releaseFactoringsellNextLlRl6Insurancecompact6;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete6)
    ImageView releaseFactoringsellNextLlRlInsurancecompactivdelete6;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompact6)
    RelativeLayout releaseFactoringsellNextLlRlInsurancecompact6;
    @BindView(R.id.release_factoringsell_next_ll_rl_insurancecompactiv61)
    ImageView releaseFactoringsellNextLlRlInsurancecompactiv61;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv1)
    ImageView releaseFactoringsellNextLlRlCompactiv1;
    @BindView(R.id.release_factoringsell_next_ll_rl1_compact1)
    RelativeLayout releaseFactoringsellNextLlRl1Compact1;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactivdelete1)
    ImageView releaseFactoringsellNextLlRlCompactivdelete1;
    @BindView(R.id.release_factoringsell_next_ll_rl_compact1)
    RelativeLayout releaseFactoringsellNextLlRlCompact1;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv11)
    ImageView releaseFactoringsellNextLlRlCompactiv11;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv2)
    ImageView releaseFactoringsellNextLlRlCompactiv2;
    @BindView(R.id.release_factoringsell_next_ll_rl2_compact2)
    RelativeLayout releaseFactoringsellNextLlRl2Compact2;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactivdelete2)
    ImageView releaseFactoringsellNextLlRlCompactivdelete2;
    @BindView(R.id.release_factoringsell_next_ll_rl_compact2)
    RelativeLayout releaseFactoringsellNextLlRlCompact2;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv21)
    ImageView releaseFactoringsellNextLlRlCompactiv21;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv3)
    ImageView releaseFactoringsellNextLlRlCompactiv3;
    @BindView(R.id.release_factoringsell_next_ll_rl3_compact3)
    RelativeLayout releaseFactoringsellNextLlRl3Compact3;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactivdelete3)
    ImageView releaseFactoringsellNextLlRlCompactivdelete3;
    @BindView(R.id.release_factoringsell_next_ll_rl_compact3)
    RelativeLayout releaseFactoringsellNextLlRlCompact3;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv31)
    ImageView releaseFactoringsellNextLlRlCompactiv31;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv4)
    ImageView releaseFactoringsellNextLlRlCompactiv4;
    @BindView(R.id.release_factoringsell_next_ll_rl4_compact4)
    RelativeLayout releaseFactoringsellNextLlRl4Compact4;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactivdelete4)
    ImageView releaseFactoringsellNextLlRlCompactivdelete4;
    @BindView(R.id.release_factoringsell_next_ll_rl_compact4)
    RelativeLayout releaseFactoringsellNextLlRlCompact4;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv41)
    ImageView releaseFactoringsellNextLlRlCompactiv41;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv5)
    ImageView releaseFactoringsellNextLlRlCompactiv5;
    @BindView(R.id.release_factoringsell_next_ll_rl5_compact5)
    RelativeLayout releaseFactoringsellNextLlRl5Compact5;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactivdelete5)
    ImageView releaseFactoringsellNextLlRlCompactivdelete5;
    @BindView(R.id.release_factoringsell_next_ll_rl_compact5)
    RelativeLayout releaseFactoringsellNextLlRlCompact5;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv51)
    ImageView releaseFactoringsellNextLlRlCompactiv51;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv6)
    ImageView releaseFactoringsellNextLlRlCompactiv6;
    @BindView(R.id.release_factoringsell_next_ll_rl6_compact6)
    RelativeLayout releaseFactoringsellNextLlRl6Compact6;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactivdelete6)
    ImageView releaseFactoringsellNextLlRlCompactivdelete6;
    @BindView(R.id.release_factoringsell_next_ll_rl_compact6)
    RelativeLayout releaseFactoringsellNextLlRlCompact6;
    @BindView(R.id.release_factoringsell_next_ll_rl_compactiv61)
    ImageView releaseFactoringsellNextLlRlCompactiv61;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv1)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv1;
    @BindView(R.id.release_factoringsell_next_ll_rl1_transportdocument1)
    RelativeLayout releaseFactoringsellNextLlRl1Transportdocument1;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete1)
    ImageView releaseFactoringsellNextLlRlTransportdocumentivdelete1;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocument1)
    RelativeLayout releaseFactoringsellNextLlRlTransportdocument1;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv11)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv11;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv2)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv2;
    @BindView(R.id.release_factoringsell_next_ll_rl2_transportdocument2)
    RelativeLayout releaseFactoringsellNextLlRl2Transportdocument2;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete2)
    ImageView releaseFactoringsellNextLlRlTransportdocumentivdelete2;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocument2)
    RelativeLayout releaseFactoringsellNextLlRlTransportdocument2;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv21)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv21;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv3)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv3;
    @BindView(R.id.release_factoringsell_next_ll_rl3_transportdocument3)
    RelativeLayout releaseFactoringsellNextLlRl3Transportdocument3;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete3)
    ImageView releaseFactoringsellNextLlRlTransportdocumentivdelete3;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocument3)
    RelativeLayout releaseFactoringsellNextLlRlTransportdocument3;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv31)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv31;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv4)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv4;
    @BindView(R.id.release_factoringsell_next_ll_rl4_transportdocument4)
    RelativeLayout releaseFactoringsellNextLlRl4Transportdocument4;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete4)
    ImageView releaseFactoringsellNextLlRlTransportdocumentivdelete4;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocument4)
    RelativeLayout releaseFactoringsellNextLlRlTransportdocument4;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv41)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv41;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv5)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv5;
    @BindView(R.id.release_factoringsell_next_ll_rl5_transportdocument5)
    RelativeLayout releaseFactoringsellNextLlRl5Transportdocument5;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete5)
    ImageView releaseFactoringsellNextLlRlTransportdocumentivdelete5;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocument5)
    RelativeLayout releaseFactoringsellNextLlRlTransportdocument5;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv51)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv51;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv6)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv6;
    @BindView(R.id.release_factoringsell_next_ll_rl6_transportdocument6)
    RelativeLayout releaseFactoringsellNextLlRl6Transportdocument6;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete6)
    ImageView releaseFactoringsellNextLlRlTransportdocumentivdelete6;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocument6)
    RelativeLayout releaseFactoringsellNextLlRlTransportdocument6;
    @BindView(R.id.release_factoringsell_next_ll_rl_transportdocumentiv61)
    ImageView releaseFactoringsellNextLlRlTransportdocumentiv61;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv1)
    ImageView releaseFactoringsellNextLlRlNoticeiv1;
    @BindView(R.id.release_factoringsell_next_ll_rl1_notice1)
    RelativeLayout releaseFactoringsellNextLlRl1Notice1;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeivdelete1)
    ImageView releaseFactoringsellNextLlRlNoticeivdelete1;
    @BindView(R.id.release_factoringsell_next_ll_rl_notice1)
    RelativeLayout releaseFactoringsellNextLlRlNotice1;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv11)
    ImageView releaseFactoringsellNextLlRlNoticeiv11;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv2)
    ImageView releaseFactoringsellNextLlRlNoticeiv2;
    @BindView(R.id.release_factoringsell_next_ll_rl2_notice2)
    RelativeLayout releaseFactoringsellNextLlRl2Notice2;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeivdelete2)
    ImageView releaseFactoringsellNextLlRlNoticeivdelete2;
    @BindView(R.id.release_factoringsell_next_ll_rl_notice2)
    RelativeLayout releaseFactoringsellNextLlRlNotice2;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv21)
    ImageView releaseFactoringsellNextLlRlNoticeiv21;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv3)
    ImageView releaseFactoringsellNextLlRlNoticeiv3;
    @BindView(R.id.release_factoringsell_next_ll_rl3_notice3)
    RelativeLayout releaseFactoringsellNextLlRl3Notice3;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeivdelete3)
    ImageView releaseFactoringsellNextLlRlNoticeivdelete3;
    @BindView(R.id.release_factoringsell_next_ll_rl_notice3)
    RelativeLayout releaseFactoringsellNextLlRlNotice3;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv31)
    ImageView releaseFactoringsellNextLlRlNoticeiv31;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv4)
    ImageView releaseFactoringsellNextLlRlNoticeiv4;
    @BindView(R.id.release_factoringsell_next_ll_rl4_notice4)
    RelativeLayout releaseFactoringsellNextLlRl4Notice4;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeivdelete4)
    ImageView releaseFactoringsellNextLlRlNoticeivdelete4;
    @BindView(R.id.release_factoringsell_next_ll_rl_notice4)
    RelativeLayout releaseFactoringsellNextLlRlNotice4;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv41)
    ImageView releaseFactoringsellNextLlRlNoticeiv41;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv5)
    ImageView releaseFactoringsellNextLlRlNoticeiv5;
    @BindView(R.id.release_factoringsell_next_ll_rl5_notice5)
    RelativeLayout releaseFactoringsellNextLlRl5Notice5;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeivdelete5)
    ImageView releaseFactoringsellNextLlRlNoticeivdelete5;
    @BindView(R.id.release_factoringsell_next_ll_rl_notice5)
    RelativeLayout releaseFactoringsellNextLlRlNotice5;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv51)
    ImageView releaseFactoringsellNextLlRlNoticeiv51;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv6)
    ImageView releaseFactoringsellNextLlRlNoticeiv6;
    @BindView(R.id.release_factoringsell_next_ll_rl6_notice6)
    RelativeLayout releaseFactoringsellNextLlRl6Notice6;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeivdelete6)
    ImageView releaseFactoringsellNextLlRlNoticeivdelete6;
    @BindView(R.id.release_factoringsell_next_ll_rl_notice6)
    RelativeLayout releaseFactoringsellNextLlRlNotice6;
    @BindView(R.id.release_factoringsell_next_ll_rl_noticeiv61)
    ImageView releaseFactoringsellNextLlRlNoticeiv61;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv1)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv1;
    @BindView(R.id.release_factoringsell_next_ll_rl1_tradingcontract1)
    RelativeLayout releaseFactoringsellNextLlRl1Tradingcontract1;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete1)
    ImageView releaseFactoringsellNextLlRlTradingcontractivdelete1;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontract1)
    RelativeLayout releaseFactoringsellNextLlRlTradingcontract1;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv11)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv11;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv2)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv2;
    @BindView(R.id.release_factoringsell_next_ll_rl2_tradingcontract2)
    RelativeLayout releaseFactoringsellNextLlRl2Tradingcontract2;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete2)
    ImageView releaseFactoringsellNextLlRlTradingcontractivdelete2;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontract2)
    RelativeLayout releaseFactoringsellNextLlRlTradingcontract2;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv21)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv21;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv3)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv3;
    @BindView(R.id.release_factoringsell_next_ll_rl3_tradingcontract3)
    RelativeLayout releaseFactoringsellNextLlRl3Tradingcontract3;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete3)
    ImageView releaseFactoringsellNextLlRlTradingcontractivdelete3;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontract3)
    RelativeLayout releaseFactoringsellNextLlRlTradingcontract3;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv31)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv31;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv4)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv4;
    @BindView(R.id.release_factoringsell_next_ll_rl4_tradingcontract4)
    RelativeLayout releaseFactoringsellNextLlRl4Tradingcontract4;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete4)
    ImageView releaseFactoringsellNextLlRlTradingcontractivdelete4;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontract4)
    RelativeLayout releaseFactoringsellNextLlRlTradingcontract4;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv41)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv41;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv5)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv5;
    @BindView(R.id.release_factoringsell_next_ll_rl5_tradingcontract5)
    RelativeLayout releaseFactoringsellNextLlRl5Tradingcontract5;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete5)
    ImageView releaseFactoringsellNextLlRlTradingcontractivdelete5;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontract5)
    RelativeLayout releaseFactoringsellNextLlRlTradingcontract5;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv51)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv51;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv6)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv6;
    @BindView(R.id.release_factoringsell_next_ll_rl6_tradingcontract6)
    RelativeLayout releaseFactoringsellNextLlRl6Tradingcontract6;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete6)
    ImageView releaseFactoringsellNextLlRlTradingcontractivdelete6;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontract6)
    RelativeLayout releaseFactoringsellNextLlRlTradingcontract6;
    @BindView(R.id.release_factoringsell_next_ll_rl_tradingcontractiv61)
    ImageView releaseFactoringsellNextLlRlTradingcontractiv61;
    //================


    //默认 保理类型  1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理
    private int factoringtype = 0;
    //默认信用证类型
    private int creditetype = 1;
    //默认地区类型
    private int areatype = 1;
    //默认国家类型
    private int countriestype = 1;
    //是否投保  默认是  1 投保 2 不投保
    int isCove = 0;
    TextView ivRtv1;

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
    //当编辑时传过来的值  1 福费廷 2 保理
    private String assestType;
    private String companyOrgId;

    //多文件列表
    private ArrayList<String> photoPaths = new ArrayList<>();
    private ArrayList<String> docPaths = new ArrayList<>();
    private List<File> files = new ArrayList<File>();
    //财务报表
    private List<String> filesstr1 = new ArrayList<String>();
    //保险合同
    private List<String> filesstr2 = new ArrayList<String>();
    //货物保险单
    private List<String> filesstr3 = new ArrayList<String>();
    //运输单据
    private List<String> filesstr4 = new ArrayList<String>();
    //应收账款转让通知书
    private List<String> filesstr5 = new ArrayList<String>();
    //贸易合同
    private List<String> filesstr6 = new ArrayList<String>();
    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_release_factoringsell_next, null);

        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        issueFactoringReq = new IssueFactoringReq();
        EditInputFilterOneHunderd[] filters = {new EditInputFilterOneHunderd()};
        releaseFactoringsellNextEtTransferTates.setFilters(filters);
        initDiscountRateListener(releaseFactoringsellNextEtTransferTates);
        EditInputMoneyFilter[] codefilters1 = {new EditInputMoneyFilter()};
        releaseFactoringsellNextEtAmount.setFilters(codefilters1);
        initDataCoutryCity();

        if (!StringUtils.isEmpty(assestId) && "2".equals(assestType)) {
            handleFactoringDetailRet(assestId);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

//获取主activity中的控件，下面这些如果写道oncreateview中是报错的
        ivRtv1 = (TextView) getActivity().findViewById(R.id.iv_Rtv1);
        ivRtv1.setText(getString(R.string.forfaiting_sell_save));
//        String st = (String) ivRtv.getText();
//        ToastUtils.showShort(st);
        ivRtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showShort("ivRtv");

                /** 提交类型：1保存，2 提交*/
                issueFactoringReq.setCommitType("1");
                if (!StringUtils.isEmpty(assestId) && "2".equals(assestType)) {
                    issueFactoringReq.setUserType(userType);
                    issueFactoringReq.setCompanyOrgId(companyOrgId);
                    if (valideDetailData()) {
                        //更新资产接口
                        submitUpdate(setDate());
                    }
                } else {
                    if (valideDetailData()) {
                        submitDate(setDate());
                    }
                }
//                ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_save));
            }
        });
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

    @OnClick({R.id.release_factoringsell_next_rl_sell, R.id.release_factoringsell_submit_rl_transportdocument, R.id.release_factoringsell_submit_rl_tradingcontract, R.id.release_factoringsell_submit_ll_notice_upload, R.id.release_factoringsell_submit_rl_notice, R.id.release_factoringsell_next_rl_area, R.id.release_factoringsell_submit_rl_compact, R.id.release_factoringsell_next_rl_state, R.id.release_factoringsell_next_rl_type, R.id.release_factoringsell_next_rl_currency, R.id.release_factoringsell_next_rl_maturity_date, R.id.release_factoringsell_next_rl_insure, R.id.release_factoringsell_next_button_next, R.id.release_factoringsell_submit_tv_compact_upload, R.id.release_factoringsell_submit_tv_insurancecompact_upload, R.id.release_factoringsell_submit_tv_transportdocument_upload, R.id.release_factoringsell_submit_tv_financialstatement_upload, R.id.release_factoringsell_submit_tv_tradingcontract_upload, R.id.release_factoringsell_button_submit, R.id.release_factoringsell_submit_rl, R.id.release_factoringsell_next_rl_amount, R.id.release_factoringsell_next_rl_validity_credit, R.id.release_factoringsell_submit_rl_financialstatement, R.id.release_factoringsell_submit_rl_insurancecompact, R.id.release_factoringsubmit_im1, R.id.release_factoringsubmit_im2, R.id.release_factoringsubmit_im3, R.id.release_factoringsubmit_im4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_rl_sell:
                break;
            case R.id.release_factoringsell_next_rl_area:
                onAreaPicker();
                break;
            case R.id.release_factoringsell_next_rl_state:
                onCountriesPicker(releaseFactoringsellNextEtState);
                break;
            case R.id.release_factoringsell_next_rl_type:
                onFactoringTypePicker();
                break;
            case R.id.release_factoringsell_next_rl_currency:
                onConstellationPicker(releaseFactoringsellNextEtCurrency);
                break;
            case R.id.release_factoringsell_next_rl_maturity_date:
                onYearMonthDayPicker(releaseFactoringsellNextEtMaturityDate);
                break;
            case R.id.release_factoringsell_next_rl_insure:
                onisCovePicker(releaseFactoringsellNextEtInsure);
                break;
            case R.id.release_factoringsell_next_button_next:
                releaseFactoringsellNextRl.setVisibility(View.GONE);
                releaseFactoringsellNextLlTitle1.setVisibility(View.GONE);
                releaseFactoringsellNextLlTitle2.setVisibility(View.VISIBLE);
                releaseFactoringsellSubmitRl.setVisibility(View.VISIBLE);
                releaseFactoringsellSubmitRl2.setVisibility(View.VISIBLE);
                releaseFactoringsellButtonSubmit.setVisibility(View.VISIBLE);
                DisPatcher.sendVisableSaveButtonBroadcast(getActivity());
                break;
            case R.id.release_factoringsell_submit_rl_financialstatement:
            case R.id.release_factoringsell_submit_tv_financialstatement_upload:
                //财务报表
//                actionCode = 1;
//                bottomDialog();
                break;
            case R.id.release_factoringsell_submit_rl_insurancecompact:
            case R.id.release_factoringsell_submit_tv_insurancecompact_upload:
                //保险合同
//                actionCode = 2;
//                bottomDialog();
                break;
            case R.id.release_factoringsell_submit_tv_compact_upload:
            case R.id.release_factoringsell_submit_rl_compact:
                //货物保险单
//                actionCode = 3;
//                bottomDialog();
                break;
            case R.id.release_factoringsell_submit_rl_transportdocument:
            case R.id.release_factoringsell_submit_tv_transportdocument_upload:
                //运输单据
//                actionCode = 4;
//                bottomDialog();
                break;
            case R.id.release_factoringsell_submit_rl_notice:
            case R.id.release_factoringsell_submit_ll_notice_upload:
                //应收账款转让通知书
//                actionCode = 5;
//                bottomDialog();
                break;
            case R.id.release_factoringsell_submit_tv_tradingcontract_upload:
            case R.id.release_factoringsell_submit_rl_tradingcontract:
                //贸易合同
//                actionCode = 6;
//                bottomDialog();
                break;
            case R.id.release_factoringsell_button_submit:
//                releaseFactoringsellSubmitRl.setVisibility(View.GONE);
//                releaseFactoringsellNextRl.setVisibility(View.VISIBLE);
//                releaseFactoringsellNextLlTitle1.setVisibility(View.VISIBLE);
//                releaseFactoringsellNextLlTitle2.setVisibility(View.GONE);
//                DisPatcher.sendGoneSaveButtonBroadcast(getActivity());

                initSureOrCancelDialogView("", getString(R.string.issue_forfaiting_sell_yesorno));
                /** 提交类型：1保存，2 提交*/
               /* issueFactoringReq.setCommitType("2");
                //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
                String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
                if (!StringUtils.isEmpty(assestId) && "2".equals(assestType)) {
                    issueFactoringReq.setUserType(userType);
                    issueFactoringReq.setCompanyOrgId(companyOrgId);
                    if(valideDetailData()) {
                        //更新资产接口
                        submitUpdate(setDate());
                    }
                } else {
                    if(valideDetailData()) {
                        submitDate(setDate());
                    }
                }*/
                break;
            case R.id.release_factoringsell_submit_rl:
                break;

            case R.id.release_factoringsell_next_rl_amount:
                break;
            case R.id.release_factoringsell_next_rl_validity_credit:
                onYearMonthDayPicker(releaseFactoringsellNextEtValidityCredit);
                break;
            case R.id.release_factoringsubmit_im1:
            case R.id.release_factoringsubmit_im3:
                releaseFactoringsellSubmitRl.setVisibility(View.GONE);
                releaseFactoringsellNextRl.setVisibility(View.VISIBLE);
                releaseFactoringsellNextLlTitle1.setVisibility(View.VISIBLE);
                releaseFactoringsellNextLlTitle2.setVisibility(View.GONE);
                releaseFactoringsellSubmitRl2.setVisibility(View.GONE);
                releaseFactoringsellButtonSubmit.setVisibility(View.GONE);
                break;
            case R.id.release_factoringsubmit_im2:
            case R.id.release_factoringsubmit_im4:
                releaseFactoringsellNextRl.setVisibility(View.GONE);
                releaseFactoringsellNextLlTitle1.setVisibility(View.GONE);
                releaseFactoringsellNextLlTitle2.setVisibility(View.VISIBLE);
                releaseFactoringsellSubmitRl.setVisibility(View.VISIBLE);
                releaseFactoringsellSubmitRl2.setVisibility(View.VISIBLE);
                releaseFactoringsellButtonSubmit.setVisibility(View.VISIBLE);
                break;
        }
    }

    /*
     {
  	"id":9,//资产ID(新增页面不传，修改页面必传项)
  	"companyOrgId":2012,//机构id(修改页面必传项)
  	"userType":2,//用户类型 2.操作经办员(必填)
  	"ST-02-04":"111",//保理合同url(必填)
  	"ST-02-05":"222",//贸易合同url
  	"ST-02-06":"333",//运输单据url
	"ST-02-07":"444",//应收账款转让通知书url(必填)
	"ST-02-08":"555",//财务报表url(必填)
	"ST-02-09":"666",//货物购买方url
  	"factoringName":"sqc1",//保理商名称
  	"countryId":4561,//国家id
  	"areaId":1231,//地区id
  	"commitType":2,//提交方式 1 保存，2 提交  审核驳回的应传2
  	"seller":"sqc1",//保理卖出方
  	"factoringType":1,//保理类型 1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理
  	"currency":"cny1",//币种
  	"amount":10000,//金额
  	"transferRate":1.21,//转让利率
  	"maturity":"2018-1-1 00:00:00",//到期日
"indateMessage":"2018-1-1 00:00:00",//信息有效期
  	"originators":"sqc",//原始债权人
  	"obligors":"sqc",//原始债务人
  	"isCove":"1",//是否投保 1 投保 2 不投保
  	"insurer":"sqc",//信用保险承保人
  }

     */
    //设置请求数据
    private IssueFactoringReq setDate() {
        issueFactoringReq.setId(assestId);
        issueFactoringReq.setSeller(releaseFactoringsellNextEtSell.getText().toString());
        issueFactoringReq.setAreaId(id_area);
        issueFactoringReq.setCountryId(countryid);
        issueFactoringReq.setFactoringType(factoringtype + "");
        issueFactoringReq.setAmount(releaseFactoringsellNextEtAmount.getText().toString());
        issueFactoringReq.setTransferRate(releaseFactoringsellNextEtTransferTates.getText().toString());
        issueFactoringReq.setMaturity(releaseFactoringsellNextEtMaturityDate.getText().toString());
        issueFactoringReq.setFactoringName(releaseFactoringsellNextEtName.getText().toString());
        issueFactoringReq.setOriginators(releaseFactoringsellNextEtOriginalCreditor.getText().toString());
        issueFactoringReq.setObligors(releaseFactoringsellNextEtOriginalDebtor.getText().toString());
        issueFactoringReq.setIsCove(isCove + "");
        issueFactoringReq.setCurrency(releaseFactoringsellNextEtCurrency.getText().toString());
        issueFactoringReq.setInsurer(releaseFactoringsellNextEtAssurer.getText().toString());
        issueFactoringReq.setIndateMessage(releaseFactoringsellNextEtValidityCredit.getText().toString());
//        issueFactoringReq.setST0208(releaseFactoringsellSubmitTvFinancialstatementUpload.getText().toString());
//        issueFactoringReq.setST0204(releaseFactoringsellSubmitTvInsurancecompactUpload.getText().toString());
//        issueFactoringReq.setST0209(releaseFactoringsellSubmitTvCompactUpload.getText().toString());
//        issueFactoringReq.setST0206(releaseFactoringsellSubmitTvTransportdocumentUpload.getText().toString());
//        issueFactoringReq.setST0207(releaseFactoringsellSubmitLlNoticeUpload.getText().toString());
//        issueFactoringReq.setST0205(releaseFactoringsellSubmitTvTradingcontractUpload.getText().toString());
        MyLogger.pLog().i(issueFactoringReq.toString());

        return issueFactoringReq;
    }

    //检查请求数据
    private boolean valideDetailData() {
        boolean res = true;
        if (StringUtils.isEmpty(releaseFactoringsellNextEtSell.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_name));
            res = false;
        } else if (StringUtils.isEmpty(id_area)) {
            ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_area));
            res = false;
        } else if (StringUtils.isEmpty(countryid)) {
            ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_countrys));
            res = false;
        } else if (factoringtype == 0) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_type));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsellNextEtAmount.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_jine));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsellNextEtCurrency.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_bizhong));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsellNextEtTransferTates.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_zhuanranglilv));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsellNextEtMaturityDate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_daoqiri));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsellNextEtName.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_factoringname));
            res = false;
        } else if (isCove == 0) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_iscove));
            res = false;
        } else if (isCove == 1 && StringUtils.isEmpty(releaseFactoringsellNextEtAssurer.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_assuer));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsellNextEtValidityCredit.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_factoring_sell_youxiaoqi));
            res = false;
        }
        return res;
    }

    private void submitDate(final IssueFactoringReq issueFactoringReq) {
        showWaitDialog();
        MyLogger.pLog().i("发布保理接口");
        issueFactoringReq.setId("");
        ActionPresenter.getInstance().issueFactoringRet(issueFactoringReq).enqueue(new Callback<IssueFactoringRes>() {
            @Override
            public void onResponse(Call<IssueFactoringRes> call, Response<IssueFactoringRes> response) {
                closeWaitDialog();
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort("发布成功");
                        if ("1".equals(issueFactoringReq.getCommitType())) {
//                            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_save));
                        } else if ("2".equals(issueFactoringReq.getCommitType())) {
                            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        }
                        getActivity().finish();
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                        if (!StringUtils.isEmpty(response.body().getMessage()) && "系统错误".equals(response.body().getMessage())) {
                            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_noaccess));
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }
                } else {
                    ToastUtils.showShort(response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void uploadFile(File file, final int type) {
//        isLoading(true);
        showWaitDialog();
        MyLogger.pLog().i("上传文件接口");
//        UploadFileReq uploadFileReq = new UploadFileReq();
//        uploadFileReq.setExtensionName(file.getName());
//        uploadFileReq.setFileSize(file.getTotalSpace()+"");
//        ActionPresenter.getInstance().uploadFileRet(null, file).enqueue(new Callback<UploadFileRes>() {
        ActionPresenter.getInstance().uploadFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
                MyLogger.pLog().d("===上传文件成功！==");
//                isLoading(false);
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        MyLogger.pLog().i(response.body().getUrl());
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_uploadsuccess));
                        //财务报表
                        if (type == 1) {
                            issueFactoringReq.setST0208(response.body().getUrl());

                        } else if (type == 2) {
                            issueFactoringReq.setST0204(response.body().getUrl());

                            //保险合同
                        } else if (type == 3) {
                            issueFactoringReq.setST0209(response.body().getUrl());

                            //货物保险单
                        } else if (type == 4) {
                            issueFactoringReq.setST0206(response.body().getUrl());

                            //运输单据
                        } else if (type == 5) {
                            issueFactoringReq.setST0207(response.body().getUrl());

                            //应收账款转让通知书
                        } else if (type == 6) {
                            issueFactoringReq.setST0205(response.body().getUrl());
                            //贸易合同
                        }
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                } else {
                    MyLogger.pLog().e(response.message());
                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_failed));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void uploadMoreFile(List<File> file, final int type) {
//        isLoading(true);
        showWaitDialog();
        MyLogger.pLog().i("上传文件接口");
//        UploadFileReq uploadFileReq = new UploadFileReq();
//        uploadFileReq.setExtensionName(file.getName());
//        uploadFileReq.setFileSize(file.getTotalSpace()+"");
//        ActionPresenter.getInstance().uploadFileRet(null, file).enqueue(new Callback<UploadFileRes>() {
        ActionPresenter.getInstance().uploadMoreFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
                MyLogger.pLog().d("===上传文件成功！==");
//                isLoading(false);
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        MyLogger.pLog().i(response.body().getUrl());
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_uploadsuccess));
                        //财务报表
                        if (type == 1) {
//                            issueFactoringReq.setST0208(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr1.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewFinancialstatement();

                        } else if (type == 2) {
//                            issueFactoringReq.setST0204(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr2.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewInsurancecompact();
                            //保险合同
                        } else if (type == 3) {
//                            issueFactoringReq.setST0209(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr3.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewCompact();
                            //货物保险单
                        } else if (type == 4) {
//                            issueFactoringReq.setST0206(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr4.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewTransportdocument();
                            //运输单据
                        } else if (type == 5) {
//                            issueFactoringReq.setST0207(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr5.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewNotice();
                            //应收账款转让通知书
                        } else if (type == 6) {
//                            issueFactoringReq.setST0205(response.body().getUrl());
                            //贸易合同
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr6.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewTradingcontract();
                        }
                    } else {
                        ToastUtils.showShort(response.body().getMessage());
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                } else {
                    MyLogger.pLog().e(response.message());
                    ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_failed));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                ToastUtils.showShort(t.getMessage());
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

    public void onisCovePicker(final View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        "是", "否"
                } : new String[]{
                        "YES", "NO"
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
        picker.setTitleText(isChinese ? "是否投保" : "isCove");
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
                    isCove = index + 1;
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
                releaseFactoringsellNextEtType.setText(item);
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
                    releaseFactoringsellNextEtArea.setText(item);
                    id_area = array1_arear[index];
                    getSecondeCountryList();
                }
            });
            picker.show();
        } else {
            ToastUtils.showShort(getString(R.string.toast_filtrate_choicearea));
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
        picker.setTitleText(isChinese ? "信用证类型" : "Tpye For Credit");
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
//                forfaitingtype = index + 1;
                    countryid = array1_country[index];
                }
            });
            picker.show();
        } else {
            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_noaccess));
        }
    }


    /*
        弹窗选择文件、照相开始
         */
    public void bottomDialog() {
        final BottomDialog bottomDialog = BottomDialog.create(getFragmentManager());
        bottomDialog.setViewListener(new BottomDialog.ViewListener() {      // 可以进行一些必要对View的操作
            @Override
            public void bindView(View v) {
                // you can do bind view operation
                dialogAction(v, bottomDialog);
            }
        })
                .setLayoutRes(R.layout.activity_file_choice)
                .setDimAmount(0.3f)            // Dialog window 背景色深度 范围：0 到 1，默认是0.2f
                .setCancelOutside(true)     // 点击外部区域是否关闭，默认true
                .setTag("BottomDialog")     // 设置DialogFragment的tag
                .show();
    }

    private void dialogAction(View v, final BottomDialog bottomDialog) {
        TextView upload = (TextView) v.findViewById(R.id.activity_file_choice_upload);
        TextView photo = (TextView) v.findViewById(R.id.activity_file_choice_photo);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                EasyImage.openGallery(FactoringSellFragment.this, 0);
//                EasyImage.openDocuments(FactoringSellFragment.this, 0);

                //多文件选择
//                String[] zipTypes = {"jpg", "png"};
//                String[] zips = {".zip", ".rar"};
//                String[] pdfs = {".pdf"};
//                FilePickerBuilder.getInstance()
//                        .setMaxCount(6)
//                        .setSelectedFiles(docPaths)
//                        .setActivityTheme(R.style.LibAppTheme)
//                        .setActivityTitle("Please select doc")
//                        .addFileSupport("ZIP", zips)
//                        .addFileSupport("PDF", pdfs, R.mipmap.iassetx)
//                        .addFileSupport("图片", zipTypes)
//                        .enableDocSupport(false)
//                        .enableSelectAll(true)
//                        .sortDocumentsBy(SortingTypes.name)
//                        .withOrientation(Orientation.UNSPECIFIED)
//                        .pickFile(FactoringSellFragment.this, 666);
                if (actionCode == 1) {
                    Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                    intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr1.size());
                    intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                    startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                }
                if (actionCode == 2) {
                    Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                    intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr2.size());
                    intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                    startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                }
                if (actionCode == 3) {
                    Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                    intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr3.size());
                    intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                    startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                }
                if (actionCode == 4) {
                    Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                    intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr4.size());
                    intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                    startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                }
                if (actionCode == 5) {
                    Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                    intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr5.size());
                    intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                    startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                }
                if (actionCode == 6) {
                    Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                    intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr6.size());
                    intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                    startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                }
                bottomDialog.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EasyImage.openCamera(FactoringSellFragment.this, 0);
                if (actionCode == 1) {
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr1.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                }
                if (actionCode == 2) {
//                    EasyImage.openCamera(ForfaitingSellFragment.this, 0);
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr2.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                }
                if (actionCode == 3) {
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr3.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                }
                if (actionCode == 4) {
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr4.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                }
                if (actionCode == 5) {
//                    EasyImage.openCamera(ForfaitingSellFragment.this, 0);
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr5.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                }
                if (actionCode == 6) {
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr6.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                }
                bottomDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogger.pLog().i(requestCode);
//        MyLogger.pLog().i("88" + resultCode + "88");

        //多文件上传返回结果
      /*  if (data != null && requestCode == 666) {
            docPaths = new ArrayList<>();
            files = new ArrayList<File>();
            docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
//            docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
            for (String path : docPaths) {
                files.add(new File(path));
            }
            //财务报表
            if (actionCode == 1) {
                uploadMoreFile(files, actionCode);
            } else if (actionCode == 2) {
                //保险合同
                uploadMoreFile(files, actionCode);
            } else if (actionCode == 3) {
                //货物保险单
                uploadMoreFile(files, actionCode);
            } else if (actionCode == 4) {
                //运输单据
                uploadMoreFile(files, actionCode);
            } else if (actionCode == 5) {
                //应收账款转让通知书
                uploadMoreFile(files, actionCode);
            } else if (actionCode == 6) {
                //贸易合同
                uploadMoreFile(files, actionCode);
            }
        }
*/

       /* EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling 获取图片失败
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //财务报表
                if (actionCode == 1) {
                    MyLogger.pLog().i("1:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                } else if (actionCode == 2) {
                    //保险合同
                    MyLogger.pLog().i("2:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                } else if (actionCode == 3) {
                    //货物保险单
                    MyLogger.pLog().i("3:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                } else if (actionCode == 4) {
                    //运输单据
                    MyLogger.pLog().i("4:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                } else if (actionCode == 5) {
                    //应收账款转让通知书
                    MyLogger.pLog().i("5:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                } else if (actionCode == 6) {
                    //贸易合同
                    MyLogger.pLog().i("6:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                // Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getActivity());
                    if (photoFile != null) photoFile.delete();
                }
            }

        });*/
//多文件2开始
        files = new ArrayList<File>();
//        filesstr = new ArrayList<String>();
        switch (requestCode) {

            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    StringBuilder builder = new StringBuilder();
                    for (ImageFile file : list) {
                        String path = file.getPath();
                        if (file.getSize() > 5242880) {
                            ToastUtils.showShort(getString(R.string.toast_forfaitingsell_filesize));
                            break;
                        }
                        files.add(new File(file.getPath()));
                        builder.append(path + "\n");
                    }
                    //财务报表
                    if (actionCode == 1) {
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 2) {
                        //保险合同
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 3) {
                        //货物保险单
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 4) {
                        //运输单据
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 5) {
                        //应收账款转让通知书
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 6) {
                        //贸易合同
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    }
                }
                break;
            case Constant.REQUEST_CODE_PICK_FILE:
                if (resultCode == RESULT_OK) {
                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    StringBuilder builder = new StringBuilder();
                    for (NormalFile file : list) {
                        String path = file.getPath();
                        if (file.getSize() > 5242880) {
                            ToastUtils.showShort(getString(R.string.toast_forfaitingsell_filesize));
                            break;
                        }
                        files.add(new File(file.getPath()));
//                        filesstr.add(file.getPath());
                        builder.append(path + "\n");
                    }
                    //财务报表
                    if (actionCode == 1) {
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 2) {
                        //保险合同
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 3) {
                        //货物保险单
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 4) {
                        //运输单据
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 5) {
                        //应收账款转让通知书
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 6) {
                        //贸易合同
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    }
                }
                break;
        }

        //多文件2结束
//        actionCode = 0;
    }

/*
弹窗选择文件、照相结束
 */

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
                            releaseFactoringsellNextEtState.setText(countrymap1.get(countryid));
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
        assestId = ((ForfaitingIssuingActivity) context).getAssestId();
        assestType = ((ForfaitingIssuingActivity) context).getAssestType();
        MyLogger.pLog().i("assestId=" + assestId);
        MyLogger.pLog().i("assestType=" + assestType);
    }

    //复用MarketForfaitingOfferReq  里面的id 请求参数就一个id

    /**
     * //保理资产详情查询用于经办修改接口
     *
     * @POST(Constants.NETPATH.HANDLEFACTORINGDETAIL) Call<HandleFactoringDetailSearchRes> handleFactoringDetailRet(@Body RequestBody requestBody);
     * <p>
     * public Call<HandleFactoringDetailSearchRes> handleFactoringDetailRet(MarketForfaitingOfferReq data) {
     * Call<HandleFactoringDetailSearchRes> handleFactoringDetailRet = mApi.handleFactoringDetailRet(createRequestBody(data));
     * return handleFactoringDetailRet;
     * }
     */
    private void handleFactoringDetailRet(String id) {
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId("" + id);
//        showWaitDialog();
        MyLogger.pLog().i("获取经办编辑保理详情接口");
        ActionPresenter.getInstance().handleFactoringDetailRet(marketForfaitingOfferReq).enqueue(new Callback<HandleFactoringDetailSearchRes>() {
            @Override
            public void onResponse(Call<HandleFactoringDetailSearchRes> call, Response<HandleFactoringDetailSearchRes> response) {
//                closeWaitDialog();
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
                        setDetailDate(response.body());
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                } else {
                    ToastUtils.showShort(response.message());
                    MyLogger.pLog().e(response.message());
//                    MyLogger.pLog().e(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    //设置请求数据
    private void setDetailDate(HandleFactoringDetailSearchRes handleFactoringDetailSearchRes) {
        if (StringUtils.isNullObject(handleFactoringDetailSearchRes) && StringUtils.isNullObject(handleFactoringDetailSearchRes.getData())) {

//            issueFactoringReq.setSeller(releaseFactoringsellNextEtSell.getText().toString());
//            issueFactoringReq.setAreaId(id_area);
//            issueFactoringReq.setCountryId(countryid);
//            issueFactoringReq.setFactoringType(factoringtype+"");
//            issueFactoringReq.setAmount(releaseFactoringsellNextEtAmount.getText().toString());
//            issueFactoringReq.setTransferRate(releaseFactoringsellNextEtTransferTates.getText().toString());
//            issueFactoringReq.setMaturity(releaseFactoringsellNextEtMaturityDate.getText().toString());
//            issueFactoringReq.setFactoringName(releaseFactoringsellNextEtName.getText().toString());
//            issueFactoringReq.setOriginators(releaseFactoringsellNextEtOriginalCreditor.getText().toString());
//            issueFactoringReq.setObligors(releaseFactoringsellNextEtOriginalDebtor.getText().toString());
//            issueFactoringReq.setIsCove(isCove + "");
//            issueFactoringReq.setCurrency(releaseFactoringsellNextEtCurrency.getText().toString());
//            issueFactoringReq.setInsurer(releaseFactoringsellNextEtAssurer.getText().toString());
//            issueFactoringReq.setIndateMessage(releaseFactoringsellNextEtValidityCredit.getText().toString());
            /*
            "ST0204":"111",//保理合同url(必填)
            "ST0205":"222",//贸易合同url
            "ST0206":"333",//运输单据url
            "ST0207":"444",//应收账款转让通知书url(必填)
            "ST0208":"555",//财务报表url(必填)
            "ST0209":"666",//货物购买方url
             */
//        issueFactoringReq.setST0208(releaseFactoringsellSubmitTvFinancialstatementUpload.getText().toString());
//        issueFactoringReq.setST0204(releaseFactoringsellSubmitTvInsurancecompactUpload.getText().toString());
//        issueFactoringReq.setST0209(releaseFactoringsellSubmitTvCompactUpload.getText().toString());
//        issueFactoringReq.setST0206(releaseFactoringsellSubmitTvTransportdocumentUpload.getText().toString());
//        issueFactoringReq.setST0207(releaseFactoringsellSubmitLlNoticeUpload.getText().toString());
//        issueFactoringReq.setST0205(releaseFactoringsellSubmitTvTradingcontractUpload.getText().toString());


            releaseFactoringsellNextEtSell.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getSeller()));
            releaseFactoringsellNextEtAmount.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getAmount()));
            releaseFactoringsellNextEtTransferTates.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getTransferRate()));
            releaseFactoringsellNextEtMaturityDate.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getMaturity()));
            releaseFactoringsellNextEtName.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getFactoringName()));
            releaseFactoringsellNextEtOriginalCreditor.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getOriginators()));
            releaseFactoringsellNextEtOriginalDebtor.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getObligors()));
            releaseFactoringsellNextEtCurrency.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getCurrency()));
            releaseFactoringsellNextEtAssurer.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getInsurer()));
            releaseFactoringsellNextEtValidityCredit.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getIndateMessage()));
            companyOrgId = StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getCompanyOrgId());

//            releaseFactoringsellSubmitTvFinancialstatementUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0208()));
//            releaseFactoringsellSubmitTvInsurancecompactUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0204()));
//            releaseFactoringsellSubmitTvCompactUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0209()));
//            releaseFactoringsellSubmitTvTransportdocumentUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0206()));
//            releaseFactoringsellSubmitLlNoticeUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0207()));
//            releaseFactoringsellSubmitTvTradingcontractUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0205()));
            if (!StringUtils.isEmpty(handleFactoringDetailSearchRes.getData().getST0204()) && handleFactoringDetailSearchRes.getData().getST0204().length() > 5) {
                issueFactoringReq.setST0204(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0204()));
                filesstr2.addAll(StringUtils.splitStr(handleFactoringDetailSearchRes.getData().getST0204(), ";"));
                photoViewInsurancecompact();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailSearchRes.getData().getST0205()) && handleFactoringDetailSearchRes.getData().getST0205().length() > 5) {
                issueFactoringReq.setST0205(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0205()));
                filesstr6.addAll(StringUtils.splitStr(handleFactoringDetailSearchRes.getData().getST0205(), ";"));
                photoViewTradingcontract();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailSearchRes.getData().getST0206()) && handleFactoringDetailSearchRes.getData().getST0206().length() > 5) {
                issueFactoringReq.setST0206(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0206()));
                filesstr4.addAll(StringUtils.splitStr(handleFactoringDetailSearchRes.getData().getST0206(), ";"));
                photoViewTransportdocument();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailSearchRes.getData().getST0207()) && handleFactoringDetailSearchRes.getData().getST0207().length() > 5) {
                issueFactoringReq.setST0207(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0207()));
                filesstr5.addAll(StringUtils.splitStr(handleFactoringDetailSearchRes.getData().getST0207(), ";"));
                photoViewNotice();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailSearchRes.getData().getST0208()) && handleFactoringDetailSearchRes.getData().getST0208().length() > 5) {
                issueFactoringReq.setST0208(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0208()));
                filesstr1.addAll(StringUtils.splitStr(handleFactoringDetailSearchRes.getData().getST0208(), ";"));
                photoViewFinancialstatement();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailSearchRes.getData().getST0209()) && handleFactoringDetailSearchRes.getData().getST0209().length() > 5) {
                issueFactoringReq.setST0209(StringUtils.nullStrToEmpty(handleFactoringDetailSearchRes.getData().getST0209()));
                filesstr3.addAll(StringUtils.splitStr(handleFactoringDetailSearchRes.getData().getST0209(), ";"));
                photoViewCompact();
            }
            countryid = handleFactoringDetailSearchRes.getData().getCountryId();
            id_area = handleFactoringDetailSearchRes.getData().getAreaId();
            if (array2_arear != null && array2_arear.length > 0 && !StringUtils.isEmpty(id_area)) {
//                int idarea = Integer.valueOf(id_area);
                releaseFactoringsellNextEtArea.setText(StringUtils.nullStrToEmpty(arearmap1.get(id_area)));
                getSecondeCountryList();
            }
            if ("1".equals(handleFactoringDetailSearchRes.getData().getFactoringType())) {
                factoringtype = 1;
                releaseFactoringsellNextEtType.setText(getString(R.string.market_facotring_type1));
            } else if ("2".equals(handleFactoringDetailSearchRes.getData().getFactoringType())) {
                factoringtype = 2;
                releaseFactoringsellNextEtType.setText(getString(R.string.market_facotring_type2));
            } else if ("3".equals(handleFactoringDetailSearchRes.getData().getFactoringType())) {
                factoringtype = 3;
                releaseFactoringsellNextEtType.setText(getString(R.string.market_facotring_type3));
            } else if ("4".equals(handleFactoringDetailSearchRes.getData().getFactoringType())) {
                releaseFactoringsellNextEtType.setText(getString(R.string.market_facotring_type4));
                factoringtype = 4;
            }

            if ("1".equals(handleFactoringDetailSearchRes.getData().getIsCove())) {
                isCove = 1;
                releaseFactoringsellNextEtInsure.setText(R.string.issue_factoring_sell_yes);
            } else if ("2".equals(handleFactoringDetailSearchRes.getData().getIsCove())) {
                releaseFactoringsellNextEtInsure.setText(R.string.issue_factoring_sell_no);
                isCove = 2;
            }
        }
    }


    private void submitUpdate(IssueFactoringReq issueFactoringReq) {
        showWaitDialog();
        MyLogger.pLog().i("编辑更新资产接口");
        ActionPresenter.getInstance().handleFactoringDetailUpdateRet(issueFactoringReq).enqueue(new Callback<MarketFactoringSellRes>() {
            @Override
            public void onResponse(Call<MarketFactoringSellRes> call, Response<MarketFactoringSellRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    closeWaitDialog();
                    if (response.body().getCode() == 300) {
                        DisPatcher.sendRefreshSellAssertBroadcast(getActivity());
//                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_save));
                        getActivity().finish();
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                        ToastUtils.showShort(response.body().getMessage());
                    }
                } else {
                    ToastUtils.showShort(response.message());
                    MyLogger.pLog().e(response.message());
//                    MyLogger.pLog().e(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    @OnClick({R.id.release_factoringsell_next_ll_rl_financialstatementiv1, R.id.release_factoringsell_next_ll_rl1_financialstatement1, R.id.release_factoringsell_next_ll_rl_financialstatementivdelete1, R.id.release_factoringsell_next_ll_rl_financialstatement1, R.id.release_factoringsell_next_ll_rl_financialstatementiv11, R.id.release_factoringsell_next_ll_rl_financialstatementiv2, R.id.release_factoringsell_next_ll_rl2_financialstatement2, R.id.release_factoringsell_next_ll_rl_financialstatementivdelete2, R.id.release_factoringsell_next_ll_rl_financialstatement2, R.id.release_factoringsell_next_ll_rl_financialstatementiv21, R.id.release_factoringsell_next_ll_rl_financialstatementiv3, R.id.release_factoringsell_next_ll_rl3_financialstatement3, R.id.release_factoringsell_next_ll_rl_financialstatementivdelete3, R.id.release_factoringsell_next_ll_rl_financialstatement3, R.id.release_factoringsell_next_ll_rl_financialstatementiv31, R.id.release_factoringsell_next_ll_rl_financialstatementiv4, R.id.release_factoringsell_next_ll_rl4_financialstatement4, R.id.release_factoringsell_next_ll_rl_financialstatementivdelete4, R.id.release_factoringsell_next_ll_rl_financialstatement4, R.id.release_factoringsell_next_ll_rl_financialstatementiv41, R.id.release_factoringsell_next_ll_rl_financialstatementiv5, R.id.release_factoringsell_next_ll_rl5_financialstatement5, R.id.release_factoringsell_next_ll_rl_financialstatementivdelete5, R.id.release_factoringsell_next_ll_rl_financialstatement5, R.id.release_factoringsell_next_ll_rl_financialstatementiv51, R.id.release_factoringsell_next_ll_rl_financialstatementiv6, R.id.release_factoringsell_next_ll_rl6_financialstatement6, R.id.release_factoringsell_next_ll_rl_financialstatementivdelete6, R.id.release_factoringsell_next_ll_rl_financialstatement6, R.id.release_factoringsell_next_ll_rl_financialstatementiv61, R.id.release_factoringsell_next_ll_financialstatement})
    public void onViewClickedfinancialstatement(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv1:
                if ("jpg".equals(StringUtils.isType(filesstr1.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr1.get(0));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl1_financialstatement1:
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementivdelete1:
            case R.id.release_factoringsell_next_ll_rl_financialstatement1:
                //删除
                filesstr1.remove(0);
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv11:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv2:
                if ("jpg".equals(StringUtils.isType(filesstr1.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr1.get(1));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl2_financialstatement2:
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementivdelete2:
            case R.id.release_factoringsell_next_ll_rl_financialstatement2:
                //删除
                filesstr1.remove(1);
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv21:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv3:
                if ("jpg".equals(StringUtils.isType(filesstr1.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr1.get(2));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl3_financialstatement3:
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementivdelete3:
            case R.id.release_factoringsell_next_ll_rl_financialstatement3:
                //删除
                filesstr1.remove(2);
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv31:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv4:
                if ("jpg".equals(StringUtils.isType(filesstr1.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr1.get(3));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl4_financialstatement4:
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementivdelete4:
            case R.id.release_factoringsell_next_ll_rl_financialstatement4:
                //删除
                filesstr1.remove(3);
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv41:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv5:
                if ("jpg".equals(StringUtils.isType(filesstr1.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr1.get(4));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl5_financialstatement5:
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementivdelete5:
            case R.id.release_factoringsell_next_ll_rl_financialstatement5:
                //删除
                filesstr1.remove(4);
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv51:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv6:
                if ("jpg".equals(StringUtils.isType(filesstr1.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr1.get(5));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl6_financialstatement6:
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementivdelete6:
            case R.id.release_factoringsell_next_ll_rl_financialstatement6:
                //删除
                filesstr1.remove(5);
                break;
            case R.id.release_factoringsell_next_ll_rl_financialstatementiv61:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_financialstatement:
                break;
        }
        photoViewFinancialstatement();
    }

    public void photoViewFinancialstatement() {
        issueFactoringReq.setST0208(StringUtils.contentSplitStr(filesstr1, ";"));
        releaseFactoringsellNextLlRlFinancialstatement1.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlFinancialstatementiv11.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlFinancialstatement2.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlFinancialstatementiv21.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlFinancialstatement3.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlFinancialstatementiv31.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlFinancialstatement4.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlFinancialstatementiv41.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlFinancialstatement5.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlFinancialstatementiv51.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlFinancialstatement6.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlFinancialstatementiv61.setVisibility(View.VISIBLE);

        if (filesstr1.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr1.get(0)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr1.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlFinancialstatementiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr1.get(0)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr1.get(0)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlFinancialstatement1.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlFinancialstatementiv11.setVisibility(View.GONE);
        }
        if (filesstr1.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr1.get(1)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr1.get(1)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr1.get(1)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlFinancialstatement2.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlFinancialstatementiv21.setVisibility(View.GONE);
        }
        if (filesstr1.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr1.get(2)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr1.get(2)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr1.get(2)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlFinancialstatement3.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlFinancialstatementiv31.setVisibility(View.GONE);
        }
        if (filesstr1.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr1.get(3)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr1.get(3)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr1.get(3)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlFinancialstatement4.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlFinancialstatementiv41.setVisibility(View.GONE);
        }
        if (filesstr1.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr1.get(4)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr1.get(4)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr1.get(4)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlFinancialstatement5.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlFinancialstatementiv51.setVisibility(View.GONE);
        }
        if (filesstr1.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr1.get(5)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr1.get(5)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr1.get(5)))) {
                releaseFactoringsellNextLlRlFinancialstatementiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlFinancialstatement6.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlFinancialstatementiv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_factoringsell_next_ll_rl_insurancecompactiv1, R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete1, R.id.release_factoringsell_next_ll_rl1_insurancecompact1, R.id.release_factoringsell_next_ll_rl_insurancecompact1, R.id.release_factoringsell_next_ll_rl_insurancecompactiv11, R.id.release_factoringsell_next_ll_rl_insurancecompactiv2, R.id.release_factoringsell_next_ll_rl2_insurancecompact2, R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete2, R.id.release_factoringsell_next_ll_rl_insurancecompact2, R.id.release_factoringsell_next_ll_rl_insurancecompactiv21, R.id.release_factoringsell_next_ll_rl_insurancecompactiv3, R.id.release_factoringsell_next_ll_rl3_insurancecompact3, R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete3, R.id.release_factoringsell_next_ll_rl_insurancecompact3, R.id.release_factoringsell_next_ll_rl_insurancecompactiv31, R.id.release_factoringsell_next_ll_rl_insurancecompactiv4, R.id.release_factoringsell_next_ll_rl4_insurancecompact4, R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete4, R.id.release_factoringsell_next_ll_rl_insurancecompact4, R.id.release_factoringsell_next_ll_rl_insurancecompactiv41, R.id.release_factoringsell_next_ll_rl_insurancecompactiv5, R.id.release_factoringsell_next_ll_rl5_insurancecompact5, R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete5, R.id.release_factoringsell_next_ll_rl_insurancecompact5, R.id.release_factoringsell_next_ll_rl_insurancecompactiv51, R.id.release_factoringsell_next_ll_rl_insurancecompactiv6, R.id.release_factoringsell_next_ll_rl6_insurancecompact6, R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete6, R.id.release_factoringsell_next_ll_rl_insurancecompact6, R.id.release_factoringsell_next_ll_rl_insurancecompactiv61})
    public void onViewClickedInsurancecompact(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv1:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(0));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl1_insurancecompact1:
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete1:
            case R.id.release_factoringsell_next_ll_rl_insurancecompact1:
                //删除
                filesstr2.remove(0);
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv11:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv2:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(1));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl2_insurancecompact2:
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete2:
            case R.id.release_factoringsell_next_ll_rl_insurancecompact2:
                //删除
                filesstr2.remove(1);
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv21:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv3:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(2));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl3_insurancecompact3:
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete3:
            case R.id.release_factoringsell_next_ll_rl_insurancecompact3:
                //删除
                filesstr2.remove(2);
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv31:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv4:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(3));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl4_insurancecompact4:
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete4:
            case R.id.release_factoringsell_next_ll_rl_insurancecompact4:
                //删除
                filesstr2.remove(3);
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv41:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv5:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(4));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl5_insurancecompact5:
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete5:
            case R.id.release_factoringsell_next_ll_rl_insurancecompact5:
                //删除
                filesstr2.remove(4);
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv51:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv6:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(5));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl6_insurancecompact6:
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactivdelete6:
            case R.id.release_factoringsell_next_ll_rl_insurancecompact6:
                //删除
                filesstr2.remove(5);
                break;
            case R.id.release_factoringsell_next_ll_rl_insurancecompactiv61:
                actionCode = 2;
                bottomDialog();
                break;
        }
        photoViewInsurancecompact();
    }

    public void photoViewInsurancecompact() {
        issueFactoringReq.setST0204(StringUtils.contentSplitStr(filesstr2, ";"));
        releaseFactoringsellNextLlRlInsurancecompact1.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlInsurancecompactiv11.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlInsurancecompact2.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlInsurancecompactiv21.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlInsurancecompact3.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlInsurancecompactiv31.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlInsurancecompact4.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlInsurancecompactiv41.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlInsurancecompact5.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlInsurancecompactiv51.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlInsurancecompact6.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlInsurancecompactiv61.setVisibility(View.VISIBLE);

        if (filesstr2.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(0)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr2.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlInsurancecompactiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(0)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(0)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlInsurancecompact1.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlInsurancecompactiv11.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(1)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(1)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(1)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlInsurancecompact2.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlInsurancecompactiv21.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(2)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(2)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(2)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlInsurancecompact3.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlInsurancecompactiv31.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(3)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(3)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(3)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlInsurancecompact4.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlInsurancecompactiv41.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(4)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(4)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(4)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlInsurancecompact5.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlInsurancecompactiv51.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(5)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(5)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(5)))) {
                releaseFactoringsellNextLlRlInsurancecompactiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlInsurancecompact6.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlInsurancecompactiv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_factoringsell_next_ll_rl_compactiv1, R.id.release_factoringsell_next_ll_rl1_compact1, R.id.release_factoringsell_next_ll_rl_compactivdelete1, R.id.release_factoringsell_next_ll_rl_compact1, R.id.release_factoringsell_next_ll_rl_compactiv11, R.id.release_factoringsell_next_ll_rl_compactiv2, R.id.release_factoringsell_next_ll_rl2_compact2, R.id.release_factoringsell_next_ll_rl_compactivdelete2, R.id.release_factoringsell_next_ll_rl_compact2, R.id.release_factoringsell_next_ll_rl_compactiv21, R.id.release_factoringsell_next_ll_rl_compactiv3, R.id.release_factoringsell_next_ll_rl3_compact3, R.id.release_factoringsell_next_ll_rl_compactivdelete3, R.id.release_factoringsell_next_ll_rl_compact3, R.id.release_factoringsell_next_ll_rl_compactiv31, R.id.release_factoringsell_next_ll_rl_compactiv4, R.id.release_factoringsell_next_ll_rl4_compact4, R.id.release_factoringsell_next_ll_rl_compactivdelete4, R.id.release_factoringsell_next_ll_rl_compact4, R.id.release_factoringsell_next_ll_rl_compactiv41, R.id.release_factoringsell_next_ll_rl_compactiv5, R.id.release_factoringsell_next_ll_rl5_compact5, R.id.release_factoringsell_next_ll_rl_compactivdelete5, R.id.release_factoringsell_next_ll_rl_compact5, R.id.release_factoringsell_next_ll_rl_compactiv51, R.id.release_factoringsell_next_ll_rl_compactiv6, R.id.release_factoringsell_next_ll_rl6_compact6, R.id.release_factoringsell_next_ll_rl_compactivdelete6, R.id.release_factoringsell_next_ll_rl_compact6, R.id.release_factoringsell_next_ll_rl_compactiv61})
    public void onViewClickedCompact(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_ll_rl_compactiv1:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(0));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl1_compact1:
                break;
            case R.id.release_factoringsell_next_ll_rl_compactivdelete1:
            case R.id.release_factoringsell_next_ll_rl_compact1:
                //删除
                filesstr3.remove(0);
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv11:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv2:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(1));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl2_compact2:
                break;
            case R.id.release_factoringsell_next_ll_rl_compactivdelete2:
            case R.id.release_factoringsell_next_ll_rl_compact2:
                //删除
                filesstr3.remove(1);
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv21:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv3:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(2));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl3_compact3:
                break;
            case R.id.release_factoringsell_next_ll_rl_compactivdelete3:
            case R.id.release_factoringsell_next_ll_rl_compact3:
                //删除
                filesstr3.remove(2);
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv31:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv4:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(3));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl4_compact4:
                break;
            case R.id.release_factoringsell_next_ll_rl_compactivdelete4:
            case R.id.release_factoringsell_next_ll_rl_compact4:
                //删除
                filesstr3.remove(3);
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv41:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv5:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(4));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl5_compact5:
                break;
            case R.id.release_factoringsell_next_ll_rl_compactivdelete5:
            case R.id.release_factoringsell_next_ll_rl_compact5:
                //删除
                filesstr3.remove(4);
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv51:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv6:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(5));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl6_compact6:
                break;
            case R.id.release_factoringsell_next_ll_rl_compactivdelete6:
            case R.id.release_factoringsell_next_ll_rl_compact6:
                //删除
                filesstr3.remove(5);
                break;
            case R.id.release_factoringsell_next_ll_rl_compactiv61:
                actionCode = 3;
                bottomDialog();
                break;
        }
        photoViewCompact();
    }

    public void photoViewCompact() {
        issueFactoringReq.setST0209(StringUtils.contentSplitStr(filesstr3, ";"));
        releaseFactoringsellNextLlRlCompact1.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlCompactiv11.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlCompact2.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlCompactiv21.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlCompact3.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlCompactiv31.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlCompact4.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlCompactiv41.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlCompact5.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlCompactiv51.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlCompact6.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlCompactiv61.setVisibility(View.VISIBLE);

        if (filesstr3.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(0)))) {
                releaseFactoringsellNextLlRlCompactiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr3.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlCompactiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(0)))) {
                releaseFactoringsellNextLlRlCompactiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(0)))) {
                releaseFactoringsellNextLlRlCompactiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlCompact1.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlCompactiv11.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(1)))) {
                releaseFactoringsellNextLlRlCompactiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(1)))) {
                releaseFactoringsellNextLlRlCompactiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(1)))) {
                releaseFactoringsellNextLlRlCompactiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlCompact2.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlCompactiv21.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(2)))) {
                releaseFactoringsellNextLlRlCompactiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(2)))) {
                releaseFactoringsellNextLlRlCompactiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(2)))) {
                releaseFactoringsellNextLlRlCompactiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlCompact3.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlCompactiv31.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(3)))) {
                releaseFactoringsellNextLlRlCompactiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(3)))) {
                releaseFactoringsellNextLlRlCompactiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(3)))) {
                releaseFactoringsellNextLlRlCompactiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlCompact4.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlCompactiv41.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(4)))) {
                releaseFactoringsellNextLlRlCompactiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(4)))) {
                releaseFactoringsellNextLlRlCompactiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(4)))) {
                releaseFactoringsellNextLlRlCompactiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlCompact5.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlCompactiv51.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(5)))) {
                releaseFactoringsellNextLlRlCompactiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(5)))) {
                releaseFactoringsellNextLlRlCompactiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(5)))) {
                releaseFactoringsellNextLlRlCompactiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlCompact6.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlCompactiv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_factoringsell_next_ll_rl_transportdocumentiv1, R.id.release_factoringsell_next_ll_rl1_transportdocument1, R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete1, R.id.release_factoringsell_next_ll_rl_transportdocument1, R.id.release_factoringsell_next_ll_rl_transportdocumentiv11, R.id.release_factoringsell_next_ll_rl_transportdocumentiv2, R.id.release_factoringsell_next_ll_rl2_transportdocument2, R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete2, R.id.release_factoringsell_next_ll_rl_transportdocument2, R.id.release_factoringsell_next_ll_rl_transportdocumentiv21, R.id.release_factoringsell_next_ll_rl_transportdocumentiv3, R.id.release_factoringsell_next_ll_rl3_transportdocument3, R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete3, R.id.release_factoringsell_next_ll_rl_transportdocument3, R.id.release_factoringsell_next_ll_rl_transportdocumentiv31, R.id.release_factoringsell_next_ll_rl_transportdocumentiv4, R.id.release_factoringsell_next_ll_rl4_transportdocument4, R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete4, R.id.release_factoringsell_next_ll_rl_transportdocument4, R.id.release_factoringsell_next_ll_rl_transportdocumentiv41, R.id.release_factoringsell_next_ll_rl_transportdocumentiv5, R.id.release_factoringsell_next_ll_rl5_transportdocument5, R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete5, R.id.release_factoringsell_next_ll_rl_transportdocument5, R.id.release_factoringsell_next_ll_rl_transportdocumentiv51, R.id.release_factoringsell_next_ll_rl_transportdocumentiv6, R.id.release_factoringsell_next_ll_rl6_transportdocument6, R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete6, R.id.release_factoringsell_next_ll_rl_transportdocument6, R.id.release_factoringsell_next_ll_rl_transportdocumentiv61})
    public void onViewClickedTransportdocument(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv1:
                if ("jpg".equals(StringUtils.isType(filesstr4.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr4.get(0));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl1_transportdocument1:
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete1:
            case R.id.release_factoringsell_next_ll_rl_transportdocument1:
                //删除
                filesstr4.remove(0);
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv11:
                actionCode = 4;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv2:
                if ("jpg".equals(StringUtils.isType(filesstr4.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr4.get(1));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl2_transportdocument2:
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete2:
            case R.id.release_factoringsell_next_ll_rl_transportdocument2:
                //删除
                filesstr4.remove(1);
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv21:
                actionCode = 4;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv3:
                if ("jpg".equals(StringUtils.isType(filesstr4.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr4.get(2));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl3_transportdocument3:
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete3:
            case R.id.release_factoringsell_next_ll_rl_transportdocument3:
                //删除
                filesstr4.remove(2);
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv31:
                actionCode = 4;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv4:
                if ("jpg".equals(StringUtils.isType(filesstr4.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr4.get(3));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl4_transportdocument4:
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete4:
            case R.id.release_factoringsell_next_ll_rl_transportdocument4:
                //删除
                filesstr4.remove(3);
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv41:
                actionCode = 4;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv5:
                if ("jpg".equals(StringUtils.isType(filesstr4.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr4.get(4));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl5_transportdocument5:
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete5:
            case R.id.release_factoringsell_next_ll_rl_transportdocument5:
                //删除
                filesstr4.remove(4);
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv51:
                actionCode = 4;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv6:
                if ("jpg".equals(StringUtils.isType(filesstr4.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr4.get(5));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl6_transportdocument6:
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentivdelete6:
            case R.id.release_factoringsell_next_ll_rl_transportdocument6:
                //删除
                filesstr4.remove(5);
                break;
            case R.id.release_factoringsell_next_ll_rl_transportdocumentiv61:
                actionCode = 4;
                bottomDialog();
                break;
        }
        photoViewTransportdocument();
    }

    public void photoViewTransportdocument() {
        issueFactoringReq.setST0206(StringUtils.contentSplitStr(filesstr4, ";"));
        releaseFactoringsellNextLlRlTransportdocument1.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTransportdocumentiv11.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTransportdocument2.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTransportdocumentiv21.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTransportdocument3.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTransportdocumentiv31.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTransportdocument4.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTransportdocumentiv41.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTransportdocument5.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTransportdocumentiv51.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTransportdocument6.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTransportdocumentiv61.setVisibility(View.VISIBLE);

        if (filesstr4.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr4.get(0)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr4.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlTransportdocumentiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr4.get(0)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr4.get(0)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTransportdocument1.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTransportdocumentiv11.setVisibility(View.GONE);
        }
        if (filesstr4.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr4.get(1)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr4.get(1)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr4.get(1)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTransportdocument2.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTransportdocumentiv21.setVisibility(View.GONE);
        }
        if (filesstr4.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr4.get(2)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr4.get(2)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr4.get(2)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTransportdocument3.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTransportdocumentiv31.setVisibility(View.GONE);
        }
        if (filesstr4.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr4.get(3)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr4.get(3)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr4.get(3)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTransportdocument4.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTransportdocumentiv41.setVisibility(View.GONE);
        }
        if (filesstr4.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr4.get(4)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr4.get(4)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr4.get(4)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTransportdocument5.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTransportdocumentiv51.setVisibility(View.GONE);
        }
        if (filesstr4.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr4.get(5)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr4.get(5)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr4.get(5)))) {
                releaseFactoringsellNextLlRlTransportdocumentiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTransportdocument6.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTransportdocumentiv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_factoringsell_next_ll_rl_noticeiv1, R.id.release_factoringsell_next_ll_rl1_notice1, R.id.release_factoringsell_next_ll_rl_noticeivdelete1, R.id.release_factoringsell_next_ll_rl_notice1, R.id.release_factoringsell_next_ll_rl_noticeiv11, R.id.release_factoringsell_next_ll_rl_noticeiv2, R.id.release_factoringsell_next_ll_rl2_notice2, R.id.release_factoringsell_next_ll_rl_noticeivdelete2, R.id.release_factoringsell_next_ll_rl_notice2, R.id.release_factoringsell_next_ll_rl_noticeiv21, R.id.release_factoringsell_next_ll_rl_noticeiv3, R.id.release_factoringsell_next_ll_rl3_notice3, R.id.release_factoringsell_next_ll_rl_noticeivdelete3, R.id.release_factoringsell_next_ll_rl_notice3, R.id.release_factoringsell_next_ll_rl_noticeiv31, R.id.release_factoringsell_next_ll_rl_noticeiv4, R.id.release_factoringsell_next_ll_rl4_notice4, R.id.release_factoringsell_next_ll_rl_noticeivdelete4, R.id.release_factoringsell_next_ll_rl_notice4, R.id.release_factoringsell_next_ll_rl_noticeiv41, R.id.release_factoringsell_next_ll_rl_noticeiv5, R.id.release_factoringsell_next_ll_rl5_notice5, R.id.release_factoringsell_next_ll_rl_noticeivdelete5, R.id.release_factoringsell_next_ll_rl_notice5, R.id.release_factoringsell_next_ll_rl_noticeiv51, R.id.release_factoringsell_next_ll_rl_noticeiv6, R.id.release_factoringsell_next_ll_rl6_notice6, R.id.release_factoringsell_next_ll_rl_noticeivdelete6, R.id.release_factoringsell_next_ll_rl_notice6, R.id.release_factoringsell_next_ll_rl_noticeiv61})
    public void onViewClickedNotice(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_ll_rl_noticeiv1:
                if ("jpg".equals(StringUtils.isType(filesstr5.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr5.get(0));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl1_notice1:
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeivdelete1:
            case R.id.release_factoringsell_next_ll_rl_notice1:
                //删除
                filesstr5.remove(0);
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv11:
                actionCode = 5;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv2:
                if ("jpg".equals(StringUtils.isType(filesstr5.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr5.get(1));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl2_notice2:
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeivdelete2:
            case R.id.release_factoringsell_next_ll_rl_notice2:
                //删除
                filesstr5.remove(1);
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv21:
                actionCode = 5;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv3:
                if ("jpg".equals(StringUtils.isType(filesstr5.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr5.get(2));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl3_notice3:
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeivdelete3:
            case R.id.release_factoringsell_next_ll_rl_notice3:
                //删除
                filesstr5.remove(2);
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv31:
                actionCode = 5;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv4:
                if ("jpg".equals(StringUtils.isType(filesstr5.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr5.get(3));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl4_notice4:
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeivdelete4:
            case R.id.release_factoringsell_next_ll_rl_notice4:
                //删除
                filesstr5.remove(3);
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv41:
                actionCode = 5;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv5:
                if ("jpg".equals(StringUtils.isType(filesstr5.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr5.get(4));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl5_notice5:
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeivdelete5:
            case R.id.release_factoringsell_next_ll_rl_notice5:
                //删除
                filesstr5.remove(4);
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv51:
                actionCode = 5;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv6:
                if ("jpg".equals(StringUtils.isType(filesstr5.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr5.get(5));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl6_notice6:
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeivdelete6:
            case R.id.release_factoringsell_next_ll_rl_notice6:
                //删除
                filesstr5.remove(5);
                break;
            case R.id.release_factoringsell_next_ll_rl_noticeiv61:
                actionCode = 5;
                bottomDialog();
                break;
        }
        photoViewNotice();
    }

    public void photoViewNotice() {
        issueFactoringReq.setST0207(StringUtils.contentSplitStr(filesstr5, ";"));
        releaseFactoringsellNextLlRlNotice1.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlNoticeiv11.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlNotice2.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlNoticeiv21.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlNotice3.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlNoticeiv31.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlNotice4.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlNoticeiv41.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlNotice5.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlNoticeiv51.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlNotice6.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlNoticeiv61.setVisibility(View.VISIBLE);

        if (filesstr5.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr5.get(0)))) {
                releaseFactoringsellNextLlRlNoticeiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr5.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlNoticeiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr5.get(0)))) {
                releaseFactoringsellNextLlRlNoticeiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr5.get(0)))) {
                releaseFactoringsellNextLlRlNoticeiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlNotice1.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlNoticeiv11.setVisibility(View.GONE);
        }
        if (filesstr5.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr5.get(1)))) {
                releaseFactoringsellNextLlRlNoticeiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr5.get(1)))) {
                releaseFactoringsellNextLlRlNoticeiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr5.get(1)))) {
                releaseFactoringsellNextLlRlNoticeiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlNotice2.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlNoticeiv21.setVisibility(View.GONE);
        }
        if (filesstr5.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr5.get(2)))) {
                releaseFactoringsellNextLlRlNoticeiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr5.get(2)))) {
                releaseFactoringsellNextLlRlNoticeiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr5.get(2)))) {
                releaseFactoringsellNextLlRlNoticeiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlNotice3.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlNoticeiv31.setVisibility(View.GONE);
        }
        if (filesstr5.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr5.get(3)))) {
                releaseFactoringsellNextLlRlNoticeiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr5.get(3)))) {
                releaseFactoringsellNextLlRlNoticeiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr5.get(3)))) {
                releaseFactoringsellNextLlRlNoticeiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlNotice4.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlNoticeiv41.setVisibility(View.GONE);
        }
        if (filesstr5.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr5.get(4)))) {
                releaseFactoringsellNextLlRlNoticeiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr5.get(4)))) {
                releaseFactoringsellNextLlRlNoticeiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr5.get(4)))) {
                releaseFactoringsellNextLlRlNoticeiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlNotice5.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlNoticeiv51.setVisibility(View.GONE);
        }
        if (filesstr5.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr5.get(5)))) {
                releaseFactoringsellNextLlRlNoticeiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr5.get(5)))) {
                releaseFactoringsellNextLlRlNoticeiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr5.get(5)))) {
                releaseFactoringsellNextLlRlNoticeiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlNotice6.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlNoticeiv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_factoringsell_next_ll_rl_tradingcontractiv1, R.id.release_factoringsell_next_ll_rl1_tradingcontract1, R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete1, R.id.release_factoringsell_next_ll_rl_tradingcontract1, R.id.release_factoringsell_next_ll_rl_tradingcontractiv11, R.id.release_factoringsell_next_ll_rl_tradingcontractiv2, R.id.release_factoringsell_next_ll_rl2_tradingcontract2, R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete2, R.id.release_factoringsell_next_ll_rl_tradingcontract2, R.id.release_factoringsell_next_ll_rl_tradingcontractiv21, R.id.release_factoringsell_next_ll_rl_tradingcontractiv3, R.id.release_factoringsell_next_ll_rl3_tradingcontract3, R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete3, R.id.release_factoringsell_next_ll_rl_tradingcontract3, R.id.release_factoringsell_next_ll_rl_tradingcontractiv31, R.id.release_factoringsell_next_ll_rl_tradingcontractiv4, R.id.release_factoringsell_next_ll_rl4_tradingcontract4, R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete4, R.id.release_factoringsell_next_ll_rl_tradingcontract4, R.id.release_factoringsell_next_ll_rl_tradingcontractiv41, R.id.release_factoringsell_next_ll_rl_tradingcontractiv5, R.id.release_factoringsell_next_ll_rl5_tradingcontract5, R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete5, R.id.release_factoringsell_next_ll_rl_tradingcontract5, R.id.release_factoringsell_next_ll_rl_tradingcontractiv51, R.id.release_factoringsell_next_ll_rl_tradingcontractiv6, R.id.release_factoringsell_next_ll_rl6_tradingcontract6, R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete6, R.id.release_factoringsell_next_ll_rl_tradingcontract6, R.id.release_factoringsell_next_ll_rl_tradingcontractiv61})
    public void onViewClickedTradingcontract(View view) {
        switch (view.getId()) {
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv1:
                if ("jpg".equals(StringUtils.isType(filesstr6.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr6.get(0));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl1_tradingcontract1:
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete1:
            case R.id.release_factoringsell_next_ll_rl_tradingcontract1:
                //删除
                filesstr6.remove(0);
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv11:
                actionCode = 6;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv2:
                if ("jpg".equals(StringUtils.isType(filesstr6.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr6.get(1));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl2_tradingcontract2:
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete2:
            case R.id.release_factoringsell_next_ll_rl_tradingcontract2:
                //删除
                filesstr6.remove(1);
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv21:
                actionCode = 6;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv3:
                if ("jpg".equals(StringUtils.isType(filesstr6.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr6.get(2));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl3_tradingcontract3:
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete3:
            case R.id.release_factoringsell_next_ll_rl_tradingcontract3:
                //删除
                filesstr6.remove(2);
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv31:
                actionCode = 6;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv4:
                if ("jpg".equals(StringUtils.isType(filesstr6.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr6.get(3));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl4_tradingcontract4:
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete4:
            case R.id.release_factoringsell_next_ll_rl_tradingcontract4:
                //删除
                filesstr6.remove(3);
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv41:
                actionCode = 6;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv5:
                if ("jpg".equals(StringUtils.isType(filesstr6.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr6.get(4));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl5_tradingcontract5:
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete5:
            case R.id.release_factoringsell_next_ll_rl_tradingcontract5:
                //删除
                filesstr6.remove(4);
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv51:
                actionCode = 6;
                bottomDialog();
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv6:
                if ("jpg".equals(StringUtils.isType(filesstr6.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr6.get(5));
                }
                break;
            case R.id.release_factoringsell_next_ll_rl6_tradingcontract6:
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractivdelete6:
            case R.id.release_factoringsell_next_ll_rl_tradingcontract6:
                //删除
                filesstr6.remove(5);
                break;
            case R.id.release_factoringsell_next_ll_rl_tradingcontractiv61:
                actionCode = 6;
                bottomDialog();
                break;
        }
        photoViewTradingcontract();
    }

    public void photoViewTradingcontract() {
        issueFactoringReq.setST0205(StringUtils.contentSplitStr(filesstr6, ";"));
        releaseFactoringsellNextLlRlTradingcontract1.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTradingcontractiv11.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTradingcontract2.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTradingcontractiv21.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTradingcontract3.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTradingcontractiv31.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTradingcontract4.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTradingcontractiv41.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTradingcontract5.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTradingcontractiv51.setVisibility(View.VISIBLE);
        releaseFactoringsellNextLlRlTradingcontract6.setVisibility(View.GONE);
        releaseFactoringsellNextLlRlTradingcontractiv61.setVisibility(View.VISIBLE);

        if (filesstr6.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr6.get(0)))) {
                releaseFactoringsellNextLlRlTradingcontractiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr6.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlTradingcontractiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr6.get(0)))) {
                releaseFactoringsellNextLlRlTradingcontractiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr6.get(0)))) {
                releaseFactoringsellNextLlRlTradingcontractiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTradingcontract1.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTradingcontractiv11.setVisibility(View.GONE);
        }
        if (filesstr6.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr6.get(1)))) {
                releaseFactoringsellNextLlRlTradingcontractiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr6.get(1)))) {
                releaseFactoringsellNextLlRlTradingcontractiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr6.get(1)))) {
                releaseFactoringsellNextLlRlTradingcontractiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTradingcontract2.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTradingcontractiv21.setVisibility(View.GONE);
        }
        if (filesstr6.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr6.get(2)))) {
                releaseFactoringsellNextLlRlTradingcontractiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr6.get(2)))) {
                releaseFactoringsellNextLlRlTradingcontractiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr6.get(2)))) {
                releaseFactoringsellNextLlRlTradingcontractiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTradingcontract3.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTradingcontractiv31.setVisibility(View.GONE);
        }
        if (filesstr6.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr6.get(3)))) {
                releaseFactoringsellNextLlRlTradingcontractiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr6.get(3)))) {
                releaseFactoringsellNextLlRlTradingcontractiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr6.get(3)))) {
                releaseFactoringsellNextLlRlTradingcontractiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTradingcontract4.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTradingcontractiv41.setVisibility(View.GONE);
        }
        if (filesstr6.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr6.get(4)))) {
                releaseFactoringsellNextLlRlTradingcontractiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr6.get(4)))) {
                releaseFactoringsellNextLlRlTradingcontractiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr6.get(4)))) {
                releaseFactoringsellNextLlRlTradingcontractiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTradingcontract5.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTradingcontractiv51.setVisibility(View.GONE);
        }
        if (filesstr6.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr6.get(5)))) {
                releaseFactoringsellNextLlRlTradingcontractiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr6.get(5)))) {
                releaseFactoringsellNextLlRlTradingcontractiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr6.get(5)))) {
                releaseFactoringsellNextLlRlTradingcontractiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseFactoringsellNextLlRlTradingcontract6.setVisibility(View.VISIBLE);
            releaseFactoringsellNextLlRlTradingcontractiv61.setVisibility(View.GONE);
        }
    }

    //编辑结束
//二次确认框
    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = getString(R.string.issue_forfaiting_sell_yesorno);
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(getActivity(), new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                /** 提交类型：1保存，2 提交*/
                issueFactoringReq.setCommitType("2");
                //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
                String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
                if (!StringUtils.isEmpty(assestId) && "2".equals(assestType)) {
                    issueFactoringReq.setUserType(userType);
                    issueFactoringReq.setCompanyOrgId(companyOrgId);
                    if (valideDetailData()) {
                        //更新资产接口
                        submitUpdate(setDate());
                    }
                } else {
                    if (valideDetailData()) {
                        submitDate(setDate());
                    }
                }
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
