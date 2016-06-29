package com.example.networklib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BAIDU_MP3_PATH = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&type=1&offset=0&size=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 同步的get 请求 注意：- 该方法是同步的方法，在实际使用中，我们需要手动封装线程 - 如果请求的body
     * 中的内容大于1m的话，避免使用string()，因为该方法是将数据全部加载到了内存中 建议使用流
     *
     * @return
     */
    public static void syncGet() throws Exception {

        // 1. 创建`OkHttpClient`对象
        final OkHttpClient client = new OkHttpClient();

        // 2.根据需求创建`Request`对象.在此只是添加了最基本的url
        Request request = new Request.Builder().url(BAIDU_MP3_PATH).build();

        // 3.通过`request`对象创建`Call`对象
        Call call = client.newCall(request);

        // 4.使用`call`对象进行网络请求。返回封装好的`Response`
        Response response = call.execute();

        // 5.解析`Response`对象，获取响应信息
        if (!response.isSuccessful())
            // code >= 200 && code < 300;
            System.out.println(response);

        Headers responseHeaders = response.headers();

        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ":"
                    + responseHeaders.value(i));
        }

        // 打印响应的内容
        System.out.println(response.body().string());

        // 字节输入流
        response.body().byteStream();

        // 字符输入流
        response.body().charStream();

    }

    /**
     * 异步的get方法
     */
    public static void asyncGet() throws Exception {

        // 1. 创建`OkHttpClient`对象
        final OkHttpClient client = new OkHttpClient();

        // 2.根据需求创建`Request`对象.在此只是添加了最基本的url
        Request request = new Request.Builder().url(BAIDU_MP3_PATH).build();

        // 3.通过`request`对象创建`Call`对象
        Call call = client.newCall(request);

        // 4.使用`call`对象进行网络请求。通过Callback 监听网络请求
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response)
                    throws IOException {
                // 5， 获取响应结果并解析
                if (!response.isSuccessful()) {
                    System.out.println(response);
                    return;
                }

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": "
                            + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }

            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

        });
    }

    public void accessHeader() throws Exception {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                // 只有一个参数的头 ，一对一，如果在添加会覆盖
                .header("User-Agent", "OkHttp Headers.java")
                // 一对多，添加的关系
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json").build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        System.out.println("Server: " + response.header("Server"));
        System.out.println("Date: " + response.header("Date"));
        // 一对多的获取
        System.out.println("Vary: " + response.headers("Vary"));
    }

    /**
     * post 请求，传入一个字符串
     *
     * @throws Exception
     */
    public static void postString() throws Exception {

        // post 传参类型 编码
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType
                .parse("text/x-markdown; charset=utf-8");
        // post 参数值
        String params = "### 三级标题";

        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, params)).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());

    }

    /**
     * 传入文件
     */
    public static void postFile() throws Exception {
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType
                .parse("text/x-markdown; charset=utf-8");

        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, new File("README.MD")))
                .build();

        // ....
    }

    /**
     * post form 表单
     */
    public static void postForm() {

        RequestBody formBody = new FormBody.Builder()
                .add("name", "1234")
                .build();

        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(formBody)
                .build();

        // .....
    }


    /**
     * 提交复杂的form 表单 附带文件
     * <p/>
     * MultiBody
     */
    public static void postMultipart() {

        /**
         * 上传的文件类型
         */
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        /**
         * 参数
         */
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "标题")
                .addFormDataPart("image", "logo.png", RequestBody.create(MEDIA_TYPE_PNG, new File("xx.png")))
                .build();

        Request request = new Request.Builder()
                .url("xxx")
                .post(requestBody)
                .build();

        // ......

    }


    /**
     * 缓存
     *
     * @throws Exception
     */
    public static void cacheResponse() throws Exception {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(new File("cache"), cacheSize);

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();

        Request request = new Request.Builder()
                .url("http://xxxxx/login.txt")
                .cacheControl(new CacheControl.Builder().noCache().build())
                .build();

        Response response1 = client.newCall(request).execute();
        if (!response1.isSuccessful())
            throw new IOException("Unexpected code " + response1);

        String response1Body = response1.body().string();
        System.out.println("Response 1 response:          " + response1);
        System.out.println("Response 1 cache response:    "
                + response1.cacheResponse());
        System.out.println("Response 1 network response:  "
                + response1.networkResponse());

        Response response2 = client.newCall(request).execute();
        if (!response2.isSuccessful())
            throw new IOException("Unexpected code " + response2);

        String response2Body = response2.body().string();
        System.out.println("Response 2 response:          " + response2);
        System.out.println("Response 2 cache response:    "
                + response2.cacheResponse());
        System.out.println("Response 2 network response:  "
                + response2.networkResponse());

        System.out.println("Response 2 equals Response 1? "
                + response1Body.equals(response2Body));
    }


    /**
     * 总的超时时间设置
     */
    public void timeOut() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)// 连接超时 10s
                .writeTimeout(10, TimeUnit.SECONDS) // 输入超时 10s
                .readTimeout(30, TimeUnit.SECONDS) // 读取超时  30s
                .build();
    }


    /**
     * 设置不同的超时时间
     */
    public static void singleTimeOut() throws Exception {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://xxxx").build();

        try {
            // 从client 中抠出一小块，用于单独的设置
            // 设置读取时间为1ms,肯定会超时
            OkHttpClient copy = client.newBuilder()
                    .readTimeout(1, TimeUnit.MILLISECONDS).build();

            Response response = copy.newCall(request).execute();
            System.out.println("Response 1 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 1 failed: " + e);
        }
        try {
            // 从client 中抠出一小块，用于单独的设置
            // 设置读取时间为3000ms
            OkHttpClient copy = client.newBuilder()
                    .readTimeout(3000, TimeUnit.MILLISECONDS).build();

            Response response = copy.newCall(request).execute();
            System.out.println("Response 2 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 2 failed: " + e);
        }

    }


    /**
     * Log 网络请求信息的实现
     * @throws Exception
     */
    public static void logInterceptor() throws Exception {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor()).build();
        Request request = new Request.Builder().url(BAIDU_MP3_PATH)
                .post(new FormBody.Builder().add("name", "123").build())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

    }

    /**
     * Log 网络请求
     */
    static class LoggingInterceptor implements Interceptor {

        public Response intercept(Chain chain) throws IOException {
            // 固定写法
            Request request = chain.request();
            // 固定写法
            Response response = chain.proceed(request);

            // --------Log 信息
            System.out.println("=====请求========");

            // 请求行
            System.out.println(request);
            // 请求头信息
            System.out.println(request.headers());

            // form 表单信息。注意，此时只对form 表单适用
            FormBody form = (FormBody) request.body();
            for (int i = 0; i < form.size(); i++) {
                System.out.println(form.encodedName(i) + "="
                        + form.encodedValue(i));
            }

            System.out.println("=====响应========");
            // 响应行
            System.out.println(response);
            // 响应头
            System.out.println(response.headers());
            // 响应体
            System.out.println(response.body().string());
            return response;
        }
    }


}
