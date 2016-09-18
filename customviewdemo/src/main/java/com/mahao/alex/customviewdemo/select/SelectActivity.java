package com.mahao.alex.customviewdemo.select;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mahao.alex.customviewdemo.R;

/**
 * Created by alex_mahao on 2016/9/9.
 */
public class SelectActivity extends AppCompatActivity {

    private RelativeLayout rl;
    private EditText mEt;
    private SelectView sv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        rl = ((RelativeLayout) findViewById(R.id.rl));

        mEt = ((EditText) findViewById(R.id.et));

        sv = ((SelectView) findViewById(R.id.sv));

        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 事件分发
                return sv.dispatchTouchEvent(event);
            }
        });

        sv.setOnSelectListener(new SelectView.OnSelectListener() {
            @Override
            public void onSelect(String value) {
                mEt.setText(value);
            }
        });



    }
}
