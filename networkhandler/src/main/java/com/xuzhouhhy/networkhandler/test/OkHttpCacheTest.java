package com.xuzhouhhy.networkhandler.test;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * created by hanhongyun on 2019/2/15 10:13
 */
public class OkHttpCacheTest {

    private OkHttpClient mOkHttpClient;

    private OkHttpCacheTest(File cacheDirectory) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheDirectory, cacheSize);
        mOkHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    public static void main(String... args) throws Exception {
        new OkHttpCacheTest(new File("~/Desktop/cache/")).run();
    }

    private void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        String response1Body = "";
        try (Response response1 = mOkHttpClient.newCall(request).execute()) {
            if (!response1.isSuccessful()) {
                throw new IOException("Unexpected code: " + response1);
            }
            if (response1.body() != null) {
                response1Body = response1.body().string();
            }
            System.out.println("Response 1 response:          " + response1);
            System.out.println("Response 1 cache response:    " + response1.cacheResponse());
            System.out.println("Response 1 network response:  " + response1.networkResponse());
        }

        String response2Body = "";
        try (Response response2 = mOkHttpClient.newCall(request).execute()) {
            if (!response2.isSuccessful()) {
                throw new IOException("Unexpected Code: " + response2);
            }
            if (response2.body() != null) {
                response2Body = response2.body().string();
            }
            System.out.println("Response 2 response:          " + response2);
            System.out.println("Response 2 cache response:    " + response2.cacheResponse());
            System.out.println("Response 2 network response:  " + response2.networkResponse());
        }

        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }
}
