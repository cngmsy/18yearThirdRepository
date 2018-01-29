package com.example.xingge.mvp.model.biz;

import com.example.xingge.mvp.net.HttpFactroy;
import com.example.xingge.mvp.net.IHttp;

/**
 * Created by xingge on 2017/7/11.
 */

public interface BaseModel {
    public static IHttp iHttp = HttpFactroy.create();
}
