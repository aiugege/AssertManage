package com.zjhl.pad.presenter.dispatcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.moudle.entity.req.ReviewOfferSubmitLetterOnSaleListReq;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitOnSaleListRes;
import com.zjhl.pad.view.activity.BlockChainDetailActivity;
import com.zjhl.pad.view.activity.CheckLetterActivity;
import com.zjhl.pad.view.activity.DetailMessageActivity;
import com.zjhl.pad.view.activity.FactoringIssuingActivity;
import com.zjhl.pad.view.activity.FileChoiceActivity;
import com.zjhl.pad.view.activity.FiltrateActivity;
import com.zjhl.pad.view.activity.ForfaitingIssuingActivity;
import com.zjhl.pad.view.activity.IssueActivity;
import com.zjhl.pad.view.activity.LoginActivity;
import com.zjhl.pad.view.activity.MainActivity;
import com.zjhl.pad.view.activity.MarketFactoringActivity;
import com.zjhl.pad.view.activity.MarketFactoringDetailActivity;
import com.zjhl.pad.view.activity.MarketFactoringOfferActivity;
import com.zjhl.pad.view.activity.MarketForfaitingActivity;
import com.zjhl.pad.view.activity.MarketForfaitingDetailActivity;
import com.zjhl.pad.view.activity.MarketForfaitingFiltrateActivity;
import com.zjhl.pad.view.activity.MarketForfaitingOfferActivity;
import com.zjhl.pad.view.activity.MyQuoteActivity;
import com.zjhl.pad.view.activity.MyQuoteFactoringDetailActivity;
import com.zjhl.pad.view.activity.MyQuoteForfaitingDetailActivity;
import com.zjhl.pad.view.activity.PicturePreviewActivity;
import com.zjhl.pad.view.activity.PropertyActivity;
import com.zjhl.pad.view.activity.RegisterNewActivity;
import com.zjhl.pad.view.activity.ReviewOfferLetterActivity;
import com.zjhl.pad.view.activity.SellAssetsRepublishActivity;
import com.zjhl.pad.view.activity.SoldFactoringDetailActivity;
import com.zjhl.pad.view.activity.SoldForfaitingDetailActivity;
import com.zjhl.pad.view.adapter.MarketFactoringDetailListAdapter;
import com.zjhl.pad.view.listener.OnBaseActivityListener;

/**
 * @desc: DisPatcher
 * @version: v1.0
 * @packagename: com.zjhl.pad.presenter.okhttp.dispatcher
 * @author: pluto
 * @create: 2018/4/19 17:05
 * @projectname: nnkj
 **/
public class DisPatcher {

    /**
     * 跳转MainActivity
     *
     * @param context
     */
    public static void startMainActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * 跳转LoginActivity
     *
     * @param context
     */
    public static void startLoginActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, LoginActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * 跳转MainActivity
     *
     * @param context
     */
    public static void startMainActivity(Activity context, int requestCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转IssueActivity
     *
     * @param context
     */
    public static void startIssueActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, IssueActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * 跳转FileChoiceActivity
     *
     * @param context
     */
    public static void startFileChoiceActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, FileChoiceActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * 跳转ForfaitingIssuingActivity
     *assestId 资产id
     * @param context  assestType 1福费廷 2保理
     */
    public static void startForfaitingIssuingActivity(Activity context,String assestId,String assestType) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ForfaitingIssuingActivity.class);
        if(!StringUtils.isEmpty(assestId)){
            intent.putExtra("assestId", assestId);
        }if(!StringUtils.isEmpty(assestType)){
            intent.putExtra("assestType", assestType);
        }
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * 跳转FactoringIssuingActivity
     *
     * @param context
     */
    public static void startFactoringIssuingActivity(Activity context,String assestId,String assestType) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, FactoringIssuingActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        if(!StringUtils.isEmpty(assestId)){
            intent.putExtra("assestId", assestId);
        }if(!StringUtils.isEmpty(assestType)){
            intent.putExtra("assestType", assestType);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转MarketFactoringActivity
     *
     * @param context
     */
    public static void startMarketFactoringActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketFactoringActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * 跳转MarketForfaitingActivity
     *
     * @param context
     */
    public static void startMarketForfaitingActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketForfaitingActivity.class);
        //intent.putStringArrayListExtra(Constancts.KEY.PAR_FOLDER_INFO, (ArrayList<String>) list);
        context.startActivity(intent);
    }

    /**
     * MarketForfaitingDetailActivity 福费廷详情
     *
     * @param context
     */
    public static void startMarketForfaitingDetailActivity(Context context, String id, String myAssets) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketForfaitingDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("myAssets", myAssets);
        context.startActivity(intent);
    }
    /**
     * MyQuoteForfaitingDetailActivity 我的报价福费廷详情
     *
     * @param context
     */
    public static void startMyQuoteForfaitingDetailActivity(Context context, String id, String priceId) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MyQuoteForfaitingDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("priceId", priceId);
        context.startActivity(intent);
    }
