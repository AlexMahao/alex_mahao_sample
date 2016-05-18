package com.mahao.alex.systemwidgetdemo.listView.ultra_refresh;

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

        //查找控件
        mPtrFrame = ((PtrClassicFrameLayout) findViewById(R.id.ultra_ptr));

        mLv = ((UltraRefreshListView) findViewById(R.id.ultra_lv));

        //创建我们的自定义头部视图
        CustomUltraRefreshHeader header = new CustomUltraRefreshHeader(this);

        //设置头部视图
        mPtrFrame.setHeaderView(header);

        //设置视图修改的回调，因为我们的CustomUltraRefreshHeader实现了PtrUIHandler
        mPtrFrame.addPtrUIHandler(header);

        //设置数据刷新的会回调，因为UltraRefreshListView实现了PtrHandler
        mPtrFrame.setPtrHandler(mLv);

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

        //设置数据刷新回调接口
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
                //刷新完成
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
                //刷新完成
                mLv.refreshComplete();
            }
        },1000);

    }
}
