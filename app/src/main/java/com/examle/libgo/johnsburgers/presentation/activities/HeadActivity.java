package com.examle.libgo.johnsburgers.presentation.activities;

import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.adapters.SwipeAdapter;
import com.examle.libgo.johnsburgers.presentation.anim.ViewAnimationFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
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
    @BindView(R.id.swipePager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            setBottomBarBadge();
            setSwipeOptions();
            tabSelectListener();
            viewPageListener();


        }
    }

    private void tabSelectListener(){
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
              switch (tabId){
                  case R.id.tab_burger_info:
                      viewPager.setCurrentItem(0);
                      textToolbar.setText(R.string.app_name);
                      break;
                  case R.id.tab_menu:
                      viewPager.setCurrentItem(1);
                      textToolbar.setText(R.string.menu_tab);
                      break;
                  case R.id.tab_basket:
                      viewPager.setCurrentItem(2);
                      textToolbar.setText(R.string.basket_tab);
                      break;
              }
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setSwipeOptions(){
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setPageTransformer(true, new ViewAnimationFragment());

    }

    private void setBottomBarBadge(){
        BottomBarTab basket = bottomBar.getTabWithId(R.id.tab_basket);
        basket.setBadgeBackgroundColor(getResources().getColor(R.color.colorPrimary));
        basket.setBadgeCount(21);

    }
}
