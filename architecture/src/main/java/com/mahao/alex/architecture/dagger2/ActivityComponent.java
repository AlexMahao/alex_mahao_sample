package com.mahao.alex.architecture.dagger2;

import dagger.Component;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */

@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(Dagger2Activity actvity);

}
