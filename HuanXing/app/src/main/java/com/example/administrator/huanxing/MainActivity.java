package com.example.administrator.huanxing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText username;
    private EditText userpassword;
    private Button login;
    private Button zhuce;
    private ProgressDialog progressDialog;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    progressDialog.dismiss();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Log.e("main", "登录聊天服务器成功！");
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case 2:
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (EMClient.getInstance().isLoggedInBefore()){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        initView();


    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        userpassword = (EditText) findViewById(R.id.userpassword);
        login = (Button) findViewById(R.id.login);
        zhuce = (Button) findViewById(R.id.zhuce);
         progressDialog=new ProgressDialog(this);
        login.setOnClickListener(this);
        zhuce.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                progressDialog.show();
            submit();
                break;
            case R.id.zhuce:

                break;
        }
    }

    private void submit() {
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "usernameString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String userpasswordString = userpassword.getText().toString().trim();
        if (TextUtils.isEmpty(userpasswordString)) {
            Toast.makeText(this, "userpasswordString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        //登录
        EMClient.getInstance().login(usernameString, userpasswordString, new EMCallBack() {
            @Override
            public void onSuccess() {
                Message message = new Message();
                message.what=1;
                handler.sendMessage(message);
            }

            @Override
            public void onError(int i, String s) {
                Message message = new Message();
                message.what=2;
                handler.sendMessage(message);
                Log.e("main", "登录聊天服务器失败！"+i+":"+s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }
}
