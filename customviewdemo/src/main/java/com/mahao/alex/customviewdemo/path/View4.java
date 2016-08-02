package com.mahao.alex.customviewdemo.path;

/**
 *
 *   Path 渲染
 *
 *   在Paint 中，Style 有几种样式，而Path 是如何判断是否需要填充颜色呢
 *
 *
 *   此处判断的图形皆为封闭图形
 *
 *   奇偶规则： 奇数表示在图形内，偶数在图形外。从任意位置p作一条射线，若与该射线相交的图形的数目为奇数，则P是图形内部点。否则是外部点。
 *
 *   非零环绕数规则：若环绕数为0表示在图形外，非零表示在图形内。
 *
 *
 *
 *
 * Created by alex_mahao on 2016/8/2.
 */
public class View4 {
}
