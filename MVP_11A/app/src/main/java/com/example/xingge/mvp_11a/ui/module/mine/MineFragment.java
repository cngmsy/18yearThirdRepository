package com.example.xingge.mvp_11a.ui.module.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xingge.mvp_11a.R;
import com.example.xingge.mvp_11a.activity.LoginActivity;
import com.example.xingge.mvp_11a.base.BaseFragment;
import com.example.xingge.mvp_11a.widget.manager.ToastManager;
import com.example.xingge.mvp_11a.widget.view.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xingge on 2017/7/27.
 */

public class MineFragment extends BaseFragment implements MineContract.View {
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.hint_email)
    TextView hintEmail;
    @BindView(R.id.et_emailpsw)
    EditText etEmailpsw;
    @BindView(R.id.et_emailquerenpsw)
    EditText etEmailquerenpsw;
    @BindView(R.id.et_emailpictureyanzheng)
    EditText etEmailpictureyanzheng;
    @BindView(R.id.email_image)
    ImageView emailImage;
    @BindView(R.id.bt_emailregister)
    Button btEmailregister;
    Unbinder unbinder;
    private MineContract.Presenter presenter;

    @Override
    public void showProgress() {

        CustomDialog.show(getActivity());
    }

    @Override
    public void dimissProgress() {

        CustomDialog.dimiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastManager.show(msg);
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showEmailTips(String msg) {

        hintEmail.setVisibility(View.VISIBLE);
        hintEmail.setText(msg);

    }

    @Override
    public void hideEmailTips() {

        hintEmail.setVisibility(View.GONE);
    }

    @Override
    public void showPwdTips(String msg) {

    }

    @Override
    public void hidePwdTips() {

    }

    @Override
    public void showImgCode(Bitmap bitmap) {

        emailImage.setImageBitmap(bitmap);

    }

    @Override
    public void toLogin() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.email_register_fragment;
    }

    @Override
    protected void init(View view) {
        new MinePresenter(this);
    }

    @Override
    protected void loadData() {

        presenter.start();
    }


    @OnClick(R.id.bt_emailregister)
    public void onViewClicked() {
        presenter.register(etEmail.getText().toString().trim(),
                etEmailpsw.getText().toString().trim(),
                etEmailpictureyanzheng.getText().toString().trim());
    }
}
