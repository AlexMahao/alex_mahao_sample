package com.mahao.alex.customviewdemo.path;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.mahao.alex.customviewdemo.R;

/**
 * Created by MH on 2016/7/21.
 */
public class PathMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private Bezier3 bezier3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        ((RadioGroup) findViewById(R.id.rg)).setOnCheckedChangeListener(this);

        bezier3 = ((Bezier3) findViewById(R.id.bezier));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for(int i = 0; i<group.getChildCount();i++){
            if(group.getChildAt(i).getId()==checkedId){
                if(i == 0){
                    bezier3.setMode(true);
                }else{
                    bezier3.setMode(false);
                }
            }
        }
    }
}
