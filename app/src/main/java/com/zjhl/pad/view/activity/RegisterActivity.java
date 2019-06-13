package com.zjhl.pad.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterDictionaryReq;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.moudle.entity.res.RegisterDictionaryRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: RegistActivity1.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/28 13:13
 * Changes (from 2018/4/28)
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.regist_iv_logo)
    ImageView registIvLogo;
    private Button byys;
    private PopupWindow window;
    private int tag;

    //    private List<Map<String, Object>> dataListType;
    private List<String> dataListType;
    private List<String> dataListType2;
    /**
     * 国家——————1.地区
     */
    private List<String> dataListTypeAraer;
    private String[] array1_arear;
    private List<RegisterCountryArearRes.DataBean> data_arear;
    private List<String> dataListTypeCountry;
    //第一级选择的某个item
    private String id_area;

    /**
     * 注册地————国家
     */
    private Button mtv_select_country;
    private List<RegisterDictionaryRes.DataBean> data_yiji;
    private List<RegisterDictionaryRes.DataBean> data;
    private ListView lsvMore;
    private SimpleAdapter adapter_type;
    private String[] array;
    private String[] array_country;
    private String[] array1;
    private String code_id;
    private String jigou_seconde_id;
    private String country_id;
    String jigou_Id;
    private List<RegisterCountryArearRes.DataBean> data_country;

    private Button spinner_type;
    private Button tv_select_city1;
    private TextView bt_find_password_next;
    private String code_two;
    private String code_first;
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_msg);
        ButterKnife.bind(this);
        initView();
        initData();
        initDataCoutryCity();
        if ("cn".equals(lanage)) {
            registIvLogo.setImageResource(R.drawable.register_cn_newlogo1);
        } else if ("en".equals(lanage)) {
            registIvLogo.setImageResource(R.drawable.register_en_newlogo1);
        }

    }

    private void initDataCoutryCity() {

        showWaitDialog();
        RegisterCounrtyArear registerCounrtyArear = new RegisterCounrtyArear();
        registerCounrtyArear.setName("");
        registerCounrtyArear.setId("");

        ActionPresenter.getInstance().registerDictionaryArearRet(registerCounrtyArear).enqueue(new Callback<RegisterCountryArearRes>() {
            @Override
            public void onResponse(Call<RegisterCountryArearRes> call, Response<RegisterCountryArearRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        closeWaitDialog();
                        data_arear = response.body().getData();
                        dataListTypeAraer.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < data_arear.size(); i++) {
                            dataListTypeAraer.add(data_arear.get(i).getName());
                        }
                        int size = dataListTypeAraer.size();
                        array1_arear = (String[]) dataListTypeAraer.toArray(new String[size]);
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void initData() {
        RegisterDictionaryReq registerDictionaryReq = new RegisterDictionaryReq();
        //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
        registerDictionaryReq.setGroupId("institutionType");

        ActionPresenter.getInstance().registerDictionaryRet(registerDictionaryReq).enqueue(new Callback<RegisterDictionaryRes>() {
            @Override
            public void onResponse(Call<RegisterDictionaryRes> call, Response<RegisterDictionaryRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        data_yiji = response.body().getData();
                        dataListType.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < data_yiji.size(); i++) {
                            dataListType.add(data_yiji.get(i).getName());
                        }
                        int size = dataListType.size();
                        array1 = (String[]) dataListType.toArray(new String[size]);
                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void initView() {
        byys = (Button) findViewById(R.id.bt_spinner);
        //第二个选择框
        spinner_type = (Button) findViewById(R.id.spinner_type);

        //注册地————地区
        tv_select_city1 = (Button) findViewById(R.id.tv_select_city1);
        //注册地————国家1
        mtv_select_country = (Button) findViewById(R.id.tv_select_country);
        //mtv_select_country
        //下一步
        bt_find_password_next = (TextView) findViewById(R.id.bt_find_password_next);

//        dataListType = new ArrayList<Map<String, Object>>();
        dataListType = new ArrayList<String>();
        dataListType2 = new ArrayList<String>();
        dataListTypeAraer = new ArrayList<String>();
        dataListTypeCountry = new ArrayList<String>();

        /**
         * 选择注册地
         */

        //注册地
        tv_select_city1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopWindowCity();
            }
        });

        //国家
        mtv_select_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(tv_select_city1.getText().toString().trim())) {
                    showPopWindowCoutry();
                } else {
                    ToastUtils.showShort(getString(R.string.hint_issue_forfaiting_sell_area));
                }

            }
        });
        /**
         * 机构类型---1
         */
        byys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopWindow();
            }
        });

        bt_find_password_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jigou_first = byys.getText().toString().trim();
                String jigou_seconde = spinner_type.getText().toString().trim();
                String area = tv_select_city1.getText().toString().trim();
                String country = mtv_select_country.getText().toString().trim();
                if (!TextUtils.isEmpty(jigou_first) && !TextUtils.isEmpty(jigou_seconde) && !TextUtils.isEmpty(area) && !TextUtils.isEmpty(country)) {
                    Intent intent = new Intent(RegisterActivity.this, RegisterNextActivity.class);
                    intent.putExtra("jigou1", code_first);
                    intent.putExtra("jigou2", code_two);
                    intent.putExtra("country", country_id);
                    intent.putExtra("id_area", id_area);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                } else {
//                    ToastUtils.showShort(getString(R.string.register_select_ziliao));
                }

            }
        });
        /**
         * 机构类型---2
         */
        spinner_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(byys.getText().toString().trim())) {
                    if (spinner_type.getText().toString().trim() != null) {
                        initPopWindow2();
                    } else {
                        ToastUtils.showShort(getString(R.string.register_select_origin));
                    }
                } else {
                    ToastUtils.showShort(getString(R.string.register_select_origin));
                }


            }
        });

    }

    @SuppressLint("NewApi")
    private void showPopWindowCoutry() {
        try {
            View popupView = getLayoutInflater().inflate(R.layout.layout_country, null);
            ListView mlist_country = (ListView) popupView.findViewById(R.id.list_select_country);
            if (dataListTypeCountry != null) {
                mlist_country.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataListTypeCountry));
            }

            window = new PopupWindow(popupView, 300, 400);
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
            window.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            window.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            window.update();
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            window.showAsDropDown(mtv_select_country, 0, 20, Gravity.RIGHT);
            mlist_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    if (array_country.length > 0) {
                        mtv_select_country.setText(array_country[position]);
//                        country_id = mtv_select_country.getText().toString().trim();
                        country_id = data_country.get(position).getId();
                    }

                    if (dataListTypeCountry.size() > 0) {
                        //国家id
                        country_id = data_country.get(position).getId();
                    }

                    window.dismiss();


                }
            });
        } catch (Exception e) {

        }

    }

    private void getSecondeCountryList() {
        showWaitDialog();
        //选择国家
        RegisterSelectCountry registerselectReq = new RegisterSelectCountry();
        registerselectReq.setAreaId(id_area);

        ActionPresenter.getInstance().registerDictionaryRet(registerselectReq).enqueue(new Callback<RegisterCountryArearRes>() {
            @Override
            public void onResponse(Call<RegisterCountryArearRes> call, Response<RegisterCountryArearRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        closeWaitDialog();
//                        data = response.body().getData();
                        data_country = response.body().getData();
                        dataListTypeCountry.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < RegisterActivity.this.data_country.size(); i++) {
                            dataListTypeCountry.add(RegisterActivity.this.data_country.get(i).getName());
                        }
                        int size = dataListTypeCountry.size();
                        array_country = (String[]) dataListTypeCountry.toArray(new String[size]);
                        if (data_country.size() > 0) {
                            mtv_select_country.setText(RegisterActivity.this.data_country.get(0).getName());
//                            country_id = mtv_select_country.getText().toString().trim();
                            country_id = data_country.get(0).getId();
                        }


                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    /**
     * 国家:注册地————1
     */
    @SuppressLint("NewApi")
    private void initPopWindowCity() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_country_list1, null);
        ListView list_country = (ListView) popupView.findViewById(R.id.list_country);
        if (dataListTypeAraer != null) {
            list_country.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataListTypeAraer));
        }

        final PopupWindow window_arear = new PopupWindow(popupView, 300, 400);
        window_arear.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_arear.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_arear.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_arear.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_arear.showAsDropDown(tv_select_city1, 0, 20, Gravity.RIGHT);
        list_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                if (data_yiji.size()>0){
//                    code_id = data_yiji.get(position).getCode();
//                }
                if (data_arear.size() > 0) {
                    id_area = data_arear.get(i).getId();
                }

                tv_select_city1.setText(array1_arear[i]);
                getSecondeCountryList();
                window_arear.dismiss();
            }
        });

    }

    private void initPopWindow2() {
        try {
            View popupView = getLayoutInflater().inflate(R.layout.layout_list2, null);
            lsvMore = (ListView) popupView.findViewById(R.id.list_country);

            if (dataListType2 != null) {
                lsvMore.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataListType2));
            }


            window = new PopupWindow(popupView, 300, 400);
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
            window.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            window.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            window.update();
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            window.showAsDropDown(spinner_type, 0, 20);

            lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    spinner_type.setText(array[position]);
                    //机构第二个ID
                    jigou_seconde_id = data.get(position).getId();
                    code_two = data.get(position).getCode();
                    window.dismiss();


                }
            });
        } catch (Exception e) {

        }

    }

    private void initPopWindow() {
        try {
            View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

            lsvMore = (ListView) popupView.findViewById(R.id.list);
            if (dataListType != null) {
                lsvMore.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataListType));
            }

            window = new PopupWindow(popupView, 300, 300);
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
            window.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            window.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            window.update();
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            window.showAsDropDown(byys, 0, 20);
            lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    byys.setText(array1[position]);
