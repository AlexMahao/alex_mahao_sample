package com.mahao.alex.alex_mahao_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by alex_mahao on 2016/9/23.
 */
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("info",MainActivity.imgPath.toString());
    }


    @Override
    protected void onDestroy() {
        MainActivity.imgPath.clear();
        super.onDestroy();

    }
}
