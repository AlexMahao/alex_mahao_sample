package com.mahao.alex.systemwidgetdemo.listView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * ListView使用总结
 * Created by Alex_MaHao on 2016/5/17.
 */
public class ListViewActivity extends AppCompatActivity {

    private ListView mLv;

    private List<String> datas = new ArrayList<>();

    private SimpleBaseAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        mLv = ((ListView) findViewById(R.id.listview_lv));

        initDatas();

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

    }

    private void initDatas() {
        for(int i = 0;i<20;i++){
            datas.add("添加数据~~"+i);
        }
    }


}
