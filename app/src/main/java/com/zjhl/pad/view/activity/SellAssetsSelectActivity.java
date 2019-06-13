package com.zjhl.pad.view.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
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
 * File: SellAssetsActivity.java 待售筛选
 * | 功能描述:
 * | 时　　间: 2018/6/7 15:16
 * | 代码创建: Pluto
 * | 版本信息: V1.0.0
 * | 代码修改:（修改人 - 修改时间）
 **/
public class SellAssetsSelectActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_waitcommit)
    RadioButton tvWaitcommit;
    @BindView(R.id.tv_baojia)
    RadioButton tvBaojia;
    @BindView(R.id.tv_fuhenoaccess)
    RadioButton tvFuhenoaccess;
    @BindView(R.id.tv_cuohe_secsess)
    RadioButton tvCuoheSecsess;
    @BindView(R.id.tv_transe_finish)
    RadioButton tvTranseFinish;
    @BindView(R.id.tv_transe_nosend)
    RadioButton tvTranseNosend;
    @BindView(R.id.tv_already_concle)
    RadioButton tvAlreadyConcle;
    @BindView(R.id.bt_reaset)
    Button btReaset;
    @BindView(R.id.sell_assets_select_currency)
    TextView sellAssetsSelectCurrency;
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
    private TextView select_bt;
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
    private String[] textList = {MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_type1), MyApplication.mMyApplication.getResources().getString(R.string.market_forfaiting_type2)};
    private ListView lsvMore;
    private RadioButton tv_baojia;
    private RadioButton tv_cuohe_secsess;
    private RadioButton tv_transe_finish;
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
    private EditText rl_open_book_name;
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
    //增加前缀防止重复
    private String shareKey = "";
    boolean isreset = false;
    Model model = new Model();
    private static final int RESULT_CANCELEDFILTER = 1;
    //用户类型 （1；管理员；2：操作经办员；3：操作复核员）
    String userType = (String) SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_TYPE, "");

    String lanage = SharedPreferanceUtils.get(MyApplication.mMyApplication, Constants.USERINFO.USERINFO_LANGUAGE, "cn").toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sell_assets_select_filter);
        ButterKnife.bind(this);
//        initModle();
        shareKey = (String) getIntent().getStringExtra("shareKey");
        initView();
        addListner();
        initDataCoutryCity();
        initData();
        initFilterData();
        if ("2".equals(userType) || "1".equals(userType)) {
            tvWaitcommit.setVisibility(View.VISIBLE);
            tvFuhenoaccess.setVisibility(View.VISIBLE);
        } else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tvFuhenoaccess.getLayoutParams();
            lp.setMargins(0, 0, 0, 0);
            tvFuhenoaccess.setLayoutParams(lp);
        }
    }

    private void initData() {
        assetsType = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "assetsType", "");
        if (!TextUtils.isEmpty(assetsType)) {
            if ("1".equals(assetsType)) {
                rl_open_book_name.setVisibility(View.VISIBLE);
                findViewById(R.id.tv_fufeiting_ceopen).setVisibility(View.VISIBLE);
                findViewById(R.id.ll_fufeiting_ceopen).setVisibility(View.VISIBLE);
                tv_zhuangrang_scop.setText(getString(R.string.market_forfaiting_filtrate_amount_count));
                tv_tiexianlv_scope.setText(getString(R.string.market_forfaiting_filtrate_discountrate));
                tvAreaCountry.setText(R.string.market_forfaiting_filtrate_openbank_areaandcountry);
                tv_select_type.setVisibility(View.VISIBLE);
                tv_fufeiting_type.setVisibility(View.VISIBLE);
//                 tv_fufeiting_ceopen1.setText(View.VISIBLE);
            } else if ("2".equals(assetsType)) {
                //保理
                rl_open_book_name.setVisibility(View.GONE);
                findViewById(R.id.tv_fufeiting_ceopen).setVisibility(View.GONE);
                findViewById(R.id.ll_fufeiting_ceopen).setVisibility(View.GONE);
                tv_zhuangrang_scop.setText(getString(R.string.market_forfaiting_filtrate_amount_round));
                tv_tiexianlv_scope.setText(getString(R.string.market_forfaiting_filtrate_transferrate));
                tvAreaCountry.setText(R.string.market_forfaiting_filtrate_areaandcountry);
                tv_select_type.setVisibility(View.GONE);
                tv_fufeiting_type.setVisibility(View.GONE);
                sellAssetsSelectCurrency.setText(getString(R.string.issue_factoring_sell_currency));
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
        btReaset.setOnClickListener(this);
        filtrateLl.setOnClickListener(this);
        tv_select_type.setOnClickListener(this);
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
            SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "currency_type", currency);
        }
    }

    class FilteOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.tv_waitcommit:
                    //资产状态
                    //待提交
                    offerType = "101";
                    break;
                case R.id.tv_baojia:
                    //资产状态
                    //复核中
                    offerType = "102";
                    break;
                case R.id.tv_fuhenoaccess:
                    //资产状态
                    //复核未通过
                    offerType = "109";
                    break;
                case R.id.tv_cuohe_secsess:
                    //审核中
                    offerType = "2222";
                    break;
                case R.id.tv_transe_finish:
                    //审核未通过
