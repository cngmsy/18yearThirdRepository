package cm.wzh.live.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiaozhi.firework_core.FireWorkView;

import cm.wzh.live.R;

/**
 * author：Administrator on 2016/12/26 09:35
 * description:文件说明
 * version:版本
 */
public class NoFragment extends Fragment implements View.OnClickListener {

    private AnimationDrawable animationDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_nochat, null, false);
        ImageView animationIV = view.findViewById(R.id.fragment_iv);

        animationIV.setImageResource(R.drawable.animation1);
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();

        ImageView iv = view.findViewById(R.id.close);
        iv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        getActivity().finish();
    }
}
