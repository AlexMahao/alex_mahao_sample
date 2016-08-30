package com.mahao.alex.designpattern;

import com.mahao.alex.designpattern.prototype.Prototype;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_prototype(){
        ArrayList<String> names = new ArrayList<>();
        Prototype prototype = new Prototype("1",names);
        // 拷贝对象
        Prototype clone = (Prototype) prototype.clone();
        System.out.println("********拷贝对象************");
        System.out.println(clone);

        // 对拷贝对象设置值
        clone.getNames().add("Alex");
        clone.setId("2");
        // 看一下修改结果
        System.out.println("********原对象************");
        System.out.println(prototype);
        System.out.println("********拷贝对象************");
        System.out.println(clone);

    }



}