/**
     * MyQuoteFactoringDetailActivity 我的报价保理详情
     *
     * @param context
     */
    public static void startMyQuoteFactoringDetailActivity(Context context, String id, String priceId) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MyQuoteFactoringDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("priceId", priceId);
        context.startActivity(intent);
    }
    /**
     * SoldForfaitingDetailActivity 在售资产福费廷详情
     *
     * @param context
     */
    public static void startSoldForfaitingDetailActivity(Context context, String id,String myAssets) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, SoldForfaitingDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("myAssets", myAssets);
        context.startActivity(intent);
    }
/**
     * SoldFactoringDetailActivity 在售资产保理详情
     *
     * @param context
     */
    public static void startSoldFactoringDetailActivity(Context context, String id) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, SoldFactoringDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    /**
     * MarketFactoringDetailActivity 保理详情
     *
     * @param context
     */
    public static void startMarketFactoringDetailActivity(Context context, String id, String myAssets) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketFactoringDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("myAssets", myAssets);
        context.startActivity(intent);
    }

    /**
     * MarketForfaitingOfferActivity 福费廷详情-报价
     *
     * @param context
     */
    public static void startMarketForfaitingOfferActivity(Activity context, MarketForfaitingDetailRes marketForfaitingDetailRes) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketForfaitingOfferActivity.class);
        intent.putExtra("marketForfaitingDetailRes", marketForfaitingDetailRes);
        context.startActivityForResult(intent,666);
    }

    /**
     * MarketFactoringOfferActivity 保理详情-报价
     *
     * @param context
     */
    public static void startMarketFactoringOfferActivity(Activity context, MarketFactoringDetailRes marketFactoringDetailRes) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketFactoringOfferActivity.class);
        intent.putExtra("marketFactoringDetailRes", marketFactoringDetailRes);
        context.startActivityForResult(intent,666);
    }

    /**
     * MarketForfaitingFiltrateActivity 福费廷列表-筛选
     *
     * @param context
     */
    public static void startMarketForfaitingFiltrateActivity(Activity context, String assetsType) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MarketForfaitingFiltrateActivity.class);
        intent.putExtra("assetsType", assetsType);
        context.startActivity(intent);
    }

    /**
     * DetailMessageActivity 消息详情
     *
     * @param context
     */
    public static void startDetailMessageActivity(Activity context, String messagetype, String messageid) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, DetailMessageActivity.class);
        intent.putExtra("messagetype", messagetype);
        intent.putExtra("messageid", messageid);
        context.startActivity(intent);
    }

    /**
     * BlockChainDetailActivity 区块链详情
     *
     * @param context
     */
    public static void startBlockChainDetailActivity(Activity context, String assestid) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BlockChainDetailActivity.class);
        intent.putExtra("assestId", assestid);
        context.startActivity(intent);
    }

    /**
     * ReviewOfferLetterActivity 要约函详情
     *
     * @param context
     */
    public static void startReviewOfferLetterActivity(Activity context, String isSelect, ReviewOfferSubmitOnSaleListRes reviewOfferSubmitOnSaleListRes, int requestCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ReviewOfferLetterActivity.class);
        intent.putExtra("isSelect", isSelect);
        intent.putExtra("reviewOfferSubmitLetterOnSaleListReq", reviewOfferSubmitOnSaleListRes);
//        context.startActivityForResult(intent,Activity.RESULT_FIRST_USER);
        context.startActivityForResult(intent,Activity.RESULT_FIRST_USER);
    }
    /**
     * PropertyActivity 我的资产
     *
     * @param context
     */
    public static void startPropertyActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, PropertyActivity.class);
        context.startActivity(intent);
    }


    /**
     * CheckLetterActivity 查看要约函
     *
     * @param context
     */
    public static void startCheckLetterActivity(Activity context, String assetsId) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CheckLetterActivity.class);
        intent.putExtra("assetsId", assetsId);
