package com.zjhl.pad.view.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.LoginActivity;
import com.zjhl.pad.view.activity.MyQuoteActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * File: MyOfferAdapter.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/18 9:35
 * Changes (from 2018/5/18)
 */
public class MyOfferAdapter extends BaseQuickAdapter<MyOfferFufei.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private String priceState;
    //资产
    private String recheckState;
    private String userCompanyid = "";
//    String companyId = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
    public MyOfferAdapter(List<MyOfferFufei.DataBean> data) {
        super(R.layout.item_myoffer, data);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, MyOfferFufei.DataBean item) {
//        blockChainCompanyRet(item.getId() + "", helper);
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
         userCompanyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
//        SharedPreferanceUtils.put(LoginActivity.this,"currentid",currentId);
        if (item != null) {
            helper.setText(R.id.offer_item_text_number, item.getAssetsNo() + "");
            helper.setText(R.id.offer_title, item.getTitle() + "");
            helper.setText(R.id.market_item_text_factoring, item.getOpenFullName() + "");
            helper.setText(R.id.offer_end_data, item.getMaturity() + "");
            helper.setText(R.id.offer_money, item.getAmount() + "");
//            helper.setText(R.id.tv_tiexianlv,item.getAssetsDiscountRate()+"");
            helper.setText(R.id.tv_bizhong, item.getCurrency() + "");
            helper.setText(R.id.tv_msg_vilidity, item.getIndateMessage() + "");
            helper.setText(R.id.tv_duixianglv, item.getAssetsDiscountRate());
//            helper.setText(R.id.market_item_rl_text1, item.getCountryName() + "");
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
                helper.setImageResource(R.id.market_item_iv_orner,R.drawable.market_item_red_orner);
            }else if("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getEnCountryName());
                helper.setImageResource(R.id.market_item_iv_orner,R.drawable.market_item_red_orneren);
            }
            //当资产id状态
            recheckState = item.getRecheckState();
            priceState = item.getPriceState();

            int ownOrgId = item.getOwnOrgId();
            String republishFlag = item.getRepublishFlag();
            helper.setBackgroundRes(R.id.card_view, R.drawable.my_offer);
//            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.market_item_red_orner);
            helper.setGone(R.id.tv_offer_edit2, false);
            helper.setGone(R.id.tv_yuanyin, false);
            helper.setGone(R.id.tv_fuhe_cansal, false);
            helper.setGone(R.id.tv_fuhe_sure, false);
            helper.setGone(R.id.bt_offer_look_book, false);
            helper.setGone(R.id.ll_fuhe, false);
            helper.setGone(R.id.tv_fuhe_bohui1, false);
            helper.setGone(R.id.tv_fuhe_commit1, false);
            helper.setGone(R.id.ll_offer_report, false);
            helper.setGone(R.id.tv_check_more, false);//查看更多(撮合成功)
            helper.setGone(R.id.tv_check_more_1, false);//查看更多(复核 撮合成功 1个)
            helper.setGone(R.id.tv_check_more_right, false);//查看更多右(经办 撮合成功，交易完成)
            helper.setGone(R.id.tv_check_more_right_1, false);//查看更多右(经办 已取消)

            helper.setGone(R.id.market_item_text_ll, false);//区块链默认不显示

            if (!TextUtils.isEmpty(userType)) {
                if (userType.equals("1")) {
                    if ("104".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("108".equals(recheckState) || "120".equals(recheckState) || "125".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    } else if ("130".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                            helper.setGone(R.id.bt_offer_look_book, false);
                            helper.setGone(R.id.tv_check_transfer_letter, false);
                        }
                    } else if ("150".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }

                }if (userType.equals("2")) {
                    if ("104".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        if ("108".equals(priceState)) {
                            //确认按钮
//                        helper.setVisible(R.id.tv_fuhe_sure,true);
                            helper.setVisible(R.id.tv_offer_edit2, true);
                            helper.setVisible(R.id.tv_yuanyin, true);
                        }


                    } else if ("108".equals(recheckState) || "120".equals(recheckState) || "125".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        if ("120".equals(recheckState)) {
                            helper.setVisible(R.id.tv_check_more_right, true);
                        } else {
                            helper.setVisible(R.id.tv_check_more_right_1, true);
                        }

                    } else if ("130".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        helper.setVisible(R.id.tv_check_more_right, true);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
//                        helper.setVisible(R.id.tv_check_transfer_letter, true);
//                        if("107".equals(item.getPriceState())) {
//                            helper.setVisible(R.id.ll_offer_report, true);
//                        }
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                            helper.setGone(R.id.bt_offer_look_book, false);
                            helper.setGone(R.id.tv_check_transfer_letter, false);
                            helper.setGone(R.id.tv_check_more, false);
                            helper.setGone(R.id.tv_check_more_1, false);
                            helper.setGone(R.id.tv_check_more_right, false);
                            helper.setGone(R.id.tv_check_more_right_1, false);
                        }

                    } else if ("150".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setVisible(R.id.tv_check_more_right_1, true);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }

                } else if (userType.equals("3")) {

                    if ("104".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        if ("105".equals(priceState)) {
                            //驳回，提交
                            helper.setVisible(R.id.ll_fuhe, true);
                            helper.setVisible(R.id.tv_fuhe_bohui1, true);
                            helper.setVisible(R.id.tv_fuhe_commit1, true);
                        }
                    } else if ("108".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_fuhe_cansal, true);
                        helper.setVisible(R.id.tv_fuhe_sure, true);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setVisible(R.id.tv_check_more_1, true);
                    }  else if ("120".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));

