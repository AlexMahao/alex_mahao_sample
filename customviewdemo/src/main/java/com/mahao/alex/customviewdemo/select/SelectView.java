package com.mahao.alex.customviewdemo.select;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.OverScroller;

import java.util.ArrayList;
import java.util.List;

/**
 * 类似于刻度尺的View
 * // 1， 画出刻度尺
 * // 2,  实现滑动
 * Created by alex_mahao on 2016/9/9.
 */
public class SelectView extends View {


    private Paint mPaint;//灰色刻度

    private Paint mPaintRed;//中间刻度画笔

    private Paint mPaintText; // 文字


    private int mWidth;

    private int mHeight;


    /**
     * 存储值
     */
    private List<String> mValueList = new ArrayList<>();


    private float mPointX = 0f;


    private int mMaxValue;

    private int mMinValue;

    // 中间位置的值
    private int mMiddleValue;

    private float mLastX;


    /**
     * 每一个刻度的单位，像素值
     */
    private int mUnit = dp2px(10);

    /**
     * 没5个小格一个大单位
     */
    private int mUnitLongLine = 5;

    /**
     * 回调监听
     */
    private OnSelectListener l;

    /**
     * 滑动控制
     */
    private OverScroller mScroll;

    /**
     * 最小滑动速率
     */
    private int mMinVelocity;

    /**
     * 速度测量
     */
    private VelocityTracker mVelocityTracker;
    private int mMaxVelocity;

    public SelectView(Context context) {
        this(context, null);
    }

    public SelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mScroll = new OverScroller(context);

        // 系统默认的最小速度
        mMinVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

        mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        setValue();

