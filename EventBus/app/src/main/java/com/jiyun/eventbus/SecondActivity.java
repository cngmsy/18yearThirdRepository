package com.jiyun.eventbus;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends Activity {
    private Button btn_FirstEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_FirstEvent = (Button) findViewById(R.id.btn_first_event);

        btn_FirstEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new FirstEvent("FirstEvent btn clicked"));
            }
        });


    }

}
