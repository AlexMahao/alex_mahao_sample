package com.mahao.alex.customviewdemo.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 滚动的适配器
 * Created by alex_mahao on 2016/8/25.
 */
public class CoverFlowAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    /**
     *  默认缩小的padding值
     */
    public static int sWidthPadding;

    public static int sHeightPadding;

    /**
     * 子元素的集合
     */
    private List<View> mViewList;

    /**
     * 滑动监听的回调接口
     */
    private OnPageSelectListener listener;

    /**
     * 上下文对象
     */
    private Context mContext;

    public CoverFlowAdapter(List<View> mImageViewList, Context context) {
        this.mViewList = mImageViewList;
        mContext = context;
        // 设置padding值
        sWidthPadding = dp2px(24);
        sHeightPadding = dp2px(32);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewList.get(position);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 该方法回调ViewPager 的滑动偏移量
        if (mViewList.size() > 0 && position < mViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            Log.i("info", "重新设置padding");
            int outHeightPadding = (int) (positionOffset * sHeightPadding);
            int outWidthPadding = (int) (positionOffset * sWidthPadding);
            // 从0滑动到一时，此时position = 0，其应该是缩小的，符合
            mViewList.get(position).setPadding(outWidthPadding, outHeightPadding, outWidthPadding, outHeightPadding);

            // position+1 为即将显示的页面，越来越大
            if (position < mViewList.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding);
                int inHeightPadding = (int) ((1 - positionOffset) * sHeightPadding);
                mViewList.get(position + 1).setPadding(inWidthPadding, inHeightPadding, inWidthPadding, inHeightPadding);
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        // 回调选择的接口
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 当将某一个作为最中央时的回调
     *
     * @param listener
     */
    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }


    /**
     * dp 转 px
     *
     * @param dp
     * @return
     */
    public int dp2px(int dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());

        return px;
    }

}