//                        helper.setVisible(R.id.tv_fuhe_cansal, true);
                        helper.setVisible(R.id.tv_fuhe_sure, true);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setVisible(R.id.tv_check_more, true);

                    }   else if ("125".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        helper.setVisible(R.id.tv_fuhe_cansal, true);
//                        helper.setVisible(R.id.tv_fuhe_sure, true);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setVisible(R.id.tv_check_more_right_1, true);
                    } else if ("130".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        if("107".equals(item.getPriceState())) {
                            helper.setVisible(R.id.ll_offer_report, true);
                            helper.setVisible(R.id.tv_offer_report, true);
                        }
                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                            helper.setGone(R.id.bt_offer_look_book, false);
                            helper.setGone(R.id.tv_check_transfer_letter, false);
                            helper.setGone(R.id.tv_check_more, false);
                            helper.setGone(R.id.tv_check_more_1, false);
                            helper.setGone(R.id.tv_check_more_right, false);
                            helper.setGone(R.id.tv_check_more_right_1, false);
                        }
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    } else if ("150".equals(recheckState)) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
//                        helper.setVisible(R.id.bt_offer_look_book, true);
                        helper.setVisible(R.id.tv_check_more_right_1, true);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }

                }
            }
            if("0".equals(item.getYn())) {
                helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                if ("104".equals(recheckState)) {
                    helper.setGone(R.id.tv_offer_edit2, false);
                    helper.setGone(R.id.tv_yuanyin, false);
                    helper.setGone(R.id.tv_fuhe_cansal, false);
                    helper.setGone(R.id.tv_fuhe_sure, false);
                    helper.setGone(R.id.bt_offer_look_book, false);
                    helper.setGone(R.id.ll_fuhe, false);
                    helper.setGone(R.id.tv_fuhe_bohui1, false);
                    helper.setGone(R.id.tv_fuhe_commit1, false);
                    helper.setGone(R.id.ll_offer_report, false);
                    helper.setGone(R.id.tv_check_transfer_letter, false);
                    helper.setGone(R.id.tv_check_more, false);
                    helper.setGone(R.id.tv_check_more_1, false);
                    helper.setGone(R.id.tv_check_more_right, false);
                    helper.setGone(R.id.tv_check_more_right_1, false);
                }
            }
            String debtType = item.getDebtType();
            if (!TextUtils.isEmpty(debtType)) {
                //国内福费廷
                if (debtType.equals("1")) {
//                    helper.setVisible(R.id.market_item_iv_orner, true);
//                    helper.setVisible(R.id.market_item_tv_title_orner, true);
//                    helper.setText(R.id.market_item_tv_title_orner, R.string.market_forfaiting_adapter_type_inland);

//                    if ("130".equals(recheckState)) {
//                        helper.setBackgroundRes(R.id.market_item_iv_orner, R.drawable.gray_country);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    }

                } else if (debtType.equals("2")) {
//                    helper.setVisible(R.id.market_item_iv_orner, false);
//                    helper.setVisible(R.id.market_item_tv_title_orner, false);
//                    helper.setText(R.id.market_item_tv_title_orner, R.string.market_forfaiting_adapter_type_outland);
//
//                    if ("130".equals(recheckState)) {
//                        helper.setBackgroundRes(R.id.market_item_iv_orner, R.drawable.gray_country);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    }
                }
            }
            if ("1".equals(item.getIsnBlockChain())) {
                helper.setVisible(R.id.market_item_text_ll, true);//是否是区块链 是显示  否 不显示  默认否
            } else {
                helper.setVisible(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
            }
            if ((userCompanyid.equals(ownOrgId+"")) && "0".equals(republishFlag)){
                helper.setVisible(R.id.tv_offer_report,true);
            }else {
                helper.setGone(R.id.tv_offer_report,false);
            }

        }

        //驳回
        helper.addOnClickListener(R.id.tv_fuhe_bohui1);
        //提交
        helper.addOnClickListener(R.id.tv_fuhe_commit1);
        //取消
        helper.addOnClickListener(R.id.tv_fuhe_cansal);
        //查看要约函
        helper.addOnClickListener(R.id.bt_offer_look_book);
        //编辑
        helper.addOnClickListener(R.id.tv_offer_edit2);
        //确认
        helper.addOnClickListener(R.id.tv_fuhe_sure);
        //查看驳回原因
        helper.addOnClickListener(R.id.tv_yuanyin);
        //查看要约函
