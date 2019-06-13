package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/*
* File: BusinessNewsActivity.java 业务消息
* Author: DELL 
* Version: V1.0
* Create: 2018/4/23 15:57 
* Changes (from 2018/4/23) 
*/
public class BusinessNewsActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.business_listview)
    ListView businessListview;
    private List<Message> lists;
//    private  ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        lists = new ArrayList<>();
        lists.add(new Message());

    }


    private void initView() {
        tvContent.setText(R.string.mine_message);
    }

    @OnClick({R.id.iv_excite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                //返回上一级
                finish();
                break;

        }
    }

    @OnItemClick(R.id.business_listview)
    public void onItemClick(int position){
        Toast.makeText(getBaseContext(), "item"+position, Toast.LENGTH_SHORT).show();

    }



}
