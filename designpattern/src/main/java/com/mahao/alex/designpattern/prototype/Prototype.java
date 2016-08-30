package com.mahao.alex.designpattern.prototype;

import java.util.ArrayList;

/**
 *
 * 原型模式
 *
 * Created by alex_mahao on 2016/8/30.
 */
public class Prototype implements Cloneable {


    private String id;

    private ArrayList<String> names ;



    public Prototype(String id, ArrayList<String> names) {
        this.id = id;
        this.names = names;
    }

    // 拷贝方法
    @Override
    public Object clone() {
        Prototype prototype = null;

        try {
            // 调用Object的clone() 拷贝对象
            prototype = (Prototype) super.clone();
            // 一些集合类都默认实现了clone()方法
            prototype.names = (ArrayList<String>) this.names.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 返回拷贝的实例对象
        return prototype ;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }


    @Override
    public String toString() {
        return "Prototype{" +
                "id='" + id + '\'' +
                ", names=" + names +
                '}';
    }
}
