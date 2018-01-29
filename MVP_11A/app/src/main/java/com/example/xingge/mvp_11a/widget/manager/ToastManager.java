package com.example.xingge.mvp_11a.widget.manager;

import android.widget.Toast;

import com.example.xingge.mvp_11a.app.App;

/**
 * Created by xingge on 2017/7/26.
 */

public class ToastManager {

    public static void show(String msg){
        Toast.makeText(App.context,msg,Toast.LENGTH_LONG).show();
    }
}
