package com.zjhl.pad.view.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.moudle.entity.res.MyOfferBaoliRs;
import com.zjhl.pad.view.R;

import java.util.List;

/*
* File: MyOfferBaoliAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/21 13:39 
* Changes (from 2018/5/21) 
*/
public class MineFactoringOnSaleListAdapter extends BaseQuickAdapter<MyOfferBaoliRs.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener  {

    private String userCompanyid = "";
    public MineFactoringOnSaleListAdapter(List<MyOfferBaoliRs.DataBean> data) {
        super(R.layout.item_mine_factoring_on_sale_list,data);

    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyOfferBaoliRs.DataBean item) {
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        userCompanyid = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        if (item!=null){
            //保理编号
            helper.setText(R.id.offer_item_text_number,item.getFactoringNo());
            //资产卖出方
            helper.setText(R.id.offer_baoli_sale_banck1,item.getSeller());
            //保理商名称
            helper.setText(R.id.tv_baoli_name,item.getFactoringName());
            //保理到期日
            helper.setText(R.id.offer_end_baoli,item.getMaturity());
            //金额
            helper.setText(R.id.offer_baoli_money,item.getAmount()+"");
            //转让利率
            helper.setText(R.id.tv_baoli_translate,item.getTransferRate());
            //币种
            helper.setText(R.id.tv_bizhong_baoli,item.getCurrency()+"");
            //信息有效期
            helper.setText(R.id.tv_msg_vilidity,item.getIndateMessage());
            //资产状态
            String checkState = item.getCheckState();
            //驳回原因
//            helper.setVisible(R.id.tv_bohuiyuanyin,true);
            helper.setText(R.id.tv_bohuiyuanyin, item.getRejectOpinion());
            //discountRateOld 如果为0
//            if (item.getDiscountRateOld()==0){
////                helper.setText(R.id.tv_first_commit,);
//                helper.setVisible(R.id.ll_data,true);
//                helper.setText(R.id.tv_baojia,item.getDiscountRate()+"");
//            }
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
            }else if("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getEnCountryName());
            }
//1,//保理类型 1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理
            if ("1".equals(item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type1);
            } else if ("2".equals(item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type2);
            } else if ("3".equals(item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type3);
            } else if ("4".equals(item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type4);
            }
//            helper.setGone(R.id.ll_fuhe, false);//报价详情
            helper.setGone(R.id.ll_fuhe_offer_detail, false);//报价详情
            helper.setGone(R.id.iv_fuhe_offer_detail, false);//报价详情
            helper.setGone(R.id.tv_fuhe_offer_detail, false);//报价详情
            helper.setGone(R.id.tv_offer_reason, false);//驳回原因

            helper.setGone(R.id.ll_fuhe_check_letter, false);//查看邀约函
            helper.setGone(R.id.iv_fuhe_check_letter, false);//查看邀约函
            helper.setGone(R.id.tv_fuhe_check_letter, false);//查看邀约函
            helper.setGone(R.id.tv_fuhe_cancel, false);//取消
            helper.setGone(R.id.tv_fuhe_commit1, false);//提交让渡函
            helper.setGone(R.id.tv_check_more_right, false);//查看更多右

            //查看邀约函
            helper.addOnClickListener(R.id.ll_fuhe_check_letter);
            //查看报价详情
            helper.addOnClickListener(R.id.ll_fuhe_offer_detail);
            //取消
            helper.addOnClickListener(R.id.tv_fuhe_cancel);
            //提交
            helper.addOnClickListener(R.id.tv_fuhe_commit1);
            //驳回原因
            helper.addOnClickListener(R.id.tv_offer_reason);
            //查看更多右
            helper.addOnClickListener(R.id.tv_check_more_right);
            /**
             * 这里有个下载邀约函
             */

            /** 审核状态 101 待提交 102 待复核审核 103 系统风控已审核(待发布) 104 报价中 108 达成交易  109 审核失败(卖家)
             *  120 卖家确认 125 买家确定 130 交易完成 140 拒绝(黑名单) 150 取消
             ，201 待运营经办审核(系统) 202 待运营复核审核(系统)  203 待风控经办审核(系统) 204 待风控复核审核(系统)
             210 运营审核不通过(系统) 211 风控审核不通过(系统)
             108 120 125 130 150 经办+复核
             */
            if (!TextUtils.isEmpty(userType)) {
                if (userType.equals("1")) {
                    if ("101".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                    } else if ("102".equals(item.getCheckState())) {
//                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifuheshenhe);
                    } else if ("103".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifabu);
                    } else if ("104".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("108".equals(item.getCheckState())) {
//            helper.setText(R.id.market_item_me_issue, "达成交易");
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    } else if ("109".equals(item.getCheckState())) {
//                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_shenheshibai);
                    } else if ("120".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    } else if ("125".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                    } else if ("130".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    } else if ("140".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_refuse);
                    } else if ("150".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }
                }if (userType.equals("2")) {
                    if ("101".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                    } else if ("102".equals(item.getCheckState())) {
//                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifuheshenhe);
                    } else if ("103".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daifabu);
                    } else if ("104".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        helper.setVisible(R.id.ll_fuhe_offer_detail, true);//显示报价详情
                        helper.setVisible(R.id.iv_fuhe_offer_detail, true);//显示报价详情
                        helper.setVisible(R.id.tv_fuhe_offer_detail, true);//显示报价详情
                        if(!StringUtils.isEmpty(item.getPriceReason())) {
                            helper.setVisible(R.id.tv_offer_reason, true);//显示驳回原因
                        }
//                    helper.setImageResource(R.id.iv_fuhe_bohui1, R.drawable.detail_icon);

                    } else if ("108".equals(item.getCheckState())) {
//            helper.setText(R.id.market_item_me_issue, "达成交易");
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
//                        helper.setVisible(R.id.ll_fuhe_check_letter, true);
//                        helper.addOnClickListener(R.id.ll_fuhe_check_letter);
//                    helper.setImageResource(R.id.iv_fuhe_bohui1, R.drawable.check_icon);
                    } else if ("109".equals(item.getCheckState())) {
//                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_shenheshibai);
                    } else if ("120".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more_right, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } else if ("125".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } else if ("130".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more_right, true);
//                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                        }
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } else if ("140".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_refuse);
                    } else if ("150".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } /*else if ("201".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待运营经办审核");
                    } else if ("202".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待运营复核审核");
                    } else if ("203".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待风控经办审核");
                    } else if ("204".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待风控复核审核");
                    } else if ("210".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "运营审核不通过");
                    } else if ("211".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "风控审核不通过");
                    }*/
                } else if (userType.equals("3")) {
                    if ("101".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                    } else if ("102".equals(item.getCheckState())) {
//                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_daifuheshenhe);
                    } else if ("103".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_daifabu);
                    } else if ("104".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//                        helper.setText(R.id.tv_fuhe_bohui1, "报价详情");
//                        helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.detail_icon);
                        helper.setVisible(R.id.ll_fuhe_offer_detail, true);//显示报价详情
                        helper.setVisible(R.id.iv_fuhe_offer_detail, true);//显示报价详情
                        helper.setVisible(R.id.tv_fuhe_offer_detail, true);//显示报价详情
                        if(!StringUtils.isEmpty(item.getPriceReason())) {
                            helper.setVisible(R.id.tv_offer_reason, true);//显示驳回原因
                        }
                        helper.setVisible(R.id.tv_fuhe_cancel, true);//取消
                    } else if ("108".equals(item.getCheckState())) {
//            helper.setText(R.id.market_item_me_issue, "达成交易");
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
//                        helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                        helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                        helper.setVisible(R.id.tv_fuhe_commit1, true);
                        helper.setVisible(R.id.tv_fuhe_cancel, true);
                    } else if ("109".equals(item.getCheckState())) {
//                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_shenheshibai);
                    } else if ("120".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more_right, true);
