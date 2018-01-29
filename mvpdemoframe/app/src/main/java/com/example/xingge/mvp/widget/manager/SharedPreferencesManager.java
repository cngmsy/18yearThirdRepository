package com.example.xingge.mvp.widget.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.xingge.mvp.app.App;

/**
 * Created by xingge on 2017/7/26.
 */

public class SharedPreferencesManager {

    private static SharedPreferences preferences = App.context.getSharedPreferences("user",Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = preferences.edit();
    public static void saveUserInfo(String userInfo){
        editor.putString("userInfo",userInfo);
        editor.commit();
    }
}
