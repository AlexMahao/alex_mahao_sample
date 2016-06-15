package com.mahao.alex.systemwidgetdemo.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.mahao.alex.systemwidgetdemo.R;

/**
 * Created by MH on 2016/6/15.
 */
public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog_custom);
    }

    public void dialog_custom(View view) {
        SimpleDialog dialog = new SimpleDialog(this);
        dialog.show();
    }


    /**
     * dialog设置动画
     */
    public void dialog_anim(View view) {

        SimpleDialog dialog = new SimpleDialog(this);
        // 设置动画
        dialog.getWindow().setWindowAnimations(R.style.DialogAnim);

        dialog.show();
    }


    /**
     * 设置大小
     * @param view
     */
    public void dialog_size(View view) {

        SimpleDialog dialog = new SimpleDialog(this);

//        // 第一种方式
//        dialog.getWindow().setLayout(100,100);


        // 第二种方式  获取参数
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

        // 设置高度
        params.height = 100;

        // 设置宽度
        params.width = 100;

        // 设置
        dialog.getWindow().setAttributes(params);


        dialog.show();
    }
}
