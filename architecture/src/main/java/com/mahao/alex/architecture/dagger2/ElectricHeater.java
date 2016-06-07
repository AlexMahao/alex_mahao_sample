package com.mahao.alex.architecture.dagger2;

/**
 *
 * 加热器的实现类
 * Created by Alex_MaHao on 2016/5/19.
 */
public class ElectricHeater implements Heater {
    boolean heating;

    @Override public void on() {
        System.out.println("~ ~ ~ heating ~ ~ ~");
        this.heating = true;
    }

    @Override public void off() {
        this.heating = false;
    }

    @Override public boolean isHot() {
        return heating;
    }
}
