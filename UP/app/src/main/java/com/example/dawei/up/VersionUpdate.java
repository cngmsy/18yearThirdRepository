package com.example.dawei.up;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.example.dawei.up.utils.SdcardUtils;
import com.example.dawei.up.utils.Utils;

import java.io.File;

/**
 * Created by Administrator on 2018/1/25.
 */

public class VersionUpdate {
    private String APP_NAME = Utils.getAppName();
    File downloadFile;
    private SdcardUtils sdcardUtils;
    private final String PACKAGE_NAME = MyApp.getMyApplicationContext().getPackageName();


    public static VersionUpdate newInstance() {
        return new VersionUpdate();
    }

    private VersionUpdate() {
    }

    //下载部分
    public void gotoUpdate(Context context, String url) {
//        String url="http://172.16.52.31:8080/appup.apk";
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