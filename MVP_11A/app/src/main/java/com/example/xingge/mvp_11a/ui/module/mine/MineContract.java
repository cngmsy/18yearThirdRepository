package com.example.xingge.mvp_11a.ui.module.mine;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.xingge.mvp_11a.base.BasePresenter;
import com.example.xingge.mvp_11a.base.BaseView;
import com.example.xingge.mvp_11a.net.callback.MyNetWorkCallback;

/**
 * Created by xingge on 2017/7/27.
 */

public interface MineContract {

    interface View extends BaseView<Presenter>{

        void showEmailTips(String msg);
        void hideEmailTips();
        void showPwdTips(String msg);
        void hidePwdTips();
        void showImgCode(Bitmap bitmap);
        void toLogin();

    }

    interface Presenter extends BasePresenter{
        boolean checkEmail(String emailAddress);
        boolean checkPwd(String pwd);
        boolean checkImgCode(String imgCode);
        void register(String mailAdd,String passWd,String verificationCode);

    }
}
