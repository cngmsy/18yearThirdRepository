package lzm.jiyun.com.myaidls;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    String activity = "Activity";
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            asInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(activity, "连接失败");
        }
    };
    @InjectView(R.id.butt)
    Button butt;
    private IMyAidlInterface asInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @OnClick(R.id.butt)
    public void onViewClicked() {
        try {
           String ss = asInterface.basicTypes("我是Activtiy");
            Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
