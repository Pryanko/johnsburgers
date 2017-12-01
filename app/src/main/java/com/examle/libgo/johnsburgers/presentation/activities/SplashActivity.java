package com.examle.libgo.johnsburgers.presentation.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.examle.libgo.johnsburgers.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(savedInstanceState == null) {
            Intent i = new Intent(this, HeadActivity.class);
            startActivity(i);
            finish();
        }
    }
}
