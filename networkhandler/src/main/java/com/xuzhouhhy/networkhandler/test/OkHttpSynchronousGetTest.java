package com.xuzhouhhy.networkhandler.test;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * created by hanhongyun on 2019/2/14 09:53
 */
public class OkHttpSynchronousGetTest {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String... args) throws Exception {
        new OkHttpSynchronousGetTest().run();
    }

    private void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("request fail");
            }
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                System.out.println(headers.name(i) + ":" + headers.value(i));
            }
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                System.out.println(responseBody.string());
            }
        }
    }

}
