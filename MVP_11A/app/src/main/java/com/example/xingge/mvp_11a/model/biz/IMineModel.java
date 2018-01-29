package com.example.xingge.mvp_11a.model.biz;

import android.os.Bundle;

import com.example.xingge.mvp_11a.model.entity.Register;
import com.example.xingge.mvp_11a.net.callback.MyNetWorkCallback;

import java.io.UnsupportedEncodingException;

/**
 * Created by xingge on 2017/7/27.
 */

public interface IMineModel extends BaseModel{

    void loadImgCode(MyNetWorkCallback<Bundle> callback);
    void register(String mailAdd,String passWd,String verificationCode,String cookie,MyNetWorkCallback<Register> callback);
}
