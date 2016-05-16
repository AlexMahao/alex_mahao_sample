package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e4;

import android.graphics.Bitmap;

/**
 * Created by Alex_MaHao on 2016/5/16.
 */
public interface ImageCache {

    public Bitmap get(String url);


    public void put(String url, Bitmap bitmap);

}
