package com.mahao.alex.architecture.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex_MaHao on 2016/5/19.
 */
@Module(includes = PumpModule.class)
public class DripCoffeeModule {

    @Provides @Singleton Heater provideHeater(){

        return new ElectricHeater();
    }


}
