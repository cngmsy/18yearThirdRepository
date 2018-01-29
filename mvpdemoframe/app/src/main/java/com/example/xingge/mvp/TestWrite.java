package com.example.xingge.mvp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/******************************************
 * 类名称：TestWrite
 * 类描述：
 *
 * @version: 1.0
 * @author: chj
 * @time: 2018/1/12
 * @email: chj294671171@126.com
 * @github: https://github.com/cngmsy
 ******************************************/
public class TestWrite {

    private static byte[] bytes;

    public static void main(String[] args) {
        String filePath="/Users/chj/persons/usefullrestore" + File.separator+"test.txt";
        //使用File.separator而不是使用/是因为每个操作系统的文件分割方式不一样
        File file= new File(filePath);
        try {
            InputStream ips=new FileInputStream(file);

            bytes = new byte[1024];
           // int[] arr=new int[10];

            int len=ips.read(bytes);
            ips.close();
            System.out.println(new String(bytes,0,len));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }



    }
}
