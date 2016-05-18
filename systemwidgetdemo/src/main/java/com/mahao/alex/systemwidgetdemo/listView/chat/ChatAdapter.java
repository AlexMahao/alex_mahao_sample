package com.mahao.alex.systemwidgetdemo.listView.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.List;

/**
 * 聊天的Adapter，实现加载不同的布局
 * Created by Alex_MaHao on 2016/5/18.
 */
public class ChatAdapter extends BaseAdapter {

    List<ChatBean> chatBeens ;

    public ChatAdapter(List<ChatBean> chatBeens) {
        this.chatBeens = chatBeens;
    }

    @Override
    public int getCount() {
        return chatBeens.size();
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
    public int getItemViewType(int position) {
        //返回值需要从0开始，对应数据所要加载的对应布局标识
        return chatBeens.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        //不同布局的种类数量
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //获取当前数据的类型
        int type = getItemViewType(position);

        ViewHolder vHolder = null;
        if(convertView==null){
            switch (type){
                case ChatBean.CHAT_MYSELF:
                    //加载自己消息显示的布局
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_chat_right,parent,false);
                    vHolder = new ViewHolder();
                    vHolder.icon = ((ImageView) convertView.findViewById(R.id.chat_right_icon));
                    vHolder.msg = ((TextView) convertView.findViewById(R.id.chat_right_msg));
                    break;
                case ChatBean.CHAT_FIRENDS:
                    //加载朋友消息显示的布局
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_chat_left,parent,false);
                    vHolder = new ViewHolder();
                    vHolder.icon = ((ImageView) convertView.findViewById(R.id.chat_left_icon));
                    vHolder.msg = ((TextView) convertView.findViewById(R.id.chat_left_msg));
                    break;
            }
            //控件应用与控件绑定
            convertView.setTag(vHolder);
        }else{
            //获取控件引用
            vHolder = (ViewHolder) convertView.getTag();
        }

        //设置数值
        vHolder.icon.setImageDrawable(chatBeens.get(position).getUserIcon());
        vHolder.msg.setText(chatBeens.get(position).getMessage());

        return convertView;
    }


    class ViewHolder{
        ImageView icon;
        TextView msg;
    }
}
