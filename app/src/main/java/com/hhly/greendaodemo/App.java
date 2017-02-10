package com.hhly.greendaodemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @创建者 frank
 * @时间 2017/2/10 15:16
 * @描述：${TODO}
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

    }
}
