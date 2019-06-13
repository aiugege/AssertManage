package com.zjhl.pad.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.GlideImageLoader;
import com.zjhl.pad.app.utils.LocalManageUtil;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.moudle.entity.res.HomeAllCountRes;
import com.zjhl.pad.moudle.entity.res.HomeBannerRes;
import com.zjhl.pad.moudle.entity.res.HomeIndexRes;
import com.zjhl.pad.presenter.dispatcher.DisPatcher;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.MainActivity;
import com.zjhl.pad.view.activity.MainActivity2;
import com.zjhl.pad.view.adapter.HomeGalleryAdapter;
import com.zjhl.pad.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * | 
 * | 功能描述:
 * | 时　　间: 2018/9/6 16:20
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class HomePageFragment2 extends BaseFragment {

    @BindView(R.id.iv_excite)
    ImageView ivExcite;
    @BindView(R.id.iv_Ricon)
    ImageView ivIcon;
    Unbinder unbinder;
    @BindView(R.id.tv_content)
    TextView tvTitle;
    @BindView(R.id.home_iv_cydia)
    ImageView homeIvCydia;
    @BindView(R.id.home_tv_cydia)
    TextView homeTvCydia;
    @BindView(R.id.home_tv_cydia_common)
    TextView homeTvCydiaCommon;
    @BindView(R.id.home_iv_informationopen)
    ImageView homeIvInformationopen;
    @BindView(R.id.home_tv_informationopen)
    TextView homeTvInformationopen;
    @BindView(R.id.home_tv_informationopen_common)
    TextView homeTvInformationopenCommon;
    @BindView(R.id.home_iv_dealfast)
    ImageView homeIvDealfast;
    @BindView(R.id.home_tv_dealfast)
    TextView homeTvDealfast;
    @BindView(R.id.home_tv_dealfast_common)
    TextView homeTvDealfastCommon;
    @BindView(R.id.home_iv_security)
    ImageView homeIvSecurity;
    @BindView(R.id.home_tv_security)
    TextView homeTvSecurity;
    @BindView(R.id.home_tv_security_common)
    TextView homeTvSecurityCommon;
    @BindView(R.id.home_banner)
    Banner homeBanner;
    Unbinder unbinder1;

    @BindView(R.id.home_tv_name_libor)
    TextView homeTvNameLibor;
    @BindView(R.id.home_tv_text_libor)
    TextView homeTvTextLibor;
    @BindView(R.id.home_tv_name_shibor)
    TextView homeTvNameShibor;
    @BindView(R.id.home_tv_text_shibor)
    TextView homeTvTextShibor;
    @BindView(R.id.home_tv_name_hibor)
    TextView homeTvNameHibor;
    @BindView(R.id.home_tv_text_hibor)
    TextView homeTvTextHibor;
    @BindView(R.id.tv_id)
    TextView tvId;

    private HomeGalleryAdapter mAdapter;
    private List<Integer> mDatas;

    public static List<String> images = new ArrayList<String>();
    public static List<String> titles = new ArrayList<>();

    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homepage2_layout, null);
        unbinder = ButterKnife.bind(this, view);
