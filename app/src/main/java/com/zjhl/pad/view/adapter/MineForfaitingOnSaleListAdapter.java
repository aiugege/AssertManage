package com.zjhl.pad.view.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;

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
public class MineForfaitingOnSaleListAdapter extends BaseQuickAdapter<MyOfferFufei.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private String userCompanyid = "";

    public MineForfaitingOnSaleListAdapter(List<MyOfferFufei.DataBean> data) {
        super(R.layout.item_mine_forfaiting_on_sale_list, data);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOfferFufei.DataBean item) {
//        blockChainCompanyRet(item.getId() + "", helper);
        helper.setVisible(R.id.market_item_text_ll, false);
//        helper.setBackgroundRes(R.id.card_view, R.drawable.my_offer);
//        helper.setImageResource(R.id.market_item_iv_orner, R.drawable.market_item_red_orner);
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        userCompanyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        if (item != null) {
            helper.setText(R.id.offer_item_text_number, item.getAssetsNo() + "");
            helper.setText(R.id.offer_title, item.getTitle() + "");
            helper.setText(R.id.market_item_text_factoring, item.getOpenFullName() + "");
            helper.setText(R.id.offer_end_data, item.getMaturity() + "");
            helper.setText(R.id.offer_money, item.getAmount() + "");
            helper.setText(R.id.tv_duixianglv, item.getDiscountRate());
            helper.setText(R.id.tv_bizhong, item.getCurrency() + "");
            helper.setText(R.id.tv_msg_vilidity, item.getIndateMessage() + "");
//            helper.setText(R.id.market_item_rl_text1, item.getCountryName() + "");
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if ("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
                helper.setImageResource(R.id.market_item_iv_orner, R.drawable.market_item_red_orner);
            } else if ("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getEnCountryName());
                helper.setImageResource(R.id.market_item_iv_orner, R.drawable.market_item_red_orneren);
            }
            if ("1".equals(item.getDebtType())) {
//                helper.setVisible(R.id.market_item_tv_title_orner, true);
//                helper.setText(R.id.market_item_tv_title_orner, mContext.getResources().getString(R.string.market_forfaiting_adapter_type_inland));
//                helper.setVisible(R.id.market_item_iv_orner, true);
            } else if ("2".equals(item.getDebtType())) {
//                helper.setVisible(R.id.market_item_tv_title_orner, true);
//                helper.setVisible(R.id.market_item_tv_title_orner, false);
//                helper.setText(R.id.market_item_tv_title_orner, mContext.getResources().getString(R.string.market_forfaiting_adapter_type_outland));
//                helper.setVisible(R.id.market_item_iv_orner, false);
            }

        }
        if ("1".equals(item.getIsnBlockChain())) {
            helper.setVisible(R.id.market_item_text_ll, true);//是否是区块链 是显示  否 不显示  默认否
        } else {
            helper.setVisible(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
        }
        //报价详情
        helper.addOnClickListener(R.id.ll_fuhe_offer_detail);
        //查看要约函
        helper.addOnClickListener(R.id.ll_fuhe_check_letter);
        //取消
        helper.addOnClickListener(R.id.tv_fuhe_cancel);
        //提交
        helper.addOnClickListener(R.id.tv_fuhe_commit1);
        //驳回原因
        helper.addOnClickListener(R.id.tv_offer_reason);
        //查看让渡函
//        helper.addOnClickListener(R.id.ll_fuhe_check_transfer_letter);
        //查看更多右(经办 撮合成功，交易完成)
        helper.addOnClickListener(R.id.tv_check_more_right);
        //查看更多右(经办 已取消)
        helper.addOnClickListener(R.id.tv_check_more_right_1);
        //查看更多中(复核 撮合成功 2个)
        helper.addOnClickListener(R.id.tv_check_more_center);
        //查看更多中(复核 撮合成功 1个)
        helper.addOnClickListener(R.id.tv_check_more_center_1);
        /**
         * 这里有个下载邀约函
         *  存在很多无用控件代码
         */
        helper.setGone(R.id.ll_fuhe, false);//报价详情
        helper.setGone(R.id.tv_offer_reason, false);//驳回原因
        helper.setGone(R.id.ll_fuhe_offer_detail, false);//报价详情
        helper.setGone(R.id.iv_fuhe_offer_detail, false);//报价详情
        helper.setGone(R.id.tv_fuhe_offer_detail, false);//报价详情

        helper.setGone(R.id.ll_fuhe_check_letter, false);//查看邀约函
        helper.setGone(R.id.iv_fuhe_check_letter, false);//查看邀约函
        helper.setGone(R.id.tv_fuhe_check_letter, false);//查看邀约函
        helper.setGone(R.id.tv_fuhe_cancel, false);//取消
        helper.setGone(R.id.tv_fuhe_commit1, false);//提交让渡函
        helper.setGone(R.id.tv_check_more_right, false);//查看更多右(经办 撮合成功，交易完成)
        helper.setGone(R.id.tv_check_more_right_1, false);//查看更多右(经办 已取消)
        helper.setGone(R.id.tv_check_more_center, false);//查看更多中(复核 撮合成功 2个)
        helper.setGone(R.id.tv_check_more_center_1, false);//查看更多中(复核 撮合成功 1个)


        /** 审核状态 101 待提交 102 待复核审核 103 系统风控已审核(待发布) 104 报价中 108 达成交易  109 审核失败(卖家)
         *  120 卖家确认 125 买家确定 130 交易完成 140 拒绝(黑名单) 150 取消
         ，201 待运营经办审核(系统) 202 待运营复核审核(系统)  203 待风控经办审核(系统) 204 待风控复核审核(系统)
         210 运营审核不通过(系统) 211 风控审核不通过(系统)
         108 120 125 130 150 经办+复核
         */
        if (!TextUtils.isEmpty(userType)) {
            if (userType.equals("1")) {
                if ("101".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                } else if ("102".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifuheshenhe);
                } else if ("103".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifabu);
                } else if ("104".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//                    helper.setImageResource(R.id.iv_fuhe_bohui1, R.drawable.detail_icon);
                } else if ("108".equals(item.getRecheckState())) {
//            helper.setText(R.id.market_item_me_issue, "达成交易");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
//                    helper.setImageResource(R.id.iv_fuhe_bohui1, R.drawable.check_icon);
                } else if ("109".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_shenheshibai);
                } else if ("120".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, "卖家确认");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("125".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, "买家确定");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("130".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                    if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                        helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    }
//                    helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                    helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("140".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_refuse);
                } else if ("150".equals(item.getRecheckState())) {
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                }
            }
            if (userType.equals("2")) {
                if ("101".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                } else if ("102".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifuheshenhe);
                } else if ("103".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifabu);
                } else if ("104".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.ll_fuhe_offer_detail, true);//显示报价详情
                    helper.setVisible(R.id.iv_fuhe_offer_detail, true);//显示报价详情
                    helper.setVisible(R.id.tv_fuhe_offer_detail, true);//显示报价详情
                    if (!StringUtils.isEmpty(item.getPriceReason())) {
                        helper.setGone(R.id.tv_offer_reason, true);//驳回原因
                    }
