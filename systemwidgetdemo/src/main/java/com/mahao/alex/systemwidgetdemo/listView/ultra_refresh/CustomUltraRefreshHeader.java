package com.mahao.alex.systemwidgetdemo.listView.ultra_refresh;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.recycleView.swipetoloadlayout.CircleView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 *
 * 自定义刷新的头部
 * Created by Alex_MaHao on 2016/5/18.
 */
public class CustomUltraRefreshHeader extends RelativeLayout implements PtrUIHandler {

    CircleView mCircleView;

    TextView mDescText;

    private ObjectAnimator anim;

    public CustomUltraRefreshHeader(Context context) {
        this(context,null);
    }

    public CustomUltraRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomUltraRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,100);
    }

    /**
     * 初始化布局
     */
    private void initView() {

        int circlewidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());

        mCircleView = new CircleView(getContext());

        LinearLayout.LayoutParams circleParams = new LinearLayout.LayoutParams(circlewidth,circlewidth);

        mCircleView.setLayoutParams(circleParams);

        mDescText = new TextView(getContext());

        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(circlewidth*3, ViewGroup.LayoutParams.WRAP_CONTENT);

        descParams.gravity = Gravity.CENTER;
        descParams.setMargins(circlewidth/2,0,0,0);
        mDescText.setLayoutParams(descParams);
        mDescText.setTextSize(12);
        mDescText.setTextColor(Color.GRAY);
        mDescText.setText("下拉刷新");

        //添加线性的父布局
        LinearLayout ll = new LinearLayout(getContext());
        RelativeLayout.LayoutParams llParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llParams.addRule(CENTER_IN_PARENT);
        ll.setLayoutParams(llParams);
        ll.setPadding(10,10,10,10);

        ll.addView(mCircleView);
        ll.addView(mDescText);

        addView(ll);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        //重置时，将动画置为初始状态
        mCircleView.setRotation(0f);
        Log.i("info","onUIReset");
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mDescText.setText("下拉加载数据");
        Log.i("info","onUIRefreshPrepare");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

        //开始刷新，启动动画
        anim = ObjectAnimator.ofFloat(mCircleView, "rotation", mCircleView.getRotation(), mCircleView.getRotation()+360f)
                .setDuration(500);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.start();

        mDescText.setText("正在加载数据");
        Log.i("info","onUIRefreshBegin");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        anim.cancel();
        mDescText.setText("加载完成");
        Log.i("info","onUIRefreshComplete");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        int headerHeight = ptrIndicator.getHeaderHeight();//头部的高度
        int lastPosY = ptrIndicator.getLastPosY();//上一次滑动的Y偏移值
        int offsetToRefresh = ptrIndicator.getOffsetToRefresh();//舒心需要滑动的偏移量
        float offsetY = ptrIndicator.getOffsetY();//当前与上一次滑动处理的偏移值
        int currentPosY = ptrIndicator.getCurrentPosY();//当前系统偏移值

        if (isUnderTouch&&status== PtrFrameLayout.PTR_STATUS_PREPARE) {

            mCircleView.setRotation(currentPosY);
            if(currentPosY<offsetToRefresh&&lastPosY >= offsetToRefresh){
                //表示不刷新了
                mDescText.setText("下拉加载数据");
            }else if(currentPosY>offsetToRefresh&&lastPosY<=offsetToRefresh){
                mDescText.setText("松开加载更多");
            }
        }

       /* if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {*//*
        Log.i("info","isUnderTouch"+isUnderTouch+"headHeight: "+headerHeight+" lastPosY "+lastPosY+" offsetToRefresh "+offsetToRefresh+" offsetY "+offsetY+" currentPosY "+currentPosY);*/
    }
}
