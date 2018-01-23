package com.example.lenovo.fang_qq_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by lenovo on 2018/1/16.
 */

public class zhuceActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText name;
    private EditText age;
    private EditText gao;
    private EditText tizhomg;
    private EditText phone;
    private RadioButton nan;
    private RadioButton nv;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_buju);
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        gao = (EditText) findViewById(R.id.gao);
        tizhomg = (EditText) findViewById(R.id.tizhomg);
        phone = (EditText) findViewById(R.id.phone);
        nan = (RadioButton) findViewById(R.id.nan);
        nv = (RadioButton) findViewById(R.id.nv);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String nameString = name.getText().toString().trim();
                SharedPreferences zhuce = getSharedPreferences("zhuce", 0);
                SharedPreferences.Editor edit = zhuce.edit();
                edit.putString("name", nameString);
                edit.commit();
                finish();
                break;

        }
    }

    private void submit() {
        // validate
        String nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "输入Name！", Toast.LENGTH_SHORT).show();
            return;
        }

        String ageString = age.getText().toString().trim();
        if (TextUtils.isEmpty(ageString)) {
            Toast.makeText(this, "输入Age！", Toast.LENGTH_SHORT).show();
            return;
        }

        String gaoString = gao.getText().toString().trim();
        if (TextUtils.isEmpty(gaoString)) {
            Toast.makeText(this, "输入身高（cm）！", Toast.LENGTH_SHORT).show();
            return;
        }

        String tizhomgString = tizhomg.getText().toString().trim();
        if (TextUtils.isEmpty(tizhomgString)) {
            Toast.makeText(this, "输入体重！", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneString = phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            Toast.makeText(this, "输入手机号！", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
