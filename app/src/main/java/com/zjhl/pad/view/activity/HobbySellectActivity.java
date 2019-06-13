package com.zjhl.pad.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjhl.pad.app.application.MyApplication;
import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.app.utils.EditInputFilterRate;
import com.zjhl.pad.app.utils.Model;
import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.app.utils.SharedPreferanceUtils;
import com.zjhl.pad.app.utils.StringUtils;
import com.zjhl.pad.app.utils.ToastUtils;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.presenter.okhttp.ActionPresenter;
import com.zjhl.pad.view.R;
import com.zjhl.pad.view.activity.base.BaseActivity;
import com.zjhl.pad.view.adapter.ClassifyMorAdapter;
import com.zjhl.pad.view.adapter.DataOrderMainAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File: HobbySellectActivity.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/6/4 11:46
 * Changes (from 2018/6/4)
 */
public class HobbySellectActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.filtrate_ll)
    LinearLayout filtrateLl;
    @BindView(R.id.tv_area_country)
    TextView tvAreaCountry;
    private ListView order_more_list;
    private DataOrderMainAdapter mainAdapter;
    private ClassifyMorAdapter moreAdapter;
    private List<Map<String, Object>> mainList;
    private PopupWindow window;
    private Button button;
    private Button select_bt;
    boolean isChecked_offer = false;
    boolean isChecked_sucsess = false;
    boolean isChecked_finish = false;
    boolean isChecked_cancel = false;
    boolean isChecked_money = false;
    boolean isChecked_usd = false;
    boolean isChecked_eur = false;
    private RelativeLayout tv_select_type;
    private RelativeLayout tv_select;
    private RelativeLayout tv_select1;
    private List<String> dataListType;
    private String[] textList = {MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_type1), MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_type1)};
    private String[] baoliType = {MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_filtrate_single), MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_filtrate_double)};
    private String[] minganbaoliType = {MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_filtrate_disclosed), MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_filtrate_undisclosed)};
    private ListView lsvMore;

    private RadioButton tv_already_concle;
    private RadioButton tv_bizhong_cn;
    private RadioButton tv_bizhong_us;
    private RadioButton tv_bizhong_eu;
    private String offerType = "";
    private String cuoheSecsess = "";
    private String aleadyCance = "";
    private String transCompleateType = "";
    private Button bt_sure_commit;
    private EditText et_put_money_scope_start;
    private EditText et_put_money_scope_end;
    private EditText et_tiexianlv_start;
    private EditText et_tiexianlv_end;
    //    private EditText rl_open_book_name;
    private List<RegisterCountryArearRes.DataBean> data_arear;
    private String[] array1_arear;
    private List<RegisterCountryArearRes.DataBean> data_country;
    private List<String> dataListTypeCountry;
    private List<String> dataListTypeCountry1;
    private String currency = "";
    private String fufeiting_type = "";
    /**
     * 国家——————1.地区
     */
    private List<String> dataListTypeAraer;
    //第一级选择的某个item
    private String id_area = "";
    private String[] array_country;
    private String moneyType;
    private RadioButton tv_radibutton;
    private RadioGroup radioGroup;
    private RadioGroup rg_main;
    private TextView spinner_type;
    private String guojia_seconde_id = "";
    private String assetsType = "";
    private TextView tv_fufeiting_ceopen1;
    private TextView button_type;
    private TextView tv_zhuangrang_scop;
    private TextView tv_tiexianlv_scope;
    private TextView tv_fufeiting_type;
    private String xinyongCard;
    private TextView tv_baoli_type;
    private LinearLayout spinner_baoli;
    private RelativeLayout rl_fufeiting;
    private TextView dui_bizhong;
    private TextView bt_single_doble;
    private RelativeLayout tv_select_single_shuang;
    private RelativeLayout rl_ming_an;
    private TextView bt_ming_an;
    private String baoli_danshuang = "";
    private String baoli_ming_an = "";
    private Button bt_reaset;
    private RadioButton tv_guoji_card;

    private RadioButton tv_guonei_card;
    private String duixian_bizhong_type;
    private RadioButton tv_bizhong_cny;
    private String scope_start_type;
    //防止重复
    private String shareKey = "";
    //    //增加前缀防止重复
