package com.zjhl.pad.view.fragment;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.HobbyDeleteReq;
import com.zjhl.pad.moudle.entity.req.MessageEvent;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.res.BusinessMessage;
import com.zjhl.pad.moudle.entity.res.HobbyDeleteRes;
import com.zjhl.pad.moudle.entity.res.MessageListRes;
import com.zjhl.pad.moudle.entity.res.MsgCountRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.LoginActivity;
import com.zjhl.pad.view.activity.MainActivity;

import com.zjhl.pad.view.activity.MainActivity2;
import com.zjhl.pad.view.adapter.CustomerMessageListAdapter;
import com.zjhl.pad.view.adapter.SystemMsgAdapter;
import com.zjhl.pad.view.base.BaseFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/*
 * File: MyAssetsFragment.java 消息
 * Author: 刘子龙
 * Version: V100R001C01
 * Create: 2018/4/2 19:27
 * Changes (from 2018/4/2)
 */
public class MessageFragment extends BaseFragment {


    private static final String TAG = MessageFragment.class.getSimpleName();//"MyAssetsFragment"
    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.tv_busniss)
    TextView tvBusniss;
    @BindView(R.id.tv_system_publish)
    TextView tvSystemPublish;
    @BindView(R.id.tv_advice)
    TextView tvAdvice;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.rg_main)
    LinearLayout rgMain;
    Unbinder unbinder;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
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
    private  View view;

    @BindView(R.id.no_massge)
    TextView noMassge;


    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;

    @BindView(R.id.rl_message_bk)
    RelativeLayout rlMessageBk;

    @BindView(R.id.tv_phone_number_address)
    TextView tv_phoneNumberAddress;

    private int mNextRequestPage = 1;
    private int messagetype = 3;//1政策资讯  2客户服务 3消息服务 4 系统公告

    private CustomerMessageListAdapter customerMessageListAdapter;
    private SystemMsgAdapter systemMsgAdapter;
    private  String assetsType;
    private int mposition;
    private TextView tv_content;
    private  int id_msg;
