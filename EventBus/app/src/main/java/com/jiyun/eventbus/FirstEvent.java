package com.jiyun.eventbus;

/**
 * Created by Administrator on 2018/1/24.
 */

public class FirstEvent {

    private String mMsg;
    public FirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
