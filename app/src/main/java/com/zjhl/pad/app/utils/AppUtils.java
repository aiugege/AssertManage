package com.zjhl.pad.app.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.util.List;

import static com.lzy.okhttputils.OkHttpUtils.getContext;

/**
 * AppUtils
 * <ul>
 * <li>{@link AppUtils#isNamedProcess(Context, String)}</li>
 * </ul>
 * |
 * | 功能描述:
 * | 时　　间: 2018/4/19 14:58
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class AppUtils {

    private AppUtils() {
        throw new AssertionError();
    }

    /**
     * whether this process is named with processName
     *
     * @param context
     * @param processName
     * @return <ul>
     * return whether this process is named with processName
     * <li>if context is null, return false</li>
     * <li>if {@link ActivityManager#getRunningAppProcesses()} is null, return false</li>
     * <li>if one process of {@link ActivityManager#getRunningAppProcesses()} is equal to processName, return
     * true, otherwise return false</li>
     * </ul>
     */
    public static boolean isNamedProcess(Context context, String processName) {
        if (context == null) {
            return false;
        }

        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
        if (processInfoList == null || processInfoList.size() == 0) {
            return false;
        }

        for (RunningAppProcessInfo processInfo : processInfoList) {
            if (processInfo != null && processInfo.pid == pid
                    && (processName == processInfo.processName || (processName != null && processName.equals(processInfo.processName)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * whether application is in background
     * <ul>
     * <li>need use permission android.permission.GET_TASKS in Manifest.xml</li>
     * </ul>
     *
     * @param context
     * @return if application is in background return true, otherwise return false
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 安装应用
     *
     * @param context
     * @param filePath
     * @return
     */
    public static boolean installApk(Context context, String filePath) {
        if (context == null || FileUtils.getFileSize(filePath) <= 0) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) { // Android 7.0 以上
//            fileUri = FileProvider.getUriForFile(context, "com.zjhl.pad.view", new File(filePath));
            // UpdateConfig.FILE_PROVIDER_AUTH 即是在清单文件中配置的authorities
            fileUri = FileProvider.getUriForFile(context, "com.zjhl.pad.view", new File(filePath));
        } else {
            fileUri = Uri.fromFile(new File(filePath));
        }
        intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
//        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }

    /**
     * 安装下载完成的apk
     *
     * @param file
     */
    public static void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.e("installApk: ", file.getAbsolutePath());
        intent.setDataAndType(getUriFromFile(file), "application/vnd.android.package-archive");
        //解决startActivity采取的上下文的Context而不是Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //解决手机安装软件的权限问题
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        getContext().startActivity(intent);
    }

    /**
     * 兼容Android版本获取Uri
     *
     * @param file
     * @return
     */
    public static Uri getUriFromFile(File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) { // Android 7.0 以上
            fileUri = FileProvider.getUriForFile(getContext(), "com.zjhl.pad.view", file);
            // UpdateConfig.FILE_PROVIDER_AUTH 即是在清单文件中配置的authorities
//            fileUri = FileProvider.getUriForFile(context, UpdateConfig.FILE_PROVIDER_AUTH, apk);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    /**
     * 通过隐式意图调用系统安装程序安装APK
     */
    public static void install(Context context,String path) {
//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "myApp.apk");
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(context, "com.zjhl.pad.view.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

}