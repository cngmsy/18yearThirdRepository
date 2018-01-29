package com.example.xingge.mvp.ui.module.home;

import com.example.xingge.mvp.base.BasePresenter;
import com.example.xingge.mvp.base.BaseView;
import com.example.xingge.mvp.model.entity.PandaHome;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomeContract {

    interface View extends BaseView<Presenter>{
        void showHomeListData(PandaHome pandaHome);
        void playVideo();
        void loadWebView();
    }

    interface Presenter extends BasePresenter{
    }

}
