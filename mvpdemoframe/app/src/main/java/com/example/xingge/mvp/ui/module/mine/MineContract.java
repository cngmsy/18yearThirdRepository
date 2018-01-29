package com.example.xingge.mvp.ui.module.mine;

import android.graphics.Bitmap;

import com.example.xingge.mvp.base.BasePresenter;
import com.example.xingge.mvp.base.BaseView;

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
