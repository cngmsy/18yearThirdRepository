package com.example.dawei.up.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.example.dawei.up.MyApp;
import com.example.dawei.up.R;

/**
 * Created by wj on 2017/5/9.
 */

public class Utils {

    private static Context myApplicationContext;
    private static Resources resources;
    private static String string;

    /**
     * 获取应用当前版本代码
     *
     * @return
     */
    public static int getCurrentVerCode() {
        String packageName = MyApp.getMyApplicationContext().getPackageName();
        int currentVer = -1;
        try {
            currentVer = MyApp.getMyApplicationContext().getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVer;
    }

    /**
     * 获取应用当前版本名称
     *
     * @return
     */
    public static String getCurrentVerName() {
        String packageName = MyApp.getMyApplicationContext().getPackageName();
        String currentVerName = "";
        try {
            currentVerName = MyApp.getMyApplicationContext().getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVerName;
    }

    /**
     * 获取应用名称
     *
     * @return
     */
    public static String getAppName() {
        myApplicationContext = MyApp.getMyApplicationContext();
        resources = myApplicationContext.getResources();
        string = resources.getText(R.string.app_name).toString();
        return string;
    }


}
