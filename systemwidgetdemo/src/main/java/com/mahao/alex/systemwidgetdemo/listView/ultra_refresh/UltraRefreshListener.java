package com.mahao.alex.systemwidgetdemo.listView.ultra_refresh;

/**
 * 数据接口的回调
 * Created by Alex_MaHao on 2016/5/18.
 */
public interface UltraRefreshListener {

    //下拉刷新
    void onRefresh();

    //上拉加载
    void addMore();
}
