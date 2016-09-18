package com.mahao.alex.customviewdemo.precent_circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex_mahao on 2016/9/14.
 */
public class PercentCircle extends View {

    /**
     * 颜色
     */
    private List<Integer> mColors = new ArrayList<>();

    // 所占百分比
    private List<Float> mPercents = new ArrayList<>();

    private int mRadius = dp2px(250);

    private int mInnerRadius = dp2px(85);

    private Paint mInnerPaint ;

    private Paint mArcPaint;

    public PercentCircle(Context context) {
        this(context,null);
    }

    public PercentCircle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PercentCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
        initPaint();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mColors.add(Color.parseColor("#5ccfd6"));
        mColors.add(Color.parseColor("#3d9eff"));
        mColors.add(Color.parseColor("#ffb83c"));
        mColors.add(Color.parseColor("#ff4a60"));

        mPercents.add((float) 0.1);
        mPercents.add((float) 0.4);
        mPercents.add((float) 0.3);
        mPercents.add((float) 0.2);
    }

    public void setData(List<Integer> colors,List<Float> percents){
        mColors.clear();
        mColors.addAll(colors);
        mPercents.clear();
        mPercents.addAll(percents);
        invalidate();
    }

    private void initPaint() {

        mArcPaint = new Paint();
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setAntiAlias(true);


        mInnerPaint = new Paint();
        mInnerPaint.setColor(Color.WHITE);
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        float startAngle = 0;

        float totleAngle = 360-mPercents.size();

        RectF rect = new RectF(0,0,mRadius,mRadius);

        for(int i = 0;i<mPercents.size();i++){


            float curAngle = mPercents.get(i)*totleAngle;

            if(curAngle==0){
                 continue;
            }

            mArcPaint.setColor(mColors.get(i));

            canvas.drawArc(rect,startAngle, mPercents.get(i)*totleAngle,true,mArcPaint);

            startAngle = curAngle+startAngle+1;
        }

        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,mInnerRadius,mInnerPaint);
    }

    /**
     * 像素转dp
     * @param px
     * @return
     */
    private int dp2px(int px){

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,getContext().getResources().getDisplayMetrics());
    }

}