        initPaint();
    }


    /**
     * 初始化Value 的值
     */
    private void setValue() {
        for (int i = 0; i < 100001; i = i + 100) {
            mValueList.add("" + i);
        }

        mMinValue = 0;
        mMaxValue = mValueList.size() - 1;

        mMiddleValue = (mMaxValue - mMinValue) / 2;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 测量获取高度和宽度
        mWidth = measureMeasureSpec(widthMeasureSpec, 200);

        mHeight = measureMeasureSpec(heightMeasureSpec, 20);

        setMeasuredDimension(mWidth, mHeight);

    }

    /**
     * 对宽度和高度进行测量
     *
     * @param measureSpec
     * @param defValue
     * @return
     */
    public int measureMeasureSpec(int measureSpec, int defValue) {

        int size = MeasureSpec.getSize(measureSpec);

        int mode = MeasureSpec.getMode(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            // 精确的

        } else {
            size = dp2px(defValue);
        }

        return size;
    }

    private void initPaint() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(1));


        mPaintRed = new Paint();
        mPaintRed.setAntiAlias(true);
        mPaintRed.setColor(Color.RED);
        mPaintRed.setStrokeWidth(dp2px(1) * 3 / 2);
        mPaintRed.setStyle(Paint.Style.STROKE);


        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.GRAY);
        mPaintText.setTextSize(dp2px(10));
        mPaintText.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawbg(canvas);

        drawLineAndText(canvas);

        drawRedLine(canvas);

        callback();
    }

    /**
     * 设置数据
     * @param value
     */
    public void setDate(String value){

        mPointX = (float) Double.parseDouble(value);

        startAnim();
    }

    /**
     * 回调方法
     * 在onDraw()方法中回调
     */
    private void callback() {
        int current = (int) (Math.rint(mPointX / mUnit));

        if (mMiddleValue - current < 0 || mMiddleValue - current > mMaxValue) {
            return;
        }
        l.onSelect(mValueList.get(mMiddleValue - current));
    }

    /**
     * 画出底部线条
     *
     * @param canvas
     */
    private void drawbg(Canvas canvas) {

        canvas.drawLine(0, mHeight, mWidth, mHeight, mPaint);
    }


    /**
     * 画出红线
     *
     * @param canvas
     */
    private void drawRedLine(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mWidth / 2, mHeight);
        path.lineTo(mWidth / 2, mHeight * 2 / 3);
        canvas.drawPath(path, mPaintRed);
    }

    /**
     * 以mMiddleValue 的距离为中心，此时mPointX为0
     * 画出刻度和红线
     *
     * @param canvas
     */
    private void drawLineAndText(Canvas canvas) {

        for (int i = mMinValue; i <= mMaxValue; i++) {
            int spce = mMiddleValue - i;

            float x = mWidth / 2 - spce * mUnit + mPointX; // 获取X的坐标
            float y = mHeight / 2;
            if (x > 0 && x < mWidth) {
                // 如果x在范围内，则画出坐标

                if (i % mUnitLongLine == 0) {

                    // 如果是长刻度，
                    y = mHeight * 2 / 3;

                    // 整数刻度绘制文本
                    String value = mValueList.get(i);

                    canvas.drawText(value,
                            x - getFontlength(mPaintText, value) / 2,
                            y,
                            mPaintText);

                } else {
                    y = y + y * 2 / 3;
                }

                canvas.drawLine(x, y, x, mHeight, mPaint);
            }

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        // 关键点
        float x = event.getX();
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);


        switch (event.getAction()) {


            case MotionEvent.ACTION_DOWN:
                // 按下不做任何操作
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:

                float mXOffset = x - mLastX; // x 轴较上一次的偏移量

                mPointX = mPointX + mXOffset; // x的总偏移量

                Log.i("info", mPointX + "");
                // 刷新视图
                postInvalidate();


                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 自动依附

                if (!countVelocityTracker(event))//控制快速滑动
                    startAnim();

                break;
        }
        mLastX = x;

        return true;
    }

    @Override
    public void computeScroll() {

        if (mScroll.computeScrollOffset()) {
            float mPointXoff = (mScroll.getFinalX() - mScroll.getCurrX());
            mPointX = mPointX + mPointXoff * functionSpeed();
            startAnim();

        }
        super.computeScroll();
    }


    /**
     * 控制滑动速度
     *
     * @return
     */
    private float functionSpeed() {
        return 0.5f;
    }


    /**
     * 控制快速滑动
     */
    private boolean countVelocityTracker(MotionEvent event) {
        mVelocityTracker.computeCurrentVelocity(1000, 5000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroll.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE,
                    Integer.MAX_VALUE, 0, 0);
            return true;
        }
        return false;
    }

    /**
     * 手指离开时，判断位置，自动依附到最近的位置
     */
    private void startAnim() {
        float absXOffset = Math.abs(mPointX);
        float absMaxOffset = (mMaxValue - mMinValue) * mUnit / 2;

        if (absXOffset > absMaxOffset) {
            // 超出了边界
            if (mPointX < 0) {
                // 最右,移动到最大值 mMaxValue
                moveToX(mMiddleValue - mMaxValue, 300);
            } else {
                //最左,移动到最小值 m
                moveToX(mMiddleValue - mMinValue, 300);
            }
        } else {
            //没有超出边界
            int current = (int) (Math.rint(mPointX / mUnit));
            moveToX(current, 200);
        }
    }


    /**
     * 移动到指定位置
     *
     * @param distance 目标刻度
     * @param time     移动的时间
     */
    public void moveToX(int distance, int time) {
        if (mScrolleAnim != null)
            clearAnimation();

        mScrolleAnim = new ScrolleAnim(mPointX, distance * mUnit);
        mScrolleAnim.setDuration(time);
        startAnimation(mScrolleAnim);

    }

    private ScrolleAnim mScrolleAnim;// 平移动画

    private class ScrolleAnim extends Animation {

        // 初始X坐标
        float fromX = 0f;

        // 目标X坐标
        float desX = 0f;

        public ScrolleAnim(float fromX, float desX) {
            this.fromX = fromX;
            this.desX = desX;
        }


        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            mPointX = fromX + (desX - fromX) * interpolatedTime;//计算动画每贞滑动的距离
            invalidate();


        }
    }

    /**
     * 获取文本的长度
     */
    public float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }


    /**
     * dp 转像素
     *
     * @param dp
     * @return
     */
    public int dp2px(int dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
        return px;
    }


    public void setOnSelectListener(OnSelectListener l) {
        this.l = l;
    }

    interface OnSelectListener {

        void onSelect(String value);
    }


}
