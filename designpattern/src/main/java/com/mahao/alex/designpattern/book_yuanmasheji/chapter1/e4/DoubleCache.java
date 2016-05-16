package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e4;

import android.graphics.Bitmap;

import com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e3.*;

/**
 * Created by Alex_MaHao on 2016/5/16.
 */
public class DoubleCache implements ImageCache {

    MemoryCache mMemoryCache = new MemoryCache();

    DiskCache mDiskCache = new DiskCache();


    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if(bitmap==null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
}
