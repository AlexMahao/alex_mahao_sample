package com.mahao.alex.systemwidgetdemo.recycleView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mahao.alex.systemwidgetdemo.R;

/**
 * RecycleView的使用
 *    1，
 *
 * Created by mdw on 2016/5/6.
 */
public class RecycleMainActivity extends AppCompatActivity  {


    private RecyclerView mRecycleView;


    private SwipeRefreshLayout swipeRefreshLayout;


    private HomeAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle);

        mRecycleView = ((RecyclerView) findViewById(R.id.recycleView));

        adapter = new HomeAdapter();

        //设置布局管理器，如果不设置此管理器，将导致数据无法显示

        //设置网格布局管理器 ,context  spanCount:每一行或列显示几个   oriention：方向
        //  reverseLayout:布局是否反转，及item倒着显示，同时，滚动到最后，及仍然显示第一个。但position对应不变。
        //GridLayoutManager layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,true);


        //瀑布流，spanCount ：列    oriention:方向
       // StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //添加分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));


        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(adapter);

        adapter.refresh();

    }




}
