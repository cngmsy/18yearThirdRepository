package com.example.xingge.mvp_11a.model.biz;

import android.os.Bundle;

import com.example.xingge.mvp_11a.config.Urls;
import com.example.xingge.mvp_11a.model.entity.Register;
import com.example.xingge.mvp_11a.net.OkHttpUtils;
import com.example.xingge.mvp_11a.net.callback.MyNetWorkCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingge on 2017/7/27.
 */

public class MineModeImpl implements IMineModel {
    @Override
    public void loadImgCode(MyNetWorkCallback<Bundle> callback) {
        OkHttpUtils.getInstance().loadImgCode(Urls.IMGCODE,callback);
    }

    @Override
    public void register(String mailAdd, String passWd, String verificationCode, String cookie, MyNetWorkCallback<Register> callback) {

        String addons = null;
        String userAgent = null;
        try {
            addons = URLEncoder.encode("iPanda.Android", "UTF-8");
            userAgent = URLEncoder.encode("CNTV_APP_CLIENT_CNTV_MOBILE", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> params = new HashMap<>();
        params.put("mailAdd",mailAdd);
        params.put("passWd",passWd);
        params.put("verificationCode",verificationCode);
        params.put("addons", addons);

        Map<String,String> headers = new HashMap<>();
        headers.put("Referer",addons);
        headers.put("User-Agent",userAgent);
        headers.put("Cookie",cookie);

        iHttp.get(Urls.EMAILREGISTER,params,headers,callback);
    }
}