//                    helper.setImageResource(R.id.iv_fuhe_bohui1, R.drawable.detail_icon);
                } else if ("108".equals(item.getRecheckState())) {
//            helper.setText(R.id.market_item_me_issue, "达成交易");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_check_more_right_1, true);
                    helper.setGone(R.id.ll_fuhe_offer_detail, false);
//                    helper.setImageResource(R.id.iv_fuhe_bohui1, R.drawable.check_icon);
                } else if ("109".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_shenheshibai);
                } else if ("120".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, "卖家确认");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setGone(R.id.ll_fuhe_offer_detail, false);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    helper.setVisible(R.id.tv_check_more_right, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("125".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, "买家确定");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setGone(R.id.ll_fuhe_offer_detail, false);
                    helper.setVisible(R.id.tv_check_more_right_1, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("130".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_check_more_right, true);
//                    if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                        helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    }
//                    helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                    helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("140".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_refuse);
                } else if ("150".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_check_more_right_1, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } /*else if ("201".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待运营经办审核");
                } else if ("202".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待运营复核审核");
                } else if ("203".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待风控经办审核");
                } else if ("204".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待风控复核审核");
                } else if ("210".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "运营审核不通过");
                } else if ("211".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "风控审核不通过");
                }*/
            } else if (userType.equals("3")) {
                if ("101".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                } else if ("102".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifuheshenhe);
                } else if ("103".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifabu);
                } else if ("104".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//                    helper.setText(R.id.tv_fuhe_bohui1, "报价详情");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.detail_icon);
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.ll_fuhe_offer_detail, true);//显示报价详情
                    helper.setVisible(R.id.iv_fuhe_offer_detail, true);//显示报价详情
                    helper.setVisible(R.id.tv_fuhe_offer_detail, true);//显示报价详情
                    if (!StringUtils.isEmpty(item.getPriceReason())) {
                        helper.setGone(R.id.tv_offer_reason, true);//驳回原因
                    }
                    helper.setVisible(R.id.tv_fuhe_cancel, true);
                } else if ("108".equals(item.getRecheckState())) {
//            helper.setText(R.id.market_item_me_issue, "达成交易");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_check_more_center_1, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_fuhe_commit1, true);
                    helper.setVisible(R.id.tv_fuhe_cancel, true);
                } else if ("109".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_shenheshibai);
                } else if ("120".equals(item.getRecheckState())) {
//                    helper.setText(R.id.market_item_me_issue, "卖家确认");
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setVisible(R.id.ll_fuhe, true);

                    helper.setVisible(R.id.ll_fuhe_offer_detail, false);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    helper.setGone(R.id.tv_fuhe_commit1, false);
                    helper.setVisible(R.id.tv_check_more_center, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("125".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    helper.setGone(R.id.ll_fuhe_offer_detail, false);
                    helper.setVisible(R.id.tv_check_more_center_1, true);

                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_fuhe_commit1, true);
                    helper.setVisible(R.id.tv_fuhe_cancel, true);
//                    helper.setText(R.id.market_item_me_issue, "买家确定");
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("130".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));

                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_check_more_right, true);
