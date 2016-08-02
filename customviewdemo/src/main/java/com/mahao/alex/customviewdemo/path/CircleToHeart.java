package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * 有一个圆形到一个心形
 * Created by alex_mahao on 2016/8/2.
 */
public class CircleToHeart extends View{

    private static final float C = 0.551915024494f;

    private Paint mPaint;

    private int mCenterX ,mCenterY;

    private PointF mCenter  = new PointF(0,0);

    private float mCircleRadius = 200;

    private float mDifference = mCircleRadius*C;

    private float[] mData = new float[8];

    private float[] mCtrl = new float[16];

    private float mDuration = 1000;
    private float mCurrent =0;
    private float mCount = 100;

    private float mPiece = mDuration/mCount;


    public CircleToHeart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);

        // 初始化数据点
        mData[0] = 0;
        mData[1] = -mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        // 初始化绘制点
        mCtrl[0] = mData[0]+mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3]-mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3]+mDifference;


        mCtrl[6] = mData[4]+mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4]-mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7]+mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7]-mDifference;

        mCtrl[14] = mData[0]-mDifference;
        mCtrl[15] = mData[1];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        mCenterX = w/2;
        mCenterY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mCenterX,mCenterY);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(mData[0],mData[1]);

        path.cubicTo(mCtrl[0],mCtrl[1],mCtrl[2],mCtrl[3],mData[2],mData[3]);
        path.cubicTo(mCtrl[4],mCtrl[5],mCtrl[6],mCtrl[7],mData[4],mData[5]);
        path.cubicTo(mCtrl[8],mCtrl[9],mCtrl[10],mCtrl[11],mData[6],mData[7]);
        path.cubicTo(mCtrl[12],mCtrl[13],mCtrl[14],mCtrl[15],mData[0],mData[1]);

        canvas.drawPath(path,mPaint);


        mCurrent += mPiece;
        if (mCurrent < mDuration){

            mData[1] += 120/mCount;

            mCtrl[4] -= 20/mCount;
            mCtrl[7] -= 80/mCount;
            mCtrl[9] -= 80/mCount;
            mCtrl[10] += 20/mCount;

            postInvalidateDelayed((long) mPiece);
            //    postInvalidateDelayed((long) mPiece);
        }


    }
}
