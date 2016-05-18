package com.mahao.alex.systemwidgetdemo.listView.custom_refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mahao.alex.systemwidgetdemo.R;
import com.mahao.alex.systemwidgetdemo.listView.SimpleBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex_MaHao on 2016/5/18.
 */
public class CustomRefreshListActivity extends AppCompatActivity implements CustomRefreshListView.OnRefreshListener {

    private CustomRefreshListView mLv;
    private List<String> datas = new ArrayList<>();

    private SimpleBaseAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_custom_refresh);

        mLv = ((CustomRefreshListView) findViewById(R.id.lv));

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

        mLv.setOnRefreshListener(this);
    }

    @Override
    public void onPullRefresh() {
        mLv.postDelayed(new Runnable() {
            @Override
            public void run() {
                datas.clear();
                for(int i = 0;i<20;i++){
                    datas.add("添加了数据~~"+i);
                }
                mAdapter.notifyDataSetChanged();
                mLv.completeRefresh();
            }
        },1000);

    }

    @Override
    public void onLoadingMore() {
        mLv.postDelayed(new Runnable() {
            @Override
            public void run() {
                int count = mAdapter.getCount();
                for(int i = count; i< count +10; i++){
                    datas.add("添加了数据~~"+i);
                }
                mAdapter.notifyDataSetChanged();
                mLv.completeRefresh();
            }
        },1000);
    }
}
