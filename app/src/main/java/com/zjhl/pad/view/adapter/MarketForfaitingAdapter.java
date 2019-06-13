package com.zjhl.pad.view.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.LocalManageUtil;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingSellRes;
import com.zjhl.pad.moudle.entity.viewentity.ClickEntity;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * |
 * | 功能描述:
 * | 时　　间: 2018/4/27 18:03
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class MarketForfaitingAdapter extends BaseQuickAdapter<MarketForfaitingSellRes.DataBean, BaseViewHolder>/* implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener*/ {
//    NestAdapter nestAdapter;

    public MarketForfaitingAdapter(List<MarketForfaitingSellRes.DataBean> data) {
//        super(R.layout.item_market_view,null);
        super(R.layout.item_market_new_view, null);
//        super(R.layout.item_market_view,data);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketForfaitingSellRes.DataBean item) {
        helper.setGone(R.id.market_item_me_issue, false);
//        blockChainCompanyRet(item.getId() + "", helper);
        helper.setGone(R.id.market_item_rl_title_orner, true);
        helper.setText(R.id.market_item_text_tv1, item.getAssetsNo());
        helper.setText(R.id.market_item_text_tv2, item.getTitle());
        helper.setText(R.id.market_item_text_tv3, "");
        helper.setText(R.id.market_item_text_tv4, item.getDiscountRate());
//        helper.setGone(R.id.market_item_text_tv2, false);
        helper.setGone(R.id.market_item_text_tv3, false);
        helper.setGone(R.id.market_item_text_tv4, false);
        helper.setText(R.id.market_item_date_tv2, item.getMaturity());
        helper.setText(R.id.market_item_text2_tv1, item.getAmount());
        helper.setText(R.id.market_item_bottom_date_tv2, item.getIndateMessage());
//        helper.setText(R.id.market_item_text_iv1,item.getOpenFullName());
        helper.setText(R.id.market_item_text2_tv3, MyApplication.mMyApplication.getResources().getString(R.string.filtrate_amount));
        helper.setText(R.id.market_item_text_compay, item.getOpenFullName());
        helper.setText(R.id.market_item_text3_tv1, item.getCurrency());
        String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
        if("cn".equals(lanage)) {
            helper.setText(R.id.market_item_rl_text1, item.getCountryName());
            helper.setImageResource(R.id.market_item_me_issue,R.drawable.market_item_me_issue);
            helper.setImageResource(R.id.market_item_iv_orner,R.drawable.market_item_red_orner);
        }else if("en".equals(lanage)) {
            helper.setText(R.id.market_item_rl_text1, item.getCountryNameEn());
            helper.setImageResource(R.id.market_item_me_issue,R.drawable.market_item_me_issue_en);
            helper.setImageResource(R.id.market_item_iv_orner,R.drawable.market_item_red_orneren);
        }
        if ("1".equals(item.getDebtType())) {
//            helper.setText(R.id.market_item_tv_title_orner, R.string.market_forfaiting_adapter_type_inland);
            helper.setGone(R.id.market_item_iv_orner,true);
//            helper.setGone(R.id.market_item_tv_title_orner,true);
        } else if ("2".equals(item.getDebtType())) {
//            helper.setText(R.id.market_item_tv_title_orner, R.string.market_forfaiting_adapter_type_outland);
            helper.setGone(R.id.market_item_iv_orner,false);
//            helper.setGone(R.id.market_item_tv_title_orner,false);
        }
        if ("1".equals(item.getMyAssets())) {
            helper.setGone(R.id.market_item_me_issue, true);//是否是自己发布的 是显示  否 不显示  默认否  "myAssets": 1,      //1为我的发布 0 不显示
        }else{
            helper.setGone(R.id.market_item_me_issue, false);//是否是自己发布的 是显示  否 不显示  默认否  "myAssets": 1,      //1为我的发布 0 不显示

        }
        if("1".equals(item.getIsnBlockChain())){
            helper.setGone(R.id.market_item_text_ll, true);//是否是区块链 是显示  否 不显示  默认否
        }else {
            helper.setGone(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
        }
//        helper.setVisible(R.id.market_item_text_ll, true);//是否是区块链 是显示  否 不显示  默认否
//        helper.addOnClickListener(R.id.market_item_text2_tv1);
//        helper.addOnClickListener(R.id.market_item_text2_tv2);
//        helper.addOnClickListener(R.id.market_item_text2_tv3);
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

    /**
     * //区块链机构查询接口
     *
     * @POST(Constants.NETPATH.BLOCKCHAINCOMPANY) Call<BlockChainRes> blockChainCompanyRet(@Body RequestBody requestBody);
     * <p>
     * public Call<BlockChainRes> blockChainCompanyRet(BlockChainReq data) {
     * Call<BlockChainRes> blockChainCompanyRet = mApi.blockChainCompanyRet(createRequestBody(data));
     * return blockChainCompanyRet;
     * }
     */
    public void blockChainCompanyRet(String id, final BaseViewHolder helper) {
        MyLogger.pLog().i("区块链查询接口");
        BlockChainReq blockChainReq = new BlockChainReq();
        blockChainReq.setBussIds(new String[]{id});
//        ActionPresenter.getInstance().blockChainCompanyRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
//        ActionPresenter.getInstance().blockChainBussinessRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
        ActionPresenter.getInstance().blockChainTellerRet(blockChainReq).enqueue(new Callback<BlockChainRes>() {
            @Override
            public void onResponse(Call<BlockChainRes> call, Response<BlockChainRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null && response.body().getData().size() > 0) {
                            if ("1".equals(response.body().getData().get(0).getIsnBlockChain())) {
                                helper.setVisible(R.id.market_item_text_ll, true);//是否是区块链 是显示  否 不显示  默认否
                            } else {
                                helper.setVisible(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
                            }
                        } else {
                            helper.setVisible(R.id.market_item_text_ll, false);//是否是区块链 是显示  否 不显示  默认否
                        }
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                        MyLogger.pLog().e(response.body().getMessage());
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }
}
