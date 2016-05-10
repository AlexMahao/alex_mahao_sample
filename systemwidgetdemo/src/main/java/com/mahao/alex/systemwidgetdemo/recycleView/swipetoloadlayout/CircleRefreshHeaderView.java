package com.mahao.alex.systemwidgetdemo.recycleView.swipetoloadlayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * 自定义的下拉刷新控件 头部
 * Created by Alex_MaHao on 2016/5/10.
 */
public class CircleRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    CircleView mCircleView;

    TextView mDescText;

    private ObjectAnimator anim;

    private boolean isRelease;

    public CircleRefreshHeaderView(Context context) {
        this(context, null, 0);
    }

    public CircleRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
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

        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(circlewidth*2, ViewGroup.LayoutParams.WRAP_CONTENT);

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
    public void onRefresh() {
        anim = ObjectAnimator.ofFloat(mCircleView, "rotation", mCircleView.getRotation(), mCircleView.getRotation()+360f)
                .setDuration(500);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.start();

        mDescText.setText("正在加载数据");
    }

    @Override
    public void onPrepare() {
        isRelease = false;
    }

    @Override
    public void onMove(int yScroll, boolean isComplete, boolean b1) {
        if (!isComplete) {
            if (yScroll < getHeight()) {
                mDescText.setText("下拉刷新");
            } else {
                mDescText.setText("松开刷新更多");
            }

            if (!isRelease)
                mCircleView.setRotation(((float) yScroll) / getHeight() * 360f);
        }


    }

    @Override
    public void onRelease() {
        isRelease = true;
    }

    @Override
    public void onComplete() {
        anim.cancel();
        mDescText.setText("加载完成");
    }

    @Override
    public void onReset() {
        mCircleView.setRotation(0f);
    }
}
