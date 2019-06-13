package com.zjhl.pad.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @desc: PdfActivity
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.activity
 * @author: pluto
 * @create: 2018/6/12 17:47
 * @projectname: nnkj
 **/
public class PdfActivity extends BaseActivity {
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.tv_crreid)
    TextView tvCrreid;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    @BindView(R.id.iv_Rtv1)
    TextView ivRtv1;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_id_number)
    TextView tvIdNumber;
    String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);
        tvContent.setText(R.string.pdf_letter_title);
        path = getIntent().getStringExtra("path");
        if (!StringUtils.isEmpty(path)) {
            File file = new File(path);
            Uri uri = Uri.fromFile(file);
            pdfView.fromUri(uri).defaultPage(0)
                    .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                    .load();
        }
    }

    @OnClick({R.id.iv_excite, R.id.tv_content, R.id.iv_Ricon, R.id.tv_crreid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.tv_content:
                break;
            case R.id.iv_Ricon:
                break;
            case R.id.tv_crreid:
                break;
        }
    }
}
