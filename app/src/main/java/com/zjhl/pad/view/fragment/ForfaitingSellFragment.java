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
import com.vincent.filepicker.ToastUtil;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.ImageFile;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.zjhl.pad.app.utils.CodeEditInputFilter;
import com.zjhl.pad.app.utils.EditInputFilterOneHunderd;
import com.zjhl.pad.app.utils.EditInputMoneyFilter;
import com.zjhl.pad.app.utils.GlideImageLoader;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ScreenUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.TimeUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.IssueForfaitingReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.HandleFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.moudle.entity.res.UploadFileRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.ForfaitingIssuingActivity;
import com.zjhl.pad.view.activity.PicturePreviewActivity;
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

//import com.mobsandgeeks.saripaar.ValidationError;
//import com.mobsandgeeks.saripaar.Validator;
//import com.mobsandgeeks.saripaar.annotation.NotEmpty;
//import com.mobsandgeeks.saripaar.annotation.Order;
//import com.zjhl.pad.app.application.MyApplication;

/**
 * @desc: FactoringSellFragment
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.fragment
 * @author: pluto
 * @create: 2018/4/25 13:38
 * @projectname: nnkj
 **/
public class ForfaitingSellFragment extends BaseFragment/* implements Validator.ValidationListener */ {

    //    protected Validator validator;
    @BindView(R.id.release_forfaitingsell_next_tv_title)
    TextView releaseForfaitingsellNextTvTitle;
    @BindView(R.id.release_forfaitingsell_next_et_title)
    EditText releaseForfaitingsellNextEtTitle;
    @BindView(R.id.release_forfaitingsell_next_rl_title)
    LinearLayout releaseForfaitingsellNextRlTitle;
    @BindView(R.id.release_forfaitingsell_next_tv_letterphoto)
    TextView releaseForfaitingsellNextTvLetterphoto;
    @BindView(R.id.release_forfaitingsell_next_tv_letterphoto_upload)
    TextView releaseForfaitingsellNextTvLetterphotoUpload;
    @BindView(R.id.release_forfaitingsell_next_rl_letterphoto)
    RelativeLayout releaseForfaitingsellNextRlLetterphoto;
    @BindView(R.id.release_forfaitingsell_next_tv_issuingbankswift)
    TextView releaseForfaitingsellNextTvIssuingbankswift;
    @BindView(R.id.release_forfaitingsell_next_et_issuingbankswift)
//    @NotEmpty(messageResId = R.string.error_message)
//    @Order(1)
            EditText releaseForfaitingsellNextEtIssuingbankswift;
    @BindView(R.id.release_forfaitingsell_next_rl_issuingbankswift)
    LinearLayout releaseForfaitingsellNextRlIssuingbankswift;
    @BindView(R.id.release_forfaitingsell_next_tv_issuingbankfullname)
    TextView releaseForfaitingsellNextTvIssuingbankfullname;
    @BindView(R.id.release_forfaitingsell_next_et_issuingbankfullname)
    EditText releaseForfaitingsellNextEtIssuingbankfullname;
    @BindView(R.id.release_forfaitingsell_next_rl_issuingbankfullname)
    LinearLayout releaseForfaitingsellNextRlIssuingbankfullname;
    @BindView(R.id.release_forfaitingsell_next_tv_acceptbankswift)
    TextView releaseForfaitingsellNextTvAcceptbankswift;
    @BindView(R.id.release_forfaitingsell_next_et_acceptbankswift)
    EditText releaseForfaitingsellNextEtAcceptbankswift;
    @BindView(R.id.release_forfaitingsell_next_rl_acceptbankswift)
    LinearLayout releaseForfaitingsellNextRlAcceptbankswift;
    @BindView(R.id.release_forfaitingsell_next_tv_acceptbankfullname)
    TextView releaseForfaitingsellNextTvAcceptbankfullname;
    @BindView(R.id.release_forfaitingsell_next_et_acceptbankfullname)
    EditText releaseForfaitingsellNextEtAcceptbankfullname;
    @BindView(R.id.release_forfaitingsell_next_rl_acceptbankfullname)
    LinearLayout releaseForfaitingsellNextRlAcceptbankfullname;
    @BindView(R.id.release_forfaitingsell_next_tv_paybankswift)
    TextView releaseForfaitingsellNextTvPaybankswift;
    @BindView(R.id.release_forfaitingsell_next_et_paybankswift)
//    @NotEmpty(messageResId = R.string.error_message1)
//    @Order(2)
            EditText releaseForfaitingsellNextEtPaybankswift;
    @BindView(R.id.release_forfaitingsell_next_rl_paybankswift)
    LinearLayout releaseForfaitingsellNextRlPaybankswift;
    @BindView(R.id.release_forfaitingsell_next_tv_paybankfullname)
    TextView releaseForfaitingsellNextTvPaybankfullname;
    @BindView(R.id.release_forfaitingsell_next_et_paybankfullname)
    EditText releaseForfaitingsellNextEtPaybankfullname;
    @BindView(R.id.release_forfaitingsell_next_rl_paybankfullname)
    LinearLayout releaseForfaitingsellNextRlPaybankfullname;
    @BindView(R.id.release_forfaitingsell_next_tv_amount)
    TextView releaseForfaitingsellNextTvAmount;
    @BindView(R.id.release_forfaitingsell_next_et_amount)
    EditText releaseForfaitingsellNextEtAmount;
    @BindView(R.id.release_forfaitingsell_next_rl_amount)
    LinearLayout releaseForfaitingsellNextRlAmount;
    @BindView(R.id.release_forfaitingsell_next_tv_currency)
    TextView releaseForfaitingsellNextTvCurrency;
    @BindView(R.id.release_forfaitingsell_next_et_currency)
    TextView releaseForfaitingsellNextEtCurrency;
    @BindView(R.id.release_forfaitingsell_next_rl_currency)
    RelativeLayout releaseForfaitingsellNextRlCurrency;
    @BindView(R.id.release_forfaitingsell_next_tv_creditnumber)
    TextView releaseForfaitingsellNextTvCreditnumber;
    @BindView(R.id.release_forfaitingsell_next_et_creditnumber)
    EditText releaseForfaitingsellNextEtCreditnumber;
    @BindView(R.id.release_forfaitingsell_next_rl_creditnumber)
    LinearLayout releaseForfaitingsellNextRlCreditnumber;
    @BindView(R.id.release_forfaitingsell_next_tv_creditissuringdate)
    TextView releaseForfaitingsellNextTvCreditissuringdate;
    @BindView(R.id.release_forfaitingsell_next_et_creditissuringdate)
    TextView releaseForfaitingsellNextEtCreditissuringdate;
    @BindView(R.id.release_forfaitingsell_next_rl_creditissuringdate)
    RelativeLayout releaseForfaitingsellNextRlCreditissuringdate;
    @BindView(R.id.release_forfaitingsell_next_tv_maturitydate)
    TextView releaseForfaitingsellNextTvMaturitydate;
    @BindView(R.id.release_forfaitingsell_next_et_maturitydate)
    TextView releaseForfaitingsellNextEtMaturitydate;
    @BindView(R.id.release_forfaitingsell_next_rl_maturitydate)
    RelativeLayout releaseForfaitingsellNextRlMaturitydate;
    @BindView(R.id.release_forfaitingsell_next_tv_acceptdate)
    TextView releaseForfaitingsellNextTvAcceptdate;
    @BindView(R.id.release_forfaitingsell_next_et_acceptdate)
    TextView releaseForfaitingsellNextEtAcceptdate;
    @BindView(R.id.release_forfaitingsell_next_rl_acceptdate)
    RelativeLayout releaseForfaitingsellNextRlAcceptdate;
    @BindView(R.id.release_forfaitingsell_next_button_next)
    Button releaseForfaitingsellNextButtonNext;
    @BindView(R.id.release_forfaitingsell_next_ll)
    LinearLayout releaseForfaitingsellNextLl;
    @BindView(R.id.release_factoringsubmit_tv_credittype)
    TextView releaseFactoringsubmitTvCredittype;
    @BindView(R.id.release_factoringsubmit_et_credittype)
    TextView releaseFactoringsubmitEtCredittype;
    @BindView(R.id.release_factoringsubmit_rl_credittype)
    RelativeLayout releaseFactoringsubmitRlCredittype;
    @BindView(R.id.release_factoringsubmit_tv_discountrate)
    TextView releaseFactoringsubmitTvDiscountrate;
    @BindView(R.id.release_factoringsubmit_et_discountrate)
    EditText releaseFactoringsubmitEtDiscountrate;
    @BindView(R.id.release_factoringsubmit_rl_discountrate)
    LinearLayout releaseFactoringsubmitRlDiscountrate;
    @BindView(R.id.release_factoringsubmit_tv_validityinformation)
    TextView releaseFactoringsubmitTvValidityinformation;
    @BindView(R.id.release_factoringsubmit_et_validityinformation)
    TextView releaseFactoringsubmitEtValidityinformation;
    @BindView(R.id.release_factoringsubmit_rl_validityinformation)
    RelativeLayout releaseFactoringsubmitRlValidityinformation;
    @BindView(R.id.release_forfaitingsubmit_next_tv_agreement)
    TextView releaseForfaitingsubmitNextTvAgreement;
    @BindView(R.id.release_forfaitingsubmit_next_tv_agreement_upload)
    TextView releaseForfaitingsubmitNextTvAgreementUpload;
    @BindView(R.id.release_forfaitingsubmit_next_rl_agreement)
    RelativeLayout releaseForfaitingsubmitNextRlAgreement;
    @BindView(R.id.release_factoringsubmit_tv_area)
    TextView releaseFactoringsubmitTvArea;
    @BindView(R.id.release_factoringsubmit_et_area)
    TextView releaseFactoringsubmitEtArea;
    @BindView(R.id.release_factoringsubmit_rl_area)
    RelativeLayout releaseFactoringsubmitRlArea;
    @BindView(R.id.release_factoringsubmit_tv_state)
    TextView releaseFactoringsubmitTvState;
    @BindView(R.id.release_factoringsubmit_et_state)
    TextView releaseFactoringsubmitEtState;
    @BindView(R.id.release_factoringsubmit_rl_state)
    RelativeLayout releaseFactoringsubmitRlState;
    @BindView(R.id.release_factoringsubmit_button_submit)
    Button releaseFactoringsubmitButtonSubmit;
    @BindView(R.id.release_factoringsubmit_ll)
    LinearLayout releaseFactoringsubmitLl;
    Unbinder unbinder;
    @BindView(R.id.release_factoringsubmit_ll_title1)
    LinearLayout releaseFactoringsubmitLlTitle1;
    @BindView(R.id.release_factoringsubmit_ll_title2)
    LinearLayout releaseFactoringsubmitLlTitle2;
    @BindView(R.id.release_forfaitingsell_next_tv_letteracceptphoto)
    TextView releaseForfaitingsellNextTvLetteracceptphoto;
    @BindView(R.id.release_forfaitingsell_next_tv_letteracceptphoto0)
    TextView releaseForfaitingsellNextTvLetteracceptphoto0;
    @BindView(R.id.release_forfaitingsell_next_tv_letteracceptphoto_upload)
    TextView releaseForfaitingsellNextTvLetteracceptphotoUpload;
    @BindView(R.id.release_forfaitingsell_next_rl_letteracceptphoto)
    RelativeLayout releaseForfaitingsellNextRlLetteracceptphoto;
    @BindView(R.id.release_forfaitingsell_next_tv_letterphoto0)
    TextView releaseForfaitingsellNextTvLetterphoto0;
    @BindView(R.id.release_forfaitingsell_next_tv_type)
    TextView releaseForfaitingsellNextTvType;
    @BindView(R.id.release_forfaitingsell_next_et_type)
    TextView releaseForfaitingsellNextEtType;
    @BindView(R.id.release_forfaitingsell_next_rl_type)
    RelativeLayout releaseForfaitingsellNextRlType;
    @BindView(R.id.release_forfaitingsubmit_next_tv_agreement0)
    TextView releaseForfaitingsubmitNextTvAgreement0;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv1)
    ImageView releaseForfaitingsellNextLlRlIv1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl1_1)
    RelativeLayout releaseForfaitingsellNextLlRl11;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete1)
    ImageView releaseForfaitingsellNextLlRlIvdelete1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_1)
    RelativeLayout releaseForfaitingsellNextLlRl1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv11)
    ImageView releaseForfaitingsellNextLlRlIv11;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv2)
    ImageView releaseForfaitingsellNextLlRlIv2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl2_2)
    RelativeLayout releaseForfaitingsellNextLlRl22;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete2)
    ImageView releaseForfaitingsellNextLlRlIvdelete2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_2)
    RelativeLayout releaseForfaitingsellNextLlRl2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv21)
    ImageView releaseForfaitingsellNextLlRlIv21;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv3)
    ImageView releaseForfaitingsellNextLlRlIv3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl3_3)
    RelativeLayout releaseForfaitingsellNextLlRl33;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete3)
    ImageView releaseForfaitingsellNextLlRlIvdelete3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_3)
    RelativeLayout releaseForfaitingsellNextLlRl3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv31)
    ImageView releaseForfaitingsellNextLlRlIv31;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv4)
    ImageView releaseForfaitingsellNextLlRlIv4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl4_4)
    RelativeLayout releaseForfaitingsellNextLlRl44;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete4)
    ImageView releaseForfaitingsellNextLlRlIvdelete4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_4)
    RelativeLayout releaseForfaitingsellNextLlRl4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv41)
    ImageView releaseForfaitingsellNextLlRlIv41;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv5)
    ImageView releaseForfaitingsellNextLlRlIv5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl5_5)
    RelativeLayout releaseForfaitingsellNextLlRl55;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete5)
    ImageView releaseForfaitingsellNextLlRlIvdelete5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_5)
    RelativeLayout releaseForfaitingsellNextLlRl5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv51)
    ImageView releaseForfaitingsellNextLlRlIv51;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv6)
    ImageView releaseForfaitingsellNextLlRlIv6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl6_6)
    RelativeLayout releaseForfaitingsellNextLlRl66;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete6)
    ImageView releaseForfaitingsellNextLlRlIvdelete6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_6)
    RelativeLayout releaseForfaitingsellNextLlRl6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv61)
    ImageView releaseForfaitingsellNextLlRlIv61;
    @BindView(R.id.release_forfaitingsell_next_ll_photo)
    LinearLayout releaseForfaitingsellNextLlPhoto;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv1)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl1_letterphoto1)
    RelativeLayout releaseForfaitingsellNextLlRl1Letterphoto1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete1)
    ImageView releaseForfaitingsellNextLlRlLetterphotoivdelete1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphoto1)
    RelativeLayout releaseForfaitingsellNextLlRlLetterphoto1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv11)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv11;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv2)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl2_letterphoto2)
    RelativeLayout releaseForfaitingsellNextLlRl2Letterphoto2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete2)
    ImageView releaseForfaitingsellNextLlRlLetterphotoivdelete2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphoto2)
    RelativeLayout releaseForfaitingsellNextLlRlLetterphoto2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv21)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv21;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv3)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl3_letterphoto3)
    RelativeLayout releaseForfaitingsellNextLlRl3Letterphoto3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete3)
    ImageView releaseForfaitingsellNextLlRlLetterphotoivdelete3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphoto3)
    RelativeLayout releaseForfaitingsellNextLlRlLetterphoto3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv31)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv31;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv4)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl4_letterphoto4)
    RelativeLayout releaseForfaitingsellNextLlRl4Letterphoto4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete4)
    ImageView releaseForfaitingsellNextLlRlLetterphotoivdelete4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphoto4)
    RelativeLayout releaseForfaitingsellNextLlRlLetterphoto4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv41)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv41;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv5)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl5_letterphoto5)
    RelativeLayout releaseForfaitingsellNextLlRl5Letterphoto5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete5)
    ImageView releaseForfaitingsellNextLlRlLetterphotoivdelete5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphoto5)
    RelativeLayout releaseForfaitingsellNextLlRlLetterphoto5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv51)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv51;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv6)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl6_letterphoto6)
    RelativeLayout releaseForfaitingsellNextLlRl6Letterphoto6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete6)
    ImageView releaseForfaitingsellNextLlRlLetterphotoivdelete6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphoto6)
    RelativeLayout releaseForfaitingsellNextLlRlLetterphoto6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_letterphotoiv61)
    ImageView releaseForfaitingsellNextLlRlLetterphotoiv61;
    @BindView(R.id.release_forfaitingsell_next_ll_letterphoto)
    LinearLayout releaseForfaitingsellNextLlLetterphoto;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv1)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl1_agreementphoto1)
    RelativeLayout releaseForfaitingsellNextLlRl1Agreementphoto1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete1)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoivdelete1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphoto1)
    RelativeLayout releaseForfaitingsellNextLlRlAgreementphoto1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv11)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv11;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv2)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl2_agreementphoto2)
    RelativeLayout releaseForfaitingsellNextLlRl2Agreementphoto2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete2)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoivdelete2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphoto2)
    RelativeLayout releaseForfaitingsellNextLlRlAgreementphoto2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv21)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv21;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv3)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl3_agreementphoto3)
    RelativeLayout releaseForfaitingsellNextLlRl3Agreementphoto3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete3)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoivdelete3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphoto3)
    RelativeLayout releaseForfaitingsellNextLlRlAgreementphoto3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv31)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv31;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv4)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl4_agreementphoto4)
    RelativeLayout releaseForfaitingsellNextLlRl4Agreementphoto4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete4)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoivdelete4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphoto4)
    RelativeLayout releaseForfaitingsellNextLlRlAgreementphoto4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv41)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv41;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv5)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl5_agreementphoto5)
    RelativeLayout releaseForfaitingsellNextLlRl5Agreementphoto5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete5)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoivdelete5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphoto5)
    RelativeLayout releaseForfaitingsellNextLlRlAgreementphoto5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv51)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv51;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv6)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl6_agreementphoto6)
    RelativeLayout releaseForfaitingsellNextLlRl6Agreementphoto6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete6)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoivdelete6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphoto6)
    RelativeLayout releaseForfaitingsellNextLlRlAgreementphoto6;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv61)
    ImageView releaseForfaitingsellNextLlRlAgreementphotoiv61;
    @BindView(R.id.release_forfaitingsell_next_ll_agreementphoto)
    LinearLayout releaseForfaitingsellNextLlAgreementphoto;

    private IssueForfaitingReq issueForfaitingReq;
    //默认国内 信用证类型
    private int forfaitingtype = 0;
    //默认信用证类型
    private int creditetype = 1;
    //默认地区类型
    private int areatype = 1;
    //默认国家类型
    private int countriestype = 1;
    //代表那个按钮触发  用于判断
    int actionCode = 0;
    TextView ivRtv;


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
    //多文件列表
    private ArrayList<String> photoPaths = new ArrayList<>();
    private ArrayList<String> docPaths = new ArrayList<>();
    private List<File> files = new ArrayList<File>();

    private List<String> filesstr = new ArrayList<String>();
    private List<String> filesstr2 = new ArrayList<String>();
    private List<String> filesstr3 = new ArrayList<String>();
    String filename = "";
    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_release_forfaitingsell_next, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
