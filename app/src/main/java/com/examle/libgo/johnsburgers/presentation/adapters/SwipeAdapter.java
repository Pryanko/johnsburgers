package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.examle.libgo.johnsburgers.presentation.fragments.BasketFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.InfoFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.MenuFragment;

/**
 * Created by libgo on 03.12.2017.
 */

public class SwipeAdapter extends FragmentPagerAdapter {

    private InfoFragment infoFragment = new InfoFragment();
    private MenuFragment menuFragment = new MenuFragment();
    private BasketFragment basketFragment = new BasketFragment();

    public SwipeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    @NonNull
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        switch (position) {
            case 0:

                infoFragment = (InfoFragment) createdFragment;
                break;
            case 1:

                menuFragment = (MenuFragment) createdFragment;
                break;
            case 2:

                basketFragment = (BasketFragment) createdFragment;
        }
        return createdFragment;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return infoFragment;

            case 1:
                return menuFragment;

            case 2:
                return basketFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
