package com.example.administrator.huanxing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.huanxing.MainActivity;
import com.example.administrator.huanxing.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener {


    private FragmentActivity activity;
    private Button my_btn_exit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        activity = getActivity();
        initView(inflate);

        return inflate;
    }

    private void initView(View inflate) {
        my_btn_exit = (Button) inflate.findViewById(R.id.my_btn_exit);

        my_btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_btn_exit:
                //退出当前账号
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.e("MyFragment","退出成功");
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e("MyFragment","退出失败"+i+s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;
        }
    }
}
