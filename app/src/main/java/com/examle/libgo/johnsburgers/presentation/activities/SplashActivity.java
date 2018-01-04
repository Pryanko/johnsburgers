package com.examle.libgo.johnsburgers.presentation.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.examle.libgo.johnsburgers.R;
public class SplashActivity extends AppCompatActivity {


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(savedInstanceState == null) {
            imageView = (ImageView) findViewById(R.id.imageView);
            Intent i = new Intent(this, HeadActivity.class);
            imageView.animate().alpha(1.0f).setDuration(600).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    imageView.setVisibility(View.VISIBLE);
                    startActivity(i);
                    overridePendingTransition(R.anim.activity_up, R.anim.activity_down);
                    finish();
                }
            });

        }
    }
}
