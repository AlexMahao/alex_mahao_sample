package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 *  Path
 *
 *      moveTo()  ： 移动到下一次的起点位置
 *      setLastPoint() : 重置当前Path中最后一个点的位置，如果在绘制之前调用，效果和moveTo() 相同
 *      lineTo() : 添加上一个点到当前点之间的直线Path.
 *      close() : 闭合路径，链接最后一个点和第一个，形成闭合的图形
 *          -  close的作用是封闭路径，与当前最后一个点和第一个点并不等价。如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。

 *       addCircle();  添加圆形
 *       addOval(RectF oval,Path.Direction dir); 绘制椭圆
 *       addRect(float left.float top , float right,float bottom,Path.Direction dir); 绘制矩形
 *       addRect(RectF rect ,Path.Direction dir); 绘制矩形
 *
 *       addRoundRect(Rect rect , float[] radii,Path.Direction dir); 绘制圆角矩形
 *       addRoundRect(Rect rect,float rx ,float ry,Path.Direction dir);
 *
 *      Path.Direction :
 *          - CW :  clockwise  顺时针
 *          - CCW  : counter-clockwise  逆时针
 *
 *      第二类Path  将另一个path 添加到当前Path 中
 *          addPath(Path src);
 *          addPath(Path src , float dx,float dy);  dx dy 代表位移量
 *          addPath(Path src, Matrix matrix);  matrix 进行矩阵的缩放
 *
 *
 *      添加圆弧：
 *          addArc() : 直接添加一个圆弧
 *          arcTo(): 如果圆弧的第一个点和上一次的最后一个点不相同，则连接这两个点
 *              - arcTo(RectF oval, float startAngle, float sweepAngle,boolean forceMoveTo)
 *                  forceMoveTo:
 *                      true  最后一个点移动到圆弧的起点，不连接最后一个点和圆弧
 *                      false 不移动，最后一个点连接圆弧的起点
 *
 *
 *      isEmpty()
 *      isRect();
 *      isConvex();
 *      set
 *          set(Path src): 将一个Path 替换当前的set
 *      offset
 *          offset(float dx,float dy);  将当前的Path移动一定的距离，平移，只针对当前的Path
 *          offset(float dx,float dy,Path dst); dst ，存储平移之前的Path
 *
 * Created by alex_mahao on 2016/8/1.
 */
public class View3 extends View {
    Paint mPaint;

    public View3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint= new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        canvas.translate(width/2,height/2);

        Path path = new Path();
        path.lineTo(100,100);

       /* Rect oval = new Rect(0,0,300,300);
        path.arcTo();
*/
   /*     Path path = new Path();
        Path src = new Path();

        path.addRect(-200,-200,200,200,Path.Direction.CW);

        src.addCircle(0,0,100,Path.Direction.CW);
        path.addPath(src,0,200);*/

       /* path.lineTo(200,200);
        path.lineTo(200,0);*/

       // path.addRect(0,0,100,100, Path.Direction.CW);


        // 判断是否是一个矩形
/*        Path path = new Path();
        path.lineTo(100,0);
        path.lineTo(100,100);
        path.lineTo(0,100);
        path.lineTo(0,0);

        RectF rect = new RectF();
        boolean b = path.isRect(rect); // rect 获取到矩形的属性*/


        canvas.drawPath(path,mPaint);
    }
}
