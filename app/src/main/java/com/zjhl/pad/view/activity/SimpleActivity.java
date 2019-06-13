package com.zjhl.pad.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.zjhl.pad.app.utils.MyLogger;
import com.zjhl.pad.view.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sx on 2016/8/17.
 */
public class SimpleActivity extends Activity implements AdapterView.OnItemSelectedListener{

    private ImageView selectImage;
    private TextView selectText;
    private Spinner simpleSpinner;
    private List<Map<String,Object>> dataList;
    private SimpleAdapter adapter;

//    private int[] imageList = {R.drawable.atguigu_logo,R.drawable.back_image,R.drawable.bisniss_messge,R.drawable.body_type_image};
    private String[] textList = {"北京","上海","广州","深圳"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);

//        selectImage = (ImageView) findViewById(R.id.seclet_image);
        selectText = (TextView) findViewById(R.id.select_text);
        simpleSpinner = (Spinner) findViewById(R.id.simple_spinner);

        //1.设置数据源
        dataList = new ArrayList<Map<String,Object>>();

        //2.定义适配器
        adapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"text"},new int[]{R.id.text});

        //3.adapter设置下拉样式，这里样式不要加，因为它并不支持图片和文字一起显示的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //4.spinner加载适配器
        simpleSpinner.setAdapter(adapter);

        //5.spinner设置监听器
        simpleSpinner.setOnItemSelectedListener(this);
    }

    private List<Map<String,Object>> getData() {
        Map<String,Object> map;
        for (int i = 0; i < textList.length; i++) {
            map = new HashMap<>();
//            map.put("image",imageList[i]);
            map.put("text",textList[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        selectImage.setImageResource(imageList[position]);
        selectText.setText(textList[position]);
//        MyLogger.pLog().e(textList[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