//                    offerType = "210";
                    offerType = "1111";
                    break;
                case R.id.tv_transe_nosend:
                    //待发布
                    offerType = "103";
                    break;
                case R.id.tv_already_concle:
                    //取消
                    //已取消
//                    offerType = "150";
                    offerType = "4444";
                    break;
                case R.id.tv_already_lost:
                    //已失效
                    offerType = "126";
                    break;
            }
//            SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "aleadyCance", offerType);
            SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "offerType", offerType);
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
                    guojia_seconde_id = data_country.get(position).getId();
                    SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_id", guojia_seconde_id);
                    if ("cn".equals(lanage)) {
                        SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_name", data_country.get(position).getName());
                    } else if ("en".equals(lanage)) {
                        SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_enname", data_country.get(position).getEnName());
                    }
                }
                window.dismiss();


            }
        });
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.tv_radigroup);
        button_type = (TextView) findViewById(R.id.bt_spinner);
        select_bt = (TextView) findViewById(R.id.bt_spinner_selsect);
        //报价
        tv_baojia = (RadioButton) findViewById(R.id.tv_baojia);
        //撮合成功
        tv_cuohe_secsess = (RadioButton) findViewById(R.id.tv_cuohe_secsess);
        //交易完成
        tv_transe_finish = (RadioButton) findViewById(R.id.tv_transe_finish);
        //已取消
        tv_already_concle = (RadioButton) findViewById(R.id.tv_already_concle);
        //币种CNY
//        tv_bizhong_cn = (RadioButton)findViewById(R.id.tv_bizhong_cn);
        //USD
        tv_bizhong_us = (RadioButton) findViewById(R.id.tv_bizhong_cny);
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
        rl_open_book_name = (EditText) findViewById(R.id.rl_open_book_name);
        tv_fufeiting_ceopen1 = (TextView) findViewById(R.id.tv_fufeiting_ceopen);
        tv_zhuangrang_scop = (TextView) findViewById(R.id.tv_zhuangrang_scop);
        tv_tiexianlv_scope = (TextView) findViewById(R.id.tv_tiexianlv_scope);
        tv_fufeiting_type = (TextView) findViewById(R.id.tv_fufeiting_type);

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
            case R.id.bt_spinner:
            case R.id.tv_select:
//                selectAarea();
                initPopWindowCity();
                break;
            case R.id.tv_select_type:
                initPopWindow();
                break;

            case R.id.tv_bizhong_us:

                break;
            case R.id.tv_bizhong_eu:

                break;
            case R.id.spinner_type:
            case R.id.tv_select1:
                if (!TextUtils.isEmpty(spinner_type.getText().toString().trim())) {
                    showPopWindowCoutry();
                } else {
                    ToastUtils.showShort(getString(R.string.toast_filtrate_choicearea));
                }
                break;
            case R.id.bt_reaset:
                //承兑金额范围
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "scope_start", "");
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "scope_end", "");
                //贴现率范围
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "tiexianlv_start", "");
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "tiexianlv_end", "");
                //开证行名称
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "open_book_name", "");
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "area_name", "");
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "area_enname", "");
                //福费廷类型
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "mine_fufeiting", "");
                //资产状态
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "offerType", "");
                //币种
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "currency_type", "");
                //保理 福费廷
