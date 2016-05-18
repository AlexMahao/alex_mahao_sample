package com.mahao.alex.systemwidgetdemo.listView.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.mahao.alex.systemwidgetdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex_MaHao on 2016/5/18.
 */
public class ChatListViewActivity extends AppCompatActivity {

    private ListView mChatLv;

    private List<ChatBean> chatBeanList = new ArrayList<>();

    private ChatAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview_chat);

        mChatLv = ((ListView) findViewById(R.id.listview_chat_lv));

        initChatBeanList();

        adapter = new ChatAdapter(chatBeanList);

        mChatLv.setAdapter(adapter);


        mChatLv.postDelayed(new Runnable() {
            @Override
            public void run() {
                //设置我们ListView显示在底部
                mChatLv.setSelection(adapter.getCount());
            }
        },200);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 初始化聊天内容
     */
    private void initChatBeanList() {
        for (int i = 0;i<10;i++){
            ChatBean chat = new ChatBean();
            if(i%2==0){
                chat.setType(ChatBean.CHAT_MYSELF);
                chat.setUserIcon(getApplicationContext().getResources().getDrawable(R.mipmap.qq));
                chat.setMessage("微信，微信，我是QQ");
            }else{
                chat.setType(ChatBean.CHAT_FIRENDS);
                chat.setUserIcon(getApplicationContext().getResources().getDrawable(R.mipmap.weixin));
                chat.setMessage("QQ，QQ，我是微信");
            }

            chatBeanList.add(chat);
        }
    }
}
