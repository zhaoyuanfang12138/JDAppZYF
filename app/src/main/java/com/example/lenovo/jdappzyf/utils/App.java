package com.example.lenovo.jdappzyf.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by lenovo on 2018/11/7.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(this);

    }
}
