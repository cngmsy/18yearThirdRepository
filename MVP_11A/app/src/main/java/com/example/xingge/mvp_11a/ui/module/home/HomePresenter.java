package com.example.xingge.mvp_11a.ui.module.home;

import com.example.xingge.mvp_11a.model.biz.IPandaHomeModel;
import com.example.xingge.mvp_11a.model.biz.PandaHomeModelImpl;
import com.example.xingge.mvp_11a.model.entity.PandaHome;
import com.example.xingge.mvp_11a.net.callback.MyNetWorkCallback;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private IPandaHomeModel homeModel;
    public HomePresenter(HomeContract.View homeView){
        this.homeView = homeView;
        homeView.setPresenter(this);
        this.homeModel = new PandaHomeModelImpl();
    }

    @Override
    public void start() {
        homeView.showProgress();
        homeModel.loadHomeList(new MyNetWorkCallback<PandaHome>() {
            @Override
            public void onSuccess(PandaHome pandaHome) {
                homeView.showHomeListData(pandaHome);
                homeView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                homeView.showMessage(errorMsg);
                homeView.dimissProgress();
            }
        });
    }
}
