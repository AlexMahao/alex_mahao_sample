package com.mahao.alex.customviewdemo.taobao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mahao.alex.customviewdemo.R;

/**
 * Created by alex_mahao on 2016/8/29.
 */
public class DetailVerticalActivity extends AppCompatActivity {

    private DetailVerticalView v;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao_detail);
        // 查找控件
        v = (DetailVerticalView) findViewById(R.id.detailVerticalView);
        // 设置滚动监听的回调
        v.setScrollChangeListener(new DetailVerticalView.ScrollChangeListener() {

            @Override
            public void scrollY(int y) {
                // y轴滑动偏移量的回调
            }

            @Override
            public void onScollStateChange(int type) {
                // 滑动到那个页面状体的变化
                if(DetailVerticalView.TOP == type){
                    Toast.makeText(DetailVerticalActivity.this, "1", Toast.LENGTH_SHORT).show();
                }else if(DetailVerticalView.BOTTOM==type){
                    Toast.makeText(DetailVerticalActivity.this, "2", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void test(View view){
        Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_SHORT).show();
    }
}
