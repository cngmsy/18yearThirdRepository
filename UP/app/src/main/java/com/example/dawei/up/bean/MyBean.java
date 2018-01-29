package com.example.dawei.up.bean;

/**
 * Created by Administrator on 2018/1/26.
 */

public class MyBean {

    /**
     * versionName : 2.0
     * versionCode : 1
     * content : 修复多项bug!
     * url : http://172.16.52.1/LYD/app-release-11-55.apk
     */

    private String versionName;
    private int versionCode;
    private String content;
    private String url;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
