package com.examle.libgo.johnsburgers.tools;

import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.examle.libgo.johnsburgers.data.pojos.News;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;

import io.realm.Realm;

/**
 * Created by libgo on 11.12.2017.
 */

public class BottomBarBadgeHelper {

    private BottomBar bottomBar;

    public void setBottomBar(BottomBar bottomBar){
        this.bottomBar = bottomBar;
    }

    public void setBottomBadge(){
        Realm realm = Realm.getDefaultInstance();
        BottomBarTab basket = bottomBar.getTabWithId(R.id.tab_basket);
        //basket.setBadgeBackgroundColor(getResources().getColor(R.color.colorPrimary));
        basket.setBadgeCount(realm.where(MenuMeal.class).findAll().size());
    }



}
