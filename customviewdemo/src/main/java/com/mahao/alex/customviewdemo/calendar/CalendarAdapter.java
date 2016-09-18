package com.mahao.alex.customviewdemo.calendar;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahao.alex.customviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  日历显示的Adapter
 * Created by alex_mahao on 2016/9/10.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<Day> mDayList = new ArrayList<>();


    /**
     * 保存被选中的记录
     */
    private int selectNum = -1;

    public CalendarAdapter(List<Day> day) {
        this.mDayList = day;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_calendar_day, parent, false);


        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, final int position) {
        // 绑定数据
        final Day day = mDayList.get(position);

        // 设置值
        holder.mDayView.setText(day.getValue());

        // 设置
        if (day.isSelect()) {
            holder.mDayRL.setBackgroundResource(R.drawable.shape_calendar_select);
        } else {
            holder.mDayRL.setBackgroundColor(Color.TRANSPARENT);

        }

        if (day.isToday()) {
            holder.mDayView.setBackgroundResource(R.drawable.shape_calendar_today);
            holder.mDayView.setTextColor(Color.WHITE);
        } else {
            holder.mDayView.setBackgroundColor(Color.TRANSPARENT);
            holder.mDayView.setTextColor(Color.parseColor("#555555"));
        }

        if (day.getRecord() <= 0) {
            holder.mRecordView.setVisibility(View.INVISIBLE);
        } else {
            holder.mRecordView.setVisibility(View.VISIBLE);
            holder.mRecordView.setText(day.getRecord() + "");
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(day.isClick()){
                    // 可以点击，且点击
                    if(position==selectNum){
                        return;
                    }
                    mDayList.get(position).setSelect(true);
                    if(selectNum>0&&selectNum<mDayList.size()){

                        mDayList.get(selectNum).setSelect(false);
                    }
                    selectNum = position;
                    notifyDataSetChanged();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mDayList.size();
    }




    /**
     * 日期Holder类
     */
    public class CalendarViewHolder extends RecyclerView.ViewHolder {

        public TextView mDayView;

        public TextView mRecordView;

        public RelativeLayout mDayRL;

        public CalendarViewHolder(View itemView) {
            super(itemView);

            mDayView = (TextView) itemView.findViewById(R.id.widget_calendar_day_tv);

            mRecordView = (TextView) itemView.findViewById(R.id.widget_calendar_record_tv);

            mDayRL = (RelativeLayout) itemView.findViewById(R.id.widget_calendar_day_rl);
        }
    }



}