//                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "assetsType", assetsType);
                //地区
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_id", "");
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_name", "");
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_enname", "");
                setResult(RESULT_CANCELEDFILTER, null);
                //重置
                SellAssetsSelectActivity.this.finish();
//                isreset = true;
                break;
            case R.id.bt_sure_commit:
//                if (!isreset) {
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
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "scope_start", scope_start);
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "scope_end", scope_end);
                //贴现率范围
                String et_tiexianlv_start1 = et_tiexianlv_start.getText().toString().trim();
                String et_tiexianlv_end1 = et_tiexianlv_end.getText().toString().trim();

                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "tiexianlv_start", et_tiexianlv_start1);
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "tiexianlv_end", et_tiexianlv_end1);

                //开证行名称
                String open_book_name = rl_open_book_name.getText().toString().trim();
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "open_book_name", open_book_name);

//                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "aleadyCance", offerType);

                //福费廷类型
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "mine_fufeiting", fufeiting_type);
                //资产状态
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "offerType", offerType);
                //币种
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "currency_type", currency);
                //保理 福费廷
//                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "assetsType", assetsType);
                //地区
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_id", guojia_seconde_id);
//                }
                setResult(RESULT_CANCELEDFILTER, null);
                //提交保存
                SellAssetsSelectActivity.this.finish();
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

    private void initPopWindowCity() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_country_list1, null);

        ListView list_country = (ListView) popupView.findViewById(R.id.list_country);
        list_country.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, dataListTypeAraer));
        final PopupWindow window_arear = new PopupWindow(popupView, 300, 250);
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
                    id_area = data_arear.get(i).getId();
                    SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "id_area", id_area);

//                    order_more_list.setVisibility(View.VISIBLE);
                    getSecondeCountryList();
                    if ("cn".equals(lanage)) {
                        SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "area_name", data_arear.get(i).getName());
                    } else if ("en".equals(lanage)) {
                        SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "area_enname", data_arear.get(i).getEnName());
                    }
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
                        for (int i = 0; i < SellAssetsSelectActivity.this.data_country.size(); i++) {
                            dataListTypeCountry.add(SellAssetsSelectActivity.this.data_country.get(i).getName());

                        }
                        int size = dataListTypeCountry.size();
                        array_country = (String[]) dataListTypeCountry.toArray(new String[size]);
                        if (data_country.size() > 0) {
                            spinner_type.setText(SellAssetsSelectActivity.this.data_country.get(0).getName());
                            guojia_seconde_id = SellAssetsSelectActivity.this.data_country.get(0).getId();
                            SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_id", guojia_seconde_id);
                            if ("cn".equals(lanage)) {
                                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_name", SellAssetsSelectActivity.this.data_country.get(0).getName());
                            } else if ("en".equals(lanage)) {
                                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "country_enname", SellAssetsSelectActivity.this.data_country.get(0).getEnName());
                            }
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


    private void initPopWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.layout_list, null);

        lsvMore = (ListView) popupView.findViewById(R.id.list);
        lsvMore.setAdapter(new ArrayAdapter<String>(this, R.layout.item_select_down, textList));
        window = new PopupWindow(popupView, 300, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#eeeeee")));
        window.setFocusable(true);
        //  设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // 更新popupwindow的状态
        window.update();
        // 以下拉的方式显示，并且可以设置显示的位置
        window.showAsDropDown(tv_select_type, 0, 20);
        lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                select_bt.setText(textList[position]);
//                if ("国内福费廷".equals(textList[position])) {
//                    fufeiting_type = "1";
//                } else if ("国际福费廷".equals(textList[position])) {
//                    fufeiting_type = "2";
//                }
                if (getResources().getString(R.string.market_forfaiting_type1).equals(textList[position])) {
                    fufeiting_type = "1";
                } else if (getResources().getString(R.string.market_forfaiting_type2).equals(textList[position])) {
                    fufeiting_type = "2";
                }
                SharedPreferanceUtils.put(SellAssetsSelectActivity.this, shareKey + "mine_fufeiting", fufeiting_type);
                window.dismiss();


            }
        });

    }

    //筛选菜单反显
    protected void initFilterData() {
        //承兑金额范围
        String scope_start = "";
        String scope_end = "";
        //贴现率范围
        String discount_rate_start = "";
        String discount_rate_end = "";
        scope_start = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "scope_start", "");
        scope_end = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "scope_end", "");
        discount_rate_start = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "tiexianlv_start", "");
        discount_rate_end = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "tiexianlv_end", "");
        //福费廷类型
        fufeiting_type = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "mine_fufeiting", "");
        //币种
        currency = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "currency_type", "");
        //资产状态
        offerType = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "offerType", "");
        //保理 福费廷