//        validator = new Validator(this);
//        validator.setValidationListener(this);
        CodeEditInputFilter[] codefilters = {new CodeEditInputFilter()};
        EditInputMoneyFilter[] codefilters1 = {new EditInputMoneyFilter()};
        releaseForfaitingsellNextEtAmount.setFilters(codefilters1);
        releaseForfaitingsellNextEtIssuingbankswift.setFilters(codefilters);
        releaseForfaitingsellNextEtAcceptbankswift.setFilters(codefilters);
        releaseForfaitingsellNextEtPaybankswift.setFilters(codefilters);
        initListener(releaseForfaitingsellNextEtIssuingbankswift);
        initListener(releaseForfaitingsellNextEtAcceptbankswift);
        initListener(releaseForfaitingsellNextEtPaybankswift);
        EditInputFilterOneHunderd[] filters = {new EditInputFilterOneHunderd()};
        releaseFactoringsubmitEtDiscountrate.setFilters(filters);
        initDiscountRateListener(releaseFactoringsubmitEtDiscountrate);
        issueForfaitingReq = new IssueForfaitingReq();
        initDataCoutryCity();
        if (!StringUtils.isEmpty(assestId) && "1".equals(assestType)) {
            handleForfaitingDetailDate(assestId);
        }

        releaseForfaitingsellNextEtTitle.setFocusable(true);
        releaseForfaitingsellNextEtTitle.setFocusableInTouchMode(true);
        releaseForfaitingsellNextEtTitle.requestFocus();

        releaseFactoringsubmitTvArea.setText(R.string.issue_forfaiting_sell_openbank_area);
        releaseFactoringsubmitTvState.setText(R.string.issue_forfaiting_sell_openbank_countrys);
        return rootView;
    }

    public void initListener(final EditText view) {
        view.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String viewtext = view.getText().toString();
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    if (view.getText().toString().length() == 8) {
//                        view.setText("XXX");
                        view.setText(viewtext + "XXX");
                    }
                    // 此处为失去焦点时的处理内容
                }
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//获取主activity中的控件，下面这些如果写道oncreateview中是报错的
        ivRtv = (TextView) getActivity().findViewById(R.id.iv_Rtv);
        ivRtv.setText(R.string.forfaiting_sell_save);