//    private  MainActivity mainActivity;
    private String lanage;
    private LinearLayout llEmail;
    private LinearLayout llPhone;
    private LinearLayout llAndress;

    private boolean islogin = false;
    @Override
    protected View initView() {
        view = View.inflate(mContext, R.layout.fragment_message_center, null);
//         llEmail = (LinearLayout)view.findViewById(R.id.ll_email);
//        llPhone = (LinearLayout) view.findViewById(R.id.ll_phone);
//        llAndress = (LinearLayout)view.findViewById(R.id.ll_msg_andress);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        rvList.setLayoutManager(new LinearLayoutManager(MyApplication.mMyApplication));
        registerAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
        tvContent.setText(R.string.message_center);

        ivExcite.setVisibility(View.GONE);
        initRefreshLayout();
        initAdapter();
        customerMessageListRet();
         lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
        refresh();
//        initItemListener();
        bussinessMessageListRet();
        return rootView;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
        unbinder.unbind();
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        //收到极光推送，请求接口
//        bussinessMessageListRet();
//         mainActivity = new MainActivity();
//        mainActivity.nodeCount();
        DisPatcher.sendSystemMessageCount(getActivity());
        islogin = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
        if (Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE.equals(intent.getAction())) {
            if (!islogin) {

            }else {
                messagetype = 3;//1政策资讯  2客户服务 3业务消息 4 系统公告
                rvList.setVisibility(View.VISIBLE);
                setChangeAdapter();
                refresh();
            }
        }
    }

    @OnClick({R.id.tv_busniss, R.id.tv_system_publish, R.id.tv_advice, R.id.tv_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_busniss:
                //业务消息
                messagetype = 3;//1政策资讯  2客户服务 3业务消息 4 系统公告
                rvList.setVisibility(View.VISIBLE);
                setChangeAdapter();
                refresh();
                break;
            case R.id.tv_system_publish:
                messagetype = 4;//1政策资讯  2客户服务 3消息服务 4 系统公告
                rvList.setVisibility(View.VISIBLE);
                setChangeAdapter();
                refresh();
                //系统公告
                break;
            case R.id.tv_advice:
                messagetype = 1;//1政策资讯  2客户服务 3消息服务 4 系统公告
                rvList.setVisibility(View.VISIBLE);
                setChangeAdapter();
                refresh();
                //政策咨询
//                customerMessageListRet();
                break;
            case R.id.tv_service:
                messagetype = 2;//1政策资讯  2客户服务 3消息服务 4 系统公告
                rvList.setVisibility(View.GONE);
                setChangeAdapter();
                refresh();

                break;
        }
    }

    private void initAdapter() {
        List<MessageListRes.DataBean> data = new ArrayList<>();
        customerMessageListAdapter = new CustomerMessageListAdapter(data);

        List<BusinessMessage.ListBean> data_messgs = new ArrayList<>();
         systemMsgAdapter = new SystemMsgAdapter(data_messgs);
        systemMsgAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        customerMessageListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });


        setChangeAdapter();


    }


    private void setChangeAdapter() {
        if (messagetype == 3){
            systemMsgAdapter.openLoadAnimation();
            rvList.setAdapter(systemMsgAdapter);
        }else if (messagetype == 2 || messagetype == 1 || messagetype == 4){
            customerMessageListAdapter.openLoadAnimation();
            rvList.setAdapter(customerMessageListAdapter);
        }
        initItemListener();
    }

    public void initItemListener() {


        customerMessageListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DisPatcher.startDetailMessageActivity(getActivity(), messagetype + "", ((MessageListRes.DataBean) adapter.getItem(position)).getId() + "");
            }
        });

        systemMsgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()  {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                 mposition = position;
                 tv_content = (TextView) view.findViewById(R.id.tv_number);
                String assetsId = systemMsgAdapter.getItem(position).getAssetsId();
                String messageNotice = systemMsgAdapter.getItem(position).getMessageNotice();
                String linkName = systemMsgAdapter.getItem(position).getLinkName();
                String getcType = systemMsgAdapter.getItem(position).getcType();
                //福费廷、保理类型
                 assetsType = systemMsgAdapter.getItem(position).getAssetsType();

                //消息id
                id_msg = systemMsgAdapter.getItem(position).getId();
                systemMsgAdapter.getItem(position).setReadState("2");
                if (!TextUtils.isEmpty(assetsId)){
                    SharedPreferanceUtils.put(getActivity(),"messge_assertid",assetsId);
                }
                if (!TextUtils.isEmpty(getcType)){
                    SharedPreferanceUtils.put(getActivity(),"messge_ctype",getcType);
                }
                if (!TextUtils.isEmpty(assetsType)){
                    SharedPreferanceUtils.put(getActivity(),"messge_assetsType",assetsType);
                }
                if (!TextUtils.isEmpty(getcType)){
                    if ("3".equals(getcType)){
                        if ("1".equals(assetsType)){
                            DisPatcher.startMarketForfaitingDetailActivity(mContext, assetsId, "");
                        }else if ("2".equals(assetsType)){
                            DisPatcher.startMarketFactoringDetailActivity(mContext, assetsId, "");
                        }

                    }


                }
                systemMsgAdapter.notifyDataSetChanged();
                upDataSystemMsg();

//                if ("1".equals(assetsType)){
//                    DisPatcher.startMarketForfaitingDetailActivity(mContext, assetsId, "");
//                }else if ("2".equals(assetsType)){
//                    DisPatcher.startMarketFactoringDetailActivity(mContext,  assetsId,"");
//                }


            }


        });

        systemMsgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
                tv_number.setLongClickable(false);
                tv_number.setTextIsSelectable(false);
