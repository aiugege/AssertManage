package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.app.utils.Utils;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.ForfaitingIssuingPagerSlidingAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: FactoringIssuingActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/4/25 11:51 发布福费廷 保理
 * @projectname: nnkj
 **/
public class ForfaitingIssuingActivity extends BaseActivity {

    @BindView(R.id.factoring_issuing_vp)
    ViewPager factoringIssuingVp;
    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.release_factoring_toolbar)
    Toolbar releaseFactoringToolbar;
    @BindView(R.id.release_factoring_tab_layout)
    TabLayout releaseFactoringTabLayout;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    int postion = 0;//0福费廷1 保理
    @BindView(R.id.iv_Rtv1)
    TextView ivRtv1;
    private ForfaitingIssuingPagerSlidingAdapter adapter = null;
    //数据源
    private List<View> viewList = null;

    //当编辑时传过来的资产id有值  保存和提交用更新接口
    private String assestId = "";
    //当编辑时传过来的值  1 福费廷 2 保理
    private String assestType = "";
    String forfaiting = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_FORFAITING, "0").toString();
    String factoring = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_FACTORING, "0").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoringissuing);
        ButterKnife.bind(this);
        tvContent.setText(getString(R.string.issue_forfaiting_sell));
        assestId = getIntent().getStringExtra("assestId");
        assestType = getIntent().getStringExtra("assestType");
        registerAction(Constants.BROADCAST_ACTION.SEND_VISABLE_BUTTON_ACTION);
        registerAction(Constants.BROADCAST_ACTION.SEND_GONE_BUTTON_ACTION);
//        releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText("出售录入"));
//        releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText("求购录入"));

        if ("1".equals(forfaiting)) {
            releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText(getString(R.string.lable_forfaiting)));
        }
        if ("1".equals(factoring)) {
            releaseFactoringTabLayout.addTab(releaseFactoringTabLayout.newTab().setText(getString(R.string.lable_facotring)));
        }
        Utils.reflex(releaseFactoringTabLayout);
        ivRtv.setVisibility(View.VISIBLE);
//        releaseFactoringTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(releaseFactoringTabLayout, 110, 110);
//            }
//        });
        adapter = new ForfaitingIssuingPagerSlidingAdapter(getSupportFragmentManager(), releaseFactoringTabLayout.getTabCount());
        factoringIssuingVp.setAdapter(adapter);
//        releaseFactoringTabLayout.setupWithViewPager(factoringIssuingVp);
        factoringIssuingVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(releaseFactoringTabLayout));
        releaseFactoringTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                postion = tab.getPosition();
                if (postion == 0) {
                        factoringIssuingVp.setCurrentItem(tab.getPosition());
                        //福费廷
                        ivRtv1.setVisibility(View.GONE);
                        ivRtv.setVisibility(View.VISIBLE);
                } else if (postion == 1) {
                        factoringIssuingVp.setCurrentItem(tab.getPosition());
                        //保理
                        ivRtv.setVisibility(View.GONE);
                        ivRtv1.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if ("1".equals(assestType)) {
            factoringIssuingVp.setCurrentItem(0);
        } else if ("2".equals(assestType)) {
            factoringIssuingVp.setCurrentItem(1);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterAction(Constants.BROADCAST_ACTION.SEND_VISABLE_BUTTON_ACTION);
        unregisterAction(Constants.BROADCAST_ACTION.SEND_GONE_BUTTON_ACTION);
    }

    @OnClick({R.id.tv_content, R.id.iv_Rtv, R.id.iv_excite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.tv_content:
                break;
//            case R.id.iv_Rtv:
//                if (postion == 0) {
//                    adapter.getItem(postion).
//                } else if (postion == 1) {
//
//                }
//                break;
        }
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
//        if (Constants.BROADCAST_ACTION.SEND_VISABLE_BUTTON_ACTION.equals(intent.getAction())) {
//            ivRtv.setText("保存");
//            ivRtv.setVisibility(View.VISIBLE);
//        } else {
////            ivRtv.setText("保存");
//            ivRtv.setVisibility(View.GONE);
//        }
    }


    public String getAssestId() {
        return assestId;
    }

    public void setAssestId(String assestId) {
        this.assestId = assestId;
    }

    public String getAssestType() {
        return assestType;
    }

    public void setAssestType(String assestType) {
        this.assestType = assestType;
    }
}
