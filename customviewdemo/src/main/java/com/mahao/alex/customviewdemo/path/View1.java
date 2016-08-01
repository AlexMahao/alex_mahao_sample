package com.mahao.alex.customviewdemo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 *  描述角度的大小：
 *      弧度：    当弧等于半径长度时，代表1 弧度。
 *      角度：  角度，当两者夹角的弧是圆周长的1/360时，代表1度
 *
 *      180 度= π 弧度
 *      弧度= 180 * xx度/π
 *
 *      因为坐标系的不同：
 *          在数学坐标系中：角度增大的方向为逆时针。
 *          在屏幕坐标系中，角度增大的方向为顺时针。
 *
 *    颜色模式：
 *
 *      ARGB8888 四通道高精度32位
 *      ARGB4444 四通道低精度24位
 *      RGB565  屏幕默认显示模式 屏幕默认显示模式 16位
 *      Alpha8 仅有透明通道8位
 *
 *
 *    测量模式 onMeasure()
 *          MeasureSpec.getSize(widthMeasureSpec); // 获得宽度数值的大小
 *          MeasureSpec.getMode(widthMeasureSpec); // 获得宽度的模式。
 *
 *          widthMeasureSpec 32位  4个字节的整形。
 *              前两位代表的是模式。 后面的代表具体的数值大小
 *
 *        模式值：
 *          UNSPECIFIED   00  默认值，父控件没有给子控件具体的限制。
 *          EXACTLY： 01 表示父控件已经确切的指定了子View 的大小。
 *          AT_MOST : 10 表示子View 的大小由自己确定，但范围需要保持在传入值的范围内。
 *
 *       设置具体的宽高值： setMeasuredDimension(widthSize,heightSize);
 *
 *
 *      super.onMeasure(widthMeasureSpec, heightMeasureSpec); 源码分析
 *          setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),...);
 *            同样调用setMeasuredDimension()方法。
 *          getDefaultSize()   根据模式判断，如果是UNSPECIFIED，使用默认值。
 *                                   AT_MOST,EXACTLY: 传入的具体数值。
 *
 *  onSizeChanged();  大小改变时回调此方法. 一般在此中获取到具体View 的宽高。为什么？
 *      这是因为View的大小不仅由View本身控制，而且受父控件的影响，
 *      所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数。
 *
 *      onSizeChanged(int w, int h, int oldw, int oldh)：
 *          现在的宽度，现在的高度，上一次的宽度，上一次的高度。
 *
 *  onLayout()。 确定子View 的布局。该方法只在ViewGroup中需要使用
 *      child.layout(l, t, r, b);
 *          l: 子View具当前View 的左侧的距离。 类似子View 中的getLeft();
 *          t: 距顶部的高度。getTop();
 *          r：距右边的距离。 getRight();
 *          b: 距底部的距离。 getBottom();
 *
 *
 *
 *
 *
 *
 * Created by MH on 2016/7/21.
 */
public class View1 extends View {

    private int mHeight;

    private int mWidth;

    public View1(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getTop();// 获取子View 左上角具父View 顶部的距离
        getLeft();// 获取子View 左上角具父View左侧的距离
        getBottom();// 获取子View 右下角具父View 底部的距离
        getRight(); // 获取子View 右下角具父View 右侧的距离。


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        event.getX();// 触摸点相对于当前组件的X轴的坐标
        event.getY(); // 触摸点相对于当前组件的Y轴坐标

        event.getRawX(); //触摸点相对于屏幕默认坐标的X值。
        event.getRawY(); // 触摸点相对于屏幕默认坐标的Y值。




        return super.onTouchEvent(event);
    }
}
