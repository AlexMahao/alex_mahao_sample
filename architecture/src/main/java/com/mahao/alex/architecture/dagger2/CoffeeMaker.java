package com.mahao.alex.architecture.dagger2;

import javax.inject.Inject;

import dagger.Lazy;

/**
 *
 * 是否存在一种方法，使我们在调用时自动构造对象，
 *      及通过工具类的形式，让他们组装好
 * Created by Alex_MaHao on 2016/5/19.
 */
public class CoffeeMaker {

    //懒惰注入，使用到该对象时，才注入并创建实例
    private final Lazy<Heater> heater;

    private final Pump pump;


    @Inject
    public CoffeeMaker(Lazy<Heater> heater,Pump pump) {
        this.heater = heater; // 加热器
        this.pump = pump; // 构造抽水器
    }

    public void brew() {
        //煮咖啡
        heater.get().on();
        pump.pump();
        System.out.println(" [_]P coffee! [_]P ");
        heater.get().off();
    }

}
