package com.example.xingge.mvp.net;


import android.widget.ImageView;

import com.example.xingge.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by xingge on 2017/7/11.
 */

public interface IHttp {

    <T> void get(String url, MyNetWorkCallback<T> callback);
    <T> void get(String url, Map<String,String> params, MyNetWorkCallback<T> callback);
    <T> void get(String url, Map<String,String> params, Map<String,String> headers,MyNetWorkCallback<T> callback);
    <T> void post(String url, Map<String,String> params, MyNetWorkCallback<T> callback);
    void upload(String url,String path,Map<String, String> params,String fileType, MediaType MEDIA_TYPE,MyNetWorkCallback<String> callback);
    void download();
    void loadImage(String url, ImageView imageView);

}
