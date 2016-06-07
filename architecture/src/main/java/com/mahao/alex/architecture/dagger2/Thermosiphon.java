package com.mahao.alex.architecture.dagger2;

import javax.inject.Inject;

/**
 * Created by Alex_MaHao on 2016/5/19.
 */
public class Thermosiphon implements Pump {

    private final Heater heater;


    @Inject
    public Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override public void pump() {
        if (heater.isHot()) {
            System.out.println("=> => pumping => =>");
        }
    }
}
