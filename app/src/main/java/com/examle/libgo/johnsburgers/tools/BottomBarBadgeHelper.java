package com.examle.libgo.johnsburgers.tools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.view.View;
import com.examle.libgo.johnsburgers.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import static com.examle.libgo.johnsburgers.tools.constants.ConstApp.COLOR_STRING;

/**
 * @author libgo (11.12.2017)
 */
public class BottomBarBadgeHelper {

    /**
     * Класс помошник для работы с BottomBar. Управляем счетчиком (итемов корзины) Badge.
     * Так же уплавляем состоянием видимости ББара, инжектим в логику общей HeadActivity, и OrderFragment оформления
     * заказа.
     */

    private DataBaseSource dataBaseSource;
    private BottomBar bottomBar;
    private BottomBarTab basket;
    private boolean bottomBarState = true;

    public BottomBarBadgeHelper(DataBaseSource dataBaseSource){
        this.dataBaseSource = dataBaseSource;
    }

    public void setBottomBar(BottomBar bottomBar){
        this.bottomBar = bottomBar;
        basket = bottomBar.getTabWithId(R.id.tab_basket);
        basket.setBadgeBackgroundColor(Color.parseColor(COLOR_STRING));
        setVisibleBottomBar(bottomBarState);
    }

    public void changeBottomBadge(){
        basket.setBadgeCount(dataBaseSource.getSizeTable());
    }

    public void setVisibleBottomBar(boolean stateBottomBar){
        if(stateBottomBar){
            onBottomBar();
            setBottomBarState(true);
        }else {
            offBottomBar();
            setBottomBarState(false);
        }
    }

    public boolean isBottomBarState() {
        return bottomBarState;
    }

    private void offBottomBar(){
            bottomBar.animate().translationY(bottomBar.getHeight()).alpha(0.0f).setDuration(450).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    bottomBar.setVisibility(View.GONE);
                }
            });
    }

    private void onBottomBar(){
            bottomBar.setVisibility(View.VISIBLE);
            bottomBar.animate().translationY(0).alpha(1.0f).setDuration(600).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });

    }

    private void setBottomBarState(boolean bottomBar_state) {
        this.bottomBarState = bottomBar_state;
    }
}