//    private String shareKey = "";
    Model model = new Model();
    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_hobby);
        ButterKnife.bind(this);
        shareKey = (String) getIntent().getStringExtra("shareKey");
        assetsType = (String) getIntent().getStringExtra("assetsType");
//        initModle();
//        shareKey = (String) getIntent().getStringExtra("shareKey");
        initView();
        addListner();
        initDataCoutryCity();
        initData();
        initReviewFilterData();
    }

    private void initData() {


        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
                tv_tiexianlv_scope.setText(R.string.market_forfaiting_filtrate_discountrate);
                tv_baoli_type.setText(R.string.market_forfaiting_filtrate_type);
                rl_fufeiting.setVisibility(View.VISIBLE);
                dui_bizhong.setText(getString(R.string.market_forfaiting_adapter_currency));
                tv_zhuangrang_scop.setText(R.string.market_forfaiting_filtrate_amount_count);
                tvAreaCountry.setText(R.string.market_forfaiting_filtrate_openbank_areaandcountry);
            } else if ("2".equals(assetsType)) {
//                //保理

                tv_zhuangrang_scop.setText(R.string.market_forfaiting_filtrate_amount_count);
                tv_tiexianlv_scope.setText(R.string.market_forfaiting_filtrate_discountrate);
                tv_baoli_type.setText(R.string.filtrate_factoring_type);
                spinner_baoli.setVisibility(View.VISIBLE);
                dui_bizhong.setText(R.string.filtrate_currency);
                tvAreaCountry.setText(R.string.market_forfaiting_filtrate_areaandcountry);
            }

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

    private void addListner() {

        spinner_type.setOnClickListener(this);
        select_bt.setOnClickListener(this);
        button_type.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new FilteOnCheckedChangeListener());
        rg_main.setOnCheckedChangeListener(new MoneyOnCheckedChangeListner());
        bt_sure_commit.setOnClickListener(this);
        bt_single_doble.setOnClickListener(this);
        tv_select_single_shuang.setOnClickListener(this);
        rl_ming_an.setOnClickListener(this);
        bt_ming_an.setOnClickListener(this);
        bt_reaset.setOnClickListener(this);
        filtrateLl.setOnClickListener(this);
        tv_select.setOnClickListener(this);
        tv_select1.setOnClickListener(this);
    }

    class MoneyOnCheckedChangeListner implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.tv_bizhong_cny:
                    //CYN
                    currency = "CNY";
                    break;
                case R.id.tv_bizhong_us:
                    //USD
                    currency = "USD";
                    break;
                case R.id.tv_bizhong_eu:
                    //EUR
                    currency = "EUR";
                    break;


            }
