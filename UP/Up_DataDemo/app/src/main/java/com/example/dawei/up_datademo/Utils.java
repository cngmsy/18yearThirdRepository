package com.example.dawei.up_datademo;

/**
 * Created by Administrator on 2018/1/28.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流转换Stream的工具栏
 *
 * @author LGL
 *
 */
public class Utils {

    // 对外发放
    public static String toStream(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 长度
        int length = 0;
        byte[] buffer = new byte[1024];
        // -1代表读完了
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        // 读完关闭
        in.close();
        out.close();
        // 我们把返回的数据转换成String
        return out.toString();
    }


}