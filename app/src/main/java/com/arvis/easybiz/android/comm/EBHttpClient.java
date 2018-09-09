package com.arvis.easybiz.android.comm;



import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class EBHttpClient {

    private OkHttpClient okHttpClient;

    private static final String API_TOKEN = "imwGwKvwJpUOCeEcT33SDf9Yp";

    private static EBHttpClient instance;

    public static void init(){

        instance = new EBHttpClient();
    }

    private EBHttpClient(){

        okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS).build();
    }

    public void setGetRequest(String url, Callback callback){

        okHttpClient.newCall(new Request.Builder().addHeader("X-App-Token", API_TOKEN).url(url).get().build()).enqueue(callback);
    }

    public static EBHttpClient getInstance(){

        return instance;
    }
}
