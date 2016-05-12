package com.mahao.alex.animationdemo.custom_anim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mahao.alex.animationdemo.R;

import java.util.ArrayList;

/**
 *  // 动画偏移量  http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0619/3090.html
 *
 *  LayoutAnimationController 布局初始化操作时的动画 LayoutTranslation,
 * ListView 等ViewGroup进入时的动画效果
 * Created by Alex_MaHao on 2016/5/11.
 */
public class ListViewFadeInAnimActivity extends AppCompatActivity {

    private ListView mListView;

    private ArrayList<String> datas;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_fade_in);

        mListView = ((ListView) findViewById(R.id.list));



        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.listview_item_anim);   //得到一个LayoutAnimationController对象；
        LayoutAnimationController controller = new LayoutAnimationController(animation);   //设置控件显示的顺序；
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);   //设置控件显示间隔时间；
        controller.setDelay(0.3f);   //为ListView设置LayoutAnimationController属性；
        mListView.setLayoutAnimation(controller);


       // mListView.setLayoutTransition(new LayoutTransition());

        datas = new ArrayList();

     /*   for (int i = 0;i<3;i++){
            datas.add(i+"");
        }*/
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
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

                TextView textView;
                if(convertView==null){
                    textView = new TextView(getApplicationContext());
                }else{
                    textView = ((TextView) convertView);
                }

                textView.setText(datas.get(position));
                textView.setPadding(10,40,10,40);
                textView.setBackgroundColor(Color.RED);
                textView.setTextColor(Color.WHITE);

                return textView;
            }


            public void onenotify(){

            }
        };
        mListView.setAdapter(adapter);

    }


    public void refresh(View view){
        datas.clear();
        for (int i = 0;i<3;i++){
            datas.add(i+"");
        }

        adapter.notifyDataSetChanged();
        mListView.startLayoutAnimation();
    }


    public void add(View view){
        for (int i = 0;i<20;i++){
            datas.add(i+"");
        }

        adapter.notifyDataSetChanged();


    }


}
