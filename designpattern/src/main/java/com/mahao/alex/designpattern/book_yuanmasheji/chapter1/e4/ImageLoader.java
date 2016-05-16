package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 开闭原则  Open Close Principle OCP
 *
 *  定义：软件中的对象，应该对于扩展是开放的，但对于修改时封闭的。
 *
 *  当软件需要变化时，应该尽量通过扩展的方式来实现变化，而不是通过修改已有的代码来实现。
 *
 *  《面向对象软件构造》 ：程序中一个类的实现只应该因错误而被修改，新的或者改变的特性应该通过新建不同的类实现
 *      新建的类可以通过继承的方式来重用原来的代码。
 */
public class ImageLoader {


    ImageCache mImageCache ;


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


    public void setImageCache(ImageCache imageCache){
        mImageCache = imageCache;
    }

}
