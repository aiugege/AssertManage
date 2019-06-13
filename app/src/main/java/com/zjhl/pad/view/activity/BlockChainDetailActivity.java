package com.zjhl.pad.view.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @desc: BlockChainDetailActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/5/29 20:02
 * @projectname: nnkj
 **/
public class BlockChainDetailActivity extends BaseActivity {
    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    @BindView(R.id.iv_Rtv1)
    TextView ivRtv1;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_id_number)
    TextView tvIdNumber;
    @BindView(R.id.blockchain_detail_tv_number)
    TextView blockchainDetailTvNumber;
    @BindView(R.id.blockchain_detail_tv2_number)
    TextView blockchainDetailTv2Number;
    @BindView(R.id.blockchain_detail_ll_number)
    LinearLayout blockchainDetailLlNumber;
    @BindView(R.id.blockchain_detail_tv_amount)
    TextView blockchainDetailTvAmount;
    @BindView(R.id.blockchain_detail_tv2_amount)
    TextView blockchainDetailTv2Amount;
    @BindView(R.id.blockchain_detail_ll_amount)
    LinearLayout blockchainDetailLlAmount;
    @BindView(R.id.blockchain_detail_tv_currency)
    TextView blockchainDetailTvCurrency;
    @BindView(R.id.blockchain_detail_tv2_currency)
    TextView blockchainDetailTv2Currency;
    @BindView(R.id.blockchain_detail_ll_currency)
    LinearLayout blockchainDetailLlCurrency;
    @BindView(R.id.blockchain_detail_tv_creditnumber)
    TextView blockchainDetailTvCreditnumber;
    @BindView(R.id.blockchain_detail_tv2_creditnumber)
    TextView blockchainDetailTv2Creditnumber;
    @BindView(R.id.blockchain_detail_ll_creditnumber)
    LinearLayout blockchainDetailLlCreditnumber;
    @BindView(R.id.blockchain_detail_tv_iussingbankswift)
    TextView blockchainDetailTvIussingbankswift;
    @BindView(R.id.blockchain_detail_tv2_iussingbankswift)
    TextView blockchainDetailTv2Iussingbankswift;
    @BindView(R.id.blockchain_detail_ll_iussingbankswift)
    LinearLayout blockchainDetailLlIussingbankswift;
    @BindView(R.id.blockchain_detail_tv_acceptingbankswift)
    TextView blockchainDetailTvAcceptingbankswift;
    @BindView(R.id.blockchain_detail_tv2_acceptingbankswift)
    TextView blockchainDetailTv2Acceptingbankswift;
    @BindView(R.id.blockchain_detail_ll_acceptingbankswift)
    LinearLayout blockchainDetailLlAcceptingbankswift;
    @BindView(R.id.blockchain_detail_ll_left)
    LinearLayout blockchainDetailLlLeft;
    @BindView(R.id.blockchain_detail_issue_tv_title)
    TextView blockchainDetailIssueTvTitle;
    @BindView(R.id.blockchain_detail_issue_tv_people)
    TextView blockchainDetailIssueTvPeople;
    @BindView(R.id.blockchain_detail_issue_tv2_people)
    TextView blockchainDetailIssueTv2People;
    @BindView(R.id.blockchain_detail_issue_ll_people)
    LinearLayout blockchainDetailIssueLlPeople;
    @BindView(R.id.blockchain_detail_issue_tv_date)
    TextView blockchainDetailIssueTvDate;
    @BindView(R.id.blockchain_detail_issue_tv2_date)
    TextView blockchainDetailIssueTv2Date;
    @BindView(R.id.blockchain_detail_issue_ll_date)
    LinearLayout blockchainDetailIssueLlDate;
    @BindView(R.id.blockchain_detail_issue_tv_id)
    TextView blockchainDetailIssueTvId;
    @BindView(R.id.blockchain_detail_issue_tv2_id)
    TextView blockchainDetailIssueTv2Id;
    @BindView(R.id.blockchain_detail_issue_ll_id)
    LinearLayout blockchainDetailIssueLlId;
    @BindView(R.id.blockchain_detail_ll_right)
    LinearLayout blockchainDetailLlRight;
    @BindView(R.id.blockchain_detail_ll_left2)
    LinearLayout blockchainDetailLlLeft2;
    @BindView(R.id.blockchain_detail_issue_tv_title2)
    TextView blockchainDetailIssueTvTitle2;
    @BindView(R.id.blockchain_detail_issue_tv_people2)
    TextView blockchainDetailIssueTvPeople2;
    @BindView(R.id.blockchain_detail_issue_tv2_people2)
    TextView blockchainDetailIssueTv2People2;
    @BindView(R.id.blockchain_detail_issue_ll_people2)
    LinearLayout blockchainDetailIssueLlPeople2;
    @BindView(R.id.blockchain_detail_issue_tv_date2)
    TextView blockchainDetailIssueTvDate2;
    @BindView(R.id.blockchain_detail_issue_tv2_date2)
    TextView blockchainDetailIssueTv2Date2;
    @BindView(R.id.blockchain_detail_issue_ll_date2)
    LinearLayout blockchainDetailIssueLlDate2;
    @BindView(R.id.blockchain_detail_issue_tv_id2)
    TextView blockchainDetailIssueTvId2;
    @BindView(R.id.blockchain_detail_issue_tv2_id2)
    TextView blockchainDetailIssueTv2Id2;
    @BindView(R.id.blockchain_detail_issue_ll_id2)
    LinearLayout blockchainDetailIssueLlId2;
    @BindView(R.id.blockchain_detail_ll_right2)
    LinearLayout blockchainDetailLlRight2;
    @BindView(R.id.blockchain_detail_ll_left3)
    LinearLayout blockchainDetailLlLeft3;
    @BindView(R.id.blockchain_detail_issue_tv_title3)
    TextView blockchainDetailIssueTvTitle3;
    @BindView(R.id.blockchain_detail_issue_tv_people3)
    TextView blockchainDetailIssueTvPeople3;
    @BindView(R.id.blockchain_detail_issue_tv2_people3)
    TextView blockchainDetailIssueTv2People3;
    @BindView(R.id.blockchain_detail_issue_ll_people3)
    LinearLayout blockchainDetailIssueLlPeople3;
    @BindView(R.id.blockchain_detail_issue_tv_date3)
    TextView blockchainDetailIssueTvDate3;
    @BindView(R.id.blockchain_detail_issue_tv2_date3)
    TextView blockchainDetailIssueTv2Date3;
    @BindView(R.id.blockchain_detail_issue_ll_date3)
    LinearLayout blockchainDetailIssueLlDate3;
    @BindView(R.id.blockchain_detail_issue_tv_id3)
    TextView blockchainDetailIssueTvId3;
    @BindView(R.id.blockchain_detail_issue_tv2_id3)
    TextView blockchainDetailIssueTv2Id3;
    @BindView(R.id.blockchain_detail_issue_ll_id3)
    LinearLayout blockchainDetailIssueLlId3;
    @BindView(R.id.blockchain_detail_ll_right3)
    LinearLayout blockchainDetailLlRight3;
    //资产id
    String assestId = "";
    @BindView(R.id.blockchain_detail_ll_left_iv)
    ImageView blockchainDetailLlLeftIv;
    @BindView(R.id.blockchain_detail_ll_left2_iv)
    ImageView blockchainDetailLlLeft2Iv;
    @BindView(R.id.blockchain_detail_ll_left3_iv)
    ImageView blockchainDetailLlLeft3Iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blockchain_detail);
        ButterKnife.bind(this);
        assestId = getIntent().getStringExtra("assestId");
        tvContent.setText(R.string.blockchain_title);
        confirmOfferData(assestId);
    }

    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.iv_Rtv, R.id.iv_Rtv1, R.id.tv_id, R.id.tv_id_number})
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
            case R.id.iv_Rtv1:
                break;
            case R.id.tv_id:
                break;
            case R.id.tv_id_number:
                break;
        }
    }

    /*
     /**
     * //区块链资产查询接口
     *
     * @POST(Constants.NETPATH.BLOCKCHAINBUSSINESS) Call<BlockChainRes> blockChainBussinessRet(@Body RequestBody requestBody);
    public Call<BlockChainRes> blockChainBussinessRet(BlockChainReq data) {
        Call<BlockChainRes> blockChainBussinessRet = mApi.blockChainBussinessRet(createRequestBody(data));
        return blockChainBussinessRet;
    }
     */
    //区块链多条接口
    public void confirmOfferData(String id) {
        MyLogger.pLog().i("区块链多条接口");
        showWaitDialog();
        BlockChainReq blockChainReq = new BlockChainReq();
        blockChainReq.setBussId(id);
        ActionPresenter.getInstance().blockChainBussinessRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    closeWaitDialog();
                    if (response.body().getCode() == 300) {
                        //获取区块链信息成功
                        setData((BlockChainRes)response.body());
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setData(BlockChainRes blockChainRes) {
        if (blockChainRes != null) {
            List<BlockChainRes.DataBean> list = blockChainRes.getData();

            if (list != null && list.size() > 0) {
                for(int i=0;i<list.size();i++){
                    String state = list.get(i).getBussTypeState();
                    if ("1".equals(state)) {
                        blockchainDetailTv2Number.setText(list.get(i).getAssetsNo());
                        blockchainDetailTv2Amount.setText(list.get(i).getAmount());
                        blockchainDetailTv2Currency.setText(list.get(i).getCurrency());
                        blockchainDetailTv2Creditnumber.setText(list.get(i).getLcNo());
                        blockchainDetailTv2Iussingbankswift.setText(list.get(i).getOpenSwift());
                        blockchainDetailTv2Acceptingbankswift.setText(list.get(i).getTenderSwift());

//                        blockchainDetailIssueTvTitle2.setTextColor(getResources().getColor(R.color.common_text_gray));
//                        blockchainDetailIssueTvTitle3.setTextColor(getResources().getColor(R.color.common_text_gray));

                        blockchainDetailIssueTv2People.setText(list.get(i).getOwnOrgName());
                        blockchainDetailIssueTv2Date.setText(list.get(i).getChainDate());
                        blockchainDetailIssueTv2Id.setText(list.get(i).getBlockChainNode());
                    }
                    if ("2".equals(state)) {
                        blockchainDetailTv2Number.setText(list.get(i).getAssetsNo());
                        blockchainDetailTv2Amount.setText(list.get(i).getAmount());
                        blockchainDetailTv2Currency.setText(list.get(i).getCurrency());
                        blockchainDetailTv2Creditnumber.setText(list.get(i).getLcNo());
                        blockchainDetailTv2Iussingbankswift.setText(list.get(i).getOpenSwift());
                        blockchainDetailTv2Acceptingbankswift.setText(list.get(i).getTenderSwift());

                        blockchainDetailIssueTvTitle2.setTextColor(getResources().getColor(R.color.dark));
//                        blockchainDetailIssueTvTitle3.setTextColor(getResources().getColor(R.color.common_text_gray));
                        blockchainDetailLlLeft2Iv.setBackground(getResources().getDrawable(R.drawable.blockchain_circle_blue));
                        blockchainDetailLlRight2.setVisibility(View.VISIBLE);

//                        blockchainDetailIssueTv2People.setText(list.get(i).getOwnOrgName());
//                        blockchainDetailIssueTv2Date.setText(list.get(i).getChainDate());
//                        blockchainDetailIssueTv2Id.setText(list.get(i).getBlockChainNode());

                        blockchainDetailIssueTv2People2.setText(list.get(i).getOwnOrgName());
                        blockchainDetailIssueTv2Date2.setText(list.get(i).getChainDate());
                        blockchainDetailIssueTv2Id2.setText(list.get(i).getBlockChainNode());
                    }
                    if ("3".equals(state)) {
                        blockchainDetailTv2Number.setText(list.get(i).getAssetsNo());
                        blockchainDetailTv2Amount.setText(list.get(i).getAmount());
                        blockchainDetailTv2Currency.setText(list.get(i).getCurrency());
                        blockchainDetailTv2Creditnumber.setText(list.get(i).getLcNo());
                        blockchainDetailTv2Iussingbankswift.setText(list.get(i).getOpenSwift());
                        blockchainDetailTv2Acceptingbankswift.setText(list.get(i).getTenderSwift());

//                        blockchainDetailIssueTvTitle2.setTextColor(getResources().getColor(R.color.dark));
                        blockchainDetailIssueTvTitle3.setTextColor(getResources().getColor(R.color.dark));
//                        blockchainDetailLlLeft2Iv.setBackground(getResources().getDrawable(R.drawable.blockchain_circle_blue));
                        blockchainDetailLlLeft3Iv.setBackground(getResources().getDrawable(R.drawable.blockchain_circle_blue));
//                        blockchainDetailLlRight2.setVisibility(View.VISIBLE);
                        blockchainDetailLlRight3.setVisibility(View.VISIBLE);
//                        blockchainDetailIssueTv2People.setText(list.get(i).getOwnOrgName());
//                        blockchainDetailIssueTv2Date.setText(list.get(i).getChainDate());
//                        blockchainDetailIssueTv2Id.setText(list.get(i).getBlockChainNode());
//
//                        blockchainDetailIssueTv2People2.setText(list.get(i).getOwnOrgName());
//                        blockchainDetailIssueTv2Date2.setText(list.get(i).getChainDate());
//                        blockchainDetailIssueTv2Id2.setText(list.get(i).getBlockChainNode());

                        blockchainDetailIssueTv2People3.setText(list.get(i).getOwnOrgName());
                        blockchainDetailIssueTv2Date3.setText(list.get(i).getChainDate());
                        blockchainDetailIssueTv2Id3.setText(list.get(i).getBlockChainNode());
                    }

                }


//                String state = list.get(0).getBussTypeState();
//                if ("1".equals(state) && list.size() == 1) {
//                    blockchainDetailTv2Number.setText(list.get(0).getAssetsNo());
//                    blockchainDetailTv2Amount.setText(list.get(0).getAmount());
//                    blockchainDetailTv2Currency.setText(list.get(0).getCurrency());
//                    blockchainDetailTv2Creditnumber.setText(list.get(0).getLcNo());
//                    blockchainDetailTv2Iussingbankswift.setText(list.get(0).getOpenSwift());
//                    blockchainDetailTv2Acceptingbankswift.setText(list.get(0).getTenderSwift());
//
//                    blockchainDetailIssueTvTitle2.setTextColor(getResources().getColor(R.color.common_text_gray));
//                    blockchainDetailIssueTvTitle3.setTextColor(getResources().getColor(R.color.common_text_gray));
//
//                    blockchainDetailIssueTv2People.setText(list.get(0).getOwnOrgName());
//                    blockchainDetailIssueTv2Date.setText(list.get(0).getChainDate());
//                    blockchainDetailIssueTv2Id.setText(list.get(0).getBlockChainNode());
//                }
//                if ("2".equals(state) && list.size() == 2) {
//                    blockchainDetailTv2Number.setText(list.get(0).getAssetsNo());
//                    blockchainDetailTv2Amount.setText(list.get(0).getAmount());
//                    blockchainDetailTv2Currency.setText(list.get(0).getCurrency());
//                    blockchainDetailTv2Creditnumber.setText(list.get(0).getLcNo());
//                    blockchainDetailTv2Iussingbankswift.setText(list.get(0).getOpenSwift());
//                    blockchainDetailTv2Acceptingbankswift.setText(list.get(0).getTenderSwift());
//
//                    blockchainDetailIssueTvTitle2.setTextColor(getResources().getColor(R.color.dark));
//                    blockchainDetailIssueTvTitle3.setTextColor(getResources().getColor(R.color.common_text_gray));
//                    blockchainDetailLlLeft2Iv.setBackground(getResources().getDrawable(R.drawable.blockchain_circle_blue));
//                    blockchainDetailLlRight2.setVisibility(View.VISIBLE);
//
//                    blockchainDetailIssueTv2People.setText(list.get(0).getOwnOrgName());
//                    blockchainDetailIssueTv2Date.setText(list.get(0).getChainDate());
//                    blockchainDetailIssueTv2Id.setText(list.get(0).getBlockChainNode());
//
//                    blockchainDetailIssueTv2People2.setText(list.get(0).getOwnOrgName());
//                    blockchainDetailIssueTv2Date2.setText(list.get(0).getChainDate());
//                    blockchainDetailIssueTv2Id2.setText(list.get(0).getBlockChainNode());
//                }
//                if ("3".equals(state)) {
//                    blockchainDetailTv2Number.setText(list.get(0).getAssetsNo());
//                    blockchainDetailTv2Amount.setText(list.get(0).getAmount());
//                    blockchainDetailTv2Currency.setText(list.get(0).getCurrency());
//                    blockchainDetailTv2Creditnumber.setText(list.get(0).getLcNo());
//                    blockchainDetailTv2Iussingbankswift.setText(list.get(0).getOpenSwift());
//                    blockchainDetailTv2Acceptingbankswift.setText(list.get(0).getTenderSwift());
//
//                    blockchainDetailIssueTvTitle2.setTextColor(getResources().getColor(R.color.dark));
//                    blockchainDetailIssueTvTitle3.setTextColor(getResources().getColor(R.color.dark));
//                    blockchainDetailLlLeft2Iv.setBackground(getResources().getDrawable(R.drawable.blockchain_circle_blue));
//                    blockchainDetailLlLeft3Iv.setBackground(getResources().getDrawable(R.drawable.blockchain_circle_blue));
//                    blockchainDetailLlRight2.setVisibility(View.VISIBLE);
//                    blockchainDetailLlRight3.setVisibility(View.VISIBLE);
//                    blockchainDetailIssueTv2People.setText(list.get(0).getOwnOrgName());
//                    blockchainDetailIssueTv2Date.setText(list.get(0).getChainDate());
//                    blockchainDetailIssueTv2Id.setText(list.get(0).getBlockChainNode());
//
//                    blockchainDetailIssueTv2People2.setText(list.get(0).getOwnOrgName());
//                    blockchainDetailIssueTv2Date2.setText(list.get(0).getChainDate());
//                    blockchainDetailIssueTv2Id2.setText(list.get(0).getBlockChainNode());
//
//                    blockchainDetailIssueTv2People3.setText(list.get(0).getOwnOrgName());
//                    blockchainDetailIssueTv2Date3.setText(list.get(0).getChainDate());
//                    blockchainDetailIssueTv2Id3.setText(list.get(0).getBlockChainNode());
//                }
            }

        }
    }

}
