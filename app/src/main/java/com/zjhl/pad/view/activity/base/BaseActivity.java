package com.zjhl.pad.view.activity.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.zjhl.pad.app.utils.LocalManageUtil;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.PermissionDialog;
import com.zjhl.pad.app.utils.PermissionsManager;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.T;
import com.zjhl.pad.view.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/*
 * File: BaseActivity.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/3 13:18
 * Changes (from 2018/4/3)
 */
public class BaseActivity extends FragmentActivity {
    private String[] permission;
    private Dialog waitDialog;

    public static final int PAGE_SIZE = 10;
    //保存注册的receiver
    private Map<String, ActivityReceiver> mReceiverMap;

    // ----------------------获取权限检查--v4兼容包方法------------------------------------
    private PermissionDialog permissionDialog;
    private PermissionsManager perManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyLogger.pLog().i("注册广播");
//        registerAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
    }

    public boolean checkPermission_v4(final Activity context, final String... permission) {
        if (perManager == null)
            perManager = new PermissionsManager(this, new PermissionsManager.PermissionListener() {
                // 添加权限请求返回监听器并内置了一个二次提示框
                @Override
                public void getRequestPermissionCallBack(String[] permissions, boolean isAgree) {
                    // 权限没有全部获取
                    if (!isAgree) {
                        final String[] p = permissions;
                        // 向用户弹出一个提示框,包含确定和取消两个按钮
                        if (permissionDialog == null)
                            permissionDialog = new PermissionDialog(context, new PermissionDialog.PermissionCheckListener() {

                                @Override
                                public void userChoosed(boolean isChooseOk) {
                                    if (isChooseOk) {// 点击确定
                                        // 先检查一下有没有完全禁止，有的话那就拜拜了您那~~ 让系统安全应用去处理吧
                                        checkShouldShowPermission(p);
                                        // 没有就再次检查指导结束
                                        checkPermission_v4(context, permission);
                                    } else {// 点击取消
                                        // 关闭页面
                                        finish();
                                    }
                                }
                            });
                        permissionDialog.show();
                    }
                }
            });
        // 检查是否已有权限
        return perManager.checkPermission(permission);
    }

    public boolean checkPermission_v4(final PermissionsManager.PermissionListener listener, final String... permission) {
        if (perManager == null)
            perManager = new PermissionsManager(this, listener);
        return perManager.checkPermission(permission);
    }

    /**
     * 检查是否可以显示权限授予对话框
     *
     * @param permissions
     * @author Wall
     */
    public void checkShouldShowPermission(String[] permissions) {
        if (perManager != null) {
            // 检查是否已被禁止
            boolean isNeedShow = perManager.checkShouldShowPermission(permissions);
            if (isNeedShow) {
                T.getInstanse(this).showLong(R.string.exception_nopermission);
                finish();
            }
        }
    }

    /**
     * 短时间吐司
     */
//    static Toast toastShort;
//
//    public Toast showShortToast1(final String message) {
//        if (toastShort == null) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    toastShort = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
//                }
//            });
//        } else {
//            toastShort.setText(message);
//            toastShort.setDuration(Toast.LENGTH_SHORT);
//        }
//        toastShort.setGravity(Gravity.CENTER, 0, 0);
//        toastShort.show();
//        return toastShort;
//    }

    /**
     * 显示网络等待
     */
    protected void showWaitDialog() {
        if (!isFinishing()) {
            waitDialog = new Dialog(this, R.style.DialogStyle);
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View waitView = layoutInflater.inflate(R.layout.loading_dialog, null);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            params.alpha = 0.0F;
            waitDialog.addContentView(waitView, params);
            waitDialog.show();
        }
    }

    /**
     * 关闭等待
     */
    protected void closeWaitDialog() {
        if (null != waitDialog) {
            waitDialog.cancel();
            waitDialog = null;
        }
    }


    /**
     * 注册广播的action
     *
     * @param action
     */
    public void registerAction(String action) {
        if (StringUtils.isBlank(action)) {
            return;
        }

        if (mReceiverMap == null) {
            mReceiverMap = new HashMap<String, ActivityReceiver>(4);
        }

        if (!mReceiverMap.containsKey(action)) {
            ActivityReceiver receiver = new ActivityReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(action);
            //自定义广播接收器，只接收自定义广播
//            LocalBroadcastManager.getInstance(this)
//                    .registerReceiver(receiver, filter);
            //系统广播接收器，能接受系统广播
            registerReceiver(receiver, filter);
            mReceiverMap.put(action, receiver);
        }
    }

    /**
     * 注销广播的action
     *
     * @param action
     */
    public void unregisterAction(String action) {
        if (StringUtils.isBlank(action) || mReceiverMap == null) {
            return;
        }

        if (mReceiverMap.containsKey(action)) {
            //自定义广播接收器，只取消绑定自定义广播
//            LocalBroadcastManager.getInstance(this)
//                    .unregisterReceiver(mReceiverMap.get(action));
            //系统广播接收器，取消绑定所有广播
            unregisterReceiver(mReceiverMap.get(action));
            mReceiverMap.remove(action);
        }
    }


    /**
     * 处理广播消息
     */
    protected void onReceive(Intent intent) {
//        MyLogger.pLog().i("接受广播"+intent.getAction());
//        if(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION.equals(intent.getAction())){
//            //
//            DisPatcher.startMainActivity(BaseActivity.this);
//        }
    }


    /**
     * 广播接收器
     *
     * @author pluto
     */
    private class ActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                MyLogger.pLog().d(getClass().getSimpleName() + " action:" + intent.getAction());
                BaseActivity.this.onReceive(intent);
            }
        }
    }


    //通过反射设置tablayout的下划线宽度
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        MyLogger.pLog().i("销毁广播");
//        unregisterAction(Constants.BROADCAST_ACTION.NEED_LOGIN_ACTION);
    }

    //    @Override
//    protected void attachBaseContext(Context newBase) {
//        String language = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
//        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, language));
//    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }
    /**
     *
     * 设置语言
     *
     * @param context
     *
     */
//    public static void setLanguage(Context context) {
//        String language = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
//        Resources res = context.getResources();
//        Configuration config = res.getConfiguration();
//        if("cn".equals(language)) {
//            config.locale = Locale.CHINESE;//选择语言类别,这个是中文
//        }else if("en".equals(language)) {
//            config.locale = Locale.ENGLISH;//选择语言类别,这个是英文
//        }
//        res.updateConfiguration(config, res.getDisplayMetrics());
//    }


}
