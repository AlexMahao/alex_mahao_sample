package com.mahao.alex.architecture.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by MH on 2016/7/18.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonForName {
    
}
