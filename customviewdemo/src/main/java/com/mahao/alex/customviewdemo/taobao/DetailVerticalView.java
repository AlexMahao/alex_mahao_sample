package com.mahao.alex.customviewdemo.taobao;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 *
 * 仿淘宝详情页
 * Created by alex_mahao on 2016/8/29.
 */
public class DetailVerticalView extends ViewGroup {

    // 滑动到顶部的状态
    public static final int TOP = 1;

    // 滑动到底部的状态
    public static final int BOTTOM = 2;

    public static final String TAG = "DetailVerticalView";

    /**
     * 屏幕高度
     */
    private int mScreenHeight;

    /**
     * 手指上次触摸事件的y轴位置
     */
    private int mLastY;

    /**
     * 点击时y轴的偏移量
     */
    private int mScrollY;

    /**
     * 滚动控件
     */
    private Scroller mScroller;

    /**
     * 最小移动距离判定
     */
    private int mTouchSlop;

    /**
     * 滑动结束的偏移量
     */
    private int mEnd;

    /**
     * 初始按下y轴坐标
     */
    private int mDownStartY;

    /**
     * 记录当前y轴坐标
     */
    private int y;

    /**
     * 控件的高度
     */
    private int mHeight;

    /**
     * 监听的回调
     */
    private ScrollChangeListener scrollChangeListener;

    public DetailVerticalView(Context context) {
        super(context, null);
    }

    public DetailVerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {

        // 创建滑对象，以便滑动时使用
        mScroller = new Scroller(getContext());

        // 获取系统的最小距离
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(getContext()));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取显示区域的高度
        mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 遍历子View
        int count = getChildCount();
        // 控件的高度
        mHeight = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            // 测量子View 高度
            int childHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            measureChild(childView, widthMeasureSpec, childHeight);
            mHeight = getChildAt(i).getMeasuredHeight() + mHeight;
        }
        // 设置控件的高度
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        int childHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                // 确定位置
                child.layout(l, childHeight, r, childHeight + child.getMeasuredHeight());
                childHeight = +child.getMeasuredHeight();
            }
        }

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 获取当前触摸位置Y轴坐标
        y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 第一次按下时的Y轴坐标
                mDownStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果大于mTouchSlop，认为是滑动，则不再让子View处理事件
                if (Math.abs(y - mDownStartY) > mTouchSlop) {
                    // 因为是在onTouchEvent中处理，如果子View处理过事件，
                    // 则该控件的onTouchEvent()不再有DOWN事件，在这里获取一些值
                    mLastY = y;
                    mScrollY = getScrollY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        y = (int) event.getY();
        mScrollY = getScrollY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果通过事件拦截获取到的触摸，则直接就为Move方法
                mLastY = y;
                mScrollY = getScrollY();
                break;

            case MotionEvent.ACTION_MOVE:
                int dy = mLastY - y;

                Log.i(TAG,"dy:"+dy+"-"+"mLastY:"+mLastY+"-"+"mScrolly:"+mScrollY);

                if(mScrollY+dy<0){
                    // 滑动到顶部和底部时，不可再滑动
                    scrollTo(0,0);
                }else if(mScrollY+dy>getMeasuredHeight()-mScreenHeight){
                    scrollTo(0,getMeasuredHeight()-mScreenHeight);
                }else{
                    scrollBy(0, dy);
                    mLastY = y;
                }

                break;
            case MotionEvent.ACTION_UP:

                // 当前偏移量是
                int absScroll = mScrollY+mScreenHeight-getChildAt(0).getMeasuredHeight();

                if(absScroll<0||absScroll>mScreenHeight){
                    // 第一个页面未滑到底部，不做操作，如果absScroll>屏幕的高度，则完全滚动
                    // 不做任何滚动操作
                }else if(absScroll>mScreenHeight/3){
                    if(scrollChangeListener!=null){
                        scrollChangeListener.onScollStateChange(BOTTOM);
                    }
                    // 松开时显示第二个页面
                    mScroller.startScroll(0, mScrollY, 0, mScreenHeight-absScroll);
                }else if(absScroll<mScreenHeight/3){
                    // 回到第一个页面
                    if(scrollChangeListener!=null){
                        scrollChangeListener.onScollStateChange(TOP);
                    }
                    mScroller.startScroll(0,mScrollY,0,-absScroll);
                }
                break;
        }
        postInvalidate();

        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断scroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            // 这里调用View的scrollTo()完成实际的滚动
            scrollTo(0, mScroller.getCurrY());
            //刷新试图
            postInvalidate();
        }
    }


    /**
     * 回到第一个页面的顶部
     */
    public void scrollToTop(){
        mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
        scrollChangeListener.onScollStateChange(TOP);
        postInvalidate();

    }


    /**
     * 监听的接口定义
     */
    public interface  ScrollChangeListener{
        void scrollY(int y);
        void onScollStateChange(int type);
    }

    /**
     * 设置监听
     * @param scrollChangeListener
     */
    public void setScrollChangeListener(ScrollChangeListener scrollChangeListener) {
        this.scrollChangeListener = scrollChangeListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if(scrollChangeListener!=null){
            scrollChangeListener.scrollY(getScrollY());
        }
    }

}
