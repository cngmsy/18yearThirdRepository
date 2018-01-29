package lzm.jiyun.com.mylistjin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.recy)
    RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        //okhttp网络请求
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("http://mapi.univs.cn/mobile/index.php?app=mobile&type=mobile&catid=11&controller=content&action=slide&time=0").build();
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ShiTiLei tiLei = gson.fromJson(string, ShiTiLei.class);
                        List<ShiTiLei.DataBean> data = tiLei.getData();
                        //设置recycleView列表的竖向排列
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recy.setLayoutManager(layoutManager);
                        //设置RecycleView的适配器
                        RecyAdapter recyAdapter = new RecyAdapter(data, MainActivity.this, recy);
                        recy.setAdapter(recyAdapter);
                    }
                });
            }
        });
    }
}
