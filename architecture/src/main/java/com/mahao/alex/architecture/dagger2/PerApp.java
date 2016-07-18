package com.mahao.alex.architecture.dagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 全局的生命周期单例
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {

}
