package com.mahao.alex.architecture.dagger2;

import android.app.Activity;

import dagger.Component;

/**
 *  子的Component
 * Created by MH on 2016/7/18.
 */
@PerActivity  // ActivityMoudule 中使用了该标记
@Component(dependencies = AppComponent.class,modules = ActivityMoudule.class)
public interface ActivityComponent {

    // 注入
    void inject(MainActivity activity);
}
