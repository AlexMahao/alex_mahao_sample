package com.mahao.alex.animationdemo.property_anim;

import android.widget.Button;

/**
 * Button按钮的包装类，实现倒计时
 * Created by Alex_MaHao on 2016/5/13.
 */
public class TimerBtnWrapper {

    private Button btn;

    public TimerBtnWrapper(Button btn) {
        this.btn = btn;
    }


    /**
     * 关键方法，动画调用此方法，修改属性值
     * @param time
     */
    public void setTimer(int time){

        btn.setText(time+"s");
    }


    /**
     * 设值动画结束后的值
     * @param text
     */
    public void setTimeEndText(String text){
        btn.setText(text);
    }

    /**
     * 设置按钮是否可以点击
     * @param isClick
     */
    public void setIsClick(boolean isClick){
        btn.setClickable(isClick);
    }
}
