package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.ReviewSubmitLetterReq;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.moudle.entity.res.UploadFileRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_TAKEN_AUTO_SELECTED;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_TAKEN_DONE;

/**
 * @desc: SubmitLetterActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/7/17 15:12 提交让渡函
 * @projectname: nnkj
 **/
public class SubmitLetterActivity extends BaseActivity {
    @BindView(R.id.dialog_offer_detail_iv_close)
    ImageView dialogOfferDetailIvClose;
    @BindView(R.id.dialog_offer_detail_iv_ss)
    ImageView dialogOfferDetailIvSs;
    @BindView(R.id.dialog_offer_detail_title_baojia)
    TextView dialogOfferDetailTitleBaojia;
    @BindView(R.id.dialog_offer_detail_title)
    LinearLayout dialogOfferDetailTitle;
    @BindView(R.id.dialog_offer_detail_tv_cancel)
    TextView dialogOfferDetailTvCancel;
    @BindView(R.id.dialog_offer_detail_line)
    View dialogOfferDetailLine;
    @BindView(R.id.dialog_offer_detail_tv_sure)
    TextView dialogOfferDetailTvSure;
    @BindView(R.id.dialog_offer_detail_ll_conent)
    LinearLayout dialogOfferDetailLlConent;
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
    @BindView(R.id.dialog_offer_detail_ll)
    LinearLayout dialogOfferDetailLl;
    private List<File> files = new ArrayList<File>();

