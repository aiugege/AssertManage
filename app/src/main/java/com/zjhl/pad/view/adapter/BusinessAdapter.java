package com.zjhl.pad.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zjhl.pad.view.R;

import java.util.ArrayList;

/**
 * Created by 子龙 on 2017/4/17.
 */

public class BusinessAdapter extends BaseAdapter{
    private Context context;
    private ArrayList list;
    public BusinessAdapter(Context context, ArrayList list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
//            vh.textview = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textview.setText(list.get(position) + "");
        return convertView;
    }
    static class ViewHolder {
        TextView textview;
    }
}
