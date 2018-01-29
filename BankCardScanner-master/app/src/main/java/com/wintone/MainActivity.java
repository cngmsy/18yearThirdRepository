package com.wintone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wintone.smartvision_bankCard.ScanCamera;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoScan(View view) {
        Intent intentTack = new Intent(this, ScanCamera.class);
        startActivity(intentTack);
    }
}
