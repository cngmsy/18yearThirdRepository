package com.example.xingge.mvp_11a.model.biz;

import com.example.xingge.mvp_11a.net.HttpFactroy;
import com.example.xingge.mvp_11a.net.IHttp;

/**
 * Created by xingge on 2017/7/11.
 */

public interface BaseModel {
    public static IHttp iHttp = HttpFactroy.create();
}
