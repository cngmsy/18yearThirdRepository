#视屏登录界面
 //获取窗口，设置全屏
```
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
    ```
效果图gif
![](Gif_qq.gif)