    private List<String> filesstr2 = new ArrayList<String>();
    int actionCode = 0;
    ReviewSubmitLetterReq reviewSubmitLetterReq = new ReviewSubmitLetterReq();
    private String assets_id;
    private String assetsType;
    //二次确认框
    SureOrCancelDialog sureOrCancelDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WindowManager.LayoutParams windowLP = getWindow().getAttributes();
//        windowLP.alpha = 0.5f;
//        getWindow().setAttributes(windowLP);
        setContentView(R.layout.dialog_submit_letter);
        ButterKnife.bind(this);
        assets_id = getIntent().getStringExtra("assets_id");
        assetsType = getIntent().getStringExtra("assetsType");
        reviewSubmitLetterReq.setId(assets_id);

    }

    @OnClick({R.id.dialog_offer_detail_iv_close, R.id.dialog_offer_detail_iv_ss, R.id.dialog_offer_detail_title_baojia, R.id.dialog_offer_detail_title, R.id.dialog_offer_detail_tv_cancel, R.id.dialog_offer_detail_line, R.id.dialog_offer_detail_tv_sure, R.id.dialog_offer_detail_ll_conent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_offer_detail_iv_close:
                finish();

                break;
            case R.id.dialog_offer_detail_iv_ss:
                break;
            case R.id.dialog_offer_detail_title_baojia:
                break;
            case R.id.dialog_offer_detail_title:
                break;
            case R.id.dialog_offer_detail_tv_cancel:
                actionCode = 1;
                Intent intent1 = new Intent(this, ImagePickActivity.class);
                intent1.putExtra(IS_NEED_CAMERA, true);
                intent1.putExtra(IS_TAKEN_AUTO_SELECTED, false);
                intent1.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
                intent1.putExtra(Constant.MAX_NUMBER, 6 - filesstr2.size());
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                break;
            case R.id.dialog_offer_detail_line:
                break;
            case R.id.dialog_offer_detail_tv_sure:
                initSureOrCancelDialogView("1", getString(R.string.issue_forfaiting_sell_yesorno));
                //让渡函 1福费廷 2保理
                /*if (!StringUtils.isEmpty(reviewSubmitLetterReq.getST0210())) {
                    if ("1".equals(assetsType)) {
                        reviewSubmitLetter();
                    } else if ("2".equals(assetsType)) {
                        reviewFactoringSubmitLetterOnSaleListRet();
                    }
                    setResult(666);
                    finish();
                }*/
                break;
            case R.id.dialog_offer_detail_ll_conent:
                break;
        }
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
                        //让渡函 1福费廷 2保理
                        if (type == 1 && "1".equals(assetsType)) {
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr2.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            reviewSubmitLetterReq.setST0210(StringUtils.contentSplitStr(filesstr2, ";"));
                            photoViewLetterPhoto();
//                            reviewSubmitLetter();
                        } else if (type == 1 && "2".equals(assetsType)) {
                            if (!StringUtils.isEmpty(response.body().getUrl())) {
                                filesstr2.addAll(StringUtils.splitStr(response.body().getUrl(), ";"));
                            }
                            reviewSubmitLetterReq.setST0210(StringUtils.contentSplitStr(filesstr2, ";"));
                            photoViewLetterPhoto();
//                            reviewFactoringSubmitLetterOnSaleListRet();
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

    /**
     * //资产卖家福费廷确认接口 提交让渡函
     *
     * @POST(Constants.NETPATH.REVIEWSUBMITLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     */
    private void reviewSubmitLetter() {
        ActionPresenter.getInstance().reviewSubmitLetterOnSaleListRet(reviewSubmitLetterReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {
                if (response != null && response.body() != null) {
//                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {

//                            ToastUtils.showShort(getString(R.string.toast_soldinassets_commit_success));
                            ToastUtils.showShort(getString(R.string.toast_assest_letter));
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
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    /**
     * /**
     * //资产卖家保理确认接口 提交让渡函  复用ReviewOfferSubmitLetterOnSaleListRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGSUBMITLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     * public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(ReviewSubmitLetterReq data) {
     * Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet = mApi.reviewFactoringSubmitLetterOnSaleListRet(createRequestBody(data));
     * return reviewFactoringSubmitLetterOnSaleListRet;
     * }
     */
    private void reviewFactoringSubmitLetterOnSaleListRet() {
        ActionPresenter.getInstance().reviewFactoringSubmitLetterOnSaleListRet(reviewSubmitLetterReq).enqueue(new Callback<ReviewOfferSubmitLetterOnSaleListRes>() {
            @Override
            public void onResponse(Call<ReviewOfferSubmitLetterOnSaleListRes> call, Response<ReviewOfferSubmitLetterOnSaleListRes> response) {
                if (response != null && response.body() != null) {
//                    MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                    MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                    if (response != null) {
                        if (response.body().getCode() == 300) {

                            ToastUtils.showShort(getString(R.string.toast_soldinassets_commit_success));
//                            refresh();
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
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogger.pLog().i(requestCode);
//        MyLogger.pLog().i("88" + resultCode + "88");
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
                    //信用证承兑电
                    if (actionCode == 1) {
//                        releaseForfaitingsellNextTvLetteracceptphoto0.setText("" + filename);
                        if (files.size() > 0)
                            uploadMoreFile(files, actionCode);
                    }
                }
                break;
        }

        //多文件2结束
//        actionCode = 0;
    }

    @OnClick({R.id.release_forfaitingsell_next_ll_rl_letterphotoiv1, R.id.release_forfaitingsell_next_ll_rl1_letterphoto1, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete1, R.id.release_forfaitingsell_next_ll_rl_letterphoto1, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv11, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv2, R.id.release_forfaitingsell_next_ll_rl2_letterphoto2, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete2, R.id.release_forfaitingsell_next_ll_rl_letterphoto2, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv21, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv3, R.id.release_forfaitingsell_next_ll_rl3_letterphoto3, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete3, R.id.release_forfaitingsell_next_ll_rl_letterphoto3, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv31, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv4, R.id.release_forfaitingsell_next_ll_rl4_letterphoto4, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete4, R.id.release_forfaitingsell_next_ll_rl_letterphoto4, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv41, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv5, R.id.release_forfaitingsell_next_ll_rl5_letterphoto5, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete5, R.id.release_forfaitingsell_next_ll_rl_letterphoto5, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv51, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv6, R.id.release_forfaitingsell_next_ll_rl6_letterphoto6, R.id.release_forfaitingsell_next_ll_rl_letterphotoivdelete6, R.id.release_forfaitingsell_next_ll_rl_letterphoto6, R.id.release_forfaitingsell_next_ll_rl_letterphotoiv61, R.id.release_forfaitingsell_next_ll_letterphoto})
    public void onViewClickedLetterPhoto(View view) {

        Intent intent4 = new Intent(this, NormalFilePickActivity.class);
        intent4.putExtra(Constant.MAX_NUMBER, 6 - filesstr2.size());
        intent4.putExtra(IS_TAKEN_DONE, getString(R.string.market_forfaiting_filtrate_sure));
        intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"pdf"/*, "jpg", "png", "jpeg"*/, "rar", "zip"});
        switch (view.getId()) {
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv1:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(0)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr2.get(0));
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
                actionCode = 1;
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv2:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(1)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr2.get(1));
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
                actionCode = 1;
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv3:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(2)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr2.get(2));
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
                actionCode = 1;
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv4:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(3)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr2.get(3));
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
                actionCode = 1;
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv5:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(4)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr2.get(4));
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
                actionCode = 1;
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
            case R.id.release_forfaitingsell_next_ll_rl_letterphotoiv6:
                if ("jpg".equals(StringUtils.isType(filesstr2.get(5)))) {
                    DisPatcher.startPicturePreviewActivity(this, filesstr2.get(5));
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
                actionCode = 1;
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
            case R.id.release_forfaitingsell_next_ll_letterphoto:
                break;
        }
        photoViewLetterPhoto();
    }

    public void photoViewLetterPhoto() {
        reviewSubmitLetterReq.setST0210(StringUtils.contentSplitStr(filesstr2, ";"));
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
//                GlideImageLoader.displayImage0(this,filesstr2.get(0),R.drawable.jpg_shrink,releaseForfaitingsellNextLlRlLetterphotoiv1);
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
                if ("1".equals(SureOrCancelDialogtype)) {
                    //让渡函 1福费廷 2保理
                    if (!StringUtils.isEmpty(reviewSubmitLetterReq.getST0210())) {
                        if ("1".equals(assetsType)) {
                            reviewSubmitLetter();
                        } else if ("2".equals(assetsType)) {
                            reviewFactoringSubmitLetterOnSaleListRet();
                        }
                        setResult(666);
                        finish();
                    }
                } else if ("2".equals(SureOrCancelDialogtype)) {

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
