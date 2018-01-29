package com.example.xingge.mvp.base;

/**
 * Created by xingge on 2017/7/11.
 */

public interface BaseView<T> {

    void showProgress();
    void dimissProgress();
    void showMessage(String msg);
    void setPresenter(T t);
}
