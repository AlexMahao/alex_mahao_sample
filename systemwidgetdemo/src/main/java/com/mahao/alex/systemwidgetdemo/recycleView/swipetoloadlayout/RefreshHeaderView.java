package com.mahao.alex.systemwidgetdemo.recycleView.swipetoloadlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;


/**
 * 基础的refreshHeadView
 */
public class RefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onRefresh() {
        //下拉到一定位置松开之后，调用此方法
        setText("refresh");

        Log.i("info","onRefresh");
    }

    @Override
    public void onPrepare() {

        //下拉之前调用此方法
        Log.i("info","onPrepare");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            //当前Y轴偏移量大于控件高度时，标识下拉到界限，显示“松开已刷新”
            if (yScrolled >= getHeight()) {

            } else {
                //未达到偏移量

            }
        }
        Log.i("info","onMove");
    }

    @Override
    public void onRelease() {
        //达到一定滑动距离，松开刷新时调用
        setText("onRelease");
        Log.i("info","onRelease");
    }

    @Override
    public void onComplete() {
        //加载完成之后调用此方法
        setText("complete");
        Log.i("info","onComplete");
    }

    @Override
    public void onReset() {
        //重置
        setText("onReset");
        Log.i("info","onReset");
    }
}
