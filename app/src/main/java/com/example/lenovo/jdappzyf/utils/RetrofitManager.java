package com.example.lenovo.jdappzyf.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2018/11/7.
 */

public class RetrofitManager {
    private static final String BASE_URL = "http://www.zhaoapi.cn/";

    private Retrofit retrofit;
    private static final class SINGLE_INSTANCE{
        private static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance(){
        return SINGLE_INSTANCE.INSTANCE;
    }
    private RetrofitManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkhttpClient())
                .build();
    }

    private OkHttpClient buildOkhttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
    public <T> T create(Class<T> clazz){
        return retrofit.create(clazz);
    }


}
