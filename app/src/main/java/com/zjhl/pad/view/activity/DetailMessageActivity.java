package com.zjhl.pad.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.res.MessageListDetailRes;
import com.zjhl.pad.moudle.entity.res.MessageListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: DetailMessageActivity.java 消息详情
 * Author: pluto
 * Version: V1.0
 * Create: 2018/4/23 15:57
 * Changes (from 2018/4/23)
 */
public class DetailMessageActivity extends BaseActivity {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_Ricon)
    ImageView ivRicon;
    @BindView(R.id.iv_Rtv)
    TextView ivRtv;
    @BindView(R.id.iv_Rtv1)
    TextView ivRtv1;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_id_number)
    TextView tvIdNumber;
    @BindView(R.id.customer_message_list_item_text_date)
    TextView customerMessageListItemTextDate;
    @BindView(R.id._item_text_comtent)
    WebView ItemTextComtent;
    String messagetype = "";
    String messageid = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);
        ButterKnife.bind(this);
        messagetype = getIntent().getStringExtra("messagetype");
        messageid = getIntent().getStringExtra("messageid");
        //        messagetype = 1;//1政策资讯  2客户服务 3消息服务 4 系统公告
        if ("1".equals(messagetype)) {
            tvContent.setText(R.string.message_ask);
            customerMessageListDetailRet();
        } else if ("2".equals(messagetype)) {
            customerMessageListDetailRet();
            tvContent.setText(R.string.message_service);
        } else if ("3".equals(messagetype)) {
            tvContent.setText(R.string.mine_message);
            bussinessMessageListDetailRet();
        } else if ("4".equals(messagetype)) {
            tvContent.setText(R.string.message_system_publish);
            systemMessageListDetailRet();
        }

    }


    @OnClick({R.id.iv_excite, R.id.iv_Ricon, R.id.iv_Rtv, R.id.iv_Rtv1, R.id.tv_id, R.id.tv_id_number, R.id.customer_message_list_item_text_date, R.id._item_text_comtent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.iv_Ricon:
                break;
            case R.id.iv_Rtv:
                break;
            case R.id.iv_Rtv1:
                break;
            case R.id.tv_id:
                break;
            case R.id.tv_id_number:
                break;
            case R.id.customer_message_list_item_text_date:
                break;
            case R.id._item_text_comtent:
                break;
        }
    }
    public void setData(MessageListDetailRes messageListDetailRes){
        if(messageListDetailRes!=null&&messageListDetailRes.getData()!=null){
//            ItemTextComtent.setText(messageListDetailRes.getData().getTitle()+":"+messageListDetailRes.getData().getContent());
            customerMessageListItemTextDate.setText(messageListDetailRes.getData().getPublishDate());
            ItemTextComtent.loadDataWithBaseURL(null, messageListDetailRes.getData().getContent().toString(), "text/html" , "utf-8", null);
        }
    }

    /**
     /**
     * //消息 客户服务列表详情接口
     *
     * @POST(Constants.NETPATH.CUSTOMERMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> customerMessageListDetailRet(@Body RequestBody requestBody);
     */
    public void customerMessageListDetailRet() {
        MyLogger.pLog().i("消息 系统公告消息列表接口");
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setId(messageid);
        ActionPresenter.getInstance().customerMessageListDetailRet(messageListReq).enqueue(new Callback<MessageListDetailRes>() {
            @Override
            public void onResponse(Call<MessageListDetailRes> call, Response<MessageListDetailRes> response) {
                MyLogger.pLog().d("=====" + response.body().toString());
                MyLogger.pLog().d("=====" + response.body().getCode());

                if (response.body().getCode() == 300) {
                    if (response.body().getData() != null) {
                        setData(response.body());
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
    /**
     * //消息 业务消息详情接口
     *
     * @POST(Constants.NETPATH.BUSSINESSMESSAGELISTDETAIL) Call<MessageListDetailRes> bussinessMessageListDetailRet(@Body RequestBody requestBody);
     */
    public void bussinessMessageListDetailRet() {
        MyLogger.pLog().i("消息 业务消息详情接口");
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setId(messageid);
        ActionPresenter.getInstance().bussinessMessageListDetailRet(messageListReq).enqueue(new Callback<MessageListDetailRes>() {
            @Override
            public void onResponse(Call<MessageListDetailRes> call, Response<MessageListDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            setData(response.body());
                        } else {
                            MyLogger.pLog().e(response.body().getMessage());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });
    }
    /**
     * //消息 系统公告消息详情接口
     *
     * @POST(Constants.NETPATH.SYSTEMMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> systemMessageListDetailRet(@Body RequestBody requestBody);
     */
    public void systemMessageListDetailRet() {
        MyLogger.pLog().i("消息 系统公告消息详情接口");
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setId(messageid);
        ActionPresenter.getInstance().systemMessageListDetailRet(messageListReq).enqueue(new Callback<MessageListDetailRes>() {
            @Override
            public void onResponse(Call<MessageListDetailRes> call, Response<MessageListDetailRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            setData(response.body());
                        } else {
                            MyLogger.pLog().e(response.body().getMessage());
                        }
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
