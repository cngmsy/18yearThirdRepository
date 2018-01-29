package com.example.xingge.mvp_11a.ui.module.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xingge.mvp_11a.R;
import com.example.xingge.mvp_11a.model.entity.PandaHome;
import com.example.xingge.mvp_11a.net.HttpFactroy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private List<Object> datas;
    private LayoutInflater inflater;
    public static final int ITEMCOUNT = 9;//加载9种不同类型的item
    public static final int BIGIMG = 0;//代表轮播图
    public static final int AREA = 1;//代表轮播图
    public static final int PANDAEYE = 2;//代表轮播图
    public static final int PANDALIVE = 3;
    public static final int WALLLIVE = 4;//代表轮播图
    public static final int CHINALIVE = 5;//代表轮播图
    public static final int INTERACTIVE = 6;//代表轮播图
    public static final int CCTV = 7;//代表轮播图
    public static final int LIST = 8;//代表轮播图
    private Context context;
    public HomeAdapter(Context context, List<Object> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = datas.get(position);
        if (position == 0) {
            return BIGIMG;
        } else if (obj instanceof PandaHome.DataBean.AreaBean) {
            return AREA;
        } else if (obj instanceof PandaHome.DataBean.PandaeyeBean) {
            return PANDAEYE;
        } else if (obj instanceof PandaHome.DataBean.PandaliveBean) {
            return PANDALIVE;
        } else if (obj instanceof PandaHome.DataBean.WallliveBean) {
            return WALLLIVE;
        } else if (obj instanceof PandaHome.DataBean.ChinaliveBean) {
            return CHINALIVE;
        } else if (obj instanceof PandaHome.DataBean.InteractiveBean) {
            return INTERACTIVE;
        } else if (obj instanceof PandaHome.DataBean.CctvBean) {
            return CCTV;
        } else if (obj instanceof PandaHome.DataBean.ListBeanXXX) {
            return LIST;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BIGIMG:
                View bigimgView = inflater.inflate(R.layout.home_bigimg, null);
                return new BigimgHolder(bigimgView);
            case AREA:
                View areaView = inflater.inflate(R.layout.home_area, null);
                return new AreaHolder(areaView);
            case PANDAEYE:
            case PANDALIVE:
                break;
            case WALLLIVE:
                break;
            case CHINALIVE:
                break;
            case INTERACTIVE:
                break;
            case CCTV:
                break;
            case LIST:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case BIGIMG:
                BigimgHolder bigimgHolder = (BigimgHolder) holder;
                List<PandaHome.DataBean.BigImgBean> bigImgs = (List<PandaHome.DataBean.BigImgBean>) datas.get(position);
                loadBigImg(bigimgHolder,bigImgs);
                break;
            case AREA:
                AreaHolder areaHolder = (AreaHolder) holder;
                PandaHome.DataBean.AreaBean areaBean = (PandaHome.DataBean.AreaBean) datas.get(position);
                loadArea(areaHolder,areaBean);
                break;
            case PANDAEYE:
                break;
        }
    }

    private void loadBigImg(BigimgHolder holder, List<PandaHome.DataBean.BigImgBean> bigImgs){
        ViewPager viewPager = holder.banner;
        List<ImageView> imgs = new ArrayList<>();
        for (PandaHome.DataBean.BigImgBean imgBean : bigImgs){
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams params =
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            HttpFactroy.create().loadImage(imgBean.getImage(),imageView);
            imgs.add(imageView);
        }
        viewPager.setAdapter(new HomeBigImgAdapter(imgs));

    }

    private void loadArea(AreaHolder holder,PandaHome.DataBean.AreaBean areaBean){
        List<PandaHome.DataBean.AreaBean.ListscrollBean> areas = areaBean.getListscroll();
        ImageView areaIcon = holder.areaIcon;
        HttpFactroy.create().loadImage(areaBean.getImage(),areaIcon);
        RecyclerView recyclerView = holder.recyclerView;
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new HomeAreaAdapter(context,areas));
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BigimgHolder extends RecyclerView.ViewHolder {


        ViewPager banner;
        public BigimgHolder(View itemView) {
            super(itemView);
            banner = (ViewPager) itemView.findViewById(R.id.banner);
        }
    }
    class AreaHolder extends RecyclerView.ViewHolder {


        RecyclerView recyclerView;
        ImageView areaIcon;
        public AreaHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.areaRecyclerView);
            areaIcon = (ImageView) itemView.findViewById(R.id.areaIcon);
        }
    }
    class PandaeyeHolder extends RecyclerView.ViewHolder {

        public PandaeyeHolder(View itemView) {
            super(itemView);
        }
    }
}
