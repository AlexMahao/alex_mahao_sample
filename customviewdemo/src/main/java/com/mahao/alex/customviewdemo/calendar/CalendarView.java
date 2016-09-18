package com.mahao.alex.customviewdemo.calendar;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mahao.alex.customviewdemo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 *  日历控件
 * Created by alex_mahao on 2016/9/12.
 */
public class CalendarView extends LinearLayout {


    /**
     * 日历对象
     */
    private List<Day> mDayList = new ArrayList<>();


    /**
     *  需要显示的年份列表
     */
    private List<String> mYears = new ArrayList<>();

    /**
     * 年份
     */
    private int mYear;

    /**
     *  月份  从0~11
     */
    private int mMonth;

    /**
     *  天
     */
    private int mDay;


    /**
     * 当前显示的年份
     */
    private int mCurrentYear;


    /**
     * 当前显示的月份
     */
    private int mCurrentMonth;



    /**
     * 显示年份的Spinner 对象
     */
    private Spinner mSpinner;

    /**
     * 显示月份的Tab
     */
    private TabLayout mTab;

    /**
     * 显示日期选择的RecycleView
     */
    private RecyclerView mCalendarRecleView;


    /**
     * 日历选择的Adapter
     */
    private CalendarAdapter mAdapter;


    public CalendarView(Context context) {
        this(context,null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.widget_calendar,this);

        init();
    }

    /**
     * 初始化方法
     */
    private void init() {

        initDate();

        initRecycleView();

        initSpinner();

        initTabLayout();


    }

    /**
     * 初始化日期
     */
    private void initDate() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        // 年份
        mYear = calendar.get(Calendar.YEAR);
        // 月份
        mMonth = calendar.get(Calendar.MONTH);

        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        mCurrentYear = mYear;

        mCurrentMonth = mMonth;
    }


    private void initRecycleView() {

        mCalendarRecleView = ((RecyclerView) findViewById(R.id.calendar_rv));

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 7);

        mCalendarRecleView.setLayoutManager(layoutManager);

        mCalendarRecleView.addItemDecoration(new CalendarItemDecoration());

        mAdapter = new CalendarAdapter(mDayList);

        mCalendarRecleView.setAdapter(mAdapter);

    }

    private void initTabLayout() {
        mTab = ((TabLayout) findViewById(R.id.calendar_tab_layout));

        for(int i=1;i<=12;i++){
            mTab.addTab(mTab.newTab().setText(i+"月"));
            mTab.getTabAt(i-1).setCustomView(getTabLayout());
        }


        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        mTab.getTabAt(mCurrentMonth).select();
                    }
                }, 100);


        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 某个tab 被选中



                mCurrentMonth= tab.getPosition();

                //刷新视图
                newDayList(mCurrentYear,mCurrentMonth);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    /**
     * 根据年份和月份生成新的数据
     * @param year
     * @param month
     */
    public void newDayList(int year,int month){

        mDayList.clear();

        Calendar calendar = Calendar.getInstance();

        calendar.set(year,month,1);

        // 一周的第几天  从周日开始算， 周日是1   周六是7
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 前面有多少空白天
        int blackDay =  dayOfWeek-1;

        // 这个月公有多少天
        int dayMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        addTitle();

        addBlackDay(blackDay);

        addDay(dayMax,getToday(year,month));

        // 刷新数据
        mAdapter.notifyDataSetChanged();


    }


    /**
     * 是否是当前时间的月份和年份，如果是，返回天数的索引
     * @param mCurrentYear
     * @param mCurrentMonth
     * @return
     */
    public int getToday(int mCurrentYear,int mCurrentMonth){

        if(mYear==mCurrentYear&&mCurrentYear==mMonth){
            return mDay ;
        }
        return -1;
    }

    /**
     * 添加日历
     *
     * @param maxDay
     * @param today
     */
    private void addDay(int maxDay, int today) {
        for (int i = 1; i <= maxDay; i++) {
            Day day = new Day(i + "", true);
            if (i == today) {
                day.setToday(true);
            }
            mDayList.add(day);
        }
        mDayList.get(31).setRecord(2);
    }

    /**
     * 添加空白的天
     *
     * @param num
     */
    private void addBlackDay(int num) {
        for (int i = 0; i < num; i++) {
            Day day = new Day("", false);
            mDayList.add(day);
        }
    }

    /**
     * 添加标题
     */
    private void addTitle() {
        for (int i = 0; i < 7; i++) {
            Day day = new Day(Day.sWeekTitle[i], false);
            mDayList.add(day);
        }
    }

    /**
     *  初始化年份选择
     */
    private void initSpinner() {
        mYears.clear();

        for(int i = -1;i<3;i++){
            mYears.add(mYear+i+"");
        }

        mSpinner = ((Spinner) findViewById(R.id.calendar_spinner));

        CalendarSpinnerAdapter adapter = new CalendarSpinnerAdapter();

        mSpinner.setAdapter(adapter);

        mSpinner.setSelection(1);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCurrentYear = Integer.parseInt(mYears.get(position));

                // 刷新适配器
                newDayList(mCurrentYear,mCurrentMonth);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    /**
     *  获取Tab的自定义布局
     * @return
     */
    public View getTabLayout(){
        return LayoutInflater.from(getContext()).inflate(R.layout.widget_calendar_tab,null);
    }



    /**
     * 年份显示的adapter
     */
    class CalendarSpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mYears.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_calendar_spinner_item, null);

            ((TextView) view.findViewById(R.id.text)).setText(mYears.get(position));

            return view;
        }
    }
}
