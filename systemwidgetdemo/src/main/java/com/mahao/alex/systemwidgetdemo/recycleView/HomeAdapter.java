package com.mahao.alex.systemwidgetdemo.recycleView;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  Home的RecycleView的adapter
 * Created by Alex_MaHao on 2016/5/6.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {


    public static final int IS_IFRST = 1;

    private List<String> datas;


    public HomeAdapter() {
        this.datas = new ArrayList<>();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {

        holder.text.setText(datas.get(position));



        // 瀑布流实现时，修改itemView的高度
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = (int) (Math.random()*150+100);
        holder.itemView.setLayoutParams(layoutParams);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), ""+datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //加载动画
        //runEnterAnimatin(holder.itemView);
    }

    /**
     * 进入动画,从右侧进入到左侧
     */
    private void runEnterAnimatin(View itemView) {

        Boolean isNotFirst = (Boolean) itemView.getTag(IS_IFRST);
        DisplayMetrics displayMetrics = itemView.getContext().getResources().getDisplayMetrics();

        itemView.setTranslationX(displayMetrics.widthPixels);
        itemView.animate()
                .translationX(0)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(1000)
                .start();

    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    class HomeViewHolder extends  RecyclerView.ViewHolder{

        private TextView text;

        public HomeViewHolder(View itemView) {
            super(itemView);
            text = ((TextView) itemView.findViewById(R.id.item_home_text));
        }
    }


    /**
     * 初始化数据
     */
    public void refresh() {
        datas.clear();
        for(int i = 0;i<30;i++){

            datas.add(i+"");
        }
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     */
    public void add(){
        int length = datas.size();
        for(int i=length ;i<length+20;i++){
            datas.add(i+"");
        }
        notifyDataSetChanged();

    }
}
