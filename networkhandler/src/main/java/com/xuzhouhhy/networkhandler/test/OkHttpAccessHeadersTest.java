package com.xuzhouhhy.networkhandler.test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * created by hanhongyun on 2019/2/14 11:52
 */
public class OkHttpAccessHeadersTest {

    public static void main(String... args) throws Exception {
        new OkHttpAccessHeadersTest().run();
    }

    public void run() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3_json")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code" + response);
            }
            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
