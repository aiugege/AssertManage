package com.zjhl.pad.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
/**
 * @desc: FactoringIssuingAdapter
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.adapter
 * @author: pluto
 * @create: 2018/4/25 13:05
 * @projectname: nnkj
 **/


public class FactoringIssuingAdapter extends PagerAdapter{

    private List<View> viewList = null;

    public FactoringIssuingAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    // 实例化一个页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    // 销毁一个页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

