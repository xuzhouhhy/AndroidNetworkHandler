package com.xuzhouhhy.networkhandler.test;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * created by hanhongyun on 2019/2/15 09:15
 */
public class OkHttpPostFileTest {

    private static final MediaType MEDIA_TYPE_MARKDOWN =
            MediaType.parse("text/x-markdown; charset=utf-8");

    public static void main(String... args) throws Exception {
        new OkHttpPostFileTest().run();
    }

    private void run() throws Exception {
        final OkHttpClient okHttpClient = new OkHttpClient();

        File file = new File("README.me");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }

            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
