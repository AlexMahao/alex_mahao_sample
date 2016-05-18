package com.mahao.alex.systemwidgetdemo.ultra_refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.ListView;

import com.mahao.alex.systemwidgetdemo.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Alex_MaHao on 2016/5/18.
 */
public class UltraRefreshListView extends ListView implements PtrHandler,AbsListView.OnScrollListener {

    private UltraRefreshListener mUltraRefreshListener;

    /**
     * 根布局
     */
    private View footView;


    private boolean isLoadData = false;

    /**
     * 是否是下拉刷新，主要在处理结果时使用
     */
    private boolean isRefresh = false;

    public UltraRefreshListView(Context context) {
        this(context,null);
    }

    public UltraRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UltraRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化布局，及添加一个跟布局
        initView();
    }

    private void initView() {
        footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_ultra_refresh_listview, null);

        setOnScrollListener(this);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return !isLoadData&&checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

        isLoadData  =true;
        isRefresh =true;
        //下拉刷新的回调
        if(mUltraRefreshListener!=null){

            mUltraRefreshListener.onRefresh();
        }
    }



    public static boolean checkContentCanBePulledDown(PtrFrameLayout frame, View content, View header) {
        return !canChildScrollUp(content);
    }

    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else {
            return view.canScrollVertically(-1);
        }
    }


    /**
     * 设置ListView的监听回调
     */
    public void setUltraRefreshListener(UltraRefreshListener mUltraRefreshListener) {
        this.mUltraRefreshListener = mUltraRefreshListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        /*Log.i("info","isLoadData:"+isLoadData+" totalItemCount "+totalItemCount+" firstVisibleItem "+
                firstVisibleItem+" visibleItemCount "+ visibleItemCount);
*/

        if(totalItemCount>1&&!isLoadData&&totalItemCount==firstVisibleItem+visibleItemCount){
            isRefresh =false;

            isLoadData = true;

            addFooterView(footView);
            mUltraRefreshListener.addMore();
        }
}



    public void refreshComplete(){
        isLoadData = false;
        if(isRefresh){

            ViewParent parent = getParent();
            if(parent instanceof PtrClassicFrameLayout){
                ((PtrClassicFrameLayout) parent).refreshComplete();
            }
        }else{
            removeFooterView(footView);
        }
    }
}
