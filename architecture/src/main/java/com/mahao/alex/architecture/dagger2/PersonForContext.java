package com.mahao.alex.architecture.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by MH on 2016/7/18.
 */

@Qualifier  // 关键词
@Retention(RetentionPolicy.RUNTIME)  // 表示
public @interface PersonForContext {
    // Context 对象的注解
}