//        context.startActivityForResult(intent,Activity.RESULT_FIRST_USER);
        context.startActivityForResult(intent,Activity.RESULT_FIRST_USER);
    }

    /**
     * FiltrateActivity 筛选条件
     *
     * @param context
     */
    public static void startFiltrateActivity(Activity context, String shareKey,int code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, FiltrateActivity.class);
        intent.putExtra("shareKey", shareKey);
        context.startActivityForResult(intent, code);
//        Intent intent = new Intent(context, MyQuoteActivity.class);
//        intent.putExtra("assestId", assestid);
//        context.startActivity(intent);
    }
    /**
     * PicturePreviewActivity 放大图片
     *
     * @param context
     */
    public static void startPicturePreviewActivity(Activity context, String url) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, PicturePreviewActivity.class);
        intent.putExtra("path", url);
        context.startActivity(intent);
    }
    /**
     * RegisterNewActivity 新注册
     *
     * @param context
     */
    public static void startRegisterNewActivity(Activity context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, RegisterNewActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转SellAssetsRepublishActivity
     *assestId 资产id
     * @param context  assestType 1福费廷 2保理
     */
    public static void startSellAssetsRepublishActivity(Activity context, String assestId,
            String assestType, String showCode, String assetsNo, String title, String discountRate) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, SellAssetsRepublishActivity.class);
        if(!StringUtils.isEmpty(assestId)){
            intent.putExtra("assestId", assestId);
        }if(!StringUtils.isEmpty(assestType)){
            intent.putExtra("assestType", assestType);
        }if(!StringUtils.isEmpty(showCode)){
            intent.putExtra("showCode", showCode);
        }if(!StringUtils.isEmpty(assetsNo)){
            intent.putExtra("assetsNo", assetsNo);
        }if(!StringUtils.isEmpty(title)){
            intent.putExtra("title", title);
        }if(!StringUtils.isEmpty(discountRate)){
            intent.putExtra("discountRate", discountRate);
        }
        context.startActivity(intent);
    }

    //==========service启动=================

    /**
     * 启动本地服务
     */
    public static void startOuerService(Context context) {
        if (context == null) {
            return;
        }

//        Intent serviceIntent = new Intent(context, Service.class);
//        context.startService(serviceIntent);
    }


    //==========broadcast发送=================

    /**
     * 发送用户需要登录广播
     *
     * @param context
     */
    public static void sendNeedLoginBroadcast(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
        context.sendBroadcast(intent);
    }

    /**
     *极光广播
     * @param context
     */
    public static void sendSystemMessage(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
        context.sendBroadcast(intent);
    }

    /**
     *刷新消息数量
     * @param context
     */
    public static void sendSystemMessageCount(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE_COUNT);
        context.sendBroadcast(intent);
    }

    /**
     *柜员添加广播
     * @param context
     */
    public static void sendAddmember(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("guiyuan_position",1);
        intent.setAction(Constants.BROADCAST_ACTION.RESAVE_ADD_MESSAGE);
        context.sendBroadcast(intent);
    }

    /**
     * 发送更新用户信息广播
     *
     * @param context
     */
    public static void sendUpdateLoginMessageBroadcast(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.UPDATE_LOGIN_MESSAGE_ACTION);
        context.sendBroadcast(intent);
    }

    /**
     * 发布显示保存按钮广播
     *
     * @param context
     */
    public static void sendVisableSaveButtonBroadcast(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.SEND_VISABLE_BUTTON_ACTION);
        context.sendBroadcast(intent);
    }

    /**
     * 刷新首页广播
     *
     * @param context
     */
    public static void sendRefreshHomeBroadcast(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.SEND_REFERSH_HOME_ACTION);
        context.sendBroadcast(intent);
    }

    /**
     * 刷新待售资产（编辑完之后）广播
     *
     * @param context
     */
    public static void sendRefreshSellAssertBroadcast(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.SEND_REFERSH_SELL_ASSERT);
        context.sendBroadcast(intent);
    }

    /**
     * 发布隐藏保存按钮广播
     *
     * @param context
     */
    public static void sendGoneSaveButtonBroadcast(@NonNull Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_ACTION.SEND_GONE_BUTTON_ACTION);
        context.sendBroadcast(intent);
    }


}
