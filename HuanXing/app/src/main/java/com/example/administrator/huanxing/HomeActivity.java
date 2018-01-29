package com.example.administrator.huanxing;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.huanxing.fragment.FriendsFragment;
import com.example.administrator.huanxing.fragment.MessageFragment;
import com.example.administrator.huanxing.fragment.MyFragment;
import com.hyphenate.chat.EMClient;

import static com.example.administrator.huanxing.R.id.imageView;


public class HomeActivity extends AppCompatActivity {

    private FrameLayout frame;
    private RadioButton message;
    private RadioButton friends;
    private RadioButton my;
    private RadioGroup radio;
    private FriendsFragment frameFragment;
    private MessageFragment messageFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        int unreadMsgsCount = EMClient.getInstance().chatManager().getUnreadMsgsCount();
        Toast.makeText(this, unreadMsgsCount+"", Toast.LENGTH_SHORT).show();
        initView();
        setListener();

    }


    private void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        message = (RadioButton) findViewById(R.id.message);
        friends = (RadioButton) findViewById(R.id.friends);
        my = (RadioButton) findViewById(R.id.my);
        radio = (RadioGroup) findViewById(R.id.radio);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
         frameFragment = new FriendsFragment();
        transaction.add(R.id.frame,frameFragment);
        transaction.commit();
    }

    private void setListener() {
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                setDelete(supportFragmentManager);
                switch (i){
                    case R.id.message:
                        if (messageFragment==null){
                             messageFragment = new MessageFragment();
                            fragmentTransaction.add(R.id.frame,messageFragment).commit();
                        }else{
                            fragmentTransaction.show(messageFragment).commit();
                        }
                        break;
                    case R.id.friends:
                        if (frameFragment==null){
                             frameFragment = new FriendsFragment();
                            fragmentTransaction.add(R.id.frame,frameFragment).commit();
                        }else{
                            fragmentTransaction.show(frameFragment).commit();
                        }
                        break;
                    case R.id.my:
                        if (myFragment==null){
                             myFragment = new MyFragment();
                            fragmentTransaction.add(R.id.frame,myFragment).commit();
                        }else{
                            fragmentTransaction.show(myFragment).commit();
                        }

                        break;

                }
            }
        });
    }

    private void setDelete(FragmentManager supportFragmentManager) {
        if (messageFragment!=null){
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.hide(messageFragment).commit();
        }
        if (frameFragment!=null){
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.hide(frameFragment).commit();
        }
        if (myFragment!=null){
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.hide(myFragment).commit();
        }

    }
}
