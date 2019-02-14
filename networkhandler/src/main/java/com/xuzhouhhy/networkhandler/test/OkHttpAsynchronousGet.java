package com.xuzhouhhy.networkhandler.test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * created by hanhongyun on 2019/2/14 10:55
 */
public class OkHttpAsynchronousGet {

    public static void main(String... args) throws Exception {
        new OkHttpAsynchronousGet().run();
    }

    public void run() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody body = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code" + response);
                    }
                    Headers headers = response.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        System.out.println(headers.name(i) + ":" + headers.value(i));
                    }
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        System.out.println(responseBody.string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
