package com.example.xingge.mvp.ui.module.livechina;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.xingge.mvp.R;
import com.example.xingge.mvp.activity.Main2Activity;
import com.example.xingge.mvp.base.BaseFragment;

/**
 * Created by xingge on 2017/7/11.
 * 点击按钮上传图片
 *
 */

public class LiveChinaFragment extends BaseFragment{


    @Override
    protected int getLayoutId() {
        return R.layout.livechina_fragment;
    }

    @Override
    protected void init(View view) {
        Button upLoad= (Button) view.findViewById(R.id.upload);
        upLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Main2Activity.class));
            }
        });
    }

    @Override
    protected void loadData() {


    }



}
