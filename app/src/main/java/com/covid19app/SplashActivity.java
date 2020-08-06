package com.covid19app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final MotionLayout motion_layout = findViewById(R.id.motion_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                motion_layout.transitionToEnd();
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                boolean isLogin = pref.getBoolean("is_login", false);
                if (!isLogin) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, AdminListActivity.class));
                    finish();
                }
            }
        }, 2000);


    }
}
