package com.zjhl.pad.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.views.ZoomImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lzl on 2018/5/11.
 */
public class BigPicDialogActivity extends BaseActivity {


    ImageView iv_big;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big__pic_dialog);
         iv_big = (ImageView) findViewById(R.id.iv_big);

        String bigPiction = getIntent().getExtras().getString("bigPiction");
        if (!TextUtils.isEmpty(bigPiction)){
//            Glide.with(BigPicDialogActivity.this).load(bigPiction).asBitmap().into(iv_big);
            Glide.with(BigPicDialogActivity.this).load(bigPiction).into(iv_big);
        }

    }
}
