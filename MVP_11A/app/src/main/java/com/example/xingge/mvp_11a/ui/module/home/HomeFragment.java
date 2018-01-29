package com.example.xingge.mvp_11a.ui.module.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.xingge.mvp_11a.R;
import com.example.xingge.mvp_11a.base.BaseFragment;
import com.example.xingge.mvp_11a.model.entity.PandaHome;
import com.example.xingge.mvp_11a.widget.manager.ToastManager;
import com.example.xingge.mvp_11a.widget.view.CustomDialog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xingge on 2017/7/11.
 */

public class HomeFragment extends BaseFragment implements XRecyclerView.LoadingListener,HomeContract.View{


    @BindView(R.id.homeRecyclerView)
    XRecyclerView homeRecyclerView;
    private HomeContract.Presenter presenter;
    private List<Object> datas;
    private HomeAdapter homeAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void init(View view) {
        this.datas = new ArrayList<Object>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecyclerView.setLayoutManager(manager);
        homeRecyclerView.setPullRefreshEnabled(true);
        homeRecyclerView.setLoadingMoreEnabled(false);
        homeRecyclerView.setLoadingListener(this);
        homeAdapter = new HomeAdapter(getActivity(),datas);
        homeRecyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void loadData() {
        presenter.start();
    }


    @OnClick(R.id.homeRecyclerView)
    public void onViewClicked() {
    }

    @Override
    public void onRefresh() {

        presenter.start();
    }

    @Override
    public void onLoadMore() {

    }

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
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHomeListData(PandaHome pandaHome) {
        datas.clear();
        datas.add(pandaHome.getData().getBigImg());
        datas.add(pandaHome.getData().getArea());
        homeAdapter.notifyDataSetChanged();
        homeRecyclerView.refreshComplete();
//        datas.add(pandaHome.getData().getPandaeye());
//        datas.add(pandaHome.getData().getPandalive());
//        datas.add(pandaHome.getData().getWalllive());
//        datas.add(pandaHome.getData().getChinalive());
//        datas.add(pandaHome.getData().getInteractive());
//        datas.add(pandaHome.getData().getCctv());
//        datas.add(pandaHome.getData().getList());
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }
}
