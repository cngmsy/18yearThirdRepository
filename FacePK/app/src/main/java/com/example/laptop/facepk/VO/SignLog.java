package com.example.laptop.facepk.VO;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by laptop on 2018/1/24.
 */

public class SignLog extends BmobObject implements Serializable {
    private String user_name,telephone,faceToken,object_id;
    private long time;
    private float confidence;

    public SignLog(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        telephone = preferences.getString("username","default");
    }

    public SignLog(String user_name, long time, float confidence) {
        this.user_name = user_name;
        this.time = time;
        this.confidence = confidence;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }
}
