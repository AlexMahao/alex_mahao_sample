package com.mahao.alex.customviewdemo.horizontalProgress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 *  水平进度条
 * Created by alex_mahao on 2016/10/11.
 */
public class HorizontalProgressView extends View {

    /**
     * 灰色
     */
    private int mGrayColor = Color.parseColor("#f4f4f4");

    /**
     * 圆环灰度
     */
    private int mCircleColor = Color.parseColor("#aaaaaa");

    /**
     * 蓝色
     */
    private int mBlueColor = Color.parseColor("#3d9eff");

    /**
     * 当前进度
     */
    private float mProgress = 1;

    /**
     * 进度条高度
     */
    private float mProgressHeight = dp2px(1.5f);

    /**
     * 程序高度
     */
    private float mHeight = dp2px(10f);

    /**
     * 外圆的半径
     */
    private float mArcRadius = dp2px(5f);

    /**
     * 内圆半径
     */
    private float mInnerRadius = dp2px(2.5f);

    /**
     * 控件宽度
     */
    private float mWidth;

    /**
     * 进度画笔
     */
    private Paint mProgressPaint;

    /**
     * 背景画笔
     */
    private Paint mBgPaint;


    /**
     * 外圆画笔
     */
    private Paint mArcPaint;

    /**
     * 内圆画笔
     */
    private Paint mInnerPaint;


    public HorizontalProgressView(Context context) {
        this(context,null);
    }

    public HorizontalProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //

        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        mBgPaint = new Paint();
        mBgPaint.setColor(mGrayColor);
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStrokeWidth(mProgressHeight);


        mProgressPaint = new Paint();
        mProgressPaint.setColor(mBlueColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeWidth(mProgressHeight);


        mArcPaint = new Paint();
        mArcPaint.setColor(mBlueColor);
        mArcPaint.setAlpha(80);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStrokeWidth(mArcRadius-mInnerRadius);
        mArcPaint.setStyle(Paint.Style.STROKE);


        mInnerPaint = new Paint();
        mInnerPaint.setColor(mBlueColor);

        mInnerPaint.setAntiAlias(true);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 控件长度
        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        // 设置控件高度
        setMeasuredDimension(widthMeasureSpec, ((int) mHeight));

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        drawGrayBg(canvas);

        drawProgress(canvas);

        /**
         * 画出导航园
         */
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {

        if(mProgress>=1){
            mInnerPaint.setColor(mCircleColor);
            mArcPaint.setColor(mCircleColor);

        }else{
            mInnerPaint.setColor(mBlueColor);
            mArcPaint.setColor(mBlueColor);
        }
        mArcPaint.setAlpha(80);
        // 画出内圆
        canvas.drawCircle(mArcRadius+(mWidth-mArcRadius*2)*mProgress,mHeight/2,mInnerRadius,mInnerPaint);

        // 画弧
        canvas.drawCircle(mArcRadius+(mWidth-mArcRadius*2)*mProgress,mHeight/2,mInnerRadius+(mArcRadius-mInnerRadius)/2,mArcPaint);
    }

    /**
     * 画出进度
     */
    private void drawProgress(Canvas canvas) {

        if(mProgress>=1){
            canvas.drawLine(mArcRadius,mHeight/2,mArcRadius+(mWidth-mArcRadius*2)*0,mHeight/2,mProgressPaint);
            return;
        }

        canvas.drawLine(mArcRadius,mHeight/2,mArcRadius+(mWidth-mArcRadius*2)*mProgress,mHeight/2,mProgressPaint);
    }

    /**
     * 画出灰色背景
     */
    private void drawGrayBg(Canvas canvas) {

        canvas.drawLine(mArcRadius,mHeight/2,mWidth-mArcRadius,mHeight/2,mBgPaint);
    }

    public float dp2px(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getContext().getResources().getDisplayMetrics());
    }


    public void setProgress(float progress){
        mProgress = progress;
    }
}
