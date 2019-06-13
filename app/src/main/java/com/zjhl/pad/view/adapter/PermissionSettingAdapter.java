package com.zjhl.pad.view.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.view.R;

import java.util.List;

/*
* File: PermissionSettingAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/14 14:33 
* Changes (from 2018/5/14) 
*/
public class PermissionSettingAdapter extends BaseQuickAdapter<TellerManagementRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener{
        int id;
    public PermissionSettingAdapter(List<TellerManagementRes.DataBean> data) {
            super(R.layout.item_permissin_setting,data);

        }
        @Override
        protected void convert(BaseViewHolder helper, TellerManagementRes.DataBean item) {

            String userType = item.getUserType();
            helper.setText(R.id.tv_tell_name,item.getRealName()+"");
//            helper.setText(R.id.tv_tell_type,item.getUserType()+"");
            helper.setText(R.id.tv_data,item.getCreateTime());
            helper.setText(R.id.tv_qukuanlian,item.getCertificate());
            String lockState = item.getLockState();

            if (!TextUtils.isEmpty(userType)){
                    if ("1".equals(userType)){
                        helper.setText(R.id.tv_tell_type, R.string.mine_teller_list_manager);
                    }else if ("2".equals(userType)){
                        helper.setText(R.id.tv_tell_type,R.string.mine_teller_list_jingban);
                    }else if ("3".equals(userType)){
                        helper.setText(R.id.tv_tell_type,R.string.mine_teller_list_fuhe);
                }
            }
            if (!TextUtils.isEmpty(lockState)){
                if ("2".equals(lockState)){
                    //锁定
//                    helper.setBackgroundRes(R.id.bt_lock,R.drawable.enterprise_lock_write);
                    helper.setText(R.id.bt_lock, R.string.mine_permission_unlock);
                }else if ("1".equals(lockState)){
                    //正常
//                    helper.setBackgroundRes(R.id.bt_lock,R.drawable.enterprise_upload_small_write);
                    helper.setText(R.id.bt_lock, R.string.mine_permission_lock);
//                    helper.setTextColor(R.id.tv_tell_status,Color.blue(R.color.blue));
                }
            }
            helper.addOnClickListener(R.id.bt_lock);
            helper.addOnClickListener(R.id.wite_off);
        }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String id = getItem(position).getId();
        ToastUtils.showShort(id+"");
    }
}
