package com.example.lenovo.android_socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private EditText show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (EditText) findViewById(R.id.show);

        new Thread(new Runnable() {
            @Override
            public void run() {
                conn();
            }
        }).start();

    }

    private void conn() {
        try {

            Socket socket = new Socket("172.16.52.1", 9000);
            //设置10秒之后即认为是超时
            socket.setSoTimeout(10000);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String line = br.readLine();
            Log.e("UnknownHost", "来自服务器的数据"+line);
            show.setText("来自服务器的数据："+line);
            br.close();
            socket.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            Log.e("UnknownHost", "来自服务器的数据");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IOException", "来自服务器的数据");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
