package com.zjhl.pad.view.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.view.R;


/*
* File: ClassifyMorAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/9 11:37 
* Changes (from 2018/5/9) 
*/
public class ClassifyMorAdapter extends BaseAdapter {
    private Context context;
    private String[] text_list;
    private int position = 0;
    Holder hold;

    public ClassifyMorAdapter(Context context, String[] text_list) {
        this.context = context;
        this.text_list = text_list;
    }

    public int getCount() {
        return text_list.length;
    }

    public Object getItem(int position) {
        return text_list[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int arg0, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.item_classify_morelist, null);
            hold = new Holder(view);
            view.setTag(hold);
        } else {
            hold = (Holder) view.getTag();
        }
        hold.txt.setText(text_list[arg0]);
        hold.txt.setTextColor(0xFF666666);
        if (arg0 == position) {
            hold.txt.setTextColor(context.getResources().getColor(R.color.blue));
            hold.moreitem_iv_duigou.setVisibility(View.VISIBLE);
            hold.moreitem_iv_duigou.setImageResource(R.drawable.duigou_select);
        }else {
            hold.moreitem_iv_duigou.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    public void setSelectItem(int position) {
        this.position = position;
    }

    private static class Holder {
        TextView txt;
        ImageView moreitem_iv_duigou;
        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.moreitem_txt);
            moreitem_iv_duigou = (ImageView) view.findViewById(R.id.moreitem_iv_duigou);
        }
    }
}
