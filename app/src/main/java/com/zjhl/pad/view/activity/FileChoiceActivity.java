package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: IssueActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/4/26 16:06
 * @projectname: nnkj
 **/
public class FileChoiceActivity extends BaseActivity {


    @BindView(R.id.activity_file_choice_title)
    TextView activityFileChoiceTitle;
    @BindView(R.id.activity_file_choice_upload)
    TextView activityFileChoiceUpload;
    @BindView(R.id.activity_file_choice_photo)
    TextView activityFileChoicePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_choice);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.activity_file_choice_title, R.id.activity_file_choice_upload, R.id.activity_file_choice_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_file_choice_title:
                break;
            case R.id.activity_file_choice_upload:

                break;
            case R.id.activity_file_choice_photo:

                break;
        }
    }
}
