package com.study.geekbang.lesson2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author hong.zheng
 * @Date: 9/25/21 9:51 PM
 **/
public class OkHttpDemo {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8081";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

}