//            SharedPreferanceUtils.put(FiltrateActivity.this, shareKey + "currency_type", currency);

        }
    }

    class FilteOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                //国际信用证  //债权类型：1 国内证、2 国际证'
                case R.id.tv_guoji_card:
                    xinyongCard = "1";
                    break;
                //国外信用证
                case R.id.tv_guonei_card:
                    xinyongCard = "2";
                    break;

            }

        }


    }

    private void showPopWindowCoutry() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_country, null);
        ListView mlist_country = (ListView) popupView.findViewById(R.id.list_select_country);
        mlist_country.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, dataListTypeCountry));
        window = new PopupWindow(popupView, 300, 250);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(spinner_type, 0, 20);
        mlist_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (array_country.length > 0) {
                    spinner_type.setText(array_country[position]);
                    //国家id
                    guojia_seconde_id = data_country.get(position).getId();
                    if ("cn".equals(lanage)) {
                        SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "country_name", data_country.get(position).getName());
                    } else if ("en".equals(lanage)) {
                        SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "country_enname", data_country.get(position).getEnName());
                    }
                }
                window.dismiss();


            }
        });
    }

    private void initView() {
//        findViewById(R.id. String scope_start)
        tv_bizhong_us = (RadioButton) findViewById(R.id.tv_bizhong_us);
        radioGroup = (RadioGroup) findViewById(R.id.tv_radigroup);
        button_type = (TextView) findViewById(R.id.bt_spinner);
        select_bt = (Button) findViewById(R.id.bt_spinner_selsect);
        //保理类型选项
        bt_single_doble = (TextView) findViewById(R.id.bt_single_doble);
//        bt_single_doble.setText("单保理");
//        baoli_danshuang = "单保理";
        tv_select_single_shuang = (RelativeLayout) findViewById(R.id.tv_select_single_shuang);
        rl_ming_an = (RelativeLayout) findViewById(R.id.rl_ming_an);
        //明暗保理
        bt_ming_an = (TextView) findViewById(R.id.bt_ming_an);
//        bt_ming_an.setText("明保理");
//        baoli_ming_an = "明保理";
        tv_baoli_type = (TextView) findViewById(R.id.tv_baoli_type);
        rl_fufeiting = (RelativeLayout) findViewById(R.id.rl_fufeiting);
        dui_bizhong = (TextView) findViewById(R.id.dui_bizhong);
        spinner_baoli = (LinearLayout) findViewById(R.id.ll_baoli);
        tv_guoji_card = (RadioButton) findViewById(R.id.tv_guoji_card);
        tv_guonei_card = (RadioButton) findViewById(R.id.tv_guonei_card);

        //已取消
        tv_already_concle = (RadioButton) findViewById(R.id.tv_already_concle);
        //币种CNY
//        tv_bizhong_cn = (RadioButton)findViewById(R.id.tv_bizhong_cn);
        //CNY
        tv_bizhong_cny = (RadioButton) findViewById(R.id.tv_bizhong_cny);
        //EUR
        bt_sure_commit = (Button) findViewById(R.id.bt_sure_commit);

        //承兑金额范围start
        et_put_money_scope_start = (EditText) findViewById(R.id.et_put_money_scope_start);
        //承兑金额范围end
        et_put_money_scope_end = (EditText) findViewById(R.id.et_put_money_scope_end);

        initCompareAmountListener(et_put_money_scope_start);
        initCompareAmountListener(et_put_money_scope_end);
        //贴现率范围
        et_tiexianlv_start = (EditText) findViewById(R.id.et_tiexianlv_start);
        et_tiexianlv_end = (EditText) findViewById(R.id.et_tiexianlv_end);

        initCompareAmountListener(et_tiexianlv_start);
        initCompareAmountListener(et_tiexianlv_end);

        EditInputFilterRate[] filters = {new EditInputFilterRate()};
        et_tiexianlv_start.setFilters(filters);
        et_tiexianlv_end.setFilters(filters);

        tv_bizhong_eu = (RadioButton) findViewById(R.id.tv_bizhong_eu);
        tv_select_type = (RelativeLayout) findViewById(R.id.tv_select_type);
        tv_select = (RelativeLayout) findViewById(R.id.tv_select);
        tv_select1 = (RelativeLayout) findViewById(R.id.tv_select1);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        spinner_type = (TextView) findViewById(R.id.spinner_type);

        //开证行名称
//        rl_open_book_name = (EditText) findViewById(R.id.rl_open_book_name);
        tv_fufeiting_ceopen1 = (TextView) findViewById(R.id.tv_fufeiting_ceopen);
        tv_zhuangrang_scop = (TextView) findViewById(R.id.tv_zhuangrang_scop);
        tv_tiexianlv_scope = (TextView) findViewById(R.id.tv_tiexianlv_scope);
//        tv_fufeiting_type = (TextView) findViewById(R.id.tv_fufeiting_type);
        bt_reaset = (Button) findViewById(R.id.bt_reaset);

        dataListTypeAraer = new ArrayList<String>();
        dataListTypeCountry = new ArrayList<String>();
        dataListTypeCountry1 = new ArrayList<String>();


    }


    private void initModle() {
        mainList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < model.LISTVIEWTXT.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
//			map.put("img", Model.LISTVIEWIMG[i]);
            map.put("txt", model.LISTVIEWTXT[i]);
            mainList.add(map);
        }
    }


    private void initAdapter(String[] array) {
        moreAdapter = new ClassifyMorAdapter(this, array);
        order_more_list.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filtrate_ll:
                finish();
                break;
            case R.id.tv_select:
            case R.id.bt_spinner:
//                selectAarea();
                et_put_money_scope_start.clearFocus();
                if (isSoftShowing()) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(view,InputMethodManager.SHOW_FORCED); //强制显示键盘
//                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
//                imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘


                } else {
                    initPopWindowCity();
                }
                break;
            case R.id.bt_spinner_selsect:
                initPopWindow();
                break;
            case R.id.tv_select_single_shuang:
                //选择保理类型
                initBaoliType();
                break;