//        SharedPreferanceUtils.put(LoginActivity.this,"currentid",currentId);
        registerAction(Constants.BROADCAST_ACTION.SEND_REFERSH_HOME_ACTION);

        tvTitle.setText(R.string.home_title_text);

        if ("cn".equals(lanage)) {
            ivExcite.setImageResource(R.drawable.icon_en);
        } else if ("en".equals(lanage)) {
            ivExcite.setImageResource(R.drawable.icon_cn);
        }
        initBannerData();
        initHomeIndexData();
        initBannerView(images);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String companyId = (String) SharedPreferanceUtils.get(getActivity(), Constants.USERINFO.USERINFO_COMPANYID, "");
        String token = (String) SharedPreferanceUtils.get(getActivity(), Constants.USERINFO.USERINFO_TOKEN, "");
        MyLogger.pLog().d("token = " + token);
        if (TextUtils.isEmpty(companyId) || StringUtils.isEmpty(token)) {
            //设置title
            ivIcon.setVisibility(View.VISIBLE);
            tvId.setVisibility(View.GONE);
            ivIcon.setImageResource(R.drawable.nologin_icon);
        } else if (!StringUtils.isEmpty(companyId) && !StringUtils.isEmpty(token)) {
            tvId.setVisibility(View.VISIBLE);
            tvId.setText(companyId);
            ivIcon.setVisibility(View.GONE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }


    public void initBannerView(List<String> imageurls) {
        //设置banner
        homeBanner.setImages(imageurls)
                .setBannerTitles(titles)
                .setImageLoader(new GlideImageLoader())
                .start();
        homeBanner.updateBannerStyle(BannerConfig.NOT_INDICATOR);
        homeBanner.setDelayTime(5000);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void onReceive(Intent intent) {
        super.onReceive(intent);
        if (Constants.BROADCAST_ACTION.SEND_REFERSH_HOME_ACTION.equals(intent.getAction())) {
//            onResume();
            initView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unregisterAction(Constants.BROADCAST_ACTION.SEND_REFERSH_HOME_ACTION);
    }

    @OnClick({R.id.home_rl_cydia, R.id.home_rl_informationopen, R.id.home_rl_dealfast, R.id.home_rl_security, R.id.iv_excite, R.id.iv_Ricon, R.id.iv_Rtv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_rl_cydia:
                break;
            case R.id.home_rl_informationopen:
                break;
            case R.id.home_rl_dealfast:
                break;
            case R.id.home_rl_security:
                break;
            case R.id.iv_excite:
                String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();
                if ("cn".equals(lanage)) {
                    ivExcite.setImageResource(R.drawable.icon_en);
                    SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "en");
                    LocalManageUtil.saveSelectLanguage(getActivity(), 3);
//                    MainActivity.reStart(getActivity());
                    MainActivity2.reStart(getActivity());
                } else if ("en".equals(lanage)) {
                    ivExcite.setImageResource(R.drawable.icon_cn);
                    SharedPreferanceUtils.put(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn");
                    LocalManageUtil.saveSelectLanguage(getActivity(), 1);
//                    MainActivity.reStart(getActivity());
                    MainActivity2.reStart(getActivity());
                }
//                MyApplication.setLanguage(MyApplication.mMyApplication);
//                getActivity().recreate();
                break;
            case R.id.iv_Ricon:
                DisPatcher.startLoginActivity(getActivity());
                break;
            case R.id.iv_Rtv:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void initBannerData() {
        MyLogger.pLog().i("首页banner接口");
        ActionPresenter.getInstance().homeBannerRet(null).enqueue(new Callback<HomeBannerRes>() {
            @Override
            public void onResponse(Call<HomeBannerRes> call, Response<HomeBannerRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null && response.body().getData().size() > 0) {
                            for (HomeBannerRes.DataBean listBean : response.body().getData()) {
                                images.add(listBean.getUrl());
                            }
                            initBannerView(images);
                        }
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


    ////首页libor SHIBOR HIBOR指数接口
    //     * Call<HomeIndexRes> homeIndexRet
    public void initHomeIndexData() {
        MyLogger.pLog().i("首页libor SHIBOR HIBOR指数接口");
        ActionPresenter.getInstance().homeIndexRet(null).enqueue(new Callback<HomeIndexRes>() {
            @Override
            public void onResponse(Call<HomeIndexRes> call, Response<HomeIndexRes> response) {
                if (response != null && response.body() != null) {
                    MyLogger.pLog().d("=====" + response.body().toString());
                    MyLogger.pLog().d("=====" + response.body().getCode());
                    if (response.body().getCode() == 300) {
                        if (response.body().getData() != null) {
                            for (HomeIndexRes.DataBean listBean : response.body().getData()) {
                                if (listBean != null && listBean.getCode() != null && listBean.getValue() != null) {
                                    if (StringUtils.isEquals("LIBOR", listBean.getCode())) {
                                        homeTvTextLibor.setText(listBean.getValue() + "");
                                    } else if (StringUtils.isEquals("SHIBOR", listBean.getCode())) {
                                        homeTvTextShibor.setText(listBean.getValue() + "");
                                    } else if (StringUtils.isEquals("HIBOR", listBean.getCode())) {
                                        homeTvTextHibor.setText(listBean.getValue() + "");
                                    }
                                }
                            }
                        }
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

}
