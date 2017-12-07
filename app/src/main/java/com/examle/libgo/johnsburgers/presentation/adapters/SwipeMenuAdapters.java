package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.examle.libgo.johnsburgers.presentation.fragments.child_fragments.DrinksFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.child_fragments.MealsFragment;

/**
 * Created by libgo on 08.12.2017.
 */

public class SwipeMenuAdapters extends FragmentPagerAdapter {


    public SwipeMenuAdapters(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new MealsFragment();

            case  1:
                return new DrinksFragment();

            default:
                return null;

        }




    }

    @Override
    public int getCount() {
        return 2;
    }
}
