package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 图片加载   单一职责原则  Single Responsibility Principle   ---SRP
 *      就一个类而言，应该仅有一个引起他变化的原因
 *
 *
 * Created by Alex_MaHao on 2016/5/16.
 */
public class ImageLoader {

    //缓存
    LruCache<String,Bitmap> mImageCache;

    //线程池，线程数量为cpu数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public ImageLoader(){
        initImageLoader();
    }

    private void initImageLoader() {
        //计算内存大小
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);

        final int cacheSize = maxMemory/4;

        mImageCache = new LruCache<String,Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return  value.getRowBytes()*value.getHeight()/1024;
            }
        };
        
        
    }
    
    public void displayImage(final String url, final ImageView imageView){
        imageView.setTag(url);
        
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                
                if(bitmap ==null){
                    return;
                }
                
                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                
                mImageCache.put(url,bitmap);

            }


        });
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap =null;

        try {
            URL url = new URL(imageUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            bitmap = BitmapFactory.decodeStream(conn.getInputStream());

            conn.disconnect();


        }catch (Exception e){
            e.printStackTrace();
        }

        return  bitmap;
    }

}
