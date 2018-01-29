package com.example.xingge.mvp_11a.model.biz;

import com.example.xingge.mvp_11a.model.entity.PandaHome;
import com.example.xingge.mvp_11a.net.callback.MyNetWorkCallback;

/**
 * Created by xingge on 2017/7/26.
 */

public interface IPandaHomeModel extends BaseModel {

    void loadHomeList(MyNetWorkCallback<PandaHome> callback);
}
