package com.mahao.alex.animationdemo.layout_transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mahao.alex.animationdemo.R;

/**
 * 布局动画，适用于ViewGroup   LayoutTransition    不会影响初始化操作
 * --如果在ViewGroup的xml文件上添加：android:animateLayoutChanges="true" 则会有默认的动画效果
 * <p/>
 * <p/>
 * <p/>
 * Created by Alex_MaHao on 2016/5/11.
 */
public class LayoutTransitionActivity extends AppCompatActivity {


    private LinearLayout mParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout_transition);

        mParent = ((LinearLayout) findViewById(R.id.parent));


        LayoutTransition mTransition = new LayoutTransition();

        mParent.setLayoutTransition(mTransition);


        //设置动画过程

        /** 通过setAnimator设置动画过程
         * LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
         LayoutTransition.CHANGE_APPEARING 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响，对其他View设置的动画
         LayoutTransition.DISAPPEARING  当一个View在ViewGroup中消失时，对此View设置的动画
         LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
         */

        //view 进入
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(null, "rotationY", -90f, 0f)
                .setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        animator1.setInterpolator(new LinearInterpolator());
        mTransition.setAnimator(LayoutTransition.APPEARING, animator1);

        //view 退出
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("translationX", 0f, 1000f);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(new Object(), propertyValuesHolder, propertyValuesHolder1);
        animator2.setDuration(mTransition.getDuration(LayoutTransition.DISAPPEARING));

//       ObjectAnimator animator2 = ObjectAnimator.ofFloat(null,"translationX",0f,1000f).setDuration(1000);
        mTransition.setAnimator(LayoutTransition.DISAPPEARING, animator2);


        //控件进入对其他控件的影响，
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder pvhScrollX = PropertyValuesHolder.ofInt("scrollX", 0, 1);
        PropertyValuesHolder pvhScrollY = PropertyValuesHolder.ofInt("scrollY", 0, 1);
        PropertyValuesHolder pvhChangeAppearing = PropertyValuesHolder.ofFloat("scaleX", 1f, 2f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight, pvhBottom,
                pvhScrollX, pvhScrollY, pvhChangeAppearing);
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, animator3);

        //如果当前动画没有结束，如果有新的View加入，会导致当前动画取消，并进行下一次动画，所以需要添加监听
        animator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View target = (View) ((ObjectAnimator) animation).getTarget();
                target.setScaleX(1f);
            }
        });


        //控件消失对其他控件的影响，类似于上面的，偷懒了
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, animator3);



        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.listview_item_anim);   //得到一个LayoutAnimationController对象；
        LayoutAnimationController controller = new LayoutAnimationController(animation);   //设置控件显示的顺序；
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);   //设置控件显示间隔时间；
        controller.setDelay(0.3f);   //为ListView设置LayoutAnimationController属性；
        mParent.setLayoutAnimation(controller);

    }


    public void add(View view) {


        Button button = new Button(this);

        button.setText(mParent.getChildCount() + 1 + "");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        button.setLayoutParams(layoutParams);

        mParent.addView(button, 0);

    }


    public void remove(View view) {
        if (mParent.getChildCount() > 0) {
            mParent.removeViewAt(0);
        }
    }
}