//                        helper.setVisible(R.id.ll_fuhe_check_letter, true);
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } else if ("125".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setVisible(R.id.tv_fuhe_commit1, true);//提交渡让函
                        helper.setVisible(R.id.tv_fuhe_cancel, true);//取消
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } else if ("130".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
//                        helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more_right, true);
//                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
//                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
//                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
//                        }
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    } else if ("140".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_refuse);
                    } else if ("150".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//                    helper.setText(R.id.tv_fuhe_bohui1, "查看邀约函");
//                    helper.setImageResource(R.id.tv_fuhe_offer_detail, R.drawable.check_icon);
                    }/* else if ("201".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待运营经办审核");
                    } else if ("202".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待运营复核审核");
                    } else if ("203".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待风控经办审核");
                    } else if ("204".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "待风控复核审核");
                    } else if ("210".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "运营审核不通过");
                    } else if ("211".equals(item.getCheckState())) {
                        helper.setText(R.id.market_item_me_issue, "风控审核不通过");
                    }*/
                }
                if("0".equals(item.getYn())) {
                    helper.setText(R.id.market_item_me_issue,  R.string.onsalelist_forfaiting_adapter_yiquxiao);
                    helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//            helper.setGone(R.id.ll_fuhe, false);//报价详情
                    helper.setGone(R.id.ll_fuhe_offer_detail, false);//报价详情
                    helper.setGone(R.id.iv_fuhe_offer_detail, false);//报价详情
                    helper.setGone(R.id.tv_fuhe_offer_detail, false);//报价详情
                    helper.setGone(R.id.tv_offer_reason, false);//驳回原因

                    helper.setGone(R.id.ll_fuhe_check_letter, false);//查看邀约函
                    helper.setGone(R.id.iv_fuhe_check_letter, false);//查看邀约函
                    helper.setGone(R.id.tv_fuhe_check_letter, false);//查看邀约函
                    helper.setGone(R.id.tv_fuhe_cancel, false);//取消
                    helper.setGone(R.id.tv_fuhe_commit1, false);//提交让渡函
                    helper.setGone(R.id.tv_check_more_right, false);//查看更多右
                }
            }
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtils.showShort("==1=="+position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

//        ToastUtils.showShort("==2=="+position);
    }
}
