package com.zjhl.pad.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zjhl.pad.view.fragment.FactoringSellFragment;
import com.zjhl.pad.view.fragment.ForfaitingBuyFragment;
import com.zjhl.pad.view.fragment.ForfaitingSellFragment;


/**
 * @desc: FactoringIssuingAdapter
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.adapter
 * @author: pluto
 * @create: 2018/4/25 13:05
 * @projectname: nnkj
 **/


public class ForfaitingIssuingPagerSlidingAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ForfaitingIssuingPagerSlidingAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ForfaitingSellFragment tab1 = new ForfaitingSellFragment();
                return tab1;
            case 1:
                //福费廷求购  偏好录入
//                ForfaitingBuyFragment tab2 = new ForfaitingBuyFragment();
                FactoringSellFragment tab2 = new FactoringSellFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

