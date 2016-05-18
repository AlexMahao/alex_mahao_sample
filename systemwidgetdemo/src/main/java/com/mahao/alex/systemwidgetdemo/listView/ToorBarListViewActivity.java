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

    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;

    private boolean isShow = true;

    private Animator mAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_toobar);

        mToolBar = ((Toolbar) findViewById(R.id.toolbar));

        setSupportActionBar(mToolBar);

        mLv = ((ListView) findViewById(R.id.listview_toolbar_lv));

        initDatas();

        mAdapter = new SimpleBaseAdapter(datas);

        mLv.setAdapter(mAdapter);

       addHeadView();


        //最小移动距离
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();


        mLv.setOnTouchListener(this);
    }

    /**
     * 添加头布局，避免被遮挡
     */
    private void addHeadView() {
        View head = new View(this);

        TypedArray actionbarSizeTypedArray = getApplicationContext().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });

        head.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) actionbarSizeTypedArray.getDimension(0, 0)));

        mLv.addHeaderView(head);
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
                    //向上滑动，隐藏toolbar
                    if(isShow){
                        toolbarAnim(false);
                        isShow = !isShow;
                    }
                }else if(direction==0){
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
            mAnimator = ObjectAnimator.ofFloat(mToolBar,"translationY",mToolBar.getTranslationY(),0);
        }else{
            mAnimator = ObjectAnimator.ofFloat(mToolBar,"translationY",mToolBar.getTranslationY(),-mToolBar.getHeight());
        }
        mAnimator.start();
    }
}
