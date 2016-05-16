package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e3;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Alex_MaHao on 2016/5/16.
 */
public class ImageCache {

    LruCache<String,Bitmap> mImageCache;

    public ImageCache(){

        initImageCache();
    }

    private void initImageCache() {

        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);

        int cacheSize = maxMemory/4;

        mImageCache = new LruCache<String ,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()*value.getHeight()/1024;


            }
        };


    }

    public void put(String url ,Bitmap bitmap){
        mImageCache.put(url, bitmap);
    }


    public Bitmap get(String url){
        return mImageCache.get(url);
    }

}
