package com.mahao.alex.architecture.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *  定义注解
 *
 *      元注解： 元注解的作用是负责注解其他注解，java定义了5个注解：
 *          - @Target: 目标，该注解指明了我们定义的注解所修饰的范围，如packages,types(类，接口，枚举)，类型成员，方法参数和本地变量。
 *              - 定义方式 @Target(ElementType.Type)
 *                  可取值：CONSTRUCTOR:用于描述构造器
 *                          FIELD:用于描述域，例如成员变量等
 *                          LOCAL_VARIABLE:用于描述局部变量
 *                          METHOD:用于描述方法。
 *                          PACKAGE: 描述包、
 *                          PARAMETER :用于描述参数
 *                          TYPE: 用于描述类，接口，或enum类型
 *
 *          - @Retention:  定义了Annotation被保留的时间长短：某些Annotaion仅出现在源代码中，而被编译器丢弃。
 *                              有一些是被编译到class文件中，单被虚拟机加载时被忽略，还有一些是能够被装载到虚拟机中的。
 *
 *                 作用：描述注解的生命周期，及其作用范围。
 *
 *               - 定义方式： @Retention(RetentionPolicy.RUNTIME)
 *
 *                  可取值： RetentionPolicy.SOURCE : 表示只在源代码时期有限，及源代码到class 文件期间发挥作用
 *                          RetentionPolicy.CLASS: 在class 文件中有限。
 *                          RetentionPolicy.RUNTION : 运行期依然有效
 *
                 *                @Target(ElementType.FIELD)
                 @Retention(RetentionPolicy.RUNTIME)
                 public @interface Column {
                 public String name() default "fieldName";
                 public String setFuncName() default "setField";
                 public String getFuncName() default "getField";
                 public boolean defaultDBValue() default false;
                 }
                  *
                  *
                  *
                  *
                  *          - @Documented:
                  *          - @Inherited:
 *
 *
 *
 *
 *
 * Created by alex_mahao on 2016/9/26.
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {

    int value();
}
