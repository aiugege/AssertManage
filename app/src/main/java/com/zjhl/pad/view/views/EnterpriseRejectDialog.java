package com.zjhl.pad.view.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.view.R;

/**
 * @desc: RejectDialog 驳回弹窗
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class EnterpriseRejectDialog extends BaseDialog {
    private ImageView mClose;//关闭
    private TextView mTitle;//驳回title
    private TextView mReason;//驳回原因
    private TextView mReason1;//驳回原因1
    private TextView mReason2;//驳回原因2

    private String isSelect = "";

    public EnterpriseRejectDialog(Context context) {
        super(context);
    }

    public EnterpriseRejectDialog(Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }

    public EnterpriseRejectDialog(Context context, OnBaseDialogListener onBaseDialogListener, String reason) {
        super(context, onBaseDialogListener);
        setReason(reason);
    }
    public EnterpriseRejectDialog(Context context, OnBaseDialogListener onBaseDialogListener, String title, String reason, String reason1, String reason2) {
        super(context, onBaseDialogListener);
        setReason(reason,reason1,reason2);
        if(!StringUtils.isEmpty(title)){
            setTitleName(title);
        }
    }

    /**
     * @param context              上下文
     * @param onBaseDialogListener 回调监听
     * @param title                自定义title
     */
    public EnterpriseRejectDialog(Context context, OnBaseDialogListener onBaseDialogListener,String title, String reason) {
        super(context, onBaseDialogListener);
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
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_enterprise_reject, null);   //得到各种
        mReason = (TextView) view.findViewById(R.id.dialog_enterprise_reject_tv_reason);
        mReason1 = (TextView) view.findViewById(R.id.dialog_enterprise_reject_tv_reason1);
        mReason2 = (TextView) view.findViewById(R.id.dialog_enterprise_reject_tv_reason2);
        mReason2.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTitle = (TextView) view.findViewById(R.id.dialog_enterprise_reject_title);
        mClose = (ImageView) view.findViewById(R.id.dialog_reject_iv_close);
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        //初始化数据
        return view;
    }

    /**
     * 设置取消 显示状态
     *
     * @param isShow
     * @return
     */
    public BaseDialog setCancelVisible(boolean isShow) {
        if (isShow) {
//            mCcncel.setVisibility(View.VISIBLE);
        } else {
//            mCcncel.setVisibility(View.GONE);
//            mLine.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置标题名字
     *
     * @param string
     * @return
     */
    public BaseDialog setTitleName(String string) {
        mTitle.setText(string);
        return this;
    }
    /**
     * 设置内容
     *
     * @param string
     * @return
     */
    public BaseDialog setReason(String string) {
            mReason.setText(StringUtils.nullStrToEmpty(string));
        return this;
    }
/**
     * 设置内容
     *
     * @param string
     * @return
     */
    public BaseDialog setReason(String string,String string1,String string2) {
            mReason.setText(StringUtils.nullStrToEmpty(string));
            mReason1.setText(StringUtils.nullStrToEmpty(string1));
            mReason2.setText(StringUtils.nullStrToEmpty(string2));
        return this;
    }

}
