package com.zjhl.pad.view.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.moudle.entity.res.IntegralRes;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.view.R;

import java.util.List;

/*
* File: MyPointsAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/7 13:32 
* Changes (from 2018/5/7) 
*/
public class MyPointsAdapter extends BaseQuickAdapter<IntegralRes.DataBean.IntegralDetailListBean, BaseViewHolder> {

    public MyPointsAdapter(List<IntegralRes.DataBean.IntegralDetailListBean> data) {
        super(R.layout.item_jifen_list,data);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralRes.DataBean.IntegralDetailListBean item) {

        String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

        if (item!=null){
            //名称
            if("cn".equals(lanage)) {
                helper.setText(R.id.iv_register, item.getIntegralDesc());
            }else if("en".equals(lanage)) {
                helper.setText(R.id.iv_register, item.getIntegralDescEn());
            }

            //时间
            helper.setText(R.id.tv_regist_data, item.getIntegralDate()+"");


            if (item.getIntegralState()==1){
                helper.setTextColor(R.id.tv_money, mContext.getResources().getColor(R.color.blue));
                //积分金额
                helper.setText(R.id.tv_money,"+"+item.getIntegralValue()+"");
            }else if (item.getIntegralState()==2){
                helper.setTextColor(R.id.tv_money, mContext.getResources().getColor(R.color.red));
                helper.setText(R.id.tv_money,"-"+item.getIntegralValue()+"");
            }
        }

    }
}
