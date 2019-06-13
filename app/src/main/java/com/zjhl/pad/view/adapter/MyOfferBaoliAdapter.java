package com.zjhl.pad.view.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.moudle.entity.res.MyOfferBaoliRs;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.view.R;

import java.util.List;

/*
* File: MyOfferBaoliAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/21 13:39 
* Changes (from 2018/5/21) 
*/
public class MyOfferBaoliAdapter extends BaseQuickAdapter<MyOfferBaoliRs.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener  {

    private String priceState;
    //资产状态
    private String checkState;

    private String userCompanyid = "";
//    String companyId = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
    public MyOfferBaoliAdapter(List<MyOfferBaoliRs.DataBean> data) {
        super(R.layout.myoffer_baoli_item,data);

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
            helper.setText(R.id.offer_end_baoli,item.getMaturity()+"");
            //金额
            helper.setText(R.id.offer_baoli_money,item.getAmount());
            //转让利率
            helper.setText(R.id.tv_baoli_translate,item.getTransferRate());
            //币种
            helper.setText(R.id.tv_bizhong_baoli,item.getCurrency());
            //信息有效期
            helper.setText(R.id.tv_msg_vilidity,item.getIndateMessage());
            //国家
//            helper.setText(R.id.market_item_rl_text1,item.getCountryName()+"");
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
            }else if("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getEnCountryName());
            }
            //资产状态
             checkState = item.getCheckState();
            priceState = item.getPriceState();
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
            //驳回原因
            helper.setGone(R.id.tv_bohuiyuanyin,false);
//            helper.setText(R.id.tv_bohuiyuanyin, item.getRejectOpinion());
            priceState = item.getPriceState();
            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.market_item_red_orner);
            helper.setBackgroundRes(R.id.card_view, R.drawable.my_offer);
            helper.setGone(R.id.tv_yuanyin,false);
            helper.setGone(R.id.tv_offer_edit,false);
            helper.setGone(R.id.ll_count,false);
            helper.setGone(R.id.bt_offer_look,false);
            helper.setGone(R.id.tv_offer_edit1,false);
            helper.setGone(R.id.tv_fuhe_cansal,false);
            helper.setGone(R.id.tv_fuhe_sure,false);
            helper.setGone(R.id.ll_fuhe,false);
            helper.setGone(R.id.tv_fuhe_bohui,false);
            helper.setGone(R.id.tv_fuhe_commit,false);
            helper.setGone(R.id.tv_check_more,false);//查看更多
            String priceState = item.getPriceState();
            //当资产id状态
//            recheckState = item.getRecheckState();
            //discountRateOld 如果为0

            if (!TextUtils.isEmpty(userType)){
                if (userType.equals("1")){
                    if ("104".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));

                    }else if ("108".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    }else if ("120".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        helper.setVisible(R.id.tv_fuhe_cansal,true);
//                        helper.setVisible(R.id.tv_fuhe_sure,true);
                    }else if ("125".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        helper.setVisible(R.id.tv_fuhe_cansal,true);
//                        helper.setVisible(R.id.tv_fuhe_sure,true);
                    }else if ("130".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        if("107".equals(item.getPriceState())) {
//                            helper.setVisible(R.id.ll_count,true);
//                            helper.setVisible(R.id.tv_offer_edit1,true);
//                            helper.setVisible(R.id.tv_offer_report,false);
//                        }
                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                        }
                    }else if ("150".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }

                }if (userType.equals("2")){
                    if ("104".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        if("108".equals(priceState)) {//报价驳回状态显示编辑和驳回原因
                            helper.setVisible(R.id.tv_yuanyin, true);
                            helper.setVisible(R.id.tv_offer_edit, true);
                        }


                    }else if ("108".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                    }else if ("120".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more,true);
//                        helper.setVisible(R.id.tv_fuhe_cansal,true);
//                        helper.setVisible(R.id.tv_fuhe_sure,true);
                    }else if ("125".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        helper.setVisible(R.id.tv_fuhe_cansal,true);
//                        helper.setVisible(R.id.tv_fuhe_sure,true);
                    }else if ("130".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more,true);
