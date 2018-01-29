package com.example.dawei.up;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.dawei.up.bean.MyBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv_version;
    private PackageInfo packageInfo;
    private int versionCode;
    private String urls;
    private TextView tv_pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getJSON();
    }

    private void initView() {
        tv_pro = (TextView) findViewById(R.id.tv_pro);
        tv_version = (TextView) findViewById(R.id.tv_version);
        // 设置版本号
        tv_version.setText("版本号：" + getAppVersion());
    }


    public void createDialogUpdate(final Context context, final String urls) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(context);
        Dialog.setTitle("更新提示")
                .setMessage("发现新版本，是否立即更新?")
                .setCancelable(false).setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 更新下载 路径
                VersionUpdate.newInstance().gotoUpdate(context, urls);
                Logger.e(urls,"sssssssssssssssss");
                dialog.dismiss();
            }
        });
        Dialog.create().show();
    }
    private void checkPermission() {
        AndPermission.with(this)
                .requestCode(200)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        createDialogUpdate(MainActivity.this, urls);
                    }
                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                    }
                })
                .start();
    }



    /**
     * 解析JSON
     */
    private void getJSON() {
        // 子线程访问，耗时操作
        String url = "http://172.16.52.31:8080/updata.json";
        OkHttpClient builder = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        builder.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MyBean myBean = gson.fromJson(string, MyBean.class);
                        urls = myBean.getUrl();
                        versionCode = myBean.getVersionCode();
                        Logger.e(urls,"url");
                        // 版本判断
                        if (versionCode > getCode()) {
                            // 提示更新
                            checkPermission();
                        } else {
                            // 不更新，跳转到主页
                        }
                    }
                });
            }
        });
    }


    /**
     * 获取versionCode
     */
    private int getCode() {
        // PackageManager管理器
        PackageManager pm = getPackageManager();
        // 获取相关信息
        try {
            packageInfo = pm.getPackageInfo(getPackageName(), 0);
            // 版本号
            int version = packageInfo.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 获取APP版本号
     *
     * @return
     */
    private String getAppVersion() {
        try {
            //PackageManager管理器
            PackageManager pm = getPackageManager();
            //获取相关信息
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //版本名称
            String name = packageInfo.versionName;
            //版本号
            int version = packageInfo.versionCode;

            Log.i("版本信息", "版本名称：" + name + "版本号" + version);
            return name;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //如果出现异常抛出null
        return null;
    }

    /**
     * 跳转主页面
     */
    private void goHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}