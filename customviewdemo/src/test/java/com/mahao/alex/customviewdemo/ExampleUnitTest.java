package com.mahao.alex.customviewdemo;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test_day(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 年份
        int year = calendar.get(Calendar.YEAR);
        // 月份
        int month = calendar.get(Calendar.MONTH);

        // 这一周的第几天
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        System.out.print("year:"+year+"month:"+month+"dayOfWeek:"+dayOfWeek+"dayOfMonth:"+dayOfMonth);
    }
}