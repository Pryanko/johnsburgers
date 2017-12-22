package com.examle.libgo.johnsburgers.tools;

import android.graphics.Color;
import com.roughike.bottombar.BottomBarTab;
import static com.examle.libgo.johnsburgers.tools.constants.ConstApp.COLOR_STRING;

/**
 * @author libgo (11.12.2017)
 */
public class BottomBarBadgeHelper {

    private DataBaseSource dataBaseSource;
    private BottomBarTab basket;

    public BottomBarBadgeHelper(DataBaseSource dataBaseSource){
        this.dataBaseSource = dataBaseSource;
    }

    public void setBottomBar(BottomBarTab basket){
        this.basket = basket;
        this.basket.setBadgeBackgroundColor(Color.parseColor(COLOR_STRING));
    }

    public void changeBottomBadge(){
        basket.setBadgeCount(dataBaseSource.getSizeTable());
    }
}
