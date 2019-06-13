package com.zjhl.pad.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
}
