package com.mahao.alex.customviewdemo.tree_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.mahao.alex.customviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MH on 2016/6/16.
 */
public class SimpleExpandListViewActivity extends AppCompatActivity {


    private ExpandableListView listview;

    private List<College> colleges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tree_view_simple);

        initData();

        // 查找控件
        listview = ((ExpandableListView) findViewById(R.id.tree_view_simple));


        SimpleExpandableListViewAdapter adapter = new SimpleExpandableListViewAdapter(colleges,this);

        // 设置适配器
        listview.setAdapter(adapter);
/*
        // 初始化数据
        List<Classes> classesList = new ArrayList<>();

        for(int i = 1 ;i<3;i++) {
            Classes classes = new Classes();

            classes.name = "计算机"+i+"班";

            List<String> list = new ArrayList<>();

            list.add("mm");
            list.add("dd");
            classes.students = list;

            classesList.add(classes);
        }*/

        // 构造适配器
       // ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(classesList,this);





    }

    /**
     * 初始化数据
     */
    private void initData() {


        College college = new College();

        college.name = "科技大学";

        List<Classes> classesList = new ArrayList<>();

        for(int i = 1 ;i<3;i++) {
            Classes classes = new Classes();

            classes.name = "计算机"+i+"班";

            List<String> list = new ArrayList<>();

            list.add("mm");
            list.add("dd");
            classes.students = list;

            classesList.add(classes);
        }

        college.classList = classesList;


        colleges = new ArrayList<>();
        colleges.add(college);
    }
}
