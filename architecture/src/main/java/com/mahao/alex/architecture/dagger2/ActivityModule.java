package com.mahao.alex.architecture.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */


@Module
public class ActivityModule {

    @Provides
    User providerUser(){
        return new User();
    }

}