//                        if("107".equals(item.getPriceState())) {
//                            helper.setVisible(R.id.ll_count,true);
//                            helper.setVisible(R.id.tv_offer_edit1,true);
//                            helper.setVisible(R.id.tv_offer_report,false);
//                        }
                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                            helper.setGone(R.id.tv_check_more,false);
                        }
                    }else if ("150".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }

                }else if (userType.equals("3")){

                    if ("104".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_baojiaozhong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        if("105".equals(priceState)) {//报价状态显示驳回
                            helper.setVisible(R.id.ll_fuhe, true);
                            helper.setVisible(R.id.tv_fuhe_commit, true);
                            helper.setVisible(R.id.tv_fuhe_bohui, true);
                        }
                    }else if ("108".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));

//                        helper.setVisible(R.id.bt_offer_look,true);
                        helper.setVisible(R.id.tv_fuhe_cansal,true);
                        helper.setVisible(R.id.tv_fuhe_sure,true);
                    }else if ("120".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        helper.setVisible(R.id.tv_fuhe_cansal,true);
                        helper.setVisible(R.id.tv_fuhe_sure,true);
                        helper.setVisible(R.id.tv_check_more,true);
//                        helper.setVisible(R.id.tv_check_transfer_letter, true);
                    }else if ("125".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_cuohechenggong);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
//                        helper.setVisible(R.id.tv_fuhe_cansal,true);
//                        helper.setVisible(R.id.tv_fuhe_sure,true);
                    }else if ("130".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_jiaoyiwancheng);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.blue));
                        helper.setVisible(R.id.tv_check_more,true);
//                        helper.setVisible(R.id.ll_count,true);
//                        helper.setVisible(R.id.tv_offer_edit1,true);
//                        helper.setVisible(R.id.tv_offer_report,false);
//                        if("107".equals(item.getPriceState())) {
//                            helper.setVisible(R.id.ll_count,true);
//                            helper.setVisible(R.id.tv_offer_edit1,true);
//                            helper.setVisible(R.id.tv_offer_report,false);
//                        }
                        if(!userCompanyid.equals(item.getOwnOrgId()+"")) {
                            helper.setImageResource(R.id.market_item_iv_orner, R.drawable.gray_country);
                            helper.setBackgroundRes(R.id.card_view, R.drawable.gray_back);
                            helper.setGone(R.id.tv_check_more,false);
                        }
                    }else if ("150".equals(checkState)){
                        helper.setText(R.id.market_item_me_issue,R.string.onsalelist_forfaiting_adapter_yiquxiao);
                        helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }

                }
            }

            if("0".equals(item.getYn())) {
                helper.setText(R.id.market_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                helper.setTextColor(R.id.market_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                if("104".equals(checkState)){
                    helper.setGone(R.id.tv_yuanyin,false);
                    helper.setGone(R.id.tv_offer_edit,false);
                    helper.setGone(R.id.ll_count,false);
                    helper.setGone(R.id.bt_offer_look,false);
                    helper.setGone(R.id.tv_offer_edit1,false);
                    helper.setGone(R.id.tv_fuhe_cansal,false);
                    helper.setGone(R.id.tv_fuhe_sure,false);
                    helper.setGone(R.id.ll_fuhe,false);
                    helper.setGone(R.id.tv_fuhe_bohui,false);
                    helper.setGone(R.id.tv_fuhe_commit,false);
                    helper.setGone(R.id.tv_check_more,false);//查看更多
                }
            }

            //驳回
            helper.addOnClickListener(R.id.tv_fuhe_bohui);
            //提交
            helper.addOnClickListener(R.id.tv_fuhe_commit);
            //取消
            helper.addOnClickListener(R.id.tv_fuhe_cansal);
            //查看要约函
            helper.addOnClickListener(R.id.bt_offer_look);
            //确认
            helper.addOnClickListener(R.id.tv_fuhe_sure);
            //查看驳回原因
            helper.addOnClickListener(R.id.tv_yuanyin);
            //编辑
            helper.addOnClickListener(R.id.tv_offer_edit);
            //查看邀约函
            helper.addOnClickListener(R.id.tv_offer_edit1);
            //再次发布
            helper.addOnClickListener(R.id.tv_offer_report);
            //查看更多
            helper.addOnClickListener(R.id.tv_check_more);
            /**
             * 这里有个下载邀约函
             *
             */
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
