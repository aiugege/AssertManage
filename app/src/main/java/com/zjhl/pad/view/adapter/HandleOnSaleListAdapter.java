package com.zjhl.pad.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.view.R;

import java.util.List;

/**
 * |
 * | 功能描述: 卖家在售资产经办报价列表
 * | 时　　间: 2018/4/27 18:03
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class HandleOnSaleListAdapter extends BaseQuickAdapter<MarketForfaitingOfferListRes.DataBean, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    //    NestAdapter nestAdapter;
    int positiontrue = -1;
    String myAssets = "0";
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");

    public HandleOnSaleListAdapter(List<MarketForfaitingOfferListRes.DataBean> data, String myAssets) {
        super(R.layout.item_handle_forfaiting_offer_list, null);
        this.myAssets = myAssets;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketForfaitingOfferListRes.DataBean item) {
        helper.setGone(R.id.item_forfaiting_detail_offer_list_im, false);
        helper.setText(R.id.item_forfaiting_detail_offer_list_companyname, item.getbOrgName());
        if (StringUtils.isEmpty(item.getStruckDate())) {
            helper.setText(R.id.item_forfaiting_detail_offer_list_date, item.getCreateTime());
        } else {
            helper.setText(R.id.item_forfaiting_detail_offer_list_date, item.getStruckDate());

        }
        helper.setText(R.id.item_forfaiting_detail_offer_list_rate, item.getDiscountRate());
//        helper.setText(R.id.item_forfaiting_detail_offer_list_reason, item.getRejectOpinion());
//        helper.setChecked(R.id.item_forfaiting_detail_offer_list_rb, item.isSelected());
        if ("1".equals(myAssets)) {
            if ("2".equals(userType)) {
                helper.setGone(R.id.item_forfaiting_detail_offer_list_im, true);
            }
        }
        //经办 107经办提交  公司名称变蓝  经办角色选中状态
        if ("2".equals(userType)) {
//            helper.setVisible(R.id.item_forfaiting_detail_offer_list_reason, false);
            if ("107".equals(item.getPriceState())) {
                helper.setTextColor(R.id.item_forfaiting_detail_offer_list_companyname, mContext.getResources().getColor(R.color.blue));
//            helper.setBackgroundColor(R.id.ll_list, mContext.getResources().getColor(R.color.gray));
//            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_checked);
                if(positiontrue == -1) {
                    item.setSelected(true);
                }
            } else {
//            helper.setBackgroundColor(R.id.ll_list, mContext.getResources().getColor(R.color.white));
//            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_normal);
//                item.setSelected(false);
            }
        }else if ("3".equals(userType)) {
            //复核 107经办提交  公司名称变蓝
//            helper.setVisible(R.id.item_forfaiting_detail_offer_list_reason, false);
            if ("107".equals(item.getPriceState())) {
                helper.setTextColor(R.id.item_forfaiting_detail_offer_list_companyname, mContext.getResources().getColor(R.color.blue));
//            helper.setBackgroundColor(R.id.ll_list, mContext.getResources().getColor(R.color.gray));
//            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_checked);
            } else {
//            helper.setBackgroundColor(R.id.ll_list, mContext.getResources().getColor(R.color.white));
//            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_normal);
            }
        }
        if (item.isSelected()) {
            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_checked);
        } else {
            helper.setImageResource(R.id.item_forfaiting_detail_offer_list_im, R.drawable.radiobutton_normal);
        }

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
