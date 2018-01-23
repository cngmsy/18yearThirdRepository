package com.example.lenovo.fang_qq_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lenovo.fang_qq_login.SQL.MySql;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static com.example.lenovo.fang_qq_login.R.id.phone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;
    private EditText name;
    private EditText pass;
    private Button login;
    private LinearLayout edit_text;
    Handler handler = new Handler();
    private TextView text;
    private CheckBox jizhuPass;
    private CheckBox zidonglogin;
    private SharedPreferences zhuce;
    private MySql mySql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySql = new MySql(getApplicationContext());
        zhuce = getSharedPreferences("zhuce", 0);
        initView();
        handler.postDelayed(runnable, 4000);
        videoView.start();
        getTiem();

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            edit_text.setVisibility(View.VISIBLE);

        }
    };

    private void getTiem() {
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }


    private void initView() {
        //获取窗口，设置全屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
//        Uri.parse("android.resource://" + getPackageName() + "/" +
        String url = "android.resource://" + getPackageName() + "/" + R.raw.login;
        Uri parse = Uri.parse(url);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(parse);
        name = (EditText) findViewById(R.id.name);
        name.setOnClickListener(this);
        pass = (EditText) findViewById(R.id.pass);
        pass.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        edit_text = (LinearLayout) findViewById(R.id.edit_text);
        edit_text.setVisibility(View.GONE);
        text = (TextView) findViewById(R.id.text);

        jizhuPass = (CheckBox) findViewById(R.id.jizhuPass);
        jizhuPass.setOnClickListener(this);
        zidonglogin = (CheckBox) findViewById(R.id.zidonglogin);
        zidonglogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Intent in = new Intent(MainActivity.this, HomeActivity.class);
                if (zhuce != null) {
                    String Name = name.getText().toString();
                    String Pass = pass.getText().toString();
                    String NameStr = zhuce.getString("name", "");
                    String PassStr = zhuce.getString("pass", "");

                    startActivity(in);
                    finish();
                } else {
                    Toast.makeText(this, "请先注册！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void onClicks(View view) {
        //SMSSDK.initSDK(this, "f3fc6baa9ac4", "7f3dedcb36d92deebcb373af921d635a");
        SMSSDK.initSDK(this, "1995457e5870a", "c8d1d355f7b955b4477f7383e828ca18");
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                super.afterEvent(i, i1, o);
            }
        });
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    Log.v("result", result + "");
                    String shoujihao = (String) phoneMap.get("shoujihao");

                    //判断手机号是否已被占用
                    Cursor cursorPhone = mySql.getUserbyPhone(shoujihao, new String[]{"phone"});
                    if (cursorPhone.getCount() != 0) {
                        Toast.makeText(MainActivity.this, "手机号已注册,请直接登陆", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, zhuceActivity.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                    }
                }
            }
        });
        registerPage.show(getApplicationContext());
    }

    private void submit() {
        // validate
        String nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "输入账号！", Toast.LENGTH_SHORT).show();
            return;
        }

        String passString = pass.getText().toString().trim();
        if (TextUtils.isEmpty(passString)) {
            Toast.makeText(this, "输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
