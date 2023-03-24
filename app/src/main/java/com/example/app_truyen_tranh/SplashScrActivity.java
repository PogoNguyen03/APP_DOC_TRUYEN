package com.example.app_truyen_tranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.app_truyen_tranh.ui.LoginActivity;

public class SplashScrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scr);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(SplashScrActivity.this, LoginActivity.class));
                    finish();
                } catch (Exception e) {
                }
            }
        };thread.start();
    }
}