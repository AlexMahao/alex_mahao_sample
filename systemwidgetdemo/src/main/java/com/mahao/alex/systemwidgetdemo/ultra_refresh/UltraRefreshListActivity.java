package com.mahao.alex.systemwidgetdemo.ultra_refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mahao.alex.systemwidgetdemo.R;
import com.mahao.alex.systemwidgetdemo.listView.SimpleBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Alex_MaHao on 2016/5/18.
 */
public class UltraRefreshListActivity extends AppCompatActivity implements UltraRefreshListener {

    private PtrClassicFrameLayout mPtrFrame;

    private List<String> datas = new ArrayList<>();

    private SimpleBaseAdapter mAdapter;

    private UltraRefreshListView mLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_ultra);

        mPtrFrame = ((PtrClassicFrameLayout) findViewById(R.id.ultra_ptr));

        mLv = ((UltraRefreshListView) findViewById(R.id.ultra_lv));

        CustomUltraRefreshHeader header = new CustomUltraRefreshHeader(this);

        mPtrFrame.setHeaderView(header);

        mPtrFrame.addPtrUIHandler(header);

        mPtrFrame.setPtrHandler(mLv);

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

        mLv.setUltraRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                datas.clear();
                for(int i = 0;i<20;i++){
                    datas.add("添加了数据~~"+i);
                }
                mLv.refreshComplete();
                mAdapter.notifyDataSetChanged();
            }
        },1000);

    }

    @Override
    public void addMore() {
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {

                int count = mAdapter.getCount();
                for(int i = count; i< count +10; i++){
                    datas.add("添加了数据~~"+i);
                }
                mAdapter.notifyDataSetChanged();
                mLv.refreshComplete();
            }
        },1000);



    }
}
