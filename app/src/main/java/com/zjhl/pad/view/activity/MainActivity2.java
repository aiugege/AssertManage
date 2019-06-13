package com.zjhl.pad.view.activity;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.next.easynavigition.view.EasyNavigitionBar;
import com.vincent.filepicker.ToastUtil;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.APKVersionCodeUtils;
import com.zjhl.pad.app.utils.AppUtils;
import com.zjhl.pad.app.utils.FileUtils;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.CheckUpdateAppReq;
import com.zjhl.pad.moudle.entity.req.HobbyDeleteReq;
import com.zjhl.pad.moudle.entity.res.CheckTransferLetterRes;
import com.zjhl.pad.moudle.entity.res.CheckUpdateAppRes;
import com.zjhl.pad.moudle.entity.res.MineStateRes;
import com.zjhl.pad.moudle.entity.res.MsgCountRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.base.BaseFragment;
import com.zjhl.pad.view.fragment.HomePageFragment2;
import com.zjhl.pad.view.fragment.MarketFragment;
import com.zjhl.pad.view.fragment.MessageFragment;
import com.zjhl.pad.view.fragment.MineFragment;
import com.zjhl.pad.view.fragment.ReleaseFragment;
import com.zjhl.pad.view.views.BaseDialog;
import com.zjhl.pad.view.views.SureOrCancelDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author lzl
 */
public class MainActivity2 extends BaseActivity {
    private EasyNavigitionBar navigitionBar;

    private String[] tabText = {MyApplication.mMyApplication.getResources().getString(R.string.maintab_home), MyApplication.mMyApplication.getResources().getString(R.string.maintab_market),/* "",*/ MyApplication.mMyApplication.getResources().getString(R.string.maintab_message), MyApplication.mMyApplication.getResources().getString(R.string.maintab_mine)};
    //    private String[] tabText = {"首页","市场","","消息","我的"};
    //未选中icon
    private int[] normalIcon = {R.drawable.ic_tab_video, R.drawable.market_image,/* R.drawable.publish_image,*/ R.drawable.message_image, R.drawable.mine_image};
    //选中时icon
    private int[] selectIcon = {R.drawable.ic_tab_video_press, R.drawable.market_press,/* R.drawable.publish_image,*/ R.drawable.message_press_down, R.drawable.mine_image_press};


    private List<android.support.v4.app.Fragment> fragments = new ArrayList<>();

    private String companyId;
    private boolean islogin = false;

    SureOrCancelDialog sureOrCancelDialog;
    private String apkurl = "";
    private String versionname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //初始化View
        initView();

        checkUpdateApp();
//        MyLogger.pLog().i("发送广播");
        //用获取用户信息接口  判断登录状态是否有效
        initDataFromServer();
        registerAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE_COUNT);
        registerAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
        registerAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);

        islogin = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
        getRedNodeCount();
    }

    private void initView() {
        navigitionBar = findViewById(R.id.navigitionBar);
/*
        String url = "http://download.newnewchain.com/AssetX.apk";
        String pathbase = FileUtils.getSDPath() + "/AssetX/";
        FileUtils.makeFolders(pathbase);
        String filename = "";
        String filename2 = "";
        if (!StringUtils.isEmpty(url)) {
            filename = url.substring(url.lastIndexOf("/") + 1);
            filename2 = filename.substring(0, filename.lastIndexOf("."));
        }
//            String path0 = context.getExternalFilesDir(null) + File.separator +filename;
        String path0 = pathbase + filename;
        if (FileUtils.isFileExist(path0)) {
            AppUtils.install(MainActivity2.this, path0);
        }else {
            downloadFileWithDynamicUrlSync("http://download.newnewchain.com/AssetX.apk");
        }*/

        fragments.add(new HomePageFragment2());//首页
        fragments.add(new MarketFragment());//市场Fragment
//        fragments.add(new ReleaseFragment());//发现
        fragments.add(new MessageFragment());//消息
        fragments.add(new MineFragment());//我的Fragment


        navigitionBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigitionBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        islogin = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
                        if (position == 2) {
                            if (!islogin) {
                                navigitionBar.setMsgPointCount(2, 0);
                            } else {
                                DisPatcher.sendSystemMessage(MainActivity2.this);
                            }
                        }
                        if (position == 3) {
                            if (!islogin) {
                                startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                                //return true则拦截事件、不进行页面切换
                                return true;
                            } else {
                                fragments.get(position).onResume();
                            }
                        }
                        return false;
                    }
                })
