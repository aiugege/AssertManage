package com.zjhl.pad.view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.res.AsellasetsBaoliRes;
import com.zjhl.pad.moudle.entity.res.AsellasetsFuFeiTingRes;
import com.zjhl.pad.view.R;

import java.util.List;

/*
 * File: SellAssetsBaoliAdapter.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/5/24 10:45
 * Changes (from 2018/5/24)
 */
public class SellAssetsBaoliAdapter extends BaseQuickAdapter<AsellasetsBaoliRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    public SellAssetsBaoliAdapter(List<AsellasetsBaoliRes.DataBean> data) {
        super(R.layout.sellassert_fufeiting_item, data);

    }

    public SellAssetsBaoliAdapter(int layoutResId, @Nullable List<AsellasetsBaoliRes.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, AsellasetsBaoliRes.DataBean item) {
        //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        if (item != null) {
            //保理编号
            helper.setText(R.id.offer_item_text_number, item.getFactoringNo() + "");
            //资产卖出方
            helper.setText(R.id.offer_baoli_sale_banck1, item.getSeller() + "");
            //保理商名称
            helper.setText(R.id.tv_baoli_name, item.getFactoringName() + "");
            //保理到期日
            helper.setText(R.id.offer_end_baoli, item.getMaturity() + "");
            //金额
            helper.setText(R.id.offer_baoli_money, item.getAmount() + "");
            //转让利率
            helper.setText(R.id.tv_baoli_translate, StringUtils.doubleFormate(item.getTransferRate()));
            //币种
            helper.setText(R.id.tv_bizhong_baoli, item.getCurrency());
            //信息有效期
            helper.setText(R.id.tv_msg_vilidity, item.getIndateMessage() + "");
            //国家
//            helper.setText(R.id.market_item_rl_text1, item.getCountryName() + "");
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if ("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
                helper.setImageResource(R.id.iv_new_add, R.drawable.new_add_asserts);
            } else if ("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getEnCountryName());
                helper.setImageResource(R.id.iv_new_add, R.drawable.new_add_asserts_en);
            }

            //1,//保理类型 1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理
            if (1 == (item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type1);
            } else if (2 == (item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type2);
            } else if (3 == (item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type3);
            } else if (4 == (item.getFactoringType())) {
                helper.setVisible(R.id.tv_danbaoli, true);
                helper.setText(R.id.tv_danbaoli, R.string.market_facotring_type4);
            }
            //审核状态
            String recheckState = item.getCheckState() + "";
            String checkStateBefore = item.getCheckStateBefore() + "";
            //交易次数
            String trsNo = item.getTrsNo();

//            helper.setGone(R.id.iv_new_add, false);
            helper.setGone(R.id.ll_commit_weitongguo, false);
            helper.setGone(R.id.ll_fabu_quxiao, false);
            helper.setGone(R.id.tv_fuhe_fabu, false);
            helper.setGone(R.id.tv_cancel, false);
            helper.setGone(R.id.tv_bohui_reason, false);

            helper.setGone(R.id.ll_commit_bohui, false);
            //驳回
            helper.setGone(R.id.tv_fuhe_bohui, false);
            //提交
            helper.setGone(R.id.tv_fuhe_commit, false);
            //驳回原因
            helper.setGone(R.id.bt_bohuireson, false);
            //再次发布
            helper.setGone(R.id.iv_new_add, false);
            if (!TextUtils.isEmpty(userType)) {
                //管理员
                if ("1".equals(userType)) {
                    if ("101".equals(recheckState)) {
                        //删除 编辑 提交
                        helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                        if (!TextUtils.isEmpty(trsNo) && Integer.parseInt(trsNo) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                    } else if ("102".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("109".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));
                        if (!TextUtils.isEmpty(trsNo) && Integer.parseInt(trsNo) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                    } else if ("201".equals(recheckState) || "202".equals(recheckState) || "203".equals(recheckState) || "204".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("210".equals(recheckState) || "211".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));

                    } else if ("103".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_daifabu);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                    } else if ("126".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_yishixiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }
                }//经办
                if ("2".equals(userType)) {
                    if ("101".equals(recheckState)) {
                        //删除 编辑 提交
                        helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_daitijiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
//                        //按钮
//                        helper.setVisible(R.id.ll_commit_weitongguo, true);
//                        //驳回原因
//                        helper.setVisible(R.id.tv_fuhe_fabu, false);
//                        //显示图标
//                        if (!TextUtils.isEmpty(trsNo) && Integer.parseInt(trsNo)>0){
//                            helper.setVisible(R.id.iv_new_add,true);
//                        }
//                        helper.setVisible(R.id.iv_new_add,true);

                        helper.setVisible(R.id.ll_commit_weitongguo, true);
                        //删除
                        helper.setVisible(R.id.tv_delete, true);
                        //编辑
                        helper.setVisible(R.id.tv_fuhe_edit, true);
                        //提交
                        if (!TextUtils.isEmpty(trsNo) && Integer.parseInt(trsNo) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                        helper.setVisible(R.id.tv_fuhe_commit1, true);

                    } else if ("102".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("109".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));

                        helper.setVisible(R.id.ll_commit_weitongguo, true);
                        //删除
                        helper.setVisible(R.id.tv_delete, true);
                        //编辑
                        helper.setVisible(R.id.tv_fuhe_edit, true);
                        //提交
                        if (!TextUtils.isEmpty(trsNo) && Integer.parseInt(trsNo) > 0) {
                            helper.setGone(R.id.iv_new_add, true);
                        }
                        helper.setVisible(R.id.tv_fuhe_commit1, true);

                        //驳回原因
                        helper.setVisible(R.id.tv_bohui_reason, true);
                        //驳回原因
                        helper.setGone(R.id.ll_commit_bohui, false);
                        helper.setGone(R.id.bt_bohuireson, false);
                    } else if ("201".equals(recheckState) || "202".equals(recheckState) || "203".equals(recheckState) || "204".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    } else if ("210".equals(recheckState) || "211".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));

                    } else if ("103".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_daifabu);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                    } else if ("126".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_yishixiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
                    }
                    //复核

                } else if ("3".equals(userType)) {
                    if ("102".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_fuhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, R.color.home_gray_text);

                        helper.setVisible(R.id.ll_commit_bohui, true);
                        //驳回
                        helper.setVisible(R.id.tv_fuhe_bohui, true);
                        //提交
                        helper.setVisible(R.id.tv_fuhe_commit, true);
                        //驳回原因
                        helper.setGone(R.id.bt_bohuireson, false);
                    } else if ("201".equals(recheckState) || "202".equals(recheckState) || "203".equals(recheckState) || "204".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenhezhong);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));


                    } else if ("210".equals(recheckState) || "211".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_shenheweitongguo);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.red));

                        helper.setVisible(R.id.ll_commit_bohui, true);
                        //驳回
                        helper.setVisible(R.id.tv_fuhe_bohui, true);
                        //提交
