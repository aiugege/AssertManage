package com.zjhl.pad.view.adapter;

import android.view.View;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingSellRes;
import com.zjhl.pad.view.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * |
 * | 功能描述:
 * | 时　　间: 2018/4/27 18:03
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class OfferDetailAdapter extends BaseQuickAdapter<MarketForfaitingOfferListRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    //    NestAdapter nestAdapter;
    int positiontrue;
    String myAssets = "0";

    public OfferDetailAdapter(List<MarketForfaitingOfferListRes.DataBean> data,String myAssets) {
        super(R.layout.item_offer_detail_list, null);
        this.myAssets = myAssets;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @Override
    protected void convert(BaseViewHolder helper, MarketForfaitingOfferListRes.DataBean item) {
        helper.setText(R.id.item_forfaiting_detail_offer_list_companyname, item.getbOrgName());
//        helper.setText(R.id.item_forfaiting_detail_offer_list_date, item.getStruckDate());
        helper.setText(R.id.item_forfaiting_detail_offer_list_rate, item.getDiscountRate());
//        helper.setChecked(R.id.item_forfaiting_detail_offer_list_rb, item.isSelected());
//        if("1".equals(myAssets)){
//            helper.setVisible(R.id.item_forfaiting_detail_offer_list_im, true);
//        }
//        if (item.isSelected()) {
//            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_checked);
//        }else{
//            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_normal);
//        }
//        helper.setOnClickListener(R.id.item_forfaiting_detail_offer_list_rb, new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                //实现单选，第一种方法，十分简单， Lv Rv通用,因为它们都有notifyDataSetChanged()方法
//                // 每次点击时，先将所有的selected设为false，并且将当前点击的item 设为true， 刷新整个视图
//                for (MarketForfaitingOfferListRes.DataBean data1 : getData()) {
//                    data1.setSelected(false);
//                }
//                getData().get(positiontrue).setSelected(true);
//                notifyDataSetChanged();
//            }
//        });
        /*helper.setText(R.id.market_item_text_tv4,item.getDiscountRate());
        helper.setVisible(R.id.market_item_text_tv2,true);
        helper.setVisible(R.id.market_item_text_tv3,true);
        helper.setVisible(R.id.market_item_text_tv4,true);
        helper.setText(R.id.market_item_date_tv2,item.getMaturity());
        helper.setText(R.id.market_item_text2_tv1,item.getAmount());
//        helper.setText(R.id.market_item_text_iv1,item.getOpenFullName());
        helper.setText(R.id.market_item_text_compay,item.getOpenFullName());
        helper.setText(R.id.market_item_text3_tv1,item.getCurrency());
        helper.setText(R.id.market_item_rl_text1,item.getCountryName());
        helper.setText(R.id.market_item_tv_title_orner,"国内");
        if("1".equals(item.getMyAssets())){
            helper.setVisible(R.id.market_item_me_issue,true);//是否是自己发布的 是显示  否 不显示  默认否  "myAssets": 1,      //1为我的发布 0 不显示
        }
        helper.setVisible(R.id.market_item_text_ll,true);//是否是区块链 是显示  否 不显示  默认否*/
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtils.showShort("a");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtils.showShort("b");
//        RadioButton radioButton = (RadioButton) view.findViewById(R.id.item_forfaiting_detail_offer_list_rb);
        positiontrue = position;
        for (MarketForfaitingOfferListRes.DataBean data1 : getData()) {
            data1.setSelected(false);
        }
        getData().get(positiontrue).setSelected(true);

//        for (MarketForfaitingOfferListRes.DataBean data1 : getData()) {
//            MyLogger.pLog().e(data1.isSelected());
//        }
        notifyDataSetChanged();
    }
}
