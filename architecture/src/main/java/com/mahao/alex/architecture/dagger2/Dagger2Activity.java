package com.mahao.alex.architecture.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mahao.alex.architecture.R;

import javax.inject.Inject;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public class Dagger2Activity extends AppCompatActivity {


    private ActivityComponent mActivityComponent;

    @Inject
    User user;

    private TextView mshowTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();

        mActivityComponent.inject(this);


        mshowTv = ((TextView) findViewById(R.id.dagger2_show_user));

        mshowTv.setText(user.name);

    }
}
