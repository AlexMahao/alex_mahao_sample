package com.mahao.alex.animationdemo.custom_anim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahao.alex.animationdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView初始加载进入的动画
 * Created by Alex_MaHao on 2016/5/12.
 */
public class RecycleViewFadeInAnimActivity extends AppCompatActivity {


    private RecyclerView mRecycler;

    private MyAdapter mAdapter;

    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclew_fade_in);

        mRecycler = ((RecyclerView) findViewById(R.id.recycle_fade_in));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecycler.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter();

        mRecycler.setAdapter(mAdapter);

        for (int i = 0;i<20;i++){
            datas.add(i+"");
        }

        mAdapter.notifyDataSetChanged();



    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getApplicationContext());

            textView.setPadding(10, 40, 10, 40);
            textView.setBackgroundColor(Color.RED);
            textView.setTextColor(Color.WHITE);
            return new MyHolder(textView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            ((TextView) holder.itemView).setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            public MyHolder(View itemView) {
                super(itemView);
            }
        }
    }


}
