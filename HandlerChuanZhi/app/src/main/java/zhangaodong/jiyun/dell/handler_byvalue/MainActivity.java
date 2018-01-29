package zhangaodong.jiyun.dell.handler_byvalue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Bu;
    private MyHandler myHandler;
    private Handler handler;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
          // 得到Application
        myHandler = (MyHandler) getApplication();
        handler = new Handler(){
            public String yyy;
         // 接收发送过来的消息
            @Override
            public void handleMessage(Message msg) {
                Log.e("-------------",yyy + "");
            }
        };
    }
    private void initView() {
        Bu = (Button) findViewById(R.id.Bu);
        Bu.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Bu:
                // 将Handler  共享到Application
               myHandler.setHandler(handler);
                Intent intent  = new Intent(MainActivity.this,MainActivity_Two.class);
                startActivity(intent);
                break;
        }
    }
}
