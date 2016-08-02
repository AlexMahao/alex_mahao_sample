package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by alex_mahao on 2016/8/2.
 */
public class RadarView extends View {

    private int centerX, centerY;

    private float radius;

    private Paint mPaint;

    private Paint mRegionPaint;

    private double[] data = {100, 60, 60, 60, 100, 50}; //各维度分值

    private float maxValue = 100;             //数据最大值

    private int count = 6;

    private float angle = (float) (Math.PI * 2 / count);

    private Paint textPaint;

    private String[] titles = {"a","b","c","d","e","f"};

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);

        mRegionPaint = new Paint();

        mRegionPaint.setColor(Color.BLUE);
        mRegionPaint.setStrokeWidth(2);
        mRegionPaint.setAlpha(100);
        mRegionPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        textPaint = new Paint();
        textPaint.setTextSize(20);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
        drawText(canvas);
    }

    /**
     * 绘制文本
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float fontHeight = fontMetrics.descent-fontMetrics.ascent;


        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < 6; i++) {

            double num = data[i];
            if(i==0){
                float x = (float) (centerX + num/maxValue*radius * Math.cos(angle * i));
                float y = (float) (centerY + num/maxValue*radius * Math.sin(angle * i));
                path.moveTo(x, y);
                Log.i("info","x:"+x+"y:"+y);
            }else{
                float x = (float) (centerX + num/maxValue*radius * Math.cos(angle * i));
                float y = (float) (centerY + num/maxValue*radius * Math.sin(angle * i));
                path.lineTo(x, y);

            }

        }
        path.close();
        canvas.drawPath(path,mRegionPaint);
    }

    /**
     * 绘制从中心到各个订单的直线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < 6; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mPaint);
        }
    }


    /**
     * 绘制蜘蛛网络
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {

        Path path = new Path();

        float r = radius / 5;

        for (int i = 1; i < 6; i++) {
            float curR = r * i;
            path.reset();

            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }

            }
            path.close();//闭合路径
            canvas.drawPath(path, mPaint);
        }
    }
}
