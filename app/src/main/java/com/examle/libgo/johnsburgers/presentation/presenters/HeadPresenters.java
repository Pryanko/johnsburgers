package com.examle.libgo.johnsburgers.presentation.presenters;

import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.activities.HeadActivity;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.PresenterBase;
import com.examle.libgo.johnsburgers.tools.BottomBarBadgeHelper;
import com.roughike.bottombar.BottomBar;

/**
 * @author libgo (04.12.2017)
 */

public class HeadPresenters {

    /**
     * По факту данный перезентер не требуется. Он не выполняет (практически) ни какой логики наполнения вью.
     * Не считая текстовой вьюшки в тулбаре. Презентер создан лишь в учебно-практических целях.
     * Возможно - это совершенно не правильный подход к MVP - возможно все переделаю. 22-12-2017
     */

    private HeadActivity headView;
    private BottomBarBadgeHelper bottomBarBadgeHelper;
    private BottomBar bottomBarTab;

    public HeadPresenters(BottomBarBadgeHelper badgeHelper){
        this.bottomBarBadgeHelper = badgeHelper;
    }

    public void setHeadView(HeadActivity activity, BottomBar barTab){
        this.headView = activity;
        this.bottomBarTab = barTab;
    }

    public void createView(){
        headView.onPlayShow();
        setBottomBarBadge();
    }

    private void setBottomBarBadge(){
        bottomBarBadgeHelper.setBottomBar(bottomBarTab);
        bottomBarBadgeHelper.changeBottomBadge();
    }

    public void setChangePosition(int position){

        switch (position){
            case 0:
                headView.changeTextToolbar(headView.getString(R.string.app_name));
                break;
            case 1:
                headView.changeTextToolbar(headView.getString(R.string.menu_tab));
                break;
            case 2:
                headView.changeTextToolbar(headView.getString(R.string.basket_tab));
                break;
        }
    }

    public void errorView() {

    }

    public void destroyView() {
        headView = null;
    }
}
