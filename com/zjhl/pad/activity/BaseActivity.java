package com.zjhl.pad.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.atguigu.android.R;
import com.zjhl.pad.utils.PermissionDialog;
import com.zjhl.pad.utils.PermissionsManager;
import com.zjhl.pad.utils.T;

/*
* File: BaseActivity.java 
* Author: DELL 
* Version: V1.0
* Create: 2018/4/3 13:18 
* Changes (from 2018/4/3) 
*/
public class BaseActivity extends Activity {
    private String[] permission;
    private Dialog waitDialog;

    // ----------------------获取权限检查--v4兼容包方法------------------------------------
    private PermissionDialog permissionDialog;
    private PermissionsManager perManager;
    public boolean checkPermission_v4(final FragmentActivity context, final String... permission) {
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
    static Toast toastShort;

    public Toast showShortToast1(final String message) {
        if (toastShort == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toastShort = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                }
            });
        } else {
            toastShort.setText(message);
            toastShort.setDuration(Toast.LENGTH_SHORT);
        }
        toastShort.setGravity(Gravity.CENTER, 0, 0);
        toastShort.show();
        return toastShort;
    }

    /**
     * 显示网络等待
     */
    protected void showWaitDialog() {
        if(!isFinishing()) {
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


}
