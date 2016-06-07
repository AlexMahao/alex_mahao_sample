package com.mahao.alex.systemwidgetdemo.listView;

import android.support.v7.app.AppCompatActivity;

/**
 *
 *
 * ListView使用总结
 * Created by Alex_MaHao on 2016/5/17.
 */
public class ListViewActivity extends AppCompatActivity {

    /*private MyListView mLv;

    private List<String> datas = new ArrayList<>();

    private SimpleBaseAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        mLv = ((MyListView) findViewById(R.id.listview_lv));

        initDatas();

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

        //设置空视图,调用此方法会默认隐藏Empty_view
        mLv.setEmptyView(findViewById(R.id.listview_empty_view));

        //该方法无法获取相应位置的变量
        //Log.i("info",mLv.getChildCount()+"");

    }

    private void initDatas() {
        for(int i = 0;i<20;i++){
            datas.add("添加数据~~"+i);
        }
    }

    *//**
     * 瞬间滚动
     * @param view
     *//*
    public void selection(View view){
        mLv.setSelection(8);
    }

    *//**
     * 平滑滚动
     * @param vieww
     *//*
    public void smooth(View vieww){
        mLv.smoothScrollToPosition(1);
    }

    *//**
     *
     * @param view
     *//*
    public void clear(View view){
        datas.clear();
        mAdapter.notifyDataSetChanged();
    }

    *//**
     *
     * @param view
     *//*
    public void add(View view){
        initDatas();
        mAdapter.notifyDataSetChanged();
    }*/

}
