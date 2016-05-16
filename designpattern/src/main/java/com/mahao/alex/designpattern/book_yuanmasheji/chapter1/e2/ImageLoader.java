package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e2;

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
 *  添加了imageCache  缓存  两个完全不一样的功能就不应该放在一个类中，一个类应该是一组相关性很高的函数，数据的封装。
 * Created by Alex_MaHao on 2016/5/16.
 */
public class ImageLoader {

    //缓存
    ImageCache mImageCache  = new ImageCache();

    //线程池，线程数量为cpu数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public ImageLoader(){
    }


    public void displayImage(final String url, final ImageView imageView){
        Bitmap bitmap = mImageCache.get(url);

        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }

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
