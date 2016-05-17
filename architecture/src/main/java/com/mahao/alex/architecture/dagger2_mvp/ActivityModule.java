package com.mahao.alex.architecture.dagger2_mvp;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity activity() {
        return this.activity;
    }

}
