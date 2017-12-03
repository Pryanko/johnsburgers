package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.examle.libgo.johnsburgers.presentation.fragments.BasketFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.InfoFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.MenuFragment;

/**
 * Created by libgo on 03.12.2017.
 */

public class SwipeAdapter extends FragmentPagerAdapter {



    public SwipeAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InfoFragment();

            case 1:
                return new MenuFragment();

            case 2:
                return new BasketFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
