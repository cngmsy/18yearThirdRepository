package com.example.lenovo.android_socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by lenovo on 2018/1/22.
 */
public class Fasong {
    public static void main(String[] args) throws IOException {
        //创建一个ServerSocket,用于监听客户端socket的连接请求
        ServerSocket ss=new ServerSocket(9000);
        //采用循环不断接受来自客户端的请求,服务器端也对应产生一个Socket
        while(true){
            Socket s=ss.accept();
            OutputStream os=s.getOutputStream();
            os.write("您好，您收到了服务器的新年祝福！\n".getBytes("utf-8"));
            os.close();
            s.close();
        }
    }
}
