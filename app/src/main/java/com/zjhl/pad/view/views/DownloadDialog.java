package com.zjhl.pad.view.views;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.view.R;

import java.util.ArrayList;
import java.util.List;

/*
* File: DownloadDialog
* Author: DELL 
* Version: V1.0
* Create: 2018/9/5 17:56 
* Changes (from 2018/9/5) 
*/
public class DownloadDialog extends BaseDialog {
    private TextView mTvDownload;//下载
    private TextView mTvTitle;//标题
    private ImageView mIvClose;//关闭
    private RelativeLayout ll_check_transfer_letter1,ll_check_transfer_letter2,
            ll_check_transfer_letter3,ll_check_transfer_letter4,
            ll_check_transfer_letter5,ll_check_transfer_letter6;
    private ImageView iv_check_transfer_letter1,iv_check_transfer_letter2,iv_check_transfer_letter3,
            iv_check_transfer_letter4,iv_check_transfer_letter5,iv_check_transfer_letter6;//文件图片

    public DownloadDialog(Context context) {
        super(context);
    }

    public DownloadDialog(Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }

    /**
     * @param context              上下文
     * @param onBaseDialogListener 回调监听
     * @param downloadName           自定义下载名字
     * @param title                自定义title
     */
    public DownloadDialog(Context context, OnBaseDialogListener onBaseDialogListener,
                              String title, String downloadName, String data) {
        super(context, onBaseDialogListener);
        setDownloadName(downloadName);
        setTitleName(title);
        showImage(data);
    }
    //设置对话框的样式

    @Override
    protected int getDialogStyleId() {
        return R.style.BaseDialog;
    }
    //继承于BaseDialog的方法，设置布局用的，这样对话框张啥样久随心所欲啦

    @Override
    protected View getView() {  // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_download, null);   //得到各种
        mTvDownload = (TextView) view.findViewById(R.id.dialog_tv_download);
        mTvTitle = (TextView) view.findViewById(R.id.dialog_offer_detail_title_baojia);
        mIvClose = (ImageView) view.findViewById(R.id.dialog_offer_detail_iv_close);
        ll_check_transfer_letter1 = (RelativeLayout) view.findViewById(R.id.ll_check_transfer_letter1);
        ll_check_transfer_letter2 = (RelativeLayout) view.findViewById(R.id.ll_check_transfer_letter2);
        ll_check_transfer_letter3 = (RelativeLayout) view.findViewById(R.id.ll_check_transfer_letter3);
        ll_check_transfer_letter4 = (RelativeLayout) view.findViewById(R.id.ll_check_transfer_letter4);
        ll_check_transfer_letter5 = (RelativeLayout) view.findViewById(R.id.ll_check_transfer_letter5);
        ll_check_transfer_letter6 = (RelativeLayout) view.findViewById(R.id.ll_check_transfer_letter6);
        iv_check_transfer_letter1 = (ImageView) view.findViewById(R.id.iv_check_transfer_letter1);
        iv_check_transfer_letter2 = (ImageView) view.findViewById(R.id.iv_check_transfer_letter2);
        iv_check_transfer_letter3 = (ImageView) view.findViewById(R.id.iv_check_transfer_letter3);
        iv_check_transfer_letter4 = (ImageView) view.findViewById(R.id.iv_check_transfer_letter4);
        iv_check_transfer_letter5 = (ImageView) view.findViewById(R.id.iv_check_transfer_letter5);
        iv_check_transfer_letter6 = (ImageView) view.findViewById(R.id.iv_check_transfer_letter6);
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if (baseDialogListener != null) {
            mTvDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialogListener.negative("","");
                    dismiss();
                }
            });

        }


        return view;
    }

    /**
     * 展示图片
     * @param data
     */
    private void showImage(String data) {
        if (!TextUtils.isEmpty(data)) {

            List<String> urlList = new ArrayList<>();
            if (!data.contains(";")) {
                urlList.add(data);
            } else {
                urlList = StringUtils.splitStr(data, ";");
            }

            for (int i = 0; i < urlList.size(); i++) {
                String url = urlList.get(i);
                if (i == 0) {
                    ll_check_transfer_letter1.setVisibility(View.VISIBLE);
                    judgeType(iv_check_transfer_letter1, url);
                    checkLetter(ll_check_transfer_letter1, url);
                }
                if (i == 1) {
                    ll_check_transfer_letter2.setVisibility(View.VISIBLE);
                    judgeType(iv_check_transfer_letter2, url);
                    checkLetter(ll_check_transfer_letter2, url);
                }
                if (i == 2) {
                    ll_check_transfer_letter3.setVisibility(View.VISIBLE);
                    judgeType(iv_check_transfer_letter3, url);
                    checkLetter(ll_check_transfer_letter3, url);
                }
                if (i == 3) {
                    ll_check_transfer_letter4.setVisibility(View.VISIBLE);
                    judgeType(iv_check_transfer_letter4, url);
                    checkLetter(ll_check_transfer_letter4, url);
                }
                if (i == 4) {
                    ll_check_transfer_letter5.setVisibility(View.VISIBLE);
                    judgeType(iv_check_transfer_letter5, url);
                    checkLetter(ll_check_transfer_letter5, url);
                }
                if (i == 5) {
                    ll_check_transfer_letter6.setVisibility(View.VISIBLE);
                    judgeType(iv_check_transfer_letter6, url);
                    checkLetter(ll_check_transfer_letter6, url);
                }

            }
        }
    }

    /**
     * 判断图片类型
     * @param url
     */
    private void judgeType(ImageView imageView, String url) {
        if ("jpg".equals(StringUtils.isType(url))) {
            imageView.setBackgroundResource(R.drawable.jpg_shrink);
//                GlideImageLoader.displayImage0(getActivity(),filesstr1.get(0),R.drawable.jpg_shrink,releaseFactoringsellNextLlRlFinancialstatementiv1);
        }
        if ("pdf".equals(StringUtils.isType(url))) {
            imageView.setBackgroundResource(R.drawable.pdf_shrink);
        }
        if ("zip".equals(StringUtils.isType(url))) {
            imageView.setBackgroundResource(R.drawable.zip_shrink);
        }
    }

    /**
     * 查看文件
     */
    private void checkLetter(View view, final String url) {
        if (!TextUtils.isEmpty(url)) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ("jpg".equals(StringUtils.isType(url))) {
                        DisPatcher.startPicturePreviewActivity((Activity) context, url);
                    }
                }
            });
        }
    }
    /**
     * 设置下载名字
     *
     * @param string
     * @return
     */
    public BaseDialog setDownloadName(String string) {
        mTvDownload.setText(string);
        return this;
    }


    /**
     * 设置标题名字
     *
     * @param string
     * @return
     */
    public BaseDialog setTitleName(String string) {
        mTvTitle.setText(string);
        return this;
    }

}

