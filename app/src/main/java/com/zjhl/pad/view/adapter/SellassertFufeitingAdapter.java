package com.zjhl.pad.view.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.moudle.entity.res.AsellasetsFuFeiTingRes;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.view.R;

import java.util.List;

import retrofit2.http.Headers;

/*
 * File: SellassertFufeitingAdapter.java 待售福费廷
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/23 15:08
 * Changes (from 2018/5/23)
 */
public class SellassertFufeitingAdapter extends BaseQuickAdapter<AsellasetsFuFeiTingRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    public SellassertFufeitingAdapter(List<AsellasetsFuFeiTingRes.DataBean> data) {
        super(R.layout.sellassert_baoli_item, data);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, AsellasetsFuFeiTingRes.DataBean item) {
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        if (item != null) {
            //
            helper.setText(R.id.offer_item_text_number, item.getAssetsNo() + "");
            helper.setText(R.id.offer_title, item.getTitle() + "");
            helper.setText(R.id.market_item_text_factoring, item.getOpenFullName() + "");
            helper.setText(R.id.offer_end_data, item.getMaturity() + "");
            helper.setText(R.id.offer_money, item.getAmount() + "");
            helper.setText(R.id.tv_duixianglv, item.getDiscountRate());
            helper.setText(R.id.tv_bizhong, item.getCurrency() + "");
            helper.setText(R.id.tv_msg_vilidity, item.getIndateMessage() + "");
            //国家
//            helper.setText(R.id.market_item_rl_text1, item.getCountryName() + "");
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
                helper.setImageResource(R.id.iv_sellsert_fufweiting,R.drawable.new_add_asserts);
                helper.setImageResource(R.id.market_item_iv_orner,R.drawable.market_item_red_orner);
            }else if("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getEnCountryName());
                helper.setImageResource(R.id.iv_sellsert_fufweiting,R.drawable.new_add_asserts_en);
                helper.setImageResource(R.id.market_item_iv_orner,R.drawable.market_item_red_orneren);
            }
            //审核状态
            String recheckState = item.getRecheckState() + "";
            String recheckStateBefore = item.getRecheckStateBefore() + "";//资产修改前状态
            //是否是新增资产
            String trsTimes = item.getTrsTimes();
//            helper.setText(R.id.bt_offer_look_book,"查看要约函");
            if ("1".equals(item.getDebtType() + "")) {
//                helper.setVisible(R.id.market_item_tv_title_orner, true);
//                helper.setText(R.id.market_item_tv_title_orner, R.string.market_forfaiting_adapter_type_inland);
//                helper.setVisible(R.id.market_item_iv_orner, true);
            } else if ("2".equals(item.getDebtType() + "")) {
//                helper.setVisible(R.id.market_item_tv_title_orner, false);
//                helper.setText(R.id.market_item_tv_title_orner, R.string.market_forfaiting_adapter_type_outland);
//                helper.setVisible(R.id.market_item_iv_orner, false);
            }

//            经办：
//            待售状态有: 待提交（101） 待提交(新增资产 trs_times>0 101)  复核中(102)  复核未通过(109)  审核中(201 202 203 204)  审核未通过(210 211)   待发布(103)  已取消(筛选查询)  已失效（126）
//            显示按钮：
//            待提交（101）：删除、编辑、提交按钮
//            待提交(新增资质 trs_times>0 101)：删除、编辑、提交按钮
//            审核中(201 202 203 204)：无按钮
//            复核未通过(109)：删除、编辑、提交按钮,驳回原因
//            审核中(201 202 203 204)：无按钮
//            审核未通过(210 211)：无按钮
//            待发布(103)：无按钮
//            已取消(筛选查询)：无按钮
//            已失效（126(筛选查询)：无按钮
//
//            管理员：只有查询权限 无按钮操作权限 不显示按钮

//            复核：
//            待售状态有 : 复核中(102)  审核中(201 202 203 204)  审核未通过(210 211)  待发布(103)  已取消(筛选查询 无需判断状态)   已失效(筛选查询)（126）
//            显示按钮：
//            复核中：(102) ----驳回、提交按钮
//            审核中(201 202 203 204)：无按钮
//            审核未通过(210 211)：驳回按钮 不弹框 提交
//            待发布(103)：发布、取消按钮
//            已取消(筛选查询)：无按钮
//            已失效（126）(筛选查询)： 无按钮

            //布局
            helper.setGone(R.id.selassert_baoli_item_ll1, false);//布局
            //再次发布
            helper.setGone(R.id.selassert_baoli_item_report, false);
            //复核ll
            helper.setGone(R.id.ll_fuhe, false);
            //驳回原因
            helper.setGone(R.id.tv_yuanyin, false);
            //删除
            helper.setGone(R.id.tv_delete, false);
            //编辑
            helper.setGone(R.id.tv_edit, false);
            //提交
            helper.setGone(R.id.tv_fuhe_commit1, false);
            //驳回
            helper.setGone(R.id.tv_baoli_bohui, false);
            //提交
            helper.setGone(R.id.tv_fuhe_commit2, false);
            //发布
            helper.setGone(R.id.tv_baoli_publish, false);
            //取消
            helper.setGone(R.id.tv_baoli_cancel, false);
            //再次发布
            helper.setGone(R.id.iv_new_add, false);

            if (!TextUtils.isEmpty(userType)) {
                //管理员
                if ("1".equals(userType)) {
                    if ("101".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                        helper.setTextColor(R.id.publish_item_me_issue, R.color.yello);
                        //显示图标
                        if (!TextUtils.isEmpty(trsTimes) && Integer.parseInt(trsTimes) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                    } else if ("101".equals(recheckState) && "0".equals(trsTimes)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                        //显示图标
                        if (!TextUtils.isEmpty(trsTimes) && Integer.parseInt(trsTimes) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                    } else if ("102".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("109".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                    } else if ("201".equals(recheckState) || "202".equals(recheckState) || "203".equals(recheckState) || "204".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenhezhong);
                        helper.setGone(R.id.tv_fuhe_commit1, false);//提交
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("210".equals(recheckState) || "211".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                    } else if ("103".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_daifabu);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                    }else if ("126".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_yishixiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }
                }//经办
                if ("2".equals(userType)) {
                    if ("101".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                        //按钮
                        helper.setVisible(R.id.ll_fuhe, true);

                        helper.setVisible(R.id.tv_edit, true);//编辑
//                        helper.setVisible(R.id.tv_yuanyin, true);//原因
                        helper.setVisible(R.id.tv_delete, true);//删除
                        helper.setVisible(R.id.tv_fuhe_commit1, true);//提交
                        //显示图标
                        if (!TextUtils.isEmpty(trsTimes) && Integer.parseInt(trsTimes) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                    } else if ("101".equals(recheckState) && "0".equals(trsTimes)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                        helper.setVisible(R.id.iv_sellsert_fufweiting, true);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                        helper.setVisible(R.id.ll_fuhe, true);
                        helper.setVisible(R.id.tv_edit, true);//编辑
//                        helper.setVisible(R.id.tv_yuanyin, true);//原因
                        helper.setVisible(R.id.tv_delete, true);//删除
                        helper.setVisible(R.id.tv_fuhe_commit1, true);//提交
                        //显示图标
                        if (!TextUtils.isEmpty(trsTimes) && Integer.parseInt(trsTimes) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                    } else if ("102".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("109".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                        helper.setVisible(R.id.ll_fuhe, true);
                        helper.setVisible(R.id.tv_yuanyin, true);//原因
                        helper.setVisible(R.id.tv_delete, true);//删除
                        helper.setVisible(R.id.tv_edit, true);//编辑
                        helper.setVisible(R.id.tv_fuhe_commit1, true);//提交
                    } else if ("201".equals(recheckState) || "202".equals(recheckState) || "203".equals(recheckState) || "204".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenhezhong);
                        helper.setGone(R.id.tv_fuhe_commit1, false);//提交
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("210".equals(recheckState) || "211".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                        //驳回原因
                        helper.setVisible(R.id.ll_fuhe, true);
                        helper.setVisible(R.id.tv_yuanyin, true);
                    } else if ("103".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_daifabu);
                        //产看邀约函
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                    }else if ("126".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_yishixiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }
                    //复核

                } else if ("3".equals(userType)) {
                    if ("102".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        helper.setVisible(R.id.tv_baoli_bohui, true);
                        helper.setVisible(R.id.tv_fuhe_commit2, true);
                    } else if ("201".equals(recheckState) || "202".equals(recheckState) || "203".equals(recheckState) || "204".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenhezhong);
                        helper.setGone(R.id.tv_fuhe_commit1, false);//提交
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("210".equals(recheckState) || "211".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                        //驳回
                        helper.setVisible(R.id.tv_baoli_bohui, true);
                        helper.setVisible(R.id.tv_delete, false);//占位 防止重叠
                        //提交
//                        helper.setVisible(R.id.tv_fuhe_commit2, true);
                        //驳回原因
                        helper.setVisible(R.id.ll_fuhe, true);
                        helper.setVisible(R.id.tv_yuanyin, true);
                    } else if ("103".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_daifabu);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                        helper.setVisible(R.id.tv_baoli_publish, true);
                        helper.setVisible(R.id.tv_baoli_cancel, true);
                    } else if ("126".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_yishixiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                        if("102".equals(recheckStateBefore)||"103".equals(recheckStateBefore)||"104".equals(recheckStateBefore)){
                            helper.setVisible(R.id.ll_fuhe, true);
                            helper.setGone(R.id.tv_yuanyin, false);//原因
                            helper.setGone(R.id.tv_delete, false);//删除
                            helper.setGone(R.id.tv_edit, true);//编辑
                            helper.setGone(R.id.tv_fuhe_commit1, false);//提交
                        }
                    } else if ("109".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                    }
                }

                if("0".equals(item.getYn())){
                    helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                    helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));

                    //布局
                    helper.setGone(R.id.selassert_baoli_item_ll1, false);//布局
                    //再次发布
                    helper.setGone(R.id.selassert_baoli_item_report, false);
                    //复核ll
                    helper.setGone(R.id.ll_fuhe, false);
                    //驳回原因
                    helper.setGone(R.id.tv_yuanyin, false);
                    //删除
                    helper.setGone(R.id.tv_delete, false);
                    //编辑
                    helper.setGone(R.id.tv_edit, false);
                    //提交
                    helper.setGone(R.id.tv_fuhe_commit1, false);
                    //驳回
                    helper.setGone(R.id.tv_baoli_bohui, false);
                    //提交
                    helper.setGone(R.id.tv_fuhe_commit2, false);
                    //发布
                    helper.setGone(R.id.tv_baoli_publish, false);
                    //取消
                    helper.setGone(R.id.tv_baoli_cancel, false);
                }
            }

            //驳回
            helper.addOnClickListener(R.id.tv_baoli_bohui);
            //经办提交
            helper.addOnClickListener(R.id.tv_fuhe_commit1);
            //提交
            helper.addOnClickListener(R.id.tv_fuhe_commit2);
            //取消
            helper.addOnClickListener(R.id.tv_baoli_cancel);
            //编辑
            helper.addOnClickListener(R.id.tv_edit);
            //删除
            helper.addOnClickListener(R.id.tv_delete);
            //发布
            helper.addOnClickListener(R.id.tv_baoli_publish);
            //取消
            helper.addOnClickListener(R.id.tv_baoli_cancel);
            //驳回原因
            helper.addOnClickListener(R.id.tv_yuanyin);

        }


    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
