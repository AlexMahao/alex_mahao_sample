package com.mahao.alex.systemwidgetdemo.recycleView.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.ArrayList;

/**
 * Created by alex_mahao on 2016/9/26.
 */
public class PersonActivity extends AppCompatActivity {


    private RecyclerView mRecycler;

    private ArrayList<Person> mPersons;

    private PersonAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mRecycler = ((RecyclerView) findViewById(R.id.reycler));


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        mRecycler.setLayoutManager(manager);


        mPersons = new ArrayList<>();

        for(int i = 0 ;i <50;i++){

            mPersons.add(new Person(i+""));
        }

        mAdapter = new PersonAdapter(mPersons);


        mRecycler.setAdapter(mAdapter);

        // 设置头部布局
        mAdapter.setHeaderView(createView(Color.BLUE,"HEADER"));

        // 设置底部布局
        mAdapter.setFooterView(createView(Color.RED,"FOOTER"));

    }


    /**
     * 创建一个简单的布局
     * @param color
     * @param str
     * @return
     */
    public View createView(int color,String str){
        TextView text = new TextView(this);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        text.setLayoutParams(params);
        text.setBackgroundColor(color);
        text.setText(str);
        text.setGravity(Gravity.CENTER);
        return text;
    }
}