//                tv_number.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
//                    @Override
//                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onDestroyActionMode(ActionMode actionMode) {
//
//                    }
//                });
                //福费廷、保理类型
                assetsType = systemMsgAdapter.getItem(position).getAssetsType();
                String assetsId = systemMsgAdapter.getItem(position).getAssetsId();
                String getcType = systemMsgAdapter.getItem(position).getcType();
                if (!TextUtils.isEmpty(assetsId)){
                    SharedPreferanceUtils.put(getActivity(),"messge_assertid",assetsId);
                }

                if (!TextUtils.isEmpty(getcType)){
                    SharedPreferanceUtils.put(getActivity(),"messge_ctype",getcType);
                }
                if (!TextUtils.isEmpty(getcType)){
                    if ("3".equals(getcType)){
                        if ("1".equals(assetsType)){
                            DisPatcher.startMarketForfaitingDetailActivity(mContext, assetsId, "");
                        }else if ("2".equals(assetsType)){
                            DisPatcher.startMarketFactoringDetailActivity(mContext, assetsId, "");
                        }

                    }


                }



            }
        });
        customerMessageListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemLongClick: ");
//                Toast.makeText(getActivity(), "onItemLongClick" + position, Toast.LENssGTH_SHORT).show();
                return true;
            }
        });
        customerMessageListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()){
//                    case R.id.market_item_text2_tv1:
//                        ToastUtils.showShort("111111111111111");
//                        break;
//                    case R.id.market_item_text2_tv2:
//                        ToastUtils.showShort("222222222");
//                        break;
//                    case R.id.market_item_text2_tv3:
//
//                        ToastUtils.showShort("3333333333");
//                        break;
//
//                }
                MyLogger.pLog().d("onItemChildClick: ");

//                Toast.makeText(getActivity(), "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        customerMessageListAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                MyLogger.pLog().d("onItemChildLongClick: ");
//                Toast.makeText(getActivity(), "onItemChildLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void upDataSystemMsg() {
        //消息-消息状态更改为已读
        HobbyDeleteReq hobbyReq = new HobbyDeleteReq();
        hobbyReq.setId(id_msg+"");

        ActionPresenter.getInstance().updateMessageListRet(hobbyReq).enqueue(new Callback<MessageListRes>() {
            @Override
            public void onResponse(Call<MessageListRes> call, Response<MessageListRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300  ) {
                        DisPatcher.sendSystemMessageCount(getActivity());
//                        refresh();
                    } else if (response.body().getCode() == 400) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else if (response.body().getCode() == 500) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else {
                        ToastUtils.showShort(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }

    private void initRefreshLayout() {
        //启用刷新
        swipeLayout.setRefreshing(false);
        //禁用下拉刷新
//        swipeLayout.setEnabled(false);
        swipeLayout.setColorSchemeResources(R.color.blue);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        @SuppressLint("RestrictedApi") View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_market_foot_view, (ViewGroup) rvList.getParent(), false);
        if (type == 1) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.item_market_more_button);
//            imageView.setImageResource(R.mipmap.rm_icon);
//            ToastUtils.showShort("点击了更多资产111");
        }
        view.setOnClickListener(listener);
        return view;
    }


    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerMessageListAdapter.removeFooterView(v);
            }
        };
    }

    /**
     * //消息 业务消息列表接口
     *
     * @POST(Constants.NETPATH.BUSSINESSEMESSAGELIST) Call<ReviewOfferSubmitLetterOnSaleListRes> bussinessMessageListRet(@Body RequestBody requestBody);
     */
    public void bussinessMessageListRet() {
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setPage(mNextRequestPage + "");
        messageListReq.setPageSize(PAGE_SIZE + "");
//        messageListReq.setType(messagetype+"");
        ActionPresenter.getInstance().bussinessMessageListRet(messageListReq).enqueue(new Callback<BusinessMessage>() {
            @Override
            public void onResponse(Call<BusinessMessage> call, Response<BusinessMessage> response) {


                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());

                    if (response.body().getCode() == 300) {
                        if (response.body().getList() != null) {
                            if (mNextRequestPage == 1) {
                                setYwu(true, response.body().getList());
                            } else if (mNextRequestPage > 1 && mNextRequestPage <=response.body().getTotalPage()) {
                                setYwu(false, response.body().getList());
                            }else {
                                systemMsgAdapter.loadMoreEnd(false);
                            }

                            systemMsgAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
//                            noMassge.setVisibility(View.GONE);
//                            llEmail.setVisibility(View.GONE);
//                            llAndress.setVisibility(View.GONE);
//
//                            llPhone.setVisibility(View.GONE);
                            rlMessageBk.setVisibility(View.GONE);
                        }else {
//                            noMassge.setVisibility(View.VISIBLE);
//                            llEmail.setVisibility(View.VISIBLE);
//                            llPhone.setVisibility(View.VISIBLE);
//                            llAndress.setVisibility(View.VISIBLE);
                            rlMessageBk.setVisibility(View.VISIBLE);
                        }
                    } else {
                        setYwu(true,new ArrayList(){});
                        systemMsgAdapter.setEnableLoadMore(true);
                        swipeLayout.setRefreshing(false);
                        rlMessageBk.setVisibility(View.GONE);
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


    private void setYwu(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            systemMsgAdapter.setNewData(data);
        } else {
            if (size > 0) {
                systemMsgAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            systemMsgAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            systemMsgAdapter.loadMoreComplete();
        }
    }

    //消息 客户服务列表接口
    public void customerMessageListRet() {
        MessageListReq messageListReq = new MessageListReq();
        messageListReq.setPage(mNextRequestPage + "");
        messageListReq.setPageSize(PAGE_SIZE + "");
        messageListReq.setType(messagetype + "");
        ActionPresenter.getInstance().customerMessageListRet(messageListReq).enqueue(new Callback<MessageListRes>() {
            @Override
            public void onResponse(Call<MessageListRes> call, Response<MessageListRes> response) {
                if (response != null && response.body() != null) {
                    if (response != null && response.body() != null) {

                        if (response.body().getCode() == 300) {
                            if (response.body().getData() != null) {
                                if (mNextRequestPage == 1) {
                                    setData(true, response.body().getData());
                                } else if (mNextRequestPage > 1 && mNextRequestPage <=response.body().getTotalPage()) {
                                    setData(false, response.body().getData());
                                }else {
                                    customerMessageListAdapter.loadMoreEnd(false);
                                }

                                customerMessageListAdapter.setEnableLoadMore(true);
                                swipeLayout.setRefreshing(false);
                            }
                        } else {
                            customerMessageListAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);
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
     * //消息 系统公告消息列表接口
     *
     * @POST(Constants.NETPATH.SYSTEMEMESSAGELIST) Call<MessageListRes> systemMessageListRet(@Body RequestBody requestBody);
     * //1政策资讯  2客户服务 3消息服务 4 系统公告
     */
    public void systemMessageListRet() {
        final MessageListReq messageListReq = new MessageListReq();
        messageListReq.setPage(mNextRequestPage + "");
        messageListReq.setPageSize(PAGE_SIZE + "");
        if (messagetype == 4){
            //系统公告
            messageListReq.setType("3");
        }else if (messagetype == 2){
            messageListReq.setType("2");
        }else if (messagetype == 1){
            messageListReq.setType("1");
        }

//        messageListReq.setType(messagetype+"");
        ActionPresenter.getInstance().systemMessageListRet(messageListReq).enqueue(new Callback<MessageListRes>() {
            @Override
            public void onResponse(Call<MessageListRes> call, Response<MessageListRes> response) {
                if (response != null && response.body() != null) {

                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {

                            if (response.body().getData().size()>0) {

                                if (messagetype == 2) {
//                                    noMassge.setVisibility(View.VISIBLE);
//                                    llEmail.setVisibility(View.VISIBLE);
//                                    llPhone.setVisibility(View.VISIBLE);
//                                    llAndress.setVisibility(View.VISIBLE);
                                    rlMessageBk.setVisibility(View.VISIBLE);

                                } else {
                                    rlMessageBk.setVisibility(View.GONE);

                                }

                                if (mNextRequestPage == 1) {
                                    if (messagetype!=2){
                                        setData(true, response.body().getData());
                                    }


                                } else if (mNextRequestPage > 1 && mNextRequestPage <=response.body().getTotalPage()) {
                                    if (messagetype!=2){
                                        setData(false, response.body().getData());
                                    }

                                }else {
                                    if (messagetype!=2){
                                        customerMessageListAdapter.loadMoreEnd(false);
                                    }

                                }

                                if (messagetype!=2){
                                    customerMessageListAdapter.setEnableLoadMore(true);
                                    swipeLayout.setRefreshing(false);
                                }


//                                noMassge.setVisibility(View.GONE);
//                                llEmail.setVisibility(View.GONE);
//                                llPhone.setVisibility(View.GONE);
//                                llAndress.setVisibility(View.GONE);
//                                rlMessageBk.setVisibility(View.GONE);
                            }else {
                                if (messagetype == 2){
//                                    noMassge.setVisibility(View.VISIBLE);
//                                    llEmail.setVisibility(View.VISIBLE);
//                                    llPhone.setVisibility(View.VISIBLE);
//                                    llAndress.setVisibility(View.VISIBLE);
                                    rlMessageBk.setVisibility(View.VISIBLE);


                                }else {
                                    rlMessageBk.setVisibility(View.GONE);

                                }
                            }

                        }
                    } else {
                            customerMessageListAdapter.setEnableLoadMore(true);
                            swipeLayout.setRefreshing(false);

//                        showShortToast1(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });
    }

    private void refresh() {
        //下拉刷新
        mNextRequestPage = 1;
        customerMessageListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        systemMsgAdapter.setEnableLoadMore(false);
//        customerMessageListRet();
//        messagetype = 1;//1政策资讯  2客户服务 3消息服务 4 系统公告
        if (1==messagetype) {
            systemMessageListRet();
        } else if (2==messagetype) {
            systemMessageListRet();
        } else if (3==messagetype) {
            bussinessMessageListRet();
        } else if (4==messagetype) {
            systemMessageListRet();
        }
        //请求成功

    }

    private void loadMore() {
//        customerMessageListRet();
        if (1==messagetype) {
            systemMessageListRet();
        } else if (2==messagetype) {
            systemMessageListRet();
        } else if (3==messagetype) {
            bussinessMessageListRet();
        } else if (4==messagetype) {
            systemMessageListRet();
        }
        //加载更多  请求带当前第几页
    }

    private void setData(boolean isRefresh, List data) {
        //增加页码  设置数据
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            customerMessageListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                customerMessageListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            customerMessageListAdapter.loadMoreEnd(isRefresh);
//            ToastUtils.showShort("当前已是最新数据");
        } else {
            customerMessageListAdapter.loadMoreComplete();
        }
    }

//    private class TextClick extends ClickableSpan {
//
//        @Override
//        public void onClick(View widget) {
//            //在此处理点击事件
//            if ("1".equals(assetsType)){
//                DisPatcher.startMarketForfaitingDetailActivity(getActivity(),  systemMsgAdapter.getItem(mposition).getAssetsId() + "", "");
//            }else if ("2".equals(assetsType)){
//                DisPatcher.startMarketFactoringDetailActivity(getActivity(),  systemMsgAdapter.getItem(mposition).getAssetsId() + "", "");
//            }
//
//        }
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            ds.setColor(Color.RED);
//        }
//    }
}
