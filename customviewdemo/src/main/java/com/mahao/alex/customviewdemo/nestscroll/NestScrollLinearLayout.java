package com.mahao.alex.customviewdemo.nestscroll;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by mahao on 17-3-29.
 */

public class NestScrollLinearLayout extends LinearLayout {

    public void log(String log) {
        Log.i("NestScrollLinearLayout", log);
    }

    private int mFirstChildHeight;

    /**
     * 控件消费的高度
     */
    private int mConsumeHeight = 0;

    public NestScrollLinearLayout(Context context) {
        this(context, null);
    }

    public NestScrollLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public NestScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mFirstChildHeight = getChildAt(0).getMeasuredHeight();
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedPrePerformAccessibilityAction(View target, int action, Bundle args) {
        return super.onNestedPrePerformAccessibilityAction(target, action, args);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (dy > 0) {
            // 向下滑动
            if (mConsumeHeight >= mFirstChildHeight) {
                // 向下滑动不再消费
            } else {
                mConsumeHeight = mConsumeHeight + dy;
                if (mConsumeHeight > mFirstChildHeight) {
                    dy = mConsumeHeight - mFirstChildHeight;
                    mConsumeHeight = mFirstChildHeight;
                }
                scrollTo(0, mConsumeHeight);
                consumed[1] = dy;
            }
        }
        log("dx=" + dx + "  dy=" + dy + "  consumed=" + consumed[0] + " " + consumed[1]);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        // 未被消费的
        if (dyUnconsumed < 0 && mConsumeHeight > 0){
            mConsumeHeight = mConsumeHeight + dyUnconsumed;
            if (mConsumeHeight < 0) {
                dyUnconsumed = 0 - mConsumeHeight;
                mConsumeHeight = 0;
            }
            scrollTo(0, mConsumeHeight);
        }
        // 滚动之后的回调 ，子类违背消费的
        log("dxConsumed=" + dxConsumed + "  dyConsumed=" + dyConsumed + "  dxUnconsumed=" + dxUnconsumed + "  dyUnconsumed=" + dyUnconsumed);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
    }
}
