package com.covid19app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.covid19app.ui.login.LoginActivity;

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
                boolean isAdminLogin = pref.getBoolean("is_login_admin", false);
                if (isLogin) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else if (isAdminLogin) {
                    startActivity(new Intent(SplashActivity.this, AdminListActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 2000);


    }
}
