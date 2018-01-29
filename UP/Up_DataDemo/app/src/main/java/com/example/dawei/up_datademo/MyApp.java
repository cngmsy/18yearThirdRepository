package com.example.dawei.up_datademo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/1/28.
 */

public class MyApp extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        getMyApplicationContext();
        mContext = MyApp.this;
    }
    public static Context getMyApplicationContext() {
        return MyApp.mContext;
    }
}
