package com.examle.libgo.johnsburgers.presentation.activities;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.examle.libgo.johnsburgers.R;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadActivity extends MvpAppCompatActivity {

    @BindView(R.id.app_toolbar)
    Toolbar toolbar;
    @BindView(R.id.textToolbar)
    TextView textToolbar;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);

        if (savedInstanceState == null){

        }

    }
}
