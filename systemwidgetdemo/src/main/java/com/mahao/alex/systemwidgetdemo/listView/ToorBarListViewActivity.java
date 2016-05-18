package com.mahao.alex.systemwidgetdemo.listView;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public class ToorBarListViewActivity extends AppCompatActivity implements View.OnTouchListener {

    private Toolbar mToolBar;

    private ListView mLv;

    private SimpleBaseAdapter mAdapter;

    private List<String> datas  = new ArrayList<>();

    //系统默认的滑动最小偏移量
    private int mTouchSlop;

    //手指初次触摸时的Y坐标
    private float mFirstY;

    //当前手指触摸的Y坐标
    private float mCurrentY;

    //手指移动的方向，0代表向下滑动，1代表向上滑动
    private int direction;

    //toobar的显示状态
    private boolean isShow = true;

    //toobar显示和隐藏的动画
    private Animator mAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_toobar);

        // 把toolbar替代ActionBar
        mToolBar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(mToolBar);

        mLv = ((ListView) findViewById(R.id.listview_toolbar_lv));
        initDatas();

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

        /**
         * 添加一个头部View,不然使用RelativeLayout，会被遮挡
         */
        addHeadView();


        //最小移动距离，判断是否滑动
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        //设置触摸监听
        mLv.setOnTouchListener(this);
    }

    /**
     * 添加头布局，避免被遮挡
     */
    private void addHeadView() {

        //创建一个与ToolBar等高的空白view，添加到ListView的头布局中
        View head = new View(this);
        TypedArray actionbarSizeTypedArray = getApplicationContext().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        head.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) actionbarSizeTypedArray.getDimension(0, 0)));

       // mLv.addHeaderView(head);
    }

    private void initDatas() {
        for(int i = 0;i<20;i++){
            datas.add("添加数据~~"+i);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //记录初次触摸的Y坐标
                mFirstY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentY = event.getY();
                if(mCurrentY-mFirstY>mTouchSlop){
                    //向下滑动
                    direction = 0;
                }else if(mFirstY-mCurrentY>mTouchSlop){
                    //向上滑动
                    direction = 1;
                }
                if(direction==1){
                    //向上滑动，且toolbar在显示状态，则隐藏
                    if(isShow){
                        toolbarAnim(false);
                        isShow = !isShow;
                    }
                }else if(direction==0){
                    //向下滑动，且toolbar在隐藏状态，则显示
                    if(!isShow){
                        toolbarAnim(true);
                        isShow = !isShow;
                    }
                }

                break;
        }


        return false;
    }


    public void toolbarAnim(boolean isShow){
        if(mAnimator!=null&&mAnimator.isRunning()){
            mAnimator.cancel();
        }

        if(isShow){
            //显现toobar
            mAnimator = ObjectAnimator.ofFloat(mToolBar,"translationY",mToolBar.getTranslationY(),0);
        }else{
            //隐藏toobar
            mAnimator = ObjectAnimator.ofFloat(mToolBar,"translationY",mToolBar.getTranslationY(),-mToolBar.getHeight());
        }
        //启动动画
        mAnimator.start();
    }
}
