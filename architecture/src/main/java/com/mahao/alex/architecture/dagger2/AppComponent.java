package com.mahao.alex.architecture.dagger2;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * 全局的Component 组件
 * Created by MH on 2016/7/18.
 */

@PerApp // 因为Module 中使用了该标记,所以需要在此添加
@Component(modules = AppModule.class)
public interface AppComponent {

    // 向其下层提供Context 对象
    Context proContext();
}
