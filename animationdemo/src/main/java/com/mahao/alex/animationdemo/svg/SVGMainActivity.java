package com.mahao.alex.animationdemo.svg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mahao.alex.animationdemo.R;

/**
 * 登录特效的实现
 * Created by MH on 2016/7/19.
 */
public class SVGMainActivity extends AppCompatActivity implements View.OnFocusChangeListener, TextWatcher {


    private ImageView img1;

    private EditText edit1, edit2;

    // 显示
    private AnimatedVectorDrawable anim1, anim2, anim3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);


        img1 = ((ImageView) findViewById(R.id.img1));
        edit1 = ((EditText) findViewById(R.id.edit1));
        edit2 = ((EditText) findViewById(R.id.edit2));

        // 加载SVG
        anim1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim1);
        anim2 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim2);
        anim3 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3);

        // 设置焦点变化的监听
        edit1.setOnFocusChangeListener(this);
        edit2.setOnFocusChangeListener(this);

        // 文本变化的监听
        edit1.addTextChangedListener(this);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit1:
                if (hasFocus) {
                    img1.setImageDrawable(anim1);
                    anim1.start();
                }
                break;
            case R.id.edit2:
                if (hasFocus) {
                    img1.setImageDrawable(anim3);
                    anim3.start();
                }
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        // 内容变化之后，判断内容
        if (!TextUtils.isEmpty(s) && edit1.getText().toString().trim().equals("1234")) {
            Toast.makeText(SVGMainActivity.this, "right", Toast.LENGTH_SHORT).show();
            img1.setImageDrawable(anim2);
            anim2.start();
        }


    }
}
