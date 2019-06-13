package com.zjhl.pad.view.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.moudle.entity.res.BusinessMessage;
import com.zjhl.pad.moudle.entity.res.MessageListRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.fragment.MessageFragment;

import java.util.List;

import static com.zjhl.pad.moudle.entity.res.MessageListRes.*;

/*
* File: SystemMsgAdapter.java 业务消息
* Author: DELL 
* Version: V1.0
* Create: 2018/6/14 16:18 
* Changes (from 2018/6/14) 
*/
public class SystemMsgAdapter  extends BaseQuickAdapter<BusinessMessage.ListBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener{

    private  String assetsType;
    private BaseQuickAdapter stsaasAdapter;
    private  int mposition;
    private  String assetsId;
    private  String linkName;
    private int id;
    private String getcType;
    public SystemMsgAdapter(List<BusinessMessage.ListBean>data) {
        super(R.layout.item_zichan, null);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessMessage.ListBean item) {

        String updateTimeDate = item.getUpdateTime();
         linkName = item.getLinkName();
         getcType = item.getcType();
        String messageNotice = item.getMessageNotice();
         assetsType = item.getAssetsType();
        String readState = item.getReadState();

        if (!TextUtils.isEmpty(readState)){
            //已读
            if ("2".equals(readState)){
//                helper.setBackgroundColor(R.id.tv_data,mContext.getResources().getColor(R.color.gray));
//                helper.setBackgroundRes(R.id.customer_message_list_item_rl_title,R.drawable.system_item_background);
                helper.setImageResource(R.id.customer_message_list_item_iv,R.drawable.message_read_icon);
            }else {
                helper.setImageResource(R.id.customer_message_list_item_iv,R.drawable.message_unread_icon);
//                helper.setBackgroundRes(R.id.customer_message_list_item_rl_title,R.drawable.sysytem_item_msg_down);
            }
        }

//         assetsId = item.getAssetsId();
         id = item.getId();
        helper.setText(R.id.tv_number,messageNotice+"");
        TextView tv_content = helper.getView(R.id.tv_number);

        if (!TextUtils.isEmpty(messageNotice) && !TextUtils.isEmpty(linkName)){
            SpannableStringBuilder spannable = new SpannableStringBuilder(messageNotice);
            int startIndex = messageNotice.indexOf(linkName);
            int endIndex = startIndex + linkName.length();

//        文字点击
            spannable.setSpan(new TextClick(),startIndex,endIndex
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_content.setMovementMethod(LinkMovementMethod.getInstance());
            tv_content.setText(spannable);
        }


        helper.setText(R.id.tv_data,updateTimeDate+"");
        helper.addOnClickListener(R.id.tv_number);



    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private class TextClick extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            String message_assertid = (String) SharedPreferanceUtils.get(mContext,"messge_assertid","");
            String messge_ctype = (String) SharedPreferanceUtils.get(mContext,"messge_ctype","");
            String messge_assetsType = (String) SharedPreferanceUtils.get(mContext,"messge_assetsType","");
            //在此处理点击事件
//            if (!TextUtils.isEmpty(messge_ctype)){
//                if ("3".equals(messge_ctype)){
//                    if ("1".equals(messge_assetsType)){
//                        DisPatcher.startMarketForfaitingDetailActivity(mContext, message_assertid, "");
//                    }else if ("2".equals(messge_assetsType)){
//                        DisPatcher.startMarketFactoringDetailActivity(mContext, message_assertid, "");
//                    }
//
//                }
//
//
//        }


        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.RED);
        }
    }
}
