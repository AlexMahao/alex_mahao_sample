package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 *  Canvas速查表：
 *      drawColor(),drawRGB(),drawARGB()。使用单一颜色绘制画布。
 *      drawPoint(),
 *
 *  绘制圆角矩形  canvas.drawRoundRect()
 *      第一种实现方式：RectF rectF = new RectF(100,100,800,400);canvas.drawRoundRect(rectF,30,30,mPaint);
 *      第二种实现方式，该方式在API21以上添加：canvas.drawRoundRect(100,100,800,400,30,30,mPaint);
 *
 *      使用方式和绘制矩形类似。唯一多出的两个参数，rx,ry。 圆弧的半径。两个值表示不是圆，可以是椭圆。
 *
 *      当rx,ry 分别为宽和高的一般是，显示的为椭圆。如果大于时，默认按照一半处理。
 *
 *  绘制椭圆：  canvas.drawOval();
 *      第一种实现方式：RectF rectF = new RectF(100,100,800,400); canvas.drawOval(rectF,mPaint);
 *      第二种实现方式：canvas.drawOval(100,100,800,400,mPaint);
 *
 *      绘制椭圆的原理就是绘制矩形的内切圆。所以使用方法和矩形类似。
 *      PS: 如果传入的矩形为正方形，则绘制的就是一个圆。
 *
 *  绘制圆：
 *      canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心在（500,500），半径为400的圆。
 *
 *  绘制圆弧： canvas.drawArc()
 *      第一种实现方式：public void drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint)
 *      第二种实现方式：drawArc(float left, float top, float right, float bottom, float startAngle,float sweepAngle, boolean useCenter, @NonNull Paint paint)
 *
 *      实现方式中，和绘制椭圆类似，唯一多的三个参数：
 *          startAngle: 起始角度。
 *          sweepAngle： 偏移的角度。
 *          useCenter ： 是否使用中心点。
 *
 *       使用中心店，则弧度与中心点共同形成形状。如果不使用中心点，则弧度的初始与结束点形成图形。
 *
 *
 *  Paint : 画笔
 *      setStyle(Paint.Style.FILL); 设置画笔模式为填充。
 *          STROKE: 描边。
 *          FILL: 填充。
 *          FILL_AND_STROKE:描边加填充。
 *
 *  canvas:  画布操作：该操作是针对画布的坐标系进行移动的。
 *      位移： translate(dx,dy);
 *          位移操作是相对于上一次的坐标系为基础的偏移量
 *
 *      缩放： scale();
 *          第一种实现方式：    scale (float sx, float sy)
 *          第二种实现方式： scale (float sx, float sy, float px, float py)
 *
 *          sx: x轴放大的倍数。 可取值 -∞~+∞
 *          sy: y轴放大的倍数。 同上
 *              当取负值时，先进行对应比例的缩放，再根据中心点进行翻转。
 *
 *          px: 缩放中心点 x 轴相对于坐标中心的偏移量
 *          py: 缩放中心点 y 轴相对于坐标中心的偏移量
 *
 *          PS: 缩放也是可叠加的。
 *
 *      翻转： rotate();
 *          第一种实现方式： rotate(float degress);
 *          第二种实现方式： rotate(float degress,float px,float py);
 *
 *          degress : 旋转的角度
 *          px：旋转中心点 x 轴相对于坐标中心的偏移量
 *          py: 旋转中心点 y 轴相对于坐标中心的偏移量
 *
 *          PS： 旋转也是可以叠加的。
 *
 *      错切(倾斜)  skew(). 类似于立体感。
 *
 *
 *      save()和restore() 的简单理解。
 *          save() ： 保存坐标轴的状态。之后的旋转等操作，是在另一个图层中操作的。
 *          restore : 恢复到之前的状态。（坐标系等）
 *
 *
 *
 *      save()  保存画布的状态。
 *      restore() 回滚到上一次保存的状态。
 *      translate() 相对于当前位置的位移。
 *      rotate() 旋转
 *
 *
 *
 * Created by MH on 2016/7/21.
 */
public class View2 extends View {


    private Paint mPaint;

    public View2(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // 画出一个点
        canvas.drawPoint(200,200,mPaint);

        //绘制一组点，坐标位置由float数组指定
        canvas.drawPoints(new float[]{
                500,500,
                500,600,
                500,700
        },mPaint);


        // 绘制直线。
        canvas.drawLine(300,300,500,600,mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                100,200,200,200,
                100,300,200,300
        },mPaint);

        // 绘制矩形   Rect RectF  两者的精度不同，整型和浮点型。
        // 第一种
        canvas.drawRect(100,100,800,400,mPaint);

        // 第二种
        Rect rect = new Rect(100,100,800,400);
        canvas.drawRect(rect,mPaint);

        // 第三种
        RectF rectF = new RectF(100,100,800,400);
        canvas.drawRect(rectF,mPaint);
    }
}
