package com.mahao.alex.systemwidgetdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.mahao.alex.systemwidgetdemo.R;

/**
 * 简单的对话框
 * Created by MH on 2016/6/15.
 */
public class SimpleDialog extends Dialog implements View.OnClickListener {


    public SimpleDialog(Context context) {

        // 注意，在此处设置样式
        super(context,R.style.CustomDialog);

        // 设置我们的布局到dialog中
        setContentView(R.layout.dialog_simple);

        // 初始化布局
        initView();
    }

    private void initView() {
        findViewById(R.id.dialog_simple_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.dialog_simple_cancel:
                // 对应的点击事件
                this.dismiss();
                break;
        }
    }
}
