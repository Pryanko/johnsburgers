package com.examle.libgo.johnsburgers.tools;

import android.graphics.Color;

import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.pojos.ItemShop;
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
        basket.setBadgeBackgroundColor(Color.parseColor("#352f2d"));
        basket.setBadgeCount(realm.where(ItemShop.class).findAll().size());
    }



}
