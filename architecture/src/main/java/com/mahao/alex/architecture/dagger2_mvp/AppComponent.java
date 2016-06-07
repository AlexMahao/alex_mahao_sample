package com.mahao.alex.architecture.dagger2_mvp;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context context();  // 提供Applicaiton的Context

    SPUtils spUtils();

}
