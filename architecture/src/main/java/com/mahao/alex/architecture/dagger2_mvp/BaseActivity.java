package com.mahao.alex.architecture.dagger2_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        afterViewCreate();
    }

    protected abstract void afterViewCreate();

    public abstract int getLayoutId();
}
