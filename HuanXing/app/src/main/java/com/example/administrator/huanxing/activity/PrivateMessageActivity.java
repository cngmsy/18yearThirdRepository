package com.example.administrator.huanxing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.administrator.huanxing.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

public class PrivateMessageActivity extends AppCompatActivity {


    private FrameLayout private_framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);
        //new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();

        initView();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        //传入参数
        Bundle args = new Bundle();
       // args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        args.putString(EaseConstant.EXTRA_USER_ID, username);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.private_framelayout, chatFragment).commit();


    }

    private void initView() {
        private_framelayout = (FrameLayout) findViewById(R.id.private_framelayout);
    }
}
