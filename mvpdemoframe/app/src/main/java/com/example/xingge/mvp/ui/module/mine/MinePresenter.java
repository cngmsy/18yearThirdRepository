package com.example.xingge.mvp.ui.module.mine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.xingge.mvp.config.Keys;
import com.example.xingge.mvp.model.biz.IMineModel;
import com.example.xingge.mvp.model.biz.MineModeImpl;
import com.example.xingge.mvp.model.entity.Register;
import com.example.xingge.mvp.net.callback.MyNetWorkCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xingge on 2017/7/27.
 */

public class MinePresenter implements MineContract.Presenter {

    private MineContract.View mineView;
    private IMineModel mineModel;
    private String jsessionid;
    public MinePresenter(MineContract.View mineView){
        this.mineView = mineView;
        this.mineView.setPresenter(this);
        this.mineModel = new MineModeImpl();
    }

    @Override
    public void start() {
        mineModel.loadImgCode(new MyNetWorkCallback<Bundle>() {
            @Override
            public void onSuccess(Bundle bundle) {
                jsessionid = bundle.getString(Keys.JSESSIONID);
                byte[] byteArray = bundle.getByteArray(Keys.IMGCODE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mineView.showImgCode(bitmap);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public boolean checkEmail(String emailAddress) {

        if(emailAddress == null || "".equals(emailAddress)){
            mineView.showEmailTips("邮箱不能为空");
            return false;
        }
        String regEx = "/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$/";
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);
        // 字符串是否与正则表达式相匹配
        boolean matches = matcher.matches();
        if(!matches){
            mineView.showEmailTips("邮箱格式不正确");
            return false;
        }

        return true;

    }

    @Override
    public boolean checkPwd(String pwd) {
        return true;
    }

    @Override
    public boolean checkImgCode(String imgCode) {
        return true;
    }


    @Override
    public void register(String mailAdd, String passWd, String verificationCode) {

//        if(!checkEmail(mailAdd))
//            return;
        if(!checkPwd(passWd))
            return;
        if(!checkImgCode(verificationCode))
            return;
        mineModel.register(mailAdd, passWd, verificationCode, jsessionid, new MyNetWorkCallback<Register>() {
            @Override
            public void onSuccess(Register register) {

                String msg = register.getMsg();
                if("success".equals(msg)){
                    mineView.toLogin();
                }
                mineView.showMessage(msg);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                mineView.showMessage(errorMsg);
            }
        });
    }
}
