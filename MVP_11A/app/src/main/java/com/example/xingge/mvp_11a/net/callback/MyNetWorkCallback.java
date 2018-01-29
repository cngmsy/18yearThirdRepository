package com.example.xingge.mvp_11a.net.callback;

/**
 * Created by xingge on 2017/7/11.
 */

public interface MyNetWorkCallback<T> {

    void onSuccess(T t);
    void onError(int errorCode,String errorMsg);

}
