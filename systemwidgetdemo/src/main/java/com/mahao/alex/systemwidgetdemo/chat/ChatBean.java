package com.mahao.alex.systemwidgetdemo.chat;

import android.graphics.drawable.Drawable;

/**
 * Created by Alex_MaHao on 2016/5/18.
 */
public class ChatBean {

    public static final int CHAT_MYSELF = 0;
    public static final int CHAT_FIRENDS = 1;

    /**
     * 聊天的头像
     */
    private Drawable userIcon;

    /**
     * 发送的消息
     */
    private String message;

    /**
     * 确定是否为当前聊天者
     */
    private int type;


    public ChatBean() {
    }

    public ChatBean(String message, int type, Drawable userIcon) {
        this.message = message;
        this.type = type;
        this.userIcon = userIcon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Drawable getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(Drawable userIcon) {
        this.userIcon = userIcon;
    }
}
