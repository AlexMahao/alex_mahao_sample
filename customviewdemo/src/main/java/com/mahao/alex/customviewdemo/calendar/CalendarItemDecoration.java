package com.mahao.alex.customviewdemo.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;

/**
 *
 *
 *
 * Created by alex_mahao on 2016/9/10.
 */
public class CalendarItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);


        int height = parent.getChildAt(0).getMeasuredHeight();

        int width = parent.getMeasuredWidth();

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#d3d3d3"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

        c.drawLine(0,height,width,height,paint);
    }
}
