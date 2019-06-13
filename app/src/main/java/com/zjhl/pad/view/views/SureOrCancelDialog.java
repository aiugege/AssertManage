package com.zjhl.pad.view.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zjhl.pad.view.R;

/**
 * @desc: SureOrCancelDialog
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class SureOrCancelDialog extends BaseDialog {
    private TextView mTvCancel;//取消
    private TextView mTvSure;//确定
    private TextView mTvTitle;//标题

    public SureOrCancelDialog(Context context) {
        super(context);
    }

    public SureOrCancelDialog(Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }

    /**
     * @param context              上下文
     * @param onBaseDialogListener 回调监听
     * @param cancelName           自定义取消名字
     * @param sureName             自定义确定名字
     * @param title                自定义title
     */
    public SureOrCancelDialog(Context context, OnBaseDialogListener onBaseDialogListener,
                              String title, String cancelName, String sureName) {
        super(context, onBaseDialogListener);
        setCancelName(cancelName);
        setSureName(sureName);
        setTitleName(title);
    }
    //设置对话框的样式

    @Override
    protected int getDialogStyleId() {
        return R.style.BaseDialog;
    }
    //继承于BaseDialog的方法，设置布局用的，这样对话框张啥样久随心所欲啦

    @Override
    protected View getView() {  // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sure_or_cancel, null);   //得到各种
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvSure = (TextView) view.findViewById(R.id.tv_sure);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        if (baseDialogListener != null) {
            mTvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialogListener.negative("","");
                    dismiss();
                }
            });
            mTvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialogListener.positive();
                    dismiss();
                }
            });
        }
        return view;
    }

    /**
     * 设置取消名字
     *
     * @param string
     * @return
     */
    public BaseDialog setCancelName(String string) {
        mTvCancel.setText(string);
        return this;
    }

    /**
     * 设置确定名字
     *
     * @param string
     * @return
     */
    public BaseDialog setSureName(String string) {
        mTvSure.setText(string);
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
