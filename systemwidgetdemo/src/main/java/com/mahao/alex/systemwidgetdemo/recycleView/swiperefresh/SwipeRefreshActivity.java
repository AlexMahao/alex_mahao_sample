package com.mahao.alex.systemwidgetdemo.recycleView.swiperefresh;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mahao.alex.systemwidgetdemo.R;
import com.mahao.alex.systemwidgetdemo.recycleView.DividerItemDecoration;
import com.mahao.alex.systemwidgetdemo.recycleView.HomeAdapter;

/**
 * 使用原生的SwipeRefreshLayout和代码判断
 *      实现RecyclewView 的刷新和加载更多
 *
 * Created by Alex_MaHao on 2016/5/10.
 */
public class SwipeRefreshActivity extends AppCompatActivity implements LoadDataScrollController.OnRecycleRefreshListener {


    private SwipeRefreshLayout mSwipeRefresh;

    private RecyclerView mRecycle;

    private HomeAdapter mAdapter;
    private LoadDataScrollController mController;
    private ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_swiperefresh);

        mRecycle = ((RecyclerView) findViewById(R.id.swipe_target));

        mSwipeRefresh = ((SwipeRefreshLayout) findViewById(R.id.swipe_refresh));

        mSwipeRefresh.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);

        mController = new LoadDataScrollController(this);

        mSwipeRefresh.setOnRefreshListener(mController);

        mAdapter = new HomeAdapter();

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //添加分割线
        mRecycle.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));


        mRecycle.setLayoutManager(layoutManager);

        mRecycle.setItemAnimator(new DefaultItemAnimator());

        mRecycle.setAdapter(mAdapter);

        mAdapter.refresh();

        mRecycle.addOnScrollListener(mController);
    }

    @Override
    public void refresh() {
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.refresh();
                mSwipeRefresh.setRefreshing(false);
                mController.setLoadDataStatus(false);
            }
        },2000);
    }

    @Override
    public void loadMore() {

        pd = new ProgressDialog(this);
        pd.show();
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.add();
                mController.setLoadDataStatus(false);
                pd.dismiss();
            }
        },2000);
    }
}
