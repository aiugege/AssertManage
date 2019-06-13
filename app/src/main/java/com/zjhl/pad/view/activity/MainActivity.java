package com.zjhl.pad.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.HobbyDeleteReq;
import com.zjhl.pad.moudle.entity.res.MineStateRes;
import com.zjhl.pad.moudle.entity.res.MsgCountRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.base.BaseFragment;
import com.zjhl.pad.view.fragment.HomePageFragment;
import com.zjhl.pad.view.fragment.HomePageFragment2;
import com.zjhl.pad.view.fragment.MarketFragment;
import com.zjhl.pad.view.fragment.MessageFragment;
import com.zjhl.pad.view.fragment.MineFragment;
import com.zjhl.pad.view.fragment.ReleaseFragment;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zjhl.pad.app.application.MyApplication.mMyApplication;


/**
 * @author lzl
 *
 */
public class MainActivity  extends BaseActivity{

    private RadioGroup mRg_main;
    private List<BaseFragment> mBaseFragment;

    /**s
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;
    private String select_tag;
    private String token_apply;
    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    private  String companyId;
    private TextView iv_node_red;
    private  String select_main;
    private  boolean islogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
        MyLogger.pLog().i("发送广播");
//        DisPatcher.sendNeedLoginBroadcast(MainActivity.this);
        //用获取用户信息接口  判断登录状态是否有效
        initDataFromServer();
        registerAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE_COUNT);
        nodeCount();

    }


    public void nodeCount(){
//
        try {
            companyId = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        }catch (Exception e){

        }

        if (!TextUtils.isEmpty(companyId)){
            getRedNodeCount();
        }

    }


    private void getRedNodeCount() {
        //消息数目

        HobbyDeleteReq hobbyReq = new HobbyDeleteReq();
        hobbyReq.setId(companyId);

        ActionPresenter.getInstance().msgNodeRedRet(hobbyReq).enqueue(new Callback<MsgCountRes>() {
            @Override
            public void onResponse(Call<MsgCountRes> call, Response<MsgCountRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300  ) {
                        String data = response.body().getData();
                        if (!TextUtils.isEmpty(data)){
                            if ("0".equals(data)){
                                iv_node_red.setVisibility(View.INVISIBLE);
                            }else {
                                iv_node_red = (TextView) findViewById(R.id.iv_node_red);
                                iv_node_red.setVisibility(View.VISIBLE);
                                iv_node_red.setText(data);
                            }
                        }


                    } else if (response.body().getCode() == 400) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else if (response.body().getCode() == 500) {
                        ToastUtils.showShort(response.body().getMessage());
                    } else {
//                        ToastUtils.showShort(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("onFailure：", "" + t.getMessage());
            }
        });

    }
    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
//        MessageFragment messageFragment = new MessageFragment();
//        initView();
        nodeCount();
//        messageFragment.initView();
//        mBaseFragment.add(new MessageFragment());//消息
//        if (Constants.BROADCAST_ACTION.SEND_VISABLE_BUTTON_ACTION.equals(intent.getAction())) {
//            ivRtv.setText("保存");
//            ivRtv.setVisibility(View.VISIBLE);
//        } else {
////            ivRtv.setText("保存");
//            ivRtv.setVisibility(View.GONE);
//        }
    }

    private void setListener() {
        mRg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架
        mRg_main.check(R.id.rb_homepager);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            islogin = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
            switch (checkedId){
                case R.id.rb_homepager://首页
                    position = 0;
                    break;
                case R.id.rb_thirdparty://市场
                    position = 1;

                    break;
                case R.id.rb_publish://发布
//                    position = 2;
                    if(islogin) {
                        DisPatcher.startIssueActivity(MainActivity.this);
                    }else{
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
//                    DisPatcher.startReviewOfferLetterActivity(MainActivity.this,"",null,1);
                    break;
                case R.id.rb_message://消息
                    position = 3;
                    break;
                case R.id.rb_mine://我的
//                    String user_token = (String) SharedPreferanceUtils.get(mMyApplication, Constants.USERINFO.USERINFO_TOKEN, "");
//                    if (TextUtils.isEmpty(user_token)){
                    if (!islogin){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }else {
                        position = 4;
                    }
                    if (!TextUtils.isEmpty(select_tag)&&"1".equals(select_tag)){
                        position = 4;
                    }
                    if (!TextUtils.isEmpty(token_apply) && "2".equals(token_apply)){
                        position = 4;
                    }
                    break;

                default:
                    position = 0;
                    break;
            }

            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent,to);

        }
    }


    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from,Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }

    }

//    private void switchFrament(BaseFragment fragment) {
//        //1.得到FragmentManger
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.fl_content, fragment);
//        //4.提交事务
//        transaction.commit();
//    }

    /**
     * 根据位置得到对应的Fragment
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);

        if(position==0){
            MyLogger.pLog().d("onStart");
//            fragment.onStart();
//            DisPatcher.sendRefreshHomeBroadcast(this);
        }
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new HomePageFragment2());//首页
        mBaseFragment.add(new MarketFragment());//市场Fragment
        mBaseFragment.add(new ReleaseFragment());//发现
        mBaseFragment.add(new MessageFragment());//消息
        mBaseFragment.add(new MineFragment());//我的Fragment


    }

    private void initView() {
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
             select_tag = extras.getString("select_tag");
             token_apply = extras.getString("token_apply");
             select_main = extras.getString("select_main");
             if ("1".equals(select_main)){
                 position = 4;
             }
        }
        setContentView(R.layout.activity_main);
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
        iv_node_red = (TextView) findViewById(R.id.iv_node_red);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE_COUNT);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    private void initDataFromServer() {
        ActionPresenter.getInstance().mineRet().enqueue(new Callback<MineStateRes>() {
            @Override
            public void onResponse(Call<MineStateRes> call, Response<MineStateRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        MineStateRes.DataBean data = response.body().getData();

                    } else if (response.body().getCode() == 400) {
//                        ToastUtils.showShort(response.body().getMessage());
                    }else if (response.body().getCode() == 410) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                    } else if (response.body().getCode() == 415) {
                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                    } else if (response.body().getCode() == 500) {
//                        ToastUtils.showShort(response.body().getMessage());
                    } else {
//                        ToastUtils.showShort(response.body().getMessage());
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
