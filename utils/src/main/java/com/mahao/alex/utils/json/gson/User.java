package com.mahao.alex.utils.json.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdw on 2016/6/6.
 */
public class User {

    private String name;

    private int age;

    @SerializedName(value = "is_man",alternate = {"is_Man","isMan"})
    private boolean isMan;

    private String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMan=" + isMan +
                ", sex='" + sex + '\'' +
                '}';
    }
}
