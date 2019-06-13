package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjhl.pad.app.utils.Utils;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.MinePropertyAdpter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
* File: PropertyActivity.java 我的资产
* Author: DELL 
* Version: V1.0
* Create: 2018/5/19 17:46 
* Changes (from 2018/5/19) 
*/
public class PropertyActivity extends BaseActivity {


    @BindView(R.id.home_tv_dealanount)
    ImageView homeTvDealanount;
    @BindView(R.id.market_property_rl)
    RelativeLayout marketPropertyRl;
    @BindView(R.id.mine_property_tab_layout)
    TabLayout minePropertyTabLayout;
    @BindView(R.id.mine_property_vp)
    ViewPager minePropertyVp;
    Unbinder unbinder;
    @BindView(R.id.iv_property)
    ImageView ivProperty;


    private MinePropertyAdpter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_property);
        unbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        minePropertyTabLayout.addTab(minePropertyTabLayout.newTab().setText(R.string.sellassets_title));
        minePropertyTabLayout.addTab(minePropertyTabLayout.newTab().setText(R.string.soidinassets_title));
        minePropertyTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        Utils.reflex(minePropertyTabLayout);
//        minePropertyTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(minePropertyTabLayout, 20, 20);
//            }
//        });
        adapter = new MinePropertyAdpter(PropertyActivity.this.getSupportFragmentManager(), minePropertyTabLayout.getTabCount());
        minePropertyVp.setAdapter(adapter);
//        marketTabLayout.setupWithViewPager(factoringIssuingVp);
        minePropertyVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(minePropertyTabLayout));
        minePropertyTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                minePropertyVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @OnClick({R.id.market_property_rl, R.id.mine_property_tab_layout, R.id.mine_property_vp, R.id.iv_property})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.market_property_rl:
                break;
            case R.id.mine_property_tab_layout:
                break;
            case R.id.mine_property_vp:
                break;
            case R.id.iv_property:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
