package com.mahao.alex.systemwidgetdemo.ultra_refresh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mahao.alex.systemwidgetdemo.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Alex_MaHao on 2016/5/18.
 */
public class UltraRefreshActivity extends Activity {


    private PtrClassicFrameLayout ptrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_ultra_refresh);

        ptrFrame = ((PtrClassicFrameLayout) findViewById(R.id.ultra_ptr_frame));

        ptrFrame.setLastUpdateTimeRelateObject(this);

        //下拉刷新的阻力，下拉时，下拉距离和显示头部的距离比例，值越大，则越不容易滑动
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.2f);

        ptrFrame.setDurationToClose(200);//返回到刷新的位置（暂未找到）

        ptrFrame.setDurationToCloseHeader(1000);//关闭头部的时间 // default is false

        ptrFrame.setPullToRefresh(false);//当下拉到一定距离时，自动刷新（true），显示释放以刷新（）

        ptrFrame.setKeepHeaderWhenRefresh(true);//见名只意

        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame,
                                             View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,
                        content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                //数据刷新的回调

                ptrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 1500);
            }
        });
    }
}
