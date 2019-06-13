package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.zjhl.pad.app.utils.CodeEditInputFilter;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ScreenUtils;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.BanckInstitutionalReq;
import com.zjhl.pad.moudle.entity.req.CheckBindPhoneNumberReq;
import com.zjhl.pad.moudle.entity.req.MineMsgReq;
import com.zjhl.pad.moudle.entity.req.NoBanckInstitutionReq;
import com.zjhl.pad.moudle.entity.req.QukuailianReq;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.CheckBindPhoneNumberRes;
import com.zjhl.pad.moudle.entity.res.MineMsgRes;
import com.zjhl.pad.moudle.entity.res.NoBanckInstitutionRes;
import com.zjhl.pad.moudle.entity.res.OrganizationmMsgRes;
import com.zjhl.pad.moudle.entity.res.QukuaiLianRes;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.moudle.entity.res.UploadFileRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.EnterpriseRejectDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;
import me.shaohui.bottomdialog.BottomDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_TAKEN_AUTO_SELECTED;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_TAKEN_DONE;

//import com.bumptech.glide.request.animation.GlideAnimation;

/*
 * File: NoBanckInstitutionalActivity.java 企业信息————非银行金融机构
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/6 16:37
 * Changes (from 2018/5/6)
 */
public class NoBanckInstitutionalActivity extends BaseActivity {

    @BindView(R.id.tv_ognition_name)
    TextView tvOgnitionName;
    @BindView(R.id.put_et_adress)
    EditText putEtAdress;
    @BindView(R.id.bt_reaset_password)
    LinearLayout btReasetPassword;
    @BindView(R.id.et_email)
    TextView etEmail;
    @BindView(R.id.put_relative_number)
    EditText putRelativeNumber;
    @BindView(R.id.et_business_license)
    TextView etBusinessLicense;
    @BindView(R.id.put_enterprice_persion)
    EditText putEnterpricePersion;
    @BindView(R.id.bt_reaset)
    RelativeLayout btReaset;
    @BindView(R.id.electronic_version_business)
    TextView electronicVersionBusiness;

    @BindView(R.id.electronic_version_business2)
    TextView electronicVersionBusiness2;

//    @BindView(R.id.tv_SWIFTCODE)
//    TextView tvSWIFTCODE;

    @BindView(R.id.tv_work_adress)
    TextView tvWorkAdress;
    @BindView(R.id.put_work_adress)
    EditText putWorkAdress;

    @BindView(R.id.bt_work_address)
    RelativeLayout btWorkAddress;

    @BindView(R.id.put_text_getMsg)
    TextView putTextGetMsg;


    @BindView(R.id.bt_get_msgcode)
    TextView btGetMsgcode;

    @BindView(R.id.bt_save)
    Button btSave;


    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_excite)
    ImageView ivExcite;

    @BindView(R.id.bank_no_enterprise_information_tv_licence)
    TextView bankNoEnterpriseInformationTvLicence;

    @BindView(R.id.tv_look_qukuailian)
    TextView tvLookQukuailian;
    @BindView(R.id.bank_no_enterprise_information_rl_licence)
    RelativeLayout bankNoEnterpriseInformationRlLicence;

    @BindView(R.id.select_bg_baoli)
    TextView selectBgBaoli;

    @BindView(R.id.put_text_type)
    TextView putTextType;

    @BindView(R.id.put_text)
    TextView putText;
    @BindView(R.id.bt_write)
    TextView btWrite;

    @BindView(R.id.put_text_bug)
    TextView putTextBug;


    @BindView(R.id.put_institutiona_email)
    TextView putInstitutiona;

    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;

    @BindView(R.id.rb_select)
    ImageView rbSelect;
    @BindView(R.id.rb_select2)
    ImageView rbSelect2;

    @BindView(R.id.rl_state)
    RelativeLayout rlState;

    @BindView(R.id.put_electronic_version_business1)
    EditText putElectronicVersionBusiness1;

    @BindView(R.id.tv_SWIFTCODE_type)
    TextView tvSWIFTCODEType;


    @BindView(R.id.select_bg_fufeiting)
    TextView selectBgFufeiting;

    @BindView(R.id.tv_ognation_number)
    TextView tvOgnationNumber;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv61)
    ImageView releaseForfaitingsellNextLlRlIv61;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv51)
    ImageView releaseForfaitingsellNextLlRlIv51;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv41)
    ImageView releaseForfaitingsellNextLlRlIv41;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv5)
    ImageView releaseForfaitingsellNextLlRlIv5;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv4)
    ImageView releaseForfaitingsellNextLlRlIv4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv1)
    ImageView releaseForfaitingsellNextLlRlIv1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv6)
    ImageView releaseForfaitingsellNextLlRlIv6;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv31)
    ImageView releaseForfaitingsellNextLlRlIv31;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv21)
    ImageView releaseForfaitingsellNextLlRlIv21;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv11)
    ImageView releaseForfaitingsellNextLlRlIv11;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_1)
    RelativeLayout releaseForfaitingsellNextLlRl1;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_2)
    RelativeLayout releaseForfaitingsellNextLlRl2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_3)
    RelativeLayout releaseForfaitingsellNextLlRl3;
    @BindView(R.id.ll_fanxuan_background)
    LinearLayout llFanxuanBackground;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_4)
    RelativeLayout releaseForfaitingsellNextLlRl4;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_5)
    RelativeLayout releaseForfaitingsellNextLlRl5;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_6)
    RelativeLayout releaseForfaitingsellNextLlRl6;

    @BindView(R.id.bt_institutiona)
    RelativeLayout btInstitutiona;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv2)
    ImageView releaseForfaitingsellNextLlRlIv2;

    @BindView(R.id.release_forfaitingsell_next_ll_rl_iv3)
    ImageView releaseForfaitingsellNextLlRlIv3;
    //手机验证码
    @BindView(R.id.put_reactive_nunber_msg)
    EditText putReactiveNunberMsg;

    @BindView(R.id.et_allow_number)
    EditText etAllowNumber;

    @BindView(R.id.bt_qukuailian_message)
    RelativeLayout btQukuailianMessage;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete1)
    ImageView releaseForfaitingsellNextLlRlIvdelete1;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete2)
    ImageView releaseForfaitingsellNextLlRlIvdelete2;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete3)
    ImageView releaseForfaitingsellNextLlRlIvdelete3;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete4)
    ImageView releaseForfaitingsellNextLlRlIvdelete4;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete5)
    ImageView releaseForfaitingsellNextLlRlIvdelete5;
    @BindView(R.id.release_forfaitingsell_next_ll_rl_ivdelete6)
    ImageView releaseForfaitingsellNextLlRlIvdelete6;
    @BindView(R.id.bt_phone)
    RelativeLayout btPhone;
    @BindView(R.id.bt_reject)
    TextView btReject;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_shopping)
    TextView tvShopping;
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
    @BindView(R.id.bt_reaset_password_pd)
    RelativeLayout btReasetPasswordPd;
    @BindView(R.id.release_forfaitingsell_next_ll_photo)
    LinearLayout releaseForfaitingsellNextLlPhoto;

    //    @BindView(R.id.bt_enterprice_upload)
