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
import com.examle.libgo.johnsburgers.R;
import com.examle.libgo.johnsburgers.data.parcelers.MealsResponse;
import com.examle.libgo.johnsburgers.data.pojos.MenuMealsAll;
import com.examle.libgo.johnsburgers.network.ApiService;
import com.examle.libgo.johnsburgers.presentation.adapters.MealsAllAdapter;

import org.parceler.Parcels;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import static com.examle.libgo.johnsburgers.tools.constants.ConstApp.KEY_MODEL_MEALS;
/**
 * @author libgo (08.12.2017)
 */
public class MealsFragment extends MvpAppCompatFragment {

    private MealsResponse mealsResponse;
    @BindView(R.id.recyclerViewAllMeals)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meals, container, false);
        ButterKnife.bind(this, view);
        if(savedInstanceState == null){
            downloadData();
        }
        if(savedInstanceState != null){
            mealsResponse = Parcels.unwrap(savedInstanceState.getParcelable(KEY_MODEL_MEALS));
            startViewMeals(mealsResponse);
        }

        return view;
    }

    private void downloadData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        compositeDisposable.add(apiService.getMenuMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::startViewMeals, this::handleError));
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }

    private void startViewMeals(MealsResponse mealsResponse) {
        this.mealsResponse = mealsResponse;
        List<MenuMealsAll> list = mealsResponse.getMenuMealsAll();
        MealsAllAdapter mealsAllAdapter = new MealsAllAdapter(list, getActivity());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(mealsAllAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_MODEL_MEALS, Parcels.wrap(mealsResponse));
    }
}



