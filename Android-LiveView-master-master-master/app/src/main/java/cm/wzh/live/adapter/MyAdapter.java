package cm.wzh.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cm.wzh.live.R;
import cm.wzh.live.entity.Fly;
import cm.wzh.live.entity.Gift;

/**
 * Created by 王晓亮 on 2017/9/25.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Fly> gifts;
    private Context context;

    public MyAdapter(ArrayList<Fly> gifts, Context context) {
        this.gifts = gifts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gifts.size();
    }

    @Override
    public Object getItem(int i) {
        return gifts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_gift, null);
            viewHolder.grid_fragment_home_item_img =
                    (ImageView) view.findViewById(R.id.grid_fragment_home_item_img);
            viewHolder.grid_fragment_home_item_txt =
                    (TextView) view.findViewById(R.id.grid_fragment_home_item_txt);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.grid_fragment_home_item_img.setImageResource(catogary.getImage_source());
        viewHolder.grid_fragment_home_item_txt.setText(0+"");
        viewHolder.grid_fragment_home_item_img.setImageResource(gifts.get(i).getName());
        return view;
    }

    public class ViewHolder {
        public ImageView grid_fragment_home_item_img;
        public TextView grid_fragment_home_item_txt;
    }
}
