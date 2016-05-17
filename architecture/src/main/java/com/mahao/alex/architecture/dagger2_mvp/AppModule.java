package com.mahao.alex.architecture.dagger2_mvp;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }


    @Provides
    @Singleton
    SPUtils provideSpfManager() {
        return new SPUtils(application);
    }


}