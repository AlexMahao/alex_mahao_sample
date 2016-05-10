package com.mahao.alex.systemwidgetdemo.recycleView.swipetoloadlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.mahao.alex.systemwidgetdemo.R;


/**
 * Created by 蔡小木 on 2016/3/6 0006.
 */
public class LoaderMoreView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public LoaderMoreView(Context context) {
        super(context);
    }

    public LoaderMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoaderMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoaderMoreView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLoadMore() {
        setText(R.string.common_view_loading);
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText(R.string.common_view_release);
            } else {
                setText(R.string.common_swipe_to_load_more);
            }
        } else {
            setText(R.string.common_view_loading_ok);
        }
    }

    @Override
    public void onRelease() {
        setText(R.string.common_load_more);
    }

    @Override
    public void onComplete() {
        setText(R.string.common_load_complete);
    }

    @Override
    public void onReset() {
        setText("");
    }
}
