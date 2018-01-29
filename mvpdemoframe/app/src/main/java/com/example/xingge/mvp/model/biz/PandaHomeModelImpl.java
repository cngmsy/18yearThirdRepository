package com.example.xingge.mvp.model.biz;

import com.example.xingge.mvp.config.Urls;
import com.example.xingge.mvp.model.entity.PandaHome;
import com.example.xingge.mvp.net.callback.MyNetWorkCallback;

/**
 * Created by xingge on 2017/7/26.
 */

public class PandaHomeModelImpl implements IPandaHomeModel {

    @Override
    public void loadHomeList(MyNetWorkCallback<PandaHome> callback) {
        iHttp.get(Urls.PANDAHOME,null,callback);
    }
}