//            case R.id.rl_ming_an:
//                //明暗保理
//                initMingAnBaoliType();
//                break;
            case R.id.rl_ming_an:
                //明暗保理
                initMingAnBaoliType();
                break;
            case R.id.tv_bizhong_us:

                break;
            case R.id.tv_bizhong_eu:

                break;
            case R.id.bt_reaset:
                if ("1".equals(assetsType)) {
                    SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "xinyong_card", "");
                } else if ("2".equals(assetsType)) {
                    //单双，明暗保理
                    SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "baoli_danshuang", "");
                    SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "baoli_ming_an", "");
                }
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_scope_start", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_scope_end", "");

                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_tiexianlv_start", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_tiexianlv_end", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_country_id", "");
                //国家id
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "guojia_seconde_id", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "mine_fufeiting", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_select_type", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_country_name", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_country_enname", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "country_name", "");
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "country_enname", "");
                //重置
                HobbySellectActivity.this.finish();
                break;
            case R.id.tv_select1:
            case R.id.spinner_type:
                if (!TextUtils.isEmpty(spinner_type.getText().toString().trim())) {
                    showPopWindowCoutry();
                } else {
                    ToastUtils.showShort(R.string.hint_issue_forfaiting_sell_area);
                }
                break;
            case R.id.bt_sure_commit:
                if ("1".equals(assetsType) && !TextUtils.isEmpty(xinyongCard)) {
                    SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "xinyong_card", xinyongCard);
                } else if ("2".equals(assetsType)) {
                    //单双，明暗保理
                    SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "baoli_danshuang", baoli_danshuang);
                    SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "baoli_ming_an", baoli_ming_an);
                }
                //承兑金额范围
                String scope_start = et_put_money_scope_start.getText().toString().trim();
                String scope_end = et_put_money_scope_end.getText().toString().trim();
                if (scope_start.length() > scope_end.length()) {
                    et_put_money_scope_start.setText(scope_end);
                    et_put_money_scope_end.setText(scope_start);
                    scope_start = et_put_money_scope_start.getText().toString().trim();
                    scope_end = et_put_money_scope_end.getText().toString().trim();
                } else if (scope_start.length() == scope_end.length()) {
                    if (scope_end.compareTo(scope_start) > 0) {
                    } else {
                        et_put_money_scope_start.setText(scope_end);
                        et_put_money_scope_end.setText(scope_start);
                        scope_start = et_put_money_scope_start.getText().toString().trim();
                        scope_end = et_put_money_scope_end.getText().toString().trim();
                    }
                }
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_scope_start", scope_start);
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_scope_end", scope_end);
                //贴现率范围
                String et_tiexianlv_start1 = et_tiexianlv_start.getText().toString().trim();
                String et_tiexianlv_end1 = et_tiexianlv_end.getText().toString().trim();

                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_tiexianlv_start", et_tiexianlv_start1);
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_tiexianlv_end", et_tiexianlv_end1);

                //地区id
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_country_id", id_area);
                //国家id
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "guojia_seconde_id", guojia_seconde_id);
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "mine_fufeiting", fufeiting_type);
                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_select_type", currency);
                //提交保存
                HobbySellectActivity.this.finish();
                break;

        }
        et_put_money_scope_start.setFocusable(false);
        et_put_money_scope_start.setFocusableInTouchMode(false);
        et_put_money_scope_start.setFocusable(true);
        et_put_money_scope_start.setFocusableInTouchMode(true);
        et_put_money_scope_end.setFocusable(false);
        et_put_money_scope_end.setFocusableInTouchMode(false);
        et_put_money_scope_end.setFocusable(true);
        et_put_money_scope_end.setFocusableInTouchMode(true);

        et_tiexianlv_start.setFocusable(false);
        et_tiexianlv_start.setFocusableInTouchMode(false);
        et_tiexianlv_start.setFocusable(true);
        et_tiexianlv_start.setFocusableInTouchMode(true);
        et_tiexianlv_end.setFocusable(false);
        et_tiexianlv_end.setFocusableInTouchMode(false);
        et_tiexianlv_end.setFocusable(true);
        et_tiexianlv_end.setFocusableInTouchMode(true);
    }

    private void initMingAnBaoliType() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.hobby_hobby_baoli_down, minganbaoliType));
        window = new PopupWindow(popupView, 170, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eeeeee")));
        window.setFocusable(true);
        //  设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // 更新popupwindow的状态
        window.update();
        // 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(bt_ming_an, 0, 20);
        lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                bt_ming_an.setText(minganbaoliType[position]);
                //明暗保理
//                baoli_ming_an = bt_ming_an.getText().toString().trim();
                if ((getResources().getString(R.string.market_forfaiting_filtrate_disclosed)).equals(minganbaoliType[position])) {
                    baoli_ming_an = "1";
                } else if ((getResources().getString(R.string.market_forfaiting_filtrate_undisclosed)).equals(minganbaoliType[position])) {
                    baoli_ming_an = "2";
                }
                window.dismiss();


            }
        });

    }

    private void initBaoliType() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.hobby_hobby_baoli_down, baoliType));
        window = new PopupWindow(popupView, 170, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eeeeee")));
        window.setFocusable(true);
        //  设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // 更新popupwindow的状态
        window.update();
        // 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(bt_single_doble, 0, 20);
        lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                bt_single_doble.setText(baoliType[position]);
                //单双保理
//                baoli_danshuang = bt_single_doble.getText().toString();
                if ((getResources().getString(R.string.market_forfaiting_filtrate_single)).equals(baoliType[position])) {
                    baoli_danshuang = "1";
                } else if ((getResources().getString(R.string.market_forfaiting_filtrate_double)).equals(baoliType[position])) {
                    baoli_danshuang = "2";
                }
                window.dismiss();


            }
        });
    }

    private void initPopWindowCity() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_country_list1, null);

        ListView list_country = (ListView) popupView.findViewById(R.id.list_country);
        list_country.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, dataListTypeAraer));
        final PopupWindow window_arear = new PopupWindow(popupView, 200, 250);
