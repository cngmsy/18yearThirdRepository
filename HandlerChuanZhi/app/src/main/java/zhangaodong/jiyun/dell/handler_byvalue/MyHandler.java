package zhangaodong.jiyun.dell.handler_byvalue;

import android.app.Application;
import android.os.Handler;

/**
 * Created by dell on 2018/1/24.
 */

public class MyHandler extends Application{
    private Handler handler = null;
     // 思路就是我们将一个Handler 共享 设置Set 和GET  方法。


    //set方法

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    //get方法
    public Handler getHandler(){
        return handler;
    }
}
