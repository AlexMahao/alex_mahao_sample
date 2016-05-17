package com.mahao.alex.architecture.dagger2_mvp;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
public class UserBean {

    private  String name;


    private String passwrod;


    public UserBean(String name, String passwrod) {
        this.name = name;
        this.passwrod = passwrod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
}
