package zhangaodong.jiyun.dell.handler_byvalue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity_Two extends AppCompatActivity {

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__two);
        initView();
         //  在这里得到Application
       final  MyHandler myHandler =  (MyHandler)getApplication();
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 这里是发送消息
                Handler handler = myHandler.getHandler();
                Message message =  new Message();
                message.obj="Hello";
                handler.sendMessage(message);
                MainActivity_Two.this.finish();
            }
        });
    }

    private void initView() {
        textview = (TextView) findViewById(R.id.textview);
    }
}
