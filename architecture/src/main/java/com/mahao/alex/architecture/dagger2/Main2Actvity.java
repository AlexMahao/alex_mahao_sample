package com.mahao.alex.architecture.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by MH on 2016/7/15.
 */
public class Main2Actvity extends AppCompatActivity {

    @Inject
    Person person;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 构造桥梁对象
        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();

        //注入
        component.inject(this);

        Log.i("dagger","person = "+ person.toString());
    }
}
