package com.mahao.alex.architecture.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mahao.alex.architecture.R;

import javax.inject.Inject;

/**
 * Created by MH on 2016/7/14.
 */
public class MainActivity extends AppCompatActivity{

    @Inject
    Person person;

    @Inject
    Person person2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // 构造桥梁对象
        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();

        //注入
        component.inject(this);
    }


    public void go(View view){
        Intent intent = new Intent(this,Main2Actvity.class);

        startActivity(intent);
    }
}
