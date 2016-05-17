package com.mahao.alex.systemwidgetdemo.listView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public abstract  class BaseAppAdapter<T> extends BaseAdapter {

    /**
     * 泛型，保存数据
     */
    protected List<T> datas;

    /**
     * 构造方法，子类必须实现其构造方法，并初始化数据
     * @param datas
     */
    public BaseAppAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
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

        BaseViewHolder vHolder;

        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(),parent,false);

            vHolder = new BaseViewHolder(convertView);

            convertView.setTag(vHolder);
        }else{
            vHolder = (BaseViewHolder) convertView.getTag();
        }

        /**
         * 数据绑定的回调
         */
        bindData(vHolder,datas.get(position));

        return convertView;
    }

    /**
     * 子类实现，获取item布局文件的id
     * @return
     */
    protected  abstract int getItemLayoutId();

    /**
     * 子类实现，绑定数据
     * @param vHolder  对应position的ViewHolder
     * @param data 对应的数据绑定
     */
    protected abstract void bindData(BaseViewHolder vHolder,T data);


    /**
     * ViewHolder类
     */
    class BaseViewHolder{

        /**
         * 保存view，以id-view的形式
         */
        Map<Integer,View> mapView;

        /**
         * 根视图
         */
        View rootView;

        public BaseViewHolder(View rootView){
            this.rootView = rootView;
            mapView = new HashMap<Integer,View>();
        }

        /**
         * 通过id查找控件
         * @param id
         * @return
         */
        public View getView(Integer id){
            View view = mapView.get(id);
            if(view==null){
                view = rootView.findViewById(id);
                mapView.put(id,view);
            }

            return view;
        }

    }

}
