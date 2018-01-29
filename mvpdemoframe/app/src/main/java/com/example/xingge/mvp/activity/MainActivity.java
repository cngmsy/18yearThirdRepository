package com.example.xingge.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xingge.mvp.R;
import com.example.xingge.mvp.base.BaseActivity;
import com.example.xingge.mvp.ui.module.home.HomeFragment;
import com.example.xingge.mvp.ui.module.home.HomePresenter;
import com.example.xingge.mvp.ui.module.livechina.LiveChinaFragment;
import com.example.xingge.mvp.ui.module.mine.MineFragment;
import com.example.xingge.mvp.ui.module.pandaculture.PandaCultureFragment;
import com.example.xingge.mvp.ui.module.pandaeye.PandaEyeFragment;
import com.example.xingge.mvp.widget.manager.ToastManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 这个框架可以使用
 * @author 没有做fragment 栈管理 所以有时候退出的时候会出现叠影
 */
public class MainActivity extends BaseActivity {



    @BindView(R.id.iconImg)
    ImageView iconImg;
    @BindView(R.id.personImg)
    ImageView personImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.hudongImg)
    ImageView hudongImg;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.homePage)
    RadioButton homePage;
    @BindView(R.id.homePandaLive)
    RadioButton homePandaLive;
    @BindView(R.id.homeRollVideo)
    RadioButton homeRollVideo;
    @BindView(R.id.homePandaBroadcast)
    RadioButton homePandaBroadcast;
    @BindView(R.id.homeLiveChina)
    RadioButton homeLiveChina;
    @BindView(R.id.homeBottomGroup)
    RadioGroup homeBottomGroup;

    private long lastTime;//上一次点击back键的时间毫秒数
    public static final int HOMETYPE = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        HomeFragment homeFragment = (HomeFragment) changeFragment(HomeFragment.class, R.id.container, true, null, false);
        new HomePresenter(homeFragment);

    }


    @OnClick({R.id.personImg, R.id.hudongImg, R.id.homePage, R.id.homePandaLive, R.id.homeRollVideo, R.id.homePandaBroadcast, R.id.homeLiveChina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personImg:
                break;
            case R.id.hudongImg:
                break;
            case R.id.homePage:
                showTitle(null, HOMETYPE);
                changeFragment(HomeFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homePandaLive:
                showTitle("熊猫直播", 0);
                changeFragment(MineFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homeRollVideo:
                showTitle("熊猫文化", 0);
                changeFragment(PandaCultureFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homePandaBroadcast:
                showTitle("熊猫观察", 0);
                changeFragment(PandaEyeFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homeLiveChina:
                showTitle("直播中国", 0);
                changeFragment(LiveChinaFragment.class, R.id.container, true, null, false);
                break;
        }
    }

    /**
     * 显示标题的方法
     *
     * @param title 显示的标题
     * @param type  1代表首页
     */
    private void showTitle(String title, int type) {
        if (type == HOMETYPE) {
            iconImg.setVisibility(View.VISIBLE);
            titleTv.setVisibility(View.GONE);
            hudongImg.setVisibility(View.VISIBLE);
        } else {
            titleTv.setText(title);
            iconImg.setVisibility(View.GONE);
            titleTv.setVisibility(View.VISIBLE);
            hudongImg.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTime < 2000) {
            finish();
        } else {
            ToastManager.show("再按一次退出应用");
            lastTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
