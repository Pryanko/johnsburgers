package com.examle.libgo.johnsburgers.presentation.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.fragments.BasketFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.InfoFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.MenuFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadActivity extends MvpAppCompatActivity {
    //Bind View
    @BindView(R.id.app_toolbar)
    Toolbar toolbar;
    @BindView(R.id.textToolbar)
    TextView textToolbar;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    //Fragments
    InfoFragment infoFragment;
    MenuFragment menuFragment;
    BasketFragment basketFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);
        initFragment();
        tabSelectListener();

        if (savedInstanceState == null){
            replaceFragment(infoFragment);
        }
    }

    private void tabSelectListener(){
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
              switch (tabId){
                  case R.id.tab_burger_info:
                      replaceFragment(infoFragment);
                      break;
                  case R.id.tab_menu:
                      replaceFragment(menuFragment);
                      break;
                  case R.id.tab_basket:
                      replaceFragment(basketFragment);
                      break;
              }
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragments, fragment)
                .commit();
    }

    protected void initFragment(){
        if (infoFragment == null) {
            infoFragment = new InfoFragment();
        }
        if (menuFragment == null) {
            menuFragment = new MenuFragment();
        }
        if (basketFragment == null) {
            basketFragment = new BasketFragment();
        }
    }


}
