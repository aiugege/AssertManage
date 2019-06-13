package com.zjhl.pad.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zjhl.pad.view.fragment.MarketFactoringFragment;
import com.zjhl.pad.view.fragment.MarketForfaitingFragment;
import com.zjhl.pad.view.fragment.SellAssetsFragment;
import com.zjhl.pad.view.fragment.SoldInAssetsFragment;

/*
* File: MinePropertyAdpter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/5/23 9:25 
* Changes (from 2018/5/23) 
*/
public class MinePropertyAdpter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MinePropertyAdpter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

//    MarketForfaitingFragment tab1 = new SellAssetsFragment();
//                return tab1;
//            case 1:
//
//    MarketFactoringFragment tab2 = new SoldInAssetsFragment();
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                //待售
                SellAssetsFragment tab1 = new SellAssetsFragment();
                return tab1;
            case 1:
                //在售
                SoldInAssetsFragment tab2 = new SoldInAssetsFragment();
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
