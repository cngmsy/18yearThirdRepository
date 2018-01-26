package lzm.jiyun.com.myaidls;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lenovo on 2018/1/26.
 */

public class MyService extends Service {
    private String serv="service";
    IBinder binder= new IMyAidlInterface.Stub() {
        @Override
        public String basicTypes(String aString) throws RemoteException {
            Log.i(serv, aString );
            return "我是Service";
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(serv, "onCreat");
    }
}
