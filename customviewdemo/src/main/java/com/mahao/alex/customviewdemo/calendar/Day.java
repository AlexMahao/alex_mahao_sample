package com.mahao.alex.customviewdemo.calendar;

/**
 *
 *  日期的封装类
 * Created by alex_mahao on 2016/9/10.
 */
public class Day {

    public static final String[]  sWeekTitle = {"日","一","二","三","四","五","六"};


    /**
     * 当前天数，包含值： “” ，“1”。“日”等
     */
    private String value;


    /**
     * 是否被点击
     */
    private boolean isClick;


    /**
     * 当前是否被选中状态
     */
    private boolean isSelect;


    /**
     * 是否是今天
     */
    private boolean isToday;

    /**
     * 还款计划次数
     */
    private int record;

    public Day() {
    }

    public Day(String value, boolean isClick) {
        this.value = value;
        this.isClick = isClick;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
}
