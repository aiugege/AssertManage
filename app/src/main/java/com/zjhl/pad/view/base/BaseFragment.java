package com.zjhl.pad.view.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

//import com.victor.loading.rotate.RotateLoading;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.shaohui.bottomdialog.BottomDialog;


/**
 * 作者：尚硅谷-杨光福 on 2016/7/21 19:20
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：基类，公共类
 * CommonFrameFragment，ThirdPartyFragment,CustomFragment,OtherFragment等都要继承该类
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;

    public static final int PAGE_SIZE = 10;
//    public RotateLoading rotateLoading;
//    BottomDialog bottomDialog;

    private Dialog waitDialog;
    private Map<String,  ActivityReceiver> mReceiverMap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 强制子类重写，实现子类特有的ui
     *
     * @return
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     */
    protected void initData() {

    }



    private void registerReceiver(ActivityReceiver receiver, IntentFilter filter) {
    }

    /**
     * 短时间吐司
     */
    static Toast toastShort;

    public Toast showShortToast1(final String message) {
        if (toastShort == null) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    toastShort = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
//                }
//            });
        } else {
            toastShort.setText(message);
            toastShort.setDuration(Toast.LENGTH_SHORT);
        }
        toastShort.setGravity(Gravity.CENTER, 0, 0);
        toastShort.show();
        return toastShort;
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

    /*
       loading页面
        */
//    public void loadingDialog() {
//        WindowManager manager = getActivity().getWindowManager();
//        Display display = manager.getDefaultDisplay();
//        bottomDialog = BottomDialog.create(getFragmentManager());
//        bottomDialog.setViewListener(new BottomDialog.ViewListener() {      // 可以进行一些必要对View的操作
//            @Override
//            public void bindView(View v) {
//                // you can do bind view operation
//                rotateLoading = (RotateLoading) v.findViewById(R.id.rotateloading);
////                if (rotateLoading.isStart()) {
////                    rotateLoading.stop();
////                } else {
//                    rotateLoading.start();
////                }
//            }
//        })
//                .setLayoutRes(R.layout.dialog_loading)
//                .setDimAmount(0.3f)            // Dialog window 背景色深度 范围：0 到 1，默认是0.2f
//                .setHeight(display.getHeight())//设置满屏
//                .setCancelOutside(false);     // 点击外部区域是否关闭，默认true
////                .setTag("BottomDialog")     // 设置DialogFragment的tag
////                .show();
//    }
//
//    public void isLoading(boolean isshow) {
//        if (bottomDialog == null) {
////            bottomDialog = BottomDialog.create(getFragmentManager());
//            loadingDialog();
//        }
//        if (isshow) {
//            bottomDialog.show();
//        } else {
//            bottomDialog.dismiss();
////            rotateLoading.stop();
//        }
//    }
    /**
     * 显示网络等待
     */
    protected void showWaitDialog() {
        if (!getActivity().isFinishing()) {
            waitDialog = new Dialog(getActivity(), R.style.DialogStyle);
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
//            LocalBroadcastManager.getInstance(getActivity())
//                    .registerReceiver(receiver, filter);
            //系统广播接收器，能接受系统广播
            getActivity().registerReceiver(receiver, filter);
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
//            LocalBroadcastManager.getInstance(getActivity())
//                    .unregisterReceiver(mReceiverMap.get(action));
            //系统广播接收器，取消绑定所有广播
            getActivity().unregisterReceiver(mReceiverMap.get(action));
            mReceiverMap.remove(action);
        }
    }
    /**
     * 处理广播消息
     */
    protected void onReceive(Intent intent) {

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
                BaseFragment.this.onReceive(intent);
            }
        }
    }

}
