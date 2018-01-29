package com.example.dawei.up_datademo;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 更新
    private static final int UPDATE_YES = 1;
    // 不更新
    private static final int UPDATE_NO = 2;
    // URL错误
    private static final int URL_ERROR = 3;
    // 没有网络
    private static final int IO_ERROR = 4;
    // 数据异常
    private static final int JSON_ERROR = 5;

    private TextView tv_version;
    private PackageInfo packageInfo;

    private JSONObject jsonObject;
    private String versionName;
    private int versionCode;
    private String content;
    private String url;

    private TextView tv_pro;

    // 升级提示框
    private CustomDialog updateDialog;
    private TextView dialog_update_content;
    private TextView dialog_confrim;
    private TextView dialog_cancel;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case UPDATE_YES:
                    showUpdateDialog();
                    break;
                case UPDATE_NO:
                    goHome();
                    break;
                case URL_ERROR:
                    Toast.makeText(getApplicationContext(), "地址错误",
                            Toast.LENGTH_LONG).show();
                    goHome();
                    break;
                case IO_ERROR:
                    Toast.makeText(getApplicationContext(), "请检查网络",
                            Toast.LENGTH_LONG).show();
                    goHome();
                    break;
                case JSON_ERROR:
                    Toast.makeText(getApplicationContext(), "Json解析错误",
                            Toast.LENGTH_LONG).show();
                    goHome();
                    break;
                // 就算你报任何错，爸比是爱你的，依然让你进主页面
            }
        }
    };
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getJSON();
    }

    /**
     * 初始化控件
     *
     * @author LGL
     */
    private void initView() {

        tv_pro = (TextView) findViewById(R.id.tv_pro);

        tv_version = (TextView) findViewById(R.id.tv_version);
        // 设置版本号
        tv_version.setText("版本号：" + getAppVersion());
    }

    /**
     * 获取APP版本号
     *
     * @return
     */
    private String getAppVersion() {
        try {
            // PackageManager管理器
            PackageManager pm = getPackageManager();
            // 获取相关信息
            packageInfo = pm.getPackageInfo(getPackageName(), 0);
            // 版本名称
            String name = packageInfo.versionName;
            // 版本号
            int version = packageInfo.versionCode;

            Log.i("版本信息", "版本名称：" + name + "版本号" + version);

            return name;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 如果出现异常抛出
        return "无法获取";
    }

    /**
     * 解析JSON
     */
    private void getJSON() {
        // 子线程访问，耗时操作
        new Thread() {
            public void run() {

                Message msg = Message.obtain();

                // 开始访问网络的时间
                long startTime = System.currentTimeMillis();

                try {
                    // JSON地址
                    HttpURLConnection conn = (HttpURLConnection) new URL(
                            // 模拟器一般有一个预留IP：10.0.2.2
                            "http://172.16.52.24/LYD/updata.json")
                            .openConnection();
                    // 请求方式GRT
                    conn.setRequestMethod("GET");
                    // 连接超时
                    conn.setConnectTimeout(5000);
                    // 响应超时
                    conn.setReadTimeout(3000);
                    // 连接
                    conn.connect();
                    // 获取请求码
                    int responseCode = conn.getResponseCode();
                    // 等于200说明请求成功
                    if (responseCode == 200) {
                        // 拿到他的输入流
                        InputStream in = conn.getInputStream();
                        String stream = Utils.toStream(in);

                        Log.i("JSON", stream);
                        jsonObject = new JSONObject(stream);
                        versionName = jsonObject.getString("versionName");
                        versionCode = jsonObject.getInt("versionCode");
                        content = jsonObject.getString("content");
                        url = jsonObject.getString("url");

                        // 版本判断
                        if (versionCode > getCode()) {
                            // 提示更新
                            msg.what = UPDATE_YES;
                        } else {
                            // 不更新，跳转到主页
                            msg.what = UPDATE_NO;
                        }
                    }

                } catch (MalformedURLException e) {
                    // URL错误
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                } catch (IOException e) {
                    // 没有网络
                    e.printStackTrace();
                    msg.what = IO_ERROR;
                } catch (JSONException e) {
                    // 数据错误
                    e.printStackTrace();
                    msg.what = JSON_ERROR;
                } finally {

                    // 网络访问结束的时间
                    long endTime = System.currentTimeMillis();
                    // 计算网络用了多少时间
                    long time = endTime - startTime;

                    try {
                        if (time < 3000) {
                            // 停留三秒钟
                            Thread.sleep(3000 - time);
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    // 全部走完发消息
                    handler.sendMessage(msg);
                }
            }
        }.start();
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
     * 升级弹框
     */
    private void showUpdateDialog() {
        updateDialog = new CustomDialog(this, 0, 0, R.layout.dialog_update,
                R.style.Theme_dialog, Gravity.CENTER, 0);
        //如果他点击其他地方，不安装，我们就直接去
        updateDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                goHome();
            }
        });
        // 更新内容
        dialog_update_content = (TextView) updateDialog
                .findViewById(R.id.dialog_update_content);
        dialog_update_content.setText(content);
        // 确定更新
        dialog_confrim = (TextView) updateDialog
                .findViewById(R.id.dialog_confrim);
        dialog_confrim.setOnClickListener(this);
        // 取消更新
        dialog_cancel = (TextView) updateDialog
                .findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(this);
        updateDialog.show();
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_confrim:
                updateDialog.dismiss();
                gotoUpdate(this, url);

                break;
            case R.id.dialog_cancel:
                // 跳主页面
                goHome();
                break;
        }
    }

    /**
     * 跳转主页面
     */
    private void goHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private SdcardUtils sdcardUtils;
    private String APP_NAME = UtilsA.getAppName();
    File downloadFile;
    //下载部分
    public void gotoUpdate(Context context, String url) {
        sdcardUtils = (new SdcardUtils());
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setMimeType("application/vnd.android.package-archive");

        downloadFile = new File(sdcardUtils.getSDPATH() + "/" + PACKAGE_NAME, APP_NAME);

        request.setDestinationUri(Uri.fromFile(downloadFile));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载新版本");
        request.setVisibleInDownloadsUi(true);

        long downloadId = downloadManager.enqueue(request);
        DownCompleteReceiver downCompleteReceiver = new DownCompleteReceiver(downloadId);
        context.registerReceiver(downCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }


    public class DownCompleteReceiver extends BroadcastReceiver {
        long enqueueId;

        public DownCompleteReceiver(long enqueueId) {
            this.enqueueId = enqueueId;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            long id = intent.getExtras().getLong(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (enqueueId != id) {
                return;
            }
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(enqueueId);
            Cursor c = dm.query(query);
            if (c != null && c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                // 下载失败也会返回这个广播，所以要判断下是否真的下载成功
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    File apkFile = new File(sdcardUtils.getSDPATH() + "/" + PACKAGE_NAME + "/" + APP_NAME);
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    // 由于没有在Activity环境下启动Activity,设置下面的标签
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
                        //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                        Uri apkUri =
                                FileProvider.getUriForFile(context, PACKAGE_NAME + ".fileprovider", apkFile);
                        //添加这一句表示对目标应用临时授权该Uri所代表的文件
                        intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent1.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    } else {
                        intent1.setDataAndType(Uri.fromFile(apkFile),
                                "application/vnd.android.package-archive");
                    }
                    context.startActivity(intent1);
                }
                c.close();
            }
        }
    }
}
