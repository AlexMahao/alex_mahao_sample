package com.mahao.alex.systemwidgetdemo.recycleView.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * 实现上拉加载的监听：加载条件：滑动到最后，且是停止状态，则开始加载数据
 * 需要判断两个，1，当前是否是向下滑动
 * Created by Alex_MaHao on 2016/5/10.
 */
public  class LoadDataScrollController extends RecyclerView.OnScrollListener implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * 当前布局管理器的类型
     */
    private LayoutManagerType mLayoutManagerType;


    /**
     * 当前RecycleView显示的最大条目
     */
    private int mLastVisibleItemPosition;


    /**
     * 每列的最后一个条目
     */
    private int[] mLastPostions;


    /**
     * 是否正在加载数据  包括刷新和向上加载更多
     */
    private boolean isLoadData = false;


    /**
     * 回调接口
     */
    private OnRecycleRefreshListener mListener;


    public LoadDataScrollController(OnRecycleRefreshListener onRecycleRefreshListener) {
        this.mListener = onRecycleRefreshListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        //如果为null，第一次运行，确定布局惯例其
        if (mLayoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT;
            } else if (layoutManager instanceof GridLayoutManager) {
                mLayoutManagerType = LayoutManagerType.GRID_LAYOUT;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                mLayoutManagerType = LayoutManagerType.STAGGERED_GRID_LAYOUT;
            } else {
                throw new RuntimeException("LayoutManager should be LinearLayoutManager,GridLayoutManager,StaggeredGridLayoutManager");
            }
        }

        switch (mLayoutManagerType) {
            case LINEAR_LAYOUT:
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID_LAYOUT:
                mLastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID_LAYOUT:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (mLastPostions == null) {
                    mLastPostions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(mLastPostions);
                mLastVisibleItemPosition = findMax(mLastPostions);
                break;
            default:
                break;
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        //RecycleView 显示的条目数
        int visibleCount = layoutManager.getChildCount();

        //显示数据总数
        int totalCount = layoutManager.getItemCount();


        if(visibleCount>0
                &&newState==RecyclerView.SCROLL_STATE_IDLE
                &&mLastVisibleItemPosition>=totalCount-1
                &&!isLoadData){
            //可以加载数据
          if(mListener!=null){
              isLoadData = true;
              mListener.loadMore();
          }
        }

    }


    /**
     * 当是瀑布流时，获取到的是每一个瀑布最下方显示的条目，通过条目进行对比
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


    public void setLoadDataStatus(boolean isLoadData){
        this.isLoadData = isLoadData;
    }

    @Override
    public void onRefresh() {
        //刷新数据的方法
        if(mListener!=null){
            isLoadData = true;
            mListener.refresh();
        }

    }


    interface OnRecycleRefreshListener{
        void refresh();
        void loadMore();
    }
}