//        String st = (String) ivRtv.getText();
//        ToastUtils.showShort(st);
        ivRtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showShort("ivRtv");
                issueForfaitingReq.setCommitType("1");
                if (checkSwiftCode()) {
                    if (valideData()) {
                        if (!StringUtils.isEmpty(assestId) && "1".equals(assestType)) {

                            //更新资产接口
                            submitUpdate(setDate());
                        } else {
                            submitDate(setDate());

                        }
//                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_save));
                    }
                } else {
                    ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeWaitDialog();
    }

    @OnClick({R.id.release_forfaitingsell_next_tv_letteracceptphoto_upload, R.id.release_forfaitingsell_next_rl_letteracceptphoto, R.id.release_forfaitingsell_next_rl_type, R.id.release_forfaitingsell_next_tv_letterphoto_upload, R.id.release_forfaitingsell_next_rl_currency, R.id.release_forfaitingsell_next_rl_creditissuringdate, R.id.release_forfaitingsell_next_rl_maturitydate, R.id.release_forfaitingsell_next_rl_acceptdate, R.id.release_forfaitingsell_next_button_next, R.id.release_forfaitingsell_next_ll, R.id.release_factoringsubmit_rl_credittype, R.id.release_factoringsubmit_rl_validityinformation, R.id.release_forfaitingsubmit_next_tv_agreement_upload, R.id.release_factoringsubmit_rl_area, R.id.release_factoringsubmit_rl_state, R.id.release_factoringsubmit_button_submit, R.id.release_factoringsubmit_ll, R.id.release_forfaitingsell_next_rl_letterphoto, R.id.release_forfaitingsubmit_next_rl_agreement, R.id.release_factoringsubmit_im1, R.id.release_factoringsubmit_im2, R.id.release_factoringsubmit_im3, R.id.release_factoringsubmit_im4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.release_forfaitingsell_next_rl_currency:
                if (forfaitingtype != 1) {
                    onConstellationPicker();
                }
                break;
            case R.id.release_forfaitingsell_next_rl_creditissuringdate:
//                        MyLogger.pLog().i(datestr);
//                        releaseForfaitingsellNextEtCreditissuringdate.setText(datestr);
                onYearMonthDayPicker(releaseForfaitingsellNextEtCreditissuringdate);
                break;
            case R.id.release_forfaitingsell_next_rl_maturitydate:
                onYearMonthDayPicker(releaseForfaitingsellNextEtMaturitydate);
                break;
            case R.id.release_forfaitingsell_next_rl_acceptdate:
                onYearMonthDayPicker(releaseForfaitingsellNextEtAcceptdate);
                break;
            case R.id.release_forfaitingsell_next_button_next:
                releaseForfaitingsellNextLl.setVisibility(View.GONE);
                releaseFactoringsubmitLlTitle1.setVisibility(View.GONE);
                releaseFactoringsubmitLl.setVisibility(View.VISIBLE);
                releaseFactoringsubmitLlTitle2.setVisibility(View.VISIBLE);
//                DisPatcher.sendVisableSaveButtonBroadcast(getActivity());
//                ivRtv.setVisibility(View.VISIBLE);
//                validator.validate();
                break;
            case R.id.release_forfaitingsell_next_ll:
                break;
            case R.id.release_factoringsubmit_rl_credittype:
                onInformationTypePicker(releaseFactoringsubmitEtCredittype);
                break;
            case R.id.release_factoringsubmit_rl_validityinformation:
                //release_factoringsubmit_et_validityinformation
                onYearMonthDayPicker(releaseFactoringsubmitEtValidityinformation);
                break;
            case R.id.release_factoringsubmit_rl_area:
                if (forfaitingtype != 1) {
                    onAreaPicker();
                }
                break;
            case R.id.release_factoringsubmit_rl_state:
                if (forfaitingtype != 1) {
                    onCountriesPicker(releaseFactoringsubmitEtState);
                }
                break;
            case R.id.release_factoringsubmit_button_submit:

                initSureOrCancelDialogView("",getString(R.string.issue_forfaiting_sell_yesorno));
                /** 提交类型：1保存，2 提交*/
            /*    issueForfaitingReq.setCommitType("2");
                boolean res = checkSwiftCode();
                if (!StringUtils.isEmpty(assestId)) {
                    if (res) {
                        if (valideData()) {
                            //更新资产接口
                            submitUpdate(setDate());
                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                    }
                } else {
                    if (res) {
                        if (valideData()) {
                            submitDate(setDate());
                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                    }
                }*/
                break;
            case R.id.release_factoringsubmit_ll:
                break;

            case R.id.release_forfaitingsell_next_tv_letteracceptphoto_upload:
            case R.id.release_forfaitingsell_next_rl_letteracceptphoto:
//                DialogUtils.showDialog(getActivity(), R.string.home_title_text, View.inflate(getActivity(), R.layout.activity_file_choice, null), true);
//                actionCode = 1;
//                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_rl_letterphoto:
            case R.id.release_forfaitingsell_next_tv_letterphoto_upload:
//                actionCode = 2;
//                bottomDialog();
                break;
            case R.id.release_forfaitingsubmit_next_rl_agreement:
            case R.id.release_forfaitingsubmit_next_tv_agreement_upload:
//                actionCode = 3;
//                bottomDialog();
                break;
            case R.id.release_factoringsubmit_im1:
            case R.id.release_factoringsubmit_im3:
                releaseForfaitingsellNextLl.setVisibility(View.VISIBLE);
                releaseFactoringsubmitLlTitle1.setVisibility(View.VISIBLE);
                releaseFactoringsubmitLl.setVisibility(View.GONE);
                releaseFactoringsubmitLlTitle2.setVisibility(View.GONE);
                break;
            case R.id.release_factoringsubmit_im2:
            case R.id.release_factoringsubmit_im4:
                releaseForfaitingsellNextLl.setVisibility(View.GONE);
                releaseFactoringsubmitLlTitle1.setVisibility(View.GONE);
                releaseFactoringsubmitLl.setVisibility(View.VISIBLE);
                releaseFactoringsubmitLlTitle2.setVisibility(View.VISIBLE);
                break;
            case R.id.release_forfaitingsell_next_rl_type:
                onForfaitingTypePicker();
                break;

        }
    }

    private void submitDate(final IssueForfaitingReq issueForfaitingReq) {
        showWaitDialog();
        MyLogger.pLog().i("发布信用证接口");
        ActionPresenter.getInstance().issueForfaitingRet(issueForfaitingReq).enqueue(new Callback<MarketFactoringSellRes>() {
            @Override
            public void onResponse(Call<MarketFactoringSellRes> call, Response<MarketFactoringSellRes> response) {
                closeWaitDialog();
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
                        if ("1".equals(issueForfaitingReq.getCommitType())) {
//                            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_save));
                        } else if ("2".equals(issueForfaitingReq.getCommitType())) {
                            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_editsuccess));
                        }
                        getActivity().finish();
                        DisPatcher.startPropertyActivity(getActivity());
                    } else {
//                        ToastUtils.showShort(response.message());
                        if (!StringUtils.isEmpty(response.body().getMessage()) && "系统错误".equals(response.body().getMessage())) {
                            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_noaccess));
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }
                } else {
                    ToastUtils.showShort(response.message());
                    MyLogger.pLog().e(response.message());
//                    MyLogger.pLog().e(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                closeWaitDialog();
                Log.d("onFailure：", "" + t.getMessage());
            }

        });
    }

    private void uploadFile(File file, final int type) {
        showWaitDialog();
        MyLogger.pLog().i("上传文件接口");
//        UploadFileReq uploadFileReq = new UploadFileReq();
//        uploadFileReq.setExtensionName(file.getName());
//        uploadFileReq.setFileSize(file.getTotalSpace()+"");
        ActionPresenter.getInstance().uploadFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        if (type == 1) {
                            //信用证承兑电
                            issueForfaitingReq.setST0212(response.body().getUrl());
                        } /*else if (type == 2) {
                            //信用证
                            issueForfaitingReq.setST0201(response.body().getUrl());
                        } else*/
                        if (type == 3) {
                            ////信用证协议
                            issueForfaitingReq.setST0202(response.body().getUrl());
                        }
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_uploadsuccess));
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());

                    }
                } else {

                    ToastUtils.showShort("" + response.message());
                    MyLogger.pLog().e(response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                closeWaitDialog();
                ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_failed));
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    //多文件上传
    private void uploadMoreFile(List<File> file, final int type) {
        showWaitDialog();
        MyLogger.pLog().i("上传多文件接口");
//        UploadFileReq uploadFileReq = new UploadFileReq();
//        uploadFileReq.setExtensionName(file.getName());
//        uploadFileReq.setFileSize(file.getTotalSpace()+"");
        ActionPresenter.getInstance().uploadMoreFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        if (type == 1) {
                            //信用证承兑电
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoView();
//                            issueForfaitingReq.setST0212(response.body().getUrl());

                        } /*else if (type == 2) {
                            //信用证
                            issueForfaitingReq.setST0201(response.body().getUrl());
                        } else*/
                        if (type == 3) {
                            ////信用证协议
//                            issueForfaitingReq.setST0202(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr3.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewAgreement();
                        }
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_uploadsuccess));
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());

                    }
                } else {

                    ToastUtils.showShort("" + response.message());
                    MyLogger.pLog().e(response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                closeWaitDialog();
                ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_failed));
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void uploadOCRFile(File file, final int type) {
//        showWaitDialog();
        MyLogger.pLog().i("上传OCR文件接口");
//        UploadFileReq uploadFileReq = new UploadFileReq();
//        uploadFileReq.setExtensionName(file.getName());
//        uploadFileReq.setFileSize(file.getTotalSpace()+"");
        ActionPresenter.getInstance().uploadOCRFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_ocr));
                     /*   if (type == 1) {
                            //信用证承兑电
                            issueForfaitingReq.setST0212(response.body().getUrl());
                        } else*/
                        if (type == 2) {
                            //信用证
//                            issueForfaitingReq.setST0201(response.body().getUrl());
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr2.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            photoViewLetterPhoto();
                        } /*else if (type == 3) {
                            ////信用证协议
                            issueForfaitingReq.setST0202(response.body().getUrl());
                        }*/
                        SetOCRData(response.body());
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());

                    }
                } else {
                    MyLogger.pLog().e(response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
                closeWaitDialog();
            }
        });
    }

    public void SetOCRData(UploadFileRes uploadFileRes) {
        /*
        " "code":200, // 200成功，400 失败，500 系统异常
  "message":"成功", // 返回成功或失败原因
  "url":“http://.....” //文件在文件服务器的路径，失败时为null，需要保存并提交到后台
"creditLetterData": {
  "form_of_documentary_credit":"IRREVOCABLE",  跟单信用证形式
                         跟单信用证有六种形式：
                (1) IRREVOCABLE（不可撤销跟单信用证）
                   (2) REVOCABLE（可撤销跟单信用证）
                (3) IRREVOCABLE TRANSFERABLE（不可撤销可转让跟单信用证）
                (4) REVOCABLE TRANSFERABLE（可撤销可转让跟单信用证）
                (5) IRREVOCABLE STANDBY（不可撤销备用信用证）
                   (6) REVOCABLE STANDBY（可撤销备用信用证）
              "date_of_issue":"20171229,     开证日期"
              "documentary_credit_number":"LC940117000435,  信用证号码"
              "date_of_expiry":"20180221 信用证有效期"
              "place_of_expiry":"CANADA有效地点"
              "currency_code":"信用证结算的货币"
            “amount” :”73126,50” ： 承兑金额
                  “drawee_bank” :  承兑行  SWIFT CODE
                  “drawee_bank_name” :  承兑行全称
                  send_bank： 开证行 SWIFT CODE
            send_bank_name：开证行全称
            recv_bank： 偿付行  SWIFT CODE
            recv_bank_name： 偿付行全称
         */
        if (uploadFileRes != null && uploadFileRes.getCreditLetterData() != null) {
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getDocumentary_credit_number()))
                releaseForfaitingsellNextEtCreditnumber.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getDocumentary_credit_number()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getDate_of_expiry()))
                releaseFactoringsubmitEtValidityinformation.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getDate_of_expiry()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getCurrency_code()))
                releaseForfaitingsellNextEtCurrency.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getCurrency_code()));

            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getDrawee_bank()))
                releaseForfaitingsellNextEtAcceptbankswift.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getDrawee_bank()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getDrawee_bank_name()))
                releaseForfaitingsellNextEtAcceptbankfullname.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getDrawee_bank_name()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getSend_bank()))
                releaseForfaitingsellNextEtIssuingbankswift.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getSend_bank()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getSend_bank_name()))
                releaseForfaitingsellNextEtIssuingbankfullname.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getSend_bank_name()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getRecv_bank()))
                releaseForfaitingsellNextEtPaybankswift.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getRecv_bank()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getRecv_bank_name()))
                releaseForfaitingsellNextEtPaybankfullname.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getRecv_bank_name()));

            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getForm_of_documentary_credit()))
                releaseFactoringsubmitEtCredittype.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getForm_of_documentary_credit()));
            if (!StringUtils.isEmpty(uploadFileRes.getCreditLetterData().getDate_of_issue()))
                releaseForfaitingsellNextEtCreditissuringdate.setText(StringUtils.nullStrToEmpty("" + uploadFileRes.getCreditLetterData().getDate_of_issue()));
            String amount = "" + StringUtils.nullStrToEmpty(uploadFileRes.getCreditLetterData().getAmount());
            if (!StringUtils.isEmpty(amount)) {
                amount = amount.replaceAll(",", "");
                releaseForfaitingsellNextEtAmount.setText(amount);
            }
            MyLogger.pLog().i(uploadFileRes.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.pLog().d("onResume");
//        if(releaseForfaitingsellNextLl.getVisibility()==View.VISIBLE){
//            DisPatcher.sendGoneSaveButtonBroadcast(getActivity());
//        }else if(releaseFactoringsubmitLl.getVisibility()==View.VISIBLE){
//            DisPatcher.sendVisableSaveButtonBroadcast(getActivity());
//        }
    }

    //设置请求数据
    private IssueForfaitingReq setDate() {
        issueForfaitingReq.setTitle(releaseForfaitingsellNextEtTitle.getText().toString());
        if (forfaitingtype == 1) {
            issueForfaitingReq.setDebtType(1);/** 债权类型（信用证类型）：1 国内信用证、2 国际信用证  必输*/
        } else if (forfaitingtype == 2) {
            issueForfaitingReq.setDebtType(2);/** 债权类型（信用证类型）：1 国内信用证、2 国际信用证  必输*/
        }
        issueForfaitingReq.setId(assestId);
        issueForfaitingReq.setOpenSwift(releaseForfaitingsellNextEtIssuingbankswift.getText().toString());/** 开证行SWIFT码 必输 */
        issueForfaitingReq.setOpenFullName(releaseForfaitingsellNextEtIssuingbankfullname.getText().toString());
        issueForfaitingReq.setTenderSwift(releaseForfaitingsellNextEtAcceptbankswift.getText().toString());
        issueForfaitingReq.setTenderFullName(releaseForfaitingsellNextEtAcceptbankfullname.getText().toString());
        issueForfaitingReq.setReimbursingBankSwift(releaseForfaitingsellNextEtPaybankswift.getText().toString());
        issueForfaitingReq.setReimbursingBankName(releaseForfaitingsellNextEtPaybankfullname.getText().toString());
        issueForfaitingReq.setCurrency(releaseForfaitingsellNextEtCurrency.getText().toString());
        issueForfaitingReq.setLcNo(releaseForfaitingsellNextEtCreditnumber.getText().toString());
        issueForfaitingReq.setIssuingDate(releaseForfaitingsellNextEtCreditissuringdate.getText().toString());/** 信用证签发日期 */
        issueForfaitingReq.setMaturity(releaseForfaitingsellNextEtMaturitydate.getText().toString());/** 资产到期日 */
        issueForfaitingReq.setAcceptanceDate(releaseForfaitingsellNextEtAcceptdate.getText().toString());/** 承兑日期 */
        issueForfaitingReq.setCreditType(releaseFactoringsubmitEtCredittype.getText().toString());
        issueForfaitingReq.setIndateMessage(releaseFactoringsubmitEtValidityinformation.getText().toString());/** 信息有效期 */
//        issueForfaitingReq.setST0202(releaseForfaitingsubmitNextTvAgreementUpload.getText().toString());
        issueForfaitingReq.setCountryId(countryid);
        issueForfaitingReq.setAreaId(id_area);
        issueForfaitingReq.setAmount(releaseForfaitingsellNextEtAmount.getText().toString());
        issueForfaitingReq.setDiscountRate(releaseFactoringsubmitEtDiscountrate.getText().toString());
        MyLogger.pLog().i(issueForfaitingReq.toString());

        return issueForfaitingReq;
    }

    //校验请求数据
    private boolean valideData() {
        boolean res = true;
        if (StringUtils.isEmpty(releaseForfaitingsellNextEtTitle.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_title));
            res = false;
        } else if (forfaitingtype == 0) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_xinyongzhengleixing));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtIssuingbankswift.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_openswiftcode));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtIssuingbankfullname.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_openswiftname));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtAcceptbankswift.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_acceptswiftcode));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtAcceptbankfullname.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_acceptfullname));
            res = false;
        } /*else if (StringUtils.isEmpty(releaseForfaitingsellNextEtAcceptdate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_accetpdate));
            res = false;
        }*/ else if (StringUtils.isEmpty(releaseForfaitingsellNextEtAmount.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_amount));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtCurrency.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_currency));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtCreditnumber.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_number));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtCreditissuringdate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_date));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtMaturitydate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_maturitydate));
            res = false;
        } else if (StringUtils.isEmpty(releaseForfaitingsellNextEtAcceptdate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_acceptdate));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsubmitEtCredittype.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_credittype));
            res = false;
        } else if (StringUtils.isEmpty(releaseFactoringsubmitEtDiscountrate.getText().toString())) {
            ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_discountrate));
            res = false;
        } else if (StringUtils.isEmpty(id_area)) {
            ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_area));
            res = false;
        } else if (StringUtils.isEmpty(countryid)) {
            ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_countrys));
            res = false;
        }
        return res;
    }

    public boolean checkSwiftCode() {
        boolean result = false;
        String str1 = releaseForfaitingsellNextEtIssuingbankswift.getText().toString();
        String str2 = releaseForfaitingsellNextEtAcceptbankswift.getText().toString();
        String str3 = releaseForfaitingsellNextEtPaybankswift.getText().toString();
        MyLogger.pLog().e("str1= " + str1.length());
        MyLogger.pLog().e("str2= " + str2.length());
        MyLogger.pLog().e("str3= " + str3.length());
        if (str1.length() == 11 || str1.length() == 8) {
            result = true;
        } else {
            result = false;
        }
        if (str2.length() == 11 || str2.length() == 8) {
            result = true;
        } else {
            result = false;
        }
        if (str3.length() == 11 || str3.length() == 8 || StringUtils.isEmpty(str3)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    //    @Override
//    public void onValidationSucceeded() {
//        //do somethings
//        //sendSubmit();
//    }
//
//    @Override
//    public void onValidationFailed(List<ValidationError> errors) {
//        for (ValidationError error : errors) {
//            View view = error.getView();
//            String message = error.getCollatedErrorMessage(getActivity());
//            if (view instanceof EditText) {
//                ((EditText) view).setError(message);
//            }
//        }
//    }
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
                releaseForfaitingsellNextEtCurrency.setText(item);
            }
        });
        picker.show();
    }

    public void onForfaitingTypePicker() {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        getResources().getString(R.string.market_forfaiting_type1), getResources().getString(R.string.market_forfaiting_type2)
                } : new String[]{
                        getResources().getString(R.string.market_forfaiting_type1), getResources().getString(R.string.market_forfaiting_type2)
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
        picker.setTitleText(isChinese ? "信用证类型" : getResources().getString(R.string.issue_forfaiting_sell_credittype));
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
                releaseForfaitingsellNextEtType.setText(item);
                forfaitingtype = index + 1;
//                releaseForfaitingsellNextEtCurrency.setText(item);
                if (index == 0) {
                    if (array2_arear != null && array2_arear.length > 0) {
                        releaseFactoringsubmitEtArea.setText(array2_arear[0]);
                        id_area = array1_arear[0];
                    }
                    releaseForfaitingsellNextEtCurrency.setText("CNY");
                }
                if (index == 1) {
                    releaseFactoringsubmitEtState.setText("");
                    releaseFactoringsubmitEtArea.setText("");
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
                    releaseFactoringsubmitEtArea.setText(item);
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


    public void onInformationTypePicker(final View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
                isChinese ? new String[]{
                        "NEGOTIATION", "ACCEPTANCE", "DEF  PAYMENT", "PAYMENT", "MIXED PAYMENT"
                } : new String[]{
                        "NEGOTIATION", "ACCEPTANCE", "DEF  PAYMENT", "PAYMENT", "MIXED PAYMENT"
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
        picker.setTitleText(isChinese ? "信用证兑付方式" : "L/C Payment Method");
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
//                forfaitingtype = index + 1;
                }
            });
            picker.show();
        } else {
            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_nodata));
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
                if (actionCode == 2) {
                    //单文件选择
//                    EasyImage.openGallery(ForfaitingSellFragment.this, 0);
//                EasyImage.openDocuments(ForfaitingSellFragment.this, 0);
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr2.size());
                    startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                } else {
                    //多文件选择
//                    String[] zipTypes = {"jpg", "jpeg", "png"};
//                    String[] zips = {".zip", ".rar"};
//                    String[] pdfs = {".pdf"};
//                    FilePickerBuilder.getInstance()
//                            .setMaxCount(6)
//                            .setSelectedFiles(docPaths)
//                            .setActivityTheme(R.style.LibAppTheme)
//                            .setActivityTitle("Please select doc")
//                            .addFileSupport("ZIP", zips)
//                            .addFileSupport("PDF", pdfs, R.mipmap.iassetx)
//                            .addFileSupport("图片", zipTypes)
//                            .enableDocSupport(false)
//                            .enableSelectAll(true)
//                            .sortDocumentsBy(SortingTypes.name)
//                            .withOrientation(Orientation.UNSPECIFIED)
//                            .pickFile(ForfaitingSellFragment.this, 555);
                    if (actionCode == 1) {
                        Intent intent4 = new Intent(getActivity(), NormalFilePickActivity.class);
                        intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr.size());
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
                }
                bottomDialog.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EasyImage.openCamera(ForfaitingSellFragment.this, 0);
                if (actionCode == 1) {
                    Intent intent1 = new Intent(getActivity(), ImagePickActivity.class);
                    intent1.putExtra(IS_NEED_CAMERA, true);
                    intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                    intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                    intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr.size());
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
       /* if (data != null && requestCode == 555) {
            docPaths = new ArrayList<>();
            files = new ArrayList<File>();
            docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
//            docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
            int i = 1;
            filename = "";
            for (String path : docPaths) {
                filename += "文件" + i + " ";
                files.add(new File(path));
                i++;
            }
            //信用证承兑电
            if (actionCode == 1) {
                releaseForfaitingsellNextTvLetteracceptphoto0.setText("" + filename);
                uploadMoreFile(files, actionCode);
            } else if (actionCode == 2) {
                //信用证
//                uploadOCRFile(imageFile, actionCode);
            } else if (actionCode == 3) {
                //信用证协议
                uploadMoreFile(files, actionCode);
                releaseForfaitingsubmitNextTvAgreement0.setText("" + filename);
//                uploadFile(imageFile, actionCode);
            }
        }*/

