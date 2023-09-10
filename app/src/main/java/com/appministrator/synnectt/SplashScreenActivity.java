package com.appministrator.synnectt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;

    Intent splashClose;

    final private static int SPLASH_SCREEN_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_splash_screen);

        if(fAuth.getCurrentUser()!= null) {
            splashClose = new Intent(SplashScreenActivity.this, MainActivity.class);
        } else {
            splashClose = new Intent(SplashScreenActivity.this, RegisterActivity.class);
        }

        new Handler().postDelayed(() -> {
            splashClose.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(splashClose);
            finish();
        }, SPLASH_SCREEN_TIME);
    }
}