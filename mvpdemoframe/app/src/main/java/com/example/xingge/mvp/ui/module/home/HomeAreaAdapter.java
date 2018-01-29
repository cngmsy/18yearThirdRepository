package com.example.xingge.mvp.ui.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xingge.mvp.R;
import com.example.xingge.mvp.model.entity.PandaHome;
import com.example.xingge.mvp.net.HttpFactroy;

import java.util.List;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomeAreaAdapter extends Adapter {

    private Context context;
    private List<PandaHome.DataBean.AreaBean.ListscrollBean> datas;
    private LayoutInflater inflater;
    public HomeAreaAdapter(Context context, List<PandaHome.DataBean.AreaBean.ListscrollBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_area_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Holder h = (Holder) holder;
        HttpFactroy.create().loadImage(datas.get(position).getImage(),h.img);
        h.titleTv.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titleTv;
        public Holder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.areaImg);
            titleTv = (TextView) itemView.findViewById(R.id.areaTitle);
        }
    }
}
