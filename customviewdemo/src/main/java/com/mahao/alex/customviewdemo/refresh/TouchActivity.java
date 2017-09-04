package com.mahao.alex.customviewdemo.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.mahao.alex.customviewdemo.R;

/**
 * Created by mahao on 17-7-7.
 */

public class TouchActivity extends AppCompatActivity {

    private View mHeaderView;
    private SwipeRefreshLayout mRefreshLayout;
    private ScrollView mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        mRootView = (ScrollView) findViewById(R.id.root);
        mHeaderView = findViewById(R.id.header);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRefreshLayout.dispatchTouchEvent(event);
                return true;
            }
        });
    }
}