//                SharedPreferanceUtils.put(FiltrateActivity.this, shareKey + "assetsType", assetsType);
        //地区
        guojia_seconde_id = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "country_id", "");
        //开证行名称
        String open_book_name = "";
        open_book_name = (String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "open_book_name", "");
        et_put_money_scope_start.setText(scope_start);
        et_put_money_scope_end.setText(scope_end);
        et_tiexianlv_start.setText(discount_rate_start);
        et_tiexianlv_end.setText(discount_rate_end);
        select_bt.setTextColor(getResources().getColor(R.color.blue));
        if ("1".equals(fufeiting_type)) {
            select_bt.setText(getResources().getString(R.string.market_forfaiting_type1));
        } else if ("2".equals(fufeiting_type)) {
            select_bt.setText(getResources().getString(R.string.market_forfaiting_type2));
        }

        if ("cn".equals(lanage)) {
            spinner_type.setText((String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "country_name", ""));
            spinner_type.setTextColor(getResources().getColor(R.color.blue));

            button_type.setText((String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "area_name", ""));
            button_type.setTextColor(getResources().getColor(R.color.blue));

        } else if ("en".equals(lanage)) {
            spinner_type.setText((String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "country_enname", ""));
            spinner_type.setTextColor(getResources().getColor(R.color.blue));

            button_type.setText((String) SharedPreferanceUtils.get(SellAssetsSelectActivity.this, shareKey + "area_enname", ""));
            button_type.setTextColor(getResources().getColor(R.color.blue));
        }
        if ("CNY".equals(currency)) {
            rg_main.check(R.id.tv_bizhong_cny);
//            rg_main.getChildAt(1).setSelected(true);
        } else if ("USD".equals(currency)) {
//            rg_main.getChildAt(2).setSelected(true);
            rg_main.check(R.id.tv_bizhong_us);
        } else if ("EUR".equals(currency)) {
//            rg_main.getChildAt(3).setSelected(true);
            rg_main.check(R.id.tv_bizhong_eu);
        }
        if ("101".equals(offerType)) {
            radioGroup.check(R.id.tv_waitcommit);
        } else if ("102".equals(offerType)) {
            radioGroup.check(R.id.tv_baojia);
        } else if ("109".equals(offerType)) {
            radioGroup.check(R.id.tv_fuhenoaccess);
        } else if ("2222".equals(offerType)) {
            radioGroup.check(R.id.tv_cuohe_secsess);
        } else if ("1111".equals(offerType)) {
            radioGroup.check(R.id.tv_transe_finish);
        } else if ("103".equals(offerType)) {
            radioGroup.check(R.id.tv_transe_nosend);
        } else if ("4444".equals(offerType)) {
            radioGroup.check(R.id.tv_already_concle);
        } else if ("126".equals(offerType)) {
            radioGroup.check(R.id.tv_already_lost);
        }
        rl_open_book_name.setText(open_book_name);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
