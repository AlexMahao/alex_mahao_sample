package com.mahao.alex.designpattern.prototype.demo;

/**
 * Created by alex_mahao on 2016/8/30.
 */
public class User implements Cloneable {

    public  String name;

    public String password;


    @Override
    public Object clone()  {
        // 实现clone方法
        User user = null;
        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return user;
    }
}
