package com.mahao.alex.architecture.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex_MaHao on 2016/5/19.
 */

@Singleton
@Component(modules = DripCoffeeModule.class)
public interface Coffee {
    CoffeeMaker maker();
}
