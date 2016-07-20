package com.mahao.alex.animationdemo.svg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahao.alex.animationdemo.R;

/**
 *
 * 搜索动画的实现
 * Created by MH on 2016/7/19.
 */
public class SVGMain2Activity extends AppCompatActivity {

    private ImageView iv;
    private TextView text;
    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg2);
        
        iv = (ImageView) findViewById(R.id.search);
        text = (TextView) findViewById(R.id.text);
        // 加载SVG
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);

        // 设置监听
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageDrawable(searchToBar);
                searchToBar.start();
            }
        });
    }

    public void lost_focus(View view) {
        // 点击文本框之外时，隐藏下划线
        iv.setImageDrawable(barToSearch);
        barToSearch.start();
    }

}
