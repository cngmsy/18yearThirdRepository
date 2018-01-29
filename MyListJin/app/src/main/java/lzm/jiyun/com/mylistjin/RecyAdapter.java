package lzm.jiyun.com.mylistjin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo on 2018/1/29.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.Myhodel> {
    private List<ShiTiLei.DataBean> list;
    private Context context;
    public RecyclerView recyclerView;

    public RecyAdapter(List<ShiTiLei.DataBean> list, Context context, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public Myhodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_login, parent, false);
        Myhodel myhodel = new Myhodel(inflate);
        return myhodel;
    }

    @Override
    public void onBindViewHolder(final Myhodel holder, final int position) {
        holder.te_1.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getThumb()).into(holder.img_1);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // 滚动状态变化时回调
                //SCROLL_STATE_IDLE(空闲状态)
                //SCROLL_STATE_FLING（滚动状态）
                //SCROLL_STATE_TOUCH_SCROLL（触摸后的状态）
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    Glide.with(context).load(list.get(position).getThumb()).into(holder.img_1);
                    holder.img_1.setVisibility(View.VISIBLE);
                }else{
                    holder.img_1.setImageResource(0);
                    holder.img_1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滚动时回调
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myhodel extends RecyclerView.ViewHolder {
        private ImageView img_1;
        private TextView te_1;

        public Myhodel(View itemView) {
            super(itemView);
            te_1=(TextView)itemView.findViewById(R.id.te_1);
            img_1=(ImageView)itemView.findViewById(R.id.img_1);
        }
    }
}