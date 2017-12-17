package com.examle.libgo.johnsburgers.presentation.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.adapters.SwipeAdapter;
import com.examle.libgo.johnsburgers.presentation.anim.ViewAnimationFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.BasketFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.InfoFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.MenuFragment;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.roughike.bottombar.BottomBar;
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

    private InfoFragment infoFragment;
    private MenuFragment menuFragment;
    private BasketFragment basketFragment;
    private BottomBarBadgeHelper bottomBarBadgeHelper = App.getAppComponent().getBottomBarBadgeHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        ButterKnife.bind(this);
        infoFragment = new InfoFragment();
        menuFragment = new MenuFragment();
        basketFragment = new BasketFragment();
        setSwipeOptions();
        tabSelectListener();
        viewPageListener();
        setBottomBarBadge();
    }

      private void tabSelectListener(){
        bottomBar.setOnTabSelectListener(tabId -> {
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
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), infoFragment, menuFragment, basketFragment);
        viewPager.setAdapter(swipeAdapter);
        viewPager.setPageTransformer(true, new ViewAnimationFragment());
    }

    private void setBottomBarBadge(){
        bottomBarBadgeHelper.setBottomBar(bottomBar);
        bottomBarBadgeHelper.changeBottomBadge();
    }
}




