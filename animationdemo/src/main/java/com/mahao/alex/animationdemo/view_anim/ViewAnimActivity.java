package com.mahao.alex.animationdemo.view_anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.mahao.alex.animationdemo.R;

/**
 *
 * 视图动画
 *
 * AlphaAnimation, AnimationSet, RotateAnimation, ScaleAnimation, TranslateAnimation
 *  //试图动画的实现方式有两种，一种是java代码形式，另一种是xml形式
 *
 *
 *   //Interpolator
 *
 *
 * Created by Alex_MaHao on 2016/5/11.
 */
public class ViewAnimActivity  extends AppCompatActivity{

    private ImageView img;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anim);

        img = ((ImageView) findViewById(R.id.img));

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewAnimActivity.this, "img click", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void alpha(View view){
        AlphaAnimation anim= new AlphaAnimation(1,0);
        //RepeatCount ,动画执行结束后的重复次数，如果大于0，则重复次数。默认是0
        //如果小于0，默认是Animation.INFINITE
        anim.setRepeatCount(1); //重复0次
        anim.setRepeatMode(Animation.REVERSE);  //Animation.RESTART  从头开始。  Animation.REVERSE :反转
        anim.setDuration(1000);

        //透明度动画 xml
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.view_alpha);;

        img.startAnimation(anim);
    }

    public void rotate(View view){
        //float fromDegrees, float toDegrees,
        // int pivotXType, float pivotXValue,
        //int pivotYType, float pivotYValue
        // pivotXType  pivotYType  ==> Relative_to_self  ||Relative_to_parent //默认self
        // pivotYValue pivotXValue  ==>   0%~1% //默认0
        RotateAnimation anim = new RotateAnimation(0f,180f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true); //设置为true ，则保持动画结束后的状态，默认false
        img.startAnimation(anim);

        //xml  R.anim.view_rotate.xml

    }

    //缩放
    public void scale(View view){

        ScaleAnimation anim = new ScaleAnimation(1f,0.5f,1f,0.5f,0.5f,0.f);
        anim.setDuration(500);
        img.startAnimation(anim);

        //R.anim.view_scale

    }


    public void translate(View view){

        //RELATIVE_TO_SELF 已自身为参考，百分比位置
        //RElative_To_Parent  已父控件为参考
        TranslateAnimation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,1f,
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,1f);

        anim.setDuration(500);

        img.startAnimation(anim);

        //R.anim.view_translate


    }
    public void set(View view){
        //shareInterpolator  true 使用set的差值器 ，false ：使用自身的差值器
        AnimationSet set = new AnimationSet(true);


        ScaleAnimation anim = new ScaleAnimation(1f,0.5f,1f,0.5f,0.5f,0.f);
        anim.setDuration(500);

        RotateAnimation anim1 = new RotateAnimation(0f,180f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(500);

        set.addAnimation(anim);
        set.addAnimation(anim1);

        set.setDuration(500); //设置动画时间，不然动画执行时间有问题，和想象中的各自执行叠加不同

        img.startAnimation(set);

        //R.anim.view_set
    }

}
