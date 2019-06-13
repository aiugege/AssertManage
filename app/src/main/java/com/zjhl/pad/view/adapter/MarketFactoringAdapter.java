package com.zjhl.pad.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingSellRes;
import com.zjhl.pad.view.R;

import java.util.List;

/**
 * |
 * | 功能描述:
 * | 时　　间: 2018/4/27 18:03
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class MarketFactoringAdapter extends BaseQuickAdapter<MarketFactoringSellRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
//    NestAdapter nestAdapter;

    public MarketFactoringAdapter(List<MarketFactoringSellRes.DataBean> data) {
        super(R.layout.item_market_new_view, data);

    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @Override
    protected void convert(BaseViewHolder helper, MarketFactoringSellRes.DataBean item) {
//        switch (helper.getItemViewType()) {
//            case ClickEntity.CLICK_ITEM_VIEW:
////                helper.addOnClickListener(R.id.btn);
//                break;
//
//        }
        //显示保理需要的保理商名称布局和资产卖出方前置字段
        helper.setVisible(R.id.market_item_ll_factoring, true);
        helper.setVisible(R.id.market_item_text_lable, true);
        helper.setGone(R.id.market_item_me_issue, false);
        helper.setGone(R.id.market_item_text_tv2, false);
//        helper.setVisible(R.id.market_item_ll_factoring,true);
        helper.setText(R.id.market_item_text3_tv1, item.getCurrency());
        helper.setText(R.id.market_item_text_tv1, item.getFactoringNo());
        helper.setText(R.id.market_item_text_compay, item.getSeller());
        helper.setText(R.id.market_item_text_factoring, item.getFactoringName());
        helper.setText(R.id.market_item_bottom_date_tv2, item.getIndateMessage());
//        helper.setText(R.id.market_item_text_tv3, "");
//        helper.setText(R.id.market_item_text_tv4, "");
        helper.setText(R.id.market_item_date_tv2, item.getMaturity());
        helper.setText(R.id.market_item_text2_tv1, item.getAmount() + "");
        helper.setText(R.id.market_item_text_iv1, item.getResidueDay());

        helper.setText(R.id.market_item_tv_title_orner, R.string.market_facotring_inland);
        helper.setText(R.id.market_item_date_tv1, R.string.market_facotring_exprie);
        if (!StringUtils.isEmpty(item.getCountryName())) {
//            helper.setVisible(R.id.market_item_rl_text1, true);
            String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
            if("cn".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryName());
                helper.setImageResource(R.id.market_item_me_issue,R.drawable.market_item_me_issue);
            }else if("en".equals(lanage)) {
                helper.setText(R.id.market_item_rl_text1, item.getCountryNameEn());
                helper.setImageResource(R.id.market_item_me_issue,R.drawable.market_item_me_issue_en);
            }
        }
        //1,//保理类型 1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理
        if (1==(item.getFactoringType())) {
            helper.setVisible(R.id.market_item_ll_factoring_type, true);
            helper.setText(R.id.market_item_text_factoring_type, R.string.market_facotring_type1);
        } else if (2==(item.getFactoringType())) {
            helper.setVisible(R.id.market_item_ll_factoring_type, true);
            helper.setText(R.id.market_item_text_factoring_type, R.string.market_facotring_type2);
        } else if (3==(item.getFactoringType())) {
            helper.setVisible(R.id.market_item_ll_factoring_type, true);
            helper.setText(R.id.market_item_text_factoring_type, R.string.market_facotring_type3);
        } else if (4==(item.getFactoringType())) {
            helper.setVisible(R.id.market_item_ll_factoring_type, true);
            helper.setText(R.id.market_item_text_factoring_type, R.string.market_facotring_type4);
        }
        if ("1".equals(item.getMyAssets())) {
            helper.setGone(R.id.market_item_me_issue, true);//是否是自己发布的 是显示  否 不显示  默认否  "myAssets": 1,      //1为我的发布 0 不显示
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtils.showShort("childView click");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        MyLogger.pLog().d("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
//        ToastUtils.showShort("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
    }
}