//单文件上传返回结果
        /*EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling 获取图片失败
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //信用证承兑电
                if (actionCode == 1) {
                    MyLogger.pLog().i("1:" + imageFile.getPath());
                    uploadFile(imageFile, actionCode);
                } else if (actionCode == 2) {
                    //信用证
                    MyLogger.pLog().i("2:" + imageFile.getPath());
                    uploadOCRFile(imageFile, actionCode);
                } else if (actionCode == 3) {
                    //信用证协议
                    MyLogger.pLog().i("3:" + imageFile.getPath());
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
//单文件上传返回结果 结束
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
//                    text1.setText(builder.toString());
                    //信用证承兑电
                    if (actionCode == 1) {
//                        releaseForfaitingsellNextTvLetteracceptphoto0.setText("" + filename);
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 2) {
                        //信用证
                        if (files.size() > 0) {
                            showWaitDialog();
                        }
                        for (File file : files) {
                            uploadOCRFile(file, actionCode);

                        }
                    } else if (actionCode == 3) {
                        //信用证协议
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
//                        releaseForfaitingsubmitNextTvAgreement0.setText("" + filename);
//                uploadFile(imageFile, actionCode);
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
//                    text1.setText(builder.toString());
                    //信用证承兑电
                    if (actionCode == 1) {
//                        releaseForfaitingsellNextTvLetteracceptphoto0.setText("" + filename);
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    } else if (actionCode == 2) {
                        //信用证
//                uploadOCRFile(imageFile, actionCode);
                    } else if (actionCode == 3) {
                        //信用证协议
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
//                        releaseForfaitingsubmitNextTvAgreement0.setText("" + filename);
//                uploadFile(imageFile, actionCode);
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
                            releaseFactoringsubmitEtState.setText(array2_country[0]);
                            countryid = array1_country[0];
                        }
                        //编辑数据反显国家
                        if (array2_country != null && array2_country.length > 0 && !StringUtils.isEmpty(countryid) && countrymap1 != null && countrymap1.size() > 0) {
//                            int countrid = Integer.valueOf(countryid);
                            releaseFactoringsubmitEtState.setText(countrymap1.get(countryid));
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
    private void handleForfaitingDetailDate(String id) {
        MarketForfaitingOfferReq marketForfaitingOfferReq = new MarketForfaitingOfferReq();
        marketForfaitingOfferReq.setId("" + id);
//        showWaitDialog();
        MyLogger.pLog().i("获取经办编辑信用证详情接口");
        ActionPresenter.getInstance().handleForfaitingDetailRet(marketForfaitingOfferReq).enqueue(new Callback<HandleFactoringDetailRes>() {
            @Override
            public void onResponse(Call<HandleFactoringDetailRes> call, Response<HandleFactoringDetailRes> response) {
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

    //设置回显数据
    private void setDetailDate(HandleFactoringDetailRes handleFactoringDetailRes) {
        if (StringUtils.isNullObject(handleFactoringDetailRes) && StringUtils.isNullObject(handleFactoringDetailRes.getData())) {
            releaseForfaitingsellNextEtTitle.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getTitle()));

            releaseForfaitingsellNextEtIssuingbankswift.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getOpenSwift()));
            releaseForfaitingsellNextEtIssuingbankfullname.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getOpenFullName()));
            releaseForfaitingsellNextEtAcceptbankswift.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getTenderSwift()));
            releaseForfaitingsellNextEtAcceptbankfullname.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getTenderFullName()));
            releaseForfaitingsellNextEtPaybankswift.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getReimbursingBankSwift()));
            releaseForfaitingsellNextEtPaybankfullname.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getReimbursingBankName()));
            releaseForfaitingsellNextEtCurrency.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getCurrency()));
            releaseForfaitingsellNextEtCreditnumber.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getLcNo()));
            releaseForfaitingsellNextEtCreditissuringdate.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getIssuingDate()));
            releaseForfaitingsellNextEtMaturitydate.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getMaturity()));
            releaseForfaitingsellNextEtAcceptdate.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getAcceptanceDate()));
            releaseFactoringsubmitEtCredittype.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getCreditType()));
            releaseFactoringsubmitEtValidityinformation.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getIndateMessage()));
//            releaseForfaitingsubmitNextTvAgreementUpload.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getST0202()));
            if (!StringUtils.isEmpty(handleFactoringDetailRes.getData().getST0202()) && handleFactoringDetailRes.getData().getST0202().length() > 5) {
                issueForfaitingReq.setST0202(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getST0202()));
                filesstr3.addAll(StringUtils.splitStr(handleFactoringDetailRes.getData().getST0202(), ";"));
                photoViewAgreement();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailRes.getData().getST0212()) && handleFactoringDetailRes.getData().getST0212().length() > 5) {
                issueForfaitingReq.setST0212(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getST0212()));
                filesstr.addAll(StringUtils.splitStr(handleFactoringDetailRes.getData().getST0212(), ";"));
                photoView();
            }
            if (!StringUtils.isEmpty(handleFactoringDetailRes.getData().getST0201()) && handleFactoringDetailRes.getData().getST0201().length() > 5) {
                issueForfaitingReq.setST0201(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getST0201()));
                filesstr2.addAll(StringUtils.splitStr(handleFactoringDetailRes.getData().getST0201(), ";"));
                photoViewLetterPhoto();
            }
            releaseForfaitingsellNextEtAmount.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getAmount()));
            releaseFactoringsubmitEtDiscountrate.setText(StringUtils.nullStrToEmpty(handleFactoringDetailRes.getData().getDiscountRate()));
            countryid = handleFactoringDetailRes.getData().getCountryId();
            id_area = handleFactoringDetailRes.getData().getAreaId();
            if (array2_arear != null && array2_arear.length > 0 && !StringUtils.isEmpty(id_area)) {
//                int idarea = Integer.valueOf(id_area);
                releaseFactoringsubmitEtArea.setText(StringUtils.nullStrToEmpty(arearmap1.get(id_area)));
                getSecondeCountryList();
            }
            if ("1".equals(handleFactoringDetailRes.getData().getDebtType())) {
                forfaitingtype = 1;
                releaseForfaitingsellNextEtType.setText(getResources().getString(R.string.market_forfaiting_type1));/** 债权类型（信用证类型）：1 国内信用证、2 国际信用证  必输*/
            } else if ("2".equals(handleFactoringDetailRes.getData().getDebtType())) {
                releaseForfaitingsellNextEtType.setText(getResources().getString(R.string.market_forfaiting_type2));/** 债权类型（信用证类型）：1 国内信用证、2 国际信用证  必输*/
                forfaitingtype = 2;
            }
        }
    }

    private void submitUpdate(IssueForfaitingReq issueForfaitingReq) {
        showWaitDialog();
        MyLogger.pLog().i("编辑更新资产接口");
        ActionPresenter.getInstance().handleForfaitingDetailUpdateRet(issueForfaitingReq).enqueue(new Callback<MarketFactoringSellRes>() {
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

    @OnClick({R.id.release_forfaitingsell_next_ll_rl_iv1, R.id.release_forfaitingsell_next_ll_rl1_1, R.id.release_forfaitingsell_next_ll_rl_ivdelete1, R.id.release_forfaitingsell_next_ll_rl_1, R.id.release_forfaitingsell_next_ll_rl_iv11, R.id.release_forfaitingsell_next_ll_rl_iv2, R.id.release_forfaitingsell_next_ll_rl2_2, R.id.release_forfaitingsell_next_ll_rl_ivdelete2, R.id.release_forfaitingsell_next_ll_rl_2, R.id.release_forfaitingsell_next_ll_rl_iv21, R.id.release_forfaitingsell_next_ll_rl_iv3, R.id.release_forfaitingsell_next_ll_rl3_3, R.id.release_forfaitingsell_next_ll_rl_ivdelete3, R.id.release_forfaitingsell_next_ll_rl_3, R.id.release_forfaitingsell_next_ll_rl_iv31, R.id.release_forfaitingsell_next_ll_rl_iv4, R.id.release_forfaitingsell_next_ll_rl4_4, R.id.release_forfaitingsell_next_ll_rl_ivdelete4, R.id.release_forfaitingsell_next_ll_rl_4, R.id.release_forfaitingsell_next_ll_rl_iv41, R.id.release_forfaitingsell_next_ll_rl_iv5, R.id.release_forfaitingsell_next_ll_rl5_5, R.id.release_forfaitingsell_next_ll_rl_ivdelete5, R.id.release_forfaitingsell_next_ll_rl_5, R.id.release_forfaitingsell_next_ll_rl_iv51, R.id.release_forfaitingsell_next_ll_rl_iv6, R.id.release_forfaitingsell_next_ll_rl6_6, R.id.release_forfaitingsell_next_ll_rl_ivdelete6, R.id.release_forfaitingsell_next_ll_rl_6, R.id.release_forfaitingsell_next_ll_rl_iv61, R.id.release_forfaitingsell_next_ll_photo})
    public void onViewClicked1(View view) {
        switch (view.getId()) {
            case R.id.release_forfaitingsell_next_ll_rl_iv1:
                if ("jpg".equals(StringUtils.isType(filesstr.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr.get(0));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl1_1:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_ivdelete1:
                //删除
            case R.id.release_forfaitingsell_next_ll_rl_1:
                //删除
                filesstr.remove(0);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv11:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv2:
                if ("jpg".equals(StringUtils.isType(filesstr.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr.get(1));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl2_2:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_ivdelete2:
                //删除
            case R.id.release_forfaitingsell_next_ll_rl_2:
                //删除
                filesstr.remove(1);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv21:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv3:
                if ("jpg".equals(StringUtils.isType(filesstr.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr.get(2));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl3_3:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_ivdelete3:
                //删除
            case R.id.release_forfaitingsell_next_ll_rl_3:
                //删除
                filesstr.remove(2);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv31:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv4:
                if ("jpg".equals(StringUtils.isType(filesstr.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr.get(3));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl4_4:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_ivdelete4:
                //删除
            case R.id.release_forfaitingsell_next_ll_rl_4:
                //删除
                filesstr.remove(3);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv41:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv5:
                if ("jpg".equals(StringUtils.isType(filesstr.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr.get(4));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl5_5:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_ivdelete5:
                //删除
            case R.id.release_forfaitingsell_next_ll_rl_5:
                //删除
                filesstr.remove(4);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv51:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv6:
                if ("jpg".equals(StringUtils.isType(filesstr.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr.get(5));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl6_6:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_ivdelete6:
                //删除
            case R.id.release_forfaitingsell_next_ll_rl_6:
                //删除
                filesstr.remove(5);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv61:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_photo:
                break;
        }
        photoView();
    }

    public void photoView() {
        issueForfaitingReq.setST0212(StringUtils.contentSplitStr(filesstr, ";"));
        releaseForfaitingsellNextLlRl1.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlIv11.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRl2.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlIv21.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRl3.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlIv31.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRl4.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlIv41.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRl5.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlIv51.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRl6.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlIv61.setVisibility(View.VISIBLE);

        if (filesstr.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr.get(0)))) {
                releaseForfaitingsellNextLlRlIv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr.get(0),R.drawable.jpg_shrink,releaseForfaitingsellNextLlRlIv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr.get(0)))) {
                releaseForfaitingsellNextLlRlIv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr.get(0)))) {
                releaseForfaitingsellNextLlRlIv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRl1.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlIv11.setVisibility(View.GONE);
        }
        if (filesstr.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr.get(1)))) {
                releaseForfaitingsellNextLlRlIv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr.get(1)))) {
                releaseForfaitingsellNextLlRlIv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr.get(1)))) {
                releaseForfaitingsellNextLlRlIv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRl2.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlIv21.setVisibility(View.GONE);
        }
        if (filesstr.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr.get(2)))) {
                releaseForfaitingsellNextLlRlIv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr.get(2)))) {
                releaseForfaitingsellNextLlRlIv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr.get(2)))) {
                releaseForfaitingsellNextLlRlIv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRl3.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlIv31.setVisibility(View.GONE);
        }
        if (filesstr.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr.get(3)))) {
                releaseForfaitingsellNextLlRlIv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr.get(3)))) {
                releaseForfaitingsellNextLlRlIv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr.get(3)))) {
                releaseForfaitingsellNextLlRlIv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRl4.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlIv41.setVisibility(View.GONE);
        }
        if (filesstr.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr.get(4)))) {
                releaseForfaitingsellNextLlRlIv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr.get(4)))) {
                releaseForfaitingsellNextLlRlIv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr.get(4)))) {
                releaseForfaitingsellNextLlRlIv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRl5.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlIv51.setVisibility(View.GONE);
        }
        if (filesstr.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr.get(5)))) {
                releaseForfaitingsellNextLlRlIv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr.get(5)))) {
                releaseForfaitingsellNextLlRlIv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr.get(5)))) {
                releaseForfaitingsellNextLlRlIv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRl6.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlIv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_forfaitingsell_next_ll_rl_letterphotoiv1, R.id.release_forfaitingsell_next_ll_rl1_letterphoto1, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete1, R.id.release_forfaitingsell_next_ll_rl_letterphoto1, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv11, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv2, R.id.release_forfaitingsell_next_ll_rl2_letterphoto2, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete2, R.id.release_forfaitingsell_next_ll_rl_letterphoto2, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv21, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv3, R.id.release_forfaitingsell_next_ll_rl3_letterphoto3, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete3, R.id.release_forfaitingsell_next_ll_rl_letterphoto3, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv31, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv4, R.id.release_forfaitingsell_next_ll_rl4_letterphoto4, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete4, R.id.release_forfaitingsell_next_ll_rl_letterphoto4, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv41, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv5, R.id.release_forfaitingsell_next_ll_rl5_letterphoto5, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete5, R.id.release_forfaitingsell_next_ll_rl_letterphoto5, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv51, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv6, R.id.release_forfaitingsell_next_ll_rl6_letterphoto6, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete6, R.id.release_forfaitingsell_next_ll_rl_letterphoto6, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv61, R.id.release_forfaitingsell_next_ll_letterphoto})
    public void onViewClickedLetterPhoto(View view) {
        switch (view.getId()) {
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv1:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(0));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl1_letterphoto1:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete1:
            case R.id.release_forfaitingsell_next_ll_rl_letterphoto1:
                //删除
                filesstr2.remove(0);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv11:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv2:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(1));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl2_letterphoto2:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete2:
            case R.id.release_forfaitingsell_next_ll_rl_letterphoto2:
                //删除
                filesstr2.remove(1);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv21:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv3:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(2));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl3_letterphoto3:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete3:
            case R.id.release_forfaitingsell_next_ll_rl_letterphoto3:
                //删除
                filesstr2.remove(2);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv31:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv4:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(3));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl4_letterphoto4:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete4:
            case R.id.release_forfaitingsell_next_ll_rl_letterphoto4:
                //删除
                filesstr2.remove(3);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv41:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv5:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(4));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl5_letterphoto5:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete5:
            case R.id.release_forfaitingsell_next_ll_rl_letterphoto5:
                //删除
                filesstr2.remove(4);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv51:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv6:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr2.get(5));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl6_letterphoto6:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete6:
            case R.id.release_forfaitingsell_next_ll_rl_letterphoto6:
                //删除
                filesstr2.remove(5);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv61:
                actionCode = 2;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_letterphoto:
                break;
        }
        photoViewLetterPhoto();
    }

    public void photoViewLetterPhoto() {

        issueForfaitingReq.setST0201(StringUtils.contentSplitStr(filesstr2, ";"));
        releaseForfaitingsellNextLlRlLetterphoto1.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlLetterphotoiv11.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlLetterphoto2.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlLetterphotoiv21.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlLetterphoto3.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlLetterphotoiv31.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlLetterphoto4.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlLetterphotoiv41.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlLetterphoto5.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlLetterphotoiv51.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlLetterphoto6.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlLetterphotoiv61.setVisibility(View.VISIBLE);

        if (filesstr2.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(0)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv1.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr2.get(0),R.drawable.jpg_shrink,releaseForfaitingsellNextLlRlLetterphotoiv1);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(0)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(0)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlLetterphoto1.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlLetterphotoiv11.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(1)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(1)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(1)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlLetterphoto2.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlLetterphotoiv21.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(2)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(2)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(2)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlLetterphoto3.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlLetterphotoiv31.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(3)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(3)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(3)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlLetterphoto4.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlLetterphotoiv41.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(4)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(4)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(4)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlLetterphoto5.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlLetterphotoiv51.setVisibility(View.GONE);
        }
        if (filesstr2.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr2.get(5)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr2.get(5)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr2.get(5)))) {
                releaseForfaitingsellNextLlRlLetterphotoiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlLetterphoto6.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlLetterphotoiv61.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv1, R.id.release_forfaitingsell_next_ll_rl1_agreementphoto1, R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete1, R.id.release_forfaitingsell_next_ll_rl_agreementphoto1, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv11, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv2, R.id.release_forfaitingsell_next_ll_rl2_agreementphoto2, R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete2, R.id.release_forfaitingsell_next_ll_rl_agreementphoto2, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv21, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv3, R.id.release_forfaitingsell_next_ll_rl3_agreementphoto3, R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete3, R.id.release_forfaitingsell_next_ll_rl_agreementphoto3, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv31, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv4, R.id.release_forfaitingsell_next_ll_rl4_agreementphoto4, R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete4, R.id.release_forfaitingsell_next_ll_rl_agreementphoto4, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv41, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv5, R.id.release_forfaitingsell_next_ll_rl5_agreementphoto5, R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete5, R.id.release_forfaitingsell_next_ll_rl_agreementphoto5, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv51, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv6, R.id.release_forfaitingsell_next_ll_rl6_agreementphoto6, R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete6, R.id.release_forfaitingsell_next_ll_rl_agreementphoto6, R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv61, R.id.release_forfaitingsell_next_ll_agreementphoto})
    public void onViewClickedAgreement(View view) {
        switch (view.getId()) {
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv1:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(0));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl1_agreementphoto1:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete1:
            case R.id.release_forfaitingsell_next_ll_rl_agreementphoto1:
                //删除
                filesstr3.remove(0);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv11:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv2:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(1));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl2_agreementphoto2:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete2:
            case R.id.release_forfaitingsell_next_ll_rl_agreementphoto2:
                //删除
                filesstr3.remove(1);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv21:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv3:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(2));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl3_agreementphoto3:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete3:
            case R.id.release_forfaitingsell_next_ll_rl_agreementphoto3:
                //删除
                filesstr3.remove(2);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv31:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv4:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(3));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl4_agreementphoto4:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete4:
            case R.id.release_forfaitingsell_next_ll_rl_agreementphoto4:
                //删除
                filesstr3.remove(3);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv41:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv5:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(4));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl5_agreementphoto5:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete5:
            case R.id.release_forfaitingsell_next_ll_rl_agreementphoto5:
                //删除
                filesstr3.remove(4);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv51:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv6:
                if ("jpg".equals(StringUtils.isType(filesstr3.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(getActivity(), filesstr3.get(5));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl6_agreementphoto6:
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoivdelete6:
            case R.id.release_forfaitingsell_next_ll_rl_agreementphoto6:
                //删除
                filesstr3.remove(5);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_agreementphotoiv61:
                actionCode = 3;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_agreementphoto:
                break;
        }
        photoViewAgreement();
    }

    public void photoViewAgreement() {
        issueForfaitingReq.setST0202(StringUtils.contentSplitStr(filesstr3, ";"));
        releaseForfaitingsellNextLlRlAgreementphoto1.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlAgreementphotoiv11.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlAgreementphoto2.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlAgreementphotoiv21.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlAgreementphoto3.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlAgreementphotoiv31.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlAgreementphoto4.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlAgreementphotoiv41.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlAgreementphoto5.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlAgreementphotoiv51.setVisibility(View.VISIBLE);
        releaseForfaitingsellNextLlRlAgreementphoto6.setVisibility(View.GONE);
        releaseForfaitingsellNextLlRlAgreementphotoiv61.setVisibility(View.VISIBLE);

        if (filesstr3.size() >= 1) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(0)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv1.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(0)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv1.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(0)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv1.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlAgreementphoto1.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlAgreementphotoiv11.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 2) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(1)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv2.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(1)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv2.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(1)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv2.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlAgreementphoto2.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlAgreementphotoiv21.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 3) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(2)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv3.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(2)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv3.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(2)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv3.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlAgreementphoto3.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlAgreementphotoiv31.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 4) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(3)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv4.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(3)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv4.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(3)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv4.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlAgreementphoto4.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlAgreementphotoiv41.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 5) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(4)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv5.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(4)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv5.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(4)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv5.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlAgreementphoto5.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlAgreementphotoiv51.setVisibility(View.GONE);
        }
        if (filesstr3.size() >= 6) {
            if ("jpg".equals(StringUtils.isType(filesstr3.get(5)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv6.setBackgroundResource(R.drawable.jpg_shrink);
            }
            if ("pdf".equals(StringUtils.isType(filesstr3.get(5)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv6.setBackgroundResource(R.drawable.pdf_shrink);
            }
            if ("zip".equals(StringUtils.isType(filesstr3.get(5)))) {
                releaseForfaitingsellNextLlRlAgreementphotoiv6.setBackgroundResource(R.drawable.zip_shrink);
            }
            releaseForfaitingsellNextLlRlAgreementphoto6.setVisibility(View.VISIBLE);
            releaseForfaitingsellNextLlRlAgreementphotoiv61.setVisibility(View.GONE);
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
                issueForfaitingReq.setCommitType("2");
                boolean res = checkSwiftCode();
                if (!StringUtils.isEmpty(assestId)) {
                    if (res) {
                        if (valideData()) {
                            //更新资产接口
                            submitUpdate(setDate());
                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                    }
                } else {
                    if (res) {
                        if (valideData()) {
                            submitDate(setDate());
                        }
                    } else {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                    }
                }
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect,String companyName) {
                MyLogger.pLog().i("negative");
            }
        }, "" + dialogContent, getString(R.string.onsalelist_forfaiting_adapter_cancel), getString(R.string.onsalelist_forfaiting_adapter_sure));
        sureOrCancelDialog.show();
    }
}
