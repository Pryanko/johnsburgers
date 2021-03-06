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
import com.examle.libgo.johnsburgers.presentation.adapters.SwipeMenuAdapter;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.ViewFragmentsBase;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libgo (03.12.2017)
 */

public class MenuFragment extends MvpAppCompatFragment implements ViewFragmentsBase{

    //Bind
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerMenu)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        //run
        onPlayShow();
        return view;
    }


    @Override
    public void onPlayShow() {

        SwipeMenuAdapter swipeMenuAdapter = new SwipeMenuAdapter(getChildFragmentManager());
        swipeMenuAdapter.addTabTitle(getResources().getString(R.string.text_menu_MEALS));
        swipeMenuAdapter.addTabTitle(getResources().getString(R.string.text_menu_DRINKS));
        viewPager.setAdapter(swipeMenuAdapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onError() {

    }
}
