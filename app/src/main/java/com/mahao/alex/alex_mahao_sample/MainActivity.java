package com.mahao.alex.alex_mahao_sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static List<String> imgPath ;

    static {
        imgPath = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void to(View view){
        imgPath.add("1");
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);


        Handler handler = new Handler();
        handler.sendEmptyMessage()
    }
}
