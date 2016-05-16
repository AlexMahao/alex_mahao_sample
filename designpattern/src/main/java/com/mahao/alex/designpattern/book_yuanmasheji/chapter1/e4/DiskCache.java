package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Alex_MaHao on 2016/5/16.
 */
public class DiskCache implements ImageCache {

    static String cacheDir = "sdcard/cache/";

    /**
     * 获取图片
     */
    public Bitmap get(String url){

        return BitmapFactory.decodeFile(cacheDir+url);
    }


    /**
     * 存储图片
     * @param url
     * @param bmp
     */
    public void put(String url,Bitmap bmp){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(cacheDir+url);
            bmp.compress(Bitmap.CompressFormat.JPEG,100,fos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
