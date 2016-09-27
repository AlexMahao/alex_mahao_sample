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
 *          - @Documented ： 声明该注解应该被JavaDoc 工具记录，该注解没有值，为标记性注解。默认情况下，javadoc不会对注解
 *                  生成文档，通过Documented修饰的注解，将会生成doc文档。
 *
 *          - @Inherited : 该元注解修饰的注解，则被该注解标明的class可以传入被子类继承。
 *
 *              注解继承的关系： 子类继承了父类，则可以继承未被重载的方法的注解，而被重载的方法的注解获取不到。同时父类的类
 *                  注解，无法再子类中获取。
 *
 *              通过@Inherited标注的class ,则能够被子类获取，其他的不变。
 *
 *
 *      注解以@Interface 声明， 通过其声明自动继承了java.lang.annotation.Annotation 接口。
 *
 *      注解中，定义方法来声明的是每一个注解中的参数，方法名代表参数名，返回值类型代表参数类型，同时可定义默认值
 *
 *
 *      注解元素必须有确定的值，要么在定义注解的默认值中指定，要么在使用注解时指定，非基本数据的数据类型不能为null。
 *          因此使用0或者空字符串是一种常用的使用方式。
 *
 *
 *  Created by alex_mahao on 2016/9/26.
 */
public class BaseAnno {

}


/**
 * 运行时注解，可以在程序运行时动态获取注解中的值
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface  Column{

    public String name() default "fieldName";
}
