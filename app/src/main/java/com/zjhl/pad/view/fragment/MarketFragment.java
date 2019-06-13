package com.zjhl.pad.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjhl.pad.view.R;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.view.adapter.FactoringIssuingPagerSlidingAdapter;
import com.zjhl.pad.view.adapter.MarketSlidingAdapter;
import com.zjhl.pad.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 * File: MarketFragment.java 市场
 * Author: 刘子龙
 * Version: V100R001C01
 * Create: 2018/4/2 19:27
 * Changes (from 2018/4/2)
 */
public class MarketFragment extends BaseFragment {


    @BindView(R.id.market_iv_property_icon)
    ImageView marketIvPropertyIcon;
    @BindView(R.id.home_tv_dealanount)
    ImageView homeTvDealanount;
    @BindView(R.id.market_property_rl)
    RelativeLayout marketPropertyRl;
    @BindView(R.id.market_tab_layout)
    TabLayout marketTabLayout;
    @BindView(R.id.market_vp)
    ViewPager marketVp;
    Unbinder unbinder;

    private MarketSlidingAdapter adapter = null;
    @Override
    protected View initView() {
        MyLogger.pLog().e("常用框架Fragment页面被初始化了...");
        View view = View.inflate(mContext, R.layout.fragment_market, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        MyLogger.pLog().e("常用框架Fragment数据被初始化了...");
        marketTabLayout.addTab(marketTabLayout.newTab().setText(R.string.lable_forfaiting));
        marketTabLayout.addTab(marketTabLayout.newTab().setText(R.string.lable_facotring));
        marketTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
//        marketTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(marketTabLayout, 110, 110);
//            }
//        });
        adapter = new MarketSlidingAdapter(getActivity().getSupportFragmentManager(), marketTabLayout.getTabCount());
        marketVp.setAdapter(adapter);
//        marketTabLayout.setupWithViewPager(factoringIssuingVp);
        marketVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(marketTabLayout));
        marketTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                marketVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.market_property_rl, R.id.market_tab_layout, R.id.market_vp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.market_property_rl:
                break;
            case R.id.market_tab_layout:
                break;
            case R.id.market_vp:
                break;
        }
    }
}
