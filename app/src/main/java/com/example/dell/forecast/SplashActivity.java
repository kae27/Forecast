package com.example.dell.forecast;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    long delayTime;
    long time=2000L;

    @Override
    protected void onResume() {
        super.onResume();
        delayTime = time;
        handler.postDelayed(runnable, delayTime);
        time = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delayTime - (System.currentTimeMillis() - time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);// Start your app main activity
                startActivity(intent);
                finish();
            }
        };



    }
}
