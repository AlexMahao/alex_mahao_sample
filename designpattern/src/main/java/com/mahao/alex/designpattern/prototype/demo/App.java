package com.mahao.alex.designpattern.prototype.demo;

import android.app.Application;

/**
 *  自定义Application
 * Created by alex_mahao on 2016/8/30.
 */
public class App extends Application {

    // 保存user对象
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // 设置user对象
    public void setUser(User user){
        user = this.user;
    }


    public User getUser(){
        return user;
    }
}
