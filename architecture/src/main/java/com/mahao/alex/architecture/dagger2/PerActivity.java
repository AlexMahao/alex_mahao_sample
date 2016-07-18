package com.mahao.alex.architecture.dagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Activity 单例生命周期
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
