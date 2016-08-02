package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.mahao.alex.customviewdemo.R;

/**
 *
 *  PathMeasure :用来测量Path 的类
 *
 *      PathMeasure(); 创建一个空的PathMeasure
 *      PathMeasure(Path path,boolean forceClosed);
 *
 *  setPath(); 将Path 和PathMeasure 进行关联
 *      如果Path 发生了变化，则需要调用setPath()方法进行重新关联
 *
 *  isClosed() ; 用于判断Path是否闭合，如果你在关联Path 的时候设置forceClosed ，则该方法一定返回true
 *
 *  getLength(); 获取Path的长度
 *
 *  getSegment(); 获取Path的某一个字段
 *      boolean getSegment (float startD, float stopD, Path dst, boolean startWithMoveTo)
 *          - 返回值 代表是否截取成功  true代表成功，并将值存入到dst中。false代表未截取成功，不会修改dst的值
 *
 *          startD 开始截取的位置距Path起点的位置
 *          stopD  结束截取位置距Path 起点的位置
 *          dst 截取的Path 将会添加到dst中，添加而不是替换
 *          startWithMoveTo 起始点是否使用MoveTo 用于截取的Path的第一个点位置不变
 *
 *  getPosTan(); 获取路径上某一长度的位置及该位置的正切值
 *      boolean getPosTan (float distance, float[] pos, float[] tan)
 *
 *          - 返回值  true 代表成功，值存入后面两个变量中，false 代表获取失败。
 *
 *          - distance ： 距离Path起点的位置
 *          - pos : 该点的坐标值
 *          - tan: 正切值
 *
 *
 *
 *
 *
 *  * Created by alex_mahao on 2016/8/2.
 */
public class View5 extends View{

    private float currentValue = 0; //用于记录当前的位置，取值范围[0,1]映射Path的整个长度

    private float[] pos;   // 当前点的实际位置

    private float[] tan;  // 当前点的tangent值，用于计算图片所需旋转的角度。

    private Bitmap mBitmap;  // 箭头图片

    private Matrix matrix; // 矩阵，用于对图片做一些操作

    private Paint paint;

    public View5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mBitmap =BitmapFactory.decodeResource(getResources(), R.mipmap.jiantou,options);
        matrix = new Matrix();

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);

        Path path = new Path();

        path.addCircle(0,0,200,Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path,false);

        currentValue += 0.005;

        if(currentValue>1){
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength()*currentValue,pos,tan);

        matrix.reset();

        float degress = (float) (Math.atan2(tan[1],tan[0])*180/Math.PI);

        matrix.postRotate(degress,mBitmap.getWidth()/2,mBitmap.getHeight()/2);

        matrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);

        canvas.drawPath(path,paint);

        canvas.drawBitmap(mBitmap,matrix,paint);

        invalidate();
    }
}
