package com.xuzhouhhy.networkhandler.test;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * created by hanhongyun on 2019/2/15 09:25
 */
public class OkHttpPostFormParametersTest {

    public static void main(String... args) throws Exception {
        new OkHttpPostFormParametersTest().run();
    }

    public void run() throws Exception {
        FormBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }

            ResponseBody body = response.body();
            System.out.println(body == null ? "body is null" : body.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
