package com.zjhl.pad.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zjhl.pad.view.fragment.TellerMaintenanceFragment;
import com.zjhl.pad.view.fragment.TellerManagementAddFragment;
import com.zjhl.pad.view.fragment.TellerManagementListFragment;

/*
* File: TellerManageListAdapter.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/6/13 11:45 
* Changes (from 2018/6/13) 
*/
public class TellerManageListAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TellerManageListAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                //柜员列表
                TellerManagementListFragment tab1 = new TellerManagementListFragment();
                return tab1;
            case 1:
                //柜员添加
                TellerManagementAddFragment tab2 = new TellerManagementAddFragment();
                return tab2;
            case 2:
                //权限设置
                TellerMaintenanceFragment tab3 = new TellerMaintenanceFragment();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
