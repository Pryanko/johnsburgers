package com.examle.libgo.johnsburgers.presentation.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.activities.contracts.ViewBase;
import com.examle.libgo.johnsburgers.presentation.adapters.SwipeAdapter;
import com.examle.libgo.johnsburgers.presentation.anim.ViewAnimationFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.BasketFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.InfoFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.MenuFragment;
import com.examle.libgo.johnsburgers.presentation.activities.contracts.BackPressedFragments;
import com.examle.libgo.johnsburgers.presentation.presenters.HeadPresenters;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadActivity extends MvpAppCompatActivity implements ViewBase {

    //Bind View
    @BindView(R.id.app_toolbar)
    Toolbar toolbar;
    @BindView(R.id.textToolbar)
    TextView textToolbar;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.swipePager)
    ViewPager viewPager;

    private InfoFragment infoFragment;
    private MenuFragment menuFragment;
    private BasketFragment basketFragment;
    private HeadPresenters headPresenters = App.getAppComponent().getHeadPresenters();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);

        headPresenters.setHeadView(this, bottomBar);
        headPresenters.createView();
    }


    @Override
    public void onPlayShow() {
        initFragments();
        tabSelectListener();
        viewPageListener();
        setSwipeOptions();

    }

    @Override
    public void onError() {

    }

    private void initFragments(){
        infoFragment = new InfoFragment();
        menuFragment = new MenuFragment();
        basketFragment = new BasketFragment();
    }


    private void tabSelectListener(){
        bottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId){
                case R.id.tab_burger_info:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.tab_menu:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.tab_basket:
                    viewPager.setCurrentItem(2);
                    break;
            }
        });
    }

    private void  viewPageListener(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 bottomBar.selectTabAtPosition(position, true);
                 headPresenters.setChangePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setSwipeOptions(){
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), infoFragment, menuFragment, basketFragment);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setPageTransformer(true, new ViewAnimationFragment());
    }

    public void changeTextToolbar(String text){
        textToolbar.setText(text);
    }

    @Override
    public void onBackPressed() {

        BackPressedFragments backPressedFragments = null;
        for (Fragment fragment: getSupportFragmentManager().getFragments()) {
            if (fragment instanceof  BackPressedFragments) {
                backPressedFragments = (BackPressedFragments) fragment;
                break;
            }
        }
        if (backPressedFragments!=null){
            backPressedFragments.onBackPressed();
        }

        super.onBackPressed();

    }

}