//                byys.setText(data.get(position).getName());
                    if (data_yiji.size() > 0) {
                        code_first = data_yiji.get(position).getCode();
                        //机构第一个id
                        jigou_Id = data_yiji.get(position).getId();
                    }


                    getSecondeList();

                    window.dismiss();


                }
            });
        } catch (Exception e) {

        }


    }

    private void getSecondeList() {
        showWaitDialog();
        RegisterDictionaryReq registerDictionaryReq = new RegisterDictionaryReq();
        //获取二级数据  bankUser银行用户  financialInstitution非银行金融机构   company企业用户
        registerDictionaryReq.setGroupId(code_first);

        ActionPresenter.getInstance().registerDictionaryRet(registerDictionaryReq).enqueue(new Callback<RegisterDictionaryRes>() {
            @Override
            public void onResponse(Call<RegisterDictionaryRes> call, Response<RegisterDictionaryRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
                        closeWaitDialog();
                        data = response.body().getData();
                        dataListType2.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < data.size(); i++) {
                            dataListType2.add(data.get(i).getName());
                        }
                        int size = dataListType2.size();
                        array = (String[]) dataListType2.toArray(new String[size]);
                        if (data.size() > 0) {
                            spinner_type.setText(data.get(0).getName());
                            code_two = data.get(0).getCode();
                            jigou_seconde_id = spinner_type.getText().toString().trim();
                        }

                    } else {
                        MyLogger.pLog().e(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.iv_excite, R.id.body_type, R.id.hend, R.id.bt_spinner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_excite:
                finish();
                break;
            case R.id.body_type:
                break;
            case R.id.hend:
                break;
            case R.id.bt_spinner:
                break;
        }
    }
}
