package com.zjhl.pad.view.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.adapter.HandleOnSaleListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.view.base.BaseFragment.PAGE_SIZE;

/**
 * @desc: RejectDialog 驳回弹窗
 * @version: v1.0
 * @packagename: com.zjhl.pad.view.views
 * @author: pluto
 * @create: 2018/5/17 11:49
 * @projectname: nnkj
 **/
public class RejectDialog extends BaseDialog {
    private TextView mTvSure;//确定
    private TextView mLimit;//确定
    private TextView mCompanyName;//被驳回的公司名称
    private ImageView mClose;//关闭
    private EditText mReason;//驳回原因
    private String assestId = "";//资产id
    private String companyname = "";//公司名称
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
    private String isSelect = "";
    public RejectDialog(Context context) {
        super(context);
    }

    public RejectDialog(Context context, OnBaseDialogListener onBaseDialogListener) {
        super(context, onBaseDialogListener);
    }
    public RejectDialog(Context context, OnBaseDialogListener onBaseDialogListener, String assestId) {
        super(context, onBaseDialogListener);
        this.assestId = assestId;
    }
    public RejectDialog(Context context, OnBaseDialogListener onBaseDialogListener, String assestId, String companyname) {
        super(context, onBaseDialogListener);
        this.assestId = assestId;
        this.companyname = companyname;
        if(!StringUtils.isEmpty(companyname)){
            //驳回原因
            String english = "";
            if ("en".equals(lanage)) {
                english = "The bid of ";
            }
            String str="<font color='#b7b7b7'>"+english+companyname+"</font><font color='#d2d2d2'> "+MyApplication.mMyApplication.getResources().getString(R.string.sold_assets_reject_reason1)+"</font>";
            mCompanyName.setVisibility(View.VISIBLE);
            mCompanyName.setText(Html.fromHtml(str));
//            mCompanyName.setText(MyApplication.mMyApplication.getResources().getString(R.string.sold_assets_reject_reason1));
        }
    }

    /**
     * @param context              上下文
     * @param onBaseDialogListener 回调监听
     * @param cancelName           自定义取消名字
     * @param sureName             自定义确定名字
     * @param title                自定义title
     */
    public RejectDialog(Context context, OnBaseDialogListener onBaseDialogListener,
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
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_reject, null);   //得到各种
        mTvSure = (TextView) view.findViewById(R.id.dialog_reject_tv_sure);
        mLimit = (TextView) view.findViewById(R.id.dialog_reject_tv_limit1);
        mCompanyName = (TextView) view.findViewById(R.id.dialog_reject_tv_companyname);
        mReason = (EditText) view.findViewById(R.id.dialog_reject_tv_reason);
        mClose = (ImageView) view.findViewById(R.id.dialog_reject_iv_close);
        if (baseDialogListener != null) {
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
//            mCcncel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    baseDialogListener.negative();
//                    dismiss();
//                }
//            });
            mTvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(StringUtils.isEmpty( mReason.getText().toString())) {
                        ToastUtils.showShort(context.getString(R.string.hint_dialog_reject_content));
                    }else {
//                    baseDialogListener.positive();
                        baseDialogListener.positive(null, "" + mReason.getText().toString());
                        dismiss();
                    }
                }
            });
            mReason.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // 输入的内容变化的监听
                    Log.e("输入过程中执行该方法", "文字变化"+s+"before:"+start+"start:"+before+"before:"+count);
                        mLimit.setText(s.length()+"");
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // 输入前的监听
//                    Log.e("输入前确认执行该方法", "开始输入");

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // 输入后的监听
//                    Log.e("输入结束执行该方法", "输入结束");

                }
            });
        }

        //初始化数据
        return view;
    }

    /**
     * 设置取消名字
     *
     * @param string
     * @return
     */
    public BaseDialog setCancelName(String string) {
//        mCcncel.setText(string);
        return this;
    }

    /**
     * 设置取消 显示状态
     *
     * @param isShow
     * @return
     */
    public BaseDialog setCancelVisible(boolean isShow) {
        if(isShow) {
//            mCcncel.setVisibility(View.VISIBLE);
        }else{
//            mCcncel.setVisibility(View.GONE);
//            mLine.setVisibility(View.GONE);
        }
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
//        mTvTitle.setText(string);
        return this;
    }

}
