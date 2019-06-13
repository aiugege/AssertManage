package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.ForfaitingIssuingPagerSlidingAdapter;
import com.zjhl.pad.view.adapter.TellerManagementAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* File: TellerManagementActivity.java 柜员管理
* Author: DELL
* Version: V1.0
* Create: 2018/4/27 10:29
* Changes (from 2018/4/27)
*/
public class TellerManagementActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.release_factoring_tab_layout)
    TabLayout releaseFactoringTabLayout;
    @BindView(R.id.factoring_issuing_vp)
    ViewPager factoringIssuingVp;
    private TellerManagementAdapter adapter = null;
    private int sTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teller_management);
        ButterKnife.bind(this);
        tvContent.setText(R.string.mine_guiyuan_manager);
        registerAction(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE);

            releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText(R.string.mine_guiyuan_list));
            releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText(R.string.mine_guiyuan_add));
            releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText(R.string.mine_jurisdiction_setting));

//        releaseFactoringTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(releaseFactoringTabLayout, 40, 40);
//            }
//        });
        adapter = new TellerManagementAdapter(getSupportFragmentManager(), releaseFactoringTabLayout.getTabCount());
        factoringIssuingVp.setAdapter(adapter);
//        releaseFactoringTabLayout.setupWithViewPager(factoringIssuingVp);
        factoringIssuingVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(releaseFactoringTabLayout));
        releaseFactoringTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                factoringIssuingVp.setCurrentItem(tab.getPosition());
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
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        if(intent.getAction().equals(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE)){
            factoringIssuingVp.setCurrentItem(0);
//            adapter.getItem(0).onStart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE);
    }

    @OnClick(R.id.iv_excite)
    public void onViewClicked() {
        finish();
    }
}
