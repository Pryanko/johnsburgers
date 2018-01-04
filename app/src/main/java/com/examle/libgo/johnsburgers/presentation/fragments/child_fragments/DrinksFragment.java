package com.examle.libgo.johnsburgers.presentation.fragments.child_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.App;
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.parcelers.DrinkResponse;
import com.examle.libgo.johnsburgers.data.pojos.MenuMeal;
import com.examle.libgo.johnsburgers.presentation.adapters.ItemAdapter;
import com.examle.libgo.johnsburgers.presentation.presenters.fragments_presenters.DrinksPresenter;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @author libgo (08.12.2017)
 */

public class DrinksFragment extends MvpAppCompatFragment {
    @BindView(R.id.recyclerViewItemDrinks)
    RecyclerView recyclerView;

    private ItemAdapter itemAdapter;
    private DrinksPresenter drinksPresenter = App.getAppComponent().getDrinkPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemAdapter = new ItemAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        ButterKnife.bind(this, view);
        drinksPresenter.setView(this);
        drinksPresenter.createView();
        return view;
    }

    public void startViewShow() {
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        itemAdapter.addList(drinksPresenter.getListMenu());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }
}


