package com.examle.libgo.johnsburgers.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.presentation.adapters.SwipeMenuAdapters;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by libgo on 03.12.2017.
 */

public class MenuFragment extends MvpAppCompatFragment {

    //Bind
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerMenu)
    ViewPager viewPager;
    private SwipeMenuAdapters swipeMenuAdapters;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        swipeMenuAdapters = new SwipeMenuAdapters(getChildFragmentManager());
        viewPager.setAdapter(swipeMenuAdapters);
        tabLayout.setupWithViewPager(viewPager);
        return view;




    }
}
