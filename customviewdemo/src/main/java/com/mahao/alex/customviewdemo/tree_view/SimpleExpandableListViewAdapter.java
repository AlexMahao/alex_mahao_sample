package com.mahao.alex.customviewdemo.tree_view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 外层ExpandListView 适配器的实现
 * Created by MH on 2016/6/16.
 */
public class SimpleExpandableListViewAdapter extends BaseExpandableListAdapter {


    // 大学的集合
    private List<College> colleges;

    private Activity activity;


    public SimpleExpandableListViewAdapter(List<College> colleges, Activity activity) {
        this.colleges = colleges;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return colleges.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 很关键，，一定要返回  1
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return colleges.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return colleges.get(groupPosition).classList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        return getGenericView(colleges.get(groupPosition).name);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        // 返回子ExpandableListView 的对象  此时传入是该父条目，即大学的对象（有歧义。。）

        return getGenericExpandableListView(colleges.get(groupPosition));
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private TextView getGenericView(String string) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(activity);
        textView.setLayoutParams(layoutParams);

        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        textView.setPadding(40, 20, 0, 20);
        textView.setText(string);
        textView.setTextColor(Color.RED);
        return textView;
    }


    /**
     *  返回子ExpandableListView 的对象  此时传入的是该大学下所有班级的集合。
     * @param college
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ExpandableListView getGenericExpandableListView(College college){
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        CustomExpandableListView view = new CustomExpandableListView(activity);

        // 加载班级的适配器
        ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(college.classList,activity);

        view.setAdapter(adapter);

        view.setPadding(20,0,0,0);
        return view;
    }
}