//                    if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                        helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    }
//                    helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                    helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } else if ("140".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_refuse);
                } else if ("150".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    helper.setVisible(R.id.ll_fuhe, true);
                    helper.setVisible(R.id.tv_check_more_right_1, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.iv_fuhe_offer_detail, R.drawable.check_icon);
                } /*else if ("201".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待运营经办审核");
                } else if ("202".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待运营复核审核");
                } else if ("203".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待风控经办审核");
                } else if ("204".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "待风控复核审核");
                } else if ("210".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "运营审核不通过");
                } else if ("211".equals(item.getRecheckState())) {
                    helper.setText(R.id.market_item_me_issue, "风控审核不通过");
                }*/
            }
            if ("0".equals(item.getYn())) {
                helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                helper.setGone(R.id.tv_offer_reason, false);//驳回原因
                helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                helper.setGone(R.id.ll_fuhe, false);//报价详情
                helper.setGone(R.id.ll_fuhe_offer_detail, false);//报价详情
                helper.setGone(R.id.iv_fuhe_offer_detail, false);//报价详情
                helper.setGone(R.id.tv_fuhe_offer_detail, false);//报价详情

                helper.setGone(R.id.ll_fuhe_check_letter, false);//查看邀约函
                helper.setGone(R.id.iv_fuhe_check_letter, false);//查看邀约函
                helper.setGone(R.id.tv_fuhe_check_letter, false);//查看邀约函
                helper.setGone(R.id.tv_fuhe_cancel, false);//取消
                helper.setGone(R.id.tv_fuhe_commit1, false);//提交让渡函
                helper.setGone(R.id.tv_check_more_right, false);
                helper.setGone(R.id.tv_check_more_right_1, false);
                helper.setGone(R.id.tv_check_more_center, false);
                helper.setGone(R.id.tv_check_more_center_1, false);
            }

            //测试驳回原因对话框代码
//                helper.setVisible(R.id.ll_fuhe, true);
//                helper.setGone(R.id.tv_offer_reason, true);//驳回原因
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtils.showShort("====" + position);
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