//        order_more_list = (ListView) popupView.findViewById(R.id.classify_morelist);
        window_arear.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        window_arear.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window_arear.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window_arear.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        window_arear.showAsDropDown(button_type, 0, 20);

        list_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                if (data_yiji.size()>0){
//                    code_id = data_yiji.get(position).getCode();
//                }
                button_type.setText(array1_arear[i]);
                if (data_arear.size() > 0) {
                    //地区id
                    id_area = data_arear.get(i).getId();
                    if ("cn".equals(lanage)) {
                        SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_country_name", data_arear.get(i).getName());
                    } else if ("en".equals(lanage)) {
                        SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "hobby_country_nename", data_arear.get(i).getEnName());
                    }
//                    order_more_list.setVisibility(View.VISIBLE);
                    getSecondeCountryList();
                }
                window_arear.dismiss();


            }
        });
    }

    private void getSecondeCountryList() {
        //选择国家
        RegisterSelectCountry registerselectReq = new RegisterSelectCountry();
        registerselectReq.setAreaId(id_area);

        ActionPresenter.getInstance().registerDictionaryRet(registerselectReq).enqueue(new Callback<RegisterCountryArearRes>() {
            @Override
            public void onResponse(Call<RegisterCountryArearRes> call, Response<RegisterCountryArearRes> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getCode() == 300) {
//                        data = response.body().getData();
                        data_country = response.body().getData();
                        dataListTypeCountry.clear();
                        Map<String, Object> map;
                        for (int i = 0; i < HobbySellectActivity.this.data_country.size(); i++) {
                            dataListTypeCountry.add(HobbySellectActivity.this.data_country.get(i).getName());
                        }
                        int size = dataListTypeCountry.size();
                        array_country = (String[]) dataListTypeCountry.toArray(new String[size]);
                        if (data_country.size() > 0) {
                            spinner_type.setText(HobbySellectActivity.this.data_country.get(0).getName());
                            //国家id
                            guojia_seconde_id = HobbySellectActivity.this.data_country.get(0).getId();
                            if ("cn".equals(lanage)) {
                                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "country_name", HobbySellectActivity.this.data_country.get(0).getName());
                            } else if ("en".equals(lanage)) {
                                SharedPreferanceUtils.put(HobbySellectActivity.this, shareKey + "country_enname", HobbySellectActivity.this.data_country.get(0).getEnName());
                            }
                        }

                        guojia_seconde_id = data_country.get(1).getId();

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


    private void initPopWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, textList));
        window = new PopupWindow(popupView, 300, 400);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eeeeee")));
        window.setFocusable(true);
        //  设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // 更新popupwindow的状态
        window.update();
        // 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(select_bt, 0, 20);
        lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                select_bt.setText(textList[position]);
                if (getResources().getString(R.string.market_forfaiting_type1).equals(textList[position])) {
                    fufeiting_type = "1";
                } else if (getResources().getString(R.string.market_forfaiting_type2).equals(textList[position])) {
                    fufeiting_type = "2";
                }

                window.dismiss();


            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //筛选菜单反显
    protected void initReviewFilterData() {
        xinyongCard = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "xinyong_card", "");
        if ("1".equals(xinyongCard)) {
            radioGroup.check(R.id.tv_guonei_card);
        } else if ("2".equals(xinyongCard)) {
            radioGroup.check(R.id.tv_guoji_card);
        }
        //币种
        currency = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_select_type", "");
        if (!TextUtils.isEmpty(currency)) {
            if ("CNY".equals(currency)) {
                tv_bizhong_cny.setChecked(true);
            } else if ("USD".equals(currency)) {
                tv_bizhong_us.setChecked(true);
            } else if ("EUR".equals(currency)) {
                tv_bizhong_eu.setChecked(true);
            }

        }
        //承兑金额范围
        String scope_start = "";
        String scope_end = "";
        scope_start = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_scope_start", "");
        scope_end = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_scope_end", "");
        //地区
        guojia_seconde_id = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "country_id", "");
        et_put_money_scope_start.setText(scope_start);
        et_put_money_scope_end.setText(scope_end);
        select_bt.setTextColor(getResources().getColor(R.color.blue));
        if (("1").equals(fufeiting_type)) {
            select_bt.setText(getResources().getString(R.string.market_forfaiting_type1));
        } else if (("1").equals(fufeiting_type)) {
            select_bt.setText(getResources().getString(R.string.market_forfaiting_type2));
        }
        baoli_danshuang = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "baoli_danshuang", "");
        baoli_ming_an = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "baoli_ming_an", "");
        //贴现率范围
        String et_tiexianlv_start1 = "";
        String et_tiexianlv_end1 = "";

        et_tiexianlv_start1 = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_tiexianlv_start", "");
        et_tiexianlv_end1 = (String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_tiexianlv_end", "");
        et_tiexianlv_start.setText(et_tiexianlv_start1);
        et_tiexianlv_end.setText(et_tiexianlv_end1);
        //保理类型  1 单保理、明保理 2 单保理、暗保理  3、双保理、明保理  4、双保理、暗保理'
        if ("1".equals(baoli_danshuang)) {
            bt_single_doble.setText(getResources().getString(R.string.market_forfaiting_filtrate_single));
            bt_single_doble.setTextColor(getResources().getColor(R.color.blue));
        } else if ("2".equals(baoli_danshuang)) {
            bt_single_doble.setText(getResources().getString(R.string.market_forfaiting_filtrate_double));
            bt_single_doble.setTextColor(getResources().getColor(R.color.blue));
        }
        if ("1".equals(baoli_ming_an)) {
            bt_ming_an.setText(getResources().getString(R.string.market_forfaiting_filtrate_disclosed));
            bt_ming_an.setTextColor(getResources().getColor(R.color.blue));
        } else if ("2".equals(baoli_ming_an)) {
            bt_ming_an.setText(getResources().getString(R.string.market_forfaiting_filtrate_undisclosed));
            bt_ming_an.setTextColor(getResources().getColor(R.color.blue));
        }
        if ("cn".equals(lanage)) {
            spinner_type.setText((String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "country_name", ""));
            spinner_type.setTextColor(getResources().getColor(R.color.blue));

            button_type.setText((String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_country_name", ""));
            button_type.setTextColor(getResources().getColor(R.color.blue));

        } else if ("en".equals(lanage)) {
            spinner_type.setText((String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "country_enname", ""));
            spinner_type.setTextColor(getResources().getColor(R.color.blue));

            button_type.setText((String) SharedPreferanceUtils.get(HobbySellectActivity.this, shareKey + "hobby_country_enname", ""));
            button_type.setTextColor(getResources().getColor(R.color.blue));
        }

    }

    private boolean isSoftShowing() {
        //获取当屏幕内容的高度
        int screenHeight = this.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }

    public void initCompareAmountListener(final EditText view) {
        view.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String viewtext = view.getText().toString();
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    //承兑金额范围
                    String scope_start = et_put_money_scope_start.getText().toString().trim();
                    String scope_end = et_put_money_scope_end.getText().toString().trim();
                    if (!StringUtils.isEmpty(scope_start) && !StringUtils.isEmpty(scope_end)) {
                        if (scope_start.length() > scope_end.length()) {
                            et_put_money_scope_start.setText(scope_end);
                            et_put_money_scope_end.setText(scope_start);
                        } else if (scope_start.length() == scope_end.length()) {
                            if (scope_end.compareTo(scope_start) > 0) {
                            } else {
                                et_put_money_scope_start.setText(scope_end);
                                et_put_money_scope_end.setText(scope_start);
                            }
                        }
                    }
                    //贴现率范围
                    String et_tiexianlv_start1 = et_tiexianlv_start.getText().toString().trim();
                    String et_tiexianlv_end1 = et_tiexianlv_end.getText().toString().trim();
                    et_tiexianlv_start1 = StringUtils.doubleFormate(et_tiexianlv_start1);
                    et_tiexianlv_end1 = StringUtils.doubleFormate(et_tiexianlv_end1);
                    et_tiexianlv_start.setText(et_tiexianlv_start1);
                    et_tiexianlv_end.setText(et_tiexianlv_end1);
                    if (!StringUtils.isEmpty(et_tiexianlv_start1) && !StringUtils.isEmpty(et_tiexianlv_end1)) {
                        if (et_tiexianlv_start1.length() > et_tiexianlv_end1.length()) {
                            et_tiexianlv_start.setText(et_tiexianlv_end1);
                            et_tiexianlv_end.setText(et_tiexianlv_start1);
                        } else if (et_tiexianlv_start1.length() == et_tiexianlv_end1.length()) {
                            if (et_tiexianlv_end1.compareTo(et_tiexianlv_start1) > 0) {
                            } else {
                                et_tiexianlv_start.setText(et_tiexianlv_end1);
                                et_tiexianlv_end.setText(et_tiexianlv_start1);
                            }
                        }
                    }
                }
            }
        });
    }
}
