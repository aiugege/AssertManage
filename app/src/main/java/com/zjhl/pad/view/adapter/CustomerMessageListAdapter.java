package com.zjhl.pad.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingSellRes;
import com.zjhl.pad.moudle.entity.res.MessageListRes;
import com.zjhl.pad.view.R;

import java.util.List;

/**
 * |
 * | 功能描述:CustomerMessageListAdapter 消息列表
 * | 时　　间: 2018/4/27 18:03
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class CustomerMessageListAdapter extends BaseQuickAdapter<MessageListRes.DataBean, BaseViewHolder>/* implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener*/ {
//    NestAdapter nestAdapter;

    public CustomerMessageListAdapter(List<MessageListRes.DataBean> data) {
        super(R.layout.item_customer_message_list_view, null);

    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @Override
    protected void convert(BaseViewHolder helper, MessageListRes.DataBean item) {
        helper.setText(R.id.customer_message_list_item_text_lable, item.getTitle());
        helper.setText(R.id.customer_message_list_item_text_date, item.getPublishDate());
//        helper.setText(R.id._item_text_comtent, item.getContent());
//        helper.setText(R.id.market_item_text_tv4, item.getDiscountRate());
    }


  /*
  @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtils.showShort("childView click");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        MyLogger.pLog().d("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次"+adapter.getItem(position).toString());
        ToastUtils.showShort("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
    }*/
}