//    Button btEnterpriceUpload;
    private String company_name;
    private String license_no;
    private String company_domicile;
    private int mid;
    boolean isCheckedBao = false;
    boolean isChecked = false;
    private String fufeitingType;
    private String baoliType;
    private String code_msg;
    private String licenseImageUtis;

    private NoBanckInstitutionReq.JsonOrgBean.DataUploadUrlBean dataUploadUrl;
    String price_persion;
    //办公地
    String work_address;
    //金融机构许可证编号
    String jin_number;
    //金融机构SWIFTCODE
    String swiftcode;
    //短信到计时器
    private MyCountDownTimer mCDT;
    private boolean isStartTimer = false;
    private NoBanckInstitutionReq nobanckInstitutionalReq;
    private String image_url;
    private String licenceimage;
    private String serverimage_url;
    private String licenceimage_url;
    private String reg_adresss;
    private String reg_name;
    private String business_number;
    private String swiftCode;
    private String banck_companyName;
    private String contactName;
    private TextView tvContactName;
    private String companyDomicile;
    private String contactTel;
    private String swift_code;
    //    OrganizationmMsgRes.DataBean.OrgDetailBean orgDetailBean;
    //代表那个按钮触发  用于判断
    int actionCode = 0;
    private String memail;
    private TextView bt_look_qushow;
    private int org_id;
    OrganizationmMsgRes.DataBean data;
    private TextView mstatues;
    private List<String> filesstr = new ArrayList<String>();

    //多文件列表
    private ArrayList<String> photoPaths = new ArrayList<>();
    private ArrayList<String> docPaths = new ArrayList<>();
    private List<File> files = new ArrayList<File>();
    private BanckInstitutionalReq.JsonOrgBean jsonOrgBean;
    private String sosil_number;
    private TextView tv_commit;

    private String phoneBind = "0";//0未绑定1已绑定;

    EnterpriseRejectDialog enterpriseRejectDialog;
    private String reasonStr = "";

    //地区、国家
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banck_no_enterprise_information);
        ButterKnife.bind(this);
        CodeEditInputFilter[] codefilters = {new CodeEditInputFilter()};
        putElectronicVersionBusiness1.setFilters(codefilters);
        initListener(putElectronicVersionBusiness1);
        initView();
        initData();
        getDataFromServer();
        DisableDeleteView(false);
        initDataCoutryCity();
    }

    private void getDataFromServer() {
        ActionPresenter.getInstance().enterRet().enqueue(new Callback<OrganizationmMsgRes>() {
            @Override
            public void onResponse(Call<OrganizationmMsgRes> call, Response<OrganizationmMsgRes> response) {

                if (response != null && response.body() != null) {
                    if (String.valueOf(response.body().getCode()) != null && response.body().getCode() == 300) {
                        data = response.body().getData();
                        getData();
                    } else if (String.valueOf(response.body().getCode()) != null && response.body().getCode() == 400) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else if (String.valueOf(response.body().getCode()) != null && response.body().getCode() == 500) {
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

    private void getData() {
        if (data != null) {

            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

            //地区
            id_area = data.getAreaId();
            if ("cn".equals(lanage)) {
                if (!TextUtils.isEmpty(data.getAreaName())) {
                    releaseFactoringsubmitEtArea.setText(data.getAreaName());
                }
            } else if ("en".equals(lanage)) {
                if (!TextUtils.isEmpty(data.getAreaNameEn())) {
                    releaseFactoringsubmitEtArea.setText(data.getAreaNameEn());
                }
            }
            if(!StringUtils.isEmpty(id_area)){
                getSecondeCountryList();
            }
            //国家
            countryid = data.getCountryId();
            if ("cn".equals(lanage)) {
                if (!TextUtils.isEmpty(data.getCountryName())) {
                    releaseFactoringsubmitEtState.setText(data.getCountryName());
                }
            } else if ("en".equals(lanage)) {
                if (!TextUtils.isEmpty(data.getCountryNameEn())) {
                    releaseFactoringsubmitEtState.setText(data.getCountryNameEn());
                }
            }

            if (!TextUtils.isEmpty(id_area) && !TextUtils.isEmpty(countryid)) {
                btReasetPasswordPd.setVisibility(View.VISIBLE);
                bankNoEnterpriseInformationRlLicence.setVisibility(View.VISIBLE);
                releaseForfaitingsellNextLlPhoto.setVisibility(View.VISIBLE);
                if ("55".equals(countryid)) {
                    //中国
                    etEmail.setText(getResources().getString(R.string.mine_social_code));
                    bankNoEnterpriseInformationTvLicence.setText(getResources().getString(R.string.mine_yingye_dianziban));
                    putRelativeNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                    putRelativeNumber.setLines(1);
                } else {
                    //其他国家
                    etEmail.setText(getResources().getString(R.string.mine_institutional_id_number));
                    bankNoEnterpriseInformationTvLicence.setText(getResources().getString(R.string.mine_institutional_id_elect_version));
                    putRelativeNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                    putRelativeNumber.setLines(2);
                }
            }

            //银行用户  中文
            String companyTypeId1 = data.getCompanyTypeId1();
            //英文
//            String companyTypeId1En = data.getCompanyTypeId1En();
            String companyTypeId1En = data.getCompanyTypeIdEn1();
            //具体银行
            String companyTypeId2 = data.getCompanyTypeId2();
            //英文
//            String companyTypeId2En = data.getCompanyTypeId2En();
            String companyTypeId2En = data.getCompanyTypeIdEn2();
            reasonStr = data.getAuditAdvise();
            //名称
            if ("cn".equals(lanage)) {
                putTextGetMsg.setText(companyTypeId1 + " " + companyTypeId2);
            } else if ("en".equals(lanage)) {
                putTextGetMsg.setText(companyTypeId1En + " " + companyTypeId2En);
            }
//
//            if ("cn".equals(lanage)) {
//                putTextType.setText(companyTypeId2);
//            } else if ("en".equals(lanage)) {
//                putTextType.setText(companyTypeId2En);
//            }
//            putTextType.setText(companyTypeId2);
            mid = data.getId();
            //企业名称
            banck_companyName = data.getCompanyName();
            putText.setText(banck_companyName);
            //联系人姓名
            contactName = data.getContactName();
            tvContactName.setText(contactName);
            //银行/公司名称
            banck_companyName = data.getCompanyName();
            //企业注册地哦
            companyDomicile = data.getCompanyDomicile();
            //注册地
            putTextBug.setText(companyDomicile);
//            nobanjsonOrg.setPermitName(reg_name);
//            nobanjsonOrg.setPermitNo(jin_number);
            putEtAdress.setText(StringUtils.nullStrToEmpty(data.getPermitName()));//接口返回
            etAllowNumber.setText(StringUtils.nullStrToEmpty(data.getPermitNo()));//接口返回
            putRelativeNumber.setText(StringUtils.nullStrToEmpty(data.getUscc()));
            putEnterpricePersion.setText(StringUtils.nullStrToEmpty(data.getLegalPerson()));//接口返回
            putElectronicVersionBusiness1.setText(StringUtils.nullStrToEmpty(data.getSwiftCode()));
            putWorkAdress.setText(StringUtils.nullStrToEmpty(data.getOfficeAddr()));
            phoneBind = data.getPhoneBind();
            //是否绑定手机号 0-未绑定 1-已绑定
            if ("1".equals(phoneBind)) {
                btPhone.setVisibility(View.GONE);
            }
            String email = data.getEmail();
            putInstitutiona.setText(email);
            //电话
            contactTel = data.getContactTel();
            tvPhoneNumber.setText(contactTel);
            //营业执照电子版地址
            final String LicenseUrl = data.getLicenseUrl();
            if (!StringUtils.isEmpty(LicenseUrl)) {
                filesstr.addAll(StringUtils.splitStr(LicenseUrl, ";"));
            }
            photoView();
            rbSelect.setBackgroundResource(R.drawable.fuxuan_no_select);
            rbSelect2.setBackgroundResource(R.drawable.fuxuan_no_select);
            GoneDeleteView(View.GONE);
            //审核状态
            int checkState = data.getCheckState();
            if (String.valueOf(checkState).equals("101") || String.valueOf(checkState).equals("102") ||
                    String.valueOf(checkState).equals("104") || String.valueOf(checkState).equals("105") ||
                    String.valueOf(checkState).equals("107") || String.valueOf(checkState).equals("108") || String.valueOf(checkState).equals("110")) {
                //完善中
                btWrite.setVisibility(View.GONE);
                tv_commit.setVisibility(View.GONE);
                mstatues.setText(R.string.mine_enterpriseinformation_checking);
                etEmail.setTextColor((getResources().getColor(R.color.hui)));
                etBusinessLicense.setTextColor((getResources().getColor(R.color.hui)));
//                ivPictureFirst.setBackgroundResource(R.drawable.no_edit);
//                electronicVersionBusiness.setTextColor(getResources().getColor(R.color.hui));
                tvSWIFTCODEType.setTextColor(getResources().getColor(R.color.hui));
                selectBgBaoli.setTextColor(getResources().getColor(R.color.hui));
                selectBgFufeiting.setTextColor(getResources().getColor(R.color.hui));

                tvOgnitionName.setTextColor((getResources().getColor(R.color.hui)));
//                electronicVersionBusiness.setTextColor(getResources().getColor(R.color.dark));
                putElectronicVersionBusiness1.setTextColor(getResources().getColor(R.color.hui));
                putWorkAdress.setTextColor(getResources().getColor(R.color.hui));

                tvOgnationNumber.setTextColor((getResources().getColor(R.color.hui)));
                bankNoEnterpriseInformationTvLicence.setTextColor((getResources().getColor(R.color.hui)));
                tvWorkAdress.setTextColor((getResources().getColor(R.color.hui)));
                electronicVersionBusiness2.setTextColor((getResources().getColor(R.color.hui)));
                tvMsg.setTextColor(getResources().getColor(R.color.hui));
                tvShopping.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitTvArea.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitEtArea.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitTvState.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitEtState.setTextColor(getResources().getColor(R.color.hui));
            } else if (String.valueOf(checkState).equals("103") || String.valueOf(checkState).equals("106")
                    || String.valueOf(checkState).equals("109") || String.valueOf(checkState).equals("112")) {
                //审核不通过
                etEmail.setTextColor((getResources().getColor(R.color.hui)));
                etBusinessLicense.setTextColor((getResources().getColor(R.color.hui)));
//                ivPictureFirst.setBackgroundResource(R.drawable.no_edit);
                electronicVersionBusiness.setTextColor(getResources().getColor(R.color.hui));
                tvOgnationNumber.setTextColor((getResources().getColor(R.color.hui)));
                tvSWIFTCODEType.setTextColor(getResources().getColor(R.color.hui));
                mstatues.setText(getString(R.string.mine_enerpriseinformation_no_crose));
                mstatues.setTextColor(getResources().getColor(R.color.red));

                selectBgBaoli.setTextColor(getResources().getColor(R.color.hui));
                selectBgFufeiting.setTextColor(getResources().getColor(R.color.hui));
                tvOgnitionName.setTextColor((getResources().getColor(R.color.hui)));
                tvOgnationNumber.setTextColor((getResources().getColor(R.color.hui)));
                bankNoEnterpriseInformationTvLicence.setTextColor((getResources().getColor(R.color.hui)));
                tvWorkAdress.setTextColor((getResources().getColor(R.color.hui)));
                electronicVersionBusiness2.setTextColor((getResources().getColor(R.color.hui)));
                tvMsg.setTextColor(getResources().getColor(R.color.hui));
                tvShopping.setTextColor(getResources().getColor(R.color.hui));
                btReject.setVisibility(View.VISIBLE);
                releaseFactoringsubmitTvArea.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitEtArea.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitTvState.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitEtState.setTextColor(getResources().getColor(R.color.hui));

            } else if (String.valueOf(checkState).equals("100")) {
                //待完善
                etEmail.setTextColor((getResources().getColor(R.color.hui)));
                etBusinessLicense.setTextColor((getResources().getColor(R.color.hui)));
//                ivPictureFirst.setBackgroundResource(R.drawable.no_edit);
                electronicVersionBusiness.setTextColor(getResources().getColor(R.color.hui));
                tvSWIFTCODEType.setTextColor(getResources().getColor(R.color.hui));
                mstatues.setText(getString(R.string.mine_will_conpleate));
                mstatues.setTextColor(getResources().getColor(R.color.red));

                selectBgBaoli.setTextColor(getResources().getColor(R.color.hui));
                selectBgFufeiting.setTextColor(getResources().getColor(R.color.hui));
                tvOgnitionName.setTextColor((getResources().getColor(R.color.hui)));
                tvOgnationNumber.setTextColor((getResources().getColor(R.color.hui)));
                bankNoEnterpriseInformationTvLicence.setTextColor((getResources().getColor(R.color.hui)));
                tvWorkAdress.setTextColor((getResources().getColor(R.color.hui)));
                electronicVersionBusiness2.setTextColor((getResources().getColor(R.color.hui)));
                tvMsg.setTextColor(getResources().getColor(R.color.hui));
                tvShopping.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitTvArea.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitEtArea.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitTvState.setTextColor(getResources().getColor(R.color.hui));
                releaseFactoringsubmitEtState.setTextColor(getResources().getColor(R.color.hui));
            } else if (String.valueOf(checkState).equals(String.valueOf("111"))) {
                //成功
                rlState.setVisibility(View.GONE);
                btWrite.setVisibility(View.GONE);
                tv_commit.setVisibility(View.GONE);
                //通过审核  不显示复选框
                rbSelect.setVisibility(View.GONE);
                rbSelect2.setVisibility(View.GONE);
                btQukuailianMessage.setVisibility(View.VISIBLE);
                llFanxuanBackground.setBackgroundResource(R.drawable.center_banck_back);
                electronicVersionBusiness.setTextColor(getResources().getColor(R.color.dark));
//                ivPictureFirst.setBackgroundResource(R.drawable.center_banck_back);
                tvSWIFTCODEType.setTextColor(getResources().getColor(R.color.dark));
                electronicVersionBusiness2.setTextColor((getResources().getColor(R.color.dark)));
                selectBgBaoli.setTextColor(getResources().getColor(R.color.common_text_gray1));
                selectBgFufeiting.setTextColor(getResources().getColor(R.color.common_text_gray1));
                tvOgnitionName.setTextColor((getResources().getColor(R.color.dark)));
                tvOgnationNumber.setTextColor((getResources().getColor(R.color.dark)));
                bankNoEnterpriseInformationTvLicence.setTextColor((getResources().getColor(R.color.dark)));
                tvWorkAdress.setTextColor((getResources().getColor(R.color.dark)));
                tvMsg.setTextColor(getResources().getColor(R.color.dark));
                tvShopping.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitTvArea.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitEtArea.setTextColor(getResources().getColor(R.color.common_text_gray1));
                releaseFactoringsubmitTvState.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitEtState.setTextColor(getResources().getColor(R.color.common_text_gray1));
//                rbSelect.setBackgroundResource(R.drawable.rb_select_push);
            }
            int size = data.getProductList().size();
            if (size > 0) {
                if (size == 1) {
                    String productCode = data.getProductList().get(0).getProductCode();
                    if ("1".equals(productCode)) {
                        rbSelect.setBackgroundResource(R.drawable.rb_select_push);
                        fufeitingType = "1";
                        isChecked = true;
                    } else {
                        //保理
                        rbSelect2.setBackgroundResource(R.drawable.rb_select_push);
                        baoliType = "2";
                        isCheckedBao = true;
                    }


                } else if (size == 2) {
                    String productCode = data.getProductList().get(0).getProductCode();
                    String productCode1 = data.getProductList().get(1).getProductCode();
                    if ("1".equals(productCode)) {
                        //福费廷
                        rbSelect.setBackgroundResource(R.drawable.rb_select_push);
                        fufeitingType = "1";
                        isChecked = true;
                    } else {
                        //保理
                        rbSelect2.setBackgroundResource(R.drawable.rb_select_push);
                        baoliType = "2";
                        isCheckedBao = true;
                    }

                    if ("1".equals(productCode1)) {
                        rbSelect.setBackgroundResource(R.drawable.rb_select_push);
                        fufeitingType = "1";
                        isChecked = true;
                    } else {
                        rbSelect2.setBackgroundResource(R.drawable.rb_select_push);
                        baoliType = "2";
                        isCheckedBao = true;

                    }


                }
            }


        }

    }

    private void initView() {
        bt_look_qushow = (TextView) findViewById(R.id.bt_look_qushow);
        tvContactName = findViewById(R.id.tv_contact_name);
        mstatues = (TextView) findViewById(R.id.tv_statue);
        tv_commit = findViewById(R.id.iv_Rtv1);
        tv_commit.setVisibility(View.VISIBLE);
        tv_commit.setText(getString(R.string.sellassert_forfaiting_adapter_commit));
    }

    private void initData() {
        jsonOrgBean = new BanckInstitutionalReq.JsonOrgBean();
        nobanckInstitutionalReq = new NoBanckInstitutionReq();
        Bundle extras = getIntent().getExtras();
        tvContent.setText(getString(R.string.mine_qiye_message));
        if (extras != null) {
            company_name = extras.getString("company_name");
//            license_no = extras.getString("license_no");
//            contact_tel = extras.getString("contact_tel");
            mid = extras.getInt("mid");
            //企业邮箱
            memail = extras.getString("memail");

            org_id = extras.getInt("org_Id");

        }
    }

    @OnClick({R.id.bt_get_msgcode, R.id.bt_save, R.id.iv_excite, R.id.tv_look_qukuailian, R.id.bt_write, R.id.bt_institutiona, R.id.rb_select, R.id.rb_select2,
            R.id.bt_enterprice_bind, R.id.release_forfaitingsell_next_ll_rl_iv61,
            R.id.release_forfaitingsell_next_ll_rl_iv11, R.id.release_forfaitingsell_next_ll_rl_iv21,
            R.id.release_forfaitingsell_next_ll_rl_iv31, R.id.release_forfaitingsell_next_ll_rl_iv51, R.id.release_forfaitingsell_next_ll_rl_iv41,
            R.id.release_forfaitingsell_next_ll_rl_1, R.id.release_forfaitingsell_next_ll_rl_2, R.id.release_forfaitingsell_next_ll_rl_3, R.id.release_forfaitingsell_next_ll_rl_4,
            R.id.release_forfaitingsell_next_ll_rl_5, R.id.release_forfaitingsell_next_ll_rl_6, R.id.iv_Rtv1, R.id.bt_reject,
            R.id.release_factoringsubmit_rl_area, R.id.release_factoringsubmit_rl_state})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.bt_get_msgcode:
                //获取验证码
                getSmsCode();
                break;
            case R.id.bt_enterprice_bind:
                if ("0".equals(phoneBind)) {
                    if (!TextUtils.isEmpty(putReactiveNunberMsg.getText().toString().trim())) {
                        code_msg = putReactiveNunberMsg.getText().toString().trim();
                        //绑定手机号
                        bindPhonenumber();
                    } else {
                        ToastUtils.showShort(getString(R.string.mine_banck_getmgs));
                    }
                }
                break;

            case R.id.rb_select:
                //福费廷
                if (isChecked) {
                    rbSelect.setBackgroundResource(R.drawable.mine_no_select);
                    isChecked = false;
                } else {
                    rbSelect.setBackgroundResource(R.drawable.rb_select_push);
                    fufeitingType = "1";
                    isChecked = true;
                }
                break;

            case R.id.bt_write:
                //补充资料，可编辑状态
                etEmail.setTextColor((getResources().getColor(R.color.dark)));
                etBusinessLicense.setTextColor((getResources().getColor(R.color.dark)));
                electronicVersionBusiness.setTextColor(getResources().getColor(R.color.dark));
                tvOgnationNumber.setTextColor(getResources().getColor(R.color.dark));
                putRelativeNumber.setVisibility(View.VISIBLE);
                putElectronicVersionBusiness1.setVisibility(View.VISIBLE);
                tvSWIFTCODEType.setTextColor(getResources().getColor(R.color.dark));
                selectBgBaoli.setTextColor(getResources().getColor(R.color.dark));
                selectBgFufeiting.setTextColor(getResources().getColor(R.color.dark));
                tvOgnitionName.setTextColor((getResources().getColor(R.color.dark)));
                bankNoEnterpriseInformationTvLicence.setTextColor((getResources().getColor(R.color.dark)));
                tvWorkAdress.setTextColor((getResources().getColor(R.color.dark)));
                electronicVersionBusiness2.setTextColor((getResources().getColor(R.color.dark)));
                tvMsg.setTextColor(getResources().getColor(R.color.dark));
                tvShopping.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitTvArea.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitEtArea.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitTvState.setTextColor(getResources().getColor(R.color.dark));
                releaseFactoringsubmitEtState.setTextColor(getResources().getColor(R.color.dark));
                GoneDeleteView(View.VISIBLE);
                DisableDeleteView(true);
                break;

            case R.id.release_forfaitingsell_next_ll_rl_iv11:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv21:
                actionCode = 1;
                bottomDialog();
                break;

            case R.id.release_forfaitingsell_next_ll_rl_iv31:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv41:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv51:
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_iv61:
                //右手边第一个
                actionCode = 1;
                bottomDialog();
                break;
            case R.id.release_forfaitingsell_next_ll_rl_1:
                //删除
                filesstr.remove(0);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_2:
                //删除
                filesstr.remove(1);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_3:
                //删除
                filesstr.remove(2);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_4:
                //删除
                filesstr.remove(3);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_5:
                //删除
                filesstr.remove(4);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_6:
                //删除
                filesstr.remove(5);
                break;
            case R.id.rb_select2:
                //OA+保
                if (isCheckedBao) {
                    rbSelect2.setBackgroundResource(R.drawable.mine_no_select);
                    isCheckedBao = false;
                } else {
                    rbSelect2.setBackgroundResource(R.drawable.rb_select_push);
                    baoliType = "2";
                    isCheckedBao = true;
                }
                break;
            case R.id.tv_look_qukuailian:
                //查看区块链
                checkQukuaiLian();
                break;
            case R.id.bt_reject:
                //查看驳回原因
                showReviewRejectDialog(reasonStr);
                break;
            case R.id.iv_Rtv1:

//                if (!TextUtils.isEmpty(textRelativeName.getText().toString().trim())&&
//                        !TextUtils.isEmpty(putEtAdress.getText().toString().trim())&&
//                        !TextUtils.isEmpty(putBusinessNumber.getText().toString().trim())&&
//                        !TextUtils.isEmpty(putReactiveAdress.getText().toString().trim())&&
//                        !TextUtils.isEmpty(tvRelativePhonenumber.getText().toString().trim())&&
//                        !TextUtils.isEmpty(price_persion)&&
//                        !TextUtils.isEmpty(work_address)&&
//                        !TextUtils.isEmpty(jin_number)){

                //选择地区
                if (TextUtils.isEmpty(id_area)) {
                    ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_area));
                    break;
                }
                //选择国家
                if (TextUtils.isEmpty(countryid)) {
                    ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_countrys));
                    break;
                }
                //统一社会信用代码/机构证件号码
                sosil_number = putRelativeNumber.getText().toString().trim();
                if ("55".equals(countryid)) {
                    if (TextUtils.isEmpty(sosil_number) || sosil_number.length()!=18) {
                        ToastUtils.showShort(getString(R.string.input_socile_code));
                        break;
                    }
                } else {
                    if (TextUtils.isEmpty(sosil_number)) {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_institutional_id_number));
                        break;
                    }
                }
                //金融机构许可证名称
                reg_name = putEtAdress.getText().toString().trim();
                if (TextUtils.isEmpty(reg_name)) {
                    ToastUtils.showShort(getString(R.string.input_jinrong_cerficate_name));
                    break;
                }
                //金融机构许可证编号
                jin_number = etAllowNumber.getText().toString().trim();
                if (TextUtils.isEmpty(jin_number)) {
                    ToastUtils.showShort(getString(R.string.input_jinrong_certificate_number));
                    break;
                }
                if (TextUtils.isEmpty(putText.getText().toString())) {
                    ToastUtils.showShort(getString(R.string.register_no_witer));
                    break;
                }
                if (TextUtils.isEmpty(putTextBug.getText().toString())) {
                    ToastUtils.showShort(getString(R.string.register_no));
                    break;
                }

                //法定代表人
                String putEnterprice_persion = putEnterpricePersion.getText().toString().trim();
                if (TextUtils.isEmpty(putEnterprice_persion)) {
                    ToastUtils.showShort(getString(R.string.input_fading_persion));
                    break;
                }
                //法定代表人
                price_persion = putEnterpricePersion.getText().toString().trim();
                //办公地
                work_address = putWorkAdress.getText().toString().trim();
                //金融机构许可证编号
