package com.examle.libgo.johnsburgers.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.examle.libgo.johnsburgers.presentation.fragments.child_fragments.DrinksFragment;
import com.examle.libgo.johnsburgers.presentation.fragments.child_fragments.MealsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libgo (08.12.2017)
 */

public class SwipeMenuAdapter extends FragmentPagerAdapter {

    private List<String> listItem = new ArrayList<>();

    public SwipeMenuAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addTabTitle(String s) {
        listItem.add(s);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listItem.get(position);

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
