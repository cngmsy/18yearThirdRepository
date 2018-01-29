package com.baidu.android.voicedemo.upload;

import com.baidu.android.voicedemo.util.Logger;
import com.baidu.android.voicedemo.wakeup.IWakeupListener;
import com.baidu.speech.EventListener;


/**
 * Created by fujiayi on 2017/6/20.
 */

public class UploadEventAdapter implements EventListener {

    private static final int ERROR_NONE = 0;

    private IWakeupListener listener;

    public UploadEventAdapter() {

    }

    private static final String TAG = "WakeupEventAdapter";

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        Logger.error(TAG, "name:" + name + "; params:" + params);

    }

}
