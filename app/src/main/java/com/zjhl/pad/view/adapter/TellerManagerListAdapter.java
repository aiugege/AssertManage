package com.zjhl.pad.view.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.view.R;

import java.util.List;

/*
* File: TellerManagerListAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/4 14:03 
* Changes (from 2018/5/4) 
*/
public class TellerManagerListAdapter extends BaseQuickAdapter<TellerManagementRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    public TellerManagerListAdapter(List<TellerManagementRes.DataBean> data) {
        super(R.layout.item_tellermanagement,data);

    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @Override
    protected void convert(BaseViewHolder helper, TellerManagementRes.DataBean item) {
        String usertype = item.getUserType() + "";
        if ("1".equals(usertype)){
            helper.setText(R.id.tv_tell_type, R.string.mine_teller_list_manager);
        }else if ("2".equals(usertype)){
            helper.setText(R.id.tv_tell_type, R.string.mine_teller_list_jingban);
        }else if ("3".equals(usertype)){
            helper.setText(R.id.tv_tell_type, R.string.mine_teller_list_fuhe);
        }
        helper.setText(R.id.tv_tell_name,item.getRealName()+"");
//        helper.setText(R.id.tv_tell_status,item.getLockState()+"");
        if ("2".equals(item.getLockState())){
            //锁定
            helper.setText(R.id.tv_tell_status, R.string.mine_teller_list_lock);
            helper.setTextColor(R.id.tv_tell_status, mContext.getResources().getColor(R.color.red));
        }else if ("1".equals(item.getLockState())){
            //正常
            helper.setText(R.id.tv_tell_status, R.string.mine_teller_list_nomal);
            helper.setTextColor(R.id.tv_tell_status, mContext.getResources().getColor(R.color.blue));
        }
        helper.addOnClickListener(R.id.tv_statue);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String id = getItem(position).getId();
        ToastUtils.showShort(id+"");

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MyLogger.pLog().d("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
        ToastUtils.showShort("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
    }
}
