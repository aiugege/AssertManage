package com.zjhl.pad.view.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.moudle.entity.res.MyHobbyRes;
import com.zjhl.pad.moudle.entity.res.MyOfferBaoliRs;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.view.R;

import java.util.List;

/*
 * File: MyHobbyOfferAdapter.java 我的偏好————福費廷
 * Author: DELL
 * Version: V1.0
 * Create: 2018/6/3 15:57
 * Changes (from 2018/6/3)
 */
public class MyHobbyOfferAdapter extends BaseQuickAdapter<MyHobbyRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    public MyHobbyOfferAdapter(List<MyHobbyRes.DataBean> data) {
        super(R.layout.item_myhobby, data);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    protected void convert(BaseViewHolder helper, MyHobbyRes.DataBean item) {
//用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        if (item != null) {
            //
            helper.setText(R.id.offer_end_data, item.getDeadLine() + "");
            helper.setText(R.id.offer_money, item.getAmount() + "");
            helper.setText(R.id.tv_bizhong, item.getCurrency() + "");
            helper.setText(R.id.tv_msg_vilidity, item.getIndateMessage() + "");
            helper.setText(R.id.tv_tiexianlv, MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_detail_discount_rate));
            helper.setText(R.id.tv_baoli_translate, item.getDiscountRate());
            helper.setText(R.id.market_item_text3_tv3, MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_adapter_currency));
            String countryname = item.getCountryName();
            if (!StringUtils.isEmpty(countryname) && !"null".equals(countryname)) {
                //国家
//                helper.setText(R.id.market_item_rl_text1, item.getCountryName() + "");
                String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
                if ("cn".equals(lanage)) {
                    helper.setText(R.id.market_item_rl_text1, item.getCountryName());
                } else if ("en".equals(lanage)) {
                    helper.setText(R.id.market_item_rl_text1, item.getCountryNameEn());
                }
            }
            if ("1".equals(userType)) {
                helper.setGone(R.id.market_item_ll_bottom_date,false);
            }else if ("2".equals(userType)) {
                helper.setGone(R.id.market_item_ll_bottom_date,true);
            }else if ("3".equals(userType)) {
                helper.setGone(R.id.market_item_ll_bottom_date,false);
            }
            String debtType = item.getDebtType();
            //信用证类型
//            String creditType = item.getCreditType();

            if (!TextUtils.isEmpty(debtType)) {
                //国内福费廷
                if (debtType.equals("1")) {
                    helper.setText(R.id.tv_title, mContext.getResources().getString(R.string.market_forfaiting_type1));

                } else if (debtType.equals("2")) {
                    helper.setText(R.id.tv_title, mContext.getResources().getString(R.string.market_forfaiting_type2));
                }
            }
            //删除
            helper.addOnClickListener(R.id.tv_delete);
            //编辑
            helper.addOnClickListener(R.id.tv_edit);
        }

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
