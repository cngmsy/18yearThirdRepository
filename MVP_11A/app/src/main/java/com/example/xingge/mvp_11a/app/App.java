package com.example.xingge.mvp_11a.app;

import android.app.Application;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.example.xingge.mvp_11a.base.BaseActivity;

import okhttp3.MediaType;

/**
 * Created by xingge on 2017/7/11.
 */

public class App extends Application {

    public static BaseActivity context = null;
    public static  MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");


    @Override
    public void onCreate() {
        super.onCreate();
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"uXdYDplWfQwMTOK4AGla8PZy");
    }



}
