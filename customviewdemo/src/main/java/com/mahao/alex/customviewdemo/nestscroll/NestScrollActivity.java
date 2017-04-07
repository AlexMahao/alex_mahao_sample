package com.mahao.alex.customviewdemo.nestscroll;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahao.alex.customviewdemo.R;

/**
 * Created by mahao on 17-3-29.
 */

public class NestScrollActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scroll);
        mRecycleView = ((RecyclerView) findViewById(R.id.recycle));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(NestScrollActivity.this);
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
            textView.setLayoutParams(params);
            return new ViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.GRAY);
            }else{
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
            ((TextView) holder.itemView).setText(position + "");
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
