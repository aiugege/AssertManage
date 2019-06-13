package com.zjhl.pad.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zjhl.pad.view.fragment.FactoringBuyFragment;
import com.zjhl.pad.view.fragment.FactoringSellFragment;
import com.zjhl.pad.view.fragment.ForfaitingBuyFragment;


/**
 * @desc: FactoringIssuingAdapter
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.adapter
 * @author: pluto
 * @create: 2018/4/25 13:05
 * @projectname: nnkj
 **/


public class FactoringIssuingPagerSlidingAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public FactoringIssuingPagerSlidingAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                //保理出售录入
//                FactoringSellFragment tab1 = new FactoringSellFragment();
                ForfaitingBuyFragment tab1 = new ForfaitingBuyFragment();
                return tab1;
            case 1:
                FactoringBuyFragment tab2 = new FactoringBuyFragment();
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

