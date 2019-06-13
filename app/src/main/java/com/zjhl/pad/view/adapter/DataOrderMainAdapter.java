package com.zjhl.pad.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjhl.pad.view.R;

import java.util.List;
import java.util.Map;

/*
* File: DataOrderMainAdapter.java
* Author: DELL 
* Version: V1.0
* Create: 2018/5/9 11:17 
* Changes (from 2018/5/9) 
*/
public class DataOrderMainAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;
    private int position = 0;
    private boolean islodingimg = true;
    Holder hold;
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    public DataOrderMainAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    public DataOrderMainAdapter(Context context, List<Map<String, Object>> list,
                               boolean islodingimg) {
        this.context = context;
        this.list = list;
        this.islodingimg = islodingimg;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int arg0, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.item_order_mainlist, null);
            hold = new Holder(view);
            view.setTag(hold);
        } else {
            hold = (Holder) view.getTag();
        }
//		if (islodingimg == true) {
//			hold.img.setImageResource(Integer.parseInt(list.get(arg0)
//					.get("img").toString()));
//		}
        hold.txt.setText(list.get(arg0).get("txt").toString());
        hold.layout.setBackgroundColor(0xFFFFFFFF);
        if (arg0 == position) {
            hold.layout.setBackgroundColor(0xFFEBEBEB);
            hold.txt.setTextColor(context.getResources().getColor(R.color.blue));
        }else{
            hold.txt.setTextColor(context.getResources().getColor(R.color.dark));
        }
        return view;
    }

    public void setSelectItem(int position) {
        this.position = position;
    }

    public int getSelectItem() {
        return position;
    }

    private static class Holder {
        LinearLayout layout;
        ImageView img;
        TextView txt;

        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.mainitem_txt);
//			img = (ImageView) view.findViewById(R.id.mainitem_img);
            layout = (LinearLayout) view.findViewById(R.id.mainitem_layout);
        }
    }
}
