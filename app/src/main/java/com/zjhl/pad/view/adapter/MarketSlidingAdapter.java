package com.zjhl.pad.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zjhl.pad.view.fragment.ForfaitingBuyFragment;
import com.zjhl.pad.view.fragment.ForfaitingSellFragment;
import com.zjhl.pad.view.fragment.MarketFactoringFragment;
import com.zjhl.pad.view.fragment.MarketForfaitingFragment;


/**
 * @desc: FactoringIssuingAdapter
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.adapter
 * @author: pluto
 * @create: 2018/4/25 13:05
 * @projectname: nnkj
 **/


public class MarketSlidingAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MarketSlidingAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MarketForfaitingFragment tab1 = new MarketForfaitingFragment();
                return tab1;
            case 1:
                MarketFactoringFragment tab2 = new MarketFactoringFragment();
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

