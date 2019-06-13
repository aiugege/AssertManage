package com.zjhl.pad.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zjhl.pad.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * | 
 * | 功能描述:
 * | 时　　间: 2018/5/3 10:24
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class CommPopAdapter extends BaseAdapter {
    /**
     * 筛选条件数据
     */
    private List<String> mDatas = new ArrayList<>();
    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    public CommPopAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.common_popup_list_item, null);
            viewHolder.mTitleTv = (TextView) convertView.findViewById(R.id.tv_common_listpop_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTitleTv.setText(mDatas.get(i));
        return convertView;
    }

    /**
     * vh
     */
    public class ViewHolder {
        /**
         * 筛选项文字tv
         */
        TextView mTitleTv;
    }

}