//                .addLayoutHeight(100)   //包含加号的布局高度 背景透明  所以加号看起来突出一块
                .addIconSize(60)    //中间加号图片的大小
                .iconSize(45)     //Tab图标大小
                .tabTextSize(12)   //Tab文字大小
                .tabTextTop(-6)     //Tab文字距Tab图标的距离
                .mode(EasyNavigitionBar.MODE_ADD)
                .navigitionHeight(60)  //导航栏高度
                .addIconRule(EasyNavigitionBar.RULE_CENTER) //RULE_CENTER 加号居中addLayoutHeight调节位置 EasyNavigitionBar.RULE_BOTTOM 加号在导航栏靠下
                .addIconBottom(-5)   //加号到底部的距离
                .addIcon(R.drawable.publish_image)
                .msgPointLeft(-20)//压住图标
                .msgPointTop(10)//压住图标
                .canScroll(false)
                .hintPointLeft(-3)  //调节提示红点的位置hintPointLeft hintPointTop（看文档说明）
                .normalTextColor(getResources().getColor(R.color.common_text_gray2))   //Tab未选中时字体颜色
                .selectTextColor(getResources().getColor(R.color.blue))   //Tab选中时字体颜色
                .onAddClickListener(new EasyNavigitionBar.OnAddClickListener() {
                    @Override
                    public boolean OnAddClickEvent(View view) {
                        islogin = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
                        //跳转页面
                        if (islogin) {
                            DisPatcher.startIssueActivity(MainActivity2.this);
                        } else {
                            DisPatcher.startLoginActivity(MainActivity2.this);
//                            startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                        }
                        return false;
                    }
                }).build();
        //数字消息大于99显示99+ 小于等于0不显示，取消显示则可以navigitionBar.setMsgPointCount(2, 0)
//        navigitionBar.setMsgPointCount(2, 109);
//        navigitionBar.setMsgPointCount(0, 5);
        //红点提示 第二个参数控制是否显示
//        navigitionBar.setHintPoint(3, true);

    }

    private void getRedNodeCount() {
        //消息数目
        companyId = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        HobbyDeleteReq hobbyReq = new HobbyDeleteReq();
        hobbyReq.setId(companyId);
        ActionPresenter.getInstance().msgNodeRedRet(hobbyReq).enqueue(new Callback<MsgCountRes>() {
            @Override
            public void onResponse(Call<MsgCountRes> call, Response<MsgCountRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        String data = response.body().getData();
                        if (!TextUtils.isEmpty(data)) {
                            if ("0".equals(data)) {
                                navigitionBar.setMsgPointCount(2, 0);
                            } else {
                                int count = Integer.parseInt(data);
                                //数字消息大于99显示99+ 小于等于0不显示，取消显示则可以navigitionBar.setMsgPointCount(2, 0)
                                navigitionBar.setMsgPointCount(2, count);
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
        islogin = (boolean) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_STATUS, false);
        if (Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION.equals(intent.getAction())) {
            ToastUtils.showShort(getString(R.string.toast_login_status_timeout));
            //需要登录 清空旧用户信息
            MyApplication.mMyApplication.UpdateUserInfo(false, "", "", "", "", "", "", "");
        }
        if (Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE_COUNT.equals(intent.getAction())) {
            if (!islogin) {
                navigitionBar.selectTab(0);//未登录返回首页
                navigitionBar.setMsgPointCount(2, 0);//未登录清空消息
            } else {
                getRedNodeCount();
            }
        }
        if (Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE.equals(intent.getAction())) {
            if (!islogin) {
//                navigitionBar.selectTab(0);//未登录返回首页
//                navigitionBar.setMsgPointCount(2, 0);//未登录清空消息
            } else {
                BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity2.this);
                builder.statusBarDrawable = R.drawable.jpush_notification_icon;
                builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                        | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
                builder.notificationDefaults = Notification.DEFAULT_SOUND
                        | Notification.DEFAULT_VIBRATE
                        | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
                //注意这里的 1 ，以后会有用
                JPushInterface.setPushNotificationBuilder(1, builder);
            }
        }

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
                    } else if (response.body().getCode() == 410) {
                        DisPatcher.sendNeedLoginBroadcast(MainActivity2.this);
//                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
                    } else if (response.body().getCode() == 415) {
                        DisPatcher.sendNeedLoginBroadcast(MainActivity2.this);
//                        MyApplication.mMyApplication.UpdateUserInfo(false, "", "");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE);
        unregisterAction(Constants.BROADCAST_ACTION.RESAVE_SYSTEM_MESSAGE_COUNT);
        unregisterAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //更新app检查
    private void checkUpdateApp() {
        //消息数目
//        companyId = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_COMPANYID, "");
        final String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
        CheckUpdateAppReq checkUpdateAppReq = new CheckUpdateAppReq();
        checkUpdateAppReq.setType("2");
        ActionPresenter.getInstance().checkAppUpdateRet(checkUpdateAppReq).enqueue(new Callback<CheckUpdateAppRes>() {
            @Override
            public void onResponse(Call<CheckUpdateAppRes> call, Response<CheckUpdateAppRes> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        versionname = response.body().getData().getVersionName();
                        //成功后判断是否弹出更新框
                        if (response.body().getData().getVersionCode() > APKVersionCodeUtils.getVersionCode(MainActivity2.this)) {
//                        if (response.body().getData().getVersionCode() < APKVersionCodeUtils.getVersionCode(MainActivity2.this)) {
                            apkurl = response.body().getData().getDownloadUrl();
                            if (StringUtils.isEmpty(response.body().getData().getContent())) {

                                initSureOrCancelDialogView("", "");
                            } else {
                                if ("cn".equals(lanage)) {
                                    initSureOrCancelDialogView("", response.body().getData().getContent());
                                } else if ("en".equals(lanage)) {
                                    initSureOrCancelDialogView("", response.body().getData().getContentEn());
                                }
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

    public void initSureOrCancelDialogView(final String SureOrCancelDialogtype, String content) {
        String dialogContent = getString(R.string.mine_will_sale_sure);
        if (!StringUtils.isEmpty(content)) {
            dialogContent = content;
        }
        sureOrCancelDialog = new SureOrCancelDialog(this, new BaseDialog.OnBaseDialogListener() {
            @Override
            public void positive() {
                MyLogger.pLog().i("positive");
                //更新进程
                String url = apkurl;//"http://download.newnewchain.com/AssetX.apk";
                if(StringUtils.isEmpty(url)){
                    url = "http://download.newnewchain.com/AssetX.apk";
                }
                if(StringUtils.isEmpty(apkurl)){
                    apkurl = "http://download.newnewchain.com/AssetX.apk";
                }
                if(!StringUtils.isEmpty(url)){
                    url = url.replaceAll(".apk",versionname+".apk");
                }
                String pathbase = FileUtils.getSDPath() + "/AssetX/";
                FileUtils.makeFolders(pathbase);
                String filename = "";
                String filename2 = "";
                if (!StringUtils.isEmpty(url)) {
                    filename = url.substring(url.lastIndexOf("/") + 1);
                    filename2 = filename.substring(0, filename.lastIndexOf("."));
                }
//            String path0 = context.getExternalFilesDir(null) + File.separator +filename;
                String path0 = pathbase + filename;
                if (FileUtils.isFileExist(path0)) {
                    AppUtils.install(MainActivity2.this, path0);
                }else {
                    downloadFileWithDynamicUrlSync(apkurl);
                }
            }

            @Override
            public void positive(ResponseBean responseBean, String isSecelt) {

            }

            @Override
            public void negative(String isSelect, String companyName) {
                MyLogger.pLog().i("negative");
            }
        }, "" + dialogContent, getString(R.string.onsalelist_forfaiting_adapter_cancel), getString(R.string.onsalelist_forfaiting_adapter_sure));
        sureOrCancelDialog.show();
    }

    /**
     * /**
     * 下载文件
     *
     * @return
     * @GET Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
     * public Call<ResponseBody> downloadFileWithDynamicUrlSync(String Url) {
     * Call<ResponseBody> downloadFileWithDynamicUrlSync = mApi.downloadFileWithDynamicUrlSync(Url);
     * return downloadFileWithDynamicUrlSync;
     * }
     */
    private void downloadFileWithDynamicUrlSync(final String url) {
        ActionPresenter.getInstance().downloadFileWithDynamicUrlSync(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                MyLogger.pLog().d("LoginRes：" + response.body().toString());
//                MyLogger.pLog().d("LoginRes：" + response.body().getCode());
                if (response != null && response.body() != null) {
                    //进度开始
                    /*try {
                        InputStream inputStream = null;
                        OutputStream outputStream = null;

                        try {
                            byte[] fileReader = new byte[4096];

                            long fileSize = response.body().contentLength();
                            long fileSizeDownloaded = 0;

                            inputStream = response.body().byteStream();

                            while (true) {
                                int read = inputStream.read(fileReader);

                                if (read == -1) {
                                    break;
                                }
                                outputStream.write(fileReader, 0, read);

                                fileSizeDownloaded += read;

                                MyLogger.pLog().d("file download: " + fileSizeDownloaded + " of " + fileSize);
                            }

                            outputStream.flush();

                        } catch (IOException e) {
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }

                            if (outputStream != null) {
                                outputStream.close();
                            }
                        }
                    }catch (Exception e){

                    }*/
                    //进度结束

                    String url = apkurl;//"http://download.newnewchain.com/AssetX.apk";
                    if(StringUtils.isEmpty(url)){
                        url = "http://download.newnewchain.com/AssetX.apk";
                    }
                    if(!StringUtils.isEmpty(url)){
                        url = url.replaceAll(".apk",versionname+".apk");
                    }
                    String writtenToDisk = FileUtils.writeResponseBodyToDisk(MyApplication.mMyApplication, response.body(), url);
                    if (!StringUtils.isEmpty(writtenToDisk)) {
//                        ToastUtils.showLong(getString(R.string.toastmarket_forfaiting_detail_downfile) + writtenToDisk);
                        AppUtils.install(MainActivity2.this, writtenToDisk);
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
