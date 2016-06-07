package com.mahao.alex.architecture.dagger2;

/**
 *
 * 咖啡的加热器
 * Created by Alex_MaHao on 2016/5/19.
 */
public interface Heater {


    void on();

    void off();

    boolean isHot();

}
