package com.examle.libgo.johnsburgers.tools;

import android.graphics.Color;
import com.examle.libgo.johnsburgers.R;
import com.roughike.bottombar.BottomBar;
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

    public void setBottomBar(BottomBar bottomBar){
        basket = bottomBar.getTabWithId(R.id.tab_basket);
        basket.setBadgeBackgroundColor(Color.parseColor(COLOR_STRING));
    }

    public void changeBottomBadge(){
        basket.setBadgeCount(dataBaseSource.getSizeTable());
    }
}
