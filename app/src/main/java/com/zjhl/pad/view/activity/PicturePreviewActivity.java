package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: PicturePreviewActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/7/16 11:13
 * @projectname: nnkj
 **/
public class PicturePreviewActivity extends BaseActivity {
    @BindView(R.id.picture_preivew)
    ImageView picturePreivew;
    String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //不显示系统的标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picturepreview);
        ButterKnife.bind(this);

        path = getIntent().getStringExtra("path");
        if (!StringUtils.isEmpty(path)) {
            Glide.with(this).load(path).into(picturePreivew);
        }
    }

    @OnClick({R.id.picture_preivew})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.picture_preivew:
                finish();
                break;
            default:
                break;
        }
    }
}
