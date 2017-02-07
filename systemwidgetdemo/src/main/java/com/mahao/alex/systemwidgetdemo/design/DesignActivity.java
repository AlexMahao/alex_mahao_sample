package com.mahao.alex.systemwidgetdemo.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.mahao.alex.systemwidgetdemo.R;

/**
 * Created by alex_mahao on 2016/10/12.
 */
public class DesignActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefresh;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        mRefresh = ((SwipeRefreshLayout) findViewById(R.id.swipe));
        mRefresh.setProgressViewOffset(true, -20, 100);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset >= 0) {
                    mRefresh.setEnabled(true);
                } else {
                    mRefresh.setEnabled(false);
                }
            }
        });
    }
}
