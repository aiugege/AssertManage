package com.zjhl.pad.view.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.moudle.entity.res.HobbyBaoliRes;
import com.zjhl.pad.moudle.entity.res.MyHobbyRes;
import com.zjhl.pad.view.R;

import java.util.List;

/*
* File: HobbyBaoliAdapter.java 我的偏好———保理
* Author: DELL 
* Version: V1.0
* Create: 2018/6/4 17:55 
* Changes (from 2018/6/4) 
*/
public class HobbyBaoliAdapter extends BaseQuickAdapter<HobbyBaoliRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener{

    public HobbyBaoliAdapter(List<HobbyBaoliRes.DataBean> data) {
        super(R.layout.item_myhobby, data);

    }
    @Override
    protected void convert(BaseViewHolder helper, HobbyBaoliRes.DataBean item) {
//用户类型 （1；管理员；2：操作经办员；3：操作复核员）
        String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");
        if (item != null) {
            //
//
            //保理类型
            int factoringType = item.getFactoringType();
            helper.setText(R.id.offer_end_data, item.getMaturity() + "");
            helper.setText(R.id.offer_money, item.getAmount() + "");
            helper.setText(R.id.tv_bizhong, item.getCurrency() + "");
            helper.setText(R.id.tv_msg_vilidity, item.getIndateMessage() + "");
            helper.setText(R.id.tv_baoli_translate, item.getTransferRate());
            //国家
//            helper.setText(R.id.market_item_rl_text1, item.getCountryName() + "");
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
            }else if("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryNameEn());
            }
            if ("1".equals(userType)) {
                helper.setGone(R.id.market_item_ll_bottom_date,false);
            }else if ("2".equals(userType)) {
                helper.setGone(R.id.market_item_ll_bottom_date,true);
            }else if ("3".equals(userType)) {
                helper.setGone(R.id.market_item_ll_bottom_date,false);
            }
            helper.setText(R.id.market_item_text2_tv3,mContext.getResources().getString(R.string.filtrate_factoring_enddate));
            if (1==factoringType){
                // 1 单保理、明保理
                helper.setText(R.id.tv_title,mContext.getResources().getString(R.string.market_facotring_type1));
            }else if (2==factoringType){
                //单保理、暗保理
                helper.setText(R.id.tv_title,mContext.getResources().getString(R.string.market_facotring_type2));
            }else if (3==factoringType){
                helper.setText(R.id.tv_title,mContext.getResources().getString(R.string.market_facotring_type3));
            }else if (4==factoringType){
                helper.setText(R.id.tv_title,mContext.getResources().getString(R.string.market_facotring_type4));
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