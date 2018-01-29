package com.example.dawei.up;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MyApp extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        getMyApplicationContext();
        mContext = MyApp.this;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static Context getMyApplicationContext() {
        return MyApp.mContext;
    }

}