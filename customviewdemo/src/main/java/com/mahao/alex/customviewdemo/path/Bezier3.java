package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 *  三阶贝塞尔曲线
 * Created by alex_mahao on 2016/8/1.
 */
public class Bezier3 extends View{

    private Paint mPaint;

    private int centerX ,centerY;

    private PointF start,end,controll1,controll2;

    private boolean mode = true;

    public Bezier3(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint  = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);

        start = new PointF(0,0);
        end = new PointF(0,0);
        controll1 = new PointF(0,0);
        controll2 = new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;
        start.x = centerX-200;
        start.y = centerY;
        end.x = centerX+200;
        end.y = centerY;
        controll1.x = centerX;
        controll1.y = centerY-100;
        controll2.x = centerX;
        controll2.y = centerY-100;

    }


    public void setMode(boolean mode){
        this.mode  = mode;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mode){
            controll1.x = event.getX();
            controll1.y = event.getY();
        }else{
            controll2.x = event.getX();
            controll2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x,start.y,mPaint);
        canvas.drawPoint(end.x,end.y,mPaint);
        canvas.drawPoint(controll1.x,controll1.y,mPaint);
        canvas.drawPoint(controll2.x,controll2.y,mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, controll1.x, controll1.y, mPaint);
        canvas.drawLine(controll1.x, controll1.y,controll2.x, controll2.y, mPaint);
        canvas.drawLine(controll2.x, controll2.y,end.x, end.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.cubicTo(controll1.x,controll1.y,controll2.x,controll2.y,end.x,end.y);

        canvas.drawPath(path,mPaint);
    }
}