//                jin_number = putRelativeNumber.getText().toString().trim();

                if (TextUtils.isEmpty(price_persion)) {
                    ToastUtils.showShort(getString(R.string.input_fading_persion));
                    break;
                }

                putElectronicVersionBusiness1.setFocusable(false);
                putElectronicVersionBusiness1.setFocusableInTouchMode(false);
                putElectronicVersionBusiness1.setFocusable(true);
                putElectronicVersionBusiness1.setFocusableInTouchMode(true);
                //SWIFT CODE
                swift_code = putElectronicVersionBusiness1.getText().toString().trim();
                //福费廷
                if (isChecked) {
                    if (TextUtils.isEmpty(swift_code)) {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                        break;
                    }
                }
                if (TextUtils.isEmpty(work_address)) {
                    ToastUtils.showShort(getString(R.string.toast_nobank_address_fail));
                    break;
                }

                if ((isCheckedBao == true) || (isChecked == true)) {

                } else {
                    ToastUtils.showShort(getString(R.string.mine_select_cichan_type));
                    break;
                }
                //福费廷
                if (isChecked) {
                    if (!checkSwiftCode()) {
                        ToastUtils.showShort(getString(R.string.toast_forfaiting_sell_swiftcode));
                        break;
                    }
                }
                saveNoBanckMsg();
                break;
            case R.id.release_factoringsubmit_rl_area:
                //地区选择
                onAreaPicker();
                break;
            case R.id.release_factoringsubmit_rl_state:
                //国家选择
                onCountriesPicker(releaseFactoringsubmitEtState);
                break;

        }
        photoView();
    }

    private void checkQukuaiLian() {
        QukuailianReq qukuailianReq = new QukuailianReq();
//        qukuailianReq.setId(org_id);
        qukuailianReq.setBussId(org_id);

        ActionPresenter.getInstance().qukuailianRet(qukuailianReq).enqueue(new Callback<QukuaiLianRes>() {
            @Override
            public void onResponse(Call<QukuaiLianRes> call, Response<QukuaiLianRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            String certificate = response.body().getData().getCertificate();
                            if (!TextUtils.isEmpty(certificate)) {
                                tvLookQukuailian.setVisibility(View.GONE);
                                bt_look_qushow.setVisibility(View.VISIBLE);

                                bt_look_qushow.setText(certificate);
                            } else {
                                tvLookQukuailian.setVisibility(View.VISIBLE);
                                bt_look_qushow.setVisibility(View.GONE);
                            }
                        }
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

    private void bindPhonenumber() {
        CheckBindPhoneNumberReq checkbindphoneReq = new CheckBindPhoneNumberReq();
        checkbindphoneReq.setMobile(contactTel);
        checkbindphoneReq.setCode(code_msg);

        ActionPresenter.getInstance().checkbindphoneRet(checkbindphoneReq).enqueue(new Callback<CheckBindPhoneNumberRes>() {
            @Override
            public void onResponse(Call<CheckBindPhoneNumberRes> call, Response<CheckBindPhoneNumberRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        ToastUtils.showShort(response.body().getMessage());
                        ToastUtils.showShort(getString(R.string.toast_bankenterprise_success));

                        btPhone.setVisibility(View.GONE);
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

    private void getSmsCode() {
        MineMsgReq mineMsg = new MineMsgReq();
        mineMsg.setMobile(contactTel);
        ActionPresenter.getInstance().mineMsgCodeRet(mineMsg).enqueue(new Callback<MineMsgRes>() {
            @Override
            public void onResponse(Call<MineMsgRes> call, Response<MineMsgRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
                        //计时器
                        mCDT = new MyCountDownTimer(180 * 1000, 1000);
                        mCDT.start();
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

    /*
    弹窗选择文件、照相开始
     */
    public void bottomDialog() {
        final BottomDialog bottomDialog = BottomDialog.create(this.getSupportFragmentManager());
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
                //单文件选择
                Intent intent4 = new Intent(NoBanckInstitutionalActivity.this, NormalFilePickActivity.class);
                intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr.size());
                intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                bottomDialog.dismiss();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EasyImage.openCamera(ForfaitingSellFragment.this, 0);
                Intent intent1 = new Intent(NoBanckInstitutionalActivity.this, ImagePickActivity.class);
                intent1.putExtra(IS_NEED_CAMERA, true);
                intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr.size());
                intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);

                bottomDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogger.pLog().i(requestCode);
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
                    //信用证承兑电

                    if (files.size() > 0)
                        uploadMoreFile(files);

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
                    //信用证承兑电
                    if (actionCode == 1) {
//                        releaseForfaitingsellNextTvLetteracceptphoto0.setText("" + filename);

                        if (files.size() > 0)
                            uploadMoreFile(files);
                    } else if (actionCode == 2) {
                        //信用证
//                uploadOCRFile(imageFile, actionCode);
                    } else if (actionCode == 3) {
                        //信用证协议

                        if (files.size() > 0)
                            uploadMoreFile(files);
//                        releaseForfaitingsubmitNextTvAgreement0.setText("" + filename);
//                uploadFile(imageFile, actionCode);
                    }
                }
                break;
        }

        //多文件2结束
        actionCode = 0;

    }


    private void uploadMoreFile(List<File> file) {
        showWaitDialog();
        MyLogger.pLog().i("上传文件接口");
        ActionPresenter.getInstance().uploadMoreFileParamsRet(null, file).enqueue(new Callback<UploadFileRes>() {
            @Override
            public void onResponse(Call<UploadFileRes> call, Response<UploadFileRes> response) {
                closeWaitDialog();
                if (response != null && response.body() != null) {
//                MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 200) {
                        MyLogger.pLog().i(response.body().getMessage());
                        MyLogger.pLog().i(response.body().getUrl());
                        ToastUtils.showShort(response.body().getMessage());
                        image_url = response.body().getUrl();

                        if (!StringUtils.isEmpty(response.body().getUrl())) {
                            filesstr.addAll(StringUtils.splitStr(image_url, ";"));
                        }
                        photoView();
                        licenseImageUtis = StringUtils.contentSplitStr(filesstr, ";");
                        ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_uploadsuccess));
                    } else {
                        ToastUtils.showShort(response.body().getMessage());

                    }
                } else {
                    MyLogger.pLog().e(response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }

    private void photoView() {

        licenseImageUtis = StringUtils.contentSplitStr(filesstr, ";");
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

    private void saveNoBanckMsg() {

        //营业执照编号
//        swiftcode = bankNoEnterpriseInformationEtSwiftcode.getText().toString().trim();

        nobanckInstitutionalReq.setOrgType("financialInstitution");
        NoBanckInstitutionReq.JsonOrgBean nobanjsonOrg = nobanckInstitutionalReq.getJsonOrg();
        nobanjsonOrg.setUscc(sosil_number);
        nobanjsonOrg.setPermitName(reg_name);
        nobanjsonOrg.setPermitNo(jin_number);
        nobanjsonOrg.setLegalPerson(price_persion);
        nobanjsonOrg.setOfficeAddr(work_address);
        nobanjsonOrg.setCompanyName(putText.getText().toString());
        nobanjsonOrg.setOrgRegisterAddr(putTextBug.getText().toString());
        nobanjsonOrg.setAreaId(id_area);
        nobanjsonOrg.setCountryId(countryid);
        if (isChecked) {
            nobanjsonOrg.setSwiftCode(swift_code);
        }
//        nobanjsonOrg.setDataUploadUrl(serverimage_url);
        nobanjsonOrg.setLicenseImgUrl(licenseImageUtis);
//        nobanjsonOrg.setLicenseImgUrl(licenceimage_url);//ui没有上传的地方 只有营业执照的
//        NoBanckInstitutionReq.JsonOrgBean.LicenseImgUrlBean licenseImgUrl = nobanckInstitutionalReq.getJsonOrg().getLicenseImgUrl();
//        licenseImgUrl.setParamUrl();
//        nobanjsonOrg.setLicenseImgUrl(licenseImgUrl);

//        dataUploadUrl = nobanckInstitutionalReq.getJsonOrg().getDataUploadUrl();
//        dataUploadUrl.setParamUrl();//相关资料上传
//        nobanjsonOrg.setLicenseImgUrl();
//        nobanjsonOrg.setDataUploadUrl();
//        dataUploadUrl.setParamUrl(serverimage_url);

        nobanjsonOrg.setOrgId(mid + "");
        nobanjsonOrg.setMobile(contactTel);
        nobanckInstitutionalReq.setJsonOrg(nobanjsonOrg);
        NoBanckInstitutionReq.JsonProductBean jsonProduct = nobanckInstitutionalReq.getJsonProduct();
        NoBanckInstitutionReq.JsonProductBean.ForfeitingBean forfeiting = jsonProduct.getForfeiting();
        if (!TextUtils.isEmpty(fufeitingType)) {
            forfeiting.setAssetsType(fufeitingType);
            forfeiting.setId("");
        }
        jsonProduct.setForfeiting(forfeiting);
//        nobanckInstitutionalReq.setJsonProduct(jsonProduct);

        NoBanckInstitutionReq.JsonProductBean.FactoringBean factoring = jsonProduct.getFactoring();
        if (!TextUtils.isEmpty(baoliType)) {
            factoring.setAssetsType(baoliType);
            factoring.setId("");
        }
        jsonProduct.setFactoring(factoring);
        nobanckInstitutionalReq.setJsonProduct(jsonProduct);


        ActionPresenter.getInstance().NoBanckInstitutionRet(nobanckInstitutionalReq).enqueue(new Callback<NoBanckInstitutionRes>() {
            @Override
            public void onResponse(Call<NoBanckInstitutionRes> call, Response<NoBanckInstitutionRes> response) {
                if (response != null && response.body() != null) {
                    if (300 == response.body().getCode()) {
                        ToastUtils.showShort(getString(R.string.mine_jigou_sucesss));
                        NoBanckInstitutionalActivity.this.finish();
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

    @OnClick({R.id.release_forfaitingsell_next_ll_rl1_1, R.id.release_forfaitingsell_next_ll_rl2_2, R.id.release_forfaitingsell_next_ll_rl3_3, R.id.release_forfaitingsell_next_ll_rl4_4, R.id.release_forfaitingsell_next_ll_rl5_5, R.id.release_forfaitingsell_next_ll_rl6_6})
    public void onViewClickedView(View view) {
        switch (view.getId()) {
            case R.id.release_forfaitingsell_next_ll_rl1_1://预览
                if ("jpg".equals(StringUtils.isType(filesstr.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr.get(0));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl2_2://预览
                if ("jpg".equals(StringUtils.isType(filesstr.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr.get(1));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl3_3://预览
                if ("jpg".equals(StringUtils.isType(filesstr.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr.get(2));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl4_4://预览
                if ("jpg".equals(StringUtils.isType(filesstr.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr.get(3));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl5_5://预览
                if ("jpg".equals(StringUtils.isType(filesstr.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr.get(4));
                }
                break;
            case R.id.release_forfaitingsell_next_ll_rl6_6://预览
                if ("jpg".equals(StringUtils.isType(filesstr.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr.get(5));
                }
                break;
        }
    }


    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            btGetMsgcode.setText(l / 1000 + "s");
            btGetMsgcode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            if (mCDT != null) {
                mCDT.cancel();
                mCDT = null;
            }
            isStartTimer = false;
            btGetMsgcode.setText(getString(R.string.mine_information_get));
            btGetMsgcode.setEnabled(false);
        }
    }

    public void GoneDeleteView(int visible) {
        //通过审核后  隐藏删除的图标
        releaseForfaitingsellNextLlRlIvdelete1.setVisibility(visible);
        releaseForfaitingsellNextLlRlIvdelete2.setVisibility(visible);
        releaseForfaitingsellNextLlRlIvdelete3.setVisibility(visible);
        releaseForfaitingsellNextLlRlIvdelete4.setVisibility(visible);
        releaseForfaitingsellNextLlRlIvdelete5.setVisibility(visible);
        releaseForfaitingsellNextLlRlIvdelete6.setVisibility(visible);
    }

    public void DisableDeleteView(boolean disable) {
        //禁用启用删除按钮
        releaseForfaitingsellNextLlRl1.setEnabled(disable);
        releaseForfaitingsellNextLlRl2.setEnabled(disable);
        releaseForfaitingsellNextLlRl3.setEnabled(disable);
        releaseForfaitingsellNextLlRl4.setEnabled(disable);
        releaseForfaitingsellNextLlRl5.setEnabled(disable);
        releaseForfaitingsellNextLlRl6.setEnabled(disable);

        rbSelect.setEnabled(disable);
        rbSelect2.setEnabled(disable);
        putText.setEnabled(disable);
        putTextBug.setEnabled(disable);
        putEtAdress.setEnabled(disable);
        putWorkAdress.setEnabled(disable);
        etAllowNumber.setEnabled(disable);
        putRelativeNumber.setEnabled(disable);
        putEnterpricePersion.setEnabled(disable);
        electronicVersionBusiness.setEnabled(disable);
        putElectronicVersionBusiness1.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv11.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv51.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv61.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv51.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv41.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv31.setEnabled(disable);
        releaseForfaitingsellNextLlRlIv21.setEnabled(disable);

        releaseFactoringsubmitRlArea.setEnabled(disable);
        releaseFactoringsubmitRlState.setEnabled(disable);
        if (disable) {
            putEtAdress.setHint(getString(R.string.mine_enterprise_information_number));
            etAllowNumber.setHint(getString(R.string.mine_enterprise_information_number));
            putEnterpricePersion.setHint(getString(R.string.mine_enterprise_information_number));
            putRelativeNumber.setHint(getString(R.string.mine_enterprise_information_number));
            putElectronicVersionBusiness1.setHint(getString(R.string.mine_enterprise_information_number));
            putWorkAdress.setHint(getString(R.string.mine_enterprise_information_number));
            putText.setHint(getString(R.string.mine_enterprise_information_number));
            putTextBug.setHint(getString(R.string.mine_enterprise_information_number));

            releaseForfaitingsellNextLlRlIv61.setImageResource(R.drawable.add_photograph);
            releaseForfaitingsellNextLlRlIv51.setImageResource(R.drawable.add_photograph);
            releaseForfaitingsellNextLlRlIv41.setImageResource(R.drawable.add_photograph);
            releaseForfaitingsellNextLlRlIv31.setImageResource(R.drawable.add_photograph);
            releaseForfaitingsellNextLlRlIv21.setImageResource(R.drawable.add_photograph);
            releaseForfaitingsellNextLlRlIv11.setImageResource(R.drawable.add_photograph);

//            releaseForfaitingsellNextLlRlIv61.setBackgroundResource(0);
//            releaseForfaitingsellNextLlRlIv51.setBackgroundResource(0);
//            releaseForfaitingsellNextLlRlIv41.setBackgroundResource(0);
//            releaseForfaitingsellNextLlRlIv31.setBackgroundResource(0);
//            releaseForfaitingsellNextLlRlIv21.setBackgroundResource(0);
//            releaseForfaitingsellNextLlRlIv11.setBackgroundResource(0);

            putText.setMaxLines(1);
            putText.setSingleLine(true);
            putTextBug.setMaxLines(1);
            putTextBug.setSingleLine(true);
            putEtAdress.setMaxLines(1);
            putEtAdress.setSingleLine(true);
            putEnterpricePersion.setMaxLines(1);
            putEnterpricePersion.setSingleLine(true);
            putWorkAdress.setMaxLines(1);
            putWorkAdress.setSingleLine(true);
        } else {
            putEtAdress.setHint("");
            etAllowNumber.setHint("");
            putEnterpricePersion.setHint("");
            putRelativeNumber.setHint("");
            putElectronicVersionBusiness1.setHint("");
            putWorkAdress.setHint("");
            putText.setHint("");
            putTextBug.setHint("");
            releaseForfaitingsellNextLlRlIv61.setImageResource(R.drawable.no_edit1);
            releaseForfaitingsellNextLlRlIv51.setImageResource(R.drawable.no_edit1);
            releaseForfaitingsellNextLlRlIv41.setImageResource(R.drawable.no_edit1);
            releaseForfaitingsellNextLlRlIv31.setImageResource(R.drawable.no_edit1);
            releaseForfaitingsellNextLlRlIv21.setImageResource(R.drawable.no_edit1);
            releaseForfaitingsellNextLlRlIv11.setImageResource(R.drawable.no_edit1);

//            releaseForfaitingsellNextLlRlIv61.setBackgroundResource(R.drawable.line_bj);
//            releaseForfaitingsellNextLlRlIv51.setBackgroundResource(R.drawable.line_bj);
//            releaseForfaitingsellNextLlRlIv41.setBackgroundResource(R.drawable.line_bj);
//            releaseForfaitingsellNextLlRlIv31.setBackgroundResource(R.drawable.line_bj);
//            releaseForfaitingsellNextLlRlIv21.setBackgroundResource(R.drawable.line_bj);
//            releaseForfaitingsellNextLlRlIv11.setBackgroundResource(R.drawable.line_bj);
            putText.setMaxLines(10);
            putText.setSingleLine(false);
            putTextBug.setMaxLines(10);
            putTextBug.setSingleLine(false);
            putEtAdress.setMaxLines(10);
            putEtAdress.setSingleLine(false);
            putEnterpricePersion.setMaxLines(10);
            putEnterpricePersion.setSingleLine(false);
            putWorkAdress.setMaxLines(10);
            putWorkAdress.setSingleLine(false);
        }
    }

    public boolean checkSwiftCode() {
        boolean result = false;
        String str1 = putElectronicVersionBusiness1.getText().toString();
        MyLogger.pLog().e("str1= " + str1.length());
        if (str1.length() == 11 || str1.length() == 8) {
            result = true;
        } else {
            result = false;
        }
        return result;
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

    //驳回原因
    private void showReviewRejectDialog(String reason) {
        enterpriseRejectDialog = new EnterpriseRejectDialog(this, null, "" + reason);
        enterpriseRejectDialog.show();
    }

    /**
     * 初始化地区列表数据
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

    /**
     * 请求国家数据
     */
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
                            releaseFactoringsubmitEtState.setText(countrymap1.get(countryid));
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

    /**
     * 地区选择器
     */
    public void onAreaPicker() {
        if (array2_arear != null && array2_arear.length > 0) {
            boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
//        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
//                isChinese ? new String[]{
//                        "亚洲", "欧洲", "美洲", "非洲", "大洋洲", "南极洲"
//                } : new String[]{
//                        "Asia", "Europe", "America", "Africa", "Oceania", "Antarctica"
//                });
            SinglePicker<String> picker = new SinglePicker<>(NoBanckInstitutionalActivity.this,
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
            final int screenWidth = ScreenUtils.getScreenWidth(NoBanckInstitutionalActivity.this);
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

    /**
     * 国家选择器
     *
     * @param view
     */
    public void onCountriesPicker(final View view) {
        if (array2_country != null && array2_country.length > 0) {
            boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
//        SinglePicker<String> picker = new SinglePicker<>(getActivity(),
//                isChinese ? new String[]{
//                        "中国", "日本"
//                } : new String[]{
//                        "China", "Japan"
//                });
            SinglePicker<String> picker = new SinglePicker<>(NoBanckInstitutionalActivity.this,
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
            final int screenWidth = ScreenUtils.getScreenWidth(NoBanckInstitutionalActivity.this);
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

                    btReasetPasswordPd.setVisibility(View.VISIBLE);
                    bankNoEnterpriseInformationRlLicence.setVisibility(View.VISIBLE);
                    releaseForfaitingsellNextLlPhoto.setVisibility(View.VISIBLE);
                    if ("55".equals(countryid)) {
                        //中国
                        etEmail.setText(getResources().getString(R.string.mine_social_code));
                        bankNoEnterpriseInformationTvLicence.setText(getResources().getString(R.string.mine_yingye_dianziban));
                        putRelativeNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                        putRelativeNumber.setLines(1);
                    } else {
                        //其他国家
                        etEmail.setText(getResources().getString(R.string.mine_institutional_id_number));
                        bankNoEnterpriseInformationTvLicence.setText(getResources().getString(R.string.mine_institutional_id_elect_version));
                        putRelativeNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                        putRelativeNumber.setLines(2);
                    }
                }
            });
            picker.show();
        } else {
            ToastUtils.showShort(getString(R.string.toast_issue_frofaiting_nodata));
        }
    }
}
