package com.mahao.alex.architecture.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex_MaHao on 2016/5/19.
 */
@Module
public class PumpModule {

    @Provides Pump providePump(Thermosiphon pump){

        return pump;
    }

}
