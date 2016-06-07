package com.mahao.alex.architecture.dagger2;

/**
 * Created by Alex_MaHao on 2016/5/19.
 */
public class CoffeeApp {

    public static void main(String[] args){
        Coffee coffee = DaggerCoffee.builder().build();
        coffee.maker().brew();
    }
}