//                        helper.setVisible(R.id.tv_fuhe_commit, true);
                        //提交隐藏
                        helper.setGone(R.id.tv_fuhe_commit, false);
                        //驳回原因
                        helper.setVisible(R.id.bt_bohuireson, true);
                    } else if ("103".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_daifabu);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.yello));
                        helper.setVisible(R.id.ll_fabu_quxiao, true);
                        helper.setVisible(R.id.tv_fuhe_fabu, true);
                        helper.setVisible(R.id.tv_cancel, true);
                    } else if ("126".equals(recheckState)) {
                        helper.setText(R.id.publish_item_me_issue, R.string.sellassert_forfaiting_adapter_yishixiao);
                        helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));

                        if ("102".equals(checkStateBefore)||"103".equals(checkStateBefore)||"104".equals(checkStateBefore)) {
                            helper.setVisible(R.id.ll_commit_weitongguo, true);
                            //删除
                            helper.setGone(R.id.tv_delete, false);
                            //编辑
                            helper.setGone(R.id.tv_fuhe_edit, true);
                            //提交
                            helper.setGone(R.id.iv_new_add, false);
                            helper.setGone(R.id.tv_fuhe_commit1, false);
                        }
                    }
                }

                if ("0".equals(item.getYn())) {
                    helper.setText(R.id.publish_item_me_issue, R.string.onsalelist_forfaiting_adapter_yiquxiao);
                    helper.setTextColor(R.id.publish_item_me_issue, mContext.getResources().getColor(R.color.home_gray_text));
//                    helper.setGone(R.id.iv_new_add, false);
                    helper.setGone(R.id.ll_commit_weitongguo, false);
                    helper.setGone(R.id.ll_fabu_quxiao, false);
                    helper.setGone(R.id.tv_fuhe_fabu, false);
                    helper.setGone(R.id.tv_cancel, false);
                    helper.setGone(R.id.tv_bohui_reason, false);

                    helper.setGone(R.id.ll_commit_bohui, false);
                    //驳回
                    helper.setGone(R.id.tv_fuhe_bohui, false);
                    //提交
                    helper.setGone(R.id.tv_fuhe_commit, false);
                    //驳回原因
                    helper.setGone(R.id.bt_bohuireson, false);
                }
            }

            //驳回
            helper.addOnClickListener(R.id.tv_fuhe_bohui);
            //提交
            helper.addOnClickListener(R.id.tv_fuhe_commit);
            //提交
            helper.addOnClickListener(R.id.tv_fuhe_commit1);
            //取消
            helper.addOnClickListener(R.id.tv_fuhe_cansal);
            //编辑
            helper.addOnClickListener(R.id.tv_edit);
            //删除
            helper.addOnClickListener(R.id.tv_delete);
            //查看驳回原因
            helper.addOnClickListener(R.id.tv_bohui_reason);
            //查看驳回原因
            helper.addOnClickListener(R.id.bt_bohuireson);
            //发布-复核
            helper.addOnClickListener(R.id.tv_fuhe_fabu);
            //取消-复核
            helper.addOnClickListener(R.id.tv_cancel);
            //编辑
            helper.addOnClickListener(R.id.tv_fuhe_edit);


        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtils.showShort("000"+position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtils.showShort("111"+position);
    }
}
