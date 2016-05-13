package com.mahao.alex.animationdemo.property_anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.mahao.alex.animationdemo.R;

/**
 * Animator
 * <p/>
 * --AnimatorSet
 * --ValueAnimator
 * --ObjectAnimator
 * --TimeAnimator
 * <p/>
 *  //实现一个计时器
 * Created by Alex_MaHao on 2016/5/11.
 */
public class PropertyAnimActivity extends AppCompatActivity {

    private ImageView img;
    private Button mTimerBtn;

    private ObjectAnimator mTimeAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);

        img = ((ImageView) findViewById(R.id.property_img));


        mTimerBtn = ((Button) findViewById(R.id.timer));

        createTimeAnim();
    }

    private void createTimeAnim() {
        mTimeAnim = ObjectAnimator.ofInt(new TimerBtnWrapper(mTimerBtn),"timer",60,0).setDuration(60*1000);

        //修改插值器，即数值改变速率平均
        mTimeAnim.setInterpolator(new LinearInterpolator());

        //设置动画监听
        mTimeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束，设置按钮可以点击，同时设置重新获取验证码

                //获取动画的目标控件
                TimerBtnWrapper target = (TimerBtnWrapper) ((ObjectAnimator) animation).getTarget();

                target.setIsClick(true);
                target.setTimeEndText("重新获取");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始时，设置按钮不可点击

                //获取动画的目标控件
                TimerBtnWrapper target = (TimerBtnWrapper) ((ObjectAnimator) animation).getTarget();

                target.setIsClick(false);
            }
        });
    }


    /**
     * 不设置属性值
     *
     * @param view
     */
    public void valueAnim(View view) {

        //代码生成ValueAnimator
        ValueAnimator anim = ValueAnimator.ofFloat(1f, 0f, 1f)
                .setDuration(1000);

//      xml 文件创建动画
//      ValueAnimator anim = (ValueAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.property_value);

        anim.setTarget(img);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //根据动画的过渡产生数量值,通过getAnimatedValue获取变化的值
                float offset = (float) animation.getAnimatedValue();
                //设置透明度
                img.setAlpha(offset);
                //设置缩放
                img.setScaleX(offset);
                img.setScaleY(offset);
            }
        });

        anim.start();

    }


    public void objectAnim(View view) {

        //第一种代码方式实现动画
        ObjectAnimator anim = ObjectAnimator.ofFloat(img, "y", 0f, 400f)
                .setDuration(1000);

        anim.start();

        //x 相对于父控件

        //translationX 像素值



        //第二种方式实现简单的动画
//        img.animate().rotation(360f).setDuration(1000).start();


        //xml 文件创建属性动画

      /*  ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.property_object);

        anim.setTarget(img);

        anim.start();*/

    }


    public void animatorSet(View view) {

        //============使用代码实现AnimatorSet ==============
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(img, "rotation", 0f, 360f)
                .setDuration(1000);

      /*  ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(img, "scale", 1, 0)
                .setDuration(1000);

        scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float offset = (float) animation.getAnimatedValue();
                img.setScaleY(offset);
                img.setScaleX(offset);
            }
        });*/

        // 使用PropertyValuesHolder实现上面所实现的缩放动画
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX",1,0);

        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY",1,0);

        ObjectAnimator scaleAnim = ObjectAnimator.ofPropertyValuesHolder(img, pvhX, pvhY);
        scaleAnim.setDuration(1000);


        AnimatorSet animSet = new AnimatorSet();

        //两个动画同时执行
        //animSet.play(rotateAnim).with(scaleAnim);

        //先缩放在旋转
//      animSet.play(rotateAnim).after(scaleAnim);

        //先旋转 再缩放
        animSet.play(rotateAnim).before(scaleAnim);
        animSet.start();


        //===========================使用xml 实现set集合===========================

        Animator anim = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.property_set);

        anim.setTarget(img);

        anim.start();

    }


    public void startTime(View view){
        mTimeAnim.start();
    }
}