//        helper.addOnClickListener(R.id.tv_offer_edit1);
        //再次发布
        helper.addOnClickListener(R.id.tv_offer_report);
        //查看让渡函
//        helper.addOnClickListener(R.id.tv_check_transfer_letter);
//        helper.addOnClickListener(R.id.tv_offer_transfer_letter);
        //查看更多(复核 撮合成功)
        helper.addOnClickListener(R.id.tv_check_more);
        //查看更多(复核 撮合成功 1个)
        helper.addOnClickListener(R.id.tv_check_more_1);
        //查看更多右(经办 撮合成功，交易完成)
        helper.addOnClickListener(R.id.tv_check_more_right);
        //查看更多右(经办 已取消)
        helper.addOnClickListener(R.id.tv_check_more_right_1);
        //查看更多(复核 交易完成)
        helper.addOnClickListener(R.id.tv_check_more_report);
        /**
         * 这里有个下载邀约函
         *
         */
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

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
    public void blockChainCompanyRet(String id, final BaseViewHolder helper) {
        MyLogger.pLog().i("区块链查询接口");
        BlockChainReq blockChainReq = new BlockChainReq();
        blockChainReq.setBussIds(new String[]{id});
//        ActionPresenter.getInstance().blockChainCompanyRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
//        ActionPresenter.getInstance().blockChainBussinessRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
        ActionPresenter.getInstance().blockChainTellerRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null && response.body().getData().size() > 0) {
                            if ("1".equals(response.body().getData().get(0).getIsnBlockChain())) {
                                helper.setVisible(R.id.market_item_text_ll, true);//是否是区块链 是显示  否 不显示  默认否
                            } else {
                                helper.setVisible(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
                            }
                        } else {
                            helper.setVisible(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
                        }
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        MyLogger.pLog().e(response.body().getMessage());
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


}
